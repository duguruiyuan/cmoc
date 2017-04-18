package com.xuequ.cmoc.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.thoughtworks.xstream.mapper.Mapper.Null;
import com.xuequ.cmoc.common.Configuration;
import com.xuequ.cmoc.common.WechatConfigure;
import com.xuequ.cmoc.common.enums.WechatReqMsgType;
import com.xuequ.cmoc.core.wechat.common.Constants;
import com.xuequ.cmoc.core.wechat.message.InputMessage;
import com.xuequ.cmoc.core.wechat.model.WechatQrcodeRsp;
import com.xuequ.cmoc.core.wechat.utils.FileUtil;
import com.xuequ.cmoc.core.wechat.utils.MessageUtil;
import com.xuequ.cmoc.core.wechat.utils.WechatUtils;
import com.xuequ.cmoc.model.ActivityHmSign;
import com.xuequ.cmoc.model.ImgGroup;
import com.xuequ.cmoc.model.ProductOrder;
import com.xuequ.cmoc.model.WechatReceiveMessage;
import com.xuequ.cmoc.model.WechatSnsToken;
import com.xuequ.cmoc.model.WechatSnsUserInfo;
import com.xuequ.cmoc.model.WechatUserInfo;
import com.xuequ.cmoc.service.IContentManageService;
import com.xuequ.cmoc.service.IProductOrderService;
import com.xuequ.cmoc.service.ISysDictService;
import com.xuequ.cmoc.utils.ImageUtils;
import com.xuequ.cmoc.utils.QRCoderUtils;
import com.xuequ.cmoc.utils.RequestUtil;
import com.xuequ.cmoc.utils.TextUtil;
import com.xuequ.cmoc.vo.ImageSynthesisVo;

/**
* <p>Title: BaseController </p>
* <p>Description: </p>
* @author pei.wang
* @date 2016年2月25日 下午2:38:10
*/
public class BaseController {
	
	private Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	@Autowired
	protected HttpSession session;

	@Autowired
	protected HttpServletRequest request;
	
	@Autowired
	protected ISysDictService sysDictService;
	@Autowired
	protected IContentManageService contentManageService;
	@Autowired
	protected IProductOrderService productOrderService;
	
	/**
	 * 视图数据绑定模型前将字符串类型时间转型为日期格式
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@ModelAttribute("config")
	protected Configuration configInstance() {
		return Configuration.getInstance();
	}
	
	@ModelAttribute
	protected void basePath() {
		if(StringUtils.isBlank(Constants.BASEPATH)) {
			Constants.BASEPATH = RequestUtil.getBasePath(request);
		}
	}
	
	public WechatUserInfo getWechatUserInfo() {
		return (WechatUserInfo) session.getAttribute(Constants.WECHAT_USERINFO);
	}
	
	/**
	 * 是否关注跳转
	 * @auther 胡启萌
	 * @Date 2016年12月3日
	 * @param model
	 * @param viewUrl
	 * @return
	 */
	public String wechatRedirect(Model model, String viewUrl) {
		WechatSnsToken snsToken = null;
		WechatUserInfo userInfo = null;
		String type = request.getParameter("type");
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		logger.info("--type={},code={},state={}", type, code, state);
		if("scope".equals(type)) {
			String openid = request.getParameter("openid");
			userInfo = WechatUtils.getUserInfo(WechatUtils.getWechatModel().getAccessToken(), openid);
		}else {
			if(StringUtils.isNotBlank(state) && StringUtils.isBlank(code)) {
				return "redirect:/" + Configuration.getInstance().getWechatAttention();
			}
			if(StringUtils.isNotBlank(code)) {
				snsToken = WechatUtils.getOpenidByCode(code);
				userInfo = WechatUtils.getUserInfo(WechatUtils.getWechatModel().getAccessToken(),
						snsToken.getOpenid());
			}else {
				WechatConfigure configure = WechatConfigure.getInstance();
				String url = TextUtil.format(configure.getOauth2Userinfo(), 
						new String[]{configure.getAppid()});
				model.addAttribute("wechat_redirect", url);
				return "openid.redir";
			}
		}
		if(userInfo != null && userInfo.getSubscribe() == 0) {
			return "redirect:/" + Configuration.getInstance().getWechatAttention();
		}
		model.addAttribute("snsToken", snsToken);
		model.addAttribute("userInfo", userInfo);
		return viewUrl;
	}
	/**
	 * 网页授权跳转
	 * @auther 胡启萌
	 * @Date 2016年12月3日
	 * @param model
	 * @param viewUrl
	 * @return
	 */
	public String wechatWebAuthRedirect(Model model, String viewUrl) {
		WechatSnsToken snsToken = null;
		WechatSnsUserInfo userInfo = null;
		String type = request.getParameter("type");
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		logger.info("--type={},code={},state={}", type, code, state);
		if("scope".equals(type)) {
			String refreshToken = request.getParameter("token");
			snsToken = WechatUtils.getOpenidByRefreshToken(refreshToken);
			userInfo = WechatUtils.getSnsUserInfo(
					snsToken.getAccess_token(), snsToken.getOpenid());
		}else {
			code = request.getParameter("code");
			state = request.getParameter("state");
			if(StringUtils.isNotBlank(state) && StringUtils.isBlank(code)) {
				return "redirect:/" + Configuration.getInstance().getWechatAttention();
			}
			if(StringUtils.isNotBlank(code)) {
				snsToken = WechatUtils.getOpenidByCode(code);
				userInfo = WechatUtils.getSnsUserInfo(
						snsToken.getAccess_token(), snsToken.getOpenid());
			}else {
				WechatConfigure configure = WechatConfigure.getInstance();
				String url = TextUtil.format(configure.getOauth2Userinfo(), 
						new String[]{configure.getAppid()});
				model.addAttribute("wechat_redirect", url);
				return "web.redir";
			}
		}
		model.addAttribute("snsToken", snsToken);
		model.addAttribute("userInfo", userInfo);
		return viewUrl;
	}
	
	/**
	 * 头部广告
	 * @param model
	 */
	public void topBannerList(Model model) {
		ImgGroup group = new ImgGroup();
		group.setPosition("1");
		group.setShelves(1);
		model.addAttribute("topBannerList",contentManageService.selectListByParam(group));
	}
	
	class PosterImgExecutor implements Runnable {
		
		private String orderNo;
		
		private WechatUserInfo userInfo;
		
		private String realPath;
		
		public PosterImgExecutor(String orderNo, WechatUserInfo userInfo, String realPath) {
			this.orderNo = orderNo;
			this.userInfo = userInfo;
			this.realPath = realPath;
		}

		@Override
		public void run() {
			try { 
				ProductOrder productOrder = productOrderService.selectByOrderNo(orderNo);
				if(productOrder != null && StringUtils.isBlank(productOrder.getPosterImg())) {
					List<ImageSynthesisVo> list = new ArrayList<>();
					ImageSynthesisVo vo1 = new ImageSynthesisVo(userInfo.getHeadimgurl(), 65, 230, 100, 100);
					WechatQrcodeRsp rsp = WechatUtils.getQrcode(MessageUtil.QR_LIMIT_SCENE, 
							MessageUtil.POSTER_MEMBER, String.valueOf(productOrder.getId()));
					ImageSynthesisVo vo2 = new ImageSynthesisVo(rsp.getQrcode(), 165, 405, 130, 130);
					list.add(vo1);
					list.add(vo2);
					String fileSrc = realPath + "images/poster-share1.png";
					String outSrcName = orderNo + ".jpg";
					String outSrc = QRCoderUtils.getPosterRealImgUrl() + File.separator + outSrcName;
					ImageUtils.composePic(fileSrc, outSrc, list, 642, 1444);
					productOrder.setPosterImg(QRCoderUtils.getPosterRspImgUrl(outSrcName));
					productOrderService.updateById(productOrder);
				}
	        } catch (Exception e) { 
	            e.printStackTrace(); 
	        }
		}
	}
	
}

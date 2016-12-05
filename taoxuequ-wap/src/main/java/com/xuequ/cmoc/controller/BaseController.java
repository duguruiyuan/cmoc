package com.xuequ.cmoc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.xuequ.cmoc.common.Configuration;
import com.xuequ.cmoc.common.WechatConfigure;
import com.xuequ.cmoc.core.wechat.utils.WechatUtils;
import com.xuequ.cmoc.model.WechatSnsToken;
import com.xuequ.cmoc.model.WechatSnsUserInfo;
import com.xuequ.cmoc.model.WechatUserInfo;
import com.xuequ.cmoc.utils.TextUtil;

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
	
}

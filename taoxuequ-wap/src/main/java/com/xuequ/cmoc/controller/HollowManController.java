package com.xuequ.cmoc.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.enums.ImgTypeEnum;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.core.wechat.model.WechatQrcodeRsp;
import com.xuequ.cmoc.core.wechat.utils.MessageUtil;
import com.xuequ.cmoc.core.wechat.utils.WechatUtils;
import com.xuequ.cmoc.model.ActivityChild;
import com.xuequ.cmoc.model.ActivityInfo;
import com.xuequ.cmoc.model.ActivityMarines;
import com.xuequ.cmoc.model.HollowManInfo;
import com.xuequ.cmoc.model.WechatSnsToken;
import com.xuequ.cmoc.service.IActivityChildService;
import com.xuequ.cmoc.service.IActivityHmService;
import com.xuequ.cmoc.service.IActivityMarinesService;
import com.xuequ.cmoc.service.IActivityService;
import com.xuequ.cmoc.service.IHollowManService;
import com.xuequ.cmoc.view.ActivityChildView;
import com.xuequ.cmoc.view.ActivityHmSignView;
import com.xuequ.cmoc.view.ActivityMarinesView;
import com.xuequ.cmoc.vo.MarineEditVO;

@RequestMapping("hm")
@Controller
public class HollowManController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(HollowManController.class);
	
	@Autowired
	private IHollowManService hollowManService;
	@Autowired
	private IActivityService activityService;
	@Autowired
	private IActivityMarinesService activityMarinesService;
	@Autowired
	private IActivityChildService activityFamilyService;
	@Autowired
	private IActivityHmService activityHmService;
	
	@RequestMapping(value={"","/"})
	public String index(Model model) {
		String page = "hm/hmMy";
		String redir = wechatRedirect(model, page);
		if(redir.equals(page)) {
			WechatSnsToken token = (WechatSnsToken) model.asMap().get("snsToken");
			String openid = request.getParameter("openid");
			if(token != null || StringUtils.isNotBlank(openid)) {
				if(token != null) openid = token.getOpenid();
				HollowManInfo hm = hollowManService.selectByOpenid(openid);
				model.addAttribute("hm", hm);
				if(hm != null && hm.getIsActive() == 1) {
					return "hm/hmMy";
				}
			}
		}
		return redir;
	}
	
	/**
	 * 透明人注册
	 * @auther 胡启萌
	 * @Date 2016年12月3日
	 * @param hm
	 * @return
	 */
	@RequestMapping("register")
	public @ResponseBody Object register(HollowManInfo hm) {
		try {
			if(StringUtils.isBlank(hm.getOpenid())){
				return new RspResult(StatusEnum.OPENID_DEFECT);
			}
			return hollowManService.addAndUpdateHollowMan(hm, null);
		} catch (Exception e) {
			logger.debug("---hm register, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
	
	@RequestMapping("act/list")
	public String activityList(Model model) {
		List<ActivityInfo> list = activityService.selectBeginingActivity(null);
		model.addAttribute("activitys", list);
		return "hm/activityList";
	}
	
	@RequestMapping("act/{activityId}")
	public String activityQuery(Model model, @PathVariable Integer activityId) {
		List<ActivityMarinesView> list = activityMarinesService.selectMarineTeam(activityId);
		for(ActivityMarinesView view : list) {
			boolean isUpdate = false;
			if(StringUtils.isBlank(view.getBindQrcodeUrl())) {
				try {
					WechatQrcodeRsp rsp = WechatUtils.getQrcode(MessageUtil.QR_SCENE, 
							MessageUtil.BIND_TYPE_MARINE, view.getId());
					if(rsp != null) {
						view.setBindQrcodeUrl(rsp.getQrcode());
						isUpdate = true;
					}
				} catch (Exception e) {
					logger.error("--activityQuery getQrcode, error={}", e);
				}
			}
			if(StringUtils.isBlank(view.getSupportQrcodeUrl())) {
				try {
					WechatQrcodeRsp rsp = WechatUtils.getQrcode(MessageUtil.QR_SCENE, 
							MessageUtil.SUPPORT_MARINE, view.getId());
					if(rsp != null) {
						view.setSupportQrcodeUrl(rsp.getQrcode());
						isUpdate = true;
					}
				} catch (Exception e) {
					logger.error("--activityQuery getQrcode, error={}", e);
				}
			}
			if(isUpdate) activityMarinesService.updateByPrimaryKeySelective(view);
		}
		model.addAttribute("marines", list);
		return "hm/marineList";
	}
	
	/**
	 * 透明人管理战队
	 * @auther 胡启萌
	 * @Date 2016年12月3日
	 * @param marineId
	 * @return
	 */
	@RequestMapping("manage/marine")
	public String bindMarine(Model model) {
		String page = "hm/marineManage";
		String redir = wechatRedirect(model, page);
		if(redir.equals(page)) {
			WechatSnsToken token = (WechatSnsToken) model.asMap().get("snsToken");
			String openid = request.getParameter("openid");
			if(token != null || StringUtils.isNotBlank(openid)) {
				if(token != null) openid = token.getOpenid();
				ActivityMarinesView marine = activityMarinesService.selectMarineByHmOpenid(openid);
				if(marine != null) {
					List<ActivityChildView> families = activityFamilyService.selectListByMarineId(marine.getId());
					model.addAttribute("marine", marine);
					model.addAttribute("families", families);
				}
			}
		}
		return redir;
	}
	
	@RequestMapping("marine/edit/query")
	public @ResponseBody Object marineEditQuery(Integer id, String type) {
		try {
			if(ImgTypeEnum.MARINE.getCode().equals(type)){
				ActivityMarines marins = activityMarinesService.selectByPrimaryKey(id);
				return new RspResult(StatusEnum.SUCCESS, marins);
			}else if(ImgTypeEnum.MEMBER.getCode().equals(type)) {
				 ActivityChild family = activityFamilyService.selectByPrimaryKey(id);
				 return new RspResult(StatusEnum.SUCCESS, family);
			}
		} catch (Exception e) {
			logger.error("--marineEditQuery, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
	
	@RequestMapping("marine/edit/submit")
	public @ResponseBody Object marineEditSubmit(MarineEditVO vo) {
		try {
			if(ImgTypeEnum.MARINE.getCode().equals(vo.getType())){
				ActivityMarines marines = new ActivityMarines();
				marines.setId(vo.getId());
				marines.setMarineName(vo.getMarineName());
				marines.setMarinePrize(vo.getMarinePrize());
				marines.setMarineSlogan(vo.getMarineSlogan());
				marines.setComment(vo.getComment());
				marines.setScore(vo.getScore());
				activityMarinesService.updateByPrimaryKeySelective(marines);
				return new RspResult(StatusEnum.SUCCESS);
			}else if(ImgTypeEnum.MEMBER.getCode().equals(vo.getType())) {
				 ActivityChild family = new ActivityChild();
				 family.setId(vo.getId());
				 family.setChildComment(vo.getChildComment());
				 family.setChildTitle(vo.getChildTitle());
				 activityFamilyService.addAndUpdateChild(family);
				 return new RspResult(StatusEnum.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("--marineEditSubmit, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	} 
	
	@RequestMapping("withteam")
	public String withTeam(Model model) {
		String page = "hm/withTeam";
		String redir = wechatRedirect(model, page);
		if(redir.equals(page)) {
			WechatSnsToken token = (WechatSnsToken) model.asMap().get("snsToken");
			String openid = request.getParameter("openid");
			if(token != null || StringUtils.isNotBlank(openid)) {
				if(token != null) openid = token.getOpenid();
				List<ActivityHmSignView> records = activityHmService.selectWithTeamRecord(openid);
				HollowManInfo hm = hollowManService.selectByOpenid(openid);
				model.addAttribute("records", records);
				model.addAttribute("hm", hm);
			}
		}
		return redir;
	}
	
	@RequestMapping("act/sign")
	public String activitySign(Model model) {
		List<ActivityInfo> list = activityService.selectScheduActivity(null);
		for(ActivityInfo view : list) {
			if(StringUtils.isBlank(view.getQrcodeUrl())) {
				try {
					WechatQrcodeRsp rsp = WechatUtils.getQrcode(MessageUtil.QR_LIMIT_SCENE, 
							MessageUtil.ACTIVITY_SIGN, view.getId());
					if(rsp != null) {
						view.setQrcodeUrl(rsp.getQrcode());
						activityService.updateByPrimaryKey(view);
					}
				} catch (Exception e) {
					logger.error("--activityQuery getQrcode, error={}", e);
				}
			}
		}
		model.addAttribute("activitys", list);
		return "hm/activitySign";
	}
	
}

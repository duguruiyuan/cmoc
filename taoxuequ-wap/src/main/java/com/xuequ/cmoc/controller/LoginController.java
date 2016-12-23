package com.xuequ.cmoc.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xuequ.cmoc.common.Configuration;
import com.xuequ.cmoc.core.wechat.common.Constants;
import com.xuequ.cmoc.core.wechat.utils.WechatUtils;
import com.xuequ.cmoc.model.WechatSnsToken;
import com.xuequ.cmoc.model.WechatUserInfo;
import com.xuequ.cmoc.utils.RequestUtil;

@Controller
public class LoginController extends BaseController {

	@RequestMapping("wechatauth")
	public String wechatauth(HttpServletResponse response) throws Exception {
		WechatSnsToken snsToken = null;
		WechatUserInfo userInfo = null;
		String code = request.getParameter("code");
		String self = request.getParameter("self");
		if(StringUtils.isNotBlank(code)) {
			snsToken = WechatUtils.getOpenidByCode(code);
			if(snsToken == null) {
				response.sendRedirect(Configuration.getInstance().getWechatAttention());
				return null;
			}
			userInfo = WechatUtils.getUserInfo(WechatUtils.getWechatModel().getAccessToken(),
					snsToken.getOpenid());
		}
		if(userInfo != null && userInfo.getSubscribe() == 1) {
			RequestUtil.saveCookie(response, "openid", userInfo.getOpenid(), (90 * 24 * 60 * 60));
			session.setAttribute(Constants.WECHAT_USERINFO, userInfo);
			return "redirect:" + self;
		}
		response.sendRedirect(Configuration.getInstance().getWechatAttention());
		return null;
	}
}

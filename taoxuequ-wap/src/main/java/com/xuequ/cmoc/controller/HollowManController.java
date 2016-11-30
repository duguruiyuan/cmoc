package com.xuequ.cmoc.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xuequ.cmoc.common.WechatConfigure;
import com.xuequ.cmoc.utils.TextUtil;

@RequestMapping("hm")
@Controller
public class HollowManController extends BaseController {

	@RequestMapping(value={"","/"})
	public String index(Model model) {
		String code = request.getParameter("code");
		if(StringUtils.isBlank(code)) {
			model.addAttribute("oauth2Base", TextUtil.format(WechatConfigure.
					getInstance().getOauth2Base(), WechatConfigure.getInstance().getAppid()));
			return "oauth";
		}else {
			
		}
		return "hm";
	}
}

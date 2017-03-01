package com.xuequ.cmoc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuequ.cmoc.common.Constants;
import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.model.ImgGroup;
import com.xuequ.cmoc.model.SysUser;
import com.xuequ.cmoc.service.IContentManageService;

@RequestMapping("content")
@Controller
public class ContentController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(ContentController.class);
	
	@Autowired
	private IContentManageService contentManageService;

	@RequestMapping("img/group")
	private String imgGroup() {
		return "content/imgGroup";
	}
	
	@RequestMapping("json/imgGroup/position")
	@ResponseBody Object imgGroupByPosition(String position) {
		ImgGroup group = new ImgGroup();
		group.setPosition(position);
		return contentManageService.selectListByParam(group);
	}
	
	@RequestMapping("json/imgGroup/id")
	@ResponseBody Object imgGroupById(Integer id) {
		return contentManageService.selectImgGroupById(id);
	}
	
	@RequestMapping("json/imgGroup/addUpdate")
	@ResponseBody Object addUpdateImgGroup(ImgGroup group){
		try {
			SysUser user = (SysUser) session.getAttribute(Constants.APP_USER);
			contentManageService.addUpdateImgGroup(group, user);
			return new RspResult(StatusEnum.SUCCESS);
		} catch (Exception e) {
			logger.error("--addUpdateImgGroup, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
	
	
	@RequestMapping("keyword")
	public String index() {
		return "content/keyword";
	}
}

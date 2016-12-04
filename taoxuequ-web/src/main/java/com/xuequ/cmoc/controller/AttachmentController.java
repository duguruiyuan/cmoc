package com.xuequ.cmoc.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xuequ.cmoc.common.Const;
import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.enums.ImgTypeEnum;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.model.ActivityFamily;
import com.xuequ.cmoc.model.ActivityMarines;
import com.xuequ.cmoc.service.IActivityFamilyService;
import com.xuequ.cmoc.service.IActivityMarinesService;
import com.xuequ.cmoc.service.IActivityService;
import com.xuequ.cmoc.utils.DateUtil;
import com.xuequ.cmoc.utils.FileUtils;
import com.xuequ.cmoc.vo.AttachmentUploadVO;

@RequestMapping("attachment")
@Controller
public class AttachmentController {
	
	@Autowired
	private IActivityMarinesService activityMarinesService;
	@Autowired
	private IActivityFamilyService activityFamilyService;
	@Autowired
	private IActivityService activityService;
	
	@RequestMapping("activity/upload/img")
	@ResponseBody Object uploadImg(AttachmentUploadVO vo, 
			@RequestParam(value="files") MultipartFile buildInfo) {
		String resultUrl = null;
		if(ImgTypeEnum.MARINE.getCode().equals(vo.getType())) {
			ActivityMarines marines = activityMarinesService.selectByPrimaryKey(vo.getResourceId());
			resultUrl = imgUpload(marines.getActivityId(), vo.getResourceId(), 2, buildInfo);
		}else if(ImgTypeEnum.MEMBER.getCode().equals(vo.getType())) {
			ActivityFamily family = activityFamilyService.selectByPrimaryKey(vo.getResourceId());
			resultUrl = imgUpload(family.getActivityId(), vo.getResourceId(), 3, buildInfo);
		}else if(ImgTypeEnum.ACTIVITY.getCode().equals(vo.getType())) {
			resultUrl = imgUpload(null, vo.getResourceId(), 1, buildInfo);
		}
		return new RspResult(StatusEnum.SUCCESS, resultUrl);
	}
	
	/**
	 * 图片上传
	 * @auther 胡启萌
	 * @Date 2016年11月23日
	 * @param activityId 活动id
	 * @param resourceId 要更新图片资源Id
	 * @param type 图片类型 1活动 2战队 3队员
	 * @param buildInfo
	 */
	private String imgUpload(Integer activityId, Integer resourceId, 
			int type, MultipartFile buildInfo) {
		String path = ImgTypeEnum.ACTIVITY.getCode() + Const.SEPARATOR + 
				DateUtil.getYear(new Date()) + 
				(activityId != null ? Const.SEPARATOR + activityId : "");
		String imgUrl = null;
		imgUrl = FileUtils.writeFile(path, null, buildInfo);
		if(type == 1) {
			activityService.updateActivityImg(imgUrl, resourceId);
		}else if(type == 2) {
			activityMarinesService.updateMarineImg(imgUrl, resourceId);
		}else if(type == 3) {
			activityFamilyService.updateFamilyImg(imgUrl, resourceId);
		}
		return imgUrl;
	}
}

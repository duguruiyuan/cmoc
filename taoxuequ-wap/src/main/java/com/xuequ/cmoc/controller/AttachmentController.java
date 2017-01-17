package com.xuequ.cmoc.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuequ.cmoc.common.Const;
import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.enums.ImgTypeEnum;
import com.xuequ.cmoc.common.enums.ResourcePathEnum;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.core.wechat.utils.FileUtil;
import com.xuequ.cmoc.core.wechat.utils.MessageUtil;
import com.xuequ.cmoc.model.WechatReceiveMessage;
import com.xuequ.cmoc.service.IActivityChildService;
import com.xuequ.cmoc.service.IActivityMarinesService;
import com.xuequ.cmoc.utils.DateUtil;

@RequestMapping("attachment")
@Controller
public class AttachmentController {
	private Logger LOGGER = LoggerFactory.getLogger(AttachmentController.class);
	
	@Autowired
	private IActivityMarinesService activityMarinesService;
	@Autowired
	private IActivityChildService activityFamilyService;
	
	@RequestMapping("media/download")
	@ResponseBody Object wechatMediaDownload(Integer activityId, 
			String mediaId, String imgType, Integer id) {
		try {
			WechatReceiveMessage message = new WechatReceiveMessage();
			message.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_IMAGE);
			message.setMediaId(mediaId);
			String imgUrl = FileUtil.getRelativePath(message, activityId);
			FileUtil.downloadWechatFile(imgUrl, message, false);
			if(ImgTypeEnum.MARINE.getCode().equals(imgType)) {
				activityMarinesService.updateMarineImg(imgUrl, id);
			}else if(ImgTypeEnum.MEMBER.getCode().equals(imgType)) {
				activityFamilyService.updateFamilyImg(imgUrl, id);
			}
			return new RspResult(StatusEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("--wechatMediaDownload, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
	
	@RequestMapping("idPhoto/upload")
	@ResponseBody Object idPhotoUpload(String mediaId) {
        try {  
        	WechatReceiveMessage message = new WechatReceiveMessage();
			message.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_IMAGE);
			message.setMediaId(mediaId);
        	String path = FileUtil.getRelativePath(message, ImgTypeEnum.ID_PHOTO.getCode());
        	FileUtil.downloadWechatFile(path, message, false);
        	return new RspResult(StatusEnum.SUCCESS, path);
        } catch (Exception e) {  
            LOGGER.error("--idPhotoUpload, error={}", e);  
        }
        return new RspResult(StatusEnum.FAIL);
	}
	
}

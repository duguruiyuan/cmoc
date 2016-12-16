package com.xuequ.cmoc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuequ.cmoc.common.Configuration;
import com.xuequ.cmoc.common.Const;
import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.enums.ImgTypeEnum;
import com.xuequ.cmoc.common.enums.ResourcePathEnum;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.common.enums.WechatReqMsgType;
import com.xuequ.cmoc.core.wechat.message.InputMessage;
import com.xuequ.cmoc.core.wechat.utils.FileUtil;
import com.xuequ.cmoc.core.wechat.utils.MessageUtil;
import com.xuequ.cmoc.model.WechatReceiveMessage;
import com.xuequ.cmoc.service.IActivityChildService;
import com.xuequ.cmoc.service.IActivityMarinesService;
import com.xuequ.cmoc.utils.DateUtil;
import com.xuequ.cmoc.utils.FileUtils;

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
			String path = DateUtil.getYear(new Date()) + Const.SEPARATOR + activityId;
			String imgUrl = FileUtil.downloadWechatFile(path, message, false);
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
        	String path = ImgTypeEnum.ID_PHOTO.getCode();
        	String imgUrl = FileUtil.downloadWechatFile(path, message, false);
        	return new RspResult(StatusEnum.SUCCESS, imgUrl);
        } catch (Exception e) {  
            LOGGER.error("--idPhotoUpload, error={}", e);  
        }
        return new RspResult(StatusEnum.FAIL);
	}
	
}

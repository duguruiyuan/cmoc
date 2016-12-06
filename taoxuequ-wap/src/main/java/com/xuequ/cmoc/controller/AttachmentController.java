package com.xuequ.cmoc.controller;

import java.io.File;
import java.io.FileOutputStream;

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
import com.xuequ.cmoc.service.IActivityFamilyService;
import com.xuequ.cmoc.service.IActivityMarinesService;
import com.xuequ.cmoc.utils.FileUtils;

@RequestMapping("attachment")
@Controller
public class AttachmentController {
	private Logger LOGGER = LoggerFactory.getLogger(AttachmentController.class);
	
	@Autowired
	private IActivityMarinesService activityMarinesService;
	@Autowired
	private IActivityFamilyService activityFamilyService;
	
	@RequestMapping("media/download")
	@ResponseBody Object wechatMediaDownload(Integer activityId, 
			String mediaId, String imgType, Integer id) {
		try {
			WechatReceiveMessage message = new WechatReceiveMessage();
			message.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_IMAGE);
			message.setMediaId(mediaId);
			String imgUrl = FileUtil.downloadWechatFile(activityId, message, false);
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
	@ResponseBody Object idPhotoUpload(String openid, String extName, String imgdata, 
			HttpServletRequest request) {
        try {  
        	String path = ResourcePathEnum.IMGE.getValue() + Const.SEPARATOR + ImgTypeEnum.ID_PHOTO.getCode();
        	String relativeAttachmentPath = Const.rootPath + path;
            FileUtils.createDir(relativeAttachmentPath);
            //数据库保存的文件路径
            String realFileName = openid + Const.DOT + extName;
            // 文件保存磁盘的完整路径
            String attachmentURL = relativeAttachmentPath + Const.SEPARATOR + realFileName;
	        // new一个文件对象用来保存图片，默认保存当前工程根目录  
	        File imageFile = new File(attachmentURL);  
	        // 创建输出流  
	        FileOutputStream outputStream = new FileOutputStream(imageFile);  
	        // 获得一个图片文件流，我这里是从flex中传过来的  
	        byte[] result = Base64.decodeBase64(imgdata.split(",")[1]);//解码  
	        for (int i = 0; i < result.length; ++i) {  
	            if (result[i] < 0) {// 调整异常数据  
	            	result[i] += 256;
	            }
	        }  
	        outputStream.write(result);  
	        outputStream.flush();   
	        outputStream.close();  
	        String dataMemoryURL = path + Const.SEPARATOR + realFileName;
	        if(Configuration.getInstance().getEnv().equals("development")) {
	        	dataMemoryURL = extName + Const.SEPARATOR + dataMemoryURL;
	        	dataMemoryURL = dataMemoryURL.replaceAll(Const.SEPARATOR, Const.REPLACE_SEPARATOR);
	        }
	        return new RspResult(StatusEnum.SUCCESS, dataMemoryURL);
        } catch (Exception e) {  
            LOGGER.error("[文件上传（fileUpload）][errors:" + e + "]");  
        }
        return new RspResult(StatusEnum.FAIL);
	}
	
}

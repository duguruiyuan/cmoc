package com.xuequ.cmoc.core.wechat.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xuequ.cmoc.common.Const;
import com.xuequ.cmoc.common.WechatConfigure;
import com.xuequ.cmoc.common.enums.MimeTypeEnum;
import com.xuequ.cmoc.common.enums.ResourcePathEnum;
import com.xuequ.cmoc.common.enums.WechatReqMsgType;
import com.xuequ.cmoc.model.WechatReceiveMessage;
import com.xuequ.cmoc.utils.DateUtil;
import com.xuequ.cmoc.utils.FileUtils;
import com.xuequ.cmoc.utils.MimeTypeUtils;
import com.xuequ.cmoc.utils.TextUtil;

public class FileUtil {
	
	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
	public static String downloadWechatFile(String path, WechatReceiveMessage message, Boolean isThumb) {
		String resourceType = null;
		if(message.getMsgType().equals(WechatReqMsgType.IMAGE.getCode())) {
			resourceType = ResourcePathEnum.IMGE.getValue() + Const.SEPARATOR;
		}else {
			resourceType = ResourcePathEnum.VIDEO.getValue() + Const.SEPARATOR;
		}
		resourceType += Const.SEPARATOR + path;
		String relativeAttachmentPath = Const.rootPath + resourceType;
		try {
			FileUtils.createDir(relativeAttachmentPath);
			String mediaId = isThumb ? message.getThumbMediaId() : message.getMediaId();
			String wechatUrl = TextUtil.format(WechatConfigure.getInstance().getMediaDownload(), 
					new String[]{WechatUtils.getAccessToken(), mediaId});
    		URL url = new URL(wechatUrl);
    		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    		conn.setRequestMethod("GET");
    		conn.setDoInput(true);
    		String contentType = conn.getHeaderField("Content-Type");
    		if(contentType.indexOf(";") != -1) {
    			contentType = contentType.substring(0, contentType.indexOf(";"));
    		}
    		String extName = MimeTypeEnum.getKey(contentType);
    		String realFileName = mediaId + Const.DOT + extName;
    		// 文件保存磁盘的完整路径
            String attachmentURL = relativeAttachmentPath + Const.SEPARATOR + realFileName;
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());  
            FileOutputStream fos = new FileOutputStream(new File(attachmentURL));  
            byte[] buf = new byte[8096];  
            int size = 0;  
            while ((size = bis.read(buf)) != -1)  
                fos.write(buf, 0, size);  
            fos.close();  
            bis.close();  
            conn.disconnect(); 
    		return resourceType + Const.SEPARATOR + realFileName;
		} catch (Exception e) {
			logger.error("创建文件路径出错, error={}", e);
		}
		return null;
    }
	
}

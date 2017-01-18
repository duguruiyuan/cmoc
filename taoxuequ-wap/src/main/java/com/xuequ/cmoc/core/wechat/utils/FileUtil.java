package com.xuequ.cmoc.core.wechat.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.xuequ.cmoc.common.Const;
import com.xuequ.cmoc.common.WechatConfigure;
import com.xuequ.cmoc.common.enums.MimeTypeEnum;
import com.xuequ.cmoc.common.enums.ResourcePathEnum;
import com.xuequ.cmoc.common.enums.WechatReqMsgType;
import com.xuequ.cmoc.model.WechatReceiveMessage;
import com.xuequ.cmoc.utils.DateUtil;
import com.xuequ.cmoc.utils.FileUtils;
import com.xuequ.cmoc.utils.TextUtil;

@SuppressWarnings("restriction")
public class FileUtil {
	
	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
	public static String getRelativePath(WechatReceiveMessage message, String path) {
		String resourceType = ResourcePathEnum.IMGE.getValue();
		String realName = message.getMsgId() + Const.DOT + "jpeg";
		resourceType += Const.SEPARATOR + path;
		String relativeAttachmentPath = Const.rootPath + resourceType;
		FileUtils.createDir(relativeAttachmentPath);
		return resourceType + Const.SEPARATOR + realName;
	}
	
	public static String getRelativePath(WechatReceiveMessage message, Integer activityId) {
		return getRelativePath(message, activityId, false);
	}
	public static String getRelativePath(WechatReceiveMessage message, Integer activityId, Boolean isThumb) {
		String resourceType = null;
		String realName = null;
		if(message.getMsgType().equals(WechatReqMsgType.IMAGE.getCode())) {
			resourceType = ResourcePathEnum.IMGE.getValue();
			realName = message.getMediaId() + Const.DOT + "jpeg";
		}else {
			resourceType = ResourcePathEnum.VIDEO.getValue();
			realName = message.getMediaId() + Const.DOT + (isThumb ? "jpeg" : "mp4");
		}
		resourceType += Const.SEPARATOR + DateUtil.getYear(new Date()) + Const.SEPARATOR + activityId;
		String relativeAttachmentPath = Const.rootPath + resourceType;
		FileUtils.createDir(relativeAttachmentPath);
		return resourceType + Const.SEPARATOR + realName;
	}
	
	public static void downloadWechatFile(String path, WechatReceiveMessage message, Boolean isThumb) {
		try {
			logger.info("--------------------path={}, message={}", path, message);
			String mediaId = isThumb ? message.getThumbMediaId() : message.getMediaId();
			String wechatUrl = TextUtil.format(WechatConfigure.getInstance().getMediaDownload(), 
					new String[]{"Kho2-Usc-E0f3u8CQmdlJ2AhDnIl6pg-BTRmBihA3yfePRM0HPP13LVlNDrYv2rxnVLStUqBEpCyVQBJa_RPMG4-fE8QcZrkvNDWt9N1Bljx9rK_nVmqgkX8XTcEocwDRYFhAEAMTA", mediaId});
			URL url = new URL(wechatUrl);
    		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    		conn.setRequestMethod("GET");
    		conn.setDoInput(true);
    		// 文件保存磁盘的完整路径
            String attachmentURL = Const.rootPath + path;
            Boolean isImageReduce = false;
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            if(message.getMsgType().equals(WechatReqMsgType.IMAGE.getCode())) {
            	BufferedImage sourceImg = ImageIO.read(new BufferedInputStream(conn.getInputStream()));
            	if(sourceImg.getWidth() > 1280) {//压缩图片
            		isImageReduce = true;
                	reduceImg(sourceImg, attachmentURL, 1280, 960);
                }
            	if(!isImageReduce) {
            		FileOutputStream fos = new FileOutputStream(new File(attachmentURL)); 
            		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);        
        	        encoder.encode(sourceImg);        
        	        fos.close();
            	}
            }else {
            	FileOutputStream fos = new FileOutputStream(new File(attachmentURL));  
                byte[] buf = new byte[8096];  
                int size = 0;  
                while ((size = bis.read(buf)) != -1)  
                    fos.write(buf, 0, size);  
                fos.close();  
                bis.close(); 
            }
            conn.disconnect();
		} catch (Exception e) {
			logger.error("创建文件路径出错, error={}", e);
		}
    }
	
	public static void reduceImg(Image src, String imgsrc, int widthdist, int heightdist) {
		try {        
			File srcfile = new File(imgsrc);        
	        if (!srcfile.exists()) {        
	            srcfile.createNewFile();     
	        }  
	        BufferedImage tag= new BufferedImage(widthdist, heightdist, BufferedImage.TYPE_INT_RGB);        
	        tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist,  Image.SCALE_SMOOTH), 0, 0,  null);        
	        FileOutputStream out = new FileOutputStream(srcfile);  
	        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);        
	        encoder.encode(tag);        
	        out.close();
	    } catch (IOException ex) {        
	        ex.printStackTrace();        
	    }   
	}
	
	public static void main(String[] args) {
		String contentType = "image/jpeg";
		if(contentType.indexOf(";") != -1) {
			contentType = contentType.substring(0, contentType.indexOf(";"));
		}
		String extName = MimeTypeEnum.getKey(contentType);
		System.out.println(extName);
	}
}

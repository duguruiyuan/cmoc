package com.xuequ.cmoc.core.wechat.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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
import com.xuequ.cmoc.utils.FileUtils;
import com.xuequ.cmoc.utils.TextUtil;

public class FileUtil {
	
	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
	public static String downloadWechatFile(String path, WechatReceiveMessage message, Boolean isThumb) {
		String resourceType = null;
		if(message.getMsgType().equals(WechatReqMsgType.IMAGE.getCode())) {
			resourceType = ResourcePathEnum.IMGE.getValue();
		}else {
			resourceType = ResourcePathEnum.VIDEO.getValue();
		}
		resourceType += Const.SEPARATOR + path;
		String relativeAttachmentPath = Const.rootPath + resourceType;
		try {
			FileUtils.createDir(relativeAttachmentPath);
			String mediaId = isThumb ? message.getThumbMediaId() : message.getMediaId();
			String wechatUrl = TextUtil.format(WechatConfigure.getInstance().getMediaDownload(), 
					new String[]{WechatConfigure.getInstance().getAccessToken(), mediaId});
    		URL url = new URL(wechatUrl);
    		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    		conn.setRequestMethod("GET");
    		conn.setDoInput(true);
    		String contentType = conn.getHeaderField("Content-Type");
    		logger.info("mediaId-" + mediaId + "--contentType--" + contentType + "--desc" + conn.getHeaderField("Content-disposition"));
    		if(contentType.indexOf(";") != -1) {
    			contentType = contentType.substring(0, contentType.indexOf(";"));
    		}
    		String extName = MimeTypeEnum.getKey(contentType);
    		String realFileName = mediaId + Const.DOT + extName;
    		// 文件保存磁盘的完整路径
            String attachmentURL = relativeAttachmentPath + Const.SEPARATOR + realFileName;
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            BufferedImage sourceImg = ImageIO.read(bis);
            if(sourceImg.getWidth() > 1280) {//压缩图片
            	reduceImg(sourceImg, attachmentURL, 1280, 960);
            }else {
            	FileOutputStream fos = new FileOutputStream(new File(attachmentURL));  
                byte[] buf = new byte[8096];  
                int size = 0;  
                while ((size = bis.read(buf)) != -1)  
                    fos.write(buf, 0, size);  
                fos.close();  
                bis.close();  
                conn.disconnect();
            }
    		return resourceType + Const.SEPARATOR + realFileName;
		} catch (Exception e) {
			logger.error("创建文件路径出错, error={}", e);
		}
		return null;
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

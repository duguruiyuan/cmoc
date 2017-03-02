package com.xuequ.cmoc.utils;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xuequ.cmoc.common.Configuration;
import com.xuequ.cmoc.common.Const;
import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.enums.ResourcePathEnum;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.vo.ImageSynthesisVo;

public class ImageUtils {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ImageUtils.class);
	
	public static RspResult saveImg(String fileName, String extName, String imgdata, String mkdir) {
		try{
			String path = ResourcePathEnum.IMGE.getValue() + Const.SEPARATOR + mkdir;
	    	String relativeAttachmentPath = Const.rootPath + path;
	        FileUtils.createDir(relativeAttachmentPath);
	        //数据库保存的文件路径
	        String realFileName = fileName + Const.DOT + extName;
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
	
	public static void composePic(String filesrc,String outsrc,List<ImageSynthesisVo> list,int width,int height) {
		try {
			File bgfile = new File(filesrc);
			Image bg_src = javax.imageio.ImageIO.read(bgfile);
			BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = tag.createGraphics();
			g2d.drawImage(bg_src, 0, 0, width, height, null);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,1.0f)); //透明度设置开始 
			for(ImageSynthesisVo vo : list) {
				URL url = new URL(vo.getFileUrl());
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.setRequestMethod("GET");
				conn.setConnectTimeout(5 * 1000);
				InputStream inStream = conn.getInputStream();//通过输入流获取图片数据
				Image logoSrc = javax.imageio.ImageIO.read(inStream);
				g2d.drawImage(logoSrc, vo.getPointX(), vo.getPointY(), vo.getWidth(), vo.getHeight(), null);     
				conn.disconnect();
			}
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER)); //透明度设置 结束
			FileOutputStream out = new FileOutputStream(outsrc);
			ImageIO.write(tag, "jpg", out);//写图片
			out.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}

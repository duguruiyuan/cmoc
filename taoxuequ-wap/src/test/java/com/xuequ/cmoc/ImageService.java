package com.xuequ.cmoc;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.xuequ.cmoc.common.Const;
import com.xuequ.cmoc.core.wechat.thread.WechatMsgCallback;
import com.xuequ.cmoc.core.wechat.utils.FileUtil;
import com.xuequ.cmoc.model.WechatReceiveMessage;
import com.xuequ.cmoc.utils.DateUtil;
import com.sun.image.codec.jpeg.JPEGCodec;

public class ImageService {

	public static void main(String[] args) {
		File file = new File("C:\\Users\\000001436\\Documents\\Axure\\活动");
		String filepath = file.getPath();
		if (file.isDirectory()) {
            String[] filelist = file.list();
            for (int i = 0; i < filelist.length; i++) {
            	String path = filepath + "\\" + filelist[i];
            	reduceImg(path, 1280, 960);    
            }
            System.out.println("success");
		}
//		long ab = new Date().getTime();
//		String src = "C:\\Users\\000001436\\Downloads\\Fo6hZaOsqQlsLWcr0k7GoZICq3sM9cKpYh6Ce44f1N34l1_nualUU15HZZF9wfX2.jpeg";
//		reduceImg(src, 1280, 960);
//		long ac = new Date().getTime();
//		System.out.println("--------------" + (ac - ab));
//		try {
//			WechatReceiveMessage message = new WechatReceiveMessage();
//			message.setMsgType("image");
//			message.setMsgId("6376098832272535098");
//			message.setMediaId("L8rEaVdoQZwwUMnJacZVH9CIeI2KLOb0cJi4jOsPxC8qcT5eY6txIWrxjFUr9wjG");
////			message.setMsgType("image");
////			message.setMsgId("6376605217506692764");
////			message.setMediaId("EhBzXjpbkskvz9x_AUslqbAkavrjmD0A6D0WZeiCMVs2SuL9iKLsuVopvnHAozf6");
//			String path = FileUtil.getRelativePath(message, 1);
//			new WechatMsgCallback(path, message).execute();
////			FileUtil.downloadWechatFile(path, message, false);
//			System.out.println("success");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	public static void reduceImg(String imgsrc, int widthdist, int heightdist) {        
	    try {        
	        File srcfile = new File(imgsrc);        
	        if (!srcfile.exists()) {        
	            return;        
	        }        
	        Image src = javax.imageio.ImageIO.read(srcfile);        
	       
	        BufferedImage tag= new BufferedImage((int) widthdist, (int) heightdist,        
	                BufferedImage.TYPE_INT_RGB);        
	       
	        tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist,  Image.SCALE_SMOOTH), 0, 0,  null);        
	        FileOutputStream out = new FileOutputStream(imgsrc);  
	        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);        
	        encoder.encode(tag);        
	        out.close();        
	    } catch (IOException ex) {        
	        ex.printStackTrace();        
	    }        
	}   
}

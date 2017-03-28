package com.xuequ.cmoc;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;

import com.xuequ.cmoc.core.wechat.utils.FileUtil;

public class ImageService {

	public static void main(String[] args) throws IOException {
		File file = new File("C:\\Users\\000001436\\Documents\\Axure\\活动");
		String filepath = file.getPath();
		if (file.isDirectory()) {
            String[] filelist = file.list();
            for (int i = 0; i < filelist.length; i++) {
            	String path = filepath + "\\" + filelist[i];
            	String srcPath = "C:\\Users\\000001436\\Documents\\Axure\\zip\\" + filelist[i];
            	BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(path)));
                BufferedImage sourceImg =  javax.imageio.ImageIO.read(bis);
            	int height = new BigDecimal(1280).divide(new BigDecimal(sourceImg.getWidth()), BigDecimal.ROUND_FLOOR, 2).multiply(new BigDecimal(sourceImg.getHeight())).intValue();
            	FileUtil.reduceImg(sourceImg, srcPath, 1280, height);
            }
            System.out.println("success");
		}
	}
}

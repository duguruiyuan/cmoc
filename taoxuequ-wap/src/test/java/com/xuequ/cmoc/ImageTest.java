package com.xuequ.cmoc;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.xuequ.cmoc.core.wechat.common.Constants;
import com.xuequ.cmoc.utils.QRCoderUtils;
import com.xuequ.cmoc.vo.ImageSynthesisVo;

public class ImageTest {

	public void composePic(String filesrc,String outsrc,List<ImageSynthesisVo> list,int width,int height) {
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
	
	

	public static void main(String args[]) {

		Long star = System.currentTimeMillis();
	
		ImageTest pic = new ImageTest();
		
		List<ImageSynthesisVo> list = new ArrayList<>();
		ImageSynthesisVo  vo1 = new ImageSynthesisVo("http://wx.qlogo.cn/mmopen/PLVuSzicWvZrYB7LJMoo7k11T7Qd6vTiaibTgvGRTlRR6sANRVfHqBXY9EBKUe3eF25eA1pZAwvLTWibdfLQgicTC9kxbhKeZ8PMX/0", 86, 134, 100, 100);
		String url = Constants.BASEPATH + "course/group/merber?nNo=17460082";
		url = QRCoderUtils.createEWM(url, 100, 100, "17460082");
		ImageSynthesisVo  vo2 = new ImageSynthesisVo(url, 189, 305, 130, 130);
		list.add(vo1);
		list.add(vo2);
		pic.composePic("f:\\ty.png","f:\\out.png",list,642,900);
	
		Long end =System.currentTimeMillis();
	
		System.out.print("time====:"+(end-star));

	}
	
}

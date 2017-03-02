package com.xuequ.cmoc.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.xuequ.cmoc.common.Configuration;
import com.xuequ.cmoc.common.Const;
import com.xuequ.cmoc.common.enums.ResourcePathEnum;
import com.xuequ.cmoc.core.wechat.common.Constants;

public final class QRCoderUtils {
	
	private static Logger LOG = Logger.getLogger(QRCoderUtils.class);
	
	public final static String IMAGE_FROMAT_NAME = "jpg";
	
	public static String getEWMRealImgUrl(){
		return ResourcePathEnum.IMGE.getValue() + Const.SEPARATOR + "EWM";
	}
	
	public static String getPosterRelativeImgUrl() {
		return ResourcePathEnum.IMGE.getValue() + Const.SEPARATOR + "POSTER";
	}
	public static String getPosterRealImgUrl() {
		String path = Const.rootPath + getPosterRelativeImgUrl();
		File file = new File(path);
		if(!file.exists()) {
			file.mkdirs();
		}
		return path;
	}
	
	public static String getPosterRspImgUrl(String fileName) {
		String dataMemoryURL = getPosterRelativeImgUrl() + Const.SEPARATOR + fileName;;
        if(Configuration.getInstance().getEnv().equals("development")) {
        	dataMemoryURL = IMAGE_FROMAT_NAME + Const.SEPARATOR + dataMemoryURL;
        	dataMemoryURL = dataMemoryURL.replaceAll(Const.SEPARATOR, Const.REPLACE_SEPARATOR);
        }
        return dataMemoryURL;
	}
	
	public static String createEWM(String url, int width, int heigth, String name) {
		String path = getEWMRealImgUrl();
    	String relativeAttachmentPath = Const.rootPath + path;
		File file = new File(relativeAttachmentPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		String realFileName = "EWM" + name + "." + IMAGE_FROMAT_NAME;
		String imageUrl = relativeAttachmentPath + File.separator + realFileName;
		File imageFile = new File(imageUrl);
		if(!imageFile.exists()) {
			encoderQRCoder(url, width, heigth, imageUrl);
		}
		String dataMemoryURL = path + Const.SEPARATOR + realFileName;
        if(Configuration.getInstance().getEnv().equals("development")) {
        	dataMemoryURL = IMAGE_FROMAT_NAME + Const.SEPARATOR + dataMemoryURL;
        	dataMemoryURL = "/xuequ" + dataMemoryURL.replaceAll(Const.SEPARATOR, Const.REPLACE_SEPARATOR);
        }
		return Configuration.getInstance().getImgUrl() + File.separator + dataMemoryURL;
	}
	
	private static void encoderQRCoder(String url, int width, int heigth, String imageUrl) {
		try{
			File file = new File(imageUrl);
			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
	        Map<EncodeHintType, Object> map = new HashMap<EncodeHintType, Object>();
	        map.put(EncodeHintType.CHARACTER_SET, "UTF-8");
	        map.put(EncodeHintType.MARGIN, 1);
	        BitMatrix bitMatrix;
			bitMatrix = multiFormatWriter.encode(url, BarcodeFormat.QR_CODE, width, heigth, map);
			MatrixToImageWriter.writeToFile(deleteWhite(bitMatrix), IMAGE_FROMAT_NAME, file);
		}catch(Exception e) {
			LOG.info(">>>>>生成二维码失败:{}",e);
		}
	}
	
	/*
	 * 去白边
	 */
	public static BitMatrix deleteWhite(BitMatrix matrix){
		int[] rec = matrix.getEnclosingRectangle();
		int resWidth = rec[2] + 1;
		int resHeight = rec[3] + 1;

		BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
		resMatrix.clear();
		for (int i = 0; i < resWidth; i++) {
			for (int j = 0; j < resHeight; j++) {
				if (matrix.get(i + rec[0], j + rec[1]))
					resMatrix.set(i, j);
			}
		}
		return resMatrix;
	}

}

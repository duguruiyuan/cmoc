package com.xuequ.cmoc;

public class ImageSynthesisVo {

	private String fileUrl;
	
	private Integer pointX;
	
	private Integer pointY;
	
	private Integer width;
	
	private Integer height;
	
	public ImageSynthesisVo() {}

	public ImageSynthesisVo(String fileUrl, Integer pointX, Integer pointY, Integer width, Integer height) {
		this.fileUrl = fileUrl;
		this.pointX = pointX;
		this.pointY = pointY;
		this.width = width;
		this.height = height;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Integer getPointX() {
		return pointX;
	}

	public void setPointX(Integer pointX) {
		this.pointX = pointX;
	}

	public Integer getPointY() {
		return pointY;
	}

	public void setPointY(Integer pointY) {
		this.pointY = pointY;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}
	
	
}

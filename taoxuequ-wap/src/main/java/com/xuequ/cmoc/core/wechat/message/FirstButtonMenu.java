package com.xuequ.cmoc.core.wechat.message;

import java.io.Serializable;
import java.util.List;

public class FirstButtonMenu implements Serializable {

	private static final long serialVersionUID = -7935623358845718252L;
	
	private String click;
	
	private String name;
	
	private String key;
	
	private String type;
	
	private String media_id;
	
	private String url;
	
	private List<SubButtonMenu> sub_button;

	public String getClick() {
		return click;
	}

	public void setClick(String click) {
		this.click = click;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<SubButtonMenu> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<SubButtonMenu> sub_button) {
		this.sub_button = sub_button;
	}
	
}

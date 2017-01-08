package com.xuequ.cmoc.core.wechat.message.MaterialMedia;

public class NewsTotItem {
	private String media_id;
	private NewsContent content;
	private Long update_time;
	public String getMedia_id() {
		return media_id;
	}
	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
	public NewsContent getContent() {
		return content;
	}
	public void setContent(NewsContent content) {
		this.content = content;
	}
	public Long getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Long update_time) {
		this.update_time = update_time;
	}
	
	
}

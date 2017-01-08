package com.xuequ.cmoc.core.wechat.message.MaterialMedia;

import java.util.List;

public class NewsMedia {
	private Integer total_count;
	private Integer item_count;
	private List<NewsTotItem> item;
	public Integer getTotal_count() {
		return total_count;
	}
	public void setTotal_count(Integer total_count) {
		this.total_count = total_count;
	}
	public Integer getItem_count() {
		return item_count;
	}
	public void setItem_count(Integer item_count) {
		this.item_count = item_count;
	}
	public List<NewsTotItem> getItem() {
		return item;
	}
	public void setItem(List<NewsTotItem> item) {
		this.item = item;
	}
	
	
}

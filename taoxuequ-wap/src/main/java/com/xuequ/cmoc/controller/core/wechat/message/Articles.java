package com.xuequ.cmoc.controller.core.wechat.message;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Articles")
public class Articles implements Serializable {

	private static final long serialVersionUID = 7431624210444173017L;

	private List<ArticleItem> list;

	public List<ArticleItem> getList() {
		return list;
	}

	public void setList(List<ArticleItem> list) {
		this.list = list;
	}
	
	
	
}

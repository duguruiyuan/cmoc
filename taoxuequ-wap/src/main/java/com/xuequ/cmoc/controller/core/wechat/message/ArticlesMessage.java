package com.xuequ.cmoc.controller.core.wechat.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class ArticlesMessage extends Articles {

	private static final long serialVersionUID = -2365223762779370815L;

	@XStreamAlias("ArticleCount")
	@XStreamCDATA
	private int ArticleCount;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	
	
}

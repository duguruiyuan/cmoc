package com.xuequ.cmoc.core.wechat.template;

import java.io.Serializable;

public class OutputTemateData implements Serializable {

	private static final long serialVersionUID = 9196375461907473260L;
	
	private String touser;//用户openid
	
	private String template_id;//模板id
	
	private String url;//链接
	
	private String topcolor;//颜色
	
	private TemplateDate data;

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTopcolor() {
		return topcolor;
	}

	public void setTopcolor(String topcolor) {
		this.topcolor = topcolor;
	}

	public TemplateDate getData() {
		return data;
	}

	public void setData(TemplateDate data) {
		this.data = data;
	}
	
}

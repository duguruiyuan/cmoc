package com.xuequ.cmoc.vo;

import org.apache.commons.lang.StringUtils;

public class MarineEditVO {

	private Integer id;
	
	private Integer childId;
	
	private String type;
	
	private String childName;
	
	private String childTitle;
	
	private String childComment;
	
	private String marineName;
	
	private String marineSlogan;
	
	private Integer score;
	
	private String marinePrize;
	
	private String comment;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getChildId() {
		return childId;
	}

	public void setChildId(Integer childId) {
		this.childId = childId;
	}

	public String getType() {
		return StringUtils.isNotBlank(type) ? type : null;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public String getChildTitle() {
		return StringUtils.isNotBlank(childTitle) ? childTitle : null;
	}

	public void setChildTitle(String childTitle) {
		this.childTitle = childTitle;
	}

	public String getChildComment() {
		return StringUtils.isNotBlank(childComment) ? childComment : null;
	}

	public void setChildComment(String childComment) {
		this.childComment = childComment;
	}

	public String getMarineName() {
		return StringUtils.isNotBlank(marineName) ? marineName : null;
	}

	public void setMarineName(String marineName) {
		this.marineName = marineName;
	}

	public String getMarineSlogan() {
		return StringUtils.isNotBlank(marineSlogan) ? marineSlogan : null;
	}

	public void setMarineSlogan(String marineSlogan) {
		this.marineSlogan = marineSlogan;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getMarinePrize() {
		return StringUtils.isNotBlank(marinePrize) ? marinePrize : null;
	}

	public void setMarinePrize(String marinePrize) {
		this.marinePrize = marinePrize;
	}

	public String getComment() {
		return StringUtils.isNotBlank(comment) ? comment : null;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
}

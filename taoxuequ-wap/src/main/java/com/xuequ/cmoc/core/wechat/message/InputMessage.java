package com.xuequ.cmoc.core.wechat.message;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class InputMessage implements Serializable {

	private static final long serialVersionUID = 2373010768339632980L;
	//开发者微信号
	@XStreamAlias("ToUserName")  
    private String toUserName;  
	//发送方帐号（一个OpenID）
    @XStreamAlias("FromUserName")  
    private String fromUserName;  
    //消息创建时间 （整型）
    @XStreamAlias("CreateTime")  
    private Long createTime;  
    //消息类型
    @XStreamAlias("MsgType")  
    private String msgType = "text";  
    //消息id，64位整型
    @XStreamAlias("MsgId")  
    private String msgId;
    //event消息推送的消息id
    @XStreamAlias("MsgID")
    private String eventMsgId;
    //消息媒体id
    @XStreamAlias("MediaId")
    private String mediaId;
    @XStreamAlias("MenuId")
    private String menuId;
    
    // 文本消息
    //文本消息内容
    @XStreamAlias("Content")  
    private String content;  
    
    // 图片消息  
    //图片链接
    @XStreamAlias("PicUrl")  
    private String picUrl;
    
    // 语音信息  
    //语音格式，如amr，speex等
    @XStreamAlias("Format")  
    private String format; 
    //语音识别结果，使用UTF8编码
    @XStreamAlias("Recognition")
    private String recognition;
    
    //视频消息
    //视频消息缩略图的媒体id
    @XStreamAlias("ThumbMediaId")
    private String thumbMediaId;
    
    // 位置消息  
    //地理位置维度
    @XStreamAlias("Location_X")  
    private Float locationX;  
    //地理位置经度
    @XStreamAlias("Location_Y")  
    private Float locationY;  
    //地图缩放大小
    @XStreamAlias("Scale")  
    private Integer scale;  
    //地理位置信息
    @XStreamAlias("Label")  
    private String label;  
    
    // 链接消息  
    //消息标题
    @XStreamAlias("Title")  
    private String title;  
    //	消息描述
    @XStreamAlias("Description")  
    private String description;  
    //消息链接
    @XStreamAlias("Url")  
    private String url;
    
    // 事件
    //事件类型
    @XStreamAlias("Event")  
    private String event;  
    //事件KEY值，是一个32位无符号整数
    @XStreamAlias("EventKey")  
    private String eventKey;  
    //二维码的ticket，可用来换取二维码图片
    @XStreamAlias("Ticket")  
    private String ticket;
    //地理位置纬度
    @XStreamAlias("Latitude")
    private String latitude;
    //地理位置经度
    @XStreamAlias("Longitude")
    private String longitude;
    //地理位置精度
    @XStreamAlias("Precision")
    private String precision;
    
    //客服账号
    @XStreamAlias("KfAccount")
    private String kfAccount;
    
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getEventMsgId() {
		return eventMsgId;
	}
	public void setEventMsgId(String eventMsgId) {
		this.eventMsgId = eventMsgId;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getRecognition() {
		return recognition;
	}
	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	public Float getLocationX() {
		return locationX;
	}
	public void setLocationX(Float locationX) {
		this.locationX = locationX;
	}
	public Float getLocationY() {
		return locationY;
	}
	public void setLocationY(Float locationY) {
		this.locationY = locationY;
	}
	public Integer getScale() {
		return scale;
	}
	public void setScale(Integer scale) {
		this.scale = scale;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getEventKey() {
		return eventKey;
	}
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getPrecision() {
		return precision;
	}
	public void setPrecision(String precision) {
		this.precision = precision;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getKfAccount() {
		return kfAccount;
	}
	public void setKfAccount(String kfAccount) {
		this.kfAccount = kfAccount;
	}
	
}

package com.xuequ.cmoc.controller.core.wechat.message;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class InputMessage implements Serializable {

	private static final long serialVersionUID = 2373010768339632980L;
	//开发者微信号
	@XStreamAlias("ToUserName")  
    private String ToUserName;  
	//发送方帐号（一个OpenID）
    @XStreamAlias("FromUserName")  
    private String FromUserName;  
    //消息创建时间 （整型）
    @XStreamAlias("CreateTime")  
    private Long CreateTime;  
    //消息类型
    @XStreamAlias("MsgType")  
    private String MsgType = "text";  
    //消息id，64位整型
    @XStreamAlias("MsgId")  
    private Long MsgId;  
    //消息媒体id
    @XStreamAlias("MediaId")
    private String MediaId;
    
    // 文本消息
    //文本消息内容
    @XStreamAlias("Content")  
    private String Content;  
    
    // 图片消息  
    //图片链接
    @XStreamAlias("PicUrl")  
    private String PicUrl;
    
    // 语音信息  
    //语音格式，如amr，speex等
    @XStreamAlias("Format")  
    private String Format; 
    //语音识别结果，使用UTF8编码
    @XStreamAlias("Recongnition")
    private String Recongnition;
    
    //视频消息
    //视频消息缩略图的媒体id
    @XStreamAlias("ThumbMediaId")
    private Long ThumbMediaId;
    
    // 位置消息  
    //地理位置维度
    @XStreamAlias("LocationX")  
    private String LocationX;  
    //地理位置经度
    @XStreamAlias("LocationY")  
    private String LocationY;  
    //地图缩放大小
    @XStreamAlias("Scale")  
    private Long Scale;  
    //地理位置信息
    @XStreamAlias("Label")  
    private String Label;  
    
    // 链接消息  
    //消息标题
    @XStreamAlias("Title")  
    private String Title;  
    //	消息描述
    @XStreamAlias("Description")  
    private String Description;  
    //消息链接
    @XStreamAlias("Url")  
    private String Url;
    
    // 事件
    //事件类型
    @XStreamAlias("Event")  
    private String Event;  
    //事件KEY值，是一个32位无符号整数
    @XStreamAlias("EventKey")  
    private String EventKey;  
    //二维码的ticket，可用来换取二维码图片
    @XStreamAlias("Ticket")  
    private String Ticket;
    //地理位置纬度
    @XStreamAlias("Latitude")
    private String Latitude;
    //地理位置经度
    @XStreamAlias("Longitude")
    private String Longitude;
    //地理位置精度
    @XStreamAlias("Precision")
    private String Precision;
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public Long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public Long getMsgId() {
		return MsgId;
	}
	public void setMsgId(Long msgId) {
		MsgId = msgId;
	}
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		Format = format;
	}
	public String getRecongnition() {
		return Recongnition;
	}
	public void setRecongnition(String recongnition) {
		Recongnition = recongnition;
	}
	public Long getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(Long thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	public String getLocationX() {
		return LocationX;
	}
	public void setLocationX(String locationX) {
		LocationX = locationX;
	}
	public String getLocationY() {
		return LocationY;
	}
	public void setLocationY(String locationY) {
		LocationY = locationY;
	}
	public Long getScale() {
		return Scale;
	}
	public void setScale(Long scale) {
		Scale = scale;
	}
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	public String getEventKey() {
		return EventKey;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	public String getTicket() {
		return Ticket;
	}
	public void setTicket(String ticket) {
		Ticket = ticket;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getPrecision() {
		return Precision;
	}
	public void setPrecision(String precision) {
		Precision = precision;
	}
    
    
	
}

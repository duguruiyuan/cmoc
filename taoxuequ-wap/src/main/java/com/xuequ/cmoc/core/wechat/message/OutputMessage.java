package com.xuequ.cmoc.core.wechat.message;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class OutputMessage implements Serializable {

	private static final long serialVersionUID = 1526082641484914474L;

	@XStreamAlias("ToUserName")  
    @XStreamCDATA 
    private String ToUserName;  
  
    @XStreamAlias("FromUserName")  
    @XStreamCDATA  
    private String FromUserName;  
  
    @XStreamAlias("CreateTime")  
    private Long CreateTime;  
    
    @XStreamAlias("MsgType")
    @XStreamCDATA
    private String MsgType;
    
    @XStreamAlias("Content")
	private String Content;
    
    private ImageMessage Image;
    
    private MusicMessage Music;
    
    private VideoMessage Video;
    
    private VoiceMessage Voice;
    
    @XStreamAlias("ArticleCount")
	private Integer ArticleCount;
    
    private List<ArticleItem> Articles;

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

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public ImageMessage getImage() {
		return Image;
	}

	public void setImage(ImageMessage image) {
		Image = image;
	}

	public MusicMessage getMusic() {
		return Music;
	}

	public void setMusic(MusicMessage music) {
		Music = music;
	}

	public Integer getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(Integer articleCount) {
		ArticleCount = articleCount;
	}

	public List<ArticleItem> getArticles() {
		return Articles;
	}

	public void setArticles(List<ArticleItem> articles) {
		Articles = articles;
	}

	public VideoMessage getVideo() {
		return Video;
	}

	public void setVideo(VideoMessage video) {
		Video = video;
	}

	public VoiceMessage getVoice() {
		return Voice;
	}

	public void setVoice(VoiceMessage voice) {
		Voice = voice;
	}

}

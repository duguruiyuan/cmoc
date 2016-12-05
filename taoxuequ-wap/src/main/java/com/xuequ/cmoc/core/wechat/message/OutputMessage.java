package com.xuequ.cmoc.core.wechat.message;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class OutputMessage implements Serializable {

	private static final long serialVersionUID = 1526082641484914474L;

	@XStreamAlias("ToUserName")  
    @XStreamCDATA 
    private String toUserName;  
  
    @XStreamAlias("FromUserName")  
    @XStreamCDATA  
    private String fromUserName;  
  
    @XStreamAlias("CreateTime")  
    private Long createTime;  
    
    @XStreamAlias("MsgType")
    @XStreamCDATA
    private String msgType;
    
    @XStreamAlias("Content")
    @XStreamCDATA
	private String content;
    
    private ImageMessage Image;
    
    private MusicMessage Music;
    
    private VideoMessage Video;
    
    private VoiceMessage Voice;
    
    @XStreamAlias("ArticleCount")
	private Integer articleCount;
    
    private List<ArticleItem> Articles;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Integer getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}

	public List<ArticleItem> getArticles() {
		return Articles;
	}

	public void setArticles(List<ArticleItem> articles) {
		Articles = articles;
	}

	
}

/**
 * 
 */
package com.xuequ.cmoc.core.wechat.model;

import java.io.Serializable;

/**
 * @author 胡启萌
 * @Date 2016年12月3日
 *
 */
public class WechatActionInfo implements Serializable {

	private static final long serialVersionUID = 9178670230145831416L;

	private WechatScene scene;

	public WechatScene getScene() {
		return scene;
	}

	public void setScene(WechatScene scene) {
		this.scene = scene;
	}
	
	

}

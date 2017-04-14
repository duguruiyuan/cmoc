/*
   * 注意：
   * 1. 所有的JS接口只能在公众号绑定的域名下调用，公众号开发者需要先登录微信公众平台进入“公众号设置”的“功能设置”里填写“JS接口安全域名”。
   * 2. 如果发现在 Android 不能分享自定义内容，请到官网下载最新的包覆盖安装，Android 自定义分享接口需升级至 6.0.2.58 版本及以上。
   * 3. 常见问题及完整 JS-SDK 文档地址：http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html
   *
   * 开发中遇到问题详见文档“附录5-常见错误及解决办法”解决，如仍未能解决可通过以下渠道反馈：
   * 邮箱地址：weixin-open@qq.com
   * 邮件主题：【微信JS-SDK反馈】具体问题
   * 邮件内容说明：用简明的语言描述问题所在，并交代清楚遇到该问题的场景，可附上截屏图片，微信团队会尽快处理你的反馈。
   */
  /***用户打开页面的时候就加载**/
  $(document).ready(function(){
	  var ua = window.navigator.userAgent.toLowerCase();
	    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
	    	initPage();
	    }
  });
  
  function initPage() {
	  /***用户点击分享到微信圈后加载接口接口*******/
	  $.ajax({
			type : 'POST',
			url : basePath + '/wechat/signature',
			data : {url: window.location.href},
			dataType : 'json',
			success : function(response) {
			  var data = response.data;
			  wx.config({
		            debug: false,
		            appId: data.appid,
		            timestamp:data.timestamp,
		            nonceStr:data.nonceStr,
		            signature:data.signature,
		            jsApiList: [
						'checkJsApi',
						'onMenuShareTimeline',
						'onMenuShareAppMessage',
						'onMenuShareQQ',
						'onMenuShareQZone',
						'onMenuShareWeibo',
						'hideMenuItems',
						'showMenuItems',
						'hideAllNonBaseMenuItem',
						'showAllNonBaseMenuItem',
						'translateVoice',
						'startRecord',
						'stopRecord',
						'onRecordEnd',
						'playVoice',
						'pauseVoice',
						'stopVoice',
						'uploadVoice',
						'downloadVoice',
						'chooseImage',
						'previewImage',
						'uploadImage',
						'downloadImage',
						'getNetworkType',
						'openLocation',
						'getLocation',
						'hideOptionMenu',
						'showOptionMenu',
						'closeWindow',
						'scanQRCode',
						'chooseWXPay',
						'openProductSpecificView',
						'addCard',
						'chooseCard',
						'openCard'
		            ]
		      });
			  var currUrl = window.location.href;
			  if(!(currUrl.indexOf(basePath1 + "/live") == 0 || currUrl.indexOf(basePath1 + "/course") == 0)) {
				  window.param = {
						title: '陶学趣,专注青少年社会实践教育',
						desc: '陶学趣欢迎您光临！不要问我凭什么：趣看看 🙋',
						wZoneTitle: '陶学趣,专注于青少年社会实践教育,欢迎您光临！不要问我凭什么：趣看看 🙋',
						imgUrl: basePath + '/images/whead.png' 
				  }
			  }else {
				  if(currUrl.indexOf(basePath1 + "/course/sign") == 0 || currUrl.indexOf(basePath1 + "/pay/") == 0 || currUrl.indexOf(basePath1 + "/hm/") == 0) {
					  wx.hideOptionMenu();
					  return;
				  }
			  }
			  	wx.onMenuShareTimeline({//分享到朋友圈
				    title: window.param.wZoneTitle, // 分享标题
				    link: getShareUrl('wZone'), // 分享链接
				    imgUrl: window.param.imgUrl // 分享图标
				});
				wx.onMenuShareAppMessage({//分享给朋友
				    title: window.param.title, // 分享标题
				    desc: window.param.desc, // 分享描述
				    link: getShareUrl('wfriend'), // 分享链接
				    imgUrl: window.param.imgUrl, // 分享图标
				    type: 'link', // 分享类型,music、video或link，不填默认为link
				    dataUrl: '' // 如果type是music或video，则要提供数据链接，默认为空
				});
				wx.onMenuShareQZone({//分享给qq朋友圈
				    title: window.param.title, // 分享标题
				    desc: window.param.desc, // 分享描述
				    link: getShareUrl('qZone'), // 分享链接
				    imgUrl: window.param.imgUrl // 分享图标
				});
				wx.onMenuShareQQ({//分享给qq
				    title: window.param.title, // 分享标题
				    desc: window.param.desc, // 分享描述
				    link: getShareUrl('qq'), // 分享链接
				    imgUrl: window.param.imgUrl // 分享图标
				});
				wx.onMenuShareWeibo({//分享给微博
				    title: window.param.title, // 分享标题
				    desc: window.param.desc, // 分享描述
				    link: getShareUrl('weibo'), // 分享链接
				    imgUrl: window.param.imgUrl // 分享图标
				});
			}
		});
  };

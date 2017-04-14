var payData = null;
function onBridgeReady(obj){
   WeixinJSBridge.invoke(
       'getBrandWCPayRequest', {
           "appId": obj.appId,     //公众号名称，由商户传入     
           "timeStamp": obj.timeStamp,         //时间戳，自1970年以来的秒数     
           "nonceStr": obj.nonceStr, //随机串     
           "package": obj.packages,     
           "signType": obj.signType,         //微信签名方式：     
           "paySign": obj.paySign //微信签名 
       },
       function(res){     
    	   payData = null;
    	   if(res.err_msg == "get_brand_wcpay_request:ok" ) {// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
        	   window.location.href = basePath + "/pay/result/" + obj.orderNo;
           }
       }
   ); 
}
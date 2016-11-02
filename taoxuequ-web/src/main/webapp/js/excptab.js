/**
 * 清算异常列表页
 */
var requestUrl;
$(document).ready(function() {
	
	requestUrl = basePath + 'clr/excp/query.do?idChanlParam='+parent.idChanlParam+'&adm='+new Date().getTime(),
	$.ajax({
	    type: 'post',
	    url: requestUrl,
	    data: {},
	    dataType: 'json',
	    success: function(data) {
	    	var myTemplate = Handlebars.compile($("#table-template").html());
	    	
	        //注册一个比较数字大小的方法compare
	        Handlebars.registerHelper("compare",function(v1,v2,options){
	          //判断v1是否比v2大
	          if(v1>v2){
	            //继续执行
	            return options.fn(this);
	          }else{
	            //执行else部分
	            return options.inverse(this);
	          }
	        });	
	        	        
	        //注册一个比较数字是否相等的方法compare2
	        Handlebars.registerHelper("compare2",function(v1,v2,options){
	          //判断v1是否等于v2
	          if(v1 != v2){
	            //继续执行
	            return options.fn(this);
	          }else{
	            //执行else部分
	            return options.inverse(this);
	          }
	        });
	        
	      //注册一个翻译解除异常状态用的Helper
	        Handlebars.registerHelper("transformat",function(value,idFailLog){
	          if(value=='Y'){
	        	  return "已完成";
	          }if(value=='N'){
	        	  return "<a href='#' onclick='relieve("+idFailLog+")'>处理</a>";
	          }else if(value=='I'){
	        	return "<a href='#' onclick='relieveCancel("+idFailLog+")'>已处理</a>";
	          }
	        });
	        
	        //注册一个异常信息对比helper
	        Handlebars.registerHelper("vsformat",function(failType,transType,idFailLog){
	          if(failType == 1) {
	        	  return "<a href=\"#\" onclick=\"todifferentPage('"+transType + "'," + idFailLog+")\">对比</a>";
	          }
	          return "";
	        });
	        
	        //注册一个遍历方法list
	        Handlebars.registerHelper('list', function(v1,v2, v3,options) {   
	        	var out = ""; 
	        	var margin_left = "";
	        	for(var i=v1; i<=v2; i++) {
	        		if(i != v1) {
	        			margin_left = " style='margin-left:5px'";
	        		}
	        		if(v3 != i ){
	        		    out = out+"<span class='pageNum'"+margin_left+" onclick=\"queryExceptionLogNum("+i+")\">"+i+"</span>";
	        		}
	        		else out = out+"<span class='pageNum pageOn'"+margin_left+">"+i+"</span>";
	        	}
	        	return out ; 
	        }); 
	        //类型转换:交易类型
	        Handlebars.registerHelper("formatTransType", function(transType) {
	    		if(transType=="DRAWBACK"){
	    			return "退款";
	    		}else if (transType == 'PAY'){
	    			return "支付";
	    		}
	    		return "";
	    	});
	        //类型转换:异常类型
	        Handlebars.registerHelper("formatFailType", function(failType) {
	        	var retStr="";
	        	if(failType=="1"){
	        		retStr="信息不一致";
	        	}else if(failType=="2"){
	    			retStr="支付渠道缺少记录";
	    		}else if(failType=="3"){
	    			retStr="支付渠道多出记录";
	    		}else if(failType=="4"){
	    			retStr="数据格式异常";
	    		}else {
	        		retStr="数据库操作异常";
	        	}
	        	return retStr;
	        });
	        //状态转换:异常状态
	        Handlebars.registerHelper("formatStatus", function(status) {
	        	var retStr="";
	        	if(status=="Y"){
	        		retStr="已处理";
	        	}else if(status=="N"){
	    			retStr="异常";
	    		}else {
	        		retStr="处理中";
	        	}
	        	return retStr;
	        });
	        //货币格式化   
	        Handlebars.registerHelper("fenToYue", function(amount,n) {
	            //四舍五入到分
	            var num = Math.round(amount)/100;
	            num = String(num.toFixed(n));
	            var re = /(-?\d+)(\d{3})/;
	            while(re.test(num)) num = num.replace(re,"$1,$2")
	            return num;
	         });
	        //日期格式化   
	        Handlebars.registerHelper("showDateFormat", function(timestamp) {
	            return new Date(timestamp).Format("yyyy-MM-dd hh:mm:ss");
	        });
	        
	      //日期格式化   
	        Handlebars.registerHelper("formatShortDate", function(clringDate) {
	            return new Date(clringDate).Format("yyyy-MM-dd");
	        });
	        
	       //将json对象用刚刚注册的Handlebars模版封装，得到最终的html，插入到基础table中。
	       $('#tableList').html(myTemplate(data));
	       
	       $('.tipss').simpletooltip({
               themes: {
                   pink: {
                       color: 'red',
                       border_color: 'red',
                       background_color: 'pink',
                       border_width: 4
                   },
                   brazil: {
                       color: 'yellow',
                       background_color: 'green',
                       border_color: 'yellow',
                       border_width: 4
                   }
               }
			});
	     },
	     error:function(data,textstatus){} 
	});		
  });

//分页
function queryExceptionLogNum(currentPage){
	clrExceptionPageOp(currentPage);	
}

/**
 * 点按钮查询或分页事件	  
 */  
function clrExceptionPageOp(currentPage){
    $.ajax({
	    type: 'post',
	    url: basePath + 'clr/excp/query.do?idChanlParam='+parent.idChanlParam+'&currentPage='+currentPage+'&adm='+new Date().getTime(),
	    data: $('#submitform').serialize(),
	    dataType: 'json',
	    success: function(data) {
	    	var myTemplate = Handlebars.compile($("#table-template").html());
	        //将json对象用刚刚注册的Handlebars模版封装，得到最终的html，插入到基础table中。
	        $('#tableList').html(myTemplate(data));
	     },
	     error:function(data,textstatus){
	    	 alert("fail:"+textstatus);
		 } 
	});	
    
}

/**
 * 点击处理事件	  
 */  
function relieve(idFailLog){
	parent.art.dialog({
	    id: 'idFailLog',
	    content: "确定将异常状态改为处理中吗？",
	    lock:true,
	    close:function(){
	       location.reload(true); 
	    },
	    button: [
	        {
	            name: '确定',
	            callback: function () {
	            	fnSure(idFailLog,status);
	            	location.reload(true);
	            },
	            focus: true
	        },
	        {
	            name: '取消',
	            close:function(){
	                location.reload(true); 
	             }
	        }
	    ]
	});		
}
/**
 * 点击已处理事件	  
 */ 
function relieveCancel(idFailLog){
	parent.art.dialog({
		id: 'idFailLog',
		content: "确定将状态改为已处理吗？",
		lock:true,
		close:function(){
			location.reload(true); 
		},
		button: [
		         {
		        	 name: '确定',
		        	 callback: function () {
		        		 fnCansel(idFailLog,status); 
		        		 location.reload(true);
		        	 },
		        	 focus: true
		         },
		         {
		        	 name: '取消',
		        	 close:function(){
		        		 location.reload(true); 
		        	 }
		         }
		         ]
	});		
}

/**
 * 点按钮处理事件confirm	  
 */
function fnSure(idFailLog,status){
	var status = 'I';
	$.ajax({
	    type: 'post',
	    url: basePath + 'clr/excp/updateClrExcpLogs.do?idFailLog='+idFailLog+'&status='+status+'&adm='+new Date().getTime(),
	    data: {'idFailLog':idFailLog,'status':status},
	    success: function(data) {
	    	location.reload(true); 
	     } 
	});
}

/**
 * 点按钮已处理事件confirm	  
 */
function fnCansel(idFailLog,status){
	var status = 'Y';
	$.ajax({
	    type: 'post',
	    url: basePath + 'clr/excp/updateClrExcpLogs.do?idFailLog='+idFailLog+'&status='+status+'&adm='+new Date().getTime(),
	    data: {'idFailLog':idFailLog,'status':status},
	    success: function(data) {
	    	location.reload(true); 
	     } 
	});
}

//小窗体交易异常对比
function todifferentPage(transType, idFailLog){
   parent.parent.diaWin(basePath+'clr/excp/query/vs.do?idFailLog='+idFailLog + '&transType=' + transType + '&idChannel=' + parent.idChanlParam +'&adm='+new Date().getTime(),"对账明细查看",300,200);
}

//生成满意度图
var handChart = function(){	
	require.config({
        paths: {
            echarts: basePath + '/js/plugin/ECharts'
        }
    });
    require(
        [
            'echarts',
            'echarts/theme/blue',
            'echarts/chart/pie'
        ],
        function (ec,theme) {
            var myChart = ec.init(document.getElementById('map_percent'),theme);
            var option = {
            		tooltip : {
    	    	        trigger: 'item',
    	    	        formatter: "{b} : {c} ({d}%)"
    	    	    },	    	   
    	    	    calculable : true,
    	    	    series : [
    	    	        {
    	    	            name:'',
    	    	            type:'pie',
    	    	            radius : '60%',
    	    	            center: ['50%', '50%'],
    	    	            data:[
    	    	                {value: $('#transCount').text(), name:'交易笔数'},
    	    	                {value: $('#refundCount').text(), name:'退款笔数'},
    	    	                {value: $('#lostCount').text(), name:'丢失笔数'},
    	    	                {value: $('#extraCount').text(), name:'溢出笔数'},
    	    	                {value: $('#infoDiffCount').text(), name:'信息不匹配笔数'}
    	    	            ]
    	    	        }
    	    	    ]
            	};
            myChart.setOption(option);
        }
    );
};

function yesShowBasic(params) {
	showBasic(params, 0);
}

function historyShowBasic(params) {
	showBasic(params, 1);
}

function showBasic(params, type) {
	$.ajax({
	    type: 'post',
	    url: basePath + 'clr/yesd/showBasic.do',
	    data: params,
	    dataType: 'json',
	    success: function(data) {
	        if(data.clringDateStr==''||data.clringDateStr==null){  //不存在昨天清算信息
	        	if(type == 0) {
	        		$('#baseDiv').css("display","none");
		            var url=basePath+'clr/yesd/intoNoMessage.do';
		        	$(parent.document.getElementById("iframe-" + parent.menu_order)).attr("src", url);
	        	}else {
	        		$('#baseDiv').css("display","none");
	        		parent.diaAlert('warning', params.strDate + '无交易记录');
	        	}
	        }
	        else{
	            $('#baseDiv').css("display","");
	            $('#clringDate').text(data.clringDateStr==null?'':data.clringDateStr);
	            $('#transCount').text(data.transCount);
	            $('#transAmount').text(toAmount(data.transAmount,2));
	            $('#refundCount').text(data.refundCount==null?'':data.refundCount);
	            $('#refundAmount').text(toAmount(data.refundAmount,2));
	            $('#lostCount').text(data.lostCount);
	            $('#extraCount').text(data.extraCount);
	            $('#infoDiffCount').text(data.infoDiffCount);
	            $('#settleTransAmount').text(toAmount(data.settleTransAmount,2));
	            $('#settleDrawbackAmount').text(toAmount(data.settleDrawbackAmount,2));
	            if(data.status == 'Y' & data.settleStatus != 'Y') {
	            	$(".audit").addClass("show-inline");
	            }
	            $('#status').text(data.status=='Y'?'成功':(data.idGeneralLog==null?'':'失败'));   
	            var settleStatus = "失败";
	            if(data.settleStatus == 'WA') {
	            	settleStatus = "待审核";
	            }else if(data.settleStatus == 'Y'){
	            	settleStatus = "成功";
	            }
	            $('#settleStatus').text(settleStatus);       
	            $('#failReason').text(data.failReason==null?'':data.failReason);
	            if(data.status=='Y' || data.status==null){
	            	$('#failReasonName').css("display","none");            
	                $('#failReason').css("display","none");
	            }else{
	            	$('#sucessShow').css("display","none");
	            }
	        	handChart(); 
	        }   	
	     },
	     error:function(data,textstatus){
		 },
	   });	 
}

/**
 * 点击审核按钮事件	  
 */  
function basicStatusOp(params){
	parent.art.dialog({
	    id: '',
	    content: "请对昨日清算的数据进行审核？",
	    lock:true,
	    button: [
	        {
	            name: '审核通过',
	            callback: function () {
	            	audit(params);
	            },
	            focus: true
	        },
	        {
	            name: '取消审核',
	            close:function(){
	            	location.close(); 
	             }
	        }
	    ]
	});		
}

/**
 * confirm点击确定按钮事件	  
 */
function audit(params) {
	$.ajax({
		type: 'post',
	    url: basePath + 'clr/yesd/audit.do',
	    data: params,
	    dataType: 'json',
	    success: function(data) {
	    	if(data.code !== '000') {
	    		parent.diaAlert('warning',data.msg);
	    	}else {
	    		location.reload();
	    	}
	     } 
	});
}



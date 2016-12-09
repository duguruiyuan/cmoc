var config = {};
config.pageSize = 10;
config.pageList = [10,20,30,40,50,100,200,500];

//type:有三种型号，succeed、warning、error
function diaAlert(type,text){
	if(type!='succeed'&&type!='warning'&&type!='error'){
		type='warning';
	}
	art.dialog({
		lock:true,
		fixed:true,
		resize:false,
		daration:0,
		time: 3,
		icon:type,
		title:'系统提示',
		content:text
	});
}

//弹框中有提示时，关掉提示时，再关弹框
//type:有三种型号，succeed、warning、error
function diaAlert2(type,text){
	if(type!='succeed'&&type!='warning'&&type!='error'){
		type='warning';
	}
	art.dialog({
		lock:true,
		fixed:true,
		resize:false,
		daration:0,
		icon:type,
		title:false,
		content:text,
		close:function(){
			art.dialog.close();
		}
	});
}

//弹框中有提示时，关掉提示时，再回调fn函数
//type:有三种型号，succeed、warning、error
function diaAlert3(type,text,fn){
	if(type!='succeed'&&type!='warning'&&type!='error'){
		type='warning';
	}
	art.dialog({
		lock:true,
		fixed:true,
		resize:false,
		daration:0,
		icon:type,
		title:false,
		content:text,
		close:function(){
			fn();
		}
	});
}

function diaWin(url, title, width, height){
	if(!width) {
		width = 600;
	}
	if(!height) {
		height = 500;
	}
	art.dialog.open(url,{
		id:'diaWinId',
		title:title,
		lock:true,
		resize:false,
		duration:0,
		width:607,
		height:500});
	//art.dialog.close()
}

//检查是否存在全角符号
//True 没有全角，False有全角
function islegal(str)
{
    for (var i = 0; i < str.length; i++){
        strCode = str.charCodeAt(i);
        if ((strCode > 65248) || (strCode == 12288)){
            return false;
        }
    }
    return true;
}

function uploadInit(id, url, resourceId, type) {
	$("#" + id).fileinput({
        uploadUrl: url,
        showRemove : false,
        language : 'zh',
        initialPreviewShowDelete:true, 
        previewFileIcon: '<i class="fa fa-file"></i>',
        previewFileIconSettings: {
    		'docx': '<i class="fa fa-file-word-o text-primary"></i>',
	        'xls': '<i class="fa fa-file-excel-o text-success"></i>',
	        'xlsx': '<i class="fa fa-file-excel-o text-success"></i>',
	        'pptx': '<i class="fa fa-file-powerpoint-o text-danger"></i>',
	        'pdf': '<i class="fa fa-file-pdf-o text-danger"></i>',
	        'zip': '<i class="fa fa-file-archive-o text-muted"></i>'
        },
        uploadExtraData: function(previewId, index) {
            var obj = {};
        	obj.resourceId = resourceId != null ? $("#" + resourceId).val() : $("#resourceId").val();
        	obj.type = type != null ? $("#" + type).val() : $("#type").val();
        	return obj;
        },
        slugCallback: function(filename) {
            return filename.replace('(', '_').replace(']', '_');
        }
	});
}

//图片上传预览    IE是用了滤镜。
function previewImage(file)
{
  var MAXWIDTH  = 260; 
  var MAXHEIGHT = 180;
  var div = document.getElementById('preview');
  if (file.files && file.files[0])
  {
      div.innerHTML ='<img id=imghead>';
      var img = document.getElementById('imghead');
      img.onload = function(){
        var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
        img.width  =  rect.width;
        img.height =  rect.height;
//         img.style.marginLeft = rect.left+'px';
        img.style.marginTop = rect.top+'px';
      }
      var reader = new FileReader();
      reader.onload = function(evt){img.src = evt.target.result;}
      reader.readAsDataURL(file.files[0]);
  }
  else //兼容IE
  {
    var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
    file.select();
    var src = document.selection.createRange().text;
    div.innerHTML = '<img id=imghead>';
    var img = document.getElementById('imghead');
    img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
    var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
    status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
    div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
  }
}
function clacImgZoomParam( maxWidth, maxHeight, width, height ){
    var param = {top:0, left:0, width:width, height:height};
    if( width>maxWidth || height>maxHeight )
    {
        rateWidth = width / maxWidth;
        rateHeight = height / maxHeight;
         
        if( rateWidth > rateHeight )
        {
            param.width =  maxWidth;
            param.height = Math.round(height / rateWidth);
        }else
        {
            param.width = Math.round(width / rateHeight);
            param.height = maxHeight;
        }
    }
     
    param.left = Math.round((maxWidth - param.width) / 2);
    param.top = Math.round((maxHeight - param.height) / 2);
    return param;
}

/**
 * 正数校验
 */
var positiveValidate = function(e) {
	if (!isNaN(e)) {
		if(e.trim()==""){
			return false;
		}
		if (e.indexOf('0') == 0) {
			if (e.indexOf('.') == 1) {
				return true;
			} else {
				return false
			}
		} else {
			return true;
		}
	} else {
		return false;
	}
};

/**
 * 百分数校验
 */
var percentNumValidate = function(e) {
	if (!isNaN(e)) {
		if(e.trim()==""){
			return false;
		}
		if (parseFloat(e)>100||parseFloat(e)<0){
			return false;
		}
		if (e.indexOf('0') == 0) {
			if (e.indexOf('.') == 1) {
				return true;
			} else {
				return false
			}
		} else {
			return true;
		}
	} else {
		return false;
	}
};

/**
 * 去掉字符中所有空格
 */
var excludeSpace = function(e) {
	return e.replace(/(^\s*)|(\s*$)/g, '');
};

$(".number").blur(function() {
	var value = this.value;
	if(value) {
		if(!positiveValidate(value)) {
			mui.alert('请输入正确格式','系统提示');
			this.value = null;
		}
	}
})

function getNullString(v){
	return (v == undefined || v == null) ? "" : v;
}

function getTakeRecord(hmId){
	var thirdHtml;
	thirdHtml=takeList(hmId);
	
	$("#takeNumDiolog").html(thirdHtml);
	thirdAcctDialog = $('#takeNumDiolog').dialog({
		title : "资产列表",
		modal : true,
		width : 1250,
		height : 400,
		top : 200,
		draggable : true,
		//resizable : true,
		//buttons : '#btns',
		onClose : function() {
			$('#takeNumDiolog').empty();
		}
	}).show();
}

function takeList(hmId){
	var html="<table class='table table-condensed'><thead><tr>"
					+"<th style='text-align: center;'>透明人编号</th>"
					+"<th style='text-align: center;'>透明人姓名</th>"
					+"<th style='text-align: center;'>透明人电话</th>"
					+"<th style='text-align: center;'>活动名称</th>"
					+"<th style='text-align: center;'>活动期数</th>"
					+"<th style='text-align: center;'>活动时间</th>"
					+"<th style='text-align: center;'>活动类型</th>"
					+"<th style='text-align: center;'>图片数</th>"
					+"<th style='text-align: center;'>视频数</th>"	
				+"</tr></thead><tbody>";
	$.ajax({
		url : basePath + "/hm/json/takeList",
	    type : 'post',
	    data : {
	    	hmId : hmId
	    },
	    dataType : 'json',
	    async:false,
		cache:false,  
	    success : function(data){
	    	if(data.code == '000'){
    			if(data.data !=null){
	    			var dataHtml=buildAssetData(data.data);
	    			html=html+dataHtml;
    			}else{
    				dataExceptionFlag=true;
    			}
    		}else{
    			$.messager.alert('系统提示', data.msg,'warning');
    		}
	    }
	});
	html=html+"</tbody></table>";
	return html;
}

function buildAssetData(obj){
	var dataHtml="";
	$.each(obj, function(n,v) {
		dataHtml += "<tr>"
			+"<td remark='透明人编号' style='text-align: center;'>"+getNullString(v.hmId)+"</td>"
			+"<td remark='透明人姓名' style='text-align: center;'>"+getNullString(v.hmName)+"</td>"
			+"<td remark='透明人电话' style='text-align: center;'>"+getNullString(v.hmMobile)+"</td>"
			+"<td remark='活动名称' style='text-align: center;'>"+getNullString(v.activityName)+"</td>"
			+"<td remark='活动期数' style='text-align: center;'>"+getNullString(v.activityNum)+"</td>"
			+"<td remark='活动时间' style='text-align: center;'>"+getTime(v.startDate, "yyyy/MM/dd")+"</td>"	
			+"<td remark='活动类型' style='text-align: center;'>"+activityTypeFormat(v.activityType)+"</td>"
			+"<td remark='图片数' style='text-align: center;'>"+Number((v.imageNum == null || v.imageNum == "") ? 0 : v.imageNum)+"</td>"
			+"<td remark='视频数' style='text-align: center;'>"+Number((v.videoNum == null || v.videoNum == "") ? 0 : v.videoNum)+"</td>"
		+"</tr>";
	});	
	return dataHtml;
}
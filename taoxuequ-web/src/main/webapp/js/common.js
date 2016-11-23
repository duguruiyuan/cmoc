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

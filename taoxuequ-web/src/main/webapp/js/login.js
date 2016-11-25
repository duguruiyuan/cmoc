$(document).ready(function(){
    $('#loginForm').bind('submit', function(){
    	if($("#j_username").val() == "") {
			$("#msgShow").html("用户名不能为空，请输入用户名");
			return false;
    	}
    	if($("#j_password").val() == "") {
			$("#msgShow").html("密码不能为空，请输入密码");
			return false;
    	}
    	var dataPara = getFormJson(this);
    	$.ajax({
    		url: getFullURL("j_spring_security_check"),
    		type: 'POST',
    		data: dataPara,
    		success: function(data) {
    			if (data != "success") {
    				$("#msgShow").html(data);
    				clear();
    			} else {
    				window.location.href = getFullURL("/auth/main");
    			}
    		},
    		error: function(data) {
    			$("#msgShow").html("系统异常,请联系管理员");
    		}
    	});
    	return false;
    });
});

function getFormJson(frm) {
	var o = {};
	var a = $(frm).serializeArray();
	$.each(a, function () {
		if (o[this.name] !== undefined) {
			if (!o[this.name].push) {
				o[this.name] = [o[this.name]];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});

	return o;
}

function changeVerifyCode(){
	$("#verifyCodeImgId").attr('src',getFullURL('servlet/VerifyCodeServlet?t=' + (new Date()).getTime())); 
}

function clear(){
	$("#veryCode").val("");
}

function isRightCode(username,password){     
    var code = $("#veryCode").val();           
    $.ajax({     
        type:"POST",     
        url:"resultServlet/validateCode",     
        data:{'c':code},
        dataType:"text",     
        success:function(data){
            if($.trim(data) =='999'){
                showMessage("验证码错误");
                $("#veryCode").val("");                
            }else{
            	formsubmit(username,password);
            }
        }     
    });     
}     


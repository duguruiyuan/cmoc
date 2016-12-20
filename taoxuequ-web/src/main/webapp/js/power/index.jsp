<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<html>
<head>
    <title>黑名单管理 - 反欺诈管理 - 综合资产管理系统</title>
    <jsp:include page="${path}/layout/header.jsp" />
</head>
<body>

<!-- 顶部  begin-->
<jsp:include page="${path}/layout/top.jsp"></jsp:include>
<!-- 顶部 end -->

<div id="middle">

    <jsp:include page="${path}/layout/left_menu.jsp"></jsp:include>

    <div class="right"  id="mainFrame">

        <div class="right_cont">
            <!-- 当前位置begin -->
            <jsp:include page="${path}/layout/localinfo.jsp" />
            <!-- 当前位置end -->


            <div class="title_right">
                <strong>黑名单管理</strong>
            </div>
            <div>
                <div class="easyui-layout" style="width:100%;height:100%;">
                    <div style="width: 100%; margin: auto;">

                        <form id="blacklistManagement" class="form-search easyui-form" method="post">
                        	<input type="hidden" name="menuId" value="${menu.id}" />
                            <table class="table table-bordered">
                                <tbody>
                                    <tr>
                                        <td class="text-right">黑名单编号</td>
                                        <td><input class="input-medium search-query search-comp th25 easyui-textbox"   name="blackListNo" value="" /></td>
                                        <td class="text-right">客户姓名</td>
                                        <td><input class="input-medium search-query search-comp th25 easyui-textbox"   name="custName" value="" /></td>
                                        <td class="text-right">证件号码</td>
                                        <td><input class="input-medium search-query search-comp th25 easyui-textbox"   name="idNo" value="" /></td>
                                    </tr>
                                    <tr>
                                        <td class="text-right">客户编码</td>
                                        <td><input class="input-medium search-query search-comp th25 easyui-textbox"   name="custId" value="" /></td>
                                        <td class="text-right">状态</td>
                                        <td>
                                            <select class="statusDict input-medium search-query search-comp th25 easyui-combobox"   name="status"></select>
                                        </td>
                                        <td class="text-right">来源</td>
                                        <td>
                                            <select class="sourceDict input-medium search-query search-comp th25 easyui-combobox"   name="sourceType"></select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="8">
                                            <a type="button" class="btn btn-default" onclick="searchCar('blacklistManagement')"><i class="icon-search icon-white"></i> 查找</a>
                                            <a type="button" class="btn btn-danger" onclick="cleanFormPanel('blacklistManagement')"><i class="fa fa-trash"></i> 清空</a>
                                            <a class="btn btn-info"  href="javascript:void(0);" onclick="blacklistManagementAdd();" role="button" ><i class="icon-plus icon-white"></i> 添加</a>
                                            <span style="margin:0 10px;">&nbsp;</span>
                                            <a class="btn btn-success "disabled  href="javascript:void(0);" id="arraignBlacklistDataBtn" onclick="" role="button" ><i class="fa fa-check-circle-o icon-white"></i> 提审</a>
                                            <a class="btn btn-danger" disabled href="javascript:void(0);"  id="removeBlacklistDataBtn" onclick=""role="button" ><i class="fa fa-remove icon-white"></i> 删除</a>
                                            <a class="btn btn-warning" disabled href="javascript:void(0);" id="cancelBlacklistDataBtn" onclick="" role="button" ><i class="fa fa-reply icon-white"></i> 取消</a>

                                            <a class="btn btn-primary"  href="javascript:void(0);" id="loadBlacklistDataBtn" onclick="loadBlacklistData()" role="button" ><i class="fa fa-cloud-upload icon-white"></i> 导入</a>
                                            <a class="btn btn-primary"  href="javascript:void(0);" id="exportBlacklistDataBtn" onclick="exportBlacklistData()" role="button" ><i class="fa fa-cloud icon-white"></i> 导出</a>
                                            <%--<a class="btn btn-info"  href="javascript:void(0);" onclick="addMortgage();"><i class="icon-plus icon-white"></i> 添加</a>--%>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>

                        <div style="width:100%;height:100%;">
                            <div class="title_right">
                                <strong>黑名单管理一览表</strong>
                            </div>

                            <jsp:include page="${path}/layout/datagridTpl/search.jsp" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="loadTemplateWindow" class="easyui-window" title="导入操作" data-options="modal:true,closed:true">
    <div id="uploadDataWrapper" style="padding:60px 20px;">
        <div id="vehicleAppearance">
            <div id="vehicleAppearanceBtn" class="uploadBoxWrapper">
                <span class="btn btn-success fileinput-button">
                    <i class="fa fa-plus"></i>
                    <span>选择文件...</span>
                    <input id="vehicleAppearanceUx" type="file" name="files" data-url="${pageContext.request.contextPath}/blacklist/json/import"  class="btn btn-small btn-info">
                </span>

                <span class="btn btn-success fileinput-button" onclick="downloadTemplate()">
                    <i class="fa fa-cloud"></i>
                    <span>模版下载</span>
                </span>

            </div>

            <div class="afterUploadBox">
                <div id="vehicleAppearanceHiddens"></div>
                <ul id="vehicleAppearanceImageList" class="nav nav-list inline" ></ul>
            </div>
        </div>
    </div>

</div>



<div id="cancelBlacklistDataWindow" class="easyui-window" title="取消操作" data-options="modal:true,closed:true" style="width:600px;height:350px;">
    <div style="padding:60px 20px;">

        <form id="cancelBlacklistDataForm" class="form-search">
            <table class="table table-bordered">

                <input type="hidden" name="ids[]" id="cancelId">

                <tbody>
                <tr>

                    <td class="text-center" colspan="2">
                        确认取消 <span style="color:#f00;">客户名称 <span id="cancelCustName"></span></span>的黑名单记录？
                    </td>

                </tr>
                <tr>
                    <td class="text-right">备注</td>
                    <td>
                        <textarea name="remark" id="cancelRemark" style="width:498px;height:48px;" ></textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="text-center">
                        <a class="btn btn-success" href="javascript:void(0);"
                           onclick="cancelBlacklistData()" role="button"><i
                                class="fa fa-check-circle-o icon-white"></i> 确认</a>
                        <a class="btn btn-danger" href="javascript:void(0);"
                           onclick="cancelBlacklistDataWindowClose()" role="button"><i
                                class="fa fa-remove"></i> 取消</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>


</div>



<input type="hidden" name="0x1" value="" id="get_dptName"/>
<input type="hidden" name="0x2" value="" id="get_IS_LEAF"/>
<input type="hidden" name="0x3" value="" id="get_valid"/>
<input type="hidden" name="0x4" value="" id="get_id"/>
<input type="hidden" name="0x4" value="${menu.id}" id="menuIdHidden"/>

<script type="text/javascript" src="<%=path%>/js/loans/common_index.js"></script>

<script type="text/javascript">
    var modifyKey,blacklistManagementInfoGrid;

    $(function(){
        uploadVehicleAppearance();
        blacklistManagementGrid();
        initBlacklistDictList('blacklistManagement');
    });

    /**
     *
     */
    function cleanFormPanel(formId){
        $("#" + formId)[0].reset();
    }

    function initBlacklistDictList(formId){
        $.ajax({
            url: basePath + '/blacklist/dict/list',
            type: 'POST',
            dataType: 'json',
            error : function(data) {
                $.messager.progress('close');
                $.messager.alert('系统提示', data.msg, 'error');
            },
            success: function (data) {

                // reasonDict 原因
                initCombobox1(formId, data['reasonDict'], 'reasonDict', 'reasonHidden', 'reasonValueHidden');
                // idTypeDict 证件类型
                initCombobox1(formId, data['idTypeDict'], 'idTypeDict', 'idTypeHidden', 'idTypeValueHidden');
                // sourceDict 信息来源
                initCombobox1(formId, data['sourceDict'], 'sourceDict', 'sourceTypeHidden', 'sourceTypeValueHidden');
                // statusDict 状态
                initCombobox1(formId, data['statusDict'], 'statusDict', 'userTypeIdHidden', 'userTypeValueHidden');
                // custTypeDict 客户类型
                initCombobox1(formId, data['custTypeDict'], 'custTypeDict', 'custTypeIdHidden', 'custTypeValueHidden');


            }
        });
    }
    /**
     * 模版下载操作
     */
    function downloadTemplate(){
        window.open('<%=path %>/doctemplate/黑名单模板.xls','_blank')
    }


    /**
     *  电调附件上传
     *
     */
    function uploadVehicleAppearance(){
        $('#vehicleAppearanceUx').fileupload({
            autoUpload : false,
            dataType: 'json',
            add: function (e, data) {
                data.context = $('<p />').html(data.files[0].name+'     '+'<button class="btn btn-warning margin-left-5 margin-right-5 btn-small"> 开始上传</button>')
                        .appendTo($("#vehicleAppearanceBtn"))
                        .click(function () {
                            data.context = $('<p/>').text('上传中... 请稍后...').replaceAll($(this));
                            data.submit();
                        });
            },
            done: function (e, data) {
                data.context.text(' ');
                var html = '<input type="hidden" name="attachmentIds"  class="attachmentIds" value="'+data.result.ids+'" id="file_'+data.result.ids+'"/>';
                $("#vehicleAppearanceHiddens").append(html);
                $.messager.alert('系统提示', data.result.msg, 'info',function(){
                    $("#loadTemplateWindow").window('close');
                    $('#dataGrid').datagrid('reload');
                });
            }
        });
    }

    /**
     *
     */
    function loadBlacklistData(){
        $("#loadTemplateWindow").window('open');
    }
    /**
     *  提审黑名单
     */
    function arraignBlacklistData(){
        var selected = $('#dataGrid').datagrid('getSelections');
        var ids = [];
        selected.map(function(item,index){
            if(item.status == '02'){
                ids.push(item.id);
            }

        });

        if( selected == null || selected == '' || typeof selected  == 'undefined'){
            $.messager.alert("系统提示","请点选√ 黑名单 ","info");
            return ;
        }

        $.ajax({
            url: basePath + '/blacklist/json/arraign',
            type: 'POST',
            dataType: 'json',
            data: {
                ids : ids,
            },
            error : function(data) {
                $.messager.progress('close');
                $.messager.alert('系统提示', data.msg, 'error');
            },
            success: function (data) {
                $.messager.alert('系统提示', data.msg, 'info', function () {
                    return $('#dataGrid').datagrid('reload');
                });
            }
        });

    }
    //初始化一览表
    function blacklistManagementGrid(){

        blacklistManagementInfoGrid = $('#dataGrid').datagrid({
            url : basePath+"/blacklist/list/query",
            fit : true,
            fitColumns : true,
            border : true,
//            singleSelect : true,
            pagination : true,
            idField : 'id',
            pageSize : 10,
            autoRowHeight : 800,
            pageList : [ 10 ],
            nowrap : true,
            striped : true,
            queryParams : {
                "menuId" :$("#menuIdHidden").val()
            },
            columns : [ [
                         {field : 'chaoz',width : '8%',align : 'center',checkbox:'true',title : '<b>操作</b>',
//                             formatter:function(value,row,index){
//                             	var apply = "";
//                             	if(row.ipiecesNo == "" || typeof row.ipiecesNo == 'undefined'){
//                             		apply ='<a href="javascript:void(0);"  onclick="applyInfo('+row.id+', \'mortgage_loan\',\''+row.loanNo+'\');"  >申请</a> ';
//                             	}
//         						var clook ='<a href="javascript:void(0);"  onclick="checkInfo(\''+row.loanNo+'\');"  >查看</a> ';
//         						var modify = "";
//                             	if(row.operateType == "" || typeof row.operateType == 'undefined' || row.operateType == 0 ){
//                             		modify ='<a href="javascript:void(0);"   onclick="modifyInfo(\''+row.loanNo+'\', \'mortgage_loan\');"  >修改</a> ';
//                             	}
//         						return apply + clook + modify;
//                             }
                         },
                         {field : 'blackListNo',width : '10%',align : 'center',title : '<b>黑名单编号</b>'},
                         {field : 'custId',width : '8%',align : 'center',title : '<b>客户编码</b>'},
                         {field : 'custName',width : '10%',align : 'center',title : '<b>客户名称</b>'},
                         {field : 'idNo',width : '10%',align : 'center',title : '<b>证件号码</b>'},
                         {field : 'mobile',width : '10%',align : 'center',title : '<b>联系电话</b>'},
                         {field : 'custTypeValue',width : '6%',align : 'center',title : '<b>客户类型</b>'},
                         {field : 'sourceTypeValue',width : '6%',align : 'center',title : '<b>信息来源</b>'},
                         {field : 'status',width : '6%',align : 'center',title : '<b>状态</b>',
                             formatter:function(value,row,index){
                             	if(row.status =="01"){
                                    return '正常'
                                }else if(row.status =="02"){
                                    return '待提审'
                                }else if(row.status =="03"){
                                    return '待审核'
                                }else if(row.status =="04"){
                                    return '审核不通过'
                                }else if(row.status =="05"){
                                    return '取消'
                                }
                             }
                         },
                         {field : 'busiTypeValue',width : '6%',align : 'center',title : '<b>业务类型</b>'},
                         {field : 'createTime',width : '6%',align : 'center',title : '<b>创建时间</b>'},
                         {field : 'createrName',width : '6%',align : 'center',title : '<b>创建人</b>'},
                         {field : 'addTypeValue',width : '6%',align : 'center',title : '<b>新增方式</b>'},

            ]],
            onDblClickRow:function(index,row){
                var blackListNo = row.blackListNo;
                window.open( basePath + "/blacklist/detail/"+blackListNo);
            },


            onSelect:function(index,row){
                var statusArr = [];
                var selected = $('#dataGrid').datagrid('getSelections');
                var result = false;
                var status01 = "01";//正常
                var status02 = "02";//待提审
                var status03 = "03";//待审核
                var status04 = "04";//审核不通过
                var status05 = "05";//取消
                selected.map(function(item,index){
                    var status = item.status;
                    statusArr.push(status);
                });
                var s1 = statusArr.indexOf(status01);
                var s2 = statusArr.indexOf(status02);
                var s3 = statusArr.indexOf(status03);
                var s4 = statusArr.indexOf(status04);
                var s5 = statusArr.indexOf(status05);
//                console.log("------- onSelect Start -------");
//                console.log("01 ||"+s1);
//                console.log("02 ||"+s2);
//                console.log("03 ||"+s3);
//                console.log("04 ||"+s4);
//                console.log("05 ||"+s5);
//                console.log("------- onSelect End -------");

                if( s1 == 0 && s2 == -1 && s3 == -1 && s4 == -1 && s5 == -1 ){
//                    console.log("正常 可用");
                    $("#arraignBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                    $("#removeBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                    $("#cancelBlacklistDataBtn").attr("disabled",false).attr("onclick","cancelBlacklistDataWindow()");
                }else if (s1 == -1 && s2 == 0 && s3 == -1 && s4 == -1 && s5 == -1 ){
//                    console.log("待提审 可用");
                    $("#arraignBlacklistDataBtn").attr("disabled",false).attr("onclick","arraignBlacklistData()");
                    $("#removeBlacklistDataBtn").attr("disabled",false).attr("onclick","removeBlacklistData()");
                    $("#cancelBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                }else if (s1 == -1 && s2 == -1 && s3 == 0 && s4 == -1 && s5 == -1){
//                    console.log("待审核 可用");
                    $("#arraignBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                    $("#removeBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                    $("#cancelBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                }else if (s1 == -1 && s2 == -1 && s3 == -1 && s4 == 0 && s5 == -1){
//                    console.log("审核不通过 可用");
                    $("#arraignBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                    $("#removeBlacklistDataBtn").attr("disabled",false).attr("onclick","removeBlacklistData()");
                    $("#cancelBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                }else if (s1 == -1 && s2 == -1 && s3 == -1 && s4 == -1 && s5 == 0){
//                    console.log("取消 可用");
                    $("#arraignBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                    $("#removeBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                    $("#cancelBlacklistDataBtn").attr("disabled",false).attr("onclick","cancelBlacklistDataWindow()");
                }else if (s1 == -1 && s2 == 0 && s3 == 1 && s4 == -1 && s5 == -1){
//                    console.log("状态不一致 无可用");
                    $("#arraignBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                    $("#removeBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                    $("#cancelBlacklistDataBtn").attr("disabled",true).attr("onclick","")
                }else{
                    $("#arraignBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                    $("#removeBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                    $("#cancelBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                }

            },
            onUnselect:function(index,row){
                var statusArr = [];
                var selected = $('#dataGrid').datagrid('getSelections');
                var result = false;
                var status01 = "01";//正常
                var status02 = "02";//待提审
                var status03 = "03";//待审核
                var status04 = "04";//审核不通过
                var status05 = "05";//取消

                if(selected == '' || selected == null || selected.length == 0){
                    $("#arraignBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                    $("#removeBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                    $("#cancelBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                }else{
                    selected.map(function(item,index){
                        var status = item.status;
                        statusArr.push(status);
                    });

                    var s1 = statusArr.indexOf(status01);
                    var s2 = statusArr.indexOf(status02);
                    var s3 = statusArr.indexOf(status03);
                    var s4 = statusArr.indexOf(status04);
                    var s5 = statusArr.indexOf(status05);

                    console.log("------- onUnselect Start -------");
                    console.log("01 ||"+s1);
                    console.log("02 ||"+s2);
                    console.log("03 ||"+s3);
                    console.log("04 ||"+s4);
                    console.log("05 ||"+s5);
                    console.log("------- onUnselect End -------");

                    if( s1 == 0 && s2 == -1 && s3 == -1 && s4 == -1 && s5 == -1 ){
                        console.log("正常 可用");
                        $("#arraignBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                        $("#removeBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                        $("#cancelBlacklistDataBtn").attr("disabled",false).attr("onclick","cancelBlacklistDataWindow()");
                    }else if (s1 == -1 && s2 == 0 && s3 == -1 && s4 == -1 && s5 == -1 ){
                        console.log("待提审 可用");
                        $("#arraignBlacklistDataBtn").attr("disabled",false).attr("onclick","arraignBlacklistData()");
                        $("#removeBlacklistDataBtn").attr("disabled",false).attr("onclick","removeBlacklistData()");
                        $("#cancelBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                    }else if (s1 == -1 && s2 == -1 && s3 == 0 && s4 == -1 && s5 == -1){
                        console.log("待审核 可用");
                        $("#arraignBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                        $("#removeBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                        $("#cancelBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                    }else if (s1 == -1 && s2 == -1 && s3 == -1 && s4 == 0 && s5 == -1){
                        console.log("审核不通过 可用");
                        $("#arraignBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                        $("#removeBlacklistDataBtn").attr("disabled",false).attr("onclick","removeBlacklistData()");
                        $("#cancelBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                    }else if (s1 == -1 && s2 == -1 && s3 == -1 && s4 == -1 && s5 == 0){
                        console.log("取消 可用");
                        $("#arraignBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                        $("#removeBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                        $("#cancelBlacklistDataBtn").attr("disabled",false).attr("onclick","cancelBlacklistDataWindow()");
                    }else if (s1 == -1 && s2 == 0 && s3 == 1 && s4 == -1 && s5 == -1){
                        console.log("状态不一致 无可用");
                        $("#arraignBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                        $("#removeBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                        $("#cancelBlacklistDataBtn").attr("disabled",true).attr("onclick","")
                    }else{
                        $("#arraignBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                        $("#removeBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                        $("#cancelBlacklistDataBtn").attr("disabled",true).attr("onclick","");
                    }

                }


            },
        });
        $(blacklistManagementInfoGrid).datagrid('getPager').pagination({});
    }
    function status02(status){
        if(status == '02'){
            $("#arraignBlacklistDataBtn").attr("disabled",false);
        }else{
            $("#arraignBlacklistDataBtn").attr("disabled",true);
        }
    }

    /**
     *  搜索正常还款结果
     */
    function searchCar(formId){
        blacklistManagementInfoGrid.datagrid('load', $.serializeObject($('#' + formId)));
    }

    /**
     * 申请抵押
     * @param id
     */
    function applyInfo(id, followName ,loanNo){
    	//此处抵押贷款是直接启动流程。
//    	addIntopieces(id,followName);
        $("#applyInfowindow").window('open');

    }
    


    /**
     * 添加黑名单URL跳转
     */
    function blacklistManagementAdd(){

        window.open( basePath + "/blacklist/add","_blank");
    }

    /**
     * 删除黑名单
     */
    function removeBlacklistData(){

        var selected = $('#dataGrid').datagrid('getSelections');
        var ids = [];
        selected.map(function(item,index){
            if(item.status == '02' || item.status == '04' ){
                ids.push(item.id);
            }
        });

        if( selected == null || selected == '' || typeof selected  == 'undefined'){
            $.messager.alert("系统提示","请点选√ 黑名单 ","info");
            return ;
        }
        $.messager.confirm('删除操作', '确认删除 黑名单 ?', function (r) {
            if(r){
                $.ajax({
                    url: basePath + '/blacklist/json/del',
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        ids : ids,
                    },
                    error : function(data) {
                        $.messager.progress('close');
                        $.messager.alert('系统提示', data.msg, 'error');
                    },
                    success: function (data) {
                        $.messager.alert('系统提示', data.msg, 'info', function () {
                            return $('#dataGrid').datagrid('reload');
                        });
                    }
                });
            }
        });

    }
    function cancelBlacklistDataWindow(){
//        debugger;
        var selected = $('#dataGrid').datagrid('getSelections');
        if(selected.length >= 2 || selected.length == 2){
            $.messager.alert("系统提示","不可多选 请单选！ ","info");
            return;
        }
        selected = selected[0];
        if(selected !== null){
            $("#cancelBlacklistDataWindow").window('open');
            $("#cancelId").val(selected.id);
            $("#cancelCustName").html(selected.custName);
        }
    }
    function cancelBlacklistDataWindowClose() {
        $("#cancelBlacklistDataForm").form('clear');
        $("#cancelBlacklistDataWindow").window('close');
    }
    /**
     * 取消黑名单
     */
    function cancelBlacklistData(){

        var cancelRemark = $("#cancelRemark").val();
        if(cancelRemark == '' || cancelRemark == null || typeof cancelRemark == 'undefined'){
            $.messager.alert("系统提示","请填写 备注 ！ ","info");
            return;
        }
        $.ajax({
            url: basePath + '/blacklist/json/cancel',
            type: 'POST',
            dataType: 'json',
            data: $("#cancelBlacklistDataForm").serialize(),
            error : function(data) {
                $.messager.progress('close');
                $.messager.alert('系统提示', data.msg, 'error');
            },
            success: function (data) {
                $.messager.alert('系统提示', data.msg, 'info', function () {
                     $('#dataGrid').datagrid('reload');
                        cancelBlacklistDataWindowClose();
                });
            }
        });
    }
    /*
    *
    * 导出操作
    * */
    function exportBlacklistData(){
    	$("#blacklistManagement").attr('action',basePath + '/blacklist/json/export').submit();

    }


</script>


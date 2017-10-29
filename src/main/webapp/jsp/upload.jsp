<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>上传工具-诺基亚工具管理平台</title>
	<link rel="stylesheet" type="text/css" href="../uikit/css/uikit.min.css">
	<link rel="stylesheet" type="text/css" href="../uikit/css/components/sticky.css">
	<link rel="stylesheet" type="text/css" href="../uikit/css/components/search.css">
	<link rel="stylesheet" type="text/css" href="../uikit/css/components/datepicker.css">
	<link rel="stylesheet" type="text/css" href="../uikit/css/components/form-file.css">
	<link rel="stylesheet" type="text/css" href="../uikit/css/components/progress.css">
	<link rel="stylesheet" type="text/css" href="../uikit/css/components/notify.css">
	<link rel="stylesheet" type="text/css" href="../css/common.css">
	<link rel="stylesheet" type="text/css" href="../css/upload.css">
	<link rel="stylesheet" type="text/css" href="../css/animate.css">

	<script src="../uikit/jquery.min.js"></script>
	<script src="../uikit/js/uikit.min.js"></script>
	<script src="../uikit/js/components/sticky.js"></script>
	<script src="../uikit/js/components/search.js"></script>
	<script src="../uikit/js/components/grid.js"></script>
	<script src="../uikit/js/components/pagination.js"></script>
	<script src="../uikit/js/components/datepicker.js"></script>
	<script src="../uikit/js/components/notify.js"></script>
	<script src="../js/clamp.min.js"></script>
	<script src="../js/jquery.color.js"></script>

    <link rel="stylesheet" type="text/css" href="webuploader.css"/>
    <script type="text/javascript" src="jquery-3.2.1.js"></script>
    <script type="text/javascript"  src="webuploader.js"></script>
    <style>
        .webuploader-pick{
            background-color: red;
        }
    </style>
</head>
<body>
<div id="dropNavContainer" class="uk-width-1-1">
    <a class="uk-navbar-brand" href="/" style="margin-left: 35px;margin-top: 10px;"><img src="../img/nokia.png" style="width:120px;"></a>
    <a class="uk-navbar-brand" href="/" style="margin-top:11px;font-size: 20px;color:rgb(18,65,145);line-height: 40px;padding-left: 0;"><strong>工具管理平台</strong></a>
    <span>
        <form id="dropSearchForm" method="get" action="/SearchPageServlet">
            <input id="dropSearchInput" name="searchKey" type="text" placeholder="Search Tools You Like...">
            <input class="dropSubmit" type="submit" value=""/>
            <i class="uk-icon-search dropSearchButton"></i>
        </form>
    </span>
    <c:if test="${loggedUserBean==null}">
        <a class="dropLogin" href="#loginBox" data-uk-modal>登录</a>
    </c:if>
    <c:if test="${loggedUserBean!=null}">
        <div class="uk-navbar-flip dropLogined">
            <ul class="uk-navbar-nav">
                <li class="uk-parent" data-uk-dropdown>
                    <a class="dropNavUser">
                        <img src="../img/headIcon.jpg">
                        <div class="navUserName uk-hidden-small">${loggedUserBean.userName}</div>
                        <i class="uk-icon-chevron-down uk-icon-xsmall uk-hidden-small navUserDown"></i>
                    </a>
                    <div class="uk-dropdown uk-dropdown-navbar dropdownList">
                        <ul class="uk-nav uk-nav-navbar uk-list-striped dropDownStyle">
                            <li><a href="/UserCenterServlet?uid=${loggedUserBean.userId}">个人中心</a></li>
                            <li><a href="/UploadPageServlet">工具上传</a></li>
                            <c:if test="${loggedUserBean.userLevel==2}">
                                <li><a href="/manage">管理中心</a></li>
                            </c:if>
                            <li><a href="/logout">退出</a></li>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>
    </c:if>
</div>
	
	<div id="uploadBox" class="uk-container uk-container-center uk-width-3-4 uk-grid">
        <div class="uk-width-1-5">
            <div class="labelHeight"><label class="uk-form-label" for="form-h-a"><strong>工具名称：</strong></label></div>
            <div class="labelHeight"><label class="uk-form-label" for="form-h-b"><strong>工具版本：</strong></label></div>
            <div class="labelHeight" style="position: relative;top: -3px;"><label class="uk-form-label" for="form-h-c" onclick="calcuteSize()"><strong>工具附件：</strong></label></div>
            <div class="labelHeight"><label class="uk-form-label" for="form-h-d"><strong>工具分类：</strong></label></div>
            <div class="labelHeight"><label class="uk-form-label" for="form-h-e"><strong>工具标签：</strong></label></div>
            <div class="labelHeight" style="height: 210px;"><label class="uk-form-label" for="form-h-f"><strong>工具简介：</strong></label></div>
            <div class="labelHeight"><label class="uk-form-label" for="form-h-g"><strong>自述文档：</strong></label></div>
        </div>
        <div class="uk-width-4-5">
            <form id="uploadForm" class="uk-form uk-form-horizontal">
                <input name="uid" type="hidden" value="1">
                <div class="uk-form-row">
                    <div class="uk-form-controls uk-width-1-1">
                        <input type="text" id="form-h-a" name="toolName" class="uk-width-3-5" placeholder="上传工具的名称" autofocus="autofocus"  onblur="isFilled(this)" onkeyup="hiddenErrorHint(this)">
                        <span class="uk-alert uk-alert-danger errorHint">请输入工具名称</span>
                    </div>
                </div>

                <div class="uk-form-row">
                    <div class="uk-form-controls uk-width-1-1">
                        <input type="text" id="form-h-b" name="version" class="uk-width-2-5" placeholder="上传工具的版本"  onblur="isFilled(this)" onkeyup="hiddenErrorHint(this)">
                        <span class="uk-alert uk-alert-danger errorHint">请输入工具版本</span>
                    </div>
                </div>

                <div class="uk-form-row">
                    <div class="uk-form-controls uk-width-1-1">
                        <div id="filePicker" style="display: none;"></div>
                        <span id="uploadIcon" onclick="toolChoose()" ><i class="uk-icon-folder-open uk-margin-small-right"></i>选择附件</span>
                        <label id="filePath"></label>
                        <span class="uk-alert uk-alert-danger errorHint">请添加工具附件</span>
                    </div>
                </div>

                <div class="uk-form-row">
                    <div class="uk-form-controls uk-width-1-1">
                        <select id="form-h-d" name="categoryId" onblur="isFilled(this)">
                            <option></option>
                            <option value="1">办公</option>
                            <option value="2">网络</option>
                            <option value="3">安全</option>
                            <option value="4">系统维护</option>
                            <option value="5">其他</option>
                        </select>
                        <span class="uk-alert uk-alert-danger errorHint">请选择工具类别</span>
                    </div>
                </div>

                <div class="uk-form-row">
                    <div class="uk-form-controls uk-width-1-1">
                        <input type="text" id="form-h-e" name="tags" class="uk-width-3-5" placeholder="添加标签（多个标签用空格隔开）"  onblur="isFilled(this)" onkeyup="hiddenErrorHint(this)">
                        <span class="uk-alert uk-alert-danger errorHint">请输入工具标签</span>
                    </div>
                </div>

                <div class="uk-form-row" style="height: 215px;">
                    <div class="uk-form-controls uk-width-1-1">
                        <textarea id="form-h-f" name="briefIntro" class="uk-width-3-5" rows="6" placeholder="对该版本的工具作简要说明（限300字）"  onblur="isFilled(this)"></textarea>
                        <span class="uk-alert uk-alert-danger errorHint" style="top:5px;">请输入工具简介</span>
                    </div>
                </div>

                <div class="uk-form-row" style="height: 240px;">
                    <div class="uk-form-controls uk-width-1-1">
                        <textarea id="form-h-g" name="description" class="uk-width-3-5" rows="8" placeholder="对该工具的具体说明，包括使用方法，注意事项"  onblur="isFilled(this)" onkeyup="hiddenErrorHint(this)"></textarea>
                        <span class="uk-alert uk-alert-danger errorHint" style="top:5px;">请输入自述文档</span>
                    </div>                    
                </div>

                <div class="uk-form-row" style="height:80px;">
                    <div class="uk-form-controls uk-width-1-1">
                        <input type="button" id="uploadButton" class="uk-width-1-3" value="开始上传" onclick="startUpload()"/>
                    </div>
                </div>

                <div class="uk-form-row">
                    <div class="uk-form-controls uk-width-1-1">
                        <button id="stopUpload" class="uk-width-1-4" type="button" onclick="stop()">暂停上传</button>
                        <button id="cancelUpload" class="uk-width-1-4">取消上传</button>
                    </div>
                </div>
            </form>
            <div id="progressAll" class="uk-progress uk-progress-striped uk-active">
                <div id="progressBar" class="uk-progress-bar"><span id="progressSum"></span></div>
            </div>
        </div>
	</div>

	<div id="uploadMargin"></div>

	<script src="../js/common.js" type="text/javascript"></script>
	<script src="../js/upload.js" type="text/javascript"></script>
</body>
<script type="text/javascript">

    var uniqueFileTag;
    WebUploader.Uploader.register(
        {
            "before-send-file":"beforeSendFile",
            "before-send":"beforeSend",
            "after-send-file":"afterSendFile"
        },
        {
            beforeSendFile:function (file) {
                var deferred = WebUploader.Deferred();
                (new WebUploader.Uploader()).md5File(file,0, 5 * 1024*1024)
                    .progress(function (percentage) {
                        $("#" + file.id).find("div.state").text("正在获取文件信息");
                    })
                    .then(function (val) {
                        uniqueFileTag = val;
                        $("#" + file.id).find("div.state").text("成功获取文件信息");
                        deferred.resolve();
                    });
                return deferred.promise();
            },
            beforeSend:function (block) {
                var deferred = WebUploader.Deferred();
                $.ajax(
                    {
                        type:"POST",
                        url:"/UploadActionServlet?action=checkChunk",
                        data:{
                            uniqueFileTag:uniqueFileTag,
                            chunk: block.chunk,
                            chunkSize:block.end - block.start
                        },
                        dataType: "json",
                        success:function (response) {
                            if (response.ifExist){
                                deferred.reject();
                            }else {
                                deferred.resolve();
                            }

                        }
                    }
                );
                this.owner.options.formData.uniqueFileTag=uniqueFileTag;
                deferred.resolve();
                return deferred.promise();
            },
            afterSendFile:function (file) {
                $.ajax(
                    {
                        type:"POST",
                        url:"/UploadActionServlet?action=mergeChunks",
                        data:{
                            "uniqueFileTag":uniqueFileTag,
                            "fileName":file.name,
                            "toolName":$("input[name='toolName']").val(),
                            "version":$("input[name='version']").val(),
                            "categoryId":$("select[name='categoryId']").val(),
                            "tags":$("input[name='tags']").val(),
                            "briefIntro":$("textarea[name='briefIntro']").val(),
                            "description":$("textarea[name='description']").val()
                        },
                        success:function (result) {
                            uploadSuccess(result);
                        }
                    }
                );
            }

        }
    );
    var uploader = WebUploader.create(
        {
            server:"/UploadChunkServlet",
            pick: {
                id:"#filePicker",
                multiple:false
            },
            auto:false,
            chunked:true,
            chunkSize:10 * 1024 * 1024,
            prepareNextFile:true,
            threads:1
        }
    );


    uploader.on( 'fileQueued', function( file ) {
        calcute(file);
    });
    uploader.on( 'uploadProgress', function( file, percentage ) {
        nowProgress(percentage);
    });

    uploader.on( 'uploadSuccess', function( file ) {
    });

    uploader.on( 'uploadError', function( file ) {
        uploadFail();
    });

    uploader.on( 'uploadComplete', function( file ) {
    });

    function start(){
        uploader.upload();
        $('#stopUpload').attr("onclick","stop()");
        $('#stopUpload').text("暂停上传");
    }

    function stop(){
        uploader.stop(true);
        $('#stopUpload').attr("onclick","start()");
        $('#stopUpload').text("继续上传");
    }
</script>

</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>更新工具-诺基亚工具管理平台</title>
	<link rel="stylesheet" type="text/css" href="../uikit/css/uikit.min.css">
	<link rel="stylesheet" type="text/css" href="../uikit/css/components/sticky.css">
	<link rel="stylesheet" type="text/css" href="../uikit/css/components/search.css">
	<link rel="stylesheet" type="text/css" href="../uikit/css/components/datepicker.css">
	<link rel="stylesheet" type="text/css" href="../uikit/css/components/form-file.css">
	<link rel="stylesheet" type="text/css" href="../uikit/css/components/progress.css">
	<link rel="stylesheet" type="text/css" href="../uikit/css/components/notify.css">
	<link rel="stylesheet" type="text/css" href="../css/common.css">
	<link rel="stylesheet" type="text/css" href="../css/update.css">
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
</head>
<body>
<div id="dropNavContainer" class="uk-width-1-1">
	<a class="uk-navbar-brand" href="/" style="margin-left: 35px;margin-top: 10px;"><img src="../img/nokia.png" style="width:120px;"></a>
	<a class="uk-navbar-brand" href="/" style="margin-top:11px;font-size: 20px;color:rgb(18,65,145);line-height: 40px;padding-left: 0;"><strong>工具管理平台</strong></a>
	<span>
        <form id="dropSearchForm" method="get" action="/SearchPageServlet">
            <input id="dropSearchInput" name="searchKey" type="text" placeholder="Search Tools You Like...">
            <input class="dropSubmit" type="submit" value=""></input>
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
	
	<div id="updateBox" class="uk-container uk-container-center uk-width-3-4 uk-grid">
		<div class="uk-width-1-5">
            <div class="labelHeight"><label class="uk-form-label" for="form-h-a"><strong>工具名称：</strong></label></div>
            <div class="labelHeight"><label class="uk-form-label" for="form-h-b"><strong>工具版本：</strong></label></div>
            <div class="labelHeight" style="position: relative;top: -3px;"><label class="uk-form-label" for="form-h-c"><strong>工具附件：</strong></label></div>
        </div>

		<div class="uk-width-4-5">
            <form id="updateForm" class="uk-form uk-form-horizontal">
                <div class="uk-form-row">
                    <div class="uk-form-controls uk-width-1-1">
						<span style="position: relative;top:5px;"><strong><span class="updateToolName" >${requestScope.OriginalToolName}</span>&nbsp;&nbsp;<span class="updateVersion" >${requestScope.OriginalToolVersion}</span></strong></span>
                        <input type="hidden" id="form-h-a" name="toolName" class="uk-width-3-5" value=${requestScope.OriginalToolName} >
						<input type="hidden"  name="toolId" class="uk-width-3-5" value=${requestScope.toolId} >
                    </div>
                </div>

                <div class="uk-form-row">
                    <div class="uk-form-controls uk-width-1-1">
                        <input type="text" id="form-h-b" name="version" class="uk-width-3-5" placeholder="更新工具的版本" autofocus="autofocus"  onblur="isFilled(this)" onkeyup="hiddenErrorHint(this)">
                        <span class="uk-alert uk-alert-danger errorHint">请输入工具版本</span>
                    </div>
                </div>

                <div class="uk-form-row">
                    <div class="uk-form-controls uk-width-1-1">
						<div id="filePicker" style="display: none;"></div>
                        <span id="updateIcon" onclick="toolChoose()" ><i class="uk-icon-folder-open uk-margin-small-right"></i>选择附件</span>
                        <label id="filePath"></label>
                        <%--<input type="file" name="file" id="form-h-c" placeholder="选择所需更新的工具" style="display: none;">--%>
                        <span class="uk-alert uk-alert-danger errorHint">请添加工具附件</span>
                    </div>
                </div>
				
				 <div class="uk-form-row" style="height:80px;">
                    <div class="uk-form-controls uk-width-1-1">
                        <input type="button" id="updateButton" class="uk-width-1-3" value="开始更新" onclick="startUpdate()"/>
                    </div>
                </div>

                <div class="uk-form-row">
                    <div class="uk-form-controls uk-width-1-1">
                        <button id="stopUpdate" class="uk-width-1-4" type="button" onclick="stop()">暂停更新</button>
                        <button id="cancelUpdate" class="uk-width-1-4">取消更新</button>
                    </div>
                </div>
            </form>
            <div id="progressAll" class="uk-progress uk-progress-striped uk-active">
                <div id="progressBar" class="uk-progress-bar"><span id="progressSum"></span></div>
            </div>
        </div>
	</div>
	<span id="progress"></span>
	<div id="updateMargin"></div>

	<script src="../js/common.js" type="text/javascript"></script>
	<script src="../js/update.js" type="text/javascript"></script>
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
                        url:"/UpdateActionServlet?action=mergeChunks",
                        data:{
                            "uniqueFileTag":uniqueFileTag,
                            "fileName":file.name,
                            "toolName":$("input[name='toolName']").val(),
                            "version":$("input[name='version']").val(),
							"toolId":$("input[name='toolId']").val()
                        },
                        success:function (result) {
                            updateSuccess(result);
                        }
                    }
                );
            }

        }
    );
    var uploader = WebUploader.create(
        {
            server:"/UpdateChunkServlet",
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
        updateFail();
    });

    uploader.on( 'uploadComplete', function( file ) {
    });

    function start(){
        uploader.upload();
        $('#stopUpdate').attr("onclick","stop()");
        $('#stopUpdate').text("暂停更新");
    }

    function stop(){
        uploader.stop(true);
        $('#stopUpdate').attr("onclick","start()");
        $('#stopUpdate').text("继续更新");
    }
</script>
</html>
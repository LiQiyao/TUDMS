<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>${toolDetailBean.toolName}-诺基亚工具管理平台</title>
		<link rel="stylesheet" href="../css/details_page.css" />
		<link rel="stylesheet" href="../uikit/css/uikit.min.css" />
		<link rel="stylesheet" href="../uikit/css/components/tooltip.css" />
		<link rel="stylesheet" href="../uikit/css/components/notify.css" />
		<link rel="stylesheet" href="../uikit/css/components/progress.css" />
		<link rel="stylesheet" type="text/css" href="../css/common.css">
		<link rel="stylesheet" type="text/css" href="../uikit/css/components/search.css">
		<link rel="stylesheet" href="../codemirror/lib/codemirror.css">
		<link rel="stylesheet" href="../uikit/css/components/htmleditor.css">
		<script src="../uikit/jquery.min.js"></script>
		<script src="../uikit/js/uikit.min.js"></script>
		<script src="../js/clamp.min.js"></script>
		<script src="../uikit/js/components/pagination.js"></script>
		<script src="../uikit/js/components/tooltip.js"></script>
		<script src="../uikit/js/components/notify.js"></script>
		<script src="../uikit/js/components/grid.js"></script>
		<script src="../js/details_page.js"></script>
		<script src="../codemirror/lib/codemirror.js"></script>
		<script src="../codemirror/mode/markdown/markdown.js"></script>
		<script src="../codemirror/addon/mode/overlay.js"></script>
		<script src="../codemirror/mode/xml/xml.js"></script>
		<script src="../codemirror/mode/gfm/gfm.js"></script>
		<script src="../marked-master/marked.min.js"></script>
		<script src="../uikit/js/components/htmleditor.js"></script>
	</head>

	<body style="width:99%">
		<div id="logMargin"></div>
		<div class="loginBox">
			<center>
				<div class="loginTitle">用户登录</div>
			</center>
			<form class="uk-form" action="/login" method="post">
				<a class="uk-close loginBoxClose"></a>
				<div class="uk-form-row">
					<center><input id="username" name="username" class="uk-width-2-3 uk-form-large" type="text" placeholder="用户名"></center>
				</div>
				<div class="uk-form-row" style="margin:0 !important;">
					<center><input id="password" name="password" class="uk-width-2-3 uk-form-large" type="password" placeholder="密码"></center>
				</div>
				<div class="uk-form-row">
					<center><input id="loginSubmit" class="uk-width-2-3 uk-button uk-margin-top uk-form-large" type="submit"></center>
				</div>
			</form>
		</div>
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
									<li>
										<a href="/UserCenterServlet?uid=${loggedUserBean.userId}">个人中心</a>
									</li>
									<li>
										<a href="/UploadPageServlet">工具上传</a>
									</li>
									<c:if test="${loggedUserBean.userLevel==2}">
										<li>
											<a href="/manage">管理中心</a>
										</li>
									</c:if>
									<li>
										<a href="/logout">退出</a>
									</li>
								</ul>
							</div>
						</li>
					</ul>
				</div>
			</c:if>
		</div>

		<div class="details-head" style="background: rgb(250,251,252);">
			<div class="details-head-t">
				<div class="details-head-t-l">
					<span id="tool-name">${toolDetailBean.toolName}</span>&nbsp;
					<a href="/?category=${toolDetailBean.categoryId}" id="tool-style">${toolDetailBean.categoryName}</a>
					<a href="/UserCenterServlet?uid=${toolDetailBean.uploaderUid}" id="tool-developer">${toolDetailBean.uploaderName}</a>
					<c:if test="${loggedUserBean!=null}">
						<span id="follow-tool" class="icon-star" data-uk-tooltip title="关注该工具"><span class="<c:if test="${helper.follow==false}">uk-icon-star-o</c:if><c:if test="${helper.follow==true}">uk-icon-star</c:if>"></span> 关注</span>
					</c:if>
					<span class="icon-share" data-uk-tooltip title="分享链接"><span class="uk-icon-share-alt"></span> 分享</span>
					<c:if test="${helperBean.uploader}">
						<button id="upload-tool" class="uk-button uk-button-success" onclick="window.location='/UpdatePageServlet?toolId=${toolDetailBean.toolId}'">更新工具</button>
					</c:if>
					<c:if test="${helperBean.adminAuth || helperBean.uploader}">
						<button id="delet" class="uk-button uk-button-danger" onclick="window.location='/api/manage/delete?toolId=${toolDetailBean.toolId}'">删除工具</button>
					</c:if>
				</div>
				<div class="details-head-t-r">
					<div class="uk-button-group">
						<button id="like-tool" class="uk-button" data-uk-tooltip title="赞一下" <c:if test="${!helperBean.logged}">disabled</c:if>> <span class="uk-icon-thumbs-o-up" id="like-icon"></span>&nbsp;<span id="like-num">${toolDetailBean.zanCount}</span></button>
						<button class="uk-button" disabled><span class="uk-icon-eye"></span>&nbsp;<span id="look-num">${toolDetailBean.commentCount}</span></button>
						<button class="uk-button" disabled><span class="uk-icon-download"></span>&nbsp;<span id="download-num">${toolDetailBean.downloadCount}</span></button>
					</div>
				</div>
			</div>
			<div class="details-head-b">
				<ul class="nav uk-tab" data-uk-tab data-uk-switcher="{connect:'#details-body-ul'}">
					<li class="uk-active">
						<a href="javascript:void(0)"><span class="uk-icon-tasks" style="margin-right: 5px;"></span>详情</a>
					</li>
					<li>
						<a href="javascript:void(0)"><span class="uk-icon-comment-o" style="margin-right: 5px;"></span>评论<span id="comment-num">${toolDetailBean.commentCount}</span></a>
					</li>
					<li>
						<a href="javascript:void(0)"><span class="uk-icon-book" style="margin-right: 5px;"></span>提问</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="details-body">
			<ul id="details-body-ul" class="uk-switcher">
				<li class="tool-datails-li">
					<div class="tool-introduction">
						<p id="tool-introduction-content">${toolDetailBean.briefIntro}</p>
					</div>
					<div class="tool-labels">
						<ul>
							<c:forEach items="${toolDetailBean.tagList}" var="tag">
								<li>${tag}</li>
							</c:forEach>
						</ul>
						<c:if test="${helperBean.uploader}">
							<span class="uk-icon-cog" data-uk-tooltip title="修改工具简介" data-uk-modal="{target:'#modify-intro'}" id="modify-intro-button">  修改</span>
						</c:if>
					</div>
					<c:if test="${helperBean.uploader}">
						<div id="modify-intro" class="uk-modal">
							<form action="/api/modify_brief_intro" method="post">
								<div class="uk-modal-dialog" id="modify-intro-body">
									<a class="uk-modal-close uk-close"></a>
									<div class="uk-modal-header">修改工具简介</div>
									<input type="hidden" name="toolId" value="${toolDetailBean.toolId}" />
									<textarea id="intro-con" name="content"></textarea>
									<div class="uk-modal-footer"><input type="submit" class="uk-button uk-button-primary" style="float: right;" /></div>
								</div>
							</form>
						</div>
					</c:if>
					<div class="tool-versions">
						<c:forEach items="${toolDetailBean.versionList}" var="version">
							<div class="tool-new-version">
								<span class="version-name">${version.versionName}</span>
								<span class="version-size"><span>size：</span><span class="vsize">${version.size}</span></span>
								<span class="version-date">${version.uploadTime}</span>
								<c:if test="${helperBean.logged}">
									<a onclick="downloadURL('${version.downloadLink}',this)" class="download-tool">下载</a>
								</c:if>
								<c:if test="${!helperBean.logged}">
									<a href="#" class="download-tool">请先登录再下载</a>
								</c:if>
								<c:if test="${helperBean.adminAuth || helperBean.uploader}">
									<a href="#" class="del-version">删除</a>
								</c:if>
							</div>
						</c:forEach>
					</div>
					<div class="tool-readme">
						<div class="readme-head">
							<span class="uk-icon-file-text"></span>
							<span>详细介绍</span>
							<c:if test="${helperBean.uploader}">
								<span id="modify-readme-button" onclick="ycimg()" class="uk-icon-cog" data-uk-tooltip title="修改自述文档" data-uk-modal="{target:'#modify-readme'}">  修改</span>
							</c:if>
						</div>
						<c:if test="${helperBean.uploader}">
							<div id="modify-readme" class="uk-modal">
								<form action="/api/modify_description" method="post" id="markdown-form">
									<div class="uk-modal-dialog" id="modify-readme-body">
										<a class="uk-modal-close uk-close"></a>
										<div class="uk-modal-header">修改工具详细介绍</div>
										<input type="hidden" name="toolId" value="${toolDetailBean.toolId}" />
										<textarea data-uk-htmleditor="{markdown:true}" name="content" id="readmecon">${toolDetailBean.description}</textarea>
										<div class="uk-modal-footer"><input type="submit" class="uk-button uk-button-primary" style="float: right;"/></div>
									</div>
								</form>
							</div>
						</c:if>
						<div class="readme-body">
${toolDetailBean.description}
						</div>
					</div>
				</li>
				<li class="tool-comments-li">
					<div class="comment-fillIn">
						<form id="comment-form">
							<div class="comment-fillIn-l">
								<img src="../img/headIcon.jpg" alt="head" />
							</div>
							<div class="comment-fillIn-r">
								<input type="hidden" name="toolId" value="${toolDetailBean.toolId}" />
								<textarea name="comment-fill" placeholder="评论写在这..."></textarea>
								<span class="num-limit"><span id="comment-wordnum">0</span>/300</span>
							</div>
							<input type="button" value="发表评论" class="sublim-comment uk-button uk-button-primary" onclick="sendComment()" />
						</form>
					</div>
					<ul class="comment-list">

					</ul>

				</li>
				<li class="tool-problems-li">
					<div class="problem-fillIn">
						<form id="ask-form">
							<div class="problem-fillIn-l">
								<img src="../img/headIcon.jpg" alt="head" />
							</div>
							<div class="problem-fillIn-r">
								<input type="hidden" name="toolId" value="${toolDetailBean.toolId}" />
								<textarea name="problem-fill" placeholder="问题写在这..."></textarea>
								<span class="num-limit"><span id="problem-wordnum">0</span>/300</span>
							</div>
							<input type="button" value="发表问题" class="sublim-problem uk-button uk-button-primary" onclick="sendAsk()" />
						</form>
					</div>
					<ul id="pro-filter" class="problem-type uk-subnav">
						<li class="uk-active" data-uk-filter>
							<a href="javascript:void(0)">全部</a>
						</li>
						<li data-uk-filter="resolvepro">
							<a href="javascript:void(0)">已回答</a>
						</li>
						<li data-uk-filter="unresolvepro">
							<a href="javascript:void(0)">未回答</a>
						</li>
					</ul>
					<ul id="problem-typeul">
						<li>
							<ul class="problem-list" data-uk-grid="{controls:'#pro-filter'}">

							</ul>
						</li>
					</ul>

				</li>
			</ul>
		</div>

		<foot></foot>
	</body>
	<script type="text/javascript" src="../js/common.js"></script>

</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>搜索-诺基亚工具管理平台</title>
	<link rel="stylesheet" type="text/css" href="../uikit/css/uikit.min.css">
	<link rel="stylesheet" type="text/css" href="../uikit/css/components/sticky.css">
	<link rel="stylesheet" type="text/css" href="../uikit/css/components/search.css">
	<link rel="stylesheet" type="text/css" href="../uikit/css/components/datepicker.css">
	<link rel="stylesheet" type="text/css" href="../css/search.css">
	<link rel="stylesheet" type="text/css" href="../css/common.css">
	<link rel="stylesheet" type="text/css" href="../css/animate.css">

	<script src="../uikit/jquery.min.js"></script>
	<script src="../uikit/js/uikit.min.js"></script>
	<script src="../uikit/js/components/sticky.js"></script>
	<script src="../uikit/js/components/search.js"></script>
	<script src="../uikit/js/components/grid.js"></script>
	<script src="../uikit/js/components/pagination.js"></script>
	<script src="../uikit/js/components/datepicker.js"></script>
	<script src="../js/clamp.min.js"></script>
	<script src="../js/searchKeyword.js"></script>

</head>
<body>
<div id="logMargin"></div>

<div class="loginBox">
	<center><div class="loginTitle">用户登录</div></center>
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


<div id="searchNav" class="uk-container uk-container-center uk-width-3-4">
			<span>
				<form id="searchForm" class="uk-search" data-uk-search>
					<input id="searchInput" value="${requestScope.searchKey}" class="uk-search-field uk-width-1-2" type="text" placeholder="Search Tools You Like..." autofocus="autofocus" onkeypress="if(event.keyCode == 13){searchButtonClick();return false;}">
					<button class="searchButton" type="button" style="left:369px;"></button>
				</form>
			</span>
	<ul data-uk-switcher="{connect:'#searchItem'}">
		<li><a id="searchAll" href="">全部</a></li><li><a id="searchUser" href="">发布者</a></li><li><a id="advancedSearch" href="">高级搜索</a></li>
	</ul>
	<div></div>
</div>

<div id="searchBox" class="uk-container uk-container-center uk-width-3-4">
	<ul id="searchItem" class="uk-switcher">
		<li>
			<div class="searchAllSum"></div>
			<div class="searchMethod">
				<form class="uk-form">
					<select id="order">
						<option value="download-count">下载量</option>
						<option value="comment-count">评论数</option>
						<option value="zan-count">点赞数</option>
					</select>
				</form>
			</div>
			<div id="searchResultBox">
				<ul id="searchResult" class="uk-pagination" data-uk-pagination="{items:100, itemsOnPage:10}"></ul>
			</div>


		</li>
		<li>
			<div id="searchUserSum"></div>
			<div>
				<ul style="display: none;" id="searchUserResult" class="uk-pagination" data-uk-pagination="{items:100, itemsOnPage:10}"></ul>
			</div>
		</li>
		<li>
			<form class="uk-form uk-form-horizontal advancedSearchForm">
				<div class="uk-form-row uk-margin-large-top">
					<label class="uk-form-label" for="form-h-a"><strong>工具名称：</strong></label>
					<div class="uk-form-controls">
						<input type="text" id="form-h-a" class="uk-width-1-2" placeholder="包含以下的全部关键词">
					</div>
				</div>
				<div class="uk-form-row uk-margin-large-top">
					<label class="uk-form-label" for="form-h-b"><strong>发布者：</strong></label>
					<div class="uk-form-controls">
						<input type="text" id="form-h-b" class="uk-width-1-2" placeholder="限定发布者的名字">
					</div>
				</div>
				<div class="uk-form-row uk-margin-large-top">
					<label class="uk-form-label" for="form-h-c"><strong>发布日期：</strong></label>
					<div class="uk-form-controls">
						<input type="text" id="form-h-c" class="uk-width-1-5" data-uk-datepicker="{format:'YYYY.MM.DD'}" placeholder="起始时间">
						<div id="divice">-------------</div>
						<input type="text" id="form-h-c2" class="uk-width-1-5" data-uk-datepicker="{format:'YYYY.MM.DD'}" placeholder="截止时间">
					</div>
				</div>
				<div class="uk-form-row uk-margin-large-top">
					<label class="uk-form-label" for="form-h-d"><strong>标签：</strong></label>
					<div class="uk-form-controls">
						<input type="text" id="form-h-d" class="uk-width-1-2" placeholder="限定工具所贴标签属性">
					</div>
				</div>
				<div class="uk-form-row uk-margin-large-top">
					<label class="uk-form-label" for="form-h-e"><strong>分类：</strong></label>
					<div class="uk-form-controls">
						<select id="form-h-e">
							<option value="0">全部</option>
							<option value="1">办公</option>
							<option value="2">网络</option>
							<option value="3">安全</option>
							<option value="4">系统维护</option>
							<option value="5">其他</option>
						</select>
					</div>
				</div>
				<div class="uk-form-row uk-margin-large-top">
					<label class="uk-form-label"></label>
					<button type="button" class="uk-button uk-width-1-4 submitButton">搜索</button>
				</div>
			</form>
		</li>
	</ul>
</div>

<a id="toTop" href="#top" data-uk-smooth-scroll>
	<center>
		<i class="uk-icon-chevron-up uk-icon-small"></i>
	</center>
</a>


<script src="../js/common.js" type="text/javascript"></script>
<script src="../js/search.js" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready( function () {
            if ($('#searchInput').val() != ""){
                search($('#searchInput').val());
            }
        }
    )

</script>
</body>
</html>
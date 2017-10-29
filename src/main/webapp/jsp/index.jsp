<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>诺基亚工具管理平台</title>
    <link rel="stylesheet" type="text/css" href="../uikit/css/uikit.min.css">
    <link rel="stylesheet" type="text/css" href="../uikit/css/components/sticky.css">
    <link rel="stylesheet" type="text/css" href="../uikit/css/components/search.css">
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <link rel="stylesheet" type="text/css" href="../css/home.css">
    <link rel="stylesheet" type="text/css" href="../css/animate.css">


    <script src="../uikit/jquery.min.js"></script>
    <script src="../uikit/trianglify.min.js"></script>
    <script src="../uikit/js/uikit.min.js"></script>
    <script src="../uikit/js/components/sticky.js"></script>
    <script src="../uikit/js/components/search.js"></script>
    <script src="../uikit/js/components/grid.js"></script>
    <script src="../js/navBg.js"></script>
    <script src="../js/clamp.min.js"></script>
</head>
<body>
<div id="logMargin"></div>
<div class="site-header">
    <div id="navContainer" class="uk-container uk-container-center uk-width-small-1-1 uk-animation-slide-top">
        <nav id="navBar" class="uk-navbar uk-navbar-attached uk-margin-top">
            <a class="uk-navbar-brand" href="/"><img src="../img/nokia.png" style="width:120px;"></a>
            <a class="uk-navbar-brand" href="/" style="font-size: 20px;color:rgb(18,65,145);line-height: 40px;font-weight: 600;padding-left: 0;">工具管理平台</a>
            <c:if test="${loggedUserBean==null}">
            <a class="uk-navbar-flip login" href="#loginBox" data-uk-modal>登录</a>
            </c:if>
            <c:if test="${loggedUserBean!=null}">
            <div class="uk-navbar-flip logined">
                <ul class="uk-navbar-nav">
                    <li class="uk-parent" data-uk-dropdown>
                        <a class="uk-margin-large-right navUser">
                            <img src="../img/headIcon.jpg">
                            <div class="navUserName uk-hidden-small">${loggedUserBean.userName}</div>
                            <i class="uk-icon-chevron-down uk-icon-xsmall uk-hidden-small navUserDown"></i>
                        </a>
                        <div class="uk-dropdown uk-dropdown-navbar dropdownList">
                            <ul class="uk-nav uk-nav-navbar uk-list-striped">
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
        </nav>
    </div>

</div>


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


<div id="dropNavContainer" class="uk-width-1-1" style="visibility: hidden;">
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
                <div class="uk-dropdown 哦，uk-dropdown-navbar dropdownList">
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

<div class="uk-width-2-5 searchDiv">
    <form id="searchForm" class="uk-width-4-5" method="get" action="/SearchPageServlet">
        <input id="searchInput" class="uk-width-1-1" type="text" name="searchKey" placeholder="Search Tools You Like..." autofocus="autofocus">
        <input class="submit" type="submit" value=""></input>
        <i class="uk-icon-search searchButton"></i>
    </form>
</div>


<div id="container" class="uk-container uk-container-center">
    <div class="uk-grid" data-uk-gird-margin>
        <div id="leftBar" class="uk-width-medium-1-8 uk-width-small-1-6 uk-grid-margin animated fadeInLeft">
            <ul id="gridFilter" class="uk-nav uk-nav-side uk-list uk-list-striped uk-list-line" data-uk-sticky="{top:100}">
                <li data-uk-filter="" class="uk-active"><a href="">全部</a></li>
                <li data-uk-filter="filter-1"><a href="">办公</a></li>
                <li data-uk-filter="filter-2"><a href="">网络</a></li>
                <li data-uk-filter="filter-3"><a href="">安全</a></li>
                <li data-uk-filter="filter-4"><a href="">系统维护</a></li>
                <li data-uk-filter="filter-5"><a href="">其他</a></li>
            </ul>
        </div>

        <div id="rightBar" class="uk-width-medium-4-5 uk-width-small-5-6 uk-grid-margin" data-uk-grid="{controls:'#gridFilter'}">
            <div class="uk-width-large-3-10 uk-width-medium-2-5 uk-margin-left uk-margin-bottom toolBox" data-uk-filter="filter-${firstItem.toolType}" onclick="window.open('/tool?tid=${firstItem.toolId}');">
                <div class="tri"></div>
                <h3 class="uk-panel-title uk-margin-top uk-margin-left">
                    <span class="uk-icon-home"></span>
                    <span class="toolName">${firstItem.toolName}</span>
                    <span class="toolSize">${firstItem.toolSize}</span>
                </h3>
                <div class="uk-margin-left uk-margin-right toolBriefIntro">
                    ${firstItem.toolBriefIntro}
                </div>
                <div class="uk-width-1-4 uk-margin-left bottomBlock" style="left:5%;bottom:10px;">
                    <span class="uk-margin-small-left uk-icon-thumbs-o-up"></span>
                    <span class="uk-margin-small-left sum">${firstItem.toolZanCount}</span>
                </div>
                <div class="uk-width-1-4 uk-margin-left bottomBlock" style="left:38%;bottom:10px;">
                    <span class="uk-margin-small-left uk-icon-comment-o"></span>
                    <span class="uk-margin-small-left sum">${firstItem.toolCommentCount}</span>
                </div>
                <div class="uk-width-1-4 uk-margin-left bottomBlock" style="right:5%;bottom:10px;">
                    <span class="uk-margin-small-left uk-icon-download"></span>
                    <span class="uk-margin-small-left sum">${firstItem.toolDownloadCount}</span>
                </div>
            </div>

        </div>
    </div>
</div>

<a id="toTop" href="#top" data-uk-smooth-scroll>
    <center>
        <i class="uk-icon-chevron-up uk-icon-small"></i>
    </center>
</a>

<div class="uk-container uk-container-center" style=" margin-top: 20px;padding-top: 20px;border-top: 1px solid #ececec;">
    <center><p>©2017 Lee Rin HH cw</p></center>
</div>



<script src="../js/trianglifyBar.js"></script>
<script src="../js/common.js" type="text/javascript"></script>
<script src="../js/home.js" type="text/javascript"></script>
</body>
</html>
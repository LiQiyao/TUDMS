<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/4
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理中心-诺基亚工具管理平台</title>
    <link rel="stylesheet" href="../css/manage.css" />
    <link rel="stylesheet" href="../uikit/css/uikit.min.css" />
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../uikit/css/components/search.css" />
    <script src="../uikit/jquery.min.js"></script>
    <script src="../uikit/js/uikit.min.js"></script>
    <script src="../uikit/js/components/grid.js"></script>
    <script src="../js/manage.js"></script>
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
<header></header>
<div class="manage-body">
    <ul class="manage-menu uk-nav uk-nav-side" data-uk-switcher="{connect:'#manage-function'}">
        <li class="uk-active li1">
            <a href="#" class="menu-a">管理权限</a>
        </li>
        <li>
            <a href="#" class="menu-a">添加权限</a>
        </li>
        <li class="li1">
            <a href="#" class="menu-a">查询用户ID</a>
        </li>
        <li>
            <a href="#" class="menu-a">管理分类</a>
        </li>
        <li class="li1">
            <a href="#" class="menu-a">管理工具</a>
        </li>
    </ul>
    <ul id="manage-function" class="uk-switcher">
        <li class="see-permission">
            <table class="uk-table uk-table-hover uk-table-striped">
                <caption>目前权限分配</caption>
                <thead>
                <tr>
                    <th>用户昵称（ID）</th>
                    <th>管理板块</th>
                    <th>撤销授权</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${adminList}" var="currentSubAdmin">
                    <c:forEach items="${currentSubAdmin.authCategory}" var="categoryId">
                        <tr>
                            <td><span class="username">${currentSubAdmin.userName}</span>（<span class="userid">${currentSubAdmin.uid}</span>）</td>
                            <td class="changeToCateName">${categoryId}</td>
                            <td>
                                <a href="/api/manage/cancel_auth?uid=${currentSubAdmin.uid}&category=${categoryId}">撤销该板块授权</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:forEach>


                </tbody>
            </table>
        </li>
        <li class="add-permission">
            <h3>添加权限</h3>
            <form method="post" action="/api/add_sub_admin_auth">
            <input type="text" name="uid" placeholder="输入预授权用户ID" id="fillin-userid" />
            <p>选择板块：</p>
            <select name="category" id="plate-select">
                <option value="1">办公</option>
                <option value="2">网络</option>
                <option value="3">安全</option>
                <option value="4">系统维护</option>
            </select><br />
            <input type="submit" value="确认授权" class="uk-button uk-button-primary sub-auth" />
            </form>
        </li>
        <li class="query-id">
            <input type="text" placeholder="输入要查询用户姓名" id="query-text" />
            <input type="submit" value="查询" id="query-button" class="uk-button uk-button-primary" />
            <div>
                <p>你查询的用户ID为：</p>
                <span id="query-result"></span>
            </div>
        </li>
        <li class="classified-manage">
            <table class="uk-table uk-table-hover uk-table-striped">
                <caption>当前分类条目</caption>
                <thead>
                <tr>
                    <th>分类</th>
                    <th>工具数量</th>
                    <th>修改分类</th>
                    <th>删除分类</th>
                </tr>
                </thead>
                <tbody>

                    <c:forEach items="${categoryList}" var="category">
                    <tr>
                        <td><span class="username">${category.name}</span></td>
                        <td class="changeToCateName"><a href="">${category.count}</a></td>
                        <td>
                            <a href="">修改名称</a>
                        </td>
                        <td>
                            <a href="">删除</a>
                        </td>
                    </tr>
                    </c:forEach>
                    <tr>
                        <td><a href="">新增分类</a></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>


                </tbody>
            </table>
        </li>
        <li class="tool-manage">
            <table class="uk-table uk-table-hover uk-table-striped">
                <caption>
                    <button id="deleteButton" class="uk-button uk-button-danger" type="button" data-uk-modal="{target:'#confirmDelete'}">删除工具</button>
                    <ul id="toolFilter" class="uk-subnav">
                        <li data-uk-filter="" class="uk-active"><a href="">全部</a></li>
                        <li data-uk-filter="filter-1"><a href="">办公</a></li>
                        <li data-uk-filter="filter-2"><a href="">网络</a></li>
                        <li data-uk-filter="filter-3"><a href="">安全</a></li>
                        <li data-uk-filter="filter-4"><a href="">系统维护</a></li>
                        <li data-uk-filter="filter-5"><a href="">其他</a></li>
                    </ul>
                </caption>
                <thead>
                <tr>
                    <th><input id="selectAll" type="checkbox"></input></th>
                    <th>工具名称</th>
                    <th>发布者</th>
                    <th>工具分类</th>
                    <th>最新版本</th>
                </tr>
                </thead>
                <tbody id="toolBox" data-uk-grid="{controls:'#toolFilter'}">
                    <c:forEach items="${toolList}" var="tool">
                        <tr data-uk-filter="filter-${tool.categoryId}" aria-hidden="false">
                            <td>
                                <input class="deleteTool" type="checkbox"></input>
                            </td>
                            <td><a href="" class="toolName">${tool.toolName}</a></td>
                            <td><a href="" class="uploaderName">${tool.uploaderName}</a></td>
                            <td>
                                <span class="classified">${tool.categoryName}</span>
                            </td>
                            <td>
                                <span class="nowVersion">${tool.version}</span>
                            </td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
            <div id="confirmDelete" class="uk-modal">
                <div class="uk-modal-dialog">
                    <button type="button" class="uk-modal-close uk-close"></button>
                    <div class="uk-modal-header">
                        <h2>请注意！删除后工具无法恢复！请谨慎操作！</h2>
                    </div>
                    <table class="uk-table uk-table-striped uk-table-hover uk-table-condensed">
                        <thead>
                        <tr>
                            <th>工具名称</th>
                            <th>发布者</th>
                            <th>工具分类</th>
                            <th>最新版本</th>
                        </tr>
                        </thead>
                        <tbody id="deleteBody">

                        </tbody>
                    </table>
                    <div class="uk-modal-footer uk-text-right">
                        <button id="cancel" type="button" class="uk-button uk-modal-close uk-margin-right">取消</button>
                        <button id="confirm" type="button" class="uk-button uk-button-danger">删除</button>
                    </div>
                </div>
            </div>
        </li>
    </ul>
</div>
</body>
<script src="../js/common.js"></script>
<script type="application/javascript">
    var mp = {
        "1":"办公",
        "2":"网络",
        "3":"安全",
        "4":"系统维护",
        "5":"其他"
    };
    $('.changeToCateName').each(function(){
        $(this).html(mp[$(this).html()]);
    });
</script>
</html>
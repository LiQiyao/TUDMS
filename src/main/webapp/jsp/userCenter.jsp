<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>用户中心-诺基亚工具管理平台</title>
		<link rel="stylesheet" href="../uikit/css/uikit.min.css" />
		<link rel="stylesheet" href="../css/userCenter.css" />
		<link rel="stylesheet" type="text/css" href="../uikit/css/components/search.css" />
		<link rel="stylesheet" href="../css/common.css"/>
	</head>

	<body class="uk-grid">
	<div id="logMargin"></div>
	<div class="loginBox" style="padding: 0px">
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

	<div style="width: 97%;height: 60px;background: #fff;" class="head"></div>
		<div class="uc-body uk-grid uk-width-2-3 uk-container-center">
			<div class="uc-leftbar uk-width-1-4" data-uk-grid-margin>
				<div class="uc-left-t uk-width-1-1">
					<img src="../img/headIcon.jpg" alt="touxiang" style="margin-bottom: 20px;" />
					<span>${sessionScope.userCenterInfo.username}</span>
					<c:if test="${sessionScope.userCenterInfo.sex==1}">
						<i class="uk-icon-mars"></i>
					</c:if>
					<c:if test="${sessionScope.userCenterInfo.sex==2}">
						<i class="uk-icon-venus"></i>
					</c:if>
					<c:if test="${sessionScope.userCenterInfo.sex==0}">

					</c:if>
				</div>
				<div class="uc-left-b uk-width-1-1">
					<span class="boldz">部门：</span><span>${sessionScope.userCenterInfo.department}</span><br />
					<span class="boldz">职务：</span><span>${sessionScope.userCenterInfo.job}</span><br />
					<span style="font-weight: bold;">简介：</span>
					<p>${sessionScope.userCenterInfo.selfIntro}</p>
				</div>

				<c:if test="${sessionScope.userCenterInfo.uId == sessionScope.selfId}">
				<button class="uk-button uk-button-success" style="margin-top: 20px;" data-uk-modal="{target:'#edit-profile'}">编辑个人资料</button>
				</c:if>
			</div>
			<c:if test="${sessionScope.userCenterInfo.uId == sessionScope.selfId}">
			<div id="edit-profile" class="uk-modal">
				<div class="uk-modal-dialog">
					<a class="uk-modal-close uk-close"></a>
					<form action="/UpdateUserInfoServlet" method="post">
						<div class="uk-modal-header">
							<h3>个人信息</h3>
						</div>
						<div class="uk-modal-body">
							<ul class="uk-width-1-1 uk-list" class="ucep-body">
								<li class="ucep-headpor">
									<span>头像</span>
									<img src="../img/headIcon.jpg" alt="touxiang" id="avatar" />
									<div style="float: left;">
										<a href="###" id="upload-new" class="" style="margin-top: 36px;margin-left: 40px;">上传新头像</a>
										<input type="file" name="upload-head" class="" id="upload-head" value="上传新头像" style="display: none;" accept="image/gif,image/jpeg,image/jpg,image/png" />
									</div>
								</li>

									<li class="ucep-name">
										<span>姓名</span>
										<input type="text" name="username" id="" value="${sessionScope.userCenterInfo.username}" />
									</li>
									<li class="ucep-department">
										<span>部门</span>
										<input type="text" name="department" id="" value="${sessionScope.userCenterInfo.department}" />
									</li>
									<li class="ucep-duty">
										<span>职务</span>
										<input type="text" name="job" id="" value="${sessionScope.userCenterInfo.job}" />
									</li>
									<li class="ucep-intro">
										<span>简介</span>
										<textarea name="selfIntro" rows="6">${sessionScope.userCenterInfo.selfIntro}</textarea>
									</li>
							</ul>
						</div>
						<div class="uk-modal-footer uk-text-right">
							<button class="uk-button uk-modal-close">取消</button>
							<input class="uk-button uk-button-primary" type="submit" value="保存" />
						</div>
					</form>
				</div>
			</div>
			</c:if>

			<div class="uk-width-3-4 uc-right">
				<ul class="uk-subnav uk-subnav-pill uc-right-bar" data-uk-switcher="{connect:'#subnav-pill-content-1'}" id="ucrightbar">
					<li class="right-bar-active" id="rightbar1">
						<a href="#"><i class="uk-icon-archive"></i>工具<i style="margin-left: 5px;" id="owntool-num">${sessionScope.userCenterToolList.num}</i></a>
					</li>
					<c:if test="${sessionScope.userCenterInfo.uId == sessionScope.selfId}">
					<li class="uc-dynamic" id="rightbar2">
						<a href="#" onclick="read(${sessionScope.userCenterInfo.uId})"><i class="uk-icon-bell"></i>动态<i style="margin-left: 5px;" id="dynamic-num">${sessionScope.unRead}</i></a>
					</li>
					<!--关注的li -->
					<li class="uc-follow" id="rightbar3">
						<a href="#"><i class="uk-icon-star-o"></i>关注<i style="margin-left: 5px;" id="follow-num">${sessionScope.attentionToolList.size()}</i></a>
					</li>
					</c:if>
					
				</ul>
				<div></div>
				<ul id="subnav-pill-content-1" class="uk-switcher">
					<li class="uk-active uc-tool">
						<div class="uk-width-1-1 uk-grid">
							<div class="uk-width-4-5">
								<form class="uk-search uk-width-1-1" data-uk-search="{source:'my-results.json'}">
									<input class="uk-search-field" style="width: 100%;border: 1px solid #d1d5da;border-radius: 3px;" type="search" placeholder="Search your tool">
									<!-- 这是通过JavaScript注入了搜索结果的下拉菜单 -->
									<div class="uk-dropdown uk-dropdown-search">
										<ul class="uk-nav uk-nav-search">...</ul>
									</div>
								</form>
							</div>
							<c:if test="${sessionScope.userCenterInfo.uId == sessionScope.selfId}">
							<div class="uk-width-1-5 uk-float-right">
								<button onclick="window.location='/UploadPageServlet'" class="uk-button uk-button-primary uk-text-truncate" style="min-width: 100px;">上传新工具</button>
							</div>
							</c:if>
						</div>
						<div class="mtool-body uk-width-1-1">
							<div class="tool-list uk-width-1-1">

								<!---->
								<c:forEach items="${sessionScope.userCenterToolList.list}" var="tool">
									<div class="one-tool uk-width-1-1" id="${tool.toolId}">
										<div class="tool-head uk-grid">
											<div class="uk-width-1-2">
												<a href="/tool?tid=${tool.toolId}" style="font-size: 22px;margin-right: 10px;">${tool.name}</a>
												<i class="uk-icon-cube" style="font-size: 20px;color: gray;"></i>
											</div>
											<div class="uk-width-1-2 tool-broinfo">
												<i class="uk-icon-thumbs-o-up"></i><span>${tool.zanCount}</span>
												<i class="uk-icon-comment-o"></i><span>${tool.commentCount}</span>
												<i class="uk-icon-download"></i><span>${tool.downloadCount}</span>
											</div>
										</div>
										<div class="tool-body">
											<p class="tool-introduction">
												${tool.briefIntro}
											</p>
										</div>
										<div class="tool-foot">
											<span>最近更新：</span><span>${tool.newestVersionTime}</span>
											<c:if test="${sessionScope.userCenterInfo.uId == sessionScope.selfId}">
											<button class="uk-button uk-button-success" onclick="window.location='/UpdatePageServlet?toolId=${tool.toolId}'">更新版本</button>
											<button class="uk-button uk-button-danger" style="margin-left: 20px;" onclick="deleteTool(${tool.toolId})">删除工具</button>
											</c:if>
										</div>
									</div>
								</c:forEach>
								<!---->
							</div>
						</div>
					</li>
					<c:if test="${sessionScope.userCenterInfo.uId == sessionScope.selfId}">
					<li>
						<div class="uk-width-1-1">
							<div class="uk-button-group uk-tab" data-uk-tab data-uk-switcher="{connect:'#dynamic-ul'}">
								<button class="uk-button ucdy-class ucdy-class-active">全部</button>
								<button class="uk-button ucdy-class">评论</button>
								<button class="uk-button ucdy-class">提问</button>
								<button class="uk-button ucdy-class">关注</button>
							</div>
						</div>
						<div class="uk-width-1-1">
							<ul id="dynamic-ul" class="uk-switcher">
								<li id="all-dynamic">
									<div class="mdynamic-body uk-width-1-1">
										<div class="dynamic-list uk-width-1-1">
											<c:forEach items="${sessionScope.userCenterAllList.list}" var="all">
											<div class="one-dynamic one-dy-push uk-width-1-1 uk-flex">
												<div class="one-dy-left">
													<img src="../img/headIcon.jpg" alt="touxiang" />
												</div>
												<div class="one-dy-right">
													<div class="uk-width-1-1">
														<span class="comper-name">${all.title}</span>
													</div>

													<div class="uk-width-1-1  dynamic-content">
														<p class="push-introduction">
																${all.content}
														</p>
													</div>
													<div class="uk-width-1-1  dynamic-time">
														<span>${all.sendTime}</span>
													</div>
													<div class="uk-text-center dynamic-bottom">
														<a href="${all.link}">查看详情</a>
													</div>
												</div>
											</div>
											</c:forEach>
										</div>
									</div>
								</li>
								<li id="comment-dynamic">

									<div class="mdynamic-body uk-width-1-1">
										<div class="dynamic-list uk-width-1-1">
											<c:forEach items="${sessionScope.userCenterCommentList.list}" var="comment">
											<div class="one-dynamic one-dy-comment uk-width-1-1 uk-flex">
												<div class="one-dy-left">
													<img src="../img/headIcon.jpg" alt="touxiang" />
												</div>
												<div class="one-dy-right">
													<div class="uk-width-1-1">
														<span class="comper-name">${comment.title}</span>
													</div>
													<div class="uk-width-1-1 dynamic-content">
														<span>${comment.content}</span>
													</div>
													<div class="uk-width-1-1 dynamic-time">
														<span>${comment.sendTime}</span>
													</div>
													<div class="uk-text-center dynamic-bottom">
														<a href="javascript:void(0)" data-uk-toggle="{target:'.one-dy-comment-reply'}">查看详情</a>
													</div>
												</div>
											</div>
											</c:forEach>
										</div>
									</div>
								</li>
								<li id="problem-dynamic">
									<div class="mdynamic-body uk-width-1-1">
										<div class="dynamic-list uk-width-1-1">
											<c:forEach items="${sessionScope.userCenterAskList.list}" var="ask">
											<div class="one-dynamic one-dy-problem uk-width-1-1 uk-flex">
												<div class="one-dy-left">
													<img src="../img/headIcon.jpg" alt="touxiang" />
												</div>
												<div class="one-dy-right">
													<div class="uk-width-1-1">
														<span class="comper-name">${ask.title}</span>
													</div>
													<div class="uk-width-1-1 dynamic-content">
														<span>${ask.content}</span>
													</div>
													<div class="uk-width-1-1  dynamic-time">
														<span>${ask.sendTime}</span>
													</div>
													<div class="uk-text-center dynamic-bottom">
														<a href="javascript:void(0)" data-uk-toggle="{target:'#dy-problem-1'}">查看详情</a>
													</div>

												</div>
											</div>
											</c:forEach>
										</div>
									</div>
								</li>
								<li id="push-dynamic">
									<div class="mdynamic-body uk-width-1-1">
										<div class="dynamic-list uk-width-1-1">
											<c:forEach items="${sessionScope.userCenterAttentionList.list}" var="attention">
											<div class="one-dynamic one-dy-push uk-width-1-1 uk-flex">
												<div class="one-dy-left">
													<img src="../img/headIcon.jpg" alt="touxiang" />
												</div>
												<div class="one-dy-right">
													<div class="uk-width-1-1">
														<span class="comper-name">${attention.title}</span>
													</div>

													<div class="uk-width-1-1  dynamic-content">
														<p class="push-introduction">
															${attention.content}
														</p>
													</div>
													<div class="uk-width-1-1  dynamic-time">
														<span>${attention.sendTime}</span>
													</div>
													<div class="uk-text-center dynamic-bottom">
														<a href="javascript:void(0)">查看详情</a>
													</div>
												</div>
											</div>
											</c:forEach>
										</div>
									</div>
								</li>
							</ul>

						</div>
					</li>
					<!--
                    	下面的东西
                    -->
                    <li>
						<div class="follow-list uk-width-1-1">
							<c:forEach items="${sessionScope.attentionToolList}" var="attentionTool" varStatus="status">
							<div class="one-follow" id="${status.count}">
								<span class="uk-icon-gear" style="font-size: 24px;color: gray;margin-right: 20px;margin-bottom: 20px;"></span>
								<span class="foltool-name"><a href="/tool?tid=${attentionTool.toolId}">${attentionTool.toolName}</a></span>
								<span style="font-size: 16px;">&nbsp;&nbsp;&nbsp;版本号：</span>
								<span class="foltool-version">${attentionTool.newestVersion}</span><br />
								<span style="color: gray;">最近更新日期：</span>
								<span class="foltool-time" style="color: gray;">${attentionTool.newestVersionTime}</span>
								<form  id="cancelForm">
									<input type="hidden" value="${sessionScope.userCenterInfo.uId}" name="uid" >
									<input type="hidden" value="${attentionTool.toolId}" name="toolId">
								</form>
								<button id="cancel-follow" class="uk-button" onclick="cancel(this, ${sessionScope.userCenterInfo.uId}, ${attentionTool.toolId})">取消关注</button>
							</div>
							</c:forEach>
						</div>
					</li>
					</c:if>
				</ul>
			</div>
		</div>
	</body>
	<script src="../uikit/jquery.min.js"></script>
	<script src="../uikit/js/uikit.min.js"></script>
	<script src="../uikit/js/components/search.js"></script>
	<script src="../uikit/js/components/pagination.js"></script>
	<script src="../js/clamp.min.js"></script>
	<script src="../js/userCenter.js"></script>
	<script src="../js/common.js"></script>
	<script>
        function deleteTool(toolId){
            $.ajax({
                url:'/DeleteSelfToolServlet',
                type:'POST',
                async:true,
                data:{
                    toolId: toolId
                },
                timeout:5000,    //超时时间
                dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                success:function(result){
                    if (result != -1){
                        document.getElementById(toolId).style.display="none";
                    }else{
                        alert("取消关注失败");
                    }
                }
            })
        }

		    function cancel(x, uid, toolId) {
                $.ajax({
                    url:'/CancelAttentionServlet',
                    type:'POST',
                    async:true,
                    data:{
                        uid: uid,
						toolId: toolId
                    },
                    timeout:5000,    //超时时间
                    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                    success:function(result){
						if (result != -1){
							$($(x).parent()).css('display', 'none');
							document.getElementById("follow-num").innerHTML = result;
						}else{
						    alert("取消关注失败");
						}
                    }
                })
            }

            function read(uid) {
				$.ajax({
					   	url:'/ReadMessageServlet',
						type:'POST',
						async:true,
						data:{
					   	    uId:uid
						},
						timeout:5000,
						dataType:'json',
						success:function(result){
					   	    if (result == 1){
					   	        document.getElementById("dynamic-num").innerHTML = 0;
							}
						}
					}
				)
            }

	</script>
</html>
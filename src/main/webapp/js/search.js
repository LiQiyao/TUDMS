$(document).ready(function(){
	//分页切换
	var searchAll=$("#searchAll");
	var searchUser=$("#searchUser");
	var advancedSearch=$("#advancedSearch");
	var searchBottomBar=$("#searchNav").find("div");

	var searchButton = $(".searchButton");


	var searchNav=$("#searchNav");
	var searchForm=$("#searchForm");

	searchBottomBar.css("left",searchAll.offset().left+"px");
	searchAll.click(function(){
		searchBottomBar.width("132px");
		searchBottomBar.css("left",searchAll.offset().left+"px");

		searchNav.css("height",120+"px");
		searchForm.fadeIn(300);

		setTimeout(function(){
			document.getElementById("searchInput").focus();
		},300);
	});
	searchUser.click(function(){
		searchBottomBar.width("148px");
		searchBottomBar.css("left",searchUser.offset().left+"px");

		searchNav.css("height",120+"px");
		searchForm.fadeIn(300);

		setTimeout(function(){
			document.getElementById("searchInput").focus();
		},300);
	});
	advancedSearch.click(function(){
		searchBottomBar.width("164px");
		searchBottomBar.css("left",advancedSearch.offset().left+"px");

		searchNav.css("height",62+"px");
		searchForm.fadeOut(100);

		setTimeout(function(){
			document.getElementById("form-h-a").focus();
		},500);
	});

	//工具介绍文字限制
	var toolIntrodution=$(".toolIntrodution");
	for(i=0;i<toolIntrodution.length;i++){
		$clamp(toolIntrodution[i], {clamp: 4});
	}

	//用户简介文字限制
	var searchUserIntroduction=$(".searchUserIntroduction");
	for(i=0;i<toolIntrodution.length;i++){
		$clamp(searchUserIntroduction[i], {clamp: 4});
	}

	//登录框
	var loginBox=$(".loginBox");
	loginBox.css("left",($(window).width()/2-loginBox.width()/2)+"px");
	loginBox.css("top",($(window).height()/2-loginBox.height()/2)+"px");

    var searchKey = "";
    var order = "download-count";

    searchButton.click(function(){
        searchKey = "";
        $(".searchAllSum").text("");
        $("#searchUserSum").text("");
        searchKey = $("#searchInput").val();
        var noBlank = 0;
        for(i=0;i<searchKey.length;i++){
            if(searchKey.charAt(i) != " "){
                noBlank++;
            }
        }

        if($($("#searchUser").parent()).hasClass("uk-active")){
            emptyUser();
            if(searchKey.length > 0 && noBlank != 0) {
                userSearch(searchKey);
            }

            $(".searchUserName").removeHighlight();
            if ( searchKey ) {
                $(".searchUserName").highlight( searchKey );
            }
        }
        else{
            order = $("#order").val();
            empty();
            if(searchKey.length > 0 && noBlank != 0) {
                search(searchKey,order);
            }

            $(".toolName").removeHighlight();
            if ( searchKey ) {
                $(".toolName").highlight( searchKey );
            }
            $(".toolIntrodution").removeHighlight();
            if ( searchKey ) {
                $(".toolIntrodution").highlight( searchKey );
            }
        }
    });

	var submitButton = $(".submitButton");
	submitButton.click(function(){
		$("#searchAll").click();
		empty();
		var toolName = $("#form-h-a").val();
		var uploader = $("#form-h-b").val();
		var timeSt = $("#form-h-c").val();
		var timeEn = $("#form-h-c2").val();
		var tag = $("#form-h-d").val();
		var category = $("#form-h-e").val();

		advanceSearch(toolName,uploader,timeSt,timeEn,tag,category);
	});

	
	var order = $("#order");
	order.change(function(){
		var searchKey = $("#searchInput").val();
		var selectedVal = $("#order option:selected").val();
		var noBlank = 0;
	    for(i=0;i<searchKey.length;i++){
	        if(searchKey.charAt(i) != " "){
	            noBlank++;
	        }
	    }
		empty();
		if(searchKey.length > 0 && noBlank != 0) {
			search(searchKey,selectedVal);
        }

   		$(".toolName").removeHighlight();
    	if ( searchKey ) {
      		$(".toolName").highlight( searchKey );
    	}
    	$(".toolIntrodution").removeHighlight();
    	if ( searchKey ) {
      		$(".toolIntrodution").highlight( searchKey );
    	}
	});
});

window.onresize=function(){
	//分页切换
	var searchAll=$("#searchAll");
	var searchUser=$("#searchUser");
	var advancedSearch=$("#advancedSearch");
	var searchBottomBar=$("#searchNav").find("div");

	if(searchAll.parent().hasClass("uk-active")){
		searchBottomBar.css("left",searchAll.offset().left+"px");
	}
	else if(searchUser.parent().hasClass("uk-active")){
		searchBottomBar.css("left",searchUser.offset().left+"px");
	}
	else if(advancedSearch.parent().hasClass("uk-active")){
		searchBottomBar.css("left",advancedSearch.offset().left+"px");
	}

	//登录框
	var logMargin=$("#logMargin");
	var loginBox=$(".loginBox");
	logMargin.width($(window).width());
	logMargin.height($(window).height());
	loginBox.css("left",($(window).width()/2-loginBox.width()/2)+"px");
	loginBox.css("top",($(window).height()/2-loginBox.height()/2)+"px");



}

function empty(){
	var items = $(".items");
	for(i=0;i<items.length;i++){
		$(items[i]).remove();
	}
}

function emptyUser(){
	var searchUserBox = $(".searchUserBox");
	for(i=0;i<searchUserBox.length;i++){
		$(searchUserBox[i]).remove();
	}
}

function search(searchKey,order){
	$.ajax({
	    url:'/SearchToolServlet',
	    type:'GET', //GET
	    cache: false,
	    async:false,    //或false,是否异步
	    data: "searchKey=" + searchKey + "&order=" + order,
	    timeout:5000,    //超时时间
	    processData: false,
       	contentType: false,
	    success:function(result){
	    	var obj = JSON.parse(result);
	    	var searchAllSum = $(".searchAllSum");
	    	searchAllSum.text("已为您找到" + obj.num + "条相关结果");
	    	for(i=0;i<obj.list.length;i++){
	    		var toolId = obj.list[i].toolId;
		    	var toolName = obj.list[i].toolName;
		    	var uploaderName = obj.list[i].uploaderName;
		    	var brefIntro = obj.list[i].brefIntro;
		    	var downloadCount = obj.list[i].downloadCount;
		    	var zanCount = obj.list[i].zanCount;
		    	var commentCount = obj.list[i].commentCount;
		    	var fileSize = obj.list[i].fileSize;
		    	if(0<fileSize && fileSize < 1024){		//1MB以下
			    	fileSize = fileSize.toFixed(2) + "KB";
			    }
			    else if(1024 <= fileSize && fileSize <1024*1024){	//1GB以下
			    	fileSize = (fileSize/1024).toFixed(2)+"MB";
			    }
			    else if(fileSize >= 1024*1024){	//1GB以上
			    	fileSize = (fileSize/1024/1024).toFixed(2)+"GB";
			    }
		    	var uploadTime = obj.list[i].uploadTime;
		    	uploadTime = new Date(uploadTime*1000).toLocaleString();
		    	for(j=0;j<uploadTime.length;j++){
		    		if(uploadTime.charAt(j) == " "){
		    			uploadTime = uploadTime.substring(0,j);
		    		}
		    	}
		    	var nowVersion = obj.list[i].nowVersion;
		    	var tagList = new Array();
		    	for(j=0;j<obj.list[i].tagList.length;j++){
		    		tagList[j] = obj.list[i].tagList[j];
		    	}
		    	var versionList = new Array();
		    	for(j=0;j<obj.list[i].versionList.length;j++){
		    		versionList[j] = obj.list[i].versionList[j];
		    	}
		    	appendTool(toolId,toolName,uploaderName,brefIntro,downloadCount,zanCount,commentCount,fileSize,uploadTime,nowVersion,tagList,versionList);

	    	}
	    }
	})
}
function searchButtonClick(){
    $(".searchButton").click();
}
function appendTool(toolId,toolName,uploaderName,brefIntro,downloadCount,zanCount,commentCount,fileSize,uploadTime,nowVersion,tagList,versionList){
	  	var searchResult = $("#searchResult");
	  	var div = $("<div class='uk-margin-top items'></div>");


	    var firstBox = $("<div class='uk-vertical-align firstBox'></div>");

	    var toolIconSpan = $("<span class='uk-vertical-align-middle uk-icon-home toolIcon'></span>");
	    var toolNameA = $("<a class='uk-vertical-align-middle uk-margin-small-left toolName' href='/tool?tid="+toolId+"'></a>").text(toolName + " " + nowVersion);
	    firstBox.append(toolIconSpan);
	    firstBox.append(toolNameA);
	    for(x=0;x<tagList.length;x++){
	    	var toolLabel = $("<span class='uk-vertical-align-middle uk-margin-left toolLabel'></span>").text(tagList[x]);
	    	firstBox.append(toolLabel);
	    }
	    

		div.append(firstBox);


	    var hiddenSecondBox = $("<div class='uk-hidden-large uk-margin-top uk-margin-bottom hiddenSecondBox'></div>");

	    var downloadSpan = $("<span class='uk-margin-right'></span>");
	    var downloadIconSpan = $("<span class='uk-margin-small-left uk-icon-download'></span>");
	    var sumSpan1 = $("<span class='uk-margin-small-left sum'></span>").text(downloadCount);
	    downloadSpan.append(downloadIconSpan);
	    downloadSpan.append(sumSpan1);

	    var commentSpan = $("<span class='uk-margin-right'></span>");
	    var commentIconSpan = $("<span class='uk-margin-small-left uk-icon-comment-o'></span>");
	    var sumSpan2 = $("<span class='uk-margin-small-left sum'></span>").text(commentCount);
	    commentSpan.append(commentIconSpan);
	    commentSpan.append(sumSpan2);

	    var zanSpan = $("<span class='uk-margin-right'></span>");
	    var zanIconSpan = $("<span class='uk-margin-small-left uk-icon-thumbs-o-up'></span>");
	    var sumSpan3 = $("<span class='uk-margin-small-left sum'></span>").text(zanCount);
	    zanSpan.append(zanIconSpan);
	    zanSpan.append(sumSpan3);

	    hiddenSecondBox.append(downloadSpan);
	    hiddenSecondBox.append(commentSpan);
	    hiddenSecondBox.append(zanSpan);

	    div.append(hiddenSecondBox);


	    var secondBox = $("<div class='secondBox'></div>");
	    var uploaderSpan = $("<span>发布者：</span>");
	    var uploader = $("<span></span>").text(uploaderName);
	    var toolSizeSpan = $("<span class='uk-margin-large-left'>工具大小：</span>");
	    var toolSize = $("<span></span>").text(fileSize);
	    var uploadTimeSpan = $("<span class='uk-margin-large-left'>上传日期：</span>");
	    var uploadTime = $("<span></span>").text(uploadTime);
	    var secondBox2 = $("<div class='uk-hidden-medium uk-hidden-small secondBox2'></div>");

	    var bottomBlock1 = $("<div class='uk-margin-right bottomBlock'></div>");
	    var bottomBlock1ZanIconSpan = $("<span class='uk-margin-small-left uk-icon-thumbs-o-up'></span>");
	    var bottomBlock1SumSpan1 = $("<span class='uk-margin-small-left sum'></span>").text(zanCount);
	    bottomBlock1.append(bottomBlock1ZanIconSpan);
	    bottomBlock1.append(bottomBlock1SumSpan1);

	    var bottomBlock2 = $("<div class='uk-margin-right bottomBlock'></div>");
	    var bottomBlock2CommentIconSpan = $("<span class='uk-margin-small-left uk-icon-comment-o'></span>");
	    var bottomBlock2SumSpan2 = $("<span class='uk-margin-small-left sum'></span>").text(commentCount);
	    bottomBlock2.append(bottomBlock2CommentIconSpan);
	    bottomBlock2.append(bottomBlock2SumSpan2);
	    
	    var bottomBlock3 = $("<div class='uk-margin-right bottomBlock'></div>");
	    var bottomBlock3DownloadIconSpan = $("<span class='uk-margin-small-left uk-icon-download'></span>");
	    var bottomBlock3SumSpan3 = $("<span class='uk-margin-small-left sum'></span>").text(downloadCount);
	    bottomBlock3.append(bottomBlock3DownloadIconSpan);
	    bottomBlock3.append(bottomBlock3SumSpan3);

	    secondBox2.append(bottomBlock1);
	    secondBox2.append(bottomBlock2);
	    secondBox2.append(bottomBlock3);

	    secondBox.append(uploaderSpan);
	    secondBox.append(uploader);
	    secondBox.append(toolSizeSpan);
	    secondBox.append(toolSize);
	    secondBox.append(uploadTimeSpan);
	    secondBox.append(uploadTime);
	    secondBox.append(secondBox2);

	    div.append(secondBox);


	    var thirdBox = $("<div class='thirdBox'></div>");

	    var toolIntrodution = $("<p class='toolIntrodution'></p>").text(brefIntro);

	    thirdBox.append(toolIntrodution);

	    div.append(thirdBox);


	    var fourthBox = $("<div class='fourthBox'></div>");

	    var preVersionSpan = $("<span>历史版本：</span>");
	    fourthBox.append(preVersionSpan);

	    var flag = false;
	    for(x=0;x<versionList.length;x++){
	    	if(x == 5){
	    		flag = true;
	    		break;
	    	}
	    	var toolPostRevision = $("<span class='uk-margin-left toolPostRevision'></span>").text(versionList[x]);
	    	fourthBox.append(toolPostRevision);
	    }
	    
	    if(flag == true){
	    	var toolMoreVision = $("<a class='uk-margin-left toolMoreVision' href=''>更多版本</a>");
	    	fourthBox.append(toolMoreVision);
	    }
	    if(versionList.length == 0){
	    	var toolMoreVision = $("<span class='uk-margin-left' style='color:rgb(172,153,153);'>无</span>");
	    	fourthBox.append(toolMoreVision);
	    }
	    

	    div.append(fourthBox);
	    searchResult.before(div);
	    
}

function userSearch(searchKey){
	$.ajax({
	    url:'/SearchUploaderServlet',
	    type:'GET', //GET
	    cache: false,
	    async:false,    //或false,是否异步
	    data: "uploader=" + searchKey,
	    timeout:5000,    //超时时间
	    processData: false,
       	contentType: false,
	    success:function(result){
	    	var obj = JSON.parse(result);
	    	var searchUserSum = $("#searchUserSum");
	    	searchUserSum.text("已为您找到" + obj.num + "条相关结果");
	    	for(i=0;i<obj.list.length;i++){
	    		var uId = obj.list[i].uId;
		    	var username = obj.list[i].username;
		    	var sex = obj.list[i].sex;
		    	var department = obj.list[i].department;
		    	var job = obj.list[i].job;
		    	var selfIntro = obj.list[i].selfIntro;
		    	
		    	appendUser(uId,username,sex,department,job,selfIntro);
	    	}
	    }
	})
}

function appendUser(uId,username,sex,department,job,selfIntro){
	var searchUserResult = $("#searchUserResult");
	var div = $("<div class='searchUserBox'></div>");

	var searchUserIcon = $("<div class='searchUserIcon'></div>");
	var iconA = $("<a href=''></a>");
	var img = $("<img src='../img/headIcon.jpg' style='width: 60px;''>");
	iconA.append(img);
	searchUserIcon.append(iconA);

	div.append(searchUserIcon);


	var searchUserIntro = $("<div class='uk-width-4-5 searchUserIntro'></div>");
	var searchUserName =$("<a href='/UserCenterServlet?uid="+uId+"' class='searchUserName'></a>").text(username);
	var searchUserSex;
	if(sex == 0){
		searchUserSex = $("<i class='uk-icon-mars uk-margin-small-left searchUserSex'></i>");
	}
	else if(sex == 1){
		searchUserSex = $("<i class='uk-icon-venus uk-margin-small-left searchUserSex'></i>");
	}
	var jobDiv = $("<div class='uk-margin-small-top'></div>");
	var searchUserDepart = $("<span class='searchUserDepart'></span>").text("部门：" + department);
	var searchUserJob = $("<span class='uk-margin-left searchUserJob'></span>").text("职务：" + job);

	jobDiv.append(searchUserDepart);
	jobDiv.append(searchUserJob);

	var introDiv = $("<div class='uk-margin-small-top'></div>");
	var introSpan = $("<span>个人简介：</span>");
	var searchUserIntroduction = $("<p class='searchUserIntroduction'></p>").text(selfIntro);

	introDiv.append(introSpan);
	introDiv.append(searchUserIntroduction);

	searchUserIntro.append(searchUserName);
	searchUserIntro.append(searchUserSex);
	searchUserIntro.append(jobDiv);
	searchUserIntro.append(introDiv);

	div.append(searchUserIntro);

	searchUserResult.before(div);
}

function advanceSearch(toolName,uploader,timeSt,timeEn,tag,category){
	$.ajax({
	    url:'/AdvancedSearchServlet',
	    type:'GET', //GET
	    cache: false,
	    async:false,    //或false,是否异步
	    data: "toolName=" + toolName + "&uploader=" + uploader + "&timeSt=" + timeSt + "&timeEn=" + timeEn + "&tag=" + tag + "&category=" + category,
	    timeout:5000,    //超时时间
	    processData: false,
       	contentType: false,
	    success:function(result){
	    	var obj = JSON.parse(result);
	    	var searchAllSum = $(".searchAllSum");
	    	searchAllSum.text("已为您找到" + obj.num + "条相关结果");
	    	for(i=0;i<obj.list.length;i++){
	    		var toolId = obj.list[i].toolId;
		    	var toolName = obj.list[i].toolName;
		    	var uploaderName = obj.list[i].uploaderName;
		    	var brefIntro = obj.list[i].brefIntro;
		    	var downloadCount = obj.list[i].downloadCount;
		    	var zanCount = obj.list[i].zanCount;
		    	var commentCount = obj.list[i].commentCount;
		    	var fileSize = obj.list[i].fileSize;
		    	if(0<fileSize && fileSize < 1024){		//1MB以下
			    	fileSize = fileSize.toFixed(2) + "KB";
			    }
			    else if(1024 <= fileSize && fileSize <1024*1024){	//1GB以下
			    	fileSize = (fileSize/1024).toFixed(2)+"MB";
			    }
			    else if(fileSize >= 1024*1024){	//1GB以上
			    	fileSize = (fileSize/1024/1024).toFixed(2)+"GB";
			    }
		    	var uploadTime = obj.list[i].uploadTime;
		    	uploadTime = new Date(uploadTime*1000).toLocaleString();
		    	for(j=0;j<uploadTime.length;j++){
		    		if(uploadTime.charAt(j) == " "){
		    			uploadTime = uploadTime.substring(0,j);
		    		}
		    	}
		    	var nowVersion = obj.list[i].nowVersion;
		    	var tagList = new Array();
		    	for(j=0;j<obj.list[i].tagList.length;j++){
		    		tagList[j] = obj.list[i].tagList[j];
		    	}
		    	var versionList = new Array();
		    	for(j=0;j<obj.list[i].versionList.length;j++){
		    		versionList[j] = obj.list[i].versionList[j];
		    	}
		    	appendTool(toolId,toolName,uploaderName,brefIntro,downloadCount,zanCount,commentCount,fileSize,uploadTime,nowVersion,tagList,versionList);

	    	}
	    }
	})
}
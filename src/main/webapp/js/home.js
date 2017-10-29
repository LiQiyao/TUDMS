var toolBoxWidth;
var toolBriefIntroHeight;
var flag=0;

$(document).ready(function(){
	//背景canvas
	var canvas=$(".site-header").find("canvas");
	canvas.text("您的浏览器不支持Canvas！");

	var toolSize = $($(".toolSize")[0]);
	var text = toolSize.text();
    if(0<text && text < 1024){		//1MB以下
        toolSize.text("(" + text.toFixed(2) + "KB)");
    }
    else if(1024 <= text && text <1024*1024){	//1GB以下
        toolSize.text("(" + (text/1024).toFixed(2)+"MB)");
    }
    else if(text >= 1024*1024){	//1GB以上
        toolSize.text("(" + (text/1024/1024).toFixed(2)+"GB)");
    }

	//登录框
	var logMargin=$("#logMargin");
	var loginBox=$(".loginBox");
	logMargin.hide();
	logMargin.css("display","default");
	loginBox.hide();
	loginBox.css("display","default");
	loginBox.css("left",($(window).width()/2-loginBox.width()/2)+"px");
	loginBox.css("top",($(window).height()/2-loginBox.height()/2)+"px");
	$(".login").click(function(){
		loginBox.fadeIn(300);
		logMargin.fadeIn(300);

		setTimeout(function(){
			document.getElementById("username").focus();
		},300);
	});
	
	//工具盒子高度
	var toolBox=document.getElementsByClassName("toolBox");
	var toolBox_width=toolBox[0].clientWidth;

	toolBoxWidth=toolBox[0].clientWidth;	//用于瀑布流添加工具盒子

	for(i=0;i<toolBox.length;i++){
		toolBox[i].style.height=parseInt(toolBox_width)*0.618+40+"px";
	}


	//盒子头部宽度
	var tri=document.getElementsByClassName("tri");
	for(i=0;i<tri.length;i++){
		tri[i].style="width:"+parseInt(toolBox_width)+"px";
	}
	
	//工具盒子内文字描述行数
	var toolBriefIntro = document.getElementsByClassName("toolBriefIntro");
	var lineSum=0;
	while($(".bottomBlock").position().top-$(".toolBriefIntro").position().top-(20*lineSum) > 32){
		lineSum++;
	}
	for(i=0;i<toolBriefIntro.length;i++){
		$clamp(toolBriefIntro[i], {clamp: lineSum});
	}

	toolBriefIntroHeight=$(".toolBriefIntro").height();


	//搜索框
	var searchForm = $("#searchForm");
	var searchInput = $("#searchInput");
	var searchButton = $(".searchButton");
	var submit = $(".submit");
	var searchDiv=$(".searchDiv");
	searchDiv.css("left",($(window).width()/2-searchDiv.width()/2)+"px");
	submit.css("left",parseInt(searchForm.width()-30) + "px");
	searchButton.css("left",parseInt(searchForm.width()-22) + "px");

	

    $(window).scroll(function(){
        var siteHeader = $(".site-header");
        var canvasTop = siteHeader.find("canvas").offset().top;
        var canvasBottom = siteHeader.find("canvas").height();
        var dropNavContainer = $("#dropNavContainer");

        //固定导航栏样式
        if(canvasTop+canvasBottom+30>=$(window).scrollTop() && canvasTop < ($(window).scrollTop()+$(window).height())){
            dropNavContainer.removeClass("animated fadeInDown");
            dropNavContainer.addClass("animated fadeOutUp");
        }
        else{
            dropNavContainer.css("visibility","visible");
            dropNavContainer.removeClass("animated fadeOutUp");
            dropNavContainer.addClass("animated fadeInDown");
        }

        //瀑布流填充工具盒子
        if($(document).scrollTop() >= $(document).height() - $(window).height()){
        	setTimeout(addTool,300);
		}
    });

 
    $("#gridFilter").find("a").click(function(){
    	$('body,html').animate({  
                scrollTop: 297  
            },
            1);
    });


});
	
window.onload=function(){
	addTool();
}

$(window).resize(function(){
	//重绘背景canvas
	var canvas=$(".site-header").find("canvas")[0];
	var cxt = canvas.getContext("2d");
	var imgData = cxt.getImageData(0,0,canvas.width,canvas.height);
    canvas.width = window.innerWidth;
    cxt.fillStyle = 'rgb(103,216,241)';
    cxt.lineWidth = .1;
    cxt.strokeStyle = 'rgb(103,216,241)';
    cxt.putImageData(imgData,0,0);
    


	//工具盒子高度
	var toolBox=document.getElementsByClassName("toolBox");
	var toolBox_width=toolBoxWidth;
	for(i=0;i<toolBox.length;i++){
		toolBox[i].style.height=parseInt(toolBox_width)*0.618+40+"px";
	}

	//盒子头部宽度
	var tri=document.getElementsByClassName("tri");
	for(i=0;i<tri.length;i++){
		tri[i].style="width:"+parseInt(toolBox_width)+"px";
	}


	//工具盒子内文字描述行数
	var toolBriefIntro = document.getElementsByClassName("toolBriefIntro");
	var lineSum=0;
	while($(".bottomBlock").position().top-$(".toolBriefIntro").position().top-(20*lineSum) > 32){
		lineSum++;
	}
	for(i=0;i<toolBriefIntro.length;i++){
		$clamp(toolBriefIntro[i], {clamp: lineSum});
	}
	

	//搜索框
	var searchForm = $("#searchForm");
	var searchInput = $("#searchInput");
	var searchButton = $(".searchButton");
	var submit = $(".submit");
	var searchDiv=$(".searchDiv");
	searchDiv.css("left",($(window).width()/2-searchDiv.width()/2)+"px");
	submit.css("left",parseInt(searchForm.width()-30) + "px");
	searchButton.css("left",parseInt(searchForm.width()-22) + "px");

	
});

$(".searchButton").click(function(){
	$("#searchForm").submit();
});



function addTool(){
	var rightBar = $("#rightBar");

	var nowType;
	var selectedType = $("#gridFilter").find("li");
	for(i=0;i<selectedType.length;i++){
		if($(selectedType[i]).hasClass("uk-active")){
			nowType=i;
		}
	}
	var nowPos= new Array(5);
	nowPos[0]=0;
	nowPos[1]=0;
	nowPos[2]=0;
	nowPos[3]=0;
	nowPos[4]=0;
	nowPos[5]=0;
	var toolBox = $(".toolBox");
	for(i=0;i<toolBox.length;i++){
		if($(toolBox[i]).attr("data-uk-filter") == "filter-1"){
			nowPos[1]++;
		}
		else if($(toolBox[i]).attr("data-uk-filter") == "filter-2"){
			nowPos[2]++;
		}
		else if($(toolBox[i]).attr("data-uk-filter") == "filter-3"){
			nowPos[3]++;
		}
		else if($(toolBox[i]).attr("data-uk-filter") == "filter-4"){
			nowPos[4]++;
		}
		else if($(toolBox[i]).attr("data-uk-filter") == "filter-5"){
			nowPos[5]++;
		}
	}
	if(nowType == 0){
		for(i=1;i<=5;i++){
			nowPos[0]+=nowPos[i];
		}
	}
	var nowNum=4;
	if(flag == 0){
		flag=1;
		nowType=0;
		nowPos[nowType]=1;
		nowNum=8;
	}
	$.ajax({
	    url:'/api/index_tool_item_puller',
	    type:'POST', //GET
	    async:false,    //或false,是否异步
	    data:{
				type:nowType,
				startPos:nowPos[nowType],
				num:nowNum    //nowNum
		},
	    timeout:5000,    //超时时间
	    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
	    
	    success:function(data,textStatus,jqXHR){
	        for(i=0;i<data.length;i++){
		        var div=$("<div class='uk-width-large-3-10 uk-width-medium-2-5 uk-margin-left uk-margin-bottom toolBox' data-uk-filter='filter-"+data[i].toolType+"'></div>");
	        	var toolUrl="/tool?tid=" + data[i].toolId;	//获取data[i].toolId
	        	div.attr("onclick","window.open('"+toolUrl+"')");
			    var triDiv=$("<div class='tri'></div>");
			    var pattern = Trianglify({height: 20,width: 1000,cell_size: 100});
				triDiv.append(pattern.canvas());



			    var iconSpan1=$("<span class='uk-icon-home'></span>");
			    var toolNameSpan=$("<span class='toolName'></span>").text(" "+data[i].toolName+" ");
			    if(0<data[i].toolSize && data[i].toolSize < 1024){		//1MB以下
			    	data[i].toolSize = data[i].toolSize.toFixed(2) + "KB)";
			    }
			    else if(1024 <= data[i].toolSize && data[i].toolSize <1024*1024){	//1GB以下
			    	data[i].toolSize = (data[i].toolSize/1024).toFixed(2)+"MB)";
			    }
			    else if(data[i].toolSize >= 1024*1024){	//1GB以上
			    	data[i].toolSize = (data[i].toolSize/1024/1024).toFixed(2)+"GB)";
			    }
			    var toolSizeSpan=$("<span class='toolSize'></span>").text("("+data[i].toolSize);
			    var toolName=$("<h3 class='uk-panel-title uk-margin-top uk-margin-left'></h3>");
			    toolName.append(iconSpan1);
				toolName.append(toolNameSpan);
				toolName.append(toolSizeSpan);


			    var toolBriefIntroDiv=$("<div class='uk-margin-left uk-margin-right toolBriefIntro'></div>").text(data[i].toolBriefIntro);
				var lineSum=0;
				while(lineSum*20+5 < toolBriefIntroHeight){
					lineSum++;
				}
				$clamp(toolBriefIntroDiv[0], {clamp: lineSum});

			    var iconSpan2=$("<span class='uk-margin-small-left uk-icon-thumbs-o-up'></span>");
			    var zanSum=$("<span class='uk-margin-small-left sum'></span>").text(data[i].toolZanCount);
			    var bottomBlockDiv1=$("<div class='uk-width-1-4 uk-margin-left bottomBlock' style='left:5%;bottom:10px;'>");
			    bottomBlockDiv1.append(iconSpan2);
			    bottomBlockDiv1.append(zanSum);

			    var iconSpan3=$("<span class='uk-margin-small-left uk-icon-comment-o'></span>");
			    var commentSum=$("<span class='uk-margin-small-left sum'></span>").text(data[i].toolCommentCount);
			    var bottomBlockDiv2=$("<div class='uk-width-1-4 uk-margin-left bottomBlock' style='left:38%;bottom:10px;'>");
			    bottomBlockDiv2.append(iconSpan3);
			    bottomBlockDiv2.append(commentSum);

			    var iconSpan4=$("<span class='uk-margin-small-left uk-icon-download'></span>");
			    var downloadSum=$("<span class='uk-margin-small-left sum'></span>").text(data[i].toolDownloadCount);
			    var bottomBlockDiv3=$("<div class='uk-width-1-4 uk-margin-left bottomBlock' style='right:5%;bottom:10px;'>");
			    bottomBlockDiv3.append(iconSpan4);
			    bottomBlockDiv3.append(downloadSum);

				div.append(triDiv);
				div.append(toolName);
				div.append(toolBriefIntroDiv);
				div.append(bottomBlockDiv1);
				div.append(bottomBlockDiv2);
				div.append(bottomBlockDiv3);

			    var toolBox=document.getElementsByClassName("toolBox");
				var toolBox_width=toolBox[0].clientWidth;
				div.height(parseInt(toolBoxWidth)*0.618+40);
				div.width(toolBox_width);
				rightBar.append(div);
				myGrid.updateLayout();	//直接改写了grid.js的源代码

				if(toolName.height() >30){
					$clamp(toolBriefIntroDiv[0], {clamp: lineSum-1});
				}
				myGrid.updateLayout();	//直接改写了grid.js的源代码
		    }
	    }
	})
	
}
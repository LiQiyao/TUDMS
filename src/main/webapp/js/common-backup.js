$(document).ready(function(){
	//登录框
	var logMargin=$("#logMargin");
	var loginBox=$(".loginBox");
	logMargin.width($(window).width());
	logMargin.height($(window).height());
	logMargin.hide();
	logMargin.css("display","default");
	loginBox.hide();
	loginBox.css("display","default");
	$(".dropLogin").click(function(){
		loginBox.fadeIn(300);
		logMargin.fadeIn(300);

		setTimeout(function(){
			document.getElementById("username").focus();
		},300);
	});
	logMargin.click(function(){
		loginBox.fadeOut(300);
		logMargin.fadeOut(300);
	});
	$(".loginBoxClose").click(function(){
		loginBox.fadeOut(300);
		logMargin.fadeOut(300);
	});

	loginBox.css("left",($(window).width()/2-loginBox.width()/2)+"px");
	loginBox.css("top",($(window).height()/2-loginBox.height()/2)+"px");



    $(window).scroll(function(){
        var toTop = $("#toTop");

        //返回顶部样式
        if ($(window).scrollTop()<500){
            toTop.removeClass("animated fadeInUp");
            toTop.addClass("animated fadeOutDown");
        }
        else{
            toTop.css("visibility","visible");
            toTop.removeClass("animated fadeOutDown");
            toTop.addClass("animated fadeInUp");
        }
    });
});


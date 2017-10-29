$(document).ready(function(){
	var searchIcon = $("#searchIcon");
	var searchBar = $("#searchBar");
	var searchMargin = $("#searchMargin");
	var searchInput = $("#searchInput");
	var dropdownStyle = $(".dropdownStyle");

	dropdownStyle.width($(".navStyle").width() - 15);
	searchIcon.click(function(){
		searchBar.fadeIn(300);
		searchMargin.fadeIn(300);
		setTimeout(function(){
			searchInput.focus();
		},400);
	});

	searchMargin.click(function(){
		searchBar.fadeOut(300);
		searchMargin.fadeOut(300);
	});

	var toolBriefIntro = $(".toolBriefIntro");
	for(i=0;i<toolBriefIntro.length;i++){
		$clamp(toolBriefIntro[i], {clamp: 3});
	}
});

$("#gridFilter").children("li").click(function(e){
	setTimeout(function(){
		$(".uk-parent").removeClass("uk-open");
		$('body,html').animate({  
                scrollTop: 0  
            },
            1);
	},10);
});

$(document).click(function(e){
	e = window.event || e;
	obj = $(e.srcElement || e.target);
	if(!$(obj).is("#sortIcon")){
		$("#sortIcon").removeClass("rotate");
	}
	else{
		if($("#sortIcon").hasClass("rotate")){
			setTimeout(function(){
				$("#sortIcon").removeClass("rotate");
			},10);
		}
		else{
			setTimeout(function(){
				$("#sortIcon").addClass("rotate");
			},10);
		}
	}
});

$(function(){
	var navStyle = $(".navStyle");
	var lastScrollTop = $(document).scrollTop();
	$(window).scroll(function(){
		var nowScrollTop = $(document).scrollTop();
		if(nowScrollTop < 0){
			$('.navStyle').removeClass('navFixed');
		}
		if(nowScrollTop > 150){
			$('.navStyle').addClass('navFixed');
		}
		if(nowScrollTop > 150){
			$('.navStyle').addClass('navHidden');
			if(nowScrollTop > lastScrollTop){
				$('.navStyle').removeClass('navShow');
			}else{
				$('.navStyle').addClass('navShow');
			}
			lastScrollTop = nowScrollTop;
		}
	});
});
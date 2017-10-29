$(document).ready(function(){
	var searchIcon = $("#searchIcon");
	var searchBar = $("#searchBar");
	var searchMargin = $("#searchMargin");
	var searchInput = $("#searchInput");
	var backIcon = $("#backIcon");

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

	backIcon.click(function(){
		$(window).attr('location','appHome.html');
	});
});
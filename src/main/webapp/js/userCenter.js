$(document).ready(function() {
	//限制字符个数
	$('.push-introduction').each(function() {
		var maxwidth = 90;
		if($(this).text().length > maxwidth) {
			$(this).text($(this).text().substring(0, maxwidth));
			$(this).html($(this).html() + '…');
		}
	});
	$('.tool-introduction').each(function() {
		var maxwidth1 = 220;
		if($(this).text().length > maxwidth1) {
			$(this).text($(this).text().substring(0, maxwidth1));
			$(this).html($(this).html() + '…');
		}
	});
	$('#rightbar1').click(function() {
		$('#rightbar1').css('border-bottom', '2px solid red');
		$('#rightbar2').css('border-bottom', 'none');
        $('#rightbar3').css('border-bottom', 'none');
	});
	$('#rightbar2').click(function() {
		$('#rightbar2').css('border-bottom', '2px solid red');
		$('#rightbar1').css('border-bottom', 'none');
        $('#rightbar3').css('border-bottom', 'none');
	});
    $('#rightbar3').click(function() {
        $('#rightbar3').css('border-bottom', '2px solid red');
        $('#rightbar1').css('border-bottom', 'none');
        $('#rightbar2').css('border-bottom', 'none');
    });
	$('.ucdy-class').click(function() {
		$('.ucdy-class').siblings().toggleClass('ucdy-class-active', false);
		$(this).toggleClass('ucdy-class-active', true);
	});
});

$('#upload-new').on('click', function() {
	$('#upload-head').click();
});
$('#upload-head').on('change', function() {
	var objUrl = getObjectURL(this.files[0]);
	if(objUrl) {
		$('#avatar').attr("src", objUrl);
	}

	function getObjectURL(file) {
		var url = null;
		if(window.createObjectURL != undefined) { // basic
			url = window.createObjectURL(file);
		} else if(window.URL != undefined) { // mozilla(firefox)
			url = window.URL.createObjectURL(file);
		} else if(window.webkitURL != undefined) { // webkit or chrome
			url = window.webkitURL.createObjectURL(file);
		}
		return url;
	}
});
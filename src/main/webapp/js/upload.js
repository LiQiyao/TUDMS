var formItem = new Array(7);


$(document).ready(function(){
    var filePicker = $("#filePicker");
    var fileInput = filePicker.find("input");
    fileInput.attr("id","form-h-c");

    formItem[0] = $("#form-h-a");
    formItem[1] = $("#form-h-b");
    formItem[2] = $("#form-h-c");
    formItem[3] = $("#form-h-d");
    formItem[4] = $("#form-h-e");
    formItem[5] = $("#form-h-f");
    formItem[6] = $("#form-h-g");

	var uploadButton=$("#uploadButton");
	var progressAll=$("#progressAll");
	var uploadMargin=$("#uploadMargin");
	var example=$("#form-h-g");
	var stopUpload=$("#stopUpload");
	var cancelUpload=$("#cancelUpload");

	var progressSum=$("#progressSum");
	progressSum.width(example.width());



	//工具简介字数限制
	document.getElementById("form-h-f").onkeydown = function(evt){
		var thisErrorHint = $($($($("#form-h-f").parent()).next()).next()).children(); 
		if(this.value.length < 300){
	    	event.returnValue = true;
	    }
	    if(this.value.length == 300 && evt.keyCode == 8){
	    	event.returnValue = true;
	    	thisErrorHint.text("请输入工具简介");
	    	thisErrorHint.css("display","none");
	    }
	    else if(this.value.length >= 300 && evt.keyCode != 8){
	    	event.returnValue = false;
	    	thisErrorHint.text("字数已超出300字");
	    	thisErrorHint.css("display","inline-block");
	    }
	}

	//上传工具遮罩
	uploadMargin.width($(window).width());
	uploadMargin.height($(window).height());
	uploadMargin.hide();
	uploadMargin.css("display","default");
	
});

window.onresize=function(){
	var uploadButton=$("#uploadButton");
	var progressAll=$("#progressAll");
	var stopUpload=$("#stopUpload");
	var cancelUpload=$("#cancelUpload");
	var uploadMargin=$("#uploadMargin");
	var example=$("#form-h-g");

	if(uploadMargin.css("display") == "block"){
		uploadButton.width(example.width());
	}
	
	uploadMargin.width($(window).width());
	uploadMargin.height($(window).height());

	progressAll.css("left",example.offset().left);
}

//工具上传按钮隐藏
function toolChoose(){
	$('input[id=form-h-c]').click();
}



//计算文件大小
function calcute(file){
    var fileName = file.name;
    var fileSize = file.size;
    var filePath = $("#filePath");
    if(0 < fileSize && fileSize < 1024){	//小于1KB
        fileSize=fileSize.toFixed(2)+"B";
    }
    else if(1024 <= fileSize && fileSize < 1048576){	//小于1MB
        fileSize=(fileSize/1024).toFixed(2)+"KB";
    }
    else if(1048576 <= fileSize && fileSize < 1073741824){	//小于1GB
        fileSize=(fileSize/1024/1024).toFixed(2)+"MB";
    }
    else if(fileSize >= 1073741824){	//大于1GB
        fileSize=(fileSize/1024/1024/1024).toFixed(2)+"GB";
    }
    filePath.text(fileName + " ("+fileSize + ")");
}

//错误提示
function isFilled(e){
	var $e = $(e);
	var thisErrorHint = $($e.next());
	if($e.val() == ""){
		thisErrorHint.css("display","inline-block");
	}
	else{
		thisErrorHint.css("display","none");
	}

	var noBlankSum = 0;
	for(i=0;i<$e.val().length;i++){
		if($e.val().charAt(i) != " "){
			noBlankSum++;
		}
	}
	if($e.val().length > 0 && noBlankSum == 0){
		thisErrorHint.css("display","inline-block");
	}
}

//按键取消错误提示
function hiddenErrorHint(e){
	var $e = $(e);
	var thisErrorHint = $($e.next());
	if($e.val() == ""){
		thisErrorHint.css("display","inline-block");
	}
	else{
		thisErrorHint.css("display","none");
	}
}

var errorHint = $(".errorHint");

function startUpload(){
    var uploadButton=$("#uploadButton");
    var progressAll=$("#progressAll");
    var uploadMargin=$("#uploadMargin");
    var example=$("#form-h-g");
    var stopUpload=$("#stopUpload");
    var cancelUpload=$("#cancelUpload");
    var progressSum=$("#progressSum");
    var progressBar=$("#progressBar");

    //判断表单是否填完
	for(i=0;i<errorHint.length;i++){
		$(errorHint[i]).css("display","none");
	}

    for(i=0;i<formItem.length;i++){
		var noBlankSum = 0;
		for(j=0;j<formItem[i].val().length;j++){
			if(formItem[i].val().charAt(j) != " "){
				noBlankSum++;
			}
		}
		if(formItem[i].val() == "" || (formItem[i].val().length > 0 && noBlankSum == 0) ){
			$('body,html').animate({
                scrollTop: formItem[i].offset().top - 100
			},500);
			$(errorHint[i]).css("display","inline-block");
			return;
		}
    }

    progressAll.width(example.width()+14);
	progressAll.css("top",uploadButton.offset().top+"px");
	progressAll.css("left",uploadButton.offset().left+"px");
	uploadMargin.fadeIn(300);
	stopUpload.fadeIn(300);
	cancelUpload.fadeIn(300);
	progressBar.width(0);
	uploadButton.val("正在上传");
	uploadButton.animate({
		width:example.width()+14,
		backgroundColor:"rgb(255,255,255)",
		color:"rgb(0,168,230)"
	});
	progressAll.css("z-index",3);

    uploader.upload();

}

function nowProgress(percentage) {
	var per = Math.round(percentage * 100);
    $("#progressBar").css("width",per+"%");
    $("#progressSum").text(per + "%");
}

function uploadSuccess(result) {
    var uploadButton=$("#uploadButton");
    var progressAll=$("#progressAll");
    var uploadMargin=$("#uploadMargin");
    var example=$("#form-h-g");
    var stopUpload=$("#stopUpload");
    var cancelUpload=$("#cancelUpload");
    var progressSum=$("#progressSum");
    var progressBar=$("#progressBar");

    if(parseInt(progressBar.width()) >= 100 && parseInt(result) != -1){
		progressBar.css("width",100 + "%");
		setTimeout(function(){
			progressBar.css("width",0);
			uploadMargin.fadeOut(300);
			stopUpload.fadeOut(300);
			cancelUpload.fadeOut(300);
			$("#progressBar").width(0);
			UIkit.notify({
				message : '上传成功!',
				status  : 'success',
				timeout : 2000,
				pos     : 'top-center'
			});
			uploadButton.animate({
					backgroundColor:"rgb(0,168,230)",
					color:"rgb(255,255,255)",
					width:200
				},
				{easing:"linear"});
			uploadButton.val("开始上传");
			setTimeout(function(){
				progressAll.css("z-index",0);
			},500);
		},300);
		setTimeout(function () {
			$(window).attr('location','/tool?tid=' + result);
		},1000);
	}
	else if(result == -1){
		uploadFail();
	}
}

function uploadFail(){
    var uploadButton=$("#uploadButton");
    var progressAll=$("#progressAll");
    var uploadMargin=$("#uploadMargin");
    var example=$("#form-h-g");
    var stopUpload=$("#stopUpload");
    var cancelUpload=$("#cancelUpload");
    var progressSum=$("#progressSum");
    var progressBar=$("#progressBar");

    uploadMargin.fadeOut(300);
    stopUpload.fadeOut(300);
    cancelUpload.fadeOut(300);
    UIkit.notify({
        message : '上传失败!',
        status  : 'danger',
        timeout : 2000,
        pos     : 'top-center'
    });
    progressBar.width(0);
    uploadButton.animate({
            backgroundColor:"rgb(0,168,230)",
            color:"rgb(255,255,255)",
            width:200
        },
        {easing:"linear"});
    uploadButton.val("开始上传");
    setTimeout(function(){
        progressAll.css("z-index",0);
    },300);
}
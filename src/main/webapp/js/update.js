var formItem = new Array(2);


$(document).ready(function(){
    var filePicker = $("#filePicker");
    var fileInput = filePicker.find("input");
    fileInput.attr("id","form-h-c");

    formItem[0] = $("#form-h-b");
    formItem[1] = $("#form-h-c");

	var updateButton=$("#updateButton");
	var progressAll=$("#progressAll");
	var updateMargin=$("#updateMargin");
	var example=$("#form-h-b");
	var stopUpdate=$("#stopUpdate");
	var cancelUpdate=$("#cancelUpdate");

	var progressSum=$("#progressSum");
	progressSum.width(example.width());

/*	updateButton.click(function(){
		//判断表单是否填完
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
           		$(formItem[i].next()).css("display","inline-block");
				return;
			}
		}
		

		progressAll.width(example.width()+14);
		progressAll.css("top",updateButton.offset().top+"px");
		progressAll.css("left",updateButton.offset().left+"px");
		updateMargin.fadeIn(300);
		stopUpdate.fadeIn(300);
		cancelUpdate.fadeIn(300);
		var progressBar=$("#progressBar");
		progressBar.width(0);
		updateButton.val("正在更新");
		updateButton.animate({
			width:example.width()+14,
			backgroundColor:"rgb(255,255,255)",
			color:"rgb(0,168,230)"
		});
		progressAll.css("z-index",3);

		var per = 0;
		$.ajax({
            url: '/UpdateServlet',
            type: 'POST',
            cache: false,
            data: new FormData($('#updateForm')[0]),
            processData: false,
            contentType: false,
            xhr: function(){
                var xhr = $.ajaxSettings.xhr();
                if(xhr.upload) {
                    xhr.upload.addEventListener("progress" , function(evt){
                        var loaded = evt.loaded;                  //已经上传大小情况
                        var tot = evt.total;                      //附件总大小
                        per = Math.round(100*loaded/tot);     //已经上传的百分比
                        progressBar.css("width",per+"%");
                        progressSum.text(per + "%");


					}, false);
                    return xhr;
                }
            },
            success:function (result) {
                if(per >= 100 && result != -1){
                    progressBar.css("width",100 + "%");
                    setTimeout(function(){
                        progressBar.css("width",0);
                        updateMargin.fadeOut(300);
                        stopUpdate.fadeOut(300);
                        cancelUpdate.fadeOut(300);
                        $("#progressBar").width(0);
                        UIkit.notify({
                            message : '更新成功!',
                            status  : 'success',
                            timeout : 2000,
                            pos     : 'top-center'
                        });
                        updateButton.animate({
                                backgroundColor:"rgb(0,168,230)",
                                color:"rgb(255,255,255)",
                                width:200
                            },
                            {easing:"linear"});
                        updateButton.val("开始更新");
                        setTimeout(function(){
                            progressAll.css("z-index",0);
                        },500);
                    },300);
					setTimeout(function () {
                        $(window).attr('location','/tool?tid=' + result);
                    },1000);
                }
                else if(result == -1){
                    updateMargin.fadeOut(300);
                    stopUpdate.fadeOut(300);
                    cancelUpdate.fadeOut(300);
                    UIkit.notify({
                        message : '更新失败!',
                        status  : 'danger',
                        timeout : 2000,
                        pos     : 'top-center'
                    });
                    progressBar.width(0);
                    updateButton.animate({
                            backgroundColor:"rgb(0,168,230)",
                            color:"rgb(255,255,255)",
                            width:200
                        },
                        {easing:"linear"});
                    updateButton.val("开始更新");
                    setTimeout(function(){
                        progressAll.css("z-index",0);
                    },300);

                }
            },
			error:function () {
                updateMargin.fadeOut(300);
                stopUpdate.fadeOut(300);
                cancelUpdate.fadeOut(300);
                UIkit.notify({
                    message : '更新失败!',
                    status  : 'danger',
                    timeout : 2000,
                    pos     : 'top-center'
                });
                progressBar.width(0);
                updateButton.animate({
                        backgroundColor:"rgb(0,168,230)",
                        color:"rgb(255,255,255)",
                        width:200
                    },
                    {easing:"linear"});
                updateButton.val("开始更新");
                setTimeout(function(){
                    progressAll.css("z-index",0);
                },300);
            }
        });
		
	});*/



	//上传工具遮罩
	updateMargin.width($(window).width());
	updateMargin.height($(window).height());
	updateMargin.hide();
	updateMargin.css("display","default");

	
});

window.onresize=function(){
	var updateButton=$("#updateButton");
	var progressAll=$("#progressAll");
	var stopUpdate=$("#stopUpdate");
	var cancelUpdate=$("#cancelUpdate");
	var updateMargin=$("#updateMargin");
	var example=$("#form-h-b");

	if(updateMargin.css("display") == "block"){
		updateButton.width(example.width());
	}
	
	updateMargin.width($(window).width());
	updateMargin.height($(window).height());

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
	if(formItem[1].val != ""){
		$(formItem[1].next()).css("display","none");
	}
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

function startUpdate(){
    var updateButton=$("#updateButton");
    var progressAll=$("#progressAll");
    var updateMargin=$("#updateMargin");
    var example=$("#form-h-b");
    var stopUpdate=$("#stopUpdate");
    var cancelUpdate=$("#cancelUpdate");
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
    progressAll.css("top",updateButton.offset().top+"px");
    progressAll.css("left",updateButton.offset().left+"px");
    updateMargin.fadeIn(300);
    stopUpdate.fadeIn(300);
    cancelUpdate.fadeIn(300);
    progressBar.width(0);
    updateButton.val("正在更新");
    updateButton.animate({
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

function updateSuccess(result) {
    var updateButton=$("#updateButton");
    var progressAll=$("#progressAll");
    var updateMargin=$("#updateMargin");
    var example=$("#form-h-b");
    var stopUpdate=$("#stopUpdate");
    var cancelUpdate=$("#cancelUpdate");
    var progressSum=$("#progressSum");
    var progressBar=$("#progressBar");

    if(parseInt(progressBar.width()) >= 100 && parseInt(result) != -1){
        progressBar.css("width",100 + "%");
        setTimeout(function(){
            progressBar.css("width",0);
            updateMargin.fadeOut(300);
            stopUpdate.fadeOut(300);
            cancelUpdate.fadeOut(300);
            $("#progressBar").width(0);
            UIkit.notify({
                message : '更新成功!',
                status  : 'success',
                timeout : 2000,
                pos     : 'top-center'
            });
            updateButton.animate({
                    backgroundColor:"rgb(0,168,230)",
                    color:"rgb(255,255,255)",
                    width:200
                },
                {easing:"linear"});
            updateButton.val("开始更新");
            setTimeout(function(){
                progressAll.css("z-index",0);
            },500);
        },300);
        setTimeout(function () {
            $(window).attr('location','/tool?tid=' + result);
        },1000);
    }
    else if(result == -1){
        updateFail();
    }
}

function updateFail(){
    var updateButton=$("#updateButton");
    var progressAll=$("#progressAll");
    var updateMargin=$("#updateMargin");
    var example=$("#form-h-b");
    var stopUpdate=$("#stopUpdate");
    var cancelUpdate=$("#cancelUpdate");
    var progressSum=$("#progressSum");
    var progressBar=$("#progressBar");

    updateMargin.fadeOut(300);
    stopUpdate.fadeOut(300);
    cancelUpdate.fadeOut(300);
    UIkit.notify({
        message : '更新失败!',
        status  : 'danger',
        timeout : 2000,
        pos     : 'top-center'
    });
    progressBar.width(0);
    updateButton.animate({
            backgroundColor:"rgb(0,168,230)",
            color:"rgb(255,255,255)",
            width:200
        },
        {easing:"linear"});
    updateButton.val("开始更新");
    setTimeout(function(){
        progressAll.css("z-index",0);
    },300);
}
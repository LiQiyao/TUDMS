function URLFileNameGetter(URL) {
    var arr=URL.split("/");
    return arr[arr.length-1];
}

var downloader={
    init:function(){
        $.ajaxTransport("+binary", function(options, originalOptions, jqXHR){
            // check for conditions and support for blob / arraybuffer response type
            if (window.FormData && ((options.dataType && (options.dataType == 'binary')) || (options.data && ((window.ArrayBuffer && options.data instanceof ArrayBuffer) || (window.Blob && options.data instanceof Blob)))))
            {
                return {
                    // create new XMLHttpRequest
                    send: function(headers, callback){
                        // setup all variables
                        var xhr = new XMLHttpRequest(),
                            url = options.url,
                            type = options.type,
                            async = options.async || true,
                            // blob or arraybuffer. Default is blob
                            dataType = options.responseType || "blob",
                            data = options.data || null,
                            username = options.username || null,
                            password = options.password || null;

                        xhr.addEventListener('load', function(){
                            var data = {};
                            data[options.dataType] = xhr.response;
                            // make callback and send data
                            callback(xhr.status, xhr.statusText, data, xhr.getAllResponseHeaders());
                        });
                        // Rin 下面这个是后来加的，上传是iXHR.upload.onprogress
                        xhr.onprogress = function (ev) {
                            if(ev.lengthComputable) {
                                changeProgress(ev.loaded/ev.total);
                            }
                        }
                        xhr.open(type, url, async, username, password);

                        // setup custom headers
                        for (var i in headers ) {
                            xhr.setRequestHeader(i, headers[i] );
                        }

                        xhr.responseType = dataType;
                        xhr.send(data);
                    },
                    abort: function(){
                        jqXHR.abort();
                    }
                };
            }
        });
    },
};
function changeProgress(progress){
    progress=parseInt(progress*100);
    $("#tooldownloadbar").html(progress+"%");
    $("#tooldownloadbar").css("left",progress+"%");
    $("#tooldownloadbar").css("width",progress+"%");
}
function downloadURL(URL,this0) {
    //显示进度条
    tooldiv = $(this0).parent().parent().html();
    console.log(tooldiv);
    $(this0).parent().siblings().toggleClass('uk-hidden',true);
    $(this0).parent().toggleClass('uk-progress uk-progress-striped uk-active',true);
    $(this0).parent().toggleClass('tool-new-version tool-old-version',false);
    $(this0).parent().html('<div class="uk-progress-bar" id="tooldownloadbar"></div>');

    function downFile(blob, fileName) {
        if (window.navigator.msSaveOrOpenBlob) {
            navigator.msSaveBlob(blob, fileName);
        } else {
            var link = document.createElement('a');
            link.href = window.URL.createObjectURL(blob);
            link.download = fileName;
            link.click();
            window.URL.revokeObjectURL(link.href);
        }
    }
    ajaxObj=$.ajax({
        type:'GET',
        dataType: "binary",
        url:URL,
        processData: false,
        success:function(data){
            console.log("download finished.");
            console.log(data);
            var blob = new Blob([data],{type:'application/octet-stream'});
            downFile(blob,URLFileNameGetter(URL));
            console.log("download succeed.");
            jdt();//取消进度条，重新显示多版本
        }
    });
}

function timeTranslate(UnixTimestamp) {
    var t=new Date(UnixTimestamp*1000).toLocaleString();
    return t;
}

$(document).ready(function() {
    downloader.init();

    var uploadTime = $(".version-date");
    for(var i=0;i<uploadTime.length;i++){
        var t = new Date(($(uploadTime[i]).text())*1000).toLocaleString();
        $(uploadTime[i]).text(t);
    }

    var fileSize = $(".vsize");
    for(var i=0;i<fileSize.length;i++){

        var size = $(fileSize[i]).text();

        if(0<size && size < 1024){		//1MB以下
            size = (size/1).toFixed(2) + "KB";
        }
        else if(1024 <= size && size <1024*1024){	//1GB以下
            size = (size/1024).toFixed(2)+"MB";
        }
        else if(size >= 1024*1024){	//1GB以上
            size = (size/1024/1024).toFixed(2)+"GB";
        }
        $(fileSize[i]).text(size)
    }


    if($('#follow-tool').children().first().hasClass('uk-icon-star')) {
        $('#follow-tool').children().first().css('color', 'orangered');
        $('#follow-tool').attr('title', '取消关注');
    } else {
        $('#follow-tool').attr('title', '关注该工具');
    }
    $('.icon-share').on('click', function() {

        UIkit.notify({
            message: '链接已复制',
            status: 'info',
            timeout: 1000,
            pos: 'top-center'
        }, {
            status: 'success'
        });
    });
    commentajax();
    problemajax();

    $('#follow-tool').on('click', function() {
        $('#follow-tool').children().first().toggleClass('uk-icon-star-o');
        $('#follow-tool').children().first().toggleClass('uk-icon-star');
        if($('#follow-tool').children().first().hasClass('uk-icon-star')) {
            $('#follow-tool').children().first().css('color', 'orangered');
            $('#follow-tool').attr('title', '取消关注');
        } else {
            $('#follow-tool').attr('title', '关注该工具');
        }
        $.ajax({
            type:"post",
            url:"/api/change_attention_status",
            data: {
                tid:getQueryString("tid")
            },
            dataType :"json",
            async:true,
            success: function(data) {
            },
            error: function(jqXHR) {
                //alert("发生错误：" + jqXHR.status);
            },
        });
    });

    $('#like-tool').on('click', function() {
        $.ajax({
            type:"post",
            url:"/api/zan",
            data: {
                tid:getQueryString("tid")
            },
            dataType :"json",
            async:true,
            success: function(data) {
            },
            error: function(jqXHR) {
                //alert("发生错误：" + jqXHR.status);
            },
        });
        $('#like-icon').toggleClass('uk-icon-thumbs-o-up');
        $('#like-icon').toggleClass('uk-icon-thumbs-up');
        if($('#like-icon').hasClass('uk-icon-thumbs-up')) {
            $('#like-icon').css('color', 'orangered');
            $('#like-tool').attr('title', '取消赞');
        } else {
            $('#like-tool').attr('title', '赞一下');
        }
    });

    $('#modify-intro-button').on('click',function(){
        var txt = $('#tool-introduction-content').text();
        $('#intro-con').text(txt);
    });
    $('#modify-readme-button').on('click',function(){
        var txt1 = $('#readme-content').text();
        $('#readme-con').text(txt1);
    });
    $('#cancel-download').on('click',function() {
        console.log($(this).parent());
        console.log(tooldiv);
        $('.tool-versions').html(tooldiv);
        $.getScript('../js/details_page.js',function() {
        });
    });

    $('.readme-body').html(marked($('.readme-body').html()));
});



function jdt() {
    console.log(tooldiv);
    $('.tool-versions').html(tooldiv);
}
function getQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}
function commentajax (){
    $('.comment-list').html('');
    $.ajax({
        type: "POST",
        url: "/api/comment_getter",
        data: {
            toolId: getQueryString("tid"),
            startPos: 0,
            num: 10
        },
        dataType: "json",
        success: function(data) {
            for(var i = 0; i < data.length; i++) {
                var comment = '<li class="one-comment"><div class="one-comment-l"><a href="/UserCenterServlet?uid='+data[i].senderUid+'"><img src="../img/headIcon.jpg" alt="head" /></a></div><div class="one-comment-r"><div class="one-comment-r-t"><a href="/UserCenterServlet?uid='+data[i].senderUid+'">' + data[i].senderName + '</a></div><p>' + data[i].content + '</p><div class="one-comment-r-b"><span>时间：</span><span class="comment-time">' + timeTranslate( data[i].commentTime ) + '</span></div></div></li>';
                $('.comment-list').append(comment);
            }
        },
        error: function(jqXHR) {
            alert("发生错误：" + jqXHR.status);
        },
    });
}
function problemajax (){
    $('.problem-list').html('');
    $.ajax({
        type: "POST",
        url: "/api/ask_getter",
        data: {
            toolId: getQueryString("tid"),
            startPos: 0,
            num: 10
        },
        dataType: "json",
        success: function(data) {
            function add0(m){return m<10?'0'+m:m }
            for(i = 0; i < data.length; i++) {
                var time = new Date(data[i].time*1000);
                var y = time.getFullYear();
                var m = time.getMonth()+1;
                var d = time.getDate();
                var h = time.getHours();
                var mm = time.getMinutes();
                var s = time.getSeconds();
                if(data[i].replyList.length!=0) {
                    var problem = '<li class="one-problem"  data-uk-filter="resolvepro">'
                        +'<div class="one-problem-l">'
                        +'<a href="/UserCenterServlet?uid='+data[i].senderUid+'"><img src="../img/headIcon.jpg" alt="head" /></a>'
                        +'</div>'
                        +'<div class="one-problem-r">'
                        +'<div class="one-problem-r-t">'
                        +'<a href="/UserCenterServlet?uid='+data[i].senderUid+'">'+data[i].senderName+'</a>'
                        +'</div>'
                        +'<p>'+data[i].content+'</p>'
                        +'<div class="one-problem-r-b">'
                        +'<span>时间：</span>'
                        +'<span class="problem-time">'+y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s)+'</span>'
                        +'<a href="javascript:void(0)" style="text-decoration: none;float: right;" data-uk-toggle="{target:'+'\'#reply-body-'+data[i].askId+'\'}">回答(<span class="reply-num">'+data[i].replyList.length+'</span>)</a>'
                        +'</div>'
                        +'</div>'
                        +'<div class="reply-body uk-hidden" id="reply-body-'+data[i].askId+'">'
                        +'<ul class="reply-list">'

                        +'</ul>'
                        +'<div class="my-reply uk-width-1-1 uk-container-center">'
                        +'<textarea name="my-replyword" class="my-replyword" id="reply'+data[i].askId+'-content"></textarea>'
                        +'<input id="ask'+data[i].askId+'" type="submit" value="发表" class="uk-button uk-button-primary" onclick="sendReply('+data[i].senderUid+','+data[i].askId+')"/>'
                        +'</div>'
                        +'</div>'
                        +'</li>';
                    $('.problem-list').append(problem);
                }else {
                    var problem = '<li class="one-problem"  data-uk-filter="unresolvepro">'
                        +'<div class="one-problem-l">'
                        +'<a href="/UserCenterServlet?uid='+data[i].senderUid+'"><img src="../img/headIcon.jpg" alt="head" /></a>'
                        +'</div>'
                        +'<div class="one-problem-r">'
                        +'<div class="one-problem-r-t">'
                        +'<a href="/UserCenterServlet?uid='+data[i].senderUid+'">'+data[i].senderName+'</a>'
                        +'</div>'
                        +'<p>'+data[i].content+'</p>'
                        +'<div class="one-problem-r-b">'
                        +'<span>时间：</span>'
                        +'<span class="problem-time">'+y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s)+'</span>'
                        +'<a href="javascript:void(0)" style="text-decoration: none;float: right;" data-uk-toggle="{target:'+'\'#reply-body-'+data[i].askId+'\'}">回答(<span class="reply-num">'+data[i].replyList.length+'</span>)</a>'
                        +'</div>'
                        +'</div>'
                        +'<div class="reply-body uk-hidden" id="reply-body-'+data[i].askId+'">'
                        +'<ul class="reply-list">'

                        +'</ul>'
                        +'<div class="my-reply uk-width-1-1 uk-container-center">'
                        +'<textarea name="my-replyword" class="my-replyword" id="reply'+data[i].askId+'-content"></textarea>'
                        +'<input id="ask'+data[i].askId+'" type="submit" value="发表" class="uk-button uk-button-primary" onclick="sendReply('+data[i].senderUid+','+data[i].askId+')"/>'
                        +'</div>'
                        +'</div>'
                        +'</li>';
                    $('.problem-list').append(problem);
                }


                for (j=0;j<data[i].replyList.length;j++) {
                    var time = new Date(data[i].replyList[j].replyTime*1000);
                    var y = time.getFullYear();
                    var m = time.getMonth()+1;
                    var d = time.getDate();
                    var h = time.getHours();
                    var mm = time.getMinutes();
                    var s = time.getSeconds();
                    var reply = '<li class="one-reply">'
                        +'<a href="/UserCenterServlet?uid='+data[i].replyList[j].replySenderUid+'"><img src="../img/headIcon.jpg" /></a>'
                        +'<div class="one-reply-body">'
                        +'<a href="/UserCenterServlet?uid='+data[i].replyList[j].replySenderUid+'" class="reply-user">'+data[i].replyList[j].replySenderName+'</a>:'
                        +'<span class="reply-content">'+data[i].replyList[j].replyContent+'</span>'
                        +'<div class="one-reply-body-b">'
                        +'<span class="reply-time">'+y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s)+'</span>'
                        +'<a id="replybtn'+data[i].replyList[j].replySenderUid+'" onclick="changereply(\''+data[i].replyList[j].replySenderName+'\','+data[i].replyList[j].replySenderUid+','+data[i].askId+')">回复</a>'
                        +'</div>'
                        +'</div>'
                        +'</li>';
                    var x = "reply-body-"+data[i].askId;
                    $('#'+x).children().first().append(reply);
                }

            }
            $.getScript('../uikit/js/uikit.min.js',function() {
            });
            $.getScript('../uikit/js/components/notify.js',function() {
            });
        },
        error: function(jqXHR) {
            alert("发生错误：" + jqXHR.status);
        },
    });
}
function sendComment() {
    $.ajax({
        type:"post",
        url:"/api/comment_setter",
        data: $('#comment-form').serialize(),
        async:true,
        success: function(data) {
            console.log("send comment succeed.");
            commentajax();
        },
        error: function(jqXHR) {
        }
    });
}

function sendAsk() {
    $.ajax({
        type:"post",
        url:"/api/ask_setter",
        data: $('#ask-form').serialize(),
        async:true,
        success: function(data) {
            console.log("send ask succeed.");
            problemajax();
        },
        error: function(jqXHR) {
        }
    });
}

function sendReply(targetuid,askid) {
    $.ajax({
        type:"post",
        url:"/api/reply_setter",
        async:true,
        data: {
            toolId: getQueryString("tid"),
            targetUid: targetuid,
            askId: askid,
            content: $("#reply"+askid+"-content").val()
        },
        success: function (data){
            problemajax();
        }
    });
}

function changereply(sendname,uid,askid) {
    $("#reply"+askid+"-content").val("@"+sendname+" ");
    $('#ask'+askid).attr('onclick','sendReply('+uid+','+askid+')');
}

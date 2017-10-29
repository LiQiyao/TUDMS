$(document).ready(function() {
    $('#query-button').on('click', function() {
        if($('#query-text').val() == '') {
            alert('请输入用户姓名！');
        } else {
            $.ajax({
                type: "post",
                url: "/api/query_uid_by_username",
                data: {
                    username: $('#query-text').val()
                },
                dataType: "json",
                async: true,

                success: function(data) {
                    $('#query-result').text(data);
                },
                error: function(jqXHR) {
                    alert("发生错误：" + jqXHR.status);
                }
            });
        }
    });

    var selectAll = $("#selectAll");
    var deleteTool = $(".deleteTool");
    selectAll.change(function(){
        var state = selectAll.prop("checked");
        for(i=0;i<deleteTool.length;i++){
            var parent = $($($(deleteTool[i]).parent()).parent());
            if(parent.attr("aria-hidden") == "false"){
                $(deleteTool[i]).prop("checked",state);
            }
        }
    });

    var toolFilter = $($("#toolFilter").find("li"));
    toolFilter.each(function(){
        $(this).click(function(){
            setTimeout(function(){
                var flag = false;
                var parent = $($("#toolBox").find("tr"));
                var checkbox = $($("#toolBox").find("input"));
                for(i=0;i<parent.length;i++){
                    if($(parent[i]).attr("aria-hidden") == "false" && $(checkbox[i]).prop("checked") == false){
                        selectAll.prop("checked",false);
                        flag = true;
                    }
                }
                if(flag == false){
                    selectAll.prop("checked",true);
                }
            },100);
        });
    });

    var parent = $($("#toolBox").find("tr"));
    var checkbox = $($("#toolBox").find("input"));

    deleteTool.each(function(){
        $(this).click(function(){
            var flag = false;
            for(i=0;i<parent.length;i++){
                if($(parent[i]).attr("aria-hidden") == "false" && $(checkbox[i]).prop("checked") == false){
                    selectAll.prop("checked",false);
                    flag = true;
                }
            }
            if(flag == false){
                selectAll.prop("checked",true);
            }
        });
    });

    var deleteButton = $("#deleteButton");
    var deleteBody = $("#deleteBody");
    var toolName = $(".toolName");
    var uploaderName = $(".uploaderName");
    var classified = $(".classified");
    var nowVersion = $(".nowVersion");
    deleteButton.click(function(){
        var preTr = deleteBody.find("tr");
        for(i=0;i<preTr.length;i++){
            $(preTr[i]).remove();
        }
        for(i=0;i<parent.length;i++){
            if($(checkbox[i]).prop("checked") == true){
                var tr = $("<tr></tr>");
                var td1 = $("<td></td>").text($(toolName[i]).text());
                var td2 = $("<td></td>").text($(uploaderName[i]).text());
                var td3 = $("<td></td>").text($(classified[i]).text());
                var td4 = $("<td></td>").text($(nowVersion[i]).text());
                tr.append(td1);
                tr.append(td2);
                tr.append(td3);
                tr.append(td4);
                deleteBody.append(tr);
            }
        }
    });
});

function stopBubble(event){
    if(event&&event.stopPropagation){
        event.stopPropagation();
    }else{
        window.event.cancelBubble = true;
    }
}
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/1
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="application/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <title>Title</title>
</head>
<body>
<form id="uploadForm" enctype="multipart/form-data" method="post" action="/upload">
    <input id="file" type="file" name="file"/>
    <input name="uploaderName" type="text"/>
    <input type="submit" value="提交"/>
    <button id="upload" type="button" onclick="fun();">upload</button>
</form>
<script type="application/javascript">
    function fun() {
        $.ajax({
            url: '/upload',
            type: 'POST',
            cache: false,
            data: new FormData($('#uploadForm')[0]),
            processData: false,
            contentType: false
        }).done(function(res) {
        }).fail(function(res) {});
    }

</script>
</body>
</html>

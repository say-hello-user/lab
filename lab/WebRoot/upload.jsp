<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>实验室赛事发布系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="./libfolder/js/bootstrap.min.css" rel="stylesheet" /> 
  <script src="./libfolder/js/jquery.min.js"></script> 
  <script src="./libfolder/js/bootstrap.min.js"></script> 
  <link href="./libfolder/fonts/summernote.css" rel="stylesheet" /> 
  <link href="./libfolder/js/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen" /> 
  <script src="./libfolder/js/summernote.js"></script>
  <style type="text/css">
      .mg-20{margin-top: 20px;}
  </style>
</head>

<body>

<div class="container"> 
   <div class="row"> 
    <div class="col-md-12" style="height: 50px; background-color: rgb(60,87,202); text-align: center; color: white; font-weight: 900; line-height: 50px; font-size: 20px;">实验室赛事发布系统</div> 
    <div class="col-md-2 mg-20"></div> 
    <div class="col-md-8 mg-20"> 
     <form class="form-horizontal" action="${pageContext.request.contextPath}/servlet/UploadHandleServlet"
    enctype="multipart/form-data" method="post"> 
      <div class="form-group"> 
       <label for="title" class="col-sm-2 control-label">赛事标题</label> 
       <div class="col-sm-10"> 
        <input name="title" type="text" class="form-control" id="title" placeholder="请输入赛事标题" />
       </div> 
      </div> 
      <div class="form-group"> 
       <label class="col-sm-2 control-label">上传日期</label> 
       <div class="col-sm-10 controls input-append date form_datetime" data-date="1979-09-16T05:25:07Z" data-date-format="dd MM yyyy - HH:ii p" data-link-field="dtp_input1"> 
        <input size="16" id="date" type="text" value="请选择日期" readonly="" class="form-control" name="date" /> 
        <span class="add-on"><i class="icon-remove"></i></span> 
        <span class="add-on"><i class="icon-th"></i></span> 
       </div> 
       <input type="hidden" id="dtp_input1" value="" /> 
       <br /> 
      </div>
      <strong>请输入文章正文</strong>
      <input type="text" id="summernote" name="summernote">
  
      <div class="form-group">
       <label for="file1" class="col-sm-2 control-label">上传附件</label> 
       <div class="col-sm-10"> 
        <input type="file" name="file1" id="file1">
       </div> 
      </div> 
      <div class="form-group"> 
       <div class="col-sm-offset-2 col-sm-10"> 
        <button type="submit" onclick="test()" class="btn btn-default">发布</button> 
       </div> 
      </div> 
     </form> 
    </div> 
    <div class="col-md-2 mg-20"></div> 
   </div> 
  </div>
  <script type="text/javascript" src="./libfolder/js/bootstrap-datetimepicker.js" charset="UTF-8"></script> 
  <script type="text/javascript" src="./libfolder/js/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script> 
  <script type="text/javascript">
    $('.form_datetime').datetimepicker({
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    });
</script> 

  <script>
    $(document).ready(function() {
        $('#summernote').summernote({
          callbacks: {
                         onImageUpload: function(files) 
                         {
                           sendFile(files);
                         }
                     },
            height:300
        });
    });
    
    
    function sendFile(files){
    var formData = new FormData();
    for(f in files){
        formData.append("file", files[f]);
    }
    $.ajax({
        data: formData,
        type: "POST",
        url: "/lab/servlet/imageUploadServlet",
        cache: false,
        contentType: false,
        processData: false,
        success: function() {
            $('#summernote').summernote('editor.insertImage',"/lab/image/"+files[0].name);
          // $('#summernote').summernote('editor.insertImage',"E:\\myeclipsework2\\lab\\WebRoot\\image\\4.png");

        },
        error: function() {
            console.log("uploadError");
        }
    });
}
    function test()
    {
    	var summernote = document.getElementById('summernote');
    	console.log($('#summernote').summernote('code'));
    	summernote.value=$('#summernote').summernote('code');
    }
    $("form").on("submit",function(event){
    var title = $("#title").val();
            if(title=="")
                 {
                 alert("标题不能为空")
                 event.preventDefault();
                 }
        })
    
  </script> 
</body>
</html>

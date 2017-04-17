<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'listfile.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="./libfolder/js/bootstrap.min.css" rel="stylesheet" /> 
  </head>
  
  <body>

    <c:url value="/servlet/DownLoadServlet" var="downurl"> 
    <c:param name="filename" value="${mydata[\"filePath\"]}"></c:param> 
    </c:url>
    
    <div class="container"> 
   <div class="row"> 
    <div class="col-md-12" style="height: 50px; background-color: rgb(60,87,202); text-align: center; color: white; font-weight: 900; line-height: 50px; font-size: 20px;">实验室赛事发布系统</div> 
    <div class="col-md-2 mg-20"></div> 
    <div class="col-md-8 mg-20">
    		<h3 style="text-align:center;">${mydata["title"]}</h3>
    		<p align="right">${mydata["date"]}</p>
    		<div style="text-align:center;">${mydata["content"]} </div>		
            <% String name = (String)request.getAttribute("hasFile"); if(!name.equals("无上传相关文件")) {%>
                      <p style="font-size:20px; font-weight:900;">参赛数据文件:</p>
                      <a href="${downurl}" class="btn btn-default">点击下载</a> <br/>  
            <% }%>
    </div>  
     <div class="col-md-2 mg-20"></div> 
    </div>
    </div> 
  </body>
</html>

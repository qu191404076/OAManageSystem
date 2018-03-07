<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ajax.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <input type="text" name="name" id="name"/>
    <input type="password" name="password" id="password" />
    <input type="submit" value="提交" onclick="ajax()"/>
    <div id="div" style="border:1px solid black; width:40px; height:30px;"></div>
    
    <script src="js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript">
    	function ajax(){
    		$.ajax({
    			data:{'name':$("#name").val(),'password':$("#password").val()},
    			type:"POST",
    			dataType:'json',
    			url:"ajax/query.do",
    			error:function(data){
    				alert("出错!"+data.msg);
    			},
    			success:function(data){
    				var msg = eval(data);
    				alert(msg[0]);
    			}
    		});
    	}
    </script>
  </body>
</html>

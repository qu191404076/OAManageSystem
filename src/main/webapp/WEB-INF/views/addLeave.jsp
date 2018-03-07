<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addLeave.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/basic.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/dcalendar.picker.css">

  </head>
  
  <body style="background-image: none">
  	<div class="panel panel-info">
  		<div class="panel-heading">
    		<h3 class="panel-title">请假单</h3>
  		</div>
  		<div class="panel-body">
    		<div class="leaveForm">
    			<label>申请人:</label>
    			<input type="text" id="userName" name="userName" value="<%=session.getAttribute("loginName") %>" disabled="disabled" style="margin-left:14px;" /><br/>
    			<label>请假类型:</label>
    			<select name="reasonType" id="reasonType">
					<option value="事假" selected="selected">事假</option>
					<option value="病假">病假</option>
				</select><br>
    			<label>请假开始时间:</label>
    			<input type="text" id="leaveStart" name="leaveStart" /><br/>
    			<label>请假结束时间:</label>
    			<input type="text" id="leaveEnd" name="leaveEnd"/><br/>
    			<label for="cause">请假理由:</label>
    			<textarea class="form-control" rows="5" id="reason" name="reason"></textarea><br/>
    			<input type="submit" class="btn btn-primary" value="保存" style="float:right;" onclick="saveForm()"/>
    		</div>
  		</div>
	</div>
  
  
  	<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
  	<script type="text/javascript" src="js/bootstrap.min.js"></script>
  	<script type="text/javascript" src="js/dcalendar.picker.js"></script>
  	
  	<script type="text/javascript">
  		$("#leaveStart").dcalendarpicker({
  			format : 'yyyy-mm-dd'
  		});
  		
  		$("#leaveEnd").dcalendarpicker({
  			format : 'yyyy-mm-dd'
  		});
  		
  		function saveForm(){
  			$.ajax({
  				data : JSON.stringify({"userName" : $("#userName").val(),"reasonType" : $("#reasonType").val(),"leaveStart" : $("#leaveStart").val(),"leaveEnd" : $("#leaveEnd").val(),"reason" : $("#reason").val()}),
  				type : "POST",
  				contentType : "application/json;charset=UTF-8",
  				url : "leave/start.do",
  				success : function(){
  					alert("保存成功");
  				},
  				error : function(){
  					alert("保存失败");
  				}
  			});
  		}
  	</script>
  </body>
</html>

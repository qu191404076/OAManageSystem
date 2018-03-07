<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'queryTask.jsp' starting page</title>
    
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
    		<h3 class="panel-title">个人任务查询</h3>
  		</div>
  		<div class="panel-body">
    		<div class="queryLeave">
    			<table class="table table-hover">
    				<thead>
    					<tr>
    						<th>任务ID</th>
    						<th>任务名称</th>
    						<th>创建时间</th>
    						<th>办理人</th>
    						<th>操作</th>
    					</tr>
    				</thead>
    				<tbody>
    					<c:forEach items="${taskList }" var="taskList">
    						<tr>
    							<td><c:out value="${taskList.id }"></c:out></td>
    							<td><c:out value="${taskList.name }"></c:out></td>
    							<td><fmt:formatDate value="${taskList.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
    							<td><c:out value="${taskList.assignee }"></c:out></td>
    							<td>
    								<c:choose>
    									<c:when test="${taskList.name == '请假申请'}">
    										<button type="button" class="btn btn-info btn-sm" onclick="window.location='leave/continueFlow.do?taskId=${taskList.id}'">提交请假单</button>
    										<button type="button" class="btn btn-danger btn-sm" onclick="window.location='leave/deleteTask.do?taskId=${taskList.id}'">删除</button>
    									</c:when>
    									<c:otherwise>
    										<button type="button" class="btn btn-info btn-sm" onclick="window.location='leave/reviewLeaveOrder.do?taskId=${taskList.id}'">审核请假单</button>
    									</c:otherwise>
    								</c:choose>
    							</td>
    						</tr>
    					</c:forEach> 
    				</tbody>
    			</table>
    		</div>
  		</div>
	</div>
  
  
  	<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
  	<script type="text/javascript" src="js/bootstrap.min.js"></script>
  	<script type="text/javascript" src="js/dcalendar.picker.js"></script>
  	
  	<script type="text/javascript">
  		
  	</script>
  </body>
</html>

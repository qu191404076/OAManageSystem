<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link rel="stylesheet" type="text/css" href="css/basic.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/dcalendar.picker.css">

  </head>
  
  <body style="background-image: none">
  	<div class="panel panel-info">
  		<div class="panel-heading">
    		<h3 class="panel-title">查询请假单信息</h3>
  		</div>
  		<div class="panel-body">
    		<div class="queryLeave">
    			<table class="table table-hover" style="table-layout:fixed">
    				<thead>
    					<tr>
    						<th width="5%">ID</th>
    						<th width="10%">姓名</th>
    						<th width="12%">请假类型</th>
    						<th width="15%">请假开始时间</th>
    						<th width="15%">请假结束时间</th>
    						<th width="15%">请假理由</th>
    						<th width="13%">状态</th>
    						<th width="20%">操作</th>
    					</tr>
    				</thead>
    				<tbody>
    					<c:forEach items="${leaveMessage }" var="leaveMessage">
    						<tr>
    							<td><c:out value="${leaveMessage.id }"></c:out></td>
    							<td><c:out value="${leaveMessage.userName }"></c:out></td>
    							<td><c:out value="${leaveMessage.reasonType }"></c:out></td>
    							<td><c:out value="${leaveMessage.leaveStart }"></c:out></td>
    							<td><c:out value="${leaveMessage.leaveEnd }"></c:out></td>
    							<td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;"><c:out value="${leaveMessage.reason }"></c:out></td>
    							<td><c:out value="${leaveMessage.state }"></c:out></td>
    							<td>
    							<c:choose>
    								<c:when test="${leaveMessage.state =='未提交'}">
    									<button type="button" class="btn btn-info btn-sm" onclick="window.location='leave/submitLeaveMessage.do?orderId=${leaveMessage.id}'">确认请假单</button>
    									<button type="button" class="btn btn-danger btn-sm" onclick="window.location='leave/deleteLeaveOrder.do?id=${leaveMessage.id}'">删除</button>
    								</c:when>
    								<c:otherwise>
    									<button type="button" class="btn btn-info btn-sm" onclick="window.location='leave/queryHistoryCommentsByProposer.do?leaveId=${leaveMessage.id}'">查看批注信息</button>
    									
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

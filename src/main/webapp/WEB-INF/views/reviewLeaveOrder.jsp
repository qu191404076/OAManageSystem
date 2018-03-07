<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    			<input type="hidden" name="taskId" value="${taskId }" id="taskId"/>
    			<label>申请人:</label>
    			<input type="text" id="userName" name="userName" value="<c:out value="${reviewLeaveMessage.userName }"></c:out>" disabled="disabled" style="margin-left:14px;" /><br/>
    			<label>请假类型:</label>
    				<c:out value="${reviewLeaveMessage.reasonType }"></c:out><br>
    			<label>请假开始时间:</label>
    			<input type="text" id="leaveStart" name="leaveStart" value="<c:out value="${reviewLeaveMessage.leaveStart }"></c:out>" disabled="disabled"/><br/>
    			<label>请假结束时间:</label>
    			<input type="text" id="leaveEnd" name="leaveEnd" value="<c:out value="${reviewLeaveMessage.leaveEnd }"></c:out>" disabled="disabled"/><br/>
    			<label for="cause">请假理由:</label>
    			<textarea class="form-control" rows="5" id="reason" name="reason" disabled="disabled"><c:out value="${reviewLeaveMessage.reason }"></c:out></textarea>
    			<label for="postil">批注:</label>
    			<textarea class="form-control" rows="5" id="postil" name="postil"></textarea><br/>
    			<button class="btn btn-danger btn-sm" style="float:right; margin-left:10px;" onclick="rejectAndCom()">拒绝</button>
    			<button class="btn btn-primary btn-sm" style="float:right;" onclick="agreeAndCom()">同意</button>
    		</div>
  		</div>
  		<div class="panel-footer">
  			<div class="historyPostil">
  				<table class="table table-hover">
  					<thead>
  						<tr>
  							<th>审批人</th>
  							<th>审批时间</th>
  							<th>历史批注</th>
  						</tr>
  					</thead>
  					<tbody>
  						<c:forEach items="${historyComments }" var="historyComments">
  							<tr>
  								<td><c:out value="${historyComments.userId }"></c:out></td>
  								<td><fmt:formatDate value="${historyComments.time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
  								<td><c:out value="${historyComments.fullMessage }"></c:out></td>
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
  		function agreeAndCom(){
  			var postilMessage = {};
  			postilMessage['taskId'] = $("#taskId").val();
  			postilMessage['postil'] = $("#postil").val();
  			$.ajax({
  				data : JSON.stringify(postilMessage),
  				type : "POST",
  				contentType : "application/json;charset=UTF-8",
  				url : "leave/agreeApply.do",
  				success : function(){
  					alert("审批通过");
  				},
  				error : function(){
  					alert("审批同意操作失败");
  				}
  			});
  			
  		}
  		
  		function rejectAndCom(){
  			var postilMessage = {};
  			postilMessage['taskId'] = $("#taskId").val();
  			postilMessage['postil'] = $("#postil").val();
  			$.ajax({
  				data : JSON.stringify(postilMessage),
  				type : "POST",
  				contentType : "application/json;charset=UTF-8",
  				url : "leave/rejectApply.do",
  				success : function(){
  					alert("驳回成功");
  				},
  				error : function(){
  					alert("驳回失败");
  				}
  			});
  		}
  	</script>
  </body>
</html>

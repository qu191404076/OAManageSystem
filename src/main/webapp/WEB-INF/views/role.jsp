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
    
    <title>My JSP 'role.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">

  </head>
  
  <body>
  	<div class="panel panel-info">
		<div class="panel-heading">
			<h3 class="panel-title">角色列表</h3>
		</div>
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>角色编号</th>
							<th>角色名称</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${roleList }" var="roleList">
							<tr>
								<td><c:out value="${roleList.roleId }"></c:out></td>
								<td><c:out value="${roleList.roleName }"></c:out></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		
		
		
	</div>
  	
  	
    <script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
  </body>
</html>

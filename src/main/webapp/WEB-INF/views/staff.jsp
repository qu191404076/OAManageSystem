<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">

<title>My JSP 'staff.jsp' starting page</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
			<h3 class="panel-title">人员列表</h3>
		</div>
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>员工编号</th>
							<th>员工姓名</th>
							<th>性别</th>
							<th>电话</th>
							<th>电子邮箱</th>
							<th>部门</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${staffList}" var="staffList">
							<tr>
								<td><c:out value="${staffList.userId }"></c:out></td>
								<td><c:out value="${staffList.userName }"></c:out></td>
								<td><c:out value="${staffList.sex }"></c:out></td>
								<td><c:out value="${staffList.phone }"></c:out></td>
								<td><c:out value="${staffList.email }"></c:out></td>
								<td><c:out value="${staffList.deptName }"></c:out></td>
								<td>
									<button type="button" class="btn btn-danger btn-sm" onclick="window.location='staff/deleteStaff.do?userId=<c:out value="${staffList.userId }"/>'">删除</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		
		<ul class="pager">
			<li class="previous" id="lastPage"><a href="javascript:void(0);" onclick="previousPage()">&larr; 上一页</a></li>
			<li class="next" id="nextPage"><a href="javascript:void(0);" onclick="nextPage()">下一页 &rarr;</a></li>
		</ul>
	</div>
	

	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function nextPage(){
			var nowPage = <%=session.getAttribute("nowPage")%>;
			var sumPage = <%=session.getAttribute("sumPage")%>;
			if(sumPage == nowPage){
				alert("当前是尾页");
			}else{
				var page = parseInt(nowPage) + parseInt(1);
				window.location.href="staff/queryAllStaff.do?page="+page;
			}
			
		}
		
		function previousPage(){
			var nowPage = <%=session.getAttribute("nowPage") %>;
			if(nowPage <= 1){
				alert("当前是首页");
			}else{
				var page = parseInt(nowPage) - parseInt(1);
				window.location.href="staff/queryAllStaff.do?page="+page;
			}
		}
	</script>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">

<title>OA管理系统后台</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="css/basic.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">

</head>

<body style="background-image: none;">
	<div class="container-fluid" style="margin-bottom:50px;">
		<nav class="navbar navbar-inverse navbar-fixed-top">

		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
			<a class="navbar-brand" href="javascript:void(0);">OA Manage</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
            	<li id="fat-menu" class="dropdown">
              		<a id="drop3" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                		${loginName }
                	<span class="caret"></span>
              		</a>
              		<ul class="dropdown-menu" aria-labelledby="drop3">
                		<li><a href="#" data-toggle="modal" data-target="#modifyPwd">修改密码</a></li>
                		<li role="separator" class="divider"></li>
                		<li><a href="<c:url value="/logout"></c:url>">注销</a></li>
              		</ul>
            	</li>
          	</ul>
		</div>

		</nav>
	</div>

	<div class="container-fluid">
		<div class="row clearfix">
			<div class="col-md-2 column" style="height:100%;">
				<div class="panel-group" id="accordion" role="tablist"
					aria-multiselectable="true">
					<div class="panel panel-info">
						<div class="panel-heading" role="tab" id="headingOne">
							<h4 class="panel-title">
								<a role="button" data-toggle="collapse" data-parent="#accordion"
									href="#staffManage" aria-expanded="true"
									aria-controls="collapseOne"> 人员管理 </a>
							</h4>
						</div>
						<div id="staffManage" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingOne">
							<ul class="list-group">
								<li class="list-group-item"><a href="staff/queryAllStaff.do" target="frames">人员列表</a></li>
								<li class="list-group-item"><a href="#" data-toggle="modal"
									data-target="#addStaff">添加人员</a></li>
							</ul>
						</div>
					</div>
					<div class="panel panel-info">
						<div class="panel-heading" role="tab" id="headingTwo">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion" href="#departmentManage"
									aria-expanded="false" aria-controls="collapseTwo"> 部门管理 </a>
							</h4>
						</div>
						<div id="departmentManage" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingTwo">
							<ul class="list-group">
								<li class="list-group-item"><a href="#" data-toggle="modal"
									data-target="#addDepartment">添加部门</a></li>
								<li class="list-group-item"><a href="dept/queryAllDept.do" target="frames">部门列表</a></li>
							</ul>
						</div>
					</div>
					<div class="panel panel-info">
						<div class="panel-heading" role="tab" id="headingThree">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion" href="#roleManage"
									aria-expanded="false" aria-controls="collapseThree"> 角色管理 </a>
							</h4>
						</div>
						<div id="roleManage" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingThree">
							<ul class="list-group">
								<li class="list-group-item"><a href="#" data-toggle="modal"
									data-target="#addRole">添加角色</a></li>
								<li class="list-group-item"><a href="role/queryAllRole.do" target="frames">角色列表</a></li>
							</ul>
						</div>
					</div>
					<div class="panel panel-info">
						<div class="panel-heading" role="tab" id="headingThree">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion" href="#notice"
									aria-expanded="false" aria-controls="collapseThree"> 公告 </a>
							</h4>
						</div>
						<div id="notice" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingThree">
							<ul class="list-group">
								<li class="list-group-item"><a href="#" data-toggle="modal"
									data-target="#addNotice">填写公告</a></li>
								<li class="list-group-item"><a href="javascript(0);">删除公告</a></li>
							</ul>
						</div>
					</div>
					<div class="panel panel-info">
						<div class="panel-heading" role="tab" id="headingThree">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion" href="#leave"
									aria-expanded="false" aria-controls="collapseThree"> 请假管理 </a>
							</h4>
						</div>
						<div id="leave" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="headingThree">
							<ul class="list-group">
								<li class="list-group-item"><a href="leave/addLeave.do" target="frames">填写请假单</a></li>
								<li class="list-group-item"><a href="leave/queryLeaveMessage.do" target="frames">查询请假单信息</a></li>
								<li class="list-group-item"><a href="leave/queryTask.do" target="frames">个人任务查询</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-10 column" style="height:100%">
				<iframe src="notice.jsp" name="frames" class="iframe"></iframe>
			</div>
		</div>
	</div>

	
	<!-- 添加人员弹框 -->
	
		<div class="modal fade bs-example-modal-lg" id="addStaff"
			tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">添加人员</h3>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-hover" id="addStaff">
									<thead>
										<tr>
											<th width="5%">员工姓名</th>
											<th width="5%">登录名</th>
											<th width="5%">登录密码</th>
											<th width="5%">性别</th>
											<th width="5%">电话</th>
											<th width="5%">电子邮箱</th>
											<th width="25%">部门</th>
											<th width="25%">角色</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><input type="text" name="userName" id="userName"/></td>
											<td><input type="text" name="loginName" id="loginName"/></td>
											<td><input type="password" name="loginPassword" id="loginPassword"/></td>
											<td><input type="text" name="sex" id="sex"/></td>
											<td><input type="number" name="phone" id="phone"/></td>
											<td><input type="email" name="email" id="email"/></td>
											<td>
												<select class="form-control" name="deptName" style="hegiht:20px;" onclick="showAllDept()" id="deptOption">
													<option value="请选择" selected="selected" class="oselect">请选择</option>
												</select>
											</td>
											<td>
												<select class="form-control" name="roleName" style="hegiht:20px;" id="addStaffRole" onclick="showAllRoleName()">
													<option value="请选择" selected="selected" class="oselect">请选择</option>
												</select>
											</td>
											<td>
												<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="modal-footer">
							<input type="submit" value="添加" class="btn btn-primary" onclick="md5(),addStaff()"/>
							<button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	

	<!-- 添加部门弹框 -->
	<form action="javascript:void(0);" method="post">
		<div class="modal fade bs-example-modal-lg" id="addDepartment"
			tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
			<div class="modal-dialog modal-sm" role="document">
				<div class="modal-content">
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">添加部门</h3>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-hover">
									<thead>
										<tr>
											<th>部门名称</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><input type="text" name="deptName" id="deptName"/></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="modal-footer">
							<input type="submit" value="添加" class="btn btn-primary" onclick="addDept()"/>
							<button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>

	<!-- 添加角色弹框 -->
		<div class="modal fade bs-example-modal-sm" id="addRole" tabindex="-1"
			role="dialog" aria-labelledby="myLargeModalLabel">
			<div class="modal-dialog modal-sm" role="document">
				<div class="modal-content">
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">添加角色</h3>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-hover">
									<thead>
										<tr>
											<th>角色名称</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><input type="text" name="roleName" id="roleName"/></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="modal-footer">
							<input type="submit" value="添加" class="btn btn-primary" onclick="addRole()"/>
							<button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	
	<!-- 修改密码弹框 -->
	
		<div class="modal fade bs-example-modal-sm" id="modifyPwd" tabindex="-1"
			role="dialog" aria-labelledby="myLargeModalLabel">
			<div class="modal-dialog modal-sm" role="document">
				<div class="modal-content">
					<div class="panel panel-info">
					<form action="staff/modifyPwd.do" method="post">
						<div class="panel-heading">
							<h3 class="panel-title">修改密码</h3>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-hover">
									<thead>
										<tr>
											<th>新密码</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><input type="password" name="loginPassword" id="mlPassword"/></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="modal-footer">
							<input type="submit" value="修改" class="btn btn-primary" onclick="md5()"/>
							<button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
						</div>
					</form>
					</div>
				</div>
			</div>
		</div>
		
		
	<!-- 填写公告弹框 -->
	<div class="modal fade bs-example-modal-lg" id="addNotice"
			tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">填写公告</h3>
						</div>
						<div class="panel-body" style="height:600px;">
							<div>
							<script id="container" name="content" type="text/plain" style="height:450px;">
        						这里写你的初始化内容
    						</script>
    						</div>
						</div>
						<div class="modal-footer">
							<input type="submit" value="添加" class="btn btn-primary" onclick="addNotice()"/>
							<button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	
	
	
	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.md5.js"></script>
	<!-- 配置文件 -->
    <script type="text/javascript" src="js/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="js/ueditor.all.min.js"></script>
	<script type="text/javascript">
		var ue;
		var deptSelect = true;
		var roleSelect = true;
		$(function(){
			ue = UE.getEditor('container',{
				autoClearinitialContent : true,
				autoHeightEnabled: false,
			});
		});
	
		function md5(){
			var pwd = document.getElementById("loginPassword").value;
			var pwdMd5 = $.md5(pwd);
			document.getElementById("loginPassword").value = pwdMd5;
			
			var pwd = document.getElementById("mlPassword").value;
			var pwdMd5 = $.md5(pwd);
			document.getElementById("mlPassword").value = pwdMd5;
		}
		
		function addDept(){
			$.ajax({
				data:{'deptName':$("#deptName").val()},
				type:'POST',
				url:"dept/addDept.do",
				dataType:'text',
				error:function(data){
                    alert("添加部门失败");
				},
				success:function(data){
					alert("添加部门成功");
				}
			});
		}
		
		function addNotice(){
			if(UE.getEditor('container').hasContents()){
				$.ajax({
					data : {'notice' : ue.getContent()},
					type : 'POST',
					url : "notice/addNotice.do",
					dataType : 'text',
					error : function(){
						alert("添加公告失败");
					},
					success : function(data){
						alert("添加公告成功");
					}
				});
			}else{
				alert("内容为空!");
			}
		}
		
		function showAllDept(){
			if(deptSelect == true){
				$.ajax({
					url:'dept/allDept.do',
					type:'POST',
					dataType:'json',
					success:function(data){
						$.each(data,function(index,item){
							$("#deptOption").append("<option value="+item.deptName+">"+item.deptName+"</option>");
						});
					},
					error:function(){
						alert("获取部门列表失败");
					}
				});
				deptSelect = false;
			}
		}
		
		function showAllRoleName(){
			if(roleSelect == true){
				$.ajax({
					dataType : 'json',
					url : 'role/queryRoleByAddStaff.do',
					type : 'POST',
					success : function(data){
						$.each(data,function(index,item){
							$("#addStaffRole").append("<option value=" + item.roleName + ">" + item.roleName + "</option>");
						});
					},
					error : function(data){
						alert("获取角色列表失败");
					}
				});
				roleSelect = false;
			}
			
		}
		
		function addRole(){
			$.ajax({
				data : {'roleName' : $("#roleName").val()},
				type : 'POST',
				url : 'role/addRole.do',
				success : function(){
					alert("添加角色成功");
				},
				error : function(){
					alert("添加角色失败");
				}
			});
		}
		
		function addStaff(){
			$.ajax({
				data : JSON.stringify({'userName' : $("#userName").val(),'loginName' : $("#loginName").val(),'loginPassword' : $("#loginPassword").val(),'sex' : $("#sex").val(),'phone' : $("#phone").val(),'email' : $("#email").val(),'deptName' : $("#deptOption").val(),'roleName' : $("#addStaffRole").val()}),
				type : "POST",
				contentType : "application/json;charset=UTF-8",
				url : "staff/addStaff.do",
				success : function(){
					alert("添加成功");
				},
				error : function(){
					alert("添加失败");
				}
			});
		}
	</script>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">

<title>My JSP 'test.jsp' starting page</title>

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
<link rel="stylesheet" type="text/css" href="css/normalize.css" />
<link rel="stylesheet" type="text/css" href="css/default.css">
<link href="assets/css/style.css" rel="stylesheet" />
<link href="assets/css/perspectiveRules.css" rel="stylesheet" />
</head>

<body>
	<div id="demo1" class="container"
		style="width: 100%; height: 100%; padding: 0; position: absolute; z-index: 50;">
		<img alt="background" src="assets/images/background.jpg"
			style="width: 100%; height: 100%;" />
		<div id="particle-target"></div>
	</div>

	<div id="start" class="start">
		<h2 style="margin: 0; padding: 0;">欢迎使用OA管理系统</h2>
		<p style="margin-top: 10px;">
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#modal">登陆</button>
		</p>
	</div>

	<!-- 登陆弹出框 -->
	<div class="modal fade" tabindex="-1" role="dialog" id="modal">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">登录</h4>
				</div>
				<div class="modal-body">
					<div id="login" class="login">
						<form class="form-signin" action="login" method="post">
							<sec:csrfInput />
							<div class="form-group">
								<label for="loginName">用户名</label> <input type="text"
									id="inputEmail" name="username" class="form-control"
									placeholder="用户名" required="" autofocus="autofocus" />
							</div>
							<div class="form-group">
								<label for="loginPassword">密码</label> <input type="password"
									id="inputPassword" name="password" class="form-control"
									placeholder="密码" required="">
							</div>
							<div class="checkbox">
								<label> <input type="checkbox" name="remember-me"
									checked="checked"> 记住我
								</label>
							</div>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<button class="btn btn-primary btn-block" type="submit"
								onclick="md5()">登录</button>

						</form>
					</div>

				</div>
			</div>

		</div>
	</div>



	<script src="js/jquery-2.1.4.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/jquery.md5.js"></script>
	<script src="assets/js/jquery.logosDistort.min.js"></script>
	<script src="assets/js/jquery.particleground.min.js"></script>
	<script>
	    var particles = true,
	        particleDensity,
	        options = {
	            effectWeight: 1,
	            outerBuffer: 1.08,
	            elementDepth: 220
	        };

	    $(document).ready(function() {

	        $("#demo1").logosDistort(options);

	        if (particles) {
	            particleDensity = window.outerWidth * 7.5;
	            if (particleDensity < 13000) {
	                particleDensity = 13000;
	            } else if (particleDensity > 20000) {
	                particleDensity = 20000;
	            }
	            return $('#particle-target').particleground({
	                dotColor: '#1ec5ee',
	                lineColor: '#0a4e90',
	                density: particleDensity.toFixed(0),
	                parallax: false
	            });
	        }
	    });
	    
	    function md5(){
			var pwd = document.getElementById("inputPassword").value;
			var pwdMd5 = $.md5(pwd);
			document.getElementById("inputPassword").value = pwdMd5;
			
		}
	    
	    
	    $(function(){
		    if(window != top){
		    	top.location.href = "loginPage.do";
		    }
		});
	</script>
</body>
</html>

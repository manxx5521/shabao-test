<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../context/head.jsp"%>
<title>沙包娱乐 - 登录</title>
<cs:resource type="css" value="jquery,bootstrap,system" />
<meta name="keywords" content="沙包娱乐后台管理系统">
<meta name="description" content="沙包娱乐后台管理系统是一个微信后台管理系统">
<link href="${ctx}/resources/system/login/login.min.css" rel="stylesheet">
<!--[if lt IE 8]>
    <meta http-equiv="refresh" content="0;ie.html" />
<![endif]-->
<script>
	if (window.top !== window.self) {
		window.top.location = window.location
	};
</script>
</head>
<body class="signin">
	<div class="signinpanel">
		<div class="row">
			<div class="col-sm-7">
				<div class="signin-info">
					<div class="logopanel m-b">
						<h1>[ 小沙包，大梦想 ]</h1>
					</div>
					<div class="m-b"></div>
					<h4>
						欢迎使用 <strong>微信后台管理系统</strong>
					</h4>
					<ul class="m-b">
						<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势一</li>
						<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势二</li>
						<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势三</li>
						<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势四</li>
						<li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势五</li>
					</ul>
					<strong>还没有账号？ <a href="#">立即注册&raquo;</a></strong>
				</div>
			</div>
			<div class="col-sm-5">
				<form method="post" name="loginform" id="loginform">
					<h4 class="no-margins">登录：</h4>
					<p class="m-t-md">登录到H+后台主题UI框架</p>
					<input name="user_id" type="text" class="form-control uname"
						placeholder="用户名" /> <input name="password" type="password"
						class="form-control pword m-b" placeholder="密码" /> <a href="">忘记密码了？</a>
					<button class="btn btn-success btn-block" type="button">登录</button>
				</form>
			</div>
		</div>
		<div class="signup-footer">
			<div class="pull-left">&copy; 2015 All Rights Reserved. ShaBao
			</div>
		</div>
	</div>
	<cs:resource type="js" value="jquery,bootstrap,system" />
	<script src="${ctx}/resources/system/login/login.js"></script>
</body>
</html>
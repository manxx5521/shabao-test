<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="../../context/head.jsp"%>
<title>主页1</title>
<cbox:resource type="css" value="jquery,bootstrap" />
</head>
<body>
	<!-- 页面显示部分 -->
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h1>主页1</h1>
			</div>
			<div class="panel-body">
			<!-- 如果是不同于域的情况，要写全路径，有专门的服务做验证 -->
				<form action="./doLogin" method="POST">
					<div class="form-group">
						<label for="user">Email address</label> <input
							type="text" class="user" id="user" name="userId"
							placeholder="用户名">
					</div>
					<div class="form-group">
						<label for="password">Password</label> <input
							type="password" class="form-control" id="password" name="password"
							placeholder="密码">
					</div>
					<input type="hidden" name="gotoUrl" class="form-control" name="url" value="${gotoUrl}">
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
		</div>
	</div>
	<cbox:resource type="js" value="jquery,bootstrap" />
	<script src="${ctx}/resources/plugins/jquery/jquery.cookie.min.js"></script>
	<script type="text/javascript">
		$(function() {

		});
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="../../context/head.jsp"%>
<title>主页</title>
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
				<h1>登录成功</h1>
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
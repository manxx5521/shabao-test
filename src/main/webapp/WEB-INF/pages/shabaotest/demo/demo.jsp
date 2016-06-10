<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <%@include file="../../context/head.jsp"%>
    <title>demo</title>
     <cs:resource type="css" value="jquery,bootstrap" />
  </head>
  <body>
  	<!-- 页面显示部分 -->
  	<div class="container">
  		<div class="panel panel-default">
  			<div class="panel-heading">
  				<h1>demo</h1>
  			</div>
  			<div class="panel-body">
   		 		页面跳转成功,
   		 		输出时间 ${date}
  			</div>
		</div>
	</div>
	<cs:resource type="js" value="jquery,bootstrap" />
  </body>
</html>
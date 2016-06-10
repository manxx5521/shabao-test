<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <%@include file="../../context/head.jsp"%>
    <title>秒杀列表</title>
     <cs:resource type="css" value="jquery,bootstrap" />
  </head>
  <body>
  	<!-- 页面显示部分 -->
  	<div class="container">
  		<div class="panel panel-default">
  			<div class="panel-heading">
    			<h3 class="panel-title">秒杀列表</h3>
  			</div>
  			<div class="panel-body">
   		 		<table class="table table-hover">
   		 			<thead>
   		 				<tr>
   		 					<th>名称</th>
   		 					<th>库存</th>
   		 					<th>开始时间</th>
   		 					<th>结束时间</th>
   		 					<th>创建时间</th>
   		 					<th>详情</th>
   		 				</tr>
   		 			</thead>
   		 			<tbody>
   		 				<c:forEach var="r" items="${list}">
						<tr>
							<td>${r.name}</td>
							<td>${r.number}</td>
							<td><fmt:formatDate value="${r.startTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><fmt:formatDate value="${r.endTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><fmt:formatDate value="${r.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><a class="btn btn-info" href="${ctx}/seckill/${r.seckillId}/detail" target="_blank">link</a></td>
						</tr>   		 				
   		 				</c:forEach>
   		 			</tbody>
   		 		</table>
  			</div>
		</div>
	</div>
	<cs:resource type="js" value="jquery,bootstrap" />
  </body>
</html>
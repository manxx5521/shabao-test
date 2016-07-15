<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"
	name="viewport">
<!-- Mobile Devices Support @begin -->
<meta content="no-cache,must-revalidate" http-equiv="Cache-Control">
<meta content="no-cache" http-equiv="pragma">
<meta content="0" http-equiv="expires">
<meta content="telephone=no, address=no" name="format-detection">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta name="apple-mobile-web-app-capable" content="yes">
<!-- apple devices fullscreen -->
<meta name="apple-mobile-web-app-status-bar-style"
	content="black-translucent">
<title>数据排行</title>
<%@include file="../../../context/head.jsp"%>
<link rel="stylesheet" href="${ctx}/resources/plugins/bootstrap/css/bootstrap.min.css">
<cs:resource type="all" value="swiper" />
</head>
<body style="padding: 0">
	<h5 style="padding: 0 10px;">${data.voteName} 排行榜/当前总票数:${data.voteNum}</h5>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>排行</th>
				<th style="text-align: center">名称</th>
				<th>票数</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="r" items="${data.list}" varStatus="idx">
			<tr>
				<td>${idx.index+1}</td>
				<td style="text-align: center"><span
					style="font-size: .8em; line-height: 1em">${r.playerNum}号 ${r.playerName}</span></td>
				<td>${r.voteNum}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<br />
</body>
</html>
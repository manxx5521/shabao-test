<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <%@include file="../../context/head.jsp"%>
    <title>秒杀详情</title>
     <cs:resource type="css" value="jquery,bootstrap" />
  </head>
  <body>
  	<!-- 页面显示部分 -->
  	<div class="container">
  		<div class="panel panel-default">
  			<div class="panel-heading">
  				<h1>${seckill.name}</h1>
  			</div>
  			<div class="panel-body">
   		 		<h2 class="text-danger">
   		 			<!-- 显示时间图标 -->
   		 			<span class="glyphicon glyphicon-time"></span>
   		 			<!-- 展现倒计时 -->
   		 			<span class="glyphicon" id="seckill-box"></span>
   		 		</h2>
  			</div>
		</div>
	</div>
	<!-- model使用 -->
	<div id="killPhoneModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title text-center">
						<span class="glyphicon glyphicon-phone"></span>秒杀电话：
					</h3>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-xs-8 col-xs-offset-2">
							<input type="text" name="killPhone" id="killPhoneKey" 
							placeholder="填写手机号" class="form-control"/>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<!-- 显示错误验证信息 -->
					<span id="killPhoneMessage" class="glyphicon"></span>
					<button type="button" id="killPhoneBtn" class="btn btn-success">
						<span class="glyphicon glyphicon-phone"></span>
						提交
					</button>
				</div>
			</div>
		</div>
	</div>
	<cs:resource type="js" value="jquery,bootstrap" />
	<script src="${ctx}/resources/plugins/jquery/jquery.cookie.min.js"></script>
	<script src="${ctx}/resources/plugins/jquery/jquery.countdown.min.js"></script>
	<!-- 当前页关联JS -->
	<script src="${ctx}/resources/js/seckill/seckill.js"></script>
	<script type="text/javascript">
		$(function(){
			//传递参数
			seckill.detail.init({
				seckillId: '${seckill.seckillId}',
				startTime:'${seckill.startTime.time}', //.time 是取毫秒 时间戳
				endTime:'${seckill.endTime.time}'
			});
		});
	</script>
  </body>
</html>
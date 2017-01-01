<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>${data.bargainName}</title>
<%@include file="../../context/head.jsp"%>
<cbox:resource type="all" value="jquery,jweixin,weui" />
<%@include file="../common.jsp"%>
</head>
<body>
	<div class="weui_msg" style="display:none;">
		<div class="weui_icon_area">
			<i class="weui_icon_success weui_icon_msg"></i>
		</div>
		<div class="weui_text_area">
			<h2 class="weui_msg_title">兑换成功</h2>
			<p class="weui_msg_desc">帐号已经成功出奖</p>
		</div>
		<div class="weui_opr_area">
			<p class="weui_btn_area">
				<a href="#" class="weui_btn weui_btn_primary">确定</a>
			</p>
		</div>
	</div>
<!--微信分享-->
<script type="text/javascript">
	wx.ready(function() {
		wx.scanQRCode({
		    needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
		    scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
		    success: function (res) {
		    var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
		    alert(result)
		    alert(result.scene_id)
			}
		});
	});
	wx.error(function(res) {
		//alert(res.errMsg); 
	});
</script>
<!--END 微信分享-->
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>${data.bargainName}</title>
<%@include file="../../../context/head.jsp"%>
<cs:resource type="all" value="jquery,swiper,leanModal,jweixin,weui" />
<%@include file="../../common.jsp"%>
<link href="${ctx}/resources/wechat/bargain/one/base.css" rel="stylesheet">
<link href="${ctx}/resources/wechat/bargain/one/index.css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/resources/wechat/bargain/one/index.js"></script>
</head>
<body>
	<div class="list" style="text-align:left">
		<div class="list_title" style="text-align: center">
			<div class="go_top" onclick="location.href='${ctx}/wechat/bargain/${data.bargainId}/bargain' ">返回</div>
			${data.bargainName}
		</div>
		<p><c:out value="${data.goods}" escapeXml="false" /></p>
	</div>
	<div class="mfooter" id="wxgjfooter" style="text-align: center; width: 100%; height: 20px; line-height: 20px; margin-top: 10px;">
		<span class="sp2">
			<a href="#" style="color: #5e5e5e; font-size: 12px;">
				技术支持
			</a>
		</span>
	</div>
<!--微信分享-->
<script type="text/javascript">
	wx.ready(function() {
		var shareData = {
			title : '${data.bargainName}',
			desc : '${data.des}',
			link : '${jsParams.url}',
			imgUrl : ''
		};
		wx.onMenuShareAppMessage(shareData);
		wx.onMenuShareTimeline(shareData);
		wx.onMenuShareQQ(shareData);
		wx.onMenuShareWeibo(shareData);
	});
	wx.error(function(res) {
		//alert(res.errMsg); 
	});
</script>
<!--END 微信分享-->
</body>
</html>
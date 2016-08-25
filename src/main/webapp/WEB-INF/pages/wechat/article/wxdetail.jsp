<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
<%@include file="../../context/head.jsp"%>
<cs:resource type="all" value="jquery,jweixin,weui" />
<%@include file="../common.jsp"%>
<title>${title}</title>
</head>
<body>
<div class="container" id="container"></div>
<div class="bd">
    <article class="weui_article">
        <h1>${data.news.title}</h1>
        <section>
            <section>
                <p><c:out value="${data.news.content}" escapeXml="false" /></p>
            </section>
        </section>
    </article>
</div>
<!--微信分享-->
<script>
	wx.ready(function() {
		var shareData = {
			title : '${data.news.title}',
			desc : '${data.news.digest}',
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
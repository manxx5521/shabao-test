<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<meta charset="UTF-8">
<title>我要报名</title>
<meta name="description" content="我要报名">
<%@include file="../../../context/head.jsp"%>
<link rel="stylesheet" href="${ctx}/resources/wechat/vote/blue/touch.css">
<link rel="stylesheet" href="${ctx}/resources/wechat/vote/blue/colorbox.css">
<cs:resource type="all" value="jquery,colorbox,masonry,swiper" />
<script src="${ctx}/resources/wechat/vote/blue/app.js" type="text/javascript"></script>
<!-- 
<script src="${ctx}/resources/js/jquery/jquery.artDialog/jquery.artDialog.js?skin=default"></script>
<script src="${ctx}/resources/js/iframeTools.js"></script>
<script src="${ctx}/resources/wechat/vote/blue/upyun.js?2013"></script>
 -->
 <!--微信分享-->
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
	wx.config({
		debug : false,
		appId : 'wxf022fc156026a511',
		timestamp : 1456969706,
		nonceStr : 'NNLd2DBnABffqxe9',
		signature : '4220e79a00df947f37734f6fdc5c461f0b1b2970',
		jsApiList : [ 'checkJsApi', 'onMenuShareTimeline',
				'onMenuShareAppMessage', 'onMenuShareQQ', 'onMenuShareWeibo',
				'hideMenuItems', 'showMenuItems', 'hideAllNonBaseMenuItem',
				'showAllNonBaseMenuItem', 'translateVoice', 'startRecord',
				'stopRecord', 'onRecordEnd', 'playVoice', 'pauseVoice',
				'stopVoice', 'uploadVoice', 'downloadVoice', 'chooseImage',
				'previewImage', 'uploadImage', 'downloadImage',
				'getNetworkType', 'openLocation', 'getLocation',
				'hideOptionMenu', 'showOptionMenu', 'closeWindow',
				'scanQRCode', 'chooseWXPay', 'openProductSpecificView',
				'addCard', 'chooseCard', 'openCard' ]
	});
</script>
<script type="text/javascript">
	wx.ready(function() {
		var shareData = {
			title : '${data.voteName}',
			desc : '${data.des}',
			link : 'http://tp.lanrenmb.com/Home/index.php/Index/index/id/217021.html',
			imgUrl : 'http://tp.lanrenmb.com/Member//Public/upload/2/3/5/f/5625fc25f2823.png'
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
<style>
.slider {
	display: none;
}

.focus span {
	width: 5px;
	height: 5px;
	margin-left: 5px;
	border-radius: 50%;
	background: #CDCDCD;
	font-size: 0
}

.focus span.current {
	background: red;
}

.upImgClass {
	display: block;
}

.upImgClass1 {
	display: none;
}
</style>
</head>
<body>
	<header>
		<div class="form-group">
<style>
.swiper-container {
	width: 100%;
	max-height: 168px;
	color: #fff;
	text-align: center;
}

.pagination {
	position: absolute;
	z-index: 20;
	right: 10px;
}

.swiper-pagination-switch {
	display: inline-block;
	width: 5px;
	height: 5px;
	border-radius: 5px;
	background: #222;
	margin-right: 5px;
	opacity: 0.8;
	border: 1px solid #fff;
	cursor: pointer;
}

.swiper-visible-switch {
	background: #aaa;
}

.swiper-active-switch {
	background: #fff;
}

.imgplay {
	max-height: 200px;
	width: 100%
}
</style>
			<div id="content" style="max-width: 640px; margin: 0 auto;">
				<!--headpic-->
				<div class="swiper-container" style="display: block;">
					<div class="swiper-wrapper">
						<c:forEach var="r" items="${data.imgList}" varStatus="idx">
						<div class="swiper-slide">
							<a href="#"><img class="imgplay" src="${ctx}/resources/wechat/upload/${r}" /></a>
						</div>
						</c:forEach>
					</div>
				</div>
				<div class="pagination" style="margin-top: -10px;"></div>
			</div>
			<!-- Initialize Swiper -->
			<script>
				var swiper = new Swiper('.swiper-container', {
					pagination : '.pagination',
					paginationClickable : true,
					autoplay : 2500
				});
			</script>
		</div>
		<div class="m_head clearfix">
			<div class="num_box">
				<ul class="num_box_ul">
					<!--<li><span class="text">已报名</span><span></span></li><li><span class="text">投票人次</span><span></span></li><li><span class="text">访问量</span><span>18</span></li>-->
				</ul>
				<img src="${ctx}/resources/wechat/vote/blue/images/mw_004.jpg">
			</div>
		</div>
	</header>
	<div class="apply">
		<p>报名处</p>
		<div class="blank10"></div>
		<form id="imageform" method="post" enctype="multipart/form-data"
			action="/Home/index.php/Index/insertForm">
			<dl class="clearfix">
				<dt>姓名:</dt>
				<dd>
					<input type="text" class="input_txt" name="username" value="" placeholder="请输入姓名">
			</dl>
			<dl class="clearfix">
				<dt>联系电话:</dt>
				<dd>
					<input name="tel" type="number" value="" class="input_txt" placeholder="请输入您的真实手机号">
				</dd>
			</dl>
			<dl class="upload clearfix">
				<dt>上传照片</dt>
				<dd class="upload_area clearfix">
					<ul id="imglist" class="post_imglist"></ul>
					<div style="display: none;" id="h_num">0</div>
					<input type="hidden" id="picurl" name="picurl" value="">
					<input type="hidden" id="picurl1" name="picurl1" value="">
					<input type="hidden" id="picurl2" name="picurl2" value="">
					<input type="hidden" id="picurl3" name="picurl3" value="">
					<input type="hidden" id="picurl4" name="picurl4" value="">
					<input type="button" name="button" value=""
						style="background: url(${ctx}/resources/wechat/vote/blue/images/upload_btn.png) center no-repeat; background-size: 60px auto; float: left; overflow: hidden; position: relative; width: 60px; height: 60px;"
						onclick="upyunWapPicUpload('picurl')">(照片数量最多五张)
				</dd>
			</dl>
			<dl class="clearfix">
				<dt>个人宣言:</dt>
				<dd>
					<textarea class="textarea" placeholder="请输入个人宣言！" name="info" id="info"></textarea>
				</dd>
			</dl>
			<div
				style="color: #EC6941; font-size: 16px; padding: 0 15px 15px 15px; line-height: 24px; font-weight: bolder;">若在线报名失败，可以将报名信息：姓名+联系方式+描述+照片发给我们的官方微信平台！
			</div>
			<div class="btn_box">
				<input type="hidden" name="token" value="gh_63745fdc51d5">
				<input type="hidden" name="wecha_id" value="oIbDQjrArc0kh0N4sXXP0IplSG6o">
				<input type="hidden" name="vid" value="217021">
				<input type="hidden" name="jumpUrl" value="/Home/index.php/Index/index/id/217021.html">
				<input type="hidden" name="mid" value="62">
				<input type="submit" name="submit" class="button" value="确认报名">
			</div>
			<div class="blank10"></div>
			<input type="hidden" name="__hash__" value="b888ba6db3fb58e4eb81ba9ce1043956_fdb728cd957f92075bfcd3802741eae8" />
		</form>
	</div>
	<section class="rules">
		<div class="text">
			<div class="prize">投票说明</div>
			<div class="neirong">
				<p>
					<c:out value="${data.rules}" escapeXml="false" />
				</p>
			</div>
		</div>
	</section>
	<!--
<section><div class="pop" id="guanzhu" style="display:block"><div class="mengceng"></div><div class="pop_up"><p class="tit_p">如何参与活动</p><p class="tit_txt">请关注微信公众号”景德镇吃货“！如果您已经关注，进入公众号，点击菜单【参赛】，或者回复”萌宝“进入活动页面进行投票。</p><a href="http://mp.weixin.qq.com/s?__biz=MzI0ODAwOTEyMA==&mid=208091876&idx=1&sn=b11f8b53d4e2009fc3b6249d55d878e6#rd" class="gz_btn">详细了解参与方法</a></div></div></section>-->
	<div id="console"></div>
</body>
</html>
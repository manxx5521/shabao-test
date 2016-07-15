<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
	name="viewport">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<title>${data.playerName} 投票</title>
<%@include file="../../../context/head.jsp"%>
<link rel="stylesheet" href="${ctx}/resources/wechat/vote/blue/touch.css">
<link rel="stylesheet" href="${ctx}/resources/wechat/vote/blue/colorbox.css">
<cs:resource type="all" value="jquery,colorbox,masonry" />
<script src="${ctx}/resources/wechat/vote/blue/app.js" type="text/javascript"></script>
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
</style>
<!--微信分享-->
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
	wx.config({
		debug : false,
		appId : 'wxf022fc156026a511',
		timestamp : 1456839962,
		nonceStr : 'O2ciojBfI3KwBtKk',
		signature : '2cabe0a811a8d58284f7ae62aa2060ae50cd3e33',
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
			title : '${data.vote.voteName}',
			desc : '${data.des}',
			link : 'http://tp.lanrenmb.com/Home/index.php/Index/content/id/217021/fid/10537/subscribe/0.html',
			imgUrl : 'http://demo4.cn.aijia798.com/Home//Public/upload/3/c/0/9/559a9f4e41523.jpg'
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
</head>
<body>
	<header>
		<div class="m_head clearfix">
			<div class="slider">
				<ul><c:forEach var="r" items="${data.posters}" varStatus="idx">
					<li><a href="#"><img src="${ctx}/resources/wechat/upload/${r}" /></a></li>
					</c:forEach>
				</ul>
			</div>
			<div class="search">
				<form action="/Home/index.php/Index/content.html" id="search_form"
					method="get">
					<input type="hidden" name="id" value="217021" /><input
						type="hidden" name="subscribe" value="0" />
					<div class="search_con">
						<div class="btn">
							<input type="submit" name="seachid" id="searchBtn" value="搜索">
						</div>
						<div class="text_box">
							<input type="search" id="searchText" value="" name="keyword"
								placeholder="搜名字或编号" autocomplete="off">
						</div>
					</div>
					<input type="hidden" name="__hash__"
						value="6bc64adbc82fb783488aabec063bce17_f8c79fd1d7f9add6dbbe1422d11dab21" />
				</form>
			</div>
		</div>
	</header>
	<section class="content" id="get_info" data-rid="503" data-sort=""
		data-kw="" data-page="">
		<div class="detial_box">
			<p class="num clearfix">
				<span class="fl" id="baby_info" itid_id="5" data-rule_id="503"
					data-vote_num="1">&nbsp;${data.playerNum}号&nbsp;${data.playerName}</span>
				<span class="fr">&nbsp;&nbsp;&nbsp;&nbsp;票数：${data.voteNum}</span>
			</p>
			<div class="blank10"></div>
			<p>描述：${data.des}</p>
			<div class="blank10"></div>
			<c:forEach var="r" items="${data.imgList}" varStatus="idx">
			<img src="${ctx}/resources/wechat/upload/${r.image}" />
			</c:forEach>
		</div>
		<div class="blank10"></div>
		<div class="abtn_box">
			<a href="javascript:void(0);" class="a_btn toupiao vote"
				onclick="loaddzp()">我要投票</a><a
				href="${ctx}/wechat/vote/${data.voteId}/participate"
				class="a_btn canjia">我也来参加</a><a
				href="${ctx}/wechat/vote/${data.voteId}/list" class="a_btn look">点击查看更多</a>
		</div>
	</section>
	<img class="bg"
		src="${ctx}/resources/wechat/vote/blue/images/mw_005.jpg" />
	<section class="rules">
		<div class="text">
			<div class="prize">活动规则</div>
			<div class="neirong">
				<p>
					<c:out value="${data.vote.rules}" escapeXml="false" />
				</p>
			</div>
			<div style="text-align: center;">
				<a href="http://www.lanrenmb.com" style="color: #5e5e5e; font-size: 1.2em;">技术支持</a>
			</div>
		</div>
		<div style="height: 60px; width: 100%; display: block;"></div>
	</section>
	<section>
		<div class="pop" id="guanzhu" style="display: none">
			<div class="mengceng"></div>
			<div class="pop_up">
				<span class="closed close_pop_up">&nbsp;</span>
				<p class="tit_p">如何参与活动</p>
				<p class="tit_txt">添加我们后,回复【蓝色】参与活动</p>
				<a
					href="http://mp.weixin.qq.com/s?__biz=MjM5NjA0MTI0OQ==&mid=200068987&idx=1&sn=1de5daeaae94c66a3c46a13e20e8011e#rd"
					class="gz_btn">详细了解参与方法</a>
			</div>
		</div>
		<div class="pop" id="voted" style="display: none;">
			<div class="mengceng"></div>
		</div>
		<div class="pop" id="voting" style="display: none;">
			<div class="mengceng"></div>
			<div class="pop_up">
				<span class="closed close_pop_up">&nbsp;</span>
				<p class="tit_p" id="voting_title"></p>
				<p class="tit_txt" id="voting_content"></p>
			</div>
		</div>
		<div class="share_overmask" style="display: none;">
			<div class="share_arrow"></div>
			<div class="share_words"></div>
		</div>
	</section>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/wechat/vote/blue/daohang.css" />
	  <div class="bot_main">
		<ul>
			<li class="ico_3"><a
				href="${ctx}/wechat/vote/${data.voteId}/participate"><span
					class="ico"><img
						src="${ctx}/resources/wechat/vote/blue/images/i11.png" /></span><span
					class="txt" style="color: #333;">报名</span></a></li>
			<li class="ico_1"><a href="${ctx}/wechat/vote/${data.voteId}/list"><span
					class="ico"><img
						src="${ctx}/resources/wechat/vote/blue/images/i1.png" /></span><span
					class="txt" style="color: #333;">首页</span></a></li>
			<li class="ico_2"><a id="paihang"
				href="${ctx}/wechat/vote/${data.voteId}/ranking"><span class="ico"><img
						src="${ctx}/resources/wechat/vote/blue/images/i3.png" /></span><span
					class="txt" style="color: #333;">排行</span></a></li>
			<li class="ico_4"><a onclick="_system._guide(true)"><span
					class="ico"><img
						src="${ctx}/resources/wechat/vote/blue/images/i4.png" /></span><span
					class="txt" style="color: #333;">点击拉票</span></a></li>
		</ul>
	</div>
	<script src="${ctx}/resources/wechat/vote/blue/yxMobileSlider.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(".slider").yxMobileSlider({
			during : 5000,
			height : 300
		});

		function loaddzp() {
			$("#guanzhu").show();
		}

		$(function() {
			$("#paihang").click(function() {
				$(this).colorbox({
					iframe : true,
					width : "90%",
					height : "90%"
				});
			});

		});

		function showVerify(vid, id) {
			$.colorbox({
				top : "10px",
				innerWidth : 220,
				innerHeight : 220,
				iframe : true,
				href : "/Home/index.php?m=Public&a=lanren_verify&mid=62&vid="
						+ vid + "&id=" + id
			});
		}
	</script>
	<style type="text/css">
#cover {
	display: none;
	position: absolute;
	left: 0;
	top: 0;
	z-index: 18888;
	background-color: #000000;
	opacity: 0.7;
}

#guide {
	display: none;
	position: absolute;
	right: 18px;
	top: 5px;
	z-index: 19999;
}

#guide img {
	width: 260px;
	height: 180px;
}
</style>
	<div id="cover"></div>
	<div id="guide">
		<img src="${ctx}/resources/wechat/vote/blue/images/guide1.png">
	</div>
	<script type="text/javascript">
		var _system = {

			$$ : function(id) {
				return document.getElementById(id);
			},

			_client : function() {
				return {
					w : document.documentElement.scrollWidth,
					h : document.documentElement.scrollHeight,
					bw : document.documentElement.clientWidth,
					bh : document.documentElement.clientHeight
				};
			},

			_scroll : function() {
				return {
					x : document.documentElement.scrollLeft ? document.documentElement.scrollLeft
							: document.body.scrollLeft,
					y : document.documentElement.scrollTop ? document.documentElement.scrollTop
							: document.body.scrollTop
				};
			},

			_cover : function(show) {
				if (show) {
					this.$$("cover").style.display = "block";
					this.$$("cover").style.width = (this._client().bw > this
							._client().w ? this._client().bw : this._client().w)
							+ "px";
					this.$$("cover").style.height = (this._client().bh > this
							._client().h ? this._client().bh : this._client().h)
							+ "px";
				} else {
					this.$$("cover").style.display = "none";
				}

			},

			_guide : function(click) {
				this._cover(true);
				this.$$("guide").style.display = "block";
				this.$$("guide").style.top = (_system._scroll().y + 5) + "px";
				window.onresize = function() {
					_system._cover(true);
					_system.$$("guide").style.top = (_system._scroll().y + 5)
							+ "px";
				};

				if (click) {
					_system.$$("cover").onclick = function() {
						_system._cover();
						_system.$$("guide").style.display = "none";
						_system.$$("cover").onclick = null;
						window.onresize = null;
					};
				}

			},

			_zero : function(n) {
				return n < 0 ? 0 : n;
			}

		}
	</script>
</body>
</html>

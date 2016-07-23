<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<title>${data.voteName}</title>
<%@include file="../../../context/head.jsp"%>
<%@include file="../../common.jsp"%>
<link rel="stylesheet" href="${ctx}/resources/wechat/vote/blue/touch.css">
<link rel="stylesheet" href="${ctx}/resources/wechat/vote/blue/colorbox.css">
<cs:resource type="all" value="jquery,colorbox,masonry,weui" />
<%-- <script src="${ctx}/resources/wechat/vote/blue/app.js" type="text/javascript"></script> --%>
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
		}
	);
</script>
<!--END 微信分享-->
</head>
<body>
	<header>
		<div class="m_head clearfix">
			<div class="slider">
				<ul><c:forEach var="r" items="${data.imgList}" varStatus="idx">
					<li><a href="#"><img src="${ctx}/resources/wechat/upload/${r}" /></a></li>
					</c:forEach>
				</ul>
			</div>
			<div class="num_box">
				<div style="text-align: center;"></div>
				<a href="${ctx}/wechat/vote/${voteId}/participate"
					class="join_us">我要报名</a>
				<ul class="num_box_ul">
					<li><span class="text">已报名</span><span>${data.count.userNum}</span></li>
					<li><span class="text">投票人次</span><span>${data.count.voteNum}</span></li>
					<li><span class="text">访问量</span><span>${data.count.visitNum}</span></li>
				</ul>
				<img src="${ctx}/resources/wechat/vote/blue/images/mw_004.jpg" />
			</div>
			<div class="search">
					<form id="search_form" method="get">
					<input type="hidden" name="openid" id="openid" value="${openid}" />
					<input type="hidden" name="voteId" id="voteId" value="${voteId}" />
					<div class="search_con">
						<div class="btn">
							<input type="button" name="seachid" id="searchBtn" value="搜索">
						</div>
						<div class="text_box">
							<input type="text" id="keyword" value="${params.keyword}" name="keyword" placeholder="搜名字或编号">
						</div>
					</div>
					</form>
			</div>
		</div>
	</header>
	<section class="content" id="get_info" data-rid="503" data-sort=""
		data-kw="" data-page="">
		<div class="text_a clearfix" id="sort">
			<a href="/Home/index.php/Index/index/id/217021/_order/addtime.html"
				class="active">最新参赛</a><a
				href="/Home/index.php/Index/index/id/217021/_order/ticket.html">投票排行</a><a
				href="/Home/index.php/Index/index/id/217021/_order/ticket/listRows/300.html">TOP300</a>
		</div>
		<div class="blank20"></div>
		<div id="pageCon" class="match_page masonry"
			style="padding-bottom: 50px">
			<ul class="list_box masonry clearfix" style="position: relative;">
				<c:forEach var="r" items="${data.page.list}" varStatus="idx">
				<li class="picCon">
					<div>
						<i class="number">${r.playerNum}号</i>
						<a href="${ctx}/wechat/vote/${r.playerId}/detail" class="img">
							<img src="${ctx}/resources/wechat/upload/${r.image}" alt="">
						</a>
						<div class="clearfix">
							<p>
								${r.playerName}<br />${r.voteNum}票
							</p>
							<a href="javascript:void(0);" class="vote" onclick="voteOne(${r.playerNum})">投票</a>
						</div>
					</div>
				</li>
				</c:forEach>
			</ul>
		</div>
<style>
.pagination {
	color: #72c0e6;
	font-size: 16px;
}

.pagination a {
	color: #72c0e6;
	font-size: 16px;
}
</style>
		<div class="pagination pagination-centered">
			 <a href='#' id="upPage" >上一页</a>&nbsp;<span class='current'>${data.page.index}</span>&nbsp;<a href='#' id="nextPage">下一页</a>
		</div>
	</section>
	<img class="bg"
		src="${ctx}/resources/wechat/vote/blue/images/mw_005.jpg" />
	<section class="rules">
		<div class="text">
			<div class="prize">活动规则</div>
			<div class="neirong">
				<p>
					<c:out value="${data.rules}" escapeXml="false" />
				</p>
			</div>
			<div style="text-align: center;">
				<a href="#"
					style="color: #5e5e5e; font-size: 1.2em;">技术支持</a>
			</div>
		</div>
		<div style="height: 60px; width: 100%; display: block;"></div>
	</section>
	<!-- 微信弹窗 begin-->
	<div class="weui_dialog_confirm" style="display: none;">
		<div class="weui_mask"></div>
		<div class="weui_dialog">
			<div class="weui_dialog_hd">
				<strong class="weui_dialog_title">提示</strong>
			</div>
			<div class="weui_dialog_bd">您当前未关注，无法进行投票。请先关注后再投票，点击详情按钮查看关注方法。</div>
			<div class="weui_dialog_ft">
				<a href="#" class="weui_btn_dialog default" onclick="$('.weui_dialog_confirm').hide()">取消</a> 
				<a href="${data.loginUrl}" class="weui_btn_dialog primary">详情</a>
			</div>
		</div>
	</div>
	<div class="weui_dialog_alert" style="display: none;">
		<div class="weui_mask"></div>
		<div class="weui_dialog">
			<div class="weui_dialog_hd">
				<strong class="weui_dialog_title">提示</strong>
			</div>
			<div class="weui_dialog_bd">弹窗内容，告知当前页面信息等</div>
			<div class="weui_dialog_ft">
				<a href="#" class="weui_btn_dialog primary">确定</a>
			</div>
		</div>
	</div>
	<!-- 微信弹窗 end-->

	<link rel="stylesheet" type="text/css" href="${ctx}/resources/wechat/vote/blue/daohang.css" />
	  <div class="bot_main">
		<ul>
			<li class="ico_3"><a
				href="${ctx}/wechat/vote/${voteId}/participate"><span
					class="ico"><img
						src="${ctx}/resources/wechat/vote/blue/images/i11.png" /></span><span
					class="txt" style="color: #333;">报名</span></a></li>
			<li class="ico_1"><a href="${ctx}/wechat/vote/${voteId}/list"><span
					class="ico"><img
						src="${ctx}/resources/wechat/vote/blue/images/i1.png" /></span><span
					class="txt" style="color: #333;">首页</span></a></li>
			<li class="ico_2"><a id="paihang"
				href="${ctx}/wechat/vote/${voteId}/ranking"><span class="ico"><img
						src="${ctx}/resources/wechat/vote/blue/images/i3.png" /></span><span
					class="txt" style="color: #333;">排行</span></a></li>
			<li class="ico_4"><a onclick="_system._guide(true)"><span
					class="ico"><img
						src="${ctx}/resources/wechat/vote/blue/images/i4.png" /></span><span
					class="txt" style="color: #333;">点击拉票</span></a></li>
		</ul>
	</div>
	<script src="${ctx}/resources/wechat/vote/blue/yxMobileSlider.js" type="text/javascript"></script>
	<!-- 
	<script type="text/javascript">
	//获取列表
	var params={index:0,size:30};
	$('#nextPage').click(function(){
		++params.index;
		params.keyword=$('#keyword').val();
		$.ajax({
			  type: 'POST',
			  url: window.webroot+'/wechat/vote/'+$('#voteId').val()+'/listPage',
			  data: params,
			  dataType:"json",
			  success: function(result){
				 if(result.success){
					 ajaxList(result.data.list);
					 if(result.data.pages<=params.index){
						 $('#nextPage').hide();
					 }
				 }
			 }
		});
	});
	function ajaxList(list){
		var i=0;
		for(var i;i<list.length;i++){
			var player=list[i];
			var li=$('<li class="picCon"></li>');
			var div=$('<div></div>');
			var i_num=$('<i class="number"></i>').html(player.playerNum+'号');
			var img=$('<img/>').attr("src",window.webroot+'/resources/wechat/upload/'+player.image);
			var a1=$('<a class="img"></a>').attr("href",window.webroot+'/wechat/vote/'+player.playerId+'/detail').append(img);
			var p=$('<p></p>').html(player.playerName+'<br/>'+player.voteNum+'票');
			var a2=$('<a href="javascript:void(0);" class="vote" onclick="loaddzp()">投票</a>')
			div.append(i_num).append(a1).append($('<div="clearfix"></div>').append(p).append(a2));
			$('.list_box').append(li.append(div));
		}
	}
	
	$(document).ready(function() {
		$('#nextPage').click();
	});
	</script>
	 -->
<script type="text/javascript">
	var vote={
		index:'${data.page.index}',
		pages:'${data.page.pages}',
		openid:'${openid}',
		url:'${data.loginUrl}',
		startTime:'${data.startTime}',
		endTime:'${data.endTime}',
		voteId:'${voteId}',
		listUrl:window.webroot+'/wechat/vote/${voteId}/list'
	};
	$(document).ready(function() {
		var index =${data.page.index};
		var pages=${data.page.pages};
		var keyword=$('#keyword').val();
		 var url= window.webroot+'/wechat/vote/'+$('#voteId').val()+'/list?keyword='+keyword+'&index=';
		if(index==1){
			$('#upPage').hide();
		}else{
			$('#upPage').attr("href",url+(index-1));
		}
		if(index==pages){
			$('#nextPage').hide();
		}else{
			$('#nextPage').attr("href",url+(index+1));
		}
		$('#searchBtn').click(function(){
			window.location.href = window.webroot+'/wechat/vote/'+$('#voteId').val()+'/list?keyword='+$('#keyword').val();
		});
	});
	//弹窗设置
	$('.weui_btn_dialog').click(function(){
		$('.weui_dialog_alert').hide();
	});
	//投票设置
	function voteOne(num){
		if(num*1==0){
			$('.weui_dialog_bd').html('未正确获得选号')
			$('.weui_dialog_alert').show();
			return false;
		}
		var i=vote.openid
		if(vote.openid==""){
			$('.weui_dialog_confirm').show();
			return false;
		}
		var now = new Date();
		//var start=vote.startTime;
		//var end=vote.endTime;
		var start=new Date(vote.startTime);
		var end=new Date(vote.endTime);
		if(now<start){
			$('.weui_dialog_bd').html('当前投票还未开始')
			$('.weui_dialog_alert').show();
			return false;
		}
		if(now>end){
			$('.weui_dialog_bd').html('当前投票已经结束')
			$('.weui_dialog_alert').show();
			return false;
		}
		$.ajax({
			  type: 'POST',
			  url: window.webroot+'/wechat/vote/'+vote.voteId+'/addVoteNum',
			  data: {playerId:num},
			  dataType:"json",
			  success: function(result){
				$('.weui_dialog_bd').html(result.message)
				$('.weui_dialog_alert').show();
				if(result.success){
					$('.weui_btn_dialog').click(function() {
						 window.location.href = vote.listUrl;
					});
				}
			 }
		});
	}
	
	
	
	
		$(".slider").yxMobileSlider({
			during : 5000,
			height : 300
		});

		$(function() {
			$("#paihang").click(function() {
				$(this).colorbox({
					top : "15px",
					iframe : true,
					width : "90%",
					height : "90%"
				});

			});
		});

		var container = $('#pageCon ul');
		container.imagesLoaded(function() {
			container.masonry({
				itemSelector : '.picCon'
			});

		});
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
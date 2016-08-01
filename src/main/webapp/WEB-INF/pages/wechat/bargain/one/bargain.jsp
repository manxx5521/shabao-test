<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport">
<meta name="format-detection" content="telephone=no">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="default">
<meta name="applicable-device" content="mobile">
<title>${data.bargainName}</title>
<%@include file="../../../context/head.jsp"%>
<cs:resource type="all" value="jquery,swiper,leanModal,jweixin" />
<%@include file="../../common.jsp"%>
<link href="${ctx}/resources/wechat/bargain/one/base.css" rel="stylesheet">
<link href="${ctx}/resources/wechat/bargain/one/index.css" rel="stylesheet">
<link href="${ctx}/resources/wechat/bargain/one/media.css" rel="stylesheet">
<script src="${ctx}/resources/wechat/bargain/one/hm.js"></script>
</head>
<body>
	
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/wechat/bargain/one/fans.css">
	<link href="${ctx}/resources/wechat/bargain/one/index(1).css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${ctx}/resources/wechat/bargain/one/topNotice.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/wechat/bargain/one/leanModal.css">
	<div id="memberNoticeBox"
		style="display: none; position: fixed; opacity: 1; z-index: 11000; left: 50%; margin-left: -170px; top: 110px;">
		<h1>提醒</h1>
		<div class="txtfield" style="width: 92%">
			<a href="http://315750.wap.weixinyunduan.com/wx/pub/yunduanwx/index.php?g=Wap&m=Index&a=memberLogin&token=315750"
				style="color:#800D8C">您好，需要您先填写个人信息才能参加活动。</a>
		</div>
		<a href="http://315750.wap.weixinyunduan.com/wx/pub/yunduanwx/index.php?g=Wap&m=Index&a=memberLogin&token=315750" class="flatbtn">去填写</a> 
		<a class="flatbtn btnC hidemodal">取消</a>
	</div>
	<div id="lean_overlay" style="display: none; opacity: 0.45;"></div>
	<script type="text/javascript">
	$(function(){
		$('#modaltrigger_notice').leanModal({
			top:110,
			overlay:0.45,
			closeButton:".hidemodal"
		});
	});
	</script>
	<!-- - -->
	<div class="layer "></div>
	<div class="moeny center">
		<audio id="player" src="/yunduanwx/tpl/static/bargain/new/123.wav">
		</audio>
		<div class="moeny_img ">
			<img src="${ctx}/resources/wechat/bargain/one/money_13_13_10.png">
		</div>
		<div class="close"></div>
		<div class="moeny_content  ">
			<p>
				哇哦，在您休息片刻之际，已有<span></span>位亲友帮您砍掉
			</p>
			<span>￥</span>
			<h3>你是不是很开森啊</h3>
			<div class="moeny_left"></div>
			<div class="moeny_right"></div>
		</div>
		<div class="coloured">
			<i></i><i></i><i></i><i></i><i></i><i></i><i></i><i></i><i></i>
		</div>
		<div class="wallet">
			<i></i><i></i><i></i>
		</div>
		<div class="coin">
			<i></i><i></i><i></i><i></i><i></i>
		</div>
	</div>
	<div class="bargain_layer center">
		<audio id="player_audio"
			src="/yunduanwx/tpl/static/bargain/new/jishui.wav"> </audio>
		<div class="bargain_ball_img">
			<img src="${ctx}/resources/wechat/bargain/one/knife_02.png">
		</div>
		<div class="close"></div>
		<div class="bargain_crack "></div>
		<div class="bargain_people">
			<i></i><i></i><i></i>
		</div>
		<div class="bargain_colour"></div>
		<div class="bargain_ribbon">
			<i></i><i></i>
		</div>
		<div class="bargain_banner">
			成功砍价<span>￥</span>
		</div>
	</div>
	<!-- -->
	<header class="clearfix">
	<div class="header_left">
		已有<span>${data.userNum}</span>人参与砍价
	</div>
	<div class="header_right">
		销量:<span>${data.saleNum}</span>
	</div>
	</header>
	<div class="banner"
		onclick="location.href='#">
		<div class="swiper-container s1 swiper-container-horizontal">
			<div class="swiper-wrapper"
				style="transition-duration: 0ms; transform: translate3d(0px, 0px, 0px);">
				<c:forEach var="r" items="${data.posters}" varStatus="idx">
				<div class="swiper-slide swiper-slide-active" style="width: 1325px;height:217px;">
					<div class="banner_img">
						<img src="${ctx}/resources/upload/poster/${r.image}">
					</div>
					<div class="banner_txt clearfix">
						<div style="clear: both"></div>
						<p>
							<span>${r.title}</span>
							<button>${r.button}</button>
						</p>
					</div>
				</div>
				</c:forEach>
			</div>
			<div class="swiper-pagination p1 swiper-pagination-clickable">
				<span class="swiper-pagination-bullet swiper-pagination-bullet-active"></span>
				<span class="swiper-pagination-bullet"></span>
			</div>
		</div>
	</div>
	<script>
var mySwiper = new Swiper('.s1',{
loop: false,
autoplay: 3000,
pagination: '.p1',
paginationClickable: true
});
$(document).ready(function(){
	});
</script>
	<div class="content">
		<div class="bargain clearfix">
			<div class="bargain_img">
				<img src="${ctx}/resources/wechat/upload/bargain/${data.info.portrait}">
			</div>
			<div class="bargain_content">
				<h1>${data.info.nickname}</h1>
				<p>
					现价：￥<span class="nowprice"><c:if test="${!empty data.info.joinUser.price}">${data.info.joinUser.price}</c:if>
					<c:if test="${empty data.info.joinUser.price}">${data.totalPrice}</c:if>
						</span>
				</p>
				<p>
					<b>原价：￥${data.totalPrice}</b>
				</p>
				<p>
					<b>底价：￥${data.mimPrice}</b>
				</p>
			</div>
			<div class="bargain_info">
				<c:if test="${data.info.joinUser.bargainNum<1}">
				<i class="myrank">未参与</i><i class="helpcount">0人帮砍</i>
				</c:if>
				<c:if test="${data.info.joinUser.bargainNum>0}">
				<i class="myrank">已参与</i><i class="helpcount">${data.bargainNum}人帮砍</i>
				</c:if>
			</div>
		</div>
		<div class="operation clearfix">
			<div class="operation_info">
				<a href="http://315750.wap.weixinyunduan.com/wx/pub/yunduanwx/index.php?g=Wap&m=Bargain&a=new_index&token=315750&id=1370&code=122333#memberNoticeBox"
					id="modaltrigger_notice" style="color: #fff"><button>点击参与</button></a>
			</div>
		</div>
	</div>
	<div class="share_bg"
		style="display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; text-align: center; background: rgba(0, 0, 0, 0.7); z-index: 99;">
		<img src="${ctx}/resources/wechat/bargain/one/share-guide.png" style="width: 100%;">
	</div>
	<div class="activity">
		<div class="activity_title">
			<ul class="clearfix">
				<li class="curn">活动规则</li>
				<li>亲友出刀</li>
				<li>砍价TOP10000</li>
			</ul>
		</div>
		<ul class="acticity_list">
			<ul>
				<li style="padding: 10px">
					<p><c:out value="${data.rules}" escapeXml="false" /></p>
				</li>
				<li class="bargain_list" style="display: none;">
					<ul class="friends">
						<c:forEach var="r" items="${data.users}" varStatus="idx">
						<li class="clearfix">
							<div class="head_img">
								<img src="${ctx}/resources/wechat/upload/bargain/${r.user.portrait}">
							</div>
							<h2>${r.user.nickname}</h2>
							<p>
								砍掉:<span>￥${r.bargainPrice}</span>
							</p>
						</li>
					</c:forEach>
					</ul>
				</li>
				<li class="bargain_list" style="display: none;">
					<ul>
					<c:forEach var="r" items="${data.rankingList}" varStatus="idx">
						<li class=" clearfix"><i>${idx.index+1}</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/upload/bargain/${r.user.portrait}">
							</div>
							<h2>${r.user.nickname}</h2>
							<p>
								已砍至:<span>￥${r.price}</span>
							</p>
						</li>
					</c:forEach>
					</ul>
				</li>
			</ul>
		</ul>
		<div class="mfooter" id="wxgjfooter" style="text-align: center; width: 100%; height: 20px; line-height: 20px; margin-top: 10px;">
			<span class="sp2"><a href="#" style="color: #5e5e5e; font-size: 12px;">技术支持</a></span>
		</div>
	</div>
	<script>
$(document).ready(function(){
	tab(".activity_title li", ".acticity_list > ul > li", "curn");
	$(".close,.layer").click(function() {
		$(".center").fadeOut(110);
		$(".layer").fadeOut(140);
		$("body").css({
			"height": "auto",
			"overflow": "auto"
		});
	});
		});
function share(){
	$('.share_bg').show();
	$('.share_bg').click(function(){
		if($(this).css('display') == 'block'){
			$(this).css('display','none');
		}
	});
}
function fistblood(){
	window.location.href="/wx/pub/yunduanwx/index.php?g=Wap&m=Bargain&a=new_fistblood&token=315750&id=1370";
}
function kandao(){
	window.location.href='/wx/pub/yunduanwx/index.php?g=Wap&m=Bargain&a=new_kandao&token=315750&id=1370';
}
function tab(a, b, c) { //a 是点击的目标,,b 是所要切换的目标,c 是点击目标的当前样式
    var len = $(a);
    len.bind("click",
    function() {
        var index = 0;
        $(this).addClass(c).siblings().removeClass(c);
        index = len.index(this); //获取当前的索引
        $(b).eq(index).show().siblings().hide();
        return false;
    }).eq(0).trigger("click"); //浏览器模拟第一个点击
}
//进入时加载
function winning() {
	var hh = $(window).height();
	var autioPlay = function(autioDom, controlDom) {
		var autoplay = typeof($(autioDom).attr('autoplay'));;
		autioDom.play();
		$("body").css({
			"height": hh,
			"overflow": "hidden"
		});
		$(".moeny,.layer").show();
		clearTimeout(t);
		var t = setTimeout(function() {
			$(".center").fadeOut(110);
			$(".layer").fadeOut(140);
			$("body").css({
				"height": "auto",
				"overflow": "auto"
			});
		}, 6000);
		$(".close,.layer").click(function() {
			autioDom.pause();
		});
	}
	autioPlay(document.getElementById('player'), $('.player'));
}
//砍价时
function notwinning() {
	var hh = $(window).height();
	$("body").css({
		"height": hh,
		"overflow": "hidden"
	});
	$(".bargain_layer,.layer").show();
	var autioPlay = function(autioDom, controlDom) {
		var autoplay = typeof($(autioDom).attr('autoplay'));;
		clearTimeout(a);
		var a = setTimeout(function() {
			autioDom.play();
			$(".close,.layer").click(function() {
				autioDom.pause();
			})
		},
		1300);
		clearTimeout(b);
		var b = setTimeout(function() {
			$(".center").fadeOut(400);
			$(".layer").fadeOut(500);
			$("body").css({
				"height": "auto",
				"overflow": "auto"
			});
		}, 6500);
	}
	autioPlay(document.getElementById('player_audio'), $('.player_audio'));
}
</script>
	<script type="text/javascript">
window.shareData = {  
	"moduleName":"疯狂砍价",
	"moduleID":"1370",
	"imgUrl": "http://www.weixinyunduan.com/yunduanwx/newuploads/3/315750/0/7/0/e/thumb_572b0ec4a9be1.jpg",
		"sendFriendLink": "http://315750.wap.weixinyunduan.com/wx/pub/yunduanwx/index.php?g=Wap&m=Bargain&a=new_index&token=315750&id=1370",
		"tTitle": "课程月卡1元购！砍价疯狂进行时~",
	"tContent": "特价课程，我要买买买"
};
</script>
		
	<script type="text/javascript">
	wx.ready(function () {
	  // 1 判断当前版本是否支持指定 JS 接口，支持批量判断
	  /*document.querySelector('#checkJsApi').onclick = function () {
	    wx.checkJsApi({
	      jsApiList: [
	        'getNetworkType',
	        'previewImage'
	      ],
	      success: function (res) {
	        //alert(JSON.stringify(res));
	      }
	    });
	  };*/
	  // 2. 分享接口
	  // 2.1 监听“分享给朋友”，按钮点击、自定义分享内容及分享结果接口
	    wx.onMenuShareAppMessage({
			title: window.shareData.tTitle,
			desc: window.shareData.tContent,
			link: window.shareData.sendFriendLink,
			imgUrl: window.shareData.imgUrl,
		    type: '', // 分享类型,music、video或link，不填默认为link
		    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
		    success: function () { 
				shareHandle('frined');
		    },
		    cancel: function () { 
		        //alert('分享朋友失败');
		    }
		});


	  // 2.2 监听“分享到朋友圈”按钮点击、自定义分享内容及分享结果接口
		wx.onMenuShareTimeline({
			title: window.shareData.fTitle?window.shareData.fTitle:window.shareData.tTitle,
			link: window.shareData.sendFriendLink,
			imgUrl: window.shareData.imgUrl,
		    success: function () { 
				shareHandle('frineds');
		        //alert('分享朋友圈成功');
		    },
		    cancel: function () { 
		        //alert('分享朋友圈失败');
		    }
		});	

	  // 2.4 监听“分享到微博”按钮点击、自定义分享内容及分享结果接口
		wx.onMenuShareWeibo({
			title: window.shareData.tTitle,
			desc: window.shareData.tContent,
			link: window.shareData.sendFriendLink,
			imgUrl: window.shareData.imgUrl,
		    success: function () { 
				shareHandle('weibo');
		       	//alert('分享微博成功');
		    },
		    cancel: function () { 
		        //alert('分享微博失败');
		    }
		});
		if(window.shareData.timeline_hide == '1'){
			wx.hideMenuItems({
			  menuList: [
				'menuItem:share:timeline', //隐藏分享到朋友圈
			  ],
			});
		}
		wx.error(function (res) {
			/*if(res.errMsg == 'config:invalid signature'){
				wx.hideOptionMenu();
			}else if(res.errMsg == 'config:invalid url domain'){
				wx.hideOptionMenu();
			}else{
				wx.hideOptionMenu();
				//alert(res.errMsg);
			}*/
			if(res.errMsg){
				wx.hideOptionMenu();
			}
		});
	});
		
	function shareHandle(to) {
		var submitData = {
			module: window.shareData.moduleName,
			moduleid: window.shareData.moduleID,
			token:'',
			wecha_id:'',
			url: window.shareData.sendFriendLink,
			to:to
		};

		$.post('index.php?g=Wap&m=Share&a=shareData&token=315750&wecha_id=',submitData,function (data) {},'json');
		if(window.shareData.isShareNum == 1){
			var ShareNum = {
				token:'315750',
				ShareNumData:window.shareData.ShareNumData
			}
			$.post('index.php?g=Wap&m=Share&a=ShareNum&token=315750&wecha_id=',ShareNum,function (data) {if(window.shareData.isShareNumReload == 1){location.reload();}},'json');
		}
	}
</script>
	<div id="lean_overlay"></div>
</body>
<style type="text/css" id="54008016393"></style>
</html>
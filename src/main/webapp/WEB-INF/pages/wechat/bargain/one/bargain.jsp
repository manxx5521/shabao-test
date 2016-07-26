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
<title>英语课程月卡</title>
<%@include file="../../../context/head.jsp"%>
<%@include file="../../common.jsp"%>
<cs:resource type="all" value="jquery,swiper,leanModal" />
<link href="${ctx}/resources/wechat/bargain/one/base.css" rel="stylesheet">
<link href="${ctx}/resources/wechat/bargain/one/index.css" rel="stylesheet">
<link href="${ctx}/resources/wechat/bargain/one/media.css" rel="stylesheet">
<script src="${ctx}/resources/wechat/bargain/one/hm.js"></script>
</head>
<body>
	
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/wechat/bargain/one/fans.css">
	<link href="${ctx}/resources/wechat/bargain/one/index(1).css" type="text/css" rel="stylesheet">
	<%-- 
	<div class="zhezhao" style="display: block; position: fixed;"></div>
	<article class="peplo_content clearfix">
	<div class="peplo_content_left">
		<img src="${ctx}/resources/wechat/bargain/one/us.png">
	</div>
	<div style="float: left; line-height: 36px;">
		<a href="http://315750.wap.weixinyunduan.com/wx/pub/yunduanwx/index.php?g=Wap&m=Index&a=memberLogin&token=315750" data-ajax="false"
			style="color: #FFF; font-weight: normal; text-decoration: none;">您好，需要您先填写个人信息才能参加活动。</a>
	</div>
	<div class="peplo_content_right" style="background: url(/tpl/static/Plugin/gb.png); background-size: 15px 15px;">
	</div>
	</article>
	<script>
	$(function(){
	    $(".peplo_content_right").click(function() {
			$(".peplo_content,.zhezhao").fadeOut(1000);
		})
	})
	</script>
	 --%>
	
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
		已有<span>95</span>人参与砍价
	</div>
	<div class="header_right">
		销量:<span>1</span>
	</div>
	</header>
	<div class="banner"
		onclick="location.href='#">
		<div class="swiper-container s1 swiper-container-horizontal">
			<div class="swiper-wrapper"
				style="transition-duration: 0ms; transform: translate3d(0px, 0px, 0px);">
				<div class="swiper-slide swiper-slide-active" style="width: 1325px;">
					<div class="banner_img">
						<img src="${ctx}/resources/wechat/upload/bargain/thumb_572b0ec4a9be1.jpg">
					</div>
					<div class="banner_txt clearfix">
						<div style="clear: both"></div>
						<p>
							<span>小朋友们开心学习</span>
							<button>商品详情</button>
						</p>
					</div>
				</div>
				<div class="swiper-slide swiper-slide-next" style="width: 1325px;">
					<div class="banner_img">
						<img src="${ctx}/resources/wechat/upload/bargain/thumb_5710843bb70f9.jpg">
					</div>
					<div class="banner_txt clearfix">
						<div style="clear: both"></div>
						<p>
							<span>亦学亦玩，结交伙伴</span>
							<button>商品详情</button>
						</p>
					</div>
				</div>
			</div>
			<div class="swiper-pagination p1 swiper-pagination-clickable">
				<span
					class="swiper-pagination-bullet swiper-pagination-bullet-active"></span><span
					class="swiper-pagination-bullet"></span>
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
				<img src="${ctx}/resources/wechat/bargain/one/portrait.jpg">
			</div>
			<div class="bargain_content">
				<h1>匿名</h1>
				<p>
					现价：￥<span class="nowprice">18</span>
				</p>
				<p>
					<b>原价：￥18</b>
				</p>
				<p>
					<b>底价：￥1</b>
				</p>
			</div>
			<div class="bargain_info">
				<i class="myrank">未参与</i><i class="helpcount">0人帮砍</i>
			</div>
		</div>
		<div class="operation clearfix">
			<div class="operation_info">
				<a
					href="http://315750.wap.weixinyunduan.com/wx/pub/yunduanwx/index.php?g=Wap&m=Bargain&a=new_index&token=315750&id=1370&code=122333#memberNoticeBox"
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
					<p>每位好友可以帮你砍一刀，越砍越便宜哦！</p>
					<p>
						<span style="background-color: #FFE500;">砍到心仪价格后，到店支付该金额即可买入。</span>
					</p>
					<p>
						<span>地址：幸福大街****号</span>
					</p>
					<p>
						<span>电话：0571-66668888</span>
					</p>
					<p>
						<span style="background-color: #FFE500;"><img
							src="${ctx}/resources/wechat/bargain/one/572b0f3280f73.jpg" alt=""><br> </span>
					</p>
					<p>
						<img src="${ctx}/resources/wechat/bargain/one/572b0f08aa87b.jpg" alt="">
					</p>
					<p>
						<img src="${ctx}/resources/wechat/bargain/one/572b0f16db39f.jpg" alt="">
					</p>
				</li>
				<li style="display: none;">
					<ul class="friends">
					</ul>
				</li>
				<li class="bargain_list" style="display: none;">
					<ul>
						<li class=" clearfix"><i>1</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/upload/user/0">
							</div>
							<h2>A0洪帮主【好活动</h2>
							<p>
								已砍至:<span>￥1</span>
							</p>
						</li>
						<li class=" clearfix"><i>2</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(1)">
							</div>
							<h2>邵纪华</h2>
							<p>
								已砍至:<span>￥1</span>
							</p></li>
						<li class=" clearfix"><i>3</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(2)">
							</div>
							<h2>断肠人在天涯</h2>
							<p>
								已砍至:<span>￥8.02</span>
							</p></li>
						<li class=" clearfix"><i>4</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(3)">
							</div>
							<h2>A00女王驾到（婚</h2>
							<p>
								已砍至:<span>￥8.31</span>
							</p></li>
						<li class=" clearfix"><i>5</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(4)">
							</div>
							<h2>徐轩昂</h2>
							<p>
								已砍至:<span>￥8.35</span>
							</p></li>
						<li class=" clearfix"><i>6</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(5)">
							</div>
							<h2>东霞</h2>
							<p>
								已砍至:<span>￥8.44</span>
							</p></li>
						<li class=" clearfix"><i>7</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(6)">
							</div>
							<h2>Ao快乐魔方&amp;小蝌</h2>
							<p>
								已砍至:<span>￥8.57</span>
							</p></li>
						<li class=" clearfix"><i>8</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(7)">
							</div>
							<h2>爱♥教育魔方</h2>
							<p>
								已砍至:<span>￥8.63</span>
							</p></li>
						<li class=" clearfix"><i>9</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(8)">
							</div>
							<h2>活着回家</h2>
							<p>
								已砍至:<span>￥8.63</span>
							</p></li>
						<li class=" clearfix"><i>10</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(9)">
							</div>
							<h2>管玉江</h2>
							<p>
								已砍至:<span>￥8.69</span>
							</p></li>
						<li class=" clearfix"><i>11</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(10)">
							</div>
							<h2>轩韵</h2>
							<p>
								已砍至:<span>￥8.86</span>
							</p></li>
						<li class=" clearfix"><i>12</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(11)">
							</div>
							<h2>远英教育管老师</h2>
							<p>
								已砍至:<span>￥8.96</span>
							</p></li>
						<li class=" clearfix"><i>13</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(12)">
							</div>
							<h2>匿名</h2>
							<p>
								已砍至:<span>￥9.1</span>
							</p></li>
						<li class=" clearfix"><i>14</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(13)">
							</div>
							<h2>人生常青</h2>
							<p>
								已砍至:<span>￥9.18</span>
							</p></li>
						<li class=" clearfix"><i>15</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(14)">
							</div>
							<h2>夕阳</h2>
							<p>
								已砍至:<span>￥9.19</span>
							</p></li>
						<li class=" clearfix"><i>16</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(15)">
							</div>
							<h2>share</h2>
							<p>
								已砍至:<span>￥9.19</span>
							</p></li>
						<li class=" clearfix"><i>17</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(16)">
							</div>
							<h2>Joker</h2>
							<p>
								已砍至:<span>￥9.37</span>
							</p></li>
						<li class=" clearfix"><i>18</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(17)">
							</div>
							<h2>shen</h2>
							<p>
								已砍至:<span>￥9.52</span>
							</p></li>
						<li class=" clearfix"><i>19</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(18)">
							</div>
							<h2>张春娥</h2>
							<p>
								已砍至:<span>￥9.56</span>
							</p></li>
						<li class=" clearfix"><i>20</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(19)">
							</div>
							<h2>数学老师</h2>
							<p>
								已砍至:<span>￥9.67</span>
							</p></li>
						<li class=" clearfix"><i>21</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/portrait(1).jpg">
							</div>
							<h2>吴国良</h2>
							<p>
								已砍至:<span>￥9.8</span>
							</p></li>
						<li class=" clearfix"><i>22</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(20)">
							</div>
							<h2>好心凉 心飞扬</h2>
							<p>
								已砍至:<span>￥10</span>
							</p></li>
						<li class=" clearfix"><i>23</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(21)">
							</div>
							<h2>丽缘居木业</h2>
							<p>
								已砍至:<span>￥10.07</span>
							</p></li>
						<li class=" clearfix"><i>24</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(22)">
							</div>
							<h2>蓝鲸</h2>
							<p>
								已砍至:<span>￥10.08</span>
							</p></li>
						<li class=" clearfix"><i>25</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(23)">
							</div>
							<h2>ZHANGYUYI</h2>
							<p>
								已砍至:<span>￥10.08</span>
							</p></li>
						<li class=" clearfix"><i>26</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(24)">
							</div>
							<h2>英伦外语(凤凰城+</h2>
							<p>
								已砍至:<span>￥10.13</span>
							</p></li>
						<li class=" clearfix"><i>27</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(25)">
							</div>
							<h2>彩虹的约定</h2>
							<p>
								已砍至:<span>￥10.35</span>
							</p></li>
						<li class=" clearfix"><i>28</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(26)">
							</div>
							<h2>俊子西</h2>
							<p>
								已砍至:<span>￥10.39</span>
							</p></li>
						<li class=" clearfix"><i>29</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(27)">
							</div>
							<h2>陈伟-扎西茨林-C</h2>
							<p>
								已砍至:<span>￥10.39</span>
							</p></li>
						<li class=" clearfix"><i>30</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(28)">
							</div>
							<h2>天样高</h2>
							<p>
								已砍至:<span>￥10.4</span>
							</p></li>
						<li class=" clearfix"><i>31</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(29)">
							</div>
							<h2>白海平</h2>
							<p>
								已砍至:<span>￥10.48</span>
							</p></li>
						<li class=" clearfix"><i>32</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(30)">
							</div>
							<h2>Sunny莹</h2>
							<p>
								已砍至:<span>￥10.55</span>
							</p></li>
						<li class=" clearfix"><i>33</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(31)">
							</div>
							<h2>¥¥¥</h2>
							<p>
								已砍至:<span>￥10.68</span>
							</p></li>
						<li class=" clearfix"><i>34</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(32)">
							</div>
							<h2>杜子悦vita</h2>
							<p>
								已砍至:<span>￥10.7</span>
							</p></li>
						<li class=" clearfix"><i>35</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(33)">
							</div>
							<h2>匿名</h2>
							<p>
								已砍至:<span>￥10.74</span>
							</p></li>
						<li class=" clearfix"><i>36</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(34)">
							</div>
							<h2>小新星国际教育昌黎</h2>
							<p>
								已砍至:<span>￥10.76</span>
							</p></li>
						<li class=" clearfix"><i>37</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(35)">
							</div>
							<h2>趴猫</h2>
							<p>
								已砍至:<span>￥10.77</span>
							</p></li>
						<li class=" clearfix"><i>38</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(36)">
							</div>
							<h2>高秀英</h2>
							<p>
								已砍至:<span>￥10.77</span>
							</p></li>
						<li class=" clearfix"><i>39</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(37)">
							</div>
							<h2>暖阳</h2>
							<p>
								已砍至:<span>￥10.82</span>
							</p></li>
						<li class=" clearfix"><i>40</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(38)">
							</div>
							<h2>深山老雕</h2>
							<p>
								已砍至:<span>￥10.82</span>
							</p></li>
						<li class=" clearfix"><i>41</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(39)">
							</div>
							<h2>好人一生平安</h2>
							<p>
								已砍至:<span>￥11.05</span>
							</p></li>
						<li class=" clearfix"><i>42</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(40)">
							</div>
							<h2>真实的我</h2>
							<p>
								已砍至:<span>￥11.29</span>
							</p></li>
						<li class=" clearfix"><i>43</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(41)">
							</div>
							<h2>开心快乐</h2>
							<p>
								已砍至:<span>￥11.36</span>
							</p></li>
						<li class=" clearfix"><i>44</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(42)">
							</div>
							<h2>吴建&amp;纽斯达足球学</h2>
							<p>
								已砍至:<span>￥11.4</span>
							</p></li>
						<li class=" clearfix"><i>45</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(43)">
							</div>
							<h2>妙彩书画 亮亮老</h2>
							<p>
								已砍至:<span>￥11.42</span>
							</p></li>
						<li class=" clearfix"><i>46</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(44)">
							</div>
							<h2>优学派 ～～淑珊老</h2>
							<p>
								已砍至:<span>￥11.52</span>
							</p></li>
						<li class=" clearfix"><i>47</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(45)">
							</div>
							<h2>黄亚然</h2>
							<p>
								已砍至:<span>￥11.54</span>
							</p></li>
						<li class=" clearfix"><i>48</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(46)">
							</div>
							<h2>伯乐</h2>
							<p>
								已砍至:<span>￥11.59</span>
							</p></li>
						<li class=" clearfix"><i>49</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(47)">
							</div>
							<h2>平安幸福</h2>
							<p>
								已砍至:<span>￥11.88</span>
							</p></li>
						<li class=" clearfix"><i>50</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(48)">
							</div>
							<h2>✿boyaೄ</h2>
							<p>
								已砍至:<span>￥12</span>
							</p></li>
						<li class=" clearfix"><i>51</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(49)">
							</div>
							<h2>惜缘</h2>
							<p>
								已砍至:<span>￥12.31</span>
							</p></li>
						<li class=" clearfix"><i>52</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(50)">
							</div>
							<h2>阿(⊙o⊙)橋</h2>
							<p>
								已砍至:<span>￥12.32</span>
							</p></li>
						<li class=" clearfix"><i>53</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(51)">
							</div>
							<h2>水浒</h2>
							<p>
								已砍至:<span>￥12.36</span>
							</p></li>
						<li class=" clearfix"><i>54</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(52)">
							</div>
							<h2>《懷 t 舊》</h2>
							<p>
								已砍至:<span>￥12.36</span>
							</p></li>
						<li class=" clearfix"><i>55</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(53)">
							</div>
							<h2>达州市七彩艺术学校</h2>
							<p>
								已砍至:<span>￥12.5</span>
							</p></li>
						<li class=" clearfix"><i>56</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(54)">
							</div>
							<h2>周燕</h2>
							<p>
								已砍至:<span>￥12.53</span>
							</p></li>
						<li class=" clearfix"><i>57</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(55)">
							</div>
							<h2>HTL全球沙发大师</h2>
							<p>
								已砍至:<span>￥12.55</span>
							</p></li>
						<li class=" clearfix"><i>58</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(56)">
							</div>
							<h2>茜茜她爸</h2>
							<p>
								已砍至:<span>￥12.61</span>
							</p></li>
						<li class=" clearfix"><i>59</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(57)">
							</div>
							<h2>王mon</h2>
							<p>
								已砍至:<span>￥12.71</span>
							</p></li>
						<li class=" clearfix"><i>60</i>
							<div class="head_img">
								<img src="${ctx}/resources/wechat/bargain/one/0(58)">
							</div>
							<h2>洪淑伟【移曼】</h2>
							<p>
								已砍至:<span>￥12.93</span>
							</p></li>
					</ul>
				</li>
			</ul>
		</ul>
		<script src="${ctx}/resources/wechat/bargain/one/weixinyunduan.html" type="text/javascript"></script>
		<div class="mfooter" id="wxgjfooter"
			style="text-align: center; width: 100%; height: 20px; line-height: 20px; margin-top: 10px;">
			<span class="sp2"><a
				href="http://wx35279.vshangtong.com/wxapi.php?ac=cate105&tid=23632"
				style="color: #5e5e5e; font-size: 12px;">好活动策划制作 /
					电话&amp;微信13291806632</a></span>
		</div>


		<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?6c1de06baaf5e88bda60d4c9acaa816b";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>

		<div style="width: 0px; height: 0px; overflow: hidden;">
			<script language="javascript" type="text/javascript"
				src="${ctx}/resources/wechat/bargain/one/18925490.js"></script>
			<a href="http://www.51.la/?18925490" target="_blank"
				title="51.La 网站流量统计系统"><img alt="51.La 网站流量统计系统"
				src="${ctx}/resources/wechat/bargain/one/icon_1.gif" style="border: none"></a>

		</div>

		<br>
		<br>
		<br>
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
	<script type="text/javascript" src="${ctx}/resources/wechat/bargain/one/jweixin-1.0.0.js"></script>
	<script type="text/javascript">
		wx.config({
		  debug: false,
		  appId: 	'wxbbe3543c661f396a',
		  timestamp: 1469288552,
		  nonceStr: '915516',
		  signature: 'd2dfdaa222dfd5494ade78ec0d14b3eca1af9e3f',
		  jsApiList: [
	    	'checkJsApi',
		    'onMenuShareTimeline',
		    'onMenuShareAppMessage',
		    'onMenuShareQQ',
		    'onMenuShareWeibo',
			'openLocation',
			'getLocation',
			'addCard',
			'chooseCard',
			'openCard',
			'hideMenuItems',
			'previewImage'
		  ]
		});
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
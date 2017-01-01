<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>沙包梦想 </title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="微信第三方接入平台" />
	<meta name="keywords" content="微信，接入，全面" />
	<meta name="author" content="梦想" />
  	<!-- Facebook and Twitter integration -->
	<meta property="og:title" content=""/>
	<meta property="og:image" content=""/>
	<meta property="og:url" content=""/>
	<meta property="og:site_name" content=""/>
	<meta property="og:description" content=""/>
	<meta name="twitter:title" content="" />
	<meta name="twitter:image" content="" />
	<meta name="twitter:url" content="" />
	<meta name="twitter:card" content="" />
	<%@include file="../../context/head.jsp"%>
	<cbox:resource type="css" value="jquery,bootstrap,carousel,magnific-popup,superfish,themify-icons" />

  	<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
  	<link rel="shortcut icon" href="${ctx}/resources/img/favicon.ico">

  	<!-- Google Webfont
	<link href='http://fonts.useso.com/css?family=Lato:300,400,700' rel='stylesheet' type='text/css'> -->
	<!-- Themify Icons
	<link rel="stylesheet" href="${ctx}/resources/system/web/css/themify-icons.css"> -->
	<!-- Easy Responsive Tabs -->
	<link rel="stylesheet" href="${ctx}/resources/system/web/css/easy-responsive-tabs.css">
	<!-- Theme Style -->
	<link rel="stylesheet" href="${ctx}/resources/system/web/css/style.css">

	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="${ctx}/resources/system/web/js/respond.min.js"></script>
	<![endif]-->

	</head>
	<body>

		<!-- START #fh5co-header -->
		<header id="fh5co-header-section" role="header" class="" >
			<div class="container">

				

				<!-- <div id="fh5co-menu-logo"> -->
					<!-- START #fh5co-logo -->
					<h1 id="fh5co-logo" class="pull-left"><a href="index.html"><img src="${ctx}/resources/system/web/images/logo.png" alt="Slant Free HTML5 Template"></a></h1>
					
					<!-- START #fh5co-menu-wrap -->
					<nav id="fh5co-menu-wrap" role="navigation">
						
						
						<ul class="sf-menu" id="fh5co-primary-menu">
							<li class="active">
								<a href="index.html">主页</a>
							</li>
							<li>
								<a href="#" class="fh5co-sub-ddown">技术支持</a>
								 <ul class="fh5co-sub-menu">
								 	<li><a href="#">技术团队</a></li>
								 	<li><a href="#">更新</a></li>
								</ul>
							</li>
							<li><a href="#">关于我们</a></li>
							<li class="fh5co-special"><a href="${ctx}/admin/login">GO 微信管理平台</a></li>
						</ul>
					</nav>
				<!-- </div> -->

			</div>
		</header>
		
		
		<div id="fh5co-hero">
			<a href="#fh5co-main" class="smoothscroll fh5co-arrow to-animate hero-animate-4"><i class="ti-angle-down"></i></a>
			<!-- End fh5co-arrow -->
			<div class="container">
				<div class="col-md-8 col-md-offset-2">
					<div class="fh5co-hero-wrap">
						<div class="fh5co-hero-intro">
							<h1 class="to-animate hero-animate-1">沙包梦想微信管理平台</h1>
							<h2 class="to-animate hero-animate-2">小沙包，大梦想 </h2>
							<p class="to-animate hero-animate-3"><a href="${ctx}/admin/login.html" class="btn btn-outline btn-lg">GO 微信管理平台</a></p>
						</div>
					</div>
				</div>
			</div>		
		</div>
		
        <div class="copyrights">Collect from <a href="#" >手机网站模板</a></div>
		<div id="fh5co-main">
	
			<div class="container">
				<div class="row" id="fh5co-features">
					
					<div class="col-md-4 col-sm-6 text-center fh5co-feature feature-box">
						<div class="fh5co-feature-icon">
							<i class="ti-mobile"></i>
						</div>
						<h3 class="heading">跨平台</h3>
						<p>依托微信，系统可以跨平台支持。</p>
					</div>
					<div class="col-md-4 col-sm-6 text-center fh5co-feature feature-box"> 
						<div class="fh5co-feature-icon">
							<i class="ti-lock"></i>
						</div>
						<h3 class="heading">安全</h3>
						<p>使用先进的shiro框架，有着完善的安全管理功能。</p>
					</div>

					<div class="clearfix visible-sm-block"></div>

					<div class="col-md-4 col-sm-6 text-center fh5co-feature feature-box"> 
						<div class="fh5co-feature-icon">
							<i class="ti-video-camera"></i>
						</div>
						<h3 class="heading">支撑</h3>
						<p>有良好的技术支撑团队，能在任意时刻为您解答。</p>
					</div>

					<div class="clearfix visible-md-block visible-lg-block"></div>

					<div class="col-md-4 col-sm-6 text-center fh5co-feature feature-box">
						<div class="fh5co-feature-icon">
							<i class="ti-shopping-cart"></i>
						</div>
						<h3 class="heading">增值</h3>
						<p>您可以通过支付来获得更多的功能和活动方式。 </p>
					</div>

					<div class="clearfix visible-sm-block"></div>

					<div class="col-md-4 col-sm-6 text-center fh5co-feature feature-box"> 
						<div class="fh5co-feature-icon">
							<i class="ti-palette"></i>
						</div>
						<h3 class="heading">多模版</h3>
						<p>根据你的喜好，可以选择系统中的多种网页模版。</p>
					</div>
					<div class="col-md-4 col-sm-6 text-center fh5co-feature feature-box"> 
						<div class="fh5co-feature-icon">
							<i class="ti-truck"></i>
						</div>
						<h3 class="heading">定制</h3>
						<p>强大的技术团队，可以根据您的要求额外定制。</p>
					</div>
				</div>
				<!-- END row -->

				<div class="fh5co-spacer fh5co-spacer-md"></div>
				<!-- End Spacer -->

				<div class="row" id="fh5co-works">
					<div class="col-md-8 col-md-offset-2 text-center fh5co-section-heading work-box">
						<h2 class="fh5co-lead">这是一个微信接入项目</h2>
						<p class="fh5co-sub">对你的微信公众号进行第三方接入。接入后可以使用本平台现有的功能和活动，并且可以根据自己的需求联系我们进行增值服务。</p>
						<div class="fh5co-spacer fh5co-spacer-sm"></div>
					</div>
					<div class="col-md-4 col-sm-6 col-xs-6 col-xxs-12 text-center fh5co-work-item work-box">
						<figure><a href="#"><img class="img-responsive" src="${ctx}/resources/system/web/images/work_1.jpg" alt="Free HTML5 Template"></a></figure>
						<p class="fh5co-category">可以查看用户分布 </p>
						<h3 class="heading">用户地图</h3>
					</div>
					<div class="col-md-4 col-sm-6 col-xs-6 col-xxs-12 text-center fh5co-work-item work-box"> 
						<figure><a href="#"><img class="img-responsive" src="${ctx}/resources/system/web/images/work_2.jpg" alt="Free HTML5 Template"></a></figure>
						<p class="fh5co-category">定制自己的投票活动 </p>
						<h3 class="heading">投票活动</h3>
					</div>

					<div class="clearfix visible-sm-block visible-xs-block"></div>

					<div class="col-md-4 col-sm-6 col-xs-6 col-xxs-12 text-center fh5co-work-item work-box"> 
						<figure><a href="#"><img class="img-responsive" src="${ctx}/resources/system/web/images/work_3.jpg" alt="Free HTML5 Template"></a></figure>
						<p class="fh5co-category">发起固定价格的砍价活动</p>
						<h3 class="heading">砍价活动</h3>
					</div>

				</div>
				<!-- END row -->
				
				<div class="fh5co-spacer fh5co-spacer-md"></div>
				<div class="row">
					<!-- Start Slider Testimonial -->
	            <h2 class="fh5co-uppercase-heading-sm text-center animate-box">还在等什么，联系我们吧</h2>
	            <div class="fh5co-spacer fh5co-spacer-xs"></div>
	            <div class="owl-carousel-fullwidth animate-box">
	            <div class="item">
	              <p class="text-center quote">&ldquo;QQ：382752556 &rdquo; <cite class="author">&mdash; 沙包梦想</cite></p>
	            </div>
	            <div class="item">
	              <p class="text-center quote">&ldquo;QQ：382752556 &rdquo; <cite class="author">&mdash; 沙包梦想</cite></p>
	            </div>
	            <div class="item">
	              <p class="text-center quote">&ldquo;QQ：382752556 &rdquo; <cite class="author">&mdash; 沙包梦想</cite></p>
	            </div>
	          </div>
	           <!-- End Slider Testimonial -->
				</div>
				<!-- END row -->
				<div class="fh5co-spacer fh5co-spacer-md"></div>

			</div>
			<!-- END container -->

		
		</div>
		<!-- END fhtco-main -->


		<footer role="contentinfo" id="fh5co-footer">
			<a href="#" class="fh5co-arrow fh5co-gotop footer-box"><i class="ti-angle-up"></i></a>
			<div class="container">
				<div class="row">
					<!--
					<div class="col-md-4 col-sm-6 footer-box">
						<h3 class="fh5co-footer-heading">About us</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Minima delectus dolorem fugit totam, commodi ad.</p>
						<p><a href="#" class="btn btn-outline btn-sm">I'm a button</a></p>

					</div>
					<div class="col-md-4 col-sm-6 footer-box">
						<h3 class="fh5co-footer-heading">Links</h3>
						<ul class="fh5co-footer-links">
							<li><a href="#">Terms &amp; Conditions</a></li>
							<li><a href="#">Our Careers</a></li>
							<li><a href="#">Support &amp; FAQ's</a></li>
							<li><a href="#">Sign up</a></li>
							<li><a href="#">Log in</a></li>
						</ul>
					</div>
					<div class="col-md-4 col-sm-12 footer-box">
						<h3 class="fh5co-footer-heading">Get in touch</h3>
						<ul class="fh5co-social-icons">
							
							<li><a href="#"><i class="ti-google"></i></a></li>
							<li><a href="#"><i class="ti-twitter-alt"></i></a></li>
							<li><a href="#"><i class="ti-facebook"></i></a></li>	
							<li><a href="#"><i class="ti-instagram"></i></a></li>
							<li><a href="#"><i class="ti-dribbble"></i></a></li>
						</ul>
					</div>
					-->
					<div class="col-md-12 footer-box">
						<div class="fh5co-copyright">
						<p>&copy; 2015 Free Slant. All Rights Reserved. <br>Designed by More Templates <a href="#" target="_blank" title="沙包梦想">沙包梦想</a> </p>
						</div>
					</div>
					
				</div>
				<!-- END row -->
				<div class="fh5co-spacer fh5co-spacer-md"></div>
			</div>
		</footer>
		<cbox:resource type="js" value="jquery,bootstrap,carousel,magnific-popup,superfish,modernizr,fastclick,waypoints" />
		<!-- jQuery Easing -->
		<script src="${ctx}/resources/system/web/js/jquery.easing.1.3.js"></script>
		<!-- Easy Responsive Tabs -->
		<script src="${ctx}/resources/system/web/js/easyResponsiveTabs.js"></script>
		<!-- Parallax 
		<script src="js/jquery.parallax-scroll.min.js"></script>-->
		<!-- Main JS -->
		<script src="${ctx}/resources/system/web/js/main.js"></script>

	</body>
</html>

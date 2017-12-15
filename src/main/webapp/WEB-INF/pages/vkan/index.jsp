<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en"  xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="../context/head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="format=xhtml;url=/e/wap/" name="mobile-agent">
<meta name="keywords" content="资源管理">
<meta name="description" content="资源管理 ">
<title>资源管理</title>
<link rel="shortcut icon"href="${ctx}/resources/img/favicon.ico" type="image/x-icon">
<cbox:resource type="css" value="cache,jquery,masonry,infinite-scroll" />
<link href="${ctx}/resources/vkan/index.css" rel="stylesheet" type="text/css">

</head>
<body>
	<div class="head-wrap">
		<div class="warp ovv">
			<div class="logo">
				<a href="./index.html" title="我的网站"> 
					<img width="180" height="45" src="${ctx}/resources/vkan/logo.png">
				</a>
			</div>
			<div class="m-nav">
				<ul>
					<li class="menu-item current-menu-item"><a href="./index.html">全部</a></li>
					<li class="menu-item "><a href="javascript:void(0)">视频</a></li>
					<li class="menu-item "><a href="javascript:void(0)">图</a></li>
					<li class="menu-item "><a href="javascript:void(0)">|</a></li>
					<!--
					<li class="menu-item "><a href="http://ecms060.99yuanma.net:8888/qingchun/">清纯</a></li>
					<li class="menu-item "><a href="http://ecms060.99yuanma.net:8888/mengmeizi/">萌妹</a></li>
					<li class="menu-item "><a href="http://ecms060.99yuanma.net:8888/nvshen/">女神</a></li>
					<li class="menu-item">
						<a href="http://ecms060.99yuanma.net:8888/more/">更多</a>
						<ul class="sub-menu">
							<li class="menu-item"><a href="http://ecms060.99yuanma.net:8888/more/qizhimeinv/">气质</a></li>
							<li class="menu-item"><a href="http://ecms060.99yuanma.net:8888/more/nenmo/">嫩模</a></li>
							<li class="menu-item"><a href="http://ecms060.99yuanma.net:8888/more/bijini/">比基尼</a></li>
							<li class="menu-item"><a href="http://ecms060.99yuanma.net:8888/more/zuqiubaobei/">足球宝贝</a></li>
							<li class="menu-item"><a href="http://ecms060.99yuanma.net:8888/more/luoli/">萝莉</a></li>
							<li class="menu-item"><a href="http://ecms060.99yuanma.net:8888/more/90hou/">90后</a></li>
							<li class="menu-item"><a href="http://ecms060.99yuanma.net:8888/more/rihanmeinv/">日韩</a></li>
						</ul>
					</li>
					<li><a href="http://ecms060.99yuanma.net:8888/hot/" class="tab">最热</a></li>
					<li><a href="http://ecms060.99yuanma.net:8888/best/" class="tab">推荐</a></li>
					-->
					<li class="menu-item "><a id="searchId"  href="javascript:void(0)" >${data.projectName}<div class="searchBtn"></div></a><!-- <div class="searchBtn"><a name="search"></a></div> --></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="warp">
		<div class="tags-container">
			<!-- <ul class="tags-box">
				<li class="hot-1 curr"><a href="javascript:void(0)" class="tag-font-size-14">全部</a></li>
				<li class="hot-1">
					<a href="http://ecms060.99yuanma.net:8888/e/tags/?tagname=%E6%80%A7%E6%84%9F"
					target="_blank" title="680个话题" class="tag-font-size-14">分类1</a></li>
				<li class="hot-1"><a
					href="http://ecms060.99yuanma.net:8888/e/tags/?tagname=%E7%BE%8E%E8%85%BF"
					target="_blank" title="246个话题" class="tag-font-size-14">分类2</a></li>
				<li class="hot-1"><a
					href="http://ecms060.99yuanma.net:8888/e/tags/?tagname=%E6%B8%85%E7%BA%AF"
					target="_blank" title="218个话题" class="tag-font-size-14">分类3</a></li>
			</ul>
			-->
		</div>
		<div class="tips"></div>
		<!--图片展示begin-->
		<div id="body-container">
			<span id="dataGroup">
				<div id="img-container" class="masonry" style="position: relative; height: 2417px;">
					<!-- 
					<div class="border-img-box masonry-brick">
						<div class="img_inner_wrapper">
							<div class="inner_wrapper_img inner_wrapper_img1">
								<div>
									<a href="http://ecms060.99yuanma.net:8888/more/nenmo/1658.html"
										target="_blank"> <img title="2014车展上靓丽车模写真"
										class="img-min-height" alt="2014车展上靓丽车模写真"
										src="./妹子图_files/b20872116a242e36ff2fe74bbc652c0f.jpg"></a>
								</div>
								<div class="mid_img_count">
									<span class="num"> <label>9</label>
									</span> <span class="mid_img_count_font"> <label>张</label>
									</span>
								</div>
								<div class="img_inner_wrapper_tag">
									<div class="title">
										<a
											href="http://ecms060.99yuanma.net:8888/more/nenmo/1658.html"
											target="_blank">2014车展上靓丽车模写真</a>
									</div>
									<div class="tag curr">
										<label>分类：</label> <a
											href="http://ecms060.99yuanma.net:8888/more/nenmo/">嫩模</a>
									</div>
									<div class="tag curr">
										<label>标签：</label> <a
											href="http://ecms060.99yuanma.net:8888/e/tags/?tagname=%E6%A8%A1%E7%89%B9"
											target="_blank" rel="tag">模特</a> <a
											href="http://ecms060.99yuanma.net:8888/e/tags/?tagname=%E5%86%99%E7%9C%9F"
											target="_blank" rel="tag">写真</a> <a
											href="http://ecms060.99yuanma.net:8888/e/tags/?tagname=%E8%BD%A6%E6%A8%A1"
											target="_blank" rel="tag">车模</a>
									</div>
								</div>
							</div>
						</div>
					</div>
 -->
				</div>
			</span>
		</div>
		<!--图片展示end-->
		<!-- 下拉分页 -->
		<div class="page-load-status">
			<p class="infinite-scroll-request">Loading...</p>
			<p class="infinite-scroll-last">End of content</p>
			<p class="infinite-scroll-error">No more pages to load</p>
		</div>
		<div id="more"><a href="#">1111</a></div>
		<div class="itempages">
			<ul>
				<a href="http://ecms060.99yuanma.net:8888/index.html#" class="current">1</a>
				<a class="page-numbers" href="http://ecms060.99yuanma.net:8888/index_2.html">2</a>
				<a class="page-numbers" href="http://ecms060.99yuanma.net:8888/index_3.html">3</a>
				<a class="page-numbers" href="http://ecms060.99yuanma.net:8888/index_4.html">4</a>
				<a class="page-numbers" href="http://ecms060.99yuanma.net:8888/index_5.html">5</a>
				<a class="page-numbers" href="http://ecms060.99yuanma.net:8888/index_6.html">6</a>
				<a class="page-numbers" href="http://ecms060.99yuanma.net:8888/index_7.html">7</a>
				<a class="page-numbers" href="http://ecms060.99yuanma.net:8888/index_8.html">8</a>
				<a class="page-numbers" href="http://ecms060.99yuanma.net:8888/index_9.html">9</a>
				<a class="page-numbers" href="http://ecms060.99yuanma.net:8888/index_10.html">10</a>
				<a class="page-numbers" href="http://ecms060.99yuanma.net:8888/index_11.html">11</a>
				<a class="page-numbers" href="http://ecms060.99yuanma.net:8888/index_12.html">12</a>
				<a class="next page-numbers" href="http://ecms060.99yuanma.net:8888/index_2.html"><span>下一页»</span></a>
				<a class="page-numbers" href="http://ecms060.99yuanma.net:8888/index_20.html">尾页</a>
			</ul>
		</div>
	</div>
	
	<div style="" id="gotoTop">
		<a class="goto-top" rel="nofollow" title="返回顶部" onclick="javascript:scroll(0,0);" href="javascript:scroll(0,0)"></a>
	</div>
	<div class="warp" id="friendlyLink">
		<div class="link_title">
			<span class="title">友情链接</span>
		</div>
		<div class="links">
			<a href="http://www.moyublog.com/" target="_blank">墨鱼部落格</a> 
			<a href="http://www.99yuanma.net/" target="_blank">久久源码</a> 
			<a href="http://www.99ziyuan.net/" target="_blank">久久资源网</a> 
			<a href="http://moyu2013.taobao.com/" target="_blank">淘宝商业源码</a>
		</div>
	</div>
	<div class="warp" id="footer">
		<div class="remark">
			©2015 <a href="http://ecms060.99yuanma.net:8888/">我的网站</a> 版权所有
			鲁ICP-123456789号 联系邮箱：123456789@qq.com
			<!--统计代码放到这里-->
		</div>
	</div>
	<div id="simplemodal-container"></div>
	<div id="searchbar">
		<p>全站搜索</p>
		<form method="post" name="searchform">
			<select name="projectPrefix">
				<c:forEach var="r" items="${data.prefixs}" varStatus="idx">
				<option value ="${r}" <c:if test="${r}==${data.projectPrefix}"> selected="selected"</c:if>>${r}</option>
				</c:forEach>
			</select>
			<select name="projectId">
				<c:forEach var="r" items="${data.projectList}" varStatus="idx">
				<option value ="${r.projectId}" <c:if test="${r.projectId}==${data.projectId}"> selected="selected"</c:if>>${r.projectName}</option>
				</c:forEach>
			</select>
			<input type="text" name="keyboard" id="edtSearch" class="text" value=""> 
			<input type="hidden" name="parentId" value="${data.parentId}"/> 
			<input type="hidden" name="projectPath" value="${data.projectPath}"/> 
			<input type="button" id="btnPost" value="确定">
		</form>
	</div>
	<cbox:resource type="js" value="jquery,masonry,infinite-scroll" />
	<script src="${ctx}/resources/vkan/index.js" type="text/javascript"></script>
</body>
</html>
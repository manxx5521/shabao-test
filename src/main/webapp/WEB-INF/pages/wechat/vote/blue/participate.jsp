<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<%@include file="../../common.jsp"%>
<link rel="stylesheet" href="${ctx}/resources/wechat/vote/blue/touch.css">
<link rel="stylesheet" href="${ctx}/resources/wechat/vote/blue/colorbox.css">
<cs:resource type="all" value="jquery,colorbox,masonry,swiper" />
<link rel="stylesheet" href="${ctx}/resources/plugins/weui/weui.min.css">
<!--微信分享-->
<script type="text/javascript">
	wx.ready(function() {
		var shareData = {
			title : '${data.voteName}',
			desc : '${data.des}',
			link : 'http://tp.lanrenmb.com/Home/index.php/Index/index/id/217021.html',
			imgUrl : 'http://tp.lanrenmb.com/Member/Public/upload/2/3/5/f/5625fc25f2823.png'
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
</style>
</head>
<body>
	<header>
		<div class="form-group">

			<div id="content" style="max-width: 640px; margin: 0 auto;">
				<!--headpic-->
				<div class="swiper-container" style="display: block;">
					<div class="swiper-wrapper">
						<c:forEach var="r" items="${data.imgList}" varStatus="idx">
							<div class="swiper-slide">
								<a href="#"><img class="imgplay"
									src="${ctx}/resources/wechat/upload/${r}" /></a>
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
	</header>
	<div class="bd" >
		<form method="post" id="form1">
		<div class="weui_cells_title">信息填写</div>
		<div class="weui_cells weui_cells_form">
			<div class="weui_cell">
				<div class="weui_cell_hd">
					<label class="weui_label">姓名</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<input class="weui_input" type="number" pattern="[0-9]*"
						placeholder="请输入姓名" />
				</div>
			</div>
			<div class="weui_cell">
				<div class="weui_cell_hd">
					<label class="weui_label">联系方式</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<input class="weui_input" type="number" pattern="[0-9]*"
						placeholder="请输入联系方式" />
				</div>
			</div>
			<div class="weui_cell">
				<div class="weui_cell_bd weui_cell_primary">
					<div class="weui_uploader">
						<div class="weui_uploader_hd weui_cell">
							<div class="weui_cell_bd weui_cell_primary">图片上传</div>
							<div class="weui_cell_ft">0/5</div>
						</div>
						<div class="weui_uploader_bd">
							<ul class="weui_uploader_files">
								<li class="weui_uploader_file" id="img1"
									style="display:none;background-image: url(http://shp.qpic.cn/weixinsrc_pic/pScBR7sbqjOBJomcuvVJ6iacVrbMJaoJZkFUIq4nzQZUIqzTKziam7ibg/)"></li>
								<li class="weui_uploader_file" id="img2"
									style="display:none;background-image: url(http://shp.qpic.cn/weixinsrc_pic/pScBR7sbqjOBJomcuvVJ6iacVrbMJaoJZkFUIq4nzQZUIqzTKziam7ibg/)"></li>
								<li class="weui_uploader_file" id="img3"
									style="display:none;background-image: url(http://shp.qpic.cn/weixinsrc_pic/pScBR7sbqjOBJomcuvVJ6iacVrbMJaoJZkFUIq4nzQZUIqzTKziam7ibg/)"></li>
								<li class="weui_uploader_file" id="img4"
									style="display:none;background-image: url(http://shp.qpic.cn/weixinsrc_pic/pScBR7sbqjOBJomcuvVJ6iacVrbMJaoJZkFUIq4nzQZUIqzTKziam7ibg/)"></li>
								<li class="weui_uploader_file" id="img5"
									style="display:none;background-image: url(http://shp.qpic.cn/weixinsrc_pic/pScBR7sbqjOBJomcuvVJ6iacVrbMJaoJZkFUIq4nzQZUIqzTKziam7ibg/)"></li>
							</ul>
							<input type="hidden" name="imgs" id="input_img1" />
							<input type="hidden" name="imgs" id="input_img2" />
							<input type="hidden" name="imgs" id="input_img3" />
							<input type="hidden" name="imgs" id="input_img4" />
							<input type="hidden" name="imgs" id="input_img5" />
							<input type="hidden" name="voteId" id="voteId" value="${data.voteId}" />
							<div class="weui_uploader_input_wrp" >
								<input class="weui_uploader_input" type="button" id="filebtn"/>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="weui_cells_title">文本域</div>
			<div class="weui_cell">
				<div class="weui_cell_bd weui_cell_primary">
					<textarea class="weui_textarea" placeholder="请输入个人宣言" rows="3"></textarea>
					<div class="weui_textarea_counter">
						<span>0</span>/200
					</div>
				</div>
			</div>
		</div>
		<div class="weui_cells_tips">若在线报名失败，可以将报名信息：姓名+联系方式+描述+照片发给我们的官方微信平台！</div>
		<div class="weui_btn_area">
			<a class="weui_btn weui_btn_primary" id="showTooltips">确定</a>
		</div>
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
	<div id="console"></div>
<script type="text/javascript">
		//打开相机 
	var seeImg=function(img){
		wx.chooseImage({
	    	count: 1, // 默认9
	    	sizeType: ['compressed'], // 可以指定是原图还是压缩图，默认二者都有
	    	sourceType: ['album','camera'], // 可以指定来源是相册还是相机，默认二者都有
	    	success: function (res) {
	        	imgid=res.localIds;
	        	setTimeout(uploadimg(img,imgid),300);
	        	//localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
	    	}
	 	});
	};
	var uploadimg=function(img,imgid){
		wx.uploadImage({
	        localId:imgid.toString() , // 需要上传的图片的本地ID，由chooseImage接口获得
	        isShowProgressTips: 1, // 默认为1，显示进度提示
	        success: function (res) {
	        	var serverId = res.serverId; // 返回图片的服务器端ID
	        	//改写本地图片
	        	ajaxUtil(serverId,img);
	    	}
	 	});
	}
	//ajax下载图片
	var ajaxUtil=function(serverId,img){
		$.ajax({
			  type: 'POST',
			  url: '${domain}/wehcat/file/'+serverId+'/get',
			  data: {path:'vote'},
			  dataType:"json",
			  success: function(result){
				 if(result.success){
					 var path='${domain}/resources/wechat/upload/vote/'+result.filename;
					 $('#'+img).show();
					 $('#'+img).css('background-image','url('+path+')');
					 $('#input_'+img).val(data.filename);
					 $('#weui_cell_ft').html(img_num+'/5')
					 img_num++;
				 }
			 }
		});
	}
	var img_num=1;
	$('#filebtn').click(function() {
		alert(1)
		seeImg('img'+img_num);
	});
	
	$('#showTooltips').click(function() {
		var formdata = $("#form1").serialize();
		$.ajax({
			type : 'POST',
			url : '${ctx}/wechat/vote/'+$('#voteId').val()+'/playeradd',
			data : formdata,
			dataType : 'json',
			success : function(result) {
				if(result.success){
					
				}else{
					
				}
			},
			error : function(info) {
				alert(info.responseText);
				alert(info);
			}
		});
	});
</script>
</body>
</html>
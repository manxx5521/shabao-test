<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>添加砍价活动</title>
<%@include file="../../../context/head.jsp"%>
<%@include file="../../../system/common.jsp"%>
<cs:resource type="css" value="jquery,bootstrap,system,sweetalert,icheck,fileinput,datetimepicker" />
</head>
<body class="gray-bg">
   <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>砍价活动</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="tabs_panels.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="tabs_panels.html#">选项1</a>
                                </li>
                                <li><a href="tabs_panels.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <p>你可以通过下面提供的网址来编辑文本内容，然后将代码粘贴到下边的内容处。</p>
                        <p>编辑文本网址：<a href="http://www.lanrenmb.com/zhengtaomoban/###" target="_blank">http://www.lanrenmb.com/zhengtaomoban/###</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>砍价活动</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="form_basic.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="form_basic.html#">选项1</a>
                                </li>
                                <li><a href="form_basic.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="commentForm">
                        	<div class="form-group">
                                <label class="col-sm-2 control-label">帐号选择</label>
                                <div class="col-sm-10">
                                	<c:forEach var="r" items="${data.accounts}" varStatus="idx">
                                    <label class="radio-inline  i-checks">
                                        <input type="radio" name="accountId" value="${r.accountId}"> ${r.appName}</label>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">活动名称：</label>
                                <div class="col-sm-9">
                                    <input id="bargainName" name="bargainName" type="text" class="form-control" value="${data.bargainName}" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">活动描述：</label>
                                <div class="col-sm-9">
                                    <input id="des" name="des" type="text" value="${data.des}" class="form-control" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">商品总价：</label>
                                <div class="col-sm-9">
                                    <input id="totalPrice" name="totalPrice" type="number" value="${data.totalPrice}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">商品底价：</label>
                                <div class="col-sm-9">
                                    <input id="minPrice" name="minPrice" type="number" value="${data.minPrice}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">单次砍掉最高价钱：</label>
                                <div class="col-sm-9">
                                    <input id="onePrice" name="onePrice" type="number" value="${data.onePrice}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">砍价的最大次数：</label>
                                <div class="col-sm-9">
                                    <input id="maxBargainNum" name="maxBargainNum" type="number" value="${data.maxBargainNum}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">活动开始时间：</label>
                                <div class="col-sm-9">
                                    <input id="startTime" name="startTime" type="text" value="${data.startTime}"  class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">活动结束时间：</label>
                                <div class="col-sm-9">
                                    <input id="endTime" name="endTime" type="text" value="${data.endTime}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">活动海报 图片：</label>
                                 <div class="col-sm-9">
                                  	<input id="posterimage" name="poster.image" type="hidden" value="${data.poster.image}" class="form-control">
                                   <!--  <input id="poster_image" name="poster.image" multiple type="file" class="file" > -->
                                	<input id="file-5" type="file" name="file" class="file" multiple data-preview-file-type="text">
                                </div>
                            </div>
                             <div class="form-group">
                                <label class="col-sm-2 control-label">活动海报标题：</label>
                                <div class="col-sm-9">
                                    <input id="poster.title" name="poster.title" type="text" value="${data.poster.title}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">活动规则：</label>
                                <div class="col-sm-9">
                                	<script id="editor_rules" name="rules" type="text/plain" style="width:100%;height:300px;"></script>
                                </div>
                            </div>
                             <div class="form-group">
                                <label class="col-sm-2 control-label">商品描述信息：</label>
                                <div class="col-sm-9">
                                	<script id="editor_goods" name="goods" type="text/plain" style="width:100%;height:300px;"></script>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                	<input id="posterState" name="posterState" type="hidden" value="">
                                    <button class="btn btn-primary" id="saveBtn" type="button" disabled="disabled">保存</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
	<cs:resource type="js" value="jquery,jqueryui,bootstrap,system,sweetalert,validate,icheck,ueditor,dimage,fileinput,datetimepicker" />
<script type="text/javascript">
	var files = new Array();//上传文件列表
	var bargain={
		type:'${type}',
		bargainId:'${data.bargainId}',
		startTime:'<fmt:formatDate value="${data.startTime}" pattern="yyyy-MM-dd" />',
		endTime:'<fmt:formatDate value="${data.endTime}" pattern="yyyy-MM-dd" />',
		rules:'${data.rules}',
		goods:'${data.goods}',
		posterImages:'${data.poster.image}'
	}
	
	$(document).ready(function() {
		//设置复选框样式
		var box = $("input[name='accountId']");
		if (box != null && box.length == 1) {
			 $("input[name='accountId']").attr('checked', true);
		}
		
		//实例化百度编辑器
		var editor_rules = UE.getEditor('editor_rules');
		//对编辑器的操作最好在编辑器ready之后再做
		editor_rules.ready(function() {
			if(bargain.rules!=''){
			    editor_rules.setContent(bargain.rules);
			}
		});
		var editor_goods = UE.getEditor('editor_goods');
		//对编辑器的操作最好在编辑器ready之后再做
		editor_goods.ready(function() {
			if(bargain.goods!=''){
			    editor_goods.setContent(bargain.goods);
			}
		});
		
		//美化表单
		$(".i-checks").iCheck({
			checkboxClass : "icheckbox_square-green",
			radioClass : "iradio_square-green",
		})
		//初始化文件上传
		$("#file-5").fileinput({
			language: 'zh', //设置语言
               uploadUrl: "http://localhost:8080/shabao-test/file/upload", //上传的地址
               allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
               uploadExtraData:{'dir': '/resources/upload/poster/', 'type':'image'},
               uploadAsync: true, //默认异步上传
               showUpload: false, //是否显示上传按钮
               showRemove : true, //显示移除按钮
               showPreview : true, //是否显示预览
               //showCaption: false,//是否显示标题
               browseClass: "btn btn-primary", //按钮样式     
               dropZoneEnabled: true,//是否显示拖拽区域
               //minImageWidth: 50, //图片的最小宽度
               //minImageHeight: 50,//图片的最小高度
               //maxImageWidth: 200,//图片的最大宽度
               //maxImageHeight: 300,//图片的最大高度
               //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
               //minFileCount: 0,
               maxFileCount: 10, //表示允许同时上传的最大文件个数
               enctype: 'multipart/form-data',
               validateInitialCount:true,
               previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
               msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！"
		}).on("filebatchselected", function(event, files) {
	           $(this).fileinput("upload");
	       }).on("fileuploaded", function(event, data) {
	        if(data.response){
	        	files.push(data.response.url);
	        }
	        $('#posterimage').val(files.join(','));
	   	}).on('filesuccessremove', function(event, id) {
	   		files= [];
	   	    $('#file-5').fileinput('clear');
	   	    $('#posterimage').val(files.join(','));
	   	}).on('filecleared', function(event, key) {
	   		//清空
	   		files= [];
	   		$('#posterimage').val(files.join(','));
	   	}).on('fileerror', function(event, data, msg) {
	   		console.log("fileinput错误");
	   	});
		if(bargain.posterImages!=''){
			var images=bargain.posterImages.split(',');
			for (var i=0;i<images.length;i++){
				images[i]="<img src='"+window.webroot+images[i]+"' class='file-preview-image' alt='海报' style='max-width:200px;' title='海报'>"
			}
			$('#file-5').fileinput('refresh', {
	               //uploadExtraData: { id: '1' },
	               initialPreview: images //预览
	           });
		}
		/* 
		$('#file-5').fileinput('refresh', {
               //uploadExtraData: { id: '1' },
               initialPreview: [ //预览图片的设置
                   "<img src='"+window.webroot+"/resources/upload/poster/20160831/20160831145413_891.png' class='file-preview-image' alt='肖像图片' title='肖像图片'>",
               ],
           });
		 */
		//时间初始化
		$('#startTime').datetimepicker({
			format:'Y-m-d',
			minDate:new Date(),
			scrollMonth:false,
			value:bargain.startTime!=''?bargain.startTime:new Date(),
			onShow:function( ct ){
				this.setOptions({
					maxDate:$('#endTime').val()?$('#endTime').val():false
				})
			},
			timepicker:false
		});
		$('#endTime').datetimepicker({
			format:'Y-m-d',
			minDate:new Date(),
			scrollMonth:false,
			value:bargain.endTime,
			onShow:function( ct ){
				this.setOptions({
			    	minDate:$('#startTime').val()?$('#startTime').val():false,
			   	})
			},
			timepicker:false
		});
		$.datetimepicker.setLocale('zh');
		initBtn();//初始化按钮
		
	})
	//end
		
	//初始化按钮
	function initBtn(){
		$bargain=bargain;
		if(bargain.type==''||bargain.type=='add'){
			$bargain.url=window.webroot+"/admin/wechat/bargain/add";
		}else{
			$bargain.url=window.webroot+"/admin/wechat/bargain/"+bargain.bargainId+"/update";
		}
		
		$('#saveBtn').click(function() {
			if($bargain.posterImages!=$('#posterimage').val()&$('#posterimage').val()!=''){
				$('#posterState').val('1');
			}
			swal({
				title : "您确定要保存吗？",
				text : "您确定要保存这条数据？",
				type : "warning",
				showCancelButton : true,
				closeOnConfirm : false,
				confirmButtonText : "是的，我要保存",
				confirmButtonColor : "#DD6B55"
			}, function() {
				var fromdata = $("#commentForm").serialize();
				$.ajax({
					//url : window.webroot+"/admin/wechat/bargain/add",
					url : $bargain.url,
					type : "POST",
					data : fromdata,
					dataType : "json",
				}).done(function(data) {
					if (data.success) {
						swal("操作成功!", "已成功保存数据！", "success");
						window.location.href =window.webroot+"/admin/wechat/bargain/list";
					} else {
						swal("操作失败!", data.message, "error");
					}
				}).error(function(data) {
					swal("OMG", "操作失败了!", "error");
				});
			});
		})
		$('#saveBtn').removeAttr('disabled');
	}
	</script>
</body>

</html>
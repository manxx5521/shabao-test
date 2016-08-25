<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>添加文章</title>
<%@include file="../../context/head.jsp"%>
<%@include file="../../system/common.jsp"%>
<cs:resource type="css" value="jquery,bootstrap,system,sweetalert,icheck" />
</head>
<body class="gray-bg">
   <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>文章</h5>
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
                        <h5>创建文章</h5>
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
                                <label class="col-sm-2 control-label">标题：</label>
                                <div class="col-sm-9">
                                    <input id="title" name="title" minlength="4" type="text" class="form-control" value="${data.news.title}" required="" aria-required="true">
                                </div>
                            </div>
                             <div class="form-group">
                                <label class="col-sm-2 control-label">封面：</label>
                                <div class="col-sm-9" id="thumb_media_id">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">是否显示封面</label>
                                <div class="col-sm-10">
                                    <label class="radio-inline  i-checks">
                                        <input type="radio" name="showCoverPic" value="1"> 是</label>
                                     <label class="radio-inline  i-checks">
                                        <input type="radio" name="showCoverPic" value="0" checked="checked">否</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">摘要：</label>
                                <div class="col-sm-9">
                                    <input id="digest" name="digest" minlength="4" type="text" value="${data.news.digest}" class="form-control" required="" aria-required="true">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">内容：</label>
                                <div class="col-sm-9">
                                	<script id="editor" name="content" type="text/plain" style="width:100%;height:400px;"></script>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                	<input id="media_id" name="media_id" type="hidden" value="">
                                    <button class="btn btn-primary" id="saveBtn" type="button">保存</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
	<cs:resource type="js" value="jquery,jqueryui,bootstrap,system,sweetalert,validate,icheck,ueditor,dimage" />
	<script type="text/javascript">
		$(document).ready(function() {
			var valdata={};
			var img_id='${data.image.id}';
			var img_readonly=false;
			if(img_id!=''){
				valdata.id=img_id;
				valdata.text='${data.image.text}';
				valdata.url='${data.image.url}';
				img_readonly=true;
			}
			//封面图片选择
			$('#thumb_media_id').dimage({
				name:'thumbMediaId',
				element_id:'100001',
				value:valdata,
				readonly:img_readonly,
				data:function(){
					var data={};
					var accountId= $("input[name='accountId']:checked").val();
					data.accountId=accountId;
					return data;
				},
				validation:function(){
					var accountId= $("input[name='accountId']:checked").val();
					if(accountId==null||accountId==""||accountId==undefined){
						cbox.alert("请先选择帐号");
						return false;
					}
				},
				resdata:false,
				rescode:function(){
					return $("input[name='accountId']:checked").val();
				}
			});
			//设置复选框样式
			var box = $("input[name='accountId']");
			if (box != null && box.length == 1) {
				 $("input[name='accountId']").attr('checked', true);
			}
			
			
			//实例化百度编辑器
			var ue = UE.getEditor('editor');
			//对编辑器的操作最好在编辑器ready之后再做
			ue.ready(function() {
				var content='${data.news.content}';
				if(content!=''){
					//设置编辑器的内容
				    ue.setContent(content);
				    ue.setDisabled();
				}
			});
			//初始化值
			var showCoverPic='${data.news.showCoverPic}';
			if(showCoverPic!=''&&showCoverPic!=null){
				var pics = $("input[name='showCoverPic']");
				$('input[name="showCoverPic"]').each(function() {
					var data = $(this).attr('value');
					if (data == showCoverPic) {
						$(this).attr('checked','checked');//  设置为默认选择的值
					}else{
						$(this).removeAttr('checked');
					}
					$(this).attr('disabled','disabled');
				});
				$("input[name='accountId']").attr('disabled', 'disabled');
				$('#digest').attr('readonly',true);
				$('#title').attr('readonly',true);
				$('#saveBtn').attr('disabled',true);
			}
			
			//美化表单
			$(".i-checks").iCheck({
				checkboxClass : "icheckbox_square-green",
				radioClass : "iradio_square-green",
			})
			

			// 提交表单
			$('#saveBtn').click(function() {
				swal({
					title : "您确定要保存吗？",
					text : "您确定要添加这条数据？",
					type : "warning",
					showCancelButton : true,
					closeOnConfirm : false,
					confirmButtonText : "是的，我要保存",
					confirmButtonColor : "#DD6B55"
				}, function() {
					var fromdata = $("#commentForm").serialize();
					$.ajax({
						url : "./add.html",
						type : "POST",
						data : fromdata,
						dataType : "json",
					}).done(function(data) {
						if (data.success) {
							swal("操作成功!", "已成功保存数据！", "success");
							window.location.href = './list.html';
						} else {
							swal("操作失败!", data.message, "error");
						}
					}).error(function(data) {
						swal("OMG", "操作失败了!", "error");
					});
				});
			})
		})
	</script>
</body>

</html>
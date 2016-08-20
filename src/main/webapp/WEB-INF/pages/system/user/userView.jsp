<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../../context/head.jsp"%>
<%@include file="../common.jsp"%>
<title>沙包娱乐 - 用户添加</title>
<cs:resource type="css" value="jquery,bootstrap,system,jstree,bootbox" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>用户添加</h5>
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
                                <label class="col-sm-2 control-label">部门：</label>
                                <div class="col-sm-9">
                                    <div id="depart"></div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">用户名：</label>
                                <div class="col-sm-9">
                                    <input id="loginName" name="title" minlength="6" type="text" class="form-control" required="required" aria-required="true">
                                </div>
                            </div>
                             <div class="form-group">
                                <label class="col-sm-2 control-label">密码：</label>
                                <div class="col-sm-9">
                                    <input id="password" name="title" minlength="6" type="text" class="form-control" required="required" aria-required="true">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">确认密码：</label>
                                <div class="col-sm-9">
                                    <input id="password2" name="title" minlength="6" type="text" class="form-control" required="required" aria-required="true">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">QQ：</label>
                                <div class="col-sm-9">
                                    <input id="qq" name="title" minlength="6" type="text" class="form-control" required="required" aria-required="true">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                    <button class="btn btn-primary" id="savebtn" type="button">保存</button>
                                </div>
                                <div class="col-sm-4 col-sm-offset-2">
                                    <button class="btn btn-primary" id="savebtn1" type="button" data-toggle="modal" data-target="#myModal">test</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
	</div>
	
	<!-- 111 -->
	<div class="modal inmodal" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content animated bounceInRight">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<i class="fa fa-laptop modal-icon"></i>
					<h4 class="modal-title">窗口标题</h4>
					<small class="font-bold">这里可以显示副标题。 </small>
				</div>
				<div class="font-bold">
					<div class="modal-body">
						<p>
							<strong>H+</strong>
							是一个完全响应式，基于Bootstrap3.3.6最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术，她提供了诸多的强大的可以重新组合的UI组件，并集成了最新的jQuery版本(v2.1.1)，当然，也集成了很多功能强大，用途广泛的jQuery插件，她可以用于所有的Web应用程序，如网站管理后台，网站会员中心，CMS，CRM，OA等等，当然，您也可以对她进行深度定制，以做出更强系统。
						</p>
						<div class="form-group">
							<label>Email</label> <input type="email" placeholder="请输入您的Email"
								class="form-control">
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary">保存</button>
					</div>
				</div>
			</div>
			<small class="font-bold"> </small>
		</div>
		<small class="font-bold"></small>
  </div>
	<cs:resource type="js" value="jquery,bootstrap,jqueryui,system,jstree,dtree,validate" />
	<script>
		$(document).ready(function() {
			//部门树
			$('#depart').dtree({
				name:'departId',
				element_id:'100000'
			});
			//添加验证
			$('#form1').validate({
				//debug:true,
				rules:{
					departId:{
						required:true,
						minlength:5,
						maxlength:5
					},
					departName:{
						required:true,
						maxlength:50
					},
					departType:{
						required:true,
						minlength:3,
						maxlength:3
					},
					parentDepartId:{
						required:true,
						minlength:1,
						maxlength:5
					},
					orderNo:{
						minlength:1,
						maxlength:3
					}
				},
				message:{
					departName:{
						required:'部门名称必须填写',
						maxlength:'部门名不能超过50个字'
					},
				}
			});
		});
		//保存		
		$('#savebtn').click(function(){
			if(!$("#commentForm").valid())
				return false;
			$('#savebtn').attr('disabled');
			var formdata = $(".form-horizontal").serialize();
			$.ajax({
				type : 'POST',
				url : '${ctx}/admin/depart/'+$('#departId').val()+'/'+operation,
				data : formdata,
				dataType : 'json',
				success : function(result) {
					if(result.success){
						switchbth('true');
						bootbox.alert(result.message);
						getDepartInfo(result.data);
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
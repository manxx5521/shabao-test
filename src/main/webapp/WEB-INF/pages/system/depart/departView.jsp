<%@page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../../context/head.jsp"%>
<%@include file="../common.jsp"%>
<title>沙包娱乐 - 登录</title>
<cs:resource type="css" value="jquery,bootstrap,system,jstree,bootbox" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<p>
							<button type="button" class="btn btn-info">添加同级</button>
							<button type="button" class="btn btn-info">添加下级</button>
							<button type="button" class="btn btn-info">修改</button>
							<button type="button" class="btn btn-info">失效</button>
						</p>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-6">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>组织结构树</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a> <a class="close-link"> <i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<div id="using_json"></div>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>组织机构信息</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a> <a class="close-link"> <i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<form method="post" class="form-horizontal" id="form1">
							<input type="hidden" name="departId" id="departId" value="${departId}"/>
							<input type="hidden" name="operation" id="operation" value="${operation}"/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">部门名称</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="departName" id="departName" value="${departName}" readonly="readonly">
                                </div>
                           	</div>
                           	<div class="form-group">
                                <label class="col-sm-3 control-label">部门类型</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="departType" id="departType" value="${departType}" readonly="readonly">
                                </div>
                           	</div>
                           	<div class="form-group">
                                <label class="col-sm-3 control-label">父级部门</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="parentDepartId" id="parentDepartId" value="${parentDepartId}" readonly="readonly">
                                </div>
                           	</div>
                           	<div class="form-group">
                                <label class="col-sm-3 control-label">部门等级</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="departLevel" id="departLevel" value="${departLevel}" readonly="readonly">
                                </div>
                           	</div>
                           	<div class="form-group">
                                <label class="col-sm-3 control-label">子部门数量</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="childNum" id="childNum" value="${childNum}" readonly="readonly">
                                </div>
                           	</div>
                           	<div class="form-group">
                                <label class="col-sm-3 control-label">排序</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control"  name="orderNo" id="orderNo" value="${orderNo}" readonly="readonly">
                                </div>
                           	</div>
                        	<div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <div class="col-sm-12 col-sm-offset-2">
                                    <button class="btn btn-primary" type="button" id="editbtn">修改</button>
                                    <button class="btn btn-primary" type="button" id="addSamebtn">增加同级</button>
                                    <button class="btn btn-primary" type="button" id="addLowerbtn">增加下级</button>
                                    <button class="btn btn-primary" type="button" id="savebtn" style="display:none;">保存</button>
                                    <button class="btn btn-white" type="button" id="delbtn" style="display:none;">取消</button>
                                </div>
                            </div>
                        </form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<cs:resource type="js" value="jquery,bootstrap,system,jstree,validate" />
	<script>
		$(document).ready(function() {
			getTree();
			//给部门按钮添加点击事件
			$('#editbtn').click(function(){
				$('#operation').val('editDepart');
				switchbth('none');
			});
			$('#addSamebtn').click(function(){
				$('#addSamebtn').val('addSameDepart');
				formclear();
				switchbth('none');
			});
			$('#addLowerbtn').click(function(){
				$('#operation').val('addLowerDepart');
				var departId=$('#departId').val();
				var level=$('#departLevel').val();
				formclear();
				switchbth('none');
				$('#departId').val(departId);
				$('#parentDepartId').val(departId);
				$('#parentDepartId').attr('readonly','readonly');
				$('#childNum').val('0').attr('readonly','readonly');
				$('#departLevel').val(level+1).attr('readonly','readonly');
			});
			//给部门按钮添加点击事件
			$('#delbtn').click(function(){
				getDepartInfo($('#departId').val());
				switchbth('true');
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
		
		function getTree(){
			//加载部门数数据
			$.ajax({
				type : "POST",
				url : "${ctx}/tree/100000/list",
				dataType : "json",
				success : function(data) {
					if (data.success) {
						$("#using_json").jstree({
							"core" : {
								"check_callback" : true,
								"data" : data.data
							},
						"plugins" : [ "types", "dnd" ],
						"types" : {
							"default" : {
								"icon" : "fa fa-folder"
							},
							"html" : {
								"icon" : "fa fa-file-code-o"
							},
							"svg" : {
								"icon" : "fa fa-file-picture-o"
							},
							"css" : {
								"icon" : "fa fa-file-code-o"
							},
							"img" : {
								"icon" : "fa fa-file-image-o"
							},
							"js" : {
								"icon" : "fa fa-file-text-o"
							}
						},
						}).on('changed.jstree', function (e, data) {
						    var i, j, r;
						    for(i = 0, j = data.selected.length; i < j; i++) {
						      r=data.instance.get_node(data.selected[i]).id;
						    }
						    getDepartInfo(r);
						    switchbth('true');
						 });
					} else {
						if (data.message != null && data.message != "") {
							alert(data.message);
						} else {
							alert('保存数据失败！');
						}
					}
				},
				error : function(info) {
					alert(info.responseText);
					alert(info);
				}
			});
		}
		
		function getDepartInfo(depart_id){
			$.ajax({
				type : "POST",
				url : '${ctx}/admin/depart/'+depart_id+'/info',
				dataType : "json",
				success : function(result) {
					if (result.success) {
						var depart=result.data;
						$('#departId').val(depart.departId);
						$('#departName').val(depart.departName);
						$('#departType').val(depart.departType);
						$('#parentDepartId').val(depart.parentDepartId);
						$('#departLevel').val(depart.departLevel);
						$('#childNum').val(depart.childNum);
						$('#orderNo').val(depart.orderNo);
					} else {
						if (result.message != null && result.message != "") {
							alert(result.message);
						} else {
							alert('保存数据失败！');
						}
					}
				},
				error : function(info) {
					alert(info.responseText);
					alert(info);
				}
			});
		}
		
		function switchbth(state){
			if(state=='none'){
				$('#editbtn').css('display','none');
				$('#addSamebtn').css('display','none');
				$('#addLowerbtn').css('display','none');
				$('#savebtn').css('display','inline');
				$('#delbtn').css('display','inline');
				switchReadonly(true);
			}else{
				$('#editbtn').css('display','inline')
				$('#addSamebtn').css('display','inline')
				$('#addLowerbtn').css('display','inline')
				$('#savebtn').css('display','none')
				$('#delbtn').css('display','none')
				switchReadonly(false);
			}
		}
		
		$('#savebtn').click(function(){
			if(!$("#form1").valid())
				return false;
			$('#savebtn').attr('disabled');
			var operation=$('#operation').val();
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
		
		//切换readonly属性
		function switchReadonly(){
			var b=$('#departName').attr('readonly');
			switchReadonly(b)
			
		}
		function switchReadonly(b){
			if(b){
				$('#departName').removeAttr('readonly');
				$('#departType').removeAttr('readonly');
				$('#parentDepartId').removeAttr('readonly');
				$('#departLevel').removeAttr('readonly');
				$('#childNum').removeAttr('readonly');
				$('#orderNo').removeAttr('readonly');
			}else{
				$('#departName').attr('readonly','readonly');
				$('#departType').attr('readonly','readonly');
				$('#parentDepartId').attr('readonly','readonly');
				$('#departLevel').attr('readonly','readonly');
				$('#childNum').attr('readonly','readonly');
				$('#orderNo').attr('readonly','readonly');
			}
		}
		function formclear(){
			$('#departId').val('');
			$('#departName').val('');
			$('#departType').val('');
			$('#parentDepartId').val('');
			$('#departLevel').val('');
			$('#childNum').val('');
			$('#orderNo').val('');
		}
		
	</script>
</body>
</html>
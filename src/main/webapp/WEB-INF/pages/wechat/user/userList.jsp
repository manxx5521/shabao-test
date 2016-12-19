<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>砍价列表</title>
	<%@include file="../../context/head.jsp"%>
    <%@include file="../../system/common.jsp"%>
	<cs:resource type="css" value="jquery,bootstrap,system,bootbox,dataTables,select2" />
	<base target="_blank">
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>砍价列表 <small>查询，修改</small></h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="table_data_tables.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="table_data_tables.html#">选项1</a>
                                </li>
                                <li><a href="table_data_tables.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                    	<form id ="form1" class="form-horizontal form-label-left" action="./list" target="_self">
                    		<div class="row search">
                    			<div class="col-md-12 col-sm-12 col-xs-12">
                    				<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
		                                <div class="form-group">
		                                    <label class="control-label col-md-4 col-sm-4 col-xs-12">帐号：</label>
		                                    <div class="col-md-8 col-sm-8 col-xs-12">
		                                        <select id="accountId" name="accountId"></select>
		                                    </div>
		                                </div>
		                            </div>
		                            <button class="btn btn-primary col-sm-offset-11" type="button" onclick="$('#form1').submit();">执行查询</button>
                            	</div>
                            </div>
                    	</form>
						<div class="">
                            <a id="syncBtn" href="javascript:void(0);" class="btn btn-primary btn-sm">同步用户</a>
                        </div>
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
                                    <th>应用名</th>
                                    <th>openid</th>
                                    <th>昵称</th>
                                    <th>性别</th>
                                    <th>省份</th>
                                    <th>城市</th>
                                    <th>是否关注</th>
                                    <th>更新时间</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach var="r" items="${data}" varStatus="idx">
                                <tr class="gradeX">
                                    <td> ${r.appName}</td>
                                    <td> ${r.openid}</td>
                                    <td> ${r.nickname}</td>
                                    <td><c:if test="${r.sex==1}">男</c:if><c:if test="${r.sex==0}">女</c:if></td>
                                    <td> ${r.province}</td>
                                    <td> ${r.city}</td>
                                    <td><c:if test="${r.type==1}">关注</c:if><c:if test="${r.type==0}">取消关注</c:if></td>
                                    <td class="center"><fmt:formatDate value="${r.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" /> </td>
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <cs:resource type="js" value="jquery,bootstrap,system,bootbox,jeditable,dataTables,jqueryui,select2,dselect2" />
    <script>
    	//下面用来显示上下页标签
        $(document).ready(function(){
        	$(".dataTables-example").dataTable({
        		"bSort": false, //排序功能
        	});
        	//下拉框
        	$('#accountId').dselect2({
        		element_id:100003,
        		value:'${accountId}'
        	});
        	
        	$('#syncBtn').click(function(){
        		var account_id=$('#accountId').val();
            	if(!account_id){
            		cbox.alert('请选择同步帐号');
            		return false;
            	}
            	$('#syncBtn').attr('disabled','disabled');
            	$.ajax({
    				  type: 'POST',
    				  url: window.webroot+'/admin/wechat/user/'+account_id+'/sync',
    				  data: {},
    				  dataType:'json',
    				  success: function(result){
    					  if(result.success){
    						  cbox.info(result.message);
    					  }else{
    						  cbox.alert(result.message);
    					  }
    					  $('#syncBtn').removeAttr('disabled')
    				 }
    			});
        	})
        });
    </script>
</body>

</html>
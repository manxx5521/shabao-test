<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>砍价列表</title>
	<%@include file="../../../context/head.jsp"%>
    <%@include file="../../../system/common.jsp"%>
	<cbox:resource type="css" value="jquery,bootstrap,system,bootbox,dataTables,select2,dselect2" />
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
		                            <!-- 
	                                <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
		                                <div class="form-group">
		                                    <label class="control-label col-md-4 col-sm-4 col-xs-12">地市：</label>
		                                    <div class="col-md-8 col-sm-8 col-xs-12">
		                                        <input name="element_id" class="form-control" type="text" placeholder="Default Input">
		                                    </div>
		                                </div>
		                            </div>
		                            <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
		                                <div class="form-group">
		                                    <label class="control-label col-md-4 col-sm-4 col-xs-12">归属部门：</label>
		                                    <div class="col-md-8 col-sm-8 col-xs-12">
		                                        <select class="form-control">
		                                            <option>Choose option</option>
		                                            <option>Option one</option>
		                                            <option>Option two</option>
		                                            <option>Option three</option>
		                                            <option>Option four</option>
		                                        </select>
		                                    </div>
		                                </div>
		                            </div>
		                             -->
		                            <button class="btn btn-primary col-sm-offset-11" type="button" onclick="$('#form1').submit();">执行查询</button>
                            	</div>
                            </div>
                    	</form>
						<div class="">
                            <a onclick="add();" href="javascript:void(0);" class="btn btn-primary btn-sm">添加</a>
                        </div>
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
                                    <th>砍价标识</th>
                                    <th>标题</th>
                                    <th>应用</th>
                                    <th>总价</th>
                                    <th>底价</th>
                                    <th>单次砍掉最高</th>
                                    <th>砍价次数</th>
                                    <th>销量</th>
                                    <th>参与人数</th>
                                    <th>创建时间</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach var="r" items="${data}" varStatus="idx">
                                <tr class="gradeX">
                                    <td> ${r.bargainId}</td>
                                    <td> <a href="#" target="_self">${r.bargainName}</a></td>
                                    <td> ${r.appName}</td>
                                    <td> ￥${r.totalPrice}</td>
                                    <td> ￥${r.minPrice}</td>
                                    <td> ￥${r.onePrice}</td>
                                    <td> ${r.bargainNum}</td>
                                    <td> ${r.saleNum}</td>
                                    <td> ${r.userNum}</td>
                                    <td class="center"><fmt:formatDate value="${r.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /> </td>
                                    <td> <a href="${ctx}/admin/wechat/bargain/${r.bargainId}/detail" target="_self">编辑</a></td>
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <cbox:resource type="js" value="jquery,bootstrap,system,bootbox,jeditable,dataTables,jqueryui,select2,dselect2" />
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
        });
        function add(){
        	window.location.href = window.webroot+'/admin/wechat/bargain/init';
        }
        
    </script>
</body>

</html>
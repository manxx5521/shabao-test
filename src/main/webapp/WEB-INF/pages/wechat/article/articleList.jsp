<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>添加文章</title>
	<%@include file="../../context/head.jsp"%>
    <%@include file="../../system/common.jsp"%>
	<cbox:resource type="css" value="jquery,bootstrap,system,bootbox,dataTables,select2" />
	<base target="_blank">
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>文章列表 <small>显示，修改</small></h5>
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
                    	<button type="submit" class="btn btn-primary col-sm-offset-11">查询</button>
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
                            <a onclick="add();" href="javascript:void(0);" class="btn btn-primary btn-sm">添加</a>
                        </div>
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
                                    <th>文章ID</th>
                                    <th>文章标题</th>
                                    <th>创建时间</th>
                                    <th>创建人</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach var="r" items="${dataList}" varStatus="idx">
                                <tr class="gradeX">
                                    <td> ${r.articleId}</td>
                                    <td> <a href="${ctx}/admin/wechat/article/${r.articleId}/detail" target="_self">${r.news.title}</a></td>
                                    <td class="center"><fmt:formatDate value="${r.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /> </td>
                                    <td > ${r.createName}</td>
                                </tr>
                                </c:forEach>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th>文章ID</th>
                                    <th>文章标题</th>
                                    <th>创建时间</th>
                                    <th>创建人</th>
                                </tr>
                            </tfoot>
                        </table>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <cbox:resource type="js" value="jquery,bootstrap,system,bootbox,jeditable,dataTables,jqueryui,select2,dselect2" />
    <script>
        function add(){
        	window.location.href = '${ctx}/admin/wechat/article/init';
        }
      
	$(document).ready(function(){
		//下拉框
		$('#accountId').dselect2({
    		element_id:100003,
    		value:'${accountId}'
    	});
		
		//table 初始化
		$(".dataTables-example").dataTable({
    		"bSort": false, //排序功能
    	});
		
	})
    </script>
</body>

</html>
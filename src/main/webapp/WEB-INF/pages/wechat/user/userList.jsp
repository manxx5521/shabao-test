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
	<cs:resource type="css" value="jquery,bootstrap,system,bootbox,dataTables" />
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
                    	<form class="form-inline">
  							<div class="form-group col-xs-4">
    							<label for="exampleInputName2" class="col-xs-5 control-label">微信帐号</label>
    							<input type="text" class="form-control col-xs-7" id="exampleInputName2" placeholder="Jane Doe">
  							</div>
                    	</form>
                    	<button type="submit" class="btn btn-primary col-sm-offset-11">查询</button>
						<div class="">
                            <a onclick="add();" href="javascript:void(0);" class="btn btn-primary btn-sm">添加</a>
                           <!--  <a onclick="addByUe();" href="javascript:void(0);" class="btn btn-primary ">添加</a> -->
                        </div>
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
                                    <th>应用名</th>
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
    <cs:resource type="js" value="jquery,bootstrap,system,bootbox,jeditable,dataTables" />
    <script>
    	//下面用来显示上下页标签
        $(document).ready(function(){
        	$(".dataTables-example").dataTable({
        		"bSort": false, //排序功能
        	});
        });
        function add(){
        	window.location.href = window.webroot+'/admin/wechat/bargain/init';
        }
    </script>
</body>

</html>
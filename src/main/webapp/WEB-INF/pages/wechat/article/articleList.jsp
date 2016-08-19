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
	<cs:resource type="css" value="jquery,bootstrap,system,bootbox,dataTables" />
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
                    	<form class="form-inline">
  							<div class="form-group col-xs-4">
    							<label for="exampleInputName2" class="col-xs-5 control-label">微信帐号</label>
    							<input type="text" class="form-control col-xs-7" id="exampleInputName2" placeholder="Jane Doe">
  							</div>
                    	</form>
                    	<button type="submit" class="btn btn-primary col-sm-offset-11">查询</button>
						<div class="">
                            <a onclick="add();" href="javascript:void(0);" class="btn btn-primary ">添加</a>
                           <!--  <a onclick="addByUe();" href="javascript:void(0);" class="btn btn-primary ">添加</a> -->
                            <a onclick="select2();" href="javascript:void(0);" class="btn btn-primary ">select2插件</a>
                        </div>
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
                                    <th>文章标题</th>
                                    <th>创建时间</th>
                                    <th>创建人</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach var="r" items="${dataList}" varStatus="idx">
                                <tr class="gradeX">
                                    <td> ${r.title}</td>
                                    <td class="center"><fmt:formatDate value="${r.create_time}" pattern="yyyy-MM-dd HH:mm:ss" /> </td>
                                    <td > ${r.user_name}</td>
                                </tr>
                                </c:forEach>
                            </tbody>
                            <tfoot>
                                <tr>
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
    <cs:resource type="js" value="jquery,bootstrap,system,bootbox,jeditable,dataTables" />
    <script>
    	//下面用来显示上下页标签
        $(document).ready(function(){$(".dataTables-example").dataTable();var oTable=$("#editable").dataTable();oTable.$("td").editable("../example_ajax.php",{"callback":function(sValue,y){var aPos=oTable.fnGetPosition(this);oTable.fnUpdate(sValue,aPos[0],aPos[1])},"submitdata":function(value,settings){return{"row_id":this.parentNode.getAttribute("id"),"column":oTable.fnGetPosition(this)[2]}},"width":"90%","height":"100%"})});
        //function fnClickAddRow(){$("#editable").dataTable().fnAddData(["Custom row","New row","New row","New row","New row"])};
        function add(){
        	window.location.href = '${ctx}/admin/wechat/article/init';
        }
        function select2(){
        	window.location.href = '${webRoot}/select/demo.html';
        }
    </script>
</body>

</html>
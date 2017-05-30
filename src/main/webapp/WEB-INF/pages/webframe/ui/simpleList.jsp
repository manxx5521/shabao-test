<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>${data.title}</title>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
	<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<c:set var="ctx" value="${pageContext.request.contextPath}" />
	<script src="${ctx}/config/jsconfig"></script>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="renderer" content="webkit">
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	${data.headerCSS}
	${data.headerBeforeScript}
	<base target="_blank">
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>${data.title} <small>查询</small></h5>
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
                    				<!-- 
                    				<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
		                                <div class="form-group">
		                                    <label class="control-label col-md-4 col-sm-4 col-xs-12">帐号：</label>
		                                    <div class="col-md-8 col-sm-8 col-xs-12">
		                                        <select id="accountId" name="accountId"></select>
		                                    </div>
		                                </div>
		                            </div>
		                             -->
		                             ${data.templateHtml}
		                            <button class="btn btn-primary col-sm-offset-11" type="button">执行查询</button>
                            	</div>
                            </div>
                    	</form>
						<div class="">
						<c:forEach var="r" items="${data.buttons}" varStatus="idx">
							<c:if test="${r.buttonValue!='BUTTON_QUERY'}">
								<a href="javascript:void(0);" onclick="clickButton('${r.buttonId}')" class="btn btn-primary btn-sm"> ${r.buttonName}</a>
						　　</c:if>
                            
                        </c:forEach>
                        </div>
                        ${data.reportHtml}
                    </div>
                </div>
            </div>
        </div>
    </div>
    ${data.headerScript}
    <script>
    	${data.reportScript}
		
    	function clickButton(buttonId){
    		$.ajax({
				  type: 'POST',
				  url: './list/'+buttonId+'/function',//+$('#form1').serialize()
				  data: {},
				  dataType:'json',
				  success: function(result){
					  if(result.success){
						  if(!!result.script){
							  eval(result.script);//优先执行脚本
						  }else if(!!result.message){
							 cbox.info(result.message);
						  }
					  }else{
						  if(!!result.message){
							  cbox.alert(result.message);
						  }
					  }
				 }
			});
    	}
    </script>
</body>

</html>
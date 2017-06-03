<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>视图</title>
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
                        <form id ="form1" class="form-horizontal form-label-left" action="./list" target="_self">
                    		<div class="row">
                    			<div class="col-md-12 col-sm-12 col-xs-12">
		                             ${data.templateHtml}
                            	</div>
                            </div>
                    	</form>
                        <div class="form-group">
                           <div class="col-sm-4 col-sm-offset-2">
                           <c:forEach var="r" items="${data.buttons}" varStatus="idx">
								<button class="btn btn-primary" onclick="clickButton('${r.buttonId}')" type="button">${r.displayName}</button>
                        	</c:forEach>
                                
                           </div>
                       </div>
                       <div class="row"></div>
                    </div>
                </div>
            </div>
        </div>
	</div>
	${data.headerScript}
    <script>
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
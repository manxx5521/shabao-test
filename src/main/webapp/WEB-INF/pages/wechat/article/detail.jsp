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
                        <p>暂时不提文本编辑器，你可以通过以下提供的网址来编辑文本内容，然后点击编辑器左上角的html按钮打开代码，然后将代码粘贴到下边的内容处。</p>
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
                                        <input type="radio" name="account_ids" value=" ${r.accountId}"> ${r.appName}</label>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">标题：</label>
                                <div class="col-sm-9">
                                    <input id="cname" name="title" minlength="4" type="text" class="form-control" required="" aria-required="true">
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
                                    <button class="btn btn-primary" type="button">保存</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <cs:resource type="js" value="jquery,bootstrap,system,sweetalert,validate,icheck,ueditor" />
  	<!-- baidu ue begin-->
  	<script type="text/javascript" charset="utf-8">
 	//	window.PROJECT_CONTEXT = '${webRoot}';
  	</script>
   <script type="text/javascript">
   $(document).ready(function() {
	   //设置复选框样式
	   var box= $(":radio");
	   if (box!=null&&box.length==1){
		   $(":radio").attr('checked', true);
	   }
	   $(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})
	   
	   	//实例化百度编辑器
    	var ue = UE.getEditor('editor');
	   
		// 提交表单
		$('.btn').click(function() {
			swal({ 
		        title: "您确定要保存吗？",  
		        text: "您确定要添加这条数据？",  
		        type: "warning", 
		        showCancelButton: true, 
		        closeOnConfirm: false, 
		        confirmButtonText: "是的，我要保存", 
		        confirmButtonColor: "#DD6B55" 
		    }, function() { 
		    	var fromdata = $("#commentForm").serialize();
		        $.ajax({ 
		            url: "articleAdd.html", 
		            type: "POST",
		            data : fromdata,
		            dataType : "json",
		        }).done(function(data) { 
		        	if (data.success) {
		        		swal("操作成功!", "已成功保存数据！", "success");
		        		window.location.href = './articleList.html';
		        	}else{
		        		swal("操作失败!",data.message, "error"); 
		        	}
		        }).error(function(data) { 
		            swal("OMG", "删除操作失败了!", "error"); 
		        }); 
		    }); 
		})
	})
   </script>
</body>

</html>
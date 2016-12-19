$(document).ready(function(){ 
	//提交表单
	$('.btn').click(function(){
		if (ch_input()) {
			var fromdata = $("#loginform").serialize();
			$.ajax({
				type : "POST",
				url : "loginIN.html",
				data : fromdata,
				dataType : "json",
				success : function(data) {
					debugger;
					if (data.success) {
						 window.location.href = './index.html';
					} else {
						if (data.message != null && data.message != "") {
							alert(data.message);
						} else {
							alert('保存数据失败！');
						}
					}
				},
				error : function(info) {
					console.log(info.responseText);
					console.log(info);
					cbox.alert("登录异常");
				}
			});
		}
	})
})

// 验证登录
function ch_input() {
	var msg = "\n登录提示信息：\n\n";
	if (document.all.loginform.user_id.value == ''
			| document.all.loginform.user_id.value == 'admin') {
		msg += "您输入的用户名为空，请重新填写并确认!\n";
		alert(msg);
		document.all.loginform.user_id.focus();
		return false;
	}
	if (document.all.loginform.password.value == ''
			| document.all.loginform.password.value == '密码') {
		msg += "您输入的密码为空,请重新填写并确认!\n";
		alert(msg);
		document.all.loginform.password.focus();
		return false;
	} 
	return true;
}
//系统统一弹出框
var cbox = {
	/** 提示框 */
	info : function(message) {
		return bootbox.alert(message);
	},
	/** 警告框 */
	alert : function(message) {
		bootbox.alert(message.toString());
		return false;
	},
	/** 错误框 */
	error : function(message) {
		return bootbox.alert(message);
	}

};
//jquery扩展
$.extend({
	/** 判断是否为空 */
	isNull : function(target) {
		if (typeof (target) == 'undefined' || null == target || '' === target
				|| 'null' == target || 'undefined' === target) {
			return true;
		} else {
			return false;
		}
	},
	/** 获得guid */
	getGuid : function(s) {
		function S4() {
			return (((1 + Math.random()) * 0x10000) | 0).toString(16)
					.substring(1);
		}
		return (S4() + S4() + S4() + S4());
	}
});

// 系统统一弹出框
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
	},
	/** 查询 */
	get : function(url, argu, callback) {
		this.ajax(url, 'get', argu, callback);
	},
	/** 新增 */
	post : function(url, argu, callback) {
		this.ajax(url, 'post', argu, callback);
	},
	// 删除
	del : function(url, argu, callback) {
		this.ajax(url, 'delete', argu, callback);
	},
	/** 修改 */
	put : function(url, argu, callback) {
		this.ajax(url, 'put', argu, callback);
	},
	/** 统一提交方法 */
	ajax : function(url, type, argu, callback, setting) {
		if ($.isNull(url)) {
			return false;
		}
		$.ajax({
			url : url,
			type : type, // GET
			async : true, // 或false,是否异步
			data : argu,
			// timeout:60000, //超时时间
			dataType : 'json', // 返回的数据格式：json/xml/html/script/jsonp/text
			contentType : 'application/json; charset=utf-8',
			beforeSend : function(xhr) {
				// console.log('发送前')
			},
			success : function(result) {
				if (result.success == true) {
					if (!$.isNull(result.message)) {
						cbox.info(result.message);
					}
					if ($.isNull(callback)) {
						callback(result);
					}
				} else {
					cbox.error(result.message);
				}
			},
			error : function(jqXHR, textStatus) {
				ufma.hideloading();
				var error = "";
				switch (jqXHR.status) {
				case 408:
					error = "请求超时";
					break;
				case 500:
					error = "服务器错误";
					break;
				default:
					break;
				}
				if (error != "") {
					cbox.error(error, function() {
					}, "error");
					return false;
				}
			},
			complete : function(data) {
				// 完成之后要操作的事情
			}
		});
	},
	// 点击其它部位时隐藏元素(未验证)
	domClickHideEle : function(selector, callback) {
		$(document)
				.on(
						'click',
						function(e) {// 这里监听div会不会有问题
							var e = e || window.event; // 浏览器兼容性
							if ($(e.target).closest(selector).length == 0
									&& $(e.target)
											.closest(+selector + '_popup').length == 0) {
								if (callback)
									callback();
							}
						});
	}

};
var asiainfoSelect2 = $.widget("ui.dselect2", {
	version : "1.0.0-beta.1",
	defaultElement : "<div>",
	delay : 300,
	options : {
		id : '',//元素选择器，无需传入可自动获取id
		element_id:'',	//必须
		name:'',//名字属性，非必传
		listeners : [],//当自己改变时，要通知刷新的列表[{selector:'#test2',type:'select'}]
		filters:[], //当自己刷新时，要传入的参数 [{selector:'#test1',name:'depart_id',type:'select'}]
		filtervals:{},//初始化时可能存在的过滤值{eparchy_code:'0472',area_code:'B001'}
		class:"form-control",
		value:'',//默认值
		readonly:false,
		disabled:false,
		ajaxData:function(){//Ajax请求数据要执行的函数,data将合并到请求参数
			var data={};
			return data;
		},
		url:function(){ //Ajax请求数据的URL
			return window.webroot+'/admin/ui/select/'+this.element_id;
		},
		validation:function(){ //获取数据时可能要做的验证
			return true;
		},
		hasall:false,//是否有请选择,select2有问题无法填true
		datadefault:{"id":"","text":"请选择"},//默认请选择数据
		minimumResultsForSearch: Infinity,  //隐藏搜索框
		//allowClear: true,
		/*templateSelection:function(state){
			var text='<span style="white-space:nowrap;">' + state.text + '</span> '
			return text;
		},*/
	},
	_create : function() {
		if(this.options.element_id==''){
			console.log('未获得元素id');
		}
		if(this.options.id==''){
			var id=this.element.context.id;
			this.options.id='#'+id;
		}
		if(this.options.name!=''){
			this.element.attr('name',this.options.name);
		}
		this.element.addClass(this.options.class);
		
		
		this._getSelectData(this.options,this.options.filtervals);
		
		this._on( $(this.options.id), {
			  "change":function( event ) {
				  this.fireListener()
			  }
		});
		if(this.options.readonly){
			$(this.options.id).prop("readonly", true);
		}
	},
	_getSelectData : function(options,params){
		//进行可能的数据验证
		var flag=this.options.validation();
		if(flag==false){
			return false;
		}
		$.ajax({
			type : 'POST',
			url : options.url(),
			data : params,
			context:this,
			dataType : 'json',
			success : function(result) {
				if (result.success) {
					var data=result.data;
					//如果使用请选择，添加一个默认元素
					if(this.options.hasall){
						data.unshift(this.options.datadefault);
					}
					if(this.readonly){
						if(this.options.value!=''){
							data.value=this.options.value;
							data = $.grep(data, function(val, key) {
								if(val.id==data.value){
									return true;
								}
								return false;
							}, false);
						}else{
							var arrayObj = new Array();
							arrayObj[0]=this.datadefault;
							data=arrayObj;
						}
					}
					this.options.data=data;
					$(this.options.id).val(null).empty();
					$(this.options.id).select2(this.options);
					
					if(this.options.value!=''){
						//$(this.options.id+" option[value='"+this.options.default_value+"']").attr("selected", true);
						//$(this.options.id).select2(this.options);
						$(this.options.id).select2('val',this.options.value);
					}
					var disabled=$(this.options.id).attr("disabled");
					if(disabled=='true'||disabled=='disabled'||this.options.disabled==true){
						$(this.options.id).prop("disabled", true); 
					}
				} else {
					if (result.message != null && result.message != "") {
						console.log(result.message)
					} else {
						console.log('服务端未知异常')
					}
				}
			},
			error : function(info) {
				console.log("请求下拉框数据失败！")
			}
		});
	},
	_resSelect : function(element_id) {
		//[{selector:'#test1',name:'depart_id',type:'select'}]
		var list = this.options.filters;
		var rs = '{';
		var size = list.length - 1;
		for (i in list) {
			var data=list[i];
			var name='';
			if(data.name){
				name=data.name;
			}else if(this.element.attr('name')){
				name=this.element.attr('name');
			}else{
				continue;
			}
			rs += '"' + name + '"';
			rs += ':"';
			rs += $(list[i].selector).val() + '"';
			if (size != i) {
				rs += ',';
			}
		}
		rs += '}';
		console.log(rs);
		this._getSelectData(this.options, $.parseJSON(rs));
	},
	setDisabled:function(param) {
		if(param==true){
			$(this.options.id).prop("disabled", true); 
		}
		if(param==false){
			$(this.options.id).prop("disabled", false); 
		}
	},
	checkSelect:function (){
		if(this.options.required){
			var data=$(this.options.id).find("option:selected").val();
			if(data=="%"||data==""){
				return false;
			}
		}
		
	},
	refreshdata : function() {
		this._resSelect();
	},
	/** 通知别人刷新 */
	fireListener : function() {
		for (i in this.options.listeners) {
			var id = this.options.listeners[i].selector;
			var type = this.options.listeners[i].type;
			var func = '$("'+id+'").d'+type+'("refreshdata")';
			eval(func);
		}
	}
	
});
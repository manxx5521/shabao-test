var dtree = $.widget("ui.dtree",{
	version : "1.0.0-beta.1",
	defaultElement : "<div>",
	delay : 300,
	options : {
		id : 'tree1view',//选择器
		element_id:'',	
		name:'treevalue',//树的名字唯一标识
		showpop:true,
		title:'请选择', //弹出树选择的标题
		class:"col-sm-9",
	},
	
	_create : function() {
		var element_name=this.options.name;
		this.element.addClass('row');
		var h_element=this.element;
		h_element.append($('<input type="hidden" id="'+this.options.name+'_val" name="'+this.options.name+'"/>'));
		if(this.options.showpop){
			var find=$('<span class="glyphicon glyphicon-search col-sm-1" id="'+this.options.name+'_find"></span>');
			var input_div=$('<div class="col-sm-11"></div>')
			input_div.append($('<input type="text" id="'+this.options.name+'_text"/>').addClass('form-control'))
			h_element.append(input_div).append(find);
		}
		var tree=$('<div id="'+this.options.name+'_tree"></div>')
		
		if(this.options.showpop){
			//添加淡出框
			var pop_element=$('<div class="modal inmodal" id="'+this.options.name+'_pop" tabindex="-1" role="dialog" aria-hidden="true"></div>');
			var pop_document=$('<div class="modal-dialog"></div>');
			var pop_content=$(' <div class="modal-content animated bounceInRight"></div>');
			var pop_head=$('<div class="modal-header"></div>')
						.append('<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">关闭</span></button>')
						.append('<h4 class="modal-title" id="'+this.options.name+'Label">'+this.options.title+'</h4>');
			var body_font=$('<div></div>')//内容样式设置
			var pop_body=$('<div class="modal-body"></div>');
			//内容
			pop_body.append('<input type="hidden" id="'+this.options.name+'_hval" />')
					.append('<input type="hidden" id="'+this.options.name+'_htext" />');
			pop_body.append(tree);
			body_font.append(pop_body);
			
			var pop_footer=$('<div class="modal-footer"></div>')
							.append('<button type="button" class="btn btn-white" data-dismiss="modal" id="'+this.options.name+'_cal">取消</button>')
							.append('<button type="button" class="btn btn-primary" id="'+this.options.name+'_ok">确定</button>');
			body_font.append(pop_footer);
			pop_content.append(pop_head).append(body_font);
			pop_element.append(pop_document.append(pop_content).append('<small class="font-bold"> </small>'));
			pop_element.append('<small class="font-bold"></small>');
			$('body').append(pop_element);
			this.element.append(h_element);
			$this=this;
			//查询按钮点击
			$('#'+this.options.name+'_find').click(function(){
				$('#'+element_name+'_hval').val('');
				$('#'+element_name+'_htext').val('');
				var model=$('#'+element_name+'_pop');
				model.modal({
					
					show:true,//显示弹出层
					//backdrop:'static',//禁止位置关闭
					keyboard:false //关闭键盘事件
				});
				$this.showtree();
			});
			
		}else{
			h_element.append(tree);
		}
		
		
		// 给组件添加监听方法
		this._on($(this.element), {
			"change" : function(event) {
			
			}
		});
	},
	showtree:function(){
		var element_id=this.options.element_id;
		if(element_id==''){
			console.log('未获得树元素id');
			return false;
		}
		//加载部门数数据
		$.ajax({
			type : 'POST',
			url : window.webroot+'/tree/'+element_id+'/list',
			dataType : 'json',
			context:this.options,
			success : function(data) {
				if (data.success) {
					$('#'+this.name+'_tree').jstree({
						"core" : {
							"check_callback" : true,
							"data" : data.data
						},
					"plugins" : [ "types", "dnd" ],
					"types" : {
						"default" : {
							"icon" : "fa fa-folder"
						},
						"html" : {
							"icon" : "fa fa-file-code-o"
						},
						"svg" : {
							"icon" : "fa fa-file-picture-o"
						},
						"css" : {
							"icon" : "fa fa-file-code-o"
						},
						"img" : {
							"icon" : "fa fa-file-image-o"
						},
						"js" : {
							"icon" : "fa fa-file-text-o"
						}
					},
					}).on('changed.jstree', function (e, data) {
					    var i, j, r;
					    for(i = 0, j = data.selected.length; i < j; i++) {
					      r=data.instance.get_node(data.selected[i]).id;
					    }
					 });
				} else {
					if (data.message != null && data.message != "") {
						console.log(data.message);
					} else {
						console.log('获得树的数据失败');
					}
				}
			},
			error : function(info) {
				console.log('获得树的数据时服务器异常');
				//alert(info.responseText);
				//alert(info);
			}
		});
	}
});

var dtree = $.widget("ui.dimage",{
	version : "1.0.0-beta.1",
	defaultElement : "<div>",
	delay : 300,
	options : {
		id : '',//无需传入自动获取
		element_id:'',	
		name:'treevalue',//名字唯一标识
		showpop:true,
		title:'请选择', //标题
		class:"col-sm-9",
		value:{},//默认值
		readonly:false,
		data:function(){//Ajax请求数据要执行的函数
			var data={};
			return data;
		},
		url:function(){ //Ajax请求数据的URL
			return window.webroot+'/admin/image/'+this.element_id+'/list';
		},
		webroot:window.webroot,
		validation:function(){ //获取数据时可能要做的验证
			return true;
		},
		resdata:true, //是否每次刷新后台数据
		restate:false,//缓存
		rescode:function(){//切换帐号等时要传入的值,当这个值变化是要刷新
			return '';
		},
		rescodeval:'' //缓存，无需传入
	},
	
	_create : function() {
		var element_name=this.options.name;
		var h_element=this.element;
		var id=this.element.context.id;
		this.options.id=id;
		
		h_element.append($('<input type="hidden" id="'+this.options.name+'_val" name="'+this.options.name+'"/>'));
		this.apped_ioc();
		/** 内容元素 */
		var c_element=$('<div id="'+this.options.name+'_element"></div>').css({overflow:'auto'});
		
		//添加淡出框
		var pop_element=$('<div class="modal" id="'+this.options.name+'_pop" tabindex="-1" role="dialog" aria-hidden="true"></div>');
		var pop_document=$('<div class="modal-dialog"></div>');
		var pop_content=$(' <div class="modal-content animated bounceInRight"></div>');
		var pop_head=$('<div class="modal-header"></div>')
						.append('<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">关闭</span></button>')
						.append('<h4 class="modal-title" id="'+this.options.name+'Label">'+this.options.title+'</h4>');
		var body_font=$('<div></div>')//内容样式设置
		var pop_body=$('<div class="modal-body"></div>');
			//内容
			/*pop_body.append('<input type="hidden" id="'+this.options.name+'_hval" />')
					.append('<input type="hidden" id="'+this.options.name+'_htext" />');*/
		pop_body.append(c_element);
		body_font.append(pop_body);
			
		var pop_footer=$('<div class="modal-footer"></div>')
							.append('<button type="button" class="btn btn-white" data-dismiss="modal">取消</button>');
							//.append('<button type="button" class="btn btn-primary" id="'+this.options.name+'_ok">确定</button>');
		body_font.append(pop_footer);
		pop_content.append(pop_head).append(body_font);
		pop_element.append(pop_document.append(pop_content).append('<small class="font-bold"> </small>'));
		pop_element.append('<small class="font-bold"></small>');
		$('body').append(pop_element);
		this.element.append(h_element);
		//默认值
		var value=this.options.value;
		var valid=value.id;
		if(value!=''&&valid!=''&&valid!=undefined){
			this.choose(value);
		}
		
		// 给组件添加监听方法
		this._on($(this.element), {
			"change" : function(event) {
			
			}
		});
	},
	showpop:function(){
		var element_id=this.options.element_id;
		if(element_id==''){
			console.log('未获得元素id');
			return false;
		}
		if(!this.options.resdata){
			var code=this.options.rescode();
			if(code!=''){
				if(this.options.rescodeval!=code){
					this.options.restate=false;
				}
				this.options.rescodeval=code;
			}
			if(this.options.restate){
				return false;
			}
			this.options.restate=true;
		}
		
		$this=this;
		//加载部门数数据
		$.ajax({
			type : 'POST',
			url : this.options.url(),
			dataType : 'json',
			data : this.options.data(),
			context:this.options,
			success : function(result) {
				if (result.success) {
					var contenxt=$('#'+this.name+'_element .file-box');
					contenxt.remove();
					for(i in result.data){
						/*var file_box=$(' <div class="file-box"></div>');
						var file=$('<div class="file"></div>');
						var a=$('<a href="">');
						a.append('<span class="corner"></span>');
						var a_image=$('<div class="image"></div>').append('<img alt="image" class="img-responsive" src="'+this.ctx+i.url+'">');
						a.append(a_image);
						var a_name=$('<div class="file-name">'+i.text+'</div>');
						a.append(a_name);
						file_box.append(file.append(a));
						contenxt.append(file_box);*/
						var temp=result.data[i];
						$this.apped_box('#'+this.name+'_element',temp.id,temp.text,temp.url);
					}
				} else {
					if (result.message != null && result.message != "") {
						console.log(result.message);
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
	},
	closepop:function(){
		var model=$('#'+this.options.name+'_pop');
		model.modal('hide');
	},
	/**
	 * 父级元素id
	 */
	apped_box:function(p_id,id,text,url){
		var contenxt=$(p_id);
		var file_box=$(' <div class="file-box"></div>').css({width:'100px'});
		var file=$('<div class="file"></div>');
		var a=$('<a href="javascript:void(0);" onclick="$(\'#'+this.options.id+'\').dimage(\'choose\',{\'id\':\''+id+'\',\'text\':\''+text+'\',\'url\':\''+url+'\'})"></a>');
		a.append('<span class="corner"></span>');
		var a_image=$('<div class="image"></div>').css({height: '70px'})
			.append('<img alt="image" class="img-responsive" src="'+window.webroot+url+'">');
		a.append(a_image);
		var a_name=$('<div class="file-name">'+text+'</div>').css({'padding-top':'0px','padding-bottom':'0px'});
		a.append(a_name);
		file_box.append(file.append(a));
		contenxt.append(file_box);
	},
	/**
	 * 父级元素id
	 */
	apped_ioc:function(){
		var file_box=$(' <div class="file-box"></div>');
		var file=$('<div class="file"></div>');
		var a=$('<a href="javascript:void(0);" id="'+this.options.name+'_find">');
		a.append('<span class="corner"></span>');
		var a_image=$('<div class="icon zpth"></div>').append('<i class="fa fa-file"></i>');
		a.append(a_image);
		var a_name=$('<div class="file-name">请选择</div>');
		a.append(a_name);
		file_box.append(file.append(a));
		this.element.append(file_box);
		if(!this.options.readonly){
			$this=this;
			//查询按钮点击
			$('#'+this.options.name+'_find').click(function(){
				//进行可能的数据验证
				var flag=$this.options.validation();
				if(flag==false){
					return false;
				}
				/*$('#'+element_name+'_hval').val('');
				$('#'+element_name+'_htext').val('');*/
				var model=$('#'+$this.options.name+'_pop');
				model.modal({
					show:true,//显示弹出层
					//backdrop:'static',//禁止位置关闭
					keyboard:false //关闭键盘事件
				});
				$this.showpop();
			});
		}
	},
	choose:function(data){
		var div=$('#'+this.options.id+' .zpth').removeClass('icon').addClass('image');
		div.empty();
		var img=$('<img alt="image" class="img-responsive" src="'+this.options.webroot+data.url+'" />').css({height:'100px'})
		div.append(img);
		$('#'+this.options.id+' .file-name').html(data.text);
		$('#'+this.options.name+'_val').val(data.id);
		this.closepop();
	}
});

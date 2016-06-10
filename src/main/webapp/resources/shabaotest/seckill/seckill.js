//存放秒杀交互逻辑
var seckill={
	//秒杀地址
	URL:{
		//获取当前时间URL
		
		now:function(){
			return window.webroot+'/seckill/time/now';
		},
		exposer:function(seckillId){
			return window.webroot+'/seckill/'+seckillId+'/exposer';
		},
		execution:function(seckillId,md5){
			return window.webroot+'/seckill/'+seckillId+'/'+md5+'/execution'
		}
	},
	//验证手机号
	validatePhone:function(phone){
		if(phone&&phone.length==11&&!isNaN(phone)){
			return true
		}else{
			return false;	
		}
	},
	//秒杀
	handlerSeckill:function(seckillId,node){
		node.hide()
			.html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');//按钮
		$.post(seckill.URL.exposer(seckillId),{},function(result){
			if(result&&result['success']){
				var exposer=result['data'];
				if(exposer['exposed']){
					//开始秒杀
					var md5=exposer['md5'];
					var killUrl=seckill.URL.execution(seckillId,md5);
					//绑定一次点击事件
					$('#killBtn').one('click',function(){
						//执行秒杀
						$(this).addClass('disabled');
						$.post(killUrl,{},function(result){
							if(result&&result['success']){
								var killResult=result['data'];
								var state=killResult['state'];
								var stateInof=killResult['stateInfo'];
								//显示秒杀结果
								node.html('<span class="label label-success">'+stateInof+'</label>');
							}else{
								alert(result['error'])
							}
						})
					});
					node.show();
				}else{
					//未开始秒杀
					var now=exposer['now'];
					var start=exposer['start'];
					var end=exposer['end'];
					//重新计算计时逻辑
					seckill.countdown(seckillId,nowTime,startTime,endTime);
				}
			}else{
				console.log('result:'+result);
			}
		});
	},
	countdown:function(seckillId,nowTime,startTime,endTime){
		var seckillBox=$('#seckill-box');
		//时间判断
		if(nowTime>endTime){
			//秒杀结束了
			seckillBox.html('秒杀结束')
		}else if(nowTime<startTime){
			//秒杀未开始，计时。加1秒是为了防止时间偏移
			var killTime=new Date(startTime*1+1000);
			//调用计时
			seckillBox.countdown(killTime,function(event){
				//控制时间的格式
				var fomat=event.strftime('秒杀倒计时： %D天 %H时 %M分 %S秒')
				seckillBox.html(fomat);
			}).on('finish.countdown',function(){
				//倒计时完成后触发
				seckill.handlerSeckill(seckillId,seckillBox);
			});
		}else{
			seckill.handlerSeckill(seckillId,seckillBox);
		}
	},
	//详情页秒杀逻辑
	detail:{
		//详情页初始化
		init:function(params){
			//用户手机验证和登录，计时交互
			//在cookie中找手机号，代替登录
			var killPhone=$.cookie('killPhone');
			var startTime=params['startTime'];
			var endTime=params['endTime'];
			var seckillId=params['seckillId'];
			//验证手机号
			if(!seckill.validatePhone(killPhone)){
				//绑定手机
				var killPhoneModal=$('#killPhoneModal');
				killPhoneModal.modal({
					show:true,//显示弹出层
					backdrop:'static',//禁止位置关闭
					keyboard:false //关闭键盘时间
				});
				$('#killPhoneBtn').click(function(){
					var inputPhone=$('#killPhoneKey').val();
					if(seckill.validatePhone(inputPhone)){
						//电话写入cookie                //expires有效期7天，path生效路径
						$.cookie('killPhone',inputPhone,{expires:7,path:'seckill'})
						//刷新页面
						window.location.reload();
					}else{
						$('#killPhoneMessage').hide()
						.html('<label class="label label-danger">手机号错误</label>').show(300);
					}
				});
			}
			//登录后计时交互
			$.get(seckill.URL.now(),{},function(result){
				if(result&&result['success']){
					var nowTime=result['data'];
					//时间判断
					seckill.countdown(seckillId,nowTime,startTime,endTime);
				}else{
					console.log('result:'+result);
				}
			})
			
			
		}
	}
}
$(function() {
	var tags = new Array();
	var page = (function() {
		// 查询标签
		return {
			loadTag : function() {
				$.ajax({
					type : "POST",
					url : webroot + "/vkan/tagList",
					dataType : "json",
					success : function(data) {
						if (!!data) {
							for (var i = 0; i < data.length; i++) {
								var tag = data[i];
								if (tag.level == 1) {
									var container = $(".tags-container");
									var ul = $('<ul class="tags-box"></ul>')
											.attr('pid', tag.tagId);
									ul.append(page.getTagLi(tag));
									container.append(ul);
								}
								if (tag.level == 2) {
									var ul = $('.tags-box[pid=' + tag.parentId
											+ ']');
									if (!!ul) {
										ul.append(page.getTagLi(tag));
									}
								}
							}
						}
					},
					error : function(info) {
						alert(info.responseText);
						alert(info);
					}
				});
			},
			/** 添加tag标签 */
			getTagLi : function(tag) {
				var li = $("<li></li>").addClass("hot-1");
				var a = $('<a href="javascript:void(0)" class="tag-font-size-14" >'
						+ tag.name + '</a>');
				if (tag.level == 1) {
					li.addClass('fenlei');
				} else {
					a.attr('tid', tag.tagId);
				}
				li.append(a);
				a.click(function() {
					var tid = this.attributes.tid.value;
					$this = $('a[tid=' + tid + ']');
					var parent = $this.parent();
					if (parent.hasClass('curr')) {
						page.deleteTag(tid);
						parent.removeClass('curr');
					} else {
						parent.addClass('curr');
						page.pushTag(tid);

					}
				})
				return li;
			},
			pushTag : function(tagId) {
				tags.push(tagId);
			},
			deleteTag : function(tagId) {
				var index;
				for (var i = 0; i < tags.length; i++) {
					var temp = tags[i];
					if (tagId == temp) {
						index = i;
					}
				}
				tags.splice(index, 1);
			},
			initSearch:function(){
				$('#searchId').click(function() {
					$('#simplemodal-container,#searchbar').show();
				});
				$('#simplemodal-container').click(function() {
					$(this).hide();
					$('#searchbar').hide();
				});
				$('#btnPost').click(function() {
					 window.location.href = './index.html?projectPrefix='+encodeURIComponent($('select[name="projectPrefix"]').val())
					 		+'&projectId='+$('select[name="projectId"]').val();
				});
			},
			loadFile:function(){
				var data={};
				var parentId=$('input[name="parentId"]').val();
				if(!!parentId){
					data.parentId=parentId;
				}
				data.tagIds=tags;
				
				$.ajax({
					type : "POST",
					url : webroot + "/vkan/fileData",
					data:data,
					dataType : "json",
					success : function(result) {
						$('#img-container').empty();
						
						for(var i=0;i<result.list.length;i++){
							page.addFileDto(result.list[i]);
						}
						page.addClickFile();
						//设置下一页数据
						$("#more a").attr("href",webroot + "/vkan/fileData?index="+(result.index+1)+"&size="+result.size);
						
						page.item_masonry();
						//重新加载数据，解决清空数据，重新加载布局错乱问题(2版本)
//						$('#img-container').masonry('reload');
						
						
					},
					error : function(info) {
						alert(info.responseText);
						alert(info);
					}
				});
			},
			/**添加文件*/
			addFileDto:function(fileDto){
				var name=fileDto.fileName;
				
				//解析图片
				var path="";
				var fileType=fileDto.fileType;
				if(fileType==1){
					path=webroot+"/resources/vkan/image/type_dir.png";
				}else if(fileType==2){
					var prefix=$('select[name="projectPrefix"]').find('option:selected').val();
					prefix="/"+prefix.substr(0,1).toLowerCase()+"/";
					path=prefix+$('input[name="projectPath"]').val().replace('vm\\','')+fileDto.path;
					path=path.replace(/\\/g,'/');
				}else if(fileType==3){
					path=webroot+"/resources/vkan/image/type_video.png";
				}else{
					path=webroot+"/resources/vkan/image/type_other.png";
				}
				
				
				var html='';
				html+='<div class="border-img-box masonry-brick">';
				html+='  <div class="img_inner_wrapper">';
				html+='    <div class="inner_wrapper_img inner_wrapper_img1">';
				html+='      <div>';
				html+='		   <a class="click_file" href="javascript:void(0)" fid="'+fileDto.fileId+'" ftype="'+fileType+'">';//TODO 下一步操作
				html+='          <img title="'+name+'" class="img-min-height" alt="'+name+'" src="'+path+'">';
				html+='		   </a>';
				html+='      <div>';
				html+='      <div class="mid_img_count">';
				html+='        <span class="num"> <label>'+(!fileDto.child_number?'*':fileDto.child_number)+'</label>';
				html+='      </div>';
				html+='      <div class="img_inner_wrapper_tag">';
				html+='        <div class="title">';
				html+='          <a href="javascript:void(0)" target="_blank">'+name+'</a>';//TODO
				html+='        </div>';
				html+='        <div class="tag curr">';
				html+='          <label>分类：</label> <a href="javascript:void(0)">我是分类</a>';//TODO
				html+='        </div>';
				html+='        <div class="tag curr">';
				html+='          <label>标签：</label>';
				
				//添加标签
				for(var i=0;i<fileDto.tagList.length;i++){
					var tag=fileDto.tagList[i];
					html+='      <a href="javascript:void(0)" target="_blank" rel="tag" tid="'+tag.tagId+'">'+tag.name+'</a>';
				}
				
				html+='        </div>';
				html+='      </div>';
				html+='    </div>';
				html+='  </div>';
				html+='</div>';
				$('#img-container').append(html);
			},
			/**给文件列表添加点击事件*/
			addClickFile:function(){
				$('.click_file').click(function(){
					var fid = this.attributes.fid.value;
					var type = this.attributes.ftype.value;
					
					//文件夹进入下级目录
					if(type==1){
						$('input[name="parentId"]').val(fid);
						page.loadFile();
					}
				})
			},
			/**实例化瀑布流*/
			item_masonry:function(){

				$grid=page.item_callback();

				$('.item').fadeIn();

				var sp = 1
				try{
					var msnry = $grid.data('masonry');
					$("#img-container").infiniteScroll({
						path: $('#more a').attr('href'),
						append: '.border-img-box',
						outlayer: msnry,
						responseType: 'text',
						status: '.page-load-status'
					});
					
					$grid.on( 'load.infiniteScroll', function( event, response ) {
						  console.log('111'+ response )
						  // parse response into JSON data
						  
						});
				/*$("#img-container").infiniteScroll({
					navSelector : "#more", //页面分页元素(成功后会被隐藏)
					nextSelector : "#more a",// 需要点击的下一页链接，和2的html要对应
					itemSelector : ".border-img-box", // ajax回来之后，每一项的selecter（比如每篇文章都有item这个class）
					loading : {
						img : webroot+"/resources/vkan/masonry_loading_1.gif",//自定义loadding的动画图
						msgText : ' ',//加载时的提示语
						finishedMsg : '木有了',//当加载失败，或者加载不出内容之后的提示语
						finished : function() {
							sp++;
							if (sp >= 5) { // 到第5页结束事件
								$("#more").remove();
								$("#infscr-loading").hide();
								$(".itempages").show();
								$(window).unbind('.infscr');
							}
						}
					},
					errorCallback : function() {
						$(".itempages").show();
					}

				}, function(newElements) {
					var $newElems = $(newElements);
					$('#img-container').masonry('appended', $newElems, false);
					$newElems.fadeIn();
					page.item_callback();
					return;
				});*/
				}catch(errs){
					console.log(errs.message)
				}
			},
			/**生成瀑布流样式*/
			item_callback:function() {

				$('.border-img-box').mouseover(function() {
					$(this).css('box-shadow', '0 1px 5px rgba(35,25,25,0.5)');
					$('.btns', this).show();
				}).mouseout(function() {
					$(this).css('box-shadow', '0 1px 3px rgba(34,25,25,0.2)');
					$('.btns', this).hide();
				});

				//瀑布流生成
				/*$('.border-img-box img').load(function() {
					$('#img-container').masonry({
						itemSelector : '.border-img-box',
						columnWidth : 228,
						gutterWidth : 15
					});
				});*/

				$grid=$('#img-container').masonry({
					itemSelector : '.border-img-box',
					columnWidth : 228,
					gutterWidth : 15
				});
				
				return $grid;
			},
			init : function() {
				this.loadTag();
				this.initSearch();
				this.loadFile();
			}
		}
	})();
	page.init();
})
jQuery(document).ready(function() {
	jQuery(".m-nav ul li").hover(function() {
		jQuery(this).children("ul").show();
		jQuery(this).addClass("li01")
	}, function() {
		jQuery(this).children("ul").hide();
		jQuery(this).removeClass("li01")
	})
});

$(document).ready(function() {
	var gotoTop = $('#gotoTop');
	$(window).scroll(function() {
		var srollPos = $(window).scrollTop();
		if (srollPos >= 320) {
			gotoTop.show();
		} else {
			gotoTop.hide();
		}
	});
});
window.onerror = function() {
	return true;
};
/*
function item_masonry() {
	$('.border-img-box img').load(function() {
		$('#img-container').masonry({
			itemSelector : '.border-img-box',
			columnWidth : 228,
			gutterWidth : 15
		});
	});

	$('#img-container').masonry({
		itemSelector : '.border-img-box',
		columnWidth : 228,
		gutterWidth : 15
	});
}

$(function() {

	function item_callback() {

		$('.border-img-box').mouseover(function() {
			$(this).css('box-shadow', '0 1px 5px rgba(35,25,25,0.5)');
			$('.btns', this).show();
		}).mouseout(function() {
			$(this).css('box-shadow', '0 1px 3px rgba(34,25,25,0.2)');
			$('.btns', this).hide();
		});

		item_masonry();

	}

	item_callback();

	$('.item').fadeIn();

	var sp = 1

	$("#img-container").infinitescroll({
		navSelector : "#more",
		nextSelector : "#more a",
		itemSelector : ".border-img-box",
		loading : {
			img : "./images/masonry_loading_1.gif",
			msgText : ' ',
			finishedMsg : '木有了',
			finished : function() {
				sp++;
				if (sp >= 5) { // 到第5页结束事件
					$("#more").remove();
					$("#infscr-loading").hide();
					$(".itempages").show();
					$(window).unbind('.infscr');
				}
			}
		},
		errorCallback : function() {
			$(".itempages").show();
		}

	}, function(newElements) {
		var $newElems = $(newElements);
		$('#img-container').masonry('appended', $newElems, false);
		$newElems.fadeIn();
		item_callback();
		return;
	});

});
*/
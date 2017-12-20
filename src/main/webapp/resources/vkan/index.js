$(function() {
	var tags = new Array();
	var page = (function() {
		var infScroll;
		var msnry;
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
									var container = $(".tags-container.list");
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
				this.addTagClick(a);
				return li;
			},
			/**给标签添加事件*/
			addTagClick:function(a){
				a.click(function() {
					var tid = this.attributes.tid.value;
					$this = $('.tags-container a[tid=' + tid + ']');
					var parent = $this.parent();
					if (parent.hasClass('curr')) {
						page.deleteTag(tid);
						parent.removeClass('curr');
					} else {
						parent.addClass('curr');
						page.pushTag(tid);
					}
					page.reLoadData();
				})
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
				
				page.setCurrml();
			},
			/**设置当前目录显示*/
			setCurrml:function(path){
				//设置当前目录
				var currml=$('select[name="projectPrefix"]').val()+$('input[name="projectPath"]').val();
				if(!!path){
					currml+=path;
				}
				$('#currml').text('当前目录：'+currml);
			},
			/**添加文件*/
			getFileDtoHtml:function(fileDto){
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
				html+='		   <a class="click_file" href="javascript:void(0)" fid="'+fileDto.fileId+'" ftype="'+fileType+'" fpath="'+fileDto.path+'">';
				html+='          <img title="'+name+'" class="img-min-height" alt="'+name+'" src="'+path+'">';
				html+='		   </a>';
				html+='      </div>';
				html+='      <div class="mid_img_count">';
				html+='        <span class="num"> <label>'+(!fileDto.child_number?'*':fileDto.child_number)+'</label>';
				html+='      </div>';
				html+='      <div class="img_inner_wrapper_tag">';
				html+='        <div class="title">';
				html+='          <a href="javascript:void(0)" target="_blank">'+name+'</a>';//TODO
				html+='        </div>';
				html+='        <div class="tag curr">';
				html+='          <label>操作：</label> <a class="operation_tag" href="javascript:void(0)" fid="'+fileDto.fileId+'">标签</a>';
				html+='        </div>';
				html+='        <div class="tag curr" id="bq_'+fileDto.fileId+'">';
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
				return html;
			},
			/**重新加载数据，清空原有内容*/
			reLoadData:function(){
				$('#img-container .border-img-box').remove();
				$('#img-container').removeAttr('style');
				
				page.infScroll.destroy();
				page.msnry.destroy();
				page.loadData();
				page.msnry.reloadItems();
			},
			/**加载文件*/
			loadData:function(){
				page.msnry = new Masonry('#img-container',{
					  itemSelector: '.border-img-box',
					  columnWidth: '.grid__col-sizer',
					  gutter: '.grid__gutter-sizer',
					  percentPosition: true,
//					  stagger: 30,
//					  visibleStyle: { transform: 'translateY(0)', opacity: 1 },
//					  hiddenStyle: { transform: 'translateY(100px)', opacity: 0 },
				});
				
				page.infScroll =new InfiniteScroll('#img-container',{
//					  path: webroot + '/vkan/fileData?index={{#}}'+url,
					  path: function() {
						  var url = webroot + '/vkan/fileData?index='+this.pageIndex;
						  if(tags.length>0){
							  url+='&idstr='+tags.join(",");
						  }
						  var parentId = page.getParentId();
						  
						  if (!!parentId) {
							  url+="&parentId=" + parentId;
						  }
						  return url;
						},
					  responseType: 'text',
					  outlayer: page.msnry,
					  status: '.page-load-status',
					  history: false,
					});
				
				page.infScroll.on( 'load', function(response, path ) {
					  console.log( response )
					  var data = JSON.parse( response ).list;
					  
					  var html="";
					  for(var i=0;i<data.length;i++){
						  html+=page.getFileDtoHtml(data[i]);
					  }
						
					  var $items = $( html );
					  
					  // append item elements
					  $items.imagesLoaded( function() {
						  page.infScroll.appendItems( $items );
						  page.msnry.appended( $items );
						  
						  page.loadAfter();
					  })
					  
					});

				page.infScroll.loadNextPage();
			},
			/**加载完成数据后操作*/
			loadAfter:function(){
				/**给文件列表添加点击事件*/
				$('.click_file').click(function(){
					var fid = this.attributes.fid.value;
					var type = this.attributes.ftype.value;
					var path = this.attributes.fpath.value;
					
					//文件夹进入下级目录
					if(type==1){
						//设置父级id、清空原有数据
						page.setParentId(fid);
						page.reLoadData();
						page.setCurrml(path);
					}
					
				})
				
				//操作-标签点击
				$('.operation_tag').unbind('click').click(function(){
					$this=this;
					var fileId = $this.attributes.fid.value;
					//页面层
					layer.open({
					  type: 2,
					  title: '标签选择',
					  shadeClose: true,
					  shade: 0.8,
					  area: ['960px', '90%'],
					  content: webroot+'/vkan/tagView.html?fileId='+fileId,
					  end:function(){
						  
						  $.ajax({
								type : "POST",
								url : webroot + "/vkan/tag/getlabelTag.html",
								dataType : "json",
								data:{'fileId':fileId},
								success : function(data) {
									if(!!data){
										$('#bq_'+fileId).find('a').remove();
										var labeldiv=$('#bq_'+fileId);
										var html='';
										for(var i=0;i<data.length;i++){
											var tag=data[i];
											html+='      <a href="javascript:void(0)" target="_blank" rel="tag" tid="'+tag.tagId+'">'+tag.name+'</a>';
										}
										labeldiv.append(html);
									}else {
										console.log(data.message);
									}
								}
							});
					  }
					});
					
				})
				
				
				
				//重置操作按钮
				if($('select[name="projectId"]').val()==page.getParentId()){
					$('#shangji').hide();
				}else{
					$('#shangji').show();
				}
			},
			initOperation:function(){
				$('#shangji').click(function(){
					var parentId=page.getParentId();
					$.ajax({
						type : "POST",
						url : webroot + "/vkan/getParentFile.html",
						dataType : "json",
						data:{'id':parentId},
						success : function(result) {
							if (result.success == true) {
								page.setParentId(result.data.parentId);
								page.reLoadData();
								page.setCurrml(result.data.path);
							} else {
								console.log(result.message);
							}
						},
						error : function(info) {
							alert(info.responseText);
							alert(info);
						}
					});
				})
			},
			/**统一获取父级id*/
			getParentId:function(){
				return $('input[name="parentId"]').val();
			},
			/**统一设置父级id**/
			setParentId:function(fid){
				if(!!fid){
					$('input[name="parentId"]').val(fid);
				}else{
					console.log('设置父级id失败，未传入父级id');
				}
				
			},
			init : function() {
				this.loadTag();
				this.initSearch();
				this.loadData();
				this.initOperation();
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

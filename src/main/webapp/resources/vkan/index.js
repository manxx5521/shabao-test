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
									var ul=$('<ul class="tags-box"></ul>').attr('pid',tag.tagId);
									ul.append(page.getTagLi(tag));
									container.append(ul);
								}
								if (tag.level == 2) {
									var ul=$('.tags-box[pid='+tag.parentId+']');
									if(!!ul){
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
				var li = $("<li></li>").addClass("hot-1 curr");
				var a = $('<a href="javascript:void(0)" class="tag-font-size-14" >'
						+ tag.name + '</a>');
				if (tag.level == 1) {
					li.addClass('curr');//TODO 分类颜色
				} else {
					a.attr('tid', tag.tagId);
				}
				li.append(a);
				a.click(function() {
					var tid=this.attributes.tid.value;
					$this=$('a[tid='+tid+']');
					var parent = $this.parent();
					if (parent.hasClass('curr')){
						page.deleteTag(tid);
						parent.removeClass('curr');
					}else{
						parent.addClass('curr');
						page.pushTag(tid);
						
					}
				})
				return li;
			},
			pushTag:function(tagId){
				tags.push(tid);
			},
			deleteTag:function(tagId){
				var index;
				for(var i=0;i<tags.length;i++){
					var temp=tags[i];
					if(tagId==tid){
						index=i;
					}
				}
				tags.splice(index,1);
			},
			init:function() {
				this.loadTag();
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

$('.searchBtn a').click(function() {
	$('#simplemodal-container,#searchbar').show();
});
$('#simplemodal-container').click(function() {
	$(this).hide();
	$('#searchbar').hide();
});
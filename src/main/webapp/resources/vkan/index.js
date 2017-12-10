jQuery(document).ready(function() {
	jQuery(".m-nav ul li").hover(function() {
		jQuery(this).children("ul").show();
		jQuery(this).addClass("li01")
	},
	function() {
		jQuery(this).children("ul").hide();
		jQuery(this).removeClass("li01")
	})
});

$(document).ready(function() {
	var gotoTop = $('#gotoTop');
	$(window).scroll(function() {
		var srollPos = $(window).scrollTop();
        if(srollPos >= 320){
        	gotoTop.show();
        }else{
        	gotoTop.hide();
        }
	});
});
window.onerror = function() {
	return true;
};


function item_masonry(){ 
	$('.border-img-box img').load(function(){ 
		$('#img-container').masonry({ 
			itemSelector: '.border-img-box',
			columnWidth:228,
			gutterWidth:15								
		});		
	});
		
	$('#img-container').masonry({ 
		itemSelector: '.border-img-box',
		columnWidth:228,
		gutterWidth:15								
	});	
}

$(function(){

	function item_callback(){ 
		
		$('.border-img-box').mouseover(function(){
			$(this).css('box-shadow', '0 1px 5px rgba(35,25,25,0.5)');
			$('.btns',this).show();
		}).mouseout(function(){
			$(this).css('box-shadow', '0 1px 3px rgba(34,25,25,0.2)');
			$('.btns',this).hide();		 	
		});
		
		item_masonry();	

	}

	item_callback();  

	$('.item').fadeIn();

	var sp = 1
	
	$("#img-container").infinitescroll({
		navSelector  	: "#more",
		nextSelector 	: "#more a",
		itemSelector 	: ".border-img-box",	
		loading:{
			img: "./images/masonry_loading_1.gif",
			msgText: ' ',
			finishedMsg: '木有了',
			finished: function(){
				sp++;
				if(sp>=5){ //到第5页结束事件
					$("#more").remove();
					$("#infscr-loading").hide();
					$(".itempages").show();
					$(window).unbind('.infscr');
				}
			}	
		},errorCallback:function(){ 
			$(".itempages").show();
		}
		
	},function(newElements){
		var $newElems = $(newElements);
		$('#img-container').masonry('appended', $newElems, false);
		$newElems.fadeIn();
		item_callback();
		return;
	});

});

$('.searchBtn a').click(function(){
		$('#simplemodal-container,#searchbar').show();
	});
	$('#simplemodal-container').click(function(){
		$(this).hide();
		$('#searchbar').hide();
	});
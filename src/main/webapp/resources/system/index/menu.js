//初始列表
var getMenu = function(dataList) {
	for (var i = 0; i < dataList.length; i++) {
		var obj = dataList[i];
		if (obj.level == 1) {
			getMenuL1(obj);
		}else if(obj.level == 2){
			getMenuL2(obj);
		}else if(obj.level == 3){
			getMenuL3(obj);
		}
	}
}

// 添加一级菜单
var getMenuL1 = function(obj) {
	var li = $('<li></li>').attr('menu_id', obj.menuId);
	// 设置a
	var a = $('<a></a>');
	// 如果是菜单按钮
	if (obj.ismenu == 1) {
		a.addClass('J_menuItem');
		a.attr('href', window.webroot+obj.url);
	} else {
		a.attr('href', '#');
	}
	var li_fa = $('<i></i>').addClass('fa').addClass(obj.ioc);// 图标
	var span_label = $('<span><span>').addClass('nav-label').text(
			obj.menuTitle);// 菜单名
	var qa = a.append(li_fa).append(span_label);// 组织a超链接
	var temp = li.append(qa);// 组织整个li元素
	// 添加元素
	$('#side-menu').append(temp);
}

//添加二级菜单
var getMenuL2 = function(obj) {
	//定义获取li的字符串
	var s_pli='li[menu_id='+obj.parentMenuId+']';
	var li_pid=$(s_pli);
	//如果没有父级标签返回
	if(li_pid.length<1)
		return false;
	//添加一级菜单向左箭头和二级ul
	var span_left=$('<span></span>').addClass('fa').addClass('arrow');
	if($(s_pli+' a:first span').length<2){
		$(s_pli+' a:first').append(span_left);
		var ul=$('<ul></ul>').addClass('nav').addClass('nav-second-level');
		li_pid.append(ul);
	}
	
	//添加二级菜单
	var a = $('<a></a>');
	// 如果是菜单按钮
	if (obj.ismenu == 1) {
		a.addClass('J_menuItem');
		a.attr('href', window.webroot+obj.url);
	} else {
		a.attr('href', '#');
	}
	a.text(obj.menuTitle);//设置菜单名
	var li = $('<li></li>').attr('menu_id', window.webroot+obj.menuId);//二级标题的li
	li.append(a);
	$(s_pli+' .nav-second-level').append(li);//添加按钮
}

//添加三级菜单
var getMenuL3 = function(obj) {
	//定义获取li的字符串
	var s_pli='li[menu_id='+obj.parentMenuId+']';
	var li_pid=$(s_pli);
	//如果没有父级标签返回
	if(li_pid.length<1)
		return false;
	//添加三级菜单向左箭头和三级ul
	var span_left=$('<span></span>').addClass('fa').addClass('arrow');
	if($(s_pli+' a:first span').length<1){
		$(s_pli+' a:first').append(span_left);
		var ul=$('<ul></ul>').addClass('nav').addClass('nav-third-level');
		li_pid.append(ul);
	}
	//添加三级菜单
	var a = $('<a></a>');
	// 如果是菜单按钮
	if (obj.ismenu == 1) {
		a.addClass('J_menuItem');
		a.attr('href', window.window.webroot+obj.url);
	} else {
		a.attr('href', '#');
	}
	a.text(obj.menuTitle);//设置菜单名
	var li = $('<li></li>').attr('menu_id', obj.menuId);//三级标题的li
	li.append(a);
	$(s_pli+' .nav-third-level').append(li);//添加按钮
	
}

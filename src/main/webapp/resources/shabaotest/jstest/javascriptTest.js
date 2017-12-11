/**设置默认值*/
var a1;
var a2=a1||2;//默认值为2，用||分割

//删除
	var objtest = { 
	    prop: 'delete me' 
	}; 
	//或者这么声明的对象 
	var objtest = new Array(); 
	objtest['prop'] = 'delete me'; 
	//删除属性'prop'： 
	delete objtest.prop; 
	//或者 
	delete objtest['prop'];

	//还可以删除任意变量 
	var numb = 17; 
	delete numb;

/**闭包
方法外可以使用方法内的变量（和作用域相反）
**/
//quo 求算下一个数值
var quo=function(idx){
	var i=0;

	return{//形成闭包
		//获得下一个值
		getNext:function(){
			i=i+idx;
			return i+idx;
		},
		getIndex:function(){//获得当前值
			return i;
		}
	}
}
var myQuo=quo(2);//生成固定步长的数字,每次+2
document.writeln(myQuo.getNext());

/**继承 **/
var Mammal=function(name){//基本类
	this.name=name;
}
Mammal.prototype.getName=function(){//添加一个获得名字的方法
	return name;
}

var Cat=function(name){//继承类
	this.name=name;
	this.saying='wo shi che';
}.inherits(Mammal) //使用关键字inherits继承
.method('say',function(){//添加或重写方法
	return saying;
});

//生成界面后执行的两种发放
$(function() {
});

$(document).ready(function() {
});

//立即执行函数
var page = function() {
	
}();

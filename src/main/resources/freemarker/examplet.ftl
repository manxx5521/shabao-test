/************************
！判断是否为空，变量必须要传入；
??双问号用于判断变量是否存在，变量可以没有创建；
? 单个问号更像是操作比如对日期格式化，

基本类型boolean转换时，会将true转换成yes和no
可以通过string自定义转换 ${booleanStr?string('正确','错误')}
************************/

${user}  取值
${user!}  取值,如果值不存在，不会报错。可以过滤错误空值。
			!对前一个属性判断，如果想要判断全部要 ${(user.name)!} 这样就会先判断user再判断name
${user!'可能是隔壁老王'} 不存在给默认值 
${user.name} 可以取出对象属性,调用的属性应该有getter和setter方法，类必须为public类

${nowDate?string('yyyy-MM-dd')} 对日期进行格式化
${nowDate!?string('yyyy-MM-dd')} 判断时间是否存在

${booleanStr?string('正确','错误')} 基本类型boolean转换时，会将true转换成yes和no。
 										可以通过string自定义转换。
${htmlContent?html} 将变量转义成HTML，类似富文本转义。web中使用


/*********************************
变量的赋值计算，以及string操作
-------------
定义变量 a=100,运算加100
<#assign a=100>
结果=${a+100}

定义数组
<#assign myList=[2,3,4,5,1,8,7,6] />


***字符串操作----------------
<#assign n1='hello' />
<#assign n2='word' />

${n1+n2} 字符串直接连接输出：helloword
${n1?substring(2,4)} 截取字符串取2到4个字符，只取头不取尾，取2和3。显示为：ll
${n1?length} 长度为5
${n1?trim} 去除空格，输出hello
${n1?replace('l','N')} 重要：替换将l替换为N,输出为：heNNo
${n1?upper_case} 大写HELLO
${n1?lower_case} 小写hello
${n1?index_of('e')} e字母在n1中首次出现的位置，显示为：1
${n1?last_index_of('l')} l字母在n1中最后一次出现的位置，显示为：3

<#assign n3='a|b|c'?split('|') />  split分割字符串,变成数组
<#assign n4='01/03/2017'?date('MM/dd/yyyy') />	转换日期${n4}
<#assign n5='15:05:30'?time('HH:mm:ss') />	转换时间${n5}
<#assign n6='2016-13-31 03:05 PM'?datetime('yyyy-MM-dd hh:mm') />	转换时间${n6}

***数字操作----------------
<#assign num=314.5662 />
${num?string('1.##')}  截取两位小数，结果：314.56
${num?round} 四舍五入，结果315

***类型判断----------------
is_number可以判断变量是否是数字
${num?is_number?string('yes','no')}  输出yes
is_string可以判断变量是否是字符串
${num?is_string?string('yes','no')}  输出no
has_content判断是否有内容和！类似
${num?has_content?string('yes','no')}  输出yes

eval 把变量变成整数运算
${'1+2'} 和 ${('1+2')?eval} 前边输出字符串1+2，后边的相加输出3

/*********************************
IF判断的使用，判断可以使用&&和||等，s?length==4可判断大小
-------------
a变量是上边的变量定义 a=100
<#if a==100>  if语句
	是的a=100
<#elseif a==9999>
	111111
<#else>
	a!=100
</#if>

判断变量是否存在，用双问号??或者是?exists。（！判断变量是否为空）
<#if a??>
	变量a存在
</#if>


多条件判断，s?length==4可判断大小
<#assign s="java">
<#if s??&&s=="java"&&s?length==4>
	变量s正确${s}
</#if>


/*********************************
列表,列表是否存在用??的形式,dataList！添加叹号可以避免list为空时抛出异常
-------------

<#list dataList! as item>
	${item_index}:${item} //遍历时${item_index}代表序号，${item}代表的是当前对象，实际应用可能是里边的属性 item.name
</#list>

${dataList?size}  //查看列表长度，显示为：3
${dataList[1]}   //直接获得第二个元素

//分组分块，2个一组，取最后一个输出（如果分组的大小小于2，则不输出）
<#list dataList?chunk(2)?last as item>
	${item}
</#list>
-----
Map遍历,同样可以用！判断空。dataMap!?keys
-------------
<#list dataMap?keys as key>
	${key}:${dataMap[key]}
</#list>


/*********************************
switch 使用。和java中使用类似
-------------
<#assign var=10 />
<#switch var>
	<#case 10>
	   执行10后不跳出，会继续执行11，执行11后break跳出
	<#case 11>
	  10 或者11,遇到break才会跳出
	  <#break>
	<#default>
		没有值
</#switch>

/**************************
列表等排序,自定义函数排序，内置函数排序
------------------
使用自定义函数sort_int() 给前边定义的myList自定义排序，需要穿件函数类，并以变量方式传入
<#list sort_int(myList) as item>
	${item},
</#list>

内置函数排序(升序)
<#list myList?sort as item>
	${item}
</#list>
内置函数排序(降序)
<#list myList?sort?reverse as item>
	${item}
</#list>

/**************************
指令、自定义指令
------------------
自建指令role,两个入参userid、roleid,两个出参result1(boolean型),result2
作用是：确定用户是否是有传入的角色

<@role userid='123456' roleid='admin'; result1,result2 >
	<#if result1>
		我的角色是admin
	</#if>
	我的权限有：
	<#list result2 as item>
		${item}
	</#list>
</@role>

#function指令------------
定义函数doAdd,将两个参数param1和param2相加
<#function doAdd param1 param2>
	<#return param1+param2>
</#function>
	调用函数  ${doAdd(100,100)}

macro宏指令------
可以通过这个指令，重复利用代码片段。paramExt...可以传入多参数的map
<#macro commtest param1 param2 param3='我是默认' paramExt... >
	我可以输出第一个人${param1}
	这是第二个${param2}
	
	下面是可能传入的代码片段
	<#nested>
</#macro>

调用commtest，可多次调用
	<@commtest param1='张三' param2='李四'>
		我是个性化代码片段（可以没有）
	</@commtest>

















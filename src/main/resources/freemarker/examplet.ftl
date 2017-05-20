/************************
！判断是否是空；??双问号用于判断变量是否存在；? 单个问号更像是操作比如对日期格式化，

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
变量的赋值计算
-------------
定义变量 a=100,运算加100
<#assign a=100>
结果=${a+100}


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

判断变量是否存在，用双问号??或者是?exists。（和！类似）
<#if a??>
	变量a存在
</#if>

多条件判断，s?length==4可判断大小
<#assign s="java">
<#if s??&&s=="java"&&s?length==4>
	变量s正确${s}
</#if>


/*********************************
列表,列表是否存在可以用!的形式，或者时if判断
-------------
<#list dataList! as item>
	${item}  //${item}代表的是当前对象，实际应用可能是里边的属性 item.name
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




















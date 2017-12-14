项目设置时，需要设置虚拟目录。

tomcat设置方法是，在server.xml文件的host节点配置中添加如下设置。
	其中 debug 代表打印日志的级别 9打印日志最多
<Host>
	<!--以下内容添加虚拟目录-->
	<Context crosscontext="true" debug="0" docBase="D:\vm" path="/d" reloadable="true"/>
	<Context crosscontext="true" debug="0" docBase="E:\vm" path="/e" reloadable="true"/>
	<Context crosscontext="true" debug="0" docBase="F:\vm" path="/f" reloadable="true"/>
	<Context crosscontext="true" debug="0" docBase="G:\vm" path="/G" reloadable="true"/>
	<Context crosscontext="true" debug="0" docBase="H:\vm" path="/h" reloadable="true"/>
	<Context crosscontext="true" debug="0" docBase="I:\vm" path="/i" reloadable="true"/>
	<Context crosscontext="true" debug="0" docBase="J:\vm" path="/j" reloadable="true"/>
	<Context crosscontext="true" debug="0" docBase="K:\vm" path="/k" reloadable="true"/>
	<Context crosscontext="true" debug="0" docBase="L:\vm" path="/l" reloadable="true"/>
	<Context crosscontext="true" debug="0" docBase="M:\vm" path="/m" reloadable="true"/>
	<Context crosscontext="true" debug="0" docBase="N:\vm" path="/n" reloadable="true"/>
</Host>



JeeWeb敏捷开发系统平台
===============
* 	QQ交流群： ******
* 	官方网站： [http://www.jeeweb.cn](http://www.jeeweb.cn)
* 	官方论坛： [http://bbs.jeeweb.cn](http://bbs.jeeweb.cn)
* 	项目演示： [http://demo.jeeweb.cn](http://demo.jeeweb.cn)

简介
-----------------------------------
JEEWEB是一款基于SpringMVC+Spring+Hibernate的敏捷开发系统；它是一款具有代码生成功能的智能快速开发平台；是以Spring Framework为核心容器，Spring MVC为模型视图控制器，Hibernate为数据访问层， Apache Shiro为权限授权层，Ehcahe对常用数据进行缓存，Disruptor作为并发框架，Bootstrap作为前端框架的优秀开源系统。

目前功能模块代码生成器、权限框架、数据字典、数据缓存、并发框架、数据监控、计划任务、多数据源管理、附件管理、类似mybatis动态SQL、UI模板标签、短信发送、邮件发送、统计功能等功能。

JEEWEB的开发方式采用（代码生成器快速设计生成代码->手工完善逻辑->丰富模板标签快速前端开发），可以快速协助java开发人员解决60%的重复工作，让开发人员更多关注业务逻辑的实现，框架使用前端模板标签，解放JAVA开发人员的开发压力，提高开发效率，为企业节省项目研发成本，减少开发周期。

JEEWEB 技术特点
-----------------------------------
JEEWEB使用目前流程的WEB开发架构技术，如SpringMVC, Hibernate,Apache Shiro,Disruptor, ehcache, Jquery ,BootStrap等等，支持多种数据库MySQL, Oracle, sqlserver等。 分层设计：使用分层设计，分为dao，service，Controller，view层，层次清楚，低耦合，高内聚。 

安全考虑：严格遵循了web安全的规范，前后台双重验证，参数编码传输，密码md5加密存储，shiro权限验证，从根本上避免了SQL注入，XSS攻击，CSRF攻击等常见的web攻击手段。

JEEWEB 功能特点
-----------------------------------
* 	采用SpringMVC+Spring+Hibernate+Shiro+ Ehcache+Disruptor+Jquery + Boostrap + Ztree等基础前后端架构架构
* 	采用面向声明的开发模式， 基于泛型编写极少代码即可实现复杂的数据展示、数据编辑、表单处理等功能，在不使用代码生成器的情况下，也只需要很少的代码就能实现基础的CURD操作，再配合在线开发与代码生成器的使用，更加加快了开发的进度，将J2EE的开发效率成本提高，可以将代码减少60%以上。

* 	JEEWEB技术点总结:

* 	在线开发(通过在线配置实现一个表模型的增删改查功能，无需一行代码，支持用户自定义表单布局) 
* 	代码生成器，支持多种数据模型,根据表生成对应的Entity,Service,Dao,Controller,JSP等,增删改查功能生成直接使用
* 	UI标签开发库，针对前端UI进行标准封装表，页面统一采用UI标签实现功能：数据datagrid,treegrid,FileInput,Editor,GridSelectTag等，实现JSP页面零JS，开发维护简洁高效
* 	查询过滤器：只需前端配置，后台动态拼SQL追加查询条件；支持多种匹配方式（全匹配/模糊查询/包含查询/不匹配查询）
* 	移动平台支持，对Bootstrap(兼容Html5)进行标准封装
* 	国际化（支持多语言，国际化的封装为多语言做了便捷支持）
*   多数据源（在线配置数据源，数据源工作类封装）
* 	数据权限：整合Shiro权限
*   计划任务控制（在线配置计划任务、方便计划任务的时间调整规划）
*   邮件发送（配置邮件模版、邮件帐号的在线配置、邮件异步发送、邮件发送日志功能统计）
*   短信发送（配置短信模版、短信帐号的在线配置、短信异步发送、短信发送日志功能统计、支持短信发送平台动态切换）
*   多种首页风格切换,支持自定义首页风格。（Inspinia风格|ACE风格）
*   数据统计报表：丰富的报表统计功能
* 	支持多种浏览器: Google, 火狐, IE,360 等
* 	支持数据库: Mysql,Oracle10g,Postgre,SqlServer等
* 	基础权限: 用户，角色，菜单权限
* 	Web容器测试通过的有Jetty和Tomcat,Weblogic
* 	要求JDK1.7+


技术文档
-----------------------------------
* [JEEWEB 开发环境搭建入门(完善中...)]
* [JEEWEB 开发手册(完善中...)]
* [JEEWEB  常见问题贴(完善中...)]
* [JEEWEB 视频教程(完善中...)]
* [JEEWEB 官方百度网盘](http://pan.baidu.com/s/1hrFKF2k)

系统演示
-----------------------------------
###  [1].多套首页风格，支持自定义（Inspinia风格|ACE风格）
![Inspinia风格](https://git.oschina.net/uploads/images/2017/0630/210146_0479a2c1_1394985.png "Inspinia风格")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0630/210630_88f71c81_1394985.png "在这里输入图片标题")
![ACE风格](https://git.oschina.net/uploads/images/2017/0630/210406_46110a2a_1394985.png "ACE风格")

###  [2].强大的代码生成功能
![JEEWEB](https://git.oschina.net/uploads/images/2017/0630/210644_096f8b47_1394985.png "JEEWEB")
![JEEWEB](https://git.oschina.net/uploads/images/2017/0630/210653_55f01447_1394985.png "JEEWEB题")
![JEEWEB](https://git.oschina.net/uploads/images/2017/0630/210702_bca112b0_1394985.png "JEEWEB")


代码示例
-----------------------------------
###  [1].GRID列表
```
<grid:grid id="codegenGrid"  url="${adminPath}/codegen/table/ajaxList">
    <grid:column label="sys.common.key" hidden="true"   name="id"/>
    <grid:column label="codegen.table.tabletype"   width="60" name="tableType"   dict="tabletype"  query="true" queryMode="select"  />
    <grid:column label="codegen.table.table.name"   width="120"  name="tableName"  query="true" />
	<grid:column label="codegen.table.remarks"  name="remarks" />
	<grid:column label="codegen.table.sync.database"  width="80" dict="sf" formatterClass="0:label label-danger;1:label label-success" name="syncDatabase" />
	
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="300"/>
	<grid:button title="sys.common.remove"  groupname="opt" function="rowConfirm" tipMsg="确认要移除该条记录吗？" outclass="btn-warning" innerclass="fa-remove" url="${adminPath}/codegen/table/{id}/remove" />
	<grid:button groupname="opt" function="delete" tipMsg="确认要删除该条记录,删除会删除对应的表结构，请谨慎操作！" />
	<grid:button title="codegen.table.sync.database"  groupname="opt" function="rowConfirm"  tipMsg="确认要强制同步数据库吗？同步数据库将删除所有数据重新建表！" outclass="btn-info" innerclass="fa-database"  url="${adminPath}/codegen/table/{id}/syncDatabase" />
	<grid:toolbar  function="create"  winwidth = "1000px"/>
	<grid:toolbar  function="update"  winwidth = "1000px"/>
	<grid:toolbar title="codegen.table.import" icon="fa-database" function="createDialog" url="${adminPath}/codegen/table/importDatabase"  />
	<grid:toolbar title="codegen.table.gen" icon="fa-file-code-o" function="updateDialog" url="${adminPath}/codegen/table/{id}/generateCode"  />
	<grid:toolbar title="codegen.table.createmenu" icon="fa-anchor" function="updateDialog" url="${adminPath}/codegen/table/{id}/createMenu"  />
	
	<grid:toolbar  function="search"/>
	<grid:toolbar  function="reset"/>
</grid:grid>
```
![JSP列表图片](https://git.oschina.net/uploads/images/2017/0630/205011_87420e1e_1394985.png "JSP列表图片")

###  [2].TREEGRID列表
```
<grid:grid id="menuGridId" async="true" treeGrid="true"  expandColumn="name"  sortname="sort" url="${adminPath}/sys/menu/ajaxTreeList">
	<grid:column label="sys.common.key" hidden="true"   name="id" />
	<grid:column label="sys.menu.name"  name="name"  query="true" condition="like"/>
	<grid:column label="sys.menu.url"  name="url"  />
    <grid:column label="sys.menu.permission"  name="permission"  />
    <grid:column label="sys.menu.isshow"  name="isshow" dict="sf"/>
    
    <grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button   groupname="opt" function="delete" />
    
	<grid:toolbar  function="create"/>
	<grid:toolbar  function="update"/>
	<grid:toolbar  function="delete"/>
	<grid:toolbar  function="search"/>
	<grid:toolbar  function="reset"/>
</grid:grid>
```
![TREEGRID](https://git.oschina.net/uploads/images/2017/0630/205353_af457e21_1394985.png "TREEGRID")
###  [3].表单代码
```
<form:form id="userForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		       <tr>
		         <td  class="width-15 active text-right">	<label><font color="red">*</font>用户名:</label></td>
		         <td  class="width-35" >
		             <form:input path="username" class="form-control" ajaxurl="${adminPath}/sys/user/validate"  validErrorMsg="用户名重复"  htmlEscape="false"  datatype="*"  nullmsg="请输入用户名！"/>
		             <label class="Validform_checktip"></label>
		         </td>
		          <td  class="width-15 active text-right">	
		              <label><font color="red">*</font>姓名:</label>
		         </td>
		         <td class="width-35" >
		             <form:input path="realname" class="form-control " datatype="*" nullmsg="请输入姓名！" validErrorMsg="用户名重复" htmlEscape="false" />
		             <label class="Validform_checktip"></label>
		         </td>
		      </tr>
		      <tr>
		         <td  class="width-15 active text-right">	
		              <label><font color="red">*</font>邮箱:</label>
		         </td>
		         <td class="width-35" >
		             <form:input path="email" class="form-control" ajaxurl="${adminPath}/sys/user/validate"   datatype="e" nullmsg="请输入邮箱！"  htmlEscape="false" />
		             <label class="Validform_checktip"></label>
		         </td>
		         <td  class="width-15 active text-right">	
		           	 <label><font color="red">*</font>联系电话:</label>
		         </td>
		         <td  class="width-35" >
		             <form:input path="phone" class="form-control" ajaxurl="${adminPath}/sys/user/validate"  htmlEscape="false"  datatype="m"  nullmsg="请输入用户名！"/>
		             <label class="Validform_checktip"></label>
		         </td>
		      </tr>
		      <tr>
		         <td  class="width-15 active text-right">	
		              <label><font color="red">*</font>密码:</label>
		         </td>
		         <td class="width-35" >
		             <input type="password" value="" name="password"  class="form-control" datatype="*6-16" nullmsg="请设置密码！" errormsg="密码范围在6~16位之间！" />
		             <label class="Validform_checktip"></label>
		         </td>
		         <td  class="width-15 active text-right">	<label><font color="red">*</font>确认密码:</label></td>
		         <td  class="width-35" >
		             <input type="password" value="" name="userpassword2" class="form-control" datatype="*" recheck="password" nullmsg="请再输入一次密码！" errormsg="您两次输入的账号密码不一致！" />
		             <label class="Validform_checktip"></label>
		         </td>
		      </tr>
		      <tr>
		         <td class="active"><label class="pull-right"><font color="red">*</font>用户角色:</label></td>
		         <td>
		         	<form:checkboxes path="roleIdList" nested="false" items="${allRoles}" itemLabel="name" itemValue="id" htmlEscape="false" cssClass="i-checks required"/>
		          
		         </td>
		      </tr>
		      <tr>
				<td class="width-15 active"><label class="pull-right">组织机构:</label></td>
				<td colspan="3">
				   <form:treeselect title="请选择组织机构" path="organizationIds"  nested="false"  dataUrl="${adminPath}/sys/organization/treeData" labelName="parentname" labelValue="${organizationNames}" multiselect="true" />	   
				</td>
		      </tr>
		     
		   </tbody>
		   </table>   
	</form:form>
```
![表单](https://git.oschina.net/uploads/images/2017/0630/205612_2d09fd89_1394985.png "表单")




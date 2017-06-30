JeeWeb敏捷开发系统平台
===============
交流QQ群：******

简介
-----------------------------------
JEEWEB是一款基于SpringMVC+Spring+Hibernate的敏捷开发系统；它是一款具有代码生成功能的智能快速开发平台；是以Spring Framework为核心容器，Spring MVC为模型视图控制器，Hibernate为数据访问层， Apache Shiro为权限授权层，Ehcahe对常用数据进行缓存，Disruptor作为并发框架，Bootstrap作为前端框架的优秀开源系统。

目前功能模块代码生成器、权限框架、数据字典、数据缓存、并发框架、数据监控、计划任务、多数据源管理、附件管理、类似mybatis动态SQL、UI模板标签、短信发送、邮件发送、统计功能等功能。

JEEWEB的开发方式采用（代码生成器快速设计生成代码->手工完善逻辑->丰富模板标签快速前端开发），可以快速协助java开发人员解决60%的重复工作，让开发人员更多关注业务逻辑的实现，框架使用前端模板标签，解放JAVA开发人员的开发压力，提高开发效率，为企业节省项目研发成本，减少开发周期。

技术文档
-----------------------------------
* [JEECG 开发环境搭建入门](http://blog.csdn.net/zhangdaiscott/article/details/50915206)
* [JEECG 开发手册](http://git.oschina.net/jeecg/jeecg/attach_files)
* [JEECG 常见问题贴](http://www.jeecg.org/forum.php?mod=viewthread&tid=1830&extra=page%3D1)
* [JEECG 视频教程](http://www.jeecg.org/forum.php?mod=viewthread&tid=197&extra=page%3D1)
* [JEECG 官方百度网盘](https://pan.baidu.com/share/home?uk=2668473880#category/type=0)
* [JEECG 版本更新日志](http://www.jeecg.org/forum.php?mod=viewthread&tid=365&extra=page%3D1)
* JEECG官方Maven镜像配置 : [http://t.cn/RJCp7wO](http://t.cn/RJCp7wO)
* 在线演示地址：[http://demo.jeecg.org](http://demo.jeecg.org)
    
技术交流
-----------------------------------
* 	QQ交流群： ③289782002、②106838471(满)、①106259349(满)、④176031980(满)</br>
* 	官方论坛： [http://www.jeecg.org](http://www.jeecg.org)
* 	官方网站： [http://www.guojusoft.com](http://www.guojusoft.com)
* 	官方博客： [http://blog.csdn.net/zhangdaiscott](http://blog.csdn.net/zhangdaiscott)
* 	技术支持： [JEECG社区官方支持QQ群汇总](http://www.jeecg.org/forum.php?mod=viewthread&tid=1249&extra=page%3D1)


系统演示
-----------------------------------
###  [1].多套首页风格，支持自定义（Inspinia风格|ACE风格）



###  [2].强大的代码生成功能


###  [3].完善的权限体系


代码示例
-----------------------------------
###  [1].GRID列表
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
![JSP列表图片](https://git.oschina.net/uploads/images/2017/0630/205011_87420e1e_1394985.png "JSP列表图片")

###  [2].TREEGRID列表
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
![TREEGRID](https://git.oschina.net/uploads/images/2017/0630/205353_af457e21_1394985.png "TREEGRID")
###  [3].完善的权限体系
    
    




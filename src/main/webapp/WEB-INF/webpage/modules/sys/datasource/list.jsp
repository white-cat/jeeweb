<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="sys.datasource.title" /></title>
  <meta name="decorator" content="list"/>
</head>
<body title="<spring:message code="sys.datasource.title" />">
<grid:grid id="datasourceGridId" url="${adminPath}/sys/datasource/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button title="sys.common.delete"  groupname="opt" function="delObj" outclass="btn-danger" innerclass="fa-trash" url="${adminPath}/sys/datasource/delete" />
    <grid:column label="数据库关键字"  name="dbKey"  query="true"  />
    <grid:column label="数据库类型"  name="dbType"  dict="dbtype" query="true"  queryMode="select"  />
    <grid:column label="驱动类"  name="driverClass"  />
    <grid:column label="数据源地址"  name="url"  />
    <grid:column label="数据库名称"  name="dbName"  />
    <grid:column label="数据库用户名"  name="dbUser"  />
    <grid:column label="数据库密码"  name="dbPassword"  />

	<grid:toolbar title="sys.common.add" icon="fa-plus" function="add" url="${adminPath}/sys/datasource/edit"  />
	<grid:toolbar title="sys.common.update" icon="fa-file-text-o" function="update" url="${adminPath}/sys/datasource/edit"  />
	<grid:toolbar title="sys.common.delete" icon="fa-trash-o" function="deleteALLSelect" url="${adminPath}/sys/datasource/batchDelete"  />
	
	<grid:toolbar  layout="right" title="sys.common.search" icon="fa-search"  function="doSearch"  />
	<grid:toolbar  layout="right" title="sys.common.reset" icon="fa-refresh"  function="searchReset"  />
</grid:grid>
</body>
</html>
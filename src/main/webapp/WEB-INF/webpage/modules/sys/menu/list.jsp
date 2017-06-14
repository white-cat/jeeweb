<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="sys.menu.title" /></title>
  <meta name="decorator" content="list"/>
</head>
<body title="<spring:message code="sys.menu.title" />">
<grid:grid id="menuGridId" async="true" treeGrid="true"  expandColumn="name"  sortname="sort" url="${adminPath}/sys/menu/ajaxTreeList">
	<grid:column label="sys.common.key" hidden="true"   name="id" />
	<grid:column label="sys.menu.name"  name="name"  query="true" condition="like"/>
	<grid:column label="sys.menu.url"  name="url"  />
    <grid:column label="sys.menu.permission"  name="permission"  />
    <grid:column label="sys.menu.isshow"  name="isshow" dict="sf"/>
    
    <grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button title="sys.common.delete"  groupname="opt" function="delObj" outclass="btn-danger" innerclass="fa-trash" url="${adminPath}/sys/menu/delete" />
    
	<grid:toolbar title="sys.common.add" icon="fa-plus" function="add" url="${adminPath}/sys/menu/edit"  />
	<grid:toolbar title="sys.common.update" icon="fa-file-text-o" function="update" url="${adminPath}/sys/menu/edit"  />
	<grid:toolbar title="sys.common.delete" icon="fa-trash-o" function="deleteALLSelect" url="${adminPath}/sys/menu/batchDelete"  />
	
	<grid:toolbar  layout="right" title="sys.common.search" icon="fa-search"  function="doSearch"  />
	<grid:toolbar  layout="right" title="sys.common.reset" icon="fa-refresh"  function="searchReset"  />
</grid:grid>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="sys.organization.title" /></title>
  <meta name="decorator" content="list"/>
</head>
<body title="<spring:message code="sys.organization.title" />">
<grid:grid id="organizationGridId" async="true" treeGrid="true" expandColumn="name"   url="${adminPath}/sys/organization/ajaxTreeList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="sys.organization.name"  name="name"  query="true" condition="like"   />
	<grid:column label="sys.organization.remarks"  name="remarks"  />
    <grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button title="sys.common.delete"  groupname="opt" function="delObj" outclass="btn-danger" innerclass="fa-trash" url="${adminPath}/sys/organization/delete" />
    
	<grid:toolbar title="sys.common.add" icon="fa-plus" function="add" url="${adminPath}/sys/organization/edit"  />
	<grid:toolbar title="sys.common.update" icon="fa-file-text-o" function="update" url="${adminPath}/sys/organization/edit"  />
	<grid:toolbar title="sys.common.delete" icon="fa-trash-o" function="deleteALLSelect" url="${adminPath}/sys/organization/batchDelete"  />
	
	<grid:toolbar  layout="right" title="sys.common.search" icon="fa-search"  function="doSearch"  />
	<grid:toolbar  layout="right" title="sys.common.reset" icon="fa-refresh"  function="searchReset"  />
</grid:grid>
</body>
</html>
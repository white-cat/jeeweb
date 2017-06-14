<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="sys.role.title" /></title>
  <meta name="decorator" content="list"/>
</head>
<body title="<spring:message code="sys.role.title" />">
<grid:grid id="roleGridId" url="${adminPath}/sys/role/ajaxList">
    <div>
       <form:hidden path="id" nested="false" />
        <form:input path="nametest" nested="false" />
    </div>
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button title="sys.role.authMenu"  groupname="opt" function="updateObj" outclass="btn-primary" winwidth="300px" innerclass="fa-plus" url="${adminPath}/sys/role/authMenu" />
	<grid:button title="sys.common.delete"  groupname="opt" function="delObj" outclass="btn-danger" innerclass="fa-trash" url="${adminPath}/sys/role/delete" />
    <grid:column label="sys.role.name"  name="name"  query="true"  />
    <grid:column label="sys.role.code"  name="code"  query="true"  />
    <grid:column label="sys.role.isSys"  name="isSys"  dict="sf" />
    <grid:column label="sys.role.usable"  name="usable"  dict="sf"/>

	<grid:toolbar title="sys.common.add" icon="fa-plus" function="add" url="${adminPath}/sys/role/edit"  />
	<grid:toolbar title="sys.common.update" icon="fa-file-text-o" function="update" url="${adminPath}/sys/role/edit"  />
	<grid:toolbar title="sys.common.delete" icon="fa-trash-o" function="deleteALLSelect" url="${adminPath}/sys/role/batchDelete"  />
	
	<grid:toolbar  layout="right" title="sys.common.search" icon="fa-search"  function="doSearch"  />
	<grid:toolbar  layout="right" title="sys.common.reset" icon="fa-refresh"  function="searchReset"  />
</grid:grid>
</body>
</html>
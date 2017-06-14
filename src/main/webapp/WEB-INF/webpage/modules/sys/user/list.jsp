<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="sys.user.title" /></title>
  <meta name="decorator" content="list"/>
</head>
<body title="<spring:message code="sys.user.title" />">
<grid:grid id="userGridId" url="${adminPath}/sys/user/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button title="sys.common.delete"  groupname="opt" function="delObj" outclass="btn-danger" innerclass="fa-trash" url="${adminPath}/sys/user/delete" />
    <grid:column label="sys.user.realname"  name="realname"  query="true"  />
    <grid:column label="sys.user.username"  name="username"  query="true" condition="like"   />
    <grid:column label="sys.user.email"  name="email"  />
    <grid:column label="sys.user.phone"  name="phone"  />

	<grid:toolbar title="sys.user.createuser" icon="fa-plus" function="add" url="${adminPath}/sys/user/create"  />
	<grid:toolbar title="sys.user.updateuser" icon="fa-file-text-o" function="update" url="${adminPath}/sys/user/edit"  />
	<grid:toolbar title="sys.user.modifypwd" icon="fa-database" function="update" url="${adminPath}/sys/user/changePassword"  />
	<grid:toolbar title="sys.common.delete" icon="fa-trash-o" function="deleteALLSelect" url="${adminPath}/sys/user/batchDelete"  />
	
	<grid:toolbar  layout="right" title="sys.common.search" icon="fa-search"  function="doSearch"  />
	<grid:toolbar  layout="right" title="sys.common.reset" icon="fa-refresh"  function="searchReset"  />
</grid:grid>
</body>
</html>
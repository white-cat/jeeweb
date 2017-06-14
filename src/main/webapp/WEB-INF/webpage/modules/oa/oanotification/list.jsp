<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>通知公告列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="通知公告">
<grid:grid id="oaNotificationGridId" url="${adminPath}/oa/oanotification/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="40"/>
	<grid:button title="sys.common.delete"  groupname="opt" function="delObj" outclass="btn-danger" innerclass="fa-trash" url="${adminPath}/oa/oanotification/delete" />
    <grid:column label="标题"  name="title"  query="true"  queryModel="input" width="100"   condition="like" />
    <grid:column label="内容"  name="content"  query="true"  queryModel="input"  condition="like" />
    <grid:column label="发布状态"  name="status"  query="true" queryMode="select" width="60"  condition="eq"  dict="sf"/>
	<grid:toolbar title="sys.common.add" icon="fa-plus" function="add" url="${adminPath}/oa/oanotification/edit"  />
	<grid:toolbar title="sys.common.update" icon="fa-file-text-o" function="update" url="${adminPath}/oa/oanotification/edit"  />
	<grid:toolbar title="sys.common.delete" icon="fa-trash-o" function="deleteALLSelect" url="${adminPath}/oa/oanotification/batchDelete"  />
	
	<grid:toolbar  layout="right" title="sys.common.search" icon="fa-search"  function="doSearch"  />
	<grid:toolbar  layout="right" title="sys.common.reset" icon="fa-refresh"  function="searchReset"  />
</grid:grid>
</body>
</html>
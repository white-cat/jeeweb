<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="sys.online.title" /></title>
  <meta name="decorator" content="list"/>
</head>
<body title="<spring:message code="sys.online.title" />">
<grid:grid id="onlineGridId" url="${adminPath}/sys/online/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button title="sys.common.delete"  groupname="opt" function="delObj" outclass="btn-danger" innerclass="fa-trash" url="${adminPath}/sys/online/delete" />
    <grid:column label="用户"  name="username"  query="true"  />
    <grid:column label="用户主机IP"  name="host" />
    <grid:column label="系统主机IP"  name="systemHost" />
    <grid:column label="登录时间"  name="startTimestamp" dateformat='yyyy-MM-dd'/>
    <grid:column label="最后访问时间"  name="lastAccessTime"  dateformat='yyyy-MM-dd' />
    <grid:column label="状态"  name="status" dict="onlinestatus" />
    <grid:column label="User-Agent"  name="userAgent"  />
    <grid:column label="用户会话ID"  name="id"  />
 
	<grid:toolbar title="强制退出" icon="fa-trash-o" function="toolbarConfirm"  url="${adminPath}/sys/online/forceLogout"  tipMsg="您确定要强制退出这些信息么，请谨慎操作！"/>
 	
	<grid:toolbar  layout="right" title="sys.common.search" icon="fa-search"  function="doSearch"  />
	<grid:toolbar  layout="right" title="sys.common.reset" icon="fa-refresh"  function="searchReset"  />
</grid:grid>
</body>
</html>
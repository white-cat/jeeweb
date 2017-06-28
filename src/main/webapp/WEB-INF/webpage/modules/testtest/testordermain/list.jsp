<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>订单主表列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="订单主表">
<grid:grid id="testOrderMainGridId" url="${adminPath}/testtest/testordermain/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button title="sys.common.delete"  groupname="opt" function="delObj" outclass="btn-danger" innerclass="fa-trash" url="${adminPath}/testtest/testordermain/delete" />
    <grid:column label="订单号"  name="orderno" />
    <grid:column label="订单日期"  name="orderdate" />
	<grid:toolbar title="sys.common.add" icon="fa-plus" function="add" url="${adminPath}/testtest/testordermain/edit"  />
	<grid:toolbar title="sys.common.update" icon="fa-file-text-o" function="update" url="${adminPath}/testtest/testordermain/edit"  />
	<grid:toolbar title="sys.common.delete" icon="fa-trash-o" function="deleteALLSelect" url="${adminPath}/testtest/testordermain/batchDelete"  />
	
	<grid:toolbar  layout="right" title="sys.common.search" icon="fa-search"  function="doSearch"  />
	<grid:toolbar  layout="right" title="sys.common.reset" icon="fa-refresh"  function="searchReset"  />
</grid:grid>
</body>
</html>
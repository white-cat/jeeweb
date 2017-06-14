<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>短信模版列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="短信模版">
<grid:grid id="smsTemplateGridId" url="${adminPath}/sms/smstemplate/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button title="sys.common.delete"  groupname="opt" function="delObj" outclass="btn-danger" innerclass="fa-trash" url="${adminPath}/sms/smstemplate/delete" />
    <grid:column label="模版名称"  name="name"  query="true"  queryModel="input"  condition="like" />
    <grid:column label="模版编码"  name="code"  query="true"  queryModel="input"  condition="like" />
    <grid:column label="业务类型"  name="businessType"  query="true"  queryModel="select"  condition="eq"  dict="businesstype"/>
    <grid:column label="模版ID"  name="templateId" />
    <grid:column label="模版内容"  name="templateContent" />
	<grid:toolbar title="sys.common.add" icon="fa-plus" function="add" url="${adminPath}/sms/smstemplate/edit"  />
	<grid:toolbar title="sys.common.update" icon="fa-file-text-o" function="update" url="${adminPath}/sms/smstemplate/edit"  />
	<grid:toolbar title="sys.common.delete" icon="fa-trash-o" function="deleteALLSelect" url="${adminPath}/sms/smstemplate/batchDelete"  />
	
	<grid:toolbar  layout="right" title="sys.common.search" icon="fa-search"  function="doSearch"  />
	<grid:toolbar  layout="right" title="sys.common.reset" icon="fa-refresh"  function="searchReset"  />
</grid:grid>
</body>
</html>
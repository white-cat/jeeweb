<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>邮件发送模板列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="邮件发送模板">
<grid:grid id="emailTemplateGridId" url="${adminPath}/email/emailtemplate/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="sys.common.opt"  name="opt" type="button" width="100"/>
	<grid:button title="sys.common.delete"  groupname="opt" function="delObj" outclass="btn-danger" innerclass="fa-trash" url="${adminPath}/email/emailtemplate/delete" />
    <grid:column label="模版名称"  name="name" />
    <grid:column label="模版编码"  name="code" />
    <grid:column label="业务类型"  name="businessType"  dict="businesstype"/>
    <grid:column label="模版主题"  name="templateSubject" />
    <grid:column label="创建时间"  name="createDate" />
	<grid:toolbar title="sys.common.add" icon="fa-plus" function="add" url="${adminPath}/email/emailtemplate/edit"  />
	<grid:toolbar title="sys.common.update" icon="fa-file-text-o" function="update" url="${adminPath}/email/emailtemplate/edit"  />
	<grid:toolbar title="sys.common.delete" icon="fa-trash-o" function="deleteALLSelect" url="${adminPath}/email/emailtemplate/batchDelete"  />
	
	<grid:toolbar  layout="right" title="sys.common.search" icon="fa-search"  function="doSearch"  />
	<grid:toolbar  layout="right" title="sys.common.reset" icon="fa-refresh"  function="searchReset"  />
</grid:grid>
</body>
</html>
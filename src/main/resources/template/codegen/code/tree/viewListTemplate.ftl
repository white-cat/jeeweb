<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>${functionName}列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="${functionName}">
<grid:grid id="${entityName?uncap_first}GridId" url="${r'${adminPath}'}/${moduleName}/${entityName?lower_case}/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button title="sys.common.delete"  groupname="opt" function="delObj" outclass="btn-danger" innerclass="fa-trash" url="${r'${adminPath}'}/${moduleName}/${entityName?lower_case}/delete" />
<#list columns as column>
   <#if column.isList>
    <grid:column label="<#if column.remarks??&&column.remarks!="">${column.javaField}<#else>${column.remarks}</#if>"  name="${column.javaField}" <#if column.isQuery> query="true" <#if column.queryModel??&&column.queryModel!=""> queryModel="${column.queryModel}"</#if> <#if column.queryType??&&column.queryType!=""> condition="${column.queryType}" </#if></#if><#if column.dictGroup??&&column.dictGroup!=""> dict="${column.dictGroup}"</#if>/>
   </#if>
</#list>
	<grid:toolbar title="sys.common.add" icon="fa-plus" function="add" url="${r'${adminPath}'}/${moduleName}/${entityName?lower_case}/edit"  />
	<grid:toolbar title="sys.common.update" icon="fa-file-text-o" function="update" url="${r'${adminPath}'}/${moduleName}/${entityName?lower_case}/edit"  />
	<grid:toolbar title="sys.common.delete" icon="fa-trash-o" function="deleteALLSelect" url="${r'${adminPath}'}/${moduleName}/${entityName?lower_case}/batchDelete"  />
	
	<grid:toolbar  layout="right" title="sys.common.search" icon="fa-search"  function="doSearch"  />
	<grid:toolbar  layout="right" title="sys.common.reset" icon="fa-refresh"  function="searchReset"  />
</grid:grid>
</body>
</html>
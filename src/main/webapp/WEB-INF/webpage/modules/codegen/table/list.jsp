<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="codegen.table.title" /></title>
  <meta name="decorator" content="list"/>
</head>
<body title="<spring:message code="codegen.table.title" />">
<grid:grid id="codegenGrid"  url="${adminPath}/codegen/table/ajaxList">
    <grid:column label="sys.common.key" hidden="true"   name="id"/>
    <grid:column label="codegen.table.tabletype"   width="60" name="tableType"   dict="tabletype"  query="true" queryMode="select"  />
    <grid:column label="codegen.table.table.name"   width="120"  name="tableName"  query="true" />
	<grid:column label="codegen.table.remarks"  name="remarks" />
	<grid:column label="codegen.table.sync.database"  dict="sf" name="syncDatabase" />
	
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="300"/>
	<grid:button title="sys.common.remove"  groupname="opt" function="delObj" outclass="btn-warning" innerclass="fa-remove" url="${adminPath}/codegen/table/remove" />
	<grid:button title="sys.common.delete"  groupname="opt" function="delObj" outclass="btn-danger" innerclass="fa-trash" url="${adminPath}/codegen/table/delete" />
	<grid:button title="codegen.table.sync.database"  groupname="opt" onclick="syncDatabase(row.id)" outclass="btn-info" innerclass="fa-database"  />
	<grid:toolbar title="sys.common.add" icon="fa-plus" function="add" winwidth = "1000px"  url="${adminPath}/codegen/table/edit"  />
	<grid:toolbar title="sys.common.update" icon="fa-file-text-o" function="update"  winwidth = "1000px"  url="${adminPath}/codegen/table/edit"  />
	<grid:toolbar title="codegen.table.import" icon="fa-database" function="add" url="${adminPath}/codegen/table/importDatabase"  />
	<grid:toolbar title="codegen.table.gen" icon="fa-file-code-o" function="update" url="${adminPath}/codegen/table/generateCode"  />
	<grid:toolbar title="codegen.table.createmenu" icon="fa-anchor" function="update" url="${adminPath}/codegen/table/createMenu"  />
	
	<grid:toolbar  layout="right" title="sys.common.search" icon="fa-search"  function="doSearch"  />
	<grid:toolbar  layout="right" title="sys.common.reset" icon="fa-refresh"  function="searchReset"  />
</grid:grid>

<script>

/**
 * 多记录刪除請求
 * @param title
 * @param url
 * @param gname
 * @return
 */
function syncDatabase(infoid) {
	   swal({
            title: "提示",
            text: "确认要强制同步数据库吗？同步数据库将删除所有数据重新建表！",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定",
            closeOnConfirm: false,
            cancelButtonText: "取消",
        }, function () {
			$.ajax({
				url : '${adminPath}/codegen/table/syncDatabase',
				type : 'post',
				data : {
					id : infoid
				},
				cache : false,
				success : function(d) {
					if (d.ret==0) {
						var msg = d.msg;
					    swal("同步成功！", msg, "success");
					    //刷新表单
			            refreshTable("codegenGrid");
					}else{
						var msg = d.msg;
					    swal("同步失败！", msg, "error");
					}
				}
			});
        });
}
</script>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="sys.dict.group.title" /></title>
  <meta name="decorator" content="list"/>
</head>
<body title="<spring:message code="sys.dict.group.title" />">
<grid:grid id="groupGridId" url="${adminPath}/sys/dict/group/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button title="sys.dict.group.adddict"  groupname="opt" function="rowDialogDetailRefresh" outclass="btn-primary"  innerclass="fa-plus" url="${adminPath}/sys/dict?gid=\"+row.id+\"" />
	<grid:button title="sys.common.delete"  groupname="opt" function="delObj" outclass="btn-danger" innerclass="fa-trash" url="${adminPath}/sys/dict/group/delete" />
    <grid:column label="sys.dict.group.name"  name="name"  query="true"  />
    <grid:column label="sys.dict.group.code"  name="code"  query="true"  />
    
	<grid:toolbar title="sys.common.add" icon="fa-plus" function="add" url="${adminPath}/sys/dict/group/edit"  />
	<grid:toolbar title="sys.common.update" icon="fa-file-text-o" function="update" url="${adminPath}/sys/dict/group/edit"  />
	<grid:toolbar title="sys.common.delete" icon="fa-trash-o" function="deleteALLSelect" url="${adminPath}/sys/dict/group/batchDelete"  />
	<grid:toolbar title="强制刷新"  icon="fa-refresh"  onclick="forceRefresh()"  />
	
	<grid:toolbar  layout="right" title="sys.common.search" icon="fa-search"  function="doSearch"  />
	<grid:toolbar  layout="right" title="sys.common.reset" icon="fa-refresh"  function="searchReset"  />
</grid:grid>
<script>
function forceRefresh() {
	   swal({
            title: "提示",
            text: "您确定强制刷新字典！",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定",
            closeOnConfirm: false,
            cancelButtonText: "取消",
        }, function () {
			$.ajax({
				url : '${adminPath}/sys/dict/group/forceRefresh',
				type : 'post',
				cache : false,
				success : function(d) {
					if (d.ret==0) {
						var msg = d.msg;
					    swal("提示！", msg, "success");
					}else{
						var msg = d.msg;
					    swal("提示！", msg, "error");
					}
				}
			});
        });
 }
</script>
</body>
</html>
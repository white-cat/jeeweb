<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>自动搜索</title>
<meta name="keywords"
	content="<spring:message code="sys.site.keywords" arguments="${platformName}"/>">
<meta name="description"
	content="<spring:message code="sys.site.description" arguments="${platformName}"/>">

<html:css
	name="favicon,bootstrap,font-awesome,animate,syntaxhighlighter" />
<html:js name="jquery,bootstrap,syntaxhighlighter" />
<link href="${staticPath}/common/css/style.css?v=4.1.0" rel="stylesheet">
<html:component name="bootstrap-fileinput" />
</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>自动搜索</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a> <a class="close-link"> <i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">

						<p>文件上传标签</p>
						<html:codedisplay>
					      <form:combox nested="false" effectiveFields="realname" effectiveFieldsAlias="realname|用户名"  
                                  showHeader="true" path="infoid"  dataUrl="${adminPath}/demo/form/ajaxCombox" />
					    </html:codedisplay>
						<form:combox nested="false" idField="id" keyField="realname" effectiveFields="realname" multiWord="true"
							effectiveFieldsAlias="realname|用户名" showHeader="true" path="infoid"
							dataUrl="${adminPath}/demo/form/ajaxCombox" />
					</div>
				</div>
			</div>
		</div>

	</div>
	<!-- 全局js -->
	<html:js name="iCheck,Validform,suggest" />
	<!-- 自定义js -->
	<script src="${staticPath}/common/js/content.js?v=1.0.0"></script>
	<script type="text/javascript">
		
	</script>
</body>

</html>
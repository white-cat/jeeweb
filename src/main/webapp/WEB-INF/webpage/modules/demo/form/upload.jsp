<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>文件上传</title>
<meta name="keywords"
	content="<spring:message code="sys.site.keywords" arguments="${platformName}"/>">
<meta name="description"
	content="<spring:message code="sys.site.description" arguments="${platformName}"/>">

<html:css
	name="favicon,bootstrap,font-awesome,animate,iCheck,Validform,markdown,syntaxhighlighter" />
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
						<h5>文件上传</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a> <a class="close-link"> <i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">

						<p>文件上传标签</p>
						<html:codedisplay>
					        <form:fileinput nested="false" path="infoid" buttonText="选择文件"
							defaultValue="40288ab85c33548d015c337aa269002d,40288ab85c33548d015c338ef0c90030,40288ab85c33548d015c338fd1ce0032" />
					    </html:codedisplay>
						<form:fileinput   nested="false" path="infoid" buttonText="选择文件"/>
					</div>
					 
				</div>
			</div>
		</div>

	</div>
	<!-- 全局js -->
	<html:js name="iCheck,Validform,markdown" />
	<!-- 自定义js -->
	<script src="${staticPath}/common/js/content.js?v=1.0.0"></script>
	<script>
	  function refreshCallback(attachmentList){
		  // var fileid= attachmentList[i].data.id;
	  }
	</script>
</body>

</html>
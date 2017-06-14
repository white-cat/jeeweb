<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>折线图</title>
<meta name="keywords" content="<spring:message code="sys.site.keywords" arguments="${platformName}"/>">
<meta name="description" content="<spring:message code="sys.site.description" arguments="${platformName}"/>">

<html:css name="favicon,bootstrap,font-awesome,animate" />
<html:js name="jquery,bootstrap" />
<link href="${staticPath}/common/css/style.css?v=4.1.0" rel="stylesheet">
<html:component name="bootstrap-fileinput" />
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>折线图</h5>
                        <div class="ibox-tools">
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="echarts" id="echarts-line-chart"></div>
                    </div>
                </div>
            </div>
         </div> 
    </div>
    
	<!-- 全局js -->
	<html:js name="echarts" />
	<script src="${staticPath}/modules/charts/js/echarts/${charttype}.js"></script>
</body>

</html>
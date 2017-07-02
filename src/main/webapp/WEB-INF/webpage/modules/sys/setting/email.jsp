<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>邮件配置</title>
    <meta name="keywords" content="<spring:message code="sys.site.keywords" arguments="${platformName}"/>">
	<meta name="description" content="<spring:message code="sys.site.description" arguments="${platformName}"/>">
	
    <link rel="shortcut icon" href="favicon.ico">
    <html:css  name="bootstrap,font-awesome,animate,iCheck,Validform"/>
    <link href="${staticPath}/common/css/style.css?v=4.1.0" rel="stylesheet">
	<link href="${staticPath}/iCheck/custom.css" rel="stylesheet">
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
       <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>邮件配置</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                         <form:form id="emailForm" modelAttribute="data" action="${adminPath}/sys/setting/email" method="post" class="form-horizontal">
                            <div class="form-group col-sm-6">
                                <label class="col-sm-4 control-label"><font color="red">*</font>邮件服务器：</label>
                                <div class="col-sm-8">
                                	<form:input path="host" class="form-control"  datatype="*"   htmlEscape="false" />
                                </div>
                            </div>
                            <div class="form-group col-sm-6">
                                <label class="col-sm-4 control-label"><font color="red">*</font>邮件发送端口：</label>
                                <div class="col-sm-8">
                                    <form:input path="port" class="form-control" defaultvalue="25"   datatype="*"    htmlEscape="false" />
                                </div>
                            </div>
                            <div class="form-group col-sm-6">
                                <label class="col-sm-4 control-label"><font color="red">*</font>是否认证：</label>
                                <div class="col-sm-8">
                                    <form:radiobuttons path="auth"   items="${trueOrFalse}"  datatype="*"    cssClass="form-control i-checks" />
                                </div>
                            </div>
                            <div class="form-group col-sm-6">
                                <label class="col-sm-4 control-label"><font color="red">*</font>认证用户名：</label>
                                <div class="col-sm-8">
                                    <form:input path="username"   defaultvalue=""   datatype="*"  htmlEscape="false" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group col-sm-6">
                                <label class="col-sm-4 control-label"><font color="red">*</font>认证密码：</label>
                                <div class="col-sm-8">
                                    <form:input path="password"    defaultvalue=""   datatype="*"  htmlEscape="false" cssClass="form-control" />
                                </div>
                            </div>
  							<div class="form-group col-sm-6">
                                <label class="col-sm-4 control-label"><font color="red">*</font>发件人地址：</label>
                                <div class="col-sm-8">
                                    <form:input path="sender"   datatype="e"  htmlEscape="false" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group col-sm-6">
                                <label class="col-sm-4 control-label">测试邮箱：</label>
                                <div class="col-sm-8">
                                    <form:input path="testemail" nested="false"   htmlEscape="false" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group col-sm-6">
                                <label class="col-sm-4 control-label">测试文本：</label>
                                <div class="col-sm-8">
                                    <form:textarea path="testcontent"  cssClass="form-control" nested="false" rows="3" cols="30" />
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-3" >
                                    <button class="btn btn-primary" type="submit">提交</button>
                                    <input  class="btn btn btn-warning" type="button" onclick="testEmail();" value="测试" />
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>

    </div>


    <!-- 全局js -->
    <html:js  name="jquery,bootstrap,iCheck,Validform"/>

	<!-- 自定义js -->
	<script src="${staticPath}/common/js/content.js?v=1.0.0"></script>
 		
	<script type="text/javascript">
	        //初始化表单
			var validateForm;
			$(document).ready(function() {
			        validateForm = $("#emailForm").Validform({
			        tiptype:3,
			        ajaxPost:true,
			        callback:function(result){
			        	$.Hidemsg();
						if(result.ret==0)
		              	{
		              	    top.layer.alert(result.msg, {icon: 0, title:'提示'});
		              	}else
		              	{
		              		top.layer.alert(result.msg, {icon: 0, title:'警告'});
		              	} 
					}
				});
			});
			//初始化checkbox样式
			$(document).ready(function() {
				$(".i-checks").iCheck({
			           checkboxClass: 'icheckbox_square-green',
			           radioClass: 'iradio_square-green',
			    });
			});
			
			function testEmail(){
				$("#emailForm").attr("action", "${adminPath}/sys/setting/testEmail");
				validateForm.ajaxPost();
				return false;
			}
			
			 
	</script>

</body>

</html>

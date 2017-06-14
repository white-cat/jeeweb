<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>订单主菜单</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor,jqgrid" />
</head>

<body class="white-bg"  formid="orderMainForm" beforeSubmit="beforeSubmit">
    <form:form id="orderMainForm" modelAttribute="data" action="${adminPath}/test/ordermain/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>订单号:</label>
		            </td>
					<td class="width-35">
						<form:input path="orderno" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>订单金额:</label>
		            </td>
					<td class="width-35">
						<form:input path="money" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">	
		              <label><font color="red">*</font>订单日期:</label>
		            </td>
					<td class="width-35">
					   <form:input path="orderdate" htmlEscape="false"  datefmt="yyyy-MM-dd HH:mm:ss" class="form-control layer-date" placeholder="YYYY-MM-DD hh:mm:ss" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"       />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">	
		              <label>备注信息:</label>
		            </td>
					<td class="width-35">
						<form:textarea path="remarks" rows="4" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
		      
		   </tbody>
		</table>   
	</form:form>
	<div class="row">
                <div class="tabs-container">
                    <ul class="nav nav-tabs">
                    	<li class="active"><a data-toggle="tab" href="#tab_orderTicket" aria-expanded="true">订单机票信息</a></li>
                    	<li class=""><a data-toggle="tab" href="#tab_orderCustomer" aria-expanded="false">订单客户信息</a></li>
                    </ul>
                    <div class="tab-content">
                         <div id="tab_orderTicket" class="tab-pane active">
                            <div class="panel-body">
                                <grid:grid id="orderTicket" datas="${data.orderTicketList}"  gridShowType="form" pageable="false"  editable="true">
								    <grid:column label="航班号" datatype="s1-5" errormsg="我来测试拉拉" name="fltno"  editable="true"   />
   									<grid:column label="航班时间"   name="flytime"  editable="true"  edittype="date"   dateformat="yyyy年MM月dd日"/>
								</grid:grid>
							</div>
                        </div>
                        <div id="tab_orderCustomer" class="tab-pane">
                       	    <div class="panel-body">
                       	        <grid:grid id="orderCustomer" datas="${data.orderCustomerList}"  gridShowType="form"  pageable="false"   editable="true">
									<grid:column label="客户姓名"  editable="true"  name="name" />
								    <grid:column label="性别"  editable="true"  name="sex" />
								    <grid:column label="电话"  editable="true"  name="phone" />
								</grid:grid>
							</div>
                        </div>
                        
                    </div>
                </div>
            </div>
	</div>
<html:js name="bootstrap-fileinput" />
<html:js name="simditor,jqgrid,jqGrid_curdtools,jqGrid_curdtools_inline" />
<script>
	$(document).ready(function () {
	    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
	    	 resizeGrid();
		});
	});
	$(function(){
	   $(window).resize(function(){   
		   resizeGrid();
	   });
	});
	function resizeGrid(){
		 $("#orderTicketGrid").setGridWidth($(window).width()-60);
	     $("#orderCustomerGrid").setGridWidth($(window).width()-60);
	}

	/**
	*提交回调方法
	*/
	function beforeSubmit(curform){
		 //这里最好还是使用JSON提交，控制器改变提交方法，并使用JSON方式来解析数
		 //通过判断，如果有问题不提交
		 if(!initGridFormData(curform,"orderTicket"))return false;
		 if(!initGridFormData(curform,"orderCustomer"))return false;
		 return true;
	}
	
	function initGridFormData(curform,gridId){
		 var rowDatas =getRowDatas(gridId+"Grid");
		 var rowJson = JSON.stringify(rowDatas);
		 if(rowJson.indexOf("editable") > 0&&rowJson.indexOf("inline-edit-cell") )
	     {
	    	 return false;
	     }
		 var gridListJson=$('#'+gridId+"ListJson").val();
		 if(gridListJson==undefined||gridListJson==''){
			 var rowInput = $('<input id="'+gridId+'ListJson" type="hidden" name="'+gridId+'ListJson" />');  
			 rowInput.attr('value', rowJson);  
			 // 附加到Form  
			 curform.append(rowInput); 
		 }else{
			 $('#'+gridId+"ListJson").val(rowJson);
		 }
		 return true;
	}
</script>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>添加税查询</title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<link type="text/css" rel="stylesheet"  href="${ctx}/res/css/styles.css"/>
	<script type="text/javascript" src="${ctx}/res/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">

		//后台提示
		$(document).ready(function(){
			
			//后台校验错误信息
			var errInfo = "${requestScope.errInfo}";
			if (errInfo != null && errInfo.length > 0){
				alert(errInfo);
			}
			
		});
		
		//返回
		function returnList(){
			document.getElementById("editForm").action = "${ctx}/hscode/HsCodeList.action";
			document.getElementById("editForm").submit();
		}
		
		//保存之前的数据校验
		function check(){
			
			var CDescription = document.getElementById("CDescription").value;
			if ($.trim(CDescription) == ""){
				alert("Description不能为空");
				document.getElementById("CDescription").focus();
				return false;
			}
			
			var CRate = document.getElementById("CRate").value;
			if ($.trim(CRate) == ""){
				alert("Rate不能为空");
				document.getElementById("CRate").focus();
				return false;
			}
			
			var CUnit = document.getElementById("CUnit").value;
			if ($.trim(CUnit) == ""){
				alert("Unit不能为空");
				document.getElementById("CUnit").focus();
				return false;
			}
			
			return true;
		}
	</script>
  </head>
  <body>
  	<div id="mainContent">
  		<div class="right">
  			<div class="edit" style="margin-top:10px;margin-left:20px;">
  				<form id="editForm" action="${ctx}/hscode/UpdateHscode.action" method="post">
	  				<div>
			           	<label>Description</label>
			           	<input id="CDescription" 
			           			name="entity.CDescription" 
			           			value="${requestScope.entity.CDescription}"
			           			type="text" class="field border" disabled="disabled"/>
			           	<input type="hidden" name="pageNow" value="${pageNow}"/>
			           	<input type="hidden" name="query.CDescription" value="${requestScope.query.CDescription}"/>
			           	<input type="hidden" name="query.CDetails" value="${requestScope.query.CDetails}"/>
			           	<input type="hidden" name="query.CName" value="${requestScope.query.CName}"/>
			           	<input type="hidden" name="query.CHscode" value="${requestScope.query.CHscode}"/>
			        </div>
			        <div>
			        	<label>Details</label>
			           	<input id="CDetails" 
			           			name="entity.CDetails" 
			           			value="${requestScope.entity.CDetails}"
			           			type="text" class="field border" disabled="disabled"/>
			        </div>
			        <div>
			        	<label>柬文品名</label>
			           	<input id="CName" 
			           			name="entity.CName" 
			           			value="${requestScope.entity.CName}"
			           			type="text" class="field border" disabled="disabled"/>
			        </div>
			        <div>
			        	<label>HS Code</label>
			           	<input id="CHscode" 
			           			name="entity.CHscode" 
			           			value="${requestScope.entity.CHscode}"
			           			type="text" class="field border" disabled="disabled"/>
			        </div>
			        <div>
			        	<label>Use/New</label>
			           	<input id="CNew" 
			           			name="entity.CNew" 
			           			value="${requestScope.entity.CNew}"
			           			type="text" class="field border" disabled="disabled"/>
			        </div>
			        <div>
			        	<label>Year</label>
			           	<input id="CYear" 
			           			name="entity.CYear" 
			           			value="${requestScope.entity.CYear}"
			           			type="text" class="field border" disabled="disabled"/>
			        </div>
			        <div>
			        	<label>Rate</label>
			           	<input id="CRate" 
			           			name="entity.CRate" 
			           			value="${requestScope.entity.CRate}"
			           			type="text" class="field border" disabled="disabled"/>
			        </div>
			        <div>
			        	<label>Unit</label>
			           	<input id="CUnit" 
			           			name="entity.CUnit" 
			           			value="${requestScope.entity.CUnit}"
			           			type="text" class="field border" disabled="disabled"/>
			        </div>
			        <div>
			        	<label>Imp.tax</label>
			           	<input id="CImp" 
			           			name="entity.CImp" 
			           			value="${requestScope.entity.CImp}"
			           			type="text" class="field border" disabled="disabled"/>
			        </div>
			        <div>
			        	<label>Sp.tax</label>
			           	<input id="CSp" 
			           			name="entity.CSp" 
			           			value="${requestScope.entity.CSp}"
			           			type="text" class="field border" disabled="disabled"/>
			        </div>
			        <div>
			        	<label>Vat</label>
			           	<input id="CVat" 
			           			name="entity.CVat" 
			           			value="${requestScope.entity.CVat}"
			           			type="text" class="field border" disabled="disabled"/>
			        </div>
			        <div>
			        	<label>Add Tax</label>
			           	<input id="CAdd" 
			           			name="entity.CAdd" 
			           			value="${requestScope.entity.CAdd}"
			           			type="text" class="field border" disabled="disabled"/>
			        </div>
			        <div>
			        	<label>Total Tax</label>
			           	<input id="CTotal" 
			           			name="entity.CTotal" 
			           			value="${requestScope.entity.CTotal}"
			           			type="text" class="field border" disabled="disabled"/>
			        </div>
			        <div>
			        	<label>Ministry</label>
			           	<input id="CMinistry" 
			           			name="entity.CMinistry" 
			           			value="${requestScope.entity.CMinistry}"
			           			type="text" class="field border" disabled="disabled"/>
			        </div>
			        <div>
			        	<label>Form</label>
			           	<input id="CForm" 
			           			name="entity.CForm" 
			           			value="${requestScope.entity.CForm}"
			           			type="text" class="field border" disabled="disabled"/>
			        </div>
			        <div>
			           	<label>备注</label>
			           	<textarea id="remarks" name="entity.CRemarks" rows="5" cols="10" disabled="disabled">${requestScope.entity.CRemarks}</textarea>
			        </div>
			        <div style="margin:10px;">
			           	<input type="button" value="返回" class="return" 
			           			style="width:60px;height:20px;margin-left:200px;" 
			           			onclick="returnList();"/>
			        </div>
		        </form>
	        </div>
        </div>
  	</div>
  </body>
</html>

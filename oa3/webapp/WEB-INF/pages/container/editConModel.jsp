<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>货柜型号修改</title>
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
		
			//设置焦点
			document.getElementById("CCode").focus();
			
		});
		
		//返回
		function returnList(){
			document.getElementById("editForm").action = "${ctx}/container/ConModelList.action";
			document.getElementById("editForm").submit();
		}
		
		//保存之前的数据校验
		function check(){

			var CCode = document.getElementById("CCode").value;
			if ($.trim(CCode) == ""){
				alert("货柜型号不能为空");
				document.getElementById("CCode").focus();
				return false;
			}
			
			return true;
		}
	</script>
  </head>
  <body>
  	<div id="mainContent">
  		<div class="right">
  			<div class="edit">
  				<form id="editForm" action="${ctx}/container/UpdateConModel.action" method="post">
	  				<div>
			           	<label><span style="color:red;">*</span>货柜型号</label>
			           	<input id="CCode" name="conModel.CCode" value="${requestScope.conModel.CCode}"
			           			type="text" class="field border" />
			           	<input id="CId" name="conModel.CId" value="${requestScope.conModel.CId}"
			           			type="hidden" />
			           	<input id="CState" name="conModel.CState" value="${requestScope.conModel.CState}"
			           			type="hidden" />
			           	<input id="CCreateUserid" name="conModel.CCreateUserid" value="${requestScope.conModel.CCreateUserid}"
			           			type="hidden" />
			           	<input type="hidden" name="pageNow" value="${pageNow}"/>
			           	<input type="hidden" name="query.CCode" value="${requestScope.query.CCode}"/>
			        </div>
			        <div>
			           	<label>&nbsp&nbsp备注</label>
			           	<textarea id="remarks" name="conModel.CRemarks" rows="5" cols="10">${requestScope.conModel.CRemarks}</textarea>
			        </div>
			        <div style="margin:10px;">
			           	<input type="submit" value="保存" class="save" style="width:60px;height:20px;margin-left:120px;" onclick="return check();"/>
			           	<input type="button" value="返回" class="return" style="width:60px;height:20px" onclick="returnList();"/>
			        </div>
		        </form>
	        </div>
        </div>
  	</div>
  </body>
</html>

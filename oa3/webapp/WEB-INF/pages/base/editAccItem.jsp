<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>财务科目修改</title>
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
			document.getElementById("CName").focus();
			
		});
		
		//返回
		function returnList(){
			document.getElementById("editForm").action = "${ctx}/acc/AccItemList.action";
			document.getElementById("editForm").submit();
		}
		
		//保存之前的数据校验
		function check(){
		
			var CName = document.getElementById("CName").value;
			if ($.trim(CName) == ""){
				alert("财务科目名称不能为空");
				document.getElementById("CName").focus();
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
  				<form id="editForm" action="${ctx}/acc/SaveAccItem.action" method="post">
			        <div>
			           	<label><span style="color:red;">*</span>财务科目名称</label>
			           	<input id="CName" name="acc.CName" value="${requestScope.acc.CName}"
			           			type="text" class="field border" />
			           	<input id="CId" name="acc.CId" value="${requestScope.acc.CId}" type="hidden" />
						<input id="CCreateUserid" name="acc.CCreateUserid" value="${requestScope.acc.CCreateUserid}" type="hidden" />
			           	<input id="CCreateDate" name="acc.CCreateDate" value="${requestScope.acc.CCreateDate}" type="hidden" />
			           	<input id="CState" name="acc.CState" value="${requestScope.acc.CState}" type="hidden" />
			        </div>
			        <div>
			           	<label>&nbsp&nbsp备注</label>
			           	<textarea id="remarks" name="acc.CRemarks" rows="5" cols="10">${requestScope.acc.CRemarks}</textarea>
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

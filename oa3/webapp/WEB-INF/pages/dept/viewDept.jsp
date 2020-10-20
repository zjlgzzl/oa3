<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>部门查看</title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<link type="text/css" rel="stylesheet"  href="${ctx}/res/css/styles.css"/>
	<script type="text/javascript" src="${ctx}/res/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">
	
		//返回
		function returnList(){
			document.getElementById("editForm").action = "${ctx}/dept/DeptList.action";
			document.getElementById("editForm").submit();
		}
	
	</script>
  </head>
  <body>
  	<div id="mainContent">
  		<div class="right">
  			<div class="edit">
  				<form id="editForm" action="${ctx}/dept/UpdateDept.action" method="post">
	  				<div>
			           	<label>部门编号</label>
			           	<input id="CCode" name="dept.CCode" value="${requestScope.dept.CCode}"
			           			type="text" class="field border" disabled="disabled" />
			           	<input id="CId" name="dept.CId" value="${requestScope.dept.CId}" type="hidden" />
			           	<input id="CCreateUserid" name="dept.CCreateUserid" value="${requestScope.dept.CCreateUserid}" type="hidden" />
			           	<input id="CCreateDate" name="dept.CCreateDate" value="${requestScope.dept.CCreateDate}" type="hidden" />
			           	<input id="CState" name="dept.CState" value="${requestScope.dept.CState}" type="hidden" />
			           	<input name="query.CCode" value="${requestScope.query.CCode}" type="hidden" />
			           	<input name="query.CName" value="${requestScope.query.CName}" type="hidden" />
			           	<input type="hidden" name="pageNow" value="${pageNow}"/>
			        </div>
			        <div>
			           	<label>部门名称</label>
			           	<input id="CCode" name="dept.CName" value="${requestScope.dept.CName}"
			           			type="text" class="field border" disabled="disabled" />
			        </div>
			        <div>
			           	<label>状态</label>
		           		<s:if test="dept.CState == 1">
		           			<input value="正常" class="field border" disabled="disabled" />
		           		</s:if>
		           		<s:else>
		           			<input value="禁用" class="field border" readonly="readonly" />
		           		</s:else>
			        </div>
			        <div>
			           	<label>备注</label>
			           	<textarea id="remarks" name="dept.CRemarks" rows="5" cols="10" disabled="disabled">${requestScope.dept.CRemarks}</textarea>
			        </div>
			        <div style="margin:10px;">
			           	<input type="button" value="返回" class="return" style="width:60px;height:20px;margin-left:280px;" onclick="returnList();"/>
			        </div>
		        </form>
	        </div>
        </div>
  	</div>
  </body>
</html>

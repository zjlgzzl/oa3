<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>添加代理</title>
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
			document.getElementById("editForm").action = "${ctx}/agent/AgentList.action";
			document.getElementById("editForm").submit();
		}
		
		//保存之前的数据校验
		function check(){
			
			var name = document.getElementById("name").value;
			if ($.trim(name) == ""){
				alert("名称不能为空");
				document.getElementById("name").focus();
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
  				<form id="editForm" action="${ctx}/agent/SaveAgent.action" method="post">
	  				<div>
			           	<label><span style="color:red;">*</span>代理</label>
			           	<input id="name" name="entity.name" value="${requestScope.entity.name}"
			           			type="text" class="field border" <c:if test="${flag == 'view' }">disabled</c:if> />
			           	<input type="hidden" name="entity.id" value="${requestScope.entity.id }" />
			           	<input type="hidden" name="entity.createUserid" value="${requestScope.entity.createUserid }" />
			           	<input type="hidden" name="entity.createDate" value="${requestScope.entity.createDate }" />
			           	<input type="hidden" name="pageNow" value="${pageNow}"/>
			           	<input type="hidden" name="query.name" value="${requestScope.query.name}"/>
			        </div>
			        <div style="margin:10px;">
			        	<c:if test="${flag != 'view' }">
			           		<input type="submit" value="保存" class="save" style="width:60px;height:20px;margin-left:120px;" onclick="return check();"/>
			           	</c:if>
			           	<input type="button" value="返回" class="return" style="width:60px;height:20px" onclick="returnList();"/>
			        </div>
		        </form>
	        </div>
        </div>
  	</div>
  </body>
</html>

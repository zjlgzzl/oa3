<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>添加业务员</title>
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
			document.getElementById("editForm").action = "${ctx}/saler/SalerList.action";
			document.getElementById("editForm").submit();
		}
		
		//保存之前的数据校验
		function check(){

			var CName = document.getElementById("CName").value;
			if ($.trim(CName) == ""){
				alert("业务员姓名不能为空");
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
  				<form id="editForm" action="${ctx}/saler/UpdateSaler.action" method="post">
	  				<div>
			           	<label><span style="color:red;">*</span>业务员姓名</label>
			           	<input id="CName" name="saler.CName" value="${requestScope.saler.CName}"
			           			type="text" class="field border" />
			           	<input name="query.CName" value="${requestScope.query.CName}" type="hidden" />
			           	<input name="saler.CId" value="${requestScope.saler.CId}" type="hidden" />
			        </div>
			        <div>
			        	<label>登录用户</label>
			        	<select id = "userId" name="saler.user.CId" class="field border">
			        		<option value="0">PLEASE CHOOSE</option>
	  						<c:forEach items="${userList }" var="user">
	  							<option value="${user.CId }" <c:if test="${user.CId == saler.user.CId }">selected</c:if>>${user.CUsername }</option>
	  						</c:forEach>
	  					</select>
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

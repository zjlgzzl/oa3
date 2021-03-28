<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>修改密码</title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<link type="text/css" rel="stylesheet"  href="${ctx}/res/css/styles.css"/>
	<script type="text/javascript" src="${ctx}/res/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
	   		var errInfo = "${requestScope.errInfo}";
	   		if (errInfo != ""){
	   			alert(errInfo);
	   		}else{
	   			document.getElementById("oldPwd").focus();
	   		}
		});
		//返回
		function returnList(){
			//获取项目名
			document.getElementById("userForm").action = "${ctx}/cons/ConsAction.action";
			document.getElementById("userForm").submit();
		}
		//保存之前的数据校验
		function check(){
			var oldPwd = document.getElementById("oldPwd").value;
			if ($.trim(oldPwd) == ""){
				alert("旧密码不能为空");
				document.getElementById("oldPwd").focus();
				return false;
			}
			var newPwd = document.getElementById("newPwd").value;
			if ($.trim(newPwd) == ""){
				alert("新密码不能为空");
				document.getElementById("newPwd").focus();
				return false;
			}
			var newPwd2 = document.getElementById("newPwd2").value;
			if ($.trim(newPwd2) == ""){
				alert("确认密码不能为空");
				document.getElementById("newPwd2").focus();
				return false;
			}
			if (newPwd != newPwd2){
				alert("两次密码输入不一致");
				return false;
			}
			return true;
		}
	</script>
  </head>
  <body>
  	<div id="mainContent">
  		<div class="right" style="margin-top:50px; margin-left:100px;">
  				<form id="userForm" action="${ctx}/ChangePwd.action" method="post">
	  				<div>
			           	<label><span style="color:red;">*</span>旧密码</label>
			           	<input id="oldPwd" name="oldPwd" value="${requestScope.oldPwd}"
			           			type="password" class="field border" style="margin-left:30px;"/>
			        </div>
			        <div style="margin-top:20px;">
			           	<label><span style="color:red;">*</span>新密码</label>
			           	<input id="newPwd" name="newPwd" value="${requestScope.newPwd}"
			           			type="password" class="field border" style="margin-left:30px;"/>
			        </div>
			        <div style="margin-top:20px;">
			           	<label><span style="color:red;">*</span>确认密码</label>
			           	<input id="newPwd2" name="newPwd2" value="${requestScope.newPwd2}"
			           			type="password" class="field border" style="margin-left:18px;"/>
			        </div>
		           	<div>
		           		<input type="submit" value="确认修改" class="save" onclick="return check();" 
		           				style="margin-left:180px; width:70px; height:25px;"/>
		           	</div>
		        </form>
	        </div>
        </div>
  </body>
</html>

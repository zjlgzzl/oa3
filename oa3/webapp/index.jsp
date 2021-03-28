<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<s:action name="CookieAction_getCookie" namespace="/util"></s:action>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>系统登录</title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<link type="text/css" rel="stylesheet"  href="${ctx}/res/css/styles.css"/>
	<script type="text/javascript" src="${ctx}/res/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/res/js/login.js"></script>
	<script type="text/javascript">
		/*初始化*/
		$(window).load(function(){
				
			//生成校验码
			$("#validCodeImg").click();
			
			//后台校验错误信息
			var errInfo = "${requestScope.errInfo}";
			if (errInfo != null && errInfo.length > 0){
				alert(errInfo);
			}
			
			//从cookie得到用户名
			var cookName = "${requestScope.cookName}";
			var reqName = "${requestScope.userName}";
			if(reqName!=""){
				document.getElementById("userName").value = reqName;
				document.getElementById("passWord").focus();
				return;
			}
			if(cookName!=""){
				document.getElementById("userName").value = cookName;
				document.getElementById("passWord").focus();
				return;
			}
		
			//初始化设置焦点
			setfocus();
		});
		
		if (window != top){
			top.location.href = location.href; 	
		}
	</script>
  </head>
  <body style="background-color:#ECEFF8;">
  	<div id="loginMain">
  		<form id="loginForm" action="${ctx}/LoginAction.action" method="post">
	  		<input id="userName" name="userName" class="field border" type="text" />
	  		<input id="passWord" name="passWord" class="field border" type="password"/>
	  		<input id="validCode" name="validCode" class="field border" type="text" maxlength="4" onkeyup="checkValidCode();" onblur="checkValidCode();"/>
			<img id ="validCodeImg" alt="点击获取验证码" onclick="getValidCode()"/>
			<div id="y" class="y hidden"></div>
			<div id="n" class="n hidden"></div>
			<div class="clear"></div>
			<!--  
			<a href="${ctx}/Regist.action" id="forget" style="color:blue;">新用户注册</a>
			-->
			<div id="forget"></div>
			<div class="clear"></div>
			<input id="btnSubmit" value="" type="submit" onclick="return check();"/>
			<input id="btnReset" value="" type="reset" />
		</form>
		<!--  
		<div style="color:blue; margin:50px auto; width:225px;">请使用IE8及以上版本浏览器或火狐浏览器</div>
  		-->
  	</div>
  </body>
</html>
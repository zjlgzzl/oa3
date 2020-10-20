<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title></title>
    <script type="text/javascript">
    	//后台提示
		$(document).ready(function(){
			/*
			var notice = "${sessionScope.fun505}";
			if (notice == 1){
				getNotice();
			}*/
		});
		function getNotice(){
			/*
			$.ajax({
				type:"Post",
				url:"${ctx}/finance/GetNotice.action",
				success:function(count){
					if (count > 0){
						$("#notice").attr("class", "");
					}else{
						$("#notice").attr("class", "hidden");
					}
				}
			});
			setTimeout("getNotice()",1000*60);*/
		}
    </script>
  </head>
  <body>
	<div id="headTitle">
		<label>办公综合管理系统V1.0</label>
	</div>
	<div id="headInfo">
		欢迎你！${sessionScope.userName} 
		|
  		<a title="修改密码" href="javascript:void(0)" onclick="changePwd();" style="background-color:#E6F0FF;text-decoration:none;">修改密码</a>
  		|
  		<a title="退出系统" href="${ctx}/ExitAction.action" style="background-color:#E6F0FF;text-decoration:none;">退出系统</a>
	</div>
	<div id="notice" class="hidden" style="margin-top:15px; float:right; margin-right:220px;">
		<label style="color:red;font-weight:bold;">
			有临时支出未处理！请到【财务支出登记】中查看。
		</label>
	</div>
  </body>
</html>

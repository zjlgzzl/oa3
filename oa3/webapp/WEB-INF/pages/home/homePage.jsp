<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<title>综合办公管理系统</title>
  	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
	<script type="text/javascript" src="${ctx}/res/js/jquery-1.9.1.min.js"></script>
	<link type="text/css" rel="stylesheet"  href="${ctx}/res/css/styles.css"/>
	<style> 
		.div-btn{ margin:0 auto;width:150px;height:100px;line-height:100px;
				  text-align:center;vertical-align:middle;border-radius:30px;
				  font-size:16px;font-family:微软雅黑;font-weight:bold;float:left;margin-left:20px;
				  filter:alpha(Opacity=20);-moz-opacity:0.5;opacity: 0.5;z-index:100; background-color:#ffffff;
				  cursor:pointer;} 
	</style>
	<script type="text/javascript">
		function query(flag){
			if(flag==314){
				if (parent.$("#tabs").tabs("exists", '开票提醒')){
					parent.$("#tabs").tabs("select", '开票提醒');
				}else{
					parent.$("#tabs").tabs('add',{
						title:'开票提醒',
						closable:true,
						content:'<iframe id="314" style="width:100%;height:100%;" scrolling="auto" frameborder="0" src="' + 
									 '${ctx}/busin/InvNoticeReport.action"' + 
								'</iframe>'
					});
				}
			}else if(flag==316){
				if (parent.$("#tabs").tabs("exists", '成本组变更提醒')){
					parent.$("#tabs").tabs("select", '成本组变更提醒');
				}else{
					parent.$("#tabs").tabs('add',{
						title:'成本组变更提醒',
						closable:true,
						content:'<iframe id="316" style="width:100%;height:100%;" scrolling="auto" frameborder="0" src="' + 
									 '${ctx}/busin/InvNoticeReport.action"' + 
								'</iframe>'
					});
				}
			}else if(flag==318){
				if (parent.$("#tabs").tabs("exists", '问题提醒')){
					parent.$("#tabs").tabs("select", '问题提醒');
				}else{
					parent.$("#tabs").tabs('add',{
						title:'问题提醒',
						closable:true,
						content:'<iframe id="318" style="width:100%;height:100%;" scrolling="auto" frameborder="0" src="' + 
									 '${ctx}/busin/ProblemNoticeReport.action"' + 
								'</iframe>'
					});
				}
			}else if(flag==629){
				if (parent.$("#tabs").tabs("exists", '财务收款提醒')){
					parent.$("#tabs").tabs("select", '财务收款提醒');
				}else{
					parent.$("#tabs").tabs('add',{
						title:'财务收款提醒',
						closable:true,
						content:'<iframe id="629" style="width:100%;height:100%;" scrolling="auto" frameborder="0" src="' + 
									 '${ctx}/report/JiezhuanReport.action"' + 
								'</iframe>'
					});
				}
			}
		}
	</script>
  </head>
   
  <body style="background-color:#ECEFF8;background:url(../res/img/background.jpg) no-repeat;">
  	<form id="editForm" method="post">
  	</form>
  	<div style="margin:150px auto;width:800px;">
  		<s:if test="#session.fun314 == 1">
  			<div class="div-btn" onclick="query(314);">
	  			开票提醒
	  		</div>
  		</s:if>
  		<s:if test="#session.fun316 == 1">
  			<div class="div-btn" onclick="query(316);">
	  			成本组变更提醒
	  		</div>
  		</s:if>
  		<s:if test="#session.fun318 == 1">
  			<div class="div-btn" onclick="query(318);">
  				问题提醒
  			</div>
  		</s:if>
  		<s:if test="#session.fun629 == 1">
  			<div class="div-btn" onclick="query(629);">
	  			财务收款提醒
	  		</div>
  		</s:if>
  	</div>
  </body>
  
</html>

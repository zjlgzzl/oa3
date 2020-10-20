<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>客户资料添加</title>
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
			document.getElementById("editForm").action = "${ctx}/cus/CusList.action";
			document.getElementById("editForm").submit();
		}
		
		//保存之前的数据校验
		function check(){
		
			var CName = document.getElementById("CName").value;
			if ($.trim(CName) == ""){
				alert("客户名称不能为空");
				document.getElementById("CName").focus();
				return false;
			}
			
			return true;
		}
	</script>
	<style>
		label{
			margin-top : 13px;
		}
	</style>
  </head>
  <body>
  	<div id="mainContent">
  		<div class="right">
  			<div class="edit">
	  			<form id="editForm" action="${ctx}/cus/SaveCus.action" method="post">
	  				<div>
	  					<label>Client Khmer name</label>
	  					<input name="cus.COther1" value="${requestScope.cus.COther1}" type="text" class="field border" style="width:60px;"/>
			           	<input id="CName" name="cus.CName" value="${requestScope.cus.CName}"
			           			type="text" class="field border" />
			           	<span style="color:red;margin-left:5px;">*</span>
			        </div>
			         <div style="padding-left:86px;">
			         	<label>Client English name</label>
			           	<input id="CAddr" name="cus.CAddr" value="${requestScope.cus.CAddr}"
			           			type="text" class="field border" />
			        </div>
			        <div style="padding-left:86px;">
			        	<label>Client address</label>
			           	<input id="CPhone" name="cus.CPhone" value="${requestScope.cus.CPhone}"
			           			type="text" class="field border" />
			        </div>
			        <div style="padding-left:86px;">
			        	<label>Client address</label>
			           <input name="cus.COther4" value="${requestScope.cus.COther4}"
			           			type="text" class="field border" />
			        </div>
			        <div style="padding-left:86px;">
			        	<label>Client address</label>
			           <input name="cus.COther5" value="${requestScope.cus.COther5}"
			           			type="text" class="field border" />
			        </div>
			        <div style="padding-left:86px;">
			        	<label>Client Contact No</label>
			           <input name="cus.COther6" value="${requestScope.cus.COther6}"
			           			type="text" class="field border" />
			        </div>
			        <div style="padding-left:86px;">
			        	<label>Client Contact No</label>
			           <input name="cus.COther7" value="${requestScope.cus.COther7}"
			           			type="text" class="field border" />
			        </div>
			        <div style="padding-left:86px;">
			        	<label>Sales name</label>
			        	<select name="cus.salerId">
			        	<option value="">PLEASE CHOOSE</option>
			        		<c:forEach items="${salerList }" var="saler">
			        			<option value="${saler.CId }">${saler.CName }</option>
			        		</c:forEach>
			        	</select>
			        </div>
			        <div style="padding-left:86px;">
			        	<label>VAT No.</label>
			           <input name="cus.COther8" value="${requestScope.cus.COther8}"
			           			type="text" class="field border" />
			        </div>
			         <div style="margin:10px;">
			           	<input type="submit" value="保存" class="save" style="width:60px;height:20px;margin-left:120px;" onclick="return check();"/>
			           	<input type="button" value="返回" class="return" style="width:60px;height:20px" onclick="returnList();"/>
			           	<input type="hidden" name="query.CName" value="${requestScope.query.CName}"/>
			           	<input type="hidden" name="pageNow" value="${pageNow}"/>
			        </div>
			    </form>
			</div>
        </div>
  	</div>
  </body>
</html>

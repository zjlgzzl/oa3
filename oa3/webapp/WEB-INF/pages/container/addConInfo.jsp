<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>货柜编号添加</title>
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
			
			//型号
			var TContainerModel = "${requestScope.conInfo.TContainerModel.CId}";
			if (TContainerModel != null && TContainerModel > 0){
				var select = document.getElementById("TContainerModel");
				for(var i=0;i<select.options.length;i++){
					if(select.options[i].value == TContainerModel){
						select.options[i].selected = true;
						break;
					}
				}
			}
		
			//设置焦点
			document.getElementById("CCode").focus();
			
		});
		
		//返回
		function returnList(){
			document.getElementById("CPrice").value = 0;
			document.getElementById("editForm").action = "${ctx}/container/ConInfoList.action";
			document.getElementById("editForm").submit();
		}
		
		//保存之前的数据校验
		function check(){
			
			//货柜编号
			var CCode = document.getElementById("CCode").value;
			if ($.trim(CCode) == ""){
				alert("货柜编号不能为空");
				document.getElementById("CCode").focus();
				return false;
			}
			
			//货柜型号
			var TContainerModel = document.getElementById("TContainerModel").value;
			if (TContainerModel == -1){
				alert("货柜型号不能为空");
				document.getElementById("TContainerModel").focus();
				return false;
			}
			
			//成本价格
			var CPrice = document.getElementById("CPrice").value;
			if ($.trim(CPrice) == ""){
				alert("成本价格不能为空");
				document.getElementById("CPrice").focus();
				return false;
			}
			if (isNaN(CPrice)){
				alert("成本价格必须是数字");
				document.getElementById("CPrice").focus();
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
  				<form id="editForm" action="${ctx}/container/SaveConInfo.action" method="post">
	  				<div>
			           	<label><span style="color:red;">*</span>货柜编号</label>
			           	<input id="CCode" name="conInfo.CCode" value="${requestScope.conInfo.CCode}"
			           			type="text" class="field border" />
			           	<input type="hidden" name="pageNow" value="${pageNow}"/>
			           	<input type="hidden" name="query.CCode" value="${requestScope.query.CCode}"/>
			        </div>
			        <div>
			           	<label><span style="color:red;">*</span>货柜型号</label>
			           	<select id="TContainerModel" name="conInfo.TContainerModel.CId" class="border" style="width:200px;">
			           		<option value="-1">--请选择--</option>
							<c:forEach var="conModelList" items="${requestScope.conModelList}">
								<option value="${conModelList.CId}">${conModelList.CCode}</option>
							</c:forEach>
						</select>
			        </div>
			        <div>
			           	<label><span style="color:red;">*</span>成本价格</label>
			           	<input id="CPrice" name="conInfo.CPrice" value="${requestScope.conInfo.CPrice}"
			           			type="text" class="field border" />
			        </div>
			        <div>
			           	<label>&nbsp&nbsp备注</label>
			           	<textarea id="remarks" name="conInfo.CRemarks" rows="5" cols="10">${requestScope.conInfo.CRemarks}</textarea>
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

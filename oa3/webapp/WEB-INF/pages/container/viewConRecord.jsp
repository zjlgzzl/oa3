<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>货柜进出登记</title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<link type="text/css" rel="stylesheet"  href="${ctx}/res/css/styles.css"/>
	<script type="text/javascript" src="${ctx}/res/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/res/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">

		//后台提示
		$(document).ready(function(){
			
			//后台校验错误信息
			var errInfo = "${requestScope.errInfo}";
			if (errInfo != null && errInfo.length > 0){
				alert(errInfo);
			}
			
			//货柜编号
			var infoCCode = "${requestScope.record.TContainerInfo.CId}";
			if (infoCCode != null && infoCCode != -1){
				var select = document.getElementById("infoCCode");
				for(var i=0; i<select.options.length; i++){
					if(select.options[i].value == infoCCode){
						document.getElementById("infoCCode").options[i].selected = true;
						document.getElementById("modelCode").options[i].selected = true;
						break;
					}
				}
			}
			
			//操作类型
			var COpertype = "${requestScope.record.COpertype}";
			if (COpertype != null && COpertype != -1){
				var select = document.getElementById("COpertype");
				for(var i=0; i<select.options.length; i++){
					if(select.options[i].value == COpertype){
						document.getElementById("COpertype").options[i].selected = true;
						break;
					}
				}
			}
			if (COpertype == 3){
				$("#moneyDiv").attr("class", "hidden");
			}
			
		});
		
		//返回
		function returnList(){
			var cstate = document.getElementById("cstate").value;
			document.getElementById("CMoney").value = 0;
			if (cstate == 1){
				document.getElementById("editForm").action = "${ctx}/container/ConRecordList.action";
			}else{
				document.getElementById("editForm").action = "${ctx}/container/ConRecordList2.action";
			}
			document.getElementById("editForm").submit();
		}
		
		function selectCode(){
			var infoCCode = document.getElementById("infoCCode").value;
			if (infoCCode > 0){
				var select = document.getElementById("infoCCode");
				for(var i=0; i<select.options.length; i++){
					if(select.options[i].value == infoCCode){
						document.getElementById("modelCode").options[i].selected = true;
						break;
					}
				}
			}else{
				document.getElementById("modelCode").value = "-1";
			}
		}
		
		function selectType(){
			var type = document.getElementById("COpertype").value;
			if (type==3){
				//收回
				document.getElementById("CMoney").value = "";
				$("#moneyDiv").attr("class", "hidden");
			}else{
				//出租、出售
				$("#moneyDiv").attr("class", "");
			}
		}
		
		//保存之前的数据校验
		function check(){
		
			//日期
			var cdate = document.getElementById("cdate").value;
			if ($.trim(cdate) == ""){
				alert("日期不能为空");
				document.getElementById("cdate").focus();
				return false;
			}
			
			//客户名称
			var CCusname = document.getElementById("CCusname").value;
			if ($.trim(CCusname) == ""){
				alert("客户名称不能为空");
				document.getElementById("CCusname").focus();
				return false;
			}
			
			//货柜编号
			var infoCCode = document.getElementById("infoCCode").value;
			if ($.trim(infoCCode) == -1){
				alert("货柜编号不能为空");
				document.getElementById("infoCCode").focus();
				return false;
			}
			
			//操作类型
			var type = document.getElementById("COpertype").value;
			if (type == -1){
				alert("操作类型不能为空");
				document.getElementById("COpertype").focus();
				return false;
			}
			
			//金额
			if (type != 3){
				var CMoney = document.getElementById("CMoney").value;
				if ($.trim(CMoney) == ""){
					alert("金额不能为空");
					document.getElementById("CMoney").focus();
					return false;
				}
				if (isNaN(CMoney)){
					alert("金额必须是数字");
					document.getElementById("CMoney").focus();
					return false;
				}
			} 
			
			return true;
		}
		
		/*打印*/
		function printRecord(){
			document.getElementById("cdate").disabled = false;
			document.getElementById("CCusname").disabled = false;
			document.getElementById("infoCCode").disabled = false;
			document.getElementById("COpertype").disabled = false;
			document.getElementById("CMoney").disabled = false;
			document.getElementById("remarks").disabled = false;
			document.getElementById("remarks2").disabled = false;
			document.getElementById("CTo").disabled = false;
			document.getElementById("editForm").action = "${ctx}/container/PrintRecord.action";
			document.getElementById("editForm").submit();
		}
		
	</script>
  </head>
  <body>
  	<div id="mainContent">
  		<div class="right">
  			<div class="edit">
  				<form id="editForm" action="${ctx}/container/SaveConRecord.action" method="post">
  					<div>
						<label>日期</label>
						<input id="cdate" name="cdate" value="${requestScope.cdate}"
								type="text" class="field border" style="width:200px" disabled="disabled"/>
						<input id="CId" name="record.CId" value="${requestScope.record.CId}"
								type="hidden"/>
						<input id="TUser" name="record.TUser.CId" value="${requestScope.record.TUser.CId}"
								type="hidden"/>
						<input id="cstate" name="record.CState" value="${requestScope.record.CState}"
								type="hidden"/>
						<input type="hidden" id="pageNow" name="pageNow" value="${pageNow}"/>
						<input type="hidden" name="startDate" value="${requestScope.startDate}"/>
						<input type="hidden" name="endDate" value="${requestScope.endDate}"/>
						<input type="hidden" name="query.cusname" value="${requestScope.query.cusname}"/>
						<input type="hidden" name="query.infoCode" value="${requestScope.query.infoCode}"/>
					</div>
					<div>
			           	<label>客户名称</label>
			           	<input id="CCusname" name="record.CCusname" value="${requestScope.record.CCusname}"
			           			type="text" class="field border" disabled="disabled"/>
			        </div>
	  				<div>
			           	<label>货柜编号</label>
			           	<select id="infoCCode" name="record.TContainerInfo.CId" class="border" style="width:200px;"
			           			disabled="disabled">
			           		<option value="-1">--请选择--</option>
							<c:forEach var="conInfoList" items="${requestScope.conInfoList}">
								<option value="${conInfoList.infoId}">${conInfoList.infoCode}</option>
							</c:forEach>
						</select>
			        </div>
			        <div>
			           	<label>货柜型号</label>
			           	<select id="modelCode" class="border" style="width:200px;" disabled="disabled">
			           		<option value="-1"></option>
							<c:forEach var="conInfoList" items="${requestScope.conInfoList}">
								<option value="0">${conInfoList.modelCode}</option>
							</c:forEach>
						</select>
			        </div>
			        <div>
			           	<label>操作类型</label>
			           	<select id="COpertype" name="record.COpertype" class="border" style="width:200px;"
			           			disabled="disabled">
			           		<option value="-1">--请选择--</option>
							<option value="1">RENTAL</option>
							<option value="2">SALING</option>
							<option value="3">GET BACK</option>
						</select>
			        </div>
			        <div id="moneyDiv">
			           	<label>金额</label>
			           	<input id="CMoney" name="record.CMoney" value="${requestScope.record.CMoney}"
			           			type="text" class="field border" disabled="disabled"/>
			        </div>
			        <div>
			           	<label>Where</label>
			           	<input id="CTo" name="record.CTo" value="${requestScope.record.CTo}"
			           			type="text" class="field border" disabled="disabled"/>
			        </div>
			        <div>
			           	<label>备注</label>
			           	<textarea id="remarks2" name="record.CRemarks2" rows="5" cols="10" disabled="disabled">${requestScope.record.CRemarks2}</textarea>
			        </div>
			        <div>
			           	<label>NOTE</label>
			           	<textarea id="remarks" name="record.CRemarks" rows="5" cols="10" disabled="disabled">${requestScope.record.CRemarks}</textarea>
			        </div>
			        <div style="margin:10px;">
			           	<input type="submit" value="打印" class="save" style="width:60px;height:20px;margin-left:120px;" onclick="printRecord();"/>
			           	<input type="button" value="返回" class="return" style="width:60px;height:20px" onclick="returnList();"/>
			        </div>
		        </form>
	        </div>
        </div>
  	</div>
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>服务单修改</title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<link type="text/css" rel="stylesheet"  href="${ctx}/res/css/styles.css"/>
	<script type='text/javascript' src="${ctx}/dwr/interface/serviceItemService.js"></script>
  	<script type='text/javascript' src="${ctx}/dwr/engine.js"></script>
 	<script type='text/javascript' src="${ctx}/dwr/util.js"></script>
	<script type="text/javascript" src="${ctx}/res/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/res/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/res/js/busin.js"></script>
	<script type="text/javascript">
	
		var conCount = 0;
		var serviceCount = 0;
		var typeItemCount = 0;
		var costCount = 0;
		var cashCount = -1;
		
		var typeId = null;
		var typeName = null;
		var costItemId = null;
		var costItemName = null;
		var empId = null;
		var empName = null;
		var fun202 = null;
		var fun203 = null;
		var fun205 = null;
		var fun207 = null;
		var fun208 = null;
		var costStr = null;

		//后台提示
		$(document).ready(function(){
		
			javascript:dwr.engine.setAsync(false);//设置dwr同步
			
			fun202 = "${sessionScope.fun202}";
			//alert(fun202);
			fun203 = "${sessionScope.fun203}";
			fun205 = "${sessionScope.fun205}";
			fun207 = "${sessionScope.fun207}";
			fun208 = "${sessionScope.fun208}";
			
			//货柜数量
			var count1 = "${requestScope.conCount}";
			if (Number(count1) > 0){
				conCount = Number(count1) - 1;//标记从0开始
			}
			
			//成本项目
			costItemId = "${requestScope.costItemId}";
			costItemId = costItemId.substring(1, costItemId.length - 1).split(", ");
			costItemName = "${requestScope.costItemName}";
			costItemName = costItemName.substring(1, costItemName.length - 1).split(", ");
			//costStr = "${costStr}";
			
			//领款人
			empId = "${requestScope.empId}";
			empId = empId.substring(1, empId.length - 1).split(", ");
			empName = "${requestScope.empName}";
			empName = empName.substring(1, empName.length - 1).split(", ");
			
			//成本数量
			var count3 = "${requestScope.costCount}";
			if (Number(count3) > 0){
				costCount = Number(count3) - 1;//标记从0开始
			}
			//alert("costnum:" + count3);
			
			//回款数量
			var count4 = "${requestScope.cashCount}";
			if (Number(count4) > 0){
				cashCount = Number(count4) - 1;//标记从0开始
			}

			//客户名称
			var TCustomer = "${requestScope.busin.TCustomer.CId}";
			if (TCustomer != null && TCustomer != ""){
				var select = document.getElementById("TCustomer");
				for(var i=0;i<select.options.length;i++){
					if(select.options[i].value == TCustomer){
						select.options[i].selected = true;
						break;
					}
				}
			}
			if (fun202 == 1 || count3 > 0){
				//成本信息
				for (var i=0; i<=costCount; i++){
	
					//成本项目
					var costItem = document.getElementById("costItem"+i).value;
					if (costItem != null && costItem != ""){
						var select = document.getElementById("TCostItem"+i);
						for(var j=0;j<select.options.length;j++){
							if(select.options[j].value == costItem){
								select.options[j].selected = true;
								break;
							}
						}
					}
					
					//领款人
					var user = document.getElementById("user"+i).value;
					if (user != null && user != ""){
						var select = document.getElementById("TUser"+i);
						for(var j=0;j<select.options.length;j++){
							if(select.options[j].value == user){
								select.options[j].selected = true;
								break;
							}
						}
					}
				}
			}
			
			//单据类型
			var TBusinType = "${requestScope.busin.TBusinType.CId}";
			if (TBusinType != null && TBusinType != ""){
				var select = document.getElementById("TBusinType");
				for(var j=0;j<select.options.length;j++){
					if(select.options[j].value == TBusinType){
						select.options[j].selected = true;
						break;
					}
				}
			}
			
			//后台校验错误信息
			var errInfo = "${requestScope.errInfo}";
			if (errInfo != null && errInfo.length > 0){
				alert(errInfo);
			}
		
			//设置焦点
			//document.getElementById("CDate").focus();
			
			javascript:dwr.engine.setAsync(true);//设置dwr异步
			
		});
		
		//返回
		function returnList(){
			document.getElementById("editForm").action = "${ctx}/busin/BusinList.action";
			document.getElementById("editForm").submit();
		}
		
		//保存之前的数据校验
		function check(){
		
			//服务单日期
			var CDate = document.getElementById("CDate").value;
			if ($.trim(CDate) == ""){
				alert("ISSUE DATE不能为空");
				document.getElementById("CDate").focus();
				return false;
			}
			
			//单据类型
			var TBusinType = document.getElementById("TBusinType").value;
			if ($.trim(TBusinType) == "0"){
				alert("TYPE OF FILE不能为空");
				document.getElementById("TBusinType").focus();
				return false;
			}
			
			//收货人
			/*
			var CReceive = document.getElementById("CReceive").value;
			if ($.trim(CReceive) == ""){
				alert("收货人不能为空");
				document.getElementById("CReceive").focus();
				return false;
			}*/
			
			//客户名称
			var TCustomer = document.getElementById("TCustomer").value;
			if (TCustomer == "0"){
				alert("CLIENT不能为空");
				document.getElementById("TCustomer").focus();
				return false;
			}
			
			//回款人
			var j = Number(costCount) + 1;
			for (var i=0; i<=j; i++){
				/*
				var TUser = document.getElementById("TUser"+i);
				if (TUser == null){
					continue;
				}
				var TCostItem = document.getElementById("TCostItem"+i).value;
				var CMoney = document.getElementById("CMoney"+i).value;
				if (TCostItem == 0 && $.trim(CMoney) == ""){
					continue;
				}
				TUser = document.getElementById("TUser"+i).value;
				if (TUser == 0){
					alert("请选择RECEIVER");
					document.getElementById("TUser"+i).focus();
					return false;
				}*/
			}
			
			//处理成本信息
			j = Number(costCount) + 1;
			for (var i=0; i<=j; i++){
				if (document.getElementById("TCostItem"+i) != null){
					document.getElementById("TCostItem"+i).disabled = false;
					document.getElementById("TUser"+i).disabled = false;
				}
			}
			
			document.getElementById("CBillNo").disabled = false;
			document.getElementById("remarks").disabled = false;
			document.getElementById("remarks3").disabled = false;
			document.getElementById("businState").value = 1;
			
			return true;
		}
		
		//处理成本项目和领款人
		function getCostSelect(count){
			var TCostItem = document.getElementById("TCostItem"+count);
			/*var myobj=eval(costStr);  
			for (var i=0; i<myobj.length; i++){
				TCostItem.options.add(new Option(myobj[i].name, myobj[i].id));
			}*/
			for (var i=0; i<costItemId.length; i++){
				TCostItem.options.add(new Option(costItemName[i], costItemId[i])); 
			}
			var TUser = document.getElementById("TUser"+count);
			for (var i=0; i<empId.length; i++){
				TUser.options.add(new Option(empName[i], empId[i])); 
			}
		}
		
		//打印领款单
		function printCost(){
			for(var i=0; i<=costCount; i++){
				document.getElementById("TCostItem"+i).disabled = false;
				document.getElementById("CMoney"+i).disabled = false;
				document.getElementById("TUser"+i).disabled = false;
				document.getElementById("CRemarks"+i).disabled = false;
			}
			document.getElementById("editForm").action = "${ctx}/busin/PrintCost.action";
			document.getElementById("editForm").submit();
		}
	
	</script>
  </head>
  <body>
  	<div id="mainContent">
  		<div class="right">
			<form id="editForm" action="${ctx}/busin/UpdateBusin.action" method="post">
				<div style="margin-left:5px; width:1000px; text-align:center; margin-top:50px;">
					<span style="color:black; font-size:14px; font-weight:bold;">
							FILE
					</span>
				</div>
				<table class="table" style="margin-top:25px; width:1025px; margin-left:5px; color:black; border-bottom:0px;">
					<tr style="border-bottom:0px;">
						<td colspan="8" style="text-align:left;border-top:0px;border-bottom:0px;" >
							<span style="color:blue; font-weight:bold; padding-left:20px;">BASE INFO</span>
						</td>
					</tr>
				</table>
				<table id="mainTable" class="table" style="margin-top:0px; width:1000px; margin-left:5px; color:black;">
					<tr>
						<td style="width:100px;">
							<label><span style="color:red;">*</span>ISSUE DATE</label>
						</td>
						<td style="width:150px;">
							<s:if test="#session.userID == busin.TUser.CId">
								<input id="CDate" name="cdate" value="${requestScope.cdate}"
										type="text" class="field border" style="width:146px" onclick="WdatePicker()" readonly="readonly"/>
							</s:if>
							<s:else>
								<input value="${requestScope.cdate}" type="text" 
										class="" style="width:146px;border:0px;" disabled="disabled"/>
								<input id="CDate" name="cdate" value="${requestScope.cdate}" type="hidden"/>
							</s:else>
							<!-- edit -->
							<input id="CId" name="busin.CId" value="${requestScope.busin.CId}"
									type="hidden" class="field border" style="width:146px"/>
							<input id="businState" name="busin.CState" value="${requestScope.busin.CState}" type="hidden"/>
							<input id="TUser" name="busin.TUser.CId" value="${requestScope.busin.TUser.CId}" type="hidden" />
							<input id="CCreateDate" name="busin.CCreateDate" value="${requestScope.busin.CCreateDate}" type="hidden" />
							<input id="CBusinAuditFlg" name="busin.CBusinAuditFlg" value="${requestScope.busin.CBusinAuditFlg}" type="hidden" />
							<input id="CFinanceAuditFlg" name="busin.CFinanceAuditFlg" value="${requestScope.busin.CFinanceAuditFlg}" type="hidden" />
							<input id="CCostPrintFlag" name="busin.CCostPrintFlag" value="${requestScope.busin.CCostPrintFlag}" type="hidden" />
						</td>
						<td style="width:100px;">
							<label>TYPE OF FILE</label>
						</td>
						<td style="width:150px;">
							<select id="TBusinType" class="" style="width:146px;border:0px;" disabled="disabled">
				           		<option value="0">PLEASE CHOOSE</option>
								<c:forEach var="businTypeList" items="${requestScope.businTypeList}">
									<option value="${businTypeList.CId}">${businTypeList.CName}</option>
								</c:forEach>
							</select>
							<input type="hidden" name="busin.TBusinType.CId" value="${busin.TBusinType.CId}" />
						</td>
						<td style="width:100px;">
							<label>FILE NO</label>
						</td>
						<td style="width:150px;">
							<!-- edit -->
							<input type="text" id="CBillNo" value="${requestScope.busin.CBillNo}" 
									class="" style="width:146px;border:0px;" disabled="disabled"/>
							<input type="hidden" name="busin.CBillNo" value="${requestScope.busin.CBillNo}" />
						</td>
						<td style="width:100px;">
							<label>SHIPPER</label>
						</td>
						<td style="width:150px;">
							<s:if test="#session.userID == busin.TUser.CId">
								<input type="text" id="CSend" name="busin.CSend" value="${requestScope.busin.CSend}" 
										class="field border" style="width:146px;" readonly="readonly"/>
							</s:if>
							<s:else>
								<input type="text" id="CSend" value="${requestScope.busin.CSend}" 
										class="" style="width:146px; border:0px;" disabled="disabled"/>
								<input type="hidden" id="CSend" name="busin.CSend" value="${requestScope.busin.CSend}" 
										class="field border" style="width:146px;"/>
							</s:else>
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							<label>CONSIGNEE</label>
						</td>
						<td style="width:150px;">
							<s:if test="#session.userID == busin.TUser.CId">
								<input type="text" id="CReceive" name="busin.CReceive" value="${requestScope.busin.CReceive}" 
										class="field border" style="width:146px;"/>
							</s:if>
							<s:else>
								<input type="text" value="${requestScope.busin.CReceive}" 
										class="" style="width:146px; border:0px;" disabled="disabled" />
								<input type="hidden" id="CReceive" name="busin.CReceive" value="${requestScope.busin.CReceive}" 
										class="field border" style="width:146px;"/>
							</s:else>
						</td>
						<td style="width:100px;">
							<label><span style="color:red;">*</span>CLIENT</label>
						</td>
						<td style="width:150px;">
							<s:if test="#session.userID == busin.TUser.CId">
								<select id="TCustomer" name="busin.TCustomer.CId" class="border" style="width:146px;">
					           		<option value="0">PLEASE CHOOSE</option>
									<c:forEach var="cusList" items="${requestScope.cusList}">
										<option value="${cusList.CId}">${cusList.CName}</option>
									</c:forEach>
								</select>
							</s:if>
							<s:else>
								<select id="TCustomer" class="" style="width:146px; border:0px;" disabled="disabled">
					           		<option value="0">PLEASE CHOOSE</option>
									<c:forEach var="cusList" items="${requestScope.cusList}">
										<option value="${cusList.CId}">${cusList.CName}</option>
									</c:forEach>
								</select>
								<input type="hidden" name="busin.TCustomer.CId" value="${requestScope.busin.TCustomer.CId}" />
							</s:else>
						</td>
						<td style="width:100px;">
							<label>BILL NO</label>
						</td>
						<td style="width:150px;">
							<s:if test="#session.userID == busin.TUser.CId">
								<input type="text" id="CTakeNo" name="busin.CTakeNo" value="${requestScope.busin.CTakeNo}" 
										class="field border" style="width:146px;"/>
							</s:if>
							<s:else>
								<input type="text" id="CTakeNo" value="${requestScope.busin.CTakeNo}" 
										class="" style="width:146px; border:0px;" disabled="disabled" />
								<input type="hidden" name="busin.CTakeNo" value="${requestScope.busin.CTakeNo}" 
										class="field border" style="width:146px;"/>
							</s:else>
						</td>
						<td style="width:100px;">
							<label>COMMODITIES</label>
						</td>
						<td style="width:150px;">
							<s:if test="#session.userID == busin.TUser.CId">
								<input type="text" id="CGoodsType" name="busin.CGoodsType" value="${requestScope.busin.CGoodsType}" 
										class="field border" style="width:146px;"/>
							</s:if>
							<s:else>
								<input type="text" id="CGoodsType" value="${requestScope.busin.CGoodsType}" 
									class="" style="width:146px;border:0px;" disabled="disabled" />
								<input type="hidden" name="busin.CGoodsType" value="${requestScope.busin.CGoodsType}" 
									class="field border" style="width:146px;"/>
							</s:else>
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							<label>APPLY DATE</label>
						</td>
						<td style="width:150px;">
							<s:if test="#session.userID == busin.TUser.CId">
								<input id="appDate" name="appDate" value="${requestScope.appDate}"
									type="text" class="field border" style="width:146px" onclick="WdatePicker()" readonly="readonly"/>
							</s:if>
							<s:else>
								<input id="appDate" value="${requestScope.appDate}"
									type="text" class="" style="width:146px;border:0px;" disabled="disabled"/>
								<input name="appDate" value="${requestScope.appDate}" type="hidden"/>
							</s:else>
						</td>
						<td style="width:100px;">
							<label>SHIPPING LINE</label>
						</td>
						<td style="width:150px;">
							<s:if test="#session.userID == busin.TUser.CId">
								<input type="text" id="shippingLine" name="busin.shippingLine" value="${requestScope.busin.shippingLine}" 
										class="field border" style="width:146px;"/>
							</s:if>
							<s:else>
								<input type="text" id="shippingLine" value="${requestScope.busin.shippingLine}" 
										class="" style="width:146px;border:0px;" disabled="disabled"/>
								<input name="busin.shippingLine" value="${requestScope.busin.shippingLine}" type="hidden"/>
							</s:else>
						</td>
						<td style="width:100px;">
							<label>CLEARANCE DATE</label>
						</td>
						<td style="width:150px;">
							<s:if test="#session.userID == busin.TUser.CId">
								<input id="clearDate" name="clearDate" value="${requestScope.clearDate}"
									type="text" class="field border" style="width:146px" onclick="WdatePicker()" readonly="readonly"/>
							</s:if>
							<s:else>
								<input id="clearDate" value="${requestScope.clearDate}"
									type="text" class="" style="width:146px;border:0px;" disabled="disabled"/>
								<input name="clearDate" value="${requestScope.clearDate}" type="hidden"/>
							</s:else>
						</td>
						<td style="width:100px;">
							<label>RETURN EMPTY DATE</label>
						</td>
						<td style="width:150px;">
							<s:if test="#session.userID == busin.TUser.CId">
								<input id="CContainerDate" name="CContainerDate" value="${requestScope.CContainerDate}"
									type="text" class="field border" style="width:146px" onclick="WdatePicker()" readonly="readonly"/>							
							</s:if>
							<s:else>
								<input id="CContainerDate" value="${requestScope.CContainerDate}"
									type="text" class="" style="width:146px;border:0px;" disabled="disabled"/>
								<input name="CContainerDate" value="${requestScope.CContainerDate}" type="hidden"/>
							</s:else>
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							<label>PoL/PoD</label>
						</td>
						<td style="width:150px;">
							<s:if test="#session.userID == busin.TUser.CId">
								<input type="text" id="CArrivalPort" name="busin.CArrivalPort" value="${requestScope.busin.CArrivalPort}" 
										class="field border" style="width:146px;"/>
							</s:if>
							<s:else>
								<input type="text" id="CArrivalPort" value="${requestScope.busin.CArrivalPort}" 
									class="" style="width:146px;border:0px;" disabled="disabled" />
								<input type="hidden" name="busin.CArrivalPort" value="${requestScope.busin.CArrivalPort}" 
									class="field border" style="width:146px;"/>
							</s:else>
						</td>
						<td style="width:100px;">
							<label>ETD/ETA</label>
						</td>
						<td style="width:150px;">
							<s:if test="#session.userID == busin.TUser.CId">
								<input id="CArrivalDate" name="CArrivalDate" value="${requestScope.CArrivalDate}"
									type="text" class="field border" style="width:146px" onclick="WdatePicker()" readonly="readonly"/>
							</s:if>
							<s:else>
								<input id="CArrivalDate" value="${requestScope.CArrivalDate}"
									type="text" class="" style="width:146px;border:0px;" disabled="disabled"/>
								<input id="CArrivalDate" name="CArrivalDate" value="${requestScope.CArrivalDate}"
									type="hidden" class="field border" style="width:146px" onclick="WdatePicker()" readonly="readonly"/>
							</s:else>
						</td>
						<td style="width:100px;">
							<label>DELIVERY DATE</label>
						</td>
						<td style="width:150px;">
							<s:if test="#session.userID == busin.TUser.CId">
								<input id="CDeliveryDate" name="CDeliveryDate" value="${requestScope.CDeliveryDate}"
										type="text" class="field border" style="width:146px" onclick="WdatePicker()" readonly="readonly"/>
							</s:if>
							<s:else>
								<input id="CDeliveryDate" value="${requestScope.CDeliveryDate}"
										type="text" class="" style="width:146px;border:0px;" disabled="disabled"/>
								<input id="CDeliveryDate" name="CDeliveryDate" value="${requestScope.CDeliveryDate}"
										type="hidden" class="field border" style="width:146px" onclick="WdatePicker()" readonly="readonly"/>
							</s:else>
						</td>
						<td style="width:100px;">
							<label>UNLOAD DATE</label>
						</td>
						<td style="width:150px;">
							<s:if test="#session.userID == busin.TUser.CId">
								<input id="CCompleteDate" name="CCompleteDate" value="${requestScope.CCompleteDate}"
										type="text" class="field border" style="width:146px" onclick="WdatePicker()" readonly="readonly"/>
							</s:if>
							<s:else>
								<input id="CCompleteDate" value="${requestScope.CCompleteDate}"
										type="text" class="" style="width:146px;border:0px;" disabled="disabled"/>
								<input id="CCompleteDate" name="CCompleteDate" value="${requestScope.CCompleteDate}"
										type="hidden" class="field border" style="width:146px" onclick="WdatePicker()" readonly="readonly"/>
							</s:else>
						</td>
					</tr>
					<tr>
						<td colspan="8" style="text-align:left;" >
							<span style="color:blue; font-weight:bold; padding-left:20px;">CONTAINER INFO</span>
							<input type="button" value="ADD" onclick="addContainer(0);" style="margin-left:10px; width:60px; height:20px;"/>
						</td>
					</tr>
				</table>
				<table id="conTable" class="table" style="margin-top:0px; width:1000px; margin-left:5px; color:black; border-top:0px;">
					<tbody id="con">
						<tr id="conTR0" style="border-top:0px;">
							<td style="width:100px; border-top:0px;">
								<label>CONTAINER NO</label>
							</td>
							<td style="width:100px; border-top:0px;">
								<s:if test="#session.userID == busin.TUser.CId">
									<input type="text" id="conNum0" name="cons[0].CContainerNum" 
											value="${requestScope.cons[0].CContainerNum}"
											class="field border" style="width:96px;"/>
								</s:if>
								<s:else>
									<input type="text" id="conNum0"
											value="${requestScope.cons[0].CContainerNum}"
											class="" style="width:96px;border:0px;" disabled="disabled"/>
									<input type="hidden" name="cons[0].CContainerNum" 
											value="${requestScope.cons[0].CContainerNum}"
											class="field border"/>
								</s:else>
								<!-- edit -->
								<input type="hidden" name="cons[0].CId" value="${cons[0].CId}" />
								<input type="hidden" name="cons[0].TBusin.CId" value="${cons[0].TBusin.CId}" />
							</td>
							<td style="width:40px; border-top:0px;">
								<label>TYPE</label>
							</td>
							<td style="width:80px; border-top:0px;">
								<s:if test="#session.userID == busin.TUser.CId">
									<input type="text" id="conType0" name="cons[0].CContainerType" 
											value="${requestScope.cons[0].CContainerType}"
											class="field border" style="width:76px;"/>
								</s:if>
								<s:else>
									<input type="text" id="conType0"
											value="${requestScope.cons[0].CContainerType}"
											class="" style="width:76px;border:0px;" disabled="disabled"/>
									<input type="hidden" name="cons[0].CContainerType" 
											value="${requestScope.cons[0].CContainerType}"
											class="field border"/>
								</s:else>
							</td>
							<td style="width:38px; border-top:0px;">
								<label>PKGS</label>
							</td>
							<td style="width:60px; border-top:0px;">
								<s:if test="#session.userID == busin.TUser.CId">
									<input type="text" id="CCount0" 
											name="cons[0].CCount" 
											value="${requestScope.cons[0].CCount}"
											class="field border" style="width:56px;"/>
								</s:if>
								<s:else>
									<input type="text" id="CCount0"  
											value="${requestScope.cons[0].CCount}"
											class="" style="width:56px;border:0px;" disabled="disabled"/>
									<input type="hidden"
											name="cons[0].CCount" 
											value="${requestScope.cons[0].CCount}"/>
								</s:else>
							</td>
							<td style="width:58px; border-top:0px;">
								<label>WEIGHT</label>
							</td>
							<td style="width:60px; border-top:0px;">
								<s:if test="#session.userID == busin.TUser.CId">
									<input type="text" id="CWeight0" 
											name="cons[0].CWeight" 
											value="${requestScope.cons[0].CWeight}"
											class="field border" style="width:56px;"/>
								</s:if>
								<s:else>
									<input type="text" id="CWeight0" 
											value="${requestScope.cons[0].CWeight}"
											class="" style="width:56px;border:0px;" disabled="disabled"/>
									<input type="hidden" id="CWeight0" 
											name="cons[0].CWeight" 
											value="${requestScope.cons[0].CWeight}"/>
								</s:else>
							</td>
							<td style="width:30px; border-top:0px;">
								<label>REM</label>
							</td>
							<td style="width:60px; border-top:0px;">
								<s:if test="#session.fun203 == 1">
									<input type="text" id="CRemarks0" name="cons[0].CRemarks" 
										value="${requestScope.cons[0].CRemarks}"
										class="field border" style="width:56px;"/>
								</s:if>
								<s:else>
									<input type="hidden" name="cons[0].CRemarks" 
										value="${requestScope.cons[0].CRemarks}"/>
								</s:else>
							</td>
							<td style="width:30px; border-top:0px;">
								<label>NT</label>
							</td>
							<td style="width:60px; border-top:0px;">
								<s:if test="#session.fun207 == 1">
									<input type="text" name="cons[0].CRemarks2" 
										value="${requestScope.cons[0].CRemarks2}"
										class="field border" style="width:56px;"/>
								</s:if>
								<s:else>
									<input type="hidden" name="cons[0].CRemarks2" 
										value="${requestScope.cons[0].CRemarks2}"/>
								</s:else>
							</td>
							<td style="width:60px; border-top:0px;">
								<label>TRUCKER</label>
							</td>
							<td style="width:125px; border-top:0px;">
							<s:if test="#session.userID == busin.TUser.CId">
								<input type="text" id="CTrucker0" 
										name="cons[0].CTrucker" 
										value="${requestScope.cons[0].CTrucker}"
										class="field border" style="width:121px;"/>
							</s:if>
							<s:else>
								<input type="text" id="CTrucker0" 
										value="${requestScope.cons[0].CTrucker}"
										class="field border" style="width:121px;" disabled="disabled"/>
								<input type="hidden"  
										name="cons[0].CTrucker" 
										value="${requestScope.cons[0].CTrucker}"
										/>
							</s:else>
							</td>
							<td style="width:77px; border-top:0px;">
								<s:if test="#session.userID == busin.TUser.CId">
									<!--  
									<input type="button" value="DELETE" onclick="delContainer(this);" style="float:left; margin-left:8px; width:60px; height:20px;"/>
									-->
								</s:if>
							</td>
						</tr>
						<s:iterator value="#request.cons" var="cons" status="num">
							<s:if test="#num.index > 0">
								<tr id="conTR<s:property value="#num.index" />">
									<td style="width:100px; border-top:0px;">
									</td>
									<td style="width:100px; border-top:0px;">
										<s:if test="#session.userID == busin.TUser.CId">
											<input type="text" id="conNum<s:property value="#num.index" />" 
													name="cons[<s:property value="#num.index" />].CContainerNum" 
													value="${requestScope.cons[num.index].CContainerNum}"
													class="field border" style="width:96px;"/>
										</s:if>
										<s:else>
											<input type="text" id="conNum<s:property value="#num.index" />" 
												value="${requestScope.cons[num.index].CContainerNum}"
												class="" style="width:96px;border:0px;" disabled="disabled"/>
											<input type="hidden"
												name="cons[<s:property value="#num.index" />].CContainerNum" 
												value="${requestScope.cons[num.index].CContainerNum}"
												class="field border" style="width:96px;"/>
										</s:else>
										<!-- edit -->
										<input type="hidden" name="cons[<s:property value="#num.index" />].CId" value="${cons[num.index].CId}" />
										<input type="hidden" name="cons[<s:property value="#num.index" />].TBusin.CId" value="${cons[num.index].TBusin.CId}" />
									</td>
									<td style="width:40px; border-top:0px;">
									</td>
									<td style="width:80px; border-top:0px;">
										<s:if test="#session.userID == busin.TUser.CId">
											<input type="text" id="conType<s:property value="#num.index" />" 
												name="cons[<s:property value="#num.index" />].CContainerType" 
												value="${requestScope.cons[num.index].CContainerType}"
												class="field border" style="width:76px;"/>
										</s:if>
										<s:else>
											<input type="text" id="conType<s:property value="#num.index" />" 
												value="${requestScope.cons[num.index].CContainerType}"
												class="" style="width:76px;border:0px;" disabled="disabled"/>
											<input type="hidden"
												name="cons[<s:property value="#num.index" />].CContainerType" 
												value="${requestScope.cons[num.index].CContainerType}"
												class="field border"/>
										</s:else>
										
									</td>
									<td style="width:38px; border-top:0px;">
									</td>
									<td style="width:60px; border-top:0px;">
										<s:if test="#session.userID == busin.TUser.CId">
											<input type="text" id="CCount<s:property value="#num.index" />" 
													name="cons[<s:property value="#num.index" />].CCount" 
													value="${requestScope.cons[num.index].CCount}"
													class="field border" style="width:56px;"/>
										</s:if>
										<s:else>
											<input type="text" id="CCount<s:property value="#num.index" />" 
													value="${requestScope.cons[num.index].CCount}"
													class="" style="width:56px;border:0px;" disabled="disabled"/>
											<input type="hidden" id="CCount<s:property value="#num.index" />" 
													name="cons[<s:property value="#num.index" />].CCount" 
													value="${requestScope.cons[num.index].CCount}"/>
										</s:else>
									</td>
									<td style="width:58px; border-top:0px;">
									</td>
									<td style="width:60px; border-top:0px;">
										<s:if test="#session.userID == busin.TUser.CId">
											<input type="text" id="CWeight<s:property value="#num.index" />" 
													name="cons[<s:property value="#num.index" />].CWeight" 
													value="${requestScope.cons[num.index].CWeight}"
													class="field border" style="width:56px;"/>
										</s:if>
										<s:else>
											<input type="text" id="CWeight<s:property value="#num.index" />" 
													value="${requestScope.cons[num.index].CWeight}"
													class="" style="width:56px;border:0px;" disabled="disabled"/>
											<input type="hidden" id="CWeight<s:property value="#num.index" />" 
													name="cons[<s:property value="#num.index" />].CWeight" 
													value="${requestScope.cons[num.index].CWeight}"/>
										</s:else>
									</td>
									<td style="width:30px; border-top:0px;">
									</td>
									<td style="width:60px; border-top:0px;">
										<s:if test="#session.fun203 == 1">
											<input type="text" id="CRemarks<s:property value="#num.index" />" 
													name="cons[<s:property value="#num.index" />].CRemarks" 
													value="${requestScope.cons[num.index].CRemarks}"
													class="field border" style="width:56px;"/>
										</s:if>
										<s:else>
											<input type="hidden"
													name="cons[<s:property value="#num.index" />].CRemarks" 
													value="${requestScope.cons[num.index].CRemarks}"/>
											
										</s:else>
									</td>
									<td style="width:30px; border-top:0px;">
									</td>
									<td style="width:60px; border-top:0px;">
										<s:if test="#session.fun207 == 1">
											<input type="text"
													name="cons[<s:property value="#num.index" />].CRemarks2" 
													value="${requestScope.cons[num.index].CRemarks2}"
													class="field border" style="width:56px;"/>
										</s:if>
										<s:else>
											<input type="hidden"
													name="cons[<s:property value="#num.index" />].CRemarks2" 
													value="${requestScope.cons[num.index].CRemarks2}"/>
										</s:else>
									</td>
									<td style="width:60px; border-top:0px;">
									</td>
									<td style="width:125px; border-top:0px;">
										<s:if test="#session.userID == busin.TUser.CId">
											<input type="text" id="CTrucker<s:property value="#num.index" />" 
												name="cons[<s:property value="#num.index" />].CTrucker" 
												value="${requestScope.cons[num.index].CTrucker}"
												class="field border" style="width:121px;"/>
										</s:if>
										<s:else>
											<input type="text" id="CTrucker<s:property value="#num.index" />" 
												value="${requestScope.cons[num.index].CTrucker}"
												class="field border" style="width:121px;"/>
											<input type="text" 
												name="cons[<s:property value="#num.index" />].CTrucker" 
												value="${requestScope.cons[num.index].CTrucker}"
												class="field border"/>
										</s:else>
									</td>
									<td style="width:157px; border-top:0px;">
										<s:if test="#session.userID == busin.TUser.CId">
											<input type="button" value="DELETE" onclick="delContainer(this);" style="float:left; margin-left:8px; width:60px; height:20px;"/>
										</s:if>
									</td>
								</tr>
							</s:if>
						</s:iterator>
					</tbody>
				</table>
				
				<table class="table" style="margin-top:0px; width:1025px; margin-left:5px; color:black; border-top:0px; border-bottom:0px;">
					<tr>
						<td colspan="8" style="text-align:left;border-top:0px;border-bottom:0px;" >
							<span style="color:blue; font-weight:bold; padding-left:20px;">COST INFO</span>
						</td>
					</tr>
				</table>
				<table id="costTable" class="table" style="margin-top:0px; width:1000px; margin-left:5px; color:black;">
					<tr id="costTR0">
						<td style="width:100px;">
							<label>TYPE OF COST</label>
						</td>
						<td style="width:150px;">
							<s:if test="(#session.fun205 == 1) || 
										(#session.userID == costs[#cnum.index].TUserByCCreateUserid.CId &&
										costs[#cnum.index].CState == 1 && #session.fun202 == 1 && 
										costs[#cnum.index].CPrint == 0)">
								<select id="TCostItem0" 
										name="costs[0].TCostItem.CId"
										class="border" style="width:146px;">
									<option value="0">PLEASE CHOOSE</option>
									<c:forEach var="costItemList" items="${requestScope.costItemList}">
										<option value="${costItemList.CId}">${costItemList.CName}</option>
									</c:forEach>
								</select>
							</s:if>
							<s:else>
								<select id="TCostItem0" 
										name="costs[0].TCostItem.CId"
										class="" style="width:146px;border:0px;" disabled="disabled">
									<option value="0">PLEASE CHOOSE</option>
									<c:forEach var="costItemList" items="${requestScope.costItemList}">
										<option value="${costItemList.CId}">${costItemList.CName}</option>
									</c:forEach>
								</select>
							</s:else>
							<input type="hidden" id="costItem0" value="${requestScope.costs[0].TCostItem.CId}" />
							<!-- edit -->
							<input type="hidden" name="costs[0].CId" value="${requestScope.costs[0].CId}" />
							<input type="hidden" name="costs[0].TBusin.CId" value="${requestScope.costs[0].TBusin.CId}" />
							<input type="hidden" name="costs[0].CState" value="${requestScope.costs[0].CState}" />
							<input type="hidden" name="costs[0].TUserByCCreateUserid.CId" 
									value="${requestScope.costs[0].TUserByCCreateUserid.CId}" />
							<input type="hidden" name="costs[0].CPrint" value="${requestScope.costs[0].CPrint}" />
						</td>
						<td style="width:60px;">
							<label>AMOUNT</label>
						</td>
						<td style="width:80px;">
							<s:if test="(#session.fun205 == 1) || 
										(#session.userID == costs[#cnum.index].TUserByCCreateUserid.CId &&
										costs[#cnum.index].CState == 1 && #session.fun202 == 1 && 
										costs[#cnum.index].CPrint == 0)">
								<input type="text" id="CMoney0" 
										name="costs[0].CMoney"
										value="${requestScope.costs[0].CMoney}"
										class="field border" style="width:76px;"/>
							</s:if>
							<s:else>
								<input type="text" id="CMoney0" 
										value="${requestScope.costs[0].CMoney}"
										class="" style="width:76px;border:0px;" disabled="disabled"/>
								<input type="hidden"
										name="costs[0].CMoney" 
										value="${requestScope.costs[0].CMoney}"
										class="field border" style="width:76px;"/>
							</s:else>
						</td>
						<td style="width:70px;">
							<label><span style="color:red;">*</span>RECEIVER</label>
						</td>
						<td style="width:120px;">
							<s:if test="(#session.fun205 == 1) || 
										(#session.userID == costs[#cnum.index].TUserByCCreateUserid.CId &&
										costs[#cnum.index].CState == 1 && #session.fun202 == 1 && 
										costs[#cnum.index].CPrint == 0)">
								<select id="TUser0" 
										name="costs[0].TUserByCUserid.CId"
										class="border" style="width:116px;">
									<option value="0">PLEASE CHOOSE</option>
									<c:forEach var="userList" items="${requestScope.userList}">
										<option value="${userList.userid}">${userList.empName}</option>
									</c:forEach>
								</select>
							</s:if>
							<s:else>
								<select id="TUser0" 
										name="costs[0].TUserByCUserid.CId"
										class="" style="width:116px;border:0px" disabled="disabled">
									<option value="0">PLEASE CHOOSE</option>
									<c:forEach var="userList" items="${requestScope.userList}">
										<option value="${userList.userid}">${userList.empName}</option>
									</c:forEach>
								</select>
							</s:else>
							<input type="hidden" id="user0" value="${requestScope.costs[0].TUserByCUserid.CId}" />
						</td>
						<td style="width:30px;">
							<label>REM</label>
						</td>
						<td style="width:97px;">
							<s:if test="#session.fun205 == 1">
								<input type="text" id="CRemarks0"
									name="costs[0].CRemarks"
									value="${requestScope.costs[0].CRemarks}"
									class="field border" style="width:93px;"/>
							</s:if>
							<s:else>
								<input type="hidden"
									name="costs[0].CRemarks" 
									value="${requestScope.costs[0].CRemarks}"
									class="field border" style="width:93px;"/>
							</s:else>
						</td>
						<td style="width:30px;">
							<label>NT</label>
						</td>
						<td style="width:97px;">
							<s:if test="#session.fun208 == 1">
								<input type="text"
										name="costs[0].CRemarks2"
										value="${requestScope.costs[0].CRemarks2}"
										class="field border" style="width:93px;"/>
							</s:if>
							<s:else>
								<input type="hidden"
									name="costs[0].CRemarks2" 
									value="${requestScope.costs[0].CRemarks2}"
									class="field border" style="width:93px;"/>
							</s:else>
						</td>
						<td style="width:157px;">
							<s:if test="#session.fun202 == 1">
								<input type="button" value="ADD" onclick="addCost(0);" style="float:left; margin-left:13px; width:60px; height:20px;"/>
							</s:if>
						</td>
					</tr>
					<tbody id="cost">
						<s:iterator value="#request.costs" var="costs" status="cnum">
							<s:if test="#cnum.index > 0">
								<tr id="costTR<s:property value="#cnum.index" />">
									<td style="width:100px;">
									</td>
									<td style="width:150px;">
										<s:if test="(#session.fun205 == 1) || 
													(#session.userID == costs[#cnum.index].TUserByCCreateUserid.CId &&
													costs[#cnum.index].CState == 1 && #session.fun202 == 1 && 
													costs[#cnum.index].CPrint == 0)">
											<select id="TCostItem<s:property value="#cnum.index" />" 
													name="costs[<s:property value="#cnum.index" />].TCostItem.CId"
													class="border" style="width:146px;">
												<option value="0">PLEASE CHOOSE</option>
												<c:forEach var="costItemList" items="${requestScope.costItemList}">
													<option value="${costItemList.CId}">${costItemList.CName}</option>
												</c:forEach>
											</select>
										</s:if>
										<s:else>
											<select id="TCostItem<s:property value="#cnum.index" />" 
													name="costs[<s:property value="#cnum.index" />].TCostItem.CId"
													class="" style="width:146px;border:0px;" disabled="disabled">
												<option value="0">PLEASE CHOOSE</option>
												<c:forEach var="costItemList" items="${requestScope.costItemList}">
													<option value="${costItemList.CId}">${costItemList.CName}</option>
												</c:forEach>
											</select>
										</s:else>
										<input type="hidden" id="costItem<s:property value="#cnum.index" />" 
											   value="${costs[cnum.index].TCostItem.CId}" />
										<!-- edit -->
										<input type="hidden" name="costs[<s:property value="#cnum.index" />].CId" value="${costs[cnum.index].CId}" />
										<input type="hidden" name="costs[<s:property value="#cnum.index" />].TBusin.CId" value="${costs[cnum.index].TBusin.CId}" />
										<input type="hidden" name="costs[<s:property value="#cnum.index" />].CState" value="${costs[cnum.index].CState}" />
										<input type="hidden" name="costs[<s:property value="#cnum.index" />].TUserByCCreateUserid.CId" 
												value="${costs[cnum.index].TUserByCCreateUserid.CId}" />
										<input type="hidden" name="costs[<s:property value="#cnum.index" />].CPrint" value="${costs[cnum.index].CPrint}" />
									</td>
									<td style="width:60px;">
									</td>
									<td style="width:80px;">
										<s:if test="(#session.fun205 == 1) || 
													(#session.userID == costs[#cnum.index].TUserByCCreateUserid.CId &&
													costs[#cnum.index].CState == 1 && #session.fun202 == 1 &&
													costs[#cnum.index].CPrint == 0)">
											<input type="text" id="CMoney<s:property value="#cnum.index" />" 
													name="costs[<s:property value="#cnum.index" />].CMoney" 
													value="${requestScope.costs[cnum.index].CMoney}"
													class="field border" style="width:76px;"/>
										</s:if>
										<s:else>
											<input type="text" id="CMoney<s:property value="#cnum.index" />" 
													value="${requestScope.costs[cnum.index].CMoney}"
													class="" style="width:76px;border:0px;" disabled="disabled"/>
											<input type="hidden"
												name="costs[<s:property value="#cnum.index" />].CMoney" 
												value="${requestScope.costs[cnum.index].CMoney}"
												class="field border" style="width:76px;"/>
										</s:else>
									</td>
									<td style="width:70x;">
									</td>
									<td style="width:120px;">
										<s:if test="(#session.fun205 == 1) || 
													(#session.userID == costs[#cnum.index].TUserByCCreateUserid.CId &&
													costs[#cnum.index].CState == 1 && #session.fun202 == 1 && 
													costs[#cnum.index].CPrint == 0)">
											<select id="TUser<s:property value="#cnum.index" />" 
													name="costs[<s:property value="#cnum.index" />].TUserByCUserid.CId"
													class="border" style="width:116px;">
												<option value="0">PLEASE CHOOSE</option>
												<c:forEach var="userList" items="${requestScope.userList}">
													<option value="${userList.userid}">${userList.empName}</option>
												</c:forEach>
											</select>
										</s:if>
										<s:else>
											<select id="TUser<s:property value="#cnum.index" />" 
													name="costs[<s:property value="#cnum.index" />].TUserByCUserid.CId"
													class="" style="width:116px;border:0px;" disabled="disabled">
												<option value="0">PLEASE CHOOSE</option>
												<c:forEach var="userList" items="${requestScope.userList}">
													<option value="${userList.userid}">${userList.empName}</option>
												</c:forEach>
											</select>
										</s:else>
										<input type="hidden" id="user<s:property value="#cnum.index" />" value="${requestScope.costs[cnum.index].TUserByCUserid.CId}" />
									</td>
									<td style="width:30px;">
									</td>
									<td style="width:97px;">
										<s:if test="#session.fun205 == 1">
											<input type="text" id="CRemarks0"
												name="costs[<s:property value="#cnum.index" />].CRemarks"
												value="${requestScope.costs[cnum.index].CRemarks}"
												class="field border" style="width:93px;"/>
										</s:if>
										<s:else>
											<input type="hidden"
												name="costs[<s:property value="#cnum.index" />].CRemarks" 
												value="${requestScope.costs[cnum.index].CRemarks}"
												class="field border" style="width:93px;"/>
										</s:else>
									</td>
									<td style="width:30px;">
									</td>
									<td style="width:97px;">
										<s:if test="#session.fun208 == 1">
											<input type="text"
													name="costs[<s:property value="#cnum.index" />].CRemarks2"
													value="${requestScope.costs[cnum.index].CRemarks2}"
													class="field border" style="width:93px;"/>
										</s:if>
										<s:else>
											<input type="hidden"
												name="costs[<s:property value="#cnum.index" />].CRemarks2" 
												value="${requestScope.costs[cnum.index].CRemarks2}"
												class="field border" style="width:93px;"/>
										</s:else>
									</td>
									<td style="width:157px;">
										<s:if test="#session.fun202 == 1">
											<input type="button" value="ADD" onclick="addCost(<s:property value="#cnum.index" />);" style="float:left; margin-left:13px; width:60px; height:20px;"/>
											<s:if test="#session.userID == costs[#cnum.index].TUserByCCreateUserid.CId &&
														costs[#cnum.index].CState == 1 && costs[#cnum.index].CPrint == 0">
												<input type="button" value="DELETE" onclick="delCost(this);" style="float:left; margin-left:10px; width:60px; height:20px;"/>
											</s:if>
										</s:if>
									</td>
								</tr>
							</s:if>
						</s:iterator>
					</tbody>
				</table>
				
				<table id="remars" class="table" style="margin-top:10px; width:1000px; margin-left:5px; color:black;border-top:0px;">
					<tr>
						<td style="width:100px;">
							REMARKS
						</td>
						<td style="width:918px; height:40px;">
							<textarea id="remarks" name="busin.CRemarks" rows="2" cols="10" style="width:914px;">${requestScope.busin.CRemarks}</textarea>
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							REMARKS
						</td>
						<td style="width:918px; height:40px;">
							<textarea id="remarks3" name="busin.CRemarks3" rows="2" cols="10" style="width:914px; border:0px;" disabled="disabled">${requestScope.busin.CRemarks3}</textarea>
						</td>
					</tr>
				</table>
				
				<table>
					<tr>
						<td colspan="8" style="padding-top:20px; padding-bottom:20px;">
							<input type="submit" value="SAVE" style="float:left; width:75px;height:25px;margin-left:380px;" onclick="return check();"/>
							<!--  
							<input type="button" value="打印领款单" style="float:left; width:75px;height:25px;margin-left:20px;" onclick="printCost();"/>
				           	-->
				           	<input type="button" value="RETURN" style="float:left; width:75px;height:25px;margin-left:20px;" onclick="returnList();"/>
						</td>
					</tr>
				</table>
	        </form>
        </div>
  	</div>
  </body>
</html>

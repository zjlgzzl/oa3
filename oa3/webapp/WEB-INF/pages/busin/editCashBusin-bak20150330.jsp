<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>服务单查看</title>
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
		var rateCount = -1;
		
		var typeId = null;
		var typeName = null;
		var costItemId = null;
		var costItemName = null;
		var cashItemId = null;
		var cashItemName = null;
		var empId = null;
		var empName = null;
		var rateId = null;
		var rateName = null;
		
		var fun303 = null;

		//后台提示
		$(document).ready(function(){
		
			javascript:dwr.engine.setAsync(false);//设置dwr同步
			
			fun303 = "${sessionScope.fun303}";
			
			//货柜数量
			var count1 = "${requestScope.conCount}";
			if (Number(count1) > 0){
				conCount = Number(count1) - 1;//标记从0开始
			}
			
			//服务项目数量
			var count2 = "${requestScope.serviceCount}";
			if (Number(count2) > 0){
				serviceCount = Number(count2) - 1;//标记从0开始
			}
			
			//成本项目
			costItemId = "${requestScope.costItemId}";
			costItemId = costItemId.substring(1, costItemId.length - 1).split(", ");
			costItemName = "${requestScope.costItemName}";
			costItemName = costItemName.substring(1, costItemName.length - 1).split(", ");
			
			//回款项目
			cashItemId = "${requestScope.cashItemId}";
			cashItemId = cashItemId.substring(1, cashItemId.length - 1).split(", ");
			cashItemName = "${requestScope.cashItemName}";
			cashItemName = cashItemName.substring(1, cashItemName.length - 1).split(", ");
			
			//税率
			/*
			rateId = "${requestScope.rateId}";
			rateId = rateId.substring(1, rateId.length - 1).split(", ");
			rateName = "${requestScope.rateName}";
			rateName = rateName.substring(1, rateName.length - 1).split(", ");*/
			
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
			
			//回款数量
			var count4 = "${requestScope.cashCount}";
			if (Number(count4) > 0){
				cashCount = Number(count4) - 1;//标记从0开始
			}
			
			//税率数量
			/*
			var count5 = "${requestScope.rateCount}";
			if (Number(count5) > 0){
				rateCount = Number(count5) - 1;//标记从0开始
			}*/

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
			
			
			//成本信息
			for (var i=0; i<=costCount; i++){

				//成本项目
				var costItem = document.getElementById("costItem"+i).value;
				alert("aaaaa" + costItem);
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
			
			//回款信息
			for (var i=0; i<=cashCount; i++){
			
				//回款项目
				var cashItem = document.getElementById("cashItem"+i).value;
				if (cashItem != null && cashItem != ""){
					var select = document.getElementById("TCashItem"+i);
					for(var j=0;j<select.options.length;j++){
						if(select.options[j].value == cashItem){
							select.options[j].selected = true;
							break;
						}
					}
				}
				
				//进度
				var schedule = document.getElementById("schedule"+i).value;
				if (schedule == 0){
					document.getElementById("no"+i).checked = true;
				}else{
					document.getElementById("yes"+i).checked = true;
				}
			}
			
			//税率信息
			/*
			for (var i=0; i<=rateCount; i++){
			
				//回款项目
				var rateItem = document.getElementById("rateItem"+i).value;
				if (rateItem != null && rateItem != ""){
					var select = document.getElementById("TRateItem"+i);
					for(var j=0;j<select.options.length;j++){
						if(select.options[j].value == rateItem){
							select.options[j].selected = true;
							break;
						}
					}
				}
				
				//进度
				var schedule = document.getElementById("schedule2"+i).value;
				if (schedule == 0){
					document.getElementById("rateno"+i).checked = true;
				}else{
					document.getElementById("rateyes"+i).checked = true;
				}
				
				//税率
				var rate = document.getElementById("rate"+i).value;
				if (rate != null && rate != ""){
					var select = document.getElementById("Rate"+i);
					for(var j=0;j<select.options.length;j++){
						if(select.options[j].value == rate){
							select.options[j].selected = true;
							break;
						}
					}
				}
			}*/
			
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
			document.getElementById("editForm").action = "${ctx}/busin/FinanceCashList.action";
			document.getElementById("editForm").submit();
		}
		
		//处理成本项目和领款人
		function getCostSelect(count){
			var TCostItem = document.getElementById("TCostItem"+count);
			for (var i=0; i<costItemId.length; i++){
				TCostItem.options.add(new Option(costItemName[i], costItemId[i])); 
			}
			var TUser = document.getElementById("TUser"+count);
			for (var i=0; i<empId.length; i++){
				TUser.options.add(new Option(empName[i], empId[i])); 
			}
		}
		
		//处理回款项目和回款人
		function getCashSelect(count){
			var TCashItem = document.getElementById("TCashItem"+count);
			//alert(cashItemId.length);
			for (var i=0; i<cashItemId.length; i++){
				TCashItem.options.add(new Option(cashItemName[i], cashItemId[i])); 
			}
			/*
			var TUser = document.getElementById("TUser2"+count);
			for (var i=0; i<empId.length; i++){
				TUser.options.add(new Option(empName[i], empId[i])); 
			}*/
		}
		
		//处理回款项目和回款人
		function getRateSelect(count){
			var TRateItem = document.getElementById("TRateItem"+count);
			for (var i=0; i<cashItemId.length; i++){
				TRateItem.options.add(new Option(cashItemName[i], cashItemId[i])); 
			}
			var Rate = document.getElementById("Rate"+count);
			for (var i=0; i<rateId.length; i++){
				Rate.options.add(new Option(rateName[i], rateId[i])); 
			}
		}
		
		//保存
		function save(){
		
			//检查回款人
			j = Number(cashCount) + 1;
			for (var i=0; i<j; i++){
				
				//回款项目
				var TCashItem = document.getElementById("TCashItem"+i);
				if (TCashItem == null){
					continue;
				}
				/*
				var cashitem = document.getElementById("TCashItem"+i).value;
				if (cashitem == 0){
					continue;
				}*/
				
				//回款人
				/*
				var TUser = document.getElementById("TUser2"+i).value;
				if ($.trim(TUser) == "0"){
					alert("回款人不能为空");
					document.getElementById("TUser2"+i).focus();
					return false;
				}*/
			}
		
			document.getElementById("editForm").action = "${ctx}/busin/SaveCashBusin.action";
			document.getElementById("editForm").submit();
		}
		
		//打印回款单
		function printCash(){
			for(var i=0; i<=cashCount; i++){
				document.getElementById("TCashItem"+i).disabled = false;
				document.getElementById("CMoney2"+i).disabled = false;
				document.getElementById("TUser2"+i).disabled = false;
			}
			document.getElementById("editForm").action = "${ctx}/busin/PrintCash.action";
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
							<label>ISSUE DATE</label>
						</td>
						<td style="width:150px;">
							<input id="CDate" name="cdate" value="${requestScope.cdate}"
									type="text" class="" style="width:146px;border:0px;" disabled="disabled"/>
							<!-- edit -->
							<input id="businId" name="businId" value="${requestScope.busin.CId}"
									type="hidden" class="field border" style="width:146px"/>
							<input id="businState" name="busin.CState" value="${requestScope.busin.CState}" type="hidden"/>
							<input id="TUser" name="busin.TUser.CId" value="${requestScope.busin.TUser.CId}" type="hidden" />
							<input id="CCreateDate" name="busin.CCreateDate" value="${requestScope.busin.CCreateDate}" type="hidden" />
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
							<input type="text" id="CBillNo" name="busin.CBillNo" value="${requestScope.busin.CBillNo}" 
									class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
						<td style="width:100px;">
							<label>SHIPPER</label>
						</td>
						<td style="width:150px;">
							<input type="text" id="CSend" name="busin.CSend" value="${requestScope.busin.CSend}" 
									class="" style="width:146px;border:0px;" disabled="disabled" />
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							<label>CONSIGNEE</label>
						</td>
						<td style="width:150px;">
							<input type="text" id="CReceive" name="busin.CReceive" value="${requestScope.busin.CReceive}" 
									class="" style="width:146px;border:0px;" disabled="disabled" />
						</td>
						<td style="width:100px;">
							<label>CLIENT</label>
						</td>
						<td style="width:150px;">
							<select id="TCustomer" name="busin.TCustomer.CId" class="" style="width:146px;border:0px;" disabled="disabled">
				           		<option value="0">PLEASE CHOOSE</option>
								<c:forEach var="cusList" items="${requestScope.cusList}">
									<option value="${cusList.CId}">${cusList.CName}</option>
								</c:forEach>
							</select>
						</td>
						<td style="width:100px;">
							<label>BILL NO</label>
						</td>
						<td style="width:150px;">
							<input type="text" id="CTakeNo" name="busin.CTakeNo" value="${requestScope.busin.CTakeNo}" 
									class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
						<td style="width:100px;">
							<label>COMMODITIES</label>
						</td>
						<td style="width:150px;">
							<input type="text" id="CGoodsType" name="busin.CGoodsType" value="${requestScope.busin.CGoodsType}" 
									class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							<label>APPLY DATE</label>
						</td>
						<td style="width:150px;">
								<input id="appDate" value="${requestScope.appDate}"
									type="text" class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
						<td style="width:100px;">
							<label>RECEIVED DATE</label>
						</td>
						<td style="width:150px;">
								<input id="relDate" value="${requestScope.relDate}"
									type="text" class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
						<td style="width:100px;">
							<label>CLEARANCE DATE</label>
						</td>
						<td style="width:150px;">
								<input id="clearDate" value="${requestScope.clearDate}"
									type="text" class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
						<td style="width:100px;">
							<label>RETURN EMPTY DATE</label>
						</td>
						<td style="width:150px;">
							<input id="CContainerDate" value="${requestScope.CContainerDate}"
									type="text" class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							<label>PoL/PoD</label>
						</td>
						<td style="width:150px;">
							<input type="text" id="CArrivalPort" name="busin.CArrivalPort" value="${requestScope.busin.CArrivalPort}" 
									class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
						<td style="width:100px;">
							<label>ETD/ETA</label>
						</td>
						<td style="width:150px;">
							<input id="CArrivalDate" name="CArrivalDate" value="${requestScope.CArrivalDate}"
									type="text" class="" style="width:146px;border:0px;" onclick="WdatePicker()" disabled="disabled"/>
						</td>
						<td style="width:100px;">
							<label>DELIVERY DATE</label>
						</td>
						<td style="width:150px;">
							<input id="CDeliveryDate" name="CDeliveryDate" value="${requestScope.CDeliveryDate}"
									type="text" class="" style="width:146px;border:0px;" onclick="WdatePicker()" disabled="disabled"/>
						</td>
						<td style="width:100px;">
							<label>UNLOAD DATE</label>
						</td>
						<td style="width:150px;">
							<input id="CCompleteDate" name="CCompleteDate" value="${requestScope.CCompleteDate}"
									type="text" class="" style="width:146px;border:0px;" onclick="WdatePicker()" disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td colspan="8" style="text-align:left;" >
							<span style="color:blue; font-weight:bold; padding-left:20px;">CONTAINER INFO</span>
						</td>
					</tr>
				</table>
				<table id="conTable" class="table" style="margin-top:0px; width:1000px; margin-left:5px; color:black; border-top:0px;">
					<tbody id="con">
						<tr id="conTR0" style="border-top:0px;">
							<td style="width:100px; border-top:0px;">
								<label>CONTAINER NO</label>
							</td>
							<td style="width:150px; border-top:0px;">
								<input type="text" id="conNum0" name="cons[0].CContainerNum" 
										value="${requestScope.cons[0].CContainerNum}"
										class="" style="width:146px;border:0px;" disabled="disabled"/>
								<!-- edit -->
								<input type="hidden" name="cons[0].CId" value="${cons[0].CId}" />
								<input type="hidden" name="cons[0].TBusin.CId" value="${cons[0].TBusin.CId}" />
							</td>
							<td style="width:100px; border-top:0px;">
								<label>TYPE</label>
							</td>
							<td style="width:150px; border-top:0px;">
								<input type="text" id="conType0" name="cons[0].CContainerType" 
										value="${requestScope.cons[0].CContainerType}"
										class="" style="width:146px;border:0px;" disabled="disabled"/>
							</td>
							<td style="width:30px; border-top:0px;">
								<label>PKGS</label>
							</td>
							<td style="width:60px; border-top:0px;">
									<input type="text" id="CCount0"  
											value="${requestScope.cons[0].CCount}"
											class="" style="width:56px;border:0px;" disabled="disabled"/>
							</td>
							<td style="width:30px; border-top:0px;">
								<label>WEIGHT</label>
							</td>
							<td style="width:60px; border-top:0px;">
								
									<input type="text" id="CWeight0" 
											value="${requestScope.cons[0].CWeight}"
											class="" style="width:56px;border:0px;" disabled="disabled"/>
							</td>
							<td style="width:30px; border-top:0px;">
								<label>REMARKS</label>
							</td>
							<td style="width:124px; border-top:0px;">
								<input type="text" id="CRemarks0"
										value="${requestScope.cons[0].CRemarks}"
										class="" style="width:120px;border:0px;" disabled="disabled"/>
							</td>
							<td style="width:157px; border-top:0px;">
							</td>
						</tr>
						<s:iterator value="#request.cons" var="cons" status="num">
							<s:if test="#num.index > 0">
								<tr id="conTR<s:property value="#num.index" />">
									<td style="width:100px; border-top:0px;">
									</td>
									<td style="width:150px; border-top:0px;">
										<input type="text" id="conNum<s:property value="#num.index" />" 
												name="cons[<s:property value="#num.index" />].CContainerNum" 
												value="${requestScope.cons[num.index].CContainerNum}"
												class="" style="width:146px;border:0px;" disabled="disabled"/>
										<!-- edit -->
										<input type="hidden" name="cons[<s:property value="#num.index" />].CId" value="${cons[num.index].CId}" />
										<input type="hidden" name="cons[<s:property value="#num.index" />].TBusin.CId" value="${cons[num.index].TBusin.CId}" />
									</td>
									<td style="width:100px; border-top:0px;">
									</td>
									<td style="width:150px; border-top:0px;">
										<input type="text" id="conType<s:property value="#num.index" />" 
												name="cons[<s:property value="#num.index" />].CContainerType" 
												value="${requestScope.cons[num.index].CContainerType}"
												class="" style="width:146px;border:0px;" disabled="disabled"/>
									</td>
									<td style="width:30px; border-top:0px;">
										
									</td>
									<td style="width:60px; border-top:0px;">
											<input type="text" id="CCount<s:property value="#num.index" />"  
													value="${requestScope.cons[num.index].CCount}"
													class="" style="width:56px;border:0px;" disabled="disabled"/>
									</td>
									<td style="width:30px; border-top:0px;">
										
									</td>
									<td style="width:60px; border-top:0px;">
										
											<input type="text" id="CWeight<s:property value="#num.index" />" 
													value="${requestScope.cons[num.index].CWeight}"
													class="" style="width:56px;border:0px;" disabled="disabled"/>
									</td>
									<td style="width:30px; border-top:0px;">
									</td>
									<td style="width:124px; border-top:0px;">
										<input type="text" id="CRemarks<s:property value="#num.index" />" 
													value="${requestScope.cons[num.index].CRemarks}"
													class="" style="width:120px;border:0px;" disabled="disabled"/>
									</td>
									<td style="width:157px; border-top:0px;">
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
							<select id="TCostItem0" 
									name="costs[0].TCostItem.CId"
									class="" style="width:146px;border:0px;" disabled="disabled">
								<option value="0">PLEASE CHOOSE</option>
								<c:forEach var="costItemList" items="${requestScope.costItemList}">
									<option value="${costItemList.CId}">${costItemList.CName}</option>
								</c:forEach>
							</select>
							<input type="hidden" id="costItem0" value="${costs[0].TCostItem.CId}" />
							<!-- edit -->
							<input type="hidden" name="costs[0].CId" value="${costs[0].CId}" />
							<input type="hidden" name="costs[0].TBusin.CId" value="${costs[0].TBusin.CId}" />
							<input type="hidden" name="costs[0].CState" value="${costs[0].CState}" />
						</td>
						<td style="width:60px;">
							<label>AMOUNT</label>
						</td>
						<td style="width:90px;">
							<input type="text" id="CMoney0" 
									name="costs[0].CMoney" 
									value="${requestScope.costs[0].CMoney}"
									class="" style="width:86px;border:0px;" disabled="disabled"/>
						</td>
						<td style="width:60px;">
							<label>RECEIVER</label>
						</td>
						<td style="width:120px;">
							<select id="TUser0" 
									name="costs[0].TUserByCUserid.CId"
									class="" style="width:116px;border:0px;" disabled="disabled">
								<option value="0">PLEASE CHOOSE</option>
								<c:forEach var="userList" items="${requestScope.userList}">
									<option value="${userList.userid}">${userList.empName}</option>
								</c:forEach>
							</select>
							<input type="hidden" id="user0" value="${requestScope.costs[0].TUserByCUserid.CId}" />
						</td>
						<td style="width:60px;">
							<label>REMARKS</label>
						</td>
						<td style="width:200px;">
							<input type="text" id="CRemarks0" 
									name="costs[0].CRemarks" 
									value="${requestScope.costs[0].CRemarks}"
									class="" style="width:196px;border:0px;" disabled="disabled"/>
						</td>
						<td style="width:157px;">
						</td>
					</tr>
					<tbody id="cost">
						<s:iterator value="#request.costs" var="costs" status="cnum">
							<s:if test="#cnum.index > 0">
								<tr id="costTR<s:property value="#cnum.index" />">
									<td style="width:100px;">
									</td>
									<td style="width:150px;">
										<select id="TCostItem<s:property value="#cnum.index" />" 
												name="costs[<s:property value="#cnum.index" />].TCostItem.CId"
												class="" style="width:146px;border:0px;" disabled="disabled">
											<option value="0">PLEASE CHOOSE</option>
											<c:forEach var="costItemList" items="${requestScope.costItemList}">
												<option value="${costItemList.CId}">${costItemList.CName}</option>
											</c:forEach>
										</select>
										<input type="hidden" id="costItem<s:property value="#cnum.index" />" 
											   value="${costs[cnum.index].TCostItem.CId}" />
										<!-- edit -->
										<input type="hidden" name="costs[<s:property value="#cnum.index" />].CId" value="${costs[cnum.index].CId}" />
										<input type="hidden" name="costs[<s:property value="#cnum.index" />].TBusin.CId" value="${costs[cnum.index].TBusin.CId}" />
										<input type="hidden" name="costs[<s:property value="#cnum.index" />].CState" value="${costs[cnum.index].CState}" />
										<input type="hidden" name="costs[<s:property value="#cnum.index" />].TUserByCCreateUserid.CId" 
												value="${costs[cnum.index].TUserByCCreateUserid.CId}" />
									</td>
									<td style="width:60px;">
									</td>
									<td style="width:90px;">
										<input type="text" id="CMoney<s:property value="#cnum.index" />" 
												name="costs[<s:property value="#cnum.index" />].CMoney" 
												value="${requestScope.costs[cnum.index].CMoney}"
												class="" style="width:86px;border:0px;" disabled="disabled"/>
									</td>
									<td style="width:60x;">
									</td>
									<td style="width:120px;">
										<select id="TUser<s:property value="#cnum.index" />" 
												name="costs[<s:property value="#cnum.index" />].TUserByCUserid.CId"
												class="" style="width:116px;border:0px;" disabled="disabled">
											<option value="0">PLEASE CHOOSE</option>
											<c:forEach var="userList" items="${requestScope.userList}">
												<option value="${userList.userid}">${userList.empName}</option>
											</c:forEach>
										</select>
										<input type="hidden" id="user<s:property value="#cnum.index" />" value="${requestScope.costs[cnum.index].TUserByCUserid.CId}" />
									</td>
									<td style="width:60px;">
									</td>
									<td style="width:200px;">
										<input type="text" id="CRemarks<s:property value="#cnum.index" />" 
												name="costs[<s:property value="#cnum.index" />].CRemarks" 
												value="${requestScope.costs[cnum.index].CRemarks}"
												class="" style="width:196px;border:0px;" disabled="disabled"/>
									</td>
									<td style="width:157px;">
									</td>
								</tr>
							</s:if>
						</s:iterator>
					</tbody>
				</table>

				<table class="table" style="margin-top:0px; width:1025px; margin-left:5px; color:black; border-top:0px;">
					<tr>
						<td colspan="8" style="text-align:left;border-top:0px;" >
							<span style="color:blue; font-weight:bold; padding-left:20px;">INVOICE INFO</span>
							<input type="button" value="ADD" onclick="addCash();" style="margin-left:13px; width:60px; height:20px;"/>
						</td>
					</tr>
				</table>
				<table id="cashTable" class="table" style="margin-top:0px; width:1000px; margin-left:5px; color:black;border-top:0px;">
					<tbody id="cash">
						<s:iterator value="#request.cash" var="cash" status="anum">
							<tr id="cashTR<s:property value="#anum.index" />">
								<s:if test="#anum.index==0">
									<td style="width:100px;border-top:0px;">
										<label>回款项目</label>
								</s:if>
								<s:else>
									<td style="width:100px;">
								</s:else>
									</td>
								<s:if test="#anum.index==0">
									<td style="width:150px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:150px;">
								</s:else>
									<s:if test="#session.userID == cash[#anum.index].TUserByCCreateUserid.CId &&
												cash[#anum.index].CState == 1">
										<select id="TCashItem<s:property value="#anum.index" />" 
												name="cash[<s:property value="#anum.index" />].TCostItem.CId"
												class="border" style="width:146px;">
											<option value="0">PLEASE CHOOSE</option>
											<c:forEach var="cashItemList" items="${requestScope.cashItemList}">
												<option value="${cashItemList.CId}">${cashItemList.CName}</option>
											</c:forEach>
										</select>
									</s:if>
									<s:else>
										<select id="TCashItem<s:property value="#anum.index" />" 
												class="" style="width:146px; border:0px;" disabled="disabled">
											<option value="0">PLEASE CHOOSE</option>
											<c:forEach var="cashItemList" items="${requestScope.cashItemList}">
												<option value="${cashItemList.CId}">${cashItemList.CName}</option>
											</c:forEach>
										</select>
										<input type="hidden"
												name="cash[<s:property value="#anum.index" />].TCostItem.CId"
												value="${requestScope.cash[anum.index].TCostItem.CId}" />
									</s:else>
										<input type="hidden" id="cashItem<s:property value="#anum.index" />" 
												value="${cash[anum.index].TCostItem.CId}" />
									</td>
								<s:if test="#anum.index==0">
									<td style="width:60px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:60px;">
								</s:else>
										<s:if test="#anum.index==0">
											<label>金额</label>
										</s:if>
									</td>
								<s:if test="#anum.index==0">
									<td style="width:90px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:90px;">
								</s:else>
										<s:if test="#session.userID == cash[#anum.index].TUserByCCreateUserid.CId &&
												    cash[#anum.index].CState == 1">
											<input type="text" id="CMoney2<s:property value="#anum.index" />" 
													name="cash[<s:property value="#anum.index" />].CMoney" 
													value="${requestScope.cash[anum.index].CMoney}"
													class="field border" style="width:86px;"/>
										</s:if>
										<s:else>
											<input type="text" id="CMoney2<s:property value="#anum.index" />" 
													value="${requestScope.cash[anum.index].CMoney}"
													class="" style="width:86px;border:0px;" disabled="disabled"/>
											<input type="hidden"
													name="cash[<s:property value="#anum.index" />].CMoney" 
													value="${requestScope.cash[anum.index].CMoney}" />
										</s:else>
									</td>
								<s:if test="#anum.index==0">
									<td style="width:60px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:60px;">
								</s:else>
										<!--  
										<s:if test="#anum.index==0">
											<label><span style="color:red;">*</span>回款人</label>
										</s:if>
										-->
									</td>
								<s:if test="#anum.index==0">
									<td style="width:120px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:120px;">
								</s:else>
										<!-- 
										<s:if test="#session.userID == cash[#anum.index].TUserByCCreateUserid.CId &&
													    cash[#anum.index].CState == 1">
												<select id="TUser2<s:property value="#anum.index" />" 
														name="cash[<s:property value="#anum.index" />].TUserByCUserid.CId"
														class="border" style="width:116px;">
													<option value="0">PLEASE CHOOSE</option>
													<c:forEach var="userList" items="${requestScope.userList}">
														<option value="${userList.userid}">${userList.empName}</option>
													</c:forEach>
												</select>
										</s:if>
										<s:else>
											<select id="TUser2<s:property value="#anum.index" />" 
													class="" style="width:116px;border:0px;" disabled="disabled">
												<option value="0">PLEASE CHOOSE</option>
												<c:forEach var="userList" items="${requestScope.userList}">
													<option value="${userList.userid}">${userList.empName}</option>
												</c:forEach>
											</select>
											<input type="hidden"
													name="cash[<s:property value="#anum.index" />].TUserByCUserid.CId" 
													value="${requestScope.cash[anum.index].TUserByCUserid.CId}" />
										</s:else>
										<input type="hidden" id="user2<s:property value="#anum.index" />" value="${requestScope.cash[anum.index].TUserByCUserid.CId}" />
										 -->
									</td>
								<s:if test="#anum.index==0">
									<td style="width:60px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:60px;">
								</s:else>
										<s:if test="#anum.index==0">
											<label>进度</label>
										</s:if>
									</td>
								<s:if test="#anum.index==0">
									<td style="width:200px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:200px;">
								</s:else>
										<s:if test="cash[#anum.index].CState == 1 && #session.fun303 == 1 && cash[#anum.index].CSchedule == 0">
											<input id="no<s:property value="#anum.index" />" type="radio" 
													name="cash[<s:property value="#anum.index" />].CSchedule" 
													value="0" style="margin-left:10px; margin-right:10px;" />未收款
											<input id="yes<s:property value="#anum.index" />" type="radio" 
													name="cash[<s:property value="#anum.index" />].CSchedule" 
													value="1" style="margin-left:10px; margin-right:10px;" />已收款
										</s:if>
										<s:else>
											<input id="no<s:property value="#anum.index" />" type="radio" 
													value="0" style="margin-left:10px; margin-right:10px;" disabled="disabled"/>未收款
											<input id="yes<s:property value="#anum.index" />" type="radio" 
													value="1" style="margin-left:10px; margin-right:10px;" disabled="disabled"/>已收款
											<input type="hidden"
													name="cash[<s:property value="#anum.index" />].CSchedule" 
													value="${requestScope.cash[anum.index].CSchedule}"/>
										</s:else>
										<input type="hidden"
												id="schedule<s:property value="#anum.index" />"
												value="${requestScope.cash[anum.index].CSchedule}"/>
									</td>
								<s:if test="#anum.index==0">
									<td style="width:157px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:157px;">
								</s:else>
										<s:if test="#session.userID == cash[#anum.index].TUserByCCreateUserid.CId &&
															    cash[#anum.index].CState == 1">
											<input type="button" value="ADD" onclick="addCash();" style="float:left; margin-left:13px; width:60px; height:20px;"/>
											<input type="button" value="DELETE" onclick="delCash(this);" style="float:left; margin-left:10px; width:60px; height:20px;"/>
										</s:if>
										<input type="hidden" name="cash[<s:property value="#anum.index" />].CId" value="${cash[anum.index].CId}" />
										<input type="hidden" name="cash[<s:property value="#anum.index" />].TBusin.CId" value="${cash[anum.index].TBusin.CId}" />
										<input type="hidden" name="cash[<s:property value="#anum.index" />].CState" value="${cash[anum.index].CState}" />
										<input type="hidden" name="cash[<s:property value="#anum.index" />].TUserByCCreateUserid.CId" 
											value="${cash[anum.index].TUserByCCreateUserid.CId}" />
									</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				
				
				
				
				
				
				<table class="table" style="margin-top:0px; width:1025px; margin-left:5px; color:black; border-top:0px;">
					<tr>
						<td colspan="8" style="text-align:left;border-top:0px;" >
							<span style="color:blue; font-weight:bold; padding-left:20px;">INVOICE WITH RATE INFO</span>
							<input type="button" value="ADD" onclick="addRate();" style="margin-left:13px; width:60px; height:20px;"/>
						</td>
					</tr>
				</table>
				<table id="rateTable" class="table" style="margin-top:0px; width:1000px; margin-left:5px; color:black;border-top:0px;">
					<tbody id="rate">
						<s:iterator value="#request.rate" var="rate" status="rnum">
							<tr id="rateTR<s:property value="#rnum.index" />">
								<s:if test="#rnum.index==0">
									<td style="width:100px;border-top:0px;">
										<label>回款项目</label>
								</s:if>
								<s:else>
									<td style="width:100px;">
								</s:else>
									</td>
								<s:if test="#rnum.index==0">
									<td style="width:150px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:150px;">
								</s:else>
									<s:if test="#session.userID == rate[#rnum.index].TUserByCCreateUserid.CId &&
												rate[#rnum.index].CState == 1">
										<select id="TRateItem<s:property value="#rnum.index" />" 
												name="rate[<s:property value="#rnum.index" />].TCostItem.CId"
												class="border" style="width:146px;">
											<option value="0">PLEASE CHOOSE</option>
											<c:forEach var="cashItemList" items="${requestScope.cashItemList}">
												<option value="${cashItemList.CId}">${cashItemList.CName}</option>
											</c:forEach>
										</select>
									</s:if>
									<s:else>
										<select id="TRateItem<s:property value="#rnum.index" />" 
												class="" style="width:146px; border:0px;" disabled="disabled">
											<option value="0">PLEASE CHOOSE</option>
											<c:forEach var="cashItemList" items="${requestScope.cashItemList}">
												<option value="${cashItemList.CId}">${cashItemList.CName}</option>
											</c:forEach>
										</select>
										<input type="hidden"
												name="rate[<s:property value="#rnum.index" />].TCostItem.CId"
												value="${requestScope.rate[rnum.index].TCostItem.CId}" />
									</s:else>
										<input type="hidden" id="rateItem<s:property value="#rnum.index" />" 
												value="${rate[rnum.index].TCostItem.CId}" />
									</td>
								<s:if test="#rnum.index==0">
									<td style="width:60px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:60px;">
								</s:else>
										<s:if test="#rnum.index==0">
											<label>金额</label>
										</s:if>
									</td>
								<s:if test="#rnum.index==0">
									<td style="width:90px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:90px;">
								</s:else>
										<s:if test="#session.userID == rate[#rnum.index].TUserByCCreateUserid.CId &&
												    rate[#rnum.index].CState == 1">
											<input type="text" id="CMoney3<s:property value="#rnum.index" />" 
													name="rate[<s:property value="#rnum.index" />].CMoney" 
													value="${requestScope.rate[rnum.index].CMoney}"
													class="field border" style="width:86px;"/>
										</s:if>
										<s:else>
											<input type="text" id="CMoney3<s:property value="#rnum.index" />" 
													value="${requestScope.rate[rnum.index].CMoney}"
													class="" style="width:86px;border:0px;" disabled="disabled"/>
											<input type="hidden"
													name="rate[<s:property value="#rnum.index" />].CMoney" 
													value="${requestScope.rate[rnum.index].CMoney}" />
										</s:else>
									</td>
								<s:if test="#rnum.index==0">
									<td style="width:60px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:60px;">
								</s:else>
									<s:if test="#session.userID == rate[#rnum.index].TUserByCCreateUserid.CId &&
												rate[#rnum.index].CState == 1">
										<select id="Rate<s:property value="#rnum.index" />"
												 name="rate[<s:property value="#rnum.index" />].TRate.CId"
												 class="border" style="width:116px;">
											<option value="0"></option>
											<c:forEach var="rateList" items="${requestScope.rateList}">
												<option value="${rateList.CId}">${rateList.CRate}</option>
											</c:forEach>
										</select>
									</s:if>
									<s:else>
										<select id="Rate<s:property value="#rnum.index" />"
												 class="border" style="width:116px;">
											<option value="0"></option>
											<c:forEach var="rateList" items="${requestScope.rateList}">
												<option value="${rateList.CId}">${rateList.CRate}</option>
											</c:forEach>
										</select>
										<input type="hidden"
												name="rate[<s:property value="#rnum.index" />].TRate.CId"
												value="${requestScope.rate[rnum.index].TRate.CId}" />
									</s:else>
									<input type="hidden" id="rate<s:property value="#rnum.index" />" 
												value="${rate[rnum.index].TRate.CId}" />
									</td>
								<s:if test="#rnum.index==0">
									<td style="width:120px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:120px;">
								</s:else>
										<!-- 
										<s:if test="#session.userID == cash[#anum.index].TUserByCCreateUserid.CId &&
													    cash[#anum.index].CState == 1">
												<select id="TUser2<s:property value="#anum.index" />" 
														name="cash[<s:property value="#anum.index" />].TUserByCUserid.CId"
														class="border" style="width:116px;">
													<option value="0">PLEASE CHOOSE</option>
													<c:forEach var="userList" items="${requestScope.userList}">
														<option value="${userList.userid}">${userList.empName}</option>
													</c:forEach>
												</select>
										</s:if>
										<s:else>
											<select id="TUser2<s:property value="#anum.index" />" 
													class="" style="width:116px;border:0px;" disabled="disabled">
												<option value="0">PLEASE CHOOSE</option>
												<c:forEach var="userList" items="${requestScope.userList}">
													<option value="${userList.userid}">${userList.empName}</option>
												</c:forEach>
											</select>
											<input type="hidden"
													name="cash[<s:property value="#anum.index" />].TUserByCUserid.CId" 
													value="${requestScope.cash[anum.index].TUserByCUserid.CId}" />
										</s:else>
										<input type="hidden" id="user2<s:property value="#anum.index" />" value="${requestScope.cash[anum.index].TUserByCUserid.CId}" />
										 -->
									</td>
								<s:if test="#rnum.index==0">
									<td style="width:60px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:60px;">
								</s:else>
										<s:if test="#rnum.index==0">
											<label>进度</label>
										</s:if>
									</td>
								<s:if test="#rnum.index==0">
									<td style="width:200px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:200px;">
								</s:else>
										<s:if test="rate[#rnum.index].CState == 1 && #session.fun303 == 1 && rate[#rnum.index].CSchedule == 0">
											<input id="rateno<s:property value="#rnum.index" />" type="radio" 
													name="rate[<s:property value="#rnum.index" />].CSchedule" 
													value="0" style="margin-left:10px; margin-right:10px;" />未收款
											<input id="rateyes<s:property value="#rnum.index" />" type="radio" 
													name="rate[<s:property value="#rnum.index" />].CSchedule" 
													value="1" style="margin-left:10px; margin-right:10px;" />已收款
										</s:if>
										<s:else>
											<input id="rateno<s:property value="#rnum.index" />" type="radio" 
													value="0" style="margin-left:10px; margin-right:10px;" disabled="disabled"/>未收款
											<input id="rateyes<s:property value="#rnum.index" />" type="radio" 
													value="1" style="margin-left:10px; margin-right:10px;" disabled="disabled"/>已收款
											<input type="hidden"
													name="rate[<s:property value="#rnum.index" />].CSchedule" 
													value="${requestScope.rate[rnum.index].CSchedule}"/>
										</s:else>
										<input type="hidden"
												id="schedule2<s:property value="#rnum.index" />"
												value="${requestScope.rate[rnum.index].CSchedule}"/>
									</td>
								<s:if test="#rnum.index==0">
									<td style="width:157px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:157px;">
								</s:else>
										<s:if test="#session.userID == rate[#rnum.index].TUserByCCreateUserid.CId &&
													rate[#rnum.index].CState == 1">
											<input type="button" value="ADD" onclick="addRate();" style="float:left; margin-left:13px; width:60px; height:20px;"/>
											<input type="button" value="DELETE" onclick="delRate(this);" style="float:left; margin-left:10px; width:60px; height:20px;"/>
										</s:if>
										<input type="hidden" name="rate[<s:property value="#rnum.index" />].CId" value="${rate[rnum.index].CId}" />
										<input type="hidden" name="rate[<s:property value="#rnum.index" />].TBusin.CId" value="${rate[rnum.index].TBusin.CId}" />
										<input type="hidden" name="rate[<s:property value="#rnum.index" />].CState" value="${rate[rnum.index].CState}" />
										<input type="hidden" name="rate[<s:property value="#rnum.index" />].TUserByCCreateUserid.CId" 
											value="${rate[rnum.index].TUserByCCreateUserid.CId}" />
									</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				
				
				
				
				<table id="remars" class="table" style="margin-top:10px; width:1000px; margin-left:5px; color:black;border-top:0px;">
					<tr>
						<td style="width:100px;">
							REMARKS
						</td>
						<td style="width:918px; height:40px;">
							<textarea id="remarks" name="busin.CRemarks" rows="2" cols="10" style="width:914px; border:0px;" disabled="disabled">${requestScope.busin.CRemarks}</textarea>
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							REMARKS
						</td>
						<td style="width:918px; height:40px;">
							<textarea id="remarks3" name="remarks3" rows="2" cols="10" class="border" style="width:914px;">${requestScope.remarks3}</textarea>
						</td>
					</tr>
				</table>
				
				<table>
					<tr>
						<td colspan="8" style="padding-top:20px; padding-bottom:20px;">
							<input type="button" value="SAVE" style="float:left; width:80px;height:25px;margin-left:380px;" onclick="save();"/>
							<input type="button" value="RETURN" style="float:left; width:80px;height:25px;margin-left:20px;" onclick="returnList();"/>
						</td>
					</tr>
				</table>
	        </form>
        </div>
  	</div>
  </body>
</html>

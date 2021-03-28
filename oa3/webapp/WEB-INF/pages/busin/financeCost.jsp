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
		
		var typeId = null;
		var typeName = null;
		var costItemId = null;
		var costItemName = null;
		var empId = null;
		var empName = null;
		
		//var costUserList = null;
		//var costUserCount = 0;

		//后台提示
		$(document).ready(function(){
		
			javascript:dwr.engine.setAsync(false);//设置dwr同步
			
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
			
			//服务类型
			typeId = "${requestScope.typeId}";
			typeId = typeId.substring(1, typeId.length - 1).split(", ");
			typeName = "${requestScope.typeName}";
			typeName = typeName.substring(1, typeName.length - 1).split(", ");
			
			//成本项目
			costItemId = "${requestScope.costItemId}";
			costItemId = costItemId.substring(1, costItemId.length - 1).split(", ");
			costItemName = "${requestScope.costItemName}";
			costItemName = costItemName.substring(1, costItemName.length - 1).split(", ");
			
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
			
			//领款人列表
			//costUserList = "${requestScope.costUserList}";
			//alert(costUserList);
			//costUserList = costUserList.substring(1, costUserList.length - 1).split(", ");
			//领款人数量
			//costUserCount = "${requestScope.costUserCount}";

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
			
			//服务项目信息
			for (var i=0; i<=serviceCount; i++){

				//服务类型
				var type = document.getElementById("servicetype"+i).value;
				if (type != null && type != ""){
					var select = document.getElementById("TServiceType"+i);
					for(var j=0;j<select.options.length;j++){
						if(select.options[j].value == type){
							select.options[j].selected = true;
							selectType(i);
							break;
						}
					}
				}
				
				//服务项目
				var item = document.getElementById("serviceitem"+i).value;
				if (item != null && item != ""){
					var select = document.getElementById("TServiceItem"+i);
					for(var j=0;j<select.options.length;j++){
						if(select.options[j].value == item){
							select.options[j].selected = true;
							break;
						}
					}
				}
			}
			
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
			
			//后台校验错误信息
			var errInfo = "${requestScope.errInfo}";
			if (errInfo != null && errInfo.length > 0){
				alert(errInfo);
			}
		
			javascript:dwr.engine.setAsync(true);//设置dwr异步
			
		});
		
		//返回
		function returnList(){
			document.getElementById("editForm").action = "${ctx}/busin/FinanceCostList.action";
			document.getElementById("editForm").submit();
		}
		
		//得到服务类型
		function getServiceType(count){
			var type = document.getElementById("TServiceType"+count);
			for (var i=0; i<typeId.length; i++){
				type.options.add(new Option(typeName[i], typeId[i])); 
			}
		}
	
		//得到服务项目
		function selectType(count){
			typeItemCount = count;
			var typeId = document.getElementById("TServiceType"+count).value;
			if (typeId > 0){
				serviceItemService.getValidList(typeId, rtnServiceItem);
			}else{
				rtnServiceItem(null);
			}
		}
		
		function rtnServiceItem(list){
			dwr.util.removeAllOptions("TServiceItem"+typeItemCount);
			dwr.util.addOptions("TServiceItem"+typeItemCount, [{CId:0, CName:"--请选择--"}], "CId", "CName");
			dwr.util.addOptions("TServiceItem"+typeItemCount, list, "CId", "CName");
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
		
		//打印领款单
		function printCost(){
			document.getElementById("editForm").action = "${ctx}/busin/PrintCost.action";
			document.getElementById("editForm").submit();
		}
		
		//确认领款
		function confirmCost(){
			if (confirm("是否确认领款？")){
				document.getElementById("editForm").action = "${ctx}/busin/ConfirmCost.action";
				document.getElementById("editForm").submit();
			}
		}
		
	</script>
  </head>
  <body>
  	<div id="mainContent">
  		<div class="right">
			<form id="editForm" action="${ctx}/busin/UpdateBusin.action" method="post">
				<div style="margin-left:5px; width:1000px; text-align:center; margin-top:50px;">
					<span style="color:black; font-size:14px; font-weight:bold;">
							服&nbsp&nbsp务&nbsp&nbsp单
					</span>
				</div>
				<table class="table" style="margin-top:25px; width:1025px; margin-left:5px; color:black; border-bottom:0px;">
					<tr style="border-bottom:0px;">
						<td colspan="8" style="text-align:left;border-top:0px;border-bottom:0px;" >
							<span style="color:blue; font-weight:bold; padding-left:20px;">基本信息</span>
						</td>
					</tr>
				</table>
				<table id="mainTable" class="table" style="margin-top:0px; width:1000px; margin-left:5px; color:black;">
					<tr>
						<td style="width:100px;">
							<label>服务单日期</label>
						</td>
						<td style="width:150px;">
							<input id="CDate" name="cdate" value="${requestScope.cdate}"
									type="text" class="field border" style="width:146px" onclick="WdatePicker()" disabled="disabled"/>
							<!-- edit -->
							<input id="businId" name="businId" value="${requestScope.busin.CId}"
									type="hidden" class="field border" style="width:146px"/>
							<input id="businState" name="busin.CState" value="${requestScope.busin.CState}" type="hidden"/>
							<input id="TUser" name="busin.TUser.CId" value="${requestScope.busin.TUser.CId}" type="hidden" />
							<input id="CCreateDate" name="busin.CCreateDate" value="${requestScope.busin.CCreateDate}" type="hidden" />
						</td>
						<td style="width:100px;">
							<label>单据编号</label>
						</td>
						<td style="width:150px;">
							<!-- edit -->
							<input type="text" id="CBillNo" name="busin.CBillNo" value="${requestScope.busin.CBillNo}" 
									class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
						<td style="width:100px;">
							<label>发货人</label>
						</td>
						<td style="width:150px;">
							<input type="text" id="CSend" name="busin.CSend" value="${requestScope.busin.CSend}" 
									class="field border" style="width:146px;" disabled="disabled" />
						</td>
						<td style="width:100px;">
							<label>收货人</label>
						</td>
						<td style="width:150px;">
							<input type="text" id="CReceive" name="busin.CReceive" value="${requestScope.busin.CReceive}" 
									class="field border" style="width:146px;" disabled="disabled" />
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							<label>客户名称</label>
						</td>
						<td style="width:150px;">
							<select id="TCustomer" name="busin.TCustomer.CId" class="border" style="width:146px;" disabled="disabled">
				           		<option value="0">--请选择--</option>
								<c:forEach var="cusList" items="${requestScope.cusList}">
									<option value="${cusList.CId}">${cusList.CName}</option>
								</c:forEach>
							</select>
						</td>
						<td style="width:100px;">
							<label>提单号码</label>
						</td>
						<td style="width:150px;">
							<input type="text" id="CTakeNo" name="busin.CTakeNo" value="${requestScope.busin.CTakeNo}" 
									class="field border" style="width:146px;" disabled="disabled"/>
						</td>
						<td style="width:100px;">
							<label>货物种类</label>
						</td>
						<td style="width:150px;">
							<input type="text" id="CGoodsType" name="busin.CGoodsType" value="${requestScope.busin.CGoodsType}" 
									class="field border" style="width:146px;" disabled="disabled"/>
						</td>
						<td style="width:100px;">
							<label>到达码头</label>
						</td>
						<td style="width:150px;">
							<input type="text" id="CArrivalPort" name="busin.CArrivalPort" value="${requestScope.busin.CArrivalPort}" 
									class="field border" style="width:146px;" disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							<label>到岗日期</label>
						</td>
						<td style="width:150px;">
							<input id="CArrivalDate" name="CArrivalDate" value="${requestScope.CArrivalDate}"
									type="text" class="field border" style="width:146px" onclick="WdatePicker()" disabled="disabled"/>
						</td>
						<td style="width:100px;">
							<label>交货日期</label>
						</td>
						<td style="width:150px;">
							<input id="CDeliveryDate" name="CDeliveryDate" value="${requestScope.CDeliveryDate}"
									type="text" class="field border" style="width:146px" onclick="WdatePicker()" disabled="disabled"/>
						</td>
						<td style="width:100px;">
							<label>卸货完毕日期</label>
						</td>
						<td style="width:150px;">
							<input id="CCompleteDate" name="CCompleteDate" value="${requestScope.CCompleteDate}"
									type="text" class="field border" style="width:146px" onclick="WdatePicker()" disabled="disabled"/>
						</td>
						<td style="width:100px;">
						</td>
						<td style="width:150px;">
						</td>
					</tr>
					<tr>
						<td colspan="8" style="text-align:left;" >
							<span style="color:blue; font-weight:bold; padding-left:20px;">货柜信息</span>
						</td>
					</tr>
				</table>
				<table id="conTable" class="table" style="margin-top:0px; width:1000px; margin-left:5px; color:black; border-top:0px;">
					<tbody id="con">
						<tr id="conTR0" style="border-top:0px;">
							<td style="width:100px; border-top:0px;">
								<label>货柜号码</label>
							</td>
							<td style="width:150px; border-top:0px;">
								<input type="text" id="conNum0" name="cons[0].CContainerNum" 
										value="${requestScope.cons[0].CContainerNum}"
										class="field border" style="width:146px;" disabled="disabled"/>
								<!-- edit -->
								<input type="hidden" name="cons[0].CId" value="${cons[0].CId}" />
								<input type="hidden" name="cons[0].TBusin.CId" value="${cons[0].TBusin.CId}" />
							</td>
							<td style="width:100px; border-top:0px;">
								<label>货柜类型</label>
							</td>
							<td style="width:150px; border-top:0px;">
								<input type="text" id="conType0" name="cons[0].CContainerType" 
										value="${requestScope.cons[0].CContainerType}"
										class="field border" style="width:146px;" disabled="disabled"/>
							</td>
							<td style="width:509px; border-top:0px;">
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
												class="field border" style="width:146px;" disabled="disabled"/>
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
												class="field border" style="width:146px;" disabled="disabled"/>
									</td>
									<td style="width:509px; border-top:0px;">
									</td>
								</tr>
							</s:if>
						</s:iterator>
					</tbody>
				</table>
				<table class="table" style="margin-top:0px; width:1025px; margin-left:5px; color:black; border-top:0px;">
					<tr>
						<td colspan="8" style="text-align:left;border-top:0px;" >
							<span style="color:blue; font-weight:bold; padding-left:20px;">服务项目</span>
						</td>
					</tr>
				</table>
				<table id="serviceTable" class="table" style="margin-top:0px; width:1000px; margin-left:5px; color:black; border-top:0px;">
					<tbody id="service">
						<tr id="serviceTR0" style="border-top:0px;">
							<td style="width:100px; border-top:0px;">
								<label>服务类型</label>
							</td>
							<td style="width:150px; border-top:0px;">
								<select id="TServiceType0" 
										name="services[0].TServiceType.CId"
										class="border" style="width:146px;" 
										onchange="selectType(0)" disabled="disabled">
									<option value="0">--请选择--</option>
									<c:forEach var="typeList" items="${requestScope.typeList}">
										<option value="${typeList.CId}">${typeList.CName}</option>
									</c:forEach>
								</select>
								<input type="hidden" id="servicetype0" value="${services[0].TServiceType.CId}" />
								<!-- edit -->
								<input type="hidden" name="services[0].CId" value="${services[0].CId}" />
								<input type="hidden" name="services[0].TBusin.CId" value="${services[0].TBusin.CId}" />
							</td>
							<td style="width:100px; border-top:0px;">
								<label>服务项目</label>
							</td>
							<td style="width:150px; border-top:0px;">
								<select id="TServiceItem0" 
										name="services[0].TServiceItem.CId"
										class="border" style="width:146px;" disabled="disabled">
									<option value="0">--请选择--</option>
								</select>
								<input type="hidden" id="serviceitem0" value="${services[0].TServiceItem.CId}" />
							</td>
							<td style="width:60px; border-top:0px;">
								<label>件数</label>
							</td>
							<td style="width:110px; border-top:0px;">
								<input type="text" id="CCount0" name="services[0].CCount" 
										value="${requestScope.services[0].CCount}"
										class="field border" style="width:106px;" disabled="disabled"/>
							</td>
							<td style="width:60px; border-top:0px;">
								<label>重量</label>
							</td>
							<td style="width:110px; border-top:0px;">
								<input type="text" id="CWeight0" name="services[0].CWeight" 
										value="${requestScope.services[0].CWeight}"
										class="field border" style="width:106px;" disabled="disabled"/>
							</td>
							<td style="width:157px; border-top:0px;">
							</td>
						</tr>
						<s:iterator value="#request.services" var="services" status="snum">
							<s:if test="#snum.index > 0">
								<tr id="serviceTR<s:property value="#snum.index" />">
									<td style="width:100px; border-top:0px;">
									</td>
									<td style="width:150px; border-top:0px;">
										<select id="TServiceType<s:property value="#snum.index" />" 
												name="services[<s:property value="#snum.index" />].TServiceType.CId"
												class="border" style="width:146px;" 
												onchange="selectType(<s:property value="(#snum.index)" />)" disabled="disabled">
											<option value="0">--请选择--</option>
											<c:forEach var="typeList" items="${requestScope.typeList}">
												<option value="${typeList.CId}">${typeList.CName}</option>
											</c:forEach>
										</select>
										<input type="hidden" id="servicetype<s:property value="#snum.index" />" 
											   value="${services[snum.index].TServiceType.CId}" />
										<!-- edit -->
										<input type="hidden" name="services[<s:property value="#snum.index" />].CId" value="${services[snum.index].CId}" />
										<input type="hidden" name="services[<s:property value="#snum.index" />].TBusin.CId" value="${services[snum.index].TBusin.CId}" />
									</td>
									<td style="width:100px; border-top:0px;">
									<br /></td>
									<td style="width:150px; border-top:0px;">
										<select id="TServiceItem<s:property value="#snum.index" />" 
												name="services[<s:property value="#snum.index" />].TServiceItem.CId"
												class="border" style="width:146px;" disabled="disabled">
											<option value="0">--请选择--</option>
										</select>
										<input type="hidden" id="serviceitem<s:property value="#snum.index" />" 
											   value="${services[snum.index].TServiceItem.CId}" />
									</td>
									<td style="width:60px; border-top:0px;">
									</td>
									<td style="width:110px; border-top:0px;">
										<input type="text" id="CCount<s:property value="#snum.index" />" 
												name="services[<s:property value="#snum.index" />].CCount" 
												value="${requestScope.services[snum.index].CCount}"
												class="field border" style="width:106px;" disabled="disabled"/>
									</td>
									<td style="width:60px; border-top:0px;">
									</td>
									<td style="width:110px; border-top:0px;">
										<input type="text" id="CWeight<s:property value="#snum.index" />" 
												name="services[<s:property value="#snum.index" />].CWeight" 
												value="${requestScope.services[snum.index].CWeight}"
												class="field border" style="width:106px;" disabled="disabled"/>
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
							<span style="color:blue; font-weight:bold; padding-left:20px;">成本信息</span>
						</td>
					</tr>
				</table>
				<table id="costTable" class="table" style="margin-top:0px; width:1000px; margin-left:5px; color:black;">
					<tr id="costTR0">
						<td style="width:100px;">
							<label>成本项目</label>
						</td>
						<td style="width:150px;">
							<select id="TCostItem0" 
									class="border" style="width:146px;" disabled="disabled">
								<option value="0">--请选择--</option>
								<c:forEach var="costItemList" items="${requestScope.costItemList}">
									<option value="${costItemList.CId}">${costItemList.CName}</option>
								</c:forEach>
							</select>
							<input type="hidden" id="costItem0" name="costs[0].TCostItem.CId" value="${costs[0].TCostItem.CId}" />
							<!-- edit -->
							<input type="hidden" name="costs[0].CId" value="${costs[0].CId}" />
							<input type="hidden" name="costs[0].TBusin.CId" value="${costs[0].TBusin.CId}" />
							<input type="hidden" name="costs[0].CState" value="${costs[0].CState}" />
						</td>
						<td style="width:60px;">
							<label>金额</label>
						</td>
						<td style="width:90px;">
							<input type="text" id="CMoney0" 
									value="${requestScope.costs[0].CMoney}"
									class="field border" style="width:86px;" disabled="disabled"/>
							<input type="hidden"
									name="costs[0].CMoney" 
									value="${requestScope.costs[0].CMoney}"/>
						</td>
						<td style="width:60px;">
							<label>领款人</label>
						</td>
						<td style="width:120px;">
							<select id="TUser0" 
									class="border" style="width:116px;" disabled="disabled">
								<option value="0">--请选择--</option>
								<c:forEach var="userList" items="${requestScope.userList}">
									<option value="${userList.userid}">${userList.empName}</option>
								</c:forEach>
							</select>
							<input type="hidden" id="user0" name="costs[0].TUserByCUserid.CId" value="${requestScope.costs[0].TUserByCUserid.CId}" />
						</td>
						<td style="width:60px;">
							<label>备注</label>
						</td>
						<td style="width:200px;">
							<input type="text" id="CRemarks0" 
									name="costs[0].CRemarks" 
									value="${requestScope.costs[0].CRemarks}"
									class="field border" style="width:196px;" disabled="disabled"/>
						</td>
						<td style="width:157px;">
							<s:if test="costs[0].CState == 2">
								<span>财务未审核</span>
							</s:if>
							<s:if test="costs[0].CState == 3">
								<span>财务审核通过</span>
							</s:if>
							<s:if test="costs[0].CState == 4">
								<span>财务审核未通过</span>
							</s:if>
							<s:if test="costs[0].CState == 5">
								<span>已领款</span>
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
										<select id="TCostItem<s:property value="#cnum.index" />" 
												class="border" style="width:146px;" disabled="disabled">
											<option value="0">--请选择--</option>
											<c:forEach var="costItemList" items="${requestScope.costItemList}">
												<option value="${costItemList.CId}">${costItemList.CName}</option>
											</c:forEach>
										</select>
										<input type="hidden" id="costItem<s:property value="#cnum.index" />" 
											   name="costs[<s:property value="#cnum.index" />].TCostItem.CId"
											   value="${costs[cnum.index].TCostItem.CId}" />
										<!-- edit -->
										<input type="hidden" name="costs[<s:property value="#cnum.index" />].CId" value="${costs[cnum.index].CId}" />
										<input type="hidden" name="costs[<s:property value="#cnum.index" />].TBusin.CId" value="${costs[cnum.index].TBusin.CId}" />
										<input type="hidden" name="costs[<s:property value="#cnum.index" />].CState" value="${costs[cnum.index].CState}" />
									</td>
									<td style="width:60px;">
									</td>
									<td style="width:90px;">
										<input type="text" id="CMoney<s:property value="#cnum.index" />" 
												name="costs[<s:property value="#cnum.index" />].CMoney" 
												value="${requestScope.costs[cnum.index].CMoney}"
												class="field border" style="width:86px;" disabled="disabled"/>
										<input type="hidden"
												name="costs[<s:property value="#cnum.index" />].CMoney" 
												value="${requestScope.costs[cnum.index].CMoney}"
										/>
									</td>
									<td style="width:60x;">
									</td>
									<td style="width:120px;">
										<select id="TUser<s:property value="#cnum.index" />" 
												class="border" style="width:116px;" disabled="disabled">
											<option value="0">--请选择--</option>
											<c:forEach var="userList" items="${requestScope.userList}">
												<option value="${userList.userid}">${userList.empName}</option>
											</c:forEach>
										</select>
										<input type="hidden" id="user<s:property value="#cnum.index" />" 
												name="costs[<s:property value="#cnum.index" />].TUserByCUserid.CId"
												value="${requestScope.costs[cnum.index].TUserByCUserid.CId}" />
									</td>
									<td style="width:60px;">
									</td>
									<td style="width:200px;">
										<input type="text" id="CRemarks<s:property value="#cnum.index" />" 
												name="costs[<s:property value="#cnum.index" />].CRemarks" 
												value="${requestScope.costs[cnum.index].CRemarks}"
												class="field border" style="width:196px;" disabled="disabled"/>
									</td>
									<td style="width:157px;">
										<s:if test="costs[#cnum.index].CState == 2">
											<span>财务未审核</span>
										</s:if>
										<s:if test="costs[#cnum.index].CState == 3">
											<span>财务审核通过</span>
										</s:if>
										<s:if test="costs[#cnum.index].CState == 4">
											<span>财务审核未通过</span>
										</s:if>
										<s:if test="costs[#cnum.index].CState == 5">
											<span>已领款</span>
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
							制单人备注
						</td>
						<td style="width:918px; height:40px;">
							<textarea id="remarks" name="busin.CRemarks" rows="2" cols="10" style="width:914px; border:0px;" disabled="disabled">${requestScope.busin.CRemarks}</textarea>
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							部门审核意见
						</td>
						<td style="width:918px; height:40px;">
							<textarea id="remarks2" name="remarks2" rows="2" cols="10" style="width:914px; border:0px;" disabled="disabled">${requestScope.remarks2}</textarea>
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							财务审核意见
						</td>
						<td style="width:918px; height:40px;">
							<textarea id="remarks3" name="remarks3" rows="2" cols="10" style="width:914px;">${requestScope.remarks3}</textarea>
						</td>
					</tr>
				</table>
				
				<table>
					<tr>
						<td colspan="8" style="padding-top:20px; padding-bottom:20px;">
							<input type="button" value="打印领款单" style="float:left; width:80px;height:25px;margin-left:380px;" onclick="printCost();"/>
							<input type="button" value="领款确认" style="float:left; width:80px;height:25px;margin-left:20px;" onclick="confirmCost();"/>
							<input type="button" value="返回" style="float:left; width:80px;height:25px;margin-left:20px;" onclick="returnList();"/>
						</td>
					</tr>
				</table>
	        </form>
        </div>
  	</div>
  </body>
</html>

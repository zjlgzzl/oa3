<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		var costsGroupCount = -1;
		var cashCount = -1;
		var rateCount = -1;
		var cash2Count = -1;
		var rate2Count = -1;
		var infoCount = -1;
		
		var typeId = null;
		var typeName = null;
		//var costItemId = null;
		//var costItemName = null;
		//var cashItemId = null;
		//var cashItemName = null;
		var empId = null;
		var empName = null;
		var rateId = null;
		var rateName = null;
		
		var fun303 = null;
		var fun205 = null;
		
		var costStr = null;
		var cashStr = null;

		//后台提示
		$(document).ready(function(){
		
			fun303 = "${sessionScope.fun303}";
			fun205 = "${sessionScope.fun205}";
			
			var saleman = "${requestScope.busin.TSalerByCSaleman.CId}";
			if (saleman != null && saleman != ""){
				var select = document.getElementById("saleman");
				for(var j=0;j<select.options.length;j++){
					if(select.options[j].value == saleman){
						select.options[j].selected = true;
						break;
					}
				}
			}
			
			var kf = "${requestScope.busin.TSalerByCKf.CId}";
			if (kf != null && kf != ""){
				var select = document.getElementById("kf");
				for(var j=0;j<select.options.length;j++){
					if(select.options[j].value == kf){
						select.options[j].selected = true;
						break;
					}
				}
			}
			
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
			//costItemId = "${requestScope.costItemId}";
			//costItemId = costItemId.substring(1, costItemId.length - 1).split(", ");
			//costItemName = "${requestScope.costItemName}";
			//costItemName = costItemName.substring(1, costItemName.length - 1).split(", ");
			costStr = "${costStr}";
			
			
			//回款项目
			/*
			cashItemId = "${requestScope.cashItemId}";
			cashItemId = cashItemId.substring(1, cashItemId.length - 1).split(", ");
			cashItemName = "${requestScope.cashItemName}";
			cashItemName = cashItemName.substring(1, cashItemName.length - 1).split(", ");*/
			cashStr = "${cashStr}";
			
			//税率
			rateId = "${requestScope.rateId}";
			rateId = rateId.substring(1, rateId.length - 1).split(", ");
			rateName = "${requestScope.rateName}";
			rateName = rateName.substring(1, rateName.length - 1).split(", ");
			
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
			
			var _count3 = "${requestScope.costsGroupSize}";
			if (Number(_count3) > 0){
				costsGroupCount = Number(_count3) - 1;//标记从0开始
			}
			
			_count3 = "${requestScope.infoCount}";
			if (Number(_count3) > 0){
				infoCount = Number(_count3) - 1;//标记从0开始
			}
			
			//回款数量
			var count4 = "${requestScope.cashCount}";
			if (Number(count4) > 0){
				cashCount = Number(count4) - 1;//标记从0开始
			}
			count4 = "${requestScope.cash2Count}";
			if (Number(count4) > 0){
				cash2Count = Number(count4) - 1;//标记从0开始
			}
		
			//税率数量
			var count5 = "${requestScope.rateCount}";
			if (Number(count5) > 0){
				rateCount = Number(count5) - 1;//标记从0开始
			}
			count5 = "${requestScope.rate2Count}";
			if (Number(count5) > 0){
				rate2Count = Number(count5) - 1;//标记从0开始
			}
			
			//成本信息
			for (var i=0; i<=costCount; i++){
				if (document.getElementById("costItem"+i) != null){
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
					var TUserByCCreateUserid = document.getElementById("userByCCreateUserid"+i).value;
					if (TUserByCCreateUserid != null && TUserByCCreateUserid != ""){
						var select = document.getElementById("TUserByCCreateUserid"+i);
						for(var j=0;j<select.options.length;j++){
							if(select.options[j].value == TUserByCCreateUserid){
								select.options[j].selected = true;
								break;
							}
						}
					}
				}
			}
			
			for (var i=0; i<=costsGroupCount; i++){
				//成本项目组
				if (document.getElementById("costItem2_"+i) != null){
					var costItem = document.getElementById("costItem2_"+i).value;
					if (costItem != null && costItem != ""){
						var select = document.getElementById("TCostItem2_"+i);
						for(var j=0;j<select.options.length;j++){
							if(select.options[j].value == costItem){
								select.options[j].selected = true;
								break;
							}
						}
					}
				}
			}
			
			for (var i=0; i<=infoCount; i++){
				//成本项目组
				if (document.getElementById("_applyBy"+i) != null){
					var applyBy = document.getElementById("_applyBy"+i).value;
					if (applyBy != null && applyBy != ""){
						var select = document.getElementById("applyBy"+i);
						for(var j=0;j<select.options.length;j++){
							if(select.options[j].value == applyBy){
								select.options[j].selected = true;
								break;
							}
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
				computeCash(i);
			}
			
			for (var i=0; i<=cash2Count; i++){
			
				//回款项目
				var cashItem = document.getElementById("cashItem2"+i).value;
				if (cashItem != null && cashItem != ""){
					var select = document.getElementById("TCashItem2"+i);
					for(var j=0;j<select.options.length;j++){
						if(select.options[j].value == cashItem){
							select.options[j].selected = true;
							break;
						}
					}
				}
				
				computeCash2(i);
			}
			
			for (var i=0; i<=rate2Count; i++){
			
				//回款项目
				var rateItem = document.getElementById("rateItem2"+i).value;
				if (rateItem != null && rateItem != ""){
					var select = document.getElementById("TRateItem2"+i);
					for(var j=0;j<select.options.length;j++){
						if(select.options[j].value == rateItem){
							select.options[j].selected = true;
							break;
						}
					}
				}
				
				//税率
				var rate = document.getElementById("rate2"+i).value;
				if (rate != null && rate != ""){
					var select = document.getElementById("Rate2"+i);
					for(var j=0;j<select.options.length;j++){
						if(select.options[j].value == rate){
							select.options[j].selected = true;
							break;
						}
					}
				}
				
				computeRate2(i);
			}
			
			//$("#msg").attr("class", "hidden");
			
			//后台校验错误信息
			var errInfo = "${requestScope.errInfo}";
			if (errInfo != null && errInfo.length > 0){
				alert(errInfo);
			}
			
		});
		
		//处理成本项目和领款人
		function getCostSelect(count){
			var TCostItem = document.getElementById("TCostItem"+count);
			var myobj=eval(costStr);  
			for (var i=0; i<myobj.length; i++){
				TCostItem.options.add(new Option(myobj[i].name, myobj[i].id));
			}
			var TUser = document.getElementById("TUser"+count);
			for (var i=0; i<empId.length; i++){
				TUser.options.add(new Option(empName[i], empId[i])); 
			}
		}
		
		//处理回款项目
		function getCashSelect(count){
			var TCashItem = document.getElementById("TCashItem"+count);
			var myobj=eval(cashStr);
			for(var i=0;i<myobj.length;i++){  
			   TCashItem.options.add(new Option(myobj[i].name, myobj[i].id)); 
			}
		}
		function getCashSelect2(count){
			var TCashItem = document.getElementById("TCashItem2"+count);
			var myobj=eval(cashStr);
			for(var i=0;i<myobj.length;i++){  
			   TCashItem.options.add(new Option(myobj[i].name, myobj[i].id)); 
			}
		}
		
		//处理回款项目和费率
		function getRateSelect(count){
			var TRateItem = document.getElementById("TRateItem"+count);
			var myobj=eval(cashStr);
			for(var i=0;i<myobj.length;i++){  
			   TRateItem.options.add(new Option(myobj[i].name, myobj[i].id)); 
			}
			var Rate = document.getElementById("Rate"+count);
			for (var i=0; i<rateId.length; i++){
				Rate.options.add(new Option(rateName[i], rateId[i])); 
			}
		}
		function getRateSelect2(count){
			var TRateItem = document.getElementById("TRateItem2"+count);
			var myobj=eval(cashStr);
			for(var i=0;i<myobj.length;i++){  
			   TRateItem.options.add(new Option(myobj[i].name, myobj[i].id)); 
			}
			var Rate = document.getElementById("Rate2"+count);
			for (var i=0; i<rateId.length; i++){
				Rate.options.add(new Option(rateName[i], rateId[i])); 
			}
		}
		
		//打印回款单
		function printCash(){
			for(var i=0; i<=cashCount; i++){
				document.getElementById("TCashItem"+i).disabled = false;
				document.getElementById("CMoney2_"+i).disabled = false;
				document.getElementById("TUser2"+i).disabled = false;
			}
			document.getElementById("editForm").action = "${ctx}/busin/PrintCash.action";
			document.getElementById("editForm").submit();
		}
		
		//计算费率金额
		function computeRate(row){
			//价格
			var cmoney = document.getElementById("CMoney3" + row).value;
			if (cmoney == null || cmoney == "" || isNaN(cmoney)){
				cmoney = 0;
			}
			//数量
			var ccount = document.getElementById("RateCount" + row).value;
			if (ccount == null || ccount == "" || isNaN(ccount)){
				ccount = 0;
			}
			if (cmoney == 0 && ccount == 0){
				document.getElementById("CMoney4" + row).value = "";
				document.getElementById("CMoney5" + row).value = "";
				document.getElementById("CMoney6" + row).value = "";
				return;
			}
			//计算金额
			var sumMoney = (Number(cmoney) * Number(ccount)).toFixed(2);
			document.getElementById("CMoney6" + row).value = sumMoney;
			
			//税率
			var selectIndex = document.getElementById("Rate" + row).selectedIndex;
			var rate = document.getElementById("Rate" + row).options[selectIndex].text;
			if (rate == null || rate == 0 || isNaN(rate)){
				document.getElementById("CMoney4" + row).value = "";
				document.getElementById("CMoney5" + row).value = "";
				return;
			}
			//计算税率
			document.getElementById("CMoney4" + row).value = (Number(sumMoney) + Number(sumMoney) * Number(rate) / 100).toFixed(2);
			document.getElementById("CMoney5" + row).value = (Number(sumMoney) * Number(rate) / 100).toFixed(2);
		}
		function computeRate2(row){
			//价格
			var cmoney = document.getElementById("CMoney32" + row).value;
			if (cmoney == null || cmoney == "" || isNaN(cmoney)){
				cmoney = 0;
			}
			//数量
			var ccount = document.getElementById("RateCount2" + row).value;
			if (ccount == null || ccount == "" || isNaN(ccount)){
				ccount = 0;
			}
			if (cmoney == 0 && ccount == 0){
				document.getElementById("CMoney42" + row).value = "";
				document.getElementById("CMoney52" + row).value = "";
				document.getElementById("CMoney62" + row).value = "";
				return;
			}
			//计算金额
			var sumMoney = (Number(cmoney) * Number(ccount)).toFixed(2);
			document.getElementById("CMoney62" + row).value = sumMoney;
			
			//税率
			var selectIndex = document.getElementById("Rate2" + row).selectedIndex;
			var rate = document.getElementById("Rate2" + row).options[selectIndex].text;
			if (rate == null || rate == 0 || isNaN(rate)){
				document.getElementById("CMoney42" + row).value = "";
				document.getElementById("CMoney52" + row).value = "";
				return;
			}
			//计算税率
			document.getElementById("CMoney42" + row).value = (Number(sumMoney) + Number(sumMoney) * Number(rate) / 100).toFixed(2);
			document.getElementById("CMoney52" + row).value = (Number(sumMoney) * Number(rate) / 100).toFixed(2);
		}
		
		function computeCash(row){
			//价格
			var cmoney = document.getElementById("CMoney2_" + row).value;
			if (cmoney == null || cmoney == "" || isNaN(cmoney)){
				cmoney = 0;
			}
			//数量
			var ccount = document.getElementById("CastCount" + row).value;
			if (ccount == null || ccount == "" || isNaN(ccount)){
				ccount = 0;
			}
			if (cmoney == 0 && ccount == 0){
				document.getElementById("CashMoney" + row).value = "";
				return;
			}
			//计算金额
			var sumMoney = (Number(cmoney) * Number(ccount)).toFixed(2);
			document.getElementById("CashMoney" + row).value = sumMoney;
		}
		function computeCash2(row){
			//价格
			var cmoney = document.getElementById("CMoney22_" + row).value;
			if (cmoney == null || cmoney == "" || isNaN(cmoney)){
				cmoney = 0;
			}
			//数量
			var ccount = document.getElementById("CastCount2" + row).value;
			if (ccount == null || ccount == "" || isNaN(ccount)){
				ccount = 0;
			}
			if (cmoney == 0 && ccount == 0){
				document.getElementById("CashMoney2" + row).value = "";
				return;
			}
			//计算金额
			var sumMoney = (Number(cmoney) * Number(ccount)).toFixed(2);
			document.getElementById("CashMoney2" + row).value = sumMoney;
		}

		
		//打印发票
		function printInvoice(){
			document.getElementById("editForm").action = "${ctx}/busin/PrintInvoice.action";
			document.getElementById("editForm").submit();
		}
		//打印含税发票
		function printInvoiceRate(){
			document.getElementById("editForm").action = "${ctx}/busin/PrintInvoiceRate.action";
			document.getElementById("editForm").submit();
		}
		//打印发票
		function printInvoice2(){
			document.getElementById("editForm").action = "${ctx}/busin/PrintInvoice2.action";
			document.getElementById("editForm").submit();
		}
		//打印含税发票
		function printInvoiceRate2(){
			document.getElementById("editForm").action = "${ctx}/busin/PrintInvoiceRate2.action";
			document.getElementById("editForm").submit();
		}
		
		//计算费率金额
		function computeRate(row){
			//价格
			var cmoney = document.getElementById("CMoney3" + row).value;
			if (cmoney == null || cmoney == "" || isNaN(cmoney)){
				cmoney = 0;
			}
			//数量
			var ccount = document.getElementById("RateCount" + row).value;
			if (ccount == null || ccount == "" || isNaN(ccount)){
				ccount = 0;
			}
			if (cmoney == 0 && ccount == 0){
				document.getElementById("CMoney4" + row).value = "";
				document.getElementById("CMoney5" + row).value = "";
				document.getElementById("CMoney6" + row).value = "";
				return;
			}
			//计算金额
			var sumMoney = (Number(cmoney) * Number(ccount)).toFixed(2);
			document.getElementById("CMoney6" + row).value = sumMoney;
			
			//税率
			var selectIndex = document.getElementById("Rate" + row).selectedIndex;
			var rate = document.getElementById("Rate" + row).options[selectIndex].text;
			if (rate == null || rate == 0 || isNaN(rate)){
				document.getElementById("CMoney4" + row).value = "";
				document.getElementById("CMoney5" + row).value = "";
				return;
			}
			//计算税率
			document.getElementById("CMoney4" + row).value = (Number(sumMoney) + Number(sumMoney) * Number(rate) / 100).toFixed(2);
			document.getElementById("CMoney5" + row).value = (Number(sumMoney) * Number(rate) / 100).toFixed(2);
		}
		
		function computeCash(row){
			//价格
			var cmoney = document.getElementById("CMoney2_" + row).value;
			if (cmoney == null || cmoney == "" || isNaN(cmoney)){
				cmoney = 0;
			}
			//数量
			var ccount = document.getElementById("CastCount" + row).value;
			if (ccount == null || ccount == "" || isNaN(ccount)){
				ccount = 0;
			}
			if (cmoney == 0 && ccount == 0){
				document.getElementById("CashMoney" + row).value = "";
				return;
			}
			//计算金额
			var sumMoney = (Number(cmoney) * Number(ccount)).toFixed(2);
			document.getElementById("CashMoney" + row).value = sumMoney;
		}
		
		/*
		function setPrintByGroup(){
			var printFlag = document.getElementsByName("printFlag");
			for (var i=0; i<printFlag.length; i++){
				printFlag[i].checked = false;
			}
			var costGroup = document.getElementById("costGroup").value;
			if (costGroup > 0){
				for (var j=0; j<printFlag.length; j++){
					if (costGroup == document.getElementById("CGroupid"+j).value){
						printFlag[j].checked = true;
					}
				}
			}
		}
		*/
		
		//完结
		function complete6(){
			if (confirm("确认完结?")){
				document.getElementById("archiveFlag").value = 1;
				document.getElementById("editForm").action = "${ctx}/busin/BusinComplete2.action";
				document.getElementById("editForm").submit();
			}
		}
		
		//锁定
		function lockBusin(){
			if (confirm("确认锁定?")){
				var recieveFlag = document.getElementById("recieveFlag").value;
				if (recieveFlag == 0){
					document.getElementById("editForm").action = "${ctx}/busin/ViewBusinLock.action";
				}else{
					document.getElementById("editForm").action = "${ctx}/busin/ViewBusinLock2.action";
				}
				document.getElementById("editForm").submit();
			}
		}
		
		function updateInfo(flag, businId, groupId, rowindex){
			var costInfo = null;
			if (flag==1){
				costInfo = document.getElementById("costDate"+rowindex).value;
			}else if(flag==2){
				costInfo = document.getElementById("applyBy"+rowindex).value;
			}else if(flag==3){
				costInfo = document.getElementById("exchange"+rowindex).value;
			}else if(flag==4){
				costInfo = document.getElementById("refno"+rowindex).value;
			}
			var url="${ctx}/busin/UpdateCostGroupInfo.action?costInfo="+encodeURI(costInfo)+"&businId="+businId+
					"&groupId="+groupId+"&costFlag="+flag;
			$.ajax({
				type:"Post",
				url:url,
				success:function(data){
					if (data != ""){
						if (data == "error"){
							alert("自动更新失败");
						}else{
							document.getElementById("refno"+rowindex).value = data;
						}
					}
					if (flag==3){
						//exchage修改，允许再次标记
						document.getElementById("biaojiBtn"+rowindex).value = "标记";
						document.getElementById("biaojiBtn"+rowindex).disabled = false;
					}
				}
			});
		}
		
		function biaoji(businId,groupId,total,rowindex){
			var url="${ctx}/busin/BusinBiaoji.action?businId="+businId+"&groupId="+groupId+
					"&total="+total;
			$.ajax({
				type:"Post",
				url:url,
				success:function(data){
					if (data != ""){
						alert(data);
					}else{
						document.getElementById("biaojiBtn"+rowindex).value = "已标记";
						document.getElementById("biaojiBtn"+rowindex).disabled = true;
					}
				}
			});
		}
		
		function getProfitByBusinId(){
			var businId = document.getElementById("businId_").value;
			var url="${ctx}/busin/GetProfitByBusinId.action?businId="+businId;
			$.ajax({
				type:"Post",
				url:url,
				success:function(data){
					if (data != ""){
						document.getElementById("profitDiv").innerHTML = Number(data).toFixed(2);
					}
				}
			});
		}
		
	</script>
  </head>
  <body>
  	<div id="mainContent">
  		<div class="right">
			<form id="editForm" action="${ctx}/busin/UpdateBusin.action" method="post">
				<div style="margin-left:5px; width:1000px; text-align:center; margin-top:20px;">
					<span style="color:black; font-size:14px; font-weight:bold;">
							FILE
					</span>
				</div>
				<table class="table" style="margin-top:25px; width:1009px; margin-left:5px; color:black; border-bottom:0px;">
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
									type="text" class="" style="width:146px;border:0px;" onclick="WdatePicker()" disabled="disabled"/>
							<input name="businId" value="${requestScope.busin.CId}" type="hidden" />
							<input type="hidden" id="pageNow2" name="pageNow" value="${pageNow}"/>
							<input type="hidden" id="listFlag2" name="listFlag" value="${listFlag}"/>
							<input type="hidden" id="businCode2" name="businCode" value="${requestScope.businCode}" />
							<input type="hidden" id="businCode22" name="businCode2" value="${requestScope.businCode2}" />
							<input type="hidden" id="typeId2" name="query.typeId" value="${requestScope.query.typeId}" />
							<input type="hidden" id="cusId2" name="query.cusId" value="${requestScope.query.cusId}" />
							<input type="hidden" id="conNum2" name="conNum" value="${requestScope.conNum}" />
							<input type="hidden" id="billNo2" name="billNo" value="${requestScope.billNo}" />
							<input type="hidden" id="empName2" name="query.empName" value="${requestScope.query.empName}" />
							<input type="hidden" id="startDate2" name="startDate" value="${requestScope.startDate}" />
							<input type="hidden" id="endDate2" name="endDate" value="${requestScope.endDate}" />
							<input type="hidden" id="businState2" name="query.businState" value="${requestScope.query.businState}" />
							<input type="hidden" id="businStateName2" name="query.businStateName" value="${requestScope.query.businStateName}" />
							<input type="hidden" id="completeFlag2" name="query.completeFlag" value="${requestScope.query.completeFlag}" />
							<input type="hidden" id="startDate22" name="startDate2" value="${requestScope.startDate2}" />
							<input type="hidden" id="endDate22" name="endDate2" value="${requestScope.endDate2}" />
							<input type="hidden" id="_businId2" name="_businId"  />
						</td>
						<td style="width:100px;">
							<label>TYPE OF FILE</label>
						</td>
						<td style="width:150px;">
							<input type="text" name="busin.TBusinType.CName" value="${requestScope.busin.TBusinType.CName}" 
									class="" style="width:146px;border:0px;" disabled="disabled"/>
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
						<td style="width:100px;" rowspan="2">
							<label>CONSIGNEE</label>
						</td>
						<td style="width:150px;" rowspan="2">
							<input type="text" id="CReceive" name="busin.CReceive" value="${requestScope.busin.CReceive}" 
									class="" style="width:146px;border:0px;" disabled="disabled" />
						</td>
						<td style="width:100px;">
							<label>CLIENT</label>
						</td>
						<td style="width:150px;">
							<input type="text" name="busin.TCustomerByCCusid.CName" value="${requestScope.busin.TCustomerByCCusid.CName}" 
									class="" style="width:146px;border:0px;" disabled="disabled"/>
							<input type="text" name="busin.TCustomerByCCusid.CAddr" value="${requestScope.busin.TCustomerByCCusid.CAddr}" 
									class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
						<td style="width:100px;" rowspan="2">
							<label>BILL NO</label>
						</td>
						<td style="width:150px;" rowspan="2">
							<input type="text" id="CTakeNo" name="busin.CTakeNo" value="${requestScope.busin.CTakeNo}" 
									class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
						<td style="width:100px;" rowspan="2">
							<label>COMMODITIES</label>
						</td>
						<td style="width:150px;" rowspan="2">
							<input type="text" id="CGoodsType" name="busin.CGoodsType" value="${requestScope.busin.CGoodsType}" 
									class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							<label>C/O</label>
						</td>
						<td style="width:150px;">
							<input type="text" name="busin.TCustomerByCCusid2.CName" value="${requestScope.busin.TCustomerByCCusid2.CName}" 
									class="" style="width:146px;border:0px;" disabled="disabled"/>
							<input type="text" name="busin.TCustomerByCCusid2.CAddr" value="${requestScope.busin.TCustomerByCCusid2.CAddr}" 
									class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							<label>APPLY DATE</label>
						</td>
						<td style="width:150px;">
							<input id="appDate" name="appDate" value="${requestScope.appDate}"
									type="text" class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
						<td style="width:100px;">
							<label>SHIPPING LINE</label>
						</td>
						<td style="width:150px;">
							<input type="text" id="shippingLine" value="${requestScope.busin.shippingLine}" 
									class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
						<td style="width:100px;">
							<label>CLEARANCE DATE</label>
						</td>
						<td style="width:150px;">
							<input id="clearDate" name="clearDate" value="${requestScope.clearDate}"
									type="text" class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
						<td style="width:100px;">
							<label>POD</label>
						</td>
						<td style="width:150px;">
							<input type="text" id="CArrivalPort" name="busin.CArrivalPort" value="${requestScope.busin.CArrivalPort}" 
									class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							<label>POL</label>
						</td>
						<td style="width:150px;">
							<input type="text" id="pol" name="busin.pol" value="${requestScope.busin.pol}" 
									class="" style="width:146px;border:0px;" disabled="disabled"/>
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
						<td style="width:100px;">
							<label>Ex-Vessel</label>
						</td>
						<td style="width:150px;">
							<input type="text" id="CVessel" name="busin.CVessel" value="${requestScope.busin.CVessel}" 
									class="" style="border:0px;width:146px;" disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							<label>ETD</label>
						</td>
						<td style="width:150px;">
							<input id="CArrivalDate" name="CArrivalDate" value="${requestScope.CArrivalDate}"
									type="text" class="" style="width:146px;border:0px;" onclick="WdatePicker()" disabled="disabled"/>
						</td>
						<td style="width:100px;">
							<label>ETA</label>
						</td>
						<td style="width:150px;">
							<input id="CArrivalDate2" name="CArrivalDate2" value="${requestScope.CArrivalDate2}"
									type="text" class="" style="width:146px;border:0px;" onclick="WdatePicker()" disabled="disabled"/>
						</td>
						<td style="width:100px;">
							<label>CD No.</label>
						</td>
						<td style="width:150px;">
							<input type="text" id="CCdno" name="busin.CCdno" value="${requestScope.busin.CCdno}" 
									class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
						<td style="width:100px;">
							<label>CD Date</label>
						</td>
						<td style="width:150px;">
							<input id="CCdDate" name="CCdDate" value="${requestScope.CCdDate}"
									type="text" class="" style="border:0px;width:146px" disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							<label>Cost Date</label>
						</td>
						<td style="width:150px;">
							<input id="CCostDate" name="CCostDate" value="${requestScope.CCostDate}"
									type="text" style="border:0px;width:146px" disabled="disabled"/>
						</td>
						<td style="width:100px;">
							<label>Apply By</label>
						</td>
						<td style="width:150px;">
							<input type="text" id="CApplyby" value="${requestScope.busin.TSalerByCApplyby.CName}" 
									disabled="disabled" style="border:0px;width:146px;"/>
						</td>
						<td style="width:100px;">
							<label>Exchange</label>
						</td>
						<td style="width:150px;">
							<fmt:formatNumber type="number" value="${requestScope.busin.CExchange}" pattern="##0" />
							<input type="hidden" id="CExchange" name="busin.CExchange" value="${requestScope.busin.CExchange}" 
									disabled="disabled" style="border:0px;width:146px;"/>
						</td>
						<td style="width:100px;">
							<label>Ref No.</label>
						</td>
						<td style="width:150px;text-align:left;padding-left:5px;">
							${requestScope.busin.CRefno}
						</td>
					</tr>
					<tr>
						<td>
							<label>D.N1</label>
						</td>
						<td>
							<input type="text" id="CNodate1" name="busin.CNodate1" value="${requestScope.busin.CNodate1}" 
									class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
						<td style="width:100px;">
							<label>No-Date(D.N1)</label>
						</td>
						<td style="width:150px;">
							<input id="CNodate2" name="CNodate2" value="${requestScope.CNodate2}"
									type="text" class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
						<td>
							<label>D.N2</label>
						</td>
						<td>
							<input type="text" id="CNodate3" name="busin.CNodate3" value="${requestScope.busin.CNodate3}" 
									class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
						<td style="width:100px;">
							<label>No-Date(D.N2)</label>
						</td>
						<td style="width:150px;">
							<input id="CNodate4" name="CNodate4" value="${requestScope.CNodate4}"
									type="text" class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td>
							<label>INV2</label>
						</td>
						<td>
							<input type="text" id="CNodate7" name="busin.CNodate7" value="${requestScope.busin.CNodate7}" 
									class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
						<td style="width:100px;">
							<label>No-Date(INV2)</label>
						</td>
						<td style="width:150px;">
							<input id="CNodate8" name="CNodate8" value="${requestScope.CNodate8}"
									type="text" class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
						<td></td>
						<td></td>
						<td style="width:100px;">
							<label>付款日期paydate</label>
						</td>
						
						<td style="width:150px;">
							<input id="payDate" name="payDate" value='<fmt:formatDate value="${busin.payDate}" pattern="dd-MM-yyyy"/>' 
									type="text" style="width:146px;border:0px;" onclick="WdatePicker()" disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td>
							<label>业务员 saler</label>
						</td>
						<td>
							<select id="saleman" name="busin.TSaler.CId" class="border" style="width:146px;margin-top:2px;" disabled="disabled">
				           		<option value="0">PLEASE CHOOSE</option>
								<c:forEach var="salerList" items="${requestScope.salerList}">
									<option value="${salerList.CId}">${salerList.CName}</option>
								</c:forEach>
							</select>
						</td>
						<td>
							<label>
								客服
							</label>
						</td>
						<td>
							<select id="kf"  class="border" 
									style="width:146px;margin-top:2px;" disabled="disabled">
				           		<option value="0">PLEASE CHOOSE</option>
								<c:forEach var="salerList" items="${requestScope.salerList}">
									<option value="${salerList.CId}">${salerList.CName}</option>
								</c:forEach>
							</select>
						</td>
						<td>
							<label>
								前程代理
							</label>
						</td>
						<td>
							<input type="text" name="busin.CAgent1" value="${requestScope.busin.CAgent1}" 
									class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
						<td>
							<label>
								后程代理
							</label>
						</td>
						<td>
							<input type="text" name="busin.CAgent2" value="${requestScope.busin.CAgent2}" 
									class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td>
							<label>Container #</label>
						</td>
						<td colspan="3">
							<input type="text" id="CConNum" name="busin.CConNum" value="${requestScope.busin.CConNum}" 
									class="" style="width:400px;border:0px;" disabled="disabled"/>
						</td>
						<td>
							<label>Fty. inv No.</label>
						</td>
						<td>
							<input type="text" id="CFty" name="busin.CFty" value="${requestScope.busin.CFty}" 
									class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
						<td>
							<label>shipment</label>
						</td>
						<td>
							<input type="text" id="CShipment" name="busin.CShipment" value="${requestScope.busin.CShipment}" 
									class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td>
							<label>CBM:</label>
						</td>
						<td colspan="3">
							<input type="text" id="CCbm" name="busin.CCbm" value="${requestScope.busin.CCbm}" 
									class="" style="width:400px;border:0px;" disabled="disabled"/>
						</td>
						<td>
							<label>PKG</label>
						</td>
						<td>
							<input type="text" id="CPkg" name="busin.CPkg" value="${requestScope.busin.CPkg}" 
									class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
						<td>
							<label>weight</label>
						</td>
						<td>
							<input type="text" id="CWeight" name="busin.CWeight" value="${requestScope.busin.CWeight}" 
									class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td>
							<label>Description</label>
						</td>
						<td>
							<input type="text" id="CDescription" name="busin.CDescription" value="${requestScope.busin.CDescription}" 
									class="" style="width:146px;border:0px;" disabled="disabled" />
						</td>
						<td>
							<label>Q'ty of Truck</label>
						</td>
						<td>
							<input type="text" id="CQtyOfTruck" name="busin.CQtyOfTruck" value="${requestScope.busin.CQtyOfTruck}" 
									class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
						<td>
							<label>Q'ty of Dox</label>
						</td>
						<td>
							<input type="text" id="CQtyOfDox" name="busin.CQtyOfDox" value="${requestScope.busin.CQtyOfDox}" 
									class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="8" style="text-align:left;" >
							<span style="color:blue; font-weight:bold; padding-left:20px;">CONTAINER INFO</span>
						</td>
					</tr>
				</table>
				<table id="conTable" class="table" style="margin-top:0px; width:1009px; margin-left:5px; color:black; border-top:0px;">
					<tbody id="con">
						<tr id="conTR0" style="border-top:0px;">
							<td style="width:100px; border-top:0px;">
								<label>CONTAINER NO</label>
							</td>
							<td style="width:100px; border-top:0px;">
								<input type="text" id="conNum0" name="cons[0].CContainerNum" 
										value="${requestScope.cons[0].CContainerNum}"
										class="" style="width:96px;border:0px;" disabled="disabled"/>
								<!-- edit -->
								<input type="hidden" name="cons[0].CId" value="${cons[0].CId}" />
								<input type="hidden" name="cons[0].TBusin.CId" value="${cons[0].TBusin.CId}" />
							</td>
							<td style="width:40px; border-top:0px;">
								<label>TYPE</label>
							</td>
							<td style="width:80px; border-top:0px;">
								<input type="text" id="conType0" name="cons[0].CContainerType" 
										value="${requestScope.cons[0].CContainerType}"
										class="" style="width:76px;border:0px;" disabled="disabled"/>
							</td>
							<td style="width:90px; border-top:0px;">
								<label>Pkgs/Weight</label>
							</td>
							<td style="width:132px; border-top:0px;">
								<input type="text" id="CCount0"  
										value="${requestScope.cons[0].CCount}"
										class="" style="width:128px;border:0px;" disabled="disabled"/>
							</td>
							<td style="width:30px; border-top:0px;">
								<label>REM</label>
							</td>
							<td style="width:60px; border-top:0px;">
								<s:if test="#session.fun203 == 1">
									<input type="text" id="CRemarks0"
											value="${requestScope.cons[0].CRemarks}"
											class="" style="width:56px;border:0px;" disabled="disabled"/>
								</s:if>
							</td>
							<td style="width:30px; border-top:0px;">
								<label>NT</label>
							</td>
							<td style="width:60px; border-top:0px;">
								<s:if test="#session.fun207 == 1">
									<input type="text"
											value="${requestScope.cons[0].CRemarks2}"
											class="" style="width:56px;border:0px;" disabled="disabled"/>
								</s:if>
							</td>
							<td style="width:60px; border-top:0px;">
								<label>TRUCKER</label>
							</td>
							<td style="width:125px; border-top:0px;">
								<input type="text" id="CTrucker0"
										value="${requestScope.cons[0].CTrucker}"
										class="" style="width:121px;border:0px;" disabled="disabled"/>
							</td>
							<td style="width:78px; border-top:0px;">
							</td>
						</tr>
						<s:iterator value="#request.cons" var="cons" status="num">
							<s:if test="#num.index > 0">
								<tr id="conTR<s:property value="#num.index" />">
									<td style="width:100px; border-top:0px;">
									</td>
									<td style="width:100px; border-top:0px;">
										<input type="text" id="conNum<s:property value="#num.index" />" 
												name="cons[<s:property value="#num.index" />].CContainerNum" 
												value="${requestScope.cons[num.index].CContainerNum}"
												class="" style="width:96px;border:0px;" disabled="disabled"/>
										<!-- edit -->
										<input type="hidden" name="cons[<s:property value="#num.index" />].CId" value="${cons[num.index].CId}" />
										<input type="hidden" name="cons[<s:property value="#num.index" />].TBusin.CId" value="${cons[num.index].TBusin.CId}" />
									</td>
									<td style="width:40px; border-top:0px;">
									</td>
									<td style="width:80px; border-top:0px;">
										<input type="text" id="conType<s:property value="#num.index" />" 
												name="cons[<s:property value="#num.index" />].CContainerType" 
												value="${requestScope.cons[num.index].CContainerType}"
												class="" style="width:76px;border:0px;" disabled="disabled"/>
									</td>
									<td style="width:90px; border-top:0px;">
									</td>
									<td style="width:132px; border-top:0px;">
										<input type="text" id="CCount<s:property value="#num.index" />"  
												value="${requestScope.cons[num.index].CCount}"
												class="" style="width:128px;border:0px;" disabled="disabled"/>
									</td>
									<td style="width:30px; border-top:0px;">
									</td>
									<td style="width:60px; border-top:0px;">
										<s:if test="#session.fun203 == 1">
											<input type="text" id="CRemarks<s:property value="#num.index" />" 
														value="${requestScope.cons[num.index].CRemarks}"
														class="" style="width:56px;border:0px;" disabled="disabled"/>
										</s:if>
									</td>
									<td style="width:30px; border-top:0px;">
									</td>
									<td style="width:60px; border-top:0px;">
										<s:if test="#session.fun207 == 1">
											<input type="text"
														value="${requestScope.cons[num.index].CRemarks2}"
														class="" style="width:56px;border:0px;" 
														disabled="disabled"/>
										</s:if>
									</td>
									<td style="width:60px; border-top:0px;">
									</td>
									<td style="width:125px; border-top:0px;">
										<input type="text" id="CTrucker<s:property value="#num.index" />"
												value="${requestScope.cons[num.index].CTrucker}"
												class="" style="width:121px;border:0px;" disabled="disabled"/>
									</td>
									<td style="width:78px; border-top:0px;">
									</td>
								</tr>
									
							</s:if>
						</s:iterator>
					</tbody>
				</table>
				
				<s:if test="#request.busin.CComplete==0 || (#request.busin.CComplete==1 && #session.fun231==1)">
					<table class="table" style="margin-top:10px; width:1500px; margin-left:5px;color:black;border-bottom:0px;">
						<tr>
							<td colspan="8" style="text-align:left;border-top:0px;border-bottom:0px;" >
								<span style="color:blue; font-weight:bold; padding-left:20px;">COST INFO</span>
								<!--  
								<select id="costGroup" class="border _select" style="width:226px;margin-left:20px;"
										onchange="setPrintByGroup();">
									<option value="0">PLEASE CHOOSE GROUP</option>
									<c:forEach var="costGroup" items="${requestScope.costGroup}">
										<option value="${costGroup.CId}">${costGroup.CName}</option>
									</c:forEach>
								</select>
								-->
							</td>
						</tr>
					</table>
					<table id="costTable" class="table" style="margin-top:0px; width:1500px; margin-left:5px; color:black;">
						<tr id="costTR0">
							<td style="width:270px;">
								<select id="TCostItem0" 
										name="costs[0].TCostItem.CId"
										class="" style="width:266px;border:0px;" disabled="disabled">
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
								<input type="hidden" name="costs[0].TCostGroup.CId" value="${costs[0].TCostGroup.CId}" />
							</td>
							<td style="width:14px;">
								<label>$</label>
							</td>
							<td style="width:80px;">
								<input type="text" id="CMoney0" 
										name="costs[0].CMoney" 
										value="${requestScope.costs[0].CMoney}"
										class="" style="width:76px;border:0px;" readonly="readonly"/>
							</td>
							<td style="width:34px;">
								<label>Riel</label>
							</td>
							<td style="width:80px;">
								<input type="text" id="CRiel0" 
										name="costs[0].CRiel"
										value="${requestScope.costs[0].CRiel}"
										class="" style="width:76px;border:0px;" readonly="readonly"/>
							</td>
							<td style="width:20px;">
								<label>RE</label>
							</td>
							<td style="width:137px;">
								<input type="text" id="CRe0"
										value="${requestScope.costs[0].CRe}"
										class="" style="width:133px;border:0px;" readonly="readonly"/>
							</td>
							<td style="width:50px;">
								<label>应付对象</label>
							</td>
							<td style="width:117px;">
								<input type="text" id="agent0" 
										name="costs[0].agent"
										value="${requestScope.costs[0].agent}"
										class="" style="width:113px;border:0px;" readonly="readonly"/>
							</td>
							<td style="width:50px;">
							<label>应付时间</label>
							</td>
							<td style="width:90px;">
								<input id="payDate0" name="costs[0].payDate" value='<fmt:formatDate value="${costs[0].payDate }" pattern="dd-MM-yyyy"/>'
										type="text" class="" style="width:80px;border:0px;" readonly="readonly"/>
							</td>
							<td style="width:35px;">
								<label>出纳1</label>
							</td>
							<td style="width:57px;">
								<s:if test="#session.fun205 == 1">
									<input type="text" id="CRemarks0" 
											name="costs[0].CRemarks" 
											value="${requestScope.costs[0].CRemarks}"
											class="" style="width:53px;border:0px;" disabled="disabled"/>
								</s:if>
							</td>
							<td style="width:35px;">
								<label>出纳2</label>
							</td>
							<td style="width:57px;">
								<s:if test="#session.fun208 == 1">
									<input type="text" 
											name="costs[0].CRemarks2" 
											value="${requestScope.costs[0].CRemarks2}"
											class="" style="width:53px;border:0px;" disabled="disabled"/>
								</s:if>
							</td>
							<td style="width:28px;">
								<label>审核</label>
							</td>
							<td style="width:57px;">
								<s:if test="#session.fun224 == 1">
									<input type="text" 
											name="costs[0].CRemarks5" 
											value="${requestScope.costs[0].CRemarks5}"
											class="" style="width:53px;border:0px;" disabled="disabled"/>
								</s:if>
							</td>
							<td style="width:28px;">
								<label>备注</label>
							</td>
							<td style="width:73px;">
								<s:if test="#session.fun217 == 1">
									<input type="text"
											name="costs[0].CRemarks4"
											value="${requestScope.costs[0].CRemarks4}"
											style="width:69px;border:0px;" disabled="disabled"/>
								</s:if>
							</td>
							<td style="width:91px;">
								<select id="TUserByCCreateUserid0"
										name="costs[0].TUserByCCreateUserid.CId"
										class="" style="width:60px;border:0px;" disabled="disabled">
									<option value="1">admin</option>
									<c:forEach var="userList" items="${requestScope.userList}">
										<option value="${userList.userid}">${userList.empName}</option>
									</c:forEach>
								</select>
								<input name="printFlag" value="${requestScope.costs[0].CId}" type="checkbox" style="margin-top:5px;"/>
								<input type="hidden" id="userByCCreateUserid0" value="${requestScope.costs[0].TUserByCCreateUserid.CId}" />
							</td>
						</tr>
						<tbody id="cost">
							<s:iterator value="#request.costs" var="costs" status="cnum">
								<s:if test="#cnum.index > 0">
									<tr id="costTR<s:property value="#cnum.index" />">
										<td>
											<select id="TCostItem<s:property value="#cnum.index" />" 
													name="costs[<s:property value="#cnum.index" />].TCostItem.CId"
													class="" style="width:266px;border:0px;" disabled="disabled">
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
											<input type="hidden"
													name="costs[<s:property value="#cnum.index" />].TCostGroup.CId" 
													value="${costs[cnum.index].TCostGroup.CId}" />
										</td>
										<td style="width:60px;">
										</td>
										<td style="width:50px;">
											<input type="text" id="CMoney<s:property value="#cnum.index" />" 
													name="costs[<s:property value="#cnum.index" />].CMoney" 
													value="${requestScope.costs[cnum.index].CMoney}"
													class="" style="width:76px;border:0px;" readonly="readonly"/>
										</td>
										<td>
										</td>
										<td>
											<input type="text" id="CRiel<s:property value="#cnum.index" />"
														value="${requestScope.costs[cnum.index].CRiel}"
														class="" style="width:76px;border:0px;" readonly="readonly"/>
										</td>
										<td>
										</td>
										<td>
											<input type="text" id="CRe<s:property value="#cnum.index" />"
														value="${requestScope.costs[cnum.index].CRe}"
														class="" style="width:133px;border:0px;" readonly="readonly"/>
										</td>
										<td>
										</td>
										<td>
											<input type="text" id="agent<s:property value="#cnum.index" />"
														value="${requestScope.costs[cnum.index].agent}"
														class="" style="width:113px;border:0px;" readonly="readonly"/>
										</td>
										<td>
										</td>
										<td>
											<input type="text" id="payDate<s:property value="#cnum.index" />"
														value="<fmt:formatDate value="${costs[cnum.index].payDate}" pattern="dd-MM-yyyy"/>"
														class="" style="width:80px;border:0px;" readonly="readonly"/>
										</td>
										<td>
										</td>
										<td>
											<s:if test="#session.fun205 == 1">
												<input type="text" id="CRemarks<s:property value="#cnum.index" />" 
														name="costs[<s:property value="#cnum.index" />].CRemarks" 
														value="${requestScope.costs[cnum.index].CRemarks}"
														class="" style="width:53px;border:0px;" disabled="disabled"/>
											</s:if>
										</td>
										<td>
										</td>
										<td style="width:97px;">
											<s:if test="#session.fun208 == 1">
												<input type="text"
														name="costs[<s:property value="#cnum.index" />].CRemarks2" 
														value="${requestScope.costs[cnum.index].CRemarks2}"
														class="" style="width:53px;border:0px;" disabled="disabled"/>
											</s:if>
										</td>
										<td>
										</td>
										<td>
											<s:if test="#session.fun216 == 1">
												<input type="text"
														name="costs[<s:property value="#cnum.index" />].CRemarks3" 
														value="${requestScope.costs[cnum.index].CRemarks3}"
														class="" style="width:53px;border:0px;" disabled="disabled"/>
											</s:if>
										</td>
										<td></td>
										<td>
											<s:if test="#session.fun224 == 1">
												<input type="text"
														name="costs[<s:property value="#cnum.index" />].CRemarks5" 
														value="${requestScope.costs[cnum.index].CRemarks5}"
														class="" style="width:53px;border:0px;" disabled="disabled"/>
											</s:if>
										</td>
										<td></td>
										<td>
											<s:if test="#session.fun217 == 1">
												<input type="text"
														name="costs[<s:property value="#cnum.index" />].CRemarks4" 
														value="${requestScope.costs[cnum.index].CRemarks4}"
														class="" style="width:69px;border:0px;" disabled="disabled"/>
											</s:if>
										</td>
										<td>
											<select id="TUserByCCreateUserid<s:property value="#cnum.index" />" 
													name="costs[<s:property value="#cnum.index" />].TUserByCCreateUserid.CId"
													class="" style="width:60px;border:0px;" disabled="disabled">
												<option value="1">admin</option>
												<c:forEach var="userList" items="${requestScope.userList}">
													<option value="${userList.userid}">${userList.empName}</option>
												</c:forEach>
											</select>
											<input name="printFlag" value="<s:property value="CId"/>" type="checkbox" style="margin-top:5px;"/>
											<input type="hidden" id="userByCCreateUserid<s:property value="#cnum.index" />" value="${requestScope.costs[cnum.index].TUserByCCreateUserid.CId}" />
										</td>
									</tr>
								</s:if>
							</s:iterator>
						</tbody>
					</table>
					
					<table class="table" style="margin-top:10px; width:1340px; margin-left:5px;color:black;border-bottom:0px;">
						<tr>
							<td colspan="8" style="text-align:left;border-top:0px;border-bottom:0px;" >
								<span style="color:blue; font-weight:bold; padding-left:20px;">COST GROUP</span>
								<label style="margin-left:13px;">应付时间</label>
								<input id="payDateStr" name="busin.payDateStr" value='<fmt:formatDate value="${costsGroup[0].payDate }" pattern="dd-MM-yyyy"/>'
										type="text" class="" style="width:80px;margin-left:13px;border:0px;" readonly="readonly"/>
							</td>
						</tr>
					</table>
					<table id="costTable" class="table" style="margin-top:0px; width:1340px; margin-left:5px; color:black;">
						<tr>
							<td style="width:270px;height:0px;border:0px;"></td>
							<td style="width:14px;height:0px;border:0px;"></td>
							<td style="width:80px;height:0px;border:0px;"></td>
							<td style="width:34px;height:0px;border:0px;"></td>
							<td style="width:80px;height:0px;border:0px;"></td>
							<td style="width:20px;height:0px;border:0px;"></td>
							<td style="width:137px;height:0px;border:0px;"></td>
							<td style="width:35px;height:0px;border:0px;"></td>
							<td style="width:57px;height:0px;border:0px;"></td>
							<td style="width:35px;height:0px;border:0px;"></td>
							<td style="width:57px;height:0px;border:0px;"></td>
							<td style="width:28px;height:0px;border:0px;"></td>
							<td style="width:57px;height:0px;border:0px;"></td>
							<td style="width:28px;height:0px;border:0px;"></td>
							<td style="width:57px;height:0px;border:0px;"></td>
							<td style="width:28px;height:0px;border:0px;"></td>
							<td style="width:73px;height:0px;border:0px;"></td>
							<td style="width:38px;height:0px;border:0px;"></td>
							<td style="width:173px;height:0px;border:0px;"></td>
							<!--  
							<td style="width:91px;height:0px;border:0px;"></td>
							-->
						</tr>
						<tbody id="cost">
							<s:iterator value="#request.costsGroup" var="costsGroup" status="cnum">
								<s:if test="#cnum.index >= 0">
									<tr id="costTR<s:property value="#cnum.index" />">
										<td>
											<select id="TCostItem2_<s:property value="#cnum.index" />" 
													name="costsGroup[<s:property value="#cnum.index" />].TCostItem.CId"
													class="" style="width:266px;border:0px;" disabled="disabled">
												<option value="0">PLEASE CHOOSE</option>
												<c:forEach var="costItemList" items="${requestScope.costItemList}">
													<option value="${costItemList.CId}">${costItemList.CName}</option>
												</c:forEach>
											</select>
											<input type="hidden" id="costItem2_<s:property value="#cnum.index" />" 
												   value="${costsGroup[cnum.index].TCostItem.CId}" />
											<!-- edit -->
											<input type="hidden" name="costsGroup[<s:property value="#cnum.index" />].CId" value="${costsGroup[cnum.index].CId}" />
											<input type="hidden" name="costsGroup[<s:property value="#cnum.index" />].TBusin.CId" value="${costsGroup[cnum.index].TBusin.CId}" />
											<input type="hidden" name="costsGroup[<s:property value="#cnum.index" />].CState" value="${costsGroup[cnum.index].CState}" />
											<input type="hidden" id="CGroupid<s:property value="#cnum.index" />" 
													name="costsGroup[<s:property value="#cnum.index" />].TCostGroup.CId" 
													value="${costsGroup[cnum.index].TCostGroup.CId}" />
										</td>
										<td>
											<s:if test="#cnum.index==0">
												$
											</s:if>
										</td>
										<td style="width:50px;">
											<input type="text" id="CMoney<s:property value="#cnum.index" />" 
													name="costsGroup[<s:property value="#cnum.index" />].CMoney" 
													value="${requestScope.costsGroup[cnum.index].CMoney}"
													class="" style="width:76px;border:0px;" readonly="readonly"/>
										</td>
										<td>
											<s:if test="#cnum.index==0">
												Riel
											</s:if>
										</td>
										<td>
											<input type="text" id="CRiel<s:property value="#cnum.index" />"
														value="${requestScope.costsGroup[cnum.index].CRiel}"
														class="" style="width:76px;border:0px;" readonly="readonly"/>
										</td>
										<td>
											<s:if test="#cnum.index==0">
												RE
											</s:if>
										</td>
										<td>
											<input type="text" id="CRe<s:property value="#cnum.index" />"
														value="${requestScope.costsGroup[cnum.index].CRe}"
														class="" style="width:133px;border:0px;" readonly="readonly"/>
										</td>
										<td>
											<s:if test="#cnum.index==0">
												出纳1
											</s:if>
										</td>
										<td>
											<s:if test="#session.fun205 == 1">
												<input type="text" id="CRemarks<s:property value="#cnum.index" />" 
														name="costsGroup[<s:property value="#cnum.index" />].CRemarks" 
														value="${requestScope.costsGroup[cnum.index].CRemarks}"
														class="" style="width:53px;border:0px;" disabled="disabled"/>
											</s:if>
										</td>
										<td>
											<s:if test="#cnum.index==0">
												出纳2
											</s:if>
										</td>
										<td style="width:97px;">
											<s:if test="#session.fun208 == 1">
												<input type="text"
														name="costsGroup[<s:property value="#cnum.index" />].CRemarks2" 
														value="${requestScope.costsGroup[cnum.index].CRemarks2}"
														class="" style="width:53px;border:0px;" disabled="disabled"/>
											</s:if>
										</td>
										<td>
											<s:if test="#cnum.index==0">
												财务
											</s:if>
										</td>
										<td>
											<s:if test="#session.fun216 == 1">
												<input type="text"
														name="costsGroup[<s:property value="#cnum.index" />].CRemarks3" 
														value="${requestScope.costsGroup[cnum.index].CRemarks3}"
														class="" style="width:53px;border:0px;" disabled="disabled"/>
											</s:if>
										</td>
										<td>
											<s:if test="#cnum.index==0">
												审核
											</s:if>
										</td>
										<td>
											<s:if test="#session.fun224 == 1">
												<input type="text"
														name="costsGroup[<s:property value="#cnum.index" />].CRemarks5" 
														value="${requestScope.costsGroup[cnum.index].CRemarks5}"
														class="" style="width:53px;border:0px;" disabled="disabled"/>
											</s:if>
										</td>
										<td>
											<s:if test="#cnum.index==0">
												备注
											</s:if>
										</td>
										<td>
											<s:if test="#session.fun217 == 1">
												<input type="text"
														name="costsGroup[<s:property value="#cnum.index" />].CRemarks4" 
														value="${requestScope.costsGroup[cnum.index].CRemarks4}"
														class="" style="width:69px;border:0px;" disabled="disabled"/>
											</s:if>
										</td>
										<td>
											<s:if test="#cnum.index==0">
												成本组
											</s:if>
										</td>
										<td>
											<input type="text"
													id="groupName<s:property value="#cnum.index" />"
													name="costsGroup[<s:property value="#cnum.index" />].TCostGroup.CName"
													value="${requestScope.costsGroup[cnum.index].TCostGroup.CName}"
													class="field border" style="width:169px;" disabled="disabled"/>
											<input name="groupPrintFlag" value="${requestScope.costsGroup[cnum.index].CId}" type="checkbox" style="margin-top:5px;" class="hidden"/>
										</td>
										<!--  
										<td>
											<select id="TUserByCCreateUserid<s:property value="#cnum.index" />" 
													name="costsGroup[<s:property value="#cnum.index" />].TUserByCCreateUserid.CId"
													class="" style="width:60px;border:0px;" disabled="disabled">
												<option value="1">admin</option>
												<c:forEach var="userList" items="${requestScope.userList}">
													<option value="${userList.userid}">${userList.empName}</option>
												</c:forEach>
											</select>
											<input name="printFlag" value="<s:property value="CId"/>" type="checkbox" style="margin-top:5px;"/>
											<input type="hidden" id="userByCCreateUserid<s:property value="#cnum.index" />" value="${requestScope.costsGroup[cnum.index].TUserByCCreateUserid.CId}" />
										</td>
										-->
									</tr>
								</s:if>
							</s:iterator>
						</tbody>
						<tr>
							<td colspan="19" style="text-align:left;padding-left:10px;font-weight:bold;">
								<s:iterator value="#request.groupSumList" var="groupSumList" status="num_group">
									<label style="margin-top:5px;margin-bottom:5px;">
										<s:property value="id.groupName" />：($)<s:property value="id.sumMoney" /> / (Riel)<s:property value="id.sumRiel" /> / (Total)<s:property value="id.total" />
									</label>
									<s:if test="id.noticeFlag==1">
										<input type="button" id="biaojiBtn<s:property value="#num_group.index" />"
												style="width:60px;height:25px;margin-top:5px;margin-bottom:5px;" value="标记"
												onclick="biaoji(<s:property value="id.businId" />,
																<s:property value="id.groupId" />,
																<s:property value="id.total" />,
																<s:property value="#num_group.index" />);" />
									</s:if>
									<s:else>
										<input type="button" id="biaojiBtn<s:property value="#num_group.index" />"
												style="width:60px;height:25px;margin-top:5px;margin-bottom:5px;" value="已标记"
												onclick="biaoji(<s:property value="id.businId" />,
																<s:property value="id.groupId" />,
																<s:property value="id.total" />,
																<s:property value="#num_group.index" />);" disabled="disabled"/>
									</s:else>
									<br/>
								</s:iterator>
							</td>
						</tr>
					</table>
					
					<table class="table" style="margin-top:10px; width:1200px; margin-left:5px;color:black;border-bottom:0px;">
						<tr>
							<td style="width:1200px;text-align:left;border-top:0px;border-bottom:0px;" >
								<span style="color:blue; font-weight:bold; padding-left:20px;">COST GROUP INFO</span>
							</td>
						</tr>
					</table>
					<table class="table" style="margin-top:0px; width:1200px; margin-left:5px;color:black;">
						<tr>
							<td style="width:400px;">
								成本组名称
							</td>
							<td style="width:145px;">
								Cost Date
							</td>
							<td style="width:200px;">
								Apply By
							</td>
							<td style="width:200px;">
								Exchange
							</td>
							<td style="width:200px;">
								Ref No.
							</td>
							<td style="width:50px;">
							</td>
						</tr>
						<s:iterator value="#request.groupInfoList" var="groupInfoList" status="num">
							<tr>
								<td style="text-align:left;padding-left:5px;">
									${requestScope.groupInfoList[num.index].TCostGroup.CName}
									<input id="costsGroupId<s:property value="#num.index" />"
											type="hidden" value="${requestScope.groupInfoList[num.index].TCostGroup.CId}" />
								</td>
								<td>
									<input id="costDate<s:property value="#num.index" />" 
											value="${requestScope.groupInfoList[num.index].CCostDate_1}"
											type="text" class="border field" style="width:136px;" onclick="WdatePicker()" readonly="readonly"
											onchange="updateInfo(1,<s:property value="CBusinId" />,
																	<s:property value="TCostGroup.CId" />,
																	<s:property value="#num.index" />)"/>
								</td>
								<td>
									<select id="applyBy<s:property value="#num.index" />" class="border field" style="width:186px;"
											onchange="updateInfo(2,<s:property value="CBusinId" />,
																	<s:property value="TCostGroup.CId" />,
																	<s:property value="#num.index" />)">
						           		<option value="0">PLEASE CHOOSE</option>
										<c:forEach var="salerList" items="${requestScope.salerList}">
											<option value="${salerList.CId}">${salerList.CName}</option>
										</c:forEach>
									</select>
									<input id="_applyBy<s:property value="#num.index" />"
											type="hidden" value="${requestScope.groupInfoList[num.index].CApplyBy}" />
											
								</td>
								<td>
									<input id="exchange<s:property value="#num.index" />"
											type="text" class="border field" style="width:186px;"
											value="${requestScope.groupInfoList[num.index].CExchange}"
											onchange="updateInfo(3,<s:property value="CBusinId" />,
																	<s:property value="TCostGroup.CId" />,
																	<s:property value="#num.index" />)"/>
								</td>
								<td>
									<input id="refno<s:property value="#num.index" />"
											type="text" class="" style="width:186px;border:0px;"
											value="${requestScope.groupInfoList[num.index].CRefNo}"
											readonly="readonly"/>
								</td>
								<td>
									<input name="printRadioFlag" value="<s:property value="TCostGroup.CId" />" 
											type="radio" style="margin-top:5px;"/>
								</td>
							</tr>
						</s:iterator>
					</table>
					
				</s:if>
				
				
				<table class="table" style="margin-top:10px; width:1009px; margin-left:5px; color:black;">
					<tr>
						<td colspan="8" style="text-align:left;border-top:0px;" >
							<span style="color:blue; font-weight:bold; padding-left:20px;">Debit Note(发票1)</span>
						</td>
					</tr>
				</table>
				<table id="cashTable" class="table" style="margin-top:0px; width:1009px; margin-left:5px; color:black;border-top:0px;">
					<tbody id="cash">
						<s:iterator value="#request.cash" var="cash" status="anum">
							<tr id="cashTR<s:property value="#anum.index" />">
								<s:if test="#anum.index==0">
									<td style="width:430px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:430px;">
								</s:else>
									<select id="TCashItem<s:property value="#anum.index" />" 
											class="" style="width:426px; border:0px;" disabled="disabled">
										<option value="0">PLEASE CHOOSE</option>
										<c:forEach var="cashItemList" items="${requestScope.cashItemList}">
											<option value="${cashItemList.CId}">${cashItemList.CName}</option>
										</c:forEach>
									</select>
									<input type="hidden"
											name="cash[<s:property value="#anum.index" />].TCostItem.CId"
											value="${requestScope.cash[anum.index].TCostItem.CId}" />
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
											<label>价格</label>
										</s:if>
									</td>
									<td style="width:90px;border-top:0px;">
										<input type="text" id="CMoney2_<s:property value="#anum.index" />" 
													value="${requestScope.cash[anum.index].CMoney}"
													class="" style="width:86px;border:0px;" disabled="disabled"/>
											<input type="hidden"
													name="cash[<s:property value="#anum.index" />].CMoney" 
													value="${requestScope.cash[anum.index].CMoney}" />
									</td>
									
								<s:if test="#anum.index==0">
									<td style="width:60px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:60px;">
								</s:else>
										<s:if test="#anum.index==0">
											<label>数量</label>
										</s:if>
									</td>
								<s:if test="#anum.index==0">
									<td style="width:67px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:67px;">
								</s:else>
										<input type="text" id="CastCount<s:property value="#anum.index" />" 
													value="${requestScope.cash[anum.index].CCount}"
													class="" style="width:63px;border:0px;" disabled="disabled"/>
											<input type="hidden"
													name="cash[<s:property value="#anum.index" />].CCount" 
													value="${requestScope.cash[anum.index].CCount}" />
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
								<input type="text" id="CashMoney<s:property value="#anum.index" />" 
										class="" style="width:86px; border:0px;" disabled="disabled"/>
								</td>
								<s:if test="#anum.index==0">
									<td style="width:143px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:143px;">
								</s:else>
										<input type="hidden" name="cash[<s:property value="#anum.index" />].CId" value="${cash[anum.index].CId}" />
										<input type="hidden" name="cash[<s:property value="#anum.index" />].TBusin.CId" value="${cash[anum.index].TBusin.CId}" />
										<input type="hidden" name="cash[<s:property value="#anum.index" />].CState" value="${cash[anum.index].CState}" />
										<input type="hidden" name="cash[<s:property value="#anum.index" />].TUserByCCreateUserid.CId" 
											value="${cash[anum.index].TUserByCCreateUserid.CId}" />
										<input type="hidden" name="cash[<s:property value="#anum.index" />].CType" value="${cash[anum.index].CType}" />
									</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				
				
				<table class="table" style="margin-top:0px; width:1009px; margin-left:5px; color:black; border-top:0px;">
					<tr>
						<td colspan="8" style="text-align:left;border-top:0px;" >
							<span style="color:blue; font-weight:bold; padding-left:20px;">Re-Imbursement(发票2)</span>
						</td>
					</tr>
				</table>
				<table id="cashTable2" class="table" style="margin-top:0px; width:1009px; margin-left:5px; color:black;border-top:0px;">
					<tbody id="cash2">
						<s:iterator value="#request.cash2" var="cash2" status="anum">
							<tr id="cashTR2<s:property value="#anum.index" />">
								<s:if test="#anum.index==0">
									<td style="width:430px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:430px;">
								</s:else>
									<select id="TCashItem2<s:property value="#anum.index" />" 
											class="" style="width:426px; border:0px;" disabled="disabled">
										<option value="0">PLEASE CHOOSE</option>
										<c:forEach var="cashItemList" items="${requestScope.cashItemList}">
											<option value="${cashItemList.CId}">${cashItemList.CName}</option>
										</c:forEach>
									</select>
									<input type="hidden"
											name="cash2[<s:property value="#anum.index" />].TCostItem.CId"
											value="${requestScope.cash2[anum.index].TCostItem.CId}" />
									<input type="hidden" id="cashItem2<s:property value="#anum.index" />" 
											value="${cash2[anum.index].TCostItem.CId}" />
								</td>
									
								<s:if test="#anum.index==0">
									<td style="width:60px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:60px;">
								</s:else>
										<s:if test="#anum.index==0">
											<label>价格</label>
										</s:if>
									</td>
									<td style="width:90px;border-top:0px;">
										<input type="text" id="CMoney22_<s:property value="#anum.index" />" 
													value="${requestScope.cash2[anum.index].CMoney}"
													class="" style="width:86px;border:0px;" disabled="disabled"/>
											<input type="hidden"
													name="cash2[<s:property value="#anum.index" />].CMoney" 
													value="${requestScope.cash2[anum.index].CMoney}" />
									</td>
									
								<s:if test="#anum.index==0">
									<td style="width:60px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:60px;">
								</s:else>
										<s:if test="#anum.index==0">
											<label>数量</label>
										</s:if>
									</td>
								<s:if test="#anum.index==0">
									<td style="width:67px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:67px;">
								</s:else>
										<input type="text" id="CastCount2<s:property value="#anum.index" />" 
													value="${requestScope.cash2[anum.index].CCount}"
													class="" style="width:63px;border:0px;" disabled="disabled"/>
											<input type="hidden"
													name="cash2[<s:property value="#anum.index" />].CCount" 
													value="${requestScope.cash2[anum.index].CCount}" />
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
								<input type="text" id="CashMoney2<s:property value="#anum.index" />" 
										class="" style="width:86px; border:0px;" disabled="disabled"/>
								</td>
								<s:if test="#anum.index==0">
									<td style="width:143px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:143px;">
								</s:else>
										<input type="hidden" name="cash2[<s:property value="#anum.index" />].CId" value="${cash2[anum.index].CId}" />
										<input type="hidden" name="cash2[<s:property value="#anum.index" />].TBusin.CId" value="${cash2[anum.index].TBusin.CId}" />
										<input type="hidden" name="cash2[<s:property value="#anum.index" />].CState" value="${cash2[anum.index].CState}" />
										<input type="hidden" name="cash2[<s:property value="#anum.index" />].TUserByCCreateUserid.CId" 
											value="${cash2[anum.index].TUserByCCreateUserid.CId}" />
										<input type="hidden" name="cash2[<s:property value="#anum.index" />].CType" value="${cash2[anum.index].CType}" />
									</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				
				
				<table class="table" style="margin-top:0px; width:1009px; margin-left:5px; color:black; border-top:0px;">
					<tr>
						<td colspan="8" style="text-align:left;border-top:0px;" >
							<span style="color:blue; font-weight:bold; padding-left:20px;">Tax Invoice(含税发票)</span>
						</td>
					</tr>
				</table>
				<table id="rateTable" class="table" style="margin-top:0px; width:1009px; margin-left:5px; color:black;border-top:0px;">
					<tbody id="rate2">
						<s:iterator value="#request.rate2" var="rate2" status="rnum">
							<tr id="rateTR2<s:property value="#rnum.index" />">
								<s:if test="#rnum.index==0">
									<td style="width:297px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:297px;">
								</s:else>
									<select id="TRateItem2<s:property value="#rnum.index" />" 
												class="" style="width:293px; border:0px;" disabled="disabled">
											<option value="0">PLEASE CHOOSE</option>
											<c:forEach var="cashItemList" items="${requestScope.cashItemList}">
												<option value="${cashItemList.CId}">${cashItemList.CName}</option>
											</c:forEach>
										</select>
									<input type="hidden"
											name="rate2[<s:property value="#rnum.index" />].TCostItem.CId"
											value="${requestScope.rate2[rnum.index].TCostItem.CId}" />
									<input type="hidden" id="rateItem2<s:property value="#rnum.index" />" 
											value="${rate2[rnum.index].TCostItem.CId}" />
									</td>
									
								<s:if test="#rnum.index==0">
									<td style="width:30px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:30px;">
								</s:else>
										<s:if test="#rnum.index==0">
											<label>价格</label>
										</s:if>
									</td>
								<s:if test="#rnum.index==0">
									<td style="width:90px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:90px;">
								</s:else>
										<input type="text" id="CMoney32<s:property value="#rnum.index" />" 
													value="${requestScope.rate2[rnum.index].CMoney}"
													class="" style="width:86px;border:0px;" disabled="disabled"/>
											<input type="hidden"
													name="rate2[<s:property value="#rnum.index" />].CMoney" 
													value="${requestScope.rate2[rnum.index].CMoney}" />
									</td>
									
								<s:if test="#rnum.index==0">
									<td style="width:30px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:30px;">
								</s:else>
									<s:if test="#rnum.index==0">
										<label>数量</label>
									</s:if>
								</td>
								<s:if test="#rnum.index==0">
									<td style="width:40px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:40px;">
								</s:else>
									<input type="text" id="RateCount2<s:property value="#rnum.index" />" 
													value="${requestScope.rate2[rnum.index].CCount}"
													class="" style="width:36px;border:0px;" disabled="disabled"/>
											<input type="hidden" id="RateCount2<s:property value="#rnum.index" />" 
													name="rate2[<s:property value="#rnum.index" />].CCount" 
													value="${requestScope.rate2[rnum.index].CCount}"
											/>
									</td>
									
								<s:if test="#rnum.index==0">
									<td style="width:30px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:30px;">
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
								<input type="text" id="CMoney62<s:property value="#rnum.index" />" 
										class="" style="width:86px;border:0px;" disabled="disabled"/>
								</td>
									
								<s:if test="#rnum.index==0">
									<td style="width:60px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:60px;">
								</s:else>
									<select id="Rate2<s:property value="#rnum.index" />"
												 class="" style="width:56px;border:0px;" disabled="disabled">
											<option value="0"></option>
											<c:forEach var="rateList" items="${requestScope.rateList}">
												<option value="${rateList.CId}">${rateList.CRate}</option>
											</c:forEach>
										</select>
										<input type="hidden"
												name="rate2[<s:property value="#rnum.index" />].TRate.CId"
												value="${requestScope.rate2[rnum.index].TRate.CId}" />
									<input type="hidden" id="rate2<s:property value="#rnum.index" />" 
												value="${rate2[rnum.index].TRate.CId}" />
									</td>
									
								<s:if test="#rnum.index==0">
									<td style="width:183px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:223px;">
								</s:else>
										<input type="text" id="CMoney52<s:property value="#rnum.index" />"
												class="" style="width:50px;border:0px;" disabled="disabled"/>
										<input type="text" id="CMoney42<s:property value="#rnum.index" />"
												class="" style="width:116px;border:0px;" disabled="disabled"/>
									</td>
									
								<s:if test="#rnum.index==0">
									<td style="width:144px;border-top:0px;">
								</s:if>
								<s:else>
									<td style="width:144px;">
								</s:else>
										<input type="hidden" name="rate2[<s:property value="#rnum.index" />].CId" value="${rate2[rnum.index].CId}" />
										<input type="hidden" name="rate2[<s:property value="#rnum.index" />].TBusin.CId" value="${rate2[rnum.index].TBusin.CId}" />
										<input type="hidden" name="rate2[<s:property value="#rnum.index" />].CState" value="${rate2[rnum.index].CState}" />
										<input type="hidden" name="rate2[<s:property value="#rnum.index" />].TUser.CId" 
												value="${rate2[rnum.index].TUser.CId}" />
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
						<td style="width:918px;">
							<input type="text" id="remarks" name="busin.CRemarks" value="${requestScope.busin.CRemarks}"
									class="field" style="width:914px;border:0px;" disabled="disabled" />
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							业务单备注2
						</td>
						<td style="width:918px;">
							<input type="text" id="remarks3" name="busin.CRemarks3" value="${requestScope.busin.CRemarks3}"
									class="field" style="width:914px;border:0px;" disabled="disabled" />
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							开票提醒
						</td>
						<td style="width:918px;">
							<input type="text" name="busin.CNoticeRemarks" value="${requestScope.busin.CNoticeRemarks}"
										class="field" style="width:914px;border:0px;" disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							DN1 REMARKS
						</td>
						<td style="width:918px;">
							<input type="text" name="busin.CDn1Remarks" value="${requestScope.busin.CDn1Remarks}"
									class="field" style="width:914px;border:0px;" disabled="disabled" />
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							DN2 REMARKS
						</td>
						<td style="width:918px;">
							<input type="text" value="${requestScope.busin.CDnRemarks}"
									class="field" style="width:914px;border:0px;" disabled="disabled" />
							<input type="hidden" name="busin.CDnRemarks"  value="${requestScope.busin.CDnRemarks}"/>
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							INV REMARKS
						</td>
						<td style="width:918px;">
							<input type="text" value="${requestScope.busin.CInvRemarks}"
									class="field" style="width:914px;border:0px;" disabled="disabled" />
							<input type="hidden" name="busin.CInvRemarks"  value="${requestScope.busin.CInvRemarks}"/>
						</td>
					</tr>
				</table>
				
				<label style="float:left;font-weight:bold;padding-top:6px;" id="profitDiv"></label>
				
				<!--  
				<table style="width:1000px;" >
					<tr>
						<td style="padding-top:20px; padding-bottom:20px;float:right;padding-right:50px;">
							<s:if test="#request.listFlag == 6 &&
										 (#request.busin.CComplete==0 || (#request.busin.CComplete==1 && #session.fun231==1))">
								<label style="float:left;font-weight:bold;padding-top:6px;" id="profitDiv">
								</label>
								<input type="button" value="获取利润" 
										style="float:left; width:70px;height:25px;margin-left:280px;" onclick="getProfitByBusinId();" />
							</s:if>
							<s:if test="#request.listFlag == 1 || #request.listFlag == 10">
								<input type="button" value="PRINT FILE" 
										style="float:left; width:80px;height:25px;margin-left:280px;" onclick="printFile();" />
								<input type="button" value="PRINT PAYMENT REQUEST" 
										style="float:left; width:200px;height:25px;margin-left:10px;" onclick="printCost();"/>
							</s:if> 
							<input type="button" value="Print Clearance Cost." 
										style="float:left; width:140px;height:25px;margin-left:10px;" onclick="printCost2();"/>
							<s:else>
								<s:if test="#request.CNodate2 != null && #request.CNodate2 != ''">
									<input type="button" value="Print Debit"  
											style="float:left; width:100px;height:25px;margin-left:10px;" 
											onclick="printInvoiceDue();"/>
								</s:if>
								<s:if test="#request.CNodate4 != null && #request.CNodate4 != ''">
									<input type="button" value="Print Reim"  
											style="float:left; width:100px;height:25px;margin-left:10px;" 
											onclick="printInvoiceDue2();"/>
								</s:if>
								<s:if test="#request.CNodate8 != null && #request.CNodate8 != ''">
									<input type="button" value="Print Inv.2" 
										style="float:left; width:100px;height:25px;margin-left:10px;" 
										onclick="printInvoiceRateDue2();"/>
								</s:if>
							</s:else>
							<s:if test="#request.listFlag == 6 && #request.busin.CComplete==0">
								<s:if test="#session.fun220 == 1">
									<input type="button" value="完 结" 
											style="float:left; width:55px;height:25px;margin-left:10px;" onclick="complete6();"/>
								</s:if>
							</s:if>
							<s:if test="#request.listFlag == 6 && #session.fun306 == 1 && 
										(#request.busin.CLock == null || #request.busin.CLock == 0)">
								<input type="button" value="锁 定" 
										style="float:left; width:55px;height:25px;margin-left:10px;" onclick="lockBusin();"/>
							</s:if>
							<input type="button" value="RETURN" style="float:left; width:65px;height:25px;margin-left:10px;" onclick="returnList();" />
						</td>
					</tr>
				</table>
				-->
				
				<div id="btn">
					<s:if test="#session.fun232 == 1">
						<table class="table" style="margin-top:10px; width:1015px; margin-left:5px; color:black;border-bottom:0px;">
							<tr>
								<td colspan="8" style="text-align:left;border-bottom:0px;" >
									<span style="color:blue; font-weight:bold; padding-left:20px;">操作日志</span>
								</td>
							</tr>
						</table>
						<table id="log" class="table" style="margin-top:0px; width:1000px; margin-left:5px; color:black;border-top:0px;margin-bottom:20px;">
							<tr>
			               		<th style="width:40px;text-align:center;">序号</th>
				               	<th style="width:180px;text-align:center;">操作日期</th>
				               	<th style="width:99px;text-align:center;">操作人</th>
				               	<th style="width:690px;text-align:center;">操作内容</th>
				            </tr>
							<s:iterator value="#request.logList" var="logList" status="logNum">
								<s:if test="#request.busin.CNewCostFlag==1 || newCostFlag==1">
									<tr style="color:red;">
								</s:if>
								<s:else>
									<tr>
								</s:else>
									<td><s:property value="#logNum.index+1" /></td>
									<td><s:date name="cdate" format="dd-MM-yyyy HH:mm:ss"/></td>
									<td><s:property value="empName" /></td>
									<td style="text-align:left;">
										${requestScope.logList[logNum.index].cnote}
									</td>
								</tr>
							</s:iterator>
						</table>
					</s:if>
				</div>
	        </form>
        </div>
  	</div>
  </body>
</html>

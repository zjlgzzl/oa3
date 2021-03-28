<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	<link type="text/css" rel="stylesheet"  href="${ctx}/res/css/combo.select.css"/>
	<script type='text/javascript' src="${ctx}/dwr/interface/serviceItemService.js"></script>
  	<script type='text/javascript' src="${ctx}/dwr/engine.js"></script>
 	<script type='text/javascript' src="${ctx}/dwr/util.js"></script>
	<script type="text/javascript" src="${ctx}/res/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/res/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/res/js/jquery.combo.select2.js"></script>
	<script type="text/javascript" src="${ctx}/res/js/busin.js?v=1.5"></script>
	<script type="text/javascript">
	
		var conCount = 0;
		var serviceCount = 0;
		var typeItemCount = 0;
		var costCount = 0;
		var costsGroupCount = -1;
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
		var fun211 = null;
		var fun217 = null;
		var fun224 = null;
		var fun229 = null;
		var fun228 = null;
		var costStr = null;
		var delCostIds = [];
		var k = 0;
		
		var comboIndex = -1;
		//后台提示
		$(document).ready(function(){
		
			javascript:dwr.engine.setAsync(false);//设置dwr同步
			
			fun202 = "${sessionScope.fun202}";
			fun203 = "${sessionScope.fun203}";
			fun205 = "${sessionScope.fun205}";
			fun207 = "${sessionScope.fun207}";
			fun208 = "${sessionScope.fun208}";
			fun216 = "${sessionScope.fun216}";
			fun211 = "${sessionScope.fun211}";
			fun217 = "${sessionScope.fun217}";
			fun224 = "${sessionScope.fun224}";
			fun229 = "${sessionScope.fun229}";
			
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
			
			var _count3 = "${requestScope.costsGroupSize}";
			if (Number(_count3) > 0){
				costsGroupCount = Number(_count3) - 1;//标记从0开始
			}
			
			//回款数量
			var count4 = "${requestScope.cashCount}";
			if (Number(count4) > 0){
				cashCount = Number(count4) - 1;//标记从0开始
			}
			
			//客户名称
			var TCustomer = "${requestScope.busin.TCustomerByCCusid.CId}";
			if (TCustomer != null && TCustomer != ""){
				var select = document.getElementById("TCustomer");
				for(var i=0;i<select.options.length;i++){
					if(select.options[i].value == TCustomer){
						select.options[i].selected = true;
						break;
					}
				}
				select = document.getElementById("TCustomer2");
				for(var i=0;i<select.options.length;i++){
					if(select.options[i].value == TCustomer){
						select.options[i].selected = true;
						break;
					}
				}
			}
			var TCustomer2 = "${requestScope.busin.TCustomerByCCusid2.CId}";
			if (TCustomer2 != null && TCustomer2 != ""){
				var select = document.getElementById("TCustomer3");
				for(var i=0;i<select.options.length;i++){
					if(select.options[i].value == TCustomer2){
						select.options[i].selected = true;
						break;
					}
				}
				select = document.getElementById("TCustomer4");
				for(var i=0;i<select.options.length;i++){
					if(select.options[i].value == TCustomer2){
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
			
			var CApplyby = "${requestScope.busin.TSalerByCApplyby.CId}";
			if (CApplyby != null && CApplyby != ""){
				var select = document.getElementById("CApplyby");
				for(var j=0;j<select.options.length;j++){
					if(select.options[j].value == CApplyby){
						select.options[j].selected = true;
						break;
					}
				}
			}
			
			for (var i=0; i<=costsGroupCount; i++){
				//成本项目组
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
			
			//后台校验错误信息
			var errInfo = "${requestScope.errInfo}";
			if (errInfo != null && errInfo.length > 0){
				alert(errInfo);
			}
		
			//设置焦点
			//document.getElementById("CDate").focus();
			
			javascript:dwr.engine.setAsync(true);//设置dwr异步
			
			$('._select').comboSelect();
			
		});
		
		function selectClient(flag){
			if (flag==1){
				var TCustomer = document.getElementById("TCustomer").value;
				var select = document.getElementById("TCustomer2");
				for(var i=0;i<select.options.length;i++){
					if(select.options[i].value == TCustomer){
						select.options[i].selected = true;
						document.getElementById("comboIndex2").value = select.options[i].text;
						break;
					}
				}
			}else if(flag==2){
				var TCustomer = document.getElementById("TCustomer2").value;
				var select = document.getElementById("TCustomer");
				for(var i=0;i<select.options.length;i++){
					if(select.options[i].value == TCustomer){
						select.options[i].selected = true;
						break;
					}
				}
			}else if(flag==3){
				var TCustomer = document.getElementById("TCustomer3").value;
				var select = document.getElementById("TCustomer4");
				for(var i=0;i<select.options.length;i++){
					if(select.options[i].value == TCustomer){
						select.options[i].selected = true;
						document.getElementById("comboIndex4").value = select.options[i].text;
						break;
					}
				}
			}else if(flag==4){
				var TCustomer = document.getElementById("TCustomer4").value;
				var select = document.getElementById("TCustomer3");
				for(var i=0;i<select.options.length;i++){
					if(select.options[i].value == TCustomer){
						select.options[i].selected = true;
						break;
					}
				}
			}
		}
		
		//返回
		function returnList(){
			//更新锁定标记
			var businId = document.getElementById("CId").value;
			var url = "${ctx}/busin/UpdateLockFlag.action?businId=" + businId;
			var msg = "";
			$.ajax({
				type:"Post",
				async: false,
				url: url,
				success:function(data){
					if (data != ""){
						msg = data;
					}
				}
			});
			if (msg != ""){
				alert(msg);
				return;
			}
			//防止报错
			var j = Number(costCount) + 1;
			for (var i=0; i<j; i++){
				var TCostItem = document.getElementById("TCostItem"+i);
				if (TCostItem == null){
					continue;
				}
				var CMoney = document.getElementById("CMoney"+i).value;
				if ($.trim(CMoney) != "" && isNaN(CMoney)){
					document.getElementById("CMoney"+i).value = 0;
				}
			}
			document.getElementById("editForm").action = "${ctx}/busin/BusinDetialList.action";
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
			}if(TBusinType == 13){
				if(!$("#CCbm").val()){
					alert("CBM不能为空");
					$("#CCbm").focus();
					return false;
				}
				if(!$("#CPkg").val()){
					alert("PKG不能为空");
					$("#CPkg").focus();
					return false;
				}
				if(!$("#CWeight").val()){
					alert("weight不能为空");
					$("#CWeight").focus();
					return false;
				}
			}
			
			//收货人
			/*
			var CReceive = document.getElementById("CReceive").value;
			if ($.trim(CReceive) == ""){
				alert("收货人不能为空");
				document.getElementById("CReceive").focus();
				return false;
			}*/
			
			/*
			var saleman = document.getElementById("saleman").value;
			if ($.trim(saleman) == "0"){
				alert("业务员 saler不能为空");
				document.getElementById("saleman").focus();
				return false;
			}
			*/
			
			//客户名称
			var TCustomer = document.getElementById("TCustomer").value;
			if (TCustomer == "0"){
				alert("CLIENT不能为空");
				document.getElementById("TCustomer").focus();
				return false;
			}
			//成本
			var j = Number(costCount) + 1;
			for (var i=0; i<=j; i++){
				var TCostItem = document.getElementById("TCostItem"+i);
				if (TCostItem == null){
					continue;
				}
				if (document.getElementById("TCostItem"+i).value == 0){
					var CMoney = document.getElementById("CMoney"+i).value;
					if ($.trim(CMoney) != "" && isNaN(CMoney)){
						alert("$必须是数字");
						document.getElementById("CMoney"+i).focus();
						return false;
					}
				}
				document.getElementById("CMoney"+i).value = $.trim(document.getElementById("CMoney"+i).value)
			}
			for (var i=1; i<=j; i++){
				var TCostItem = document.getElementById("TCostItem"+i);
				if (TCostItem == null){
					continue;
				}
				if (document.getElementById("TCostItem"+i).value == 0){
					alert("成本项目不能为空");
					document.getElementById("TCostItem"+i).focus();
					return false;
				}
			}
			
			//成本组
			var k = Number(costsGroupCount) + 1;
			for (var i=0; i<=k; i++){
				var TCostItem = document.getElementById("TCostItem2_"+i);
				if (TCostItem == null){
					continue;
				}
				if (document.getElementById("TCostItem2_"+i).value == 0){
					alert("成本项目不能为空");
					document.getElementById("TCostItem2_"+i).focus();
					return false;
				}
			}
			
			for (var i = 0; i < costsGroupCount; i++) {
				var CMoney = document.getElementById("CMoney2_"+i).value;
				if ($.trim(CMoney) != "" && isNaN(CMoney)){
					alert("$必须是数字");
					document.getElementById("CMoney2_"+i).focus();
					return false;
				}
				document.getElementById("CMoney2_"+i).value = $.trim(CMoney)
			}
			
			//处理成本信息
			j = Number(costCount) + 1;
			for (var i=0; i<=j; i++){
				if (document.getElementById("TCostItem"+i) != null){
					document.getElementById("TCostItem"+i).disabled = false;
				}
			}
			
			var tb = document.getElementById("costTable");
			var rowcount = tb.rows.length;
			if (rowcount > 0){
				for(var i=0; i<rowcount; i++){
					//设置序号
					var cid = tb.rows[i].id.toString().substring(6);
					document.getElementById("costOrder"+cid).value = i;
				}
			}
			
			document.getElementById("CBillNo").disabled = false;
			document.getElementById("remarks").disabled = false;
			document.getElementById("remarks3").disabled = false;
			document.getElementById("businState").value = 1;
			
			document.getElementById("delCostIds").value = delCostIds;
			
			return true;
		}
		
		//处理成本项目和领款人
		function getCostSelect(count){
			var TCostItem = document.getElementById("TCostItem"+count);
			for (var i=0; i<costItemId.length; i++){
				TCostItem.options.add(new Option(costItemName[i], costItemId[i])); 
			}
			var agent = document.getElementById("agent"+count);
			agent.innerHTML = document.getElementById("agent0").innerHTML
		}
		
		function loadData(count){
			var con = document.getElementById("conType"+count);
			con.innerHTML = document.getElementById("conType0").innerHTML
		}
		
		function getCostSelect2(count){
			var TCostItem = document.getElementById("TCostItem2_"+count);
			for (var i=0; i<costItemId.length; i++){
				TCostItem.options.add(new Option(costItemName[i], costItemId[i])); 
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
		
		/*增加成本*/
		function addCost2(){
			
			var tb = document.getElementById("costsGroupTable");
			var rowcount = tb.rows.length - 1;
			
			//成本数量
			costsGroupCount = Number(costsGroupCount) + 1;
			
			//创建行
			var tr = document.createElement("tr");
			tr.id = "costTR2_" + costsGroupCount.toString();
			
			//成本项目
			/*
			var td1 = document.createElement("td");
			td1.innerHTML = '';
			tr.appendChild(td1);*/
			
			var td2 = document.createElement("td");
			td2.innerHTML = '<select id="TCostItem2_' + costsGroupCount + '"' +  
							'		 name="costsGroup[' + costsGroupCount + '].TCostItem.CId"' +
							'		 class="border _select" style="width:266px;">' +
							'	<option value="0">PLEASE CHOOSE</option>' +
							'</select>' +
							'<input type="hidden" id="costOrder2_' + costsGroupCount + '"' + 
							'		 name="costsGroup[' + costsGroupCount + '].COrder"' + '/>' +
							'<input type="hidden" id="CGroupid2_' + costsGroupCount + '"' + 
							'		 name="costsGroup[' + costsGroupCount + '].TCostGroup.CId"' + '/>';
			tr.appendChild(td2);
			
			//金额
			var td3 = document.createElement("td");
			if (rowcount > 0){
				td3.innerHTML = '';
			}else{
				td3.innerHTML = '$';
			}
			tr.appendChild(td3);
			
			var td4 = document.createElement("td");
			td4.innerHTML = '<input type="text" id="CMoney2_' + costsGroupCount + '" ' +
							'		name="costsGroup[' + costsGroupCount +'].CMoney" ' +
							'		class="field border" style="width:76px;"/>' +
							'<input id="money_2_' + costsGroupCount + '" type="checkbox"' +
							'       style="margin-left:3px;width:15px;height:15px;"' +
							'       onclick="computeTmpMoney2();" />';
			tr.appendChild(td4);
			
			//金额
			td3 = document.createElement("td");
			if (rowcount > 0){
				td3.innerHTML = '';
			}else{
				td3.innerHTML = 'Riel';
			}
			tr.appendChild(td3);
			
			td4 = document.createElement("td");
			td4.innerHTML = '<input type="text" id="CRiel2_' + costsGroupCount + '" ' +
							'		name="costsGroup[' + costsGroupCount +'].CRiel" ' +
							'		class="field border" style="width:86px;"/>';
			tr.appendChild(td4);
			
			//领款人
			var td5 = document.createElement("td");
			if (rowcount > 0){
				td5.innerHTML = '';
			}else{
				td5.innerHTML = 'RE';
			}
			tr.appendChild(td5);
			
			var td6 = document.createElement("td");
			/*
			td6.innerHTML = '<select id="TUser' + costsGroupCount + '"' +  
							'		 name="costs[' + costsGroupCount + '].TUserByCUserid.CId"' +
							'		 class="border" style="width:169px;">' +
							'	<option value="0">PLEASE CHOOSE</option>' +
							'</select>';*/
			td6.innerHTML = '<input type="text" id="CRe2_' + costsGroupCount + '" ' +
							'		name="costsGroup[' + costsGroupCount +'].CRe" ' +
							'		class="field border" style="width:111px;"/>';
			tr.appendChild(td6);
			
			//备注
			var td7 = document.createElement("td");
			if (rowcount > 0){
				td7.innerHTML = '';
			}else{
				td7.innerHTML = '出纳1';
			}
			tr.appendChild(td7);
			
			var td8 = document.createElement("td");
			//if (fun228 == 1 || fun205 == 1){
			if (fun205 == 1 || (fun228 != null && fun228 == 1)){
				td8.innerHTML = '<input type="text" id="CRemarks2_' + costsGroupCount + '" ' +
				'		name="costsGroup[' + costsGroupCount +'].CRemarks" ' +
				'		class="field border" style="width:53px;"/>';
			}else{
				td8.innerHTML = '';
			}
			tr.appendChild(td8);
			
			//备注2
			var td77 = document.createElement("td");
			if (rowcount > 0){
				td77.innerHTML = '';
			}else{
				td77.innerHTML = '出纳2';
			}
			tr.appendChild(td77);
			
			var td88 = document.createElement("td");
			//if (fun228 == 1 || fun208 == 1){
			if (fun208 == 1 || (fun228 != null && fun228 == 1)){
				td88.innerHTML = '<input type="text" ' +
				'		name="costsGroup[' + costsGroupCount +'].CRemarks2" ' +
				'		class="field border" style="width:53px;"/>';
			}else{
				td88.innerHTML = '';
			}
			tr.appendChild(td88);
			
			//备注3
			td77 = document.createElement("td");
			if (rowcount > 0){
				td77.innerHTML = '';
			}else{
				td77.innerHTML = '财务';
			}
			tr.appendChild(td77);
			
			td88 = document.createElement("td");
			//if (fun228 == 1 || fun216 == 1){
			if (fun216 == 1 || (fun228 != null && fun228 == 1)){
				td88.innerHTML = '<input type="text" ' +
				'		name="costsGroup[' + costsGroupCount +'].CRemarks3" ' +
				'		class="field border" style="width:53px;"/>';
			}else{
				td88.innerHTML = '';
			}
			tr.appendChild(td88);
			
			//审核
			td77 = document.createElement("td");
			if (rowcount > 0){
				td77.innerHTML = '';
			}else{
				td77.innerHTML = '审核';
			}
			tr.appendChild(td77);
			
			td88 = document.createElement("td");
			//if (fun228 == 1 || fun224 == 1){
			if (fun224 == 1 || (fun228 != null && fun228 == 1)){
				td88.innerHTML = '<input type="text" ' +
				'		name="costsGroup[' + costsGroupCount +'].CRemarks5" ' +
				'		class="field border" style="width:53px;"/>';
			}else{
				td88.innerHTML = '';
			}
			tr.appendChild(td88);
			
			//备注4
			td77 = document.createElement("td");
			if (rowcount > 0){
				td77.innerHTML = '';
			}else{
				td77.innerHTML = '备注';
			}
			tr.appendChild(td77);
			
			td88 = document.createElement("td");
			//if (fun228 == 1 || fun229 == 1){
			if (fun229 == 1 || (fun228 != null && fun228 == 1)){
				td88.innerHTML = '<input type="text" ' +
				'		name="costsGroup[' + costsGroupCount +'].CRemarks4" ' +
				'		class="field border" style="width:69px;"/>';
			}else{
				td88.innerHTML = '';
			}
			tr.appendChild(td88);
			
			td77 = document.createElement("td");
			if (rowcount > 0){
				td77.innerHTML = '';
			}else{
				td77.innerHTML = '成本组';
			}
			tr.appendChild(td77);
			
			td88 = document.createElement("td");
			td88.innerHTML = '<input type="text" ' +
								'		id="groupName2_' + costsGroupCount + '"' +
								'		name="costsGroup[' + costCount +'].TCostGroup.CName" ' +
								'		class="field border" style="width:169px;" disabled="disabled" />';
			tr.appendChild(td88);
			
			//增加行、删除行
			var td9 = document.createElement("td");
			td9.innerHTML = '<input type="button" value="DEL" style="float:left; margin-left:5px; width:40px; height:20px;"' +
							'		onclick = "delCostInfo2(' + costsGroupCount + ',this);" />';
			tr.appendChild(td9);
			
			document.getElementById("costsGroupTable").appendChild(tr);
			
			getCostSelect2(costsGroupCount);
			
			$('._select').comboSelect();
		}

		function computeTmpMoney2(){
			var sumMoney = 0;
			var j = Number(costsGroupCount) + 1;
			for (var i=0; i<j; i++){
				if (document.getElementById("money_2_"+i) != null && document.getElementById("money_2_"+i).checked){
					sumMoney = Number(sumMoney) + Number(document.getElementById("CMoney2_"+i).value);
				}
			}
			document.getElementById("costTmpSumMoney2").innerHTML = "成本总计：" + sumMoney.toFixed(2);
		}
		
		/*删除成本*/
		function delCostInfo2(num, obj){
			if (confirm("确认删除？")){
				var costsId = document.getElementById("costsId2_"+num);
				if (costsId != null){
					var cid = document.getElementById("costsId2_"+num).value;
					if (cid != null && cid > 0){
						delCostIds[k] = cid;
						k++;
					}
				}
				var tr = obj.parentNode.parentNode;
				var tbody = tr.parentNode;
				tbody.removeChild(tr);
			}
		}

		//选择开票项目组
		function addCostGroup(){
			var groupId = document.getElementById("costGroup").value;
			if (groupId == 0){
				alert("请选择成本项目组");
				document.getElementById("costGroup").focus();
				return;
			}
			var url = "${ctx}/busin/GetCostGroupItemsByGroupId.action?cgroupId="+groupId;
			$.ajax({
				type:"Post",
				url: url,
				async: false,
				contentType: "application/x-www-form-urlencoded;charset=UTF-8",
				success:function(data){
					if (data != null){
						var myobj=eval(data);
						if (myobj.length > 0){
							for (var i=0; i<myobj.length; i++){
								addCost2();
								document.getElementById("TCostItem2_"+costsGroupCount).value = myobj[i].itemId;
								document.getElementById("CGroupid2_"+costsGroupCount).value = groupId;
								document.getElementById("groupName2_"+costsGroupCount).value = myobj[i].groupName;
							}
							document.getElementById("CMoney2_"+costsGroupCount).focus();
							$('._select').comboSelect();
						}
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
				<div style="margin-left:5px; width:1025px; text-align:center; margin-top:50px;">
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
				<table id="mainTable" class="table" style="margin-top:0px; width:1025px; margin-left:5px; color:black;">
					<tr>
						<td style="width:100px;">
							<label>ISSUE DATE</label>
						</td>
						<td style="width:150px;">
							<!--  
							<s:if test="#session.userID == busin.TUser.CId">
								<input id="CDate" name="cdate" value="${requestScope.cdate}"
										type="text" class="field border" style="width:146px" onclick="WdatePicker()" readonly="readonly"/>
							</s:if>
							<s:else>
								<input value="${requestScope.cdate}" type="text" 
										class="" style="width:146px;border:0px;" disabled="disabled"/>
								<input id="CDate" name="cdate" value="${requestScope.cdate}" type="hidden"/>
							</s:else>
							-->
							<s:if test="#session.fun240 == 1">
								<input value="${requestScope.cdate}" type="text" 
											class="field border" style="width:146px;border:0px;" onclick="WdatePicker()" readonly="readonly"/>
								<input id="CDate" name="cdate" value="${requestScope.cdate}" type="hidden"/>
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
							<input name="busin.COrder" value="${requestScope.busin.COrder}" type="hidden" />
							<input name="busin.CScheduleArchive" value="${requestScope.busin.CScheduleArchive}" type="hidden" />
							<input name="busin.CRateArchive" value="${requestScope.busin.CRateArchive}" type="hidden" />
							<input name="busin.CLock" value="${requestScope.busin.CLock}" type="hidden" />
							<input name="busin.CLastDate2" value="${requestScope.busin.CLastDate2}" type="hidden" />
							<input name="busin.CPickupStatus" value="${requestScope.busin.CPickupStatus}" type="hidden" />
							
							<input type="hidden" id="pageNow" name="pageNow" value="${pageNow}"/>
							<input type="hidden" id="listFlag" name="listFlag" value="${listFlag}"/>
							<input type="hidden" name="businCode" value="${requestScope.businCode}" />
							<input type="hidden" name="businCode2" value="${requestScope.businCode2}" />
							<input type="hidden" name="query.typeId" value="${requestScope.query.typeId}" />
							<input type="hidden" name="query.cusId" value="${requestScope.query.cusId}" />
							<input type="hidden" name="conNum" value="${requestScope.conNum}" />
							<input type="hidden" name="billNo" value="${requestScope.billNo}" />
							<input type="hidden" name="query.empName" value="${requestScope.query.empName}" />
							<input type="hidden" name="startDate" value="${requestScope.startDate}" />
							<input type="hidden" name="endDate" value="${requestScope.endDate}" />
							<input type="hidden" name="query.businState" value="${requestScope.query.businState}" />
							<input type="hidden" name="query.businStateName" value="${requestScope.query.businStateName}" />
							<input type="hidden" name="query.completeFlag" value="${requestScope.query.completeFlag}" />
							<input type="hidden" name="startDate2" value="${requestScope.startDate2}" />
							<input type="hidden" name="endDate2" value="${requestScope.endDate2}" />
							<input id="businId" name="businId" value="${requestScope.busin.CId}" type="hidden" />
							<input name="_businId" value="${requestScope._businId}" type="hidden" />
							
							<!-- 删除的id -->
							<input id="delCostIds" name="delCost" type="hidden" />
							<input name="busin.CRecieveMoney" value="${requestScope.busin.CRecieveMoney}" type="hidden" />
							<input name="busin.CRecieveMoney1" value="${requestScope.busin.CRecieveMoney1}" type="hidden" />
							<input name="busin.CRecieveMoney2" value="${requestScope.busin.CRecieveMoney2}" type="hidden" />
							<input name="busin.CRecieveMoney3" value="${requestScope.busin.CRecieveMoney3}" type="hidden" />
							<input name="busin.CRecieveMoney4" value="${requestScope.busin.CRecieveMoney4}" type="hidden" />
							<input name="busin.CArchiveRemarks" value="${requestScope.busin.CArchiveRemarks}" type="hidden" />
							<input name="busin.CRecieveRemarks" value="${requestScope.busin.CRecieveRemarks}" type="hidden" />
							<input name="busin.CRecieveRemarks2" value="${requestScope.busin.CRecieveRemarks2}" type="hidden" />
							<input name="busin.CComplete" value="${requestScope.busin.CComplete}" type="hidden"/>
							<input name="busin.CFileRemarks" value="${requestScope.busin.CFileRemarks}" type="hidden" />
							<input id="recieveFlag" name="busin.CRecieveFlag" value="${requestScope.busin.CRecieveFlag}" type="hidden" />
							<input name="busin.CRecieveRemarks3" value="${requestScope.busin.CRecieveRemarks3}" type="hidden" />
							<input name="busin.CKaipiaoRemarks" value="${requestScope.busin.CKaipiaoRemarks}" type="hidden" />
							
							
							
							<input name="busin.CNewCostFlag" value="${requestScope.busin.CNewCostFlag}" type="hidden" />
							
							<input type="hidden" name="editDatetime" value="${editDatetime}"/>
							
							<input type="hidden" name="busin.CDescription" value="${requestScope.busin.CDescription}" />
							<input type="hidden" name="busin.CQtyOfTruck" value="${requestScope.busin.CQtyOfTruck}" />
							<input type="hidden" name="busin.CQtyOfDox" value="${requestScope.busin.CQtyOfDox}" />
							
							<input name="busin.CPayComplete" value="${requestScope.busin.CPayComplete}" type="hidden" />
							
							<input type="hidden" name="busin.CDepositAmount" value="${requestScope.busin.CDepositAmount}" />
							<input type="hidden" name="busin.CCashReceived" value="${requestScope.busin.CCashReceived}" />
							<input type="hidden" name="busin.CAcNotice" value="${requestScope.busin.CAcNotice}" />
							
							<input type="hidden" name="busin.CNoticeLast" value="${requestScope.busin.CNoticeLast}" />
							<input type="hidden" name="busin.CNoticeLastUserid" value="${requestScope.busin.CNoticeLastUserid}" />
							<input type="hidden" name="busin.CAddGroup" value="${requestScope.busin.CAddGroup}" />
							<input type="hidden" name="busin.CNoticeLastUserid2" value="${requestScope.busin.CNoticeLastUserid2}" />
							
						</td>
						<td style="width:100px;">
							<label>TYPE OF FILE</label>
						</td>
						<td style="width:150px;">
							<s:if test="#session.fun240 == 1">
								<select id="TBusinType" name="busin.TBusinType.CId" class="border field" style="width:146px;">
					           		<option value="0">PLEASE CHOOSE</option>
									<c:forEach var="businTypeList" items="${requestScope.businTypeList}">
										<option value="${businTypeList.CId}">${businTypeList.CName}</option>
									</c:forEach>
								</select>
							</s:if>
							<s:elseif test="#session.fun214 == 1">
								<select id="TBusinType" name="busin.TBusinType.CId" class="border field" style="width:146px;">
					           		<option value="0">PLEASE CHOOSE</option>
									<c:forEach var="businTypeList" items="${requestScope.businTypeList}">
										<option value="${businTypeList.CId}">${businTypeList.CName}</option>
									</c:forEach>
								</select>
							</s:elseif>
							<s:else>
								<select id="TBusinType" class="" style="width:146px;border:0px;" disabled="disabled">
					           		<option value="0">PLEASE CHOOSE</option>
									<c:forEach var="businTypeList" items="${requestScope.businTypeList}">
										<option value="${businTypeList.CId}">${businTypeList.CName}</option>
									</c:forEach>
								</select>
								<input type="hidden" name="busin.TBusinType.CId" value="${busin.TBusinType.CId}" />
							</s:else>
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
							<s:if test="#session.fun240 == 1">
								<input type="text" id="CSend" name="busin.CSend" value="${requestScope.busin.CSend}" 
										class="field border" style="width:146px;"/>
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
						<td style="width:100px;" rowspan="2">
							<label>CONSIGNEE</label>
						</td>
						<td style="width:150px;" rowspan="2">
							<s:if test="#session.fun240 == 1">
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
						<td style="width:150px;padding-top:3px;padding-bottom:3px;">
							<s:if test="#session.fun240 == 1">
								<select id="TCustomer" name="busin.TCustomerByCCusid.CId" class="border" style="width:146px;" onchange="selectClient(1);">
					           		<option value="0">PLEASE CHOOSE</option>
									<c:forEach var="cusList" items="${requestScope.cusList}">
										<option value="${cusList.CId}">${cusList.CName}</option>
									</c:forEach>
								</select>
								<select id="TCustomer2" class="border"
										style="width:146px;margin-top:2px;border:1px solid lightblue;margin-left:2.5px;" 
										onchange="selectClient(2);">
					           		<option value="0">PLEASE CHOOSE</option>
									<c:forEach var="cusList" items="${requestScope.cusList}">
										<option value="${cusList.CId}">${cusList.CAddr}</option>
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
								<select id="TCustomer2" style="width:146px;border:0px;" disabled="disabled">
					           		<option value="0">PLEASE CHOOSE</option>
									<c:forEach var="cusList" items="${requestScope.cusList}">
										<option value="${cusList.CId}">${cusList.CAddr}</option>
									</c:forEach>
								</select>
								<input type="hidden" name="busin.TCustomerByCCusid.CId" value="${requestScope.busin.TCustomerByCCusid.CId}" />
							</s:else>
						</td>
						<td style="width:100px;" rowspan="2">
							<label>BILL NO</label>
						</td>
						<td style="width:150px;" rowspan="2">
							<s:if test="#session.fun240 == 1">
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
						<td style="width:100px;" rowspan="2">
							<label>COMMODITIES</label>
						</td>
						<td style="width:150px;" rowspan="2">
							<s:if test="#session.fun240 == 1">
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
							<label>C/O</label>
						</td>
						<td style="width:150px;padding-top:3px;padding-bottom:3px;">
							<s:if test="#session.fun240 == 1">
								<select id="TCustomer3" name="busin.TCustomerByCCusid2.CId" class="border" style="width:146px;" onchange="selectClient(3);">
					           		<option value="0">PLEASE CHOOSE</option>
									<c:forEach var="cusList" items="${requestScope.cusList}">
										<option value="${cusList.CId}">${cusList.CName}</option>
									</c:forEach>
								</select>
								<select id="TCustomer4" class="border" 
										style="width:146px;margin-top:2px;border:1px solid lightblue;margin-left:2.5px;" 
										onchange="selectClient(4);">
					           		<option value="0">PLEASE CHOOSE</option>
									<c:forEach var="cusList" items="${requestScope.cusList}">
										<option value="${cusList.CId}">${cusList.CAddr}</option>
									</c:forEach>
								</select>
							</s:if>
							<s:else>
								<select id="TCustomer3" class="" style="width:146px; border:0px;" disabled="disabled">
					           		<option value="0">PLEASE CHOOSE</option>
									<c:forEach var="cusList" items="${requestScope.cusList}">
										<option value="${cusList.CId}">${cusList.CName}</option>
									</c:forEach>
								</select>
								<select id="TCustomer4" style="width:146px;border:0px;" disabled="disabled">
					           		<option value="0">PLEASE CHOOSE</option>
									<c:forEach var="cusList" items="${requestScope.cusList}">
										<option value="${cusList.CId}">${cusList.CAddr}</option>
									</c:forEach>
								</select>
								<input type="hidden" name="busin.TCustomerByCCusid2.CId" value="${requestScope.busin.TCustomerByCCusid2.CId}" />
							</s:else>
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							<label>APPLY DATE</label>
						</td>
						<td style="width:150px;">
							<s:if test="#session.fun240 == 1">
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
							<s:if test="#session.fun240 == 1">
								<select id="shippingLine" name="busin.shippingLine" class="field border" style="width:146px;">
									<option value="">PLEASE CHOOSE</option>
									<c:forEach items="${shippingLineList }" var="one">
										<option value="${one.name }" <c:if test="${requestScope.busin.shippingLine == one.name}">selected</c:if>>${one.name }</option>
									</c:forEach>
								</select>
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
							<s:if test="#session.fun240 == 1">
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
							<label>POD</label>
						</td>
						<td style="width:150px;">
							<s:if test="#session.fun240 == 1">
								<select id="CArrivalPort" name="busin.CArrivalPort" class="field border" style="width:146px;">
								<option value="">PLEASE CHOOSE</option>
								<c:forEach items="${portList }" var="port">
									<option value="${port.name }" <c:if test="${requestScope.busin.CArrivalPort == port.name}">selected</c:if>>${port.name }</option>
								</c:forEach>
							</select>						
							</s:if>
							<s:else>
								<select id="CArrivalPort" name="busin.CArrivalPort"  style="width:146px;border:0px;" disabled="disabled">
									<option value="">PLEASE CHOOSE</option>
									<c:forEach items="${portList }" var="port">
										<option value="${port.name }" <c:if test="${requestScope.busin.CArrivalPort == port.name}">selected</c:if>>${port.name }</option>
									</c:forEach>
								</select>
							</s:else>
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							<label>POL</label>
						</td>
						<td style="width:150px;">
							<s:if test="#session.fun240 == 1">
								<select id="pol" name="busin.pol" class="field border" style="width:146px;">
								<option value="">PLEASE CHOOSE</option>
								<c:forEach items="${portList }" var="port">
									<option value="${port.name }" <c:if test="${requestScope.busin.pol == port.name}">selected</c:if>>${port.name }</option>
								</c:forEach>
							</select>
							</s:if>
							<s:else>
								<select id="pol" name="busin.pol" style="width:146px;border:0px;" disabled="disabled">
									<option value="">PLEASE CHOOSE</option>
									<c:forEach items="${portList }" var="port">
										<option value="${port.name }" <c:if test="${requestScope.busin.pol == port.name}">selected</c:if>>${port.name }</option>
									</c:forEach>
								</select>
							</s:else>
						</td>
						<td style="width:100px;">
							<label>DELIVERY DATE</label>
						</td>
						<td style="width:150px;">
							<s:if test="#session.fun240 == 1">
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
							<s:if test="#session.fun240 == 1">
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
						<td style="width:100px;">
							<label>Ex-Vessel</label>
						</td>
						<td style="width:150px;">
							<input type="text" id="CVessel" name="busin.CVessel" value="${requestScope.busin.CVessel}" 
									class="field border" style="width:146px;"/>
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							<label>ETD</label>
						</td>
						<td style="width:150px;">
							<input id="CArrivalDate" name="CArrivalDate" value="${requestScope.CArrivalDate}"
									type="text" class="field border" style="width:146px" onclick="WdatePicker()" readonly="readonly"/>
						</td>
						<td style="width:100px;">
							<label>ETA</label>
						</td>
						<td style="width:150px;">
							<input id="CArrivalDate2" name="CArrivalDate2" value="${requestScope.CArrivalDate2}"
									type="text" class="field border" style="width:146px" onclick="WdatePicker()" readonly="readonly"/>
						</td>
						<td style="width:100px;">
							<label>CD No.</label>
						</td>
						<td style="width:150px;">
							<input type="text" id="CCdno" name="busin.CCdno" value="${requestScope.busin.CCdno}" 
									class="field border" style="width:146px;"/>
						</td>
						<td style="width:100px;">
							<label>CD Date</label>
						</td>
						<td style="width:150px;">
							<input id="CCdDate" name="CCdDate" value="${requestScope.CCdDate}"
									type="text" class="field border" style="width:146px" onclick="WdatePicker()" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							<label>Cost Date</label>
						</td>
						<td style="width:150px;">
							<input id="CCostDate" name="CCostDate" value="${requestScope.CCostDate}"
									type="text" class="field border" style="width:146px" onclick="WdatePicker()" readonly="readonly"/>
						</td>
						<td style="width:100px;">
							<label>Apply By</label>
						</td>
						<td style="width:150px;">
							<select id="CApplyby" name="busin.TSalerByCApplyby.CId" class="border" style="width:146px;margin-top:2px;">
				           		<option value="0">PLEASE CHOOSE</option>
								<c:forEach var="salerList" items="${requestScope.salerList}">
									<option value="${salerList.CId}">${salerList.CName}</option>
								</c:forEach>
							</select>
						</td>
						<td style="width:100px;">
							<label>Exchange</label>
						</td>
						<td style="width:150px;">
							<input type="number" id="CExchange" name="busin.CExchange" value="${requestScope.busin.CExchange}" 
									class="field border" style="width:146px;"/>
						</td>
						<td style="width:100px;">
							<label>Ref No.</label>
						</td>
						<td style="width:150px;text-align:left;padding-left:5px;">
							${requestScope.busin.CRefno}
							<input type="hidden" name="busin.CRefno" value="${requestScope.busin.CRefno}" />
						</td>
					</tr>
					<tr>
						<td>
							<label>D.N1</label>
						</td>
						<td>
							<input type="text" id="CNodate1" name="busin.CNodate1" value="${requestScope.busin.CNodate1}" 
									class="field border" style="width:146px;" readonly="readonly"/>
						</td>
						<td style="width:100px;">
							<label>No-Date(D.N1)</label>
						</td>
						<td style="width:150px;">
							<input id="CNodate2" name="CNodate2" value="${requestScope.CNodate2}"
									type="text" class="field border" style="width:146px" readonly="readonly"/>
						</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>
							<label>D.N2</label>
						</td>
						<td>
							<input type="text" id="CNodate3" name="busin.CNodate3" value="${requestScope.busin.CNodate3}" 
									class="field border" style="width:146px;" readonly="readonly"/>
						</td>
						<td style="width:100px;">
							<label>No-Date(D.N2)</label>
						</td>
						<td style="width:150px;">
							<input id="CNodate4" name="CNodate4" value="${requestScope.CNodate4}"
									type="text" class="field border" style="width:146px" readonly="readonly"/>
						</td>
						<td>
							<label>INV2</label>
						</td>
						<td>
							<input type="text" id="CNodate7" name="busin.CNodate7" value="${requestScope.busin.CNodate7}" 
									class="field border" style="width:146px;" readonly="readonly"/>
						</td>
						<td style="width:100px;">
							<label>No-Date(INV2)</label>
						</td>
						<td style="width:150px;">
							<input id="CNodate8" name="CNodate8" value="${requestScope.CNodate8}"
									type="text" class="field border" style="width:146px" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td>
							<label>
								业务员 saler
							</label>
						</td>
						<td>
							<s:if test="#session.fun240 == 1">
								<select id="saleman" name="busin.TSalerByCSaleman.CId" class="border" style="width:146px;margin-top:2px;">
					           		<option value="0">PLEASE CHOOSE</option>
									<c:forEach var="salerList" items="${requestScope.salerList}">
										<option value="${salerList.CId}">${salerList.CName}</option>
									</c:forEach>
								</select>
							</s:if>
							<s:else>
								<select id="saleman" class="border" 
										style="width:146px;margin-top:2px;" disabled="disabled">
					           		<option value="0">PLEASE CHOOSE</option>
									<c:forEach var="salerList" items="${requestScope.salerList}">
										<option value="${salerList.CId}">${salerList.CName}</option>
									</c:forEach>
								</select>
								<input name="busin.TSalerByCSaleman.CId" value="${requestScope.busin.TSalerByCSaleman.CId}" type="hidden"/>
							</s:else>
						</td>
						<td>
							<label>
								客服
							</label>
						</td>
						<td>
							<s:if test="#session.fun240 == 1">
								<select id="kf" name="busin.TSalerByCKf.CId" class="border" style="width:146px;margin-top:2px;">
					           		<option value="0">PLEASE CHOOSE</option>
									<c:forEach var="salerList" items="${requestScope.salerList}">
										<option value="${salerList.CId}">${salerList.CName}</option>
									</c:forEach>
								</select>
							</s:if>
							<s:else>
								<select id="kf"  class="border" 
										style="width:146px;margin-top:2px;" disabled="disabled">
					           		<option value="0">PLEASE CHOOSE</option>
									<c:forEach var="salerList" items="${requestScope.salerList}">
										<option value="${salerList.CId}">${salerList.CName}</option>
									</c:forEach>
								</select>
								<input name="busin.TSalerByCKf.CId" value="${requestScope.busin.TSalerByCKf.CId}" type="hidden"/>
							</s:else>
						</td>
						<td>
							<label>
								前程代理
							</label>
						</td>
						<td>
							<select name="busin.CAgent1" class="border _select" style="width:146px;margin-top:2px;">
				           		<option value="">PLEASE CHOOSE</option>
								<c:forEach var="agent" items="${requestScope.agentList}">
									<option value="${agent.name}" <c:if test="${requestScope.busin.CAgent1 == agent.name }">selected</c:if>>${agent.name}</option>
								</c:forEach>
							</select>
						</td>
						<td>
							<label>
								后程代理
							</label>
						</td>
						<td>
							<select name="busin.CAgent2" class="border _select" style="width:146px;margin-top:2px;">
				           		<option value="">PLEASE CHOOSE</option>
								<c:forEach var="agent" items="${requestScope.agentList}">
									<option value="${agent.name}" <c:if test="${requestScope.busin.CAgent2 == agent.name }">selected</c:if>>${agent.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							<label>Container #</label>
						</td>
						<td colspan="3">
							<input type="text" id="CConNum" name="busin.CConNum" value="${requestScope.busin.CConNum}" 
									class="field border" style="width:400px;"/>
						</td>
						<td>
							<label>Fty. inv No.</label>
						</td>
						<td>
							<input type="text" id="CFty" name="busin.CFty" value="${requestScope.busin.CFty}" 
									class="field border" style="width:146px;"/>
						</td>
						<td>
							<label>shipment</label>
						</td>
						<td>
							<select id="CShipment" name="busin.CShipment" class="field border" style="width:146px;">
								<option value="">PLEASE CHOOSE</option>
								<c:forEach items="${shipmentList }" var="one">
									<option value="${one.name }" <c:if test="${requestScope.busin.CShipment == one.name}">selected</c:if>>${one.name }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							<label>CBM:</label>
						</td>
						<td colspan="3">
							<input type="text" id="CCbm" name="busin.CCbm" value="${requestScope.busin.CCbm}" 
									class="field border" style="width:400px;"/>
						</td>
						<td>
							<label>PKG</label>
						</td>
						<td>
							<input type="text" id="CPkg" name="busin.CPkg" value="${requestScope.busin.CPkg}" 
									class="field border" style="width:146px;"/>
						</td>
						<td>
							<label>weight</label>
						</td>
						<td>
							<input type="text" id="CWeight" name="busin.CWeight" value="${requestScope.busin.CWeight}" 
									class="field border" style="width:146px;"/>
						</td>
					</tr>
					<tr>
						<td colspan="8" style="text-align:left;" >
							<span style="color:blue; font-weight:bold; padding-left:20px;">CONTAINER INFO</span>
							<input type="button" value="ADD" onclick="addContainer(0);" style="margin-left:10px; width:60px; height:20px;"/>
						</td>
					</tr>
				</table>
				<table id="conTable" class="table" style="margin-top:0px; width:1025px; margin-left:5px; color:black; border-top:0px;">
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
									<select id="conType0" name="cons[0].CContainerType" class="field border" style="width:76px;">
										<option value="">PLEASE CHOOSE</option>
										<c:forEach items="${typeList }" var="one">
											<option value="${one.name }" <c:if test="${requestScope.cons[0].CContainerType == one.name}">selected</c:if>>${one.name }</option>
										</c:forEach>
									</select>
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
							<td style="width:90px; border-top:0px;">
								<label>Pkgs/Weight</label>
							</td>
							<td style="width:132px; border-top:0px;">
								<s:if test="#session.userID == busin.TUser.CId">
									<input type="text" id="CCount0" 
											name="cons[0].CCount" 
											value="${requestScope.cons[0].CCount}"
											class="field border" style="width:128px;"/>
								</s:if>
								<s:else>
									<input type="text" id="CCount0"  
											value="${requestScope.cons[0].CCount}"
											class="" style="width:128px;border:0px;" disabled="disabled"/>
									<input type="hidden"
											name="cons[0].CCount" 
											value="${requestScope.cons[0].CCount}"/>
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
								<input type="text" id="CTrucker0" 
										name="cons[0].CTrucker" 
										value="${requestScope.cons[0].CTrucker}"
										class="field border" style="width:121px;"/>
								<!-- 
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
								 -->
							</td>
							<td style="width:78px; border-top:0px;">
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
											<select id="conType<s:property value="#num.index" />" name="cons[<s:property value="#num.index" />].CContainerType" class="field border" style="width:76px;">
												<option value="">PLEASE CHOOSE</option>
												<c:forEach items="${typeList }" var="one">
													<option value="${one.name }" <c:if test="${requestScope.cons[num.index].CContainerType == one.name}">selected</c:if>>${one.name }</option>
												</c:forEach>
											</select>
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
									<td style="width:90px; border-top:0px;">
									</td>
									<td style="width:132px; border-top:0px;">
										<s:if test="#session.userID == busin.TUser.CId">
											<input type="text" id="CCount<s:property value="#num.index" />" 
													name="cons[<s:property value="#num.index" />].CCount" 
													value="${requestScope.cons[num.index].CCount}"
													class="field border" style="width:128px;"/>
										</s:if>
										<s:else>
											<input type="text" id="CCount<s:property value="#num.index" />" 
													value="${requestScope.cons[num.index].CCount}"
													class="" style="width:128px;border:0px;" disabled="disabled"/>
											<input type="hidden" id="CCount<s:property value="#num.index" />" 
													name="cons[<s:property value="#num.index" />].CCount" 
													value="${requestScope.cons[num.index].CCount}"/>
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
										<input type="text" id="CTrucker<s:property value="#num.index" />" 
												name="cons[<s:property value="#num.index" />].CTrucker" 
												value="${requestScope.cons[num.index].CTrucker}"
												class="field border" style="width:121px;"/>
										<!--  
										<s:if test="#session.userID == busin.TUser.CId">
											<input type="text" id="CTrucker<s:property value="#num.index" />" 
												name="cons[<s:property value="#num.index" />].CTrucker" 
												value="${requestScope.cons[num.index].CTrucker}"
												class="field border" style="width:121px;"/>
										</s:if>
										<s:else>
											<input type="text" id="CTrucker<s:property value="#num.index" />" 
												value="${requestScope.cons[num.index].CTrucker}"
												class="field border" style="width:121px;" disabled="disabled"/>
											<input type="hidden" 
												name="cons[<s:property value="#num.index" />].CTrucker" 
												value="${requestScope.cons[num.index].CTrucker}"
												class="field border"/>
										</s:else>
										-->
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
				
				<table class="table" style="margin-top:10px; width:1500px; margin-left:5px;color:black;border-bottom:0px;">
					<tr>
						<td colspan="8" style="text-align:left;border-top:0px;border-bottom:0px;" >
							<span style="color:blue; font-weight:bold; padding-left:20px;float:left;">COST INFO</span>
							<!--  
							<select id="costGroup" class="border _select" style="float:left;width:226px;margin-left:20px;">
								<option value="0">PLEASE CHOOSE GROUP</option>
								<c:forEach var="costGroup" items="${requestScope.costGroup}">
									<option value="${costGroup.CId}">${costGroup.CName}</option>
								</c:forEach>
							</select>
							<input type="button" value="ADD GROUP" onclick="addCostGroup();" 
									style="margin-left:13px; width:100px; height:20px;float:left;"/>
							-->
						</td>
					</tr>
				</table>
				<table id="costTable" class="table" style="margin-top:0px; width:1500px; margin-left:5px; color:black;">
					<tr id="costTR0">
						<td style="width:270px;">
							<s:if test="(#session.fun211 == 1 && (#request.costsSize == 0 || costs[0].TCostItem == null || 
												(costs[0].TCostItem.CId != 198 && costs[0].TCostItem.CId != 199)))">
								<select id="TCostItem0" 
										name="costs[0].TCostItem.CId"
										class="border _select" style="width:266px;">
									<option value="0">PLEASE CHOOSE</option>
									<c:forEach var="costItemList" items="${requestScope.costItemList}">
										<option value="${costItemList.CId}">${costItemList.CName}</option>
									</c:forEach>
								</select>
								<input type="hidden" id="costItem0" 
									value="${requestScope.costs[0].TCostItem.CId}" />
							</s:if>
							<s:else>
								<select id="TCostItem0" 
										class="" style="width:266px;border:0px;" disabled="disabled">
									<option value="0">PLEASE CHOOSE</option>
									<c:forEach var="costItemList" items="${requestScope.costItemList}">
										<option value="${costItemList.CId}">${costItemList.CName}</option>
									</c:forEach>
								</select>
								<input type="hidden" id="costItem0" 
									name="costs[0].TCostItem.CId"
									value="${requestScope.costs[0].TCostItem.CId}" />
							</s:else>
							<!-- edit -->
							<input type="hidden" id="costsId0" name="costs[0].CId" value="${requestScope.costs[0].CId}" />
							<input type="hidden" name="costs[0].TBusin.CId" value="${requestScope.costs[0].TBusin.CId}" />
							<input type="hidden" name="costs[0].CState" value="${requestScope.costs[0].CState}" />
							<input type="hidden" name="costs[0].TUserByCCreateUserid.CId" 
									value="${requestScope.costs[0].TUserByCCreateUserid.CId}" />
							<input type="hidden" name="costs[0].CPrint" value="${requestScope.costs[0].CPrint}" />
							<input name="costs[0].CHidden" value="${requestScope.costs[0].CHidden}" type="hidden" />
							<input name="costs[0].CNewCostFlag" value="${requestScope.costs[0].CNewCostFlag}" type="hidden" />
							<input type="hidden" 
										id="costOrder0" 
										name="costs[0].COrder" 
										value="${requestScope.costs[0].COrder}" />
							<input type="hidden" name="costs[0].CCreateDate" value="${requestScope.costs[0].CCreateDate}" />
							<input type="hidden" id="CGroupid0" name="costs[0].TCostGroup.CId" value="${costs[0].TCostGroup.CId}" />
						</td>
						<td style="width:14px;">
							<label>$</label>
						</td>
						<td style="width:100px;">
							<s:if test="(#session.fun211 == 1 && (#request.costsSize == 0 || costs[0].TCostItem == null || 
												(costs[0].TCostItem.CId != 198 && costs[0].TCostItem.CId != 199)))">
								<input type="text" id="CMoney0" 
											name="costs[0].CMoney"
											value="${requestScope.costs[0].CMoney}"
											class="field border" style="width:76px;"/>
								<input id="money_0" type="checkbox"
													style="margin-left:1px;width:15px;height:15px;"
													onclick="computeTmpMoney();" />
							</s:if>
							<s:else>
								<input type="text" id="CMoney0" 
											value="${requestScope.costs[0].CMoney}"
											class="" style="width:76px;border:0px;" disabled="disabled"/>
								<input id="money_0" type="checkbox"
													style="margin-left:1px;width:15px;height:15px;"
													onclick="computeTmpMoney();" />
								<input type="hidden" id="CMoney0" 
											name="costs[0].CMoney"
											value="${requestScope.costs[0].CMoney}"
											/>
							</s:else>
						</td>
						<td style="width:34px;">
							<label>Riel</label>
						</td>
						<td style="width:96px;">
							<s:if test="(#session.fun211 == 1 && (#request.costsSize == 0 || costs[0].TCostItem == null || 
											(costs[0].TCostItem.CId != 198 && costs[0].TCostItem.CId != 199)))">
								<input type="text" id="CRiel0" 
											name="costs[0].CRiel"
											value="${requestScope.costs[0].CRiel}"
											class="field border" style="width:86px;"/>
							</s:if>
							<s:else>
								<input type="text" id="CRiel0" 
											value="${requestScope.costs[0].CRiel}"
											class="" style="width:76px;border:0px;" disabled="disabled"/>
								<input type="hidden" id="CRiel" 
											name="costs[0].CRiel"
											value="${requestScope.costs[0].CRiel}"
											/>
							</s:else>
						</td>
						<td style="width:20px;">
							<label><!--<span style="color:red;">*</span>-->RE</label>
						</td>
						<td style="width:117px;">
							<s:if test="(#session.fun211 == 1 && (#request.costsSize == 0 || costs[0].TCostItem == null || 
											(costs[0].TCostItem.CId != 198 && costs[0].TCostItem.CId != 199)))">
								<input type="text" id="CRe0"
										name="costs[0].CRe"
										value="${requestScope.costs[0].CRe}"
										class="field border" style="width:113px;"/>
							</s:if>
							<s:else>
								<input type="text" id="CRe0"
										value="${requestScope.costs[0].CRe}"
										class="" style="width:113px;border:0px;" disabled="disabled"/>
								<input type="hidden"
										name="costs[0].CRe"
										value="${requestScope.costs[0].CRe}"
										class="field border"/>
							</s:else>
						</td>
						<td style="width:50px;">
							<label>应付对象</label>
						</td>
						<td style="width:117px;">
							<select id="agent0" name="costs[0].agent" class="field border _select" style="width:113px;">
								<option value="">PLEASE CHOOSE</option>
								<c:forEach items="${agentList }" var="agent">
									<option value="${agent.name }" <c:if test="${costs[0].agent == agent.name }">selected</c:if>>${agent.name }</option>
								</c:forEach>
							</select>
						</td>
						<td style="width:50px;">
							<label>应付时间</label>
						</td>
						<td style="width:90px;">
							<input id="payDate0" name="costs[0].payDateStr" value='<fmt:formatDate value="${costs[0].payDate }" pattern="dd-MM-yyyy"/>'
									type="text" class="field border" style="width:80px" onclick="WdatePicker()" readonly="readonly"/>
						</td>
						<td style="width:35px;">
							<label>出纳1</label>
						</td>
						<td style="width:57px;">
							<s:if test="#session.fun205 == 1">
								<input type="text" id="CRemarks0"
									name="costs[0].CRemarks"
									value="${requestScope.costs[0].CRemarks}"
									class="field border" style="width:53px;"/>
							</s:if>
							<s:else>
								<input type="hidden"
									name="costs[0].CRemarks" 
									value="${requestScope.costs[0].CRemarks}"
									class="field border" style="width:53px;"/>
							</s:else>
						</td>
						<td style="width:35px;">
							<label>出纳2</label>
						</td>
						<td style="width:57px;">
							<s:if test="#session.fun208 == 1">
								<input type="text"
										name="costs[0].CRemarks2"
										value="${requestScope.costs[0].CRemarks2}"
										class="field border" style="width:53px;"/>
							</s:if>
							<s:else>
								<input type="hidden"
									name="costs[0].CRemarks2" 
									value="${requestScope.costs[0].CRemarks2}"
									class="field border" style="width:53px;"/>
							</s:else>
						</td>
						<td style="width:28px;">
							<label>审核</label>
						</td>
						<td style="width:57px;">
							<s:if test="#session.fun224 == 1">
								<input type="text"
										name="costs[0].CRemarks5"
										value="${requestScope.costs[0].CRemarks5}"
										class="field border" style="width:53px;"/>
							</s:if>
							<s:else>
								<input type="hidden"
									name="costs[0].CRemarks5" 
									value="${requestScope.costs[0].CRemarks5}"
									class="field border" style="width:53px;"/>
							</s:else>
						</td>
						<td style="width:28px;">
							<label>备注</label>
						</td>
						<td style="width:73px;">
							<s:if test="#session.fun229 == 1">
								<input type="text"
										name="costs[0].CRemarks4"
										value="${requestScope.costs[0].CRemarks4}"
										class="field border" style="width:69px;"/>
							</s:if>
							<s:else>
								<input type="hidden"
									name="costs[0].CRemarks4" 
									value="${requestScope.costs[0].CRemarks4}"
									class="field border" style="width:69px;"/>
							</s:else>
						</td>
						<td style="width:91px;">
							<s:if test="#session.fun202 == 1">
								<input type="button" value="ADD" onclick="addCost(0);" style="float:left; margin-left:3px; width:40px; height:20px;"/>
							</s:if>
						</td>
					</tr>
					<tbody id="cost">
						<s:iterator value="#request.costs" var="costs" status="cnum">
							<s:if test="#cnum.index > 0">
								<tr id="costTR<s:property value="#cnum.index" />">
									<td>
										<s:if test="(#session.fun211 == 1 && 
														(costs[#cnum.index].TCostItem == null || 
													 	(costs[#cnum.index].TCostItem.CId != 198 && costs[#cnum.index].TCostItem.CId != 199)))">
											<select id="TCostItem<s:property value="#cnum.index" />" 
													name="costs[<s:property value="#cnum.index" />].TCostItem.CId"
													class="border _select" style="width:266px;">
												<option value="0">PLEASE CHOOSE</option>
												<c:forEach var="costItemList" items="${requestScope.costItemList}">
													<option value="${costItemList.CId}">${costItemList.CName}</option>
												</c:forEach>
											</select>
											<input type="hidden" id="costItem<s:property value="#cnum.index" />" 
											   		value="${costs[cnum.index].TCostItem.CId}" />
										</s:if>
										<s:else>
											<select id="TCostItem<s:property value="#cnum.index" />" 
													class="" style="width:266px;border:0px;" disabled="disabled">
												<option value="0">PLEASE CHOOSE</option>
												<c:forEach var="costItemList" items="${requestScope.costItemList}">
													<option value="${costItemList.CId}">${costItemList.CName}</option>
												</c:forEach>
											</select>
											<input type="hidden" id="costItem<s:property value="#cnum.index" />" 
												name="costs[<s:property value="#cnum.index" />].TCostItem.CId"
											   value="${costs[cnum.index].TCostItem.CId}" />
										</s:else>
										
										<!-- edit -->
										<input type="hidden" id="costsId<s:property value="#cnum.index" />" 
												name="costs[<s:property value="#cnum.index" />].CId" value="${costs[cnum.index].CId}" />
										<input type="hidden" name="costs[<s:property value="#cnum.index" />].TBusin.CId" value="${costs[cnum.index].TBusin.CId}" />
										<input type="hidden" name="costs[<s:property value="#cnum.index" />].CState" value="${costs[cnum.index].CState}" />
										<input type="hidden" name="costs[<s:property value="#cnum.index" />].TUserByCCreateUserid.CId" 
												value="${costs[cnum.index].TUserByCCreateUserid.CId}" />
										<input type="hidden" name="costs[<s:property value="#cnum.index" />].CPrint" value="${costs[cnum.index].CPrint}" />
										<input name="costs[<s:property value="#cnum.index" />].CHidden" value="${requestScope.costs[cnum.index].CHidden}" type="hidden" />
										<input name="costs[<s:property value="#cnum.index" />].CNewCostFlag" value="${requestScope.costs[cnum.index].CNewCostFlag}" type="hidden" />
										<input type="hidden" 
													id="costOrder<s:property value="#cnum.index" />" 
													name="costs[<s:property value="#cnum.index" />].COrder" 
													value="${requestScope.costs[cnum.index].COrder}" />
										<input type="hidden" 
												name="costs[<s:property value="#cnum.index" />].CCreateDate" 
												value="${requestScope.costs[cnum.index].CCreateDate}" />
										<input type="hidden" id="CGroupid<s:property value="#cnum.index" />" 
												   name="costs[<s:property value="#cnum.index" />].TCostGroup.CId" 
												   value="${requestScope.costs[cnum.index].TCostGroup.CId}" />
									</td>
									<td>
									</td>
									<td>
										<s:if test="(#session.fun211 == 1 && 
														(costs[#cnum.index].TCostItem == null || 
														 (costs[#cnum.index].TCostItem.CId != 198 && costs[#cnum.index].TCostItem.CId != 199)))">
											<input type="text" id="CMoney<s:property value="#cnum.index" />" 
													name="costs[<s:property value="#cnum.index" />].CMoney" 
													value="${requestScope.costs[cnum.index].CMoney}"
													class="field border" style="width:76px;"/>
											<input id="money_<s:property value="#cnum.index" />" type="checkbox"
													style="margin-left:1px;width:15px;height:15px;"
													onclick="computeTmpMoney();" />
										</s:if>
										<s:else>
											<input type="text" id="CMoney<s:property value="#cnum.index" />" 
													value="${requestScope.costs[cnum.index].CMoney}"
													class="" style="width:76px;border:0px;" disabled="disabled"/>
											<input id="money_<s:property value="#cnum.index" />" type="checkbox"
													style="margin-left:1px;width:15px;height:15px;"
													onclick="computeTmpMoney();" />
											<input type="hidden"
												name="costs[<s:property value="#cnum.index" />].CMoney" 
												value="${requestScope.costs[cnum.index].CMoney}"
											/>
										</s:else>
									</td>
									<td>
									</td>
									<td>
										<s:if test="(#session.fun211 == 1 && 
														(costs[#cnum.index].TCostItem == null || 
													 	(costs[#cnum.index].TCostItem.CId != 198 && costs[#cnum.index].TCostItem.CId != 199)))">
											<input type="text" id="CRiel<s:property value="#cnum.index" />" 
													name="costs[<s:property value="#cnum.index" />].CRiel" 
													value="${requestScope.costs[cnum.index].CRiel}"
													class="field border" style="width:86px;"/>
										</s:if>
										<s:else>
											<input type="text" id="CRiel<s:property value="#cnum.index" />" 
													value="${requestScope.costs[cnum.index].CRiel}"
													class="" style="width:76px;border:0px;" disabled="disabled"/>
											<input type="hidden"
												name="costs[<s:property value="#cnum.index" />].CRiel" 
												value="${requestScope.costs[cnum.index].CRiel}"
											/>
										</s:else>
									</td>
									<td>
									</td>
									<td>
										<s:if test="(#session.fun211 == 1 && 
														 (costs[#cnum.index].TCostItem == null || 
														 (costs[#cnum.index].TCostItem.CId != 198 && costs[#cnum.index].TCostItem.CId != 199)))">
											<input type="text" id="CRe<s:property value="#cnum.index" />"
													name="costs[<s:property value="#cnum.index" />].CRe"
													value="${requestScope.costs[cnum.index].CRe}"
													class="field border" style="width:113px;"/>
										</s:if>
										<s:else>
											<input type="text" id="CRe<s:property value="#cnum.index" />"
													value="${requestScope.costs[cnum.index].CRe}"
													class="" style="width:113px;border:0px;" disabled="disabled"/>
											<input type="hidden"
													name="costs[<s:property value="#cnum.index" />].CRe"
													value="${requestScope.costs[cnum.index].CRe}"
													class="field border"/>
										</s:else>
										<input type="hidden" id="user<s:property value="#cnum.index" />" value="${requestScope.costs[cnum.index].TUserByCUserid.CId}" />
									</td>
									<td>
									</td>
									
										<td style="width:117px;">
											<select id="agent[<s:property value="#cnum.index" />]" name="costs[<s:property value="#cnum.index" />].agent" class="field border _select" style="width:113px;">
												<option value="">PLEASE CHOOSE</option>
												<c:forEach items="${agentList }" var="agent">
													<option value="${agent.name }" <c:if test="${costs[cnum.index].agent == agent.name }">selected</c:if>>${agent.name }</option>
												</c:forEach>
											</select>
										</td>
									<td>
									</td>
									<td style="width:90px;">
										<input id="payDate[<s:property value="#cnum.index" />]" name="costs[<s:property value="#cnum.index" />].payDateStr" value='<fmt:formatDate value="${costs[cnum.index].payDate }" pattern="dd-MM-yyyy"/>'
												type="text" class="field border" style="width:80px" onclick="WdatePicker()" readonly="readonly"/>
									</td>
									<td>
									</td>
									<td>
										<s:if test="#session.fun205 == 1">
											<input type="text" id="CRemarks0"
												name="costs[<s:property value="#cnum.index" />].CRemarks"
												value="${requestScope.costs[cnum.index].CRemarks}"
												class="field border" style="width:53px;"/>
										</s:if>
										<s:else>
											<input type="hidden"
												name="costs[<s:property value="#cnum.index" />].CRemarks" 
												value="${requestScope.costs[cnum.index].CRemarks}"
												class="field border" style="width:53px;"/>
										</s:else>
									</td>
									<td>
									</td>
									<td>
										<s:if test="#session.fun208 == 1">
											<input type="text"
													name="costs[<s:property value="#cnum.index" />].CRemarks2"
													value="${requestScope.costs[cnum.index].CRemarks2}"
													class="field border" style="width:53px;"/>
										</s:if>
										<s:else>
											<input type="hidden"
												name="costs[<s:property value="#cnum.index" />].CRemarks2" 
												value="${requestScope.costs[cnum.index].CRemarks2}"
												class="field border" style="width:53px;"/>
										</s:else>
									</td>
									<td></td>
									<td>
										<s:if test="#session.fun224 == 1">
											<input type="text"
													name="costs[<s:property value="#cnum.index" />].CRemarks5"
													value="${requestScope.costs[cnum.index].CRemarks5}"
													class="field border" style="width:53px;"/>
										</s:if>
										<s:else>
											<input type="hidden"
												name="costs[<s:property value="#cnum.index" />].CRemarks5" 
												value="${requestScope.costs[cnum.index].CRemarks5}"
												class="field border" style="width:53px;"/>
										</s:else>
									</td>
									<td></td>
									<td>
										<s:if test="#session.fun229 == 1">
											<input type="text"
													name="costs[<s:property value="#cnum.index" />].CRemarks4"
													value="${requestScope.costs[cnum.index].CRemarks4}"
													class="field border" style="width:69px;"/>
										</s:if>
										<s:else>
											<input type="hidden"
												name="costs[<s:property value="#cnum.index" />].CRemarks4" 
												value="${requestScope.costs[cnum.index].CRemarks4}"
												style="width:69px;border:0px;"/>
										</s:else>
									</td>
									<td>
										<s:if test="#session.fun202 == 1">
											<input type="button" value="ADD" onclick="addCost(<s:property value="#cnum.index" />);" style="float:left; margin-left:3px; width:40px; height:20px;"/>
											<s:if test="#session.fun211 == 1 &&
														#session.userID == costs[#cnum.index].TUserByCCreateUserid.CId &&
														costs[#cnum.index].CState == 1 && 
														costs[#cnum.index].CPrint == 0 &&
														(costs[#cnum.index].TCostItem == null || 
														(costs[#cnum.index].TCostItem.CId != 198 && costs[#cnum.index].TCostItem.CId != 199))">
												<input type="button" value="DEL" onclick='delCostInfo(<s:property value="#cnum.index" />,this);' style="float:left; margin-left:5px; width:40px; height:20px;"/>
											</s:if>
										</s:if>
									</td>
									</tr>
							</s:if>
						</s:iterator>
					</tbody>
				</table>
				<table class="table" style="margin-top:2px; width:1025px; margin-left:5px; color:black;border-top:0px;">
					<tr>
						<td style="width:858px;text-align:left;padding-left:224px;font-weight:bold;">
							<label id="costTmpSumMoney">成本总计：</label>
						</td>
					</tr>
				</table>
				
				
				
				<!-- cost group -->
				<table class="table" style="margin-top:10px; width:1430px; margin-left:5px;color:black;">
					<tr>
						<td colspan="8" style="text-align:left;border-top:0px;border-bottom:0px;" >
							<span style="color:blue; font-weight:bold; padding-left:20px;float:left;">COST GROUP</span>
							<select id="costGroup" class="border _select" style="float:left;width:226px;margin-left:20px;">
								<option value="0">PLEASE CHOOSE GROUP</option>
								<c:forEach var="costGroup" items="${requestScope.costGroup}">
									<option value="${costGroup.CId}">${costGroup.CName}</option>
								</c:forEach>
							</select>
							<input type="button" value="ADD GROUP" onclick="addCostGroup();" 
									style="margin-left:13px; width:100px; height:20px;float:left;"/>
							<label style="margin-left:13px;">应付时间</label>
							<input id="payDateStr" name="busin.payDateStr" value='<fmt:formatDate value="${costsGroup[0].payDate }" pattern="dd-MM-yyyy"/>'
									type="text" class="field border" style="width:80px;margin-left:13px;" onclick="WdatePicker()" readonly="readonly"/>
						</td>
					</tr>
				</table>
				<table id="costsGroupTable" class="table" style="margin-top:0px; width:1430px; margin-left:5px; color:black;">
					<!--  
					<tr id="costsGroupTR0">
						<td style="width:270px;">
							<s:if test=" (costsGroup[0].TCostGroup != null && costsGroup[0].TCostGroup.CId != null && costsGroup[0].TCostGroup.CId > 0) ||
										  (((#session.fun228 == 1 || #session.fun211 == 1) && 
										  ((#request.costsGroupSize == 0 || costsGroup[0].TCostItem == null ||
										    (costsGroup[0].TCostItem.CId != 198 && costsGroup[0].TCostItem.CId != 199)))))">
								<select id="TCostItem2_0" 
										name="costsGroup[0].TCostItem.CId"
										class="border _select" style="width:266px;">
									<option value="0">PLEASE CHOOSE</option>
									<c:forEach var="costItemList" items="${requestScope.costItemList}">
										<option value="${costItemList.CId}">${costItemList.CName}</option>
									</c:forEach>
								</select>
								<input type="hidden" id="costItem2_0"
									value="${requestScope.costsGroup[0].TCostItem.CId}" />
							</s:if>
							<s:else>
								<select id="TCostItem2_0" 
										class="" style="width:266px;border:0px;" disabled="disabled">
									<option value="0">PLEASE CHOOSE</option>
									<c:forEach var="costItemList" items="${requestScope.costItemList}">
										<option value="${costItemList.CId}">${costItemList.CName}</option>
									</c:forEach>
								</select>
								<input type="hidden" id="costItem2_0" name="costsGroup[0].TCostItem.CId"
									value="${requestScope.costsGroup[0].TCostItem.CId}" />
							</s:else>
							
							<input type="hidden" id="costsId2_0" name="costsGroup[0].CId" value="${requestScope.costsGroup[0].CId}" />
							<input type="hidden" name="costsGroup[0].TBusin.CId" value="${requestScope.costsGroup[0].TBusin.CId}" />
							<input type="hidden" name="costsGroup[0].CState" value="${requestScope.costsGroup[0].CState}" />
							<input type="hidden" name="costsGroup[0].TUserByCCreateUserid.CId" 
									value="${requestScope.costsGroup[0].TUserByCCreateUserid.CId}" />
							<input type="hidden" name="costsGroup[0].CPrint" value="${requestScope.costsGroup[0].CPrint}" />
							<input name="costsGroup[0].CHidden" value="${requestScope.costsGroup[0].CHidden}" type="hidden" />
							<input name="costsGroup[0].CNewCostFlag" value="${requestScope.costsGroup[0].CNewCostFlag}" type="hidden" />
							<input type="hidden" 
										id="costOrder2_0" 
										name="costsGroup[0].COrder" 
										value="${requestScope.costsGroup[0].COrder}" />
							<input type="hidden" name="costsGroup[0].CCreateDate" value="${requestScope.costsGroup[0].CCreateDate}" />
							<input type="hidden" id="CGroupid2_0" name="costsGroup[0].TCostGroup.CId" value="${costsGroup[0].TCostGroup.CId}" />
						</td>
						<td style="width:14px;">
							<label>$</label>
						</td>
						<td style="width:100px;">
							<s:if test=" (costsGroup[0].TCostGroup != null && costsGroup[0].TCostGroup.CId != null && costsGroup[0].TCostGroup.CId > 0) ||
											((#session.fun228 == 1 || #session.fun211 == 1) && 
										  		((#request.costsGroupSize == 0 || costsGroup[0].TCostItem == null ||
										    	(costsGroup[0].TCostItem.CId != 198 && costsGroup[0].TCostItem.CId != 199))))">
								<input type="text" id="CMoney2_0" 
											name="costsGroup[0].CMoney"
											value="${requestScope.costsGroup[0].CMoney}"
											class="field border" style="width:76px;"/>
								<input id="money_2_0" type="checkbox"
													style="margin-left:1px;width:15px;height:15px;"
													onclick="computeTmpMoney2();" />
							</s:if>
							<s:else>
								<input type="text" id="CMoney2_0" 
											value="${requestScope.costsGroup[0].CMoney}"
											class="" style="width:76px;border:0px;" disabled="disabled"/>
								<input id="money_2_0" type="checkbox"
													style="margin-left:1px;width:15px;height:15px;"
													onclick="computeTmpMoney2();" />
								<input type="hidden" id="CMoney2_0" 
											name="costsGroup[0].CMoney"
											value="${requestScope.costsGroup[0].CMoney}"
											/>
							</s:else>
						</td>
						<td style="width:34px;">
							<label>Riel</label>
						</td>
						<td style="width:96px;">
							<s:if test=" (costsGroup[0].TCostGroup != null && costsGroup[0].TCostGroup.CId != null && costsGroup[0].TCostGroup.CId > 0) ||
											(#session.fun211 == 1 && (#request.costsGroupSize == 0 || costsGroup[0].TCostItem == null || 
											(costsGroup[0].TCostItem.CId != 198 && costsGroup[0].TCostItem.CId != 199)))">
								<input type="text" id="CRiel2_0" 
											name="costsGroup[0].CRiel"
											value="${requestScope.costsGroup[0].CRiel}"
											class="field border" style="width:86px;"/>
							</s:if>
							<s:else>
								<input type="text" id="CRiel2_0" 
											value="${requestScope.costsGroup[0].CRiel}"
											class="" style="width:76px;border:0px;" disabled="disabled"/>
								<input type="hidden"
											name="costsGroup[0].CRiel"
											value="${requestScope.costsGroup[0].CRiel}"
											/>
							</s:else>
						</td>
						<td style="width:20px;">
							<label>RE</label>
						</td>
						<td style="width:117px;">
							<s:if test=" (costsGroup[0].TCostGroup != null && costsGroup[0].TCostGroup.CId != null && costsGroup[0].TCostGroup.CId > 0) ||
										((#session.fun228 == 1 || #session.fun211 == 1) && 
										  ((#request.costsGroupSize == 0 || costsGroup[0].TCostItem == null ||
										    (costsGroup[0].TCostItem.CId != 198 && costsGroup[0].TCostItem.CId != 199))))">
								<input type="text" id="CRe2_0"
										name="costsGroup[0].CRe"
										value="${requestScope.costsGroup[0].CRe}"
										class="field border" style="width:113px;"/>
							</s:if>
							<s:else>
								<input type="text" id="CRe2_0"
										value="${requestScope.costsGroup[0].CRe}"
										class="" style="width:113px;border:0px;" disabled="disabled"/>
								<input type="hidden"
										name="costsGroup[0].CRe"
										value="${requestScope.costsGroup[0].CRe}"
										class="field border"/>
							</s:else>
						</td>
						<td style="width:35px;">
							<label>出纳1</label>
						</td>
						<td style="width:57px;">
							<s:if test="#session.fun228 == 1 || #session.fun205 == 1">
								<input type="text" id="CRemarks2_0"
									name="costsGroup[0].CRemarks"
									value="${requestScope.costsGroup[0].CRemarks}"
									class="field border" style="width:53px;"/>
							</s:if>
							<s:else>
								<input type="hidden"
									name="costsGroup[0].CRemarks" 
									value="${requestScope.costsGroup[0].CRemarks}"
									class="field border" style="width:53px;"/>
							</s:else>
						</td>
						<td style="width:35px;">
							<label>出纳2</label>
						</td>
						<td style="width:57px;">
							<s:if test="#session.fun228 == 1 || #session.fun208 == 1">
								<input type="text"
										name="costsGroup[0].CRemarks2"
										value="${requestScope.costsGroup[0].CRemarks2}"
										class="field border" style="width:53px;"/>
							</s:if>
							<s:else>
								<input type="hidden"
									name="costsGroup[0].CRemarks2" 
									value="${requestScope.costsGroup[0].CRemarks2}"
									class="field border" style="width:53px;"/>
							</s:else>
						</td>
						<td style="width:28px;">
							<label>财务</label>
						</td>
						<td style="width:57px;">
							<s:if test="#session.fun228 == 1 || #session.fun216 == 1">
								<input type="text"
										name="costsGroup[0].CRemarks3"
										value="${requestScope.costsGroup[0].CRemarks3}"
										class="field border" style="width:53px;"/>
							</s:if>
							<s:else>
								<input type="hidden"
									name="costsGroup[0].CRemarks3" 
									value="${requestScope.costsGroup[0].CRemarks3}"
									class="field border" style="width:53px;"/>
							</s:else>
						</td>
						<td style="width:28px;">
							<label>审核</label>
						</td>
						<td style="width:57px;">
							<s:if test="#session.fun228 == 1 || #session.fun224 == 1">
								<input type="text"
										name="costsGroup[0].CRemarks5"
										value="${requestScope.costsGroup[0].CRemarks5}"
										class="field border" style="width:53px;"/>
							</s:if>
							<s:else>
								<input type="hidden"
									name="costsGroup[0].CRemarks5" 
									value="${requestScope.costsGroup[0].CRemarks5}"
									class="field border" style="width:53px;"/>
							</s:else>
						</td>
						<td style="width:28px;">
							<label>备注</label>
						</td>
						<td style="width:73px;">
							<s:if test="#session.fun228 == 1 || #session.fun229 == 1">
								<input type="text"
										name="costsGroup[0].CRemarks4"
										value="${requestScope.costsGroup[0].CRemarks4}"
										class="field border" style="width:69px;"/>
							</s:if>
							<s:else>
								<input type="hidden"
									name="costsGroup[0].CRemarks4" 
									value="${requestScope.costsGroup[0].CRemarks4}"
									class="field border" style="width:69px;"/>
							</s:else>
						</td>
						<td style="width:38px;">
							<label>成本组</label>
						</td>
						<td style="width:173px;">
							<input type="text"
									id="groupName2_0"
									name="costsGroup[0].TCostGroup.CName" 
									value="${requestScope.costsGroup[0].TCostGroup.CName}"
									class="field border" style="width:169px;" disabled="disabled"/>
						</td>
						<td style="width:91px;">
						</td>
					</tr>
					-->
					<tr>
						<td style="width:270px;height:0px;border:0px;"></td>
						<td style="width:14px;height:0px;border:0px;"></td>
						<td style="width:100px;height:0px;border:0px;"></td>
						<td style="width:34px;height:0px;border:0px;"></td>
						<td style="width:96px;height:0px;border:0px;"></td>
						<td style="width:20px;height:0px;border:0px;"></td>
						<td style="width:117px;height:0px;border:0px;"></td>
						<td style="width:35px;height:0px;border:0px;"></td>
						<td style="width:61px;height:0px;border:0px;"></td>
						<td style="width:35px;height:0px;border:0px;"></td>
						<td style="width:61px;height:0px;border:0px;"></td>
						<td style="width:28px;height:0px;border:0px;"></td>
						<td style="width:61px;height:0px;border:0px;"></td>
						<td style="width:28px;height:0px;border:0px;"></td>
						<td style="width:61px;height:0px;border:0px;"></td>
						<td style="width:28px;height:0px;border:0px;"></td>
						<td style="width:80px;height:0px;border:0px;"></td>
						<td style="width:38px;height:0px;border:0px;"></td>
						<td style="width:173px;height:0px;border:0px;"></td>
						<td style="width:50px;height:0px;border:0px;"></td>
					</tr>
					<tbody id="cost">
						<s:iterator value="#request.costsGroup" var="costsGroup" status="cnum">
							<s:if test="#cnum.index >= 0">
								<tr id="costTR2_<s:property value="#cnum.index" />">
									<td>
										<s:if test="(costsGroup[#cnum.index].TCostGroup != null && costsGroup[#cnum.index].TCostGroup.CId != null && costsGroup[#cnum.index].TCostGroup.CId > 0) ||
													((#session.fun228 == 1 || #session.fun211 == 1) && 
													(costsGroup[#cnum.index].TCostItem == null || 
													 (costsGroup[#cnum.index].TCostItem.CId != 198 && costsGroup[#cnum.index].TCostItem.CId != 199)))">
											<select id="TCostItem2_<s:property value="#cnum.index" />" 
													 name="costsGroup[<s:property value="#cnum.index" />].TCostItem.CId"
													class="border _select" style="width:266px;">
												<option value="0">PLEASE CHOOSE</option>
												<c:forEach var="costItemList" items="${requestScope.costItemList}">
													<option value="${costItemList.CId}">${costItemList.CName}</option>
												</c:forEach>
											</select>
											<input type="hidden" id="costItem2_<s:property value="#cnum.index" />" 
											   value="${costsGroup[cnum.index].TCostItem.CId}" />
										</s:if>
										<s:else>
											<select id="TCostItem2_<s:property value="#cnum.index" />" 
													class="" style="width:266px;border:0px;" disabled="disabled">
												<option value="0">PLEASE CHOOSE</option>
												<c:forEach var="costItemList" items="${requestScope.costItemList}">
													<option value="${costItemList.CId}">${costItemList.CName}</option>
												</c:forEach>
											</select>
											<input type="hidden" id="costItem2_<s:property value="#cnum.index" />" 
											   name="costsGroup[<s:property value="#cnum.index" />].TCostItem.CId"
											   value="${costsGroup[cnum.index].TCostItem.CId}" />
										</s:else>
										<!-- edit -->
										<input type="hidden" id="costsId2_<s:property value="#cnum.index" />" 
												name="costsGroup[<s:property value="#cnum.index" />].CId" value="${costsGroup[cnum.index].CId}" />
										<input type="hidden" name="costsGroup[<s:property value="#cnum.index" />].TBusin.CId" value="${costsGroup[cnum.index].TBusin.CId}" />
										<input type="hidden" name="costsGroup[<s:property value="#cnum.index" />].CState" value="${costsGroup[cnum.index].CState}" />
										<input type="hidden" name="costsGroup[<s:property value="#cnum.index" />].TUserByCCreateUserid.CId" 
												value="${costsGroup[cnum.index].TUserByCCreateUserid.CId}" />
										<input type="hidden" name="costsGroup[<s:property value="#cnum.index" />].CPrint" value="${costsGroup[cnum.index].CPrint}" />
										<input name="costsGroup[<s:property value="#cnum.index" />].CHidden" value="${requestScope.costsGroup[cnum.index].CHidden}" type="hidden" />
										<input name="costsGroup[<s:property value="#cnum.index" />].CNewCostFlag" value="${requestScope.costsGroup[cnum.index].CNewCostFlag}" type="hidden" />
										<input type="hidden" 
													id="costOrder2_<s:property value="#cnum.index" />" 
													name="costsGroup[<s:property value="#cnum.index" />].COrder" 
													value="${requestScope.costsGroup[cnum.index].COrder}" />
										<input type="hidden" 
												name="costsGroup[<s:property value="#cnum.index" />].CCreateDate" 
												value="${requestScope.costsGroup[cnum.index].CCreateDate}" />
										<input type="hidden" id="CGroupid2_<s:property value="#cnum.index" />" 
												   name="costsGroup[<s:property value="#cnum.index" />].TCostGroup.CId" 
												   value="${requestScope.costsGroup[cnum.index].TCostGroup.CId}" />
									</td>
									<td>
										<s:if test="#cnum.index==0">
											$
										</s:if>
									</td>
									<td>
										<s:if test="(costsGroup[#cnum.index].TCostGroup != null && costsGroup[#cnum.index].TCostGroup.CId != null && costsGroup[#cnum.index].TCostGroup.CId > 0) ||
													((#session.fun228 == 1 || #session.fun211 == 1) && 
													(costsGroup[#cnum.index].TCostItem == null || 
													 (costsGroup[#cnum.index].TCostItem.CId != 198 && costsGroup[#cnum.index].TCostItem.CId != 199)))">
											<input type="text" id="CMoney2_<s:property value="#cnum.index" />" 
													name="costsGroup[<s:property value="#cnum.index" />].CMoney" 
													value="${requestScope.costsGroup[cnum.index].CMoney}"
													class="field border" style="width:76px;"/>
											<input id="money_2_<s:property value="#cnum.index" />" type="checkbox"
													style="margin-left:1px;width:15px;height:15px;"
													onclick="computeTmpMoney2();" />
										</s:if>
										<s:else>
											<input type="text" id="CMoney2_<s:property value="#cnum.index" />" 
													value="${requestScope.costsGroup[cnum.index].CMoney}"
													class="" style="width:76px;border:0px;" disabled="disabled"/>
											<input id="money_2_<s:property value="#cnum.index" />" type="checkbox"
													style="margin-left:1px;width:15px;height:15px;"
													onclick="computeTmpMoney2();" />
											<input type="hidden"
												name="costsGroup[<s:property value="#cnum.index" />].CMoney" 
												value="${requestScope.costsGroup[cnum.index].CMoney}"
											/>
										</s:else>
									</td>
									<td>
										<s:if test="#cnum.index==0">
											Riel
										</s:if>
									</td>
									<td>
										<s:if test="(costsGroup[#cnum.index].TCostGroup != null && costsGroup[#cnum.index].TCostGroup.CId != null && costsGroup[#cnum.index].TCostGroup.CId > 0) ||
													((#session.fun228 == 1 || #session.fun211 == 1) && 
													(costsGroup[#cnum.index].TCostItem == null || 
													 (costsGroup[#cnum.index].TCostItem.CId != 198 && costsGroup[#cnum.index].TCostItem.CId != 199)))">
											<input type="text" id="CRiel2_<s:property value="#cnum.index" />" 
													name="costsGroup[<s:property value="#cnum.index" />].CRiel" 
													value="${requestScope.costsGroup[cnum.index].CRiel}"
													class="field border" style="width:86px;"/>
										</s:if>
										<s:else>
											<input type="text" id="CRiel2_<s:property value="#cnum.index" />" 
													value="${requestScope.costsGroup[cnum.index].CRiel}"
													class="" style="width:76px;border:0px;" disabled="disabled"/>
											<input type="hidden"
												name="costsGroup[<s:property value="#cnum.index" />].CRiel" 
												value="${requestScope.costsGroup[cnum.index].CRiel}"
											/>
										</s:else>
									</td>
									<td>
										<s:if test="#cnum.index==0">
											RE
										</s:if>
									</td>
									<td>
										<s:if test="(costsGroup[#cnum.index].TCostGroup != null && costsGroup[#cnum.index].TCostGroup.CId != null && costsGroup[#cnum.index].TCostGroup.CId > 0) ||
													((#session.fun228 == 1 || #session.fun211 == 1) && 
													(costsGroup[#cnum.index].TCostItem == null || 
													 (costsGroup[#cnum.index].TCostItem.CId != 198 && costsGroup[#cnum.index].TCostItem.CId != 199)))">
											<input type="text" id="CRe2_<s:property value="#cnum.index" />"
													name="costsGroup[<s:property value="#cnum.index" />].CRe"
													value="${requestScope.costsGroup[cnum.index].CRe}"
													class="field border" style="width:113px;"/>
										</s:if>
										<s:else>
											<input type="text" id="CRe2_<s:property value="#cnum.index" />"
													value="${requestScope.costsGroup[cnum.index].CRe}"
													class="" style="width:113px;border:0px;" disabled="disabled"/>
											<input type="hidden"
													name="costsGroup[<s:property value="#cnum.index" />].CRe"
													value="${requestScope.costsGroup[cnum.index].CRe}"
													class="field border"/>
										</s:else>
										<input type="hidden" id="user2_<s:property value="#cnum.index" />" value="${requestScope.costsGroup[cnum.index].TUserByCUserid.CId}" />
									</td>
									<td>
										<s:if test="#cnum.index==0">
											出纳1
										</s:if>
									</td>
									<td>
										<s:if test="#session.fun228 == 1 || #session.fun205 == 1">
											<input type="text" id="CRemarks2_<s:property value="#cnum.index" />"
												name="costsGroup[<s:property value="#cnum.index" />].CRemarks"
												value="${requestScope.costsGroup[cnum.index].CRemarks}"
												class="field border" style="width:53px;"/>
										</s:if>
										<s:else>
											<input type="hidden"
												name="costsGroup[<s:property value="#cnum.index" />].CRemarks" 
												value="${requestScope.costsGroup[cnum.index].CRemarks}"
												class="field border" style="width:53px;"/>
										</s:else>
									</td>
									<td>
										<s:if test="#cnum.index==0">
											出纳2
										</s:if>
									</td>
									<td>
										<s:if test="#session.fun228 == 1 || #session.fun208 == 1">
											<input type="text"
													name="costsGroup[<s:property value="#cnum.index" />].CRemarks2"
													value="${requestScope.costsGroup[cnum.index].CRemarks2}"
													class="field border" style="width:53px;"/>
										</s:if>
										<s:else>
											<input type="hidden"
												name="costsGroup[<s:property value="#cnum.index" />].CRemarks2" 
												value="${requestScope.costsGroup[cnum.index].CRemarks2}"
												class="field border" style="width:53px;"/>
										</s:else>
									</td>
									<td>
										<s:if test="#cnum.index==0">
											财务
										</s:if>
									</td>
									<td>
										<s:if test="#session.fun228 == 1 || #session.fun216 == 1">
											<input type="text"
													name="costsGroup[<s:property value="#cnum.index" />].CRemarks3"
													value="${requestScope.costsGroup[cnum.index].CRemarks3}"
													class="field border" style="width:53px;"/>
										</s:if>
										<s:else>
											<input type="hidden"
												name="costsGroup[<s:property value="#cnum.index" />].CRemarks3" 
												value="${requestScope.costsGroup[cnum.index].CRemarks3}"
												class="field border" style="width:53px;"/>
										</s:else>
									</td>
									<td>
										<s:if test="#cnum.index==0">
											审核
										</s:if>
									</td>
									<td>
										<s:if test="#session.fun228 == 1 || #session.fun224 == 1">
											<input type="text"
													name="costsGroup[<s:property value="#cnum.index" />].CRemarks5"
													value="${requestScope.costsGroup[cnum.index].CRemarks5}"
													class="field border" style="width:53px;"/>
										</s:if>
										<s:else>
											<input type="hidden"
												name="costsGroup[<s:property value="#cnum.index" />].CRemarks5" 
												value="${requestScope.costsGroup[cnum.index].CRemarks5}"
												class="field border" style="width:53px;"/>
										</s:else>
									</td>
									<td>
										<s:if test="#cnum.index==0">
											备注
										</s:if>
									</td>
									<td>
										<s:if test="#session.fun228 == 1 || #session.fun229 == 1">
											<input type="text"
													name="costsGroup[<s:property value="#cnum.index" />].CRemarks4"
													value="${requestScope.costsGroup[cnum.index].CRemarks4}"
													class="field border" style="width:69px;"/>
										</s:if>
										<s:else>
											<input type="hidden"
												name="costsGroup[<s:property value="#cnum.index" />].CRemarks4" 
												value="${requestScope.costsGroup[cnum.index].CRemarks4}"
												style="width:69px;border:0px;"/>
										</s:else>
									</td>
									<td>
										<s:if test="#cnum.index==0">
											成本组
										</s:if>
									</td>
									<td>
										<input type="text"
												id="groupName2_<s:property value="#cnum.index" />"
												name="costsGroup[<s:property value="#cnum.index" />].TCostGroup.CName"
												value="${requestScope.costsGroup[cnum.index].TCostGroup.CName}"
												class="field border" style="width:169px;" disabled="disabled"/>
									</td>
									<td>
										<input type="button" value="DEL" onclick='delCostInfo2(<s:property value="#cnum.index" />,this);' style="float:left; margin-left:5px; width:40px; height:20px;"/>
									</td>
								</tr>
							</s:if>
						</s:iterator>
					</tbody>
				</table>
				<table class="table" style="margin-top:2px; width:1025px; margin-left:5px; color:black;border-top:0px;">
					<tr>
						<td style="width:858px;text-align:left;padding-left:224px;font-weight:bold;">
							<label id="costTmpSumMoney2">成本总计：</label>
						</td>
					</tr>
				</table>
				
				
				
				
				<table id="remars" class="table" style="margin-top:10px; width:1025px; margin-left:5px; color:black;border-top:0px;">
					<tr>
						<td style="width:100px;">
							REMARKS
						</td>
						<td style="width:918px;">
							<input type="text" id="remarks" name="busin.CRemarks" value="${requestScope.busin.CRemarks}"
									class="field border" style="width:914px;"/>
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							业务单备注2
						</td>
						<td style="width:918px;">
							<s:if test="#session.fun219 == 1">
								<input type="text" id="remarks3" name="busin.CRemarks3" value="${requestScope.busin.CRemarks3}"
										class="field border" style="width:914px;"/>
							</s:if>
							<s:else>
								<input type="text" id="remarks3" value="${requestScope.busin.CRemarks3}"
										class="field" style="width:914px;border:0px;" disabled="disabled"/>
								<input type="hidden" name="busin.CRemarks3" value="${requestScope.busin.CRemarks3}"
										class="field border" style="width:914px;"/>
							</s:else>
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							开票提醒
						</td>
						<td style="width:918px;">
							<input type="text" value="${requestScope.busin.CNoticeRemarks}"
										class="field" style="width:914px;border:0px;" disabled="disabled"/>
							<input type="hidden" name="busin.CNoticeRemarks"  value="${requestScope.busin.CNoticeRemarks}"/>
							<input type="hidden" name="busin.CNoticeFlag"  value="${requestScope.busin.CNoticeFlag}"/>
							<input type="hidden" name="busin.CNoticeReply"  value="${requestScope.busin.CNoticeReply}"/>
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							问  题
						</td>
						<td style="width:918px;">
							<input type="text" name="busin.CAskRemarks" value="${requestScope.busin.CAskRemarks}"
										class="field border" style="width:914px;" />
							<input type="hidden" name="busin.CAnswerRemarks"  value="${requestScope.busin.CAnswerRemarks}"/>
							<input type="hidden" name="busin.CAsk"  value="${requestScope.busin.CAsk}"/>
							<input type="hidden" name="busin.CAnswer"  value="${requestScope.busin.CAnswer}"/>
							<input type="hidden" name="busin.CAskNotice"  value="${requestScope.busin.CAskNotice}"/>
							<input type="hidden" name="busin.CAskDate"  value="${requestScope.busin.CAskDate}"/>
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							DN1 REMARKS
						</td>
						<td style="width:918px;">
							<input type="text" name="busin.CDn1Remarks" value="${requestScope.busin.CDn1Remarks}"
									class="field" style="width:914px;border:0px;" disabled="disabled" />
							<input type="hidden" name="busin.CDn1Remarks"  value="${requestScope.busin.CDn1Remarks}"/>
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
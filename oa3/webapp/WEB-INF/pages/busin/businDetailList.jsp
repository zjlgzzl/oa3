<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>旅行社信息</title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
	<!-- 引入jquery easyui -->
	<link type="text/css" rel="stylesheet" href="${ctx}/res/jquery-easyui-1.3.5/themes/default/easyui.css"></link>
	<link type="text/css" rel="stylesheet" href="${ctx}/res/jquery-easyui-1.3.5/themes/icon.css"></link>
	<script type="text/javascript" src="${ctx}/res/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/res/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
	<script	type="text/javascript" src="${ctx}/res/jquery-easyui-1.3.5/easyui-lang-zh_CN.js"></script>
	<!-- 项目css -->
	<link type="text/css" rel="stylesheet"  href="${ctx}/res/css/styles.css"/>
	<style type="text/css">  
	 	a{
			text-decoration:none;
			color: #2C82A5;
		}
		a:hover{
			text-decoration:underline;
			color: #2C82A5;
			background-color:white;
		}
		.s1 {background-color:lightyellow;}
		.btn_s1{width:70px;height:23px;}
	</style>
	
	<script type="text/javascript">
	
		var fun231 = 0;//查看利润
		var fun212 = 0;//归档(未)
		var fun221 = 0;//归档(已)
		var fun206 = 0;//服务单归档
		var fun302 = 0;//开票(菜单)
		var fun305 = 0;//开票(按钮)
		var fun308 = 0;//开票归档
		var fun310 = 0;//已审核
		var fun220 = 0;//完结
		
		//初始化
		$(document).ready(function(){
			
			//查看利润
			fun231 = "${sessionScope.fun231}";
			//归档(未)
			fun212 = "${sessionScope.fun212}";
			//归档(已)
			fun221 = "${sessionScope.fun221}";
			//服务单归档
			fun206 = "${sessionScope.fun206}";
			//开票(菜单)
			fun302 = "${sessionScope.fun302}";
			//开票(按钮)
			fun305 = "${sessionScope.fun305}";
			//开票归档
			fun308 = "${sessionScope.fun308}";
			//已审核
			fun310 = "${sessionScope.fun310}";
			//完结
			fun220 = "${sessionScope.fun220}";
			
			$("#table1 tbody tr").click(function() {
				$(this).addClass("s1").siblings().removeClass("s1");
			});
			
			var businId = "${requestScope._businId}";
			if (businId > 0){
				//双击进入
				for(i=0; i<19; i++){
					if (document.getElementById("businId"+i).value == businId){
						$("#tr_"+i).addClass("s1");
						viewBusin(businId, i);
						break;
					}
				}
			}else{
				//上一页下一页刷新
				$("#tr_0").addClass("s1");
				viewBusin(document.getElementById("businId0").value,0);
			}
			
			//后台校验错误信息
			var errInfo = "${requestScope.errInfo}";
			if (errInfo != null && errInfo.length > 0){
				alert(errInfo);
			}
			
		});
		
		//查看
		function viewBusin(businId, rowIndex){
		
			var businState = 0;//服务单状态
			var completeFlag = 0;//是否完结
		
			/*刷新服务单*/
			//服务单id
			document.getElementById("businId_").value = businId;
			document.getElementById("_businId").value = businId;
			//重新加载服务单查看信息
			$("#content").load("${ctx}/busin/ViewBusin.action?businId="+businId);
			
			//listFlag：1，服务单；6，开票
			var listFlag = document.getElementById("listFlag").value;
			
			/*获取利润*/
			//清空利润
			if (document.getElementById("profitDiv") != null){
				document.getElementById("profitDiv").innerHTML = "";
			}
			//是否完结
			completeFlag = document.getElementById("completeFlag"+rowIndex).value;
			//查看利润按钮
			if (document.getElementById("profitBtn") != null){
				if (completeFlag == 0 || (completeFlag == 1 && fun231 == 1)){
					$("#profitBtn").attr("class", "btn_s1");
				}else{
					$("#profitBtn").attr("class", "hidden");
				}
			}
			
			/*归档*/
			if (listFlag == 1){
				$("#ar1").attr("class", "hidden");
				$("#ar2").attr("class", "hidden");
				$("#ar3").attr("class", "hidden");
				businState = document.getElementById("state"+rowIndex).value;
				if(businState == 1){
					if (fun212 == 1){
						$("#ar1").attr("class", "btn_s1");
					}
					if (fun221 == 1){
						$("#ar2").attr("class", "btn_s1");
					}
				}else if(businState == 2){
					if(fun221 == 1){
						$("#ar2").attr("class", "btn_s1");
					}
				}else if(businState == 3){
					if ((businState == 2 && fun212 == 1) || (businState == 3 && fun221 == 1)){
						$("#ar3").attr("class", "btn_s1");
					}
				}
			}
			
			/*开票*/
			if (listFlag == 6){
				if(completeFlag == 0 && fun302 == 1 && fun305 == 1){
					$("#invBtn").attr("class", "btn_s1");
				}else{
					$("#invBtn").attr("class", "hidden");
				}
			}
			
			/*已审核*/
			var recieveFlag = document.getElementById("recieveFlag"+rowIndex).value;
			var businStateName = document.getElementById("businStateName"+rowIndex).value;
			if (completeFlag == 0 && fun308 == 1 && fun310 == 1 && recieveFlag == 0 && businStateName == '已开票'){
				$("#invBtn2").attr("class", "btn_s1");
			}else{
				$("#invBtn2").attr("class", "hidden");
			}
			
			/*开票打印*/
			if (listFlag == 6){
				var nodate2 = document.getElementById("nodate2_"+rowIndex).value;
				if (nodate2 != null && nodate2 != ""){
					$("#printInv2").attr("class", "btn_s1");
				}else{
					$("#printInv2").attr("class", "hidden");
				}
				var nodate4 = document.getElementById("nodate4_"+rowIndex).value;
				if (nodate4 != null && nodate4 != ""){
					$("#printInv4").attr("class", "btn_s1");
				}else{
					$("#printInv4").attr("class", "hidden");
				}
				var nodate8 = document.getElementById("nodate8_"+rowIndex).value;
				if (nodate8 != null && nodate8 != ""){
					$("#printInv8").attr("class", "btn_s1");
				}else{
					$("#printInv8").attr("class", "hidden");
				}
			}
			
			/*完结*/
			if (listFlag == 6){
				if(fun220==1){
					if (completeFlag == 0){
						$("#completeBtn").attr("class", "btn_s1");
						$("#completeBtn2").attr("class", "hidden");
					}else{
						$("#completeBtn").attr("class", "hidden");
						$("#completeBtn2").attr("class", "btn_s1");
					}
				}
			}
		}
		
		//返回
		function returnList(){
			var listFlag = document.getElementById("listFlag").value;
			if (listFlag == 1){
				document.getElementById("queryForm").action = "${ctx}/busin/BusinList.action";
			}else{
				document.getElementById("queryForm").action = "${ctx}/busin/FinanceCashList.action";
			}
			document.getElementById("queryForm").submit();
		}
		
		//添加
		function add(){
			document.getElementById("queryForm").action = "${ctx}/busin/AddBusin.action";
			document.getElementById("queryForm").submit();
		}
		
		//修改
		function edit(){
			var businId = document.getElementById("businId_").value;
			if(!checkLock(businId)){
				return;
			}
			document.getElementById("queryForm").action = "${ctx}/busin/EditBusin.action";
			document.getElementById("queryForm").submit();
		}
		
		//删除
		function del(){
			var businId = document.getElementById("businId_").value;
			if(!checkLock(businId)){
				return;
			}
			if(confirm("确定删除该业务单吗？")){
				$("#delBtn").attr("disabled","disabled")
				document.getElementById("queryForm").action = "${ctx}/busin/DeleteBusin.action";
				document.getElementById("queryForm").submit();
			}
		}
		
		//检查是否锁定
		function checkLock(businId){
			var url = "${ctx}/busin/CheckLock.action?businId=" + businId;
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
				return false;
			}
			return true;
		}
		
		//归档确认
		function conClose(flag){
			if (confirm("归档后的单据将不允许修改任何信息，确认单据已经全部执行完成并归档？")){
				document.getElementById("archiveFlag").value = flag;
				//没有服务单归档查看权限，不记录当前businid，返回显示第一条
				if (fun206 == 0){
					document.getElementById("_businId").value = 0;
				}
				document.getElementById("queryForm").action = "${ctx}/busin/BusinClose.action";
				document.getElementById("queryForm").submit();
			}
		}
		
		//解除归档
		function conOpen(cid){
			if (confirm("确认解除归档？")){
				document.getElementById("queryForm").action = "${ctx}/busin/BusinOpen.action";
				document.getElementById("queryForm").submit();
			}
		}
		
		//开票
		function editCashBusin(){
			var businId = document.getElementById("businId_").value;
			if(!checkLock(businId)){
				return;
			}
			var url = "${ctx}/busin/CheckBjFlagBeforeEdit.action?businId="+businId;
			$.ajax({
				type:"Post",
				async: false,
				url: url,
				success:function(data){
					if (data != ""){
						var flag = Number(data);
						if (flag == 1){
							alert("请先标记成本项目组!");
							return;
						}else{
							document.getElementById("queryForm").action = "${ctx}/busin/EditCashBusin.action";
							document.getElementById("queryForm").submit();
						}
					}
				}
			});
		}
		
		//已开票
		function updateRecieveFlag(){
			document.getElementById("queryForm").action = "${ctx}/busin/UpdateRecieveFlag.action";
			document.getElementById("queryForm").submit();
		}
		
		//print file
		function printFile(){
			//打印
			document.getElementById("queryForm").action = "${ctx}/busin/PrintBusinFile.action";
			document.getElementById("queryForm").submit();
		}
		
		//print payment request
		function printCost(){
			//if(fun205==1){
				var printFlag = document.getElementsByName("printFlag");
				var check = false;
				for (var i=0; i<printFlag.length; i++){
					if (printFlag[i].checked){
						check = true;
						break;
					}
				}
				if (!check){
					alert("请选择需要打印的成本项目！");
					return false;
				}
			//}
			copyHiddenValue();
			document.getElementById("editForm").action = "${ctx}/busin/PrintCost.action";
			document.getElementById("editForm").submit();
		}
		
		function printCost2(){
		
			var printRadioFlag = document.getElementsByName("printRadioFlag");
			var check = false;
			var selectIndex = 0;
			for (var i=0; i<printRadioFlag.length; i++){
				if (printRadioFlag[i].checked){
					var msg = "";
					var CCostDate = document.getElementById("costDate"+i).value;
					if (CCostDate == null || CCostDate == ""){
						msg = "Cost Date";
					}
					var CApplyby = document.getElementById("applyBy"+i).value;
					if (CApplyby == null || CApplyby == ""){
						if (msg != ""){
							msg += "、";
						}
						msg += "Apply By";
					}
					var CExchange = document.getElementById("exchange"+i).value;
					if (CExchange == null || CExchange == ""){
						if (msg != ""){
							msg += "、";
						}
						msg += "Exchange";
					}
					if (msg != ""){
						msg += "必须填写"
						alert(msg);
						return;
					}
					check = true;
					selectIndex = i;
					break;
				}
			}
			
			if (!check){
				alert("请选择需要打印的成本项目组");
				return;
			}
			
			//设置打印项目
			var costGroup = document.getElementById("costsGroupId"+selectIndex).value;
			var printFlag = document.getElementsByName("groupPrintFlag");
			for (var i=0; i<printFlag.length; i++){
				printFlag[i].checked = false;
			}
			for (var j=0; j<printFlag.length; j++){
				if (costGroup == document.getElementById("CGroupid"+j).value){
					printFlag[j].checked = true;
				}
			}
			copyHiddenValue();
			document.getElementById("editForm").action = "${ctx}/busin/PrintCost2.action";
			document.getElementById("editForm").submit();
		}
		
		//打印发票
		function printInvoiceDue(){
			document.getElementById("queryForm").action = "${ctx}/busin/PrintInvoiceDue.action";
			document.getElementById("queryForm").submit();
		}
		//打印含税发票
		function printInvoiceDue2(){
			document.getElementById("queryForm").action = "${ctx}/busin/PrintInvoiceDue2.action";
			document.getElementById("queryForm").submit();
		}
		//打印含税发票
		function printInvoiceRateDue2(){
			document.getElementById("queryForm").action = "${ctx}/busin/PrintInvoiceRateDue2.action";
			document.getElementById("queryForm").submit();
		}
		
		function businComplete(flag){
			var msg = "";
			if (flag==1){
				msg = "完结";
			}else{
				msg = "解除完结";
			}
			if (confirm("确认"+msg+"?")){
				document.getElementById("archiveFlag").value = flag;
				document.getElementById("queryForm").action = "${ctx}/busin/BusinComplete.action";
				document.getElementById("queryForm").submit();
			}
		}
		
		function copyHiddenValue(){
			document.getElementById("pageNow2").value = document.getElementById("pageNow").value;
			document.getElementById("listFlag2").value = document.getElementById("listFlag").value;
			document.getElementById("businCode2").value = document.getElementById("businCode").value;
			document.getElementById("businCode22").value = document.getElementById("businCode2").value;
			document.getElementById("typeId2").value = document.getElementById("typeId").value;
			document.getElementById("cusId2").value = document.getElementById("cusId").value;
			document.getElementById("conNum2").value = document.getElementById("conNum").value;
			document.getElementById("billNo2").value = document.getElementById("billNo").value;
			document.getElementById("empName2").value = document.getElementById("empName").value;
			document.getElementById("startDate2").value = document.getElementById("startDate").value;
			document.getElementById("endDate2").value = document.getElementById("endDate").value;
			document.getElementById("businState2").value = document.getElementById("businState").value;
			document.getElementById("businStateName2").value = document.getElementById("businStateName").value;
			document.getElementById("completeFlag2").value = document.getElementById("completeFlag").value;
			document.getElementById("startDate22").value = document.getElementById("startDate2").value;
			document.getElementById("endDate22").value = document.getElementById("endDate2").value;
			document.getElementById("_businId2").value = document.getElementById("_businId").value;
		}
		
		//分页控件
		function forward(flag){
			document.getElementById("_businId").value = 0;
			document.getElementById("queryForm").action = "${ctx}/busin/BusinDetialList.action";
			//上一页
			if (flag == 2){
				var rowNow = "${requestScope.pageBean.pageNow}";
				rowNow = Number(rowNow) - Number(1);
				document.getElementById("pageNow").value = rowNow;
				document.getElementById("queryForm").submit();
				return false;
			}
			//下一页
			if (flag == 3){
				var rowNow = "${requestScope.pageBean.pageNow}";
				rowNow = Number(rowNow) + Number(1);
				document.getElementById("pageNow").value = rowNow;
				document.getElementById("queryForm").submit();
				return false;
			}
		}
		
	</script>
	
  </head>
  
  <body class="easyui-layout">
  
  	<!-- menu -->
	<div id="travelTree" data-options="region:'west',split:true,title:''" style="width:200px;">
		<table id="table1" class="table" style="width:194px;margin-left:5px;margin-top:5px;margin-bottom:5px;">
			<tr>
				<th style="width:35px;text-align:center;font-weight:bold;">序号</th>
              	<th style="width:157px;text-align:center;font-weight:bold;">单据编号</th>
            </tr>
			<s:iterator value="#request.businList" var="businList" status="num">
	           	<tr id="tr_<s:property value="#num.index" />">
	           		<td onclick="viewBusin(<s:property value="businId" />,<s:property value="#num.index" />);">
						<s:property value="(#num.index+1)+(pageNow-1)*pageSize" />
					</td>
	           		<td onclick="viewBusin(<s:property value="businId" />,<s:property value="#num.index" />);" style="text-align:left;padding-left:3px;">
						<s:property value="billNo" />
						<input id="businId<s:property value="#num.index" />" 
								value="<s:property value="businId" />"
								type="hidden" />
						<input id="state<s:property value="#num.index" />" 
								value="<s:property value="businState" />"
								type="hidden" />
						<input id="completeFlag<s:property value="#num.index" />" 
								value="<s:property value="completeFlag" />"
								type="hidden" />
						<input id="recieveFlag<s:property value="#num.index" />" 
								value="<s:property value="recieveFlag" />"
								type="hidden" />
						<input id="nodate2_<s:property value="#num.index" />" 
								value="<s:property value="nodate2" />"
								type="hidden" />
						<input id="nodate4_<s:property value="#num.index" />" 
								value="<s:property value="nodate4" />"
								type="hidden" />
						<input id="nodate8_<s:property value="#num.index" />" 
								value="<s:property value="nodate8" />"
								type="hidden" />
						<input id="businStateName<s:property value="#num.index" />" 
								value="<s:property value="businStateName" />"
								type="hidden" />
					</td>
	            </tr>
			</s:iterator>
		</table>
	</div>
  	
  	<!-- main -->
	<div data-options="region:'center'">
		<div style="margin-left:15px;">
			<input type="button" value="返 回" class="btn_s1" style="margin-left:5px;margin-top:10px;" onclick="returnList();"/>
			<c:if test="${pageBean.pageNow>1}">
				<input id="" type="button" value="上一页" class="btn_s1" style="margin-left:5px;margin-top:10px;" onclick="forward(2);"/>
			</c:if>
			<c:if test="${pageBean.pageNow<pageBean.pageCount}">
				<input type="button" value="下一页" class="btn_s1" style="margin-left:5px;margin-top:10px;" onclick="forward(3);"/>
			</c:if>
			<input id="profitBtn" type="button" value="获取利润" class="btn_s1" style="margin-left:5px;margin-top:10px;" onclick="getProfitByBusinId();"/>
			<label style="font-weight:bold;margin-left:3px;margin-top:10px;" id="profitDiv"></label>
			<s:if test="#request.listFlag == 1">
				<input id="addBtn" type="button" value="添 加" class="btn_s1" style="margin-left:5px;margin-top:10px;" onclick="add();"/>
				<input id="eidtBtn" type="button" value="修 改" class="btn_s1" style="margin-left:5px;margin-top:10px;" onclick="edit();"/>
				<input id="delBtn" type="button" value="删 除" class="btn_s1" style="margin-left:5px;margin-top:10px;" onclick="del();"/>
				<input id="ar1" type="button" value="归档(未)" class="btn_s1" style="margin-left:5px;margin-top:10px;" onclick="conClose(2);"/>
				<input id="ar2" type="button" value="归档(已)" class="btn_s1" style="margin-left:5px;margin-top:10px;" onclick="conClose(3);"/>
				<input id="ar3" type="button" value="解除归档" class="btn_s1" style="margin-left:5px;margin-top:10px;" onclick="conOpen();"/>
			</s:if>
			<input id="invBtn2" type="button" value="已审核" class="btn_s1" style="margin-left:5px;margin-top:10px;" onclick="updateRecieveFlag();"/>
			<s:if test="#request.listFlag == 6">
				<input id="invBtn" type="button" value="开 票" class="btn_s1" style="margin-left:5px;margin-top:10px;" onclick="editCashBusin();"/>
				<input id="completeBtn" type="button" value="完 结" class="btn_s1" style="margin-left:5px;margin-top:10px;" onclick="businComplete(1);"/>
				<input id="completeBtn2" type="button" value="解除完结" class="btn_s1" style="margin-left:5px;margin-top:10px;" onclick="businComplete(0);"/>
			</s:if>
			<s:if test="#request.listFlag == 1">
				<input type="button" value="PRINT FILE" class="btn_s1" style="margin-left:5px;margin-top:10px;width:90px;" onclick="printFile();"/>
				<input type="button" value="PRINT PAYMENT REQUEST" class="btn_s1" style="margin-left:5px;margin-top:10px;width:180px;" onclick="printCost();"/>
			</s:if>
			<input type="button" value="Print Clearance Cost." class="btn_s1" style="margin-left:5px;margin-top:10px;width:150px;" onclick="printCost2();"/>
			<s:if test="#request.listFlag == 6">
				<input id="printInv2" type="button" value="Print Debit" class="btn_s1" style="margin-left:5px;margin-top:10px;width:90px;" onclick="printInvoiceDue();"/>
				<input id="printInv4" type="button" value="Print Reim"  class="btn_s1" style="margin-left:5px;margin-top:10px;width:90px;" onclick="printInvoiceDue2();"/>
				<input id="printInv8" type="button" value="Print Inv.2" class="btn_s1" style="margin-left:5px;margin-top:10px;width:90px;" onclick="printInvoiceRateDue2();"/>
			</s:if>
		</div>
		
		<form id="queryForm" action="" method="post">
			<input type="hidden" id="pageNow" name="pageNow" value="${pageNow}"/>
			<input type="hidden" id="listFlag" name="listFlag" value="${listFlag}"/>
			<input type="hidden" id="businCode" name="businCode" value="${requestScope.businCode}" />
			<input type="hidden" id="businCode2" name="businCode2" value="${requestScope.businCode2}" />
			<input type="hidden" id="typeId" name="query.typeId" value="${requestScope.query.typeId}" />
			<input type="hidden" id="cusId" name="query.cusId" value="${requestScope.query.cusId}" />
			<input type="hidden" id="conNum" name="conNum" value="${requestScope.conNum}" />
			<input type="hidden" id="billNo" name="billNo" value="${requestScope.billNo}" />
			<input type="hidden" id="empName" name="query.empName" value="${requestScope.query.empName}" />
			<input type="hidden" id="startDate" name="startDate" value="${requestScope.startDate}" />
			<input type="hidden" id="endDate" name="endDate" value="${requestScope.endDate}" />
			<input type="hidden" id="businState" name="query.businState" value="${requestScope.query.businState}" />
			<input type="hidden" id="businStateName" name="query.businStateName" value="${requestScope.query.businStateName}" />
			<input type="hidden" id="completeFlag" name="query.completeFlag" value="${requestScope.query.completeFlag}" />
			<input type="hidden" id="startDate2" name="startDate2" value="${requestScope.startDate2}" />
			<input type="hidden" id="endDate2" name="endDate2" value="${requestScope.endDate2}" />
			<input type="hidden" id="businId_" name="businId"  />
			<input type="hidden" id="_businId" name="_businId"  />
			<input type="hidden" id="archiveFlag" name="archiveFlag" value="${requestScope.archiveFlag}"/>
		</form>
		
		<div id="content">
          <div>
          </div>
		</div>
	</div>
	
  </body>
  
</html>

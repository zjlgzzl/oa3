<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title></title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<link type="text/css" rel="stylesheet"  href="${ctx}/res/css/styles.css" />
	<script type='text/javascript' src="${ctx}/dwr/interface/serviceItemService.js"></script>
  	<script type='text/javascript' src="${ctx}/dwr/engine.js"></script>
 	<script type='text/javascript' src="${ctx}/dwr/util.js"></script>
	<script type="text/javascript" src="${ctx}/res/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/res/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/res/js/busin.js"></script>
	<style type="text/css">
		table tr td{
			height:22px;
		}
	</style>
	<script type="text/javascript">
	
		var userCount = "${requestScope.cashUserCount}";
		var maxRows = 10;
		var sumMoney = 0;
		var sumRe = 0;
		
		function addPrint(){
			var tr = document.createElement("tr");
			var td = document.createElement("td");
			td.innerHTML = '';
			tr.appendChild(td);
			td = document.createElement("td");
			td.innerHTML = "";
			tr.appendChild(td);
			td = document.createElement("td");
			td.innerHTML = "";
			tr.appendChild(td);
			document.getElementById("costDetail").appendChild(tr);
		}
	
		//初始化
		$(document).ready(function(){
			sumMoney = "${requestScope.sumMoney}";
			if (sumMoney == null || sumMoney == "" || isNaN(sumMoney)){
				sumMoney = 0;
			}
			sumRe = "${requestScope.sumRe}";
			if (sumRe == null || sumRe == "" || isNaN(sumRe)){
				sumRe = 0;
			}
			var costTab = document.getElementById("costMoney");
			var rows = costTab.rows.length - 2;
			if (rows < maxRows){
				rows = Number(maxRows) - Number(rows)
				for(var i=0; i<rows; i++){
					addPrint();
				}
			}
			computeMoney();
		});
		//返回
		function returnList(){
			document.getElementById("editForm").action = "${ctx}/busin/BusinDetialList.action";
			document.getElementById("editForm").submit();
		}
		//打印
		function printCash(){
			$("#btn").attr("class", "hidden");
			window.print();
			setTimeout("showBtn()", 1000);
		}
		function showBtn(){
			$("#btn").attr("class", "");
		}
		function updateMoney(flag,businId){
			var cmoney = 0;
			if (flag==1){
				cmoney = document.getElementById("depositAmount").value;
			}else{
				cmoney = document.getElementById("cashReceived").value;
			}
			var url="${ctx}/busin/UpdatePrintCost2Money.action?businId="+businId+"&cmoney="+cmoney+"&flag="+flag;
			$.ajax({
				type:"Post",
				url:url,
				success:function(data){
					if (data != ""){
						alert(data);
					}
					//计算金额
					computeMoney();
				}
			});
		}
		
		function computeMoney(){
			var total = 0;
			var grand = 0;
			var exchange = document.getElementById("exchange").value;
			if (exchange == null || exchange == "" || isNaN(exchange)){
			}else{
				total = Number(sumRe)/Number(exchange) + Number(sumMoney);
			}
			var depositAmount = document.getElementById("depositAmount").value;
			if (depositAmount == null || depositAmount == "" || isNaN(depositAmount)){
				depositAmount = 0;
			}
			grand = Number(total) + Number(depositAmount);
			document.getElementById("total").innerHTML = Number(total).toFixed(2);
			document.getElementById("grand").innerHTML = Number(grand).toFixed(2);
		}
		
	</script>
  </head>
  <body>
  	<div id="mainContent">
  		<div style="width:636px;">
  			<form id="editForm" action="${ctx}/busin/FinanceCost.action" method="post">
  				<input type="hidden" name="businId" value="${requestScope.busin.CId}" />
  				<div style="margin-left:10px; width:666px; margin-top:50px;">
					<div style="color:black;font-size:12px;font-weight:bold;text-align:center;width:200px;
						margin:0px auto;">
					 	CLEARANCE COST 
					</div>
				</div>
				<table id="" class="table" style="margin-left:10px; margin-top:15px; width:666px; color:black; border:0px; font-size:8px;" >
					<tr>
						<td style="width:90px;height:0px;border:0px;"></td>
						<td style="width:10px;height:0px;border:0px;"></td>
						<td style="width:180px;height:0px;border:0px;"></td>
						
						<td style="width:60px;height:0px;border:0px;"></td>
						<td style="width:10px;height:0px;border:0px;"></td>
						<td style="width:150px;height:0px;border:0px;"></td>
						
						<td style="width:70px;height:0px;border:0px;"></td>
						<td style="width:10px;height:0px;border:0px;"></td>
						<td style="width:80px;height:0px;border:0px;"></td>
					</tr>
					<tr>
						<td style="text-align:left;border:0px;">
							File
						</td>
						<td style="border:0px;text-align:center;">
							<span>:</span>
						</td>
						<td style="text-align:left;border:0px;">
							${requestScope.busin.CBillNo}
						</td>
						
						<td style="text-align:left;border:0px;">
							CD No.
						</td>
						<td style="border:0px;text-align:center;">
							<span>:</span>
						</td>
						<td style="text-align:left;border:0px;">
							${requestScope.busin.CCdno}
						</td>
						
						<td style="text-align:left;border:0px;">
							CD Date
						</td>
						<td style="border:0px;text-align:center;">
							<span>:</span>
						</td>
						<td style="text-align:left;border:0px;">
							<s:date name="busin.CCdDate" format="dd/MM/yyyy"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:left;border:0px;">
							Ex-Vessel
						</td>
						<td style="border:0px;text-align:center;">
							<span>:</span>
						</td>
						<td style="text-align:left;border:0px;">
							${requestScope.busin.CVessel}
						</td>
						<td style="text-align:left;border:0px;">
							Ref No.
						</td>
						<td style="border:0px;text-align:center;">
							<span>:</span>
						</td>
						<td style="text-align:left;border:0px;">
							${requestScope.busin.CRefno}
						</td>
						<td style="text-align:left;border:0px;">
							Cost Date
						</td>
						<td style="border:0px;text-align:center;">
							<span>:</span>
						</td>
						<td style="text-align:left;border:0px;">
							<s:date name="busin.CCostDate" format="dd/MM/yyyy"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:left;border:0px;">
							Customer
						</td>
						<td style="border:0px;text-align:center;">
							<span>:</span>
						</td>
						<td style="text-align:left;border:0px;" colspan="7">
							${requestScope.busin.TCustomerByCCusid.CAddr}
						</td>
					</tr>
					<tr>
						<td style="text-align:left;border:0px;">
							C/O
						</td>
						<td style="border:0px;text-align:center;">
							<span>:</span>
						</td>
						<td style="text-align:left;border:0px;">
							${requestScope.busin.TCustomerByCCusid2.CAddr}
						</td>
						<td style="text-align:left;border:0px;">
							Apply By
						</td>
						<td style="border:0px;text-align:center;">
							<span>:</span>
						</td>
						<td style="text-align:left;border:0px;" colspan="4">
							${requestScope.busin.TSalerByCApplyby.CName}
						</td>
					</tr>
					<tr>
						<td style="text-align:left;border:0px;">
							B/L No.
						</td>
						<td style="border:0px;text-align:center;">
							<span>:</span>
						</td>
						<td style="text-align:left;border:0px;" colspan="7">
							${requestScope.busin.CTakeNo}
						</td>
					</tr>
					<tr>
						<td style="text-align:left;border:0px;">
							Container No.
						</td>
						<td style="border:0px;text-align:center;">
							<span>:</span>
						</td>
						<td style="text-align:left;border:0px;" colspan="7">
							${requestScope.conNum2}&nbsp;&nbsp;&nbsp;&nbsp;${requestScope.types}
						</td>
					</tr>
					<tr>
						<td style="text-align:left;border:0px;">
							Pkgs/Weight
						</td>
						<td style="border:0px;text-align:center;">
							<span>:</span>
						</td>
						<td style="text-align:left;border:0px;" colspan="7">
							${requestScope.pkgs}
						</td>
					</tr>
					<tr>
						<td style="text-align:left;border:0px;">
							Shipment
						</td>
						<td style="border:0px;text-align:center;">
							<span>:</span>
						</td>
						<td style="text-align:left;border:0px;" colspan="7">
							${requestScope.busin.CShipment}
						</td>
					</tr>
					<tr>
						<td style="text-align:left;border:0px;">
							Exchange
						</td>
						<td style="border:0px;text-align:center;">
							<span>:</span>
						</td>
						<td style="text-align:left;border:0px;" colspan="7">
							${requestScope.busin.CExchange}
							<input type="hidden" id="exchange" value="${requestScope.busin.CExchange}"/>
						</td>
					</tr>
				</table>
				
				<table id="costMoney" class="costTable" style="margin-top:25px; width:666px; border:1px solid black; color:black; margin-left:10px; float:left;">
					<tr>
						<th style="width:330px;font-size:14px;border:1px solid black;height:18px;
									border-bottom:0px;">Description</th>
						<th style="width:200px;font-size:14px;border:1px solid black;height:18px;
									border-bottom:0px;">Riel</th>
						<th style="width:130px;font-size:14px;border:1px solid black;height:18px;
									border-bottom:0px;">USD</th>
					</tr>
					<tbody id="costDetail">
						<s:iterator value="#request.costs" var="costs" status="num">
							<tr id="<s:property value="#num.index" />">
								<td style="text-align:left;border:1px solid black;
											font-size:12px;"><s:property value="TCostItem.CName" /></td>
								<td style="border:1px solid black;font-size:12px;text-align:right;padding-right:5px;">
									<fmt:formatNumber pattern="#,##0.00" value="${CRiel}"></fmt:formatNumber>
								</td>
								<td style="border:1px solid black;font-size:12px;text-align:right;padding-right:5px;">
									<fmt:formatNumber pattern="#,##0.00" value="${CMoney}"></fmt:formatNumber>
								</td>
							</tr>
						</s:iterator>
					</tbody>
					<tr>
						<td>
						</td>
						<td style="font-size:12px;text-align:right;padding-right:5px;font-weight:bold;">
							<fmt:formatNumber pattern="#,##0.00" value="${sumRe}"></fmt:formatNumber>
						</td>
						<td style="font-size:12px;text-align:right;padding-right:5px;font-weight:bold;">
							<fmt:formatNumber pattern="#,##0.00" value="${sumMoney}"></fmt:formatNumber>
						</td>
					</tr>
				</table>
				<table style="margin-top:15px; width:666px; color:black; margin-left:10px;">
					<tr>
						<td style="width:330px;font-size:12px;height:18px;
									border-bottom:0px;">
						</td>
						<td style="width:200px;font-size:12px;height:18px;
									border-bottom:0px;text-align:left;font-weight:bold;">
							Total
						</td>
						<td style="width:130px;font-size:12px;height:18px;
									border-bottom:0px;text-align:right;font-weight:bold;margin-right:5px;">
							<div id="total"></div>
						</td>
					</tr>
					<tr>
						<td style="width:330px;font-size:12px;height:18px;
									border-bottom:0px;">
						</td>
						<td style="width:200px;font-size:12px;height:18px;
									border-bottom:0px;text-align:left;font-weight:bold;">
							Deposit Amount
						</td>
						<td style="width:130px;font-size:12px;height:18px;
									border-bottom:0px;text-align:left;font-weight:bold;margin-right:5px;">
							<input id="depositAmount" type="text" 
									style="text-align:right;width:130px;height:25px;border:0px;font-size:12px;font-weight:bold;" 
									value="${requestScope.busin.CDepositAmount}"
									onchange="updateMoney(1,<s:property value="busin.CId" />);"/>
						</td>
					</tr>
					<tr>
						<td style="width:330px;font-size:12px;height:18px;
									border-bottom:0px;">
						</td>
						<td style="width:200x;font-size:12px;height:18px;
									border-bottom:0px;text-align:left;font-weight:bold;">
							Grand Total
						</td>
						<td style="width:130px;font-size:12px;height:18px;
									border-bottom:0px;text-align:right;font-weight:bold;margin-right:5px;">
							<div id="grand"></div>
						</td>
					</tr>
					<tr>
						<td style="width:330px;font-size:12px;height:18px;
									border-bottom:0px;">
						</td>
						<td style="width:200px;font-size:12px;height:18px;
									border-bottom:0px;text-align:left;font-weight:bold;">
							Cash Received in Advance
						</td>
						<td style="width:130px;font-size:12px;height:18px;
									border-bottom:0px;text-align:left;font-weight:bold;margin-right:5px;">
							<input id="cashReceived" type="text" 
									style="text-align:right;width:130px;height:25px;border:0px;font-size:12px;font-weight:bold;" 
									value="${requestScope.busin.CCashReceived}"
									onchange="updateMoney(2,<s:property value="busin.CId" />);"/>
						</td>
					</tr>
				</table>
				<table style="width:666px; margin-left:10px; margin-top:60px;">
					<tr>
						<td style="width:220px;height:0px;"></td>
						<td style="width:220px;height:0px;"></td>
						<td style="width:220px;height:0px;"></td>
						<td style="width:220px;height:0px;"></td>
					</tr>
					<tr>
						<td style="height:5px;text-align:left;">Approved By</td>
						<td style="height:5px;text-align:left">PP Check By</td>
						<td style="height:5px;text-align:left">Sih Check by</td>
						<td style="height:5px;text-align:left">Issue INV by</td>
					</tr>
				</table>
				<hr style="margin-top:50px;margin-left:10px;width:666px;"/>
				<table id="btn">
					<tr>
						<td colspan="8" style="padding-top:50px; padding-bottom:20px;">
							<input id="printBtn" type="button" value="打印" style="float:left; width:80px;height:25px;margin-left:410px;" onclick="printCash();"/>
							<input id="returnBtn" type="button" value="返回" style="float:left; width:80px;height:25px;margin-left:20px;" onclick="returnList();"/>
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
							<input id="_businId" name="_businId" value="${requestScope._businId}" type="hidden" />
						
						</td>
					</tr>
				</table>
			</form>      
        </div>
  	</div>
  </body>
</html>

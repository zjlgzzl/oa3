<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title> </title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
	<link type="text/css" rel="stylesheet"  href="${ctx}/res/css/styles.css" />
	<script type="text/javascript" src="${ctx}/res/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/res/js/busin.js"></script>
	
	<style type="text/css"> 
		.printTabel{ 
			border-collapse:collapse; 
			width:900px;
			margin-top:15px;
			margin-left:15px;
		}
		.printTabel td{ 
			text-align:left;
			padding-left:20px;
			font-size:18px;
			font-weight:bold;
			color:black;
			height:30px; 
		}
		.printTabel{ 
			table-layout:fixed;
		} 
		.printTabel th { 
			/*background-repeat:repeat-x;*/
			height:30px; 
		} 
		.printTabel td,.printTabel th{ 
			padding:0 1px 0; 
			word-break: break-all; 
			word-wrap:break-word;
		}
	</style>
	
	<script type="text/javascript">
	
		//返回
		function returnList(){
			document.getElementById("editForm").action = "${ctx}/busin/BusinDetialList.action";
			document.getElementById("editForm").submit();
		}
		
		//打印
		function printCost(){
			//打印
			$("#btn").attr("class", "hidden");
			window.print();
			setTimeout("showBtn()", 1000);
		}
		
		function showBtn(){
			$("#btn").attr("class", "");
		}
		
	</script>
  </head>
  <body>
  	<div id="mainContent">
  		<div class="right">
  			<form id="editForm" action="${ctx}/busin/FinanceCost.action" method="post">
  				<input id="businId" type="hidden" name="businId" value="${requestScope.busin.CId}" />
				<table class="printTabel">
					<tr>
						<td style="width:180px;">FILE NO：</td>
						<td style="width:300px;">${requestScope.busin.CBillNo}</td>
						<td style="width:80px;">Date:</td>
						<td style="width:340px;">${requestScope.cdate}</td>
					</tr>
					<tr>
						<td style="width:180px;">PoL/PoD：</td>
						<td style="width:720px;" colspan="3">${requestScope.busin.CArrivalPort}</td>
					</tr>
					<tr>
						<td style="width:180px;">ETD/ETA：</td>
						<td style="width:720px;" colspan="3">${requestScope.CArrivalDate}</td>
					</tr>
					<tr>
						<td style="width:180px;">CUSTOMER：</td>
						<td style="width:720px;" colspan="3">${requestScope.busin.TCustomerByCCusid.CAddr}</td>
					</tr>
					<tr>
						<td style="width:180px;">C/O：</td>
						<td style="width:720px;" colspan="3">${requestScope.busin.TCustomerByCCusid2.CAddr}</td>
					</tr>
					<tr>
						<td style="width:180px;">SHIPPER：</td>
						<td style="width:720px;" colspan="3">${requestScope.busin.CSend}</td>
					</tr>
					<tr>
						<td style="width:180px;">CNEE：</td>
						<td style="width:70px;" colspan="3">${requestScope.busin.CReceive}</td>
					</tr>
					<tr>
						<td style="width:180px;">B/L or AWB No：</td>
						<td style="width:720px;" colspan="3">${requestScope.busin.CTakeNo}</td>
					</tr>
					<tr>
						<td style="width:180px;">COMMODITIES：</td>
						<td style="width:720px;" colspan="3">${requestScope.busin.CGoodsType}</td>
					</tr>
					<tr>
						<td style="width:180px;">HANDLE BY：</td>
						<td style="width:720px;" colspan="3">${requestScope.empName}</td>
					</tr>
					<tr>
						<td style="width:180px;">CNTR NO：</td>
						<td style="width:720px;" colspan="3">
							<table class="printTabel" style="width:720px;margin-left:0px;">
								<s:iterator value="#request.cons" var="cons" status="num">
									<tr id="conTR<s:property value="#num.index" />">
										<td style="width:470px; border-top:0px;">
											${requestScope.cons[num.index].CCount}
										</td>
										<td style="width:250px; border-top:0px;" colspan="2">
											${requestScope.cons[num.index].CContainerType}
										</td>
									</tr>
									<tr>
										<td colspan="3">
											${requestScope.cons[num.index].CContainerNum}
										</td>
									</tr>
								</s:iterator>
							</table>
						</td>
					</tr>
					<!--  
					<tr>
						<td style="width:180px;">COST INFO：</td>
						<td style="width:720px;" colspan="3">
							<table class="printTabel" style="width:720px;margin-left:0px;">
								<s:iterator value="#request.costs" var="costs" status="num">
									<tr id="conTR<s:property value="#num.index" />">
										<td style="width:530px; border-top:0px;">
											${requestScope.costs[num.index].TCostItem.CName}
										</td>
										<td style="width:180px; border-top:0px;">
											${requestScope.costs[num.index].CMoney}
										</td>
									</tr>
								</s:iterator>
							</table>
						</td>
					</tr>
					-->
				</table>
				<table id="btn">
					<tr>
						<td colspan="8" style="padding-top:50px; padding-bottom:20px;">
							<input id="printBtn" type="button" value="打印" style="float:left; width:80px;height:25px;margin-left:600px;" onclick="printCost();"/>
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

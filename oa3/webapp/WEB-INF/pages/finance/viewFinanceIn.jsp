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
	<script type="text/javascript" src="${ctx}/res/js/finance.js"></script>
	<script type="text/javascript">
	
		var detailCount = -1;
		var xuh = 0;

		//后台提示
		$(document).ready(function(){
			
			//后台校验错误信息
			var errInfo = "${requestScope.errInfo}";
			if (errInfo != null && errInfo.length > 0){
				alert(errInfo);
			}
			
			//货柜数量
			var count = "${requestScope.detailCount}";
			if (Number(count) > 0){
				detailCount = Number(count) - 1;//标记从0开始
				xuh = xuh + Number(count);
			}
			
			//现金支票
			var CIsCash = "${requestScope.finance.CIsCash}";
			if (CIsCash != null && CIsCash != -1){
				var select = document.getElementById("CIsCash");
				for(var i=0; i<select.options.length; i++){
					if(select.options[i].value == CIsCash){
						document.getElementById("CIsCash").options[i].selected = true;
						break;
					}
				}
			}
			
			//银行
			var bank = "${requestScope.finance.TBank.CId}";
			if (bank != null && bank != -1){
				var select = document.getElementById("bank");
				for(var i=0; i<select.options.length; i++){
					if(select.options[i].value == bank){
						document.getElementById("bank").options[i].selected = true;
						break;
					}
				}
			}
			
			//收入类型
			var financeType = "${requestScope.finance.TFinancetype.CId}";
			if (financeType != null && financeType != -1){
				var select = document.getElementById("financeType");
				for(var i=0; i<select.options.length; i++){
					if(select.options[i].value == financeType){
						document.getElementById("financeType").options[i].selected = true;
						break;
					}
				}
			}
			
		});
		
		//返回
		function returnList(){
			if (document.getElementById("listFlag").value == 1){
				document.getElementById("editForm").action = "${ctx}/finance/FinanceInList.action";
			}else{
				document.getElementById("editForm").action = "${ctx}/finance/FinanceInList2.action";
			}
			document.getElementById("editForm").submit();
		}
		
		//打印
		function printFinance(){
			document.getElementById("editForm").action = "${ctx}/finance/PrintFinanceIn.action";
			document.getElementById("editForm").submit();
		}
		
	</script>
  </head>
  <body>
  	<div id="mainContent">
  		<div class="right">
			<form id="editForm" action="${ctx}/finance/UpdateFinanceIn.action" method="post">
				<div style="margin-left:5px; width:780px; text-align:center; margin-top:50px;">
					<span style="color:black; font-size:14px; font-weight:bold;">财务收入登记 </span>
				</div>
				<table id="mainTable" class="table" style="margin-top:30px; width:780px; margin-left:5px;">
					<tr>
						<td style="width:100px;">
							<label style="color:black;">日期</label>
						</td>
						<td style="width:150px;">
							<input id="CDate" name="cdate" value="${requestScope.cdate}"
									type="text" class="" style="width:146px;border:0px;" disabled="disabled"/>
							<input name="finance.CId" value="${requestScope.finance.CId}" type="hidden"/>
							<input name="finance.TUser.CId" value="${requestScope.finance.TUser.CId}" type="hidden"/>
							<input name="finance.CType" value="${requestScope.finance.CType}" type="hidden"/>
							<input name="finance.CState" value="${requestScope.finance.CState}" type="hidden"/>
							<input type="hidden" id="listFlag" name="listFlag" value="${requestScope.listFlag}"/>
							<input type="hidden" name="pageNow" value="${pageNow}"/>
							<input type="hidden" name="startDate" value="${requestScope.startDate}"/>
							<input type="hidden" name="endDate" value="${requestScope.endDate}"/>
							<input type="hidden" name="query.ftype" value="${requestScope.query.ftype}"/>
							<input type="hidden" name="query.cpay" value="${requestScope.query.cpay}"/>
							<input type="hidden" name="query.bankName" value="${requestScope.query.bankName}"/>
							<input type="hidden" name="query.empName" value="${requestScope.query.empName}"/>
							<input type="hidden" name="query.archivingFlag" value="${requestScope.query.archivingFlag}"/>
							<input type="hidden" name="detailRemarks" value="${requestScope.detailRemarks}"/>
							<input type="hidden" name="query.cmoney" value="${requestScope.query.cmoney}"/>
						</td>
						<td style="width:100px;">
							<label style="color:black;">服务单号</label>
						</td>
						<td style="width:150px;">
							<input id="CBillNo" name="finance.CBillNo" value="${requestScope.finance.CBillNo}"
									type="text" class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
						<td style="width:100px;">
							<label style="color:black;">收入凭证编号</label>
						</td>
						<td style="width:150px;">
							<input id="CFinanceNo" value="${requestScope.finance.CFinanceNo}"
									type="text" class="" style="width:146px;border:0px;" disabled="disabled"/>
							<input name="finance.CFinanceNo" value="${requestScope.finance.CFinanceNo}" type="hidden"/>
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							<label style="color:black;">收入类型</label>
						</td>
						<td style="width:150px;">
							<select id="financeType" name="finance.TFinancetype.CId" class="" style="width:146px;border:0px;" disabled="disabled">
				           		<option value="-1">--请选择--</option>
								<c:forEach var="financeTypeList" items="${requestScope.financeTypeList}">
									<option value="${financeTypeList.CId}">${financeTypeList.CName}</option>
								</c:forEach>
							</select>
						</td>
						<td style="width:100px;">
							<label style="color:black;">来自</label>
						</td>
						<td colspan="3">
							<input id="CPay" name="finance.CPay" value="${requestScope.finance.CPay}"
									type="text" class="" style="width:402px;border:0px;" disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							<label style="color:black;">现金/支票</label>
						</td>
						<td style="width:150px;">
							<select id="CIsCash" name="finance.CIsCash" class="" style="width:146px;border:0px;" disabled="disabled"> 
				           		<option value="-1">--请选择--</option>
								<option value="1">CASH</option>
								<option value="2">CHEQUE</option>
							</select>
						</td>
						<td style="width:100px;">
							<label style="color:black;">银行</label>
						</td>
						<td style="width:150px;">
							<select id="bank" name="finance.TBank.CId" class="" style="width:146px;border:0px;" disabled="disabled">
				           		<option value="-1">--请选择--</option>
								<c:forEach var="bankList" items="${requestScope.bankList}">
									<option value="${bankList.CId}">${bankList.CName}</option>
								</c:forEach>
							</select>
						</td>
						<td style="width:100px;">
							<label style="color:black;">支票号码</label>
						</td>
						<td style="width:150px;">
							<input id="CCheckNo" name="finance.CCheckNo" value="${requestScope.finance.CCheckNo}"
									type="text" class="" style="width:146px;border:0px;" disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td style="width:100px;" rowspan="2" >
							<label>CLIENT</label>
						</td>
						<td style="width:402px;padding-top:3px;padding-bottom:3px;" colspan="3">
							<select id="TCustomer" name="finance.cus.CId" class="border _select" style="width:402px;margin-top:2px;border:1px solid lightblue;margin-left:2.5px;" onchange="selectClient(1);"
							 disabled="disabled">
				           		<option value="">PLEASE CHOOSE</option>
								<c:forEach var="cusList" items="${requestScope.cusList}">
									<option value="${cusList.CId}" <c:if test="${finance.cus.CId == cusList.CId }">selected</c:if>>${cusList.CName}</option>
								</c:forEach>
							</select>
							<select id="TCustomer2" class="border _select" disabled="disabled"
									style="width:402px;margin-top:2px;border:1px solid lightblue;margin-left:2.5px;" 
									onchange="selectClient(2);">
				           		<option value="">PLEASE CHOOSE</option>
								<c:forEach var="cusList" items="${requestScope.cusList}">
									<option value="${cusList.CId}" <c:if test="${finance.cus.CId == cusList.CId }">selected</c:if>>${cusList.CAddr}</option>
								</c:forEach>
							</select>
						</td>
						<td colspan="2"></td>
					</tr>
				</table>
				<table id="detailTable" class="table" style="margin-top:0; width:780px; margin-left:5px; border-top:0px;">
					<tbody id="detail">
						<tr>
							<td style="width:100px; border-top:0px;">
								<label style="color:black;">序号</label>
							</td>
							<td style="width:150px; border-top:0px;">
								<label style="color:black;">
									收入金额
								</label>
							</td>
							<td style="width:356px; border-top:0px;">
								<label style="color:black;">备注</label>
							</td>
							<td style="width:150px; border-top:0px;">
								
							</td>
						</tr>
						<s:iterator value="#request.detail" var="detail" status="num">
							<tr>
								<td id="xuh<s:property value="#num.index" />">
									<input type="text" id="xuh<s:property value="#num.index" />" 
											value="${requestScope.num.index + 1}"
											style="width:96px;border:0px;text-align:center;" disabled="disabled"/>
									<input name="detail[<s:property value="#num.index" />].CId" 
											value="${requestScope.detail[num.index].CId}" 
											type="hidden"/>
									<input name="detail[<s:property value="#num.index" />].TFinance.CId" 
											value="${requestScope.detail[num.index].TFinance.CId}" 
											type="hidden"/>
								</td>
								<td>
									<input type="text" id="CMoney<s:property value="#num.index" />" 
											name="detail[<s:property value="#num.index" />].CMoney" 
											value="${requestScope.detail[num.index].CMoney}"
											class="" style="width:146px;border:0px;" disabled="disabled"/>
								</td>
								<td>
									<input type="text" id="CRemarks<s:property value="#num.index" />" 
											name="detail[<s:property value="#num.index" />].CRemarks" 
											value="${requestScope.detail[num.index].CRemarks}"
											class="" style="width:352px;border:0px;" disabled="disabled"/>
								</td>
								<td>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				<table id="remarksTable" class="table" style="margin-top:0; width:780px; margin-left:5px; border-top:0px;">
					<tr>
						<td style="width:100px; border-top:0px;">
							<label style="color:black;">描述</label>
						</td>
						<td style="width:662px; border-top:0px;">
							<input id="remarks" name="finance.CDescription" value="${requestScope.finance.CDescription}"
									type="text" class="" style="width:658px;border:0px;" disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td style="width:100px; border-top:0px;">
							<label style="color:black;">备注</label>
						</td>
						<td style="width:662px; border-top:0px;">
							<s:if test="#session.fun510==1">
								<input id="remarks" name="finance.CRemarks" value="${requestScope.finance.CRemarks}"
										type="text" class="" style="width:658px;border:0px;" disabled="disabled"/>
							</s:if>
						</td>
					</tr>
				</table>
		        <div style="margin:10px;">
		        	<input type="button" value="打印" style="margin-left:300px;width:60px;height:20px" onclick="printFinance();"/>
		           	<input type="button" value="返回" class="return" style="width:60px;height:20px" onclick="returnList();"/>
		        </div>
	        </form>
        </div>
  	</div>
  </body>
</html>

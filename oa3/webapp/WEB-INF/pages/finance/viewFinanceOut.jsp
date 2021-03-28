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
			
			//支出类型
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
			 
			//支出选项
			for(var i=0; i<=detailCount; i++){
				var item = document.getElementById("item" + i.toString()).value;
				var select = document.getElementById("CItem" + i.toString());
				for(var j=0; j<select.options.length; j++){
					if(select.options[j].value == item){
						document.getElementById("CItem" + i.toString()).options[j].selected = true;
						break;
					}
				}
				var CNotice = document.getElementById("notice" + i.toString()).value;
				select = document.getElementById("CNotice" + i.toString());
				for(var j=0; j<select.options.length; j++){
					if(select.options[j].value == CNotice){
						document.getElementById("CNotice" + i.toString()).options[j].selected = true;
						break;
					}
				}
			}
			
		});
		
		//返回
		function returnList(){
			for(var i=0; i<=detailCount; i++){
				var cmoney = document.getElementById("CMoney" + i.toString());
				if (cmoney == null){
					continue;
				}
				document.getElementById("CMoney" + i.toString()).value = "";
			}
			if (document.getElementById("listFlag").value == 1){
				document.getElementById("editForm").action = "${ctx}/finance/FinanceOutList.action";
			}else{
				document.getElementById("editForm").action = "${ctx}/finance/FinanceOutList2.action";
			}
			document.getElementById("editForm").submit();
		}
		
		//保存之前的数据校验
		function check(){

			//日期
			var CDate = document.getElementById("CDate").value;
			if ($.trim(CDate) == ""){
				alert("日期不能为空");
				document.getElementById("CDate").focus();
				return false;
			}
			
			//来自
			var CPay = document.getElementById("CPay").value;
			if ($.trim(CPay) == ""){
				alert("付给不能为空"); //////
				document.getElementById("CPay").focus();
				return false;
			}
			
			//现金/支票
			var CIsCash = document.getElementById("CIsCash").value;
			if (CIsCash == -1){
				alert("现金/支票不能为空");
				document.getElementById("CIsCash").focus();
				return false;
			}
			
			//明细
			if (detailCount == -1){
				alert("请增加支出明细");
				return false;
			}
			
			//收入金额、支出选项
			for(var i=0; i<=detailCount; i++){
				var cmoney = document.getElementById("CMoney" + i.toString());
				if (cmoney == null){
					continue;
				}
				cmoney = document.getElementById("CMoney" + i.toString()).value;
				if ($.trim(cmoney) == "" || isNaN(cmoney)){
					alert("支出金额不能为空且必须是数字"); //////////////
					document.getElementById("CMoney" + i.toString()).focus();
					return false;
				}
				var CItem = document.getElementById("CItem" + i.toString()).value;
				if (CItem == -1){
					alert("支出选项不能为空"); //////////////
					document.getElementById("CItem" + i.toString()).focus();
					return false;
				}
			}
			
			return true;
		}
		
		//打印
		function printFinance(){
			document.getElementById("editForm").action = "${ctx}/finance/PrintFinanceOut.action";
			document.getElementById("editForm").submit();
		}
	</script>
  </head>
  <body>
  	<div id="mainContent">
  		<div class="right">
			<form id="editForm" action="${ctx}/finance/UpdateFinanceOut.action" method="post">
				<div style="margin-left:5px; width:780px; text-align:center; margin-top:50px;">
					<span style="color:black; font-size:14px; font-weight:bold;">财务支出登记 </span>
				</div>
				<table id="mainTable" class="table" style="margin-top:30px; width:780px; margin-left:5px;">
					<tr>
						<td style="width:100px;">
							<label style="color:black;">日期</label>
						</td>
						<td style="width:150px;">
							<input id="CDate" name="cdate" value="${requestScope.cdate}"
									type="text" style="width:146px;border:0px;" disabled="disabled"/>
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
									type="text" style="width:146px; border:0px;" disabled="disabled"/>
						</td>
						<td style="width:100px;">
							<label style="color:black;">支出凭证编号</label>
						</td>
						<td style="width:150px;">
							<input id="CFinanceNo" value="${requestScope.finance.CFinanceNo}"
									type="text" style="width:146px; border:0px;" disabled="disabled"/>
							<input name="finance.CFinanceNo" value="${requestScope.finance.CFinanceNo}" type="hidden"/>
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							<label style="color:black;">支出类型</label>
						</td>
						<td style="width:150px;">
							<select id="financeType" name="finance.TFinancetype.CId" class="" style="width:146px;border:0px;" disabled="disabled">
				           		<option value="-1"></option>
								<c:forEach var="financeTypeList" items="${requestScope.financeTypeList}">
									<option value="${financeTypeList.CId}">${financeTypeList.CName}</option>
								</c:forEach>
							</select>
						</td>
						<td style="width:100px;">
							<label style="color:black;">付给</label>
						</td>
						<td colspan="3">
							<input id="CPay" name="finance.CPay" value="${requestScope.finance.CPay}"
									type="text" style="width:402px; border:0px;" disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							<label style="color:black;">现金/支票</label>
						</td>
						<td style="width:150px;">
							<select id="CIsCash" name="finance.CIsCash" style="width:146px; border:0px;" disabled="disabled">
				           		<option value="-1">--请选择--</option>
								<option value="1">CASH</option>
								<option value="2">CHEQUE</option>
							</select>
						</td>
						<td style="width:100px;">
							<label style="color:black;">银行</label>
						</td>
						<td style="width:150px;">
							<select id="bank" name="finance.TBank.CId" style="width:146px; border:0px;" disabled="disabled">
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
									type="text" style="width:146px; border:0px;" disabled="disabled"/>
						</td>
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
									支出金额
								</label>
							</td>
							<td style="width:100px; border-top:0px;">
								<label style="color:black;">
									支出选项
								</label>
							</td>
							<td style="width:150px; border-top:0px;">
								<label style="color:black;">备注</label>
							</td>
							<td style="width:100px; border-top:0px;">
								<label style="color:black;">是否提醒</label>
							</td>
							<td style="width:150px; border-top:0px;">
							</td>
						</tr>
						<s:iterator value="#request.detail" var="detail" status="num">
							<tr>
								<td>
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
											style="width:146px; border:0px;" disabled="disabled"/>
								</td>
								<td>
									<select id="CItem<s:property value="#num.index" />" 
											name="detail[<s:property value="#num.index" />].CItem" 
											style="width:96px;border:0px;" disabled="disabled">
						           		<option value="-1">--请选择--</option>
										<option value="临时支出">临时支出</option>
										<option value="永久支出">永久支出</option>
									</select>
									<input type="hidden" id="item<s:property value="#num.index" />"
											value="${requestScope.detail[num.index].CItem}" />
								</td>
								<td>
									<input type="text" id="CRemarks<s:property value="#num.index" />" 
											name="detail[<s:property value="#num.index" />].CRemarks" 
											value="${requestScope.detail[num.index].CRemarks}"
											style="width:146px;border:0px;" disabled="disabled"/>
								</td>
								<td>
									<select id="CNotice<s:property value="#num.index" />" 
											name="detail[<s:property value="#num.index" />].CNotice" 
											style="width:96px;border:0px;" disabled="disabled">
										<option value="1">提醒</option>
										<option value="2">不提醒</option>
									</select>
									<input type="hidden" id="notice<s:property value="#num.index" />"
											value="${requestScope.detail[num.index].CNotice}" />
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
							<s:if test="#session.fun511==1">
								<input id="remarks" name="finance.CRemarks" value="${requestScope.finance.CRemarks}"
										type="text" style="width:658px;border:0px;" disabled="disabled"/>
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

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
	<link type="text/css" rel="stylesheet"  href="${ctx}/res/css/combo.select.css"/>
	<script type="text/javascript" src="${ctx}/res/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/res/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/res/js/jquery.combo.select.js"></script>
	<script type="text/javascript" src="${ctx}/res/js/finance.js"></script>
	<script type="text/javascript">
	
		var detailCount = -1;
		var xuh = 0;
		var fun509 = 0;
		
		//后台提示
		$(document).ready(function(){
		
			fun509 = "${sessionScope.fun509}";
			
			//后台校验错误信息
			var errInfo = "${requestScope.errInfo}";
			if (errInfo != null && errInfo.length > 0){
				alert(errInfo);
			}
			
			//明细数量
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
			/*
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
			}*/
			
			$('._select').comboSelect();
			
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
			document.getElementById("editForm").action = "${ctx}/finance/FinanceOutList.action";
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
			
			//支出类型
			var financeType = document.getElementById("financeType").value;
			if (financeType == -1){
				alert("支出类型不能为空");
				document.getElementById("financeType").focus();
				return false;
			}
			
			//银行
			var bank = document.getElementById("bank").value;
			if (bank == -1){
				alert("银行不能为空");
				document.getElementById("bank").focus();
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
				/*
				var CItem = document.getElementById("CItem" + i.toString()).value;
				if (CItem == -1){
					alert("支出选项不能为空"); //////////////
					document.getElementById("CItem" + i.toString()).focus();
					return false;
				}*/
			}
			
			return true;
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
							<span style="color:red;">*</span><label style="color:black;">日期</label>
						</td>
						<td style="width:150px;">
							<s:if test="#session.userID == #request.finance.TUser.CId && #request.finance.CState == 1">
								<input id="CDate" name="cdate" value="${requestScope.cdate}"
									type="text" class="field border" style="width:146px" onclick="WdatePicker()" readonly="readonly"/>
							</s:if>
							<s:else>
								<input id="CDate" value="${requestScope.cdate}"
									type="text" class="" style="width:146px;border:0px;" disabled="disabled"/>
								<input id="CDate" name="cdate" value="${requestScope.cdate}"
									type="hidden" />
							</s:else>
							<input name="finance.CId" value="${requestScope.finance.CId}" type="hidden"/>
							<input name="finance.TUser.CId" value="${requestScope.finance.TUser.CId}" type="hidden"/>
							<input name="finance.CType" value="${requestScope.finance.CType}" type="hidden"/>
							<input name="finance.CState" value="${requestScope.finance.CState}" type="hidden"/>
							<input name="finance.CArchivingFlag" value="${requestScope.finance.CArchivingFlag}" type="hidden"/>
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
							<s:if test="#session.userID == #request.finance.TUser.CId && #request.finance.CState == 1">
								<input id="CBillNo" name="finance.CBillNo" value="${requestScope.finance.CBillNo}"
									type="text" class="field border" style="width:146px"/>
							</s:if>
							<s:else>
								<input id="CBillNo" value="${requestScope.finance.CBillNo}"
									type="text" class="" style="width:146px;border:0px;" disabled="disabled"/>
								<input name="finance.CBillNo" value="${requestScope.finance.CBillNo}" type="hidden" />
							</s:else>
						</td>
						<td style="width:100px;">
							<label style="color:black;">支出凭证编号</label>
						</td>
						<td style="width:150px;">
							<input id="CFinanceNo" value="${requestScope.finance.CFinanceNo}"
									type="text" class="field border" style="width:146px" disabled="disabled"/>
							<input name="finance.CFinanceNo" value="${requestScope.finance.CFinanceNo}" type="hidden"/>
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							<span style="color:red;">*</span><label style="color:black;">支出类型</label>
						</td>
						<td style="width:150px;">
							<s:if test="#session.userID == #request.finance.TUser.CId && #request.finance.CState == 1">
								<select id="financeType" name="finance.TFinancetype.CId" class="border _select" style="width:146px;">
					           		<option value="-1">--请选择--</option>
									<c:forEach var="financeTypeList" items="${requestScope.financeTypeList}">
										<option value="${financeTypeList.CId}">${financeTypeList.CName}</option>
									</c:forEach>
								</select>
							</s:if>
							<s:else>
								<select id="financeType" class="" style="width:146px;border:0px;" disabled="disabled">
					           		<option value="-1">--请选择--</option>
									<c:forEach var="financeTypeList" items="${requestScope.financeTypeList}">
										<option value="${financeTypeList.CId}">${financeTypeList.CName}</option>
									</c:forEach>
								</select>
								<input name="finance.TFinancetype.CId" value="${finance.TFinancetype.CId}" type="hidden"/>
							</s:else>
						</td>
						<td style="width:100px;">
							<span style="color:red;">*</span><label style="color:black;">付给</label>
						</td>
						<td colspan="3">
							<s:if test="#session.userID == #request.finance.TUser.CId && #request.finance.CState == 1">
								<input id="CPay" name="finance.CPay" value="${requestScope.finance.CPay}"
									type="text" class="field border" style="width:402px"/>
							</s:if>
							<s:else>
								<input id="CPay" value="${requestScope.finance.CPay}"
									type="text" class="" style="width:402px;border:0px;" disabled="disabled" />
								<input name="finance.CPay" value="${requestScope.finance.CPay}" type="hidden" />
							</s:else>
						</td>
					</tr>
					<tr>
						<td style="width:100px;">
							<span style="color:red;">*</span><label style="color:black;">现金/支票</label>
						</td>
						<td style="width:150px;">
							<s:if test="#session.userID == #request.finance.TUser.CId && #request.finance.CState == 1">
								<select id="CIsCash" name="finance.CIsCash" class="border" style="width:146px;">
					           		<option value="-1">--请选择--</option>
									<option value="1">CASH</option>
									<option value="2">CHEQUE</option>
								</select>
							</s:if>
							<s:else>
								<select id="CIsCash" class="" style="width:146px;border:0px;" disabled="disabled">
					           		<option value="-1">--请选择--</option>
									<option value="1">CASH</option>
									<option value="2">CHEQUE</option>
								</select>
								<input name="finance.CIsCash" value="${requestScope.finance.CIsCash}" type="hidden"/>
							</s:else>
						</td>
						<td style="width:100px;">
							<span style="color:red;">*</span><label style="color:black;">银行</label>
						</td>
						<td style="width:150px;">
							<s:if test="#session.userID == #request.finance.TUser.CId && #request.finance.CState == 1">
								<select id="bank" name="finance.TBank.CId" class="border" style="width:146px;">
					           		<option value="-1">--请选择--</option>
									<c:forEach var="bankList" items="${requestScope.bankList}">
										<option value="${bankList.CId}">${bankList.CName}</option>
									</c:forEach>
								</select>
							</s:if>
							<s:else>
								<select id="bank" class="" style="width:146px;border:0px;" disabled="disabled">
					           		<option value="-1">--请选择--</option>
									<c:forEach var="bankList" items="${requestScope.bankList}">
										<option value="${bankList.CId}">${bankList.CName}</option>
									</c:forEach>
								</select>
								<input name="finance.TBank.CId" value="${requestScope.finance.TBank.CId}" type="hidden" />
							</s:else>
						</td>
						<td style="width:100px;">
							<label style="color:black;">支票号码</label>
						</td>
						<td style="width:150px;">
							<s:if test="#session.userID == #request.finance.TUser.CId && #request.finance.CState == 1">
								<input id="CCheckNo" name="finance.CCheckNo" value="${requestScope.finance.CCheckNo}"
									type="text" class="field border" style="width:146px"/>
							</s:if>
							<s:else>
								<input id="CCheckNo" value="${requestScope.finance.CCheckNo}"
									type="text" class="" style="width:146px;border:0px;" disabled="disabled"/>
								<input name="finance.CCheckNo" value="${requestScope.finance.CCheckNo}"
									type="hidden"/>
							</s:else>
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
									<span style="color:red;">*</span>支出金额
								</label>
							</td>
							<td style="width:356px; border-top:0px;">
								<label style="color:black;">备注</label>
							</td>
							<td style="width:150px; border-top:0px;">
								<s:if test="#session.userID == #request.finance.TUser.CId && #request.finance.CState == 1">
									<input type="button" value="增加"
										style="width:60px;height:20px" onclick="addDetailOut();"/>
								</s:if>
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
									<s:if test="#session.userID == #request.finance.TUser.CId && #request.finance.CState == 1">
										<input type="text" id="CMoney<s:property value="#num.index" />" 
												name="detail[<s:property value="#num.index" />].CMoney" 
												value="${requestScope.detail[num.index].CMoney}"
												class="field border" style="width:146px;"/>
									</s:if>
									<s:else>
										<input type="text" id="CMoney<s:property value="#num.index" />" 
												value="${requestScope.detail[num.index].CMoney}"
												class="" style="width:146px;border:0px;" disabled="disabled"/>
										<input type="hidden"
												name="detail[<s:property value="#num.index" />].CMoney" 
												value="${requestScope.detail[num.index].CMoney}"/>
									</s:else>
								</td>
								<td>
									<s:if test="#session.fun509 == 1">
										<input type="text" id="CRemarks<s:property value="#num.index" />" 
												name="detail[<s:property value="#num.index" />].CRemarks" 
												value="${requestScope.detail[num.index].CRemarks}"
												class="field border" style="width:352px;"/>
									</s:if>
									<s:else>
										<input type="text" id="CRemarks<s:property value="#num.index" />" 
												value="${requestScope.detail[num.index].CRemarks}"
												class="" style="width:352px;border:0px;" disabled="disabled"/>
										<input type="hidden" 
												name="detail[<s:property value="#num.index" />].CRemarks" 
												value="${requestScope.detail[num.index].CRemarks}"
												/>
									</s:else>
								</td>
								<td>
									<s:if test="#session.userID == #request.finance.TUser.CId && #request.finance.CState == 1">
										<input type="button" value="删除" style="width:60px; height:20px;"
												onclick = "delDetail(this)" />
									</s:if>
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
							<s:if test="#session.userID == #request.finance.TUser.CId && #request.finance.CState == 1">
								<input id="remarks" name="finance.CDescription" value="${requestScope.finance.CDescription}"
									type="text" class="field border" style="width:658px"/>
							</s:if>
							<s:else>
								<input id="remarks" value="${requestScope.finance.CDescription}"
									type="text" class="" style="width:658px;border:0px;" disabled="disabled" />
								<input name="finance.CDescription" value="${requestScope.finance.CDescription}" type="hidden" />
							</s:else>
						</td>
					</tr>
					<tr>
						<td style="width:100px; border-top:0px;">
							<label style="color:black;">备注</label>
						</td>
						<td style="width:662px; border-top:0px;">
							<s:if test="#session.fun511==1">
								<input id="remarks" name="finance.CRemarks" value="${requestScope.finance.CRemarks}"
									type="text" class="field border" style="width:658px"/>
							</s:if>
							<s:else>
								<input name="finance.CRemarks" value="${requestScope.finance.CRemarks}"
									type="hidden"/>
							</s:else>
						</td>
					</tr>
				</table>
		        <div style="margin:10px;">
		           	<input type="submit" value="保存" class="save" style="width:60px;height:20px;margin-left:300px;" onclick="return check();"/>
		           	<input type="button" value="返回" class="return" style="width:60px;height:20px" onclick="returnList();"/>
		        </div>
	        </form>
        </div>
  	</div>
  </body>
</html>

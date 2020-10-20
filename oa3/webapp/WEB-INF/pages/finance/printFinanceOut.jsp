<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title> </title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<link type="text/css" rel="stylesheet"  href="${ctx}/res/css/styles.css" />
	<script type="text/javascript" src="${ctx}/res/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/res/js/busin.js"></script>
	
	<style type="text/css">
		td{
			height:15px;
		}
	</style>
	
	<script type="text/javascript">

		//初始化
		$(document).ready(function(){
			var sumMoney = Number("${requestScope.sumMoney}");
			var tmp = Translate(parseInt(sumMoney).toString());
			var tmp2 = sumMoney.toString();
			var pos = tmp2.indexOf(".") + 1;
			if (pos <= 0){
				tmp2 = " Only";
			}else{
				tmp2 = " And Cents " + Translate(tmp2.substring(pos)) + " Only";
			}
			document.getElementById("sumMoney").innerHTML = tmp + tmp2;
		});
		
		//返回
		function returnList(){
			//history.go(-1);
			var id = document.getElementById("financeId").value;
			document.getElementById("editForm").action = "${ctx}/finance/ViewFinanceOut.action?id="+id;
			document.getElementById("editForm").submit();
		}
		
		//打印
		function printCost(){
			$("#btn").attr("class", "hidden");
			window.print();
			setTimeout("showBtn()", 1000);
			//更新状态
			document.getElementById("editForm").submit();
		}
		function showBtn(){
			$("#btn").attr("class", "");
		}
		
		var arr1=new Array(""," Thousand"," Million"," Billion")
		var arr2=new Array("Zero","Ten","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety")
		var arr3=new Array("Zero","One","Two","Three","Four","Five","Six","Sever","Eight","Nine");
		var arr4=new Array("Ten","Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen");
		function Translate(num){
			var len=num.length,i,j=0,strRet="";
			var cols=Math.ceil(len/3);
			var first=len-cols*3
			var strRet=""
			for(i=first;i<len;i+=3){
				++j;
				if(i>=0)
					num3=num.substring(i,i+3)
				else
					num3=num.substring(0,first+3)
				strEng=English(num3)
				if(strEng!=""){
					if(strRet!="")	strRet+=" "	
					strRet+=English(num3)+arr1[cols-j]
				}
			}
			return strRet
		}
		function English(num){
			strRet=""
			if((num.length==3) && (num.substr(0,3)!="000")){
				if((num.substr(0,1)!="0")){
					strRet+=arr3[num.substr(0,1)]+" Hundred"
					if(num.substr(1,2)!="00")strRet+=" and "
				}
				num=num.substring(1);
			}
			if((num.length==2)){
				if((num.substr(0,1)=="0")){
					num=num.substring(1)
				}
				else if((num.substr(0,1)=="1")){
					strRet+=arr4[num.substr(1,2)]
				}
				else{
					strRet+=arr2[num.substr(0,1)]
					if(num.substr(1,1)!="0")strRet+="-"
					num=num.substring(1)
				}
			}
			if((num.length==1) && (num.substr(0,1)!="0")){
				strRet+=arr3[num.substr(0,1)]
			}
			return strRet;
		}
	
	</script>
  </head>
  <body>
  	<div id="mainContent">
  		<div class="right">
  			<form id="editForm" action="${ctx}/finance/UpdateOutState.action" method="post">
  				<div style="margin-left:1px; width:1000px; margin-top:30px;">
  					<div style="float:left; margin-top:20px; margin-left:350px; ">
  						<span style="color:black; font-size:14px; font-weight:bold; text-decoration:underline">
							PAYMENT VOUCHER
						</span>
  					</div>
  					<input id="financeId" name="finance.CId" value="${requestScope.finance.CId}" type="hidden"/>
  					<div class="clear"></div>
				</div>
				<table style="margin-top:35px; width:820px; color:black; border:0px;" >
					<tr>
						<td style="width:120px;"></td>
						<td style="width:100px;"></td>
						<td style="width:150px;"></td>
						<td style="width:150px;"></td>
						<td style="width:150px;"></td>
						<td style="width:150px;"></td>
					</tr>
					<tr>
						<td colspan="4" style="width:540px;text-align:left;">
							${requestScope.ftype}
						</td>
						<td style="width:150px;text-align:right;padding-right:20px;">Ref:</td>
						<td style="width:150px;border-bottom:1px black solid;text-align:left;">
							${requestScope.finance.CFinanceNo}
						</td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="4" style="width:540px;text-align:left;">
							${requestScope.bank}
						</td>
						<td style="width:150px;text-align:right;padding-right:20px;">File No.:</td>
						<td style="width:150px;border-bottom:1px black solid;text-align:left;">
							${requestScope.finance.CBillNo}
						</td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td style="text-align:left;">Pay to:</td>
						<td style="border-bottom:1px black solid;text-align:left;">
							${requestScope.finance.CPay}
						</td>
						<td style="text-align:right;">C'nee:</td>
						<td style="border-bottom:1px black solid;text-align:left;">
							
						</td>
						<td style="border:0px;text-align:right;padding-right:20px;">Date:</td>
						<td style="border-bottom:1px black solid;text-align:left;">
							${requestScope.cdate}
						</td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td style="text-align:left;">Con't No:</td>
						<td colspan="5" style="text-align:left;border-bottom:1px black solid;">
							
						</td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td style="text-align:left;">Payment Amount:</td>
						<td style="text-align:right; padding-right:10px;">Cash</td>
						<td style="border:1px black solid;text-align:left;">
							<s:if test="#request.finance.CIsCash == 1">
								USD:<fmt:formatNumber pattern="0.00" value="${requestScope.sumMoney}"></fmt:formatNumber>
							</s:if>
						</td>
						<td style="text-align:right; padding-right:20px;">Cheque/No.:</td>
						<td style="border:1px black solid;text-align:left;">
							<s:if test="#request.finance.CIsCash == 2">
								USD:<fmt:formatNumber pattern="0.00" value="${requestScope.sumMoney}"></fmt:formatNumber>
							</s:if>
						</td>
						<td style="border:1px black solid;text-align:left;">
							${requestScope.finance.CCheckNo}
						</td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td style="text-align:left;">Amount in word:</td>
						<td colspan="5" style="text-align:left;border-bottom:1px black solid;">
							<div id="sumMoney"></div>
						</td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td style="text-align:left;border-bottom:1px black solid;">Description:</td>
						<td colspan="5" style="text-align:left;border-bottom:1px black solid;">
							${requestScope.finance.CDescription}
						</td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td style="text-align:left;border-bottom:1px black solid;">Remarks:</td>
						<td colspan="5" style="text-align:left;border-bottom:1px black solid;">
							<s:if test="#session.fun511==1">
								${requestScope.finance.CRemarks}
							</s:if>
						</td>
					</tr>
					<tr>
						<td style="height:80px;"></td>
						<td style="height:80px;"></td>
						<td style="height:80px;"></td>
						<td style="height:80px;"></td>
						<td style="height:80px;"></td>
						<td style="height:80px;"></td>
					</tr>
					<tr>
						<td style="text-align:left;">
							<input style="border:0px; border-bottom:1px black solid; width:100px;" readonly="readonly"/>
						</td>
						<td style="text-align:center;">
							<input style="border:0px; border-bottom:1px black solid; width:80px;" readonly="readonly"/>
						</td>
						<td style="text-align:center;">
							<input style="border:0px; border-bottom:1px black solid; width:120px;" readonly="readonly"/>
						</td>
						<td style="text-align:center;">
							<input style="border:0px; border-bottom:1px black solid; width:120px;" readonly="readonly"/>
						</td>
						<td style="text-align:center;">
							<input style="border:0px; border-bottom:1px black solid; width:120px;" readonly="readonly"/>
						</td>
						<td style="text-align:right;">
							<input style="border:0px; border-bottom:1px black solid; width:120px;" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">
							M.Director
						</td>
						<td style="text-align:center;">
							Approved by
						</td>
						<td style="text-align:center;">
							Accountant/Cashier
						</td>
						<td style="text-align:center;">
							Verified by
						</td>
						<td style="text-align:center;">
							Acknowledged by
						</td>
						<td style="text-align:center;">
							Received by
						</td>
					</tr>
					<tr id="btn">
						<td colspan="6" style="padding-top:50px; padding-bottom:20px;">
							<input type="hidden" id="listFlag" name="listFlag" value="${requestScope.listFlag}"/>
							<input id="printBtn" type="button" value="打印" style="float:left; width:80px;height:25px;margin-left:600px;" onclick="printCost();"/>
							<input id="returnBtn" type="button" value="返回" style="float:left; width:80px;height:25px;margin-left:20px;" onclick="returnList();"/>
						</td>
					</tr>
				</table>
			</form>      
        </div>
  	</div>
  </body>
</html>

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
	<script type="text/javascript">
	
		var userCount = "${requestScope.cashUserCount}";
		var maxRows = 12;
		var cashMaxRows = 8;
	
		//初始化
		$(document).ready(function(){
			addCashPrint();
		});
		
		//返回
		function returnList(){
			//document.getElementById("editForm").action = "${ctx}/busin/FinanceCash.action";
			//document.getElementById("editForm").submit();
			history.go(-1);
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
  			<form id="editForm" action="${ctx}/busin/FinanceCost.action" method="post">
  				<input type="hidden" name="businId" value="${requestScope.busin.CId}" />
  				
  					
			  				<div style="margin-left:5px; width:900px; margin-top:30px;">
				  					<div style="margin-top:20px;margin-left:300px;">
				  						<span style="color:black; font-size:20px; font-weight:bold; text-decoration:underline">
											INVOICE
										</span>
				  					</div>
				  					<div class="clear"></div>
							</div>
							<table id="" class="table" style="margin-top:35px; width:920px; color:black; border:0px;" >
								<tr>
									<td style="width:60px;height:0px;border:0px;"></td>
									<td style="width:400px;height:0px;border:0px;"></td>
									<td style="width:120px;height:0px;border:0px;"></td>
									<td style="width:20px;height:0px;border:0px;"></td>
									<td style="width:320px;height:0px;border:0px;"></td>
								</tr>
								<tr>
									<td style="border:0px;">
										<span style="font-size:14px; font-weight:bold;">To:</span>
									</td>
									<td style="text-align:left;border:0px;">
										<span style="font-size:14px; font-weight:bold;">${requestScope.cus.CName}</span>
									</td>
									<td style="border:0px;text-align:left;">
										<span style="font-weight:bold;">No-Date</span>
									</td>
									<td style="border:0px;text-align:center;">
										<span style="font-weight:bold;">:</span>
									</td>
									<td colspan="3" style="text-align:left;border:0px;">
										
									</td>
								</tr>
								<tr>
									<td style="border:0px;"></td>
									<td style="text-align:left;border:0px;">
										${requestScope.cus.CAddr}
									</td>
									<td style="border:0px;text-align:left;">
										File No.
									</td>
									<td style="border:0px;text-align:center;">
										<span>:</span>
									</td>
									<td colspan="3" style="text-align:left;border:0px;">
										${requestScope.busin.CBillNo}
									</td>
								</tr>
								<tr>
									<td style="border:0px;"></td>
									<td style="text-align:left;border:0px;">
										Tel: ${requestScope.cus.CPhone}
									</td>
									<td style="border:0px;text-align:left;">
										B/L or Awb No.
									</td>
									<td style="border:0px;text-align:center;">
										<span>:</span>
									</td>
									<td colspan="3" style="text-align:left;border:0px;">
										${requestScope.busin.CTakeNo}
									</td>
								</tr>
								<tr>
									<td style="border:0px;"></td>
									<td style="text-align:left;border:0px;">
										<span style="color:black; font-size:14px; font-weight:bold;">
											VAT No. 
										</span>
									</td>
									<td style="border:0px;text-align:left;">
										Fty Inv#
									</td>
									<td style="border:0px;text-align:center;">
										<span>:</span>
									</td>
									<td colspan="3" style="text-align:left;border:0px;">
										
									</td>
								</tr>
								<tr>
									<td style="border:0px;"></td>
									<td style="text-align:left;border:0px;">
										
									</td>
									<td style="border:0px;text-align:left;">
										Container No.
									</td>
									<td style="border:0px;text-align:center;">
										<span>:</span>
									</td>
									<td colspan="3" style="text-align:left;border:0px;">
										${requestScope.containerNum}
									</td>
								</tr>
								<tr>
									<td style="border:0px;"></td>
									<td style="text-align:left;border:0px;">
										
									</td>
									<td style="border:0px;text-align:left;">
										Weight
									</td>
									<td style="border:0px;text-align:center;">
										<span>:</span>
									</td>
									<td colspan="3" style="text-align:left;border:0px;">
										${requestScope.weight}
									</td>
								</tr>
								<tr>
									<td style="border:0px;"></td>
									<td style="text-align:left;border:0px;">
										
									</td>
									<td style="border:0px;text-align:left;">
										Commodity
									</td>
									<td style="border:0px;text-align:center;">
										<span>:</span>
									</td>
									<td colspan="3" style="text-align:left;border:0px;">
										
									</td>
								</tr>
								<tr>
									<td style="border:0px;"></td>
									<td style="text-align:left;border:0px;">
										
									</td>
									<td style="border:0px;text-align:left;">
										Type of ship.
									</td>
									<td style="border:0px;text-align:center;">
										<span>:</span>
									</td>
									<td colspan="3" style="text-align:left;border:0px;">
										
									</td>
								</tr>
								<tr>
									<td style="border:0px;"></td>
									<td style="text-align:left;border:0px;">
										
									</td>
									<td style="border:0px;text-align:left;">
										Exchange Rate
									</td>
									<td style="border:0px;text-align:center;">
										<span>:</span>
									</td>
									<td colspan="3" style="text-align:left;border:0px;">
										
									</td>
								</tr>
							</table>
							<table id="costMoney" class="costTable" style="width:620px; border:1px solid black; margin-top:20px; color:black; margin-left:10px; float:left;">
								<tr>
									<th style="width:40px;border:1px solid black;">Item</th>
									<th style="width:300px;border:1px solid black;">Description</th>
									<th style="width:80px;border:1px solid black;">Q'Ty</th>
									<th style="width:100px;border:1px solid black;">Unit Price</th>
									<th style="width:100px;border:1px solid black;">Amount</th>
								</tr>
								<tbody id="cashDetail">
									<s:iterator value="#request.cash" var="cash" status="cashNum">
											<tr id="<s:property value="#cashNum.index" />">
												<td style="border:1px solid black;">
													<input id="xuh<s:property value="#cashNum.index" />" 
															style="border:0px;width:38px;text-align:center;" readonly="readonly"/>
												</td>
												<td style="text-align:left;border:1px solid black;"><s:property value="TCostItem.CName" /></td>
												<td style="border:1px solid black;"></td>
												<td style="border:1px solid black;"></td>
												<td style="border:1px solid black;">
													$<fmt:formatNumber pattern="0.00" value="${CMoney}"></fmt:formatNumber>
													<input id='money<s:property value="#cashNum.index" />' 
															value="$<s:property value="CMoney" />" 
															style="border:0px;width:90px;text-align:center" 
															type="hidden" readonly="readonly"/>
												</td>
											</tr>
									</s:iterator>
									<s:iterator value="#request.rate" var="rate" status="rateNum">
											<tr id="<s:property value="#request.cash.size() + #rateNum.index" />">
												<td style="border:1px solid black;">
													<input id="xuh<s:property value="#request.cash.size() + #rateNum.index" />" 
															style="border:0px;width:38px;text-align:center;" readonly="readonly"/>
												</td>
												<td style="text-align:left;border:1px solid black;"><s:property value="TCostItem.CName" /></td>
												<td style="border:1px solid black;"></td>
												<td style="border:1px solid black;"></td>
												<td style="border:1px solid black;">
													$<fmt:formatNumber pattern="0.00" value="${CMoney}"></fmt:formatNumber>
													<input id='money<s:property value="#request.cash.size() + #rateNum.index" />' 
															value="$<s:property value="CMoney" />" 
															style="border:0px;width:90px;text-align:center" 
															type="hidden" readonly="readonly"/>
												</td>
											</tr>
									</s:iterator>
								</tbody>
							</table>
							<div class="clear"></div>
							<div id="totalDollars" style="margin-left:10px; margin-top:30px; font-weight:bold;">
							</div>
							<div style="margin-left:500px; margin-top:50px;">
								<input type="text" style="border:0px;border-bottom:1px black solid;width:200px;" readonly="readonly"/>
							</div>
							<div style="margin-left:505px; margin-top:5px;">
								<span>Authorized Signature & stamp</span>
							</div>
						
					
						
					<table id="btn">
						<tr>
							<td colspan="8" style="padding-top:50px; padding-bottom:20px;">
								<input id="printBtn" type="button" value="打印" style="float:left; width:80px;height:25px;margin-left:450px;" onclick="printCash();"/>
								<input id="returnBtn" type="button" value="返回" style="float:left; width:80px;height:25px;margin-left:20px;" onclick="returnList();"/>
							</td>
						</tr>
					</table>
			</form>      
        </div>
  	</div>
  </body>
</html>

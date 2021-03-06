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
		#editForm { 
		   font-size:12px;
		}
		table tr td{
			height:17px;
			border-top:0px;
			border-bottom:0px;
		}
	</style>
	<script type="text/javascript">
	
		var userCount = "${requestScope.cashUserCount}";
		var maxRows = 12;
		var cashMaxRows = 13;
	
		//初始化
		$(document).ready(function(){
			addCashPrint(5);
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
		var arr3=new Array("Zero","One","Two","Three","Four","Five","Six","Seven","Eight","Nine");
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
  		<div style="width:666px;">
  			<form id="editForm" action="${ctx}/busin/FinanceCost.action" method="post">
  				<input type="hidden" name="businId" value="${requestScope.busin.CId}" />
  				<div style="margin-left:2px; width:666px; margin-top:190px;">
	  					<div style="margin-top:10px;text-align:center;">
	  						<span style="color:black; font-size:16px;border-bottom:1px solid black;">
								<span style="font-size:20px;">វិក្កយបត្រអាករ</span>/TAX INVOICE
							</span>
	  					</div>
	  					<div class="clear"></div>
				</div>
				<table id="" class="table" style="margin-top:10px; margin-left:2px; width:666px; color:black; border:0px;" >
					<tr>
						<td style="width:50px;height:0px;border:0px;"></td>
						<td style="width:306px;height:0px;border:0px;"></td>
						<td style="width:150px;height:0px;border:0px;"></td>
						<td style="width:15px;height:0px;border:0px;"></td>
						<td style="width:155px;height:0px;border:0px;"></td>
					</tr>
					<tr>
						<td style="border:0px;text-align:left;padding-left:10px;" colspan="2">
							<span style="font-size:12px; font-weight:bold;">
								អតិថិជន:
							</span>
							<span style="font-size:12px; font-weight:bold;">
							វ៉ាន់កូ អ៊ិនដាសស្ទ្រៀល
							</span>
						</td>
						<td style="border:0px;text-align:left;padding-left:35px;">
							<span style="font-size:12px;;">
								វិក្កយបត្រលេខ
							</span>
						</td>
						<td style="border:0px;text-align:center;">
							:
						</td>
						<td style="text-align:left;border:0px;">
							${requestScope.busin.CNodate5}
						</td>
					</tr>
					<tr>
						<td style="border:0px;text-align:left;padding-left:10px;">
							<span style="font-size:12px; font-weight:bold;">${requestScope.cus.COther1}</span>
						</td>
						<td style="text-align:left;border:0px;">
							<span style="font-size:12px; font-weight:bold;">${requestScope.cus.CName}</span>
						</td>
						<td style="border:0px;text-align:left;padding-left:35px;">
							<span style="font-size:12px;">កាលបរិច្ឆេទ</span>
						</td>
						<td style="border:0px;text-align:center;">
							<span>:</span>
						</td>
						<td style="text-align:left;border:0px;">
							<s:date name="busin.CNodate6" format="dd-MM-yyyy"/>
						</td>
					</tr>
					<tr>
						<td style="border:0px;"></td>
						<td style="text-align:left;border:0px;">
							${requestScope.cus.CAddr}
						</td>
						<td style="border:0px;text-align:left;padding-left:35px;">
							File No.
						</td>
						<td style="border:0px;text-align:center;">
							<span>:</span>
						</td>
						<td style="text-align:left;border:0px;">
							${requestScope.busin.CBillNo}
						</td>
					</tr>
					<tr>
						<td style="border:0px;"></td>
						<td style="text-align:left;border:0px;">
							${requestScope.cus.CPhone}
						</td>
						<td style="border:0px;text-align:left;padding-left:35px;">
							B/L No.
						</td>
						<td style="border:0px;text-align:center;">
							<span>:</span>
						</td>
						<td style="text-align:left;border:0px;">
							${requestScope.busin.CTakeNo}
						</td>
					</tr>
					<tr>
						<td style="border:0px;"></td>
						<td style="text-align:left;border:0px;">
							<span style="color:black;">
								${requestScope.cus.COther4}
							</span>
						</td>
						<td style="border:0px;text-align:left;padding-left:35px;">
							Container No.
						</td>
						<td style="border:0px;text-align:center;">
							<span>:</span>
						</td>
						<td style="text-align:left;border:0px;">
							${requestScope.busin.CConNum}
						</td>
					</tr>
					<tr>
						<td style="border:0px;"></td>
						<td style="text-align:left;border:0px;">
							${requestScope.cus.COther5}
						</td>
						<td style="border:0px;text-align:left;padding-left:35px;">
							Weight
						</td>
						<td style="border:0px;text-align:center;">
							<span>:</span>
						</td>
						<td style="text-align:left;border:0px;">
							${requestScope.pkgs}
						</td>
					</tr>
					<tr>
						<td style="border:0px;"></td>
						<td style="text-align:left;border:0px;">
							${requestScope.cus.COther6}
						</td>
						<td style="border:0px;text-align:left;padding-left:35px;">
							Commodity
						</td>
						<td style="border:0px;text-align:center;">
							<span>:</span>
						</td>
						<td colspan="3" style="text-align:left;border:0px;">
							${requestScope.busin.CGoodsType}
						</td>
					</tr>
					<tr>
						<td style="border:0px;"></td>
						<td style="text-align:left;border:0px;">
							${requestScope.cus.COther7}
						</td>
						<!--  
						<td style="border:0px;text-align:right;">
							Container No.
						</td>
						<td style="border:0px;text-align:center;">
							<span>:</span>
						</td>
						<td style="text-align:left;border:0px;">
							${requestScope.containerNum}
						</td>
						-->
						<td style="border:0px;text-align:left;padding-left:35px;">
							Factory Inv. No.
						</td>
						<td style="border:0px;text-align:center;">
							<span>:</span>
						</td>
						<td style="text-align:left;border:0px;">
							${requestScope.busin.CFty}
						</td>
					</tr>
					<tr>
						<td style="border:0px;"></td>
						<td style="text-align:left;border:0px;">
							${requestScope.cus.COther8}
						</td>
						<td style="border:0px;text-align:left;padding-left:35px;">
							Type of shipment
						</td>
						<td style="border:0px;text-align:center;">
							<span>:</span>
						</td>
						<td style="text-align:left;border:0px;">
							${requestScope.busin.CShipment}
						</td>
					</tr>
				</table>
				<table id="costMoney" class="costTable" style="margin-top:25px; width:620px; border:1px solid black; color:black; margin-left:10px; float:left;">
					<tr>
						<th style="width:60px;font-size:14px;font-weight:normal;border:1px solid black;height:18px;
									border-bottom:0px;">ល.រ</th>
						<th style="width:315px;font-size:14px;font-weight:normal;border:1px solid black;height:18px;
									border-bottom:0px;">បរិយាយ</th>
						<th style="width:95px;font-size:14px;font-weight:normal;border:1px solid black;height:18px;
									border-bottom:0px;">ឯកត្តា</th>
						<th style="width:95px;font-size:14px;font-weight:normal;border:1px solid black;height:18px;
									border-bottom:0px;">តម្លៃឯកត្តា</th>
						<th style="width:95px;font-size:14px;font-weight:normal;border:1px solid black;height:18px;
									border-bottom:0px;">ថ្លៃទំនិញ</th>
					</tr>
					<tr>
						<th style="border:1px solid black;height:14px;font-weight:normal;border-top:0px;">No.</th>
						<th style="border:1px solid black;height:14px;font-weight:normal;border-top:0px;">Description</th>
						<th style="border:1px solid black;height:14px;font-weight:normal;border-top:0px;">Q'Ty</th>
						<th style="border:1px solid black;height:14px;font-weight:normal;border-top:0px;">Unit Price</th>
						<th style="border:1px solid black;height:14px;font-weight:normal;border-top:0px;">Amount</th>
					</tr>
					<tbody id="cashDetail">
						<s:iterator value="#request.rate" var="rate" status="rateNum">
							<tr id="<s:property value="#rateNum.index" />">
								<td style="border:1px solid black;font-size:12px;">
									<input id="xuh<s:property value="#rateNum.index" />" 
											style="border:0px;width:38px;text-align:center;height:20px;" readonly="readonly"/>
								</td>
								<td style="text-align:left;border:1px solid black;
											font-size:12px;"><s:property value="TCostItem.CName" /></td>
								<td style="border:1px solid black;font-size:12px;">
									<fmt:formatNumber pattern="#,##0.000" value="${CCount}"></fmt:formatNumber>
								</td>
								<td style="border:1px solid black;font-size:12px;">
									<fmt:formatNumber pattern="#,##0.00" value="${CMoney}"></fmt:formatNumber>
								</td>
								<td style="border:1px solid black;font-size:12px;">
									<fmt:formatNumber pattern="#,##0.00" value="${CCount*CMoney}"></fmt:formatNumber>
									<input id='money<s:property value="#rateNum.index" />' 
											value="$<s:property value="CMoney" />" 
											type="hidden" readonly="readonly"/>
									<input id='count<s:property value="#rateNum.index" />' 
											value="<s:property value="CCount" />"
											type="hidden" readonly="readonly"/>
									<input id='rate<s:property value="#rateNum.index" />' 
											value="<s:property value="TRate.CRate" />" 
											type="hidden" readonly="readonly"/>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				<table class="costTable" style="margin-top:2px; width:620px; color:black; margin-left:10px; border-top:0px;">
					<tr>
						<td style="width:60px;border-top:0px;" rowspan="3"></td>
						<td style="width:315px;border-top:0px;" rowspan="3">
							<div id="totalDollars">
							</div>
						</td>
						<td style="width:193px;border-top:0px;text-align:right;"><span style="font-size:16px;">សរុប</span>/Total</td>
						<td style="width:95px;border-top:0px;font-weight:bold;">
							<div id="amount"></div>
						</td>
					</tr>
					<tr>
						<td style="width:193px;border-top:0px;text-align:right;"><span style="font-size:16px;">អាករលើតម្លៃបន្ថែម </span>/VAT 10%</td>
						<td style="width:95px;border-top:0px;">
							<div id="vat"></div>
						</td>
					</tr>
					<tr>
						<td style="width:193px;border-top:0px;text-align:right;"><span style="font-size:16px;">សរុបរួម  </span>/Grand Total</td>
						<td style="width:95px;border-top:0px;font-weight:bold;">
							<div id="total"></div>
						</td>
					</tr>
				</table>
				<div class="clear"></div>
				<table style="margin-top:20px;width:676px;margin-left:10px;">
					<tr>
						<td style="border:0px;width:375px;text-align:left;"></td>
						<td style="border:0px;width:190px;font-weight:bold;text-align:right;">Received in advance:</td>
						<td style="font-weight:bold;">
							$<fmt:formatNumber pattern="0.00" value="${busin.CRecieveMoney3}"></fmt:formatNumber>
							<input type="hidden" value="${busin.CRecieveMoney3}" id="CRecieveMoney"/>
						</td>
					</tr>
					<tr>
						<td></td>
						<td style="font-weight:bold;text-align:right;">Amount in Due:</td>
						<td style="font-weight:bold;">
							<div id="due" style="font-weight:bold;">
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="4" style="text-align:left;">
							<div id="totalDollars"></div>
						</td>
					</tr>
				</table>
				<div class="clear"></div>
				<table style="width:666px; margin-left:10px; margin-top:50px;">
					<tr>
						<td style="width:250px;height:0px;"></td>
						<td style="width:165px;height:0px;"></td>
						<td style="width:250px;height:0px;"></td>
					</tr>
					<tr>
						<td style="height:5px;text-align:left;">--------------------------</td>
						<td style="height:5px;"></td>
						<td style="height:5px;">--------------------------</td>
					</tr>
					<tr>
						<td style="font-size:12px;text-align:left;">ហត្ថលេខា​ និងឈ្មោះអ្នកទិញ</td>
						<td></td>
						<td style="font-size:12px;">ហត្ថលេខា​ និងឈ្មោះអ្នកលក់</td>
					</tr>
					<tr>
						<td style="font-size:12px;text-align:left;">Authorized Signature</td>
						<td></td>
						<td style="font-size:12px;">Authorized Signature</td>
					</tr>
					<tr>
						<td colspan="3" style="text-align:left;font-size:10px;;">
							សម្គាល់:ច្បាប់ដើមសម្រាប់អ្នកទិញ និងច្បាប់ចម្លងសំរាប់អ្នកលក់
						</td>
					</tr>
				</table>
				<div class="clear"></div>
				<table id="btn">
					<tr>
						<td colspan="8" style="padding-top:50px; padding-bottom:20px;">
							<input id="printBtn" type="button" value="打印" style="float:left; width:80px;height:25px;margin-left:400px;" onclick="printCash();"/>
							<input id="returnBtn" type="button" value="返回" style="float:left; width:80px;height:25px;margin-left:20px;" onclick="returnList();"/>
						</td>
					</tr>
				</table>
			</form>      
        </div>
  	</div>
  </body>
</html>

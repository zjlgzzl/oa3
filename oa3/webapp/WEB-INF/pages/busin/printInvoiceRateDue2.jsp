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
			addCashPrint(6);
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
  				<div style="margin-left:2px; width:666px; margin-top:150px;">
	  					<div style="margin-top:10px;text-align:center;">
	  						<span style="color:black; font-size:16px;border-bottom:1px solid black;">
								<span style="font-size:20px;">វិក្កយបត្រអាករ</span>/TAX INVOICE
							</span>
	  					</div>
	  					<div class="clear"></div>
				</div>
				<table id="" class="table" style="margin-top:10px; margin-left:2px; width:666px; color:black; border:0px;" >
					<tr>
						<td style="width:60px;height:0px;border:0px;"></td>
						<td style="width:286px;height:0px;border:0px;"></td>
						<td style="width:150px;height:0px;border:0px;"></td>
						<td style="width:15px;height:0px;border:0px;"></td>
						<td style="width:165px;height:0px;border:0px;"></td>
					</tr>
					<tr>
						<td style="border:0px;text-align:left;padding-left:10px;" colspan="2">
							<span style="font-size:12px; font-weight:bold;">
								អតិថិជន:
							</span>
							${requestScope.cus.CName}		
							<span style="font-size:12px; font-weight:bold;">
											
							</span>
						</td>
						<td style="border:0px;text-align:left;padding-left:35px;">
							<span style="font-size:12px;">
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
							${requestScope.cus.CAddr}
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
							${requestScope.cus.CPhone}
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
							${requestScope.cus.COther4}
						</td>
						<td style="border:0px;text-align:left;padding-left:35px;">
							Bill 提单号码
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
								${requestScope.cus.COther5}
							</span>
						</td>
						<td style="border:0px;text-align:left;padding-left:35px;">
							Con't 货柜号码
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
							${requestScope.cus.COther6}
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
							${requestScope.cus.COther7}
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
						<td style="border:0px;font-size:12px; font-weight:bold;">VAT No:</td>
						<td style="text-align:left;border:0px;">
							${requestScope.cus.COther8}
						</td>
						<td style="border:0px;text-align:left;padding-left:35px;">
							ETD 船开日期
						</td>
						<td style="border:0px;text-align:center;">
							<span>:</span>
						</td>
						<td colspan="3" style="text-align:left;border:0px;">
							${requestScope.CArrivalDate}
						</td>
					</tr>
					<tr>
						<td style="border:0px;font-size:12px; font-weight:bold;"></td>
						<td style="text-align:left;border:0px;">
							
						</td>
						<td style="border:0px;text-align:left;padding-left:35px;">
							ETA 船到日期
						</td>
						<td style="border:0px;text-align:center;">
							<span>:</span>
						</td>
						<td colspan="3" style="text-align:left;border:0px;">
							${requestScope.CArrivalDate2}
						</td>
					</tr>
					<tr>
						<td style="border:0px;font-size:12px; font-weight:bold;"></td>
						<td style="text-align:left;border:0px;">
						</td>
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
						<td style="border:0px;font-size:12px; font-weight:bold;"></td>
						<td style="text-align:left;border:0px;">
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
					<tr>
						<td style="border:0px;font-size:12px; font-weight:bold;"></td>
						<td style="text-align:left;border:0px;">
						</td>
						<td style="border:0px;text-align:left;padding-left:35px;">
							Exchange Rate
						</td>
						<td style="border:0px;text-align:center;">
							<span>:</span>
						</td>
						<td style="text-align:left;border:0px;">
							<fmt:formatNumber type="number" value="${requestScope.busin.CExchange}" pattern="##0" />
							<input type="hidden" id="_businRate" name="busin.CExchange" value="${requestScope.busin.CExchange}" />
						</td>
					</tr>
					
				</table>
				<table id="costMoney" class="costTable" style="margin-top:2px; width:680px; border:1px solid black; color:black; margin-left:10px; float:left;">
					<tr>
						<th style="width:40px;font-size:14px;font-weight:normal;border:1px solid black;height:18px;
									border-bottom:0px;">ល.រ</th>
						<th style="width:222px;font-size:14px;font-weight:normal;border:1px solid black;height:18px;
									border-bottom:0px;">បរិយាយ</th>
						<th style="width:60px;font-size:14px;font-weight:normal;border:1px solid black;height:18px;
									border-bottom:0px;">ឯកត្តា</th>
						<th style="width:88px;font-size:14px;font-weight:normal;border:1px solid black;height:18px;
									border-bottom:0px;">តម្លៃឯកត្តា</th>
						<th style="width:90px;font-size:14px;font-weight:normal;border:1px solid black;height:18px;
									border-bottom:0px;">ថ្លៃទំនិញ</th>
						<th style="width:90px;font-size:14px;font-weight:normal;border:1px solid black;height:18px;
									border-bottom:0px;">អ.ត.ប</th>
						<th style="width:90px;font-size:14px;font-weight:normal;border:1px solid black;height:18px;
									border-bottom:0px;">សរុប</th>
					</tr>
					<tr>
						<th style="border:1px solid black;height:14px;font-weight:normal;border-top:0px;">No.</th>
						<th style="border:1px solid black;height:14px;font-weight:normal;border-top:0px;">Description</th>
						<th style="border:1px solid black;height:14px;font-weight:normal;border-top:0px;">Q'Ty</th>
						<th style="border:1px solid black;height:14px;font-weight:normal;border-top:0px;">Unit Price</th>
						<th style="border:1px solid black;height:14px;font-weight:normal;border-top:0px;">Amount</th>
						<th style="border:1px solid black;height:14px;font-weight:normal;border-top:0px;">VAT 10%</th>
						<th style="border:1px solid black;height:14px;font-weight:normal;border-top:0px;">Total</th>
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
									<fmt:formatNumber pattern="#,##0.###" value="${CCount}"></fmt:formatNumber>
								</td>
								<td style="border:1px solid black;font-size:12px;">
									<fmt:formatNumber pattern="#,##0.00" value="${CMoney}"></fmt:formatNumber>
								</td>
								<td style="border:1px solid black;font-size:12px;">
									<fmt:formatNumber pattern="#,##0.00" value="${CCount*CMoney}"></fmt:formatNumber>
								</td>
								<td style="border:1px solid black;font-size:12px;">
									<fmt:formatNumber pattern="#,##0.00" value="${CCount*CMoney*TRate.CRate/100}"></fmt:formatNumber>
								</td>
								<td style="border:1px solid black;font-size:12px;">
									<fmt:formatNumber pattern="#,##0.00" value="${CCount*CMoney + CCount*CMoney*TRate.CRate/100}"></fmt:formatNumber>
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
				<table class="costTable" style="margin-top:2px; width:680px; color:black; margin-left:10px; border-top:0px;">
					<tr>
						<td style="width:265px;border-top:0px;text-align:left;" rowspan="2">
							<div id="totalDollars"></div>
						</td>
						<td style="width:151px;border-top:0px;text-align:center;">
							<span style="font-size:14px;">សរុប</span>/Total:
						</td>
						<td style="width:90px;border-top:0px;">
							<div id="amount"></div>
						</td>
						<td style="width:90px;border-top:0px;">
							<div id="vat"></div>
						</td>
						<td style="width:90px;border-top:0px;">
							<div id="total"></div>
						</td>
					</tr>
					<tr>
						<td style="width:153px;border-top:0px;text-align:center;">
							<span style="font-size:14px;">សរុបជាបា្រក់រៀល </span>(KHR):
						</td>
						<td style="width:80px;border-top:0px;">
							<div id="amountKHR"></div>
						</td>
						<td style="width:70px;border-top:0px;">
							<div id="vatKHR"></div>
						</td>
						<td style="width:70px;border-top:0px;">
							<div id="totalKHR"></div>
						</td>
					</tr>
				</table>
				<div class="clear"></div>
				<table style="margin-top:5px;width:660px;margin-left:20px;">
					<tr>
						<td style="width:370px;text-align:left;padding-right:10px;" rowspan="3">
							${requestScope.busin.CInvRemarks}
						</td>
						<td style="border:0px;width:200px;font-weight:bold;text-align:right;">Received in advance:</td>
						<td style="font-weight:bold;padding-left:10px;">
							$<fmt:formatNumber pattern="#,##0.00" value="${busin.CRecieveMoney4}"></fmt:formatNumber>
							<input type="hidden" value="${busin.CRecieveMoney4}" id="CRecieveMoney"/>
						</td>
					</tr>
					<tr>
						<td style="font-weight:bold;text-align:right;">Amount in Due:</td>
						<td style="font-weight:bold;padding-left:10px;">
							<div id="due" style="font-weight:bold;">
							</div>
						</td>
					</tr>
					<!--  
					<tr>
						<td style="font-weight:bold;text-align:right;">សរុបរួម(Grand Total in Rie):</td>
						<td style="font-weight:bold;padding-left:10px;">
							<div id="grandRie" style="font-weight:bold;">
							</div>
						</td>
					</tr>
					-->
					<tr>
						<td style="font-weight:bold;text-align:right;"></td>
						<td style="font-weight:bold;padding-left:10px;">
						</td>
					</tr>
				</table>
				<div class="clear"></div>
				<table style="width:615px; margin-left:10px;">
					<tr>
						<td style="width:60px;height:0px;"></td>
						<td style="width:210px;height:0px;"></td>
						<td style="width:30px;height:0px;"></td>
						<td style="width:80px;height:0px;"></td>
						<td style="width:80px;height:0px;"></td>
					</tr>
					<tr>
						<td></td>
						<td colspan="4" style="text-align:left;margin-top:0;">
							<div id="totalDollars">
							</div>
						</td>
					</tr>
				</table>
				<div style="height:10px;margin-top:20px;">
					<!--  
					<table style="width:600px;">
						<tr>
							<td style="width:190px;border:0px;height:0px;"></td>
							<td style="width:190px;border:0px;height:0px;"></td>
							<td style="width:190px;border:0px;height:0px;"></td>
						</tr>
						<s:iterator value="#request.conList" var="conList" status="conNum">
							<s:if test="#conNum.index > 0">
								<s:if test="#conNum.index == 1 ||(#conNum.index > 2 && #conNum.index%3 == 1)">
									<tr>
								</s:if>
									<td style="border:0px;">
										<s:property value="CContainerNum" />
										<s:property value="CContainerType" />
									</td>
								<s:if test="#conNum.last||(#conNum.index > 2 && #conNum.index%3 == 0)">
									</tr>
								</s:if>
							</s:if>
						</s:iterator>
					</table>
					-->
				</div>
				<table style="width:666px; margin-left:10px; margin-top:60px;">
					<tr>
						<td style="width:250px;height:0px;"></td>
						<td style="width:165px;height:0px;"></td>
						<td style="width:250px;height:0px;"></td>
					</tr>
					<tr>
						<td style="height:5px;">--------------------------</td>
						<td style="height:5px;"></td>
						<td style="height:5px;">--------------------------</td>
					</tr>
					<tr>
						<td style="font-size:12px;">ហត្ថលេខា​ និងឈ្មោះអ្នកទិញ</td>
						<td></td>
						<td style="font-size:12px;">ហត្ថលេខា​ និងឈ្មោះអ្នកលក់</td>
					</tr>
					<tr>
						<td style="font-size:12px;">Authorized Signature</td>
						<td></td>
						<td style="font-size:12px;">Authorized Signature</td>
					</tr>
					<tr>
						<td colspan="3" style="text-align:left;font-size:10px;padding-left:16px;">
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
							<input type="hidden" id="_businId" name="_businId" value="${requestScope._businId}" />
						</td>
					</tr>
				</table>
			</form>      
        </div>
  	</div>
  </body>
</html>

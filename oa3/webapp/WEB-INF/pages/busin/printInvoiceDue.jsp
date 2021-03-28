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
			height:22px;
		}
	</style>
	<script type="text/javascript">
	
		var userCount = "${requestScope.cashUserCount}";
		var maxRows = 12;
		var cashMaxRows = 14;
	
		//初始化
		$(document).ready(function(){
			addCashPrint(4);
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
  		<div style="width:636px;">
  			<form id="editForm" action="${ctx}/busin/FinanceCost.action" method="post">
  				<input type="hidden" name="businId" value="${requestScope.busin.CId}" />
  				<div style="margin-left:5px; width:666px; margin-top:50px;">
  					<div style="color:black; font-size:20px; font-weight:bold; text-align:center;text-decoration:underline;width:200px;margin:0 auto;">
						ការទូទាត់សងវិញ
					</div>
					<div style="color:black; font-size:14px; font-weight:bold; text-align:center;text-decoration:underline;width:200px;
						margin:0px auto;">
					 	Debit Note
					</div>
				</div>
				<table id="" class="table" style="margin-left:5px; margin-top:15px; width:666px; color:black; border:0px;" >
					<tr>
						<td style="width:50px;height:0px;border:0px;"></td>
						<td style="width:246px;height:0px;border:0px;"></td>
						<td style="width:145px;height:0px;border:0px;"></td>
						<td style="width:10px;height:0px;border:0px;"></td>
						<td style="width:205px;height:0px;border:0px;"></td>
					</tr>
					<tr>
						<td style="border:0px;">
							<!--  
							<span style="font-weight:bold;">${requestScope.cus.COther1}</span>
							-->
						</td>
						<td style="text-align:left;border:0px;">
							<!-- 
							<span style="font-weight:bold;">${requestScope.cus.CName}</span>
							-->
						</td>
						<td style="border:0px;text-align:left;padding-left:35px;">
							<span>No-Date</span>
						</td>
						<td style="border:0px;text-align:center;">
							<span>:</span>
						</td>
						<td colspan="3" style="text-align:left;border:0px;">
							${requestScope.busin.CNodate1}
							&nbsp;
							<s:date name="busin.CNodate2" format="dd-MM-yyyy"/>
						</td>
					</tr>
					<tr>
						<td style="border:0px;">To:</td>
						<td style="text-align:left;border:0px;">
							${requestScope.cus.CAddr}
						</td>
						<td style="border:0px;text-align:left;padding-left:35px;">
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
							${requestScope.cus.CPhone}
						</td>
						<td style="border:0px;text-align:left;padding-left:35px;">
							Bill 提单号码
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
							<span style="color:black;">
								${requestScope.cus.COther4}
							</span>
						</td>
						<td style="border:0px;text-align:left;padding-left:35px;">
							Con't 货柜号码
						</td>
						<td style="border:0px;text-align:center;">
							<span>:</span>
						</td>
						<td colspan="3" style="text-align:left;border:0px;">
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
						<td colspan="3" style="text-align:left;border:0px;">
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
						<td style="border:0px;"></td>
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
						<td style="border:0px;"></td>
						<td style="text-align:left;border:0px;">
						</td>
						<td style="border:0px;text-align:left;padding-left:35px;">
							Fty. inv No.
						</td>
						<td style="border:0px;text-align:center;">
							<span>:</span>
						</td>
						<td colspan="3" style="text-align:left;border:0px;">
							${requestScope.busin.CFty}
						</td>
					</tr>
					<tr>
						<td style="border:0px;"></td>
						<td style="text-align:left;border:0px;">
						</td>
						<td style="border:0px;text-align:left;padding-left:35px;">
							Type of shipment
						</td>
						<td style="border:0px;text-align:center;">
							<span>:</span>
						</td>
						<td colspan="3" style="text-align:left;border:0px;">
							${requestScope.busin.CShipment}
						</td>
					</tr>
				</table>
				<table id="costMoney" class="costTable" style="margin-top:2px;width:666px; border:1px solid black; color:black; margin-left:10px; float:left;">
					<tr>
						<th style="width:40px;font-size:14px;font-weight:normal;border:1px solid black;height:18px;
									border-bottom:0px;">ល.រ</th>
						<th style="width:330px;font-size:14px;font-weight:normal;border:1px solid black;height:18px;
									border-bottom:0px;">បរិយាយ</th>
						<th style="width:80px;font-size:14px;font-weight:normal;border:1px solid black;height:18px;
									border-bottom:0px;">ឯកត្តា</th>
						<th style="width:110px;font-size:14px;font-weight:normal;border:1px solid black;height:18px;
									border-bottom:0px;">តម្លៃឯកត្តា</th>
						<th style="width:100px;font-size:14px;font-weight:normal;border:1px solid black;height:18px;
									border-bottom:0px;">សរុប</th>
					</tr>
					<tr>
						
						<th style="width:40px;border:1px solid black;height:15px;font-weight:normal;border-top:0px;">Item</th>
						<th style="width:330px;border:1px solid black;height:15px;font-weight:normal;border-top:0px;">Description</th>
						<th style="width:80px;border:1px solid black;height:15px;font-weight:normal;border-top:0px;">Q'Ty</th>
						<th style="width:110px;border:1px solid black;height:15px;font-weight:normal;border-top:0px;">Unit Price</th>
						<th style="width:100px;border:1px solid black;height:15px;font-weight:normal;border-top:0px;">Amount</th>
					</tr>
					<tbody id="cashDetail">
						<s:iterator value="#request.cash" var="cash" status="cashNum">
							<tr id="<s:property value="#cashNum.index" />">
								<td style="height:15px;">
									<input id="xuh<s:property value="#cashNum.index" />" 
											style="border:0px; width:38px;height:20px;text-align:center;" readonly="readonly"/>
								</td>
								<td style="border:1px solid black;height:15px;text-align:left;padding-left:2px;">
									<s:property value="TCostItem.CName" />
								</td>
								<td style="border:1px solid black;height:15px;">
									<fmt:formatNumber pattern="#,##0.###" value="${CCount}"></fmt:formatNumber>
								</td>
								<td style="border:1px solid black;height:15px;">
									<fmt:formatNumber pattern="#,##0.00" value="${CMoney}"></fmt:formatNumber>
								</td>
								<td style="border:1px solid black;height:15px;">
									<fmt:formatNumber pattern="#,##0.00" value="${CCount*CMoney}"></fmt:formatNumber>
										<input id='money<s:property value="#cashNum.index" />' 
												value="$<s:property value="CMoney" />" 
												type="hidden" readonly="readonly"/>
										<input id='count<s:property value="#cashNum.index" />' 
												value="<s:property value="CCount" />" 
												type="hidden" readonly="readonly"/>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				<div class="clear"></div>
				<table style="margin-top:20px;width:676px;margin-left:10px;">
					<tr>
						<td style="border:0px;width:367px;text-align:left;padding-right:10px;" rowspan="3">
							${requestScope.busin.CDn1Remarks}
						</td>
						<td style="border:0px;width:190px;text-align:left;font-weight:bold;">
							<span style="font-size:14px;">សរុប​​ </span>/Total:</td>
						<td style="border:0px;width:101px;border:1px solid black;">
							<div id="total" style="font-weight:bold;width:100px;">
							</div>
						</td>
					</tr>
					<tr>
						<td style="font-weight:bold;text-align:left;">Received in Advance:</td>
						<td style="font-weight:bold;">
							$<fmt:formatNumber pattern="#,##0.00" value="${busin.CRecieveMoney1}"></fmt:formatNumber>
							<input type="hidden" value="${busin.CRecieveMoney1}" id="CRecieveMoney"/>
						</td>
					</tr>
					<tr>
						<td style="font-weight:bold;text-align:left;">Amount in Due:</td>
						<td style="font-weight:bold;">
							<div id="due" style="font-weight:bold;">
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="3" style="text-align:left;">
							<div id="totalDollars"></div>
						</td>
					</tr>
				</table>
				<div class="clear"></div>
				<!-- 
				<div id="total" style="width:620px; text-align:right; margin-top:5px; font-weight:bold;">
				</div>
				 -->
				
				<div style="height:12px;margin-top:20px;">
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
						<td style="height:5px;text-align:left;"></td>
						<td style="height:5px;"></td>
						<td style="height:5px;">--------------------------</td>
					</tr>
					<tr>
						<td style="font-size:12px;text-align:left;"></td>
						<td></td>
						<td style="font-size:12px;">Authorized Signature</td>
					</tr>
				</table>
				<table id="btn">
					<tr>
						<td colspan="8" style="padding-top:50px; padding-bottom:20px;">
							<input id="printBtn" type="button" value="打印" style="float:left; width:80px;height:25px;margin-left:410px;" onclick="printCash();"/>
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

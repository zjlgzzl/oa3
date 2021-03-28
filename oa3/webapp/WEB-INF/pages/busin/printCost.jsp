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
	<script type="text/javascript">
	
		var userCount = "${requestScope.costUserCount}";
		var maxRows = 12;
	
		//初始化
		$(document).ready(function(){
			addCostPrint();
			
		});
		
		//返回
		function returnList(){
			document.getElementById("editForm").action = "${ctx}/busin/BusinDetialList.action";
			document.getElementById("editForm").submit();
		}
		
		//打印
		function printCost(){
			//更新打印状态
			var businId = document.getElementById("businId").value;
			$.ajax({
				type:"Post",
				url:"${ctx}/busin/UpdatePrintState.action?businId="+businId,
				success:function(){}
			});
			//打印
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
  				<input id="businId" type="hidden" name="businId" value="${requestScope.busin.CId}" />
	  				<div style="margin-left:5px; width:820px; margin-top:30px;">
		  					<div style="float:left; margin-top:20px; margin-left:350px; ">
		  						<span style="color:black; font-size:14px; font-weight:bold; text-decoration:underline">
									PAYMENT REQUEST
								</span>
		  					</div>
		  					<div class="clear"></div>
					</div>
					<table id="" class="table" style="margin-top:35px; width:820px; color:black; border:0px;" >
						<tr>
							<td style="width:120px;height:0px;border:0px;"></td>
							<td style="width:90px;height:0px;border:0px;"></td>
							<td style="width:80px;height:0px;border:0px;"></td>
							<td style="width:130px;height:0px;border:0px;"></td>
							<td style="width:80px;height:0px;border:0px;"></td>
							<td style="width:130px;height:0px;border:0px;"></td>
							<td style="width:60px;height:0px;border:0px;"></td>
							<td style="width:130px;height:0px;border:0px;"></td>
						</tr>
						<tr>
							<td style="text-align:left;border:0px;">
								CLIENT
							</td>
							<td colspan="5" style="text-align:left;border:0px;">
								${requestScope.cusName}
							</td>
							<td style="text-align:left;border:0px;">
								File No
							</td>
							<td style="text-align:left;border:0px;">
								${requestScope.busin.CBillNo}
							</td>
						</tr>
						<tr>
							<td style="text-align:left;border:0px;">
								Description
							</td>
							<td id="des" 
								style="text-align:left;border:0px;" colspan="7">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;border:0px;">
								CONTAINER NO
							</td>
							<td colspan="7" style="text-align:left;border:0px;">
								${requestScope.conNum2}
							</td>
						</tr>
						<tr>
							<td style="text-align:left;border:0px;">
								Amount:
							</td>
							<td colspan="7" style="text-align:left;border:0px;">
								<input id="amount"
									style="border:0px; width:700px;" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<td style="text-align:left;border:0px;">
								Amount in word:
							</td>
							<td colspan="7" style="text-align:left;border:0px;">
								<input id="amountInWord"
									style="border:0px; width:700px;" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<td style="text-align:left;border:0px;">
								Remarks:
							</td>
							<td colspan="7" style="text-align:left;border:0px;">
								<input value="${requestScope.busin.CRemarks}"
									style="border:0px; width:700px;" readonly="readonly"/>
							</td>
						</tr>
					</table>
					<table id="" class="table" style="margin-top:20px; width:820px; color:black; margin-left:0px; float:left; border:0px;">
						<tr>
							<td style="width:170px; border:0px; text-align:left;">
								Request by
							</td>
							<td style="width:170px; border:0px; text-align:left">
								Verified by
							</td>
							<td style="width:170px; border:0px; text-align:left">
								Approved by
							</td>
							<td style="width:185px; border:0px; text-align:left">
								Accountant
							</td>
							<td style="width:130px; border:0px; text-align:left">
								Acknowledged by
							</td>
						</tr>
					</table>
					<table id="" class="table" style="margin-top:150px; width:820px; color:black; margin-left:0px; float:left; border:0px;">
						<tr>
							<td style="width:170px; border:0px; text-align:left">
								<input style="border:0px; border-bottom:1px black solid; width:100px;" readonly="readonly"/>
							</td>
							<td style="width:170px; border:0px; text-align:left">
								<input style="border:0px; border-bottom:1px black solid; width:100px;" readonly="readonly"/>
							</td>
							<td style="width:170px; border:0px; text-align:left">
								<input style="border:0px; border-bottom:1px black solid; width:100px;" readonly="readonly"/>
							</td>
							<td style="width:185px; border:0px; text-align:left">
								<input style="border:0px; border-bottom:1px black solid; width:100px;" readonly="readonly"/>
							</td>
							<td style="width:130px; border:0px; text-align:left">
								<input style="border:0px; border-bottom:1px black solid; width:100px;" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<td style="width:170px; border:0px; text-align:left; height:5px;">
								<span style="font-size:10px;">Sign,Name & Date</span>
							</td>
							<td style="width:170px; border:0px; text-align:left; height:5px;">
								<span style="font-size:10px;">Sign,Name & Date</span>
							</td>
							<td style="width:170px; border:0px; text-align:left; height:5px;">
								<span style="font-size:10px;">Sign,Name & Date</span>
							</td>
							<td style="width:185px; border:0px; text-align:left; height:5px;">
								<span style="font-size:10px;">Sign,Name & Date</span>
							</td>
							<td style="width:130px; border:0px; text-align:left; height:5px;">
								<span style="font-size:10px;">Sign,Name & Date</span>
							</td>
						</tr>
					</table>
					<div class="clear"></div>
					
					<div class="hidden">
						<table id="costMoney0" class="costTable" style="border:1px solid black; margin-top:20px; width:580px; color:black; margin-left:50px; float:left;" >
							<tr>
								<th style="width:40px;border:1px solid black;">Item</th>
								<th style="width:240px;border:1px solid black;">Description</th>
								<th style="width:80px;border:1px solid black;">Q'Ty</th>
								<th style="width:100px;border:1px solid black;">Rate($)</th>
								<th style="width:120px;border:1px solid black;">Amount</th>
							</tr>
							<tbody id="costDetail">
								<s:iterator value="#request.costs" var="costs" status="costNum">
									<tr id="<s:property value="#costNum.index" />">
										<td style="border:1px solid black;">
											<input id="xuh<s:property value="#costNum.index" />" 
													style="border:0px;width:38px;text-align:center;" readonly="readonly"/>
										</td>
										<td style="text-align:left;border:1px solid black;"><s:property value="TCostItem.CName" /></td>
										<td style="border:1px solid black;"></td>
										<td style="border:1px solid black;"></td>
										<td style="border:1px solid black;text-align:center;">
											<input id="money<s:property value="#costNum.index" />" 
													value="<s:property value="CMoney" />" 
													style="border:0px;width:110px;text-align:center;" readonly="readonly"/>
											<input id="costitem<s:property value="#costNum.index" />" 
													value="<s:property value="TCostItem.CName" />" 
													style="border:0px;width:110px;text-align:center;" readonly="readonly"/>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
					
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

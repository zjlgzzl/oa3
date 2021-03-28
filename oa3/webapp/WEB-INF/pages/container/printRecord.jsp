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
	<script type="text/javascript">
	
		//返回
		function returnList(){
			history.go(-1);
		}
		
		//打印
		function printRecord(){
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
  	<div style="margin-left:10px;">
  		<form id="editForm" action="${ctx}/container/SaveConRecord.action" method="post">
			<table id="" class="table" style="margin-top:35px; width:650px; color:black; border:0px;" >
				<tr>
					<td style="width:130px;height:0px;border:0px;"></td>
					<td style="width:130px;height:0px;border:0px;"></td>
					<td style="width:130px;height:0px;border:0px;"></td>
					<td style="width:130px;height:0px;border:0px;"></td>
					<td style="width:130px;height:0px;border:0px;"></td>
				</tr>
				<tr>
					<td colspan="5" style="font-size:14px; font-weight:bold; border:0px; padding-bottom:20px;">
						CONTAINER<br/>TRACKING RECORD
					</td>
				</tr>
				<tr>
					<td style="border:0px;text-align:left;">
						DATE:
					</td>
					<td colspan="2" style="border:0px;text-align:left;">
						${requestScope.cdate}
					</td>
					<td style="border:0px;text-align:left;">
						REF:
					</td>
					<td style="border:0px;">
					</td>
				</tr>
				<tr>
					<td style="border:0px;text-align:left;">
						CONTAINER No:
					</td>
					<td style="border:0px;text-align:left;">
						${requestScope.infoCode}
					</td>
					<td style="border:0px;text-align:left;">
						TYPE:&nbsp&nbsp&nbsp${requestScope.modelCode}
					</td>
					<td style="border:0px;">
					</td>
				</tr>
				<tr>
					<td style="border:0px;text-align:left;">
						CLIENT:
					</td>
					<td colspan="4" style="border:0px;text-align:left;">
						${requestScope.record.CCusname}
					</td>
				</tr>
				<tr>
					<td style="border:0px;text-align:left;">
						ACTION:
					</td>
					<td colspan="4" style="border:0px;text-align:left;">
						<s:if test="#request.record.COpertype == 1">
							RENTAL
						</s:if>
						<s:if test="#request.record.COpertype == 2">
							SALING
						</s:if>
						<s:if test="#request.record.COpertype == 3">
							GET BACK
						</s:if>
					</td>
				</tr>
				<tr>
					<td style="border:0px;text-align:left;">
						WHERE:
					</td>
					<td colspan="4" style="border:0px;text-align:left;">
						${requestScope.record.CTo}
					</td>
				</tr>
				<tr>
					<td style="border:0px;text-align:left;">
						PRICE:
					</td>
					<td colspan="4" style="border:0px;text-align:left;">
						${requestScope.record.CMoney}
					</td>
				</tr>
				<tr>
					<td style="border:0px;text-align:left;">
						备注:
					</td>
					<td colspan="4" style="border:0px;text-align:left;">
						${requestScope.record.CRemarks2}
					</td>
				</tr>
				<tr>
					<td style="border:0px;text-align:left;">
						NOTE:
					</td>
					<td colspan="4" style="border:0px;text-align:left;">
						${requestScope.record.CRemarks}
					</td>
				</tr>
				<tr>
					<td style="border:0px;text-align:left;padding-top:40px;">
						M.Director
					</td>
					<td style="border:0px;text-align:left;padding-top:40px;">
						Approved by
					</td>
					<td style="border:0px;text-align:left;padding-top:40px;">
						Verify by
					</td>
					<td style="border:0px;text-align:left;padding-top:40px;">
						Acknowledged by
					</td>
					<td style="border:0px;text-align:left;padding-top:40px;">
						Received by
					</td>
				</tr>
				<tr>
					<td style="border:0px;text-align:left;padding-top:40px;">
						<input style="border:0px; border-bottom:1px black solid; width:100px;" readonly="readonly"/>
					</td>
					<td style="border:0px;text-align:left;padding-top:40px;">
						<input style="border:0px; border-bottom:1px black solid; width:100px;" readonly="readonly"/>
					</td>
					<td style="border:0px;text-align:left;padding-top:40px;">
						<input style="border:0px; border-bottom:1px black solid; width:100px;" readonly="readonly"/>
					</td>
					<td style="border:0px;text-align:left;padding-top:40px;">
						<input style="border:0px; border-bottom:1px black solid; width:100px;" readonly="readonly"/>
					</td>
					<td style="border:0px;text-align:left;padding-top:40px;">
						<input style="border:0px; border-bottom:1px black solid; width:100px;" readonly="readonly"/>
					</td>
				</tr>
			</table>
	        <div id="btn" style="margin-bottom:30px;">
	           	<input type="button" value="打印" class="save" style="width:60px;height:25px;margin-left:300px;" onclick="printRecord();"/>
	           	<input type="button" value="返回" class="return" style="width:60px;height:25px" onclick="returnList();"/>
	        </div>
        </form>
  	</div>
  </body>
</html>

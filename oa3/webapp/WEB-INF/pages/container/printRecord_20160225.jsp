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
  	<div id="mainContent">
  		<div class="right">
  				<form id="editForm" action="${ctx}/container/SaveConRecord.action" method="post">
  					<div style="margin-left:5px; width:1000px; margin-top:30px;">
	  					<div style="float:left; margin-top:20px; margin-left:350px; ">
	  						<span style="color:black; font-size:14px; font-weight:bold; text-decoration:underline">
								货柜进出登记单
							</span>
	  					</div>
	  					<div class="clear"></div>
					</div>
  					<table id="" class="table" style="margin-top:35px; width:840px; color:black; border:0px;" >
						<tr>
							<td style="width:80px;height:0px;border:0px;"></td>
							<td style="width:130px;height:0px;border:0px;"></td>
							<td style="width:80px;height:0px;border:0px;"></td>
							<td style="width:130px;height:0px;border:0px;"></td>
							<td style="width:80px;height:0px;border:0px;"></td>
							<td style="width:130px;height:0px;border:0px;"></td>
							<td style="width:80px;height:0px;border:0px;"></td>
							<td style="width:130px;height:0px;border:0px;"></td>
						</tr>
						<tr>
							<td style="border:1px solid black;">
								日期
							</td>
							<td style="border:1px solid black;">
								${requestScope.cdate}
							</td>
							<td style="border:1px solid black;">
								客户名称
							</td>
							<td style="border:1px solid black; text-align:left; padding-left:5px;" colspan="5">
								${requestScope.record.CCusname}
							</td>
						</tr>
						<tr>
							<td style="border:1px solid black;">
								货柜编号
							</td>
							<td style="border:1px solid black;">
								${requestScope.infoCode}
							</td>
							<td style="border:1px solid black;">
								货柜型号
							</td>
							<td style="border:1px solid black;">
								${requestScope.modelCode}
							</td>
							<td style="border:1px solid black;">
								操作类型
							</td>
							<td style="border:1px solid black;">
								<s:if test="#request.record.COpertype == 1">
									RENTAL
								</s:if>
								<s:if test="#request.record.COpertype == 2">
									SELL
								</s:if>
								<s:if test="#request.record.COpertype == 3">
									GET BACK
								</s:if>
							</td>
							<td style="border:1px solid black;">
								金额
							</td>
							<td style="border:1px solid black;">
								${requestScope.record.CMoney}
							</td>
						</tr>
						<tr>
							<td style="border:1px solid black;">
								备注
							</td>
							<td style="border:1px solid black; text-align:left; padding-left:5px;" colspan="7">
								${requestScope.record.CRemarks}
							</td>
						</tr>
					</table>
			        <div id="btn" style="margin:10px;">
			           	<input type="button" value="打印" class="save" style="width:60px;height:20px;margin-left:350px;" onclick="printRecord();"/>
			           	<input type="button" value="返回" class="return" style="width:60px;height:20px" onclick="returnList();"/>
			        </div>
		        </form>
        </div>
  	</div>
  </body>
</html>

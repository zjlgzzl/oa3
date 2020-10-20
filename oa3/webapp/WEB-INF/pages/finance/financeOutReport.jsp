<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>服务单编制</title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<script type="text/javascript" src="${ctx}/res/js/My97DatePicker/WdatePicker.js"></script>
	<link type="text/css" rel="stylesheet"  href="${ctx}/res/css/styles.css"/>
	<script type="text/javascript" src="${ctx}/res/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">
		//初始化
		$(document).ready(function(){
		});
		function expFile(){
			document.getElementById("queryForm").action = "${ctx}/finance/ExpOutReport.action";
			document.getElementById("queryForm").submit();
		}
	</script>
  </head>
  <body>
  	<div class="right">
  		<div class="right_1">
			<form id="queryForm" action="${ctx}/finance/FinanceOutReport.action" method="post">
					<table style="width:800px; margin-left:0;">
						<tr>
		  					<td style="width:100px;">支出开始日期</td>
							<td style="text-align:left; width:100px;">
								<input id="startDate" name="startDate" value="${requestScope.startDate}"
										type="text" class="field border" style="width:100px" onclick="WdatePicker()" readonly="readonly"/>
		  					</td>
		  					<td style="width:100px;">支出结束日期</td>
							<td style="text-align:left; width:100px;">
								<input id="endDate" name="endDate" value="${requestScope.endDate}" 
										type="text" class="field border" style="width:100px" onclick="WdatePicker()" readonly="readonly"/>
		  					</td>
		  					<td style="text-align:left;">
		  						<input type="submit" value="查询" class="btn_img"
										style="margin-left:10px;" />
								<input type="button" value="导出" class="btn_img"
										style="margin-left:10px;" onclick="expFile();" />
		  					</td>
						</tr>
					</table>
				
			</form>
		</div>
        <div class="right_3" style="margin-bottom:20px;">
	        <table id="table1" class="table" style="width:760px;">
	        	<tr>
	               	<th style="width:50px;">序号</th>
	               	<th style="width:500px;">支出类型名称</th>
	               	<th style="width:200px;">支出金额汇总</th>
	            </tr>
	            <s:iterator value="#request.reportList" var="reportList" status="num">
		            <tr>
		            	<td>
							<s:property value="#num.index+1" />
						</td>
						<td>
							<s:property value="typename" />
						</td>
						<td>
							<fmt:formatNumber type="number" value="${cmoney}" pattern="###,###.00" />
						</td>
					</tr>
	            </s:iterator>
	       </table>
	    </div>
    </div>
  </body>
</html>

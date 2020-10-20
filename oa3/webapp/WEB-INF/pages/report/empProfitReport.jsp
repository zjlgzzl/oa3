<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>财务收支登记</title>
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
		//分页控件
		function forward(flag){
			document.getElementById("queryForm").action = "${ctx}/report/EmpProfitReport.action";
			//首页
			if (flag == 1){
				document.getElementById("pageNow").value = Number(1);
				document.getElementById("queryForm").submit();
				return false;
			}
			//上一页
			if (flag == 2){
				var rowNow = "${requestScope.pageBean.pageNow}";
				rowNow = Number(rowNow) - Number(1);
				document.getElementById("pageNow").value = rowNow;
				document.getElementById("queryForm").submit();
				return false;
			}
			//下一页
			if (flag == 3){
				var rowNow = "${requestScope.pageBean.pageNow}";
				rowNow = Number(rowNow) + Number(1);
				document.getElementById("pageNow").value = rowNow;
				document.getElementById("queryForm").submit();
				return false;
			}
			//尾页
			if (flag == 4){
				var rowCount = "${requestScope.pageBean.pageCount}";
				document.getElementById("pageNow").value = rowCount;
				document.getElementById("queryForm").submit();
				return false;
			}
			//查询
			if (flag == -1){
				document.getElementById("pageNow").value = 1;
				return true;
			}
			if (flag == 5){
				var rowCount = "${requestScope.pageBean.pageCount}";
				var jumpPage = document.getElementById("jumpPage").value;
				if (isNaN(jumpPage) || Number(jumpPage) < 1 || Number(jumpPage) > Number(rowCount)){
					alert("请输入正确的页码");
					return false;
				}
				document.getElementById("pageNow").value = jumpPage;
				document.getElementById("queryForm").submit();
				return false;
			}
		}
		function expFile(){
			document.getElementById("queryForm").action = "${ctx}/report/ExpEmpProfitReport.action";
			document.getElementById("queryForm").submit();
		}
	</script>
  </head>
  <body>
  	<div class="right">
  		<div class="right_1">
			<form id="queryForm" action="${ctx}/report/BusinProfitReport2.action" method="post">
				<table style="width:700px; margin-left:0;">
					<tr>
						<td style="width:90px;">开始日期</td>
						<td style="text-align:left; width:90px;">
							<input id="startDate2" name="startDate2" value="${requestScope.startDate2}"
									type="text" class="field border" style="width:90px" onclick="WdatePicker()" readonly="readonly"/>
	  					</td>
	  					<td style="width:90px;">结束日期</td>
						<td style="text-align:left; width:90px;">
							<input id="endDate2" name="endDate2" value="${requestScope.endDate2}" 
									type="text" class="field border" style="width:90px" onclick="WdatePicker()" readonly="readonly"/>
	  					</td>
	  					<td style="width:90px;">业务员 saler</td>
						<td style="width:140px;text-align:left; ">
							<input id="empName" name="proQuery.empName" value="${requestScope.proQuery.empName}" 
									type="text" class="field border" style="width:140px;" />
	  					</td>
	  					<td style="width:200px;">
							<input type="submit" value="查询" class="btn_img" style="margin-left:10px;"
										onclick="return forward(-1);"/>
							<input type="button" value="导出" class="btn_img" style="margin-left:10px;"
										onclick="expFile();"/>
							<input type="hidden" id="pageNow" name="pageNow" value="${pageNow}"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
       	<div class="right_2">
        </div>
        <div class="right_3">
	        <table id="table1" class="table" style="width:910px;">
	           	<tr>
	               	<th style="width:40px;">序号</th>
	               	<th style="width:120px;">业务员 saler</th>
	               	<th style="width:110px;">成本金额</th>
					<th style="width:90px;">发票1</th>
					<th style="width:90px;">发票2</th>
					<th style="width:90px;">含税发票</th>
	               	<th style="width:110px;">实收金额</th>
	               	<th style="width:150px;">应得利润</th>
	               	<th style="width:150px;">实际利润</th>
	            </tr>
	            <s:iterator value="#request.proList" var="proList" status="num">
		            <tr>
						<td><s:property value="(#num.index+1)+(pageNow-1)*pageSize" /></td>
						<td><s:property value="empName" /></td>
						<td>
							<fmt:formatNumber type="number" value="${costMoney}" pattern="##0.00" />
						</td>
						<td>
							<fmt:formatNumber type="number" value="${cashMoney}" pattern="##0.00" />
						</td>
						<td>
							<fmt:formatNumber type="number" value="${cashMoney2}" pattern="##0.00" />
						</td>
						<td>
							<fmt:formatNumber type="number" value="${cashMoney4}" pattern="##0.00" />
						</td>
						<td>
							<fmt:formatNumber type="number" value="${receiveMoney}" pattern="##0.00" />
						</td>
						<td style="text-align:right;padding-right:5px;">
							<fmt:formatNumber type="number" value="${profit}" pattern="##0.00" />
						</td>
						<td style="text-align:right;padding-right:5px;"><fmt:formatNumber type="number" value="${profit2}" pattern="##0.00" /></td>
					</tr>
	            </s:iterator>
	            <tr>
					<td colspan="7"></td>
					<td style="text-align:right;padding-right:5px;font-weight:bold;">
						<fmt:formatNumber type="number" value="${sumMoney}" pattern="###,##0.00" />
					</td>
					<td style="text-align:right;padding-right:5px;font-weight:bold;">
						<fmt:formatNumber type="number" value="${sumMoney2}" pattern="###,##0.00" />
					</td>
				</tr>
	       </table>
	    </div>
	    <div class="clear"></div>
       <div class="right_4">
           	<div style="float:left; margin-left:5px;">
           		当前第${pageBean.pageNow}页/共${pageBean.pageCount}页
           	</div>
           	<div style="float:left; margin-left:340px;">
           		<a onclick="return forward(1)" href="javascript:void(0)">首页</a>
	           	<c:if test="${pageBean.pageNow==1}">
					上一页
				</c:if>
				<c:if test="${pageBean.pageNow>1}">
					<a onclick="return forward(2)" href="javascript:void(0)">上一页</a>
				</c:if>
				<c:if test="${pageBean.pageNow==pageBean.pageCount}">
					下一页
				</c:if>
				<c:if test="${pageBean.pageNow<pageBean.pageCount}">
					<a  onclick="return forward(3)" href="javascript:void(0)">下一页</a>
				</c:if>
				<a onclick="return forward(4)" href="javascript:void(0)">尾页</a>
				<label style="margin-left:20px;">跳转至</label>
				<input id="jumpPage" type="text" style="width:40px; height:15px; margin-left:5px;" />
				<input type="button" value="跳转" style="width:40px; height:25px;" onclick="forward(5);"/>
			</div>
        </div>
    </div>
  </body>
</html>

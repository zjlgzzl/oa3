<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>应付</title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<script type="text/javascript" src="${ctx}/res/js/My97DatePicker/WdatePicker.js"></script>
	<link type="text/css" rel="stylesheet"  href="${ctx}/res/css/styles.css"/>
	<link type="text/css" rel="stylesheet"  href="${ctx}/res/css/combo.select.css"/>
	<script type="text/javascript" src="${ctx}/res/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/res/js/jquery.combo.select.js"></script>
	<script type="text/javascript">
		//初始化
		$(document).ready(function(){
			//后台校验错误信息
			var errInfo = "${requestScope.errInfo}";
			if (errInfo != null && errInfo.length > 0){
				alert(errInfo);
			}
			$('._select').comboSelect();
		});
		//分页控件
		function forward(flag){
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
	</script>
  </head>
  <body>
  	<div class="right">
  		<div class="right_1">
			<form id="queryForm" action="${ctx}/report/CostCashoutReport.action" method="post">
				<table style="width:1200px; margin-left:0;">
					<tr>
						<td style="width:80px;">开始日期</td>
						<td style="text-align:left;">
							<input id="startDate" name="startDate" value="${query.startDate }"
									type="text" class="field border" style="width:130px" onclick="WdatePicker()" readonly="readonly"/>
						</td>
						<td style="width:80px;">结束日期</td>
						<td style="text-align:left;">
							<input id="endDate" name="endDate" value="${query.endDate }"
									type="text" class="field border" 
									style="width:130px" onclick="WdatePicker()" readonly="readonly"/>
						</td>
	  					<td style="width:80px;">服务单号</td>
						<td style="text-align:left; ">
							<input name="billNo" value="${query.billNo }" 
									type="text" class="field border" style="width:130px;" />
	  					</td>
	  					<td style="width:80px;">应付代理</td>
						<td style="text-align:left; ">
							<select id="agent" name="agent"  
									 class="field border _select" style="width:130px;margin-top:2px;border:1px solid lightblue;margin-left:2.5px;">
								<option value="">全部</option>
								<c:forEach items="${agentList }" var="agent">
									<option value="${agent.name }" <c:if test="${query.agent == agent.name }">selected</c:if>>${agent.name }</option>
								</c:forEach>
							</select>
	  					</td>
	  					<td style="width:80px;">客户名称</td>
						<td style="text-align:left; ">
							<select name="cusId" id="cus" 
									 class="field border _select" style="width:130px;margin-top:2px;border:1px solid lightblue;margin-left:2.5px;">
								<option value="">全部</option>
								<c:forEach items="${cusList }" var="cus">
									<option value="${cus.CId }" <c:if test="${query.cusId == cus.CId }">selected</c:if>>${cus.CAddr }</option>
								</c:forEach>
							</select>
	  					</td>
	  					<td style="width:80px;">支付状态</td>
						<td style="text-align:left; ">
							<select name="status" value="${query.status }" 
									 class="field border" style="width:130px;">
								<option value="">全部</option>
								<option value="payed" <c:if test="${query.status == 'payed' }">selected</c:if>>已支付</option>
								<option value="unpay" <c:if test="${query.status == 'unpay' }">selected</c:if>>未支付</option>
							</select>
	  					</td>
	  					<td>
							<input type="submit" value="查询" class="btn_img" style="margin-left:10px;"
										onclick="return forward(-1);"/>
							<input type="hidden" id="pageNow" name="pageNow" value="${pageNow}"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
        <div class="right_3">
	        <table id="table1" class="table" style="width:920px;">
	           	<tr>
	               	<th style="width:40px;">序号</th>
	               	<th style="width:150px;">服务单号</th>
	               	<th style="width:150px;">客户名称</th>
	               	<th style="width:80px;">应付对象</th>
	               	<th style="width:200px;">应付明细</th>
	               	<th style="width:80px;">应付金额</th>
	               	<th style="width:80px;">已付金额</th>
	               	<th style="width:80px;">差额</th>
	               	<th style="width:150px;">支出明细</th>
	               	<th style="width:80px;">支付状态</th>
	            </tr>
	            <s:iterator value="#request.list" var="list" status="num">
	            <tr>
					<td><s:property value="(#num.index+1)+(pageNow-1)*pageSize" /></td>
					<td><s:property value="billNo"/></td>
					<td><s:property value="name"/></td>
					<td><s:property value="agent"/></td>
					<td><s:property value="items"/></td>
					<td>$<s:property value="cosMoney"/><s:property value="cosMoneyTotal"/></td>
					<td>$<s:property value="payMoney"/><s:property value="payMoneyTotal"/></td>
					<td>$<s:property value="diffMoney"/><s:property value="diffMoneyTotal"/></td>
					<td><s:property value="remark"/></td>
					<td><s:property value="status"/></td>
					
				</tr>
	            </s:iterator>
	       </table>
	    </div>
       <div class="right_4">
           	<div style="float:left; margin-left:5px;">
           		当前第${pageBean.pageNow}页/共${pageBean.pageCount}页
           	</div>
           	<div style="float:left; margin-left:730px;">
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
				<!--  
				<label style="margin-left:20px;">跳转至</label>
				<input id="jumpPage" type="text" style="width:40px; height:15px; margin-left:5px;" />
				<input type="button" value="跳转" style="width:40px; height:25px;" onclick="forward(5);"/>
				-->
			</div>
        </div>
    </div>
  </body>
</html>

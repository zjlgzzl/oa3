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
			var complete = "${requestScope.proQuery.complete}";
			if (complete != null && complete != ""){
				var select = document.getElementById("complete");
				for(var j=0;j<select.options.length;j++){
					if(select.options[j].value == complete){
						select.options[j].selected = true;
						break;
					}
				}
			}
		});
		//分页控件
		function forward(flag){
			document.getElementById("queryForm").action = "${ctx}/report/BusinProfitReport2.action";
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
		function confirmArchive(businId){
			if (confirm("确认解除归档？")){
				document.getElementById("businId").value = businId;
				document.getElementById("queryForm").action = "${ctx}/report/CancelRateArchive.action";
				document.getElementById("queryForm").submit();
			}
		}
		function expFile(){
			document.getElementById("queryForm").action = "${ctx}/report/ExpProfitReport.action";
			document.getElementById("queryForm").submit();
		}
	</script>
  </head>
  <body>
  	<div class="right">
  		<div class="right_1">
			<form id="queryForm" action="${ctx}/report/BusinProfitReport2.action" method="post">
				<table style="width:1180px; margin-left:0;">
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
	  					<td style="width:90px;">客户名称</td>
						<td style="width:140px;text-align:left; ">
							<input id="cusName" name="proQuery.cusName" value="${requestScope.proQuery.cusName}" 
									type="text" class="field border" style="width:140px;" />
	  					</td>
						<td style="width:90px;">服务单号</td>
						<td style="width:100px;text-align:left; ">
							<input id="billNo" name="proQuery.billNo" value="${requestScope.proQuery.billNo}" 
									type="text" class="field border" style="width:100px;" />
	  					</td>
	  					<td style="width:70px;">是否完结</td>
	  					<td style="width:100px;text-align:left; ">
							<select id="complete" name="proQuery.complete" class="border" style="width:100px;">
				           		<option value="-1">全部</option>
				           		<option value="0">未完结</option>
				           		<option value="1">已完结</option>
							</select>
						</td>
	  					<td style="width:200px;">
							<input type="submit" value="查询" class="btn_img" style="margin-left:10px;"
										onclick="return forward(-1);"/>
							<input type="button" value="导出" class="btn_img" style="margin-left:10px;"
										onclick="expFile();"/>
							<input type="hidden" id="pageNow" name="pageNow" value="${pageNow}"/>
							<input type="hidden" id="businId" name="businId" />
						</td>
					</tr>
				</table>
			</form>
		</div>
       	<div class="right_2">
        </div>
        <div class="right_3">
	        <table id="table1" class="table" style="width:1470px;">
	           	<tr>
	               	<th style="width:40px;">序号</th>
	               	<!-- 
	               	<th style="width:90px;">日期</th>
	                -->
	               	<th style="width:270px;">客户名称</th>
	               	<th style="width:150px;">服务单号</th>
	               	<th style="width:200px;">Container #</th>
	               	<!--  
	               	<th style="width:90px;">外包</th>
	               	<th style="width:90px;">S.Dam Truck</th>
	               	<th style="width:90px;">Em Chanthy</th>
	               	<th style="width:90px;">Vat to S.Dam</th>
	               	-->
	               	<th style="width:90px;">成本金额</th>
					<th style="width:90px;">发票1</th>
					<th style="width:90px;">发票2</th>
					<th style="width:90px;">含税发票</th>
	               	<th style="width:90px;">实收金额</th>
	               	<th style="width:150px;">应得利润</th>
	               	<th style="width:90px;">应得利润<br/>百分比</th>
	               	<th style="width:150px;">实际利润</th>
	               	<th style="width:90px;">完结标记</th>
	                <th style="width:80px;"></th>
	            </tr>
	            <s:iterator value="#request.proList" var="proList" status="num">
		            <tr>
						<td><s:property value="(#num.index+1)+(pageNow-1)*pageSize" /></td>
						<!-- 
						<td><s:date name="cdate" format="dd-MM-yyyy"/></td>
						-->
						<td><s:property value="cusName"/></td>
						<td><s:property value="billNo"/></td>
						<td><s:property value="conNum"/></td>
						<!--  
						<td>
							<fmt:formatNumber type="number" value="${wbMoney}" pattern="###,##0.00" />
						</td>
						<td>
							<fmt:formatNumber type="number" value="${truck}" pattern="###,##0.00" />
						</td>
						<td>
							<fmt:formatNumber type="number" value="${em}" pattern="###,##0.00" />
						</td>
						<td>
							<fmt:formatNumber type="number" value="${vat}" pattern="###,##0.00" />
						</td>
						-->
						<td>
							<fmt:formatNumber type="number" value="${costMoney}" pattern="###,##0.00" />
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
							<fmt:formatNumber type="number" value="${receiveMoney}" pattern="###,##0.00" />
						</td>
						<td style="text-align:right;padding-right:5px;">
							<fmt:formatNumber type="number" value="${profit}" pattern="###,##0.00" />
						</td>
						<td>
							<s:if test="#request.costMoney != 0">
								<s:if test="#request.profit/#request.costMoney < 0.1">
									<span style="color:red;">
								</s:if>
								<fmt:formatNumber type="number" value="${profit/costMoney*100}" pattern="##0.00" />%
								<s:if test="#request.profit/#request.costMoney < 0.1">
									</span>
								</s:if>
							</s:if>
						</td>
						<td style="text-align:right;padding-right:5px;"><fmt:formatNumber type="number" value="${profit2}" pattern="##0.00" /></td>
						<td>
							<s:if test="complete==1">
								<span style="color:red;">已完结</span>
							</s:if>
							<s:if test="complete==0">
								未完结
							</s:if>
						</td>
						<td>
							<s:if test="rate_archive==1">
								<a href="javascript:void(0);" onclick="confirmArchive(<s:property value="businId"/>);">解除归档</a>
							</s:if>
						</td>
					</tr>
	            </s:iterator>
	            <tr>
					<td colspan="9"></td>
					<td style="text-align:right;padding-right:5px;font-weight:bold;">
						<fmt:formatNumber type="number" value="${sumMoney}" pattern="###,##0.00" />
					</td>
					<td></td>
					<td style="text-align:right;padding-right:5px;font-weight:bold;">
						<fmt:formatNumber type="number" value="${sumMoney2}" pattern="###,##0.00" />
					</td>
					<td colspan="3"></td>
				</tr>
	       </table>
	    </div>
	    <!--  
	    <div class="right_4" style="margin-top:10px;">
	    	<div style="float:left; margin-left:5px; font-weight:bold;">
           		成本金额总计：${requestScope.cmoney1}
           	</div>
           	<div style="float:left; margin-left:25px; font-weight:bold;">
           		应收金额总计：${requestScope.cmoney2}
           	</div>
           	<div style="float:left; margin-left:25px; font-weight:bold;">
           		实收金额总计：${requestScope.cmoney3}
           	</div>
           	<div style="float:left; margin-left:25px; font-weight:bold;">
           		应得利润总计：${requestScope.cmoney4}
           	</div>
	    	<div style="float:left; margin-left:25px; font-weight:bold;">
           		实际利润总计：${requestScope.cmoney5}
           	</div>
	    </div>
	    -->
	    <div class="clear"></div>
       <div class="right_4">
           	<div style="float:left; margin-left:5px;">
           		当前第${pageBean.pageNow}页/共${pageBean.pageCount}页
           	</div>
           	<div style="float:left; margin-left:640px;">
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

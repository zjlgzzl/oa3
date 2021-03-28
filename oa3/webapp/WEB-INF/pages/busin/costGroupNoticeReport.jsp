<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>财务收款提醒</title>
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
			var cusid = "${requestScope.queryBiaoji.TBusin.TCustomerByCCusid.CId}";
			var select = document.getElementById("cusid");
			for(var i=0;i<select.options.length;i++){
				if(select.options[i].value == cusid){
					select.options[i].selected = true;
					break;
				}
			}
			$('#cusid').comboSelect();
		});
		//分页控件
		function forward(flag){
			document.getElementById("queryForm").action = "${ctx}/busin/CostGroupNoticeReport.action";
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
		
		function cancalNotice(businId, groupId){
			var url="${ctx}/busin/CancelBiaojiNotice.action";
			document.getElementById("businId").value = businId;
			document.getElementById("groupId").value = groupId;
			document.getElementById("queryForm").action = url;
			document.getElementById("queryForm").submit();
		}
	</script>
  </head>
  <body>
  <form id="queryForm" action="" method="post">
	  	<div class="right">
	  		<div class="right_1">
				<table style="width:1980px; margin-left:0;">
					<tr>
	  					<td style="width:60px;">客户名称</td>
						<td style="width:470px;text-align:left; ">
							<select id="cusid" name="queryBiaoji.TBusin.TCustomerByCCusid.CId" 
									class="border" style="width:540px;border:1px solid lightblue;">
								<option value="-1">--全部--</option>
								<c:forEach var="cusList" items="${requestScope.cusList}">
									<option value="${cusList.CId}">${cusList.CAddr}</option>
								</c:forEach>
							</select>
	  					</td>
						<td style="width:70px;">服务单号</td>
						<td style="width:120px;text-align:left; ">
							<input id="billNo" name="queryBiaoji.TBusin.CBillNo" value="${requestScope.queryBiaoji.TBusin.CBillNo}" 
									type="text" class="field border" style="width:120px;" />
	  					</td>
	  					<td style="text-align:left;">
							<input type="submit" value="查询" class="btn_img" style="margin-left:5px;"
										onclick="return forward(-1);"/>
							<input type="hidden" id="pageNow" name="pageNow" value="${pageNow}"/>
							<input type="hidden" id="businId" name="businId" />
							<input type="hidden" id="groupId" name="groupId" />
						</td>
					</tr>
				</table>
			</div>
	       	<div class="right_2">
	        </div>
	        <div class="right_3">
		        <table id="table1" class="table" style="width:880px;">
		           	<tr>
		           		<th style="width:50px;">序号</th>
		           		<th style="width:150px;">业务单</th> 
		               	<th style="width:280px;">客户</th>
		               	<th style="width:200px;">成本组</th>
		               	<th style="width:120px;">原始数据</th>
		               	<th style="width:120px;">修改数据</th>
		               	<th style="width:100px;"></th>
		            </tr>
		            <s:iterator value="#request.reportList" var="reportList" status="num">
			            <tr>
							<td><s:property value="(#num.index+1)+(pageNow-1)*pageSize" /></td>
							<td><s:property value="TBusin.CBillNo"/></td>
							<td><s:property value="TBusin.TCustomerByCCusid.CAddr"/></td>
							<td><s:property value="TCostGroup.CName"/></td>
							<td style="text-align:right;padding-right:5px;">
								<fmt:formatNumber type="number" value="${CMoney}" pattern="##0.00" />
							</td>
							<td style="text-align:right;padding-right:5px;">
								<fmt:formatNumber type="number" value="${CMoney2}" pattern="##0.00" />
							</td>
							<td>
								<s:if test="#session.fun317==1">
									<a href="javascript:void(0);" 
										onclick="cancalNotice(<s:property value="TBusin.CId"/>,
															  <s:property value="TCostGroup.CId"/>);">取消提醒</a>
								</s:if>
							</td>
						</tr>
		            </s:iterator>
		       </table>
		    </div>
		    <div class="clear"></div>
	       <div class="right_4">
	           	<div style="float:left; margin-left:5px;">
	           		当前第${pageBean.pageNow}页/共${pageBean.pageCount}页
	           	</div>
	           	<div style="float:left; margin-left:460px;">
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
	  </form>
  </body>
</html>

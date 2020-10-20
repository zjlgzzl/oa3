<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>成本项目组</title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<link type="text/css" rel="stylesheet"  href="${ctx}/res/css/styles.css"/>
	<link type="text/css" rel="stylesheet"  href="${ctx}/res/css/styles.css"/>
	<link type="text/css" rel="stylesheet"  href="${ctx}/res/css/combo.select.css"/>
	<script type='text/javascript' src="${ctx}/dwr/interface/serviceItemService.js"></script>
  	<script type='text/javascript' src="${ctx}/dwr/engine.js"></script>
 	<script type='text/javascript' src="${ctx}/dwr/util.js"></script>
	<script type="text/javascript" src="${ctx}/res/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/res/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/res/js/jquery.combo.select.js"></script>
	<script type="text/javascript">
	
		var detailCount = -1;

		//后台提示
		$(document).ready(function(){
		
			javascript:dwr.engine.setAsync(false);//设置dwr同步
			
			//数量
			var count = "${requestScope.citemsCount}";
			if (Number(count) > 0){
				detailCount = Number(count) - 1;//标记从0开始
			}
			
			for (var i=0; i<=detailCount; i++){
				var cashItem = document.getElementById("_cashItem"+i).value;
				if (cashItem != null && cashItem != ""){
					var select = document.getElementById("cashItem"+i);
					for(var j=0;j<select.options.length;j++){
						if(select.options[j].value == cashItem){
							select.options[j].selected = true;
							break;
						}
					}
				}
			}
			
			javascript:dwr.engine.setAsync(true);//设置dwr异步
			
			setXuh();
			
			//后台校验错误信息
			var errInfo = "${requestScope.errInfo}";
			if (errInfo != null && errInfo.length > 0){
				alert(errInfo);
			}
			
		});
		
		//返回
		function returnList(){
			document.getElementById("editForm").action = "${ctx}/busin/CostGroupList.action";
			document.getElementById("editForm").submit();
		}
		
		
		/*设置序号*/
		function setXuh(){
			var k = 1;
			var tb = document.getElementById("detailTable");
			var rows = tb.rows.length - 1;
			for (var i=1; i<=rows; i++){
				var cid = tb.rows[i].id.toString().substring(2);
				document.getElementById("xuh"+cid).innerHTML = k;
				k ++;
			}
		}
		
	</script>
  </head>
  <body>
  	<div id="mainContent">
  		<div class="right">
			<form id="editForm" action="${ctx}/busin/UpdateFinanceGroup.action" method="post">
				<div style="margin-left:5px; width:650px; text-align:center; margin-top:50px;">
					<span style="color:black; font-size:14px; font-weight:bold;">成本项目组 </span>
				</div>
				<table id="mainTable" class="table" style="margin-top:30px; width:650px; margin-left:5px;">
					<tr>
						<td style="width:150px;">
							成本项目组名称
						</td>
						<td style="width:500px;text-align:left;padding-left:5px;">
							${requestScope.cgroup.CName}
							<input type="hidden" name="cgroup.CId" value="${requestScope.cgroup.CId}"/>
							<input type="hidden" name="pageNow" value="${pageNow}"/>
							<input type="hidden" name="cgroupQuery.CName" value="${requestScope.cgroupQuery.CName}"/>
						</td>
					</tr>
					<tr>
						<td>
							备注
						</td>
						<td style="text-align:left;padding-left:5px;">
							${requestScope.cgroup.CRemarks}
						</td>
					</tr>
				</table>
				<table id="detailTable" class="table" style="margin-top:0; width:651px; margin-left:5px; border-top:0px;">
					<tbody id="detail">
						<tr>
							<td style="width:40px; border-top:0px;">
								<label style="color:black;">序号</label>
							</td>
							<td style="width:515px; border-top:0px;">
								成本项目
							</td>
							<td style="width:99px; border-top:0px;">
								排序码
							</td>
						</tr>
						<s:iterator value="#request.cgroupItems" var="cgroupItems" status="num">
							<tr id="tr<s:property value="#num.index" />">
								<td>
									<div id="xuh<s:property value="#num.index" />"></div>
								</td>
								<td>
									<select id="cashItem<s:property value="#num.index" />"
											name="cgroupItems[<s:property value="#num.index" />].TCostItem.CId"
								 			class="border _select" style="width:500px;" disabled="disabled">
										<option value="0">PLEASE CHOOSE</option>
										<c:forEach var="costItemList" items="${requestScope.costItemList}">
											<option value="${costItemList.CId}">${costItemList.CName}</option>
										</c:forEach>
									</select>
									<input type="hidden" id="_cashItem<s:property value="#num.index" />" 
										  value="${cgroupItems[num.index].TCostItem.CId}" />
								</td>
								<td>
									${cgroupItems[num.index].COrder}
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
		        <div style="margin:0px;">
		           	<input type="button" value="返回" class="return" style="width:60px;height:20px;margin-left:400px;" onclick="returnList();"/>
		        </div>
	        </form>
        </div>
  	</div>
  </body>
</html>

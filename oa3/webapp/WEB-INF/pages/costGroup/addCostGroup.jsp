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
		var cashStr = null;
		var costItemId = null;
		var costItemName = null;

		//后台提示
		$(document).ready(function(){
		
			javascript:dwr.engine.setAsync(false);//设置dwr同步
			
			//数量
			var count = "${requestScope.itemsCount}";
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
			
			costItemId = "${requestScope.costItemId}";
			costItemId = costItemId.substring(1, costItemId.length - 1).split(", ");
			costItemName = "${requestScope.costItemName}";
			costItemName = costItemName.substring(1, costItemName.length - 1).split(", ");
			
			javascript:dwr.engine.setAsync(true);//设置dwr异步
			
			$('._select').comboSelect();
			
			//后台校验错误信息
			var errInfo = "${requestScope.errInfo}";
			if (errInfo != null && errInfo.length > 0){
				alert(errInfo);
			}
			
			document.getElementById("cname").focus();
			
		});
		
		//返回
		function returnList(){
			document.getElementById("editForm").action = "${ctx}/busin/CostGroupList.action";
			document.getElementById("editForm").submit();
		}
		
		//保存之前的数据校验
		function check(){
			//成本项目组名称
			var cname = document.getElementById("cname").value;
			if ($.trim(cname) == ""){
				alert("成本项目组名称不能为空");
				document.getElementById("cname").focus();
				return false;
			}
			//明细
			if (detailCount == -1){
				alert("请增加成本项目");
				return false;
			}
			//成本项目
			for(var i=0; i<=detailCount; i++){
				var cashItem = document.getElementById("cashItem" + i);
				if (cashItem == null){
					continue;
				}
				cashItem = document.getElementById("cashItem" + i).value;
				if (cashItem == 0){
					alert("请选择成本项目");
					document.getElementById("cashItem" + i).focus();
					return false;
				}
			}
			return true;
		}
		
		function addDetail(){
			//数量
			detailCount = Number(detailCount) + 1;
			//创建行
			var tr = document.createElement("tr");
			tr.id = "tr" + detailCount.toString();
			//序号
			var td = document.createElement("td");
			td.innerHTML = '<div id="xuh' + detailCount + '"></div>';
			tr.appendChild(td);
			//成本项目
			var td2 = document.createElement("td");
			td2.innerHTML = '<select id="cashItem' + detailCount + '"' +  
							'		 name="cgroupItems[' + detailCount + '].TCostItem.CId"' +
							'		 class="border _select" style="width:400px;">' +
							'	<option value="0">PLEASE CHOOSE</option>' +
							'</select>';
			tr.appendChild(td2);
			//排序码
			var td3 = document.createElement("td");
			td3.innerHTML = '<input id="order' + detailCount + '"' +
							'       name="cgroupItems[' + detailCount + '].COrder"' +
							'		class="border field" style="width:90px;text-align:center;" />';
			tr.appendChild(td3);
			//删除按钮
			var td9 = document.createElement("td");
			td9.innerHTML = '<input type="button" value="删除" style="width:60px; height:20px;"' +
							'		onclick = "deleteDetail(this)" />';
			tr.appendChild(td9);
			
			document.getElementById("detail").appendChild(tr);
			
			setXuh();
			
			//获取成本项目下拉
			getCashSelect(detailCount);
			
			$('._select').comboSelect();
			
		}
		
		/*获取成本项目下拉*/
		function getCashSelect(count){
			var cashItem = document.getElementById("cashItem"+count);
			for (var i=0; i<costItemId.length; i++){
				cashItem.options.add(new Option(costItemName[i], costItemId[i])); 
			}
		}
		
		/*删除成本*/
		function deleteDetail(obj){
			if (confirm("确认删除？")){		
				var tr = obj.parentNode.parentNode;
				var tbody = tr.parentNode;
				tbody.removeChild(tr);
			}
			setXuh();
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
			<form id="editForm" action="${ctx}/busin/SaveCostGroup.action" method="post">
				<div style="margin-left:5px; width:650px; text-align:center; margin-top:50px;">
					<span style="color:black; font-size:14px; font-weight:bold;">成本项目组 </span>
				</div>
				<table id="mainTable" class="table" style="margin-top:30px; width:650px; margin-left:5px;">
					<tr>
						<td style="width:150px;">
							<span style="color:red;">*</span><label style="color:black;">成本项目组名称</label>
						</td>
						<td style="width:500px;">
							<input id="cname" name="cgroup.CName" value="${requestScope.cgroup.CName}"
									type="text" class="field border" style="width:485px"/>
							<input type="hidden" name="pageNow" value="${pageNow}"/>
							<input type="hidden" name="cgroupQuery.CName" value="${requestScope.cgroupQuery.CName}"/>
						</td>
					</tr>
					<tr>
						<td>
							代理Agent
						</td>
						<td>
							<select id="agent" name="cgroup.agent" class="field border" style="width:485px">
								<option value="">PLEASE CHOOSE</option>
								<c:forEach items="${agentList }" var="agent">
									<option value="${agent.name }">${agent.name }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							备注
						</td>
						<td>
							<input id="remarks" name="cgroup.CRemarks" value="${requestScope.cgroup.CRemarks}"
									type="text" class="field border" style="width:485px;"/>
						</td>
					</tr>
				</table>
				<table id="detailTable" class="table" style="margin-top:0; width:646px; margin-left:5px; border-top:0px;">
					<tbody id="detail">
						<tr>
							<td style="width:40px; border-top:0px;">
								<label style="color:black;">序号</label>
							</td>
							<td style="width:410px; border-top:0px;">
								<span style="color:red;">*</span>成本项目
							</td>
							<td style="width:100px; border-top:0px;">
								排序码
							</td>
							<td style="width:98px; border-top:0px;">
								<input type="button" value="添加" style="width:60px; height:20px;"
											onclick = "addDetail()" />
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
								 			class="border _select" style="width:400px;">
										<option value="0">PLEASE CHOOSE</option>
										<c:forEach var="costItemList" items="${requestScope.costItemList}">
											<option value="${costItemList.CId}">${costItemList.CName}</option>
										</c:forEach>
									</select>
									<input type="hidden" id="_cashItem<s:property value="#num.index" />" 
										  value="${cgroupItems[num.index].TCostItem.CId}" />
								</td>
								<td>
									
								</td>
								<td>
									<input type="button" value="删除" style="width:60px; height:20px;"
											onclick = "deleteDetail(this)" />
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
		        <div style="margin:0px;">
		           	<input type="submit" value="保存" class="save" style="width:60px;height:20px;margin-left:300px;" onclick="return check();"/>
		           	<input type="button" value="返回" class="return" style="width:60px;height:20px" onclick="returnList();"/>
		        </div>
	        </form>
        </div>
  	</div>
  </body>
</html>

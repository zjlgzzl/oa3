<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>回款项目组</title>
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
			
			cashStr = "${cashStr}";
			
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
			document.getElementById("editForm").action = "${ctx}/busin/FinanceGroupList.action";
			document.getElementById("editForm").submit();
		}
		
		//保存之前的数据校验
		function check(){
			//回款项目组名称
			var cname = document.getElementById("cname").value;
			if ($.trim(cname) == ""){
				alert("回款项目组名称不能为空");
				document.getElementById("cname").focus();
				return false;
			}
			//明细
			if (detailCount == -1){
				alert("请增加回款项目");
				return false;
			}
			//回款项目
			for(var i=0; i<=detailCount; i++){
				var cashItem = document.getElementById("cashItem" + i);
				if (cashItem == null){
					continue;
				}
				cashItem = document.getElementById("cashItem" + i).value;
				if (cashItem == 0){
					alert("请选择回款项目");
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
			//回款项目
			var td2 = document.createElement("td");
			td2.innerHTML = '<select id="cashItem' + detailCount + '"' +  
							'		 name="groupItems[' + detailCount + '].TCostItem.CId"' +
							'		 class="border _select" style="width:400px;">' +
							'	<option value="0">PLEASE CHOOSE</option>' +
							'</select>';
			tr.appendChild(td2);
			//删除按钮
			var td9 = document.createElement("td");
			td9.innerHTML = '<input type="button" value="删除" style="width:60px; height:20px;"' +
							'		onclick = "deleteDetail(this)" />';
			tr.appendChild(td9);
			
			document.getElementById("detail").appendChild(tr);
			
			setXuh();
			
			//获取回款项目下拉
			getCashSelect(detailCount);
			
			$('._select').comboSelect();
			
		}
		
		/*获取回款项目下拉*/
		function getCashSelect(count){
			var cashItem = document.getElementById("cashItem"+count);
			var myobj=eval(cashStr);
			for(var i=0;i<myobj.length;i++){  
			   cashItem.options.add(new Option(myobj[i].name, myobj[i].id)); 
			}
		}
		
		/*删除回款*/
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
			<form id="editForm" action="${ctx}/busin/UpdateFinanceGroup.action" method="post">
				<div style="margin-left:5px; width:350px; text-align:center; margin-top:50px;">
					<span style="color:black; font-size:14px; font-weight:bold;">回款项目组 </span>
				</div>
				<table id="mainTable" class="table" style="margin-top:30px; width:550px; margin-left:5px;">
					<tr>
						<td style="width:150px;">
							回款项目组名称
						</td>
						<td style="width:400px;">
							<input id="cname" name="group.CName" value="${requestScope.group.CName}"
									type="text" class="field border" style="width:385px" disabled="disabled" />
							<input type="hidden" name="group.CId" value="${requestScope.group.CId}"/>
							<input type="hidden" name="pageNow" value="${pageNow}"/>
							<input type="hidden" name="groupQuery.CName" value="${requestScope.groupQuery.CName}"/>
						</td>
					</tr>
					<tr>
						<td>
							备注
						</td>
						<td>
							<input id="remarks" name="group.CRemarks" value="${requestScope.group.CRemarks}"
									type="text" class="field border" style="width:385px;" disabled="disabled"/>
						</td>
					</tr>
				</table>
				<table id="detailTable" class="table" style="margin-top:0; width:550px; margin-left:5px; border-top:0px;">
					<tbody id="detail">
						<tr>
							<td style="width:40px; border-top:0px;">
								<label style="color:black;">序号</label>
							</td>
							<td style="width:510px; border-top:0px;">
								回款项目
							</td>
						</tr>
						<s:iterator value="#request.groupItems" var="groupItems" status="num">
							<tr id="tr<s:property value="#num.index" />">
								<td>
									<div id="xuh<s:property value="#num.index" />"></div>
								</td>
								<td>
									<select id="cashItem<s:property value="#num.index" />"
											name="groupItems[<s:property value="#num.index" />].TCostItem.CId"
								 			class="border _select" style="width:500px;" disabled="disabled">
										<option value="0">PLEASE CHOOSE</option>
										<c:forEach var="cashItemList" items="${requestScope.cashItemList}">
											<option value="${cashItemList.CId}">${cashItemList.CName}</option>
										</c:forEach>
									</select>
									<input type="hidden" id="_cashItem<s:property value="#num.index" />" 
										  value="${groupItems[num.index].TCostItem.CId}" />
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

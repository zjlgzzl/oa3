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
	<link type="text/css" rel="stylesheet"  href="${ctx}/res/css/combo.select.css"/>
	<script type="text/javascript" src="${ctx}/res/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/res/js/jquery.combo.select.js"></script>
	<script type="text/javascript">
		//初始化
		$(document).ready(function(){
			//客户列表
			var cusid = "${requestScope.query.cusId}";
			if (cusid != null && cusid != ""){
				var select = document.getElementById("cusid");
				for(var i=0;i<select.options.length;i++){
					if(select.options[i].value == cusid){
						select.options[i].selected = true;
						break;
					}
				}
			}
			//归档状态
			var businState = "${requestScope.query.businState}";
			if (businState != null && businState != "" && businState != -1){
				var select = document.getElementById("businState");
				if (select != null){
					for(var i=0; i<select.options.length; i++){
						if(select.options[i].value == businState){
							select.options[i].selected = true;
							break;
						}
					}
				}
			}
			
			//加急状态
			var orderNum = "${requestScope.query.orderNum}";
			if (orderNum != null && orderNum != "" && orderNum != -1){
				var select = document.getElementById("orderNum");
				if (select != null){
					for(var i=0; i<select.options.length; i++){
						if(select.options[i].value == orderNum){
							select.options[i].selected = true;
							break;
						}
					}
				}
			}
			
			//锁定标记
			var lockFlag = "${requestScope.query.lockFlag}";
			if (lockFlag != null && lockFlag != "" && lockFlag != -1){
				var select = document.getElementById("lockFlag");
				if (select != null){
					for(var i=0; i<select.options.length; i++){
						if(select.options[i].value == lockFlag){
							select.options[i].selected = true;
							break;
						}
					}
				}
			}
			
			//成本重复
			var costDupFlag = "${requestScope.query.costDupFlag}";
			if (costDupFlag != null && costDupFlag > 0){
				var select = document.getElementById("dupFlag");
				if (select != null){
					for(var i=0; i<select.options.length; i++){
						if(select.options[i].value == costDupFlag){
							select.options[i].selected = true;
							break;
						}
					}
				}
			}
			
			var TBusinType = "${requestScope.query.typeId}";
			if (TBusinType != null && TBusinType != ""){
				var select = document.getElementById("TBusinType");
				for(var j=0;j<select.options.length;j++){
					if(select.options[j].value == TBusinType){
						select.options[j].selected = true;
						break;
					}
				}
			}
			
			if (document.getElementById("businStateName") != null){
				var businStateName = "${requestScope.query.businStateName}";
				if (businStateName != null && businStateName != ""){
					var select = document.getElementById("businStateName");
					for(var j=0;j<select.options.length;j++){
						if(select.options[j].value == businStateName){
							select.options[j].selected = true;
							break;
						}
					}
				}
			}
			
			var completeFlag = "${requestScope.query.completeFlag}";
			if (completeFlag != null && completeFlag != ""){
				var select = document.getElementById("completeFlag");
				for(var j=0;j<select.options.length;j++){
					if(select.options[j].value == completeFlag){
						select.options[j].selected = true;
						break;
					}
				}
			}
			
			//后台校验错误信息
			var errInfo = "${requestScope.errInfo}";
			if (errInfo != null && errInfo.length > 0){
				alert(errInfo);
			}
			
			$('#cusid').comboSelect();
			
		});
		//分页控件
		function forward(flag){
			document.getElementById("queryForm").action = "${ctx}/busin/BusinList.action";
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
		//检查是否锁定
		function checkLock(businId){
			var url = "${ctx}/busin/CheckLock.action?businId=" + businId;
			var msg = "";
			$.ajax({
				type:"Post",
				async: false,
				url: url,
				success:function(data){
					if (data != ""){
						msg = data;
					}
				}
			});
			if (msg != ""){
				alert(msg);
				return false;
			}
			return true;
		}
		//添加
		function add(){
			document.getElementById("queryForm").action = "${ctx}/busin/AddBusin.action";
			document.getElementById("queryForm").submit();
		}
		//修改
		function edit(cid){
			if(!checkLock(cid)){
				return;
			}
			document.getElementById("cid").value = cid;
			document.getElementById("queryForm").action = "${ctx}/busin/EditBusin.action";
			document.getElementById("queryForm").submit();
		}
		//查看
		function view(cid){
			document.getElementById("cid").value = cid;
			document.getElementById("queryForm").action = "${ctx}/busin/ViewBusin.action";
			document.getElementById("queryForm").submit();
		}
		//修改归档
		function editArchiving(cid){
			if(!checkLock(cid)){
				return;
			}
			document.getElementById("cid").value = cid;
			document.getElementById("queryForm").action = "${ctx}/busin/EditArchiving.action";
			document.getElementById("queryForm").submit();
		}
		//归档确认
		function conClose(cid,flag){
			if (confirm("归档后的单据将不允许修改任何信息，确认单据已经全部执行完成并归档？")){
				document.getElementById("cid").value = cid;
				document.getElementById("archiveFlag").value = flag;
				document.getElementById("queryForm").action = "${ctx}/busin/BusinClose.action";
				document.getElementById("queryForm").submit();
			}
		}
		//归档确认--回款
		function conArchivingClose(cid,flag){
			if (confirm("归档后的单据将不允许修改任何信息，确认单据已经全部执行完成并归档？")){
				document.getElementById("cid").value = cid;
				document.getElementById("archiveFlag").value = flag;
				document.getElementById("queryForm").action = "${ctx}/busin/BusinArchivingClose.action";
				document.getElementById("queryForm").submit();
			}
		}
		//解除归档确认
		function conOpen(cid){
			if (confirm("确认解除归档？")){
				document.getElementById("cid").value = cid;
				document.getElementById("queryForm").action = "${ctx}/busin/BusinOpen.action";
				document.getElementById("queryForm").submit();
			}
		}
		function complete3(flag,cid){
			var msg = "";
			if (flag==1){
				msg = "完结";
			}else{
				msg = "解除完结";
			}
			if (confirm("确认"+msg+"?")){
				document.getElementById("cid").value = cid;
				document.getElementById("archiveFlag").value = flag;
				document.getElementById("queryForm").action = "${ctx}/busin/BusinComplete3.action";
				document.getElementById("queryForm").submit();
			}
		}
		function complete2(flag,cid){
			var msg = "";
			if (flag==1){
				msg = "完结";
			}else{
				msg = "解除完结";
			}
			if (confirm("确认"+msg+"?")){
				document.getElementById("cid").value = cid;
				document.getElementById("archiveFlag").value = flag;
				document.getElementById("queryForm").action = "${ctx}/busin/BusinComplete2.action";
				document.getElementById("queryForm").submit();
			}
		}
		
		//回款登记
		function editCashBusin(cid){
			if(!checkLock(cid)){
				return;
			}
			var url = "${ctx}/busin/CheckBjFlagBeforeEdit.action?businId="+cid;
			$.ajax({
				type:"Post",
				async: false,
				url: url,
				success:function(data){
					if (data != ""){
						var flag = Number(data);
						if (flag == 1){
							alert("请先标记成本项目组!");
							return;
						}else{
							document.getElementById("cid").value = cid;
							document.getElementById("queryForm").action = "${ctx}/busin/EditCashBusin.action";
							document.getElementById("queryForm").submit();
						}
					}
				}
			});
		}
		
		//已回款
		function updateRecieveFlag(cid){
			document.getElementById("cid").value = cid;
			document.getElementById("queryForm").action = "${ctx}/busin/UpdateRecieveFlag.action";
			document.getElementById("queryForm").submit();
		}
		function updateRecieveFlag2(cid){
			document.getElementById("cid").value = cid;
			document.getElementById("queryForm").action = "${ctx}/busin/UpdateRecieveFlag2.action";
			document.getElementById("queryForm").submit();
		}
		
		function setOrder(flag,cid){
			if (confirm("确认取消加急？")){
				document.getElementById("orderFlag").value = flag;
				document.getElementById("cid").value = cid;
				document.getElementById("queryForm").action = "${ctx}/busin/SetOrder.action";
				document.getElementById("queryForm").submit();
			}
		}
		function setOrder2(flag,cid){
			if (confirm("确认取消加急？")){
				document.getElementById("orderFlag").value = flag;
				document.getElementById("cid").value = cid;
				document.getElementById("queryForm").action = "${ctx}/busin/SetOrder2.action";
				document.getElementById("queryForm").submit();
			}
		}
		
		function businLock3(cid){
			if (confirm("确认锁定？")){
				document.getElementById("cid").value = cid;
				document.getElementById("queryForm").action = "${ctx}/busin/BusinLock3.action";
				document.getElementById("queryForm").submit();
			}
		}
		function businLock2(cid){
			if (confirm("确认锁定？")){
				document.getElementById("cid").value = cid;
				document.getElementById("queryForm").action = "${ctx}/busin/BusinLock2.action";
				document.getElementById("queryForm").submit();
			}
		}
		
		function businUnlock3(cid){
			if (confirm("确认解除锁定？")){
				document.getElementById("cid").value = cid;
				document.getElementById("queryForm").action = "${ctx}/busin/BusinUnlock3.action";
				document.getElementById("queryForm").submit();
			}
		}
		function businUnlock2(cid){
			if (confirm("确认解除锁定？")){
				document.getElementById("cid").value = cid;
				document.getElementById("queryForm").action = "${ctx}/busin/BusinUnlock2.action";
				document.getElementById("queryForm").submit();
			}
		}
		
		
		//导出
		function exp(){
			document.getElementById("queryForm").action = "${ctx}/busin/ExpBusinList.action";
			document.getElementById("queryForm").submit();
		}
		function updateRemarks(rownum,businId){
			var archiveRemarks = document.getElementById("archiveRemarks"+rownum).value;
			var url="${ctx}/busin/UpdateArchiveRemarks.action?businId=" + businId + "&archiveRemarks=" + encodeURI(archiveRemarks);
			$.ajax({
				type:"Post",
				url:url,
				success:function(data){
					if (data != ""){
						alert(data);
					}
				}
			});
		}
		//加急备注
		function updateKaipiaoRemarks(rownum,businId){
			var kaipiaoRemarks = document.getElementById("kaipiaoRemarks"+rownum).value;
			var url="${ctx}/busin/UpdateKaipiaoRemarks.action?businId=" + businId + "&archiveRemarks=" + encodeURI(kaipiaoRemarks);
			$.ajax({
				type:"Post",
				url:url,
				success:function(data){
					if (data != ""){
						alert(data);
					}
				}
			});
		}
		function updateFileRemarks(rownum,businId){
			var fileRemarks = document.getElementById("fileRemarks"+rownum).value;
			var url="${ctx}/busin/UpdateFileRemarks.action?businId=" + businId + "&fileRemarks=" + encodeURI(fileRemarks);
			$.ajax({
				type:"Post",
				url:url,
				success:function(data){
					if (data != ""){
						alert(data);
					}
				}
			});
		}
		function updateRecieveRemarks(rownum,businId){
			var recieveRemarks = document.getElementById("recieveRemarks"+rownum).value;
			var url="${ctx}/busin/UpdateRecieveRemarks.action?businId=" + businId + "&recieveRemarks=" + encodeURI(recieveRemarks);
			$.ajax({
				type:"Post",
				url:url,
				success:function(data){
					if (data != ""){
						alert(data);
					}
				}
			});
		}
		
		function setNewCostFlag(businId){
			document.getElementById("cid").value = businId;
			document.getElementById("queryForm").action = "${ctx}/busin/SetNewCostFlag2.action";
			document.getElementById("queryForm").submit();
		}
		
		function setCostDupFlag(businId,flag){
			document.getElementById("cid").value = businId;
			document.getElementById("queryForm").action = "${ctx}/busin/SetCostDupFlag" + flag + ".action";
			document.getElementById("queryForm").submit();
		}
		
		
		function selectItem(){
			var tb = document.getElementById("table1");
			var rowcount = tb.rows.length - 1;
			for(var i=0; i<rowcount; i++){
				if (document.getElementById("delBtn").checked){
					//全选
					document.getElementsByName("businIds")[i].checked = true;
				}else{
					//全不选
					document.getElementsByName("businIds")[i].checked = false;
				}
			}
		}
		function confirmLock(flag){
			document.getElementById("_lock").value = flag;
			document.getElementById("queryForm").action = "${ctx}/busin/UpdateLock.action";
			document.getElementById("queryForm").submit();
		}
		
	</script>
  </head>
  <body>
  	<form id="queryForm" action="${ctx}/busin/BusinList.action" method="post">
	  	<div class="right">
	  		<div class="right_1">
					<table style="width:1420px; margin-left:0;">
						<tr>
							<td style="width:120px;">单据编号</td>
							<td style="width:120px;text-align:left; ">
								<input id="billNo" name="query.billNo" value="${requestScope.query.billNo}" 
										type="text" class="field border" style="width:120px;" />
		  					</td>
		  					<td style="width:70px;">单据类型</td>
							<td style="width:120px;text-align:left; ">
								<select id="TBusinType" name="query.typeId" class="border" style="width:146px;">
					           		<option value="0">PLEASE CHOOSE</option>
									<c:forEach var="businTypeList" items="${requestScope.businTypeList}">
										<option value="${businTypeList.CId}">${businTypeList.CName}</option>
									</c:forEach>
								</select>
		  					</td>
		  					<td style="width:70px;">客户名称</td>
							<td style="width:200px;text-align:left; ">
								<select id="cusid" name="query.cusId" class="border" style="width:195px;border:1px solid lightblue;">
									<option value="-1">--全部--</option>
									<c:forEach var="cusList" items="${requestScope.cusList}">
										<option value="${cusList.CId}">${cusList.CAddr}</option>
									</c:forEach>
								</select>
		  					</td>
		  					<td style="width:100px;">
		  						<s:if test="#request.listFlag == 6">
		  							开票开始日期
		  						</s:if>
		  						<s:else>
		  							单据开始日期
		  						</s:else>
		  					</td>
							<td style="text-align:left; width:100px;">
								<input id="startDate" name="startDate" value="${requestScope.startDate}"
										type="text" class="field border" style="width:100px" onclick="WdatePicker()" readonly="readonly"/>
		  					</td>
		  					<td style="width:100px;">
		  						<s:if test="#request.listFlag == 6">
		  							开票结束日期
		  						</s:if>
		  						<s:else>
		  							单据结束日期
		  						</s:else>
		  					</td>
							<td style="text-align:left; width:100px;">
								<input id="endDate" name="endDate" value="${requestScope.endDate}" 
										type="text" class="field border" style="width:100px" onclick="WdatePicker()" readonly="readonly"/>
		  					</td>
		  					<td style="width:70px;">是否完结</td>
		  					<td style="width:100px;text-align:left; ">
								<select id="completeFlag" name="query.completeFlag" class="border" style="width:100px;">
					           		<option value="-1">全部</option>
					           		<option value="0">未完结</option>
					           		<option value="1">已完结</option>
								</select>
							</td>
							<td style="width:180px;"></td>
						</tr>
						<tr>
							<td style="width:70px;">货柜号码</td>
							<td style="width:120px;text-align:left; ">
								<input id="conNum" name="conNum" value="${requestScope.conNum}" 
										type="text" class="field border" style="width:120px;" />
		  					</td>
		  					<td style="width:70px;">BILL NO</td>
							<td style="width:120px;text-align:left; ">
								<input name="billNo" value="${requestScope.billNo}" 
										type="text" class="field border" style="width:120px;" />
		  					</td>
		  					<s:if test="listFlag == 10">
			  					<td style="width:70px;">归档状态</td>
			  					<td style="width:70px;text-align:left; ">
									<select id="businState" name="query.businState" class="border" style="width:70px;">
						           		<option value="-1">全部</option>
						           		<option value="2">未完成</option>
						           		<option value="3">已完成</option>
									</select>
								</td>
							</s:if>
							<s:if test="listFlag == 6">
								<td style="width:200px;" colspan="2">
									<label>加急状态</label>
				  					<select id="orderNum" name="query.orderNum" class="border" style="width:60px;">
						           		<option value="-1">全部</option>
						           		<option value="1">加急</option>
									</select>
									<label>是否锁定</label>
				  					<select id="lockFlag" name="query.lockFlag" class="border" style="width:60px;">
						           		<option value="-1">全部</option>
						           		<option value="1">锁定</option>
						           		<option value="0">未锁定</option>
									</select>
								</td>
							</s:if>
							
							<td style="width:70px;">业务员</td>
		  					<td style="width:70px;text-align:left; ">
								<input id="empName" name="query.empName" value="${requestScope.query.empName}" 
									type="text" class="field border" style="width:100px;" />
							</td>
							<td style="width:70px;">成本重复</td>
		  					<td style="width:70px;text-align:left; ">
								<select id="dupFlag" name="query.costDupFlag" class="border" style="width:70px;">
					           		<option value="0">全部</option>
					           		<option value="1">重复</option>
								</select>
							</td>
							<td style="width:70px;">是否开票</td>
		  					<td style="width:70px;text-align:left; ">
								<select id="businStateName" name="query.businStateName" class="border" style="width:70px;">
					           		<option value="0">全部</option>
					           		<option value="1">已开票</option>
					           		<option value="2">未开票</option>
								</select>
							</td>
		  					<td colspan="5" style="text-align:left;">
		  						<input type="submit" value="查询" class="btn_img"
										style="margin-left:10px;"
										onclick="return forward(-1);"/>
								<s:if test="#session.userName != 'admin'">
					       			<s:if test="listFlag == 1">
					       				<input type="button" value="添加" class="btn_img" style="margin-left:10px;"
												onclick="add();"/>
					       			</s:if>
					       		</s:if>
								<input type="button" value="导出" class="btn_img" style="margin-left:10px;"
										onclick="exp();"/>
								<input type="hidden" id="pageNow" name="pageNow" value="${pageNow}"/>
								<input type="hidden" id="listFlag" name="listFlag" value="${listFlag}"/>
								<input type="hidden" name="query.recieveFlag" value="${requestScope.query.recieveFlag}"/>
								<input type="hidden" id="cid" name="businId"/>
								<input type="hidden" id="archiveFlag" name="archiveFlag" value="${requestScope.archiveFlag}"/>
								<input type="hidden" id="orderFlag" name="orderFlag" value="${requestScope.orderFlag}"/>
								<input type="hidden" id="_lock" name="_lock"/>
							</td>
						</tr>
						<s:if test="#request.listFlag == 6">
							<tr>
								<td style="width:120px;">CLEARANCE DATE</td>
								<td style="text-align:left; width:100px;">
									<input name="startDate2" value="${requestScope.startDate2}"
											type="text" class="field border" style="width:120px" onclick="WdatePicker()" readonly="readonly"/>
			  					</td>
			  					<td style="width:100px;">至</td>
								<td style="text-align:left; width:100px;">
									<input name="endDate2" value="${requestScope.endDate2}" 
											type="text" class="field border" style="width:120px" onclick="WdatePicker()" readonly="readonly"/>
			  					</td>
			  					<s:if test="#session.fun306 == 1">
									<td colspan="6" style="text-align:right;padding-right:40px;">
										<input type="submit" value="批量锁定" class="btn_img" style="margin-left:90px;"
												onclick="confirmLock(1);"/>
										<input type="submit" value="批量解锁" class="btn_img" style="margin-left:10px;"
												onclick="confirmLock(0);"/>
									</td>
								</s:if>
							</tr>
						</s:if>
					</table>
				
		</div>
        <div class="right_3">
        	<s:if test="#request.listFlag == 6 && #request.query.recieveFlag == 0">
        		<table id="table1" class="table" style="width:1750px;">
        	</s:if>
        	<s:else>
        		<table id="table1" class="table" style="width:1750px;">
        	</s:else>
	           	<tr>
	               	<th style="width:40px;">序号</th>
	               	<th style="width:100px;">单据日期</th>
	               	<th style="width:160px;">单据编号</th>
	               	<s:if test="#request.listFlag == 6">
	               		<th style="width:80px;">单据类型</th>
	               		<!--  
	               		<th style="width:120px;">发货人</th>
	               		-->
	               		<th style="width:240px;">C/O</th>
	               	</s:if>
	               	<s:if test="#request.listFlag != 6">
	               		<th style="width:120px;">收货人</th>
	               	</s:if>
	               	<th style="width:240px;">客户名称</th>
	               	<s:if test="#request.listFlag != 6 || (#request.listFlag == 6 && #request.query.recieveFlag == 0)">
	               		<th style="width:210px;">操作</th>
	               	</s:if>
	               	<th style="width:100px;">前程代理</th>
	               	<th style="width:100px;">后程代理</th>
	               	<s:if test="#request.listFlag == 6 && #request.query.recieveFlag == 0">
	               		<th style="width:120px;">加急备注</th>
	               	</s:if>
	               	<th style="width:100px;">业务员</th>
	               	<th style="width:90px;">状态</th>
	               	<th style="width:70px;">完结标志</th>
	               	<s:if test="#request.listFlag == 1 || #request.listFlag > 9">
	               		<s:if test="#request.listFlag > 9">
	             			<th style="width:120px;">服务单(归档)备注</th>
	             		</s:if>
	             		<s:if test="#request.listFlag == 1">
	             			<th style="width:120px;">服务单备注</th>
	             		</s:if>
	             	</s:if>
	             	<s:if test="#request.listFlag == 6 && #session.fun306 == 1">
	             		<th style="width:30px;">
	             			<input type="checkbox" id="delBtn" onclick="selectItem();"/>
	             		</th>
	             	</s:if>
	             	<s:if test="(#request.listFlag == 6 && #request.query.recieveFlag == 1)">
	               		<th style="width:200px;">操作</th>
	               	</s:if>
	                <s:if test="#request.listFlag == 6 && #request.query.recieveFlag == 1 &&  #session.fun311 == 1">
	                	<th style="width:120px;">已回款备注</th>
	                </s:if>
	                <s:if test="#request.listFlag == 6 && #request.query.recieveFlag == 1">
	                	<th style="width:60px;">新增成本标记</th>
	                	<th style="width:60px;"></th>
	               	</s:if>
	               	<th style="width:150px;"></th>
	            </tr>
	            <s:iterator value="#request.businList" var="businList" status="num">
	            <s:if test="#request.listFlag == 6">
		            <s:if test="#request.query.recieveFlag == 1 && newCostFlag==1">
		            	<tr style="color:red;">
		            </s:if>
		            <s:elseif test="addGroupFlag==1">
		            	<tr style="color:blue;">
		            </s:elseif>
		            <s:else>
		            	<tr>
		            </s:else>
		        </s:if>
	            <s:else>
	            	<tr>
	            </s:else>
					<td>
						<s:if test="orderNum==1 && listFlag == 6">
							<span style="color:red;">
						</s:if>
						<s:property value="(#num.index+1)+(pageNow-1)*pageSize" />
						<s:if test="orderNum==1">
							</span>
						</s:if>
					</td>
					<td>
						<s:if test="orderNum==1 && listFlag == 6">
							<span style="color:red;">
						</s:if>
							<s:if test="listFlag == 6">
								<s:if test="nodate == null || nodate == ''">
									<s:date name="businDate" format="dd-MM-yyyy"/>
								</s:if>
								<s:else>
									<s:date name="nodate" format="dd-MM-yyyy"/>
								</s:else>
							</s:if>
							<s:else>
								<s:date name="businDate" format="dd-MM-yyyy"/>
							</s:else>
						<s:if test="orderNum==1">
							</span>
						</s:if>
					</td>
					<td>
						<s:if test="orderNum==1 && listFlag == 6">
							<span style="color:red;">
						</s:if>
						<s:property value="billNo" />
						<s:if test="orderNum==1">
							</span>
						</s:if>
					</td>
					<s:if test="listFlag == 6">
						<td>
							<s:if test="orderNum==1">
								<span style="color:red;">
							</s:if>
								<s:property value="typeName" />
							<s:if test="orderNum==1">
								</span>
							</s:if>
						</td>
						<td>
							<s:if test="orderNum==1">
								<span style="color:red;">
							</s:if>
								<s:property value="coname" />
							<s:if test="orderNum==1">
								</span>
							</s:if>
						</td>
						<!--  
						<td>
							<s:if test="orderNum==1">
								<span style="color:red;">
							</s:if>
								<s:property value="send" />
							<s:if test="orderNum==1">
								</span>
							</s:if>
						</td>
						-->
					</s:if>
					<s:if test="listFlag != 6">
						<td>
							<s:if test="orderNum==1 && listFlag == 6">
								<span style="color:red;">
							</s:if>
							<s:property value="receive" />
							<s:if test="orderNum==1">
								</span>
							</s:if>
						</td>
					</s:if>
					<td>
						<s:if test="orderNum==1 && listFlag == 6">
							<span style="color:red;">
						</s:if>
						<s:property value="cusName" />
						<s:if test="orderNum==1">
							</span>
						</s:if>
					</td>
					<s:if test="#request.listFlag != 6 || (#request.listFlag == 6 && #request.query.recieveFlag == 0)">
						<td>
							<s:if test="((listFlag == 1||listFlag == 10) && #session.uppId == deptId)">
								<s:if test="completeFlag==0">
									<s:if test="businState == 1">
										<a href="javascript:void(0);"
											onclick="edit(<s:property value="businId"/>);">修改</a>
									</s:if>
									<s:if test="businState == 2">
										<a href="javascript:void(0);"
											onclick="editArchiving(<s:property value="businId"/>);">修改</a>
									</s:if>
									<s:if test="businState == 1">
										<s:if test="#session.fun212 == 1">
											<a href="javascript:void(0);"
												onclick="conClose(<s:property value="businId"/>, 2);">归档(未)</a>
										</s:if>
										<s:if test="#session.fun221 == 1">
											<a href="javascript:void(0);"
												onclick="conClose(<s:property value="businId"/>, 3);">归档(已)</a>
										</s:if>
									</s:if>
									<s:if test="businState == 2">
										<s:if test="#session.fun221 == 1">
											<a href="javascript:void(0);"
												onclick="conArchivingClose(<s:property value="businId"/>, 3);">归档(已)</a>
										</s:if>
									</s:if>
									<s:if test="((businState == 2 && #session.fun212 == 1) || (businState == 3 && #session.fun221))">
										<a href="javascript:void(0);"
												onclick="conOpen(<s:property value="businId"/>, 3);">解除归档</a>
									</s:if>
								</s:if>
								<s:if test="completeFlag==0">
									<s:if test="#session.fun220 == 1">
										<s:if test="recieveFlag == 0">
											<a href="javascript:void(0);"
												onclick="complete3(1,<s:property value="businId"/>);">完结</a>
										</s:if>
										<s:if test="recieveFlag == 1">
											<a href="javascript:void(0);"
												onclick="complete2(1,<s:property value="businId"/>);">完结</a>
										</s:if>
									</s:if>
								</s:if>
								<s:if test="completeFlag==1">
									<s:if test="#session.fun220 == 1">
										<s:if test="recieveFlag == 0">
											<a href="javascript:void(0);"
												onclick="complete3(0,<s:property value="businId"/>);">解除完结</a>
										</s:if>
										<s:if test="recieveFlag == 1">
											<a href="javascript:void(0);"
												onclick="complete2(0,<s:property value="businId"/>);">解除完结</a>
										</s:if>
									</s:if>
								</s:if>
							</s:if>
							
							<s:if test="(listFlag == 6)">
					
								<s:if test="completeFlag==0">
									<s:if test="#session.fun305 == 1">
										<a href="javascript:void(0);"
											onclick="editCashBusin(<s:property value="businId"/>);">开票</a>
									</s:if>
								</s:if>
								
								<a href="javascript:void(0);" onclick="view(<s:property value="businId"/>);">查看</a>
								
								<!-- 
								<s:if test="listFlag == 6 && businState == 1 && completeFlag==0">
									<s:if test="orderNum==0">
										<s:if test="recieveFlag == 0">
											<a href="javascript:void(0);"
													onclick="setOrder(1,<s:property value="businId"/>);">加急</a>
										</s:if>
										<s:if test="recieveFlag == 1">
											<a href="javascript:void(0);"
													onclick="setOrder2(1,<s:property value="businId"/>);">加急</a>
										</s:if>
									</s:if>
									<s:else>
										<s:if test="recieveFlag == 0">
											<a href="javascript:void(0);"
													onclick="setOrder(0,<s:property value="businId"/>);">取消加急</a>
										</s:if>
										<s:if test="recieveFlag == 1">
											<a href="javascript:void(0);"
													onclick="setOrder2(0,<s:property value="businId"/>);">取消加急</a>
										</s:if>
									</s:else>
								</s:if>
								-->
							
								<s:if test="completeFlag==0">
									
									<s:if test="#session.fun308 == 1 && recieveFlag == 0 && #session.fun310 == 1">
										<a href="javascript:void(0);"
											onclick="updateRecieveFlag(<s:property value="businId"/>);">
											已开票
										</a>
									</s:if>
									<!--  
									<s:if test="#session.fun308 == 1 && recieveFlag == 1 && #session.fun310 == 1">
										<a href="javascript:void(0);"
											onclick="updateRecieveFlag2(<s:property value="businId"/>);">
											取消回款
										</a>
									</s:if>
									-->
								</s:if>
								
								<s:if test="completeFlag==0">
									<s:if test="#session.fun220 == 1">
										<s:if test="recieveFlag == 0">
											<a href="javascript:void(0);"
												onclick="complete3(1,<s:property value="businId"/>);">完结</a>
										</s:if>
										<s:if test="recieveFlag == 1">
											<a href="javascript:void(0);"
												onclick="complete2(1,<s:property value="businId"/>);">完结</a>
										</s:if>
									</s:if>
								</s:if>
								<s:if test="completeFlag==1">
									<s:if test="#session.fun220 == 1">
										<s:if test="recieveFlag == 0">
											<a href="javascript:void(0);"
												onclick="complete3(0,<s:property value="businId"/>);">解除完结</a>
										</s:if>
										<s:if test="recieveFlag == 1">
											<a href="javascript:void(0);"
												onclick="complete2(0,<s:property value="businId"/>);">解除完结</a>
										</s:if>
									</s:if>
								</s:if>
							</s:if>
							
							<s:if test="listFlag != 6">
								<a href="javascript:void(0);" onclick="view(<s:property value="businId"/>);">查看</a>
							</s:if>
							
							<s:if test="listFlag == 6">
								<s:if test="#session.fun306 == 1 && #request.lockFlag == 0">
									<s:if test="recieveFlag == 0">
										<a href="javascript:void(0);"
												onclick="businLock3(<s:property value="businId"/>);">锁定</a>
									</s:if>
									<s:if test="recieveFlag == 1">
										<a href="javascript:void(0);"
												onclick="businLock2(<s:property value="businId"/>);">锁定</a>
									</s:if>
								</s:if>
								<s:if test="#session.fun306 == 1 && #request.lockFlag == 1">
									<s:if test="recieveFlag == 0">
										<a href="javascript:void(0);"
												onclick="businUnlock3(<s:property value="businId"/>);">解除锁定</a>
									</s:if>
									<s:if test="recieveFlag == 1">
										<a href="javascript:void(0);"
												onclick="businUnlock2(<s:property value="businId"/>);">解除锁定</a>
									</s:if>
								</s:if>
							</s:if>
						</td>
					</s:if>
					
					<td>
						<s:if test="orderNum==1 && listFlag == 6">
							<span style="color:red;">
						</s:if>
						<s:property value="agent1" />
						<s:if test="orderNum==1">
							</span>
						</s:if>
					</td>
					
					<td>
						<s:if test="orderNum==1 && listFlag == 6">
							<span style="color:red;">
						</s:if>
						<s:property value="agent2" />
						<s:if test="orderNum==1">
							</span>
						</s:if>
					</td>
					
					<s:if test="#request.listFlag == 6 && #request.query.recieveFlag == 0">
						<td>
							<input id="kaipiaoRemarks<s:property value="#num.index" />" type="text" value="${kaipiaoRemarks}"
									class="border field" style="width:106px;" 
									onchange="updateKaipiaoRemarks(<s:property value="#num.index" />,<s:property value="businId" />);"/>
						</td>
					</s:if>
					
					<td>
						<s:if test="orderNum==1 && listFlag == 6">
							<span style="color:red;">
						</s:if>
						<s:property value="saleman" />
						<s:if test="orderNum==1">
							</span>
						</s:if>
					</td>
					<td>
						<s:if test="lockFlag == 1 && listFlag == 6">
							<s:if test="orderNum==1">
								<span style="color:red;">
							</s:if>
								锁定
							<s:if test="orderNum==1">
								</span>
							</s:if>
						</s:if>
						<s:else>
							<s:if test="listFlag == 1 || (listFlag == 6 && recieveFlag == 0)">
								<s:if test="businStateName=='已开票'">
									<span style="color:red;">
										<s:property value="businStateName" />
									</span>
								</s:if>
								<s:else>
									<s:property value="businStateName" />
								</s:else>
							</s:if>
							<s:else>
								<s:if test="businState == 1">
									<s:if test="orderNum==1 && listFlag == 6">
										<span style="color:red;">
									</s:if>
										编制
									<s:if test="orderNum==1">
										</span>
									</s:if>
								</s:if>
								<s:if test="businState == 2">
									<span style="color:red;">归档(未)</span>
								</s:if>
								<s:if test="businState == 3">
									<span style="color:green;">归档(已)</span>
								</s:if>
							</s:else>
						</s:else>
					</td>
					<td>
						<s:if test="completeFlag == 0">
							未完结
						</s:if>
						<s:if test="completeFlag == 1">
							<span style="color:red;">已完结</span>
						</s:if>
					</td>
					<s:if test="#request.listFlag > 9">
						<td>
							<s:if test="#session.fun607 == 1">
								<input id="archiveRemarks<s:property value="#num.index" />" type="text" value="${archiveRemarks}"
									class="border field" style="width:106px;" 
									onchange="updateRemarks(<s:property value="#num.index" />,<s:property value="businId" />);"/>
							</s:if>
							<s:else>
								<s:if test="orderNum==1">
									<span style="color:red;">
								</s:if>
									<s:property value="archiveRemarks" />
								<s:if test="orderNum==1">
									</span>
								</s:if>
							</s:else>
						</td>
					</s:if>
					<s:if test="#request.listFlag == 1">
						<td>
							<s:if test="#session.fun217 == 1">
								<input id="fileRemarks<s:property value="#num.index" />" type="text" value="${fileRemarks}"
									class="border field" style="width:106px;" 
									onchange="updateFileRemarks(<s:property value="#num.index" />,<s:property value="businId" />);"/>
							</s:if>
							<s:else>
								<s:if test="orderNum==1">
									<span style="color:red;">
								</s:if>
									<s:property value="fileRemarks" />
								<s:if test="orderNum==1">
									</span>
								</s:if>
							</s:else>
						</td>
					</s:if>
					<s:if test="#request.listFlag == 6 && #session.fun306 == 1">
						<td>
	             			<input type="checkbox" name="businIds" value="<s:property value="businId"/>" />
	             		</td>
	             	</s:if>
	             	
	             	<s:if test="(#request.listFlag == 6 && recieveFlag == 1)">
						<td>
							<s:if test="((listFlag == 1||listFlag == 10) && #session.uppId == deptId)">
								<s:if test="completeFlag==0">
									<s:if test="businState == 1">
										<a href="javascript:void(0);"
											onclick="edit(<s:property value="businId"/>);">修改</a>
									</s:if>
									<s:if test="businState == 2">
										<a href="javascript:void(0);"
											onclick="editArchiving(<s:property value="businId"/>);">修改</a>
									</s:if>
									<s:if test="businState == 1">
										<s:if test="#session.fun212 == 1">
											<a href="javascript:void(0);"
												onclick="conClose(<s:property value="businId"/>, 2);">归档(未)</a>
										</s:if>
										<s:if test="#session.fun221 == 1">
											<a href="javascript:void(0);"
												onclick="conClose(<s:property value="businId"/>, 3);">归档(已)</a>
										</s:if>
									</s:if>
									<s:if test="businState == 2">
										<s:if test="#session.fun221 == 1">
											<a href="javascript:void(0);"
												onclick="conArchivingClose(<s:property value="businId"/>, 3);">归档(已)</a>
										</s:if>
									</s:if>
									<s:if test="((businState == 2 && #session.fun212 == 1) || (businState == 3 && #session.fun221))">
										<a href="javascript:void(0);"
												onclick="conOpen(<s:property value="businId"/>, 3);">解除归档</a>
									</s:if>
								</s:if>
								<s:if test="completeFlag==0">
									<s:if test="#session.fun220 == 1">
										<s:if test="recieveFlag == 0">
											<a href="javascript:void(0);"
												onclick="complete3(1,<s:property value="businId"/>);">完结</a>
										</s:if>
										<s:if test="recieveFlag == 1">
											<a href="javascript:void(0);"
												onclick="complete2(1,<s:property value="businId"/>);">完结</a>
										</s:if>
									</s:if>
								</s:if>
								<s:if test="completeFlag==1">
									<s:if test="#session.fun220 == 1">
										<s:if test="recieveFlag == 0">
											<a href="javascript:void(0);"
												onclick="complete3(0,<s:property value="businId"/>);">解除完结</a>
										</s:if>
										<s:if test="recieveFlag == 1">
											<a href="javascript:void(0);"
												onclick="complete2(0,<s:property value="businId"/>);">解除完结</a>
										</s:if>
									</s:if>
								</s:if>
							</s:if>
							
							<s:if test="(listFlag == 6)">
					
								<s:if test="completeFlag==0">
									<s:if test="#session.fun305 == 1">
										<a href="javascript:void(0);"
											onclick="editCashBusin(<s:property value="businId"/>);">开票</a>
									</s:if>
								</s:if>
								
								<a href="javascript:void(0);" onclick="view(<s:property value="businId"/>);">查看</a>
								
								<!-- 
								<s:if test="listFlag == 6 && businState == 1 && completeFlag==0">
									<s:if test="orderNum==0">
										<s:if test="recieveFlag == 0">
											<a href="javascript:void(0);"
													onclick="setOrder(1,<s:property value="businId"/>);">加急</a>
										</s:if>
										<s:if test="recieveFlag == 1">
											<a href="javascript:void(0);"
													onclick="setOrder2(1,<s:property value="businId"/>);">加急</a>
										</s:if>
									</s:if>
									<s:else>
										<s:if test="recieveFlag == 0">
											<a href="javascript:void(0);"
													onclick="setOrder(0,<s:property value="businId"/>);">取消加急</a>
										</s:if>
										<s:if test="recieveFlag == 1">
											<a href="javascript:void(0);"
													onclick="setOrder2(0,<s:property value="businId"/>);">取消加急</a>
										</s:if>
									</s:else>
								</s:if>
								-->
							
								<s:if test="completeFlag==0">
									
									<s:if test="#session.fun308 == 1 && recieveFlag == 0 && #session.fun310 == 1">
										<a href="javascript:void(0);"
											onclick="updateRecieveFlag(<s:property value="businId"/>);">
											已开票
										</a>
									</s:if>
									<!--  
									<s:if test="#session.fun308 == 1 && recieveFlag == 1 && #session.fun310 == 1">
										<a href="javascript:void(0);"
											onclick="updateRecieveFlag2(<s:property value="businId"/>);">
											取消回款
										</a>
									</s:if>
									-->
								</s:if>
								
								<s:if test="completeFlag==0">
									<s:if test="#session.fun220 == 1">
										<s:if test="recieveFlag == 0">
											<a href="javascript:void(0);"
												onclick="complete3(1,<s:property value="businId"/>);">完结</a>
										</s:if>
										<s:if test="recieveFlag == 1">
											<a href="javascript:void(0);"
												onclick="complete2(1,<s:property value="businId"/>);">完结</a>
										</s:if>
									</s:if>
								</s:if>
								<s:if test="completeFlag==1">
									<s:if test="#session.fun220 == 1">
										<s:if test="recieveFlag == 0">
											<a href="javascript:void(0);"
												onclick="complete3(0,<s:property value="businId"/>);">解除完结</a>
										</s:if>
										<s:if test="recieveFlag == 1">
											<a href="javascript:void(0);"
												onclick="complete2(0,<s:property value="businId"/>);">解除完结</a>
										</s:if>
									</s:if>
								</s:if>
							</s:if>
							
							<s:if test="listFlag != 6">
								<a href="javascript:void(0);" onclick="view(<s:property value="businId"/>);">查看</a>
							</s:if>
							
							<s:if test="listFlag == 6">
								<s:if test="#session.fun306 == 1 && #request.lockFlag == 0">
									<s:if test="recieveFlag == 0">
										<a href="javascript:void(0);"
												onclick="businLock3(<s:property value="businId"/>);">锁定</a>
									</s:if>
									<s:if test="recieveFlag == 1">
										<a href="javascript:void(0);"
												onclick="businLock2(<s:property value="businId"/>);">锁定</a>
									</s:if>
								</s:if>
								<s:if test="#session.fun306 == 1 && #request.lockFlag == 1">
									<s:if test="recieveFlag == 0">
										<a href="javascript:void(0);"
												onclick="businUnlock3(<s:property value="businId"/>);">解除锁定</a>
									</s:if>
									<s:if test="recieveFlag == 1">
										<a href="javascript:void(0);"
												onclick="businUnlock2(<s:property value="businId"/>);">解除锁定</a>
									</s:if>
								</s:if>
							</s:if>
						</td>
					</s:if>
	             	
					<s:if test="#request.listFlag == 6 && #request.query.recieveFlag == 1 &&  #session.fun311 == 1">
						<td>
							<input id="recieveRemarks<s:property value="#num.index" />" type="text" value="${recieveRemarks}"
									class="border field" style="width:106px;" 
									onchange="updateRecieveRemarks(<s:property value="#num.index" />,<s:property value="businId" />);"/>
						</td>
					</s:if>
					<s:if test="#request.listFlag == 6 && #request.query.recieveFlag == 1">
						<td>
		                	<s:if test="newCostFlag==1">
	            				新增
	            			</s:if>
	            		</td>
	            		<td>
		                	<s:if test="newCostFlag==1 && #session.fun225 == 1">
	            				<a href="javascript:void(0);" 
									onclick="setNewCostFlag(<s:property value="businId"/>);">取消提醒</a>
	            			</s:if>
	            		</td>
	               	</s:if>
	               	<td>
	                	<s:if test="costDupFlag==1 && #session.fun225 == 1">
	                		<s:if test="#request.listFlag == 1">
	            				<a href="javascript:void(0);" 
									onclick="setCostDupFlag(<s:property value="businId"/>, 1);">取消提醒(成本重复)</a>
							</s:if>
							<s:if test="#request.listFlag == 10">
	            				<a href="javascript:void(0);" 
									onclick="setCostDupFlag(<s:property value="businId"/>, 2);">取消提醒(成本重复)</a>
							</s:if>
							<s:if test="#request.listFlag == 6">
								<s:if test="recieveFlag == 0">
									<a href="javascript:void(0);" 
										onclick="setCostDupFlag(<s:property value="businId"/>, 3);">取消提醒(成本重复)</a>
								</s:if>
	            				<s:else>
	            					<a href="javascript:void(0);" 
										onclick="setCostDupFlag(<s:property value="businId"/>, 4);">取消提醒(成本重复)</a>
	            				</s:else>
							</s:if>
            			</s:if>
            		</td>
				</tr>
	            </s:iterator>
	       </table>
	    </div>
	    </form>
	    </div>
       <div class="right_4">
           	<div style="float:left; margin-left:5px;">
           		当前第${pageBean.pageNow}页/共${pageBean.pageCount}页
           	</div>
           	<div style="float:left; margin-left:600px;">
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
  </body>
</html>

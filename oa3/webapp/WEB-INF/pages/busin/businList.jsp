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
	<style type="text/css">
		.s1 {background-color:lightyellow;}
	</style>
	<script type="text/javascript">
		//初始化
		$(document).ready(function(){
		
			//单据编号
			/*
			var code = "${requestScope.businCode}";
			var code2 = "${requestScope.businCode2}";
			if (code != null && code != ""){
				document.getElementById("businCode").value = code;
			}else{
				if (code2 != null && code2 != ""){
					document.getElementById("businCode").value = code2;
				}
			}*/
			
			//单据类型
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
			
			//客户名称
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
			
			//是否开票
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
			
			//是否完结
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
			
			$("#table1 tbody tr").click(function() {
				$(this).addClass("s1").siblings().removeClass("s1");
			});
			
			/*
			$("#table1 tbody tr").dblclick(function (){
				//document.getElementById("cid").value = businId;
	            document.getElementById("queryForm").action = "${ctx}/busin/BusinDetialList.action";
	            document.getElementById("queryForm").submit();
	        });
	        */
			
			//后台校验错误信息
			var errInfo = "${requestScope.errInfo}";
			if (errInfo != null && errInfo.length > 0){
				alert(errInfo);
			}
			
			$('#cusid').comboSelect();
			
		});
		
		function queryDetail(businId){
			document.getElementById("_businId").value = businId;
	        document.getElementById("queryForm").action = "${ctx}/busin/BusinDetialList.action";
	        document.getElementById("queryForm").submit();
		}
		
		//分页控件
		function forward(flag){
			document.getElementById("queryForm").action = "${ctx}/busin/BusinList.action";
			/*
			if ($("#queryTable").attr("class") == "hidden"){
				document.getElementById("businCode2").value = "";
			}else{
				document.getElementById("businCode").value = "";
			}
			*/
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
		
		//显示隐藏查询条件
		function showQueryTable(){
			if ($("#queryTable").attr("class") == "hidden"){
				$("#billNo2_div").attr("class", "hidden");
				$("#queryTable").attr("class", "");
				document.getElementById("queryFlag").innerHTML = "△";
				document.getElementById("queryText").innerHTML = "隐藏筛选条件";
				var code = document.getElementById("businCode").value;
				if (code != null && code != ""){
					document.getElementById("businCode2").value = code;
				}
			}else{
				$("#billNo2_div").attr("class", "");
				$("#queryTable").attr("class", "hidden");
				document.getElementById("queryFlag").innerHTML = "▽";
				document.getElementById("queryText").innerHTML = "显示筛选条件";
				var code = document.getElementById("businCode2").value;
				if (code != null && code != ""){
					document.getElementById("businCode").value = code;
				}
			}
		}
		
		//导出
		function exp(){
			document.getElementById("queryForm").action = "${ctx}/busin/ExpBusinList.action";
			document.getElementById("queryForm").submit();
		}
		
		//服务单备注
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
		//服务单归档备注
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
		//已回款备注
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
		
		//添加
		function add(){
			document.getElementById("queryForm").action = "${ctx}/busin/AddBusin.action";
			document.getElementById("queryForm").submit();
		}
		
		function pickup(businId){
			if(confirm("确定要将提货状态改为【已提货】吗？")){
				var url = "${ctx}/busin/Pickup.action";
				$.ajax({
					url:url,
					data:{'businId':businId},
					success : function (code){
						if(code == 0){
							alert("操作成功！")
							document.getElementById("queryForm").submit();
						}else{
							alert("操作失败！")
						}
					}
				})
			}
		}
		
		//添加
		function copy(businId){
			$("#cid").val(businId)
			document.getElementById("queryForm").action = "${ctx}/busin/CopyBusin.action";
			document.getElementById("queryForm").submit();
		}
	</script>
  </head>
  <body>
  	<form id="queryForm" action="${ctx}/busin/BusinList.action" method="post">
	  	<div class="right" style="margin-left:10px;">
	  
	  		<div style="width:1210px;text-align:left;margin-top:10px;">
	  			<!--  
	  			<div style="width:110px;text-align:left;margin-left:10px;cursor:pointer;float:left;padding-top:2px;"
	  				 onclick="showQueryTable();">
		  			<label id="queryText" style="color:blue;cursor:pointer;font-weight:bold;">显示筛选条件</label>
					<label id="queryFlag" style="color:blue;cursor:pointer;font-weight:bold;">▽</label>
		  		</div>
		  		<div id="billNo2_div" style="width:195px;text-align:left;cursor:pointer;float:left;">
			  		<label>单据编号</label>
					<input id="businCode" name="businCode" value="${requestScope.businCode}"
								type="text" class="field border" style="width:130px;" />
		  		</div>
		  		-->
	  			<input type="submit" value="查询" class="btn_img" style="margin-left:10px;" onclick="return forward(-1);"/>
				<s:if test="listFlag == 1">
       				<input type="button" value="添加" class="btn_img" style="margin-left:5px;" onclick="add();"/>
       			</s:if>
				<input type="button" value="导出" class="btn_img" style="margin-left:5px;" onclick="exp();"/>
				<input type="hidden" id="pageNow" name="pageNow" value="${pageNow}"/>
				<input type="hidden" id="listFlag" name="listFlag" value="${listFlag}"/>
				<input type="hidden" id="cid" name="businId"/>
				<input type="hidden" id="_businId" name="_businId"/>
				<input type="hidden" id="archiveFlag" name="archiveFlag" value="${requestScope.archiveFlag}"/>
				<input type="hidden" id="_lock" name="_lock"/>
	  		</div>
			<table id="queryTable" style="width:1210px;margin-top:10px;">
				<tr>
					<td style="width:80px;">单据编号</td>
					<td style="width:130px;text-align:left; ">
						<input id="businCode2" name="businCode2" value="${requestScope.businCode2}" 
								type="text" class="field border" style="width:130px;" />
  					</td>
  					<td style="width:60px;">单据类型</td>
					<td style="width:130px;text-align:left; ">
						<select id="TBusinType" name="query.typeId" class="border" style="width:132px;height:22px;">
			           		<option value="0">PLEASE CHOOSE</option>
							<c:forEach var="businTypeList" items="${requestScope.businTypeList}">
								<option value="${businTypeList.CId}">${businTypeList.CName}</option>
							</c:forEach>
						</select>
  					</td>
  					<td style="width:60px;">客户名称</td>
					<td style="width:200px;text-align:left;">
						<select id="cusid" name="query.cusId" class="border" style="width:200px;border:1px solid lightblue;">
							<option value="-1">--全部--</option>
							<c:forEach var="cusList" items="${requestScope.cusList}">
								<option value="${cusList.CId}">${cusList.CAddr}</option>
							</c:forEach>
						</select>
  					</td>
  					<td style="width:60px;">货柜号码</td>
					<td style="width:120px;text-align:left; ">
						<input id="conNum" name="conNum" value="${requestScope.conNum}" 
								type="text" class="field border" style="width:120px;" />
  					</td>
  					<td style="width:60px;">BILL NO</td>
					<td style="width:120px;text-align:left; ">
						<input name="billNo" value="${requestScope.billNo}" 
								type="text" class="field border" style="width:120px;" />
  					</td>
  					<td style="width:60px;">业务员</td>
  					<td style="width:120px;text-align:left; ">
						<input id="empName" name="query.empName" value="${requestScope.query.empName}" 
							type="text" class="field border" style="width:120px;" />
					</td>
				</tr>
				<tr>
					<s:if test="#request.listFlag != 6">
						<td>单据日期从</td>
						<td style="text-align:left;">
							<input id="startDate" name="startDate" value="${requestScope.startDate}"
									type="text" class="field border" 
									style="width:130px" onclick="WdatePicker()" readonly="readonly"/>
						</td>
						<td>至</td>
						<td style="text-align:left;">
							<input id="endDate" name="endDate" value="${requestScope.endDate}"
									type="text" class="field border" 
									style="width:130px" onclick="WdatePicker()" readonly="readonly"/>
						</td>
					</s:if>
					<s:else>
						<td>开票日期从</td>
						<td style="text-align:left;">
							<input id="startDate" name="startDate" value="${requestScope.startDate}"
									type="text" class="field border" 
									style="width:130px" onclick="WdatePicker()" readonly="readonly"/>
						</td>
						<td>至</td>
						<td style="text-align:left;">
							<input id="endDate" name="endDate" value="${requestScope.endDate}"
									type="text" class="field border" 
									style="width:130px" onclick="WdatePicker()" readonly="readonly"/>
						</td>
					</s:else>
					<td>归档状态</td>
					<td style="text-align:left; ">
						<select id="businState" name="query.businState" class="border" style="width:200px;height:22px;">
							<option value="-1">全部</option>
							<option value="1">未归档</option>
							<s:if test="(#request.listFlag==1 && #session.fun206==1) ||
										(#request.listFlag==6 && #session.fun308==1)">
								<option value="2">归档(未)</option>
								<option value="3">归档(已)</option>
							</s:if>
						</select>
					</td>
					<td>是否开票</td>
					<td style="text-align:left; ">
						<select id="businStateName" name="query.businStateName" class="border" style="width:122px;height:22px;">
							<option value="0">全部</option>
							<option value="2">未开票</option>
							<option value="3">已审核</option>
							<option value="1">已开票</option>
						</select>
					</td>
					<td>是否完结</td>
					<td style="text-align:left; ">
						<select id="completeFlag" name="query.completeFlag" class="border" style="width:122px;height:22px;">
							<option value="-1">全部</option>
							<option value="0">未完结</option>
							<option value="1">已完结</option>
						</select>
					</td>
					<td style="width:60px;">提货状态PU</td>
					<td style="width:200px;text-align:left;">
						<select id="pickup" name="query.pickupStatus" class="border" style="width:122px;height:22px;">
							<option value="">--全部--</option>
							<option value="0" <c:if test="${requestScope.query.pickupStatus == '0'}">selected</c:if>>未提货NPU</option>
							<option value="1" <c:if test="${requestScope.query.pickupStatus == '1'}">selected</c:if>>已提货APU</option>
						</select>
  					</td>
				</tr>
				<s:if test="#request.listFlag == 6">
					<tr>
						<td>CLEARANCE DATE</td>
						<td style="text-align:left;">
							<input id="startDate2" name="startDate2" value="${requestScope.startDate2}"
									type="text" class="field border" 
									style="width:130px" onclick="WdatePicker()" readonly="readonly"/>
						</td>
						<td>至</td>
						<td style="text-align:left;">
							<input id="endDate2" name="endDate2" value="${requestScope.endDate2}"
									type="text" class="field border" 
									style="width:130px" onclick="WdatePicker()" readonly="readonly"/>
						</td>
					</tr>
				</s:if>
			</table>
		</div>
        <div class="right_3">
        	<table id="table1" class="table" style="margin-left:20px;">
	           	<tr>
	           		<s:if test="#request.listFlag == 1">
	           			<th style="width:40px;"></th>
	           		</s:if>
	               	<th style="width:40px;">序号</th>
	               	<th style="width:85px;">单据日期</th>
	               	<th style="width:130px;">单据编号</th>
	               	<s:if test="#request.listFlag == 6">
	               		<th style="width:60px;">单据类型</th>
	               		<th style="width:240px;">C/O</th>
	               	</s:if>
	               	<s:if test="#request.listFlag != 6">
	               		<th style="width:220px;">收货人</th>
	               	</s:if>
	               	<th style="width:240px;">客户名称</th>
	               	<th style="width:80px;">前程代理</th>
	               	<th style="width:80px;">后程代理</th>
	               	<th style="width:80px;">业务员</th>
	               	<th style="width:65px;">服务单状态</th>
	               	<th style="width:55px;">开票状态</th>
	               	<th style="width:55px;">审核状态</th>
	               	<th style="width:55px;">完结标志</th>
	               	<th style="width:55px;">提货状态PU</th>
	               	<s:if test="#request.listFlag == 1 && #session.fun201 == 1">
             			<th style="width:115px;">服务单备注</th>
             		</s:if>
             		<s:if test="#request.listFlag == 1 && #session.fun206 == 1">
             			<th style="width:115px;">服务单(归档)备注</th>
             		</s:if>
	                <s:if test="#request.listFlag == 6 && #session.fun308 == 1 &&  #session.fun311 == 1">
	                	<th style="width:115px;">已回款备注</th>
	                </s:if>
	                <s:if test="#request.listFlag == 6 && #session.fun308 == 1">
	                	<th style="width:60px;"></th>
	               	</s:if>
	            </tr>
	            <s:iterator value="#request.businList" var="businList" status="num">
		            <s:if test="#request.listFlag == 6">
			            <s:if test="#session.fun308 == 1 && newCostFlag==1">
			            	<tr ondblclick="queryDetail(<s:property value="businId" />)">
			            </s:if>
			            <s:elseif test="addGroupFlag==1">
			            	<tr style="color:blue;" ondblclick="queryDetail(<s:property value="businId" />)">
			            </s:elseif>
			            <s:else>
			            	<tr ondblclick="queryDetail(<s:property value="businId" />)">
			            </s:else>
			        </s:if>
		            <s:else>
		            	<tr ondblclick="queryDetail(<s:property value="businId" />)">
		            </s:else>
		            	<s:if test="#request.listFlag == 1">
	               			<td>
			            		<a href="javascript:void(0);"  onclick="copy(<s:property value="businId"/>);">复制</a>
			            	</td>
	               		</s:if>
						<td>
							<s:property value="(#num.index+1)+(pageNow-1)*pageSize" />
						</td>
						<td>
							<s:if test="#request.query.businStateName==2">
								<s:date name="businDate" format="dd-MM-yyyy"/>
							</s:if>
							<s:elseif test="#request.query.businStateName==1 || #request.query.businStateName==3">
								<s:date name="nodate" format="dd-MM-yyyy"/>
							</s:elseif>
							<s:else>
								<s:if test="businStateName=='已开票' || recieveFlag==1">
									<s:date name="nodate" format="dd-MM-yyyy"/>
								</s:if>
								<s:else>
									<s:date name="businDate" format="dd-MM-yyyy"/>
								</s:else>
							</s:else>
							<!--  
							<s:if test="listFlag == 6">
								<s:if test="#request.query.businStateName=='2'">
									<s:date name="businDate" format="dd-MM-yyyy"/>
								</s:if>
								<s:else>
									<s:date name="nodate" format="dd-MM-yyyy"/>
								</s:else>
							</s:if>
							<s:else>
								<s:date name="businDate" format="dd-MM-yyyy"/>
							</s:else>
							-->
						</td>
						<td>
							<s:property value="billNo" />
						</td>
						<s:if test="listFlag == 6">
							<td>
								<s:property value="typeName" />
							</td>
							<td>
								<s:property value="coname" />
							</td>
						</s:if>
						<s:if test="listFlag != 6">
							<td>
								<s:property value="receive" />
							</td>
						</s:if>
						<td>
							<s:property value="cusName" />
						</td>
						<td>
							<s:property value="agent1" />
						</td>
						<td>
							<s:property value="agent2" />
						</td>
						<td>
							<s:property value="saleman" />
						</td>
						<td>
							<s:if test="businState == 1">
								编制
							</s:if>
							<s:if test="businState == 2">
								<span style="color:red;">归档(未)</span>
							</s:if>
							<s:if test="businState == 3">
								<span style="color:green;">归档(已)</span>
							</s:if>
						</td>
						<td>
							<s:if test="businStateName=='已开票' ">
								<span style="color:red;"><s:property value="businStateName" /></span>
								
							</s:if>
							<s:else>
								<s:property value="businStateName" />
							</s:else>
						</td>
						<td>
							<s:if test="recieveFlag==1">
								<span style="color:red;">
									已审核
								</span>
							</s:if>
							<s:else>
								未审核
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
						<td><s:property value="pickupStatus" /></td>
						<s:if test="#request.listFlag == 1 && #session.fun201 == 1">
							<td>
								<s:if test="#session.fun217 == 1">
									<input id="fileRemarks<s:property value="#num.index" />" type="text" value="${fileRemarks}"
										class="border field" style="width:106px;" 
										onchange="updateFileRemarks(<s:property value="#num.index" />,<s:property value="businId" />);"/>
								</s:if>
								<s:else>
									<s:property value="fileRemarks" />
								</s:else>
							</td>
						</s:if>
						<s:if test="#request.listFlag == 1 && #session.fun206 == 1">
							<td>
								<s:if test="#session.fun607 == 1">
									<input id="archiveRemarks<s:property value="#num.index" />" type="text" value="${archiveRemarks}"
										class="border field" style="width:106px;" 
										onchange="updateRemarks(<s:property value="#num.index" />,<s:property value="businId" />);"/>
								</s:if>
								<s:else>
									<s:property value="archiveRemarks" />
								</s:else>
							</td>
						</s:if>
						<s:if test="#request.listFlag == 6 && #session.fun308 == 1 &&  #session.fun311 == 1">
							<td>
								<input id="recieveRemarks<s:property value="#num.index" />" type="text" value="${recieveRemarks}"
										class="border field" style="width:106px;" 
										onchange="updateRecieveRemarks(<s:property value="#num.index" />,<s:property value="businId" />);"/>
							</td>
						</s:if>
						<s:if test="#request.listFlag == 6 && #session.fun308 == 1">
		            		<td>
			                	<s:if test="pickupStatus == '未提货NPU'">
		            				<a href="javascript:void(0);"  onclick="pickup(<s:property value="businId"/>);">已提货APU</a>
		            			</s:if>
		            		</td>
		               	</s:if>
					</tr>
	            </s:iterator>
	       </table>
	    </div>
	   </form>
       <div class="right_4">
           	<div style="float:left; margin-left:20px;">
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

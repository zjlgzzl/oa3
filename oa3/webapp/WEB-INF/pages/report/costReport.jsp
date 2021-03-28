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
	<link type="text/css" rel="stylesheet"  href="${ctx}/res/css/combo.select.css"/>
	<script type="text/javascript" src="${ctx}/res/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/res/js/jquery.combo.select.js"></script>
	<script type="text/javascript">
		//初始化
		$(document).ready(function(){
			var itemId = "${requestScope.query.id.itemId}";
			if (itemId != null && itemId != "" && itemId > 0){
				var select = document.getElementById("itemId");
				if (select != null){
					for(var i=0; i<select.options.length; i++){
						if(select.options[i].value == itemId){
							select.options[i].selected = true;
							break;
						}
					}
				}
			}
			var cstate = "${requestScope.query.id.cstate}";
			if (cstate != null && cstate != "" && cstate > 0){
				var select = document.getElementById("cstate");
				if (select != null){
					for(var i=0; i<select.options.length; i++){
						if(select.options[i].value == cstate){
							select.options[i].selected = true;
							break;
						}
					}
				}
			}
			var hiddenFlag = "${requestScope.query.id.hiddenFlag}";
			if (hiddenFlag != null && hiddenFlag != "" && hiddenFlag > -1){
				var select = document.getElementById("hiddenFlag");
				if (select != null){
					for(var i=0; i<select.options.length; i++){
						if(select.options[i].value == hiddenFlag){
							select.options[i].selected = true;
							break;
						}
					}
				}
			}
			$('._select').comboSelect();
		});
		//分页控件
		function forward(flag){
			document.getElementById("queryForm").action = "${ctx}/report/CostReport.action";
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
			document.getElementById("queryForm").action = "${ctx}/report/ExpCostReport.action?cflag=1";
			document.getElementById("queryForm").submit();
		}
		function updateRemarks(businId, rowIndex){
			var remarks = document.getElementById("remarks"+rowIndex).value;
			var url="${ctx}/report/UpdateCostRemarks.action?remarks="+encodeURI(remarks)+"&businId="+businId;
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
		function updateMoney(businId, rowIndex){
			var cmoney = document.getElementById("cmoney"+rowIndex).value;
			if (cmoney == null || cmoney == "" || isNaN(cmoney)){
				cmoney = 0;
			}
			var url="${ctx}/report/UpdateCostMoney.action?cmoney="+cmoney+"&businId="+businId;
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
		function setHidden(businId, hiddenFlag){
			document.getElementById("businId").value = businId;
			document.getElementById("hiddenflag").value = hiddenFlag;
			document.getElementById("queryForm").action = "${ctx}/busin/SetHidden.action";
			document.getElementById("queryForm").submit();
		}
		function setHidden2(businId){
			document.getElementById("queryForm").action = "${ctx}/busin/SetHiddenBatch.action";
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
		function setNewCostFlag(businId){
			document.getElementById("businId").value = businId;
			document.getElementById("queryForm").action = "${ctx}/busin/SetNewCostFlag.action";
			document.getElementById("queryForm").submit();
		}
		function setNewCostFlag2(){
			document.getElementById("queryForm").action = "${ctx}/busin/SetNewCostFlagBatch.action";
			document.getElementById("queryForm").submit();
		}
	</script>
  </head>
  <body>
  	<form id="queryForm" action="${ctx}/report/CostReport.action" method="post">
	  	<div class="right">
	  		<div class="right_1">
				<table style="width:1580px; margin-left:0;">
					<tr>
						<td style="width:90px;">开始日期</td>
						<td style="text-align:left; width:90px;">
							<input id="startDate1" name="startDate1" value="${requestScope.startDate1}"
									type="text" class="field border" style="width:90px" onclick="WdatePicker()" readonly="readonly"/>
	  					</td>
	  					<td style="width:90px;">结束日期</td>
						<td style="text-align:left; width:90px;">
							<input id="endDate1" name="endDate1" value="${requestScope.endDate1}" 
									type="text" class="field border" style="width:90px" onclick="WdatePicker()" readonly="readonly"/>
	  					</td>
	  					<td style="width:90px;">服务单号</td>
						<td style="width:100px;text-align:left; ">
							<input id="billNo" name="query.id.billNo" value="${requestScope.query.id.billNo}" 
									type="text" class="field border" style="width:100px;" />
	  					</td>
	  					<td style="width:90px;">客户名称</td>
						<td style="width:140px;text-align:left; ">
							<input id="cusName" name="query.id.cusName" value="${requestScope.query.id.cusName}" 
									type="text" class="field border" style="width:140px;" />
	  					</td>
	  					<td style="width:90px;">成本项目</td>
						<td style="width:140px;text-align:left; ">
							<select id="itemId" name="query.id.itemId" class="border _select" style="width:140px;border:1px solid lightblue;">
								<option value="0">--全部--</option>
								<c:forEach var="itemList" items="${requestScope.itemList}">
									<option value="${itemList.CId}">${itemList.CName}</option>
								</c:forEach>
							</select>
	  					</td>
	  					<td style="width:90px;">归档标记</td>
						<td style="width:80px;text-align:left; ">
							<select id="cstate" name="query.id.cstate" class="border" style="width:80px;">
								<option value="0">--全部--</option>
								<option value="1">未归档</option>
								<option value="2">归档(未)</option>
								<option value="3">归档(已)</option>
							</select>
	  					</td>
	  					<td style="width:90px;">隐藏标记</td>
						<td style="width:100px;text-align:left; ">
							<select id="hiddenFlag" name="query.id.hiddenFlag" class="border" style="width:100px;">
								<option value="-1">--全部--</option>
								<option value="0">未隐藏</option>
								<option value="1">隐藏</option>
							</select>
	  					</td>
	  					<td style="width:260px;text-align:left; ">
							<input type="submit" value="查询" class="btn_img" style="margin-left:10px;"
										onclick="return forward(-1);"/>
							<input type="button" value="导出" class="btn_img" style="margin-left:10px;"
										onclick="expFile();"/>
							<input type="hidden" id="pageNow" name="pageNow" value="${pageNow}"/>
							<input type="hidden" id="businId" name="businId" />
							<input type="hidden" id="hiddenflag" name="hiddenFlag" />
						</td>
						<td style="width:300px;text-align:left;">
							<s:if test="#session.fun625 == 1">
								<input type="submit" value="批量取消提醒" class="btn_img2"
										onclick="setNewCostFlag2();"/>
							</s:if>
							<s:if test="#session.fun627 == 1">
								<input type="submit" value="批量取消隐藏" class="btn_img2"
										onclick="setHidden2();"/>
							</s:if>
						</td>
					</tr>
				</table>
			</div>
	       	<div class="right_2">
	        </div>
	        <div class="right_3">
		        <table id="table1" class="table" style="width:1590px;">
		           	<tr>
		               	<th style="width:40px;">序号</th>
		               	<!--  
		               	<th style="width:80px;">单据日期</th>
		               	-->
		               	<th style="width:140px;">服务单号</th>
		               	<th style="width:180px;">客户名称</th>
		               	<th style="width:260px;">成本项目</th>
		               	<th style="width:120px;">INV2</th>
		               	<th style="width:90px;">成本金额</th>
						<th style="width:90px;">RE</th>
		               	<th style="width:90px;">出纳1</th>
		               	<th style="width:90px;">出纳2</th>
		               	<th style="width:90px;">财务</th>
		               	<th style="width:90px;">审核</th>
		               	<th style="width:90px;">备注</th>
		               	<th style="width:40px;">
		               		<input type="checkbox" id="delBtn" onclick="selectItem();"/>
		               	</th>
		               	<th style="width:130px;">操作</th>
		               	<th style="width:80px;">成本添加人</th>
		               	<th style="width:60px;">归档标记</th>
		               	<th style="width:60px;">隐藏标记</th>
		               	<th style="width:60px;">新增成本标记</th>
		            </tr>
		            <s:iterator value="#request.list" var="list" status="num">
		            	<s:if test="id.newCostFlag==1">
		            		<tr style="color:red;">
		            	</s:if>
			            <s:else>
			            	<tr>
			            </s:else>
							<td><s:property value="(#num.index+1)+(pageNow-1)*pageSize" /></td>
							<!--  
							<td>
								<s:if test="id.cnodate8 != ''">
									<s:date name="id.cdate" format="dd-MM-yyyy"/>
								</s:if>
							</td>
							-->
							<td><s:property value="id.billNo"/></td>
							<td><s:property value="id.cusName"/></td>
							<td><s:property value="id.itemName"/></td>
							<td><s:property value="id.inv2"/></td>
							<td>
								<s:if test="id.itemId != 198 && id.itemId != 199 && #session.fun623 == 1">
									<input id="cmoney<s:property value="#num.index" />"
											type="text" value="${id.cmoney}" class="border field" style="width:86px;text-align:center;"
											onchange="updateMoney(<s:property value="id.businId" />,<s:property value="#num.index" />)"/>
								</s:if>
								<s:else>
									<s:property value="id.cmoney"/>
								</s:else>
							</td>
							<td><s:property value="id.re"/></td>
							<td><s:property value="id.rem"/></td>
							<td><s:property value="id.nt"/></td>
							<td><s:property value="id.remarks"/></td>
							<td><s:property value="id.remarks5"/></td>
							<td>
								<s:if test="#session.fun217 == 1">
									<input id="remarks<s:property value="#num.index" />"
											type="text" value="${id.cost1}" class="border field" style="width:86px;"
											onchange="updateRemarks(<s:property value="id.businId" />,<s:property value="#num.index" />)"/>
								</s:if>
							</td>
							<td>
								<input type="checkbox" name="businIds" value="<s:property value="id.businId"/>" />
							</td>
							<td>
								<s:if test="#session.fun627 == 1">
									<s:if test="id.hiddenFlag==0">
										<a href="javascript:void(0);" 
											onclick="setHidden(<s:property value="id.businId"/>,1);">隐藏</a>
									</s:if>
									<s:if test="id.hiddenFlag==1">
										<a href="javascript:void(0);" 
											onclick="setHidden(<s:property value="id.businId"/>,0);">取消隐藏</a>
									</s:if>
								</s:if>
								<s:if test="id.newCostFlag==1 && #session.fun625 == 1">
		            				<a href="javascript:void(0);" 
										onclick="setNewCostFlag(<s:property value="id.businId"/>);">取消提醒</a>
		            			</s:if>
							</td>
							<td><s:property value="id.empName"/></td>
							<td>
								<s:if test="id.cstate==1">
									<s:property value="id.stateName"/>
								</s:if>
								<s:if test="id.cstate==2">
									<span style="color:red;"><s:property value="id.stateName"/></span>
								</s:if>
								<s:if test="id.cstate==3">
									<span style="color:green;"><s:property value="id.stateName"/></span>
								</s:if>
							</td>
							<td>
								<s:if test="id.hiddenFlag==1"><span style="color:red;">隐藏</span></s:if>
								<s:if test="id.hiddenFlag==0">未隐藏</s:if>
							</td>
							<td>
								<s:if test="id.newCostFlag==1">
		            				新增
		            			</s:if>
							</td>
						</tr>
		            </s:iterator>
		       </table>
		    </div>
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
	  </form>
  </body>
</html>

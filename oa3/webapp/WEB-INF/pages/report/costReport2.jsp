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
			var itemId = "${requestScope.query2.id.itemId}";
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
			var cstate = "${requestScope.query2.id.cstate}";
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
			var hiddenFlag = "${requestScope.query2.id.hiddenFlag}";
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
			var costGroup = "${requestScope.query2.id.groupid}";
			if (costGroup != null && costGroup != "" && costGroup > -1){
				var select = document.getElementById("costGroup");
				if (select != null){
					for(var i=0; i<select.options.length; i++){
						if(select.options[i].value == costGroup){
							select.options[i].selected = true;
							break;
						}
					}
				}
			}
			var baoxiao = "${requestScope.query2.id.baoxiao}";
			if (baoxiao != null && baoxiao != "" && baoxiao > -1){
				var select = document.getElementById("baoxiao");
				if (select != null){
					for(var i=0; i<select.options.length; i++){
						if(select.options[i].value == baoxiao){
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
			document.getElementById("queryForm").action = "${ctx}/report/CostReport2.action";
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
			document.getElementById("queryForm").action = "${ctx}/report/ExpCostReport2.action";
			document.getElementById("queryForm").submit();
		}
		function setHidden(businId, hiddenFlag){
			document.getElementById("businId").value = businId;
			document.getElementById("hiddenflag").value = hiddenFlag;
			document.getElementById("queryForm").action = "${ctx}/busin/SetHidden2.action";
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
			document.getElementById("queryForm").action = "${ctx}/busin/SetNewCostFlag22.action";
			document.getElementById("queryForm").submit();
		}
		function setBaoxiao(businId, hiddenFlag){
			document.getElementById("businId").value = businId;
			document.getElementById("hiddenflag").value = hiddenFlag;
			document.getElementById("queryForm").action = "${ctx}/busin/SetBaoxiao.action";
			document.getElementById("queryForm").submit();
		}
		function setBaoxiaoBatch(){
			document.getElementById("queryForm").action = "${ctx}/busin/SetBaoxiaoBatch.action";
			document.getElementById("queryForm").submit();
		}
	</script>
  </head>
  <body>
  	<form id="queryForm" action="${ctx}/report/CostReport.action" method="post">
	  	<div class="right">
	  		<div class="right_1">
				<table style="width:1390px; margin-left:0;">
					<tr>
						<td style="width:70px;">开始日期</td>
						<td style="text-align:left; width:90px;">
							<input id="startDate1" name="startDate1" value="${requestScope.startDate1}"
									type="text" class="field border" style="width:90px" onclick="WdatePicker()" readonly="readonly"/>
	  					</td>
	  					<td style="width:70px;">结束日期</td>
						<td style="text-align:left; width:90px;">
							<input id="endDate1" name="endDate1" value="${requestScope.endDate1}" 
									type="text" class="field border" style="width:90px" onclick="WdatePicker()" readonly="readonly"/>
	  					</td>
						<td style="width:70px;">服务单号</td>
						<td style="width:140px;text-align:left; ">
							<input id="billNo" name="query2.id.billNo" value="${requestScope.query2.id.billNo}" 
									type="text" class="field border" style="width:140px;" />
	  					</td>
	  					<td style="width:70px;">货柜号码</td>
						<td style="width:100px;text-align:left; ">
							<input name="container" value="${requestScope.container}" 
									type="text" class="field border" style="width:100px;" />
	  					</td>
	  					<td style="width:70px;">提单号码</td>
						<td style="width:100px;text-align:left; ">
							<input name="query2.id.takeNo" value="${requestScope.query2.id.takeNo}" 
									type="text" class="field border" style="width:100px;" />
	  					</td>
	  					<td style="width:70px;">客户名称</td>
						<td style="width:140px;text-align:left; ">
							<input id="cusName" name="query2.id.cusName" value="${requestScope.query2.id.cusName}" 
									type="text" class="field border" style="width:140px;" />
	  					</td>
	  					<td style="width:90px;">成本项目组</td>
						<td style="width:240px;">
							<select id="costGroup" name="query2.id.groupid" class="border _select" style="float:left;width:240px;border:1px solid lightblue;">
								<option value="0">PLEASE CHOOSE GROUP</option>
								<c:forEach var="costGroup" items="${requestScope.costGroup}">
									<option value="${costGroup.CId}">${costGroup.CName}</option>
								</c:forEach>
							</select>
	  					</td>
					</tr>
					<tr>
						<td>Ref</td>
						<td>
							<input name="query2.id.refno" value="${requestScope.query2.id.refno}" 
									type="text" class="field border" style="width:90px;" />
						</td>
						<td>Apply By</td>
						<td>
							<input name="query2.id.appbyName" value="${requestScope.query2.id.appbyName}" 
									type="text" class="field border" style="width:90px;" />
						</td>
						<!--  
						<td style="width:90px;">成本项目</td>
						<td >
							<select id="itemId" name="query2.id.itemId" class="border _select" style="width:140px;border:1px solid lightblue;">
								<option value="0">--全部--</option>
								<c:forEach var="itemList" items="${requestScope.itemList}">
									<option value="${itemList.CId}">${itemList.CName}</option>
								</c:forEach>
							</select>
	  					</td>
	  					-->
	  					<td style="width:90px;">隐藏标记</td>
						<td style="width:100px;text-align:left; ">
							<select id="hiddenFlag" name="query2.id.hiddenFlag" class="border" style="width:100px;">
								<option value="-1">--全部--</option>
								<option value="0">未隐藏</option>
								<option value="1">隐藏</option>
							</select>
	  					</td>
	  					<td style="width:90px;">报销标记</td>
						<td style="width:80px;text-align:left;">
							<select id="baoxiao" name="query2.id.baoxiao" class="border" style="width:100px;">
								<option value="-1">--全部--</option>
								<option value="0">未报销</option>
								<option value="1">已报销</option>
							</select>
	  					</td>
						<td colspan="4" style="text-align:left;">
							<input type="submit" value="查询" class="btn_img" style="margin-left:10px;"
										onclick="return forward(-1);"/>
							<input type="button" value="导出" class="btn_img" style="margin-left:10px;"
										onclick="expFile();"/>
							<s:if test="#session.fun628 == 1">
								<input type="submit" value="批量报销" class="btn_img2"
										onclick="setBaoxiaoBatch();" style="margin-left:10px;"/>
							</s:if>
							<input type="hidden" id="pageNow" name="pageNow" value="${pageNow}"/>
							<input type="hidden" id="businId" name="businId" />
							<input type="hidden" id="hiddenflag" name="hiddenFlag" />
							<input type="hidden" name="flag" value="1"/>
						</td>
					</tr>
				</table>
			</div>
	       	<div class="right_2">
	        </div>
	        <div class="right_3">
		        <table id="table1" class="table" style="width:1440px;">
		           	<tr>
		               	<th style="width:40px;">序号</th>
		               	<th style="width:140px;">File 业务单号</th>
		               	<th style="width:200px;">Client 客户名称</th>
		               	<th style="width:90px;">Apply By</th>
		               	<th style="width:220px;">（成本项目组）名称</th>
		               	<!--  
		               	<th style="width:220px;">成本项目</th>
		               	-->
		               	<th style="width:90px;">成本金额</th>
		               	<th style="width:90px;">财务</th>
		               	<th style="width:90px;">审核</th>
		               	<th style="width:90px;">备注</th>
		               	<th style="width:40px;">
		               		<input type="checkbox" id="delBtn" onclick="selectItem();"/>
		               	</th>
		               	<th style="width:140px;">操作</th>
		               	<th style="width:80px;">成本添加人</th>
		               	<th style="width:60px;">隐藏标记</th>
		               	<th style="width:60px;">成本标记</th>
		               	<th style="width:140px;">操作</th>
		            </tr>
		            <s:iterator value="#request.list" var="list" status="num">
		            	<s:if test="id.newCostFlag==1 || id.editCostFlag==1">
		            		<tr style="color:red;">
		            	</s:if>
			            <s:else>
			            	<tr>
			            </s:else>
							<td><s:property value="(#num.index+1)+(pageNow-1)*pageSize" /></td>
							<td><s:property value="id.billNo"/></td>
							<td><s:property value="id.cusName"/></td>
							<td><s:property value="id.appbyName"/></td>
							<td>
								<s:if test="id.groupid > 0">
									<s:property value="id.groupName"/>
								</s:if>
							</td>
							<!--  
							<td>
								<s:if test="id.groupid == 0">
									<s:property value="id.itemName"/>
								</s:if>
							</td>
							-->
							<td style="text-align:right;padding-right:5px;">
								<fmt:formatNumber type="number" value="${id.cmoney}" pattern="###,##0.00" />
							</td>
							<td><s:property value="id.remarks"/></td>
							<td><s:property value="id.remarks5"/></td>
							<td><s:property value="id.cost1"/></td>
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
								<s:if test="#session.fun628 == 1">
									<s:if test="id.baoxiao==0">
										<a href="javascript:void(0);" 
											onclick="setBaoxiao(<s:property value="id.businId"/>,1);">报销</a>
									</s:if>
									<s:if test="id.baoxiao==1">
										<a href="javascript:void(0);" 
											onclick="setBaoxiao(<s:property value="id.businId"/>,0);">取消报销</a>
									</s:if>
								</s:if>
							</td>
							<td><s:property value="id.empName"/></td>
							<td>
								<s:if test="id.hiddenFlag==1"><span style="color:red;">隐藏</span></s:if>
								<s:if test="id.hiddenFlag==0">未隐藏</s:if>
							</td>
							<td>
								<s:if test="id.editCostFlag==1">
		            				修改
		            			</s:if>
		            			<s:else>
		            				<s:if test="id.newCostFlag==1">
			            				新增
			            			</s:if>
		            			</s:else>
							</td>
							<td>
								<s:if test="(id.editCostFlag==1 || id.newCostFlag==1) && #session.fun625 == 1 &&
											#session.empId == id.empId">
		            				<a href="javascript:void(0);" 
										onclick="setNewCostFlag(<s:property value="id.businId"/>);">取消提醒</a>
		            			</s:if>
							</td>
						</tr>
		            </s:iterator>
		            <tr>
		            	<td colspan="5"></td>
		            	<td style="text-align:right;padding-right:5px;font-weight:bold;">
		            		<fmt:formatNumber type="number" value="${sumMoney}" pattern="###,##0.00" />
		            	</td>
		            	<td colspan="9"></td>
		            </tr>
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

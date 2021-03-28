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
			var cusid = "${requestScope.reQuery.cusId}";
			var select = document.getElementById("cusid");
			for(var i=0;i<select.options.length;i++){
				if(select.options[i].value == cusid){
					select.options[i].selected = true;
					break;
				}
			}
			var cusid2 = "${requestScope.reQuery.cusId2}";
			var select = document.getElementById("cusid2");
			for(var i=0;i<select.options.length;i++){
				if(select.options[i].value == cusid2){
					select.options[i].selected = true;
					break;
				}
			}
			var orderRule = "${requestScope.orderRule}";
			select = document.getElementById("orderRule");
			for(var i=0;i<select.options.length;i++){
				if(select.options[i].value == orderRule){
					select.options[i].selected = true;
					break;
				}
			}
			$('#cusid').comboSelect();
			$('#cusid2').comboSelect();
		});
		//分页控件
		function forward(flag){
			document.getElementById("queryForm").action = "${ctx}/report/BusinReceiveDReport.action";
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
		function computeRecieveMoney(businId, rowIndex, flag){
			var cmoney = document.getElementById("cmoney"+rowIndex).value;
			if (cmoney == "" || isNaN(cmoney)){
				cmoney = 0;
			}
			var recieveMoney1 = document.getElementById("receiveMoney1_"+rowIndex).value;
			if (recieveMoney1 == "" || isNaN(recieveMoney1)){
				recieveMoney1 = 0;
			}
			var recieveMoney2 = document.getElementById("receiveMoney2_"+rowIndex).value;
			if (recieveMoney2 == "" || isNaN(recieveMoney2)){
				recieveMoney2 = 0;
			}
			var recieveMoney4 = document.getElementById("receiveMoney4_"+rowIndex).value;
			if (recieveMoney4 == "" || isNaN(recieveMoney4)){
				recieveMoney4 = 0;
			}
			var recieveMoney = document.getElementById("receiveMoney"+rowIndex).value;
			if (recieveMoney == "" || isNaN(recieveMoney)){
				recieveMoney = 0;
			}
			
			document.getElementById("cmoney2_"+rowIndex).innerHTML = 
				Number(cmoney - recieveMoney1 - recieveMoney2 - recieveMoney4 - recieveMoney).toFixed(2);
				//Number(cmoney - recieveMoney1 - recieveMoney2 - recieveMoney3 - recieveMoney4 - recieveMoney).toFixed(2);
			var updateMoney = 0;
			if (flag==1){
				updateMoney = recieveMoney1;
			}else if (flag==2){
				updateMoney = recieveMoney2;
			}else if (flag==3){
				updateMoney = recieveMoney4;
			}else if (flag==4){
				updateMoney = recieveMoney;
			}
			var url="${ctx}/report/UpdateRecieveMoney.action?businId=" + businId + "&cmoney=" + updateMoney + "&flag="+flag;
			$.ajax({
				type:"Post",
				url:url,
				success:function(data){
					if (data != ""){
						alert(data);
					}
					if(Number(cmoney - recieveMoney1 - recieveMoney2 - recieveMoney4 - recieveMoney).toFixed(2) == 0.00){
						autoArchive(businId);
					}
				}
			});
		}
		function confirmArchive(businId, rowIndex){
			if (confirm("确认归档？")){
				//服务单id
				document.getElementById("businId").value = businId;
				//应收
				var cmoney = document.getElementById("cmoney"+rowIndex).value;
				if (cmoney == "" || isNaN(cmoney)){
					cmoney = 0;
				}
				//预付1
				var recieveMoney1 = document.getElementById("receiveMoney1_"+rowIndex).value;
				if (recieveMoney1 == "" || isNaN(recieveMoney1)){
					recieveMoney1 = 0;
				}
				//预付2
				var recieveMoney2 = document.getElementById("receiveMoney2_"+rowIndex).value;
				if (recieveMoney2 == "" || isNaN(recieveMoney2)){
					recieveMoney2 = 0;
				}
				//预付4
				var recieveMoney4 = document.getElementById("receiveMoney4_"+rowIndex).value;
				if (recieveMoney4 == "" || isNaN(recieveMoney4)){
					recieveMoney4 = 0;
				}
				//已收
				var recieveMoney = document.getElementById("receiveMoney"+rowIndex).value;
				if (recieveMoney == "" || isNaN(recieveMoney)){
					recieveMoney = 0;
				}
				//差额转至已收
				var cmoney = Number(cmoney - recieveMoney1 - recieveMoney2 - recieveMoney4).toFixed(2);
				document.getElementById("queryForm").action = "${ctx}/report/ScheduleArchive.action?cmoney="+cmoney;
				document.getElementById("queryForm").submit();
			}
		}
		function expFile(flag){
			if (flag==2 && !confirm("确认结转并导出？")){
				return;
			}
			document.getElementById("queryForm").action = "${ctx}/report/ExpRecieveReport.action?jiezhuan="+flag;
			document.getElementById("queryForm").submit();
		}
		function updateRemarks(rowIndex, businId){
			var remarks = document.getElementById("remarks"+rowIndex).value;
			var url="${ctx}/report/UpdateRecieveRemarks.action?remarks="+encodeURI(remarks)+"&businId="+businId;
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
		function updateRemarks2(rowIndex, businId){
			var remarks = document.getElementById("remarks2_"+rowIndex).value;
			var url="${ctx}/report/UpdateRecieveRemarks2.action?remarks="+encodeURI(remarks)+"&businId="+businId;
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
		//自动归档
		function autoArchive(businId){
			document.getElementById("businId").value = businId;
			document.getElementById("queryForm").action = "${ctx}/report/ScheduleArchive.action";
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
		function confirmArchive2(){
			document.getElementById("queryForm").action = "${ctx}/report/ScheduleArchive2.action";
			document.getElementById("queryForm").submit();
		}
	</script>
  </head>
  <body>
  <form id="queryForm" action="${ctx}/report/BusinReceiveDReport.action" method="post">
	  	<div class="right">
	  		<div class="right_1">
				<table style="width:2180px; margin-left:0;">
					<tr>
						<td style="width:60px;">开始日期</td>
						<td style="text-align:left; width:90px;">
							<input id="startDate1" name="startDate1" value="${requestScope.startDate1}"
									type="text" class="field border" style="width:90px" onclick="WdatePicker()" readonly="readonly"/>
	  					</td>
	  					<td style="width:60px;">结束日期</td>
						<td style="text-align:left; width:90px;">
							<input id="endDate1" name="endDate1" value="${requestScope.endDate1}" 
									type="text" class="field border" style="width:90px" onclick="WdatePicker()" readonly="readonly"/>
	  					</td>
	  					<td style="width:60px;">客户名称</td>
						<td style="width:140px;text-align:left; ">
							<select id="cusid" name="reQuery.cusId" class="border" style="width:140px;border:1px solid lightblue;">
								<option value="-1">--全部--</option>
								<c:forEach var="cusList" items="${requestScope.cusList}">
									<option value="${cusList.CId}">${cusList.CAddr}</option>
								</c:forEach>
							</select>
	  					</td>
	  					<td style="width:40px;">C/O</td>
						<td style="width:140px;text-align:left; ">
							<select id="cusid2" name="reQuery.cusId2" class="border" style="width:140px;border:1px solid lightblue;">
								<option value="-1">--全部--</option>
								<c:forEach var="cusList" items="${requestScope.cusList}">
									<option value="${cusList.CId}">${cusList.CAddr}</option>
								</c:forEach>
							</select>
	  					</td>
	  					<!--  
	  					<td style="width:70px;">收款进度</td>
						<td style="width:80px;text-align:left; ">
							<select id="flag" name="reQuery.flag" class="border" style="width:80px;">
								<option value="-1">--全部--</option>
								<option value="1">收清</option>
								<option value="2">未收清</option>
							</select>
	  					</td>
	  					-->
						<td style="width:70px;">服务单号</td>
						<td style="width:100px;text-align:left; ">
							<input id="billNo" name="reQuery.billNo" value="${requestScope.reQuery.billNo}" 
									type="text" class="field border" style="width:100px;" />
	  					</td>
	  					<td style="width:60px;">单据类型</td>
						<td style="width:130px;text-align:left; ">
							<select id="TBusinType" name="reQuery.typeId" class="border" style="width:132px;height:22px;">
				           		<option value="">PLEASE CHOOSE</option>
								<c:forEach var="businTypeList" items="${requestScope.businTypeList}">
									<option value="${businTypeList.CId}" <c:if test="${queryBean.typeId == businTypeList.CId}">selected</c:if>>${businTypeList.CName}</option>
								</c:forEach>
							</select>
	  					</td>
	  					<!--  
	  					<td style="width:60px;">经手人</td>
						<td style="width:80px;text-align:left; ">
							<input id="empName" name="reQuery.empName" value="${requestScope.reQuery.empName}" 
									type="text" class="field border" style="width:80px;" />
	  					</td>
	  					-->
	  					<td style="width:60px;">业务员</td>
						<td style="width:140px;text-align:left; ">
							<select id="salerId" name="reQuery.salerId" class="border" style="width:140px;border:1px solid lightblue;">
								<option value="-1">--全部--</option>
								<c:forEach var="saler" items="${requestScope.salerList}">
									<option value="${saler.CId}" <c:if test="${queryBean.salerId == saler.CId}">selected</c:if>>${saler.CName}</option>
								</c:forEach>
							</select>
	  					</td>
	  					<td style="width:60px;">是否超期</td>
						<td style="width:140px;text-align:left; ">
							<select id="overDaysFlag" name="reQuery.overDaysFlag" class="border" style="width:140px;border:1px solid lightblue;">
								<option value="">--全部--</option>
								<option value="0" <c:if test="${queryBean.overDaysFlag == 0}">selected</c:if>>未超期</option>
								<option value="1" <c:if test="${queryBean.overDaysFlag == 1}">selected</c:if>>已超期</option>
							</select>
	  					</td>
	  					<td style="width:60px;">排序规则</td>
						<td style="width:140px;text-align:left; ">
							<select id="orderRule" name="orderRule" class="border" style="width:120px;margin-left:7px;">
	  							<option value="0">--</option>
								<option value="2">按差额降序</option>
								<option value="1">按差额升序</option>
								<option value="4">按修改日期降序</option>
								<option value="3">按修改日期升序</option>
							</select>
	  					</td>
	  					<td>
							<input type="submit" value="查询" class="btn_img" style="margin-left:10px;"
										onclick="return forward(-1);"/>
							
							
							
	  					</td>
	  					<td>
	  					<input type="submit" value="导出" class="btn_img" style="margin-left:10px;"
									onclick="expFile(1);"/>
	  					</td>
	  					<td>
	  					<s:if test="#session.fun629 == 1">
								<input type="submit" value="结转并导出" class="btn_img" style="margin-left:10px;"
									onclick="expFile(2);"/>
							</s:if>
							<input type="hidden" id="pageNow" name="pageNow" value="${pageNow}"/>
							<input type="hidden" id="businId" name="businId" />
	  					</td>
						<td style="text-align:right;padding-right:280px;">
							<s:if test="#session.fun618==1">
								<input type="submit" value="批量归档" class="btn_img" style="margin-left:140px;"
										onclick="confirmArchive2();"/>
							</s:if>
						</td>
					</tr>
				</table>
			</div>
	       	<div class="right_2">
	        </div>
	        <div class="right_3">
		        <table id="table1" class="table" style="width:1880px;">
		           	<tr>
		           		<!--  
		               	<th style="width:40px;">序号</th>
		               	
		               	<th style="width:80px;">日期</th>
		               	-->
		               	<th style="width:150px;">客户名称</th>
		               	<th style="width:150px;">C/O</th>
		               	<th style="width:130px;">D.N</th>
		               	<th style="width:120px;">发票1</th>
		               	<th style="width:130px;">Re-Im</th>
		               	<th style="width:100px;">发票2</th>
		               	<th style="width:90px;">Tax INV</th>
		               	<th style="width:100px;">含税发票</th>
		               	<th style="width:100px;">应收金额</th>
						<th style="width:100px;">发票1<br/>预收款</th>
		               	<th style="width:100px;">发票2<br/>预收款</th>
		               	<th style="width:100px;">含税发票<br/>预收款</th>
		               	<th style="width:100px;">已收金额</th>
		               	<th style="width:100px;">差额</th>
		               	
		               	<!--  
		               	<th style="width:150px;">财务备注</th>
		               	-->
		               	<th style="width:150px;">备注</th>
		               	<th style="width:120px;">付款到期</th>
		               	<th style="width:120px;">超期天数</th>
		               	<th style="width:40px;">
		               		<input type="checkbox" id="delBtn" onclick="selectItem();"/>
		               	</th>
		               	<th style="width:60px;"></th>
		               	<th style="width:130px;">服务单号</th> 
		            </tr>
		            <s:iterator value="#request.receiveList" var="receiveList" status="num">
			            <tr <s:if test="overDaysFlag == 1">style='color:red;'</s:if>>
			            	<!--  
							<td><s:property value="(#num.index+1)+(pageNow-1)*pageSize" /></td>
							<td>
								<s:date name="cdate2" format="dd-MM-yyyy"/>
							</td>
							-->
							<td><s:property value="cusName"/></td>
							<td><s:property value="cusName2"/></td>
							
							<td><s:property value="reim"/></td>
							<td style="text-align:right;padding-right:5px;">
								$<fmt:formatNumber type="number" 
										value="${money1 - receiveMoney1}" 
										pattern="##0.00" />
							</td>
							
							<td><s:property value="inv"/></td>
							<td style="text-align:right;padding-right:5px;">
								$<fmt:formatNumber type="number" 
										value="${money2 - receiveMoney2}" 
										pattern="##0.00" />
							</td>
							
							<td><s:property value="nodate7"/></td>
							<td style="text-align:right;padding-right:5px;">
								$<fmt:formatNumber type="number" 
										value="${money4 - receiveMoney4}" 
										pattern="##0.00" />
							</td>
							
							<td style="text-align:right;padding-right:5px;">
								<fmt:formatNumber type="number" value="${cmoney}" pattern="##0.00" />
								<input id="cmoney<s:property value="#num.index" />" type="hidden" value="${cmoney}"/>
							</td>
							<td>
								<s:if test="#session.fun608==1">
									<input id="receiveMoney1_<s:property value="#num.index" />" type="text" value="${receiveMoney1}"
											class="border field" style="width:86px;text-align:right;" 
											onchange="computeRecieveMoney(<s:property value="businId" />,<s:property value="#num.index" />,1);"/>
								</s:if>
								<s:else>
									<input id="receiveMoney1_<s:property value="#num.index" />" type="hidden" value="${receiveMoney1}"/>
								</s:else>
							</td>
							<td>
								<s:if test="#session.fun608==1">
									<input id="receiveMoney2_<s:property value="#num.index" />" type="text" value="${receiveMoney2}"
											class="border field" style="width:86px;text-align:right;" 
											onchange="computeRecieveMoney(<s:property value="businId" />,<s:property value="#num.index" />,2);"/>
								</s:if>
								<s:else>
									<input id="receiveMoney2_<s:property value="#num.index" />" type="hidden" value="${receiveMoney2}"/>
								</s:else>
							</td>
							<td>
								<s:if test="#session.fun608==1">
									<input id="receiveMoney4_<s:property value="#num.index" />" type="text" value="${receiveMoney4}"
											class="border field" style="width:86px;text-align:right;" 
											onchange="computeRecieveMoney(<s:property value="businId" />,<s:property value="#num.index" />,3);"/>
								</s:if>
								<s:else>
									<input id="receiveMoney4_<s:property value="#num.index" />" type="hidden" value="${receiveMoney4}"/>
								</s:else>
							</td>
							<td>
								<s:if test="#session.fun620==1">
									<input id="receiveMoney<s:property value="#num.index" />" type="text" value="${receiveMoney}"
											class="border field" style="width:86px;text-align:right;" 
											onchange="computeRecieveMoney(<s:property value="businId" />,<s:property value="#num.index" />,4);"/>
								</s:if>
								<s:else>
									<input id="receiveMoney<s:property value="#num.index" />" type="hidden" value="${receiveMoney}"/>
								</s:else>
							</td>
							<td>
								<div id="cmoney2_<s:property value="#num.index" />" style="text-align:right;padding-right:5px;">
									<fmt:formatNumber type="number" 
										value="${cmoney - receiveMoney1 - receiveMoney2 - receiveMoney3 - receiveMoney4 - receiveMoney}" 
										pattern="##0.00" />
								</div>
							</td>
							
							<!--  
							<td><s:property value="empName"/></td>
							
							<td>
								<s:if test="#session.fun619==1">
									<input id="remarks2_<s:property value="#num.index" />" type="text" value="${remarks2}"
										class="border field" style="width:146px;" 
										onchange="updateRemarks2(<s:property value="#num.index" />,<s:property value="businId" />);"/>
								</s:if>
							</td>
							-->
							<td>
								<s:if test="#session.fun618==1">
									<input id="remarks<s:property value="#num.index" />" type="text" value="${remarks}"
										class="border field" style="width:146px;" 
										onchange="updateRemarks(<s:property value="#num.index" />,<s:property value="businId" />);"/>
								</s:if>
							</td>
							<td><s:property value="payDateStr"/></td>
							<td><s:property value="overDays"/></td>
							<td>
								<input type="checkbox" name="businIds" value="<s:property value="businId"/>" />
							</td>
							<td>
								<s:if test="schedule_archive==0">
									<s:if test="#session.fun618==1">
										<a href="javascript:void(0);" 
											onclick="confirmArchive(<s:property value="businId"/>,<s:property value="#num.index" />);">归档</a>
									</s:if>
								</s:if>
							</td>
							
							<td><s:property value="billNo"/></td>
							
						</tr>
		            </s:iterator>
		            <tr>
						<td colspan="3"></td>
						<td style="text-align:right;padding-right:5px;font-weight:bold;">
							<fmt:formatNumber type="number" value="${_money1}" pattern="###,##0.00" />
						</td>
						<td></td>
						<td style="text-align:right;padding-right:5px;font-weight:bold;">
							<fmt:formatNumber type="number" value="${_money2}" pattern="###,##0.00" />
						</td>
						<td></td>
						<td style="text-align:right;padding-right:5px;font-weight:bold;">
							<fmt:formatNumber type="number" value="${_money4}" pattern="###,##0.00" />
						</td>
						<td style="text-align:right;padding-right:5px;font-weight:bold;">
							<fmt:formatNumber type="number" value="${recieve}" pattern="###,##0.00" />
						</td>
						<td colspan="3"></td>
						<td style="text-align:right;padding-right:5px;font-weight:bold;">
							<fmt:formatNumber type="number" value="${recieved}" pattern="###,##0.00" />
						</td>
						<td style="text-align:right;padding-right:5px;font-weight:bold;">
							<fmt:formatNumber type="number" value="${limit}" pattern="###,##0.00" />
						</td>
						<td colspan="4"></td>
					</tr>
		       </table>
		    </div>
		    <!--  
		    <div class="right_4" style="margin-top:10px;">
		    	<div style="float:left; margin-left:5px; font-weight:bold;">
	           		应收总计：${requestScope.cmoney11}
	           	</div>
	           	<div style="float:left; margin-left:25px; font-weight:bold;">
	           		已收总计：${requestScope.receiveMoney11}
	           	</div>
	           	<div style="float:left; margin-left:25px; font-weight:bold;">
	           		差额总计：${requestScope.cmoney22}
	           	</div>
		    </div>
		    -->
		    <div class="clear"></div>
	       <div class="right_4">
	           	<div style="float:left; margin-left:5px;">
	           		当前第${pageBean.pageNow}页/共${pageBean.pageCount}页
	           	</div>
	           	<div style="float:left; margin-left:610px;">
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

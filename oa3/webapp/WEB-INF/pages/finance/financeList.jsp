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
		
			//支出类型
			var financeType = "${requestScope.query.ftype}";
			if (financeType != null && financeType != "" && financeType != -1){
				var select = document.getElementById("financeType");
				for(var i=0; i<select.options.length; i++){
					if(select.options[i].value == financeType){
						document.getElementById("financeType").options[i].selected = true;
						break;
					}
				}
			}
			
			//归档状态
			var archivingFlag = "${requestScope.query.archivingFlag}";
			if (archivingFlag != null && archivingFlag != "" && archivingFlag != -1){
				var select = document.getElementById("archive");
				for(var i=0; i<select.options.length; i++){
					if(select.options[i].value == archivingFlag){
						select.options[i].selected = true;
						break;
					}
				}
			}
			
			//后台校验错误信息
			var errInfo = "${requestScope.errInfo}";
			if (errInfo != null && errInfo.length > 0){
				alert(errInfo);
			}
		});
		
		//分页控件
		function forward(flag){
		
			if (document.getElementById("ctype").value == 1){
				if (document.getElementById("listFlag").value == 1){
					document.getElementById("queryForm").action = "${ctx}/finance/FinanceInList.action";
				}else{
					document.getElementById("queryForm").action = "${ctx}/finance/FinanceInList2.action";
				}
			}else{
				if (document.getElementById("listFlag").value == 1){
					document.getElementById("queryForm").action = "${ctx}/finance/FinanceOutList.action";
				}else{
					document.getElementById("queryForm").action = "${ctx}/finance/FinanceOutList2.action";
				}
			}
		
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
		//更新备注
		function updateRemarks(financeid){
			var remarks = encodeURI(document.getElementById("remarks"+financeid).value);
			var url="${ctx}/finance/UpdateRemarks.action?id=" + financeid + "&remarks='" + remarks + "'";
			$.ajax({
				type:"Post",
				url:url,
				success:function(data){
					if (data != ""){
						alert("自动更新备注失败！");
					}
				}
			});
		}
		function updateRemarks2(financeid){
			var remarks = encodeURI(document.getElementById("remarks2_"+financeid).value);
			var url="${ctx}/finance/UpdateRemarks2.action?id=" + financeid + "&remarks='" + remarks + "'";
			$.ajax({
				type:"Post",
				url:url,
				success:function(data){
					if (data != ""){
						alert("自动更新备注失败！");
					}
				}
			});
		}
		//归档确认
		function conClose(cid,archiveFlag){
			if (confirm("确认归档？")){
				document.getElementById("cid").value = cid;
				document.getElementById("archiveFlag").value = archiveFlag;
				if (document.getElementById("ctype").value == 1){
					document.getElementById("queryForm").action = "${ctx}/finance/FinanceInClose.action";
				}else{
					document.getElementById("queryForm").action = "${ctx}/finance/FinanceOutClose.action";
				}
				document.getElementById("queryForm").submit();
			}
		}
		//解除归档确认
		function conOpen(cid){
			if (confirm("确认解除归档？")){
				document.getElementById("cid").value = cid;
				if (document.getElementById("ctype").value == 1){
					document.getElementById("queryForm").action = "${ctx}/finance/FinanceInOpen.action";
				}else{
					document.getElementById("queryForm").action = "${ctx}/finance/FinanceOutOpen.action";
				}
				document.getElementById("queryForm").submit();
			}
		}
		//查看
		function view(cid){
			document.getElementById("cid").value = cid;
			if (document.getElementById("ctype").value == 1){
				document.getElementById("queryForm").action = "${ctx}/finance/ViewFinanceIn.action";
			}else{
				document.getElementById("queryForm").action = "${ctx}/finance/ViewFinanceOut.action";
			}
			document.getElementById("queryForm").submit();
		}
		//添加
		function add(){
			if (document.getElementById("ctype").value == 1){
				document.getElementById("queryForm").action = "${ctx}/finance/AddFinanceIn.action";
			}else{
				document.getElementById("queryForm").action = "${ctx}/finance/AddFinanceOut.action";
			}
			document.getElementById("queryForm").submit();
		}
		//修改
		function edit(cid){
			document.getElementById("cid").value = cid;
			if (document.getElementById("ctype").value == 1){
				document.getElementById("queryForm").action = "${ctx}/finance/EditFinanceIn.action";
			}else{
				document.getElementById("queryForm").action = "${ctx}/finance/EditFinanceOut.action";
			}
			document.getElementById("queryForm").submit();
		}
		//删除确认
		function confirmDel(cid){
			if (confirm("删除的数据将无法恢复，是否确认删除？")){
				document.getElementById("cid").value = cid;
				if (document.getElementById("ctype").value == 1){
					document.getElementById("queryForm").action = "${ctx}/finance/DeleteFinanceIn.action";
				}else{
					document.getElementById("queryForm").action = "${ctx}/finance/DeleteFinanceOut.action";
				}
				document.getElementById("queryForm").submit();
			}
			return false;
		}
		//导出
		function exp(){
			if (document.getElementById("ctype").value == 1){
				document.getElementById("queryForm").action = "${ctx}/finance/ExpFinanceIn.action";
			}else{
				document.getElementById("queryForm").action = "${ctx}/finance/ExpFinanceOut.action";
			}
			document.getElementById("queryForm").submit();
		}
		function selectItem(){
			var tb = document.getElementById("table1");
			var rowcount = tb.rows.length - 1;
			for(var i=0; i<rowcount; i++){
				if (document.getElementById("delBtn").checked){
					//全选
					document.getElementsByName("financeId")[i].checked = true;
				}else{
					//全不选
					document.getElementsByName("financeId")[i].checked = false;
				}
			}
		}
		function batchClose(flag, archiveFlag){
			if (confirm("确认批量归档？")){
				document.getElementById("archiveFlag").value = archiveFlag;
				if (flag==1){
					document.getElementById("queryForm").action = "${ctx}/finance/FinanceBatchInClose.action";
				}else{
					document.getElementById("queryForm").action = "${ctx}/finance/FinanceBatchOutClose.action";
				}
				document.getElementById("queryForm").submit();
			}
		}
	</script>
  </head>
  <body>
  	<form id="queryForm" action="${ctx}/finance/FinanceList.action" method="post">
	  	<div class="right">
	  		<div class="right_1">
				<table style="width:1200px; margin-left:0;">
					<tr>
						<td style="width:70px;">开始日期</td>
						<td style="text-align:left; width:80px;">
							<input id="startDate" name="startDate" value="${requestScope.startDate}"
									type="text" class="field border" style="width:80px" onclick="WdatePicker()" readonly="readonly"/>
	  					</td>
	  					<td style="width:70px;">结束日期</td>
						<td style="text-align:left; width:80px;">
							<input id="endDate" name="endDate" value="${requestScope.endDate}" 
									type="text" class="field border" style="width:80px" onclick="WdatePicker()" readonly="readonly"/>
	  					</td>
	  					
	  					<td style="width:70px;">
	  						<s:if test="#request.query.ctype == 2">
								支出类型
							</s:if>
							<s:else>
								收入类型
							</s:else>
	  					</td>
	  					<td style="width:150px;text-align:left; ">
							<select id="financeType" name="query.ftype" class="border" style="width:150px;">
				           		<option value="-1">--请选择--</option>
								<c:forEach var="financeTypeList" items="${requestScope.financeTypeList}">
									<option value="${financeTypeList.CId}">${financeTypeList.CName}</option>
								</c:forEach>
							</select>
						</td>
	  					<td style="width:40px;">
	  						<s:if test="#request.query.ctype == 1">
	  							来自
	  						</s:if>
	  						<s:else>
	  							付给
	  						</s:else>
	  					</td>
						<td style="width:80px;text-align:left; ">
							<input id="cpay" name="query.cpay" value="${requestScope.query.cpay}" 
									type="text" class="field border" style="width:80px;" />
	  					</td>
						<td style="width:70px;">银行名称</td>
						<td style="width:70px;text-align:left; ">
							<input id="bankName" name="query.bankName" value="${requestScope.query.bankName}" 
									type="text" class="field border" style="width:70px;" />
	  					</td>
	  					<td style="width:50px;">经手人</td>
						<td style="width:50px;text-align:left; ">
							<input id="empName" name="query.empName" value="${requestScope.query.empName}" 
									type="text" class="field border" style="width:50px;" />
	  					</td>
	  					<td style="width:70px;">归档状态</td>
	  					<td style="width:60px;text-align:left; ">
							<select id="archive" name="query.archivingFlag" class="border" style="width:60px;">
				           		<option value="-1">全部</option>
				           		<option value="2">未完成</option>
				           		<option value="3">已完成</option>
							</select>
						</td>
						<td style="width:200px;"></td>
					</tr>
					<tr>
						<td>备注明细</td>
						<td style="text-align:left; " colspan="3">
							<input name="detailRemarks" value="${requestScope.detailRemarks}" 
									type="text" class="field border" style="width:180px;" />
	  					</td>
	  					<td>描述</td>
						<td style="width:60px;text-align:left; ">
							<input name="query.description" value="${requestScope.query.description}" 
									type="text" class="field border" style="width:120px;" />
	  					</td>
						<td>总金额</td>
						<td style="width:60px;text-align:left; ">
							<input name="query.cmoney" value="${requestScope.query.cmoney}" 
									type="text" class="field border" style="width:120px;" />
	  					</td>
	  					<td colspan="6" style="text-align:left;">
							<input type="submit" value="查询" class="btn_img" style="margin-left:10px;"
										onclick="return forward(-1);"/>
							<s:if test="#request.listFlag == 1">
								<input type="button" value="添加" class="btn_img" style="margin-left:10px;"
										onclick="add();"/>
							</s:if>
							<input type="button" value="导出" class="btn_img" style="margin-left:10px;"
										onclick="exp();"/>
							<input type="hidden" id="pageNow" name="pageNow" value="${pageNow}"/>
							<input type="hidden" id="ctype" name="query.ctype" value="${requestScope.query.ctype}"/>
							<input type="hidden" id="listFlag" name="listFlag" value="${requestScope.listFlag}"/>
							<input type="hidden" id="cid" name="id" value="${requestScope.id}"/>
							<input type="hidden" id="archiveFlag" name="archiveFlag" />
						</td>
						<td colspan="3" style="text-align:right;">
							<s:if test="#request.listFlag == 1">
								<s:if test="#request.query.ctype == 1">
									<s:if test="#session.fun514 == 1">
										<input type="submit" value="归档(未)" class="btn_img" style="margin-left:10px;"
												onclick="batchClose(1,2);"/>
										<input type="submit" value="归档(已)" class="btn_img" style="margin-left:10px;"
												onclick="batchClose(1,3);"/>
									</s:if>
								</s:if>
								<s:else>
									<s:if test="#session.fun515 == 1">
										<input type="submit" value="归档(未)" class="btn_img" style="margin-left:10px;"
												onclick="batchClose(2,2);"/>
										<input type="submit" value="归档(已)" class="btn_img" style="margin-left:10px;"
												onclick="batchClose(2,3);"/>
									</s:if>
								</s:else>
							</s:if>
						</td>
					</tr>
				</table>
			</div>
	        <div class="right_3">
	        	<s:if test="#request.query.ctype == 1">
		        	<table id="table1" class="table" style="width:1430px;">
		        </s:if>
		        <s:else>
		        	<table id="table1" class="table" style="width:1530px;">
		        </s:else>
		        
		           	<tr>
		               	<th style="width:30px;">序号</th>
		               	<th style="width:80px;">日期</th>
		               	<th style="width:120px;">
		               		<s:if test="#request.query.ctype == 1">
		               			收入凭证编号
		               		</s:if>
		               		<s:else>
		               			支出凭证编号
		               		</s:else>
		               	</th>
		               	<s:if test="#request.query.ctype == 1">
		               		<th style="width:140px;">
		               		客户名称
		               		</th>
		               	</s:if>
		               	<th style="width:140px;">
		               		<s:if test="#request.query.ctype == 1">
		               			来自
		               		</s:if>
		               		<s:else>
		               			付给
		               		</s:else>
						</th>
						<s:if test="#request.query.ctype == 2">
							<th style="width:80px;">支出类型</th>
						</s:if>
						<s:else>
							<th style="width:80px;">收入类型</th>
						</s:else>
						<th style="width:100px;">银行名称</th>
						<s:if test="#request.query.ctype == 2">
							<th style="width:70px;">支票号码</th>
						</s:if>
		               	<th style="width:100px;">明细备注</th>
		               	<th style="width:140px;">描述</th>
		               	<th style="width:60px;">总金额</th>
		               	<th style="width:70px;">经手人</th>
		               	<th style="width:50px;">是否<br/>打印</th>
		               	<th style="width:90px;">状态</th>
		               	<s:if test="#request.listFlag == 1">
			               	<th style="width:40px;">
			               		<input type="checkbox" id="delBtn" onclick="selectItem();"/>
			               	</th>
			            </s:if>
		                <th style="width:200px;">操作</th>
		               	<th style="width:140px;">备注</th>
		               	<th style="width:140px;">remarks</th>
		            </tr>
		            <s:iterator value="#request.financeList" var="financeList" status="num">
		            <tr>
						<td>
							<s:property value="(#num.index+1)+(pageNow-1)*pageSize" />
						</td>
						<td><s:date name="cdate" format="dd-MM-yyyy"/></td>
						<td><s:property value="financeNo"/></td>
						<s:if test="#request.query.ctype == 1">
							<td><s:property value="cusName"/></td>
						</s:if>
						<td><s:property value="cpay"/></td>
						<td><s:property value="ftypeName"/></td>
						<td><s:property value="bankName"/></td>
						<s:if test="#request.query.ctype == 2">
							<td><s:property value="checkNo"/></td>
						</s:if>
						<td><s:property value="isCashName"/></td>
						<td><s:property value="description"/></td>
						<td><s:property value="cmoney"/></td>
						<td><s:property value="empName"/></td>
						<td><s:property value="stateName"/></td>
						<td>
							<s:if test="archivingFlag == 1">
								<s:property value="archivingName"/>
							</s:if>
							<s:if test="archivingFlag == 2">
								<span style="color:red;">
									<s:property value="archivingName"/>
								</span>
							</s:if>
							<s:if test="archivingFlag == 3">
								<span style="color:green;">
									<s:property value="archivingName"/>
								</span>
							</s:if>
						</td>
						<s:if test="#request.listFlag == 1">
			               	<td>
								<input type="checkbox" name="financeId" value="<s:property value="cid"/>" />
							</td>
			            </s:if>
						<td>
							<s:if test="#request.query.ctype == 1">
								<s:if test="#request.archivingFlag == 1">
									<a href="javascript:void(0);" onclick="edit(<s:property value="cid"/>);">修改</a>
									<s:if test="cstate == 1">
										<a href="javascript:void(0);" onclick="confirmDel(<s:property value="cid"/>);">删除</a>
									</s:if>
									<s:if test="#session.fun514 == 1">
										<a href="javascript:void(0);" onclick="conClose(<s:property value="cid"/>,2);">归档(未)</a>
										<a href="javascript:void(0);" onclick="conClose(<s:property value="cid"/>,3);">归档(已)</a>
									</s:if>
								</s:if>
								<s:else>
									<s:if test="#session.fun514 == 1">
										<a href="javascript:void(0);" onclick="conOpen(<s:property value="cid"/>);">解除归档</a>
									</s:if>
									<s:if test="#request.archivingFlag == 2">
										<s:if test="#session.fun514 == 1">
											<a href="javascript:void(0);" onclick="conClose(<s:property value="cid"/>,3);">归档(已)</a>
										</s:if>
									</s:if>
								</s:else>
							</s:if>
							<s:else>
								<s:if test="#request.archivingFlag == 1">
									<a href="javascript:void(0);" onclick="edit(<s:property value="cid"/>);">修改</a>
									<s:if test="cstate == 1">
										<a href="javascript:void(0);" onclick="confirmDel(<s:property value="cid"/>);">删除</a>
									</s:if>
									<s:if test="#session.fun515 == 1">
										<a href="javascript:void(0);" onclick="conClose(<s:property value="cid"/>,2);">归档(未)</a>
										<a href="javascript:void(0);" onclick="conClose(<s:property value="cid"/>,3);">归档(已)</a>
									</s:if>
									<s:else>
										<s:if test="ftype == 5 && #session.fun516 == 1">
											<a href="javascript:void(0);" onclick="conClose(<s:property value="cid"/>,3);">归档(已)</a>
										</s:if>
									</s:else>
								</s:if>
								<s:else>
									<s:if test="#session.fun515 == 1">
										<a href="javascript:void(0);" onclick="conOpen(<s:property value="cid"/>);">解除归档</a>
									</s:if>
									<s:if test="#request.archivingFlag == 2">
										<s:if test="#session.fun515 == 1 || (ftype == 5 && #session.fun516 == 1)">
											<a href="javascript:void(0);" onclick="conClose(<s:property value="cid"/>,3);">归档(已)</a>
										</s:if>
									</s:if>
								</s:else>
							</s:else>
							<a href="javascript:void(0);" onclick="view(<s:property value="cid"/>);">查看</a>
						</td>
						<s:if test="#request.query.ctype == 1">
							<td>
								<s:if test="#session.fun510==1">
									<!--  
									<s:if test="#request.archivingFlag == 1">
										<input id="remarks<s:property value="cid"/>" type="text" value="<s:property value="remarks"/>" 
												class="field border" style="width:136px" 
												onblur="updateRemarks(<s:property value="cid"/>);" />
									</s:if>
									<s:else>
										${requestScope.remarks}
									</s:else>
									-->
									<input id="remarks<s:property value="cid"/>" type="text" value="<s:property value="remarks"/>" 
												class="field border" style="width:136px" 
												onblur="updateRemarks(<s:property value="cid"/>);" />
								</s:if>
							</td>
						</s:if>
						<s:if test="#request.query.ctype == 2">
							<td>
								<s:if test="#session.fun511==1">
									<!--  
									<s:if test="#request.archivingFlag == 1">
										<input id="remarks<s:property value="cid"/>" type="text" value="<s:property value="remarks"/>" 
											class="field border" style="width:136px" 
											onblur="updateRemarks(<s:property value="cid"/>);" />
									</s:if>
									<s:else>
										${requestScope.remarks}
									</s:else>
									-->
									<input id="remarks<s:property value="cid"/>" type="text" value="<s:property value="remarks"/>" 
											class="field border" style="width:136px" 
											onblur="updateRemarks(<s:property value="cid"/>);" />
								</s:if>
							</td>
						</s:if>
						<td>
							<input id="remarks2_<s:property value="cid"/>" type="text" value="<s:property value="remarks2"/>" 
										class="field border" style="width:136px" 
										onblur="updateRemarks2(<s:property value="cid"/>);" />
						</td>
					</tr>
		            </s:iterator>
		       </table>
		    </div>
		</form>
	    <div class="right_4" style="margin-top:10px;">
	    	<div style="float:left; margin-left:5px; font-weight:bold;">
           		<s:if test="#request.query.ctype == 1">
           			收入总计：
           		</s:if>
           		<s:else>
           			支出总计：
           		</s:else>
           		<fmt:formatNumber type="number" value="${cmoney}" pattern="###,###.00" />
           	</div>
	    </div>
	     <div class="clear"></div>
       <div class="right_4" style="width:1300px;">
           	<div style="float:left; margin-left:5px;">
           		当前第${pageBean.pageNow}页/共${pageBean.pageCount}页
           	</div>
           	<s:if test="#request.query.ctype == 1">
           		<div style="float:left; margin-left:810px;">
           	</s:if>
           	<s:else>
           		<div style="float:left; margin-left:1000px;">
           	</s:else>
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

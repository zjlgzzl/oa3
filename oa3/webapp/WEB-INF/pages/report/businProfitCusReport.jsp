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
			var cusid = "${requestScope.businProfitCusQuery.cusId}";
			var select = document.getElementById("cusid");
			for(var i=0;i<select.options.length;i++){
				if(select.options[i].value == cusid){
					select.options[i].selected = true;
					break;
				}
			}
			var rowcount = "${requestScope.rowcount}";
			if (rowcount > 0){
				for(var i=0; i<rowcount; i++){
					var _saleId = document.getElementById("_saleId"+i).value;
					var select = document.getElementById("saleId"+i);
					for(var j=0;j<select.options.length;j++){
						if(select.options[j].value == _saleId){
							select.options[j].selected = true;
							break;
						}
					}
				}
			}
			$('#cusid').comboSelect();
		});
		//分页控件
		function forward(flag){
			document.getElementById("queryForm").action = "${ctx}/report/BusinReceiveCusReport.action";
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
		function updateRemarks(cusId, rowIndex){
			var remarks = document.getElementById("remarks"+rowIndex).value;
			var url="${ctx}/report/UpdateCusRemarks.action?remarks="+encodeURI(remarks)+"&cusId="+cusId;
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
		
		function updateRemarks2(cusId, rowIndex){
			var remarks = document.getElementById("remarks2_"+rowIndex).value;
			var url="${ctx}/report/UpdateCusRemarks2.action?remarks="+encodeURI(remarks)+"&cusId="+cusId;
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
		
		
		function updatePayableDatetime(cusId, rowIndex){
			var payableDatetime = document.getElementById("payableDatetime"+rowIndex).value;
			if (payableDatetime == null){
				payableDatetime = "";
			}
			var url="${ctx}/report/UpdateCusPayableDatetime.action?payableDatetime="+payableDatetime+"&cusId="+cusId;
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
		
		function updateSale(cusId, rowIndex){
			var saleId = document.getElementById("saleId"+rowIndex).value;
			var url="${ctx}/report/UpdateCusSale.action?saleId="+saleId+"&cusId="+cusId;
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
		
		function setFlag(cusId, flag){
			document.getElementById("queryForm").action = "${ctx}/report/UpdateCusFlag.action?cflag="+flag+"&cusId="+cusId;
			document.getElementById("queryForm").submit();
		}
		
		function payableDateFocus(element,cusId,rowIndex){
			var onpickedFunc = function(){updatePayableDatetime(cusId, rowIndex);}
			var clearedFunc = function(){updatePayableDatetime(cusId, rowIndex);}
			WdatePicker({el:element,oncleared:clearedFunc,onpicked:onpickedFunc})
		}
		
		function expFile(){
			document.getElementById("queryForm").action = "${ctx}/report/ExpBusinReceiveCusReport.action";
			document.getElementById("queryForm").submit();
		}
		
	</script>
  </head>
  <body>
  	<div class="right">
  		<div class="right_1">
			<form id="queryForm" action="${ctx}/report/BusinReceiveCusReport.action" method="post">
				<table style="width:1200px; margin-left:0;">
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
	  					<td style="width:70px;">客户名称</td>
						<td style="width:150px;text-align:left; ">
							<select id="cusid" name="businProfitCusQuery.cusId" class="border" style="width:220px;border:1px solid lightblue;">
								<option value="-1">--全部--</option>
								<c:forEach var="cusList" items="${requestScope.cusList}">
									<option value="${cusList.CId}">${cusList.CAddr}</option>
								</c:forEach>
							</select>
	  					</td>
	  					<td style="width:70px;">业务员</td>
						<td style="width:150px;text-align:left; ">
							<select id="saleId" name="businProfitCusQuery.saleId" class="border" style="width:220px;border:1px solid lightblue;">
								<option value="-1">--全部--</option>
								<c:forEach var="saler" items="${requestScope.saleList}">
									<option value="${saler.CId}" <c:if test="${queryBean.saleId == saler.CId}">selected</c:if>>${saler.CName}</option>
								</c:forEach>
							</select>
	  					</td>
	  					<td style="text-align:left;">
	  						<input type="submit" value="查询" class="btn_img" style="margin-left:10px;"
									onclick="return forward(-1);"/>
							<input type="submit" value="导出" class="btn_img" style="margin-left:10px;"
									onclick="expFile();"/>
							<input type="hidden" id="pageNow" name="pageNow" value="${pageNow}"/>
							<input type="hidden" id="firstQuery" name="firstQuery" value="0"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
        <div class="right_3">
	        <table id="table1" class="table" style="width:890px;">
	           	<tr>
	               	<th style="width:40px;">序号</th>
	               	<th style="width:360px;">客户名称</th>
					<th style="width:140px;">应收金额</th>
					<th style="width:120px;">差额</th>
					<th style="width:100px;">该付款日期</th>
					<th style="width:80px;">超时提醒</th>
					<th style="width:360px;">备注</th>
					<th style="width:100px;">收款人</th>
					<th style="width:360px;">付款方式备注</th>
					<th style="width:90px;">操作</th>
	            </tr>
	            <s:iterator value="#request.proList" var="proList" status="num">
	            	<s:if test="flag==1 || flag2==1">
	            		<tr style="color:red;">
	            	</s:if>
	            	<s:else>
	            		<tr>
	            	</s:else>
						<td><s:property value="(#num.index+1)+(pageNow-1)*60" /></td>
						<td><s:property value="cusName"/></td>
						<td style="text-align:right;padding-right:5px;">
							<fmt:formatNumber type="number" value="${cmoney}" pattern="###,##0.00" />
						</td>
						<td style="text-align:right;padding-right:5px;">
							<fmt:formatNumber type="number" value="${cmoney2}" pattern="###,##0.00" />
						</td>
						<td>
							<input id="payableDatetime<s:property value="#num.index" />" 
									value="${payableDate}"
									type="text" class="field border" style="width:90px;text-align:center;" 
									onfocus="payableDateFocus(this,<s:property value='cusId' />,<s:property value='#num.index' />);" 
									readonly="readonly"/>
						</td>
						<td>
							<s:if test="flag2==1">
								<span style="color:red;">超时</span>
							</s:if>
						</td>
						<td>
							<input id="remarks<s:property value="#num.index" />"
									type="text" value="${remarks}" class="border field" style="width:354px;"
									onchange="updateRemarks(<s:property value="cusId" />,<s:property value="#num.index" />)"/>
						</td>
						<td>
							<select id="saleId<s:property value="#num.index" />" class="border" style="width:90px;height:22px;border:1px solid lightblue;"
									onchange="updateSale(<s:property value="cusId" />,<s:property value="#num.index" />)">
								<option value="0"></option>
								<c:forEach var="saleList" items="${requestScope.saleList}">
									<option value="${saleList.CId}">${saleList.CName}</option>
								</c:forEach>
							</select>
							<input id="_saleId<s:property value="#num.index" />" type="hidden" value="${saleId}" />
						</td>
						<td>
							<input id="remarks2_<s:property value="#num.index" />"
									type="text" value="${remarks2}" class="border field" style="width:354px;"
									onchange="updateRemarks2(<s:property value="cusId" />,<s:property value="#num.index" />)"/>
						</td>
						<td>
							<s:if test="flag==0">
								<a href="javascript:void(0);" 
									onclick="setFlag(<s:property value="cusId" />, 1);">加急</a>
							</s:if>
							<s:if test="flag==1">
								<a href="javascript:void(0);" 
									onclick="setFlag(<s:property value="cusId" />, 0);">取消加急</a>
							</s:if>
						</td>
					</tr>
	            </s:iterator>
	            <tr>
					<td colspan="2"></td>
					<td style="text-align:right;padding-right:5px;font-weight:bold;">
						<fmt:formatNumber type="number" value="${sumMoney}" pattern="###,##0.00" />
					</td>
					<td style="text-align:right;padding-right:5px;font-weight:bold;">
						<fmt:formatNumber type="number" value="${sumMoney2}" pattern="###,##0.00" />
					</td>
					<td colspan="6"></td>
				</tr>
	       </table>
	    </div>
	    <div class="clear"></div>
       <div class="right_4">
           	<div style="float:left; margin-left:5px;">
           		当前第${pageBean.pageNow}页/共${pageBean.pageCount}页
           	</div>
           	<div style="float:left; margin-left:140px;">
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

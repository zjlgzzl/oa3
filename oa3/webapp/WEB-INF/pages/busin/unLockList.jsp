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
	<script type="text/javascript" src="${ctx}/res/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">
		//初始化
		$(document).ready(function(){
			//后台校验错误信息
			var errInfo = "${requestScope.errInfo}";
			if (errInfo != null && errInfo.length > 0){
				alert(errInfo);
			}
		});
		//分页控件
		function forward(flag){
			document.getElementById("queryForm").action = "${ctx}/busin/UnlockList.action";
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
		function conUnlock(){
			if (confirm("确认解除锁定？")){
				return true;
			}else{
				return false;
			}
		}
	</script>
  </head>
  <body>
  	<div class="right">
  		<div class="right_1">
			<form id="queryForm" action="${ctx}/busin/UnlockList.action" method="post">
					<table style="width:310px; margin-left:0;">
						<tr>
							<td style="width:70px;">单据编号</td>
							<td style="width:120px;text-align:left; ">
								<input id="billNo" name="query.billNo" value="${requestScope.query.billNo}" 
										type="text" class="field border" style="width:120px;" />
								<input name="query.cusId" type="hidden"/>
								<input name="startDate" type="hidden"/>
								<input name="endDate" type="hidden"/>
								<input name="conNum" type="hidden"/>
								<input name="billNo" type="hidden"/>
								<input name="query.businState" type="hidden"/>
								<input name="query.orderNum" type="hidden"/>
								<input name="query.lockFlag" type="hidden"/>
		  					</td>
		  					<td>
								<input type="submit" value="查询" class="btn_img"
										style="margin-left:10px;"
										onclick="return forward(-1);"/>
								<input type="hidden" id="pageNow" name="pageNow" value="${pageNow}"/>
							</td>
						</tr>
					</table>
				
			</form>
		</div>
        <div class="right_3">
	        <table id="table1" class="table" style="width:1010px;">
	           	<tr>
	               	<th style="width:40px;">序号</th>
	               	<th style="width:120px;">单据日期</th>
	               	<th style="width:160px;">单据编号</th>
	               	<th style="width:120px;">发货人</th>
	               	<th style="width:120px;">收货人</th>
	               	<th style="width:120px;">客户名称</th>
	               	<th style="width:100px;">制单人</th>
	               	<th style="width:120px;">状态</th>
	                <th style="width:100px;">操作</th>
	            </tr>
	            <s:iterator value="#request.businList" var="businList" status="num">
	            <tr>
					<td>
						<s:property value="(#num.index+1)+(pageNow-1)*pageSize" />
					</td>
					<td>
						<s:date name="businDate" format="dd-MM-yyyy"/>
					</td>
					<td>
						<s:property value="billNo" />
					</td>
					<td>
						<s:property value="send" />
					</td>
					<td>
						<s:property value="receive" />
					</td>
					<td>
						<s:property value="cusName" />
					</td>
					<td>
						<s:property value="empName" />
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
						<a href="${ctx}/busin/UnLockBusin.action?businId=<s:property value="businId"/>">解锁</a>
					</td>
				</tr>
	            </s:iterator>
	       </table>
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
    </div>
  </body>
</html>

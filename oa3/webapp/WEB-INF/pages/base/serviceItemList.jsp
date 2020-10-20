<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>服务项目维护</title>
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
		}
		//删除确认
		function confirmDel(){
			if (confirm("删除的数据将无法恢复，是否确认删除？")){
				return true;
			}else{
				return false;
			}
		}
		//禁用确认
		function confirmInvalid(){
			if (confirm("禁用的数据将无法恢复，是否确认删除？")){
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
			<form id="queryForm" action="${ctx}/sitem/ServiceItemList.action" method="post">
				<table style="width:600px; margin-left:0;">
					<tr>
	  					<td style="width:90px;">服务项目名称</td>
						<td style="width:120px;text-align:left; ">
							<input id="queryName" name="query.CName" value="${requestScope.query.CName}" 
									type="text" class="field border" style="width:120px;" />
	  					</td>
	  					<td>
							<input type="submit" value="查询" style="width:65px; height:25px; float:left; margin-left:10px;"
									onclick="return forward(-1);"/>
							<input type="hidden" id="pageNow" name="pageNow" value="${pageNow}"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
       	<div class="right_2">
       		<a href="${ctx}/sitem/AddServiceItem.action" style="margin-left:780px;">添加</a>
        </div>
        <div class="right_3">
	        <table id="table1" class="table">
	           	<tr>
	               	<th style="width:40px;">序号</th>
	               	<th style="width:150px;">服务类型名称</th>
	               	<th style="width:150px;">服务项目名称</th>
	               	<th style="width:100px;">状态</th>
	               	<th style="width:150px;">备注 </th>
	                <th style="width:210px;">操作</th>
	            </tr>
	            <s:iterator value="#request.sitemList" var="sitemList" status="num">
	            <tr>
					<td><s:property value="(#num.index+1)+(pageNow-1)*pageSize" /></td>
					<td><s:property value="TServiceType.CName"/></td>
					<td><s:property value="CName"/></td>
					<td>
						<s:if test="CState==1">
							正常
						</s:if>
						<s:else>
							<span style="color:red;">禁用</span>
						</s:else>
					</td>
					<td><s:property value="CRemarks"/></td>
					<td>
						<s:if test="(CState == 1) && (CCreateUserid==#session.userID)">
							<a href="${ctx}/sitem/EditServiceItem.action?sitemId=<s:property value="CId"/>">修改</a>
							<a href="${ctx}/sitem/DeleteServiceItem.action?sitemId=<s:property value="CId"/>"
								onclick="return confirmDel();">删除</a>
							<!--  
							<a href="${ctx}/sitem/InvalidServiceItem.action?sitemId=<s:property value="CId"/>"
								onclick="return confirmInvalid();">禁用</a>
							-->
						</s:if>
						<a href="${ctx}/sitem/ViewServiceItem.action?sitemId=<s:property value="CId"/>">查看</a>
					</td>
				</tr>
	            </s:iterator>
	       </table>
	    </div>
       <div class="right_4">
           	<div style="float:left; margin-left:5px;">
           		当前第${pageBean.pageNow}页/共${pageBean.pageCount}页
           	</div>
           	<div style="float:left; margin-left:580px;">
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
			</div>
        </div>
    </div>
  </body>
</html>
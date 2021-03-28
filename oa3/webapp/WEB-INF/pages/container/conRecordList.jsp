<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>货柜进出登记</title>
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
			document.getElementById("queryForm").action="${ctx}/container/ConRecordList.action";
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
		//添加
		function add(){
			document.getElementById("queryForm").action = "${ctx}/container/AddConRecord.action";
			document.getElementById("queryForm").submit();
		}
		//修改
		function edit(cid){
			document.getElementById("cid").value = cid;
			document.getElementById("queryForm").action = "${ctx}/container/EditConRecord.action";
			document.getElementById("queryForm").submit();
		}
		//查看
		function view(cid){
			document.getElementById("cid").value = cid;
			document.getElementById("queryForm").action = "${ctx}/container/ViewConRecord.action";
			document.getElementById("queryForm").submit();
		}
		//删除确认
		function confirmDel(cid){
			if (confirm("删除的数据将无法恢复，是否确认删除？")){
				document.getElementById("cid").value = cid;
				document.getElementById("queryForm").action = "${ctx}/container/DeleteConRecord.action";
				document.getElementById("queryForm").submit();
			}
			return false;
		}
		//归档
		function archive(cid,cstate){
			if (confirm("确认归档？")){
				document.getElementById("cid").value = cid;
				document.getElementById("cstate").value = cstate;
				document.getElementById("queryForm").action = "${ctx}/container/ArchiveRecord.action";
				document.getElementById("queryForm").submit();
			}
		}
		//导出
		function exp(){
			document.getElementById("cstate").value = 1;
			document.getElementById("queryForm").action = "${ctx}/container/ExpRecord.action";
			document.getElementById("queryForm").submit();
		}
	</script>
  </head>
  <body>
  	<div class="right">
  		<div class="right_1">
			<form id="queryForm" action="${ctx}/container/ConRecordList.action" method="post">
					<table style="width:950px; margin-left:0;">
						<tr>
							<td style="width:80px;">开始日期</td>
							<td style="text-align:left; width:100px;">
								<input id="startDate" name="startDate" value="${requestScope.startDate}"
										type="text" class="field border" style="width:100px" onclick="WdatePicker()" readonly="readonly"/>
		  					</td>
		  					<td style="width:80px;">结束日期</td>
							<td style="text-align:left; width:100px;">
								<input id="endDate" name="endDate" value="${requestScope.endDate}" 
										type="text" class="field border" style="width:100px" onclick="WdatePicker()" readonly="readonly"/>
		  					</td>
		  					<td style="width:70px;">客户名称</td>
							<td style="width:130px;text-align:left; ">
								<input id="cusname" name="query.cusname" value="${requestScope.query.cusname}" 
										type="text" class="field border" style="width:130px;" />
		  					</td>
							<td style="width:70px;">货柜编号</td>
							<td style="width:100px;text-align:left; ">
								<input id="infoCode" name="query.infoCode" value="${requestScope.query.infoCode}" 
										type="text" class="field border" style="width:100px;" />
		  					</td>
		  					<td style="width:280px;">
								<input type="submit" value="查询" class="btn_img" style="margin-left:10px;"
										onclick="return forward(-1);"/>
								<input type="button" value="添加" class="btn_img" style="margin-left:10px;"
										onclick="add();"/>
								<input type="button" value="导出" class="btn_img" style="margin-left:10px;"
										onclick="exp();"/>
								<input type="hidden" id="pageNow" name="pageNow" value="${pageNow}"/>
								<input type="hidden" id="cid" name="id" value="${requestScope.id}"/>
								<input type="hidden" id="cstate" name="cstate" value="${requestScope.cstate}"/>
							</td>
						</tr>
					</table>
				
			</form>
		</div>
        <div class="right_3">
	        <table id="table1" class="table" style="width:1100px;">
	           	<tr>
	               	<th style="width:40px;">序号</th>
	               	<th style="width:100px;">日期</th>
	               	<th style="width:150px;">客户名称</th>
	               	<th style="width:80px;">货柜编号</th>
	               	<th style="width:80px;">货柜型号</th>
	               	<th style="width:100px;">操作类型</th>
	               	<th style="width:60px;">金额</th>
	               	<th style="width:150px;">Where</th>
	               	<th style="width:100px;">经手人</th>
	                <th style="width:240px;">操作</th>
	            </tr>
	            <s:iterator value="#request.conRecordList" var="conRecordList" status="num">
	            <tr>
					<td><s:property value="(#num.index+1)+(pageNow-1)*pageSize" /></td>
					<td><s:date name="cdate" format="dd-MM-yyyy"/></td>
					<td><s:property value="cusname"/></td>
					<td><s:property value="infoCode"/></td>
					<td><s:property value="modelCode"/></td>
					<td><s:property value="operName"/></td>
					<td><s:property value="cmoney"/></td>
					<td><s:property value="conTo"/></td>
					<td><s:property value="empName"/></td>
					<td>
						<s:if test="(userId==#session.userID)">
							<a href="javascript:void(0);" onclick="edit(<s:property value="recordId"/>);">修改</a>
							<a href="javascript:void(0);" onclick="confirmDel(<s:property value="recordId"/>);">删除</a>
						</s:if>
						<a href="javascript:void(0);" onclick="archive(<s:property value="recordId"/>,2);">归档(未完成)</a>
						<a href="javascript:void(0);" onclick="archive(<s:property value="recordId"/>,3);">归档(已完成)</a>
						<a href="javascript:void(0);" onclick="view(<s:property value="recordId"/>);">查看</a>
					</td>
				</tr>
	            </s:iterator>
	       </table>
	    </div>
       <div class="right_4">
           	<div style="float:left; margin-left:5px;">
           		当前第${pageBean.pageNow}页/共${pageBean.pageCount}页
           	</div>
           	<div style="float:left; margin-left:530px;">
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

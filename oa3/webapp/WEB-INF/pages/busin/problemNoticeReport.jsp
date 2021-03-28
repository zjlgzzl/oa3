<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>问题提醒</title>
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
			var eid = "${requestScope.askQuery.id.eid}";
			var select = document.getElementById("eid");
			for(var i=0;i<select.options.length;i++){
				if(select.options[i].value == eid){
					select.options[i].selected = true;
					break;
				}
			}
			var cusid = "${requestScope.askQuery.id.cusid}";
			var select = document.getElementById("cusid");
			for(var i=0;i<select.options.length;i++){
				if(select.options[i].value == cusid){
					select.options[i].selected = true;
					break;
				}
			}
			$('#cusid').comboSelect();
		});
		//分页控件
		function forward(flag){
			document.getElementById("queryForm").action = "${ctx}/busin/ProblemNoticeReport.action";
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
		function updateReply(businId, rowIndex){
			var reply = document.getElementById("reply"+rowIndex).value;
			var url="${ctx}/busin/UpdateAnswer.action?archiveRemarks="+encodeURI(reply)+"&businId="+businId;
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
		function stopNotice(businId){
			document.getElementById("businId").value = businId;
			document.getElementById("queryForm").submit();
		}
	</script>
  </head>
  <body>
  <form id="queryForm" action="${ctx}/busin/StopProblemNotice.action" method="post">
	  	<div class="right">
	  		<div class="right_1">
				<table style="width:950px; margin-left:0;">
					<tr>
						<td style="width:70px;">业务单</td>
						<td style="text-align:left; width:120px;">
							<input name="askQuery.id.ccode" value="${requestScope.askQuery.id.ccode}"
									class="field border" style="width:120px;"/>
	  					</td>
	  					<td style="width:70px;">客户名称</td>
						<td style="width:340px;text-align:left; ">
							<select id="cusid" name="askQuery.id.cusid" class="border" style="width:340px;border:1px solid lightblue;">
								<option value="-1">--全部--</option>
								<c:forEach var="cusList" items="${requestScope.cusList}">
									<option value="${cusList.CId}">${cusList.CAddr}</option>
								</c:forEach>
							</select>
	  					</td>
	  					<td style="width:70px;">开票人</td>
						<td style="width:150px;text-align:left; ">
							<select id="eid" name="askQuery.id.eid" class="border" style="width:150px;border:1px solid lightblue;">
								<option value="-1">--全部--</option>
								<c:forEach var="empList" items="${requestScope.empList}">
									<option value="${empList.empId}">${empList.empName}</option>
								</c:forEach>
							</select>
	  					</td>
	  					<td style="text-align:left;">
							<input type="submit" value="查询" class="btn_img" style="margin-left:10px;"
										onclick="return forward(-1);"/>
							<input type="hidden" id="pageNow" name="pageNow" value="${pageNow}"/>
							<input type="hidden" id="businId" name="businId" />
						</td>
					</tr>
				</table>
			</div>
	       	<div class="right_2">
	        </div>
	        <div class="right_3">
		        <table id="table1" class="table" style="width:1140px;">
		           	<tr>
		               	<th style="width:50px;">序号</th>
		               	<th style="width:160px;">FILE</th>
		               	<th style="width:280px;">客户</th>
		               	<th style="width:100px;">开票人</th>
		               	<th style="width:100px;">操作</th>
		               	<th style="width:200px;">问题</th>
		               	<th style="width:100px;">发问人</th>
		               	<th style="width:100px;">回答</th>
		               	<th style="width:200px;">回答人</th>
		               	<th style="width:60px;"></th>
		            </tr>
		            <s:iterator value="#request.businList" var="businList" status="num">
			            <tr>
			            	<td><s:property value="(#num.index+1)+(pageNow-1)*pageSize" /></td>
							<td><s:property value="id.ccode"/></td>
							<td><s:property value="id.cusName"/></td>
							<td><s:property value="id.ename"/></td>
							<td><s:property value="id.createName"/></td>
							<td><s:property value="id.askRemarks"/></td>
							<td><s:property value="id.askName"/></td>
							<td><s:property value="id.answerName"/></td>
							<td>
								<input id="reply<s:property value="#num.index" />"
											type="text" value="${id.answerRemarks}" class="border field" style="width:186px;"
											onchange="updateReply(<s:property value="id.businId" />,<s:property value="#num.index" />)"/>
							</td>
							<td>
								<s:if test="#session.fun315==1">
									<a href="javascript:void(0);" 
											onclick="stopNotice(<s:property value="id.businId"/>);">取消提醒</a>
								</s:if>
							</td>
						</tr>
		            </s:iterator>
		       </table>
		    </div>
		    <div class="clear"></div>
	       <div class="right_4">
	           	<div style="float:left; margin-left:5px;">
	           		当前第${pageBean.pageNow}页/共${pageBean.pageCount}页
	           	</div>
	           	<div style="float:left; margin-left:260px;">
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

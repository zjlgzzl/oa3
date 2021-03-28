<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>员工修改</title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<link type="text/css" rel="stylesheet"  href="${ctx}/res/css/styles.css"/>
	<script type="text/javascript" src="${ctx}/res/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">

		//后台提示
		$(document).ready(function(){
		
			//权限设置
			var role = document.getElementsByName("role");
			var roleList = "${requestScope.roleList}";
			var roles = roleList.substring(1, roleList.length - 1).split(",");
			if (roles != null){
				for (var i=0; i<role.length; i++){
					for (var j=0; j<roles.length; j++){
						//去除前面空格
						while ((roles[j].indexOf(" ")==0) && (roles[j].length>1)){
							roles[j]=roles[j].substring(1,roles[j].length);
						}
						//去除后面空格
						while ((roles[j].lastIndexOf(" ")==roles[j].length-1)&&(roles[j].length>1)){
							roles[j]=roles[j].substring(0,roles[j].length-1);
						}
						if (role[i].value == roles[j]){
							role[i].checked = true;
							break;
						}
					}
				}
			}
			
			//所属部门
			var deptid = "${requestScope.emp.TDept.CId}";
			if (deptid != null && deptid != ""){
				var select = document.getElementById("deptid");
				for(var i=0;i<select.options.length;i++){
					if(select.options[i].value == deptid){
						select.options[i].selected = true;
						break;
					}
				}
			}
			
			//性别
			var gender = "${requestScope.emp.CGender}";
			if (gender != null && gender != ""){
				var select = document.getElementById("CGender");
				for(var i=0;i<select.options.length;i++){
					if(select.options[i].value == gender){
						select.options[i].selected = true;
						break;
					}
				}
			}
			
			//是否部门经理
			var manageFlag = "${requestScope.emp.CManageflag}";
			if (manageFlag == 0){
				document.getElementsByName("emp.CManageflag")[0].checked = true;
			}else{
				document.getElementsByName("emp.CManageflag")[1].checked = true;
			}
			
			//后台校验错误信息
			var errInfo = "${requestScope.errInfo}";
			if (errInfo != null && errInfo.length > 0){
				alert(errInfo);
			}
			
		});
		
		//返回
		function returnList(){
			document.getElementById("editForm").action = "${ctx}/emp/EmpList.action";
			document.getElementById("editForm").submit();
		}
		
		//保存之前的数据校验
		function check(){
		
			var deptid = document.getElementById("deptid").value;
			if ($.trim(deptid) == "0"){
				alert("所属部门不能为空");
				document.getElementById("deptid").focus();
				return false;
			}
			
			var CCode = document.getElementById("CCode").value;
			if ($.trim(CCode) == ""){
				alert("员工编号不能为空");
				document.getElementById("CCode").focus();
				return false;
			}
			
			var CName = document.getElementById("CName").value;
			if ($.trim(CName) == ""){
				alert("员工姓名不能为空");
				document.getElementById("CName").focus();
				return false;
			}
			
			var CUsername = document.getElementById("CUsername").value;
			if ($.trim(CUsername) == ""){
				alert("登陆账号不能为空");
				document.getElementById("CUsername").focus();
				return false;
			}
			
			//处理权限
			var role = document.getElementsByName("role");
			var check = false;
			for (var i=0; i<role.length; i++){
				if (role[i].value.length == 3 && role[i].checked){
					check = true;
					break;
				}
			}
			if (!check){
				alert("请选择员工权限");
				return false;
			}
			
			return true;
		}
		
		//选择或取消一级权限
		function topRole(flag){
			var role = document.getElementsByName("role");
			if (document.getElementById(flag).checked){
				for (var i=0; i<role.length; i++){
					if (Number(role[i].value.toString().substr(0,1)) == flag){
						role[i].checked = true;
					}
				}
			}else{
				for (var i=0; i<role.length; i++){
					if (Number(role[i].value.toString().substr(0,1)) == flag){
						role[i].checked = false;
					}
				}
			}
		}
		
		//选择或取消二级权限
		function subRole(flag){
			var role = document.getElementsByName("role");
			if (document.getElementById(flag).checked){
				for (var i=0; i<role.length; i++){
					if (role[i].value == flag.toString().substr(0,1)){
						role[i].checked = true;
						return;
					}
				}
			}else{
				for (var i=0; i<role.length; i++){
					if (role[i].value.length == 3 && 
						role[i].value.toString().substr(0,1) == flag.toString().substr(0, 1) && 
						role[i].checked){
						return;
					}
				}
				document.getElementById(flag.toString().substr(0, 1)).checked = false;
			}
		}
	</script>
  </head>
  <body>
  	<div id="mainContent">
  		<div class="right" style="margin-top:30px;">
  			<form id="editForm" action="${ctx}/emp/UpdateEmp.action" method="post">
	  			<div style="color:blue; font-weight:bold;">员工基本信息</div>
	  			<table class="editTab" style="margin-top:15px; width:680px;">
	  				<tr>
	  					<td class="title" style="width:100px;">
							所属部门
						</td>
						<td class="content" style="width:120px;">
							<select id="deptid" name="emp.TDept.CId" class="border" style="width:120px;" disabled="disabled">
				           		<option value="0">--请选择--</option>
								<c:forEach var="deptList" items="${requestScope.deptList}">
									<option value="${deptList.CId}">${deptList.CName}</option>
								</c:forEach>
							</select>
							<input id="CId" name="emp.CId" value="${requestScope.emp.CId}" type="hidden" />
							<input id="CCreateUserid" name="emp.CCreateUserid" value="${requestScope.emp.CCreateUserid}" type="hidden" />
				           	<input id="CCreateDate" name="emp.CCreateDate" value="${requestScope.emp.CCreateDate}" type="hidden" />
				           	<input id="CState" name="emp.CState" value="${requestScope.emp.CState}" type="hidden" />
				           	<input name="query.empCode" value="${requestScope.query.empCode}" type="hidden" />
			           		<input name="query.empName" value="${requestScope.query.empName}" type="hidden" />
			           		<input type="hidden" name="pageNow" value="${pageNow}"/>
						</td>
						<td class="title" style="width:100px;">
							员工编号
						</td>
						<td class="content" style="width:120px;">
							<input id="CCode" name="emp.CCode" value="${requestScope.emp.CCode}"
				           			type="text" class="field border" disabled="disabled" />
						</td>
						<td class="title" style="width:120px;">
							员工姓名
						</td>
						<td class="content" style="width:120px;">
							<input id="CName" name="emp.CName" value="${requestScope.emp.CName}"
				           			type="text" class="field border" disabled="disabled" />
						</td>
	  				</tr>
	  				<tr style="margin-top:15px;">
	  					<td class="title" style="width:100px;">
	  						<label>性别</label>
	  					</td>
	  					<td class="content" style="width:120px;">
	  						<select id="CGender" name="emp.CGender" class="border" style="width:120px;" disabled="disabled">
				           		<option value="">--请选择--</option>
								<option value="男">男</option>
								<option value="女">女</option>
								<option value="未知">未知</option>
							</select>
	  					</td>
	  					<td class="title" style="width:100px;">
	  						<label>联系电话</label>
	  					</td>
	  					<td class="content" style="width:120px;">
	  						<input id="CPhone" name="emp.CPhone" value="${requestScope.emp.CPhone}"
				           			type="text" class="field border" disabled="disabled" />
	  					</td>
	  					<td class="title" style="width:120px;">
	  						<label>是否部门经理</label>
	  					</td>
	  					<td class="content" style="width:120px;">
	  						<input id="no" type="radio" name="emp.CManageflag" value="0"
	  								style="margin-left:10px; margin-right:10px;" disabled="disabled" />否
				           	<input id="yes" type="radio" name="emp.CManageflag" value="1" 
				           			style="margin-left:30px; margin-right:10px;" disabled="disabled" />是
	  					</td>
	  				</tr>
	  				<tr style="margin-top:15px;">
	  					<td class="title" style="width:100px;">
	  						<label>备注</label>
	  					</td>
	  					<td class="content" colspan="5" style="width:600px;">
	  						<textarea id="remarks" name="emp.CRemarks" 
	  									rows="2" cols="10" style="width:600px;margin-top:5px;" disabled="disabled">${requestScope.emp.CRemarks}</textarea>
	  					</td>
	  				</tr>
	  			</table>
	  			
	  			<div style="color:blue; font-weight:bold; margin-top:20px;">员工登陆信息</div>
	  			<table class="editTab" style="margin-top:15px; width:610px;">
	  				<tr>
	  					<td class="title" style="width:68px;">
	  						<label>登陆账号</label>
	  					</td>
	  					<td class="content" style="width:120px;">
	  						<input id="CUsername" name="user.CUsername" value="${requestScope.user.CUsername}"
				           			type="text" class="field border" disabled="disabled" />
				           	<input id="CId" name="user.CId" value="${requestScope.user.CId}" type="hidden" />
				           	<input id="CPassword" name="user.CPassword" value="${requestScope.user.CPassword}" type="hidden" />
				           	<input id="CEmpid" name="user.CEmpid" value="${requestScope.user.CEmpid}" type="hidden" />
				           	<input id="CCreateUserid" name="user.CCreateUserid" value="${requestScope.user.CCreateUserid}" type="hidden" />
				           	<input id="CCreateDate" name="user.CCreateDate" value="${requestScope.user.CCreateDate}" type="hidden" />
				           	<input id="CState" name="user.CState" value="${requestScope.user.CState}" type="hidden" />
	  					</td>
	  					<td class="content" colspan="4" style="width:420px; padding-left:20px; color:red;">
	  						员工登陆系统初始密码123456，登陆系统后可修改密码。
	  					</td>
	  				</tr>
	  			</table>
	  			
	  			<div style="color:blue; font-weight:bold; margin-top:20px;">员工权限设置</div>
	  			<table class="editTab" style="margin-top:15px; margin-left:20px; width:1560px;">
	  				<tr>
	  					<td style="width:40px;height:1px;"></td>
	  					<td style="width:40px;height:1px;"></td>
	  					<td style="width:150px;height:1px;"></td>
	  					<td style="width:40px;height:1px;"></td>
	  					<td style="width:150px;height:1px;"></td>
	  					<td style="width:40px;height:1px;"></td>
	  					<td style="width:150px;height:1px;"></td>
	  					<td style="width:40px;height:1px;"></td>
	  					<td style="width:150px;height:1px;"></td>
	  					<td style="width:40px;height:1px;"></td>
	  					<td style="width:150px;height:1px;"></td>
	  					<td style="width:40px;height:1px;"></td>
	  					<td style="width:150px;height:1px;"></td>
	  					<td style="width:40px;height:1px;"></td>
	  					<td style="width:150px;height:1px;"></td>
	  					<td style="width:40px;height:1px;"></td>
	  					<td style="width:150px;height:1px;"></td>
	  				</tr>
	  				<s:iterator value="#session.topRoleList" var="topRoleList" status="num">
						<s:if test="#session.topRoleList[#num.index].clevel == 1">
							<tr>
								<td style="width:40px; text-align:center;">
									<input type="checkbox" id="<s:property value="funid" />" 
											name="role" value="<s:property value="funid" />" 
											onclick="topRole(<s:property value="funid" />)" disabled="disabled"/>
								</td>
								<td colspan="16" style="width:850px; text-align:left; padding-top:3px;">
									<div id="div<s:property value="funid"/>">
										${sessionScope.topRoleList[num.index].funname}
									</div>
								</td>
							</tr>
							<tr>
								<td style="width:40px;"></td>
								<s:iterator value="#session.subRoleList" var="subRoleList" status="num2">
									<s:if test="(#session.subRoleList[#num2.index].clevel == 2) && (#session.subRoleList[#num2.index].uppid == #session.topRoleList[#num.index].funid) &&
												(#session.subRoleList[#num2.index].funid != 215)">
										<td style="width:40px; text-align:center;">
											<input type="checkbox" id="<s:property value="funid"/>" 
													name="role" value="<s:property value="funid"/>" 
													onclick="subRole(<s:property value="funid"/>)" disabled="disabled"/>
										</td>
										<td style="width:150px; text-align:left;">
											<div id="div<s:property value="funid"/>">
												${sessionScope.subRoleList[num2.index].funname}
											</div>
										</td>
									</s:if>
								</s:iterator>
							</tr>
						</s:if>
	  				</s:iterator>
	  			</table>
	  			<div style="margin-bottom:20px;">
				    <input type="button" value="返回" class="return" style="width:60px;height:20px;margin-left:660px;" onclick="returnList();"/>
	  			</div>
	  		</form>
	    </div>
  	</div>
  </body>
</html>

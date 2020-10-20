<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>支出类型添加</title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<script type="text/javascript" src="${ctx}/res/js/jquery-1.9.1.min.js"></script>
	
	<style type="text/css">
		*{
			margin:0;
			padding:0;
		}
		body,input,select{
			/*font:14px "微软雅黑", Verdana,"Lucida Grande",Lucida,Arial,Helvetica,"宋体", sans-serif;*/
			font:12px Verdana,"Lucida Grande",Lucida,Arial,Helvetica,"宋体", sans-serif;
		}
		.border,textarea{
			border-top:1px solid lightblue;
			border-left:1px solid lightblue;
			border-bottom:1px solid lightblue;
			border-right:1px solid lightblue;
		}
		/*控件得到焦点时的效果*/
		.field:focus, #validCode:focus, textarea:focus{
			background-color:#ffc;
		}
		.field{
			line-height:20px;/*行高*/
			width: 182px; 
			height: 20px;
		}
		.right .edit input, .right .edit textarea, .right .edit select{
			margin-top:10px;
			margin-left:20px;
			width:200px;
		}
		.save{
			width:60px;
			height:20px;
			margin-top:30px;
			margin-left:120px;
		}
		.return{
			width:60px;
			height:20px;
			margin-top:30px;
			margin-left:20px;
		}
    </style>

	<script type="text/javascript">

		//后台提示
		$(document).ready(function(){
			
			//勾选用户
			if (document.getElementById("userTab") != null){
				var userIds = document.getElementsByName("userIds");
				var userIdList = "${requestScope.userIdList}";
				var userIdList = userIdList.substring(1, userIdList.length - 1).split(",");
				if (userIdList != null && userIds != null){
					for (var i=0; i<userIds.length; i++){
						for (var j=0; j<userIdList.length; j++){
							//去除前面空格
							while ((userIdList[j].indexOf(" ")==0) && (userIdList[j].length>1)){
								userIdList[j]=userIdList[j].substring(1,userIdList[j].length);
							}
							//去除后面空格
							while ((userIdList[j].lastIndexOf(" ")==userIdList[j].length-1)&&(userIdList[j].length>1)){
								userIdList[j]=userIdList[j].substring(0,userIdList[j].length-1);
							}
							if (userIds[i].value == userIdList[j]){
								userIds[i].checked = true;
								break;
							}
						}
					}
				}
			}
			
			//后台校验错误信息
			var errInfo = "${requestScope.errInfo}";
			if (errInfo != null && errInfo.length > 0){
				alert(errInfo);
			}
		
			//设置焦点
			document.getElementById("CName").focus();
			
		});
		
		//返回
		function returnList(){
			var CType = document.getElementById("CType").value;
			if (CType == 1){
				document.getElementById("editForm").action = "${ctx}/financeType/FinanceTypeInList.action";
			}else{
				document.getElementById("editForm").action = "${ctx}/financeType/FinanceTypeList.action";
			}
			document.getElementById("editForm").submit();
		}
		
		//保存之前的数据校验
		function check(){
			
			var CType = document.getElementById("CType").value;
			
			var CName = document.getElementById("CName").value;
			if ($.trim(CName) == ""){
				if (CType = 1){
					alert("收入类型名称不能为空");
				}else{
					alert("支出类型名称不能为空");
				}
				document.getElementById("CName").focus();
				return;
			}
			
			if (CType == 1){
				document.getElementById("editForm").action = "${ctx}/financeType/SaveFinanceInType.action";
			}else{
				document.getElementById("editForm").action = "${ctx}/financeType/SaveFinanceType.action";
			}
			document.getElementById("editForm").submit();

		}
	</script>
  </head>
  <body>
  	<div>
  		<div style="background:#FFF;margin-top:50px;margin-left:20px;width:1000px;">
 			<form id="editForm" action="${ctx}/financeType/SaveFinanceType.action" method="post">
  				<table style="width:720px;">
  					<tr style="height:50px;">
  						<td style="width:120px;text-align:center;">
			           		<s:if test="#request.financeType.CType == 1">
			           			收入类型名称
			           		</s:if>
			           		<s:else>
			           			支出类型名称
			           		</s:else>
  						</td>
  						<td style="width:600px;text-align:left;">
  							<input id="CName" name="financeType.CName" value="${requestScope.financeType.CName}"
				           			type="text" class="field border" disabled="disabled"/>
				           		<input name="financeType.CId" value="${requestScope.financeType.CId}" type="hidden" />
					           	<input name="financeType.CState" value="${requestScope.financeType.CState}" type="hidden" />
					           	<input name="financeType.CCreateUserid" value="${requestScope.financeType.CCreateUserid}" type="hidden" />
					           	<input id="CType" name="financeType.CType" value="${requestScope.financeType.CType}" type="hidden"/>
					           	<input type="hidden" name="pageNow" value="${pageNow}"/>
					           	<input type="hidden" name="query.CName" value="${requestScope.query.CName}"/>
					           	<input type="hidden" name="query.CType" value="${requestScope.query.CType}"/>
  						</td>
  					</tr>
  					<tr style="height:50px;">
  						<td style="width:120px;text-align:center;">
  							备注
  						</td>
  						<td style="width:600px;text-align:left;">
  							<textarea id="remarks" name="financeType.CRemarks" rows="5" cols="10" style="width:180px;" disabled="disabled">${requestScope.financeType.CRemarks}</textarea>
  						</td>
  					</tr>
  				</table>
		        
		        	<table id="userTab" style="margin-left:2px;width:980px;margin-top:20px;">
		        		<s:iterator value="#request.empList" var="empList" status="num">
		        			<s:if test="#num.index%8 == 0">
		        				<tr>
		        			</s:if>
		        				<td style="width:100px;text-align:left;">
		        					<input type="checkbox" id="userid<s:property value="userid" />" 
					        				name="userIds"
					        				value="<s:property value="userid" />" 
					        				onclick="return false;" />
					        		<label for="userid<s:property value="userid" />" style="margin-left:10px;">
					        			${requestScope.empList[num.index].empName}
					        		</label>
		        				</td>
			        		<s:if test="#num.index%8 == 7 || #num.last">
		        				</tr>
		        			</s:if>
			        	</s:iterator>
			        </table>
		         
		        <div style="margin:10px;">
		           	<input type="button" value="返回" class="return" style="width:60px;height:20px" onclick="returnList();"/>
		        </div>
	        </form>
        </div>
  	</div>
  </body>
</html>





<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<title>综合办公管理系统</title>
  	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
	<!-- 引入jquery easyui -->
	<link type="text/css" rel="stylesheet" href="${ctx}/res/jquery-easyui-1.3.5/themes/default/easyui.css"></link>
	<link type="text/css" rel="stylesheet" href="${ctx}/res/jquery-easyui-1.3.5/themes/icon.css"></link>
	<script type="text/javascript" src="${ctx}/res/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/res/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
	<script	type="text/javascript" src="${ctx}/res/jquery-easyui-1.3.5/easyui-lang-zh_CN.js"></script>
	<!-- 引入ztree -->
	<link rel="stylesheet" type="text/css" href="${ctx}/res/zTree_3.5.15/css/demo.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/res/zTree_3.5.15/css/zTreeStyle/zTreeStyle.css" />
	<script type="text/javascript" src="${ctx}/res/zTree_3.5.15/js/jquery.ztree.core-3.5.js"></script>
	<!-- 引入项目css -->
	<link type="text/css" rel="stylesheet"  href="${ctx}/res/css/styles.css"/>
	<script type="text/javascript">
	
		//ztree控件
		var treeNodes;
		
		//ztree初始化设置
		var setting = {
			data: {
				simpleData: {
					enable: true
				},
			},
			callback: {
				onClick: onClick
			}
		};
		
		//初始化ztree菜单
		$(function(){
			var proUrl = "${ctx}";
			$.ajax({
				type: "Post",
				dataType: "json",
				url: "${ctx}/GetFuncTree.action",
				error: function () {
					alert("该用户没有操作权限, 请联系管理员!");
				},
				success:function(data){
					if(data!=null && data!=""){
						treeNodes = data;
						$.fn.zTree.init($("#menu_tree"), setting, treeNodes);
					}
					initSelf();
				}
			});
		});
		

		function initSelf(){
		
			//首页
			$("#tabs").tabs('add',{
				title:'首页',
				closable:false,
				content:'<iframe id="-2" style="width:100%;height:100%;" scrolling="auto" frameborder="0" src="' + 
							 '${ctx}/busin/HomePage.action"' + 
						'</iframe>'
			});
		
			var fun606 = "${sessionScope.fun606}";
			if (fun606 == 1){
				$("#tabs").tabs('add',{
					title:'成本统计cost',
					closable:true,
					content:'<iframe id="-2" style="width:100%;height:100%;" scrolling="auto" frameborder="0" src="' + 
								 '${ctx}/report/CostReport.action"' + 
							'</iframe>'
				});
			}
			/*
			var fun314 = "${sessionScope.fun314}";
			if (fun314 == 1){
				$("#tabs").tabs('add',{
					title:'开票提醒',
					closable:true,
					content:'<iframe id="-2" style="width:100%;height:100%;" scrolling="auto" frameborder="0" src="' + 
								 '${ctx}/busin/InvNoticeReport.action"' + 
							'</iframe>'
				});
			}
			*/
			$("#tabs").tabs("select", '首页')
			
		}
		
		//打开tab
		function onClick(event, treeId, treeNode, clickFlag) {
			if (treeNode.pId != null && treeNode.pId > 0){
				if ($("#tabs").tabs("exists", treeNode.name)){
					$("#tabs").tabs("select", treeNode.name)
				}else{
					$("#tabs").tabs('add',{
						title:treeNode.name,
						closable:true,
						content:'<iframe id="' + treeNode.id + '" style="width:100%;height:100%;" scrolling="auto" frameborder="0" src="' + 
									 treeNode.page + '" >' + 
								'</iframe>'
					});
				}
			}
		}
		
		function SetWinHeight(id){ 
			var sheight = document.body.scrollHeight;
			if (sheight < 606){
				sheight = 606;
			}
			var swidth = document.body.scrollWidth;
			if (swidth < 1362){
				swidth = 1362;
			}
			$("#"+id).width(swidth - 220);
			$("#"+id).height(sheight - 83);
			
		} 
		
		function changePwd(){
			if ($("#tabs").tabs("exists", '修改密码')){
				$("#tabs").tabs("select", '修改密码')
			}else{
				var proUrl = "${ctx}";
				$("#tabs").tabs('add',{
					title:'修改密码',
					closable:true,
					content:'<iframe id="-1" scrolling="yes" frameborder="0" src="' + 
							 proUrl + '/ForwardPwd.action" onload="javascript:SetWinHeight(-1)">' + 
							 '</iframe>'
				});
			}
		}

	</script>
  </head>
   
  <body class="easyui-layout">
  
  	<!-- head -->
	<div id="head" data-options="region:'north',border:false">
		<jsp:include page="head.jsp"></jsp:include>
	</div>
	
	<!-- menu -->
	<div id="menu" data-options="region:'west',split:true,title:'功能菜单'">
		<div id="menu_tree" class="ztree" style="float:left; margin:2px;"></div>
	</div>
	
	<!-- main -->
	<div data-options="region:'center'">
		<div id="tabs" class="easyui-tabs" data-options="border:false,fit:true" style="overflow:hidden;">
		</div>
	</div>
	
  </body>
  
</html>

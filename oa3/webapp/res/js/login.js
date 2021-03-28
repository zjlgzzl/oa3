/*生成验证码*/
function getValidCode(){
	$("#validCodeImg").attr("src", "util/ValidCode_createCode.action?" + Math.random());
}

/*检查校验码是否输入正确*/
function checkValidCode(){
	var code = $.trim($("#validCode").val());
	if (code.length == 4){
		$.ajax({
			type:"Post",
			url:"util/ValidCode_getSessionCode.action",
			success:function(sessioncode){
				if (code.toUpperCase() == sessioncode.toUpperCase()){
					$("#y").attr("class", "y");
					$("#n").attr("class", "n hidden");
				}else{
					$("#y").attr("class", "y hidden");
					$("#n").attr("class", "n");
				}
			}
		});
	}else{
		$("#y").attr("class", "y hidden");
		$("#n").attr("class", "n hidden");
	}
}

/*焦点设置*/
function setfocus(){
	if ($.trim($("#userName").val()) == ''){
		$("#userName").focus();
		return;
	}
	if ($.trim($("#passWord").val()) == ''){
		$("#passWord").focus();
		return;
	}
	if ($.trim($("#validCode").val()) == ''){
		$("#validCode").focus();
	}
}

/*检查登录信息完整性*/
function check(){
	
	//提交按钮不可用（防止重复提交）
	$("#btnSubmit").attr("disabled", true);
	
	//用户名
	if ($.trim($("#userName").val()) == '') {
		alert("用户名不能为空！");
		$("#userName").focus();
		//提交按钮可用
		$("#btnSubmit").attr("disabled", false);
		return false;
	}
	
	//密码
	if ($.trim($("#passWord").val()) == '') {
		alert("密码不能为空！");
		$("#passWord").focus();
		//提交按钮可用
		$("#btnSubmit").attr("disabled", false);
		return false;
	}
	
	//验证码
	if ($.trim($("#validCode").val()) == '') {
		alert("验证码不能为空！");
		$("#validCode").focus();
		//提交按钮可用
		$("#btnSubmit").attr("disabled", false);
		return false;
	}
	
	//验证码是否输入正确
	if ("y" != $("#y").attr("class")){
		alert("验证码输入错误！");
		$("#validCode").focus();
		//提交按钮可用
		$("#btnSubmit").attr("disabled", false);
		return false;
	}
	
	$("#btnSubmit").attr("disabled", false);
	
	return true;
}
<%@ page import="com.ndktools.javamd5.Mademd5" %>
<%@ page language="java" pageEncoding="UTF-8"%>

<html>
<head>
<title></title>
<base href="${base}/" />
<meta charset="UTF-8" />
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">

<script>
//表单验证
function checkPwd(){
	//let oldpwd = document.getElementById("oldpwd");
	let password = document.getElementById("password");
	let cfmpwd = document.getElementById("cfmpwd");

	// if (oldpwd.value.length<=0){
	// 	document.getElementById("oldpwdMsg").innerText="请输入旧密码！"
	// 	oldpwd.focus();
	// 	return false;
	// }else {
	// 	document.getElementById("oldpwdMsg").innerText=""
	// }

	if (password.value.length<=0){
		document.getElementById("passwordMsg").innerText="请输入新密码！"
		password.focus();
		return false;
	}else {
		document.getElementById("passwordMsg").innerText=""
	}

	if (cfmpwd.value.length<=0){
		document.getElementById("cfmpwdMsg").innerText="请输入确认密码！"
		cfmpwd.focus();
		return false;
	}else {
		document.getElementById("cfmpwd").innerText=""
	}

	if (password.value != cfmpwd.value){
		document.getElementById("cfmpwdMsg").innerText="密码不一致,请重新输入！"
		cfmpwd.focus();
		return false;
	}else {
		document.getElementById("cfmpwd").innerText=""
	}


}

let msg='${msg}';
if(msg!=null && msg!=""){
	alert(msg);
	if (msg == '修改成功!'){
		alert("请重新登录！");
		window.parent.location.href='login?opr=out'
	}
}
<% request.setAttribute("msg", null);%>
</script>
</head>
<body>
	<div class="panel admin-panel">
		<div class="panel-head" id="add">
			<strong><span class="icon-pencil-square-o"></span>修改密码</strong>
		</div>
		<div class="body-content">
			<form method="post" class="form-x" onsubmit="return checkPwd()" action="admin?opr=updatePwd">
				<input type="hidden" name="id" value="${admin.id}">
				<div class="form-group">
					<div class="label">
						<label>原密码：</label>
					</div>
					<div class="field">
						<input type="password" class="input w50" value="${admin.password}" name="oldpwd"
							id="oldpwd" />
						<div class="tips" id="oldpwdMsg"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>新密码：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="" name="password"
							id="password" />
						<div class="tips" id="passwordMsg"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>确认密码：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="" name="cfmpwd"
							id="cfmpwd" />
						<div class="tips" id="cfmpwdMsg"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label></label>
					</div>
					<div class="field">
						<button class="button bg-main icon-check-square-o" type="submit"
							id="saveBtn">提交</button>
					</div>
				</div>
			</form>
		</div>
		<div id="testDiv"></div>
	</div>
</body>
</html>
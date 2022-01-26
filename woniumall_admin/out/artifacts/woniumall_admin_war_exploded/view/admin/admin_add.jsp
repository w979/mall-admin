<%@ page language="java" pageEncoding="UTF-8"%>

<html>
<head>
<title></title>
<base href="${base}/" />
<meta charset="UTF-8" />
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
	<script src="js/axios.js"></script>
	<script type="text/javascript">
		let flag = false;
		//表单验证
		function cherkform(){
			//获取账号密码
			let account = document.getElementById("account");
			let password = document.getElementById("password");
			let cfmpwd = document.getElementById("cfmpwd");

			if(account.value.length<=0){
				document.getElementById("accountMsg").innerText="账号不能为空！";
				account.focus();
				return false;
			}else{
				document.getElementById("accountMsg").innerText="";
			}

			//账号存在时
			if(flag){
				document.getElementById("accountMsg").innerText="账号已存在！";
				return false;
			}else{
				document.getElementById("accountMsg").innerText="";
			}

			if(password.value.length<=0){
				document.getElementById("passwordMsg").innerText="密码不能为空！";
				password.focus();
				return false;
			}else{
				document.getElementById("passwordMsg").innerText="";
			}

			if(cfmpwd.value.length<=0){
				document.getElementById("cfmpwdMsg").innerText="确认密码不能为空！";
				cfmpwd.focus();
				return false;
			}else{
				document.getElementById("cfmpwdMsg").innerText="";
			}

			if(password.value != cfmpwd.value){
				document.getElementById("cfmpwdMsg").innerText="两次密码不一致！";
				cfmpwd.focus();
				return false;
			}else{
				document.getElementById("cfmpwdMsg").innerText="";
			}

			return true;
		}

		//账号唯一性验证
		function checkAccount(){
			let account = document.getElementById("account").value;
			//异步验证
			axios.get('admin',{params:{opr:'checkAccount',account:account}}).then(result=>{
					if (result.data.code == 500){
						//账号存在
						document.getElementById("accountMsg").innerText=result.data.msg;
						flag =true;
					}else {
						document.getElementById("accountMsg").innerText="";
						flag = false;
					}
			}).catch(e=>{
				alert("服务器在裸奔0.0")
			});
			return flag;
		}
	</script>
</head>
<body>
	<div class="panel admin-panel">
		<div class="panel-head" id="add">
			<strong><span class="icon-pencil-square-o"></span>增加管理员</strong>
		</div>
		<div class="body-content">
			<form method="post" class="form-x" action="admin?opr=add" onsubmit="return cherkform()">
<%--				隐藏表单，账号默认正常--%>
				<input type="hidden" name="status" id="status" value="1">
				<div class="form-group">
					<div class="label">
						<label>账号：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="${adminInfo.account}" name="account" onblur="checkAccount()"
							id="account" />
						<div class="tips" id="accountMsg"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>密码：</label>
					</div>
					<div class="field">
						<input type="password" class="input w50" value="${adminInfo.password}" name="password" id="password" />
						<div class="tips" id="passwordMsg"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>确认密码：</label>
					</div>
					<div class="field">
						<input type="password" class="input w50" value="" name="cfmpwd" id="cfmpwd" />
						<div class="tips" id="cfmpwdMsg"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>角色：</label>
					</div>
					<div class="field">
						<input type="radio" name="role" value="1" checked="checked" >超级管理员
						<input type="radio" name="role" value="2" >普通管理员
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label></label>
					</div>
					<div class="field">
						<button class="button bg-main icon-check-square-o" type="submit" id="saveBtn">
							提交</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script>
		let msg='${msg}';
		if(msg!=null && msg!=""){
			alert(msg);
		}
		<% request.setAttribute("msg", null);%>
	</script>
</body>
</html>
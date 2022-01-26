<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
			//获取密码
			let password = document.getElementById("password");
			if(password.value.length<=0){
				document.getElementById("passwordMsg").innerText="密码不能为空！";
				password.focus();
				return false;
			}else{
				document.getElementById("passwordMsg").innerText="";
			}
			return true;
		}
	</script>
</head>
<body>
	<div class="panel admin-panel">
		<div class="panel-head" id="add">
			<strong><span class="icon-pencil-square-o"></span>修改管理员</strong>
		</div>
		<div class="body-content">
			<form method="post" class="form-x" action="admin?opr=update" onsubmit="return checkPwd()">
<%--				隐藏表单旧密码--%>
				<input type="hidden" name="jpwd" id="jpwd" value="${admin.password}">
				<div class="form-group">
					<div class="label">
						<label>账号：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="${admin.account}" readonly="readonly" name="account"
							id="account" />
						<div class="tips" id="accountMsg"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>密码：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="${admin.password}" name="password" id="password" />
						<div class="tips" id="passwordMsg"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>角色：</label>
					</div>
					<div class="field">
						<input type="radio" name="role" value="1" <c:if test="${admin.role==1}">checked="checked"</c:if> >超级管理员
						<input type="radio" name="role" value="2"<c:if test="${admin.role==2}">checked="checked"</c:if> >普通管理员

					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>状态：</label>
					</div>
					<div class="field">
							<input type="radio" name="status" value="1" <c:if test="${admin.status==1}">checked="checked"</c:if> >正常
							<input type="radio" name="status" value="2" <c:if test="${admin.status==2}">checked="checked"</c:if> >锁定
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label></label>
					</div>
					<div class="field">
						<button class="su bg-main icon-check-square-o" type="submit" id="saveBtn">
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
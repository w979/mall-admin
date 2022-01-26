<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title></title>
<base href="${base}/" />
<meta charset="UTF-8" />
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
</head>
<body>
	<div class="panel admin-panel">
		<div class="panel-head" id="add">
			<strong><span class="icon-pencil-square-o"></span>修改类别</strong>
		</div>
		<div class="body-content">
			<form method="post" class="form-x" action="category">
				<input type="hidden" name="opr" value="update" />
				<input type="hidden" name="id" value="${category.id}" />
				<div class="form-group">
					<div class="label">
						<label>商品类别名称：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="${category.name}"
							name="name" />
						<div class="tips"></div>
					</div>
				</div>
				 <div class="form-group">
					<div class="label">
						<label>商品类别状态：</label>
					</div>
					<div class="field">
						<input type="radio" name="status" value="1" <c:if test="${category.status==1}">checked="checked"</c:if>>正常
						<input type="radio" name="status" value="2" <c:if test="${category.status==2}">checked="checked"</c:if>>禁用
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>是否为导航：</label>
					</div>
					<div class="field">
						<input type="radio" name="navable" value="y" <c:if test="${category.navable=='y'}">checked="checked"</c:if>>是
						<input type="radio" name="navable" value="n" <c:if test="${category.navable=='n'}">checked="checked"</c:if>>否
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label></label>
					</div>
					<div class="field">
						<button class="button bg-main icon-check-square-o" type="submit">
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
	//删除Session
	<% request.setAttribute("msg", null);%>


</script>
</body>
</html>
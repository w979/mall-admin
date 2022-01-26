<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>系统信息</title>
<link rel="stylesheet" href="css/pintuer.css">
<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}

.error-container {
	background: #fff;
	border: 1px solid #0ae;
	text-align: center;
	width: 450px;
	margin: 120px auto;
	font-family: Microsoft Yahei;
	border-top-left-radius: 5px;
	border-top-right-radius: 5px;
}

.error-container h1 {
	font-size: 16px;
	padding: 12px 0;
	background: #0ae;
	color: #fff;
}

.errorcon {
	padding: 35px 0;
	text-align: center;
	color: #0ae;
	font-size: 18px;
}

.errorcon i {
	display: block;
	margin: 12px auto;
	font-size: 30px;
}

.errorcon span {
	color: red;
}

h4 {
	font-size: 14px;
	color: #666;
}

a {
	color: #0ae;
}
</style>
</head>
<body class="no-skin">
	<div class="error-container">
		<h1>后台管理系统-信息提示</h1>
		<div class="errorcon">
			<c:if test="${empty r}">
				<i class="icon-smile-o"></i>操作成功
			</c:if>
			<c:if test="${not empty r}">
				<i class="icon-frown-o"></i>操作失败!
			 </c:if>
		</div>
		<h4 class="smaller">

			您希望接下来做什么?<a id="href" href="">继续操作</a> <a id="href" href="">回到列表页</a>
		</h4>
	</div>
</body>
</html>

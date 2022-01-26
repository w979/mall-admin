<%@ page language="java" pageEncoding="UTF-8"%>

<html>
<head>
<title>后台管理中心</title>
<base href="${base}/" />
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
	<script type="text/javascript">
		function loginOut(){
			if (confirm("确定退出吗？")){
				window.location.href='login?opr=out';
			}
		}
	</script>
</head>
<body style="background-color: #f2f9fd;">
	<div class="header bg-main">
		<div class="logo margin-big-left fadein-top">
			<h1>蜗牛电商后台管理系统</h1>
		</div>
		<div class="head-l">
			<a class="button button-little bg-green" href="view/welcome.jsp"
				target="right"> <span class="icon-home"></span>管理首页
			</a> &nbsp;&nbsp; <a class="button button-little bg-red" href="javascript:loginOut()">
				<span class="icon-power-off"></span>退出登录
			</a>
			欢迎您！${admin.account}
		</div>
	</div>
	<div class="leftnav">
		<div class="leftnav-title">
			<strong><span class="icon-list"></span>菜单列表</strong>
		</div>
		<h2>
			<a href="admin?opr=list" target="right">
			  <span class="icon-user"></span>管理员管理
			</a>
		</h2>
		<h2>
		  <a href="category?opr=list" target="right">
		  	<span class="icon-pencil-square-o"></span>商品类别管理
		  </a>
		</h2>
		<h2>
			<a href="goods?opr=list" target="right">
				<span class="icon-user"></span>商品管理
			</a>
		</h2>
		<h2>
			<a href="order?opr=list" target="right">
				<span class="icon-user"></span>订单管理
			</a>
		</h2>
		<h2>
			<a href="user?opr=list" target="right">
				<span class="icon-user"></span>用户管理
			</a>
		</h2>
		<h2>
			<a href="admin?opr=showUpdatePwd" target="right">
				<span class="icon-user"></span>修改密码
			</a>
		</h2>

	</div>
	<div class="admin">
		<iframe src="view/welcome.jsp" name="right" width="100%" height="100%" id="right"></iframe>
	</div>
</body>
</html>
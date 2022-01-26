<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<meta charset="utf-8" />
<base href="${base}/" />
<title></title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script type="text/javascript">
	function del(id) {
		if (confirm("确定删除吗?")) {
			window.location.href = "user?opr=del&id=" + id;
		}
	}

	let msg ='${msg}';
	if (msg != null && msg !=""){
		alert(msg);
	}
	<%request.getSession().removeAttribute("msg");%>
</script>
</head>
<body>
	<div class="panel admin-panel">
		<div class="panel-head">
			<strong class="icon-reorder">用户列表</strong>
		</div>
		<table class="table table-hover text-center">
			<tr>
				<th>序号</th>
				<th>账号</th>
				<th>邮箱</th>
				<th>头像</th>
				<th>积分</th>
				<th>注册时间</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${pageInfo.list}" var="user" varStatus="vs">
				<tr>
					<td>${vs.count}</td>
					<td>${user.account}</td>
					<td>${user.email}</td>
					<td>
					   <img src="${user.avatar}" width="50px" height="50px" />
					</td>
					<td>${user.score}</td>
					<td><fmt:formatDate value="${user.regtime}" type="both"/></td>
					<td>
						<c:if test="${user.status==1}"><span style="color: gray"  class="label label-warning">未激活</span></c:if>
						<c:if test="${user.status==2}"><span style="color: green" class="label label-success">正常</span></c:if>
						<c:if test="${user.status==3}"><span style="color: red" class="label label-danger">锁定</span></c:if>
					</td>
					<td>
						<div class="button-group">
							<a class="button border-red" <c:if test="${user.status==3}"> disabled="true" </c:if>
								href="javascript:void(0)" onclick="del('${user.id}')"><span
								class="icon-trash-o"></span>锁定</a>
						</div>
					</td>
				</tr>
			</c:forEach>
		</table>

		<!--分页插件-->
		<div style="text-align: center">
			<nav aria-label="Page navigation">
				<ul class="pagination">
					<li>
						<a href="javascript:prePage('${pageInfo.pageNum}','${pageInfo.pageSize}')" aria-label="Previous">
							<span aria-hidden="true">上一页</span>
						</a>
					</li>
					<c:forEach begin="1" end="${pageInfo.pages}" var="pagenum">
						<li><a href="javascript:goPage('${pagenum}','${pageInfo.pageSize}')">${pagenum}</a></li>
					</c:forEach>
					<li>
						<a href="javascript:nextPage('${pageInfo.pageNum}','${pageInfo.pageSize}','${pageInfo.pages}')"
						   aria-label="Next">
							<span aria-hidden="true">下一页</span>
						</a>
					</li>
					<li>
						<a disabled="true">当前第 ${pageInfo.pageNum} 页 | 共 ${pageInfo.pages} 页 | ${pageInfo.total}
							条数据</a>
					</li>
				</ul>
			</nav>
		</div>
		<!-- 分页插件结束 -->
	</div>
	<script>

		//上一页
		function prePage(pageNo, pageSize) {
			if (parseInt(pageNo) > 1) {
				pageNo--;
				//将form表单中的数据转为键值形式
				let formdata = new URLSearchParams({
					opr: 'list',
					pageNo: pageNo,
					pageSize: pageSize,
				})
				window.location.href = "user?" + formdata.toString();
			}
		}

		//下一页
		function nextPage(pageNo, pageSize, pages) {
			if (parseInt(pageNo) < parseInt(pages)) {
				pageNo++;
				//将form表单中的数据转为键值形式
				let formdata = new URLSearchParams({
					opr: 'list',
					pageNo: pageNo,
					pageSize: pageSize,
				})
				window.location.href = "user?" + formdata.toString();
			}
		}

		//页码跳转
		function goPage(pageNo, pageSize) {
			//将form表单中的数据转为键值形式
			let formdata = new URLSearchParams({
				opr: 'list',
				pageNo: pageNo,
				pageSize: pageSize,
			})
			window.location.href = "user?" + formdata.toString();
		}
	</script>
</body>
</html>
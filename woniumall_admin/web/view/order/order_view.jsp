<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<meta charset="utf-8" />
<title></title>
<base href="${base}/" />
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery-3.5.1.js"></script>
<script>

</script>
</head>
<body>

	<div class="panel admin-panel">
		<div class="panel-head">
			<strong class="icon-reorder"> 订单详情</strong>
		</div>
		<div class="padding border-bottom">
			<ul class="search" style="padding-left: 10px;">
				<li>订单编号：${pageInfo.list[0].orders.orderno}
				</li>
				<li>下单时间：<fmt:formatDate value="${pageInfo.list[0].orders.ordertime}" type="both"/>
				</li>
				<li>用户：${pageInfo.list[0].orders.user.account}</li>
			</ul>
		</div>
		<table class="table table-hover text-center">
			<tr>
				<th>序号</th>
				<th>商品名</th>
				<th>图片</th>
				<th>价格</th>
				<th>数量</th>
			</tr>
			<tbody>
			<c:forEach items="${pageInfo.list}" varStatus="vs" var="detail">
			<tr>
				<td>${vs.count}</td>
				<td>${detail.goods.name}</td>
				<td><img src="${detail.goods.image}" width="100" height="100"></td>
				<td>${detail.price}</td>
				<td>${detail.nums}</td>
			</tr>
			</c:forEach>
			</tbody>
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
			let orderid = '${orderid}'
			//将form表单中的数据转为键值形式
			let formdata = new URLSearchParams({
				opr: 'queryDetail',
				pageNo: pageNo,
				pageSize: pageSize,
				orderid: orderid
			})
			window.location.href = "order?" + formdata.toString();
		}
	}

	//下一页
	function nextPage(pageNo, pageSize, pages) {
		if (parseInt(pageNo) < parseInt(pages)) {
			pageNo++;
			let orderid = '${orderid}'
			//将form表单中的数据转为键值形式
			let formdata = new URLSearchParams({
				opr: 'queryDetail',
				pageNo: pageNo,
				pageSize: pageSize,
				orderid: orderid
			})
			window.location.href = "order?" + formdata.toString();
		}
	}

	//页码跳转
	function goPage(pageNo, pageSize) {
		let orderid = '${orderid}'
		//将form表单中的数据转为键值形式
		let formdata = new URLSearchParams({
			opr: 'queryDetail',
			pageNo: pageNo,
			pageSize: pageSize,
			orderid: orderid
		})
		window.location.href = "order?" + formdata.toString();
	}
</script>
</body>
</html>
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
<script src="js/laydate/laydate.js"></script>
<script src="js/template-web.js"></script>

<script>
	function clears(){
		document.getElementById("orderno").setAttribute("value","");
		document.getElementById("startTime").setAttribute("value","");
		document.getElementById("endTime").setAttribute("value","");
		document.getElementById("status").setAttribute("value","0");
	}

function delOrder(id){
	if (confirm("确定删除吗？")){
		window.location.href='order?opr=del&id='+id;
	}
}
</script>

</head>
<body>
	<div class="mydiv"></div>
	<div class="mydiv"></div>
	<div class="mydiv"></div>
	<div class="panel admin-panel">
		<div class="panel-head">
			<strong class="icon-reorder"> 订单列表</strong>
		</div>
		<div class="padding border-bottom">
			<form method="post" action="order?opr=list" id="queryForm" name="queryForm" >
				<ul class="search" style="padding-left: 10px;">
					<li>订单编号：<input type="text" placeholder="请输入订单编号"
						name="orderno" class="input" value="${orderno}" id="orderno"
						style="width: 150px; line-height: 17px; display: inline-block" />
					</li>
					<li>下单时间：<input type="text" placeholder="请输入起始时间"
						class="input" value="<fmt:formatDate value="${startTime}" type="date"/>"  id="startTime" name="startTime"
						style="width: 150px; line-height: 17px; display: inline-block" />--
						<input type="text" placeholder="请输入结束时间" name="endTime" class="input"
							value="<fmt:formatDate value="${endTime}" type="date"/>" id="endTime"
						style="width: 150px; line-height: 17px; display: inline-block" />
					</li>
					<li><select name="status" class="input" id="status"
						style="width: 200px; line-height: 17px;">
						<option value="0" <c:if test="${status==0}">selected="selected"</c:if>>不限</option>
							<option value="1" <c:if test="${status==1}">selected="selected"</c:if>>待付款</option>
							<option value="2" <c:if test="${status==2}">selected="selected"</c:if>>已付款</option>
							<option value="3" <c:if test="${status==3}">selected="selected"</c:if>>已收货</option>
							<option value="4" <c:if test="${status==4}">selected="selected"</c:if>>已取消</option>
							<option value="5" <c:if test="${status==5}">selected="selected"</c:if>>已关闭</option>
							<option value="6" <c:if test="${status==6}">selected="selected"</c:if>>已完成</option>
					</select></li>
					<li><button class="button border-main icon-search"
							type="submit" id="searchBtn">搜索</button>
							<button class="button border-main icon-search"
							type="reset" id="clearBtn" onclick="clears()">清空查询条件</button></li>
				</ul>
			</form>
		</div>
		<table class="table table-hover text-center">
			<thead>
				<tr>
					<th>序号</th>
					<th>订单编号</th>
					<th>用户</th>
					<th>下单时间</th>
					<th>收货地址</th>
					<th>金额</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="orderData">
			<c:forEach items="${pageInfo.list}" var="order" varStatus="vs">
					<tr>
						<td>${vs.count}</td>
						<td>${order.orderno}</td>
						<td>${order.user.account}</td>
						<td><fmt:formatDate value="${order.ordertime}" type="both"/></td>
						<td>${order.address}</td>
						<td>${order.money}</td>
						<td><c:if test="${order.status==1 }">
								<span class="label label-default">待付款</span>
							</c:if> <c:if test="${order.status==2 }">
								<span class="label label-primary">已付款</span>
							</c:if> <c:if test="${order.status==3 }">
								<span class="label label-success">已收货</span>
							</c:if> <c:if test="${order.status==4 }">
								<span class="label label-warning">已取消</span>
							</c:if> <c:if test="${order.status==5 }">
								<span class="label label-danger">已关闭</span>
							</c:if> <c:if test="${order.status==6 }">
								<span class="label label-info">已完成</span>
							</c:if>
						<td>
							<div class="button-group">
								<a class="button border-main" href="order?opr=queryDetail&orderid=${order.id}"><span
									class="icon-edit"></span> 查看</a> 
									<c:if test="${(order.status== 4 || order.status== 5) && order.isdel=='n' }">
									  <a class="button border-red"
									  href="javascript:delOrder(${order.id})"><span
									  class="icon-trash-o"></span>删除</a>
									</c:if>
									
							</div></td>
				    </tr>
				</c:forEach>
			</tbody>
		</table>
	<!-- 分页插件开始 -->
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
				let queryForm = document.getElementById("queryForm");
				//将form表单中的数据转为键值形式
				let formdata = new URLSearchParams({
					opr: 'list',
					pageNo: pageNo,
					pageSize: pageSize,
					orderno: queryForm.orderno.value,
					startTime: queryForm.startTime.value,
					endTime: queryForm.endTime.value,
					status: queryForm.status.value,
				})
				window.location.href = "order?" + formdata.toString();
			}
		}

		//下一页
		function nextPage(pageNo, pageSize, pages) {
			if (parseInt(pageNo) < parseInt(pages)) {
				pageNo++;
				//将form表单中的数据转为键值形式
				let queryForm = document.getElementById("queryForm");
				let formdata = new URLSearchParams({
					opr: 'list',
					pageNo: pageNo,
					pageSize: pageSize,
					orderno: queryForm.orderno.value,
					startTime: queryForm.startTime.value,
					endTime: queryForm.endTime.value,
					status: queryForm.status.value,
				})
				window.location.href = "order?" + formdata.toString();
			}
		}

		//页码跳转
		function goPage(pageNo, pageSize) {
			let queryForm = document.getElementById("queryForm");
			//将form表单中的数据转为键值形式
			let formdata = new URLSearchParams({
				opr: 'list',
				pageNo: pageNo,
				pageSize: pageSize,
				orderno: queryForm.orderno.value,
				startTime: queryForm.startTime.value,
				endTime: queryForm.endTime.value,
				status: queryForm.status.value,
			})
			window.location.href = "order?" + formdata.toString();
		}

		let msg ='${msg}';
		if (msg != null && msg !=""){
			alert(msg);
		}
		<%request.getSession().removeAttribute("msg");%>
</script>
</body>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="utf-8" />
<title></title>
<base href="${base}/" />
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
	<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
	<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
<script>

//下架商品
function downGoods(id){
	if (confirm("确定下架吗？")){
		window.location.href='goods?opr=downGoods&id='+id;
	}
}

//获得全选复选框
function downAll(){
	//获得全选复选框
	let allcheck = document.getElementById("allcheck");
	//获得所有复选框
	let goodId = document.getElementsByName("goodId");
	for (let i=0; i<goodId.length;i++){
		goodId[i].checked = allcheck.checked;
	}
}

//全选按钮
function btndownAll(){
	//获得全选复选框
	let allcheck = document.getElementById("allcheck");
	let flag = false;
	//全选复选框的状态未选择时
	if (allcheck.checked==false){
		flag = true;
		//点击全选按钮后，就把全选复选框的状态改为选中
		document.getElementById("allcheck").checked=true;
	}else {
		document.getElementById("allcheck").checked=false;
	}
	//获得所有复选框
	let goodId = document.getElementsByName("goodId");
	for (let i=0; i<goodId.length;i++){
		goodId[i].checked = flag;
	}
}

//计算被选中的个数
function checkNum(){
let count = 0;
//获得所有复选框
	let goodId = document.getElementsByName("goodId");
	for (let i=0; i<goodId.length;i++){
		if (goodId[i].checked==true){
			count++;
		}
	}
return count;
}

//批量下架
function downcheckAll(){
	//获得选中商品的个数
	let count = checkNum();
	//获得批量下架按钮
  let btnDown = document.getElementById("btnDown");
  if (count == 0){
  	alert("您还没有选择商品！");
  }else {
  	//选中的id
  	let ids = '';
	  //获得所有复选框
	  let goodId = document.getElementsByName("goodId");
	  for (let i=0; i<goodId.length;i++){
		  if (goodId[i].checked==true){
			  //被选中的
			 ids += goodId[i].value+",";
		  }
	  }
	  //把最后一个逗号截取掉
		ids	 = ids.substring(0,ids.length-1);
	  if (confirm("确定要下架吗？")){
		  window.location.href='goods?opr=downGoods&id='+ids;
	  }
  }
}


</script>
</head>
<body>
	<div class="panel admin-panel">
		<div class="panel-head">
			<strong class="icon-reorder"> 商品列表</strong>
		</div>
		<div class="padding border-bottom">
			<button type="button" class="button border-green" id="checkall" onclick="btndownAll()">
				<span class="icon-check"></span> 全选
			</button>
			<button type="submit" id="btnDown" onclick="downcheckAll()" class="button border-red">
				<span class="icon-trash-o"></span> 批量下架
			</button>
			<button type="button" class="button border-yellow" onclick="location.href='goods?opr=showAdd'">
				<span class="icon-plus-square-o"></span>增加商品
			</button>
		</div>

		<div class="padding border-bottom">
			<form method="post" action="goods?opr=list" id="queryForm">

				<ul class="search" style="padding-left: 10px;">
					<li>商品名称：<input type="text" placeholder="请输入商品名称" name="name"
						class="input" value="${name}"
						style="width: 150px; line-height: 17px; display: inline-block" />
					</li>
					<li>商品类型:</li>
					<li><select name="categoryid" class="input" id="categoryid"
						style="width: 150px; line-height: 17px;">
							<option value="0" selected="selected">不限</option>
							<c:forEach items="${categoryList}" var="category">
						<option value="${category.id}"<c:if test="${category.id==categoryid}">selected="selected"</c:if>>${category.name}</option>
							</c:forEach>
					</select></li>
					<li>商品状态:</li>
					<li><select name="status" class="input" id="status"
						style="width: 100px; line-height: 17px;">
							<option value="0" selected="selected">不限</option>
						<option value="1" <c:if test="${status==1}">selected="selected"</c:if>>上架</option>
						<option value="2" <c:if test="${status==2}">selected="selected"</c:if>>下架</option>
					</select></li>
					<li>库存：<input type="text" name="startstock" class="input"
						value="${startstock}"
						style="width: 100px; line-height: 17px; display: inline-block" />--<input
						type="text" name="endstock" class="input"
						value="${endstock}"
						style="width: 100px; line-height: 17px; display: inline-block" /></li>
					<li><button type="submit" class="button border-main icon-search">搜索</button></li>
				</ul>
			</form>
		</div>
		<table class="table table-hover text-center">
			<tr>
				<th><input type="checkbox"  id="allcheck" onclick="downAll()">选择</th>
				<th>序号</th>
				<th width="400px">商品名称</th>
				<th>图片</th>
				<th>类别</th>
				<th>库存</th>
				<th>市场价</th>
				<th>销售价</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${pageInfo.list}" var="goods" varStatus="vs">
				<tr>
					<td><input type="checkbox" <c:if test="${goods.status==2 }"> disabled </c:if> name="goodId" value="${goods.id}"></td>
					<td>${vs.count}</td>
					<td>${goods.name}</td>
					<td><img src="${goods.image}" width="80px" height="80px" /></td>
					<td>${goods.category.name}</td>
					<td>${goods.stock}</td>
					<td>${goods.marketprice}</td>
					<td>${goods.salesprice}</td>
					<td>
						<c:if test="${goods.status==1 }">
							<span class="label label-success">上架</span>
						</c:if>
						<c:if test="${goods.status==2 }">
							<span class="label label-danger" >下架</span>
						</c:if>
					<td><div class="button-group">
							<a class="button border-main" href="goods?opr=showUpdate&id=${goods.id}"><span class="icon-edit"></span>
								修改</a>
					<a class="button border-red"<c:if test="${goods.status==2 }"> disabled="true" </c:if> href="javascript:downGoods(${goods.id})"
								onclick=""><span class="icon-trash-o"></span> 下架</a>
						</div></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="10">
					<div>
						<!--分页插件-->
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
									<a href="javascript:void(0)">当前第 ${pageInfo.pageNum} 页/共 ${pageInfo.pages} 页/共 ${pageInfo.total} 条数据</a>
								</li>
							</ul>
						</nav>
					</div>
				</td>
			</tr>
		</table>
	</div>
<script>
		//上一页
		function prePage(pageNo, pageSize) {
			if (pageNo > 1) {
				pageNo--;
				let queryForm = document.getElementById("queryForm");
				//将form表单中的数据转为键值形式
				let formdata = new URLSearchParams({
					opr: 'list',
					pageNo: pageNo,
					pageSize: pageSize,
					name: queryForm.name.value,
					categoryid: queryForm.categoryid.value,
					status: queryForm.status.value,
					startstock: queryForm.startstock.value,
					endstock: queryForm.endstock.value
				})
				window.location.href = "goods?" + formdata.toString();
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
					name: queryForm.name.value,
					categoryid: queryForm.categoryid.value,
					status: queryForm.status.value,
					startstock: queryForm.startstock.value,
					endstock: queryForm.endstock.value
				})
				window.location.href = "goods?" + formdata.toString();
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
				name: queryForm.name.value,
				categoryid: queryForm.categoryid.value,
				status: queryForm.status.value,
				startstock: queryForm.startstock.value,
				endstock: queryForm.endstock.value
			})
			window.location.href = "goods?" + formdata.toString();
	}

let msg ='${msg}';
if (msg != null && msg !=""){
	alert(msg);
}
<%request.getSession().removeAttribute("msg");%>
</script>
</body>
</html>
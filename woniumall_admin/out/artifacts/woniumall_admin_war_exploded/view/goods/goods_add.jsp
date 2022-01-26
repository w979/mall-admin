<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<base href="${pageContext.request.contextPath}/">
<head>
	<title>Title</title>
	<link rel="stylesheet" href="css/pintuer.css">
	<link rel="stylesheet" href="css/admin.css">
	<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
	<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="ueditor/ueditor.config.js"></script>
	<script type="text/javascript" src="ueditor/ueditor.all.js"></script>
	<script type="text/javascript" src="js/axios.js"></script>

	<script type="text/javascript">
		window.addEventListener("load", function () {
			let ue = UE.getEditor('container', {
				toolbars: [[
					'fullscreen', 'source', '|', 'undo', 'redo', '|',
					'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
					'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
					'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
					'directionalityltr', 'directionalityrtl', 'indent', '|',
					'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
					'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
					'simpleupload', 'insertimage', 'emotion', 'scrawl', 'insertvideo', 'music', 'attachment', 'map', 'gmap', 'insertframe', 'insertcode', 'webapp', 'pagebreak', 'template', 'background', '|',
					'horizontal', 'date', 'time', 'spechars', 'snapscreen', 'wordimage', '|',
					'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts', '|',
					'print', 'preview', 'searchreplace', 'drafts', 'help'
				]],
				initialFrameHeight: 500
			});
		});
	</script>

</head>
<body>
<div class="panel admin-panel">
	<div class="panel-head" id="add">
		<strong><span class="icon-pencil-square-o"></span>增加商品</strong>
	</div>
	<div class="body-content">
		<form method="post" class="form-x" action="goods" id="myform" onsubmit="return checks()">
			<input type="hidden" name="opr" value="add"/>
			<input type="hidden" name="image" id="pic" src="${goods.image}">
			<div class="form-group">
				<div class="label">
					<label>商品名称：</label>
				</div>
				<div class="field">
					<input type="text" class="input" name="name" id="name" value="${goods.name}"/>
					<div class="tips" id="nameMsg"></div>
				</div>
			</div>
			<div class="form-group">
				<div class="label">
					<label>作者：</label>
				</div>
				<div class="field">
					<input type="text" class="input w50" name="author" id="author" value="${goods.author}"/>
					<div class="tips" id="authorMsg"></div>
				</div>
			</div>
			<div class="form-group">
				<div class="label">
					<label>出版社：</label>
				</div>
				<div class="field">
					<input type="text" class="input w50" name="publisher" id="publisher" value="${goods.publisher}"/>
					<div class="tips" id="publisherMsg"></div>
				</div>
			</div>
			<div class="form-group">
				<div class="label">
					<label>出版时间：</label>
				</div>
				<div class="field">
					<input type="date" class="input w50" name="publishtime" value=""/>
					<div></div>
				</div>
			</div>
			<div class="form-group">
				<div class="label">
					<label>商品类别：</label>
				</div>
				<div class="field">
					<select name="categoryid" class="input w50">
						<option value="0">请选择商品类别</option>
						<c:forEach items="${categoryList}" var="category">
							<option value="${category.id}" <c:if test="${goods.categoryid==category.id}">selected="selected"</c:if>>${category.name}</option>
						</c:forEach>
					</select>
					<div class="tips"></div>
				</div>
			</div>
			<div class="form-group">
				<div class="label">
					<label>库存：</label>
				</div>
				<div class="field">
					<input type="text" class="input w50" name="stock" id="stock" value="${goods.stock}"/>
					<div class="tips" id="stockMsg"></div>
				</div>
			</div>
			<div class="form-group">
				<div class="label">
					<label>市场价：</label>
				</div>
				<div class="field">
					<input type="text" class="input w50" name="marketprice" id="marketprice" value="${goods.marketprice}"/>
					<div class="tips" id="marketpriceMsg"></div>
				</div>
			</div>
			<div class="form-group">
				<div class="label">
					<label>销售价：</label>
				</div>
				<div class="field">
					<input type="text" class="input w50" name="salesprice" id="salesprice" value="${goods.salesprice}"/>
					<div class="tips" id="salespriceMsg"></div>
				</div>
			</div>
			<div class="form-group">
				<div class="label">
					<label>图片：</label>
				</div>
				<div class="field">
					<input type="file" class="input w50" name="image" id="image" onchange="upFile()"/>
					<img src="${goods.image}" id="cover" width="100" height="100">
				</div>
			</div>
			<div class="form-group">
				<div class="label">
					<label>描述：</label>
				</div>
				<div class="field">
					<script id="container" name="description" type="text/plain">${goods.description}</script>
					<div class="tips" id="descriptionMsg"></div>
				</div>
			</div>
			<div class="form-group">
				<div class="label">
					<label></label>
				</div>
				<div class="field">
					<button class="button bg-main icon-check-square-o" type="submit">提交</button>
				</div>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
	//异步上传
	function upFile(){
		let image=document.getElementById("image").files[0];
		let formdata= new FormData();
		formdata.append("uploadFile",image);

		axios.post('fileServlet',formdata,{contentType:'multipart/form-data'})
				.then(result=>{
					console.log(result.data);
					if(result.data.code==200){
						//上传成功，保存图片路径
						document.getElementById("cover").setAttribute("src",result.data.msg);
						document.getElementById("pic").value=result.data.msg;
					}else{
						alert(result.data.msg);
					}
				}).catch(e=>{
			alert("服务器出错啦。。。");
		})
	}

	function checks(){
		let name =document.getElementById("name");
		let author =document.getElementById("author");
		let publisher =document.getElementById("publisher");
		let stock =document.getElementById("stock");
		let marketprice =document.getElementById("marketprice");
		let salesprice =document.getElementById("salesprice");
		let image = document.getElementById("pic").value;
		let description =document.getElementById("description");

		if (name.value.length<=0){
			document.getElementById("nameMsg").innerText="请输入图书名称";
			name.focus();
			return false;
		}else {
			document.getElementById("nameMsg").innerText="";
		}

		if (author.value.length<=0){
			document.getElementById("authorMsg").innerText="请输入图书作者";
			author.focus();
			return false;
		}else {
			document.getElementById("authorMsg").innerText="";
		}

		if (publisher.value.length<=0){
			document.getElementById("publisherMsg").innerText="请输入图书出版社";
			name.focus();
			return false;
		}else {
			document.getElementById("publisherMsg").innerText="";
		}

		if (stock.value.length<=0){
			document.getElementById("stockMsg").innerText="请输入图书库存";
			name.focus();
			return false;
		}else {
			document.getElementById("stockMsg").innerText="";
		}

		if (marketprice.value.length<=0){
			document.getElementById("marketpriceMsg").innerText="请输入图书市场价";
			name.focus();
			return false;
		}else {
			document.getElementById("marketpriceMsg").innerText="";
		}

		if (salesprice.value.length<=0){
			document.getElementById("salespriceMsg").innerText="请输入图书销售价";
			name.focus();
			return false;
		}else {
			document.getElementById("salespriceMsg").innerText="";
		}

		if (image.length <=0){
			alert("请上传图片")
			return false;
		}
		if (description.value.length<=0){
			document.getElementById("descriptionMsg").innerText="请输入图书描述";
			name.focus();
			return false;
		}else {
			document.getElementById("descriptionMsg").innerText="";
		}
		return true;

	}
</script>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
		<strong><span class="icon-pencil-square-o"></span>????????????</strong>
	</div>
	<div class="body-content">
		<form method="post" class="form-x" action="goods?opr=update">
<%--			id--%>
			<input type="hidden" name="id" value="${goods.id}" />
<%--			??????--%>
			<input type="hidden" name="pic" id="pic" value="${goods.image}" />
			<div class="form-group">
				<div class="label">
					<label>???????????????</label>
				</div>
				<div class="field">
					<input type="text" class="input" name="name" value="${goods.name}" />
					<div class="tips"></div>
				</div>
			</div>
			<div class="form-group">
				<div class="label">
					<label>?????????</label>
				</div>
				<div class="field">
					<input type="text" class="input w50" name="author" value="${goods.author}" />
					<div class="tips"></div>
				</div>
			</div>
			<div class="form-group">
				<div class="label">
					<label>????????????</label>
				</div>
				<div class="field">
					<input type="text" class="input w50" name="publisher" value="${goods.publisher}" />
					<div class="tips"></div>
				</div>
			</div>
			<div class="form-group">
				<div class="label">
					<label>???????????????</label>
				</div>
				<div class="field">
					<input type="date" class="input w50" name="publishtime" value="" />
					<div class="tips"></div>
				</div>
			</div>
			<div class="form-group">
				<div class="label">
					<label>???????????????</label>
				</div>
				<div class="field">
					<select name="categoryid" class="input w50">
						<option value="0">?????????????????????</option>
						<c:forEach items="${categoryList}" var="category">
							<option value="${category.id}" <c:if test="${goods.categoryid==category.id}">selected="selected"</c:if>>${category.name}</option>
						</c:forEach>
					</select>
					<div class="tips"></div>
				</div>
			</div>
			<div class="form-group">
				<div class="label">
					<label>?????????</label>
				</div>
				<div class="field">
					<input type="text" class="input w50" name="stock" value="${goods.stock}" />
					<div class="tips"></div>
				</div>
			</div>
			<div class="form-group">
				<div class="label">
					<label>????????????</label>
				</div>
				<div class="field">
					<input type="text" class="input w50" name="marketprice" value="${goods.marketprice}" />
					<div class="tips"></div>
				</div>
			</div>
			<div class="form-group">
				<div class="label">
					<label>????????????</label>
				</div>
				<div class="field">
					<input type="text" class="input w50" name="salesprice" value="${goods.salesprice}" />
					<div class="tips"></div>
				</div>
			</div>
			<div class="form-group">
				<div class="label">
					<label>?????????</label>
				</div>
				<div class="field">
					<input type="file" id="image"  class="input w50" name="image" onchange="upimg()" />
					<img src="${goods.image}" id="newimg" height="80" width="80">
					<div class="tips"></div>
				</div>
			</div>
			<div class="form-group">
				<div class="label">
					<label>?????????</label>
				</div>
				<div class="field">
					<script id="container" name="description" type="text/plain">${goods.description}</script>
				</div>
			</div>
			<div class="form-group">
				<div class="label">
					<label></label>
				</div>
				<div class="field">
					<button class="button bg-main icon-check-square-o" type="submit">??????</button>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	function upimg(){
		//??????????????????
		let image = document.getElementById("image").files[0];
		let formdata = new FormData();
		formdata.append("uploadFile",image);
		axios.post('fileServlet',formdata,{contentType:'multipart/form-data'}).then(result=>{
			console.log(result.data)
			if (result.data.code==200){
				//????????????
				document.getElementById("newimg").setAttribute("src",result.data.msg);
				//??????????????????
				document.getElementById("pic").value=result.data.msg;
				alert("???????????????");
			}else {
				alert(result.data.msg);
			}

		}).catch(e=>{
			alert("??????????????????0.0");
		});
	}

	let msg='${msg}';
	if(msg!=null && msg!=""){
		alert(msg);
	}

	<% request.setAttribute("msg", null);%>
</script>
</body>
</html>

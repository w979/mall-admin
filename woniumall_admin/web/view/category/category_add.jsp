<%@ page language="java" pageEncoding="UTF-8"%>

<html>
<head>
<title></title>
<base href="${base}/" />
<meta charset="UTF-8" />
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
	<script src="js/axios.js"></script>
	<script>
        let flag = false;
        //类别名唯一性验证
        function checkOnly(){
            let name = document.getElementById("name").value;
            axios.get('category',{params:{opr:'checkName',name:name}}).then(result=>{
                if (result.data.code == 500){
                    //存在
                    document.getElementById("nameMsg").innerText=result.data.msg;
                    flag = true;
                }else {
                    document.getElementById("nameMsg").innerText="";
					flag = false;
                }
            }).catch(e=>{
                alert("服务器在裸奔0.0")
            });

        }

		//表单验证
		function checkName(){
			let name = document.getElementById("name");
			if (name.value.length <= 0){
				document.getElementById("nameMsg").innerText="请输入商品类名！";
				name.focus();
				return false;
			}else {
				document.getElementById("nameMsg").innerText="";
			}

			if (flag){
				document.getElementById("nameMsg").innerText="商品类别已存在！";
                name.focus();
				return false;
			}else {
				document.getElementById("nameMsg").innerText="";
			}

			return true;
		}


	</script>
</head>
<body>
	<div class="panel admin-panel">
		<div class="panel-head" id="add">
			<strong><span class="icon-pencil-square-o"></span>增加类别</strong>
		</div>
		<div class="body-content">
			<form method="post" class="form-x" action="category?opr=add" onsubmit="return checkName()">
<%--				隐藏表单 状态默认正常--%>
				<input type="hidden" name="status" id="status" value="1">

				<div class="form-group">
					<div class="label">
						<label>商品类别名称：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" name="name" id="name" onblur="checkOnly()" />
						<div class="tips" id="nameMsg"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>是否为导航：</label>
					</div>
					<div class="field">
						<input type="radio" name="navable" value="y" checked="checked" >是
						<input type="radio" name="navable" value="n" >否
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
	<% request.setAttribute("msg", null);%>
</script>
</body>
</html>
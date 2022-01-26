<%@ page language="java" pageEncoding="UTF-8" %>

<html lang="zh-cn">
<head>
    <title>登录</title>
    <base href="${base}/"/>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script type="text/javascript" src="js/jquery-3.5.1.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <script type="text/javascript" src="js/axios.js"></script>
    <script type="text/javascript">

        //表单验证
        function checkLogin() {
            //获取账号密码
            let account = document.getElementById("account").value;
            let password = document.getElementById("password").value;
            if (account.length <= 0) {
                alert("请输入账号！");
                return false;
            }
            if (password.length <= 0) {
                alert("请输入密码！");
                return false;
            }
            return true;
        }

        function btnLogin() {
            if (checkLogin()){
                //获取账号密码
                let account = document.getElementById("account").value;
                let password = document.getElementById("password").value;
                if (checkLogin) {
                    //异步登录
                    axios.get('login', {params: {opr: 'dologin', account: account, password: password}}).then(result => {
                        if (result.data.code == 200) {
                            //登录成功
                            window.location.href = 'view/index.jsp';
                        } else {
                            alert(result.data.msg);
                        }
                    }).catch(e => {
                        alert("服务器在裸奔0.0");
                    });
                }
            }
        }
    </script>
</head>

<body>
<div class="bg"></div>
<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height:150px;"></div>
            <div class="media media-y margin-big-bottom"></div>
            <form action="" method="post">
                <input type="hidden" name="opr" value="login"/>
                <div class="panel loginbox">
                    <div class="text-center margin-big padding-big-top">
                        <h1>后台管理中心</h1>
                    </div>
                    <div class="panel-body"
                         style="padding:30px; padding-bottom:10px; padding-top:10px;">
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="text" class="input input-big" name="account" id="account"
                                       placeholder="登录账号" value=""/> <span
                                    class="icon icon-user margin-small"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="password" class="input input-big" name="password" id="password"
                                       placeholder="登录密码" value=""/> <span
                                    class="icon icon-key margin-small"></span>
                            </div>
                        </div>

                        <div class="tips"><input type="checkbox" name="isRemember" value="1">记住我?
                            <select name="time">
                                <option value="1">1分钟</option>
                                <option value="2">1天</option>
                                <option value="3">1月</option>
                            </select></div>
                    </div>
                    <div style="padding:30px;">
                        <input type="button"
                               class="button button-block bg-main text-big input-big" onclick="btnLogin()"
                               value="登录">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    let msg = "${msg}";
    if (msg != "") {
        alert(msg);
    }
</script>
</body>
</html>
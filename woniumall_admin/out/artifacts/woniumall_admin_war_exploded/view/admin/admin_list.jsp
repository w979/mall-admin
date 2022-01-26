<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta charset="utf-8"/>
    <base href="${base}/"/>
    <title></title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <script type="text/javascript" href="../bootstrap/js/bootstrap.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head">
        <strong class="icon-reorder">管理员列表</strong>
    </div>
    <div class="padding border-bottom">
        <button type="button" class="button border-yellow"
                onclick="location.href='admin?opr=showAdd'">
            <span class="icon-plus-square-o"></span>增加管理员
        </button>
    </div>
    <table class="table table-hover text-center">
        <tr>
            <th>序号</th>
            <th>账号</th>
            <th>最后登录时间</th>
            <th>最后登录IP</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${pageInfo.list}" var="admin" varStatus="vs">
            <tr>
                <td>${vs.count}</td>
                <td>${admin.account}</td>

                <td><fmt:formatDate value="${admin.lastlogintime}" type="both"/></td>
                <td>${admin.lastloginip }</td>
                <td>
                    <span class="label label-default"></span>
                    <c:if test="${admin.status==1 }">
                        <span class="label label-success">正常</span>
                    </c:if>
                    <c:if test="${admin.status==2 }">
                        <span class="label label-danger">锁定</span>
                    </c:if>
                </td>
                <td>
                    <div class="button-group">
                        <a class="button border-main"
                           href="admin?opr=showUpdate&account=${admin.account}">
                            <span class="icon-edit"></span> 修改</a>

                            <a class="button border-red"
                                    <c:if test="${admin.status==2}"> disabled='true'</c:if>
                               href="javascript:delAdmin('${admin.account}')">
                                <span class="icon-trash-o"></span> 删除
                            </a>

                    </div>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="10">
                <div style="text-align: center">
                    <%--		分页--%>
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <li>
                                <%--					上一页--%>
                                <a href="javascript:prePage('${pageInfo.pageNum}','${pageInfo.pageSize}')"
                                   aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <%--				页面跳转--%>
                            <c:forEach begin="1" end="${pageInfo.pages}" var="pagenum">
                                <li><a href="javascript:goPage('${pagenum}','${pageInfo.pageSize}')">${pagenum}</a></li>
                            </c:forEach>

                            <li>
                                <%--					下一页--%>
                                <a href="javascript:nextPage('${pageInfo.pageNum}','${pageInfo.pageSize}','${pageInfo.pages}')"
                                   aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                            <li>
                                <a disabled="true">当前第 ${pageInfo.pageNum} 页 | 共 ${pageInfo.pages} 页 | ${pageInfo.total}
                                    条数据</a>
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
        if (parseInt(pageNo) > 1) {
            pageNo--;
            window.location.href = "admin?opr=list&pageNo=" + pageNo + "&pageSize=" + pageSize;
        }
    }

    //下一页
    function nextPage(pageNo, pageSize, pages) {
        if (parseInt(pageNo) < parseInt(pages)) {
            pageNo++;
            window.location.href = "admin?opr=list&pageNo=" + pageNo + "&pageSize=" + pageSize;
        }
    }

    //页码跳转
    function goPage(pageNo, pageSize) {
        window.location.href = "admin?opr=list&pageNo=" + pageNo + "&pageSize=" + pageSize;
    }



    //删除
    function delAdmin(account){
        if (confirm("确定锁定吗？")){
            window.location.href='admin?opr=del&account='+account;
        }
    }


    let msg='${msg}';
    if(msg!=null && msg!=""){
        alert(msg);
    }
    //删除Session
    <% request.getSession().removeAttribute("msg");%>

</script>
</body>
</html>
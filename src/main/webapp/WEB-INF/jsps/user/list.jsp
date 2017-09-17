<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="zhangfn" uri="http://github.com/zhangkaitao/tags/zhang-functions" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/css.css">
</head>
<body>

<c:if test="${not empty msg}">
    <div class="message">${msg}</div>
</c:if>

<shiro:hasPermission name="users:create">
    <a href="users/create">用户新增</a><br/>
</shiro:hasPermission>

<table class="table">
    <thead>
        <tr>
            <th>用户名</th>
            <th>员工姓名</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.username}</td>
                <td>${user.employee.name}</td>
                <td>
                	${user.dataStatus}
                </td>
                <td>
                    <shiro:hasPermission name="users:update">
                        <a href="users/${user.id}/update">修改</a>
                    </shiro:hasPermission>

                    <shiro:hasPermission name="users:delete">
                        <a href="users/${user.id}/delete">删除</a>
                    </shiro:hasPermission>

                    <shiro:hasPermission name="users:update">
                        <a href="users/${user.id}/changePassword">改密</a>
                    </shiro:hasPermission>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
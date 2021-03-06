<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login_action_period</title>
</head>
<body>
    <div align="center">
        <h3>Вход под своей учетной записью</h3>
        <c:out value="${error}"></c:out>
        <c:out value="${message}"></c:out>
        <c:out value="${userExist}"></c:out>
        <form id="formLogin" action="<c:url value="/login" />" method="POST">
            <table>
                <tr>
                    <td>username:</td>
                    <td><input type="text" name="username" value='' autofocus required></td>
                </tr>
                <tr>
                    <td>password:</td>
                    <td><input type="password" name="password" required/></td>
                </tr>
                <tr>
                    <td colspan='2'><input name="submit" type="submit" value="Войти"/></td>
                </tr>
            </table>
        </form>
    </div>

    <div align="right">
        <form action="./addUser" method="get">
            <button type="submit">Добавить нового пользователя</button>
        </form>
    </div>
</body>
</html>

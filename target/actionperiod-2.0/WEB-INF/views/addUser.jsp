<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>addUser</title>
</head>
<body>
    <div align="center">
        <form id="addUser" action="./addUser" method="post">
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
                    <td colspan='2'><input name="submit" type="submit" value="Добавить"/></td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>

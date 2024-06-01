<%--
  Created by IntelliJ IDEA.
  User: WYT
  Date: 2024/1/14
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>个人中心</title>
    <style>
        body {
            background: linear-gradient(to right,  #a6c1ee, #fbc2eb);
            font-family: Arial, sans-serif;
        }

        h1 {
            text-align: center;
            color: white;
        }

        form {
            text-align: center;
            margin-top: 20px;
        }

        input[type="submit"] {
            background-color:cadetblue;
            color: white;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
            font-size: 16px;
            margin-top: 10px;
            width: 200px;
        }

        input[type="submit"]:hover {
            background-color:#a6c1ee;
        }
    </style>
</head>
<body>
<h1>个人中心</h1>

<form action="personal-center" method="get">
    <input type="hidden" name="action" value="order-history">
    <input type="submit" value="历史订单">
</form>

<form action="personal-center" method="get">
    <input type="hidden" name="action" value="shopping-cart">
    <input type="submit" value="购物车">
</form>

<form action="personal-center" method="get">
    <input type="hidden" name="action" value="published-books">
    <input type="submit" value="已发布">
</form>

<form action="personal-center" method="get">
    <input type="hidden" name="action" value="soldOutBooks">
    <input type="submit" value="销售订单">
</form>
</body>
</html>
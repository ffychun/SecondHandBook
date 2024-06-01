<%@ page import="com.org.vo.Order_history" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ser.OrderHistoryServlet" %>
<%@ page import="com.org.vo.Order_history" %><%--
  Created by IntelliJ IDEA.
  User: WYT
  Date: 2024/1/14
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>历史订单</title>
    <style>
        body {
            background: linear-gradient(to right,  #a6c1ee, #fbc2eb);
            color: white;
            font-family: Arial, sans-serif;
        }

        h1 {
            text-align: center;
        }

        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid white;
        }

        img {
            width: 50px;
            height: 70px;
        }
    </style>
</head>
<body>
<h1>历史订单</h1>

<table>
    <tr>
        <th>书籍名称</th>
        <th>购买价格</th>
        <th>购买数量</th>
        <th>支付总价</th>
        <th>购买时间</th>
        <th>书籍图片</th>
    </tr>
    <%
        List<Order_history> orderHistoryList = (List<Order_history>) request.getAttribute("orderList");
        if (orderHistoryList != null) {
            for (Order_history orderHistory : orderHistoryList) {
    %>
    <tr>
        <td><%= orderHistory.getBookName() %></td>
        <td><%= String.format("%.2f", orderHistory.getPriceOrder()) %></td>
        <td><%= orderHistory.getAmountOrder() %></td>
        <td><%= String.format("%.2f", orderHistory.getPriceOrder()*orderHistory.getAmountOrder()) %></td>
        <td><%= orderHistory.getTime() %></td>
        <td><img src="<%= orderHistory.getPath() %>" alt="<%= orderHistory.getBookName() %>"></td>
    </tr>
    <%
        }
    } else {
    %>
    <!-- 处理 orderHistoryList 为 null 的情况，例如显示一个提示信息 -->
    <tr>
        <td colspan="5">暂无历史订单!</td>
    </tr>
    <%
        }
    %>

</table>
</body>
</html>
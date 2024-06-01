<%--
  Created by IntelliJ IDEA.
  User: Hou Xiujun
  Date: 2024/1/19
  Time: 2:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.org.vo.Order_history" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ser.OrderHistoryServlet" %>
<%@ page import="com.org.vo.Order_history" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>已售出图书</title>
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
            height: 30%; /* 设置表格高度为页面的30% */
        }

        th, td {
            padding: 10px;
            text-align: center;
            border-bottom: 1px solid white;
            vertical-align: middle; /* 表头文字垂直居中 */
        }

        img {
            display: block; /* 使图片垂直居中 */
            margin: auto; /* 使图片水平居中 */
            width: 280px;
            height: 280px;
            object-fit: contain; /* 不拉伸图片，保持原始比例填充 */
            border-radius: 10px;
        }

        th:nth-child(1), td:nth-child(1) {
            width: 10%;
        }

        th:nth-child(2), td:nth-child(2) {
            width: 10%;
        }

        th:nth-child(3), td:nth-child(3) {
            width: 10%;
        }

        th:nth-child(4), td:nth-child(4) {
            width: 20%;
        }

        th:nth-child(5), td:nth-child(5) {
            width: 20%;
        }
        th:nth-child(6), td:nth-child(6) {
            width: 30%;
        }
    </style>

</head>
<body>
<h1>已售出图书</h1>

<table>
    <tr>
        <th>书籍名称</th>
        <th>售出价格</th>
        <th>售出数量</th>
        <th>售出时间</th>
        <th>发货信息</th>
        <th>书籍图片</th>
    </tr>
    <%
        List<Order_history> orderHistoryList = (List<Order_history>) request.getAttribute("soldOutBooks");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(orderHistoryList != null && !orderHistoryList.isEmpty()) {
            for (Order_history orderHistory : orderHistoryList) {
    %>
    <tr>
        <td><%= orderHistory.getBookName() %></td>
        <td><%= String.format("%.2f", orderHistory.getPriceOrder()) %></td>
        <td><%= orderHistory.getAmountOrder() %></td>
        <td><%= sdf.format(orderHistory.getTime()) %></td>
        <td><%= orderHistory.getBuyerAddress() %></td>
        <td><img src="<%= orderHistory.getPath() %>" alt="<%= orderHistory.getBookName() %>"></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td align="center" colspan="6">暂无已售出图书！</td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
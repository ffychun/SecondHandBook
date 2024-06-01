<%@ page import="com.org.vo.Shoppingcartlist" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: WYT
  Date: 2024/1/16
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
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
<h1>购物车</h1>

<table>
    <tr>
        <th>书籍名称</th>
        <th>加购数量</th>
        <th>现价</th>
        <th>总价</th>
        <th>书籍图片</th>
        <th>详情</th>
        <th>购买</th>
        <th>删除</th>
    </tr>
    <%
        List<Shoppingcartlist> cartItemList = (List<Shoppingcartlist>) request.getAttribute("cartItemList");
        for (Shoppingcartlist cartItem : cartItemList) {if  (cartItem.getInventory()==0){
    %>
    <tr>
        <td><%= cartItem.getBookName() %></td>
        <td><%= cartItem.getAmount() %></td>
        <td><%= String.format("%.2f",cartItem.getPrice()) %></td>
        <td><%= String.format("%.2f",cartItem.getPrice()*cartItem.getAmount())%></td>
        <td><img src="<%= cartItem.getPath() %>"></td>
        <td><a href="showBookDetails.jsp?bookId=<%= cartItem.getBookId() %>">详情</a></td>
        <td>已售罄</td>
        <td><button onclick="deleteProduct(<%= cartItem.getBookId() %>)">删除</button></td>
    </tr>
    <%
    }
    else if(cartItem.getInventory()>0){
    %>
    <tr>
        <td><%= cartItem.getBookName() %></td>
        <td><%= cartItem.getAmount() %></td>
        <td><%= String.format("%.2f",cartItem.getPrice())%></td>
        <td><%= String.format("%.2f",cartItem.getPrice()*cartItem.getAmount())%></td>
        <td><img src="<%= cartItem.getPath() %>"></td>
        <td><a href="showBookDetails.jsp?bookId=<%= cartItem.getBookId() %>">详情</a></td>
        <td><a href="purchaseFromCart.jsp?bookId=<%= cartItem.getBookId() %>&quantity=<%= cartItem.getAmount() %>">购买</a></td>
        <td><button onclick="deleteProduct(<%= cartItem.getBookId() %>)">删除</button></td>
    </tr>
    <% }
    }%>
</table>

<script>
    function deleteProduct(bookId) {
        if (confirm('确定要删除吗？')) {
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4) {
                    if (xhr.status === 200) {
                        alert('删除成功');
                    } else {
                        alert('删除失败');
                    }
                }
            };
            xhr.open('GET', '${pageContext.request.contextPath}/Set-Inv?bookId=' + bookId, true);
            xhr.send();
        }
    }
</script>
</body>
</html>
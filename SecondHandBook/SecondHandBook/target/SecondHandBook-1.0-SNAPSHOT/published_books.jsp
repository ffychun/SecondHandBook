<%@ page import="com.org.vo.Book" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: WYT
  Date: 2024/1/17
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>已发布书籍</title>
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
<h1>已发布书籍</h1>

<table>
    <tr>
        <th>书籍名称</th>
        <th>库存</th>
        <th>现价</th>
        <th>书籍图片</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    <%
        List<Book> publishedBooks = (List<Book>) request.getAttribute("publishedBooks");
        for (Book book : publishedBooks) {if (book.getInventory()==0){
    %>
    <tr>
        <td><%= book.getBookName() %></td>
        <td><%= book.getInventory() %></td>
        <td><%= String.format("%.2f",book.getPrice()) %></td>
        <td><img src="<%= book.getPath() %>"></td>
        <td>已售罄</td>
        <td><button onclick="setInv(<%= book.getBookId() %>)">删除</button></td>
    </tr>
    <%
    }
    else if(book.getInventory()>0){
    %>
    <tr>
        <td><%= book.getBookName() %></td>
        <td><%= book.getInventory() %></td>
        <td><%= String.format("%.2f",book.getPrice()) %></td>
        <td><img src="<%= book.getPath() %>"></td>
        <td><button onclick="bookDetail(<%= book.getBookId() %>)">商品详情</button></td>
        <td><button onclick="setInv(<%= book.getBookId() %>)">删除</button></td>
    </tr>
    <% }
    }
    %>
</table>
<script>
    function bookDetail(bookID) {
        window.location.href = "showBookDetails.jsp?bookId=" + bookID;
    }
    function setInv(bookId) {
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
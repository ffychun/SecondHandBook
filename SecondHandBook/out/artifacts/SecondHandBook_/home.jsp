<%@ page import="com.org.vo.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="com.org.factory.DAOFactory" %>
<%@ page import="com.org.dao.BookDAO" %>
<%@ page import="com.org.dao.UserDAO" %>
<%--
  Created by IntelliJ IDEA.
  User: 范春
  Date: 2024/1/12
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>二手书交易系统</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        html {
            height: 100%;
        }

        body {
            height: 100%;
        }

        .container {
            height: 100%;
            background-image: linear-gradient(to right, #fbc2eb, #a6c1ee);
        }

        .status-bar {
            background-color: #f2f2f2;
            padding: 10px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .search-form {
            display: flex;
            align-items: center;
        }

        .search-form input[type="text"] {
            padding: 5px;
            border: none;
            border-radius: 3px;
        }

        .search-form button {
            padding: 5px 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        .add-book-button {
            padding: 5px 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        .bookContainer {
            padding: 20px;
            border: 2px solid #ccc;
            display: inline-block;
            text-align: center;
            box-shadow: 4px 4px 10px #888888;
            margin: 20px;
            width: 300px;
            height: 400px;
            background-color: #fff;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        table, th, td {
            border: 2px;
            margin-bottom: 10px;
        }
        h2 {
            text-align: center;
        }

        .bookImage {
            width: 230px;
            height: 230px;
            object-fit: contain; /* 不拉伸图片，保持原始比例填充 */
            margin-bottom: 10px; /* 底部留出一些空间 */
        }


        .a1 {
            background-color: #eec3ce;
            color: rgba(0, 0, 0, 0.99);
            padding: 10px;
            text-decoration: none;
            font-size: 16px;
            box-shadow: #665859 0px 0px 6px;
        }

        .a1:active{
            background-color: #bdbc9d;
        }

        .center-align {
            display: flex;
            justify-content: center;
            align-items: center;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="status-bar" style="background-color: #333;color: #fff;">
        <div style="flex:1;text-align: center;">
            <form action="SearchBookServlet" method="get">
                <input required="required" type="text" id="searchTerm" name="searchTerm" placeholder="输入书名" style="padding: 5px;border: none;border-radius: 3px;">
                <button type="submit" style="padding: 5px 10px;background-color: #4CAF50;color: white;border: none;border-radius: 3px;cursor:pointer;">搜索</button>
            </form>
        </div>
        <div>
            <button class="add-book-button" onclick="window.location.href='addBook.jsp'" type="button">发布图书</button>
        </div>
        <div style="margin: 10px;"></div>
        <div>
            <button class="add-book-button" onclick="window.location.href='personal_center.jsp'" type="button">个人中心</button>
        </div>
    </div>
    <%
        BookDAO bookDAO = DAOFactory.getBookDAOInstance();
        List<Book> book = bookDAO.findBook();

        for(Book bk: book){
    %>
    <div class="bookContainer">
        <table>
            <tr>
                <td align="left"><strong>书籍名称</strong></td>
                <td align="left"><%= bk.getBookName() %></td>
            </tr>
            <tr>
                <td align="left"><strong>价格</strong></td>
                <td align="left">¥<%= String.format("%.2f", bk.getPrice()) %></td>
            </tr>
            <tr>
                <td align="left"><strong>售卖者</strong></td>
                <td align="left"><%=DAOFactory.getUserDAOInstance().findUser(bk.getuId()).getuName()%></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <img class="bookImage" src="<%= bk.getPath() %>" alt="<%= bk.getBookName() %>">
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <button onclick="bkDetail(<%= bk.getBookId() %>)">商品详情</button>
                </td>
            </tr>
        </table>
    </div>
    <%
        }
    %>
    <script>
        function bkDetail(bookID) {
            window.location.href = "showBookDetails.jsp?bookId=" + bookID;
        }
    </script>
</div>
</body>
</html>
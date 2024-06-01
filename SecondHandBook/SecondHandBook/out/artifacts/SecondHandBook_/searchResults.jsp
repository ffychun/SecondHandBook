<%@ page import="com.org.vo.Book" %>
<%@ page import="com.org.vo.User" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>搜索结果</title>
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
            margin: 0;
            padding: 0;
            background-image: linear-gradient(to right, #fbc2eb, #a6c1ee);
            background-attachment: fixed;
            background-size: cover;
        }
        /* 设置容器的大小和布局 */
        .outerContainer {
            width: 90%; /* 设置宽度，可以根据需要调整百分比 */
            margin: 0 auto; /* 左右居中 */
            text-align: center; /* 内容居中 */
        }
        .bookContainer {
            border-radius: 15px;
            padding: 20px; /* 增加内边距 */
            display: inline-block; /* 水平排列 */
            text-align: center; /* 文本居中 */
            margin: 20px auto; /* 外边距 */
            width: 25%; /* 设置每个子容器的宽度，可以根据需要调整百分比 */
            position: relative;
            background-color: #fff; /* 设置背景颜色 */
        }
        table {
            width: 100%; /* 宽度占满父容器 */
            border-collapse: collapse; /* 合并单元格边框 */
        }
        table, th, td {
            word-break: break-all; /* 自适应文字 */
            border: none; /* 去除表格边框 */
            margin-bottom: 10px;
        }
        td:last-child {
            padding-bottom: 5px; /* 调整按钮与单元格底部之间的距离 */
        }
        h1 {
            text-align: center; /* 标题居中 */
        }
        /* 设置书籍图片的样式 */
        .bookImage {
            width: 250px;
            height: 280px;
            object-fit: contain; /* 不拉伸图片，保持原始比例填充 */
            margin-bottom: 10px;
            border-radius: 10px;
        }
        .btn {
            text-align: center;
            background-color: #a6c1ee;
            color: #fff;
            border-radius: 8px;
            border: none; /* 去除边框 */
            width: 80px; /* 宽度变为默认值的1.5倍 */
            height: 40px; /* 高度变为默认值的1.5倍 */
            font-size: 1em; /* 字体大小变大 */
        }

    </style>
</head>
<body>
<br>
<h1>搜索结果</h1>
<div class="outerContainer">
    <%
        // 获取查询结果
        List<Book> searchResults = (List<Book>) request.getAttribute("searchResults");
        List<User> sellerResults = (List<User>) request.getAttribute("sellerResults");
        if(searchResults.isEmpty()){
    %>
    <div class="bookContainer">
        <p style="text-align: center;">暂无搜索结果</p>
    </div>
    <%
    }else{
        // 展示查询结果
        for (int i = 0; i < sellerResults.size(); i++) {
    %>
    <div class="bookContainer">
        <table>
            <tr>
                <td width="80px" align="left"><strong>书籍名称:</strong></td>
                <td align="center"><%= searchResults.get(i).getBookName() %></td>
            </tr>
            <tr>
                <td width="80px" align="left"><strong>书籍价格:</strong></td>
                <td align="center"><%= String.format("%.2f", searchResults.get(i).getPrice()) %></td>
            </tr>
            <tr>
                <td width="80px" align="left"><strong>卖家名称:</strong></td>
                <td align="center"><%= sellerResults.get(i).getuName() %></td>
            </tr>
            <tr>
                <td colspan="2">
                    <!-- 展示图片 -->
                    <img class="bookImage" src="<%= searchResults.get(i).getPath() %>" alt="<%= searchResults.get(i).getBookName() %>">
                </td>
            </tr>
            <tr>
                <td colspan="2" >
                    <!-- 详情按钮，跳转到展示详细信息的页面,showBookDetails.jsp就是商品详情页的文件名-->
                    <button class="btn" onclick="showDetails(<%= searchResults.get(i).getBookId() %>)">商品详情</button>
                </td>
            </tr>
        </table>
    </div>
    <%
            }
        }
    %>
</div>
<script>
    function showDetails(bookID) {
        window.location.href = "showBookDetails.jsp?bookId=" + bookID;
    }
</script>
</body>
</html>
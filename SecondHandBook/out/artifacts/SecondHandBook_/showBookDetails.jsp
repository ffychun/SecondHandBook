<%@ page import="com.org.vo.Book" %>
<%@ page import="com.org.dao.BookDAO" %>
<%@ page import="com.org.factory.DAOFactory" %>
<%@ page import="com.org.dao.UserDAO" %>
<%@ page import="com.org.vo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品详情</title>
    <style>
        /* 添加样式表 */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
        }

        .container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
            background-image: linear-gradient(to right, #fbc2eb, #a6c1ee);
        }

        .image-container {
            flex: 1;
            margin-right: 20px;
        }

        .image-container img {
            width: 100%;
            max-width: 300px;
            height: auto;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            border-radius: 5px;
        }

        .details-container {
            flex: 1;
        }

        .details-container h2 {
            font-size: 24px;
            margin-bottom: 10px;
        }

        .details-container p {
            margin-bottom: 10px;
        }

        .details-container label {
            margin-right: 10px;
        }

        .details-container input[type="number"] {
            width: 60px;
            padding: 5px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        .details-container button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .details-container button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<%
    int userId = (int) request.getSession().getAttribute("userId");
%>
<input type="hidden" id="buyerId" value="<%= userId %>">
<%
    String bookId = request.getParameter("bookId");
    int bookid = Integer.parseInt(bookId);
    BookDAO bookDAO = DAOFactory.getBookDAOInstance();
    Book bookDetails = bookDAO.findBook(bookid);

    UserDAO userDAO = DAOFactory.getUserDAOInstance();
    User user = userDAO.findUser(bookDetails.getuId());
%>
<div class="container">
    <div class="image-container">
        <img src="<%= bookDetails.getPath() %>" alt="<%= bookDetails.getBookName() %>">
    </div>
    <div class="details-container">
        <h2><%= bookDetails.getBookName() %></h2>
        <p>简介：<%= bookDetails.getNote() %></p>
        <p>售价：￥<%=bookDetails.getPrice()%></p>
        <p>卖家：<%=user.getuName()%></p>
        <p>库存：<%=bookDetails.getInventory()%></p>
        <label for="quantity">购买数量：</label>
        <input type="number" required="required" id="quantity" name="quantity"
               oninput="if(parseInt(value) < 1) value = '1'; if(parseInt(value) > <%=bookDetails.getInventory()%>) value = <%=bookDetails.getInventory()%>"
               min="1" max="<%=bookDetails.getInventory()%>" value="1">
        <br><br>
        <button onclick="addToCart()">加入购物车</button>
        <button onclick="buyNow()">立即购买</button>
    </div>
</div>

<script>
    let quantity = 1; // 初始化 quantity 为 1
    document.getElementById("quantity").addEventListener("input", function() {
        quantity = this.value;
    });
    let buyerId = <%= userId %>;
    let bookID = <%= bookDetails.getBookId()%>; //获取欲购买书的id
    function addToCart() {
        if(quantity !== "" && parseInt(quantity) > 0){
            // 创建XMLHttpRequest对象
            let xhr = new XMLHttpRequest();

            // 设置请求参数
            let url = "addToCart.jsp"; // 替换为处理添加购物车请求的JSP页面
            let params = "buyerId=" + buyerId + "&quantity=" + quantity +"&bookId=" + bookID;
            xhr.open("POST", url, true);
            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

            // 处理响应
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    // 添加购物车成功，执行相应操作
                    alert("已加入购物车");
                }
            }
            // 发送请求
            xhr.send(params);
        }else{
            alert("请输入有效的购买数量");
        }
    }
    function buyNow() {
        if(quantity !== "" && parseInt(quantity) > 0){
            // 重定向到确认订单页面，并传递商品ID和购买数量
            window.location.href = "confirmOrder.jsp?bookId=" + bookID + "&quantity=" + quantity;
        }else{
            alert("请输入有效的购买数量");
        }

    }
</script>
</body>
</html>
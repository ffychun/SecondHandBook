<%@ page import="com.org.vo.Book" %>
<%@ page import="com.org.dao.BookDAO" %>
<%@ page import="com.org.factory.DAOFactory" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>订单确认</title>
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
        table {
            border-collapse: collapse;
            width: 500px; /* 修改表格宽度 */
            margin: auto;
        }
        table td, table th {
            border: none;
            font-weight: bold;
            font-size: 18px;
            text-align: center;
            line-height: 40px;
        }

        textarea {
            width: 80%;
        }

        textarea {
            resize: vertical; /* 允许垂直改变文本域大小 */
            height: auto; /* 高度自适应内容 */
            min-height: 100px; /* 设置最小高度 */
        }
        .container {
            display: flex;
            flex-direction: column; /* 垂直布局 */
            justify-content: center; /* 水平居中 */
            align-items: center; /* 垂直居中 */
            height: 100%;
        }
        .addContainer {
            border-radius: 15px;
            padding: 20px;
            text-align: center;
            width: 500px;
            position: absolute; /* 绝对定位 */
            top: 50%; /* 以父容器为参考基准向下偏移 50% */
            left: 50%; /* 以父容器为参考基准向右偏移 50% */
            transform: translate(-50%, -50%); /* 使用 CSS3 transform 属性来使子元素在垂直和水平方向上都居中 */
            background-color: #fff;
        }


        .btn {
            text-align: center;
            background-color: #a6c1ee;
            color: #fff;
            border-radius: 8px;
            border: none;
            width: 90px;
            height: 45px;
            font-size: 1em; /* 字体大小变大 */
        }

    </style>
    <%
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        BookDAO bookDAO = DAOFactory.getBookDAOInstance();
        Book book = bookDAO.findBook((int)bookId);

    %>
    <script>
        // 页面加载时计算并显示初始的总金额
        document.addEventListener("DOMContentLoaded", function() {
            var quantityInput = document.getElementById("quantity");
            // 添加事件监听器，当输入改变时触发计算
            quantityInput.addEventListener("input", function() {
                calculateTotal();
            });

            // 初始化计算
            calculateTotal();
        });

        // 处理用户输入导致的总金额变化
        function calculateTotal() {
            var quantityValue = document.getElementById("quantity").value;
            var quantity = quantityValue === '' ? 0 : parseInt(quantityValue); // 获取购买数量，并转换为整数；如果为空则设置为0
            var unitPrice = <%= book.getPrice() %>; // 假设书的单价存储在price属性中
            updateTotal(quantity, unitPrice);
        }

        // 更新总金额
        function updateTotal(quantity, unitPrice) {
            var totalAmount = (quantity * unitPrice).toFixed(2); // 计算总金额并保留两位小数
            document.getElementById("totalAmount").innerText = "￥" + totalAmount; // 添加 "￥" 符号
        }

    </script>
</head>
<body>
<br>
<h1 style="text-align: center;">订单确认</h1>
<div class="container">
    <br>
    <div class="addContainer">
        <form action="PurchaseFromCartServlet" method="post">
            <input type="hidden" name="bookId" value="<%= book.getBookId() %>">
            <table>
                <tr>
                    <td width="120px">书籍名称:</td>
                    <td style="text-align: left;"><%= book.getBookName() %></td>
                </tr>
                <tr>
                    <td width="120px"><label for="quantity">购买数量:</label></td>
                    <td style="text-align: left;">
                        <input type="number" required="required" id="quantity" name="quantity"
                               oninput="if(parseInt(value) < 1) value = '1'; if(parseInt(value) > <%=book.getInventory()%>) value = <%=book.getInventory()%>"
                               min="1" max="<%=book.getInventory()%>" value="<%= quantity %>">
                    </td>
                </tr>
                <tr>
                    <td width="120px">总金额:</td>
                    <td style="text-align: left;"><p id="totalAmount"><%= book.getPrice() %></p></td>
                </tr>
                <tr>
                    <td width="120px"><label for="address">收货信息:</label></td>
                    <td style="text-align: left;">
                    <textarea id="address" required="required" name="address" rows="12"
                          placeholder="请输入收货人姓名、收货人手机号码、收货人地址"></textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="2"><input class="btn" type="submit" value="确认购买" style="width: 90px; height: 45px;"></td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
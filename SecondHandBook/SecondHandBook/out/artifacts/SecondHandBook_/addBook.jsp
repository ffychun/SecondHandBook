<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>发布图书</title>
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

        input, textarea {
            width: 80%;
        }

        textarea {
            resize: vertical; /* 允许垂直改变文本域大小 */
            height: auto; /* 高度自适应内容 */
            min-height: 100px; /* 设置最小高度 */
        }
        .container {
            height: 100%;
            background-image: linear-gradient(to right, #fbc2eb, #a6c1ee);
        }
        .addContainer {
            border-radius: 15px;
            padding: 20px; /* 增加内边距 */
            display: block; /* 修改为块级元素，以便居中显示 */
            text-align: center; /* 文本居中 */
            margin: 20px auto; /* 上下外边距为20px，左右外边距自动，实现居中 */
            width: 500px; /* 设置每个子容器的宽度，可以根据需要调整百分比 */
            position: relative;
            background-color: #fff; /* 设置背景颜色 */
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
        #imagePreview {
            max-width: 400px; /* 设置最大宽度 */
            max-height: 300px; /* 设置最大高度 */
            overflow: hidden; /* 隐藏溢出部分 */
        }

    </style>
    <script>
        function previewImage(input, previewId) {
            var preview = document.getElementById(previewId);
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function(e) {
                    preview.innerHTML = '<img src="' + e.target.result + '" style="max-width:400px; max-height:290px;" />';
                }
                reader.readAsDataURL(input.files[0]);
            }
        }
    </script>

</head>
<body>
<div class="container">
    <br>
    <h1 style="text-align: center;">发布图书</h1>
    <div class="addContainer">
        <form action="AddBookServlet" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td width="80px"><label for="bookName">书籍名称</label></td>
                    <td><input type="text" required="required" id="bookName" name="bookName"></td>
                </tr>
                <tr>
                    <td width="80px"><label for="inventory">书籍库存</label></td>
                    <td><input type="number" required="required" id="inventory" min="0" name="inventory"></td>
                </tr>
                <tr>
                    <td width="80px"><label for="price">书籍价格</label></td>
                    <td><input type="number" required="required" id="price" min="0.00" step="0.01" name="price"></td>
                </tr>
                <tr>
                    <td width="80px"><label for="note">书籍简介</label></td>
                    <td><textarea id="note" required="required" name="note" rows="8"></textarea></td> <!-- 增加可容纳的行数到8 -->
                </tr>
                <tr>
                    <td width="80px"><label for="picture">上传图片</label></td>
                    <td><input type="file" required="required" id="picture" name="picture" onchange="previewImage(this, 'imagePreview')"></td>
                </tr>
                <tr>
                    <td width="80px"><label>图片预览</label></td>
                    <td style="text-align: left;" height="300px" colspan="2" style="text-align: center;"><div id="imagePreview"></div></td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;"><input class="btn" type="submit" value="确认发布" style="width: 90px; height: 45px;"></td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
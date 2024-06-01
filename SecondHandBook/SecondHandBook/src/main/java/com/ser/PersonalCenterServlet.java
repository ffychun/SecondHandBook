package com.ser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/personal-center")
public class PersonalCenterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            // 默认展示个人中心页面
            response.sendRedirect("/personal_center.jsp");
        } else if (action.equals("order-history")) {
            // 跳转到历史订单页面
            request.getRequestDispatcher("/order-history").forward(request, response);
        } else if (action.equals("shopping-cart")) {
            // 跳转到购物车页面
            request.getRequestDispatcher("/shopping-cart").forward(request, response);
        } else if (action.equals("published-books")) {
            // 跳转到已发布书籍页面
            request.getRequestDispatcher("/published-books").forward(request, response);
        }else if (action.equals("soldOutBooks")) {
            // 跳转到已售出图书页面
            request.getRequestDispatcher("/soldOutBookServlet").forward(request, response);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
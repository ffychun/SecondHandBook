package com.ser;

import com.org.dao.OrderDAO;

import com.org.factory.DAOFactory;
import com.org.vo.Order_history;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/order-history")
public class OrderHistoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取用户ID
        try {
            int userId = getUserIdFromSession(request);
            OrderDAO orderDAO = DAOFactory.getOrderDAOInstance();

            // 调用方法查询并获取历史订单数据
            List<Order_history> orderHistoryList = new ArrayList<>();
            orderHistoryList.addAll(orderDAO.findAll(userId));
            request.setAttribute("orderList", orderHistoryList);
            // 转发到历史订单页面
            request.getRequestDispatcher("/order_history.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int getUserIdFromSession(HttpServletRequest request) {
        // 从会话中获取用户ID，此处仅作示例
        int userId = (int)request.getSession().getAttribute("userId");
        return userId;
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}


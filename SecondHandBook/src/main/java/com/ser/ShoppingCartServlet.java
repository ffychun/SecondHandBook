package com.ser;

import com.org.dao.ShoppingCartDAO;
import com.org.vo.Shoppingcartlist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.org.factory.DAOFactory;

@WebServlet("/shopping-cart")
public class ShoppingCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{// 获取用户ID
        int userId = getUserIdFromSession(request);

        // 调用方法查询并获取购物车数据
        List<Shoppingcartlist> cartItemList = new ArrayList<>();
        ShoppingCartDAO shoppingcartDAO = DAOFactory.getShoppingCartDAOInstance();
        cartItemList.addAll(shoppingcartDAO.findAll(userId));

        // 将购物车数据传递给JSP页面
        request.setAttribute("cartItemList", cartItemList);

        // 转发到购物车页面
        request.getRequestDispatcher("/shopping_cart.jsp").forward(request, response);}
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }



    private int getUserIdFromSession(HttpServletRequest request) {
        // 从会话中获取用户ID，此处仅作示例
        int userId = (int)request.getSession().getAttribute("userId");
        return userId;
    }
}

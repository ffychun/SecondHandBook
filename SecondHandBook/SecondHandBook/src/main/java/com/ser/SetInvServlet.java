package com.ser;

import com.org.dao.BookDAO;
import com.org.dao.ShoppingCartDAO;
import com.org.factory.DAOFactory;
import com.org.vo.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Set-Inv")
public class SetInvServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // 获取要删除的bookId参数
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            int userId = getUserIdFromSession(request);
            BookDAO bookDAO = DAOFactory.getBookDAOInstance();
            // 获取所有商品信息
            bookDAO.alterBook(bookId,-1);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("删除成功!");

        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("删除失败!");
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

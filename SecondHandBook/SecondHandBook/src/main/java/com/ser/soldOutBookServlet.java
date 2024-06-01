package com.ser;

import com.org.dao.BookDAO;
import com.org.dao.OrderDAO;
import com.org.factory.DAOFactory;
import com.org.vo.Book;
import com.org.vo.Order_history;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "soldOutBookServlet",urlPatterns = "/soldOutBookServlet")
public class soldOutBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        try{
            int userId = (int)request.getSession().getAttribute("userId");
            BookDAO bookDAO = DAOFactory.getBookDAOInstance();
            List<Book> bookList = new ArrayList<>();
            List<Order_history> orderList = new ArrayList<>();
            bookList.addAll(bookDAO.findAll(userId));
            for (Book bk: bookList) {
                OrderDAO orderDAO = DAOFactory.getOrderDAOInstance();
                orderList.addAll(orderDAO.findByBookId(bk.getBookId()));
            }
            request.setAttribute("soldOutBooks", orderList);
            request.getRequestDispatcher("soldOutBooks.jsp").forward(request, response);}
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

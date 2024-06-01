package com.ser;

import com.org.dao.BookDAO;
import com.org.factory.*;
import com.org.vo.Book;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DisplayBookServlet", value = "/DisplayBookServlet")
public class DisplayBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            // 获取 BookDAO 实例
            BookDAO bookDAO = DAOFactory.getBookDAOInstance();
            //System.out.println(1);
            // 获取所有商品信息
            List<Book> books = bookDAO.findBook();
            //System.out.println(2);
            // 将商品信息放入 request 属性中
            request.setAttribute("books", books);
            //System.out.println(3);
            // 转发到主页 JSP 页面
            RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
            //System.out.println(4);
            dispatcher.forward(request, response);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}

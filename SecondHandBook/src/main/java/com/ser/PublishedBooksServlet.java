package com.ser;

import com.org.dao.BookDAO;
import com.org.factory.DAOFactory;
import com.org.vo.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/published-books")
public class PublishedBooksServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取用户ID

        try{
        int userId = getUserIdFromSession(request);
        BookDAO bookDAO = DAOFactory.getBookDAOInstance();

        // 调用方法查询并获取历史订单数据
        List<Book> bookList = new ArrayList<>();
        Book order=new Book();
        bookList.addAll(bookDAO.findAll(userId));
        // 调用方法查询并获取已发布书籍数据

        // 将已发布书籍数据传递给JSP页面
        request.setAttribute("publishedBooks", bookList);

        // 转发到已发布书籍页面
        request.getRequestDispatcher("/published_books.jsp").forward(request, response);}
        catch (Exception e) {
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

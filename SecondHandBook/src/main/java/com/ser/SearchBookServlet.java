package com.ser;

import com.org.dao.BookDAO;
import com.org.dao.UserDAO;
import com.org.factory.DAOFactory;
import com.org.vo.Book;
import com.org.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchBookServlet", urlPatterns = "/SearchBookServlet")
public class SearchBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            BookDAO bookDAO = DAOFactory.getBookDAOInstance();
            String searchTerm = request.getParameter("searchTerm");
            List<Book> searchResults = bookDAO.findBook(searchTerm);
            List<User> sellerResults = new ArrayList<>();
            for (Book book : searchResults) {
                UserDAO userDAO = DAOFactory.getUserDAOInstance();
                User seller = userDAO.findUser(book.getuId());
                sellerResults.add(seller);
            }
            // 保存查询结果
            request.setAttribute("searchResults", searchResults);
            request.setAttribute("sellerResults", sellerResults);
            // 转发请求到JSP页面
            request.getRequestDispatcher("searchResults.jsp").forward(request, response);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

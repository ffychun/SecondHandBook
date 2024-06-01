package com.ser;


import com.org.dao.BookDAO;
import com.org.dao.OrderDAO;
import com.org.factory.DAOFactory;
import com.org.vo.Book;
import com.org.vo.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "SubmitOrderServlet",urlPatterns = "/SubmitOrderServlet")
public class SubmitOrderServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("utf8");
        try {
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String  address = request.getParameter("address");
            HttpSession session = request.getSession();
            int userId = (int) session.getAttribute("userId");

            BookDAO bookDAO = DAOFactory.getBookDAOInstance();
            Book book = bookDAO.findBook((int)bookId);

            if(book.getInventory() >= quantity){
                Order newOrder = new Order();
                newOrder.setAmountOrder(quantity);
                newOrder.setBookIdOrder(bookId);
                newOrder.setPriceOrder((float)book.getPrice());
                newOrder.setuIdOrder(userId);
                newOrder.setTime(new Date());
                newOrder.setBuyerAddress(address);
                // 将订单信息插入order表
                OrderDAO orderDAO = DAOFactory.getOrderDAOInstance();
                orderDAO.addOrder(newOrder);
                //修改书的库存
                BookDAO bookDAO2 = DAOFactory.getBookDAOInstance();
                bookDAO2.alterBook(book.getBookId(),book.getInventory()-quantity);

                response.sendRedirect("operateSucceed.jsp");

            }else{
                out.println("库存不足");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}


<%@ page import="com.org.dao.ShoppingCartDAO" %>
<%@ page import="com.org.factory.DAOFactory" %>
<%@ page import="com.org.dao.BookDAO" %>
<%@ page import="com.org.vo.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int buyerId = Integer.parseInt(request.getParameter("buyerId"));
    int quantity = Integer.parseInt(request.getParameter("quantity"));
    int bookID = Integer.parseInt(request.getParameter("bookId"));
    try{
    // 创建购物车DAO对象
    ShoppingCartDAO cartDAO = DAOFactory.getShoppingCartDAOInstance();

    BookDAO bookDAO = DAOFactory.getBookDAOInstance();
    Book book = bookDAO.findBook(bookID);

    // 将商品添加到购物车
    cartDAO.addShoppingCart(buyerId, bookID, quantity);}
    catch(Exception e){
        throw e;
    }


%>

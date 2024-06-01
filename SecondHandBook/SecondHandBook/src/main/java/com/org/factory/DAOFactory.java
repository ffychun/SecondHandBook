package com.org.factory;

import com.org.dao.*;
import com.org.dao.proxy.BookDAOProxy;
import com.org.dao.proxy.OrderDAOProxy;
import com.org.dao.proxy.ShoppingCartDAOProxy;
import com.org.dao.proxy.UserDAOProxy;

public class DAOFactory {
    public static UserDAO getUserDAOInstance() throws Exception{
        return new UserDAOProxy();
    }
    public static OrderDAO getOrderDAOInstance() throws Exception{
        return new OrderDAOProxy();
    }
    public static ShoppingCartDAO getShoppingCartDAOInstance() throws Exception{
        return new ShoppingCartDAOProxy();
    }
    public static BookDAO getBookDAOInstance() throws Exception{
        return new BookDAOProxy();
    }
}

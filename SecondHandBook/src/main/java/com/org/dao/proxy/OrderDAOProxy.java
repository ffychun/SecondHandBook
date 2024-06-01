package com.org.dao.proxy;

import com.org.dao.OrderDAO;
import com.org.dao.impl.OrderDAOImpl;
import com.org.dbc.DatabaseConnection;
import com.org.vo.Order;
import com.org.vo.Order_history;


import java.util.List;

public class OrderDAOProxy implements OrderDAO {
    private DatabaseConnection dbc = null;
    private OrderDAO dao = null;

    public OrderDAOProxy() throws Exception{
        this.dbc = new DatabaseConnection();
        this.dao = new OrderDAOImpl(this.dbc.getConnection());
    }

    public List<Order_history> findAll(int uId) throws Exception{
        List<Order_history> all = null;
        try{
            all = this.dao.findAll(uId);
        }
        catch (Exception e){
            throw e;
        }
        finally{
            this.dbc.close();
        }
        return all;
    }
    public List<Order_history> findByBookId(int bookId) throws Exception{
        List<Order_history> all = null;
        try{
            all = this.dao.findByBookId(bookId);
        }
        catch (Exception e){
            throw e;
        }
        finally{
            this.dbc.close();
        }
        return all;
    }
    public void addOrder(Order order) throws Exception{
        try{
            this.dao.addOrder(order);
        }
        catch (Exception e){
            throw e;
        }
        finally{
            this.dbc.close();
        }
    }
}


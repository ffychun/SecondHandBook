package com.org.dao;

import com.org.vo.Order;
import com.org.vo.Order_history;

import java.util.List;

public interface OrderDAO {
    public List<Order_history> findAll(int uId) throws Exception;
    public List<Order_history> findByBookId(int bookId) throws Exception;
    public void addOrder(Order order) throws Exception;
}

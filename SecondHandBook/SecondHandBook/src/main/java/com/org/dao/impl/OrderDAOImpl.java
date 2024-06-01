package com.org.dao.impl;

import com.org.dao.OrderDAO;
import com.org.vo.Order;
import com.org.vo.Order_history;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    public OrderDAOImpl(Connection conn) {
        super();
        this.conn = conn;
    }

    public List<Order_history> findAll(int uId) throws Exception {
        List<Order_history> all = new ArrayList<>();
        String sql = "SELECT * FROM `order`, book WHERE book.book_id = `order`.book_id_order;";
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
        Order_history orderHistory = null;

        while (rs.next()) {
            orderHistory = new Order_history();
            orderHistory.setuIdOrder(rs.getInt(1));
            orderHistory.setBookIdOrder(rs.getInt(2));
            orderHistory.setTime(rs.getDate(3));
            orderHistory.setPriceOrder(rs.getFloat(4));
            orderHistory.setAmountOrder(rs.getInt(5));
            orderHistory.setBuyerAddress(rs.getString(6));
            orderHistory.setPath(rs.getString("path"));
            orderHistory.setBookName(rs.getString("book_name"));
            if (orderHistory.getuIdOrder() == uId) {

                all.add(orderHistory);
            }
        }
        return all;
    }
    public List<Order_history> findByBookId(int bookId) throws Exception {
        List<Order_history> orders = new ArrayList<>();
        String sql = "SELECT * FROM `order`, book WHERE book.book_id = `order`.book_id_order AND `order`.book_id_order = ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, bookId);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            Order_history orderHistory = new Order_history();
            orderHistory.setuIdOrder(rs.getInt(1));
            orderHistory.setBookIdOrder(rs.getInt(2));
            orderHistory.setTime(rs.getTimestamp(3));
            orderHistory.setPriceOrder(rs.getFloat(4));
            orderHistory.setAmountOrder(rs.getInt(5));
            orderHistory.setBuyerAddress(rs.getString(6));
            orderHistory.setPath(rs.getString("path"));
            orderHistory.setBookName(rs.getString("book_name"));
            orders.add(orderHistory);
        }
        return orders;
    }

    public void addOrder(Order order) throws Exception{
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO `order` (u_id_order, book_id_order, time, price_order, amount_order, buyer_address) VALUES (?, ?, ?, ?, ?,?)";
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, order.getuIdOrder());
        pstmt.setInt(2, order.getBookIdOrder());
        pstmt.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
        pstmt.setDouble(4, order.getPriceOrder());
        pstmt.setInt(5, order.getAmountOrder());
        pstmt.setString(6, order.getBuyerAddress());
        pstmt.executeUpdate();
    }
}



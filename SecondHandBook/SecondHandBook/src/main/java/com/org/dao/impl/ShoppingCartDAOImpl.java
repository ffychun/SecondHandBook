package com.org.dao.impl;

import com.org.dao.ShoppingCartDAO;
import com.org.vo.Shoppingcartlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDAOImpl implements ShoppingCartDAO {
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    public ShoppingCartDAOImpl(Connection conn) {
        super();
        this.conn = conn;
    }

    public List<Shoppingcartlist> findAll(int uId) throws Exception {
        List<Shoppingcartlist> all = new ArrayList<>();
        String sql = "select * from shopping_cart,book where shopping_cart.book_id=book.book_id";
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
        Shoppingcartlist shoppingCart = null;

        while (rs.next()) {
            shoppingCart= new Shoppingcartlist();
            shoppingCart.setuIdCart(rs.getInt("u_id_cart"));
            shoppingCart.setAmount(rs.getInt("amount"));
            shoppingCart.setBookId(rs.getInt("book_id"));
            shoppingCart.setBookName(rs.getString("book_name"));
            shoppingCart.setPath(rs.getString("path"));
            shoppingCart.setPrice(rs.getFloat("price"));
            shoppingCart.setInventory(rs.getInt("inventory"));
            if (shoppingCart.getuIdCart() == uId) {

                all.add(shoppingCart);
            }
        }
        return all;
    }
    public void delete(int uId,int BookId) throws Exception{
        String sql = "DELETE FROM shopping_cart WHERE u_id_cart = ? AND book_id = ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, uId);
        this.pstmt.setInt(2, BookId);
        this.pstmt.executeUpdate();
    }
    @Override
    public void addShoppingCart(int buyerId, int sellerId, int quantity) throws Exception {
        String sql = "insert into shopping_cart values(?,?,?)";
        this.pstmt = this.conn.prepareStatement(sql);

        pstmt.setInt(1,buyerId);
        pstmt.setInt(2,sellerId);
        pstmt.setInt(3,quantity);

        pstmt.executeUpdate();
        pstmt.close();
    }
}

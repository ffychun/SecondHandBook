package com.org.dao;

import com.org.vo.Shoppingcartlist;

import java.util.List;

public interface ShoppingCartDAO {
    public List<Shoppingcartlist> findAll(int uId) throws Exception;
    public void delete(int uId,int BookId) throws Exception;
    public void addShoppingCart(int buyerId, int sellerId, int quantity) throws Exception;
}

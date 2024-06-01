package com.org.dao.proxy;

import com.org.dao.ShoppingCartDAO;
import com.org.dao.impl.ShoppingCartDAOImpl;
import com.org.dbc.DatabaseConnection;
import com.org.vo.Shoppingcartlist;

import java.util.List;

public class ShoppingCartDAOProxy implements ShoppingCartDAO {
    private DatabaseConnection dbc = null;
    private ShoppingCartDAO dao = null;

    public ShoppingCartDAOProxy() throws Exception{
        this.dbc = new DatabaseConnection();
        this.dao = new ShoppingCartDAOImpl(this.dbc.getConnection());
    }

    public List<Shoppingcartlist> findAll(int uId) throws Exception{
        List<Shoppingcartlist> all = null;
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
    public void delete(int uId,int BookId) throws Exception{
        try{
            this.dao.delete(uId,BookId);
        }
        catch (Exception e){
            throw e;
        }
        finally{
            this.dbc.close();
        }
    }
    public void addShoppingCart(int buyerId, int sellerId, int quantity) throws Exception {
        try{
            this.dao.addShoppingCart(buyerId, sellerId, quantity);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}


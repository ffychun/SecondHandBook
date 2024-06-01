package com.org.vo;

public class Shopping_cart {
    private int uIdCart; //用户id
    private int bookId; //添加到购物车的商品id
    private int amount; //购物车中欲购买数量

    public int getuIdCart() {
        return uIdCart;
    }

    public void setuIdCart(int uIdCart) {
        this.uIdCart = uIdCart;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Shopping_cart{" +
                "uIdCart=" + uIdCart +
                ", bookId=" + bookId +
                ", amount=" + amount +
                '}';
    }
}

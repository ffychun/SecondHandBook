package com.org.vo;

public class Shoppingcartlist {
    private int uIdCart; //用户id
    private int bookId; //添加到购物车的商品id
    private int amount; //购物车中欲购买数量
    private String bookName; //图书名字
    private double price;
    private String path;
    private int inventory;
    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

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
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

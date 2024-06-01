package com.org.vo;


import java.util.Date;

public class Order_history {
    private int uIdOrder; //买家的id
    private int bookIdOrder; //购买的书的id
    private String bookName;
    private String path;
    private java.util.Date time; //付款时间
    private float priceOrder; //购买时支付的单价
    private int amountOrder; //购买数量
    private String buyerAddress;
    public Order_history(){};

    public int getuIdOrder() {
        return uIdOrder;
    }

    public void setuIdOrder(int uIdOrder) {
        this.uIdOrder = uIdOrder;
    }

    public int getBookIdOrder() {
        return bookIdOrder;
    }

    public void setBookIdOrder(int bookIdOrder) {
        this.bookIdOrder = bookIdOrder;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public float getPriceOrder() {
        return priceOrder;
    }

    public void setPriceOrder(float priceOrder) {
        this.priceOrder = priceOrder;
    }

    public int getAmountOrder() {
        return amountOrder;
    }

    public void setAmountOrder(int amountOrder) {
        this.amountOrder = amountOrder;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setBuyerAddress(String buyerAddress){ this.buyerAddress = buyerAddress;}

    public String getBuyerAddress(){return this.buyerAddress;}
    @Override
    public String toString() {
        return "Order_history{" +
                "uIdOrder=" + uIdOrder +
                ", bookIdOrder=" + bookIdOrder +
                ", time=" + time +
                ", priceOrder=" + priceOrder +
                ", amountOrder=" + amountOrder +
                '}';
    }
}

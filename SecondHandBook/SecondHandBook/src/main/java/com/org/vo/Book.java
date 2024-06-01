package com.org.vo;

public class Book {
    private int bookId; //图书id
    private String bookName; //图书名字
    private int inventory; //图书库存量
    private double price; //图书价格
    private String note; //图书简介
    private int uId; //卖家id
    private String path; //图书图片的存储路径

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", inventory=" + inventory +
                ", price=" + price +
                ", note='" + note + '\'' +
                ", uId=" + uId +
                ", path='" + path + '\'' +
                '}';
    }
}

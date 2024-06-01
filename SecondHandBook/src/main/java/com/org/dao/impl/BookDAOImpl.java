package com.org.dao.impl;

import com.org.dao.BookDAO;
import com.org.vo.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    public BookDAOImpl(Connection conn) {
        super();
        this.conn = conn;
    }

    public List<Book> findAll(int uId) throws Exception {
        List<Book> all = new ArrayList<>();
        String sql = "select * from book";
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
        Book book = null;

        while (rs.next()) {
            book= new Book();
            book.setuId(rs.getInt("u_id"));
            book.setBookId(rs.getInt("book_id"));
            book.setBookName(rs.getString("book_name"));
            book.setInventory(rs.getInt("inventory"));
            book.setPath(rs.getString("path"));
            book.setPrice(rs.getFloat("price"));
            book.setNote(rs.getString("note"));
            if (book.getuId() == uId && book.getInventory()>=0) {
                all.add(book);
            }
        }
        return all;
    }
    public List<Book> findBook() throws Exception{
        List <Book> all = new ArrayList<>();
        String sql = "select * from book ";
        PreparedStatement pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        Book book= null;

        while(rs.next()){
            if(rs.getInt(3) > 0){
                book = new Book();
                book.setBookId(rs.getInt(1));
                book.setBookName(rs.getString(2));
                book.setInventory(rs.getInt(3));
                book.setPath(rs.getString(4));
                book.setNote(rs.getString(5));
                book.setuId(rs.getInt(6));
                book.setPrice(rs.getDouble(7));
                all.add(book);
            }
        }
        return all;
    }
    //根据输入的书名，查找所有库存大于0的图书
    public List<Book> findBook(String bookname) throws Exception{
        List<Book> all = new ArrayList<>();
        String sql = "select * from book where book_name like ?";
        PreparedStatement pstmt = this.conn.prepareStatement(sql);

        pstmt.setString(1, "%" + bookname + "%"); // 在这里设置模糊匹配的值

        ResultSet rs = pstmt.executeQuery();
        Book book= null;

        while(rs.next()){
            if(rs.getInt(3) > 0){
                book = new Book();
                book.setBookId(rs.getInt(1));
                book.setBookName(rs.getString(2));
                book.setInventory(rs.getInt(3));
                book.setPath(rs.getString(4));
                book.setNote(rs.getString(5));
                book.setuId(rs.getInt(6));
                book.setPrice(rs.getDouble(7));
                all.add(book);
            }
        }
        return all;
    }

    public Book findBook(int boodID) throws Exception{
        Book book = new Book();
        String sql = "select * from book where book_id = ?";
        PreparedStatement pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, boodID);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            book = new Book();
            book.setBookId(rs.getInt(1));
            book.setBookName(rs.getString(2));
            book.setInventory(rs.getInt(3));
            book.setPath(rs.getString(4));
            book.setNote(rs.getString(5));
            book.setuId(rs.getInt(6));
            book.setPrice(rs.getDouble(7));
        }
        return book;
    }

    public void addBook(Book book) throws Exception {
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO book (book_name, inventory, price, note, u_id, path) VALUES (?, ?, ?, ?, ?, ?)";
        pstmt = this.conn.prepareStatement(sql);

        pstmt.setString(1, book.getBookName());
        pstmt.setInt(2, book.getInventory());
        pstmt.setDouble(3, book.getPrice());
        pstmt.setString(4, book.getNote());
        pstmt.setInt(5, book.getuId());
        pstmt.setString(6, book.getPath());
        pstmt.executeUpdate();
    }
    //图书修改方法我只按照自己的需求写的，查找某用户的某本书，修改它的库存
    public void alterBook(int bookID, int newInventory) throws Exception {
        PreparedStatement pstmt = null;

        String sql = "UPDATE book SET inventory = ? WHERE book_id = ?";
        pstmt = this.conn.prepareStatement(sql);

        pstmt.setInt(1, newInventory);
        pstmt.setInt(2, bookID);
        pstmt.executeUpdate();
    }


}


package com.org.dao;

import com.org.vo.Book;

import java.util.List;

public interface BookDAO {
    public List<Book> findAll(int uId) throws Exception;
    public List<Book> findBook() throws Exception;
    public List<Book> findBook(String bookname) throws Exception;
    public Book findBook(int bookID) throws Exception;
    public void addBook(Book bood) throws Exception;
    public void alterBook(int bookID, int newInventory) throws Exception;
}

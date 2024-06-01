package com.org.dao.proxy;

import com.org.dao.BookDAO;
import com.org.dao.impl.BookDAOImpl;
import com.org.dbc.DatabaseConnection;
import com.org.vo.Book;

import java.util.List;

public class BookDAOProxy implements BookDAO {
    private DatabaseConnection dbc = null;
    private BookDAO dao = null;

    public BookDAOProxy() throws Exception{
        this.dbc = new DatabaseConnection();
        this.dao = new BookDAOImpl(this.dbc.getConnection());
    }
    public List<Book> findBook() throws Exception{
        List<Book> all = null;
        try{
            all = this.dao.findBook();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            this.dbc.close();
        }
        return all;
    }

    public List<Book> findBook(String bookname) throws Exception{
        List<Book> all = null;
        try{
            all = this.dao.findBook(bookname);
        }
        catch (Exception e){
            throw e;
        }
        finally{
            this.dbc.close();
        }
        return all;
    }

    public Book findBook(int boodID) throws Exception{
        Book book = new Book();
        try{
            book =  this.dao.findBook(boodID);
        }
        catch (Exception e){
            throw e;
        }
        finally{
            this.dbc.close();
        }
        return book;
    }
    public void addBook(Book book) throws Exception {
        try {
            this.dao.addBook(book);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }
    public void alterBook(int bookID, int newInventory) throws Exception {
        try{
            this.dao.alterBook(bookID,newInventory);
        }
        catch (Exception e){
            throw e;
        }
        finally{
            this.dbc.close();
        }
    }

    public List<Book> findAll(int uId) throws Exception{
        List<Book> all = null;
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
}

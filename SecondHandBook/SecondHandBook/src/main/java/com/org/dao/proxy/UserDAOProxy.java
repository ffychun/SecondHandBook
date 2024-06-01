package com.org.dao.proxy;


import com.org.dao.UserDAO;
import com.org.dao.impl.UserDAOImpl;
import com.org.dbc.DatabaseConnection;
import com.org.vo.User;
import java.util.List;
public class UserDAOProxy implements UserDAO {
    private DatabaseConnection dbc = null;
    private UserDAO dao = null;

    public UserDAOProxy() throws Exception{
        this.dbc = new DatabaseConnection();
        this.dao = new UserDAOImpl(this.dbc.getConnection());
    }

    public List<User> findUser() throws Exception{
        List<User> all = null;
        try{
            all = this.dao.findUser();
        }
        catch (Exception e){
            throw e;
        }
        finally{
            this.dbc.close();
        }
        return all;
    }

    public User findUser(int id) throws Exception{
        User user = null;
        try{
            user = this.dao.findUser(id);
        }
        catch (Exception e){
            throw e;
        }
        finally{
            this.dbc.close();
        }
        return user;
    }
}

package com.org.dao.impl;

import com.org.dao.UserDAO;
import com.org.vo.*;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;

public class UserDAOImpl implements UserDAO {
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    public UserDAOImpl(Connection conn){
        super();
        this.conn = conn;
    }

    public List<User> findUser() throws Exception{
        List <User> all = new ArrayList<>();
        String sql = "select * from user";
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
        User user= null;

        while(rs.next()){
            user = new User();
            user.setuName(rs.getString(1));
            user.setPswd(rs.getString(2));
            user.setpNumber(rs.getString(3));
            user.setuId(rs.getInt(4));

            all.add(user);
        }
        return all;
    }
    //对findAll方法重载
    public User findUser(int id) throws Exception{
        String sql = "select * from User where u_id = ?";
        this.pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = this.pstmt.executeQuery();
        User user = null;
        if (rs.next()) {
            user = new User();
            user.setuName(rs.getString(1));
            user.setPswd(rs.getString(2));
            user.setpNumber(rs.getString(3));
            user.setuId(rs.getInt(4));
        }
        return user;
    }
}

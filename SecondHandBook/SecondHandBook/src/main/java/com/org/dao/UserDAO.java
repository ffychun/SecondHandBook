package com.org.dao;

import java.util.List;
import com.org.vo.*;

public interface UserDAO {
    public List<User> findUser() throws Exception;
    public User findUser(int id) throws Exception;

}

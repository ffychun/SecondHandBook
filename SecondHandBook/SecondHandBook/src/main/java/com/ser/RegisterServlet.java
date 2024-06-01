package com.ser;

import com.org.dao.UserDAO;
import com.org.dbc.DatabaseConnection;
import com.org.factory.DAOFactory;
import com.org.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 获取用户输入的昵称、密码和电话号码
        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");


        try {
            // 获取UserDAO实例
            UserDAO userDAO = DAOFactory.getUserDAOInstance();
            List<User> allUser = userDAO.findUser();

            // 判断电话号码是否已经存在
            for(User x:allUser){
                if(x.getpNumber().equals(phone)){
                    response.setContentType("text/html");
                    String message = "此电话号码已被注册，用户已存在！";
                    response.getWriter().println("<script>alert('" + message + "'); window.location.href='register.jsp';</script>");
                    return;
                }
            }

            // 判断密码是否符合要求
            if (password.length() < 6) {
                response.setContentType("text/html");
                String message = "密码强度不够，请增加密码长度至不少于6位！";
                response.getWriter().println("<script>alert('" + message + "'); window.location.href='register.jsp';</script>");
                return;
            }

            // 获取数据库连接
            DatabaseConnection DC = new DatabaseConnection();
            Connection conn = DC.getConnection();
            System.out.println(3);

            // 创建User对象
            /*
            User user = new User();
            user.setuName(nickname);
            user.setPswd(password);
            user.setpNumber(phone);
            */

            // 设置sql语句
            String sql = "insert into user (u_name,pswd,p_number) values (?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            // 将用户信息插入数据库
            statement.setString(1,nickname);
            statement.setString(2,password);
            statement.setString(3,phone);

            int rowsInserted = statement.executeUpdate();

            // 关闭数据库连接
            statement.close();
            DC.close();

            // 返回注册成功信息给用户
            if(rowsInserted>0) {
                response.setContentType("text/html");
                String message = "注册成功";
                response.getWriter().println("<script>alert('" + message + "'); window.location.href='index.jsp';</script>");
            }
            else
                response.sendRedirect("error.jsp");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

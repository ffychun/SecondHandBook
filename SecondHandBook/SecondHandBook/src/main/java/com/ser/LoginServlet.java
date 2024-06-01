package com.ser;

import com.org.dao.UserDAO;
import com.org.factory.DAOFactory;
import com.org.vo.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 获取用户输入的密码和电话号码
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        try{
            // 获取UserDAO实例
            UserDAO userDAO = DAOFactory.getUserDAOInstance();
            List<User> allUser = userDAO.findUser();

            for(User x: allUser){
                if(x.getpNumber().equals(phone) && x.getPswd().equals(password)){
                    HttpSession session = request.getSession();
                    session.setAttribute("userId",x.getuId());
                    response.sendRedirect("home.jsp");
                    break;
                }
                else if (x.getpNumber().equals(phone) && !(x.getPswd().equals(password))){
                    response.setContentType("text/html");
                    String message = "密码错误！";
                    response.getWriter().println("<script>alert('" + message + "'); window.location.href='index.jsp';</script>");
                    break;
                }
            }
            response.setContentType("text/html");
            String message = "用户不存在！";
            response.getWriter().println("<script>alert('" + message + "'); window.location.href='index.jsp';</script>");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

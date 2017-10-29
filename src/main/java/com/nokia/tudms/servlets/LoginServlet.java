package com.nokia.tudms.servlets;

import com.nokia.tudms.beans.LoggedUser;
import com.nokia.tudms.dao.UserAccountDAO;
import com.nokia.tudms.utils.TokenManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Rin
 * @Date 2017/4/24
 */
@WebServlet(name = "LoginServlet",urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String userName=request.getParameter("username");
        String password=request.getParameter("password");

        System.out.println(userName);
        System.out.println(password);

        int uid = UserAccountDAO.loginVerify(userName, password);
        System.out.println(uid);
        if(uid<0){
            Cookie cookie = new Cookie("token","");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        else{
            int level=UserAccountDAO.levelQuery(uid);
            LoggedUser loggedUser = TokenManager.getInstance().addUser(uid,userName,level);

            Cookie cookie = new Cookie("token",loggedUser.getUserToken());
            cookie.setMaxAge(1296000);
            response.addCookie(cookie);
        }
        String lastPage = request.getHeader("Referer");
        if(lastPage!=null)
            response.sendRedirect(lastPage);
        else
            response.sendRedirect("/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

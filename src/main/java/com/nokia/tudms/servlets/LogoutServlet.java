package com.nokia.tudms.servlets;

import com.nokia.tudms.beans.LoggedUser;
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
 * @Date 2017/5/3
 */
@WebServlet(name = "LogoutServlet",urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie [] allCookies=request.getCookies();
        if(allCookies!=null) {
            for (Cookie cookie : allCookies) {
                if (cookie.getName().equals("token")) {
                    LoggedUser loggedUserBean = TokenManager.getInstance().findUser(cookie.getValue());
                    TokenManager.getInstance().removeUser(loggedUserBean.getUserToken());
                }
            }
        }
        Cookie cookie = new Cookie("token","");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        String lastPage = request.getHeader("Referer");
        if(lastPage!=null)
            response.sendRedirect(lastPage);
        else
            response.sendRedirect("/");
    }
}

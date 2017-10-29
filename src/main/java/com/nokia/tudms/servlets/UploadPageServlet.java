package com.nokia.tudms.servlets;

import com.nokia.tudms.beans.LoggedUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Lee on 2017/5/4.
 * 访问上传页面需要经过的Servlet
 */
@WebServlet(name = "UploadPageServlet", urlPatterns = "/UploadPageServlet")
public class UploadPageServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        LoggedUser loggedUser = (LoggedUser)req.getAttribute("loggedUserBean");
        if (loggedUser != null){
            req.getRequestDispatcher("/jsp/upload.jsp").forward(req, resp);
        }else {
            resp.getWriter().print("请先登录。。。");
            resp.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

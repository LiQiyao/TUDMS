package com.nokia.tudms.servlets;

import com.nokia.tudms.dao.tool.QueryToolDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Lee on 2017/5/6.
 * 进入搜索页面之前的鉴权页面
 */
@WebServlet(name = "SearchPageServlet", urlPatterns = "/SearchPageServlet")
public class SearchPageServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String searchKey = req.getParameter("searchKey");
        if ( searchKey != null){
            req.setAttribute("searchKey", searchKey);
            req.getRequestDispatcher("/jsp/search.jsp").forward(req, resp);
        }else {
            resp.getWriter().print("请先登录。。。");
            resp.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}

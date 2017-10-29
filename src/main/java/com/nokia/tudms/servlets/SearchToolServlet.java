package com.nokia.tudms.servlets;

import com.google.gson.Gson;
import com.nokia.tudms.beans.search.SearchToolBeanList;
import com.nokia.tudms.dao.search.SearchToolBeanListDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Lee on 2017/5/3.
 * 普通搜索工具的Servlet
 */
@WebServlet(name = "SearchToolServlet", urlPatterns = "/SearchToolServlet")
public class SearchToolServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; UTF-8");
        PrintWriter out = resp.getWriter();

        String searchKey = req.getParameter("searchKey");
        String order = req.getParameter("order");
        System.out.println("Aa" + searchKey);
        SearchToolBeanList searchToolBeanList =  SearchToolBeanListDAO.getSearchToolBeanList(searchKey, order);
        Gson gson = new Gson();
        String jsonList = gson.toJson(searchToolBeanList);
        out.print(jsonList);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

package com.nokia.tudms.servlets;

import com.google.gson.Gson;
import com.nokia.tudms.beans.search.SearchUploaderBeanList;
import com.nokia.tudms.dao.search.SearchUploaderBeanListDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Lee on 2017/5/3.
 * 搜索发布者的Servlet
 */
@WebServlet(name = "SearchUploaderServlet", urlPatterns = "/SearchUploaderServlet")
public class SearchUploaderServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; UTF-8");
        PrintWriter out = resp.getWriter();

        String uploader = req.getParameter("uploader");
        SearchUploaderBeanList searchUploaderBeanList = SearchUploaderBeanListDAO.getSearchUploaderBeanList(uploader);
        Gson gson = new Gson();
        String json = gson.toJson(searchUploaderBeanList);
        System.out.println(json);
        out.print(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

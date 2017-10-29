package com.nokia.tudms.servlets;

import com.google.gson.Gson;
import com.nokia.tudms.beans.search.AdvancedSearchInputBean;
import com.nokia.tudms.beans.search.SearchToolBeanList;
import com.nokia.tudms.beans.search.SearchUploaderBeanList;
import com.nokia.tudms.dao.search.AdvancedSearchDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Lee on 2017/5/3.
 * 高级搜索的Servlet
 */
@WebServlet(name = "AdvancedSearchServlet", urlPatterns = "/AdvancedSearchServlet")
public class AdvancedSearchServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; UTF-8");
        PrintWriter out = resp.getWriter();
        System.out.println("1!!");
        String toolName = req.getParameter("toolName");
        String uploader = req.getParameter("uploader");
        String timeSt = req.getParameter("timeSt");
        String timeEn = req.getParameter("timeEn");
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        System.out.println("2!!");
        int uploaderTimeSt = 0;
        int uploaderTimeEn = 0;
        if (timeEn != null && !"".equals(timeEn)){
            try {
                uploaderTimeEn = (int)(format.parse(timeEn).getTime() / 1000);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (timeSt != null && !"".equals(timeSt)){
            try {
                uploaderTimeSt = (int)(format.parse(timeSt).getTime() / 1000);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println("3!!");
        String tag = req.getParameter("tag");
        String categoryString = req.getParameter("category");
        int category = Integer.parseInt(categoryString);//!!!!!!!!
        AdvancedSearchInputBean advancedSearchInputBean = new AdvancedSearchInputBean();
        advancedSearchInputBean.setToolNameKey(toolName);
        advancedSearchInputBean.setCategory(category);
        advancedSearchInputBean.setUploaderName(uploader);
        advancedSearchInputBean.setUploaderTimeSt(uploaderTimeSt);
        advancedSearchInputBean.setUploaderTimeEn(uploaderTimeEn);
        advancedSearchInputBean.setTagName(tag);

        SearchToolBeanList searchToolBeanList = AdvancedSearchDAO.advancedSearch(advancedSearchInputBean);
        Gson gson = new Gson();
        String json = gson.toJson(searchToolBeanList);
        out.print(json);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


}

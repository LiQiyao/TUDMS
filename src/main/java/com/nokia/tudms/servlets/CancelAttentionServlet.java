package com.nokia.tudms.servlets;

import com.nokia.tudms.dao.usercenter.AttentionToolDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Lee on 2017/5/4.
 * 取消关注的Servlet
 */
@WebServlet(name = "CancelAttentionServlet", urlPatterns = "/CancelAttentionServlet")
public class CancelAttentionServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; UTF-8");
        PrintWriter out = resp.getWriter();
        String uIdString = req.getParameter("uid");
        String toolIdString = req.getParameter("toolId");
        System.out.println("2" + uIdString + toolIdString);
        if (uIdString == null || toolIdString == null || "".equals(uIdString) || "".equals(toolIdString)){
            resp.sendRedirect("/");
            return;
        }

        int uId = Integer.parseInt(uIdString);
        int toolId = Integer.parseInt(toolIdString);
        out.print(AttentionToolDAO.cancelAttention(uId, toolId));
    }
}

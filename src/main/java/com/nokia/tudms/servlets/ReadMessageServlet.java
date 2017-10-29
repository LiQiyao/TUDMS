package com.nokia.tudms.servlets;

import com.nokia.tudms.dao.usercenter.ReadMessageDAO;
import com.sun.tools.corba.se.idl.constExpr.ShiftRight;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Lee on 2017/5/6.
 * 读动态的Servlet
 */
@WebServlet(name = "ReadMessageServlet", urlPatterns = "/ReadMessageServlet")
public class ReadMessageServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; UTF-8");
        PrintWriter out = resp.getWriter();
        String uIdString = req.getParameter("uId");
        if (uIdString == null || "".equals(uIdString)){
            resp.sendRedirect("/");
        }
        int uId = Integer.parseInt(uIdString);
        if (ReadMessageDAO.readMessage(uId)){
            out.print(1);
        }
    }
}

package com.nokia.tudms.servlets;

import com.nokia.tudms.dao.admin.DeleteToolDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Lee on 2017/5/18.
 */
@WebServlet(name = "DeleteSelfToolServlet", urlPatterns = "/DeleteSelfToolServlet")
public class DeleteSelfToolServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String toolIdS = req.getParameter("toolId");
        String path = req.getServletContext().getRealPath("/");
        if(toolIdS != null){
            int toolId = Integer.parseInt(toolIdS);
            if (DeleteToolDAO.deleteTool(toolId, path)){
                out.print(1);
            }else {
                out.print(-1);
            }
        }
    }
}

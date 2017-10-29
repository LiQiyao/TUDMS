package com.nokia.tudms.servlets;

import com.nokia.tudms.beans.LoggedUser;
import com.nokia.tudms.dao.tool.QueryToolDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Lee on 2017/5/6.
 * 进入更新工具之前的鉴权页面
 */
@WebServlet(name = "UpdatePageServlet", urlPatterns = "/UpdatePageServlet")
public class UpdatePageServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        LoggedUser loggedUser = (LoggedUser)req.getAttribute("loggedUserBean");
        String toolIdString = req.getParameter("toolId");
        if (loggedUser != null && toolIdString != null){
            int toolId = Integer.parseInt(toolIdString);
            req.setAttribute("toolId", toolId);
            req.setAttribute("OriginalToolName", QueryToolDAO.queryToolName(toolId));
            req.setAttribute("OriginalToolVersion", QueryToolDAO.queryToolVersion(toolId));
            req.getRequestDispatcher("/jsp/update.jsp").forward(req, resp);
        }else {
            resp.getWriter().print("请先登录。。。");
            resp.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/");
    }
}

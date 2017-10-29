package com.nokia.tudms.servlets.tool;

import com.nokia.tudms.beans.LoggedUser;
import com.nokia.tudms.dao.tool.ZanDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Rin
 * @Date 2017/5/6
 */
@WebServlet(name = "ZanServlet",urlPatterns = {"/api/zan"})
public class ZanServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int uid = ((LoggedUser)request.getAttribute("loggedUserBean")).getUserId();
        int toolId = Integer.parseInt(request.getParameter("tid"));
        if(ZanDAO.dianZan(uid,toolId)){
            response.getWriter().print(1);
        }else {
            response.getWriter().print(-1);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

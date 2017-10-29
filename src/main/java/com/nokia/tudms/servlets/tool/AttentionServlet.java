package com.nokia.tudms.servlets.tool;


import com.nokia.tudms.beans.LoggedUser;
import com.nokia.tudms.dao.tool.AttentionDAO;

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
@WebServlet(name = "AttentionServlet",urlPatterns = {"/api/change_attention_status"})
public class AttentionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int toolId = Integer.parseInt(request.getParameter("tid"));
        int uid = ((LoggedUser)request.getAttribute("loggedUserBean")).getUserId();
        AttentionDAO.changeAttentionStatus(uid,toolId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

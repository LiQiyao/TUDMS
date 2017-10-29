package com.nokia.tudms.servlets.manage;

import com.nokia.tudms.dao.admin.UsernameUidQueryDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Rin
 * @Date 2017/5/4
 */
@WebServlet(name = "QueryUidByUsernameServlet",urlPatterns = {"/api/query_uid_by_username"})
public class QueryUidByUsernameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String userName = request.getParameter("username");
        response.getWriter().print(UsernameUidQueryDAO.getUidByUserName(userName));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

package com.nokia.tudms.servlets.tool;

import com.nokia.tudms.dao.tool.ModifyBriefIntroDAO;
import com.nokia.tudms.dao.tool.ModifyDescriptionDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Rin
 * @Date 2017/5/7
 */
@WebServlet(name = "ModifyDescriptionServlet",urlPatterns = {"/api/modify_description"})
public class ModifyDescriptionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int toolId = Integer.parseInt(request.getParameter("toolId"));
        String content = request.getParameter("content");
        ModifyDescriptionDAO.modifyDescription(toolId,content);
        String lastPage = request.getHeader("Referer");
        if(lastPage!=null)
            response.sendRedirect(lastPage);
        else
            response.sendRedirect("/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

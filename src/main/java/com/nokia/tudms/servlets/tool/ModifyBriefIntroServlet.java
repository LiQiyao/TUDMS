package com.nokia.tudms.servlets.tool;

import com.nokia.tudms.dao.tool.ModifyBriefIntroDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用于更改工具简介
 * 注意：不严格的安全检测
 * @Author Rin
 * @Date 2017/5/7
 */
@WebServlet(name = "ModifyBriefIntroServlet",urlPatterns = {"/api/modify_brief_intro"})
public class ModifyBriefIntroServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int toolId = Integer.parseInt(request.getParameter("toolId"));
        String content = request.getParameter("content");
        ModifyBriefIntroDAO.ModifyBriefIntro(toolId,content);
        String lastPage = request.getHeader("Referer");
        if(lastPage!=null)
            response.sendRedirect(lastPage);
        else
            response.sendRedirect("/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

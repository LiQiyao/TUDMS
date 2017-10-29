package com.nokia.tudms.servlets.tool;

import com.google.gson.Gson;
import com.nokia.tudms.beans.LoggedUser;
import com.nokia.tudms.beans.tool.CommentBean;
import com.nokia.tudms.dao.tool.CommentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.nokia.tudms.dao.tool.CommentDAO.getComments;

/**
 * 该servlet用于在详情页中获取评论
 * @Author Rin
 * @Date 2017/4/29
 */
@WebServlet(name = "CommentGetterServlet",urlPatterns = {"/api/comment_getter"})
public class CommentGetterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");

        int toolId = Integer.parseInt(request.getParameter("toolId"));
        int startPos = Integer.parseInt(request.getParameter("startPos"));
        int needNumber = Integer.parseInt(request.getParameter("num"));

        ArrayList<CommentBean> commentList = CommentDAO.getComments(toolId,startPos,needNumber);
        Gson gson = new Gson();
        String json = gson.toJson(commentList);
        response.getWriter().append(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

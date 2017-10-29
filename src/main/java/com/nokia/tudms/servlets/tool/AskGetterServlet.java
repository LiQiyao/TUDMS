package com.nokia.tudms.servlets.tool;

import com.google.gson.Gson;
import com.nokia.tudms.beans.tool.AskBean;
import com.nokia.tudms.dao.tool.AskDAO;
import com.nokia.tudms.dao.tool.ReplyDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author Rin
 * @Date 2017/4/30
 */
@WebServlet(name = "AskGetterServlet",urlPatterns = {"/api/ask_getter"})
public class AskGetterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int toolId=Integer.parseInt(request.getParameter("toolId"));
        int startPos=Integer.parseInt(request.getParameter("startPos"));
        int num=Integer.parseInt(request.getParameter("num"));
        ArrayList<AskBean> askList=AskDAO.getAskList(toolId,startPos,num);
        for(AskBean askBean:askList){
            askBean.setReplyList(ReplyDAO.getReplyList(askBean.getAskId()));
        }
        response.setCharacterEncoding("utf-8");
        Gson gson = new Gson();
        String json = gson.toJson(askList);
        System.out.println(json);
        response.getWriter().append(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

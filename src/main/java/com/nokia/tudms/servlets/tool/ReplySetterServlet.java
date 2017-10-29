package com.nokia.tudms.servlets.tool;

import com.nokia.tudms.beans.LoggedUser;
import com.nokia.tudms.beans.tool.ToolDetailBean;
import com.nokia.tudms.dao.tool.AskDAO;
import com.nokia.tudms.dao.tool.PushDAO;
import com.nokia.tudms.dao.tool.ReplyDAO;
import com.nokia.tudms.dao.tool.ToolDetailDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 该Servlet用于回复问题
 * @Author Rin
 * @Date 2017/5/1
 */
@WebServlet(name = "ReplySetterServlet",urlPatterns = {"/api/reply_setter"})
public class ReplySetterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int status=-1;
        request.setCharacterEncoding("utf-8");
        LoggedUser loggedUser =(LoggedUser)request.getAttribute("loggedUserBean");
        if(loggedUser!=null){
            int senderId=loggedUser.getUserId();
            int toolId = Integer.parseInt(request.getParameter("toolId"));
            int targetUid=Integer.parseInt(request.getParameter("targetUid"));
            int askId=Integer.parseInt(request.getParameter("askId"));
            String content=request.getParameter("content");
            if(ReplyDAO.setReply(senderId,targetUid,askId,content));

            PushDAO.pushMessage(targetUid,2,loggedUser.getUserName()+" 回复了您的问题",content,"/tool?tid="+toolId);
        }
        response.getWriter().print(status);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

package com.nokia.tudms.servlets.tool;

import com.nokia.tudms.beans.LoggedUser;
import com.nokia.tudms.beans.tool.ToolDetailBean;
import com.nokia.tudms.beans.usercenter.UserCenterInfoBean;
import com.nokia.tudms.dao.tool.CommentDAO;
import com.nokia.tudms.dao.tool.PushDAO;
import com.nokia.tudms.dao.tool.ToolDetailDAO;
import com.nokia.tudms.dao.usercenter.UserCenterInfoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 该Servlet用于发送评论
 * @Author Rin
 * @Date 2017/4/29
 */
@WebServlet(name = "CommentSetterServlet",urlPatterns = {"/api/comment_setter"})
public class CommentSetterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int status=-1;
        request.setCharacterEncoding("utf-8");
        LoggedUser loggedUser =(LoggedUser)request.getAttribute("loggedUserBean");
        if(loggedUser!=null){
            int toolId=Integer.parseInt(request.getParameter("toolId"));
            int senderId=loggedUser.getUserId();
            String content=request.getParameter("comment-fill");
            if(CommentDAO.addComment(toolId,senderId,content)==true){
                status=1;
            }

            ToolDetailBean toolDetailBean= ToolDetailDAO.getToolDetail(toolId);
            PushDAO.pushMessage(toolDetailBean.getUploaderUid(),1,loggedUser.getUserName()+" 评论了您的工具",content,"/tool?tid="+toolId);
        }
        response.getWriter().print(status);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

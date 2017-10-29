package com.nokia.tudms.servlets.tool;

import com.nokia.tudms.beans.LoggedUser;
import com.nokia.tudms.beans.tool.ToolDetailBean;
import com.nokia.tudms.beans.usercenter.UserCenterInfoBean;
import com.nokia.tudms.dao.tool.AskDAO;
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
 * @Author Rin
 * @Date 2017/4/30
 */
@WebServlet(name = "AskSetterServlet",urlPatterns = {"/api/ask_setter"})
public class AskSetterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int status=-1;
        request.setCharacterEncoding("utf-8");
        LoggedUser loggedUser =(LoggedUser)request.getAttribute("loggedUserBean");
        if(loggedUser!=null){
            int toolId=Integer.parseInt(request.getParameter("toolId"));
            int senderId=loggedUser.getUserId();
            String content=request.getParameter("problem-fill");
            if(AskDAO.setAsk(senderId,toolId,content)){
                status=1;
            }

            ToolDetailBean toolDetailBean= ToolDetailDAO.getToolDetail(toolId);
            PushDAO.pushMessage(toolDetailBean.getUploaderUid(),2,loggedUser.getUserName()+" 向您提问",content,"/tool?tid="+toolId);
        }
        response.getWriter().print(status);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

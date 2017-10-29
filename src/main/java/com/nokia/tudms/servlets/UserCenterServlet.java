package com.nokia.tudms.servlets;

import com.nokia.tudms.beans.LoggedUser;
import com.nokia.tudms.beans.usercenter.AttentionTool;
import com.nokia.tudms.beans.usercenter.UserCenterMessageList;
import com.nokia.tudms.beans.usercenter.UserCenterInfoBean;
import com.nokia.tudms.beans.usercenter.UserCenterToolList;
import com.nokia.tudms.dao.usercenter.AttentionToolDAO;
import com.nokia.tudms.dao.usercenter.UserCenterMessageListDAO;
import com.nokia.tudms.dao.usercenter.UserCenterInfoDAO;
import com.nokia.tudms.dao.usercenter.UserCenterToolListDAO;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Lee on 2017/4/25.
 * 个人中心页面的servlet
 */
@WebServlet(name="UserCenterServlet", urlPatterns = "/UserCenterServlet")
public class UserCenterServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String uIdString = req.getParameter("uid");
        if (uIdString == null || "".equals(uIdString)){
            resp.sendRedirect("/");
            return;
        }
        int uId = Integer.parseInt(uIdString);
        int selfId = -1;
        LoggedUser loggedUser = (LoggedUser)req.getAttribute("loggedUserBean");
        if(loggedUser!=null){
            selfId=loggedUser.getUserId();
        }
        UserCenterInfoBean userCenterInfo = UserCenterInfoDAO.getUserCenterInfo(uId);
        req.getSession().setAttribute("userCenterInfo", userCenterInfo);
        UserCenterToolList userCenterToolList = UserCenterToolListDAO.getUserCenterToolList(uId);
        req.getSession().setAttribute("userCenterToolList", userCenterToolList);
        req.getSession().setAttribute("selfId", selfId);
        if (uId == selfId){
            UserCenterMessageList userCenterCommentList = UserCenterMessageListDAO.getUserCenterMessageList(selfId, 1);
            UserCenterMessageList userCenterAskList = UserCenterMessageListDAO.getUserCenterMessageList(selfId, 2);
            UserCenterMessageList userCenterAttentionList = UserCenterMessageListDAO.getUserCenterMessageList(selfId, 3);
            UserCenterMessageList userCenterAllList = UserCenterMessageListDAO.getUserCenterMessageList(selfId, 0);
            ArrayList<AttentionTool> attentionToolList = AttentionToolDAO.queryAttentionTool(selfId);
            req.getSession().setAttribute("userCenterCommentList", userCenterCommentList);
            req.getSession().setAttribute("userCenterAskList", userCenterAskList);
            req.getSession().setAttribute("userCenterAttentionList", userCenterAttentionList);
            req.getSession().setAttribute("userCenterAllList", userCenterAllList);
            req.getSession().setAttribute("unRead", userCenterAllList.getUnRead());
            req.getSession().setAttribute("attentionToolList", attentionToolList);

        }
        req.getRequestDispatcher("jsp/userCenter.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

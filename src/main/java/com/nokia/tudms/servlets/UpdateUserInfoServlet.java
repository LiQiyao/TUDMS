package com.nokia.tudms.servlets;

import com.nokia.tudms.beans.LoggedUser;
import com.nokia.tudms.beans.usercenter.UserCenterInfoBean;
import com.nokia.tudms.dao.usercenter.UserCenterInfoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Lee on 2017/5/4.
 * 个人中心里面更新个人信息的Servlet
 */
@WebServlet(name = "UpdateUserInfoServlet", urlPatterns = "/UpdateUserInfoServlet")
public class UpdateUserInfoServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String department = req.getParameter("department");
        String job = req.getParameter("job");
        String selfIntro = req.getParameter("selfIntro");

        // Rin 于2017.5.9修改
        LoggedUser loggedUser = (LoggedUser)req.getAttribute("loggedUserBean");
        int uId = loggedUser.getUserId();

        UserCenterInfoBean userCenterInfoBean = new UserCenterInfoBean();
        userCenterInfoBean.setuId(uId);
        userCenterInfoBean.setDepartment(department);
        userCenterInfoBean.setJob(job);
        userCenterInfoBean.setUsername(username);
        userCenterInfoBean.setSelfIntro(selfIntro);
        UserCenterInfoDAO.upDateUserCenterInfo(userCenterInfoBean);
        resp.sendRedirect("/UserCenterServlet?uid="+uId);
    }
}

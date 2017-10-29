package com.nokia.tudms.servlets.manage;

import com.nokia.tudms.beans.LoggedUser;
import com.nokia.tudms.beans.admin.CategoryBean;
import com.nokia.tudms.beans.admin.SubAdminInfoBean;
import com.nokia.tudms.beans.admin.ToolMgrBean;
import com.nokia.tudms.dao.ToolDAO;
import com.nokia.tudms.dao.admin.CategoryDAO;
import com.nokia.tudms.dao.admin.SubAdminManagementDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author Rin
 * @Date 2017/5/4
 */
@WebServlet(name = "ManagePageServlet",urlPatterns = {"/manage"})
public class ManagePageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoggedUser loggedUser = (LoggedUser)request.getAttribute("loggedUserBean");
        if(loggedUser!=null&&loggedUser.getUserLevel()==2) {
            ArrayList<SubAdminInfoBean> subAdminInfoList = SubAdminManagementDAO.getSubAdminInfoList();
            request.setAttribute("adminList", subAdminInfoList);

            ArrayList<CategoryBean> categoryList = CategoryDAO.getCategoryList();
            request.setAttribute("categoryList",categoryList);

            ArrayList<ToolMgrBean> toolList = ToolDAO.getToolListMgr();
            request.setAttribute("toolList",toolList);

            request.getRequestDispatcher("/jsp/manage.jsp").forward(request, response);
        }else{
            response.sendRedirect("/");
        }
    }
}

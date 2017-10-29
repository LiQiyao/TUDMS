package com.nokia.tudms.servlets.tool;

import com.nokia.tudms.beans.LoggedUser;
import com.nokia.tudms.beans.tool.DetailPageHelperBean;
import com.nokia.tudms.beans.tool.ToolDetailBean;
import com.nokia.tudms.dao.admin.SubAdminManagementDAO;
import com.nokia.tudms.dao.tool.AttentionDAO;
import com.nokia.tudms.dao.tool.ToolDetailDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Rin
 * @Date 2017/5/1
 */
@WebServlet(name = "ToolServlet",urlPatterns = {"/tool"})
public class ToolServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        int toolId=Integer.parseInt(request.getParameter("tid"));
        ToolDetailBean toolDetail = ToolDetailDAO.getToolDetail(toolId);

        //判断当前用户是否登录、是否作者、是否关注、是否本版块管理员
        DetailPageHelperBean helperBean;
        LoggedUser loggedUser = (LoggedUser)request.getAttribute("loggedUserBean");
        if(loggedUser!=null){
            helperBean = new DetailPageHelperBean(true);
            helperBean.setAdminAuth(SubAdminManagementDAO.checkSubAdmin(loggedUser.getUserId(),toolDetail.getCategoryId()));
            if(loggedUser.getUserId()==toolDetail.getUploaderUid()){
                helperBean.setUploader(true);
            }
            helperBean.setFollow(AttentionDAO.getAttentionStatus(loggedUser.getUserId(),toolId));

        }else{
            helperBean = new DetailPageHelperBean(false);
        }

        request.setAttribute("toolDetailBean",toolDetail);
        request.setAttribute("helperBean",helperBean);
        request.getRequestDispatcher("/jsp/details_page.jsp").forward(request,response);
    }
}

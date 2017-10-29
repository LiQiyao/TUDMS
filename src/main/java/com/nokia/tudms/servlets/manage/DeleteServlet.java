package com.nokia.tudms.servlets.manage;

import com.nokia.tudms.beans.LoggedUser;
import com.nokia.tudms.dao.admin.DeleteCommentDAO;
import com.nokia.tudms.dao.admin.DeleteToolDAO;
import com.nokia.tudms.dao.admin.SubAdminManagementDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Rin
 * @Date 2017/5/5
 */
@WebServlet(name = "DeleteServlet",urlPatterns = {"/api/manage/delete"})
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoggedUser loggedUser = (LoggedUser)request.getAttribute("loggedUserBean");
        if(loggedUser!=null&&(loggedUser.getUserLevel()==2||loggedUser.getUserLevel()==1)){
            String toolId=request.getParameter("toolId");
            String commentId=request.getParameter("commentId");

            if(toolId!=null){
                int tid=Integer.parseInt(toolId);
                DeleteToolDAO.deleteTool(tid, request.getServletContext().getRealPath("/"));//zanshi
                response.sendRedirect("/");
            }
            if(commentId!=null){
                int cid=Integer.parseInt(commentId);
                DeleteCommentDAO.deleteComment(cid);
            }

/*            String lastPage = request.getHeader("Referer");
            System.out.println("!!!" + lastPage);
            if(lastPage!=null)
                response.sendRedirect(lastPage);
            else
                response.sendRedirect("/");*/
        }else{
            response.getWriter().println("Warning:No Auth!");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

package com.nokia.tudms.servlets;

import com.nokia.tudms.beans.IndexToolItemBean;
import com.nokia.tudms.dao.ToolDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 该servlet完成跳转到首页的动作
 * @Author Rin
 * @Date 2017/4/24
 */
@WebServlet(name = "Index", urlPatterns = {"//*"})
public class Index extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<IndexToolItemBean> toolItemList = ToolDAO.getToolItemList(0, 0, 1);
        IndexToolItemBean firstItem=toolItemList.get(0);
        request.setAttribute("firstItem",firstItem);
        request.getRequestDispatcher("/jsp/index.jsp").forward(request,response);
    }
}

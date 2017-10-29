package com.nokia.tudms.servlets;

import com.nokia.tudms.beans.upload.UpdateToolBean;
import com.nokia.tudms.dao.tool.PushDAO;
import com.nokia.tudms.dao.upload.UpdatePushDAO;
import com.nokia.tudms.dao.upload.UpdateToolBeanDAO;
import com.nokia.tudms.utils.StringArrayToArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Lee on 2017/5/3.
 * 更新文件的Servlet
 */
@WebServlet(name = "UpdateServlet", urlPatterns = "/UpdateServlet")
@MultipartConfig(location = "D:/tools", maxFileSize = 1024L*1024L*1024L * 2)
public class UpdateServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String toolName = req.getParameter("toolName");
        System.out.println(toolName);
        int toolId = Integer.parseInt(req.getParameter("toolId"));//!!!!!!!!!!!!!!!!!!!
        String version = req.getParameter("version");

        String savePath = req.getServletContext().getRealPath("/") + "tools\\" +toolName + "\\" + version;
        File saveDir = new File(savePath);
        if (!saveDir.exists()){
            saveDir.mkdirs();
        }
        System.out.println("1!");
        String header = req.getHeader("content-disposition");
        Part part = req.getPart("file");
        System.out.println("2!");
        String submittedFileName = part.getSubmittedFileName();
        part.write(savePath + "/" + submittedFileName);
        UpdateToolBean updateToolBean = new UpdateToolBean();
        updateToolBean.setToolId(toolId);
        updateToolBean.setVersion(version);
        updateToolBean.setFilePosition("tools/" + toolName + "/" + version + "/" + submittedFileName);
        updateToolBean.setFileSize(part.getSize()/1024);
        updateToolBean.setUploadTime((int)(new Date().getTime() / 1000));
        if (UpdateToolBeanDAO.updateTool(updateToolBean) && UpdatePushDAO.push(toolId, toolName, version)){
            out.print(toolId);
        }else {
            out.print(-1);
        }

    }
}

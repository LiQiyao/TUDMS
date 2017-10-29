package com.nokia.tudms.servlets;

import com.nokia.tudms.beans.LoggedUser;
import com.nokia.tudms.beans.upload.UploadToolBean;
import com.nokia.tudms.dao.upload.UploadToolBeanDAO;
import com.nokia.tudms.utils.StringArrayToArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Lee on 2017/4/30.
 * 上传页面转发到该servlet
 */
@WebServlet(name = "UploadServlet", urlPatterns = "/UploadServlet")
@MultipartConfig(location = "D:/tools", maxFileSize = 1024L*1024L*1024L * 2)
public class UploadServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        int uId=((LoggedUser)req.getAttribute("loggedUserBean")).getUserId();
        System.out.println("uid is"+uId);
        String toolName = req.getParameter("toolName");
        String version = req.getParameter("version");
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));

        String[] tags = req.getParameter("tags").split(" ");
        ArrayList<String> tagList = StringArrayToArrayList.trans(tags);
        String briefIntro = req.getParameter("briefIntro");
        String description = req.getParameter("description");
        String savePath = req.getServletContext().getRealPath("/") + "tools\\" +toolName + "\\" + version;

        File saveDir = new File(savePath);
        if (!saveDir.exists()){
            saveDir.mkdirs();
        }
        String header = req.getHeader("content-disposition");
        Part part = req.getPart("file");
        String submittedFileName = part.getSubmittedFileName();
        part.write(savePath + "/" + submittedFileName);

        UploadToolBean uploadToolBean = new UploadToolBean();
        //set
        uploadToolBean.setUploaderUId(uId);
        uploadToolBean.setBriefIntro(briefIntro);
        uploadToolBean.setCategoryId(categoryId);
        uploadToolBean.setDescription(description);
        uploadToolBean.setFilePosition("tools/"+ toolName + "/" + version + "/" + submittedFileName);
        uploadToolBean.setFileSize(part.getSize()/1024);
        uploadToolBean.setToolName(toolName);
        uploadToolBean.setVersion(version);
        System.out.println(new Date().getTime());
        uploadToolBean.setUploadTime((int)(new Date().getTime() / 1000));
        uploadToolBean.setTagList(tagList);
        int toolId = UploadToolBeanDAO.insertToolInfo(uploadToolBean);
        if (toolId != -1){
            out.print(toolId);
        }else {
            out.print(-1);
        }
    }

}

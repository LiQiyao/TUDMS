package com.nokia.tudms.servlets;

import com.nokia.tudms.beans.LoggedUser;
import com.nokia.tudms.beans.upload.UploadToolBean;
import com.nokia.tudms.dao.upload.UploadToolBeanDAO;
import com.nokia.tudms.utils.StringArrayToArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.channels.FileChannel;
import java.util.*;

/**
 * Created by Lee on 2017/5/19.
 */
@WebServlet(name = "UploadActionServlet", urlPatterns = "/UploadActionServlet")
public class UploadActionServlet extends HttpServlet {
    private String uploadPath = "";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        uploadPath = req.getServletContext().getRealPath("/") + "tools\\";
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String action = req.getParameter("action");

        if (action.equals("mergeChunks")){

            int uId=((LoggedUser)req.getAttribute("loggedUserBean")).getUserId();
            String uniqueFileTag = req.getParameter("uniqueFileTag");
            String fileName = req.getParameter("fileName");
            String toolName = req.getParameter("toolName");
            String version = req.getParameter("version");
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            String[] tags = req.getParameter("tags").split(" ");
            ArrayList<String> tagList = StringArrayToArrayList.trans(tags);
            String briefIntro = req.getParameter("briefIntro");
            String description = req.getParameter("description");
            System.out.println(categoryId + briefIntro);
            File f = new File(uploadPath + uniqueFileTag);
            File[] fileArray = f.listFiles(new FileFilter() {
                public boolean accept(File pathname) {
                    if (pathname.isDirectory()) {
                        return false;
                    }
                    return true;
                }
            });

            List<File> fileList = new ArrayList<File>(Arrays.asList(fileArray));
            Collections.sort(fileList, new Comparator<File>() {
                public int compare(File o1, File o2) {
                    if (Integer.parseInt(o1.getName()) < Integer.parseInt(o2.getName())){
                        return -1;
                    }
                    return 1;
                }
            });

            File pathFile = new File(uploadPath + toolName + "\\" + version);
            if(!pathFile.exists()){
                pathFile.mkdirs();
            }
            File outputFile = new File(pathFile + "\\" + fileName);
            outputFile.createNewFile();

            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            FileChannel outChannel = fileOutputStream.getChannel();
            FileChannel inChanel;

            for (File file : fileList){
                inChanel = new FileInputStream(file).getChannel();
                inChanel.transferTo(0, inChanel.size(), outChannel);
                inChanel.close();
                file.delete();
            }

            File tempFile = new File(uploadPath + uniqueFileTag);

            if (tempFile.isDirectory() &&tempFile.exists()){
                tempFile.delete();
            }

            UploadToolBean uploadToolBean = new UploadToolBean();
            //set
            uploadToolBean.setUploaderUId(uId);
            uploadToolBean.setBriefIntro(briefIntro);
            uploadToolBean.setCategoryId(categoryId);
            uploadToolBean.setDescription(description);
            uploadToolBean.setFilePosition("tools/"+ toolName + "/" + version + "/" + fileName);
            uploadToolBean.setFileSize(outputFile.length()/1024);
            uploadToolBean.setToolName(toolName);
            uploadToolBean.setVersion(version);
            System.out.println(new Date().getTime());
            uploadToolBean.setUploadTime((int)(new Date().getTime() / 1000));
            uploadToolBean.setTagList(tagList);
            outChannel.close();
            fileOutputStream.close();
            int toolId = UploadToolBeanDAO.insertToolInfo(uploadToolBean);
            if (toolId != -1){
                out.print(toolId);
            }else {
                out.print(-1);
            }


        } else if (action.equals("checkChunk")){
            String uniqueFileTag = req.getParameter("uniqueFileTag");
            String chunk = req.getParameter("chunk");
            String chunkSize = req.getParameter("chunkSize");

            File checkFile = new File(uploadPath + uniqueFileTag + "/" + chunk);
            if (checkFile.exists() && checkFile.length() == Integer.parseInt(chunkSize)){
                resp.getWriter().write("{\"ifExist\":1}");
            }else {
                resp.getWriter().write("{\"ifExist\":0}");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}

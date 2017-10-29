package com.nokia.tudms.servlets;

import com.nokia.tudms.beans.LoggedUser;
import com.nokia.tudms.beans.upload.UpdateToolBean;
import com.nokia.tudms.beans.upload.UploadToolBean;
import com.nokia.tudms.dao.upload.UpdatePushDAO;
import com.nokia.tudms.dao.upload.UpdateToolBeanDAO;
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
 * Created by Lee on 2017/5/22.
 */
@WebServlet(name = "UpdateActionServlet", urlPatterns = "/UpdateActionServlet")
public class UpdateActionServlet extends HttpServlet{
    private String uploadPath = "";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

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
            System.out.println(toolName);
            int toolId = Integer.parseInt(req.getParameter("toolId"));//!!!!!!!!!!!!!!!!!!!
            String version = req.getParameter("version");
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

            UpdateToolBean updateToolBean = new UpdateToolBean();
            updateToolBean.setToolId(toolId);
            updateToolBean.setVersion(version);
            updateToolBean.setFilePosition("tools/" + toolName + "/" + version + "/" + fileName);
            updateToolBean.setFileSize(outputFile.length()/1024);
            updateToolBean.setUploadTime((int)(new Date().getTime() / 1000));
            outChannel.close();
            fileOutputStream.close();
            if (UpdateToolBeanDAO.updateTool(updateToolBean) && UpdatePushDAO.push(toolId, toolName, version)){
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
}

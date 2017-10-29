package com.nokia.tudms.servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Lee on 2017/5/22.
 */
@WebServlet(name = "UpdateChunkServlet", urlPatterns = "/UpdateChunkServlet")
public class UpdateChunkServlet extends HttpServlet {
    private String uploadPath = "";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        uploadPath = req.getServletContext().getRealPath("/") + "tools\\";
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(factory);
        sfu.setHeaderEncoding("utf-8");
        try{
            List<FileItem> items = sfu.parseRequest(req);
            String uniqueFileTag = null;
            String chunk = null;
            for (FileItem item : items){
                if (item.isFormField()){
                    String fieldName = item.getFieldName();
                    if(fieldName.equals("uniqueFileTag")){
                        uniqueFileTag = item.getString("utf-8");
                    }
                    if(fieldName.equals("chunk")){
                        chunk = item.getString("utf-8");
                    }
                }else {
                    File file = new File(uploadPath + uniqueFileTag + "/");
                    if (!file.exists()){
                        file.mkdir();
                    }
                    File ck = new File(uploadPath + uniqueFileTag + "/" + chunk);
                    FileUtils.copyInputStreamToFile(item.getInputStream(), ck);
                }
                item.getInputStream().close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

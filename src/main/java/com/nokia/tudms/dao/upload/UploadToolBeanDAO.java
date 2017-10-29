package com.nokia.tudms.dao.upload;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.nokia.tudms.beans.upload.UploadToolBean;
import com.nokia.tudms.dao.DBPool;
import com.nokia.tudms.dao.usercenter.UserCenterInfoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by Lee on 2017/4/29.
 * 将已经上传的工具信息存入数据库
 */
public class UploadToolBeanDAO {

    private static Logger logger = Logger.getLogger(UserCenterInfoDAO.class.getName());

    private static DBPool dbPool = DBPool.getInstance();

    private static DruidPooledConnection connection  = null;

    public static int insertToolInfo(UploadToolBean uploadToolBean){
        int toolId = -1;
        try {
            int fileId = 0;
            connection = dbPool.getConnection();
            //开始事务
            connection.setAutoCommit(false);
            //插入tool表
            PreparedStatement pstm = connection.prepareStatement
                    ("INSERT INTO tool (`tool-name`,`uploader-uid`,`category`,`brief-intro`,`description`) VALUES(?,?,?,?,?)");
            //set
            pstm.setString(1, uploadToolBean.getToolName());
            pstm.setInt(2, uploadToolBean.getUploaderUId());
            pstm.setInt(3, uploadToolBean.getCategoryId());
            pstm.setString(4, uploadToolBean.getBriefIntro());
            pstm.setString(5, uploadToolBean.getDescription());
            pstm.execute();

            PreparedStatement pstm3 = connection.prepareStatement("SELECT `tool-id` FROM tool ORDER BY  `tool-id` DESC");
            ResultSet rs3 = pstm3.executeQuery();
            if (rs3.next()){
                toolId = rs3.getInt(1);
            }

            //插入tool_file表
            PreparedStatement pstm4 = connection.prepareStatement
                    ("INSERT INTO tool_file (`tool-id`,`version`,`upload-time`,`file-position`,`file-size`) VALUES (?,?,?,?,?)");
            //set
            pstm4.setInt(1,toolId);
            pstm4.setString(2, uploadToolBean.getVersion());
            pstm4.setInt(3, uploadToolBean.getUploadTime());
            pstm4.setString(4, uploadToolBean.getFilePosition());
            pstm4.setFloat(5, uploadToolBean.getFileSize());
            pstm4.execute();

            //查询newest-file-id
            PreparedStatement pstm5 = connection.prepareStatement("SELECT `file-id` FROM tool_file WHERE `tool-id`=? ORDER BY `upload-time` DESC ");
            pstm5.setInt(1, toolId);
            ResultSet rs5 = pstm5.executeQuery();
            if (rs5.next()){
                fileId = rs5.getInt(1);
            }

            //更新tool表中的newest-file-id
            PreparedStatement pstm6 = connection.prepareStatement("UPDATE tool SET `newest-file-id`=? WHERE `tool-id`=?");
            pstm6.setInt(1, fileId);
            pstm6.setInt(2, toolId);
            pstm6.execute();

            //更新tag表

            PreparedStatement pstm7 = null;

            ArrayList<String> a = uploadToolBean.getTagList();
            for (int i = 0; i < a.size(); i++){
                try {
                    pstm7 = connection.prepareStatement("INSERT INTO tag (`tag-name`) VALUES (?)");
                    pstm7.setString(1, a.get(i));
                    pstm7.execute();
                }catch (Exception e){}

            }

            //更新tool_tag_relation表
            PreparedStatement pstm8 = null;
            PreparedStatement pstm9 = null;
            for (int i = 0; i < a.size(); i++){
                int tagId = 0;
                pstm8 = connection.prepareStatement("SELECT `tag-id` FROM tag WHERE `tag-name`=?");
                pstm8.setString(1, a.get(i));
                ResultSet rs = pstm8.executeQuery();
                if (rs.next()){
                    tagId = rs.getInt(1);
                }
                pstm9 = connection.prepareStatement("INSERT INTO tool_tag_relation (`tool-id`, `tag-id`)VALUES (?,?)");
                pstm9.setInt(1, toolId);
                pstm9.setInt(2, tagId);
                pstm9.execute();
            }

            connection.commit();
            connection.setAutoCommit(true);
            return toolId;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        UploadToolBean uploadToolBean = new UploadToolBean();
        uploadToolBean.setUploaderUId(1);
        uploadToolBean.setBriefIntro("不错的");
        uploadToolBean.setCategoryId(1);
        uploadToolBean.setDescription("非常不错的");
        uploadToolBean.setFilePosition("www.baidu.com");
        uploadToolBean.setFileSize(18.5f);
        uploadToolBean.setToolName("计算器");
        uploadToolBean.setVersion("v1.0");
        uploadToolBean.setUploadTime(111);
        ArrayList<String> a = new ArrayList<String>();
        a.add("标签0");
        a.add("标签4");
        a.add("标签10");
        uploadToolBean.setTagList(a);
        insertToolInfo(uploadToolBean);
    }
}

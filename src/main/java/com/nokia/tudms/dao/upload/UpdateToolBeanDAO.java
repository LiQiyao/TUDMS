package com.nokia.tudms.dao.upload;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.nokia.tudms.beans.upload.UpdateToolBean;
import com.nokia.tudms.beans.upload.UploadToolBean;
import com.nokia.tudms.dao.DBPool;
import com.nokia.tudms.dao.usercenter.UserCenterInfoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by Lee on 2017/5/3.
 * 更新工具的DAO
 */
public class UpdateToolBeanDAO {
    private static Logger logger = Logger.getLogger(UserCenterInfoDAO.class.getName());

    private static DBPool dbPool = DBPool.getInstance();

    private static DruidPooledConnection connection  = null;

    public static boolean updateTool(UpdateToolBean updateToolBean){
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            int fileId = 0;
            connection = dbPool.getConnection();
            pstm = connection.prepareStatement
                    ("INSERT INTO tool_file (`tool-id`,`version`,`upload-time`,`file-position`,`file-size`) VALUES (?,?,?,?,?)");
            pstm.setInt(1, updateToolBean.getToolId());
            pstm.setString(2, updateToolBean.getVersion());
            pstm.setInt(3, updateToolBean.getUploadTime());
            pstm.setString(4, updateToolBean.getFilePosition());
            pstm.setFloat(5, updateToolBean.getFileSize());
            pstm.execute();
            pstm = connection.prepareStatement("SELECT LAST_INSERT_ID()");
            rs = pstm.executeQuery();
            if (rs.next()){
                fileId = rs.getInt(1);
            }
            pstm = connection.prepareStatement("UPDATE tool SET `newest-file-id` = ? WHERE `tool-id`=?");
            pstm.setInt(1, fileId);
            pstm.setInt(2, updateToolBean.getToolId());
            pstm.execute();

            connection.setAutoCommit(false);
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstm != null){
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}

package com.nokia.tudms.dao.tool;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.nokia.tudms.dao.DBPool;
import com.nokia.tudms.dao.usercenter.UserCenterInfoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by Lee on 2017/5/6.
 * 根据toolId查询tool的信息的DAO
 */
public class QueryToolDAO {
    private static Logger logger = Logger.getLogger(UserCenterInfoDAO.class.getName());

    private static DBPool dbPool = DBPool.getInstance();

    private static DruidPooledConnection connection  = null;

    public static String queryToolName(int toolId){
        String toolName = null;
        try {
            connection = dbPool.getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT `tool-name` FROM tool WHERE `tool-id`=?");
            pstm.setInt(1, toolId);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()){
                toolName = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return toolName;
    }

    public static String queryToolVersion(int toolId){
        String version = null;
        try {
            connection = dbPool.getConnection();
            PreparedStatement pstm = connection.prepareStatement
                    ("SELECT `version` FROM tool_file WHERE `file-id`=(SELECT `newest-file-id` FROM tool WHERE tool.`tool-id`=?)");
            pstm.setInt(1, toolId);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()){
                version = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return version;
    }
}

package com.nokia.tudms.dao.upload;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.nokia.tudms.dao.DBPool;
import com.nokia.tudms.dao.tool.PushDAO;
import com.nokia.tudms.dao.usercenter.UserCenterInfoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by Lee on 2017/5/6.
 * 工具更新推送的DAO
 */
public class UpdatePushDAO {
    private static Logger logger = Logger.getLogger(UserCenterInfoDAO.class.getName());

    private static DBPool dbPool = DBPool.getInstance();

    private static DruidPooledConnection connection  = null;

    public static boolean push(int toolId, String toolName, String version){
        try {
            connection = dbPool.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement pstm = connection.prepareStatement("SELECT `uid` FROM attention WHERE `tool-id`=?");
            pstm.setInt(1, toolId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                int uId = rs.getInt(1);
                PushDAO.pushMessage(uId, 3, "您关注的"+toolName+"有更新", "更新版本为："+version, "/tool?tid=" + toolId );
            }
            connection.commit();
            connection.setAutoCommit(true);
            return true;
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
        return false;
    }
}

package com.nokia.tudms.dao.usercenter;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.nokia.tudms.dao.DBPool;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by Lee on 2017/5/6.
 * 读动态的DAO
 */
public class ReadMessageDAO {
    private static Logger logger = Logger.getLogger(UserCenterInfoDAO.class.getName());

    private static DBPool dbPool = DBPool.getInstance();

    private static DruidPooledConnection connection  = null;

    public static boolean readMessage(int uId){
        try {
            connection = dbPool.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement pstm = connection.prepareStatement("UPDATE message_push SET `have-read`=1 WHERE `receiver-uid`=?");
            pstm.setInt(1, uId);
            pstm.execute();
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

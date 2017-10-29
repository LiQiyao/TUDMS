package com.nokia.tudms.dao.usercenter;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.nokia.tudms.beans.usercenter.UserCenterInfoBean;
import com.nokia.tudms.dao.DBPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by Lee on 2017/4/25.
 * 个人中心的左侧用户信息栏的DAO层
 */
public class UserCenterInfoDAO {

    private static Logger logger = Logger.getLogger(UserCenterInfoDAO.class.getName());

    private static DBPool dbPool = DBPool.getInstance();

    private static DruidPooledConnection connection  = null;

    public static UserCenterInfoBean getUserCenterInfo(int uId){

        UserCenterInfoBean userCenterInfo = new UserCenterInfoBean();
        try {
            connection = dbPool.getConnection();
            PreparedStatement pstm
                    = connection.prepareStatement("SELECT `uid`,`username`,`department`,`sex`,`self-intro`,`job`  FROM user WHERE uid=?");
            pstm.setInt(1, uId);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                userCenterInfo.setuId(rs.getInt(1));
                userCenterInfo.setUsername(rs.getString(2));
                userCenterInfo.setDepartment(rs.getString(3));
                userCenterInfo.setSex(rs.getInt(4));
                userCenterInfo.setSelfIntro(rs.getString(5));
                userCenterInfo.setJob(rs.getString(6));
                logger.info("job:" + rs.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userCenterInfo;
    }

    public static void upDateUserCenterInfo(UserCenterInfoBean userCenterInfo){
        try {
            connection = dbPool.getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE user SET `department`=?, `job`=?, `self-intro`=? WHERE uid = ?");
            pstm.setString(1, userCenterInfo.getDepartment());
            pstm.setString(2, userCenterInfo.getJob());
            pstm.setString(3, userCenterInfo.getSelfIntro());
            pstm.setInt(4, userCenterInfo.getuId());
            pstm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        getUserCenterInfo(1);
    }
}

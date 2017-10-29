package com.nokia.tudms.dao.admin;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.nokia.tudms.beans.admin.SubAdminInfoBean;
import com.nokia.tudms.dao.DBPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 管理员界面，获取全部授权信息、为指定用户赋予权限
 * @Author Rin
 * @Date 2017/5/2
 */
public class SubAdminManagementDAO {
    public static ArrayList<SubAdminInfoBean> getSubAdminInfoList(){
        ArrayList<SubAdminInfoBean> subAdminList = new ArrayList<SubAdminInfoBean>();
        try {
            DruidPooledConnection connection = DBPool.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT management.*,user.`username` FROM management,user WHERE management.`user-id`=user.uid ORDER BY management.`user-id` ASC");
            ResultSet rs = ps.executeQuery();
            int previousUid=-1;
            SubAdminInfoBean subAdminInfoBean = new SubAdminInfoBean();
            while (rs.next()){
                int currentUid=rs.getInt("user-id");
                if(currentUid!=previousUid){
                    previousUid=currentUid;
                    subAdminInfoBean = new SubAdminInfoBean();
                    subAdminList.add(subAdminInfoBean);
                    subAdminInfoBean.setUid(currentUid);
                    subAdminInfoBean.setUserName(rs.getString("username"));
                }
                subAdminInfoBean.appendAuthCategory(rs.getInt("category-id"));
            }
            rs.close();
            ps.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return subAdminList;
    }
    public static boolean setSubAdmin(int uid,int categoryId){
        boolean status=false;
        try{
            DruidPooledConnection connection = DBPool.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `management` (`user-id`, `category-id`) VALUES (?, ?);");
            ps.setInt(1,uid);
            ps.setInt(2,categoryId);
            ps.execute();
            status=true;
            ps.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return status;
    }
    public static void deleteSubAdmin(int uid,int categoryId){
        try{
            DruidPooledConnection connection = DBPool.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM `management` WHERE `user-id`=? AND `category-id`=?");
            ps.setInt(1,uid);
            ps.setInt(2,categoryId);
            ps.execute();
            ps.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static boolean checkSubAdmin(int uid,int categoryId){
        boolean ans = false;
        try {
            DruidPooledConnection connection = DBPool.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `management` WHERE `user-id`=? AND `category-id`=?");
            ps.setInt(1,uid);
            ps.setInt(2,categoryId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                ans = true;
            }
            rs.close();
            ps.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ans;
    }

}

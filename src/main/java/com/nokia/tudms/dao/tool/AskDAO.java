package com.nokia.tudms.dao.tool;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.nokia.tudms.beans.tool.AskBean;
import com.nokia.tudms.dao.DBPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 该DAO用于获取及提出问题
 * @Author Rin
 * @Date 2017/4/30
 */
public class AskDAO {
    public static int getNum(int toolId){
        int num=-1;
        try {
            DruidPooledConnection connection = DBPool.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM ask WHERE `tool-id`=?;");
            ps.setInt(1,toolId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            num=rs.getInt(1);

            rs.close();
            ps.close();
            connection.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return num;
    }

    public static boolean setAsk(int senderUid,int toolId,String content){
        boolean status=false;
        try{
            DruidPooledConnection connection = DBPool.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `ask` (`sender-uid`, `tool-id`, `content`, `time`) VALUES (?, ?, ?, ?);");
            ps.setInt(1,senderUid);
            ps.setInt(2,toolId);
            ps.setString(3,content);
            ps.setInt(4,(int)(System.currentTimeMillis()/1000));
            ps.execute();
            status=true;

            ps.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return status;
    }

    // 该方法获取到的Ask是没有Reply的
    public static ArrayList<AskBean> getAskList(int toolId,int startPos,int num){
        ArrayList<AskBean> askList = new ArrayList<AskBean>();
        try {
            DruidPooledConnection connection = DBPool.getInstance().getConnection();
            PreparedStatement ps=connection.prepareStatement("SELECT ask.*,`user`.`username` FROM ask,user WHERE `tool-id`=? AND `sender-uid`=`uid`ORDER BY `ask-id` desc limit ?,?;");
            ps.setInt(1,toolId);
            ps.setInt(2,startPos);
            ps.setInt(3,num);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                AskBean askBean = new AskBean(rs.getInt("ask-id"),
                        rs.getInt("sender-uid"),
                        rs.getString("username"),
                        rs.getInt("tool-id"),
                        rs.getInt("time"),
                        rs.getString("content"));
                askList.add(askBean);
            }
            rs.close();
            ps.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return askList;
    }
}

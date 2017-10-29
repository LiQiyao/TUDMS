package com.nokia.tudms.dao.tool;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.nokia.tudms.dao.DBPool;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author Rin
 * @Date 2017/5/4
 */
public class PushDAO {
    public static void pushMessage(int receiverUid,int type,String title,String content,String link){
        try{
            DruidPooledConnection connection = DBPool.getInstance().getConnection();
            PreparedStatement ps=connection.prepareStatement("INSERT INTO `message_push` (`receiver-uid`, `type`, `title`, `content`, `sendtime`, `link`) VALUES (?,?,?,?,?,?);");
            ps.setInt(1,receiverUid);
            ps.setInt(2,type);
            ps.setString(3,title);
            ps.setString(4,content);
            ps.setInt(5,(int)(System.currentTimeMillis()/1000));
            ps.setString(6,link);
            ps.execute();
            ps.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

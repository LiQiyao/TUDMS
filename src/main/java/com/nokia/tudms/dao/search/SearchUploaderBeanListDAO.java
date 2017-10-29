package com.nokia.tudms.dao.search;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.nokia.tudms.beans.search.SearchUploaderBean;
import com.nokia.tudms.beans.search.SearchUploaderBeanList;
import com.nokia.tudms.dao.DBPool;
import com.nokia.tudms.dao.usercenter.UserCenterInfoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by Lee on 2017/4/28.
 * 按发布者搜索列表的DAO
 */
public class SearchUploaderBeanListDAO {

    private static Logger logger = Logger.getLogger(UserCenterInfoDAO.class.getName());

    private static DBPool dbPool = DBPool.getInstance();

    private static DruidPooledConnection connection  = null;

    public static SearchUploaderBeanList getSearchUploaderBeanList(String searchKey){
        SearchUploaderBeanList searchUploaderBeanList = new SearchUploaderBeanList();
        try {
            connection = dbPool.getConnection();

            PreparedStatement pstm
                    = connection.prepareStatement
                    ("SELECT `uid`,`username`,`department`,`sex`,`self-intro`,`job`  FROM user WHERE `username` LIKE ? OR `self-intro` LIKE ? OR `job` LIKE ?");
            pstm.setString(1, "%" + searchKey + "%");
            pstm.setString(2, "%" + searchKey + "%");
            pstm.setString(3, "%" + searchKey + "%");
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                SearchUploaderBean searchUploaderBean = new SearchUploaderBean();
                searchUploaderBean.setuId(rs.getInt(1));
                searchUploaderBean.setUsername(rs.getString(2));
                searchUploaderBean.setDepartment(rs.getString(3));
                searchUploaderBean.setSex(rs.getInt(4));
                searchUploaderBean.setSelfIntro(rs.getString(5));
                searchUploaderBean.setJob(rs.getString(6));
                logger.info("name:" + searchUploaderBean.getUsername());
                searchUploaderBeanList.addSearchUploaderBean(searchUploaderBean);
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
        return searchUploaderBeanList;
    }

    public static void main(String[] args) {

        SearchUploaderBeanList list = getSearchUploaderBeanList("老铁");
        System.out.println(list.getList().get(0).getUsername());
    }
}

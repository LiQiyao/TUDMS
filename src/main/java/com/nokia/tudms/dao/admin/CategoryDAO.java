package com.nokia.tudms.dao.admin;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.nokia.tudms.beans.admin.CategoryBean;
import com.nokia.tudms.dao.DBPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @Author Rin
 * @Date 2017/5/28
 */
public class CategoryDAO {
    public static ArrayList<CategoryBean> getCategoryList(){
        ArrayList<CategoryBean> categoryList = new ArrayList<CategoryBean>();

        try {
            DruidPooledConnection connection = DBPool.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT category.*,count(`tool-id`) count FROM category,tool WHERE tool.`category`=category.`id` GROUP BY category.id;");
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                CategoryBean categoryBean=new CategoryBean();
                categoryBean.setId(rs.getInt(1));
                categoryBean.setName(rs.getString(2));
                categoryBean.setCount(rs.getInt(3));
                categoryList.add(categoryBean);
            }
            rs.close();
            ps.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return categoryList;
    }
}

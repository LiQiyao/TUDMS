package com.nokia.tudms.dao;

import com.google.gson.Gson;
import com.nokia.tudms.beans.IndexToolItemBean;
import com.nokia.tudms.beans.admin.SubAdminInfoBean;
import com.nokia.tudms.beans.tool.AskBean;
import com.nokia.tudms.beans.tool.CommentBean;
import com.nokia.tudms.beans.tool.ToolDetailBean;
import com.nokia.tudms.dao.admin.SubAdminManagementDAO;
import com.nokia.tudms.dao.tool.*;

import java.util.ArrayList;

/**
 * @Author Rin
 * @Date 2017/4/25
 */
public class UADAOTest {
    public static void main(String[] args) {
        try {
            PushDAO.pushMessage(101,1,"Title","Content","http");
            /*Gson gson = new Gson();
            String json = gson.toJson(SubAdminManagementDAO.getSubAdminInfoList());
            System.out.println(json);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

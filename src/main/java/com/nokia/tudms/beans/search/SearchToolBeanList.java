package com.nokia.tudms.beans.search;

import java.util.ArrayList;

/**
 * Created by Lee on 2017/4/27.
 * 搜索得到的工具列表
 */
public class SearchToolBeanList {
    private int num = 0;
    private ArrayList<SearchToolBean> list = new ArrayList<SearchToolBean>();

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public ArrayList<SearchToolBean> getList() {
        return list;
    }

    public void setList(ArrayList<SearchToolBean> list) {
        this.list = list;
    }

    public void addSearchToolBean(SearchToolBean searchToolBean){
        this.list.add(searchToolBean);
        this.num++;
    }
}

package com.nokia.tudms.beans.search;

import java.util.ArrayList;

/**
 * Created by Lee on 2017/4/28.
 * 按发布者搜索的结果列表
 */
public class SearchUploaderBeanList {

    private int num = 0;
    private ArrayList<SearchUploaderBean> list = new ArrayList<SearchUploaderBean>();

    public void addSearchUploaderBean(SearchUploaderBean searchUploaderBean){
        list.add(searchUploaderBean);
        this.num += 1;
    }

    public void removeSearchUploaderBean(SearchUploaderBean searchUploaderBean){
        list.remove(searchUploaderBean);
        if(this.num > 0){
            this.num--;
        }
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public ArrayList<SearchUploaderBean> getList() {
        return list;
    }

    public void setList(ArrayList<SearchUploaderBean> list) {
        this.list = list;
    }
}

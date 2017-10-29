package com.nokia.tudms.utils;

import java.util.ArrayList;

/**
 * Created by Lee on 2017/5/2.
 * 将String数组转化为ArrayList
 */
public class StringArrayToArrayList {
    public static ArrayList<String> trans(String[] array){
        ArrayList<String> list = new ArrayList<String>();
        for (String e : array){
            list.add(e);
        }
        return list;
    }
}

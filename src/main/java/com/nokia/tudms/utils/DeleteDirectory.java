package com.nokia.tudms.utils;

import java.io.File;

/**
 * Created by Lee on 2017/5/18.
 */
public class DeleteDirectory {
    public static boolean deleteDir(File dir){
        if (dir.isDirectory()){
            String[] child = dir.list();
            for (int i = 0; i < child.length; i++){
                boolean success = deleteDir(new File(dir, child[i]));
                if (!success){
                    return false;
                }
            }
        }
        return dir.delete();
    }
}

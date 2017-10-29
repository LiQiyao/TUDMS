package com.nokia.tudms.utils;

import com.nokia.tudms.beans.LoggedUser;
import sun.rmi.runtime.Log;

import java.util.HashMap;

/**
 * 该类用于管理Token
 * @Author Rin
 * @Date 2017/4/24
 */
public class TokenManager {
    private static TokenManager uniqueTokenManager = new TokenManager();
    private HashMap<String,LoggedUser> tokenMap;
    private HashMap<Integer,LoggedUser> userIdMap;
    private TokenManager(){
        tokenMap = new HashMap<String, LoggedUser>();
        userIdMap = new HashMap<Integer, LoggedUser>();
    }
    public static TokenManager getInstance(){
        return uniqueTokenManager;
    }
    /**
     * 向Token管理器中添加用户
     * @param userId
     * @param userName
     * @param userLevel 用户权限等级
     * @return 返回记录用户信息的beans.LoggedUser
     */
    public LoggedUser addUser(int userId,String userName,int userLevel){
        String token = MD5Calculator.getMD5(String.valueOf(userId)+String.valueOf(System.nanoTime()));
        LoggedUser newUser = new LoggedUser(userId,userName,token,userLevel);
        tokenMap.put(token,newUser);
        userIdMap.put(userId,newUser);
        return newUser;
    }

    /**
     * 向Token管理器中添加用户，并默认权限等级为0（普通用户）
     * @param userId
     * @param userName
     * @return 返回记录用户信息的beans.LoggedUser
     */
    public LoggedUser addUser(int userId,String userName){
        String token = MD5Calculator.getMD5(String.valueOf(userId)+String.valueOf(System.nanoTime()));
        LoggedUser newUser = new LoggedUser(userId,userName,token,0);
        tokenMap.put(token,newUser);
        userIdMap.put(userId,newUser);
        return newUser;
    }
    public LoggedUser findUser(String token){
        return tokenMap.get(token);
    }
    public LoggedUser findUser(int userId){
        return userIdMap.get(userId);
    }
    public void removeUser(String token){
        LoggedUser user = tokenMap.get(token);
        tokenMap.remove(token);
        if(user!=null){
            userIdMap.remove(user.getUserId());
        }
    }
    public void removeUser(int userId){
        LoggedUser user = userIdMap.get(userId);
        userIdMap.remove(userId);
        if(user!=null){
            tokenMap.remove(user.getUserToken());
        }
    }
}

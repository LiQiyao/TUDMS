package com.nokia.tudms.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Rin on 2017/4/24.
 * 根据一串字符串生成一个32字的MD5加密计算摘要
 */
public class MD5Calculator {
    public static String getMD5(String str) {
        // 生成一个32字的MD5加密计算摘要
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}

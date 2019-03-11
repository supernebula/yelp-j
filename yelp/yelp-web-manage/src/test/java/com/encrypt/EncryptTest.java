package com.encrypt;

import evol.security.MD5Util;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;


public class EncryptTest {

    @Test
    public void  ShiroOrMD5BySaltTest(){
        String password = "123456";
        String salt = "abcdefg";
        String val1 = new SimpleHash("MD5", password, salt).toString();
        String val2 = MD5Util.MD5(password, salt);
        System.out.println("SimpleHash val1:" + val1);
        System.out.println("MD5Util val2:" + val2);
    }

    @Test
    public void  ShiroOrMD5Test(){
        String password = "123456";
        String salt = "abcdefg";
        String val1 = new SimpleHash("MD5", password).toString();
        String val2 = MD5Util.MD5(password);
        System.out.println("SimpleHash val1:" + val1);
        System.out.println("MD5Util val2:" + val2);

    }
}

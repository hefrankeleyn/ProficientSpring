package com.hef.web;

import com.hef.placeholder.DESUtils;
import com.hef.utils.OneDESUtils;

/**
 * @Date 2020/5/31
 * @Author lifei
 */
public class EncodeApp {

    public static void main(String[] args) {
        String username = "root";
        String password = "spring$pass";
        myDESUtilTest(username);
        myDESUtilTest(password);

//        oneDESUtilTest(username);
//        oneDESUtilTest(password);

    }

    private static void oneDESUtilTest(String str){
        String encryptString = OneDESUtils.getEncryptString(str);
        System.out.println("encryptString: "+encryptString);
        String decryptString = OneDESUtils.getDecryptString(encryptString);
        System.out.println("decryptString: "+decryptString);
    }


    private static void myDESUtilTest(String str){
        String encryptString = DESUtils.getEncryptString(str);
        System.out.println("encryptString: "+encryptString);
        String decryptString = DESUtils.getDecryptString(encryptString);
        System.out.println("decryptString: "+decryptString);
    }
}

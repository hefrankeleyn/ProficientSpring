package com.hef.conf;

import com.hef.placeholder.DESUtils;
import com.hef.utils.OneDESUtils;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.io.UnsupportedEncodingException;

/**
 * @Date 2020/6/2
 * @Author lifei
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private String[] encryptPropNames = {"username","password"};

    /**
     * 对特定属性对属性之进行转换
     * @param propertyName
     * @param propertyValue
     * @return
     */
    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if (isEncryptProp(propertyName)){
            System.out.println(propertyValue);
            String decryptString = null;
            decryptString = DESUtils.getDecryptString(propertyValue);
            System.out.println(decryptString);
            return decryptString;
        }else {
            return propertyValue;
        }
    }

    /**
     * 判断是否为需要解密对属性
     * @param propertyName
     * @return
     */
    private boolean isEncryptProp(String propertyName){
        for (String encryptPropName : encryptPropNames) {
            if (encryptPropName.equals(propertyName)){
                return true;
            }
        }
        return false;
    }
}

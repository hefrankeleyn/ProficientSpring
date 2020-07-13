package com.hef.service;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author lifei
 * @since 2020/7/13
 */
public class TTypeExprSample {

    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();

        Class stringClass = parser.parseExpression("T(java.lang.String)").getValue(Class.class);
        System.out.println(stringClass == String.class);

        //  直接调用静态方法
        Object randomValue = parser.parseExpression("T(java.lang.Math).random()").getValue();
        System.out.println(randomValue);
    }
}

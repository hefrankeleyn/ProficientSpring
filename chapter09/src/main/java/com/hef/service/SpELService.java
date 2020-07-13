package com.hef.service;

import com.hef.beans.User;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author lifei
 * @since 2020/7/12
 */
public class SpELService {

    public static void main(String[] args) {
//        testStrConcat();
        objPropertiesValue();
    }

    private static void objPropertiesValue(){
        User user = new User();
        user.setUserName("XiaoMing");
        user.setCredits(100);
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext(user);
        Object userName = parser.parseExpression("userName").getValue(context);
        System.out.println(userName);
    }

    /**
     * 字符串的拼接
     */
    private static void testStrConcat(){
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("'HelloWorld'.concat('!')");
        String value = (String)expression.getValue();
        System.out.println(value);
        System.out.println(expression.getValue(String.class));
    }
}

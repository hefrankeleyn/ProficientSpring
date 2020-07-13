package com.hef.service;

import com.hef.beans.User;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author lifei
 * @since 2020/7/13
 */
public class IfThenElseExprSample {

    public static void main(String[] args) {
        User user = new User();
        user.setUserName("Tom");

        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext(user);

        String result = parser.parseExpression("userName=='Tom'?'hello '+userName:'not Tom'").getValue(context, String.class);
        System.out.println(result);
        user.setUserName(null);
        String elvisValue = parser.parseExpression("userName?:'empty Username'").getValue(context, String.class);
        System.out.println(elvisValue);
    }
}

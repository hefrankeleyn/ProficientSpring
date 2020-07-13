package com.hef.service;

import com.hef.beans.User;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author lifei
 * @since 2020/7/13
 */
public class NewObjExprSample {

    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        User value = parser.parseExpression("new com.hef.beans.User('aaa')").getValue(User.class);
        System.out.println(value);

        // 设置一个变量
        EvaluationContext context = new StandardEvaluationContext(value);
        context.setVariable("oneUserName", "Tom");
        parser.parseExpression("userName=#oneUserName").getValue(context);
        System.out.println(value.getUserName());
    }
}

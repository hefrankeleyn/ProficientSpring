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
public class ObjectExprSample {

    public static void main(String[] args) {
        User user = new User();
        user.setUserName("Tom");
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext(user);
        // 通过 setValue赋值
        parser.parseExpression("userName").setValue(context, "XiaoMing");
        System.out.println(user.getUserName());
        // 通过 表达式赋值
        parser.parseExpression("userName='xiaoHong'").getValue(context);
        System.out.println(user.getUserName());
    }
}

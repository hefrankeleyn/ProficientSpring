package com.hef.service;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author lifei
 * @since 2020/7/12
 */
public class SpELHello {

    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("'hello' + ' world'");
        String message= (String) expression.getValue();
        System.out.println(message);
    }
}

package com.hef.service;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author lifei
 * @since 2020/7/13
 */
public class LiteralExprSample {

    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();

        String helloWorld = parser.parseExpression("'Hello World'").getValue(String.class);

        double doubleNumber = (Double) parser.parseExpression("6.0221415E+23").getValue();
        System.out.println(doubleNumber);// 6.0221415E23

    }
}

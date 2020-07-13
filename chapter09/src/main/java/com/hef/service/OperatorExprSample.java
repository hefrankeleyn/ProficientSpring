package com.hef.service;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author lifei
 * @since 2020/7/13
 */
public class OperatorExprSample {

    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();

        Boolean trueValue = parser.parseExpression("2 == 2").getValue(Boolean.class);
        System.out.println(trueValue);

        trueValue = parser.parseExpression("'black' < 'block'").getValue(Boolean.class);
        System.out.println(trueValue);

        Boolean falseValue = parser.parseExpression("'xyz' instanceof T(int)").getValue(Boolean.class);
        System.out.println(falseValue);

        Boolean value = parser.parseExpression("'5.00' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class);
        System.out.println(value);
    }
}

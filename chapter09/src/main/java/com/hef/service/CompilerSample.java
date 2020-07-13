package com.hef.service;

import com.hef.beans.User;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 *
 * @author lifei
 * @since 2020/7/13
 */
public class CompilerSample {

    public static void main(String[] args) {
        User user = new User();
        // 创建解析配置
        SpelParserConfiguration configuration = new SpelParserConfiguration(SpelCompilerMode.IMMEDIATE,
                CompilerSample.class.getClassLoader());
        // 创建解析器
        SpelExpressionParser parser = new SpelExpressionParser(configuration);

        // 创建获取上下文
        EvaluationContext context = new StandardEvaluationContext(user);

        // 表达式
        String expression = "isVipMember('tom') && isVipMember('pip')";

        // 解析表达式
        Expression spELExpression = parser.parseRaw(expression);

        // 通过表达式求值
        System.out.println(spELExpression.getValue(context));
        System.out.println(spELExpression.getValue(context));
    }
}

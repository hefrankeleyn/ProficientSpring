package com.hef.service;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.*;

/**
 * @author lifei
 * @since 2020/7/13
 */
public class CollectExprSample {

    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        // 过滤list
        List<Integer> valueList = new ArrayList<>();
        valueList.addAll(Arrays.asList(2,3,6,10,5,11,13));
        context.setVariable("list", valueList);
        List<Integer> value = (List<Integer>) parser.parseExpression("#list.?[#this>5]").getValue(context);
        System.out.println(value);
        // 过滤map
        Map<String, Integer> map = new HashMap<>();
        map.put("aa", 23);
        map.put("bb", 12);
        map.put("cc", 6);

        context.setVariable("map", map);
        // 过滤出子集
        Map<String, Integer> map1= (Map<String, Integer>) parser.parseExpression("#map.?[value>7]").getValue(context);
        System.out.println(map1);
        // 取第一个值
        Map<String, Integer> map2= (Map<String, Integer>) parser.parseExpression("#map.^[value>7]").getValue(context);
        System.out.println(map2);
        // 取最后一个值
        Map<String, Integer> map3= (Map<String, Integer>) parser.parseExpression("#map.$[value>7]").getValue(context);
        System.out.println(map3);

        // 集合的转换
        List<Boolean> booleanList= (List<Boolean>) parser.parseExpression("#list.![#this>10]").getValue(context);
        System.out.println(booleanList);
    }
}

package com.smart.util;

import com.smart.entity.Question;

import java.util.List;

/**
 * 生成题目
 */
public interface IProduceQuestionUtil {
    /**
     * 生成题目
     *
     * @return 将生成的题目返回（包含问题和答案）
     */
    List<Question> produce(int n, int r);

    /**
     *
     */
    boolean compareSuffix(Question question1, Question question2);

    /**
     * 根据后缀表达式生成中缀表达式
     *
     * @param suffix 传入的后缀表达式
     */
    String suffixToInfix(String suffix);
}

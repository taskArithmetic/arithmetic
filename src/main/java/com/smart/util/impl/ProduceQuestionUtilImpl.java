package com.smart.util.impl;

import com.smart.entity.Question;
import com.smart.util.IOperandUtil;
import com.smart.util.IOperatorUtil;
import com.smart.util.IProduceQuestionUtil;

import java.util.Random;

public class ProduceQuestionUtilImpl implements IProduceQuestionUtil {

    /**
     * 生成问题，将问题返回，问题包括：中缀表达式，后缀表达式，答案
     *
     * @param r 该参数是用户指定的操作数的最大值
     */
    public Question produce(int r) {
        Question question = new Question();
        Random random = new Random();
        ArithmeticUtilImpl arithmeticUtil = new ArithmeticUtilImpl();

        // 操作符最多有3个，则操作数最多有四个、最少有两个
        int operandCount = random.nextInt(3) + 2;
        String suffixQuestion = OperandUtilImpl.randomOperand(r);
        for (int i = 0; i < operandCount - 1; i++) {
            suffixQuestion += OperandUtilImpl.randomOperand(r);
            suffixQuestion += IOperatorUtil.randomOperator();
        }
        // 将中缀表达式，后缀表达式，答案封装进question中
        question.setSuffixQuestion(suffixQuestion);
        question.setInfixQuestion(suffixToInfix(suffixQuestion));
        arithmeticUtil.operate(question);
        return question;
    }

    public String suffixToInfix(String suffix) {
        return suffix;
    }
}

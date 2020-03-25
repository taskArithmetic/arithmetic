package com.smart.util.impl;

import com.smart.entity.Question;
import com.smart.util.IOperandUtil;
import com.smart.util.IOperatorUtil;
import com.smart.util.IProduceQuestionUtil;

import java.util.Random;

public class ProduceQuestionImpl implements IProduceQuestionUtil {
    @Override
    public Question produce(int r) {
        Question question = new Question();
        Random random = new Random();
        int operandCount = random.nextInt(3) + 2;
        String[] operands = new String[operandCount];
        String[] operators = new String[operandCount - 1];
        for (int i = 0; i < operandCount; i++) {
            operands[i] = IOperandUtil.randomOperand(r);
            operators[i] = IOperatorUtil.randomOperator();
        }

        return question;
    }
}

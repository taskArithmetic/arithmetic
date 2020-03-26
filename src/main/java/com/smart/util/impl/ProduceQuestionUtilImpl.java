package com.smart.util.impl;

import com.smart.entity.ArithmeticStack;
import com.smart.entity.Question;
import com.smart.util.IOperandUtil;
import com.smart.util.IOperatorUtil;
import com.smart.util.IParamsUtil;
import com.smart.util.IProduceQuestionUtil;
import org.w3c.dom.css.CSSUnknownRule;

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
        String suffixQuestion = OperandUtilImpl.randomOperand(r)+" ";
        for (int i = 0; i < operandCount - 1; i++) {
            suffixQuestion += OperandUtilImpl.randomOperand(r) + " ";
            suffixQuestion += OperatorUtilImpl.randomOperator() +" ";
        }
        // 将中缀表达式，后缀表达式，答案封装进question中
        question.setSuffixQuestion(suffixQuestion);
        question.setInfixQuestion(suffixToInfix(suffixQuestion));
        arithmeticUtil.operate(question);
        return question;
    }

    public String suffixToInfix(String suffix) {
        ArithmeticStack stack = new ArithmeticStack(10);
        IOperatorUtil operatorUtil = new OperatorUtilImpl();
        // 记录前一个操作符
        char preOperator = ' ';

        for (int i = 0; i < suffix.length(); i++) {
            char curOperator = suffix.charAt(i);
            if ((curOperator >= '0' && curOperator <= '9') || curOperator == '/') {
                String value = "";
                while (curOperator != ' ') {
                    value += curOperator;
                    curOperator = suffix.charAt(++i);
                }
                stack.push(value);
            } else {
                if (curOperator != ' ') {
                    String y = stack.pop();
                    String x = stack.pop();

                    if (operatorUtil.getOperatorOrder(curOperator)
                            > operatorUtil.getOperatorOrder(preOperator)) {
                        stack.push("( " + x + " ) " + curOperator + " " + y);
                    } else {
                        stack.push(x + " " + curOperator + " " + y);
                    }
                    preOperator = curOperator;
                }
            }
        }
        return stack.pop();
    }
}

package com.smart.util.impl;

import com.smart.entity.ArithmeticStack;
import com.smart.entity.Question;
import com.smart.util.*;

import java.util.*;

public class ProduceQuestionUtilImpl implements IProduceQuestionUtil {

    /**
     * 生成问题，将问题返回，问题包括：中缀表达式，后缀表达式，答案
     *
     * @param r 该参数是用户指定的操作数的最大值
     */
    public List<Question> produce(int n, int r) {
        // 存放要返回的question列表
        List<Question> questions = new ArrayList<>();
        Random random = new Random();
        IArithmeticUtil arithmeticUtil = new ArithmeticUtilImpl();
        Map<Question, String> questionStringMap = new HashMap<>();
        boolean flag;

        while (questions.size() < n) {
            flag = true;
            Question question = new Question();
            // 操作符最多有3个，则操作数最多有四个、最少有两个
            int count = random.nextInt(3) + 2;
            int operandCount = 0;
            int operatorCount = 0;
            String suffixQuestion = "";
            while (operatorCount != operandCount - 1 || operandCount < count) {
                if (operandCount == count) {
                    suffixQuestion += OperatorUtilImpl.randomOperator() + " ";
                    operatorCount++;
                    continue;
                }
                if (operandCount - operatorCount > 1) {
                    switch (random.nextInt(2)) {
                        case 0:
                            suffixQuestion += OperandUtilImpl.randomOperand(r) + " ";
                            operandCount++;
                            break;
                        case 1:
                            suffixQuestion += OperatorUtilImpl.randomOperator() + " ";
                            operatorCount++;
                            break;
                    }
                } else {
                    suffixQuestion += OperandUtilImpl.randomOperand(r) + " ";
                    operandCount++;
                }
            }
            // 将中缀表达式，后缀表达式，答案封装进question中
            question.setSuffixQuestion(suffixQuestion);
            question.setInfixQuestion(suffixToInfix(suffixQuestion));
            if (!arithmeticUtil.operate(question)) {
                continue;
            }
            if (questionStringMap.containsValue(question.getAnswer())) {
                for (Map.Entry<Question, String> entry : questionStringMap.entrySet()) {
                    // 当答案一样时，进行栈比较，如果栈比较返回的结果为“重复”，将flag设为false
                    if (entry.getValue().equals(question.getAnswer()) &&
                            compareSuffix(entry.getKey(), question)) {
                        flag = false;
                    }
                }
            }
            if (flag) {
                questionStringMap.put(question, question.getAnswer());
                questions.add(question);
            }
        }
        return questions;
    }

    /**
     * 对两个表达式进行查重，方法：
     * 首先判断两个表达式是否完全相同，如果完全相同，返回true，否则继续执行方法
     *
     * @return 如果题目重复返回true，不重复返回false
     */
    public boolean compareSuffix(Question q1, Question q2) {
        String suffix1 = q1.getSuffixQuestion();
        String suffix2 = q2.getSuffixQuestion();
        if (suffix1.length() != suffix2.length()) {
            return false;
        }
        if (suffix1.equals(suffix2)) {
            return true;
        }
        ArithmeticStack stack1 = new ArithmeticStack(suffix1.length());
        ArithmeticStack stack2 = new ArithmeticStack(suffix2.length());
        String[] params1 = suffix1.split(" ");
        String[] params2 = suffix2.split(" ");
        int j = 0;
        for (String s : params1) {
            if (s.matches("[+\\-*÷]")) {
                String s1_1 = stack1.pop();
                String s1_2 = stack1.pop();
                while (!params2[j].matches("[+\\-*÷]")) {
                    stack2.push(params2[j]);
                    j++;
                }
                if (!s.equals(params2[j])) {
                    return false;
                }
                String s2_1 = stack2.pop();
                String s2_2 = stack2.pop();
                IOperatorUtil operatorUtil = new OperatorUtilImpl();
                // 判断s1_1和s1_2是否为运算式
                if (operatorUtil.hasOperator(s1_1) || operatorUtil.hasOperator(s1_2)) {
                    if (!operatorUtil.hasOperator(s2_1) && !operatorUtil.hasOperator(s2_2)) {
                        return false;
                    }
                    if (operatorUtil.hasOperator(s1_1)) {
                        if (operatorUtil.hasOperator(s2_1)) {
                            if (!s1_2.equals(s2_2)) {
                                return false;
                            }
                        } else {
                            if (!s1_2.equals(s2_1)) {
                                return false;
                            }
                        }
                    } else {
                        if (operatorUtil.hasOperator(s2_1)) {
                            if (!s1_1.equals(s2_2)) {
                                return false;
                            }
                        } else {
                            if (!s1_1.equals(s2_1)) {
                                return false;
                            }
                        }
                    }
                } else {
                    if (!((s1_1.equals(s2_1) && s1_2.equals(s2_2)) ||
                            (s1_1.equals(s2_2) && s1_2.equals(s2_1)))) {
                        return false;
                    }
                }
                stack1.push("(" + s1_2 + s + s1_1 + ")");
                stack2.push("(" + s2_2 + s + s2_1 + ")");
                j++;
            } else if (!s.matches("[+\\-*÷]") &&
                    !s.equals(" ")) {
                stack1.push(s);
            }
        }
        return true;
    }

    public String suffixToInfix(String suffix) {
        ArithmeticStack stack = new ArithmeticStack(10);
        IOperatorUtil operatorUtil = new OperatorUtilImpl();
        // 记录前一个操作符
        String preOperator = " ";
        String curOperator;

        String[] params = suffix.split(" ");
        for (String param : params) {
            if (param.matches("[+\\-*÷]")) {
                // 读取到的是操作符
                curOperator = param;
                String s1 = stack.pop();
                String s2 = stack.pop();
                if (operatorUtil.getOperatorOrder(preOperator) <=
                        operatorUtil.getOperatorOrder(curOperator)) {
                    if (operatorUtil.hasOperator(s2)) {
                        s2 = "( " + s2 + " )";
                    }
                    if (operatorUtil.hasOperator(s1)) {
                        s1 = "( " + s1 + " )";
                    }
                }
                stack.push(s2 + " " + param + " " + s1);
                preOperator = curOperator;
            } else {
                // 读取到的是运算数
                stack.push(param);
            }
        }
        return stack.pop().replace(" ", "");
    }
}

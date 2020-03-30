package com.smart.util.impl;

import com.smart.entity.ArithmeticStack;
import com.smart.entity.Question;
import com.smart.util.IArithmeticUtil;

public class ArithmeticUtilImpl implements IArithmeticUtil {

    public boolean operate(Question question) {
        String suffix = question.getSuffixQuestion();
        String[] params = suffix.split(" ");
        String result;     // 存放中间结果
        ArithmeticStack stack = new ArithmeticStack(suffix.length());
        for (String param : params) {
            if (param.matches("[+\\-*÷]")) {      // 遇到操作符，弹出两个操作数计算并进栈
                String operand1 = stack.pop();
                String operand2 = stack.pop();
                result = compute(operand1, operand2, param);
                if (result == null) {
                    return false;
                }
                stack.push(result);
            } else {    // 遇到数字直接进栈
                stack.push(param);
            }
        }
        result = simplify(stack.pop());
        question.setAnswer(result);
        return true;
    }

    public String compute(String number1, String number2, String operator) {
        String result = null;
        int[] numerator = new int[2];      // 两个操作数的分子
        int[] denominator = new int[2];    // 两个操作数的分母

        String[] operand1 = number1.split("/");
        String[] operand2 = number2.split("/");

        if (operand1.length == 1) {       // 判断是否为分数
            numerator[1] = Integer.parseInt(operand1[0]);
            denominator[1] = 1;
        } else {
            denominator[1] = Integer.parseInt(operand1[1]);
            if (operand1[0].contains("'")) {   // 判断是否为带分数
                String[] num = operand1[0].split("'");
                numerator[1] = Integer.parseInt(num[0]) * denominator[1] +
                        Integer.parseInt(num[1]);
            } else {
                numerator[1] = Integer.parseInt(operand1[0]);
            }
        }

        if (operand2.length == 1) {       // 判断是否为分数
            numerator[0] = Integer.parseInt(operand2[0]);
            denominator[0] = 1;
        } else {
            denominator[0] = Integer.parseInt(operand2[1]);
            if (operand2[0].contains("'")) {   // 判断是否为带分数
                String[] num = operand2[0].split("'");
                numerator[0] = Integer.parseInt(num[0]) * denominator[0] +
                        Integer.parseInt(num[1]);
            } else {
                numerator[0] = Integer.parseInt(operand2[0]);
            }
        }

        switch (operator) {
            case "+":
                result = (numerator[0] * denominator[1] + numerator[1] * denominator[0]) + "/" + (denominator[0] * denominator[1]);
                break;
            case "-":
                int i = numerator[0] * denominator[1] - numerator[1] * denominator[0];
                if (i < 0) {
                    return null;
                }
                result = i + "/" + (denominator[0] * denominator[1]);
                break;
            case "*":
                result = (numerator[0] * numerator[1]) + "/" + (denominator[0] * denominator[1]);
                break;
            case "÷":
                if (numerator[0] * denominator[1] > numerator[1] * denominator[0]) {
                    return null;
                }
                if (numerator[1] * denominator[0] == 0) {
                    return null;
                }
                result = (numerator[0] * denominator[1]) + "/" + (numerator[1] * denominator[0]);
                break;
        }
        return result;
    }

    public String simplify(String fraction) {
        String[] parts;
        if (fraction.contains("/")) {
            parts = fraction.split("/");
            if (parts[0].contains("'")) {   // 判断是否为带分数
                String[] num = parts[0].split("'");
                parts[0] = Integer.toString(Integer.parseInt(num[0]) * Integer.parseInt(parts[1]) +
                        Integer.parseInt(num[1]));
            }
        } else {
            fraction += "/1";
            parts = fraction.split("/");
        }

        // 得到分子分母
        int numerator = Integer.parseInt(parts[0]);
        int denominator = Integer.parseInt(parts[1]);
        int gcd;           // 最大公约数
        int simNumerator;  // 约分后的分子
        int simDenominator; // 约分后的分母

        // 如果分子为0，返回0
        if (numerator == 0)
            return "0";

        gcd = getGcd(numerator, denominator);
        simNumerator = numerator / gcd;
        simDenominator = denominator / gcd;

        if (simDenominator == 1)
            return simNumerator + "";

        // 将假分数转换为真分数
        if (simNumerator >= simDenominator) {
            int consult;
            int remainder;
            consult = simNumerator / simDenominator;
            remainder = simNumerator % simDenominator;
            if (remainder == 0)
                return consult + "";
            else
                return consult + "'" + remainder + "/" + simDenominator;
        }

        return simNumerator + "/" + simDenominator;
    }

    public int getGcd(int x, int y) {
        if (y == 0)
            return x;
        else
            return getGcd(y, x % y);
    }
}

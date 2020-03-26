package com.smart.util.impl;

import com.smart.util.IOperatorUtil;

import java.util.Random;

public class OperatorUtilImpl implements IOperatorUtil {
    public static String randomOperator() {
        String[] operators = {"+", "-", "*", "÷"};
        Random random = new Random();
        int i = random.nextInt(4);
        return operators[i];
    }

    public int getOperatorOrder(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 0;
            case '*':
            case '÷':
                return 1;
            case ' ':
                return 2;
        }
        return 0;
    }
}

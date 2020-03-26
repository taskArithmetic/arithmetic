package com.smart.util.impl;

import com.smart.util.IOperatorUtil;

public class OperatorUtilImpl implements IOperatorUtil {
    public static String randomOperator(){
        return null;
    }

    public int getOperatorOrder(String operator) {
        switch (operator){
            case "(":
                return 0;
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;

        }
        return 0;
    }
}

package com.smart.util.impl;

import com.smart.util.IOperandUtil;

import java.util.Random;

public class OperandUtilImpl implements IOperandUtil {
    public static String randomOperand(int r) {
        int numerator;   // 分子
        int denominator; // 分母
        Random ran = new Random();

        denominator = ran.nextInt(15) + 1;
        numerator = ran.nextInt(denominator * r);
        String fraction = numerator + "/" + denominator;
        ArithmeticUtilImpl arithmeticUtil = new ArithmeticUtilImpl();

        return arithmeticUtil.simplify(fraction);
    }
}

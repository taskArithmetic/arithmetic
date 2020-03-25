package com.smart.util;

import java.util.Random;

/**
 * 功能：①生成随机操作?
 */
public interface IOperandUtil {
    /**
     * @return 返回生成的操作数
     */
    static String randomOperand(int r){
    	int numerator;   
    	int denominator;
    	Random ran = new Random();
    	
    	denominator = ran.nextInt(20);
    	numerator = ran.nextInt(denominator*r);
    	ArithmeticUtilImpl aImpl = new ArithmeticUtilImpl();
    	String operand = aImpl.simplify(numerator, denominator);
    	
        return operand;
    }
}

package com.smart.util;

import com.smart.entity.Question;
import java.util.List;
/**
 * 实现的功能：①使用栈�? Question 进行运算
 *            ②化�?分数
 */
public interface IArithmeticUtil {
    /**
     * 计算问题
     * @param question 计算传入问题�? question 属�?�，将答案放�? answer �?
     */
    void operate(List<String> operand, List<String> operator);

    /**
     * 化简分数
     * 将假分数化简成真分数
     * @param numerator, denominator 进行分数化简
     */
    String simplify(int numerator, int denominator);
    
    /**
     * 辗转相除法求最大公约数
     * @param x,y 求两者的最大公约数
     */
    int getGcd(int x, int y);
    
}

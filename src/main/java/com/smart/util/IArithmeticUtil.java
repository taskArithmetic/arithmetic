package com.smart.util;

import com.smart.entity.Question;
import java.util.List;
/**
 * 实现的功能：①使用栈 Question 进行运算
 *            ②化简分数
 */
public interface IArithmeticUtil {
    /**
     * 计算问题
     * @param question 计算传入问题 question ，将答案放到 answer 中
     */
    void operate(Question question);

    /**
     * 化简分数
     * 将假分数化简成真分数
     * @param fraction 进行化简的分数
     */
    String simplify(String fraction);
    
    /**
     * 辗转相除法求最大公约数
     * @param x,y 求两者的最大公约数
     */
    int getGcd(int x, int y);
    
}

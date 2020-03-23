package com.smart.util;

import com.smart.entity.Question;

/**
 * 实现的功能：①使用栈对 Question 进行运算
 *            ②化简分数
 */
public interface IArithmeticUtil {
    /**
     * 计算问题
     * @param question 计算传入问题的 question 属性，将答案放至 answer 中
     */
    void operate(Question question);

    /**
     * 化简分数
     * @param fraction 需要进行化简的分数
     */
    void simplify(String fraction);
}

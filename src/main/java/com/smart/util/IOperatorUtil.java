package com.smart.util;

/**
 * 功能：①生成随机操作
 */
public interface IOperatorUtil {
    /**
     * @return 返回随机生成的操作符
     */
    static String randomOperator() {
        return null;
    }

    /**
     * 获得操作符的优先级
     */
    int getOperatorOrder(String operator);

    /**
     * 检查参数是否含有运算符
     */
    boolean hasOperator(String param);
}

package com.smart.util;

/**
 * 实现该接口时�?编写其它的内部私有方法，在checkInputParams中组�?
 * 功能：①判断用户的输入是否合�?
 */
public interface IParamsUtil {
    /**
     * 判断输入是否合法
     *
     * @param params 用户输入的参�?
     * @return 返回是否合法
     */
    static boolean checkInputParams(String params) {
        return false;
    }

    boolean isExe(String param);

    boolean isFile(String param);

    boolean isOperator(String param);
}

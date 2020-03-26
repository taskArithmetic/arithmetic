package com.smart.util;

/**
 * 实现该接口时编写其它的内部私有方法，在checkInputParams中组装?
 * 功能：①判断用户的输入是否合法?
 */
public interface IParamsUtil {
    /**
     * 判断输入是否合法
     *
     * @param params 用户输入的参数?
     * @return 返回是否合法
     */
    static boolean checkInputParams(String params) {
        return false;
    }

    // 判断参数是否为 arithmetic.exe
    boolean isExe(String param);

    // 判断参数表示的文件是否存在
    boolean isFile(String param);

    // 判断参数表示的操作是否合法
    boolean isParam(String param);
}

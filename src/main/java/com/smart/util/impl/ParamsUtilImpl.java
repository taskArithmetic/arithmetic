package com.smart.util.impl;

import com.smart.util.IParamsUtil;

import java.io.File;

public class ParamsUtilImpl implements IParamsUtil {
    // 判断第一个参数是否为 "arithmetic.exe"
    public boolean isExe(String param) {
        return param.equals("arithmetic.exe");
    }

    public boolean isParam(String param) {
        switch (param) {
            case "-n":
            case "-r":
            case "-e":
            case "-a":
                return true;
            default:
                System.out.println("isOperator false with " + param);
                return false;
        }
    }

    public boolean isFile(String param) {
        return new File(param).exists();
    }

    /**
     * 判断输入是否合法
     *
     * @param inputParams 用户输入的参数
     * @return 返回是否合法
     */
    public static boolean checkInputParams(String inputParams) {
        String[] params = inputParams.split(" ");
        IParamsUtil paramsUtil = new ParamsUtilImpl();
        // 判断输入的第一个参数是否为 "arithmetic.exe"
        if (!paramsUtil.isExe(params[0])) {
            return false;
        }
        // 判断操作参数和文件名参数是否合法
        // 可以选择的参数：
        for (int i = 1; i < params.length; i++) {
            if (params[i].contains("-")) {
                if (i + 1 >= params.length) {
                    return false;
                }
                if (paramsUtil.isParam(params[i]) &&
                        (params[i + 1].matches("[0-9]+") || paramsUtil.isFile(params[i + 1]))) {
                    continue;
                }
                return false;
            } else if (params[i].contains(".")) {
                if (paramsUtil.isFile(params[i])) {
                    continue;
                }
                return false;
            } else {
                if (params[i].matches("[0-9]+")) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }
}

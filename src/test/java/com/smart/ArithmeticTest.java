package com.smart;


import com.smart.util.IParamsUtil;
import com.smart.util.impl.ParamsUtilImpl;
import org.junit.Test;

import java.io.File;

public class ArithmeticTest {
    @Test
    public void testParamsUtil() {
        String inputParams1 = "a.exe -n 100 -r 10";
        String inputParams2 = "arithmetic.exe -n -r 10";
        String inputParams3 = "arithmetic.exe -n 100 -r";
        String inputParams4 = "arithmetic.exe -e testFile/test1.txt -a";
        String inputParams5 = "arithmetic.exe -n 100 -r 10 -e testFile/test1.txt";
//        System.out.println(inputParams1+" : "+ParamsUtilImpl.checkInputParams(inputParams1));
//        System.out.println(inputParams2+" : "+ParamsUtilImpl.checkInputParams(inputParams2));
//        System.out.println(inputParams3+" : "+ParamsUtilImpl.checkInputParams(inputParams3));
//        System.out.println(inputParams4+" : "+ParamsUtilImpl.checkInputParams(inputParams4));
        System.out.println(inputParams5+" : "+ParamsUtilImpl.checkInputParams(inputParams5));
    }
}

package com.smart.util.impl;

import com.smart.entity.Question;
import com.smart.util.IArithmeticUtil;

public class ArithmeticUtilImpl implements IArithmeticUtil {

    public void operate(Question question) {

    }

    public String simplify(String fraction) {
        // 得到分子分母
        int numerator = Integer.parseInt(fraction.split("/")[0]);
        int denominator = Integer.parseInt(fraction.split("/")[1]);
        int gcd;           // 最大公约数
        int simNumerator;  // 约分后的分子
        int simDenominator; // 约分后的分母

        // 如果分子为0，返回0
        if (numerator == 0)
            return "0";

        gcd = getGcd(numerator, denominator);
        simNumerator = numerator / gcd;
        simDenominator = denominator / gcd;

        if (simDenominator == 1)
            return simNumerator + "";

        // 将假分数转换为真分数
        if (simNumerator >= simDenominator) {
            int consult;
            int remainder;
            consult = simNumerator / simDenominator;
            remainder = simNumerator % simDenominator;
            if (remainder == 0)
                return consult + "";
            else
                return consult + "'" + remainder + "/" + simDenominator;
        }

        return simNumerator + "/" + simDenominator;
    }

    public int getGcd(int x, int y) {
        if (y == 0)
            return x;
        else
            return getGcd(y, x % y);
    }


}

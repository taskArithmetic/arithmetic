package com.smart.util.impl;

import com.smart.entity.ArithmeticStack;
import com.smart.entity.Question;
import com.smart.util.IArithmeticUtil;

public class ArithmeticUtilImpl implements IArithmeticUtil {

    public void operate(Question question) {
    	
        String suffix = question.getSuffixQuestion();
        String[] params = suffix.split(" ");
        String result = null;     // 存放中间结果
        ArithmeticStack stack = new ArithmeticStack(suffix.length());
       for(String param : params) { 
    	   if(param.matches("[\\+\\-\\*÷]")) {      // 遇到操作符，弹出两个操作数计算并进栈
    		   String[] operand;
    		   int[] numerator = new int[2];      // 两个操作数的分子
    		   int[] denominator = new int[2];    // 两个操作数的分母

    		   for(int i=1;i>=0;i--) {
    			   operand = stack.pop().split("/");
    			   if(operand.length==1) {       // 判断是否为分数
    				   numerator[i] = Integer.parseInt(operand[0]);
    				   denominator[i] = 1;
    			   }else { 
    				   denominator[i] = Integer.parseInt(operand[1]);
    				   if(operand[0].contains("'")) {   // 判断是否为带分数
    					   String[] num = operand[0].split("'");
    					   numerator[i] = Integer.parseInt(num[0])*denominator[i]+Integer.parseInt(num[1]);
    				   }
    				   else
    					   numerator[i] = Integer.parseInt(operand[0]);
    			   }
    			 
    		   }
    		 
    		   switch(param) {
    		      case "+":
    		    	  result = (numerator[0]*denominator[1]+numerator[1]*denominator[0]) + "/" + (denominator[0]*denominator[1]); break;
    		      case "-":
    		    	  result = (numerator[0]*denominator[1]-numerator[1]*denominator[0]) + "/" + (denominator[0]*denominator[1]); break;
    		      case "*":
    		    	  result = (numerator[0]*numerator[1]) + "/" + (denominator[0]*denominator[1]); break;
    		      case "÷":
    		    	  result = (numerator[0]*denominator[1]) + "/" + (numerator[1]*denominator[0]); break;
    		    	 
    		   }
    		   stack.push(result);
    		   
    	   }else {    // 遇到数字直接进栈
    		   stack.push(param);     
    	   }	   
       }
	   result = simplify(stack.pop());
       question.setAnswer(result);
       
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

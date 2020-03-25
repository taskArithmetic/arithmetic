package com.smart.util;

import java.util.List;

import com.smart.entity.Question;

public class ArithmeticUtilImpl implements IArithmeticUtil{
	
	public ArithmeticUtilImpl(){
		
	}

	@Override
	public void operate(List<String> operand, List<String> operator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String simplify(int numerator, int denominator) {
		// TODO Auto-generated method stub
		int gcd;           // 最大公约数
		int simNumerator;  // 约分后的分子
		int simDenominator; // 约分后的分母
	
		if(numerator == 0)
			return "0";
		
		gcd = getGcd(numerator,denominator);
		simNumerator = numerator/gcd;
		simDenominator = denominator/gcd;
		
		if(simDenominator == 1)
			return simNumerator + "";  
		
		// 将假分数转换为真分数
		if(simNumerator>=simDenominator) {
			int consult;
			int remainder;
			consult = simNumerator/simDenominator;
			remainder = simNumerator%simDenominator;
			if(remainder == 0)
				return consult + "";
			else
				return consult + "'" + remainder + "/" + simDenominator;
		}
			
		return simNumerator + "/" + simDenominator;
	}

	public int getGcd(int x, int y) {
		if(y == 0)
			return x;
		else
			return getGcd(y,x%y);	
	}


}

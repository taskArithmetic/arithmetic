package com.smart;

import java.util.*;

import com.smart.entity.Question;
import com.smart.util.*;

public class ArithmeticMain {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String arg1 = new String();  // 第一个命令符
		String arg2 = new String();  // 第二个命令符
		int num1 = 0;                    // 第一个参数
		int num2 = 0;                    // 第二个参数
		List<Question> question_list = new ArrayList<>();
		Scanner scan = new Scanner(System.in);
		System.out.println("ָ命令符说明 ");
		System.out.println("-n 参数控制生成题目的个数");
		System.out.println("-r 参数控制题目中数值（自然数、真分数和真分数分母）的范围 ");
		System.out.println("******************************************************");
		System.out.println("请输入命令: ");
       
		if(scan.hasNext()) 
			arg1 = scan.next();
		if(scan.hasNextInt()) {
			num1 = scan.nextInt();	
		}else {
			System.out.println("输入有误");
		}

		if(scan.hasNext()) 
			arg2 = scan.next();
		if(scan.hasNextInt()) {
			num2 = scan.nextInt();
		}else {
			System.out.println("输入有误");
		}
		
		ParamsUtilImpl param = new ParamsUtilImpl(arg1,num1,arg2,num2); 
		 /*
		if( true ) {   // 判断参数是否违规
			ProduceQuestionUtilImpl pquestion = new ProduceQuestionUtilImpl();
			
		}
          
	 */
	
	}
    public void show() {}
   
}

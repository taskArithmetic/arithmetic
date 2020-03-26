package com.smart;

import java.util.*;

import com.smart.entity.Question;
import com.smart.util.*;
import com.smart.util.impl.ParamsUtilImpl;

public class ArithmeticMain {


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String inputParams = new String();
        List<Question> question_list = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        System.out.println("ָ命令符说明 ");
        System.out.println("-n 参数控制生成题目的个数");
        System.out.println("-r 参数控制题目中数值（自然数、真分数和真分数分母）的范围 ");
        System.out.println("******************************************************");
        System.out.println("请输入命令: ");

        if (scan.hasNext())
            inputParams = scan.next();
        if (ParamsUtilImpl.checkInputParams(inputParams)) {   // 判断参数是否违规
//            IProduceQuestionUtil produceQuestionUtil = new ProduceQuestionUtilImpl();
        }


    }

    public void show() {
    }

}

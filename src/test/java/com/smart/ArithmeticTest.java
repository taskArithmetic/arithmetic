package com.smart;

import com.smart.util.IProduceQuestionUtil;
import com.smart.util.impl.ParamsUtilImpl;
import com.smart.entity.Question;
import com.smart.util.IArithmeticUtil;
import com.smart.util.impl.ArithmeticUtilImpl;
import com.smart.util.impl.ProduceQuestionUtilImpl;
import org.junit.Test;

public class ArithmeticTest {
    @Test
    public void testCheckParams() {
        String params1 = "a.exe -n 100 -r 10";
        String params2 = "arithmetic.exe -n a -r 10";
        String params3 = "arithmetic.exe -n -r 10";
        String params4 = "arithmetic.exe -n 100 -r a";
        String params5 = "arithmetic.exe -n 100 -r";
        String params6 = "arithmetic.exe -n 100 -r 10";
        String params7 = "arithmetic.exe -a testFile/test1.txt -e testFile/test1.txt";
        String params8 = "arithmetic.exe -n 100";
        System.out.println("params1：" + ParamsUtilImpl.checkInputParams(params1));
        System.out.println("params2：" + ParamsUtilImpl.checkInputParams(params2));
        System.out.println("params3：" + ParamsUtilImpl.checkInputParams(params3));
        System.out.println("params4：" + ParamsUtilImpl.checkInputParams(params4));
        System.out.println("params5：" + ParamsUtilImpl.checkInputParams(params5));
        System.out.println("params6：" + ParamsUtilImpl.checkInputParams(params6));
        System.out.println("params7：" + ParamsUtilImpl.checkInputParams(params7));
        System.out.println("params8：" + ParamsUtilImpl.checkInputParams(params8));
    }

    @Test
    public void testSuffixToInfix() {
        IProduceQuestionUtil produceQuestionUtil = new ProduceQuestionUtilImpl();
        String suffix = "1 2 - 3 + 4 *";
        String infix = produceQuestionUtil.suffixToInfix(suffix);
        System.out.println(infix);
    }

    @Test
    public void testProduceQuestions() {
        IProduceQuestionUtil produceQuestionUtil = new ProduceQuestionUtilImpl();
        for (int i = 0; i < 10; i++) {
            System.out.println(produceQuestionUtil.produce(10));
        }
    } 
    
    @Test
    public void testOpera() {
        IArithmeticUtil arithmeticUtil = new ArithmeticUtilImpl();
        String suffix = "8'1/20 2'1/2 ÷ 8'1/6 * 1 *";
        Question question = new Question();
        question.setSuffixQuestion(suffix);
        arithmeticUtil.operate(question);
        System.out.println(question);
    }
}

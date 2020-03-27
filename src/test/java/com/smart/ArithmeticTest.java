package com.smart;


import com.smart.entity.Question;
import com.smart.util.IArithmeticUtil;
import com.smart.util.IProduceQuestionUtil;
import com.smart.util.impl.ArithmeticUtilImpl;
import com.smart.util.impl.ProduceQuestionUtilImpl;
import org.junit.Test;

public class ArithmeticTest {
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

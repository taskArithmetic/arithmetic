package com.smart;

import com.smart.util.IIOUtil;
import com.smart.util.IProduceQuestionUtil;
import com.smart.util.impl.IOUtilImpl;
import com.smart.util.impl.ParamsUtilImpl;
import com.smart.entity.Question;
import com.smart.util.IArithmeticUtil;
import com.smart.util.impl.ArithmeticUtilImpl;
import com.smart.util.impl.ProduceQuestionUtilImpl;
import org.junit.Test;
import sun.nio.ch.IOUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        String suffix1 = "1 2 - 3 + 4 *";
        String suffix2 = "3 2 1 + +";
        String suffix3 = "4/5 7'7/9 + 8 +  ";
        String suffix4 = "2 3 4 - *";
//        String infix1 = produceQuestionUtil.suffixToInfix(suffix1);
//        String infix2 = produceQuestionUtil.suffixToInfix(suffix2);
//        String infix3 = produceQuestionUtil.suffixToInfix(suffix3);
        String infix4 = produceQuestionUtil.suffixToInfix(suffix4);
//        System.out.println(infix1);
//        System.out.println(infix2);
//        System.out.println(infix3);
        System.out.println(infix4);
    }

    @Test
    public void testProduceQuestions() {
        IProduceQuestionUtil produceQuestionUtil = new ProduceQuestionUtilImpl();
        for (Question question : produceQuestionUtil.produce(10, 10)) {
            System.out.println(question);
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

    @Test
    public void testCompareSuffix() {
        IProduceQuestionUtil produceQuestionUtil = new ProduceQuestionUtilImpl();
        Question question1 = new Question("3 1 2 + + ");
        Question question2 = new Question("1 2 + 3 + ");
        System.out.print(question1.getSuffixQuestion() + " and " + question2.getSuffixQuestion() + ": ");
        System.out.println(produceQuestionUtil.compareSuffix(question1, question2) ? "重复" : "不重复");
        question1 = new Question("3 2 1 4 + + + ");
        question2 = new Question("2 1 4 + + 3 + ");
        System.out.print(question1.getSuffixQuestion() + " and " + question2.getSuffixQuestion() + ": ");
        System.out.println(produceQuestionUtil.compareSuffix(question1, question2) ? "重复" : "不重复");
    }

    @Test
    public void testSimply() {
        IArithmeticUtil arithmeticUtil = new ArithmeticUtilImpl();
        System.out.println(arithmeticUtil.simplify("4"));
        System.out.println(arithmeticUtil.simplify("16/4"));
        System.out.println(arithmeticUtil.simplify("3/15"));
        System.out.println(arithmeticUtil.simplify("3'12/24"));
    }

    @Test
    public void testCheckAnswerFile() throws IOException {
        IIOUtil ioUtil = new IOUtilImpl();
//        ioUtil.checkAnswer("a.txt", "b.txt");
//        ioUtil.checkAnswer("questions/answer1.txt", "a.txt");
        ioUtil.checkAnswer("questions/answer1.txt",
                "questions/task1.txt");
    }
}

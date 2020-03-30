package com.smart;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import com.smart.entity.Question;
import com.smart.gui.ArithmeticFrame;
import com.smart.util.*;
import com.smart.util.impl.IOUtilImpl;
import com.smart.util.impl.ParamsUtilImpl;
import com.smart.util.impl.ProduceQuestionUtilImpl;

import javax.swing.*;

public class ArithmeticMain {
    public static void main(String[] args) throws IOException,
            ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
//        new ArithmeticFrame();
        Scanner scanner = new Scanner(System.in);
        // 提供给用户菜单
        show();
        // 读取用户输入的操作
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputParams = null;
        // 创建一个判断输入参数的类实例
        while ((inputParams = reader.readLine()) != null) {
            // 对输入的参数进行第一步判断
            if (ParamsUtilImpl.checkInputParams(inputParams)) {
                /*
                    对输入的参数进行第二步判断：
                    -n 和 -r 需同时出现；-a 和 -e 需同时出现
                 */
                if ((inputParams.contains("-n") && inputParams.contains("-r")) ||
                        inputParams.contains("-a") && inputParams.contains("-e")) {
                    String[] params = inputParams.split(" ");
                    String n = null; // 参数控制生成题目的个数
                    String r = null; // 参数控制题目中数值（自然数、真分数和真分数分母）的范围
                    String e = null; // 参数指定校对答案的题目文件
                    String a = null; // 参数指定校对答案的答案文件
                    for (int i = 0; i < params.length; i++) {
                        switch (params[i]) {
                            case "-n":
                                n = params[i + 1];
                                break;
                            case "-r":
                                r = params[i + 1];
                                break;
                            case "-e":
                                e = params[i + 1];
                                break;
                            case "-a":
                                a = params[i + 1];
                                break;
                        }
                    }
                    // 生成指定数目的题目
                    IProduceQuestionUtil produceQuestionUtil = new ProduceQuestionUtilImpl();
                    IIOUtil ioUtil = new IOUtilImpl();
                    List<Question> questions = new ArrayList<>();
                    if (n != null && r != null) {
                        System.out.println("请输入指定输出到哪个文件：");
                        String filePath;
                        while (new File(filePath = scanner.next()).exists()) {
                            System.out.println("文件已存在！");
                            System.out.println("请输入指定输出到哪个文件：");
                        }
                        questions = produceQuestionUtil.produce(Integer.parseInt(n), Integer.parseInt(r));
                        ioUtil.output(questions, filePath);
                    } else if (e != null && a != null) {
                        ioUtil.checkAnswer(a, e);
                    }
                } else {
                    System.out.println("输入的参数有误！");
                    show();
                }
            } else {
                System.out.println("输入的参数有误！");
                show();
            }
        }
    }

    // 打印菜单
    public static void show() {
        System.out.println("ָ可进行的操作：");
        System.out.println("-n =====> 参数控制生成题目的个数");
        System.out.println("-r =====> 参数控制题目中数值（自然数、真分数和真分数分母）的范围");
        System.out.println("-e =====> 参数指定校对答案的题目文件");
        System.out.println("-a =====> 参数指定校对答案的答案文件");
        System.out.println("示例操作：arithmetic.exe -n 100 -r 10");
        System.out.println("示例操作：arithmetic.exe -e exerciseFile.txt -a answerFile.txt");
        System.out.println("===============================================================");
        System.out.println("请输入操作：");
    }
}

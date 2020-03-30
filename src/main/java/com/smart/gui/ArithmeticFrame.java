package com.smart.gui;

import com.smart.entity.Question;
import com.smart.util.IIOUtil;
import com.smart.util.IProduceQuestionUtil;
import com.smart.util.impl.IOUtilImpl;
import com.smart.util.impl.ProduceQuestionUtilImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArithmeticFrame implements ActionListener {

    JFrame frame = new JFrame("Arithmetic");
    JTabbedPane tabPane = new JTabbedPane();
    Container container1 = new Container();
    Container container2 = new Container();
    JLabel nLabel = new JLabel("题目数量：");
    JLabel rLabel = new JLabel("数值范围：");
    JLabel fpLabel = new JLabel("生成路径：");
    JLabel fnLabel = new JLabel("文件名：");
    JLabel sufLabel = new JLabel(".txt");
    JLabel aLabel = new JLabel("答案文件：");
    JLabel eLabel = new JLabel("作业文件：");
    // 输入生成题目的数量
    JTextField text1 = new JTextField();
    // 输入题目的数值范围
    JTextField text2 = new JTextField();
    // 显示选择的文件路径
    JTextField text3 = new JTextField();
    // 输入题目文件文件名
    JTextField text4 = new JTextField();
    // 显示选择的答案文件
    JTextField aText = new JTextField();
    // 显示选择的作业文件
    JTextField eText = new JTextField();
    // 文件选择器
    JFileChooser fileChooser = new JFileChooser();
    // 提交按钮
    JButton createButton = new JButton("确定");
    JButton checkButton = new JButton("确定");
    // 文件路径选择路径
    JButton fpButton = new JButton("选择");
    JButton aButton = new JButton("选择");
    JButton eButton = new JButton("选择");
    // 存放输入错误信息
    List<String> errors = new ArrayList<>();

    {
        // 设置按钮格式
        createButton.setBorder(BorderFactory.createRaisedBevelBorder());
        createButton.setFocusPainted(false);
        checkButton.setBorder(BorderFactory.createRaisedBevelBorder());
        checkButton.setFocusPainted(false);
        fpButton.setBorder(BorderFactory.createRaisedBevelBorder());
        fpButton.setFocusPainted(false);
        aButton.setBorder(BorderFactory.createRaisedBevelBorder());
        aButton.setFocusPainted(false);
        eButton.setBorder(BorderFactory.createRaisedBevelBorder());
        eButton.setFocusPainted(false);
    }

    public ArithmeticFrame() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);

        // 获得显示屏幕的宽度
        double lx = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        // 获得显示屏幕的高度
        double ly = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

        // 设置窗口出现的位置
        frame.setLocation(new Point((int) (lx / 2) - 150, (int) (ly / 2) - 150));
        // 设置窗口的大小
        frame.setSize(380, 230);
        // 设置布局
        frame.setContentPane(tabPane);

        container1.setName("生成题目");
        container2.setName("校对答案");

        nLabel.setBounds(30, 20, 70, 20);
        text1.setBounds(100, 20, 65, 20);
        rLabel.setBounds(170, 20, 70, 20);
        text2.setBounds(240, 20, 65, 20);

        aLabel.setBounds(30, 20, 70, 20);
        aText.setBounds(100, 20, 165, 20);
        aButton.setBounds(270, 20, 35, 20);

        fpLabel.setBounds(30, 50, 70, 20);
        text3.setBounds(100, 50, 165, 20);
        fpButton.setBounds(270, 50, 35, 20);

        eLabel.setBounds(30, 50, 70, 20);
        eText.setBounds(100, 50, 165, 20);
        eButton.setBounds(270, 50, 35, 20);

        fnLabel.setBounds(30, 80, 70, 20);
        text4.setBounds(100, 80, 165, 20);
        sufLabel.setBounds(270, 80, 30, 20);

        checkButton.setBounds(30, 80, 80, 20);

        createButton.setBounds(30, 110, 80, 20);

        // 为按钮添加事件
        fpButton.addActionListener(this);
        createButton.addActionListener(this);
        aButton.addActionListener(this);
        eButton.addActionListener(this);
        checkButton.addActionListener(this);

        // 设置文件选择路径不可输入
        text3.setEditable(false);
        aText.setEditable(false);
        eText.setEditable(false);

        // 将所有组件添加到container中
        container1.add(nLabel);
        container1.add(rLabel);
        container1.add(fpLabel);
        container1.add(fnLabel);
        container1.add(text1);
        container1.add(text2);
        container1.add(text3);
        container1.add(text4);
        container1.add(sufLabel);
        container1.add(fpButton);
        container1.add(createButton);
        container2.add(aLabel);
        container2.add(aText);
        container2.add(aButton);
        container2.add(eLabel);
        container2.add(eText);
        container2.add(eButton);
        container2.add(checkButton);

        // 设置窗口可见
        frame.setVisible(true);
        // 设置关闭窗口结束程序
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tabPane.add(container1);
        tabPane.add(container2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File filePathChecked;
        File aFile;
        File eFile;
        if (e.getSource().equals(createButton)) {
            String n = text1.getText();
            String r = text2.getText();
            String filepath = text3.getText();
            String fileName = text4.getText();
            errors = new ArrayList<>();
            // 如果参数检查不通过，显示错误信息
            if (!checkParams(n, r, filepath, fileName)) {
                showErrors();
            } else {
                // 如果参数检查通过，生成题目并写出到指定路径
                IProduceQuestionUtil produceQuestionUtil = new ProduceQuestionUtilImpl();
                List<Question> questions;
                IIOUtil ioUtil = new IOUtilImpl();
                questions = produceQuestionUtil.produce(Integer.parseInt(n), Integer.parseInt(r));
                try {
                    ioUtil.output(questions, filepath + "/" + fileName + ".txt");
                    JOptionPane.showMessageDialog(null, "题目文件" +
                                    fileName + ".txt已成功写出到\n" + filepath + "目录下",
                            "题目生成成功", JOptionPane.PLAIN_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (e.getSource().equals(fpButton)) {
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            // 打开文件选择器
            int state = fileChooser.showOpenDialog(null);
            // 如果关闭文件选择，直接返回；否则将选择的文件的文件名显示在面板上
            if (state != 1) {
                filePathChecked = fileChooser.getSelectedFile();
                text3.setText(filePathChecked.getAbsolutePath());
            }
        } else if (e.getSource().equals(aButton)) {
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int state = fileChooser.showOpenDialog(null);
            if (state != 1) {
                aFile = fileChooser.getSelectedFile();
                aText.setText(aFile.getAbsolutePath());
            }
        } else if (e.getSource().equals(eButton)) {
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int state = fileChooser.showOpenDialog(null);
            if (state != 1) {
                eFile = fileChooser.getSelectedFile();
                eText.setText(eFile.getAbsolutePath());
            }
        } else if (e.getSource().equals(checkButton)) {
            String aFilePath = aText.getText();
            String eFilePath = eText.getText();
            if (aFilePath.equals("") || eFilePath.equals("")) {
                JOptionPane.showMessageDialog(null, "未选择文件",
                        "警告", JOptionPane.WARNING_MESSAGE);
            } else {
                IIOUtil ioUtil = new IOUtilImpl();
                try {
                    ioUtil.checkAnswer(aFilePath, eFilePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "检查成功！已生成结果", "提示", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    private boolean checkParams(String n, String r, String filePath, String fileName) {
        if (!n.matches("[0-9]+")) {
            errors.add("参数-->生成题目的数量<--不合法\n");
        }
        if (!r.matches("[0-9]+")) {
            errors.add("参数-->题目的数值范围<--不合法\n");
        }
        if (fileName.contains(".")) {
            errors.add("参数-->题目文件文件名<--不合法\n");
        }
        if (new File(filePath + "/" + fileName).exists()) {
            errors.add("参数-->题目文件文件名<--文件已存在");
        }
        return errors.size() == 0;
    }

    private void showErrors() {
        JOptionPane.showMessageDialog(null, errors.toArray(),
                "输入的参数不合法", JOptionPane.WARNING_MESSAGE);
    }
}

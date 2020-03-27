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
import java.net.InterfaceAddress;
import java.util.ArrayList;
import java.util.List;

public class ArithmeticFrame implements ActionListener {

    JFrame frame = new JFrame("题目生成");
    JTabbedPane tabPane = new JTabbedPane();
    Container container = new Container();
    JPanel panel = new JPanel();
    JLabel nLabel = new JLabel("生成题目的数量：");
    JLabel rLabel = new JLabel("题目的数值范围：");
    JLabel fpLabel = new JLabel("题目的生成路径：");
    JLabel fnLabel = new JLabel("题目文件文件名：");
    JLabel sufLabel = new JLabel(".txt");
    // 输入生成题目的数量
    JTextField text1 = new JTextField();
    // 输入题目的数值范围
    JTextField text2 = new JTextField();
    // 显示选择的文件路径
    JTextField text3 = new JTextField();
    // 输入题目文件文件名
    JTextField text4 = new JTextField();
    // 文件选择器
    JFileChooser fileChooser = new JFileChooser();
    // 提交按钮
    JButton submitButton = new JButton("确定");
    // 文件路径选择路径
    JButton fpButton = new JButton("选择");
    // 存放输入错误信息
    List<String> errors = new ArrayList<>();

    {
        // 设置按钮格式
        submitButton.setBorder(BorderFactory.createRaisedBevelBorder());
        submitButton.setFocusPainted(false);
        fpButton.setBorder(BorderFactory.createRaisedBevelBorder());
        fpButton.setFocusPainted(false);
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
        frame.setSize(380, 280);
        // 设置布局
        frame.setContentPane(tabPane);

        nLabel.setBounds(30, 20, 120, 20);
        text1.setBounds(150, 20, 150, 20);
        rLabel.setBounds(30, 50, 120, 20);
        text2.setBounds(150, 50, 150, 20);
        fpLabel.setBounds(30, 80, 120, 20);
        text3.setBounds(150, 80, 150, 20);
        fpButton.setBounds(305, 80, 35, 20);
        fnLabel.setBounds(30, 110, 120, 20);
        text4.setBounds(150, 110, 130, 20);
        sufLabel.setBounds(280, 110, 20, 20);
        submitButton.setBounds(30, 140, 80, 20);

        // 为按钮添加事件
        fpButton.addActionListener(this);
        submitButton.addActionListener(this);

        // 设置文件选择路径不可输入
        text3.setEditable(false);

        // 将所有组件添加到container中
        container.add(nLabel);
        container.add(rLabel);
        container.add(fpLabel);
        container.add(fnLabel);
        container.add(text1);
        container.add(text2);
        container.add(text3);
        container.add(text4);
        container.add(sufLabel);
        container.add(fpButton);
        container.add(submitButton);

        // 设置窗口可见
        frame.setVisible(true);
        // 设置关闭窗口结束程序
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tabPane.add(container);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File filePathChecked = null;
        if (e.getSource().equals(submitButton)) {
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
                List<Question> questions = new ArrayList<>();
                IIOUtil ioUtil = new IOUtilImpl();
                for (int i = 1; i <= Integer.parseInt(n); i++) {
                    questions.add(produceQuestionUtil.produce(Integer.parseInt(r)));
                }
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

package com.smart.util.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.smart.entity.Question;
import com.smart.util.IIOUtil;

public class IOUtilImpl implements IIOUtil {

    public void output(List<Question> questions, String filePath) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            System.out.println("文件" + filePath + "已存在！请更改路径或文件名");
            return;
        }
        file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (Question question : questions) {
            writer.write(question.getInfixQuestion() + " = " + question.getAnswer() + "\n");
        }
        System.out.println("文件已经成功写出到" + filePath + "中");
        writer.close();
    }

    public void checkAnswer(String answerFilePath, String taskFilePath) throws IOException {
        File answerFile = new File(answerFilePath);
        File taskFile = new File(taskFilePath);
        if (!answerFile.exists() || !taskFile.exists()) {
            String s = "";
            s += answerFile.exists() ? "" : (" " + answerFilePath);
            s += taskFile.exists() ? "" : (" " + taskFilePath + " ");
            System.out.println("选择的文件" + s + "不存在！");
            return;
        }
        BufferedReader answerReader = new BufferedReader(new FileReader(answerFile));
        BufferedReader taskReader = new BufferedReader(new FileReader(taskFile));
        String answer, task;
        List<Integer> wrongQuestions = new ArrayList<>();
        List<Integer> rightQuestions = new ArrayList<>();
        int count = 0;
        while ((answer = answerReader.readLine()) != null &&
                (task = taskReader.readLine()) != null) {
            count++;
            answer = answer.replaceAll(" ", "");
            task = task.replaceAll(" ", "");
            String[] answerParams = answer.split("=");
            String[] taskParams = task.split("=");
            if (!answerParams[1].equals(taskParams[1])) {
                wrongQuestions.add(count);
            } else {
                rightQuestions.add(count);
            }
        }
        answerReader.close();
        taskReader.close();
        String answerFileName = answerFile.getName();
        String taskFileName = taskFile.getName();
        File gradeFile = new File("grades/" +
                answerFileName.substring(0, answerFileName.indexOf(".")) + "_"
                + taskFileName.substring(0, taskFileName.indexOf(".")) + "_"
                + "grade.txt");
        if (gradeFile.exists()) {
            gradeFile.delete();
        }
        gradeFile.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(gradeFile));
        writer.write("Correct:" + rightQuestions.size() + "\n( ");
        for (Integer i : rightQuestions) {
            writer.write(i + " ");
        }
        writer.write(")\n\n");
        writer.write("Wrong:" + wrongQuestions.size() + "\n( ");
        for (Integer i : wrongQuestions) {
            writer.write(i + " ");
        }
        writer.write(")");
        writer.close();
    }

}

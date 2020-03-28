package com.smart.util.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.smart.entity.Question;
import com.smart.util.IIOUtil;

public class IOUtilImpl implements IIOUtil {

    public void output(List<Question> questions, String filePath) throws IOException {
        File file = new File(filePath);
        boolean b = file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (Question question : questions) {
            writer.write(question.getInfixQuestion() + "=" + question.getAnswer() + "\n");
        }
        System.out.println("文件已经成功写出到" + filePath + "中");
        writer.close();
    }

    public void checkAnswer(String answerFilePath, String taskFilePath) {
        // TODO Auto-generated method stub

    }

}

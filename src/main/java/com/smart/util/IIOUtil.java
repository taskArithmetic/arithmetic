package com.smart.util;

import com.smart.entity.Question;

import java.util.List;

/**
 * 实现功能：①将生成的题目写到盘中
 *          ②读取两个文件，对比答案
 */
public interface IIOUtil {
    /**
     * 将问题输出到文件，默认输出到D�?
     * @param questions 生成的问�?
     * @param filePath 输出的文件的文件路径
     */
    void output(List<Question> questions,String filePath);

    /**
     * 对比答案，输出正确和错误的题目的数目
     * @param answerFilePath 答案文件的路�?
     * @param taskFilePath 作业文件的路�?
     */
    void checkAnswer(String answerFilePath,String taskFilePath);
}

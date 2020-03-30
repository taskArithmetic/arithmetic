package com.smart.entity;

import java.util.Arrays;

/**
 * 四则运算实现栈
 */
public class ArithmeticStack {
    String[] data;
    int maxsize;
    int top;

    public ArithmeticStack(int maxsize) {
        this.maxsize = maxsize;
        data = new String[maxsize];
        top = 0;
    }

    // 将数据压入栈中
    public void push(String s) {
        data[top++] = s;
    }

    // 弹出栈中的一个值
    public String pop() {
        if (top == 0) {
            return null;
        } else {
            return data[--top];
        }
    }

    @Override
    public String toString() {
        return "ArithmeticStack{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}

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
    public boolean push(String s) {
        if (top == maxsize) {
            return false;
        } else {
            data[top++] = s;
            return true;
        }
    }

    // 弹出栈中的一个值
    public String pop() {
        if (top == 0) {
            return null;
        } else {
            return data[--top];
        }
    }

    // 获得栈顶的值
    public String getTop() {
        if (top == 0) {
            return null;
        } else {
            return data[top - 1];
        }
    }

    @Override
    public String toString() {
        return "ArithmeticStack{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}

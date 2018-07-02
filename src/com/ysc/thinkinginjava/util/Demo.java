package com.ysc.thinkinginjava.util;

public class Demo implements Comparable<Demo> {
    private Integer num;
    public Demo() {
        num = CommonTool.random.nextInt(100);
    }
    public Demo(Integer num) {
        this.num = num;
    }

    @Override
    public int compareTo(Demo o) {
        return num - o.num;
    }

    @Override
    public String toString() {
        return "demo" + num;
    }
}

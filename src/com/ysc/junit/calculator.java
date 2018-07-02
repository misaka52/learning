package com.ysc.junit;

public class calculator {
    private static int result;

    public void add(int n) {
        result += n;
    }

    public void substract(int n) {
        result -= 1;
    }

    public void multiply(int n) {
        result *= n;
    }

    public void clear() {
        result = 0;
    }

    public int squre(int n) {
        return n * n;
    }

    public void squareRoot(int n) {
        for(; ;);
    }

    public int getResult() {
        return result;
    }

}

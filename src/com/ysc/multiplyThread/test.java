package com.ysc.multiplyThread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

class Student {
    public int id = 1;
}

public class test {
    private thread t;

    public void initalize() {
        t = new thread();
    }

    public static void main(String args[]) {
        Student s = new Student();
        System.out.println(s.id);
        solve(s);
        System.out.println(s.id);
    }

    private static void solve(Student s) {
        s.id = 2;
    }
}

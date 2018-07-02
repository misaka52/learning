package com.ysc.thinkinginjava;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Demo {
    public Demo() {}
    public Demo(int a) {
        this.a = a;
    }
    private int a = 1;
    private void f() {
        System.out.println("private void f()");
    }
    @Override
    public String toString() {
        return "a = " + a;
    }
}

public class Reflect {
    public static void main(String[] args) {
//        invokeMethod(new Demo(), "f");
//        try {
//            testNewInstance();
//        } catch ( IllegalAccessException e ) {
//            e.printStackTrace();
//        } catch ( InstantiationException e ) {
//            e.printStackTrace();
//        } catch ( NoSuchMethodException e ) {
//            e.printStackTrace();
//        } catch ( InvocationTargetException e ) {
//            e.printStackTrace();
//        }

        Demo demo = new Demo();
        try {
            Field field = demo.getClass().getDeclaredField("a");
            field.setAccessible(true);
            System.out.println(field.get(demo));
            field.set(demo, 2);
            System.out.println(demo);
        } catch ( NoSuchFieldException | IllegalAccessException e ) {
            e.printStackTrace();
        }
    }

    public static void testNewInstance() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Demo demo;
        //1
        demo = new Demo();
        invokeMethod(demo, "f");

        //2
        demo = Demo.class.newInstance();
        invokeMethod(demo, "f");

        //3
        Constructor con = Demo.class.getConstructor(int.class);
        demo = (Demo) con.newInstance(1);
        invokeMethod(demo, "f");
    }

    public static void invokeMethod(Object o, String methodName) {
        try {
            Method m = o.getClass().getDeclaredMethod(methodName);
            m.setAccessible(true);
            m.invoke(o);
        } catch ( NoSuchMethodException | IllegalAccessException | InvocationTargetException e ) {
            e.printStackTrace();
        }
    }
}

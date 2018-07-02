package com.ysc.multiplyThread;

public class thread extends Thread {

    private static class insideClass extends Thread {
        public insideClass(String a) {
            setName(a);
        }

        @Override
        public void run() {
            for(int i = 0; i < 10; ++i) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String args[]) {
        new insideClass("A").start();
        new insideClass("B").start();
        for(int i = 0; i < 10; ++i)
            System.out.println("main:" + i);
    }
}

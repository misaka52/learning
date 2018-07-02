package com.ysc.compile;

import java.util.ArrayList;
import java.util.List;

public class test3 {
    public static void main(String[] args) {
        List<Integer> lists = new ArrayList<>();
        for (int i = 0; i < 100000; ++i)
            lists.add(i);

        int t;
        long time1 = System.currentTimeMillis();
        for(int i = 0; i < 100000; ++i)
            t = lists.get(i);
        long time2 = System.currentTimeMillis();

        long time3 = System.currentTimeMillis();
        for (Integer i : lists)
//            System.out.println(i);
            t = i;
        long time4 = System.currentTimeMillis();

        System.out.println("下标遍历：" + (time2 - time1));
        System.out.println("foreach遍历：" + (time4 - time3));
    }
}

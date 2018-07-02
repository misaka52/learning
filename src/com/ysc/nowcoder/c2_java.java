package com.ysc.nowcoder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class c2_java {

    /**
     * 打印对象
     *
     * @param index
     * @param object
     */
    public static void print(int index, Object object) {
        System.out.println(String.format("{%d}, %s", index, object));
    }

    public static void demoList() {
        List<String> strList = new ArrayList<String>();
        for (int i = 0; i < 4; ++i)
            strList.add(String.valueOf(i));
        print(1, strList);
        for (int i = 0; i < 4; ++i)
            strList.add(String.valueOf(i * i));
        print(2, strList);
        Collections.sort(strList);
        print(3, strList);
        Collections.sort(strList, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        print(4, strList);
    }

    public static void demoException() {
        try {
            int a = 2 / 0;
            String str = null;
            str.charAt(1);
        } catch(NullPointerException e) {
            print(1, "npe");
        } catch(ArithmeticException e) {
            print(2, "ae");
        } finally {
            print(0, "finally");
        }
    }

    public static void demoCommon() {
        Date date = new Date();
        print(1, date);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        print(2, df.format(date));
    }

    public static void main(String[] args) {
        // demoList();
        // demoException();
        demoCommon();
        System.out.println("The End!");
    }
}

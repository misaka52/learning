package com.ysc.thinkinginjava.util;

import java.util.Map;
import java.util.Random;

public class CommonTool {
    public static Random random = new Random(47);

    public static void print(Map<?, ?> map) {
        for (Object o : map.keySet())
            System.out.println(o + ":" + map.get(o));
    }
}

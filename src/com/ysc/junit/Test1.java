package com.ysc.junit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class Test1 {
    private static calculator c = new calculator();
    private int param;
    private int result;

    public Test1(int param, int result) {
        this.param = param;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][] {
                {2, 4},
                {0, 0},
                {-3, 9}
        });
    }

    @Before
    public void init() {
//        System.out.println("The begin:");
        c.clear();
    }

    @After
    public void clear() {
//        System.out.println("The End");
    }

    @Test
    public void square() {
        c.squre(param);
        assertEquals(result, c.squre(param));
    }
}
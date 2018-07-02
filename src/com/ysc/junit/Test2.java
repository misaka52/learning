package com.ysc.junit;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Test2 {
    private static calculator c = new calculator();

    @Before
    public void init() {
        System.out.println("Test2");
        c.clear();
    }

    @Test
    public void add() {
        c.add(2);
        c.add(3);
        assertEquals(5, c.getResult());
    }

    @Test
    public void substract() {
        c.add(10);
        c.substract(5);
        assertEquals(5, c.getResult());
    }
}

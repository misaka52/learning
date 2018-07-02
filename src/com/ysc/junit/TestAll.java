package com.ysc.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
                calculator.class,
                Test1.class,
                Test2.class
        })
public class TestAll {
}

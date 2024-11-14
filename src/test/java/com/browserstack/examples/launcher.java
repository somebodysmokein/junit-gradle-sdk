package com.browserstack.examples;

import com.browserstack.examples.tests.e2e.PurchaseTest;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

public class launcher {

    public static void main(String... args)
    {
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        junit.run(PurchaseTest.class);
    }

}

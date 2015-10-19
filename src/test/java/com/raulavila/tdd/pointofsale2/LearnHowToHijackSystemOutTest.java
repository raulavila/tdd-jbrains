package com.raulavila.tdd.pointofsale2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class LearnHowToHijackSystemOutTest {

    private PrintStream productionSystemOut;

    @Before
    public void rememberSystemOut() throws Exception {
        productionSystemOut = System.out;
    }

    @After
    public void restoreSystemOut() throws Exception {
        System.setOut(productionSystemOut);
    }

    @Test
    public void singleLineOfText() throws Exception {
        ByteArrayOutputStream canvas = new ByteArrayOutputStream();
        System.setOut(new PrintStream(canvas));
        
        System.out.println("Hello World");

        assertEquals(
                Collections.singletonList("Hello World"),
                TextUtilities.lines(canvas.toString("UTF-8")));
    }

    @Test
    public void severalLinesOfText() throws Exception {
        ByteArrayOutputStream canvas = new ByteArrayOutputStream();
        System.setOut(new PrintStream(canvas));

        for (int i = 1; i <= 5; i++) {
            System.out.println("line"+i);
        }

        assertEquals(
                Arrays.asList("line1", "line2", "line3", "line4", "line5"),
                TextUtilities.lines(canvas.toString("UTF-8")));
    }

    @Test
    public void writeToConsole() throws Exception {
        System.out.println("You should see this on the console");
    }
}

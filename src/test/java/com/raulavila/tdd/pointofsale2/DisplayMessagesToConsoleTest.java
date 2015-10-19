package com.raulavila.tdd.pointofsale2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class DisplayMessagesToConsoleTest {

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
    public void productNotFoundMessage() throws Exception {
        ByteArrayOutputStream canvas = new ByteArrayOutputStream();
        System.setOut(new PrintStream(canvas));

        new EnglishLanguageConsoleDisplay().displayProductNotFoundMessage("9183748");
        
        assertEquals(
                Arrays.asList("Product not found for 9183748"),
                TextUtilities.lines(canvas.toString("UTF-8")));
    }

    @Test
    public void emptyBarcodeMessage() throws Exception {
        ByteArrayOutputStream canvas = new ByteArrayOutputStream();
        System.setOut(new PrintStream(canvas));

        new EnglishLanguageConsoleDisplay().displayEmptyBarcodeMessage();

        assertEquals(
                Arrays.asList("Scanning error: empty barcode"),
                TextUtilities.lines(canvas.toString("UTF-8")));
    }

    @Test
    public void multipleMessage() throws Exception {
        ByteArrayOutputStream canvas = new ByteArrayOutputStream();
        System.setOut(new PrintStream(canvas));

        EnglishLanguageConsoleDisplay englishLanguageConsoleDisplay = new EnglishLanguageConsoleDisplay();
        englishLanguageConsoleDisplay.displayProductNotFoundMessage("9183748");
        englishLanguageConsoleDisplay.displayEmptyBarcodeMessage();
        englishLanguageConsoleDisplay.displayProductNotFoundMessage("323");
        englishLanguageConsoleDisplay.displayEmptyBarcodeMessage();
        
        assertEquals(
                Arrays.asList(
                        "Product not found for 9183748",
                        "Scanning error: empty barcode",
                        "Product not found for 323",
                        "Scanning error: empty barcode"),
                TextUtilities.lines(canvas.toString("UTF-8")));
    }

}

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

        new EnglishLanguageTextDisplay(new ConsolePostOffice()).displayProductNotFoundMessage("9183748");
        
        assertEquals(
                Arrays.asList("Product not found for 9183748"),
                TextUtilities.lines(canvas.toString("UTF-8")));
    }

    @Test
    public void emptyBarcodeMessage() throws Exception {
        ByteArrayOutputStream canvas = new ByteArrayOutputStream();
        System.setOut(new PrintStream(canvas));

        new EnglishLanguageTextDisplay(new ConsolePostOffice()).displayEmptyBarcodeMessage();

        assertEquals(
                Arrays.asList("Scanning error: empty barcode"),
                TextUtilities.lines(canvas.toString("UTF-8")));
    }

    @Test
    public void multipleMessage() throws Exception {
        ByteArrayOutputStream canvas = new ByteArrayOutputStream();
        System.setOut(new PrintStream(canvas));

        EnglishLanguageTextDisplay englishLanguageTextDisplay = new EnglishLanguageTextDisplay(new ConsolePostOffice());
        englishLanguageTextDisplay.displayProductNotFoundMessage("9183748");
        englishLanguageTextDisplay.displayEmptyBarcodeMessage();
        englishLanguageTextDisplay.displayProductNotFoundMessage("323");
        englishLanguageTextDisplay.displayEmptyBarcodeMessage();
        
        assertEquals(
                Arrays.asList(
                        "Product not found for 9183748",
                        "Scanning error: empty barcode",
                        "Product not found for 323",
                        "Scanning error: empty barcode"),
                TextUtilities.lines(canvas.toString("UTF-8")));
    }

}

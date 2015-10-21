package com.raulavila.tdd.pointofsale2.ui;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.io.StringReader;

public class InterpretTextCommandsTest {

    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();
    
    @Test
    public void zero() throws Exception {
        BarcodeScannedListener barcodeScannedListener = 
                context.mock(BarcodeScannedListener.class);

        context.checking(new Expectations() {{
            never(barcodeScannedListener);

        }});

        new TextProcessorAndCommandInterpreter(barcodeScannedListener).process(new StringReader(""));
    }

    @Test
    public void oneBarcode() throws Exception {
        BarcodeScannedListener barcodeScannedListener =
                context.mock(BarcodeScannedListener.class);

        context.checking(new Expectations() {{
            oneOf(barcodeScannedListener).onBarcode("::barcode::");
        }});
        
        new TextProcessorAndCommandInterpreter(barcodeScannedListener).process(
                new StringReader("::barcode::\n"));

    }

    //SMELL: runs the entire interpreter, even though it only checks
    //that the lines are trimmed before they are interpreted
    @Test
    public void trimsBarcode() throws Exception {
        BarcodeScannedListener barcodeScannedListener =
                context.mock(BarcodeScannedListener.class);

        context.checking(new Expectations() {{
            oneOf(barcodeScannedListener).onBarcode("::barcode::");
        }});

        new TextProcessorAndCommandInterpreter(barcodeScannedListener).process(
                new StringReader("\t\t    \t::barcode::     \t \t\n"));

    }

    @Test
    public void severalBarcodes() throws Exception {
        BarcodeScannedListener barcodeScannedListener =
                context.mock(BarcodeScannedListener.class);

        context.checking(new Expectations() {{
            oneOf(barcodeScannedListener).onBarcode("::barcode 1::");
            oneOf(barcodeScannedListener).onBarcode("::barcode 2::");
            oneOf(barcodeScannedListener).onBarcode("::barcode 3::");
        }});

        new TextProcessorAndCommandInterpreter(barcodeScannedListener).process(
                new StringReader("::barcode 1::\n::barcode 2::\n::barcode 3::\n"));
    }

    //SMELL: this test runs the entire interpreter, when it only checks if we
    //filter out empty lines
    @Test
    public void severalBarcodesInterspersedWithEmptyLines() throws Exception {
        BarcodeScannedListener barcodeScannedListener =
                context.mock(BarcodeScannedListener.class);

        context.checking(new Expectations() {{
            oneOf(barcodeScannedListener).onBarcode("::barcode 1::");
            oneOf(barcodeScannedListener).onBarcode("::barcode 2::");
            oneOf(barcodeScannedListener).onBarcode("::barcode 3::");
        }});

        new TextProcessorAndCommandInterpreter(barcodeScannedListener).process(
                new StringReader("\n::barcode 1::\n\t\n::barcode 2::\n\n  \n::barcode 3::\n\n"));

    }

}

package com.raulavila.tdd.pointofsale;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ScanOneItemTest {

    private Display display;
    private Sale sale;

    @Before
    public void setUp() throws Exception {
        display = new Display();
        sale = new Sale(display, new Catalog(new HashMap<String, Integer>() {{
            put("12345", 795);
            put("23456", 1295);
        }}));
    }

    @Test
    public void productFound() throws Exception {
        sale.onBarCode("12345");
        assertEquals("$7.95", display.getText());
    }

    @Test
    public void anotherProductFound() throws Exception {
        sale.onBarCode("23456");
        assertEquals("$12.95", display.getText());
    }

    @Test
    public void productNotFound() throws Exception {
        sale.onBarCode("99999");
        assertEquals("Product not found for 99999", display.getText());
    }

    @Test
    public void emptyBarcode() throws Exception {
        Sale sale = new Sale(display, new Catalog(null));
        
        sale.onBarCode("");

        assertEquals("Scanning error: empty barcode", display.getText());
    }


}

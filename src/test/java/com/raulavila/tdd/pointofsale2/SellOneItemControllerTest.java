package com.raulavila.tdd.pointofsale2;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class SellOneItemControllerTest {

    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();
    
    private final Catalog catalog = context.mock(Catalog.class);
    private final SaleController saleController = new SaleController(catalog);

    @Test
    public void productFound() throws Exception {
        Price irrelevantPrice = Price.cents(795);
        
        context.checking(new Expectations() {{
            allowing(catalog).findPrice(with("::product found::"));
            will(returnValue(irrelevantPrice));
        }});

        SellOneItemView sellOneItemView = saleController.onBarcode("::product found::");

        assertEquals(
                new SellOneItemView(
                        "Scanned Product Price", 
                        Collections.singletonMap("price", irrelevantPrice)), sellOneItemView);
        
        //verify method expectations is done automatically by the @Rule
    }

    @Test
    public void productNotFound() throws Exception {
        //::product not found:: is a metaconstant (self documenting)
        context.checking(new Expectations(){{
            allowing(catalog).findPrice(with("::product not found::"));
            will(returnValue(null));
        }});

        SellOneItemView sellOneItemView = saleController.onBarcode("::product not found::");

        assertEquals(
                new SellOneItemView(
                        "Product Not Found",
                        Collections.singletonMap("barcode", "::product not found::")), sellOneItemView);
    }

}

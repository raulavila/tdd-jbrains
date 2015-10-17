package com.raulavila.tdd.pointofsale2;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class SellOneItemControllerTest {

    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();
    
    @Test
    public void productFound() throws Exception {
        Catalog catalog = context.mock(Catalog.class);
        Display display = context.mock(Display.class);
        Price irrelevantPrice = Price.cents(795);
        
        context.checking(new Expectations() {{
            allowing(catalog).findPrice(with("::product found::"));
            will(returnValue(irrelevantPrice));

            oneOf(display).displayPrice(with(irrelevantPrice));
        }});

        SaleController saleController = new SaleController(catalog, display);
        saleController.onBarcode("::product found::");

        //verify method expectations is done automatically by the @Rule
    }

    @Test
    public void productNotFound() throws Exception {
        Catalog catalog = context.mock(Catalog.class);
        Display display = context.mock(Display.class);
        
        //::product not found:: is a metaconstant (self documenting)
        context.checking(new Expectations(){{
            allowing(catalog).findPrice(with("::product not found::"));
            will(returnValue(null));
            
            oneOf(display).displayProductNotFoundMessage(with("::product not found::"));
        }});

        SaleController saleController = new SaleController(catalog, display);
        saleController.onBarcode("::product not found::");
    }

    @Test
    public void emptyBarcode() throws Exception {
        Display display = context.mock(Display.class);
        
        context.checking(new Expectations(){{
            oneOf(display).displayEmptyBarcodeMessage();
        }});

        SaleController saleController = new SaleController(null, display);
        saleController.onBarcode("");
    }

    public interface Display {
        void displayPrice(Price price);
        void displayProductNotFoundMessage(String barcodeNotFound);
        void displayEmptyBarcodeMessage();
    }

    public static class SaleController {
        private Display display;
        private Catalog catalog;

        public SaleController(Catalog catalog, Display display) {
            this.display = display;
            this.catalog = catalog;
        }

        public void onBarcode(String barcode) {
            //Smell: should I get an empty barcode at all
            if("".equals(barcode)) {
                display.displayEmptyBarcodeMessage();
                return;
            }
            
            Price price = catalog.findPrice(barcode);
            if(price == null)
                display.displayProductNotFoundMessage(barcode);
            else
                display.displayPrice(price);
        }
    }
}

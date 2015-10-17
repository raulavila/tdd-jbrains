package com.raulavila.tdd.pointofsale2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public abstract class FindPriceInCatalogContract {
    @Test
    public void productFound() throws Exception {
        Price foundPrice = Price.cents(1250);

        Catalog catalog = catalogWith("12345", foundPrice);

        assertEquals(
                foundPrice,
                catalog.findPrice("12345"));
    }

    protected abstract Catalog catalogWith(String barcode, Price price);

    @Test
    public void productNotFound() throws Exception {
        Catalog catalog = catalogWithout("12345");
        assertEquals(null, catalog.findPrice("12345"));
    }

    protected abstract Catalog catalogWithout(String barcodeToAvoid);
}

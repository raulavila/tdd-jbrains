package com.raulavila.tdd.pointofsale2;

import java.util.Map;

public class InMemoryCatalog implements Catalog {
    private Map<String, Price> pricesByBarcode;

    //Refactor: take a snapshot of the state of this map on the way in (avoid aliasing problem)
    public InMemoryCatalog(Map<String, Price> pricesByBarcode) {
        this.pricesByBarcode = pricesByBarcode;
    }

    @Override
    public Price findPrice(String barcode) {
        return pricesByBarcode.get(barcode);
    }
}

package com.raulavila.tdd.pointofsale;

import java.util.Map;

public class Catalog {
    private Map<String, Integer> pricesInCentsByBarcode;

    public Catalog(Map<String, Integer> pricesInCentsByBarcode) {
        this.pricesInCentsByBarcode = pricesInCentsByBarcode;
    }

    public Integer findPrice(String barcode) {
        return pricesInCentsByBarcode.get(barcode);
    }
}

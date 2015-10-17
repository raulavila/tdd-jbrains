package com.raulavila.tdd.pointofsale2;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FindPriceInMemoryCatalogTest extends FindPriceInCatalogContract {

    @Override
    protected Catalog catalogWith(String barcode, Price price) {
        return new InMemoryCatalog(new HashMap<String, Price>(){{
            put("definitely not " + barcode, Price.cents(0));
            put(barcode, price);
            put("once again, definitely not " + barcode, Price.cents(100000));
        }});
    }

    @Override
    protected Catalog catalogWithout(String barcodeToAvoid) {
        return new InMemoryCatalog(Collections.singletonMap("anything but " + barcodeToAvoid, Price.cents(0)));
    }

    private class InMemoryCatalog implements Catalog {
        private Map<String, Price> pricesByBarcode;

        public InMemoryCatalog(Map<String, Price> pricesByBarcode) {
            this.pricesByBarcode = pricesByBarcode;
        }

        @Override
        public Price findPrice(String barcode) {
            return pricesByBarcode.get(barcode);
        }
    }
}

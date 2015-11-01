package com.raulavila.tdd.pointofsale2;

import com.raulavila.tdd.pointofsale2.ui.BarcodeScannedListener;

import java.util.Collections;

public class SaleController implements BarcodeScannedListener {
    private Catalog catalog;

    public SaleController(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public SellOneItemView onBarcode(String barcode) {
        Price price = catalog.findPrice(barcode);
        if(price == null) {
            return new SellOneItemView(
                    "Product Not Found",
                    Collections.singletonMap("barcode", barcode));
        }
        else {
            return new SellOneItemView(
                    "Scanned Product Price",
                    Collections.singletonMap("price", price));
        }
    }

}

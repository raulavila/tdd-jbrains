package com.raulavila.tdd.pointofsale2;

import com.raulavila.tdd.pointofsale2.ui.BarcodeScannedListener;

public class SaleController implements BarcodeScannedListener {
    private Display display;
    private Catalog catalog;

    public SaleController(Catalog catalog, Display display) {
        this.display = display;
        this.catalog = catalog;
    }

    public void onBarcode(String barcode) {
        //Refactor: move this up the call stack
        if("".equals(barcode)) {
            display.displayEmptyBarcodeMessage();
            return;
        }

        Price price = catalog.findPrice(barcode);
        if(price == null)
            display.displayProductNotFoundMessage(barcode);
        else
            display.displayScannedProductPriceMessage(price);
    }
}

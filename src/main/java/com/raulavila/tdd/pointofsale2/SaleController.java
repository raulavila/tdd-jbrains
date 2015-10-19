package com.raulavila.tdd.pointofsale2;

public class SaleController {
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

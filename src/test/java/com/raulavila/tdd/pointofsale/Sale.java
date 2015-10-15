package com.raulavila.tdd.pointofsale;

import java.util.ArrayList;
import java.util.Collection;

public class Sale {

    private Display display;
    private Catalog catalog;
    private Collection<Integer> pendingPurchaseItemPrices = new ArrayList<>();

    public Sale(Display display, Catalog catalog) {
        this.display = display;
        this.catalog = catalog;
    }

    public void onBarCode(String barcode) {
        if ("".equals(barcode)) {
            display.displayEmptyBarcodeMessage();
            return;
        }

        Integer priceInCents = catalog.findPrice(barcode);
        if (priceInCents == null) {
            display.displayProductNotFoundMessage(barcode);
        } else {
            pendingPurchaseItemPrices.add(priceInCents);
            display.displayPrice(priceInCents);
        }
    }

    public void onTotal() {
        if (pendingPurchaseItemPrices.isEmpty()) {
            display.displayNoSaleInProgressMessage();
        } else {
            display.displayPurchaseTotal(pendingPurchaseTotal());
        }
    }

    private Integer pendingPurchaseTotal() {
        return computePurchaseTotal(pendingPurchaseItemPrices);
    }

    public static Integer computePurchaseTotal(Collection<Integer> purchaseItemPrices) {
        return purchaseItemPrices
                .stream()
                .reduce(0, Integer::sum);

    }

}

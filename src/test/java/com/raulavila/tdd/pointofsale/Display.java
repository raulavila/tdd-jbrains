package com.raulavila.tdd.pointofsale;

import java.util.Locale;

public class Display {

    private String text;

    public static String formatMonetaryAmount(int priceInCents) {
        //Java String format thousand separator
        return String.format(Locale.ENGLISH, "$%,.2f", priceInCents / 100d);
    }

    public String getText() {
        return text;
    }

    public void displayEmptyBarcodeMessage() {
        this.text = "Scanning error: empty barcode";
    }

    public void displayProductNotFoundMessage(String barcode) {
        this.text = String.format("Product not found for %s", barcode);
    }

    public void displayNoSaleInProgressMessage() {
        this.text = "No sale in progress. Try scanning a product";
    }

    public void displayPurchaseTotal(Integer purchaseTotal) {
        this.text = "Total: " + formatMonetaryAmount(purchaseTotal);
    }

    public void displayPrice(Integer priceInCents) {
        this.text = formatMonetaryAmount(priceInCents);
    }
}

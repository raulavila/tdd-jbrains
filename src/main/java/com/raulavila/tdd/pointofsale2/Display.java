package com.raulavila.tdd.pointofsale2;

public interface Display {
    void displayScannedProductPriceMessage(Price price);
    void displayProductNotFoundMessage(String barcodeNotFound);
    void displayEmptyBarcodeMessage();
}

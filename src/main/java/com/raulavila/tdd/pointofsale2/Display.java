package com.raulavila.tdd.pointofsale2;

public interface Display {
    void displayPrice(Price price);
    void displayProductNotFoundMessage(String barcodeNotFound);
    void displayEmptyBarcodeMessage();
}

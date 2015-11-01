package com.raulavila.tdd.pointofsale2;

import java.util.Locale;

//Smell: does the formatting but delegates the rendering...
// it should either do both or delegate both
class EnglishLanguageTextDisplay implements Display {

    public static final String PRODUCT_NOT_FOUND_MESSAGE_FORMAT = "Product not found for %s";
    public static final String SCANNING_ERROR_EMPTY_BARCODE_MESSAGE_FORMAT = "Scanning error: empty barcode";
    public static final String PRICE_IN_DOLLARS_MESSAGE_FORMAT = "$%,.2f";
    
    private final PostOffice postOffice;

    //Smell: leaky abstraction, the display doesn't care about sending messages
    //it seems to want to render templates
    public EnglishLanguageTextDisplay(PostOffice postOffice) {
        this.postOffice = postOffice;
    }

    @Override
    public void displayProductNotFoundMessage(String barcodeNotFound) {
        render(mergeTemplate(PRODUCT_NOT_FOUND_MESSAGE_FORMAT, barcodeNotFound));
    }

    @Override
    public void displayEmptyBarcodeMessage() {
        render(mergeTemplate(SCANNING_ERROR_EMPTY_BARCODE_MESSAGE_FORMAT));
    }

    @Override
    public void displayScannedProductPriceMessage(Price price) {
        render(mergeTemplate(PRICE_IN_DOLLARS_MESSAGE_FORMAT, price.dollarValue()));
    }

    private String mergeTemplate(String messageTemplate, Object... placeholderValues) {
        return String.format(Locale.ENGLISH, messageTemplate, placeholderValues);
    }

    private void render(String text) {
        postOffice.sendMessage(text);
    }

}

package com.raulavila.tdd.pointofsale2;

import com.raulavila.tdd.pointofsale2.ui.ViewRenderer;

import java.util.Locale;

//Smell: does the formatting but delegates the rendering...
// it should either do both or delegate both
public class EnglishLanguageTextViewRenderer implements ViewRenderer {

    public static final String PRODUCT_NOT_FOUND_MESSAGE_FORMAT = "Product not found for %s";
    public static final String SCANNING_ERROR_EMPTY_BARCODE_MESSAGE_FORMAT = "Scanning error: empty barcode";
    public static final String PRICE_IN_DOLLARS_MESSAGE_FORMAT = "$%,.2f";
    
    private final PostOffice canvas;

    //Smell: leaky abstraction, the display doesn't care about sending messages
    //it seems to want to render templates
    public EnglishLanguageTextViewRenderer(PostOffice canvas) {
        this.canvas = canvas;
    }

    //Refactor: tested but not invoked directly in production
    public void displayProductNotFoundMessage(String barcodeNotFound) {
        render(mergeTemplate(PRODUCT_NOT_FOUND_MESSAGE_FORMAT, barcodeNotFound));
    }

    //Refactor: tested but not invoked directly in production
    public void displayEmptyBarcodeMessage() {
        render(mergeTemplate(SCANNING_ERROR_EMPTY_BARCODE_MESSAGE_FORMAT));
    }

    //Refactor: tested but not invoked directly in production
    public void displayScannedProductPriceMessage(Price price) {
        render(mergeTemplate(PRICE_IN_DOLLARS_MESSAGE_FORMAT, price.dollarValue()));
    }

    private String mergeTemplate(String messageTemplate, Object... placeholderValues) {
        return String.format(Locale.ENGLISH, messageTemplate, placeholderValues);
    }

    private void render(String text) {
        canvas.sendMessage(text);
    }

    public void renderView(SellOneItemView sellOneItemView) {
        if("Product Not Found".equals(sellOneItemView.getViewName()))
            displayProductNotFoundMessage(
                    (String) sellOneItemView.getPlaceholderValues().get("barcode"));
        else if("Scanned Product Price".equals((sellOneItemView.getViewName())))
            displayScannedProductPriceMessage(
                    (Price) sellOneItemView.getPlaceholderValues().get("price"));
        else if ("Scanned Empty Barcode".equals((sellOneItemView.getViewName())))
            displayEmptyBarcodeMessage();
    }
}

package com.raulavila.tdd.pointofsale2.ui;

import com.raulavila.tdd.pointofsale2.*;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.util.Collections;

public class RenderSellOneItemViewsAsEnglishTextTest {

    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();
    
    private final PostOffice postOffice = context.mock(PostOffice.class);
    private final EnglishLanguageTextViewRenderer englishLanguageTextViewRenderer
            = new EnglishLanguageTextViewRenderer(postOffice);

    @Test
    public void scannedProductPriceView() throws Exception {
        context.checking(new Expectations() {{
            oneOf(postOffice).sendMessage("$7.95");
        }});

        englishLanguageTextViewRenderer.renderView(
                new SellOneItemView("Scanned Product Price",
                        Collections.singletonMap("price", Price.cents(795))));
    }

    @Test
    public void productNotFoundView() throws Exception {
        context.checking(new Expectations() {{
            oneOf(postOffice).sendMessage("Product not found for 123422");
        }});

        englishLanguageTextViewRenderer.renderView(
                new SellOneItemView("Product Not Found",
                        Collections.singletonMap("barcode", "123422")));

    }

    @Test
    public void emptyBarcodeView() throws Exception {
        context.checking(new Expectations() {{
            oneOf(postOffice).sendMessage("Scanning error: empty barcode");
        }});

        englishLanguageTextViewRenderer.renderView(
                new SellOneItemView("Scanned Empty Barcode",
                        Collections.emptyMap()));
    }
}

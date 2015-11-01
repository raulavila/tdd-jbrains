package com.raulavila.tdd.pointofsale2.ui;

import com.raulavila.tdd.pointofsale2.SellOneItemView;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;

public class RenderTheControllerResponseViewTest {

    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();

    @Test
    public void renderTheViewWithWhichTheControllerResponds() throws Exception {
        BarcodeScannedListener barcodeScannedListener = context.mock(BarcodeScannedListener.class);
        ViewRenderer viewRenderer = context.mock(ViewRenderer.class);

        SellOneItemView controllerResponseView = new SellOneItemView("irrelevant view name", Collections.emptyMap());

        context.checking(new Expectations() {{
            allowing(barcodeScannedListener).onBarcode(with(any(String.class)));
            will(returnValue(controllerResponseView));

            oneOf(viewRenderer).renderView(controllerResponseView);
        }});

        processScannedBarcodeCommandWith(barcodeScannedListener, viewRenderer);
    }

    private void processScannedBarcodeCommandWith(
            BarcodeScannedListener barcodeScannedListener, 
            ViewRenderer viewRenderer) throws IOException {
        
        new TextProcessorAndCommandInterpreter(barcodeScannedListener, viewRenderer).process(
                new StringReader("::valid barcode command::\n")
        );
    }

}



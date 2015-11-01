package com.raulavila.tdd.pointofsale2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class DisplayPricesToConsoleTest {

    private PrintStream productionSystemOut;

    @Before
    public void rememberSystemOut() throws Exception {
        productionSystemOut = System.out;
    }

    @After
    public void restoreSystemOut() throws Exception {
        System.setOut(productionSystemOut);
    }
    
    private final int priceInCents;
    private final String expectedFormattedPrice;

    public DisplayPricesToConsoleTest(int priceInCents, String expectedFormattedPrice) {
        this.priceInCents = priceInCents;
        this.expectedFormattedPrice = expectedFormattedPrice;
    }

    @Parameterized.Parameters(name = "Monetary amount {0} formats to ''{1}''")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {789, "$7.89"},
                {520, "$5.20"},
                {400, "$4.00"},
                {0, "$0.00"},
                {2, "$0.02"},
                {37, "$0.37"},
                {418976, "$4,189.76"},
                {210832281, "$2,108,322.81"}
        });
    }

    @Test
    public void test() throws Exception {
        ByteArrayOutputStream canvas = new ByteArrayOutputStream();
        System.setOut(new PrintStream(canvas));

        new EnglishLanguageTextDisplay(new ConsolePostOffice())
                .displayScannedProductPriceMessage(Price.cents(priceInCents));
        
        assertEquals(
                Arrays.asList(expectedFormattedPrice),
                TextUtilities.lines(canvas.toString("UTF-8")));
    }

}

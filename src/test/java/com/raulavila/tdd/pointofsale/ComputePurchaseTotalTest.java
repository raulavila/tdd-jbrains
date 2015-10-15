package com.raulavila.tdd.pointofsale;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ComputePurchaseTotalTest {

    @Test
    public void zeroItems() throws Exception {
        assertEquals(0, computePurchaseTotal(Collections.<Integer>emptyList()));
    }

    @Test
    public void oneItem() throws Exception {
        assertEquals(795, computePurchaseTotal(Collections.singletonList(795)));
    }

    @Test
    public void severalItems() throws Exception {
        assertEquals(2455, computePurchaseTotal(Arrays.asList(850, 1275, 330)));
    }

    private int computePurchaseTotal(List<Integer> purchaseItemPrices) {
        return Sale.computePurchaseTotal(purchaseItemPrices).intValue();
    }
}

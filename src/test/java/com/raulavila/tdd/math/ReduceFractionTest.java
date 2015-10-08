package com.raulavila.tdd.math;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReduceFractionTest {

    @Test
    public void alreadyInLowestTerms() throws Exception {
        assertEquals(new Fraction(3, 4), new Fraction(3, 4));
        //We've already tested this in FractionEqualsTest
    }

    @Test
    public void reduceToNotWholeNumber() throws Exception {
        assertEquals(new Fraction(3, 4), new Fraction(6, 8));
    }

    @Test
    public void reduceToWholeNumber() throws Exception {
        assertEquals(new Fraction(24, 4), new Fraction(6));
    }

    @Test
    public void reduceZero() throws Exception {
        assertEquals(new Fraction(0, 12), new Fraction(0));
    }
}

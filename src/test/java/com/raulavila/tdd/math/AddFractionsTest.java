package com.raulavila.tdd.math;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddFractionsTest {

    @Test
    public void zeroPlusZero() throws Exception {
        Fraction sum = new Fraction(0).plus(new Fraction(0));
        assertEquals(new Fraction(0), sum);
    }

    @Test
    public void nonZeroPlusZero() throws Exception {
        Fraction sum = new Fraction(3).plus(new Fraction(0));
        assertEquals(new Fraction(3), sum);
    }

    @Test
    public void zeroPlusNonZero() throws Exception {
        Fraction sum = new Fraction(0).plus(new Fraction(5));
        assertEquals(new Fraction(5), sum);
    }

    @Test
    public void nonNegativeNonZeroOperants() throws Exception {
        Fraction sum = new Fraction(3).plus(new Fraction(4));
        assertEquals(new Fraction(7), sum);
    }

    @Test
    public void negativeInputsAndNegativeOutput() throws Exception {
        Fraction sum = new Fraction(-3).plus(new Fraction(1));
        assertEquals(new Fraction(-2), sum);
    }

    @Test
    public void nonTrivialDenominatorButSameValue() throws Exception {
        Fraction sum = new Fraction(1, 5).plus(new Fraction(2, 5));
        assertEquals(new Fraction(3, 5), sum);
    }

    @Test
    public void differentDenominatorsWithoutReducing() throws Exception {
        assertEquals(new Fraction(5, 6),
                new Fraction(1, 2).plus(new Fraction(1, 3)));
    }

    @Test
    public void reduceResultToWholeNumber() throws Exception {
        assertEquals(new Fraction(1), new Fraction(1,3).plus(new Fraction(2,3)));
    }

    @Test
    public void oneDenominatorIsMultipleOfTheOther() throws Exception {
        assertEquals(new Fraction(11,8), new Fraction(3,4).plus(new Fraction(5,8)));
    }

    @Test
    public void commonFactorInDenominators() throws Exception {
        assertEquals(new Fraction(11,18), new Fraction(1,6).plus(new Fraction(4,9)));
    }

    @Test
    public void reduceResultEvenWhenDenominatorsAreTheSame() throws Exception {
        assertEquals(new Fraction(3,2), new Fraction(3,4).plus(new Fraction(3,4)));
    }

    @Test
    public void negativeFractionAndReducing() throws Exception {
        assertEquals(new Fraction(1,2), new Fraction(-1,4).plus(new Fraction(3,4)));
        assertEquals(new Fraction(-1,8), new Fraction(3,8).plus(new Fraction(-1,2)));
    }

    @Test
    public void negativeSignsEverywhere() throws Exception {
        assertEquals(new Fraction(1,2), new Fraction(1,-4).plus(new Fraction(-3,-4)));  
    }
}

package com.raulavila.tdd.math;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FractionEqualsTest {

    @Test
    public void fractionAndObject() throws Exception {
        assertNotEquals(new Fraction(3, 5), new Object());
    }

    @Test
    public void sameFractionWithoutDenominator() throws Exception {
        assertEquals(new Fraction(3), new Fraction(3));
    }

    @Test
    public void wholeNumberAndFraction() throws Exception {
        assertEquals(new Fraction(3), new Fraction(3, 1));
    }

    @Test
    public void differentFractionWithoutDenominator() throws Exception {
        assertNotEquals(new Fraction(3), new Fraction(5));
    }
    
    @Test
    public void sameNumeratorAndDenominator() throws Exception {
        assertEquals(new Fraction(3, 5), new Fraction(3, 5));
    }

    @Test
    public void differentNumeratorSameDenominator() throws Exception {
        assertNotEquals(new Fraction(2, 5), new Fraction(3, 5));
    }

    @Test
    public void sameNumeratorDifferentDenominator() throws Exception {
        assertNotEquals(new Fraction(2, 3), new Fraction(2, 5));
    }

    @Test
    public void differentNumeratorDifferentDenominator() throws Exception {
        assertNotEquals(new Fraction(2, 3), new Fraction(3, 2));
    }

    @Test
    public void negativeDenominator() throws Exception {
        assertEquals(new Fraction(1, 2), new Fraction(-1, -2));
        assertEquals(new Fraction(-1, 2), new Fraction(1, -2));
    }

}

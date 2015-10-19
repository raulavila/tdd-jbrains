package com.raulavila.tdd.pointofsale2;

public class Price {
    private final int centsValue;

    private Price(int centsValue) {
        this.centsValue = centsValue;
    }

    public static Price cents(int centsValue) {
        return new Price(centsValue);
    }

    public double dollarValue() {
        return centsValue / 100d;
    }

    @Override
    public String toString() {
        return "a Price";
    }
}

package com.raulavila.tdd.pointofsale2;

public class Price {
    public static Price cents(int centsValue) {
        return new Price();
    }

    @Override
    public String toString() {
        return "a Price";
    }
}

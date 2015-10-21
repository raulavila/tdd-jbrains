package com.raulavila.tdd.pointofsale2;

public class ConsolePostOffice implements PostOffice {
    @Override
    public void sendMessage(String text) {
        System.out.println(text);
    }
}

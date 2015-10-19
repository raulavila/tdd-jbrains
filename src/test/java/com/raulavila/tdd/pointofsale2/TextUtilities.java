package com.raulavila.tdd.pointofsale2;

import java.util.Arrays;
import java.util.List;

public class TextUtilities {
    //Refactor: move this to a resusable library,
    //or find a library that implements it more reliably
    static List<String> lines(String text) {
        return Arrays.asList(text.split("\\n"));
    }
}

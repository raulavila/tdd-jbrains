package com.raulavila.tdd.pointofsale2;

import com.raulavila.tdd.pointofsale2.ui.TextProcessorAndCommandInterpreter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;

public class VirtualPointOfSaleTerminal {

    public static void main(String[] args) throws IOException {
        TextProcessorAndCommandInterpreter textProcessorAndCommandInterpreter = new TextProcessorAndCommandInterpreter(
                new SaleController(
                        new InMemoryCatalog(
                                new HashMap<String, Price>() {{
                                    put("12345", Price.cents(795));
                                    put("23455", Price.cents(1250));
                                    put("092340077", Price.cents(379));
                                }}

                        ),
                        new EnglishLanguageDisplay(new ConsolePostOffice())
                )
        );
        
        textProcessorAndCommandInterpreter.process(
                new StringReader(
                        "12345\n23455\n9999\n\n"
                )
        );
        
        textProcessorAndCommandInterpreter.process(
                new InputStreamReader(System.in)
        );
    }
}

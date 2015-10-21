package com.raulavila.tdd.pointofsale2.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LearningCueCatTest {
    
    //This test shows us that the CueCat behaves just like a keyboard device
    //Run this program, then either scan barcodes into stdin or type on the 
    //keyboard, then press ENTER. This program will echo each line it reads to stdout
    
    //To test-drive this behaviour, it looks like we can simply simulate stdin
    
    public static void main(String[] args) throws IOException {
        try (BufferedReader stdinLineReader =
                     new BufferedReader(new InputStreamReader(System.in))) {

            stdinLineReader
                    .lines()
                    .forEach(line -> System.out.println(line));

        }
            
    }
}

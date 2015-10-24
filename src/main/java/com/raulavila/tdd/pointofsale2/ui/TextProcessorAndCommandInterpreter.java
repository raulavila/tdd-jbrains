package com.raulavila.tdd.pointofsale2.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.stream.Stream;

public class TextProcessorAndCommandInterpreter {
    private BarcodeScannedListener barcodeScannedListener;

    //Refactor: invert the dependency, so that BarcodeScannedListener
    //objects subscribe to this, rather than it knowing about the listeners directly
    public TextProcessorAndCommandInterpreter(BarcodeScannedListener barcodeScannedListener) {
        this.barcodeScannedListener = barcodeScannedListener;
    }

    public void process(Reader reader) throws IOException {
        try (BufferedReader commandReader = new BufferedReader(reader)) {
            processLines(commandReader.lines());
        }
    }

    private void processLines(Stream<String> lines) {
        interpretLines(sanitizeLines(lines));
    }

    private Stream<String> sanitizeLines(Stream<String> lines) {
        return lines.map(line -> line.trim());
    }

    private void interpretLines(Stream<String> sanitizedLines) {
        sanitizedLines
                .filter(line -> !line.isEmpty())
                .forEach(line -> interpretLine(line));
    }

    private void interpretLine(String line) {
        barcodeScannedListener.onBarcode(line);
    }
}

package com.raulavila.tdd.pointofsale2.ui;

import com.raulavila.tdd.pointofsale2.SellOneItemView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.stream.Stream;

public class TextProcessorAndCommandInterpreter {
    private final BarcodeScannedListener barcodeScannedListener;
    private final ViewRenderer viewRenderer;

    //Refactor: invert the dependency, so that BarcodeScannedListener
    //objects subscribe to this, rather than it knowing about the listeners directly
    public TextProcessorAndCommandInterpreter(BarcodeScannedListener barcodeScannedListener, ViewRenderer viewRenderer) {
        this.barcodeScannedListener = barcodeScannedListener;
        this.viewRenderer = viewRenderer;
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

    //Caution: framework emerging
    private void interpretLine(String line) {
        // Smell: Mixed levels of abstraction
        // maybe this is renderView(executeController(line))
        
        SellOneItemView sellOneItemView = barcodeScannedListener.onBarcode(line);
        viewRenderer.renderView(sellOneItemView);
    }
}

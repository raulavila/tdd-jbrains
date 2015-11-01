package com.raulavila.tdd.pointofsale2.ui;

import com.raulavila.tdd.pointofsale2.SellOneItemView;

public interface BarcodeScannedListener {
    
    // CONTRACT onBarcode() does not attempt to detect "" (empty String)
    // Barcodes of non-standard incorrect format will simple be treated
    // as products not found
    SellOneItemView onBarcode(String barcode);
}

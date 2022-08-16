package com.salestaxes.view;

import com.salestaxes.receipts.Receipt;

public interface View {
    void render(Receipt receipt);
}

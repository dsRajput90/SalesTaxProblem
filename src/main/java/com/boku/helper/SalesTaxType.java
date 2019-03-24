package com.boku.helper;

import java.math.BigDecimal;
import java.util.function.Function;

import com.boku.dto.Item;

/**
 * SalesTax enum which decides the percentage of tax alloted to the items
 * */
public enum SalesTaxType implements Function<Item, BigDecimal> {
    EXEMPTED (item -> !item.isExempted() ? new BigDecimal(10.0f) : new BigDecimal(0.0f)),
    IMPORTED (item -> item.isImported() ? new BigDecimal(5.0f) : new BigDecimal(0.0f));

    private final Function<Item, BigDecimal> func;

    private SalesTaxType(Function<Item, BigDecimal> func) {
        this.func = func;
    }

    boolean isExempted = false;

    @Override
    public BigDecimal apply(Item item) {
        return func.apply(item);
    }
}
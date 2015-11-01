package com.raulavila.tdd.pointofsale2;

import java.util.Map;

public class SellOneItemView {
    private final String viewName;
    private final Map<String, Object> placeholderValues;

    public SellOneItemView(String viewName, Map<String, Object> placeholderValues) {
        this.viewName = viewName;
        this.placeholderValues = placeholderValues;
    }

    public String getViewName() {
        return viewName;
    }

    public Map<String, Object> getPlaceholderValues() {
        return placeholderValues;
    }

    @Override
    public String toString() {
        return String.format(
                "a SaleView named %s with placeholder values %s",
                viewName,
                placeholderValues.toString());
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof SellOneItemView) {
            final SellOneItemView that = (SellOneItemView) other;
            return this.viewName.equals(that.viewName) &&
                    this.placeholderValues.equals(that.placeholderValues);

        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return -762;
    }
}

package com.github.paohaijiao.enums;

public enum JRoot {

    ROOT("$"),

    CURRENT("@");

    private final String symbol;

    JRoot(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public static JRoot fromString(String symbol) {
        for (JRoot root : values()) {
            if (root.symbol.equals(symbol)) {
                return root;
            }
        }
        throw new IllegalArgumentException("Unknown root symbol: " + symbol);
    }
}

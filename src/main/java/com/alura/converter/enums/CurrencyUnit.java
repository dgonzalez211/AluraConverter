package com.alura.converter.enums;

import java.util.Arrays;
import java.util.List;

public enum CurrencyUnit {
    COP("COP"),
    USD("USD"),
    MXN("MXN"),
    AUD("AUD"),
    BRL("BRL"),
    CAD("CAD"),
    CRC("CRC"),
    EUR("EUR"),
    JPY("JPY"),
    PEN("PEN"),
    VEF("VEF"),
    LBP("LBP");

    private final String unitName;

    CurrencyUnit(String stringValue) {
        this.unitName = stringValue;
    }

    public String getUnitName() {
        return unitName;
    }

    public static List<CurrencyUnit> getAllCurrencyUnits() {
        return Arrays.asList(CurrencyUnit.values());
    }

    @Override
    public String toString() {
        return unitName;
    }
}

package com.alura.converter.enums;

import java.util.Arrays;
import java.util.List;

public enum TemperatureUnit {

    CELSIUS("Celsius"),
    FAHRENHEIT("Fahrenheit"),
    DELISLE("Delisle"),
    KELVIN("Kelvin"),
    NEWTON("Newton"),
    RANKINE("Rankine"),
    REAUMUR("Reaumur"),
    ROMER("Romer");

    private final String unitName;

    TemperatureUnit(String stringValue) {
        this.unitName = stringValue;
    }

    public String getUnitName() {
        return unitName;
    }

    public static List<TemperatureUnit> getAllTemperatureUnits() {
        return Arrays.asList(TemperatureUnit.values());
    }

    @Override
    public String toString() {
        return unitName;
    }
}

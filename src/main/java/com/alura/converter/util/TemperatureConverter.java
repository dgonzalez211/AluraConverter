package com.alura.converter.util;

import com.alura.converter.enums.TemperatureUnit;

public final class TemperatureConverter {

    public static double convertTemperature(double value, TemperatureUnit fromUnit, TemperatureUnit toUnit) {
        if (fromUnit == toUnit) {
            return value;  // No conversion needed
        }

        double celsiusValue = convertToCelsius(value, fromUnit);
        return convertFromCelsius(celsiusValue, toUnit);
    }

    private static double convertToCelsius(double value, TemperatureUnit fromUnit) {
        switch (fromUnit) {
            case CELSIUS:
                return value;
            case FAHRENHEIT:
                return (value - 32) * 5 / 9;
            case DELISLE:
                return 100 - (value * 2 / 3);
            case KELVIN:
                return value - 273.15;
            case NEWTON:
                return value * 100 / 33;
            case RANKINE:
                return (value - 491.67) * 5 / 9;
            case REAUMUR:
                return value * 5 / 4;
            case ROMER:
                return (value - 7.5) * 40 / 21;
            default:
                throw new IllegalArgumentException("Invalid temperature unit: " + fromUnit.getUnitName());
        }
    }

    private static double convertFromCelsius(double celsiusValue, TemperatureUnit toUnit) {
        switch (toUnit) {
            case CELSIUS:
                return celsiusValue;
            case FAHRENHEIT:
                return celsiusValue * 9 / 5 + 32;
            case DELISLE:
                return (100 - celsiusValue) * 3 / 2;
            case KELVIN:
                return celsiusValue + 273.15;
            case NEWTON:
                return celsiusValue * 33 / 100;
            case RANKINE:
                return (celsiusValue + 273.15) * 9 / 5;
            case REAUMUR:
                return celsiusValue * 4 / 5;
            case ROMER:
                return celsiusValue * 21 / 40 + 7.5;
            default:
                throw new IllegalArgumentException("Invalid temperature unit: " + toUnit.getUnitName());
        }
    }
}

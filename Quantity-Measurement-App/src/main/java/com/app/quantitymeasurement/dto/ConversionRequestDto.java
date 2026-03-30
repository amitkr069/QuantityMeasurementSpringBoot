package com.app.quantitymeasurement.dto;

import com.app.quantitymeasurement.enums.*;
public class ConversionRequestDto {

    private double value;
    private  Unit fromUnit;
    private Unit  toUnit;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Unit getFromUnit() {
        return fromUnit;
    }

    public void setFromUnit(Unit fromUnit) {
        this.fromUnit = fromUnit;
    }

    public Unit getToUnit() {
        return toUnit;
    }

    public void setToUnit(Unit toUnit) {
        this.toUnit = toUnit;
    }
}

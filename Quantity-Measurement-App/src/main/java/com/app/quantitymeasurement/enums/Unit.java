package com.app.quantitymeasurement.enums;


public enum Unit {

    KM(UnitCategory.LENGTH),
    M(UnitCategory.LENGTH),
    CM(UnitCategory.LENGTH),

    TON(UnitCategory.WEIGHT),
    KG(UnitCategory.WEIGHT),
    G(UnitCategory.WEIGHT),

    C(UnitCategory.TEMPERATURE),
    F(UnitCategory.TEMPERATURE),
    K(UnitCategory.TEMPERATURE),

    L(UnitCategory.VOLUME),
    ML(UnitCategory.VOLUME),
    GAL(UnitCategory.VOLUME);

    private UnitCategory category;

    Unit(UnitCategory category) {
        this.category = category;
    }

    public UnitCategory getCategory() {
        return category;
    }
}
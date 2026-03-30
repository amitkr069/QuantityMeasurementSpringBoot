package com.app.quantitymeasurement.dto;



import com.app.quantitymeasurement.enums.ArithmeticOperation;
import com.app.quantitymeasurement.enums.Unit;

public class ArithmeticRequestDto {

    private double value1;
    private Unit unit1;

    private double value2;
    private Unit unit2;

    private ArithmeticOperation operation;

    // New: desired unit for the arithmetic result
    private Unit resultUnit;
    
	public double getValue1() {
		return value1;
	}

	public void setValue1(double value1) {
		this.value1 = value1;
	}

	public Unit getUnit1() {
		return unit1;
	}

	public void setUnit1(Unit unit1) {
		this.unit1 = unit1;
	}

	public double getValue2() {
		return value2;
	}

	public void setValue2(double value2) {
		this.value2 = value2;
	}

	public Unit getUnit2() {
		return unit2;
	}

	public void setUnit2(Unit unit2) {
		this.unit2 = unit2;
	}

	public ArithmeticOperation getOperation() {
		return operation;
	}

	public void setOperation(ArithmeticOperation operation) {
		this.operation = operation;
	}

    public Unit getResultUnit() {
        return resultUnit;
    }

    public void setResultUnit(Unit resultUnit) {
        this.resultUnit = resultUnit;
    }

    
}
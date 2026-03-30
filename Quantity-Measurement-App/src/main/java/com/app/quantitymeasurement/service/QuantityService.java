package com.app.quantitymeasurement.service;


import org.springframework.stereotype.Service;
import com.app.quantitymeasurement.dto.*;
import com.app.quantitymeasurement.enums.*;
import com.app.quantitymeasurement.exception.InvalidUnitException;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.quantitymeasurement.entity.CalculationHistory;
import com.app.quantitymeasurement.repository.CalculationHistoryRepository;

@Service
public class QuantityService {
	


    @Autowired
    private CalculationHistoryRepository repository;

	private double doConvert(double value, Unit from, Unit to) {
	    if (from.getCategory() != to.getCategory()) {
	        throw new InvalidUnitException("Units must belong to same category");
	    }

	    String key = from.name() + "_" + to.name();
	    double result;

	    if (from == to) {
	        result = value;
	    } else if (ConversionFactorService.factors.containsKey(key)) {
	        result = value * ConversionFactorService.factors.get(key);
	    } else if (from == Unit.C && to == Unit.F) result = (value * 9.0 / 5.0) + 32.0;
	    else if (from == Unit.F && to == Unit.C) result = (value - 32.0) * 5.0 / 9.0;
	    else if (from == Unit.C && to == Unit.K) result = value + 273.15;
	    else if (from == Unit.K && to == Unit.C) result = value - 273.15;
	    else if (from == Unit.F && to == Unit.K) result = ((value - 32.0) * 5.0 / 9.0) + 273.15;
	    else if (from == Unit.K && to == Unit.F) result = ((value - 273.15) * 9.0 / 5.0) + 32.0;
	    else throw new InvalidUnitException("Conversion not supported");

	    return result;
	}

	public double convert(ConversionRequestDto dto) {
	    double result = doConvert(dto.getValue(), dto.getFromUnit(), dto.getToUnit());
	    saveHistory("CONVERT", dto.getValue(), 0, result);
	    return result;
	}

	public double convert(double value, com.app.quantitymeasurement.enums.Unit from, com.app.quantitymeasurement.enums.Unit to) {
		return doConvert(value, from, to);
	}

    public String compare(ComparisonRequestDto dto) {

        double converted = convert(dto.getValue2(), dto.getUnit2(), dto.getUnit1());

        String result;
        if (dto.getValue1() > converted) result = "Value1 is greater";
        else if (dto.getValue1() < converted) result = "Value2 is greater";
        else result = "Equal";

        // persist comparison result as string
        saveHistory("COMPARE", dto.getValue1(), dto.getValue2(), result);

        return result;
    }

    public double arithmetic(ArithmeticRequestDto dto) {

        // Determine the desired unit for the final result; default to unit1 if not provided
        Unit desired = dto.getResultUnit();
        if (desired == null) {
            desired = dto.getUnit1();
        }

        // Convert both operands to the desired unit (use doConvert so no CONVERT rows are saved)
        double left = doConvert(dto.getValue1(), dto.getUnit1(), desired);
        double right = doConvert(dto.getValue2(), dto.getUnit2(), desired);

        double finalResult;
        switch (dto.getOperation()) {
            case ADD -> finalResult = left + right;
            case SUBTRACT -> finalResult = left - right;
            case MULTIPLY -> finalResult = left * right;
            case DIVIDE -> {
                if (right == 0) throw new RuntimeException("Division by zero");
                finalResult = left / right;
            }
            default -> throw new IllegalStateException("Unexpected value: " + dto.getOperation());
        }

        // save history with result string including unit
        String resultString = Double.toString(finalResult) + " " + desired.name();
        saveHistory(dto.getOperation().name(), dto.getValue1(), dto.getValue2(), resultString);
        return finalResult;
    }

    private void saveHistory(String operation, double input1, double input2, double result) {
        saveHistory(operation, input1, input2, Double.toString(result));
    }

    private void saveHistory(String operation, double input1, double input2, String result) {
        CalculationHistory history = new CalculationHistory();
        history.setOperation(operation);
        history.setInput1(input1);
        history.setInput2(input2);
        history.setResult(result);
        repository.save(history);
    }

}
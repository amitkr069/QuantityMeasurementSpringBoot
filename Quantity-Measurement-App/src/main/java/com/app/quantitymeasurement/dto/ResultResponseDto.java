package com.app.quantitymeasurement.dto;



public class ResultResponseDto {

    private String message;
    private double result;

    public ResultResponseDto(String message, double result) {
        this.message = message;
        this.result = result;
    }

    
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}
    
    // getters setters
}

package com.app.quantitymeasurement.controller;

//import java.util.logging.Logger;
import org.springframework.web.bind.annotation.*;
import com.app.quantitymeasurement.dto.*;
import com.app.quantitymeasurement.service.QuantityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import com.app.quantitymeasurement.entity.CalculationHistory;

@RestController
@RequestMapping("/quantity")
public class QuantityController {
	
	// Logger initialization
//    private static final Logger logger = Logger.getLogger(
//        QuantityController.class.getName()
//    );
//
//    // Getter method
//    public static Logger getLogger() {
//        return logger;
//    }
	
    private static final Logger logger = LoggerFactory.getLogger(QuantityController.class);


    private final QuantityService service;

    public QuantityController(QuantityService service) {
        this.service = service;
    }

    @PostMapping("/convert")
    public ResultResponseDto convert(@RequestBody ConversionRequestDto dto) {
    	logger.info("Convert API called - Input: value=" + dto.getValue() + 
                ", fromUnit=" + dto.getFromUnit() + ", toUnit=" + dto.getToUnit());
        double result = service.convert(dto);
        
        logger.info("Convert API response - Output: " + result);
        
        return new ResultResponseDto("Converted", result);
    }

    @PostMapping("/compare")
    public String compare(@RequestBody ComparisonRequestDto dto) {
//        return service.compare(dto);
    	
    	logger.info("Compare API called - Input: value1=" + dto.getValue1() + 
                ", unit1=" + dto.getUnit1() + ", value2=" + dto.getValue2() + 
                ", unit2=" + dto.getUnit2());

		String result = service.compare(dto);
		
		logger.info("Compare API response - Output: " + result);
		return result;
    }

    @PostMapping("/arithmetic")
    public ResultResponseDto arithmetic(@RequestBody ArithmeticRequestDto dto) {
//        double result = service.arithmetic(dto);
//        return new ResultResponseDto("Calculated", result);
    	
    	logger.info("Arithmetic API called - Input: operation=" + dto.getOperation() + 
                ", value1=" + dto.getValue1() + ", unit1=" + dto.getUnit1() + 
                ", value2=" + dto.getValue2() + ", unit2=" + dto.getUnit2() + 
                ", resultUnit=" + dto.getResultUnit());

		double result = service.arithmetic(dto);
		
		logger.info("Arithmetic API response - Output: " + result);
		return new ResultResponseDto("Calculated", result);
    }
    
    @GetMapping("/history")
    public List<CalculationHistory> getHistory() {
        logger.info("History API called - Fetching all records");
        return service.getHistory();
    }
    
    @GetMapping("/hello")

    public String hello() {
    	return "Hello";
    }
}

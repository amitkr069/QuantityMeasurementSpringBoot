package com.app.quantitymeasurement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.app.quantitymeasurement.dto.*;
import com.app.quantitymeasurement.entity.CalculationHistory;
import com.app.quantitymeasurement.enums.*;
import com.app.quantitymeasurement.exception.InvalidUnitException;
import com.app.quantitymeasurement.repository.CalculationHistoryRepository;
import com.app.quantitymeasurement.service.QuantityService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("QuantityService Tests with Mock Repository")
class QuantityServiceTestWithMockito {

   
    @Mock
    private CalculationHistoryRepository mockRepository;

    private QuantityService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        service = new QuantityService();
        ((QuantityService) service).setRepository(mockRepository);
    }

    // convert

    @Test
    @DisplayName("Test 1: Convert KM to M - Fake Repo Define Behavior")
    void testConvertKMToM() {
        System.out.println("\n TEST 1: Convert KM to M\n");

        when(mockRepository.save(any(CalculationHistory.class)))
                .thenReturn(new CalculationHistory());
        System.out.println(" Step 2: Mock repository behavior defined");

        ConversionRequestDto dto = new ConversionRequestDto();
        dto.setValue(5);
        dto.setFromUnit(Unit.KM);
        dto.setToUnit(Unit.M);

        double result = service.convert(dto);
        System.out.println("Step 3: Service called - Conversion done");

        assertEquals(5000, result, "5 KM should be 5000 M");
        System.out.println("Step 4: Result verified - " + result);

        verify(mockRepository, times(1)).save(any(CalculationHistory.class));
        System.out.println("Step 5: Repository.save() was called exactly 1 time\n");
    }

    

    // compare

    @Test
    @DisplayName("Test 2: Compare Values - Equal")
    void testCompareEqual() {
        System.out.println("\n TEST 2: Compare Values (Equal)\n");

        when(mockRepository.save(any(CalculationHistory.class)))
                .thenReturn(new CalculationHistory());

        ComparisonRequestDto dto = new ComparisonRequestDto();
        dto.setValue1(1);
        dto.setUnit1(Unit.KM);
        dto.setValue2(1000);
        dto.setUnit2(Unit.M);

        String result = service.compare(dto);

        assertEquals("Equal", result);
        verify(mockRepository, times(1)).save(any(CalculationHistory.class));
        System.out.println("1 KM == 1000 M - Result: " + result + "\n");
    }

    

    // ========== ARITHMETIC TESTS ==========

    @Test
    @DisplayName("Test 3: Addition Operation")
    void testAddition() {
        System.out.println("\n TEST 3: Addition (2 KM + 500 M)\n");

        when(mockRepository.save(any(CalculationHistory.class)))
                .thenReturn(new CalculationHistory());

        ArithmeticRequestDto dto = new ArithmeticRequestDto();
        dto.setValue1(2);
        dto.setUnit1(Unit.KM);
        dto.setValue2(500);
        dto.setUnit2(Unit.M);
        dto.setOperation(ArithmeticOperation.ADD);
        dto.setResultUnit(Unit.M);

        double result = service.arithmetic(dto);

        assertEquals(2500, result);
        verify(mockRepository, times(1)).save(any(CalculationHistory.class));
        System.out.println("2 KM + 500 M = " + result + " M\n");
    }

    
}
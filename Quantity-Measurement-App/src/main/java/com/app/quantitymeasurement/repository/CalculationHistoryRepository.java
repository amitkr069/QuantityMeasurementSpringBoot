package com.app.quantitymeasurement.repository;


import com.app.quantitymeasurement.entity.CalculationHistory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculationHistoryRepository extends JpaRepository<CalculationHistory, Long> {
}
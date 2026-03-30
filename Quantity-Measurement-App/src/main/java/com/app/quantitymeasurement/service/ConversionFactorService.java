package com.app.quantitymeasurement.service;


import java.util.HashMap;
import java.util.Map;

public class ConversionFactorService {

    public static final Map<String, Double> factors = new HashMap<>();

    static {

        // Length
        factors.put("KM_M", 1000.0);
        factors.put("KM_CM", 100000.0);
        factors.put("M_KM", 0.001);
        factors.put("M_CM", 100.0);
        factors.put("CM_M", 0.01);
        factors.put("CM_KM", 0.00001);

        // Weight
        factors.put("TON_KG", 1000.0);
        factors.put("TON_G", 1000000.0);
        factors.put("KG_TON", 0.001);
        factors.put("KG_G", 1000.0);
        factors.put("G_KG", 0.001);
        factors.put("G_TON", 0.000001);

        // Volume
        factors.put("L_ML", 1000.0);
        factors.put("L_GAL", 0.264172);
        factors.put("ML_L", 0.001);
        factors.put("ML_GAL", 0.000264172);
        factors.put("GAL_L", 3.78541);
        factors.put("GAL_ML", 3785.41);
    }
}

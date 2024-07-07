package com.juaracoding.drivers.utils;

public enum ScenarioTests {

    T1("Successful login with valid credentials"),
    T2("Failed login with invalid credentials"),
    T3("Add a product to the cart successfully"),
    T4("Successfully checkout a product"),
    T5("Failed firstname checkout a product"),
    T6("Failed lastname checkout a product"),
    T7("Failed zip checkout a product");



    // T1 ... T100

    private String scenarioTestName;

    ScenarioTests(String value){
        scenarioTestName = value;
    }

    public String getScenarioTestName() {
        return scenarioTestName;
    }

}
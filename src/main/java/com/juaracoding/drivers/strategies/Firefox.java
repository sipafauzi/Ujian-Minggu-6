package com.juaracoding.drivers.strategies;

import com.juaracoding.drivers.strategies.DriverStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Firefox implements DriverStrategy {

    @Override
    public WebDriver setStrategy() {
        String path = "D:\\Juara Coding SQA\\geckodriver.exe";
        System.setProperty("webdriver.gecko.driver", path);
        return new FirefoxDriver();
    }
}

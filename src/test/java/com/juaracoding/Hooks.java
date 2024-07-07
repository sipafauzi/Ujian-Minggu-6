package com.juaracoding;

import com.juaracoding.drivers.DriverSingleton;

import com.juaracoding.drivers.utils.ScenarioTests;
import com.juaracoding.drivers.utils.Utils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class Hooks {

    static WebDriver driver;

    static ExtentTest extentTest;
    static ExtentReports reports = new ExtentReports("target/extent-report.html");

    @Before
    public static void setUp(){
        DriverSingleton.getInstance("chrome");
        driver = DriverSingleton.getDriver();
        ScenarioTests[] test = ScenarioTests.values();
        extentTest = reports.startTest(test[Utils.testCount].getScenarioTestName());
        Utils.testCount++;
    }

    // if step == error/bug then screenshot
    @AfterStep
    public void getResultStatus(Scenario scenario) throws IOException {
        if(scenario.isFailed()){
            String screenshotPath = Utils.getScreenshot(driver, scenario.getName()
                    .replace(" ","_"));
            extentTest.log(LogStatus.FAIL,scenario.getName()+"\n"
                    +extentTest.addScreenCapture(screenshotPath));
        }
    }

    @After
    public void endScenarioTest(){
        reports.endTest(extentTest);
        reports.flush();
    }

    @AfterAll
    public static void finish(){
        DriverSingleton.delay(3);
        DriverSingleton.closeObjectInstance();
    }
}

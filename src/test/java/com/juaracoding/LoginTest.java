package com.juaracoding;


import com.juaracoding.drivers.pages.HomePage;
import com.juaracoding.drivers.pages.LoginPage;
import com.juaracoding.drivers.utils.Constant;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginTest {

    private static WebDriver driver;

    private ExtentTest extentTest;

    private static LoginPage loginPage = new LoginPage();

    private static HomePage homePage = new HomePage();

    public LoginTest(){
        driver = Hooks.driver;
        extentTest = Hooks.extentTest;
    }


    @Given("I am on the login page")
    public void i_am_on_the_login_page(){
        driver.get(Constant.URL);
        extentTest.log(LogStatus.PASS,"I am on the login page");
    }

    @When("I enter a valid username and password")
    public void i_enter_a_valid_username_and_password(){
        loginPage.usernamePassword("standard_user", "secret_sauce");
        System.out.println("I enter a valid username and password");
        extentTest.log(LogStatus.PASS,"I enter a valid username and password");

    }

    @And("I click the login button")
    public void i_click_the_login_button(){
        loginPage.loginButtonClick();
        extentTest.log(LogStatus.PASS,"I click the login button");
    }

    @Then("I should be redirected to the inventory page")
    public void i_should_be_redirected_to_the_inventory_page(){
        Assert.assertEquals(homePage.getTxtLogo(),"Swag Labs");
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
        extentTest.log(LogStatus.PASS,"I should be redirected to the inventory page");

    }

    @When("I enter an invalid username and password")
    public void i_enter_an_invalid_username_and_password(){
        loginPage.usernamePassword("Fauzi","101100");
        extentTest.log(LogStatus.PASS,"I enter an invalid username and password");
    }

    @Then("I should see an error message")
    public void i_should_see_an_error_message(){
        Assert.assertTrue(loginPage.getTxtError().contains("Username and password do not match"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.getAttributeLoginLogo(), "login_logo");
        extentTest.log(LogStatus.PASS,"I should see an error message");
    }
}

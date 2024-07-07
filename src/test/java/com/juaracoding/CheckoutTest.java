package com.juaracoding;


import com.juaracoding.drivers.pages.CartPage;
import com.juaracoding.drivers.pages.CheckoutPage;
import com.juaracoding.drivers.pages.HomePage;
import com.juaracoding.drivers.pages.LoginPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CheckoutTest {

    private ExtentTest extentTest;
    private WebDriver driver;
    private LoginPage loginPage = new LoginPage();
    private HomePage homePage = new HomePage();
    private CartPage cartPage = new CartPage();
    private CheckoutPage checkoutPage = new CheckoutPage();

    public CheckoutTest(){
        driver = Hooks.driver;
        extentTest = Hooks.extentTest;
    }

    @Given("I logged in to the application")
    public void i_logged_in_to_the_application(){
        loginPage.login("standard_user", "secret_sauce");
        extentTest.log(LogStatus.PASS,"I logged in to the application");
    }
    @Given("I click back home")
    public void i_click_back_home(){
        checkoutPage.clickBackHomeButton();
    }

    @When("I add new product to the cart")
    public void i_add_new_product_to_the_cart(){
        homePage.clickAddButton();
        homePage.clickCartButton();
        extentTest.log(LogStatus.PASS,"I add new product to the cart");
    }

    @And("I proceed to checkout")
    public void i_proceed_to_checkout(){
        cartPage.clickCheckoutButton();
        extentTest.log(LogStatus.PASS,"I proceed to checkout");
    }


    @And("I provide valid checkout information")
    public void i_provide_valid_checkout_information(){
        checkoutPage.isiDataCheckout("sipa", "fauzi", "43292");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 300)");
        checkoutPage.clickContinueButton();
        extentTest.log(LogStatus.PASS,"I provide valid checkout information");
    }
    @And("I provide invalid firstname checkout information")
    public void i_provide_invalid_firstname_checkout_information(){
        checkoutPage.isiDataCheckout("", "fauzi", "43292");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 300)");
        checkoutPage.clickContinueButton();
        extentTest.log(LogStatus.PASS,"I provide invalid firstname checkout information");
    }
    @And("I provide invalid lastname checkout information")
    public void i_provide_invalid_lastname_checkout_information(){
        checkoutPage.clearAllData();
        checkoutPage.isiDataCheckout("Sipa", "", "43292");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 300)");
        checkoutPage.clickContinueButton();
        extentTest.log(LogStatus.PASS,"I provide invalid lastname checkout information");
    }
    @And("I provide invalid postalcode checkout information")
    public void i_provide_invalid_zip_checkout_information(){
        checkoutPage.clearAllData();
        checkoutPage.isiDataCheckout("Sipa", "fauzi", "");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 300)");
        checkoutPage.clickContinueButton();
        extentTest.log(LogStatus.PASS,"I provide invalid zip checkout information");
    }

    @And("I finish the checkout process")
    public void i_finish_the_checkout_process(){
        checkoutPage.clickFinishButton();
        extentTest.log(LogStatus.PASS,"I finish the checkout process");
    }

    @Then("I should see the order confirmation page")
    public void i_should_see_the_order_confirmation_page(){
        Assert.assertEquals(checkoutPage.getTxtCheckoutComplete(),"Checkout: Complete!");
        extentTest.log(LogStatus.PASS,"I should see the order confirmation page");
    }
    @Then("I should see an error message firstname")
    public void i_should_see_an_error_message_firstname(){
        Assert.assertEquals(checkoutPage.getErrorMessage(),"Error: First Name is required");
        extentTest.log(LogStatus.PASS,"I should see an error message firstname");
    }
    @Then("I should see an error message lastname")
    public void i_should_see_an_error_message_lastname(){
        Assert.assertEquals(checkoutPage.getErrorMessage(),"Error: Last Name is required");
        extentTest.log(LogStatus.PASS,"I should see an error message lastname");
    }
    @Then("I should see an error message zip")
    public void i_should_see_an_error_message_zip(){
        Assert.assertEquals(checkoutPage.getErrorMessage(),"Error: Postal Code is required");
        extentTest.log(LogStatus.PASS,"I should see an error message zip");
    }



}

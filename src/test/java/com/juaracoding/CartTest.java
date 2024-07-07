package com.juaracoding;



import com.juaracoding.drivers.pages.CartPage;
import com.juaracoding.drivers.pages.HomePage;
import com.juaracoding.drivers.pages.LoginPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CartTest {

    private ExtentTest extentTest;
    private WebDriver driver;
    private LoginPage loginPage = new LoginPage();
    private HomePage homePage = new HomePage();
    private CartPage cartPage = new CartPage();

    public CartTest(){
        driver = Hooks.driver;
        extentTest = Hooks.extentTest;
    }

    @Given("I am logged in to the application")
    public void i_am_logged_in_to_the_application(){
        loginPage.clearUsernamePassword();
        loginPage.login("standard_user", "secret_sauce");
        extentTest.log(LogStatus.PASS,"I am logged in to the application");
    }

    @When("I add a product to the cart")
    public void i_add_a_product_to_the_cart(){
        homePage.clickAddButton();
        extentTest.log(LogStatus.PASS,"I am logged in to the application");
    }

    @Then("I should see total product on icon cart")
    public void i_should_see_total_product_on_icon_cart(){
        Assert.assertEquals(homePage.getTxtCart(), "1");
        extentTest.log(LogStatus.PASS,"I am logged in to the application");
    }

    @And("button Add to Cart changed to Remove")
    public void button_add_to_cart_changed_to_remove(){
        Assert.assertEquals(homePage.getTxtRemove(), "Remove");
        extentTest.log(LogStatus.PASS,"button Add to Cart changed to Remove");
    }

    @And("I am on the cart page")
    public void i_am_on_the_cart_page(){
        homePage.clickCartButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
        extentTest.log(LogStatus.PASS,"I am on the cart page");
    }

    @And("the product should be in the cart")
    public void the_product_should_be_in_the_cart(){
        Assert.assertEquals(cartPage.getItemName(), "Sauce Labs Backpack");
        cartPage.clickBackButton();
        homePage.clickRemoveButton();
        homePage.logout();
        extentTest.log(LogStatus.PASS,"the product should be in the cart");
    }

}

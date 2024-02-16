package bitconsulting.StepDefinitions;

import bitconsulting.PageObjects.*;
import bitconsulting.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefImplemetation extends BaseTest {
    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public ConfirmationPage confirmationPage;

    @Given("I landed on ecommerce  page")
    public void I_Landed_On_Ecommerce_Page() throws IOException {
        landingPage= super.launchApp();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void Logged_In_With_Username_And_Password(String usr, String psw){
        productCatalogue= landingPage.loginApp(usr, psw);
    }

    @When("^i add product (.+) to cart$")
    public void I_Add_Product_To_Cart(String prod) throws InterruptedException {
        System.out.println("submit order running");
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(prod);
    }
    @When("^checkOut (.+) and submit the order$")
    public void checkOut_And_Submit_The_Order(String prod)  {
        CartPage cartPage = productCatalogue.goToCartPage();

        Assert.assertTrue(cartPage.verifyProductAdded(prod));
        CheckoutPage checkoutPage= cartPage.goToCheckoutPage();
        checkoutPage.selectCountry("India");
        confirmationPage= checkoutPage.goToConfirmationPage();

    }
    @Then("{string} is displayed on confirmation page")
    public void message_Confirmation_Is_Displayed_On_Confirmation_Page(String string){
        String confirmMessage = confirmationPage.getConfirmationText();
        Assert.assertTrue( confirmMessage.equalsIgnoreCase(string));
        driver.close();
    }

    @Then("{string} message is displayed on login page")
    public void Error_message_Is_Displayed_On_Login_Page(String string){
        Assert.assertEquals(string, landingPage.getErrorLoginMsg());
        driver.close();
    }
}

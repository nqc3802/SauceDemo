package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import base.BaseTest;
import pages.CartPage;
import pages.CheckoutCompletePage;
import pages.CheckoutStepOnePage;
import pages.CheckoutStepTwoPage;
import pages.ProductPage;
import utils.ConfigReader;

public class CheckoutTest extends BaseTest {
	@Test
	@DisplayName("Test đặt hàng thành công")
	public void checkoutSuccessfulTest() {
		ProductPage productPage = new ProductPage(driver);
		CartPage cartPage = new CartPage(driver);
		CheckoutStepOnePage checkoutStepOnePage = new CheckoutStepOnePage(driver);
		CheckoutStepTwoPage checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
		CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);

		performLogin();
		productPage.waitForInventoryItems(5);
		performAddToCart();
		productPage.openCart();
		cartPage.waitForCartItems(5);
		cartPage.clickCheckout();
		checkoutStepOnePage.waitForCheckoutForm(5);
		checkoutStepOnePage.enterCheckoutInformation(ConfigReader.get("first_name"), ConfigReader.get("last_name"),
				ConfigReader.get("zip"));
		checkoutStepOnePage.clickContinue();
		checkoutStepTwoPage.waitForCheckoutInformation(5);
		Assertions.assertEquals(checkoutStepTwoPage.itemTotal(), checkoutStepTwoPage.getItemTotal(),
				"Item total does not match");
		Assertions.assertEquals(checkoutStepTwoPage.itemTotal() + checkoutStepTwoPage.getTax(),
				checkoutStepTwoPage.getTotal(), "Total does not match expected value");
		checkoutStepTwoPage.clickFinishButton();
		Assertions.assertEquals("Thank you for your order!", checkoutCompletePage.getCompleteHeader(),
				"Complete header does not match expected value");
		Assertions.assertEquals(
				"Your order has been dispatched, and will arrive just as fast as the pony can get there!",
				checkoutCompletePage.getCompleteText(), "Complete text does not match expected value");
		checkoutCompletePage.clickBackHomeButton();
	}

	@Test
	@DisplayName("Test đặt hàng không thành công")
	public void checkoutUnsuccessfulTest() {
		ProductPage productPage = new ProductPage(driver);
		CartPage cartPage = new CartPage(driver);

		performLogin();
		productPage.waitForInventoryItems(5);
		productPage.openCart();
		cartPage.waitForCartItems(5);
		cartPage.clickCheckout();
		String errorMessage = cartPage.getErrorMessage();
		Assertions.assertEquals("There are no products in the cart, checkout cannot be performed!", errorMessage,
				"Error message does not match expected value");
	}

	@Test
	@DisplayName("Test đặt hàng với thông tin chứa ký tự đặc biệt")
	public void checkoutWithSpecialCharacterTest() {
		ProductPage productPage = new ProductPage(driver);
		CartPage cartPage = new CartPage(driver);
		CheckoutStepOnePage checkoutStepOnePage = new CheckoutStepOnePage(driver);

		performLogin();
		productPage.waitForInventoryItems(5);
		productPage.addOnesieToCart();
		productPage.openCart();
		cartPage.waitForCartItems(5);
		cartPage.clickCheckout();
		checkoutStepOnePage.waitForCheckoutForm(5);
		checkoutStepOnePage.enterCheckoutInformation(ConfigReader.get("special_character_first_name"),
				ConfigReader.get("special_character_last_name"), ConfigReader.get("zip"));
		checkoutStepOnePage.clickContinue();

		String errorMessage = checkoutStepOnePage.getErrorMessage();
		Assertions.assertEquals("First and last name fields cannot contain numbers and special characters!",
				errorMessage, "Error message does not match expected value");
	}
	
	@Test
	@DisplayName("Test đặt hàng với thông tin trống")
	public void checkoutWithEmptyInformationTest() {
		ProductPage productPage = new ProductPage(driver);
		CartPage cartPage = new CartPage(driver);
		CheckoutStepOnePage checkoutStepOnePage = new CheckoutStepOnePage(driver);

		performLogin();
		productPage.waitForInventoryItems(5);
		productPage.addBoltTShirtToCart();
		productPage.openCart();
		cartPage.waitForCartItems(5);
		cartPage.clickCheckout();
		checkoutStepOnePage.waitForCheckoutForm(5);
		checkoutStepOnePage.enterCheckoutInformation("", ConfigReader.get("last_name"), "");
		checkoutStepOnePage.clickContinue();

		String errorMessage = checkoutStepOnePage.getErrorMessage();
		Assertions.assertEquals("Error: First Name is required", errorMessage, "Error message does not match expected value");
	}

}

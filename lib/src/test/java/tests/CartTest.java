package tests;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import base.BaseTest;
import pages.CartPage;
import pages.ProductDetailPage;
import pages.ProductPage;

public class CartTest extends BaseTest {

	@Test
	@DisplayName("Test thêm vào giỏ")
	public void addToCartTest() {
		performLogin();
		ProductPage productPage = new ProductPage(driver);
		CartPage cartPage = new CartPage(driver);

		productPage.addBackpackToCart();
		productPage.addBikeLightToCart();
		productPage.openCart();
		List<String> cartItems = cartPage.getCartItemNames();
		Assertions.assertTrue(cartItems.contains("Sauce Labs Backpack"), "There is no Sauce Labs Backpack in the cart");
		Assertions.assertTrue(cartItems.contains("Sauce Labs Bike Light"),
				"There is no Sauce Labs Bike Light in the cart");
		Assertions.assertEquals(2, cartItems.size(), "Number of items in cart is not correct");
		cartPage.removeAllItems();
	}

	@Test
	@DisplayName("Test xoá khỏi giỏ tại giỏ hàng")
	public void removeFromCartTest() {
		performLogin();
		ProductPage productPage = new ProductPage(driver);
		CartPage cartPage = new CartPage(driver);

		productPage.addBackpackToCart();
		productPage.addBoltTShirtToCart();
		productPage.openCart();

		List<String> cartItemsBeforeRemove = cartPage.getCartItemNames();
		Assertions.assertTrue(cartItemsBeforeRemove.contains("Sauce Labs Backpack"),
				"There is no Sauce Labs Backpack in the cart");
		Assertions.assertTrue(cartItemsBeforeRemove.contains("Sauce Labs Bolt T-Shirt"),
				"There is no Sauce Labs Bike Light in the cart");
		Assertions.assertEquals(2, cartItemsBeforeRemove.size(),
				"Number of items in cart before remove is not correct");

		cartPage.removeItemFromCart();

		List<String> cartItemsAfterRemove = cartPage.getCartItemNames();
		Assertions.assertFalse(cartItemsAfterRemove.contains("Sauce Labs Bolt T-Shirt"),
				"Sauce Labs Backpack was not removed from the cart");
		Assertions.assertEquals(1, cartItemsAfterRemove.size(), "Number of items in cart after remove is not correct");
		cartPage.removeAllItems();
	}

	@Test
	@DisplayName("Test xoá khỏi giỏ tại trang sản phẩm")
	public void removeFromProductPageTest() {
		performLogin();
		ProductPage productPage = new ProductPage(driver);
		CartPage cartPage = new CartPage(driver);

		productPage.addBackpackToCart();
		productPage.addBikeLightToCart();
		productPage.openCart();

		List<String> cartItemsBeforeRemove = cartPage.getCartItemNames();
		Assertions.assertTrue(cartItemsBeforeRemove.contains("Sauce Labs Backpack"),
				"There is no Sauce Labs Backpack in the cart");
		Assertions.assertTrue(cartItemsBeforeRemove.contains("Sauce Labs Bike Light"),
				"There is no Sauce Labs Bike Light in the cart");
		Assertions.assertEquals(2, cartItemsBeforeRemove.size(),
				"Number of items in cart before remove is not correct");
		cartPage.clickContinueShopping();
		productPage.removeBikeLightFromCart();
		productPage.openCart();

		List<String> cartItemsAfterRemove = cartPage.getCartItemNames();
		Assertions.assertFalse(cartItemsAfterRemove.contains("Sauce Labs Bike Light"),
				"Sauce Labs Backpack was not removed from the cart");
		Assertions.assertEquals(1, cartItemsAfterRemove.size(), "Number of items in cart after remove is not correct");
		cartPage.removeAllItems();
	}
	
	@Test
	@DisplayName("Test xoá khỏi giỏ tại trang chi tiết sản phẩm")
	public void removeFromProductDetailPageTest() {
		performLogin();
		ProductPage productPage = new ProductPage(driver);
		CartPage cartPage = new CartPage(driver);
		ProductDetailPage productDetailPage = new ProductDetailPage(driver);

		productPage.addBackpackToCart();
		productPage.addBikeLightToCart();
		productPage.openCart();

		List<String> cartItemsBeforeRemove = cartPage.getCartItemNames();
		Assertions.assertTrue(cartItemsBeforeRemove.contains("Sauce Labs Backpack"),
				"There is no Sauce Labs Backpack in the cart");
		Assertions.assertTrue(cartItemsBeforeRemove.contains("Sauce Labs Bike Light"),
				"There is no Sauce Labs Bike Light in the cart");
		Assertions.assertEquals(2, cartItemsBeforeRemove.size(),
				"Number of items in cart before remove is not correct");

		cartPage.clickOnProduct("Sauce Labs Bike Light");
		productDetailPage.removeFromCart();

		productDetailPage.openCart();

		List<String> cartItemsAfterRemove = cartPage.getCartItemNames();
		Assertions.assertTrue(cartItemsAfterRemove.contains("Sauce Labs Backpack"),
				"Sauce Labs Bike Light was not removed from the cart");
		Assertions.assertEquals(1, cartItemsAfterRemove.size(), "Number of items in cart after remove is not correct");

		cartPage.removeAllItems();
	}
}

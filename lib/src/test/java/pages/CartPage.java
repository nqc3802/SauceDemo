package pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	private WebDriver driver;

	private By cartItems = By.className("cart_item");
	private By itemName = By.className("inventory_item_name");
	private By itemPrice = By.className("inventory_item_price");
	private By removeBtn1 = By.id("remove-sauce-labs-bolt-t-shirt");
	private By checkoutButton = By.id("checkout");
	private By continueShoppingButton = By.id("continue-shopping");

	public CartPage(WebDriver driver) {
		this.driver = driver;
	}

	public List<CartItem> getCartItems() {
		waitForCartItems(5);
		List<WebElement> items = driver.findElements(cartItems);
		return items.stream()
				.map(item -> new CartItem(item.findElement(itemName).getText(), item.findElement(itemPrice).getText()))
				.collect(Collectors.toList());
	}

	public List<String> getCartItemNames() {
		waitForCartItems(5);
		List<WebElement> items = driver.findElements(cartItems);
		return items.stream().map(item -> item.findElement(itemName).getText()).collect(Collectors.toList());
	}

	public double getCartItemPrice() {
		waitForCartItems(5);
		List<WebElement> items = driver.findElements(cartItems);
		return items.stream()
				.mapToDouble(item -> Double.parseDouble(item.findElement(itemPrice).getText().replace("$", "").trim()))
				.sum();
	}
	
	public void removeItemFromCart() {
		waitForCartItems(5);
		WebElement removeButton = driver.findElement(removeBtn1);
		if (removeButton.isDisplayed()) {
			removeButton.click();
		} else {
			throw new RuntimeException("Remove button is not displayed");
		}
	}
	
	public void clickCheckout() {
		driver.findElement(checkoutButton).click();
	}
	
	public void clickContinueShopping() {
		driver.findElement(continueShoppingButton).click();
	}
	
	public String getErrorMessage() {
		Alert alert = driver.switchTo().alert();
		String errorMessage = alert.getText();
		alert.accept();
		return errorMessage;
	}
	
	public void waitForCartItems(int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.presenceOfElementLocated(checkoutButton));
	}
	
	public void removeAllItems() {
		waitForCartItems(5);
		List<WebElement> items = driver.findElements(cartItems);
		for (WebElement item : items) {
			WebElement removeButton = item.findElement(By.xpath(".//button[text()='Remove']"));
			if (removeButton.isDisplayed()) {
				removeButton.click();
			}
		}
	}
	
	public void clickOnProduct(String productName) {
		waitForCartItems(5);
		List<WebElement> items = driver.findElements(cartItems);
		for (WebElement item : items) {
			if (item.findElement(itemName).getText().equalsIgnoreCase(productName)) {
				item.findElement(itemName).click();
				return;
			}
		}
		throw new RuntimeException("Product with name '" + productName + "' not found in the cart.");
	}

	public static class CartItem {
		private String name;
		private String price;

		public CartItem(String name, String price) {
			this.name = name;
			this.price = price;
		}

		public String getName() {
			return name;
		}

		public String getPrice() {
			return price;
		}

		@Override
		public String toString() {
			return name + " - " + price;
		}
	}
}

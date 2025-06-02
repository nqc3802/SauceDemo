package pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.CartPage.CartItem;

public class CheckoutStepTwoPage {
	private WebDriver driver;
	
	private By cartItems = By.className("cart_item");
	private By itemName = By.className("inventory_item_name");
	private By itemPrice = By.className("inventory_item_price");
	private By itemQuantity = By.className("cart_quantity");
	private By itemTotal = By.className("summary_subtotal_label");
	private By tax = By.className("summary_tax_label");
	private By total = By.className("summary_total_label");
	private By finishButton = By.id("finish");
	private By cancelButton = By.id("cancel");
	
	public CheckoutStepTwoPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public List<CartItem> getCartItems() {
		List<WebElement> items = driver.findElements(cartItems);
		return items.stream()
				.map(item -> new CartItem(item.findElement(itemName).getText(), item.findElement(itemPrice).getText()))
				.collect(Collectors.toList());
	}
	
	public List<String> getCartItemNames() {
		List<WebElement> items = driver.findElements(cartItems);
		return items.stream().map(item -> item.findElement(itemName).getText()).collect(Collectors.toList());
	}
	
	public int getItemQuantity(String targetName) {
		List<WebElement> items = driver.findElements(cartItems);
		for (WebElement item : items) {
			String name = item.findElement(itemName).getText().trim();
			if (name.equalsIgnoreCase(targetName.trim())) {
				String quantityText = item.findElement(itemQuantity).getText().trim();
				return Integer.parseInt(quantityText);
			}
		}
		return 0;
	}
	
	public double getCartItemPrice(String targetName) {
		List<WebElement> items = driver.findElements(cartItems);
		for (WebElement item : items) {
			String name = item.findElement(itemName).getText().trim();
			if (name.equalsIgnoreCase(targetName.trim())) {
				String priceText = item.findElement(itemPrice).getText().replace("$", "").trim();
				return Double.parseDouble(priceText);
			}
		}
		throw new RuntimeException("Item with name '" + targetName + "' not found.");
	}
	
	public double itemTotal() {
		List<WebElement> items = driver.findElements(cartItems);
		double total = 0.0;
		for (WebElement item : items) {
			String priceText = item.findElement(itemPrice).getText().replace("$", "").trim();
			String quantityText = item.findElement(itemQuantity).getText().trim();
			double price = Double.parseDouble(priceText);
			int quantity = Integer.parseInt(quantityText);
			total += price * quantity;
		}
		return total;
	}
	
	public double getItemTotal() {
		WebElement totalElement = driver.findElement(itemTotal);
		String totalText = totalElement.getText().replace("Item total: $", "").trim();
		return Double.parseDouble(totalText);
	}
	
	public double getTax() {
		WebElement taxElement = driver.findElement(tax);
		String taxText = taxElement.getText().replace("Tax: $", "").trim();
		return Double.parseDouble(taxText);
	}
	
	public double getTotal() {
		WebElement totalElement = driver.findElement(total);
		String totalText = totalElement.getText().replace("Total: $", "").trim();
		return Double.parseDouble(totalText);
	}
	
	public void clickFinishButton() {
		driver.findElement(finishButton).click();
	}
	
	public void clickCancelButton() {
		driver.findElement(cancelButton).click();
	}
	
	public void waitForCheckoutInformation(int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.presenceOfElementLocated(cartItems));
	}
}

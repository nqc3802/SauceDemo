package pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
	private WebDriver driver;

	private By addToCartBtn1 = By.id("add-to-cart-sauce-labs-backpack");
	private By addToCartBtn2 = By.id("add-to-cart-sauce-labs-bike-light");
	private By addToCartBtn3 = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
	private By addToCartBtn4 = By.id("add-to-cart-sauce-labs-fleece-jacket");
	private By addToCartBtn5 = By.id("add-to-cart-sauce-labs-onesie");
	private By addToCartBtn6 = By.id("add-to-cart-test.allthethings()-t-shirt-(red)");
	
	private By removeBtn1 = By.id("remove-sauce-labs-backpack");
	private By removeBtn2 = By.id("remove-sauce-labs-bike-light");
	private By removeBtn3 = By.id("remove-sauce-labs-bolt-t-shirt");
	private By removeBtn4 = By.id("remove-sauce-labs-fleece-jacket");
	private By removeBtn5 = By.id("remove-sauce-labs-onesie");
	private By removeBtn6 = By.id("remove-test.allthethings()-t-shirt-(red)");
	
	private By productSortContainer = By.className("product_sort_container");
	private By cartIcon = By.className("shopping_cart_link");
	
	private By menuButton = By.id("react-burger-menu-btn");
	private By logoutButton = By.id("logout_sidebar_link");
	private By resetButton = By.id("reset_sidebar_link");

	private By inventoryItems = By.className("inventory_item");
	private By productName = By.className("inventory_item_name");
	private By productPrice = By.className("inventory_item_price");

	public ProductPage(WebDriver driver) {
		this.driver = driver;
	}

	public void addBackpackToCart() {
		waitForButtonToBeClickable(addToCartBtn1, 5);
		driver.findElement(addToCartBtn1).click();
	}

	public void addBikeLightToCart() {
		waitForButtonToBeClickable(addToCartBtn2, 5);
		driver.findElement(addToCartBtn2).click();
	}

	public void addBoltTShirtToCart() {
		waitForButtonToBeClickable(addToCartBtn3, 5);
		driver.findElement(addToCartBtn3).click();
	}

	public void addFleeceJacketToCart() {
		waitForButtonToBeClickable(addToCartBtn4, 5);
		driver.findElement(addToCartBtn4).click();
	}

	public void addOnesieToCart() {
		waitForButtonToBeClickable(addToCartBtn5, 5);
		driver.findElement(addToCartBtn5).click();
	}

	public void addRedTShirtToCart() {
		waitForButtonToBeClickable(addToCartBtn6, 5);
		driver.findElement(addToCartBtn6).click();
	}
	
	public void removeBackpackFromCart() {
		waitForButtonToBeClickable(removeBtn1, 5);
		driver.findElement(removeBtn1).click();
	}

	public void removeBikeLightFromCart() {
		waitForButtonToBeClickable(removeBtn2, 5);
		driver.findElement(removeBtn2).click();
	}

	public void removeBoltTShirtFromCart() {
		waitForButtonToBeClickable(removeBtn3, 5);
		driver.findElement(removeBtn3).click();
	}

	public void removeFleeceJacketFromCart() {
		waitForButtonToBeClickable(removeBtn4, 5);
		driver.findElement(removeBtn4).click();
	}

	public void removeOnesieFromCart() {
		waitForButtonToBeClickable(removeBtn5, 5);
		driver.findElement(removeBtn5).click();
	}

	public void removeRedTShirtFromCart() {
		waitForButtonToBeClickable(removeBtn6, 5);
		driver.findElement(removeBtn6).click();
	}

	public void selectProductSort(String sortOption) {
		WebElement sortContainer = driver.findElement(productSortContainer);
		Select select = new Select(sortContainer);
		select.selectByValue(sortOption);
	}

	public void openMenu() {
		driver.findElement(menuButton).click();
	}

	public void logout() {
		openMenu();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
		driver.findElement(logoutButton).click();
	}

	public void openCart() {
		driver.findElement(cartIcon).click();
	}

	public List<InventoryItem> getInventoryItems() {
		waitForInventoryItems(5);
		List<WebElement> items = driver.findElements(inventoryItems);
		return items.stream().map(item -> new InventoryItem(item.findElement(productName).getText(),
				item.findElement(productPrice).getText())).collect(Collectors.toList());
	}

	public List<String> getInventoryItemsName() {
		waitForInventoryItems(5);
		List<WebElement> items = driver.findElements(inventoryItems);
		return items.stream().map(item -> item.findElement(productName).getText()).collect(Collectors.toList());
	}

	public List<Double> getInventoryItemsPrice() {
		waitForInventoryItems(5);
		List<WebElement> items = driver.findElements(inventoryItems);
		return items.stream().map(item -> item.findElement(productPrice).getText().replace("$", "").trim())
				.map(Double::parseDouble).collect(Collectors.toList());
	}

	public double getInventoryItemPrice(String targetName) {
		waitForInventoryItems(5);
		List<WebElement> items = driver.findElements(inventoryItems);
		for (WebElement item : items) {
			String name = item.findElement(productName).getText().trim();
			if (name.equalsIgnoreCase(targetName.trim())) {
				String priceText = item.findElement(productPrice).getText().replace("$", "").trim();
				return Double.parseDouble(priceText);
			}
		}
		throw new RuntimeException("Item with name '" + targetName + "' not found.");
	}

	public void waitForInventoryItems(int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.presenceOfElementLocated(inventoryItems));
	}
	
	public void waitForButtonToBeClickable(By locator, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public void resetApp() {
		openMenu();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(resetButton));
		driver.findElement(resetButton).click();
	}

	public static class InventoryItem {
		private String name;
		private String price;

		public InventoryItem(String name, String price) {
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
			return "IventoryItem [name=" + name + ", price=" + price + "]";
		}
	}
}

package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailPage {
	private WebDriver driver;

	private By cartIcon = By.className("shopping_cart_link");
	private By productName = By.className("inventory_details_name");
	private By removeButton = By.id("remove");

	public ProductDetailPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getProductName() {
		return driver.findElement(productName).getText();
	}

	public void removeFromCart() {
		waitForRemoveBtnToBeClickable(5);
		driver.findElement(removeButton).click();
	}
	
	public void openCart() {
		driver.findElement(cartIcon).click();
	}

	private void waitForRemoveBtnToBeClickable(int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.elementToBeClickable(removeButton));
	}

}

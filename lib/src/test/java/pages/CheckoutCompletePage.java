package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage {
	private WebDriver driver;
	
	private By completeHeader = By.xpath("//h2[@class='complete-header']");
	private By completeText = By.xpath("//div[@class='complete-text']");
	private By backHomeButton = By.id("back-to-products");
	
	public CheckoutCompletePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getCompleteHeader() {
		return driver.findElement(completeHeader).getText();
	}
	
	public String getCompleteText() {
		return driver.findElement(completeText).getText();
	}

	public void clickBackHomeButton() {
		driver.findElement(backHomeButton).click();
	}
}

package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutStepOnePage {
	private WebDriver driver;
	
	private By firstNameInput = By.id("first-name");
	private By lastNameInput = By.id("last-name");
	private By postalCodeInput = By.id("postal-code");
	private By continueButton = By.id("continue");
	private By cancelButton = By.id("cancel");
	private By errorMessage = By.cssSelector("h3[data-test='error']");
	
	public CheckoutStepOnePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void enterCheckoutInformation(String firstName, String lastName, String postalCode) {
		driver.findElement(firstNameInput).sendKeys(firstName);
		driver.findElement(lastNameInput).sendKeys(lastName);
		driver.findElement(postalCodeInput).sendKeys(postalCode);
	}
	
	public void clickContinue() {
		driver.findElement(continueButton).click();
	}
	
	public void clickCancel() {
		driver.findElement(cancelButton).click();
	}
	
	public String getErrorMessage() {
		return driver.findElement(errorMessage).getText();
	}
	
	public void waitForCheckoutForm(int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.elementToBeClickable(continueButton));
	}
}

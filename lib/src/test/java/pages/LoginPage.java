package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	private WebDriver driver;
	
	private By usernameField = By.id("user-name");
	private By passwordField = By.id("password");
	private By loginBtn = By.id("login-button");
	private By errorMessage = By.cssSelector("h3[data-test='error']");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void login(String username, String password) {
		driver.findElement(usernameField).sendKeys(username);
		driver.findElement(passwordField).sendKeys(password);
		driver.findElement(loginBtn).click();
	}
	
	public String getErrorMessage() {
		return driver.findElement(errorMessage).getText();
	}
}

package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTest extends BaseTest {
	@Test
	@DisplayName("Test đăng nhập thành công")
	public void loginSuccessTest() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
		
		String currentUrl = driver.getCurrentUrl();
		Assertions.assertEquals("https://www.saucedemo.com/inventory.html", currentUrl);
	}
	
	@Test
	@DisplayName("Test đăng nhập tài khoản bị khoá")
	public void loginLockedOutUserTest() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(ConfigReader.get("locked_user"), ConfigReader.get("password"));
		
		String errorMessage = loginPage.getErrorMessage();
		Assertions.assertEquals("Epic sadface: Sorry, this user has been locked out.", errorMessage);
	}
	
	@Test
	@DisplayName("Test đăng nhập với username chứa ký tự đặc biệt")
	public void loginWithSpecialCharacterTest() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(ConfigReader.get("special_character_user"), ConfigReader.get("password"));

		String errorMessage = loginPage.getErrorMessage();
		Assertions.assertEquals("Epic sadface: Username and password do not match any user in this service",
				errorMessage);
	}
	
	@Test
	@DisplayName("Test đăng nhập với username không tồn tại")
	public void loginWithWrongUsernameTest() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(ConfigReader.get("wrong_username"), ConfigReader.get("password"));

		String errorMessage = loginPage.getErrorMessage();
		Assertions.assertEquals("Epic sadface: Username and password do not match any user in this service",
				errorMessage);
	}
	
	@Test
	@DisplayName("Test đăng nhập với username trống")
	public void loginWithEmptyUsernameTest() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("", ConfigReader.get("password"));

		String errorMessage = loginPage.getErrorMessage();
		Assertions.assertEquals("Epic sadface: Username is required", errorMessage);
	}
}

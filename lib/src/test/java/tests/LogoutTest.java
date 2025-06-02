package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import base.BaseTest;
import pages.ProductPage;
import utils.ConfigReader;

public class LogoutTest extends BaseTest {
	@Test
	@DisplayName("Test đăng xuất thành công")
	public void logoutTest() {
		performLogin();
		
		ProductPage productPage = new ProductPage(driver);
		productPage.logout();
		String currentUrl = driver.getCurrentUrl();
		Assertions.assertTrue(currentUrl.contains(ConfigReader.get("baseUrl")), "Logout failed, current URL: " + currentUrl);
	}
}

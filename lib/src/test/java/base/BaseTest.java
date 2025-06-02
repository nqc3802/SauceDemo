package base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;
import utils.ConfigReader;

public class BaseTest {
	protected static WebDriver driver;

	@BeforeAll
	public static void setUp() {
		// Bật ẩn danh
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
	}

	@AfterAll
	public static void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
	
	@BeforeEach
	public void beforeEach() {
		driver.get(ConfigReader.get("baseUrl"));
	}
	
	@AfterEach
	public void afterEach() {
		driver.get(ConfigReader.get("cartUrl"));
		CartPage cartPage = new CartPage(driver);
		cartPage.removeAllItems();
	}
	
	public void performLogin() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
	}
	
	public void performAddToCart() {
		ProductPage productPage = new ProductPage(driver);
		productPage.addBackpackToCart();
		productPage.addBikeLightToCart();
	}
}

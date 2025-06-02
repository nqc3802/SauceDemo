package tests;

import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import base.BaseTest;
import pages.ProductPage;

public class SortingTest extends BaseTest {
	@Test
	@DisplayName("Test sắp xếp từ A đến Z")
	public void sortAZTest() {
		performLogin();
		ProductPage productPage = new ProductPage(driver);
		
		List<String> items = productPage.getInventoryItemsName();
		items.sort(String::compareTo);
		productPage.selectProductSort("az");
		List<String> sortedItems = productPage.getInventoryItemsName();
		for (int i = 0; i < items.size(); i++) {
			if (!items.get(i).equals(sortedItems.get(i))) {
				throw new AssertionError("Items are not sorted from A to Z");
			}
		}
	}
	
	@Test
	@DisplayName("Test sắp xếp từ Z đến A")
	public void sortZATest() {
		performLogin();
		ProductPage productPage = new ProductPage(driver);

		List<String> items = productPage.getInventoryItemsName();
		items.sort(Comparator.reverseOrder());
		productPage.selectProductSort("za");
		List<String> sortedItems = productPage.getInventoryItemsName();
		for (int i = 0; i < items.size(); i++) {
			if (!items.get(i).equals(sortedItems.get(i))) {
				throw new AssertionError("Items are not sorted from Z to A");
			}
		}
	}
	
	@Test
	@DisplayName("Test sắp xếp theo giá từ thấp đến cao")
	public void sortLowToHighTest() {
		performLogin();
		ProductPage productPage = new ProductPage(driver);

		List<Double> prices = productPage.getInventoryItemsPrice();
		prices.sort(Double::compareTo);
		productPage.selectProductSort("lohi");
		List<Double> sortedPrices = productPage.getInventoryItemsPrice();
		for (int i = 0; i < prices.size(); i++) {
			if (!prices.get(i).equals(sortedPrices.get(i))) {
				throw new AssertionError("Items are not sorted from low to high");
			}
		}
	}
	
	@Test
	@DisplayName("Test sắp xếp theo giá từ cao đến thấp")
	public void sortHighToLowTest() {
		performLogin();
		ProductPage productPage = new ProductPage(driver);

		List<Double> prices = productPage.getInventoryItemsPrice();
		prices.sort(Comparator.reverseOrder());
		productPage.selectProductSort("hilo");
		List<Double> sortedPrices = productPage.getInventoryItemsPrice();
		for (int i = 0; i < prices.size(); i++) {
			if (!prices.get(i).equals(sortedPrices.get(i))) {
				throw new AssertionError("Items are not sorted from high to low");
			}
		}
	}

}

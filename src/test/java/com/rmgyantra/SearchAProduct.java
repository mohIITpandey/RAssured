package com.rmgyantra;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import Homepage.homepage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchAProduct {
	WebDriver driver;
	homepage home=new homepage(driver);
@Test
public void test() {
	WebDriverManager.chromedriver().setup();
	driver= new ChromeDriver();
	driver.get("https://amazon.in");
	home.selectFromDropDown("Computers & Accessories");
	home.getSearchTextBox().sendKeys("dell Laptop");
	home.getSearchButton().click();
}
}

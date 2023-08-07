package com.rmgyantra;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
public class thirdScenario {
@Test
public void test() {
	WebDriverManager.chromedriver().setup();
   WebDriver driver=new ChromeDriver();
   driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
   driver.get("https://cosmocode.io/automation-practice-webtable/");
   WebElement more = driver.findElement(By.xpath("//nav[@id='site-navigation']//ul[@id='mega-menu-primary']/li/a[contains(.,'More')]"));
   Actions a= new Actions(driver);
   a.moveToElement(more);
   driver.findElement(By.xpath("//nav[@id='site-navigation']//ul[@id='mega-menu-primary']/li/a[contains(.,'More')]/following-sibling::ul/li/a[contains(.,'WebTable')]")).click();
   Alert a1=driver.switchTo().alert();
   a1.accept();
   List<WebElement> countriesName = driver.findElements(By.xpath("//table[@id='countries']//td[contains(.,'Dollar')]/following-sibling::td[contains(.,'English')]/preceding-sibling::td/strong"));
   for(int i=0;i<countriesName.size();i++) {
	   String name = countriesName.get(i).getText();
	   System.out.println(name);
   }
}
}

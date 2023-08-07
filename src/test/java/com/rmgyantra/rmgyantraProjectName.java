package com.rmgyantra;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import Utlility.DataBaseUtility;
import Utlility.JavaUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import static io.restassured.RestAssured.*;

public class rmgyantraProjectName {
	JavaUtility Utils= new JavaUtility();
	DataBaseUtility DbUtils=new DataBaseUtility();
	
	@Test
	public void test() throws InterruptedException, SQLException {
	
	WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("http://rmgtestingserver:8084/");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		
		driver.findElement(By.xpath("//button[.='Sign in']")).click();
		
		driver.findElement(By.xpath("//a[.='Projects']")).click();
		driver.findElement(By.xpath("//span[.='Create Project']")).click();
		String name="mohit"+Utils.getRandomNum();
		driver.findElement(By.xpath("//input[@name='projectName']")).sendKeys(name);
	    driver.findElement(By.xpath("//input[@name='createdBy']")).sendKeys("sachin");
        WebElement sel = driver.findElement(By.xpath("//select[@name='status' and not(@class='form-control')]"));
        Select s=new Select(sel);
        s.selectByValue("Created");
        driver.findElement(By.xpath("//input[@value='Add Project']")).click();
        String text = driver.findElement(By.xpath("//tr/td[2][.='"+name+"']/preceding-sibling::td")).getText();
        System.out.println(text);
        
        baseURI="http://rmgtestingserver";
        port=8084;
        when().get("/projects/"+text)
        .then().log().all().assertThat().statusCode(200);
        
       DbUtils.EstablishDataBaseConnection();
       String query="select * from project;";
       String actData = DbUtils.readDataFromDBAndValidate(query, 1, text);
       System.out.println(actData+"---------");

       AssertJUnit.assertEquals(text,actData);
       System.out.println("OK");
        
        
        
	}
}

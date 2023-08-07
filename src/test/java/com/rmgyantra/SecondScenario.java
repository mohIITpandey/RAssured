package com.rmgyantra;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import Utlility.DataBaseUtility;
import Utlility.JavaUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SecondScenario {
	JavaUtility Utils= new JavaUtility();
    DataBaseUtility DbUtils=new DataBaseUtility();
	
	@Test
	public void test() throws SQLException {
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
		JSONObject jobj=new JSONObject();
		jobj.put("status","Completed");
		jobj.put("projectName","mohmaya1");
		jobj.put("teamSize","12");
	
		Response res = given().body(jobj).contentType(ContentType.JSON)
		.when().put("http://rmgtestingserver:8084/projects/"+text);
		 System.out.println(res.prettyPeek());
		 res.then().assertThat().statusCode(200);
		 
        DbUtils.EstablishDataBaseConnection();
    	DbUtils.readDataFromDBAndValidate("select * from project;", 1, text);
         DbUtils.query(text);
         
		 
	        
       }

}

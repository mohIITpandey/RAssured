package Homepage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class homepage {
@FindBy(id="searchDropdownBox")private WebElement SearchDropDown;
@FindBy(id="twotabsearchtextbox")private WebElement searchTextBox;
@FindBy(id="//input[contains(@id,'submit-button')]")private WebElement searchButton;
public WebElement getSearchDropDown() {
	return SearchDropDown;
}
public void setSearchDropDown(WebElement searchDropDown) {
	SearchDropDown = searchDropDown;
}
public WebElement getSearchTextBox() {
	return searchTextBox;
}
public void setSearchTextBox(WebElement searchTextBox) {
	this.searchTextBox = searchTextBox;
}
public WebElement getSearchButton() {
	return searchButton;
}
public void setSearchButton(WebElement searchButton) {
	this.searchButton = searchButton;
}
public homepage(WebDriver driver) {
	PageFactory.initElements(driver, this);
}


public void selectFromDropDown(String text) {
	Select s= new Select(getSearchDropDown());
	s.selectByVisibleText(text);
}


}

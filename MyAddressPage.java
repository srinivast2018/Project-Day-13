package com.ibm.groceriespages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ibm.utilities.GetScreenshot;

public class MyAddressPage {

	// To locate full name
	@FindBy(xpath = "//input[@id='name']")
	WebElement fullnameEle;

	// To locate email
	@FindBy(xpath = "//input[@id='email']")
	WebElement emailEle;

	// To locate address
	@FindBy(xpath = "//textarea[@id='address']")
	WebElement addressEle;

	// To locate city drop down
	@FindBy(xpath = "//select[@id='city']")
	WebElement cityEle;

	// To locate pin code
	@FindBy(xpath = "//input[@id='pincode']")
	WebElement pincodeEle;

	// To locate home or office drop down
	@FindBy(xpath = "//select[@id='type']")
	WebElement placeEle;

	// To locate Update button
	@FindBy(xpath = "//button[@class='button button-check-out']")
	WebElement updatEle;

	WebDriverWait wait;
	WebDriver driver;

	public MyAddressPage(WebDriver driver, WebDriverWait wait) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.wait = wait;
	}

	// To update with out filling address
	public String fillAddressEmpty() throws IOException, InterruptedException {
		fullnameEle.clear();
		emailEle.clear();
		addressEle.clear();

		// To select city as blank
		Select citySelect = new Select(cityEle);
		citySelect.selectByIndex(0);
		pincodeEle.clear();

		GetScreenshot screen = new GetScreenshot();
		screen.takeScreenshot(driver);

		// To click on update buttton
		updatEle.click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String tooltipMessage = js.executeScript("return document.getElementsByName('name')[0].validationMessage;")
				.toString();
		return tooltipMessage;

	}

	public String enterAddress(String fullname, String mailid, String address, String pincode, String cityName,
			String placeName) throws IOException, InterruptedException {
		fullnameEle.sendKeys(fullname);
		emailEle.sendKeys(mailid);
		addressEle.sendKeys(address);

		// To select city
		Select citySelect = new Select(cityEle);
		// citySelect.selectByIndex(10);
		citySelect.selectByVisibleText(cityName);
		// To enter pin code
		pincodeEle.sendKeys(pincode);

		// To select place
		Select placeSelect = new Select(placeEle);
		// placeSelect.selectByIndex(0);
		placeSelect.selectByVisibleText(placeName);
		GetScreenshot screen = new GetScreenshot();
		screen.takeScreenshot(driver);

		// To click on continue to payment button
		updatEle.click();
		return driver.getPageSource();
	}

	// to click on My address link
	public void clickOnMyAddress() {
		driver.findElement(By.partialLinkText("My Account")).click();

		driver.findElement(By.xpath("//a[contains(text(),'My Address')]")).click();
		
	}

	// To get updated full name
	public String getFullName() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String name = js.executeScript("return document.getElementsByName('name')[0].value;").toString();
		return name;
	}

	// TO get updated email
	public String getEmail() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String email = js.executeScript("return document.getElementsByName('email')[0].value;").toString();
		return email;
	}

	// TO get updated address
	public String getAddress() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String address = js.executeScript("return document.getElementsByName('address')[0].value;").toString();
		return address;
	}

	// To Get updated City
	public String getCity() {
		// To get city
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String city = js.executeScript("return document.getElementsByName('city')[0].value;").toString();
		return city;

	}

	// TO get updated pin code
	public String getPinCode() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String pincode = js.executeScript("return document.getElementsByName('pincode')[0].value;").toString();
		return pincode;

	}

	public String getPlace() {
		// To get place
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String place = js.executeScript("return document.getElementsByName('type')[0].value;").toString();
		return place;

	}
}

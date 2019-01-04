package com.ibm.groceriespages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ibm.utilities.GetScreenshot;

public class AccountPage {

	@FindBy(xpath = "//a[contains(text(),'My Address')]")
	WebElement myaddressEle;

	WebDriver driver;
	WebDriverWait wait;

	@FindBy(linkText = "Log Out")
	WebElement logOutEle;

	public AccountPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver, this);
	}

	// Method to click on My address link
	public void clickOMyAccount() throws IOException, InterruptedException {

		driver.findElement(By.partialLinkText("My Account")).click();
		GetScreenshot screen = new GetScreenshot();
		screen.takeScreenshot(driver);
		myaddressEle.click();
	}

}

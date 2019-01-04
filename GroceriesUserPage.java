package com.ibm.groceriespages;

import java.io.IOException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.ibm.utilities.GetScreenshot;

public class GroceriesUserPage {

	@FindBy(xpath = "//input[@placeholder='Search for products...']")
	WebElement searchEle;

	@FindBy(xpath = "(//div[@class='input-group']/descendant::input[1])[2]")
	WebElement searchEle2;

	@FindBy(xpath = "//div[@id='searchproducts-div']/descendant::a[1]")
	WebElement productNewLink;

	// To locate email link on user page
	@FindBy(xpath = "(//div[@class='header-mid-right-content']/descendant::a[1])[2]")
	WebElement emailLink;

	// To locate phone link on user page
	@FindBy(xpath = "(//div[@class='header-mid-right-content']/descendant::a[1])[1]")
	WebElement phoneLink;

	// To locate SignUp link
	@FindBy(xpath = "//a[text()='SignUp']")
	WebElement signupEle;

	// To locate fullname text box
	@FindBy(xpath = "//input[@id='name']")
	WebElement fullnameEle;

	// To locate phonnumber text box on signupuser page
	@FindBy(xpath = "//input[@id='pnum']")
	WebElement phonenumEle;

	// To locate password element on signup user page
	@FindBy(xpath = "//input[@id='password']")
	WebElement passwordEle;

	// To locate confirm password text box
	@FindBy(xpath = "//input[@id='cpassword']")
	WebElement confirmpasswordEle;

	// TO locate agree terms check box
	@FindBy(xpath = "//input[@id='tccheckbox']")
	WebElement agreeCheckEle;

	// To locate sign up button
	@FindBy(xpath = "//button[@id='mem_signup']")
	WebElement signupButton;

	// To locate addto cart link
	@FindBy(xpath = "//a[@id='addtocart_cartbtn336']")
	WebElement addcartEle;

	// To locate addto cart link for placing order
	@FindBy(xpath = "//a[@id='addtocart_cartbtn403']")
	WebElement addcartEle2;

	// To locate Cart icon link
	// @FindBy(xpath="//a[text()=' Cart']")
	// WebElement cartEle;

	@FindBy(xpath = "//div[@class='header-bottom-right']/descendant::a[1]")
	WebElement cartEle;

	// To locate Go to Cart icon link
	@FindBy(xpath = "//a[text()='Go To Cart']")
	WebElement gotocartEle;

	// To locate Check Out button
	@FindBy(xpath = "//a[text()='Check Out']")
	WebElement checkoutEle;
	
	// To locate Login link button
	@FindBy(xpath = "//a[text()='Login']")
	WebElement loginLink;
	
	//To locate phone number2 on sign in user page
	@FindBy(xpath="//input[@id='pnum2']")
	WebElement phonenumberEle;
	
	// To locate password element on sign in user page
	@FindBy(xpath = "//input[@id='pword2']")
	WebElement pwordEle;
	
	// To locate login button on sign in user page
		@FindBy(xpath = "//button[@id='mem_login']")
		WebElement loginButton;
		
	
	WebDriverWait wait;
	WebDriver driver;

	public GroceriesUserPage(WebDriver driver, WebDriverWait wait) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.wait = wait;
	}

	public String searchProduct(String proudctName) throws InterruptedException {

		searchEle.sendKeys(proudctName);
		searchEle.click();

		// To enter product name in search text box of popup
		searchEle2.sendKeys(proudctName);
		Thread.sleep(10000);
		productNewLink.click();

		return driver.getPageSource();

	}

	// To verify the new address on user page
	public String verifyAddress() {
		return driver.getPageSource();
	}

	// To verify the new email link is on user page
	public boolean verifyEmail(String newemail) {

		if (emailLink.getText().contains(newemail))

			return true;
		else
			return false;
	}

	// To verify the new phone number is user page
	public boolean verifyPhone(String newphone) {

		if (phoneLink.getText().contains(newphone))

			return true;
		else
			return false;

	}

	public void signUp(String fullName, String phoneNum, String password, String confirmPassword)
			throws InterruptedException {

		// To click signUp link
		signupEle.click();
		// To enter the value for full name
		fullnameEle.sendKeys(fullName);
		// To enter the value for phone number
		phonenumEle.sendKeys(phoneNum);
		// To enter the value for password
		passwordEle.sendKeys(password);
		// To enter the value for confirm password
		confirmpasswordEle.sendKeys(confirmPassword);

		// To click on check box for agree terms
		agreeCheckEle.click();

		// To click on SignUp button
		signupButton.click();

		// To click on Ok button of Alert box after signup
		Alert alertBox = driver.switchTo().alert();
		String text = alertBox.getText();
		Thread.sleep(2000);
		alertBox.accept();

	}

	public void addProductToCart() throws InterruptedException, IOException {
		GetScreenshot screenObj = new GetScreenshot();
		// To scroll down using key down
		Actions actions = new Actions(driver);
		for (int i = 1; i <= 4; i++)
			actions.sendKeys(Keys.ARROW_DOWN).build().perform();

		// To click on Add to Cart link
		addcartEle.click();

		// To click on Cart link at top right corner
		cartEle.click();

		screenObj.takeScreenshot(driver);

		// To click on Got TO Cart link
		gotocartEle.click();

		screenObj.takeScreenshot(driver);
	}

	public String gotoCheckOut() throws IOException, InterruptedException {
		GetScreenshot screenObj = new GetScreenshot();

		// To scroll down using key down
		Actions actions = new Actions(driver);
		for (int i = 1; i <= 4; i++)
			actions.sendKeys(Keys.ARROW_DOWN).build().perform();

		// To click on Add to Cart link
		addcartEle2.click();
		// To click on Cart link at top right corner
		cartEle.click();
		screenObj.takeScreenshot(driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Check Out")));
		// To click on Check Out button
		checkoutEle.click();
		screenObj.takeScreenshot(driver);
		return driver.getPageSource();
	}
	
	//To click on login link
	public void clickOnLogin()
	{
		loginLink.click();
	}
	
	//Method to signin from user portal
	public void singIn(String phoneNum,String password) throws IOException, InterruptedException
	{
		GetScreenshot screenObj = new GetScreenshot();
		phonenumberEle.sendKeys(phoneNum);
		pwordEle.sendKeys(password);
		screenObj.takeScreenshot(driver);
		loginButton.click();
	}
	
}

package com.ibm.groceries;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.ibm.groceriespages.AccountPage;
import com.ibm.groceriespages.GroceriesUserPage;
import com.ibm.groceriespages.MyAddressPage;
import com.ibm.initialization.WebDriverLaunch;
import com.ibm.utilities.DatabaseConnection;
import com.ibm.utilities.GetScreenshot;

public class UpdateAddress extends WebDriverLaunch {

	@Test(priority = 1, testName = "UpdateAddress", groups = "low")
	public void updateAddress() throws IOException, InterruptedException, SQLException {
		String userPage = data.get("userPageUrl");
		String phoneNum = data.get("phoneNum");
		String password = data.get("pwd");
		String expErrorMessage = data.get("expErrorMessage");
		String fullname = data.get("fullnamevalue");
		String mailid = data.get("mailidvalue");
		String address = data.get("addressvalue");
		String pincode = data.get("pincodevalue");
		String cityName = data.get("cityName");
		String placeName = data.get("placeName");

		String expAddressUpdateMessage = data.get("expAddressUpdateMessage");

		// To launch Groceries user page
		driver.get(userPage);
		GetScreenshot screen = new GetScreenshot();
		screen.takeScreenshot(driver);

		GroceriesUserPage userpage = new GroceriesUserPage(driver, wait);
		// Calling method to click on Login link on uer portal
		userpage.clickOnLogin();

		// Calling method to sign in
		userpage.singIn(phoneNum, password);

		wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("My Account")));
		screen.takeScreenshot(driver);

		AccountPage accountObj = new AccountPage(driver, wait);
		// calling method to click on My address link
		accountObj.clickOMyAccount();

		// verifying full name text box is displayed
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='name']")).isDisplayed());
		MyAddressPage addressObj = new MyAddressPage(driver, wait);

		// calling method to update with out filling address
		String actualtooltipMessage = addressObj.fillAddressEmpty();
		screen.takeScreenshot(driver);

		// Verifying the expected tooltip error message displayed
		Assert.assertEquals(actualtooltipMessage, expErrorMessage);
		System.out.println(actualtooltipMessage);

		// Calling method to enter address details
		String pageSource = addressObj.enterAddress(fullname, mailid, address, pincode, cityName, placeName);
		Thread.sleep(2000);
		Assert.assertTrue(pageSource.contains(expAddressUpdateMessage));
		System.out.println(expAddressUpdateMessage);
		Reporter.log(expAddressUpdateMessage);

		// Calling method to click on My account->My Address
		addressObj.clickOnMyAddress();
		screen.takeScreenshot(driver);

		Thread.sleep(2000);
		// Verifying the updated address details
		Assert.assertEquals(addressObj.getFullName(), fullname);
		Assert.assertEquals(addressObj.getEmail(), mailid);
		Assert.assertEquals(addressObj.getAddress(), address);
		Assert.assertEquals(addressObj.getPinCode(), pincode);
		Assert.assertEquals(addressObj.getCity(), cityName);
		Assert.assertEquals(addressObj.getPlace(), placeName);

		DatabaseConnection conn = new DatabaseConnection();
		Statement st = conn.connectDatabase();
		// Verifying the address details in table
		ResultSet rs = st.executeQuery("select *from as_customer where name=" + "'"+fullname+"'");
		if (rs.next()) {
			Assert.assertEquals(rs.getString("name"), fullname);
			Assert.assertEquals(rs.getString("email"), mailid);
			Assert.assertEquals(rs.getString("address"), address);
			Assert.assertEquals(rs.getString("city"), cityName);
			Assert.assertEquals(rs.getString("pincode"), pincode);
			Assert.assertEquals(rs.getString("type"), placeName);
		}

	}
}
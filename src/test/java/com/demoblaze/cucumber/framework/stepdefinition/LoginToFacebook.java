package com.demoblaze.cucumber.framework.stepdefinition;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.demoblaze.cucumber.framework.helper.Alert.AlertHelper;
import com.demoblaze.cucumber.framework.helper.Logger.LoggerHelper;
import com.demoblaze.cucumber.framework.helper.TestBase.TestBase;
import com.demoblaze.cucumber.framework.helper.Wait.WaitHelper;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.*;
import java.util.ArrayList;

public class LoginToFacebook {
	private Logger Log = LoggerHelper.getLogger(LoginToFacebook.class);

	@Given("^User navigated to 123\\.COM$")
	public void user_navigated_to_Demoblaze123() throws Throwable {
	//	getResponseBody();
	  //   getResponseStatus();
	}

	
	
	@Given("^User navigated to Demoblaze\\.COM$")
	public void user_navigated_to_Demoblaze_COM() throws Throwable {
		TestBase.driver.get("https://www.demoblaze.com/index.html");
	}

	@When("^I click on \"([^\"]*)\"$")
	public void i_click_on(String arg1) throws Throwable {
		new WaitHelper(TestBase.driver).waitForDocumentReadyState();
		new WaitHelper(TestBase.driver).waitForElement(TestBase.driver, 5,
				TestBase.driver.findElement(By.xpath("//*[text()='" + arg1 + "']")));
		TestBase.driver.findElement(By.xpath("//*[text()='" + arg1 + "']")).click();
		new WaitHelper(TestBase.driver).waitForDocumentReadyState();

	}

	@When("^user select the \"([^\"]*)\"$")
	public void user_select_the(String arg1) throws Throwable {
		System.out.println(arg1);
		TestBase.driver.findElement(By.xpath(".//*[@id='email']")).click();
	}

	@Then("^I click on required laptops$")
	public void clickOnRequiredLaptops(DataTable testData) throws Throwable {
		List<String> details = testData.asList(String.class);
		System.out.println(details.get(0));
		for (String str : details) {
			TestBase.driver.findElement(By.xpath("//*[text()='" + str + "']")).click();
			TestBase.driver.findElement(By.xpath("//*[text()='Add to cart']")).click();
			new WaitHelper(TestBase.driver).waitForAlert();
			new AlertHelper(TestBase.driver).AcceptAlert();
		}
	}

	@Then("^I click on delete for product \"([^\"]*)\"$")
	public void i_click_on_delete_for_product(String arg1) throws Throwable {
		new WaitHelper(TestBase.driver).waitForElement(TestBase.driver, 5,
				TestBase.driver.findElement(By.xpath("//*[text()='" + arg1 + "']//following-sibling::td//a")));
		TestBase.driver.findElement(By.xpath("//*[text()='" + arg1 + "']//following-sibling::td//a")).click();
		int rowcount = TestBase.driver.findElements(By.xpath("//table//tr")).size();
		while (rowcount > 2) {
			Thread.sleep(2000);
			rowcount = TestBase.driver.findElements(By.xpath("//table//tr")).size();
		}

	}

	@Then("^I fill the web form fields$")
	public void i_fill_the_web_form_fields() throws Throwable {
		TestBase.driver.findElement(By.xpath("//*[@id='name']")).sendKeys("name");
		TestBase.driver.findElement(By.xpath("//*[@id='country']")).sendKeys("country");
		TestBase.driver.findElement(By.xpath("//*[@id='city']")).sendKeys("city");
		TestBase.driver.findElement(By.xpath("//*[@id='card']")).sendKeys("111");
		TestBase.driver.findElement(By.xpath("//*[@id='month']")).sendKeys("12");
		TestBase.driver.findElement(By.xpath("//*[@id='year']")).sendKeys("2020");
		String total = TestBase.driver.findElement(By.id("totalm")).toString();
		if (total.contains("0")) {
			Thread.sleep(2000);
		}

		new WaitHelper(TestBase.driver).waitForDocumentReadyState();

	}

	@Then("^I verfiy the Amount$")
	public void i_verfiy_the_Amount() throws Throwable {
		String[] text = TestBase.driver.findElement(By.xpath("//p[@class='lead text-muted ']")).getText().split("\n");
		System.out.println(text[1]);

		Assert.assertEquals(text[1], "Amount: 790 USD");
		for (String txt : text) {
			Log.info(txt);
		}

	}
	@When("^I wait for page load$")
	public void i_wait_for_page_load() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Thread.sleep(2000);
	}



	@Then("^I log the amount and Id$")
	public void i_log_the_amount_and_Id() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		int size = TestBase.driver.findElements(By.xpath("//p[@class='lead text-muted ']")).size();
		for (int i = 0; i < size; i++) {
			String text = TestBase.driver.findElement(By.xpath("//p[@class='lead text-muted ']//text()[" + i + "]"))
					.getText();
			System.out.println(text);
			Log.info(text);
		}

	}
	
	@Then("^I close the browser$")
	public void i_close_the_browser() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		TestBase.driver.close();
		TestBase.driver.quit();
	}

}

package com.pratice.fat_free_crm_qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest {
  private WebDriver driver;
  private String URL = "http://qacrm.herokuapp.com/login";

  @BeforeClass
  public void SetUp() {
    driver = new ChromeDriver();
    driver.get(URL);
  }

  @Test
  public void LoginWithValidCredientals() {
    // verify all the elements present on login page
    WebElement logo = driver.findElement(By.partialLinkText("Fat Free CRM"));
    WebElement loginLink = driver.findElement(By.partialLinkText("Login"));
    WebElement aboutLink = driver.findElement(By.partialLinkText("About"));
    WebElement loginTextfield = driver.findElement(By.id("authentication_username"));
    WebElement loginPasswordfield = driver.findElement(By.id("authentication_password"));
    WebElement loginButton = driver.findElement(By.name("commit"));
    WebElement forgotPasswordLink = driver.findElement(By.partialLinkText("Forgot Password?"));

    SoftAssert loginPage = new SoftAssert();
    loginPage.assertTrue(logo.isDisplayed(), "Fat Free CRM link is not present on the homepage");
    loginPage.assertTrue(loginLink.isDisplayed(), "Login link is not present on the homepage");
    loginPage.assertTrue(aboutLink.isDisplayed(), "About link is not present on the homepage");

    loginTextfield.sendKeys("heather");
    loginPasswordfield.sendKeys("heather");
    loginButton.click();
  }

  @Test
  public void LoginWithInValidUsernameCredientals() {
    // verify error message present on login page
    WebElement loginTextfield = driver.findElement(By.id("authentication_username"));
    loginTextfield.sendKeys("ghghjjhjk");
    WebElement loginPasswordfield = driver.findElement(By.id("authentication_password"));
    loginPasswordfield.sendKeys("heather");
    WebElement loginButton = driver.findElement(By.name("commit"));
    loginButton.click();

    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
    //      SoftAssert verifyingErrorMessageForUsername = new SoftAssert();
    //      verifyingErrorMessageForUsername.assertTrue(errorMessageForUsername.isDisplayed(),
    // "Invalid username or password error message is not present on the homepage");
  }
  
  
}
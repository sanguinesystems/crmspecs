package com.pratice.fat_free_crm_qa;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest {
  private WebDriver driver;
  private String URL = "http://qacrm.herokuapp.com/login";

  @BeforeMethod
  public void SetUp() throws MalformedURLException {
    // driver = new ChromeDriver();
    driver = new RemoteWebDriver(new URL(
                "http://pype-se-alb-1nslzg7mzvwrj-1482060190.us-east-1.elb.amazonaws.com/wd/hub"),
            DesiredCapabilities.chrome());
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

  @Test
  public void LoginWithInValidPasswordCredientals() {
    // verify error message present on login page
    WebElement loginTextfield = driver.findElement(By.id("authentication_username"));
    loginTextfield.sendKeys("heather");
    WebElement loginPasswordfield = driver.findElement(By.id("authentication_password"));
    loginPasswordfield.sendKeys("sdgfsjfgsdjf");
    WebElement loginButton = driver.findElement(By.name("commit"));
    loginButton.click();

    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
  }

  @Test
  public void JavaScriptExecutor() {
    WebElement loginTextfield = driver.findElement(By.id("authentication_username"));
    loginTextfield.sendKeys("heather");
    WebElement loginPasswordfield = driver.findElement(By.id("authentication_password"));
    loginPasswordfield.sendKeys("heather");
    WebElement loginButton = driver.findElement(By.name("commit"));
    loginButton.click();

    JavascriptExecutor js = ((JavascriptExecutor) driver);
    js.executeScript("scroll(0,1000);");
    WebElement aboutFatFreeCRMLink =
        driver.findElement(By.xpath("//a[@title='About Fat Free CRM']"));
    SoftAssert aboutLink = new SoftAssert();
    aboutLink.assertTrue(
        aboutFatFreeCRMLink.isDisplayed(),
        "About Fat Free CRM link is not present on the homepage");
  }
  
  @AfterMethod
  public void tearDown() {
    driver.quit();
  }
}

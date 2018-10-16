package com.pratice.fat_free_crm_qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DashboardLeftPanelLists {
  private WebDriver driver;
  private String URL = "http://qacrm.herokuapp.com/login";

  @BeforeMethod
  public void SetUp() {
    driver = new ChromeDriver();
    //	driver = new RemoteWebDriver(new URL(
    //
    // "http://pype-se-alb-1nslzg7mzvwrj-1482060190.us-east-1.elb.amazonaws.com/wd/hub"),
    //            DesiredCapabilities.chrome());
    driver.get(URL);
  }

  @Test
  public void DashboardLeftPanelListsTest() {
    //Login into the website  
    WebElement loginTextfield = driver.findElement(By.id("authentication_username"));
    loginTextfield.sendKeys("heather");
    WebElement loginPasswordfield = driver.findElement(By.id("authentication_password"));
    loginPasswordfield.sendKeys("heather");
    WebElement loginButton = driver.findElement(By.name("commit"));
    loginButton.click();
    //Verify all the left panel list present on the dashboard page
    WebElement leftpaneGlobalLists = driver.findElement(By.xpath("//div[@class='caption' and .//text()='Global lists']"));
    WebElement leftpaneMyLists = driver.findElement(By.xpath("//div[@class='caption' and text()='My lists']"));
    leftpaneMyLists.click();
  }

  @AfterMethod
  public void TearDown() {
    driver.quit();
  }
}

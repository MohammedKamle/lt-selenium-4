package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class SampleTest {
    String username = System.getenv("LT_USERNAME") == null ? "mohammadk" : System.getenv("LT_USERNAME");
    String authkey = System.getenv("LT_ACCESS_KEY") == null ? "gkrzT0iFKjDjehXpMTznxM1lHIZXSYjV3H8Ntk0s2rCUJJO3WU" : System.getenv("LT_ACCESS_KEY");

    String hub = "@hub.lambdatest.com/wd/hub";

    private String Status = "failed";

    private RemoteWebDriver driver;

    @BeforeTest
    @Parameters({"browser"})
    public void beforeTest(String browser) throws MalformedURLException {

        if (browser.equals("chrome")){
            ChromeOptions browserOptions = new ChromeOptions();
            browserOptions.setPlatformName("Windows 10");
            browserOptions.setBrowserVersion("120.0");
            HashMap<String, Object> ltOptions = new HashMap<String, Object>();
            ltOptions.put("username", "mohammadk");
            ltOptions.put("accessKey", "gkrzT0iFKjDjehXpMTznxM1lHIZXSYjV3H8Ntk0s2rCUJJO3WU");
            ltOptions.put("project", "Untitled");
            ltOptions.put("selenium_version", "4.0.0");
            ltOptions.put("w3c", true);
            ltOptions.put("build", "debug");
            browserOptions.setCapability("LT:Options", ltOptions);
            System.out.println("Browser is "+browser);
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), browserOptions);
        }else {
            FirefoxOptions browserOptions = new FirefoxOptions();
            browserOptions.setPlatformName("Windows 10");
            browserOptions.setBrowserVersion("120.0");
            HashMap<String, Object> ltOptions = new HashMap<String, Object>();
            ltOptions.put("username", "mohammadk");
            ltOptions.put("accessKey", "gkrzT0iFKjDjehXpMTznxM1lHIZXSYjV3H8Ntk0s2rCUJJO3WU");
            ltOptions.put("project", "Untitled");
            ltOptions.put("w3c", true);
            ltOptions.put("build", "debug");

            browserOptions.setCapability("LT:Options", ltOptions);
            System.out.println("Browser is "+browser);

            driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), browserOptions);
        }



    }

    @Test
    public void test() {
        try {
            String spanText;
            System.out.println("Loading Url");

            driver.get("https://lambdatest.github.io/sample-todo-app/");

            System.out.println("Checking Box");
            driver.findElement(By.name("li1")).click();

            System.out.println("Checking Another Box");
            driver.findElement(By.name("li2")).click();

            System.out.println("Checking Box");
            driver.findElement(By.name("li3")).click();

            System.out.println("Checking Another Box");
            driver.findElement(By.name("li4")).click();

            driver.findElement(By.id("sampletodotext")).sendKeys(" List Item 6");
            driver.findElement(By.xpath("//*[@id = 'addbutton']")).click();

            driver.findElement(By.id("sampletodotext")).sendKeys(" List Item 7");
            driver.findElement(By.id("addbutton")).click();

            driver.findElement(By.id("sampletodotext")).sendKeys(" List Item 8");
            driver.findElement(By.id("addbutton")).click();

            System.out.println("Checking Another Box");
            driver.findElement(By.name("li1")).click();

            System.out.println("Checking Another Box");
            driver.findElement(By.name("li3")).click();

            System.out.println("Checking Another Box");
            driver.findElement(By.name("li7")).click();

            System.out.println("Checking Another Box");
            driver.findElement(By.name("li8")).click();

            System.out.println("Entering Text");
            driver.findElement(By.id("sampletodotext")).sendKeys("Get Taste of Lambda and Stick to It");

            driver.findElement(By.id("addbutton")).click();

            System.out.println("Checking Another Box");
            driver.findElement(By.name("li9")).click();

            // Let's also assert that the todo we added is present in the list.

            spanText = driver.findElement(By.xpath("/html/body/div/div/div/ul/li[9]/span")).getText();
            Assert.assertEquals("Get Taste of Lambda and Stick to It", spanText);
            Status = "passed";
            Thread.sleep(150);

            System.out.println("TestFinished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Close the browser window
            driver.executeScript("lambda-status=" + Status);
            driver.quit();
        }
    }
}


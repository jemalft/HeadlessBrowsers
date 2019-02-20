package initTest;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HeadlessTest {


    Logger log = LoggerFactory.getLogger(HeadlessTest.class);

    @Test
    public void HeadlessChromeDriverTest(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver_3");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.navigate().to("https://www.avenuecode.com/");
        String pageTitle = driver.getTitle();

        log.info("Page opened: {}", pageTitle);


        Assert.assertTrue(pageTitle.contains("Avenue Code | Trusted Advisors for Enterprise IT Consulting"));

        log.info("Quitting driver");
        driver.quit();

    }

    @Test
    public void headlessFirefoxDriverTest(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/geckodriver_3");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--headless");
        WebDriver driver = new FirefoxDriver(firefoxOptions);
        driver.get("https://www.avenuecode.com/");
        String actualTitle = driver.getTitle();

        log.info("Page opened: {}", actualTitle);

        Assert.assertTrue(actualTitle.contains("Avenue Code | Trusted Advisors for Enterprise IT Consulting"));

        log.info("closing driver");

        driver.quit();





    }




}

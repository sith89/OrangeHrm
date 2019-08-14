package test;

import common.FilePath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utility.PropertyManager;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    WebDriver driver;
    Properties prop = PropertyManager.loadData();

    private void openBrowser() {
        System.setProperty("webdriver.chrome.driver", FilePath.CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    private void closeBrowser() {
        driver.close();
    }

    private void loadApplication() {
        driver.navigate().to(prop.getProperty("loginUrl"));
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @BeforeClass
    public void runBeforeClass() {
        openBrowser();
        loadApplication();
    }

    @AfterClass
    public void tearDown() {
        closeBrowser();
    }
}

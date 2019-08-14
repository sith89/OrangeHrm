package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.LoggerUtil;

public class BasePage {

    private WebDriverWait wait;

    String verifyPageTitle(WebDriver driver) {
        return driver.getTitle();
    }


    void waitUntilElementClickable(WebDriver driver, By webElement, long waitingTimeInSeconds) {
        try {
            wait = new WebDriverWait(driver, waitingTimeInSeconds);
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
        } catch (WebDriverException e) {
            LoggerUtil.info(e.getMessage());
        }
    }

    public void waitForElementLoad(WebDriver driver, By webElement) {
        try {
            wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.visibilityOfElementLocated(webElement));
        } catch (WebDriverException e) {
            LoggerUtil.info(e.getMessage());
        }
    }

    void waitThread(long waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (Exception e) {
            LoggerUtil.info(e.getMessage());
        }
    }
}

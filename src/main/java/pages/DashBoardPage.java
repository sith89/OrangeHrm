package pages;

import org.openqa.selenium.*;
import utility.LoggerUtil;

public class DashBoardPage extends BasePage {

    private WebDriver driver;

    private By imgOrgHRMLogo = By.xpath("//img[@alt='OrangeHRM']");
    private By linkWelcomeUser = By.xpath("//a[@id='welcome']");
    private By linkBtnAdmin = By.id("menu_admin_viewAdminModule");
    private By btnLogout = By.linkText("Logout");

    public DashBoardPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return verifyPageTitle(driver);
    }

    public boolean isPageLogoDisplayed() {
        try {
            return driver.findElement(imgOrgHRMLogo).isDisplayed();
        } catch (ElementNotVisibleException e) {
            LoggerUtil.info(e.getMessage());
            return false;
        }
    }

    public boolean verifyWelcomeUser(String expectedWelcomeUserText) {
        try {
            waitUntilElementClickable(driver, linkWelcomeUser, 3);
            return driver.findElement(linkWelcomeUser).getText().equals(expectedWelcomeUserText);
        } catch (NoSuchElementException e) {
            LoggerUtil.info(e.getMessage());
            return false;
        }
    }

    public void clickAdmin() {
        WebElement loginBtn = driver.findElement(linkBtnAdmin);
        try {
            if (loginBtn.isEnabled() && loginBtn.isDisplayed())
                loginBtn.click();
        } catch (TimeoutException e) {
            LoggerUtil.info(e.getMessage());
        }
    }

    public void logOut() {
        try {
            driver.findElement(linkWelcomeUser).click();
            driver.findElement(btnLogout).click();

        } catch (TimeoutException e) {
            LoggerUtil.info(e.getMessage());
        }
    }


}

package pages;

import org.openqa.selenium.*;
import utility.LoggerUtil;

public class LoginPage extends BasePage {

    private WebDriver driver;

    private By imgOrgHRMLogo = By.id("divLogo");
    private By txtUserName = By.id("txtUsername");
    private By txtPassword = By.id("txtPassword");
    private By btnLogin = By.id("btnLogin");
    private By lblInvalidLogin = By.id("spanMessage");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return verifyPageTitle(driver);
    }

    public boolean isPageLogoDisplayed() {
        return driver.findElement(imgOrgHRMLogo).isDisplayed();
    }

    private void addUserName(String userName) {
        try {
            waitForElementLoad(driver, txtUserName);
            WebElement userNameTxtBox = driver.findElement(txtUserName);
            userNameTxtBox.sendKeys(userName);
            waitThread(1000);

        } catch (NoSuchElementException e) {
            LoggerUtil.info(e.getMessage());
        }
    }

    private void addUserPassword(String password) {
        try {
            waitForElementLoad(driver, txtPassword);
            WebElement userNameTxtBox = driver.findElement(txtPassword);
            userNameTxtBox.sendKeys(password);
            waitThread(1000);

        } catch (NoSuchElementException e) {
            LoggerUtil.info(e.getMessage());
        }
    }

    private void clickLoginBtn() {
        try {
            WebElement loginBtn = driver.findElement(btnLogin);
            if (loginBtn.isEnabled() && loginBtn.isDisplayed())
                loginBtn.click();
        } catch (TimeoutException e) {
            LoggerUtil.info(e.getMessage());
        }
    }

    public String getInvalidLoginLabelTxt() {
        try {
            WebElement invalidLogin = driver.findElement(lblInvalidLogin);
            if (invalidLogin.isDisplayed())
                return invalidLogin.getText();

        } catch (NoSuchElementException e) {
            LoggerUtil.info(e.getMessage());
        }
        return null;
    }

    /**
     * This method is used to Login in the orangeHRM
     *
     * @param userName valid username for the Login which is stored in configuration.properties
     * @param password valid password for the login which is stored in configuration.properties
     */
    public void loginToOrangeHRM(String userName, String password) {
        this.addUserName(userName);
        this.addUserPassword(password);
        this.clickLoginBtn();
    }

}

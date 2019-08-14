package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utility.LoggerUtil;

import java.util.List;

public class AddUserPage extends BasePage {

    private WebDriver driver;

    private By drpDwnUsrRole = By.id("systemUser_userType");
    private By txtEmployeeName = By.id("systemUser_employeeName_empName");
    private By txtEmployeeSuggestedList = By.xpath("//div[@class='ac_results']/ul/li");
    private By txtUserName = By.id("systemUser_userName");
    private By drpDwnStatus = By.id("systemUser_status");
    private By txtPassword = By.id("systemUser_password");
    private By lblPasswordStrongMeter = By.id("systemUser_password_strength_meter");
    private By txtConfirmPassword = By.id("systemUser_confirmPassword");
    private By btnSave = By.id("btnSave");

    public AddUserPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectUserRole(String selectedText) {
        try {
            waitUntilElementClickable(driver, drpDwnUsrRole, 3);
            WebElement userRoleDrpDown = driver.findElement(drpDwnUsrRole);
            Select select = new Select(userRoleDrpDown);
            if (!select.getFirstSelectedOption().getText().equals(selectedText)) {
                select.selectByVisibleText(selectedText);
            }
            waitThread(1000);
        } catch (ElementNotSelectableException e) {
            LoggerUtil.info(e.getMessage());
        }
    }

    /**
     * Select employee Name.
     * here if we click name by mouse click, inline error message is displayed.
     * so used Keys.ENTER function
     *
     * @param employeeName passing employee name which we need to select
     */
    public void selectEmployeeName(String employeeName) {
        try {
            waitUntilElementClickable(driver, drpDwnUsrRole, 3);
            WebElement employeeNameTxtBox = driver.findElement(txtEmployeeName);
            employeeNameTxtBox.sendKeys(employeeName);
            waitThread(1000);
            List<WebElement> empNamesList = driver.findElements(txtEmployeeSuggestedList);
            if (!empNamesList.isEmpty()) {
                new Actions(driver).moveToElement(empNamesList.get(0)).sendKeys(Keys.ENTER).perform();
            }
            waitThread(1000);
        } catch (ElementNotSelectableException e) {
            LoggerUtil.info(e.getMessage());
        }
    }

    public void addUserName(String userName) {
        try {
            WebElement userNameTxtBox = driver.findElement(txtUserName);
            userNameTxtBox.sendKeys(userName);

        } catch (NoSuchElementException e) {
            LoggerUtil.info(e.getMessage());
        }
    }

    public void selectStatus(String selectedText) {
        try {
            waitUntilElementClickable(driver, drpDwnStatus, 3);
            WebElement statusDrpDown = driver.findElement(drpDwnStatus);
            Select select = new Select(statusDrpDown);
            if (!select.getFirstSelectedOption().getText().equals(selectedText)) {
                select.selectByVisibleText(selectedText);
            }
            waitThread(1000);

        } catch (ElementNotSelectableException e) {
            LoggerUtil.info(e.getMessage());
        }
    }

    public void addPassword(String password) {
        try {
            WebElement passwordTxtBox = driver.findElement(txtPassword);
            passwordTxtBox.sendKeys(password);
            waitThread(1000);

        } catch (NoSuchElementException e) {
            LoggerUtil.info(e.getMessage());
        }
    }

    /**
     * retrieve password Strong Meter status
     *
     * @return password Strong Meter status
     */
    public String getPasswordStrongMeterStatus() {
        WebElement passwordStrongMeter = driver.findElement(lblPasswordStrongMeter);
        waitThread(1000);
        return passwordStrongMeter.getText();
    }

    public void addConfirmPassword(String confirmPassword) {
        try {
            WebElement confirmPasswordTxtBox = driver.findElement(txtConfirmPassword);
            confirmPasswordTxtBox.sendKeys(confirmPassword);
            waitThread(1000);

        } catch (NoSuchElementException e) {
            LoggerUtil.info(e.getMessage());
        }
    }

    public void clickBtnSave() {
        try {
            driver.findElement(btnSave).click();
            waitThread(1000);
        } catch (TimeoutException e) {
            LoggerUtil.info(e.getMessage());

        }
    }

}

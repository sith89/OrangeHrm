package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utility.LoggerUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminPage extends BasePage {

    private WebDriver driver;

    private By btnAddUser = By.id("btnAdd");
    private By tblBody = By.xpath("//table[@id='resultTable']/tbody/tr");

    public AdminPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickAddUserBtn() {
        try {
            WebElement loginBtn = driver.findElement(btnAddUser);
            loginBtn.click();
        } catch (TimeoutException e) {
            LoggerUtil.info(e.getMessage());

        }

    }

    /**
     * This method returns a Map with saved User data
     * @param userName
     * @return Map with created username, user role, employee name, status
     */
    public Map<String, String> getSavedUserData(String userName) {
        Map<String, String> userData = new HashMap<String, String>();
        List<WebElement> tblData = driver.findElements(tblBody);
        for (WebElement rows : tblData) {
            List<WebElement> rowData = rows.findElements(By.tagName("td"));
            if (rowData.get(1).findElement(By.tagName("a")).getText().equals(userName)) {
                userData.put("username", rowData.get(1).findElement(By.tagName("a")).getText());
                userData.put("userRole", rowData.get(2).getText());
                userData.put("employeeName", rowData.get(3).getText());
                userData.put("status", rowData.get(4).getText());
                break;
            }
        }
        return userData;
    }

}

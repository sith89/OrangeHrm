package test;

import common.AssertionErrors;
import common.FilePath;
import data.CommonDataProvider;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddUserPage;
import pages.AdminPage;
import pages.DashBoardPage;
import pages.LoginPage;
import utility.ErrorScreenshot;
import utility.HelperUtil;
import utility.LoggerUtil;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Sithira Pathirana
 * @version 1.0
 */
public class OrangeHRMTest extends TestBase {

    private SoftAssert softAssert = new SoftAssert();
    // load username and password form a configuration.properties file
    private String userName = prop.getProperty("username");
    private String password = prop.getProperty("password");
    private DashBoardPage dashBoardPage;
    private LoginPage loginPage;

    @BeforeClass
    public void initLog() {
        String log4jConfPath = FilePath.LOG4J_PATH;
        PropertyConfigurator.configure(log4jConfPath);
        LoggerUtil.startLog(this.getClass().getName());
    }

    @Test(priority = 0, description = "Successful Login Test for the OrangeHRM")
    public void testOrangeHRMLogin(Method method) {
        LoggerUtil.info(method.getName() + " Test is Starting");

        loginPage = new LoginPage(driver);
        softAssert.assertEquals(loginPage.getPageTitle(), CommonDataProvider.PAGE_TITLE, AssertionErrors.INVALID_PAGE_TITLE);
        softAssert.assertTrue(loginPage.isPageLogoDisplayed(), AssertionErrors.PAGE_LOGO_IS_NOT_DISPLAYED);
        loginPage.loginToOrangeHRM(userName, password);

        // Assert Dashboard page details once after Login to the system
        dashBoardPage = new DashBoardPage(driver);
        softAssert.assertEquals(dashBoardPage.getPageTitle(), CommonDataProvider.PAGE_TITLE, AssertionErrors.INVALID_PAGE_TITLE);
        softAssert.assertTrue(dashBoardPage.isPageLogoDisplayed(), AssertionErrors.PAGE_LOGO_IS_NOT_DISPLAYED);
        softAssert.assertTrue(dashBoardPage.verifyWelcomeUser("Welcome " + userName), "Invalid Welcome User Link Text");

        LoggerUtil.info(method.getName() + " Test is Ending");
        softAssert.assertAll();
    }

    @Test(priority = 1, dependsOnMethods = "testOrangeHRMLogin")
    public void testAddUserToTheHRM(Method method) {
        LoggerUtil.info(method.getName() + " Test is Starting");

        String userRole = "Admin";
        String employeeName = "Linda";
        String status = "Disabled";
        String password = "1qaz@wsxAbc150";
        //Add user form only allowed for unique System User name. so here using customize random String
        String systemUserName = HelperUtil.getRandomStringForGivenLength(7);

        dashBoardPage.clickAdmin();
        AdminPage adminPage = new AdminPage(driver);
        adminPage.clickAddUserBtn();

        AddUserPage addUserPage = new AddUserPage(driver);
        fillAddUserForm(addUserPage, userRole, employeeName, systemUserName, status, password);
        softAssert.assertTrue(addUserPage.getPasswordStrongMeterStatus().equals("Strong") || addUserPage.getPasswordStrongMeterStatus().equals("Strongest"), "Password Is not Strong enough");
        addUserPage.clickBtnSave();

        //Assert  created User record in Table
        Map<String, String> userTableData = adminPage.getSavedUserData(systemUserName);
        softAssert.assertEquals(userTableData.get("username"), systemUserName, "Invalid User Data Stored");
        softAssert.assertEquals(userTableData.get("userRole"), userRole, "Invalid User Role Stored");
        softAssert.assertTrue(userTableData.get("employeeName").contains(employeeName), "Invalid Employee Name Stored");
        softAssert.assertEquals(userTableData.get("status"), status, "Invalid Status Stored");

        LoggerUtil.info(method.getName() + " Test is Ending");
        softAssert.assertAll();
    }

    //Additional test for invalid username password assertions
    @Test(priority = 2, dependsOnMethods = "testAddUserToTheHRM")
    public void testOrangeHAMLoginWithInvalidCredential(Method method) {
        String invalidPassword = "12345678";
        LoggerUtil.info(method.getName() + " Test is Starting");
        dashBoardPage.logOut();

        loginPage.loginToOrangeHRM(userName, invalidPassword);
        softAssert.assertEquals(loginPage.getInvalidLoginLabelTxt(), "Invalid credentials", "Invalid login error Message");
        softAssert.assertFalse(dashBoardPage.verifyWelcomeUser("Welcome " + userName), "Welcome User Link Text should not be displayed");
        softAssert.assertAll();
    }

    /**
     * This method is used to Add the User details in to relevant fields
     *
     * @param addUserPage  User page object
     * @param userRole     Admin role
     * @param employeeName selected employee name
     * @param userName     random name greater than 5 characters
     * @param status       Disabled
     * @param password     password
     */
    private void fillAddUserForm(AddUserPage addUserPage, String userRole, String employeeName, String userName, String status, String password) {
        addUserPage.selectUserRole(userRole);
        addUserPage.selectEmployeeName(employeeName);
        addUserPage.addUserName(userName);
        addUserPage.selectStatus(status);
        addUserPage.addPassword(password);
        addUserPage.addConfirmPassword(password);
    }

    @AfterMethod
    public void getScreenShotWhenFailure(ITestResult result) {
        ErrorScreenshot.takeScreenShotWhenFailure(driver, result);
    }

    @AfterClass
    public void endLog() {
        LoggerUtil.endLog(this.getClass().getName());
    }

}

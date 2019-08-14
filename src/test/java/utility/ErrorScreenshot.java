package utility;

import common.FilePath;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ErrorScreenshot {

    /**
     * Take screenshot when there is a failure and save in screenshot folder in test package
     *
     * @param result retrieve
     */
    public static void takeScreenShotWhenFailure(WebDriver driver, ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                TakesScreenshot screenshot = (TakesScreenshot) driver;
                File src = screenshot.getScreenshotAs(OutputType.FILE);
                FileHandler.copy(src, new File(String.format(FilePath.SCREENSHOT_PATH, result.getName())));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (ITestResult.SUCCESS == result.getStatus()) {
            try {
                Files.deleteIfExists(Paths.get(String.format(FilePath.SCREENSHOT_PATH, result.getName())));
            } catch (Exception e) {
                e.getMessage();
            }
        }

    }
}

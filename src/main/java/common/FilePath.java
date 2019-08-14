package common;

public class FilePath {

    private static String userDirectoryPath = System.getProperty("user.dir");

    public static final String PROPERTY_FILE_PATH = userDirectoryPath + "/src/main/resources/configuration.properties";
    public static final String CHROME_DRIVER_PATH = userDirectoryPath + "/src/main/resources/drivers/chromedriver";
    public static final String LOG4J_PATH = userDirectoryPath + "/src/main/resources/logs/log4j.properties";
    public static final String SCREENSHOT_PATH = userDirectoryPath + "/src/test/screenshots/%s" + ".png";

}

package utility;

import org.apache.commons.lang3.RandomStringUtils;

public class HelperUtil {

    public static String getRandomStringForGivenLength(int count) {
        return RandomStringUtils.randomAlphanumeric(count);
    }
}

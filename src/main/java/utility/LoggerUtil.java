package utility;

import org.apache.log4j.Logger;

public class LoggerUtil {

    private static Logger log = Logger.getLogger(LoggerUtil.class.getName());

    public static void startLog(String testClassName) {
        log.info(testClassName.concat(" Test is Starting"));
    }

    public static void endLog(String testClassName) {
        log.info(testClassName.concat(" Test is Ending"));
    }

    public static void info(String message) {
        log.info(message);
    }

}

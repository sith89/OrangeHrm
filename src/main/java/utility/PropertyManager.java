package utility;

import common.FilePath;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

    private PropertyManager(){}

    public static Properties loadData(){
        File file = new File(FilePath.PROPERTY_FILE_PATH);
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            LoggerUtil.info("No Config file is found "+e.getMessage());
        }
        Properties prop = new Properties();

        //load properties file
        try {
            prop.load(fileInput);
        } catch (IOException e) {
            LoggerUtil.info("Properties not loaded "+ e.getMessage());
        }

        return prop;

    }

}

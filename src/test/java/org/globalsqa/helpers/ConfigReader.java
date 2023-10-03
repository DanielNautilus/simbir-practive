package org.globalsqa.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    public static synchronized void loadConfig() {
        if (properties == null) {
            properties = new Properties();
            try {
                FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties");
                properties.load(fileInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getProperty(String key) {
        if (properties == null) {
            loadConfig();
        }
        return properties.getProperty(key);
    }
}

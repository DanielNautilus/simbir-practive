package org.globalsqa.helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverHelper {
    private static final ThreadLocal<WebDriver> driverThreadLocal = ThreadLocal.withInitial(DriverHelper::initDriver);

    private static synchronized WebDriver initDriver() {
        String browserDriver = ConfigReader.getProperty("browser.driver");
        WebDriver driver;
        switch (browserDriver.toLowerCase()) {
            case "chrome":
                WebDriverManager
                        .chromedriver()
                        .avoidResolutionCache()
                        .avoidResolutionCache()
                        .setup();
                ChromeOptions chromeOptions = createOptions();
                driver = new ChromeDriver(chromeOptions);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser driver: " + browserDriver);
        }
        driver.manage()
                .window()
                .maximize();
        driver.manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    private static ChromeOptions createOptions() {
        String options = ConfigReader.getProperty("browser.options");
        ChromeOptions chromeOptions = new ChromeOptions();
        if (options != null && !options.isEmpty()) {
            String[] optionList = options.split(",");
            for (String option : optionList) {
                chromeOptions.addArguments(option.trim());
            }
        }
        return chromeOptions;
    }

    public static void closeDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.close();
            driverThreadLocal.remove();
        }
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}

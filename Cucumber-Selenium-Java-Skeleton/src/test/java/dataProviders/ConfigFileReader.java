package dataProviders;

import enums.DriverType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class ConfigFileReader {
    private Properties properties;
    private static final String PROPERTY_FILE_PATH = "src/test/resources/configs/Configuration.properties";
    private static final String DRIVER_PATH_CMD_LINE_EXCEPTION = "Driver Path not specified in commandline parameter for the Key:driverPath";
    private static final String DRIVER_PATH_CONFIG_EXCEPTION = "Driver Path not specified in the Configuration.properties file for the Key:driverPath";
    private static final String BROWSER_NAME_CONFIG_EXCEPTION = "Browser Name Key value in Configuration.properties is not matched : ";
    private static final String BROWSER_NAME_CMD_LINE_EXCEPTION = "Browser Name Key value in the commandline parameter is not matched : ";

    private static final int DEFAULT_IMPLICIT_WAIT_SECONDS = 30;


    public ConfigFileReader() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PROPERTY_FILE_PATH))) {
            properties = new Properties();
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDriverPath() {
        String browserName = System.getProperty("browser");
        if (browserName == null) {
            return getDriverPathFromConfig();
        }
        return switch (browserName) {
            case "chrome" -> Optional.ofNullable(System.getProperty("chromeDriverPath")).orElseThrow(() -> new RuntimeException(DRIVER_PATH_CMD_LINE_EXCEPTION));
            case "firefox" -> Optional.ofNullable(System.getProperty("firefoxDriverPath")).orElseThrow(() -> new RuntimeException(DRIVER_PATH_CMD_LINE_EXCEPTION));
            case "edge" -> Optional.ofNullable(System.getProperty("edgeDriverPath")).orElseThrow(() -> new RuntimeException(DRIVER_PATH_CMD_LINE_EXCEPTION));
            case "safari" -> Optional.ofNullable(System.getProperty("safariDriverPath")).orElseThrow(() -> new RuntimeException(DRIVER_PATH_CMD_LINE_EXCEPTION));
            default -> throw new RuntimeException(BROWSER_NAME_CMD_LINE_EXCEPTION + browserName);
        };
    }

    private String getDriverPathFromConfig() {
        String browserName = properties.getProperty("browser");
        if (browserName == null || browserName.equals("chrome")) {
            return Optional.ofNullable(properties.getProperty("chromeDriverPath")).orElseThrow(() -> new RuntimeException(DRIVER_PATH_CONFIG_EXCEPTION));
        }
        return switch (browserName) {
            case "firefox" -> Optional.ofNullable(properties.getProperty("firefoxDriverPath")).orElseThrow(() -> new RuntimeException(DRIVER_PATH_CONFIG_EXCEPTION));
            case "edge" -> Optional.ofNullable(properties.getProperty("edgeDriverPath")).orElseThrow(() -> new RuntimeException(DRIVER_PATH_CONFIG_EXCEPTION));
            case "safari" -> Optional.ofNullable(properties.getProperty("safariDriverPath")).orElseThrow(() -> new RuntimeException(DRIVER_PATH_CONFIG_EXCEPTION));
            default -> throw new RuntimeException(BROWSER_NAME_CONFIG_EXCEPTION + browserName);
        };
    }

    public long getImplicitlyWait() {

        String implicitlyWait = properties.getProperty("implicitlyWait");
        if(implicitlyWait != null) {
            try{
                return Long.parseLong(implicitlyWait);
            }catch(NumberFormatException e) {
                throw new RuntimeException("Not able to parse value : " + implicitlyWait + " in to Long");
            }
        }
        else return DEFAULT_IMPLICIT_WAIT_SECONDS;
    }

    public String getApplicationUrl() {
        String url = System.getProperty("url");
        if(url == null) {
            return getApplicationUrlFromConfig();
        }
        else return url;
    }

    private String getApplicationUrlFromConfig() {
        String url = properties.getProperty("url");
        if (url == null) {
            throw new RuntimeException("Application Url not specified in the Configuration.properties file for the Key:url");
        }
        else return url;
    }

    public DriverType getBrowser() {
        String browserName = System.getProperty("browser");
        if (browserName == null) {
            return getBrowserFromConfig();
        }
        return switch (browserName) {
            case "chrome" -> DriverType.CHROME;
            case "firefox" -> DriverType.FIREFOX;
            case "edge" -> DriverType.EDGE;
            case "safari" -> DriverType.SAFARI;
            default -> throw new RuntimeException(BROWSER_NAME_CMD_LINE_EXCEPTION + System.getProperty("browser"));
        };
    }

    private DriverType getBrowserFromConfig() {
        String browserName = properties.getProperty("browser");
        if (browserName == null || browserName.equals("chrome")) return DriverType.CHROME;
        return switch (browserName) {
            case "firefox" -> DriverType.FIREFOX;
            case "edge" -> DriverType.EDGE;
            case "safari" -> DriverType.SAFARI;
            default -> throw new RuntimeException(BROWSER_NAME_CONFIG_EXCEPTION + browserName);
        };
    }

    public String getBrowserPath() {
        String browserName = System.getProperty("browser");
        if (browserName == null) {
            return getBrowserPathFromConfig();
        }
        return switch (browserName) {
            case "chrome" -> System.getProperty("chromeBrowserPath");
            case "firefox" -> System.getProperty("firefoxBrowserPath");
            case "edge" -> System.getProperty("edgeBrowserPath");
            case "safari" -> System.getProperty("safariBrowserPath");
            default -> throw new RuntimeException(BROWSER_NAME_CMD_LINE_EXCEPTION + browserName);
        };
    }

    private String getBrowserPathFromConfig() {
        String browserName = properties.getProperty("browser");
        if (browserName == null || browserName.equals("chrome")) {
            return properties.getProperty("chromeBrowserPath");
        }
        return switch (browserName) {
            case "firefox" -> properties.getProperty("firefoxBrowserPath");
            case "edge" -> properties.getProperty("edgeBrowserPath");
            case "safari" -> properties.getProperty("safariBrowserPath");
            default -> throw new RuntimeException(BROWSER_NAME_CONFIG_EXCEPTION + browserName);
        };
    }

    public Boolean isBrowserWindowMaximized() {
        String windowSize = properties.getProperty("windowMaximize");
        if(windowSize != null) {
            return Boolean.valueOf(windowSize);
        }
        else {
            return true;
        }
    }

    public Boolean getHeadlessFlag() {
        String headlessFlag = properties.getProperty("headlessMode");
        if (headlessFlag != null) {
            return Boolean.valueOf(headlessFlag);
        } else {
            return true;
        }
    }

}
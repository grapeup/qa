package dataProviders;

import enums.DriverType;
import enums.EnvironmentType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    private Properties properties;
    private final String propertyFilePath= "src/test/resources/configs/Configuration.properties";

    public ConfigFileReader(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public String getDriverPath(){
        String browserName = System.getProperty("browser");
        if(browserName == null)  {
            return getDriverPathFromConfig();
        }
        else if (browserName.equals("chrome")) {
            String driverPath = System.getProperty("chromeDriverPath");
            if(driverPath!= null) return driverPath;
            else throw new RuntimeException("Driver Path not specified in commandline parameter for the Key:driverPath");
        }
        else if(browserName.equals("firefox")) {
            String driverPath = System.getProperty("firefoxDriverPath");
            if(driverPath!= null) return driverPath;
            else throw new RuntimeException("Driver Path not specified in commandline parameter for the Key:driverPath");
        }
        else if(browserName.equals("edge")) {
            String driverPath = System.getProperty("edgeDriverPath");
            if(driverPath!= null) return driverPath;
            else throw new RuntimeException("Driver Path not specified in commandline parameter for the Key:driverPath");
        }
        else if (browserName.equals("safari")) {
            String driverPath = System.getProperty("safariDriverPath");
            if(driverPath!= null) return driverPath;
            else throw new RuntimeException("Driver Path not specified in commandline parameter for the Key:driverPath");
        }
        else throw new RuntimeException("Browser Name Key value in the commandline parameter is not matched : " + browserName);
    }

    private String getDriverPathFromConfig() {
        String browserName = properties.getProperty("browser");
        if(browserName == null || browserName.equals("chrome")) {
            String driverPath = properties.getProperty("chromeDriverPath");
            if(driverPath!= null) return driverPath;
            else throw new RuntimeException("Driver Path not specified in the Configuration.properties file for the Key:driverPath");
        }
        else if(browserName.equals("firefox")) {
            String driverPath = properties.getProperty("firefoxDriverPath");
            if(driverPath!= null) return driverPath;
            else throw new RuntimeException("Driver Path not specified in the Configuration.properties file for the Key:driverPath");
        }
        else if(browserName.equals("edge")) {
            String driverPath = properties.getProperty("edgeDriverPath");
            if(driverPath!= null) return driverPath;
            else throw new RuntimeException("Driver Path not specified in the Configuration.properties file for the Key:driverPath");
        }
        else if (browserName.equals("safari")) {
            String driverPath = properties.getProperty("safariDriverPath");
            if(driverPath!= null) return driverPath;
            else throw new RuntimeException("Driver Path not specified in the Configuration.properties file for the Key:driverPath");
        }
        else throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched : " + browserName);
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
        else return 30;
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
        if(browserName == null) {
            return getBrowserFromConfig();
        } else if(browserName.equals("chrome")) {
            return DriverType.CHROME;
        } else if(browserName.equalsIgnoreCase("firefox")) {
            return DriverType.FIREFOX;
        } else if(browserName.equals("edge")) {
            return DriverType.EDGE;
        } else if(browserName.equals("safari")) {
            return DriverType.SAFARI;
        }
        else throw new RuntimeException("Browser Name Key value in the command line parameter is not matched : " + System.getProperty("browser"));
    }

    private DriverType getBrowserFromConfig() {
        String browserName = properties.getProperty("browser");
        if(browserName == null || browserName.equals("chrome")) return DriverType.CHROME;
        else if(browserName.equalsIgnoreCase("firefox")) return DriverType.FIREFOX;
        else if(browserName.equals("edge")) return DriverType.EDGE;
        else if(browserName.equals("safari")) return DriverType.SAFARI;
        else throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched : " + browserName);
    }

    public String getBrowserPath() {
        String browserName = System.getProperty("browser");
        if(browserName == null) {
            return getBrowserPathFromConfig();
        }
        else if (browserName.equals("chrome")) {
            return System.getProperty("chromeBrowserPath");
        }
        else if(browserName.equals("firefox")) {
            return System.getProperty("firefoxBrowserPath");
        }
        else if(browserName.equals("edge")) {
            return System.getProperty("edgeBrowserPath");
        }
        else if(browserName.equals("safari")) {
            return System.getProperty("safariBrowserPath");
        }
        else throw new RuntimeException("Browser Name Key value in the commandline parameter is not matched : " + browserName);
    }

    private String getBrowserPathFromConfig() {
        String browserName = properties.getProperty("browser");
        if(browserName == null || browserName.equals("chrome")) return properties.getProperty("chromeBrowserPath");
        else if(browserName.equalsIgnoreCase("firefox")) return properties.getProperty("firefoxBrowserPath");
        else if(browserName.equals("edge")) return properties.getProperty("edgeBrowserPath");
        else if(browserName.equals("safari")) return properties.getProperty("safariBrowserPath");
        else throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched : " + browserName);
    }

    public EnvironmentType getEnvironment() {
        String environmentName = properties.getProperty("environment");
        if(environmentName == null || environmentName.equalsIgnoreCase("local")) return EnvironmentType.LOCAL;
        else if(environmentName.equals("remote")) return EnvironmentType.DEV;
        else throw new RuntimeException("Environment Type Key value in Configuration.properties is not matched : " + environmentName);
    }

    public Boolean getBrowserWindowSize() {
        String windowSize = properties.getProperty("windowMaximize");
        if(windowSize != null) {
            return Boolean.valueOf(windowSize);
        }
        else return true;
    }

}

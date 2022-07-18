package managers;

import enums.DriverType;
import enums.EnvironmentType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;



public class WebDriverManager {
    private static WebDriver driver;
    private static DriverType driverType;
    private static EnvironmentType environmentType;
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    private static final String FIREFOX_DRIVER_PROPERTY = "webdriver.gecko.driver";
    private static final String SAFARI_DRIVER_PROPERTY = "webdriver.safari.driver";
    private static final String EDGE_DRIVER_PROPERTY = "webdriver.edge.driver";

    public WebDriverManager() {
        driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
        environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
    }

    public WebDriver getDriver() {
        System.out.println("Getting driver");
        switch (environmentType) {
            case LOCAL : driver = createLocalDriver();
                break;
            case DEV : driver = createDevDriver();
                break;
        }
        return driver;
    }

    private WebDriver createDevDriver() {
        throw new RuntimeException("RemoteWebDriver is not yet implemented");
    }

    private WebDriver createLocalDriver() {
        switch (driverType) {
            case FIREFOX :
                System.setProperty(FIREFOX_DRIVER_PROPERTY, System.getProperty("user.dir") + FileReaderManager.getInstance().getConfigReader().getDriverPath());
                FirefoxOptions optionsFirefox = new FirefoxOptions();
                optionsFirefox.setBinary(FileReaderManager.getInstance().getConfigReader().getBrowserPath());
                optionsFirefox.addArguments("-height 1080");
                optionsFirefox.addArguments("-width 1920");
                optionsFirefox.addPreference("intl.accept_languages", "en-US");
                //optionsFirefox.addArguments("-headless");
                if (driver == null) {
                    driver = new FirefoxDriver(optionsFirefox);
                    System.out.println("Initiated Firefox driver");
                }else if (((FirefoxDriver) driver).getSessionId() == null) {
                    driver = new FirefoxDriver(optionsFirefox);
                    System.out.println("Initiated Firefox driver");
                }
                else {
                    System.out.println("Driver already initialized, skipping driver creation");
                }
                break;
            case CHROME :
                System.setProperty(CHROME_DRIVER_PROPERTY, System.getProperty("user.dir") + FileReaderManager.getInstance().getConfigReader().getDriverPath());
                ChromeOptions optionsChrome = new ChromeOptions();
                optionsChrome.addArguments("--window-size=1080,1920");
                optionsChrome.addArguments("--lang=en-us");
                //optionsChrome.addArguments("--headless");
                if (driver == null) {
                    driver = new ChromeDriver(optionsChrome);
                    System.out.println("Initiated Chrome driver");
                }else if (((ChromeDriver) driver).getSessionId() == null) {
                driver = new ChromeDriver(optionsChrome);
                System.out.println("Initiated Chrome driver");
            }
                else {
                System.out.println("Driver already initialized, skipping driver creation");
            }
            break;
            case EDGE :
                System.setProperty(EDGE_DRIVER_PROPERTY, System.getProperty("user.dir") + FileReaderManager.getInstance().getConfigReader().getDriverPath());
                EdgeOptions optionsEdge = new EdgeOptions();
                //optionsEdge.addArguments("--headless");
                optionsEdge.addArguments("--window-size=1080,1920");
                optionsEdge.addArguments("--lang=en-us");
                if (driver == null) {
                    driver = new EdgeDriver(optionsEdge);
                    System.out.println("Initiated Edge driver");
                }else if (((EdgeDriver) driver).getSessionId() == null) {
                    driver = new EdgeDriver(optionsEdge);
                    System.out.println("Initiated Edge driver");
                }
                else {
                    System.out.println("Driver already initialized, skipping driver creation");
                }
                break;
            case SAFARI :
                System.setProperty(SAFARI_DRIVER_PROPERTY, System.getProperty("user.dir") + FileReaderManager.getInstance().getConfigReader().getDriverPath());
                if (driver == null) {
                    driver = new SafariDriver();
                    System.out.println("Initiated Safari driver");
                }else if (((SafariDriver) driver).getSessionId() == null) {
                    driver = new SafariDriver();
                    System.out.println("Initiated Safari driver");
                }
                else {
                    System.out.println("Driver already initialized, skipping driver creation");
                }
                break;
        }

        if(FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize()) driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait()));
        return driver;

    }

    public static void closeDriver() {
        System.out.println("Driver teardown");
        driver.quit();
    }

}

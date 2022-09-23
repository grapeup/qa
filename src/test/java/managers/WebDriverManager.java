package managers;

import enums.DriverType;
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
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    private static final String FIREFOX_DRIVER_PROPERTY = "webdriver.gecko.driver";
    private static final String SAFARI_DRIVER_PROPERTY = "webdriver.safari.driver";
    private static final String EDGE_DRIVER_PROPERTY = "webdriver.edge.driver";
    private static final String headlessChromium = "--headless";
    private static final String headlessFirefox = "-headless";
    private static final String[] argumentsChromium = {"--window-size=1080,1920", "--lang=en-us"};
    private static final String[] argumentsFirefox = {"-height 1080", "-width 1920"};
    private static final String preferenceFirefox = "intl.accept_languages";
    private static final Object languageFirefox = "en-US";
    private static final String driverPath = System.getProperty("user.dir") + FileReaderManager.getInstance().getConfigReader().getDriverPath();

    public WebDriverManager() {
        driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
    }

    public static DriverType getDriverType(){
        return driverType;
    }
    public WebDriver getDriver() {
        switch (driverType) {
            case FIREFOX -> createFirefoxDriver();
            case CHROME -> createChromeDriver();
            case EDGE -> createEdgeDriver();
            case SAFARI -> createSafariDriver();
        }
        if(FileReaderManager.getInstance().getConfigReader().isBrowserWindowMaximized()) {
            driver.manage().window().maximize();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait()));
        return driver;
    }

    private void createFirefoxDriver() {
        System.setProperty(FIREFOX_DRIVER_PROPERTY, driverPath);
        FirefoxOptions optionsFirefox = new FirefoxOptions();
        optionsFirefox.setBinary(FileReaderManager.getInstance().getConfigReader().getBrowserPath());
        optionsFirefox.addArguments(argumentsFirefox);
        optionsFirefox.addPreference(preferenceFirefox, languageFirefox);
        if (FileReaderManager.getInstance().getConfigReader().getHeadlessFlag()) {
            optionsFirefox.addArguments(headlessFirefox);
        }
        if (driver == null || ((FirefoxDriver) driver).getSessionId() == null) {
            driver = new FirefoxDriver(optionsFirefox);
        }
    }

    private void createChromeDriver() {
        System.setProperty(CHROME_DRIVER_PROPERTY, driverPath);
        ChromeOptions optionsChrome = new ChromeOptions();
        optionsChrome.addArguments(argumentsChromium);
        if (FileReaderManager.getInstance().getConfigReader().getHeadlessFlag()) {
            optionsChrome.addArguments(headlessChromium);
        }
        if (driver == null || ((ChromeDriver) driver).getSessionId() == null) {
            driver = new ChromeDriver(optionsChrome);
        }
    }

    private void createEdgeDriver() {
        System.setProperty(EDGE_DRIVER_PROPERTY, System.getProperty("user.dir") + FileReaderManager.getInstance().getConfigReader().getDriverPath());
        EdgeOptions optionsEdge = new EdgeOptions();
        optionsEdge.addArguments(argumentsChromium);
        if (FileReaderManager.getInstance().getConfigReader().getHeadlessFlag()) {
            optionsEdge.addArguments(headlessChromium);
        }
        if (driver == null || ((EdgeDriver) driver).getSessionId() == null) {
            driver = new EdgeDriver(optionsEdge);
        }
    }

    private void createSafariDriver() {
        System.setProperty(SAFARI_DRIVER_PROPERTY, System.getProperty("user.dir") + FileReaderManager.getInstance().getConfigReader().getDriverPath());
        if (driver == null || ((SafariDriver) driver).getSessionId() == null) {
            driver = new SafariDriver();
        }
    }

    public static void closeDriver() {
        driver.quit();
    }

}

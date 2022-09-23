package helpers;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;



public class Helpers {

    private static WebDriver driver;

    public Helpers(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static boolean isVisible(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Throwable e) {
            return false;
        }
        return true;
    }

    public static boolean isInvisible(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (Throwable e) {
            return false;
        }
        return true;
    }

    public static boolean areVisible(WebElement... elements) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        } catch (Throwable e) {
            return false;
        }
        return true;
    }

    public static boolean areInvisible(WebElement... elements) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
        } catch (Throwable e) {
            return false;
        }
        return true;
    }

    public static void waitUntilVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    public static void waitUntilInvisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }


    public static void waitUntilClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void scrollToViewElement(WebElement element) {
        Actions a = new Actions(driver);
        a.scrollToElement(element);
    }

    public static void moveToElement(WebElement element) {
        Actions a = new Actions(driver);
        a.moveToElement(element).perform();
    }

    public static void scrollToSeeElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void openNewTab(){
        driver.switchTo().newWindow(WindowType.TAB);
    }

    public static void closeTab(){
        driver.close();
    }

    public static void switchTab(String string){
        driver.switchTo().window(string);
    }

    public static void switchToNewTab() {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
    }

    public static String getWindowHandle() {
        return driver.getWindowHandle();
    }

    public static String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public static String getPageTitle() {
        return driver.getTitle();
    }


}
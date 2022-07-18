package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class Helpers {

    private static WebDriver driver;
    public Helpers(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }




    
    public static boolean isPresent(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Throwable e) {
            return false;
        }
        return true;
    }
}

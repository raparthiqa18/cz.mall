package commonutils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class utilities {
    private static WebDriver driver;

     //To launch the browser
    public static WebDriver launchBrowser(){
        String browserType = System.getProperty("browser", "chrome");
        String headlessMode = System.getProperty("headless", "true");

        // Create WebDriver instance based on the browser type
        if (browserType.equalsIgnoreCase("chrome")) {
            ChromeOptions options=new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--start-maximized");
            options.addArguments("disable-infobars");
            options.addArguments("--disable-extensions");
            if(headlessMode.equalsIgnoreCase("true")){
                options.addArguments("--headless");
            }
            driver=new ChromeDriver(options);
        } else if (browserType.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserType.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Invalid browser type specified.");
        }
        return driver;
    }


    //Element exists in DOM
    public void scrolldownToCarousel(WebDriver driver, WebElement targetElement){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "arguments[0].scrollIntoView(true);";
        js.executeScript(script,  targetElement);
    }

    //To quit webdriver and close all browsers
    public static void releaseResources(WebDriver driver) {
        System.out.println("Releasing resources now.....");
        if (null != driver) {
            driver.close();
            driver.quit();
        }
    }
}

package pages;

import commonutils.utilities;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private utilities utils;

    //Locators
    private By shippingpricesLoc = By.xpath("//a/span[text()='Ceny dopravy']");
    private By carouselLoc = By.xpath(("//div[@class=\"cms-carousel-b__banner-wrapper\"]"));
    private By elementLoc = By.tagName("h3");
    private By footerLoc = By.xpath("//div[@class='footer__top__group']");


     public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.utils = new utilities();

    }

    //To check if the mall.cz page has loaded
    public Boolean isShipPricesDisplayed(){
        WebElement shippingpricesLocatorElement = wait.until(ExpectedConditions.presenceOfElementLocated(shippingpricesLoc));
        return shippingpricesLocatorElement.isDisplayed();
    }

    //To scroll down to footer
    public Boolean scrollPage(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean foundFooter = false;
        try{
            while (true) {
                js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
                if(isFooterPresent()){
                    foundFooter = true;
                    break;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return foundFooter;
    }

    //To find the no. of carousels
    public int getCarouselsCount(){
        int carouselsCount=0;
        List<WebElement> carousels = null;
        try{
            carousels = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(carouselLoc));
            carouselsCount = carousels.size();
            System.out.println("Final size of Carousels:  " + carouselsCount);
        }catch (Exception e){
            e.printStackTrace();
        }
        return carouselsCount;
    }

    //To get the list of carousel elements
    private List<WebElement> getCarousels(){
        int carouselsCount=0;
        List<WebElement> carousels = null;
        try{
            carousels = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(carouselLoc));
            carouselsCount = carousels.size();
            System.out.println("Final size of Carousels:  " + carouselsCount);
        }catch (Exception e){
            e.printStackTrace();
        }
        return carousels;
    }

    //To get the carousel vs elements count for each carousel
    public Map<Integer, Integer> getCarouselElementsCount(){
         List<WebElement> carousels = getCarousels();
        List<WebElement> carouselElements = null;
        Map<Integer, Integer> carouselElementMap = new HashMap<Integer, Integer>();//Key-Carousel_Number: Value-Carousel_Elements_Count
        try{
            for (int i=0; i<carousels.size();i++) {
                //To get the unique elements
                Set<String> elementTextSet  = new HashSet<>();
                carouselElements = carousels.get(i).findElements(elementLoc);
                for(int j=0; j<carouselElements.size();j++){
                    elementTextSet.add(carouselElements.get(j).getAttribute("title"));
                }
                if(elementTextSet.size()!=carouselElements.size()){
                    System.out.println("Elements present in Carousel does not match with number of unique elements");
                }
                carouselElementMap.put(i, elementTextSet.size());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return carouselElementMap;
    }

    //To check if footer is present
    private boolean isFooterPresent() {
        try {
            WebElement footer = wait.until(ExpectedConditions.presenceOfElementLocated(footerLoc));
            return footer.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }


}

package steps;

import commonutils.utilities;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.HomePage;
import org.openqa.selenium.WebDriver;
import java.util.Map;


public class HomePageStepDefs {
    private WebDriver driver;
    private HomePage homePage;
    private utilities utils;
    private Scenario scenario;

    @Before
    public void setup(Scenario scenario){
        driver = utils.launchBrowser();
        homePage = new HomePage(driver);
        this.scenario=scenario;
    }

    @Given("I am on the mall.cz home page")
    public void iAmOnMallCzHomepage() {
        driver.get("https://www.mall.cz/");
        System.out.println(driver.getTitle());
        Assert.assertTrue("Check whether home page is launched successfully", driver.getTitle().contains("MALL.CZ"));
//        Assert.assertTrue("Check whether home page is launched successfully", homePage.isShipPricesDisplayed());
    }

    @When("I scroll down to the carousels")
    public void iScrollDowntoCarousels(){
        Assert.assertTrue("Home Page scrolled to footer: ", homePage.scrollPage());
    }

    @Then("I should see unique {int} elements for each carousel")
    public void iShouldSeeUniqueElementsForCarousel(int elementsCount) {
        Map<Integer, Integer> carouselElementMap = homePage.getCarouselElementsCount();
        boolean hasFailures = false;
        if(carouselElementMap.size()!=0){
            for(Map.Entry<Integer, Integer> entry: carouselElementMap.entrySet()){
                scenario.log("Carousel " + entry.getKey() + " has " + entry.getValue() +" Elements");
                try{
                    Assert.assertEquals("Check Carousel " + entry.getKey() + " contains 15 elements", 15, entry.getValue().intValue());
                }catch (AssertionError e) {
                    // Log the failure without throwing an exception
                    scenario.log("Assertion failed: " + e.getMessage());
                    hasFailures = true;
                }
            }
        }else{
            Assert.assertFalse("Carousels found in the homepage:" , carouselElementMap.size()==0);
        }

        if (hasFailures) {
            throw new AssertionError("One or more assertions failed.");
        }
    }

    @After
    public void cleanup(){
        utils.releaseResources(driver);
    }
}

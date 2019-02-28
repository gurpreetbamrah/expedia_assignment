package scenariosteps;

import cucumber.api.java.en.Then;
import model.locatorhandler.WebElementLocator;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.By;

public class FlightSearchPageSteps {


    @Steps
    EndUserSteps endUserSteps;

    WebElementLocator webElementLocator = new WebElementLocator("flightsearchpage");
    By flightpricelist = webElementLocator.findElementBySelector("flightpricelist");
    By getflightprice = webElementLocator.findElementBySelector("getflightprice");
    By noThanks = webElementLocator.findElementBySelector("noThanks");
    By firstflight = webElementLocator.findElementBySelector("firstflight");


    @Then("user should see list of flights with the cheapest on the top")
    public void userShouldSeeListOfFlightsWithCheapestOnTop() {
        endUserSteps.userShouldSeeListOfFlightsWithCheapestOnTop(flightpricelist, getflightprice);
    }

    @Then("I select the cheapest flight")
    public void selectcheapestflight() {
        endUserSteps.selectFirstFlight(firstflight);
        endUserSteps.clickNoThanks(noThanks);
    }

}

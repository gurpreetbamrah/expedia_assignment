package scenariosteps;

import cucumber.api.java.en.Then;
import model.locatorhandler.WebElementLocator;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.By;

public class FlightInformationPageSteps {

    @Steps
    EndUserSteps endUserSteps;

    WebElementLocator webElementLocator = new WebElementLocator("flightinformationpage");
    By packagePriceTotal = webElementLocator.findElementBySelector("packagePriceTotal");

    @Then("I verify the price under trip summary")
    public void verifyPriceUnderTripSummary() {
        endUserSteps.verifyPriceUnderTripSummary(packagePriceTotal);
    }


}

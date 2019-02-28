package scenariosteps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import model.locatorhandler.WebElementLocator;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.By;

public class HomePageSteps {


    @Steps
    EndUserSteps endUserSteps;

    WebElementLocator webElementLocator = new WebElementLocator("homepage");
    By flighticon = webElementLocator.findElementBySelector("flighticon");
    By oneway = webElementLocator.findElementBySelector("oneway");
    By flightfrom = webElementLocator.findElementBySelector("flightfrom");
    By flightto = webElementLocator.findElementBySelector("flightTo");
    By selectDate = webElementLocator.findElementBySelector("selectDate");
    By searchButton = webElementLocator.findElementBySelector("searchButton");
    By closeDatePicker = webElementLocator.findElementBySelector("closeDatePicker");

    @Given("^the user is on the expedia website home page$")
    public void givenTheUserIsOnTheHomePage() {
        endUserSteps.open_page();
    }

    @Then("user clicks on flight icon")
    public void clickOnFlightIcon() {
            endUserSteps.clickOnFlightIcon(flighticon);
    }


    @Then("user enter the source ([^\\\"]*)")
    public void userSelectSource(String source) {
        endUserSteps.userSelectSource(flightfrom, source);
    }

    @Then("user enter the destination ([^\\\"]*)")
    public void userSelectDestination(String destination) {
        endUserSteps.userSelectDestination(flightto, destination);
    }

    @Then("user selects the date of journey")
    public void userSelectDate() {
        endUserSteps.selectDate(selectDate);
        endUserSteps.closeDatePicker(closeDatePicker);
    }

    @Then("user select flight type as one way")
    public void selectFlightType() {
        endUserSteps.selectFlightType(oneway);
    }

    @Then("user clicks on search button")
    public void clickOnSearchButton() {
        endUserSteps.clickOnSearchButton(searchButton);
    }


}

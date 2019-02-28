package scenariosteps;

import model.uicomponenthandler.OpenPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class EndUserSteps extends ScenarioSteps {

    public static int expectedprice;

    OpenPage openPage;


    public void open_page() {
        openPage.open();
    }

    public void clickOnFlightIcon(By flighticon) {
        openPage.clickOn(getDriver().findElement(flighticon));
    }

    public void selectFlightType(By oneway) {
        openPage.clickOn(getDriver().findElement(oneway));
    }

    public void userSelectSource(By flightfrom, String source) {
        openPage.typeInto(getDriver().findElement(flightfrom), source);

    }

    public void userSelectDestination(By flightto, String destination) {
        openPage.typeInto(getDriver().findElement(flightto), destination);
    }

    public void selectDate(By selectDate) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        openPage.typeInto(getDriver().findElement(selectDate), dateFormat.format(date));
    }

    public void clickOnSearchButton(By searchButton) {
        openPage.clickOn(getDriver().findElement(searchButton));
    }

    public void userShouldSeeListOfFlightsWithCheapestOnTop(By flightpricelist, By cheapestflight) {
        List<WebElement> ls = openPage.thenReturnElementList(flightpricelist);
        List<Integer> pricelist = new ArrayList<>();
        for (WebElement element : ls) {
            int price = Integer.parseInt(element.getText().replaceAll("[^0-9]", ""));
            pricelist.add(price);
        }
        expectedprice = Collections.min(pricelist);
        int actualprice = Integer.parseInt(openPage.getDriver().findElement(cheapestflight).getText().replaceAll("[^0-9]", ""));
        assertTrue(expectedprice == actualprice);

    }

    public void verifyPriceUnderTripSummary(By packagePriceTotal) {
        String winHandleBefore = openPage.getDriver().getWindowHandle();

        for (String winHandle : openPage.getDriver().getWindowHandles()) {
            openPage.getDriver().switchTo().window(winHandle);
        }
        String getPackagePrice = openPage.getDriver().findElement(packagePriceTotal).getText();
        String packagePrice = getPackagePrice.replaceAll("Rs", "");
        String var = packagePrice.replaceAll(",", "");
        int actualprice = Math.round(Float.parseFloat(var));
        assertTrue(expectedprice == actualprice);
        getDriver().close();
        getDriver().switchTo().window(winHandleBefore);

    }

    public void clickNoThanks(By noThanks) {
        WebElement element = getDriver().findElement(noThanks);
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click();", element);

    }

    public void closeDatePicker(By closeDatePicker) {
        openPage.clickOn(getDriver().findElement(closeDatePicker));
    }

    public void selectFirstFlight(By firstflight) {
        openPage.clickOn(getDriver().findElement(firstflight));
    }
}

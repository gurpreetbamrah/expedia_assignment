package model.locatorhandler;
import net.thucydides.core.pages.Pages;
import org.openqa.selenium.By;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class WebElementLocator {

    private Properties prop = new Properties();

    private static String LOCATORS_LOCATION = "/properties/";

    public WebElementLocator(String fileName) {
        try {

            prop.load(Pages.class.getResourceAsStream(LOCATORS_LOCATION + fileName + ".properties"));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getIdentifier(String Identifier) {
        String identifier = prop.getProperty(Identifier);
        return identifier;
    }

    public By findElementBySelector(String ElementName) {
        By locateElement = null;
        String locator = prop.getProperty(ElementName);
// Split the value which contains locator type and locator value
        String locatorType = locator.split("-->")[0];
        String locatorValue = locator.split("-->")[1];
        switch (locatorType) {
            case "xpath":
                locateElement = By.xpath(locatorValue);
                break;
            case "id":
                locateElement = By.id(locatorValue);
                break;
            case "name":
                locateElement = By.name(locatorValue);
                break;
            case "classname":
                locateElement = By.className(locatorValue);
            case "css":
                locateElement = By.cssSelector(locatorValue);
                break;
            case "linktext":
                locateElement = By.linkText(locatorValue);
                break;
            case "partiallinktext":
                locateElement = By.partialLinkText(locatorValue);
                break;
            case "tagname":
                locateElement = By.tagName(locatorValue);
                break;
            default:
                System.out.println("Locator not configured properly");
        }
        return locateElement;

    }
}
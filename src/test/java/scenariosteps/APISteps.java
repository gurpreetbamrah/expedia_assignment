package scenariosteps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import model.api.JsonHandler;
import model.api.RequestModel;
import net.thucydides.core.annotations.Steps;
import java.io.IOException;
import java.util.Properties;

import static junit.framework.TestCase.assertTrue;


public class APISteps {

    @Steps
    RequestModel requestModel;

    @Steps
    JsonHandler jsonHandler;

    static String getHttpResponse;
    static String postHttpResponse;

    private Properties prop = new Properties();
    private static String LOCATORS_LOCATION = "/properties/";


    public APISteps() throws IOException {
        prop.load(APISteps.class.getResourceAsStream(LOCATORS_LOCATION + "common.properties"));
    }


    @Given("I send http query request using ([^\\\"]*) ([^\\\"]*) and ([^\\\"]*) as ([^\\\"]*)$")
    public void whenISendHTTPRequest(String url, String resource, String param, String value) throws Exception {
        requestModel.whenISendHTTPRequest(prop.getProperty(url), resource, param, value);

    }

    @Then("I validate ([^\\\"]*) values as ([^\\\"]*) in response of webservice")
    public void thenIVerifyFieldValuesInGetRequestResponse(String fields, String values) throws Exception {
        String[] fieldArray = fields.split("__");
        String[] valArray = values.split("__");
        if (valArray.length == fieldArray.length) {
            for (int i = 0; i < valArray.length; i++) {
                assertTrue(jsonHandler.isFieldValuePresentInJsonArray(RequestModel.httpResponse, fieldArray[i], valArray[i]));
            }
        }

    }

    @Then("I count ([^\\\"]*) occurrence in response of webservice as (\\d+)$")
    public void thenICountOccurranceOfFieldInResponse(String fieldToCheck, int expValue) throws Exception {
        jsonHandler.countOccurranceOfFieldInResponse(RequestModel.httpResponse, fieldToCheck, expValue);

    }


    @Given("I send http post request using ([^\\\"]*) ([^\\\"]*) and ([^\\\"]*) ([^\\\"]*)$")
    public void whenISendHTTPPostRequest(String uri, String jsonbody, String resource, String header) throws Exception {
        postHttpResponse = requestModel.sendPost(prop.getProperty(uri), jsonbody, resource, header);

    }


    @Given("I send http get request using ([^\\\"]*) ([^\\\"]*) and ([^\\\"]*) ([^\\\"]*)$")
    public void whenISendHTTPGetRequest(String uri, String resource, String headerkey, String headervalue) throws Exception {
        getHttpResponse = requestModel.sendGet(prop.getProperty(uri), resource, headerkey, headervalue);
    }


}
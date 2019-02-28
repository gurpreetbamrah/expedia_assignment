package scenariosteps;

import cucumber.api.java.en.Given;
import model.dbutil.DBModel;
import net.thucydides.core.annotations.Steps;

import java.io.IOException;
import java.util.Properties;

public class DBSteps {

    @Steps
    DBModel dbModel;
    private Properties prop = new Properties();
    private static String LOCATORS_LOCATION = "/properties/";


    public DBSteps() throws IOException {
        prop.load(APISteps.class.getResourceAsStream(LOCATORS_LOCATION + "common.properties"));
    }

    @Given("user connects to DB")
    public void userConnectsToDB() throws Exception {
        dbModel.connectToDB(prop.getProperty("dbserverurl"), prop.getProperty("dbusername"), prop.getProperty("dbpassword"));

    }

    @Given("user execute the following query ([^\\\"]*)")
    public void executeQuery(String fileName) throws Exception {
        dbModel.executeQuery(fileName);

    }

    @Given("I verify the result count as ([^\\\"]*)")
    public void verifyresultCount(String count) throws Exception {
        dbModel.verifyresultCount((count));

    }
}

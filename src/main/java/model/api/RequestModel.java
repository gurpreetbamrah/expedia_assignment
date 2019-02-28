package model.api;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import static io.restassured.RestAssured.given;


public class RequestModel extends ScenarioSteps {

    public static String httpResponse = "";

/* This class contains methods for sending the HTTP request"*/

    @Step
    public String whenISendHTTPRequest(String Requesturl, String resource, String param, String value) throws IOException {
        String URLF = Requesturl + resource + param + value;
        URL url = new URL(URLF.trim());
        System.out.println(url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Error code:" + conn.getResponseCode());
        }
        Scanner scan = new Scanner(url.openStream());
        httpResponse = new String();
        while (scan.hasNext())
            httpResponse += scan.nextLine();
        scan.close();
        conn.disconnect();
        return httpResponse;

    }


    @Step
    public String sendGet(String baseURL, String resource, String headerkey, String headervalue) {
        RestAssured.baseURI = baseURL;
        Response response =
                given().header(headerkey, headervalue)
                        .when().get(resource)
                        .then().assertThat().statusCode(200).and().contentType(ContentType.JSON).extract().response();
        return response.asString();
    }


    @Step
    public String sendPost(String baseURL, String jsonbody, String resource, String header) {
        RestAssured.baseURI = baseURL;
        Response response = given().
                body(jsonbody)
                .header("Content-Type", header).
                        when().
                        post(resource).
                        then().assertThat().statusCode(200).and().extract().response();
        return response.asString();

    }

}

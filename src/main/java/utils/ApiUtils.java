package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ApiUtils {

    // Set up base URI from configuration or environment variable
    public static void setBaseUri(String baseUri) {
        RestAssured.baseURI = baseUri;
    }

    // Send a GET request
    public static Response sendGetRequest(String endpoint) {
        return RestAssured.get(endpoint);
    }

    // Validate status code
    public static void validateStatusCode(Response response, int expectedStatusCode) {
        assertThat("Unexpected status code", response.statusCode(), is(expectedStatusCode));
    }

    // Validate a field is not empty
    public static void validateNotEmptyField(Response response, String fieldPath) {
        String field = response.jsonPath().getString(fieldPath);
        assertThat("Field " + fieldPath + " should not be empty", field, not(""));
    }

    // Validate a field contains a positive number
    public static void validatePositiveNumber(Response response, String fieldPath) {
        int number = response.jsonPath().getInt(fieldPath);
        assertThat("Field " + fieldPath + " should be a positive number", number, greaterThan(0));
    }

    // Validate a field is a valid email
    public static void validateEmailField(Response response, String fieldPath) {
        String email = response.jsonPath().getString(fieldPath);
        assertThat("Field " + fieldPath + " should be a valid email", email, matchesPattern("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"));
    }

    // Validate a field is not null
    public static void validateNotNullField(Response response, String fieldPath) {
        String field = response.jsonPath().getString(fieldPath);
        assertThat("Field " + fieldPath + " should not be null", field, notNullValue());
    }
}

package tests;

import config.ConfigLoader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static utils.ApiUtils.*;

public class ApiTests {

    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeAll
    public static void setup() {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(ConfigLoader.getReportLocation()); // Output file location
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);  // Attach reporter to the extent report

        setBaseUri(ConfigLoader.getBaseUrl());
    }

    @Test
    @DisplayName("Validate /posts/1 Endpoint")
    public void validatePostEndpoint() {
        test = extent.createTest("Validate Posts Endpoint");
        Response response = sendGetRequest("/posts/1");

        validateStatusCode(response, 200);
        validateNotEmptyField(response, "title");
        validatePositiveNumber(response, "userId");
        test.pass("Posts endpoint passed, response: " + response.getBody().asString());
    }

    @Test
    @DisplayName("Validate /users/1 Endpoint")
    public void validateUserEndpoint() {
        test = extent.createTest("Validate Users Endpoint");
        Response response = sendGetRequest("/users/1");

        validateStatusCode(response, 200);
        validateEmailField(response, "email");
        validateNotNullField(response, "address.city");

        test.pass("Users endpoint passed, response: " + response.getBody().asString());
    }

    @AfterEach
    public void tearDown() {
        extent.flush();  // Flush the report to file
    }
}

package com.apiautomation.base;

import com.apiautomation.actions.AssertActions;
import com.apiautomation.endpoints.APIConstants;
import com.apiautomation.modules.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    //Base Test (Father) --> Testcase (Son) --> Single Inheritance

    public RequestSpecification requestSpecification;
    public AssertActions assertActions;
    public JsonPath jsonPath;
    public PayloadManager payloadManager;
    public Response response;
    public ValidatableResponse validatableResponse;


    @BeforeTest
    public void setConfig(){
        System.out.println("Before Test");
        payloadManager = new PayloadManager();
        assertActions = new AssertActions();
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(APIConstants.BASE_URL)
                .addHeader("Content-Type", "application/json")
                .build().log().all();

//        requestSpecification = RestAssured
//                .given()
//                .baseUri(APIConstants.BASE_URL)
//                .contentType(ContentType.JSON)
//                .log().all();
//

    }

    public String getToken(){
        return null;
    }
}

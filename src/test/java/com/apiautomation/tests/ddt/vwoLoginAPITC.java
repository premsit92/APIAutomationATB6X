package com.apiautomation.tests.ddt;

import com.apiautomation.pojos.VWOLoginPOJO;
import com.apiautomation.utils.UtilsExcel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.regex.Matcher;

public class vwoLoginAPITC {

    RequestSpecification r2;
    ValidatableResponse vR2;
    Integer ID;
    Response res2;

    @Test (dataProvider = "getData", dataProviderClass = UtilsExcel.class)
    public void vwoLoginAPI(String username, String password){

        System.out.println("--Login API Testing");
        System.out.println(username);
        System.out.println(password);

        //Payload
        VWOLoginPOJO vwoLoginPOJO = new VWOLoginPOJO();
        vwoLoginPOJO.setUsername(username);
        vwoLoginPOJO.setPassword(password);
        vwoLoginPOJO.setRemember(false);
        vwoLoginPOJO.setRecaptchaResponseField("");



        //RestAssured Code
        r2 = RestAssured.given();
        r2.baseUri("https://app.vwo.com");
        r2.basePath("/login");
        r2.contentType("application/json");
        r2.body(vwoLoginPOJO).log().all();

        res2 = r2.when().post();
        vR2 = res2.then();

        String resString = res2.asString();
        System.out.println(resString);
      //  vR2.body("id", Matchers.notNullValue());
        vR2.statusCode(401);

        //AssertJ



    }
}

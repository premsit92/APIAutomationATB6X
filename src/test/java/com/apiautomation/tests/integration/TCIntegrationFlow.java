package com.apiautomation.tests.integration;

import com.apiautomation.base.BaseTest;
import com.apiautomation.endpoints.APIConstants;
import com.apiautomation.pojos.Booking;
import com.apiautomation.pojos.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TCIntegrationFlow extends BaseTest {

    //Create a Booking, Create a Token
    //Get Booking details
    //Update the booking
    // Delete the Booking


    @Test(groups = "integration", priority = 1)
    @Owner("Prem")
    @Description ("TC#INT1 - Step 1. Verify that Booking can be Created" )
    public void testCreateBooking(ITestContext iTestContext){
        iTestContext.setAttribute("token",getToken());
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification)
                .when()
                .body(payloadManager.createPayloadBookingAsString()).post();
        validatableResponse = response.then().log().all();

        validatableResponse.statusCode(200);
        //Deserialize
        BookingResponse bookingResponse = payloadManager.bookingResponseJava((response.asString()));
        //Assertj
        assertThat(bookingResponse.getBookingid()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotNull().isNotEmpty();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("John");

        //Set Booking Id
        iTestContext.setAttribute("bookingid",bookingResponse.getBookingid());



    }

    @Test(groups = "integration", priority = 2)
    @Owner("Prem")
    @Description ("TC#INT1 - Step 2. Verify that the Booking by ID" )
    public void testVerifyBookingID(ITestContext iTestContext){
        //GET Req
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        String basePathGET = APIConstants.CREATE_UPDATE_BOOKING_URL+"/" +bookingid;
        System.out.println(basePathGET);
        requestSpecification.basePath(basePathGET);
        response = RestAssured.given(requestSpecification)
                .when().get();
        validatableResponse = response.then().log().all();

        validatableResponse.statusCode(200);

//        System.out.println("Booking ID - "+iTestContext.getAttribute("bookingid"));
//        System.out.println("Token - "+iTestContext.getAttribute("token"));

        Booking booking = payloadManager.bookingResponseFromJSON(response.asString());

        assertThat(booking.getFirstname()).isNotNull().isNotEmpty();
        assertThat(booking.getFirstname()).isEqualTo("John");


    }

    @Test(groups = "integration", priority = 3)
    @Owner("Prem")
    @Description ("TC#INT1 - Step 3. Verify Updated Booking by ID" )
    public void testUpdateBookingByID(ITestContext iTestContext){
       // System.out.println("Token - "+iTestContext.getAttribute("token"));
        String token = iTestContext.getAttribute("token").toString();
        //put or patch
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String basePathPUTPATCH = APIConstants.CREATE_UPDATE_BOOKING_URL+"/" +bookingid;

        requestSpecification.basePath(basePathPUTPATCH);
        response = RestAssured.given(requestSpecification).cookie("token", token)
                .when().body(payloadManager.fullupdatePayloadAsString()).put();
        validatableResponse = response.then().log().all();

        validatableResponse.statusCode(200);

//        System.out.println("Booking ID - "+iTestContext.getAttribute("bookingid"));
//        System.out.println("Token - "+iTestContext.getAttribute("token"));

        Booking booking = payloadManager.bookingResponseFromJSON(response.asString());

        assertThat(booking.getFirstname()).isNotNull().isNotEmpty();
        assertThat(booking.getFirstname()).isEqualTo("James");
        assertThat(booking.getLastname()).isEqualTo("Dutta");

        Assert.assertTrue(true);
    }

    @Test(groups = "integration", priority = 4)
    @Owner("Prem")
    @Description ("TC#INT1 - Step 4. Delete the Booking by ID" )
    public void testDeleteBookingByID(ITestContext iTestContext){
       // System.out.println("Token - "+iTestContext.getAttribute("token"));
        String token = iTestContext.getAttribute("token").toString();
        //put or patch
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String basePathDELETE = APIConstants.CREATE_UPDATE_BOOKING_URL+"/" +bookingid;

        requestSpecification.basePath(basePathDELETE).cookie("token", token);
        response = RestAssured.given(requestSpecification).spec(requestSpecification)
                .when().delete();
        validatableResponse = response.then().log().all();

        validatableResponse.statusCode(201);

//        System.out.println("Booking ID - "+iTestContext.getAttribute("bookingid"));
//        System.out.println("Token - "+iTestContext.getAttribute("token"));

//        Booking booking = payloadManager.bookingResponseFromJSON(response.asString());
//
//        assertThat(booking.getFirstname()).isNotNull().isNotEmpty();
//        assertThat(booking.getFirstname()).isEqualTo("James");
//        assertThat(booking.getLastname()).isEqualTo("Dutta");
//
//        Assert.assertTrue(true);
    }
}

package com.apiautomation.tests.crud.postTC;

import com.apiautomation.base.BaseTest;
import com.apiautomation.endpoints.APIConstants;
import com.apiautomation.pojos.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class testCreateBookingPOST extends BaseTest {

    @Test (groups = "smoke")
    @Owner("Prem")
    @Severity(SeverityLevel.NORMAL)
    @Description("TC#1 - verify that the Booking can be Created")
    public void testCreateBooking(){
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured
                .given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString()).post();

        validatableResponse = response.then().log().all();

        //Validatable Assertion
        validatableResponse.statusCode(200);
        //Deserialize
        BookingResponse bookingResponse = payloadManager.bookingResponseJava((response.asString()));
        //Assertj
        assertThat(bookingResponse.getBookingid()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotNull().isNotEmpty();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("John");

        //TestNG Assertion
        assertActions.verifyStatusCode(response);

    }
}

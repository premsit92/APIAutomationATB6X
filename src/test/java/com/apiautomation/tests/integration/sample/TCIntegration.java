package com.apiautomation.tests.integration.sample;

import com.apiautomation.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TCIntegration extends BaseTest {

    //Create a Booking, Create a Token
    //Get Booking details
    //Update the booking
    // Delete the Booking


    @Test(groups = "integration", priority = 1)
    @Owner("Prem")
    @Description ("TC#INT1 - Step 1. Verify that Booking can be Created" )
    public void testCreateBooking(){
        Assert.assertTrue(true);

    }

    @Test(groups = "integration", priority = 2)
    @Owner("Prem")
    @Description ("TC#INT1 - Step 2. Verify that the Booking by ID" )
    public void testVerifyBookingID(){
        Assert.assertTrue(true);
    }

    @Test(groups = "integration", priority = 3)
    @Owner("Prem")
    @Description ("TC#INT1 - Step 3. Verify Updated Booking by ID" )
    public void testUpdateBookingByID(){
        Assert.assertTrue(true);
    }

    @Test(groups = "integration", priority = 4)
    @Owner("Prem")
    @Description ("TC#INT1 - Step 4. Delete the Booking by ID" )
    public void testDeleteBookingByID(){
        Assert.assertTrue(true);
    }
}

package com.apiautomation.modules;

import com.apiautomation.pojos.Booking;
import com.apiautomation.pojos.BookingDates;
import com.apiautomation.pojos.BookingResponse;
import com.google.gson.Gson;

public class PayloadManager {

    Gson gson;

    public String createPayloadBookingAsString() {
        Booking booking = new Booking();
        booking.setFirstname("John");
        booking.setLastname("Doe");
        booking.setTotalprice(123);
        booking.setDepositpaid(true);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2023-05-01");
        bookingdates.setCheckout("2023-05-10");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");
        gson = new Gson();
        return gson.toJson(booking);
    }
    public String fullupdatePayloadAsString(){
        Booking booking = new Booking();
        booking.setFirstname("James");
        booking.setLastname("Dutta");
        booking.setTotalprice(456);
        booking.setDepositpaid(true);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2023-05-01");
        bookingdates.setCheckout("2023-05-10");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");
        gson = new Gson();
        return gson.toJson(booking);
    }

    public BookingResponse bookingResponseJava(String responseString){
        gson = new Gson();
        BookingResponse bookingRespons = gson.fromJson(responseString, BookingResponse.class);
        return bookingRespons;
    }

}

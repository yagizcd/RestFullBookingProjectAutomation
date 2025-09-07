package utils;

import com.github.javafaker.Faker;
import models.Booking;
import models.BookingDates;

import java.util.HashMap;

public class TestFactory {

    private static final Faker faker = new Faker();

    public String getFakeName(){
        return faker.name().firstName();
    }

    public String getLastName(){
        return faker.name().lastName();
    }

    public String getTotalPrice(int digit){
        return faker.number().digits(digit);
    }

    public Boolean getRandomBoolean(){
        return faker.bool().bool();
    }

    public String getANeed(){
        return faker.food().sushi();
    }

    public int getTotalPriceLower(int maxPrice){
        return faker.number().numberBetween(0,maxPrice);
    }


    public Booking returnBody() {

        BookingDates dates = new BookingDates("2025-08-28", "2025-09-05");

        int getFakeTotalPrice = Integer.parseInt(getTotalPrice(3));

        Booking bookingPayload = new Booking();

        bookingPayload.setFirstname(getFakeName());
        bookingPayload.setLastname(getLastName());
        bookingPayload.setTotalprice(getFakeTotalPrice);
        bookingPayload.setDepositpaid(getRandomBoolean());
        bookingPayload.setBookingdates(dates);
        bookingPayload.setAdditionalneeds(getANeed());

        return bookingPayload;
    }



}

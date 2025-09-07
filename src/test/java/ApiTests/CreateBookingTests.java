package ApiTests;

import io.restassured.response.Response;
import models.Booking;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.TestFactory;

import java.util.HashMap;


public class CreateBookingTests extends BaseTest{
    final TestFactory fakeParameters = new TestFactory();
    @Test
    public void PostBookingTest(){
        Booking body = fakeParameters.returnBody();

        Response createBookingResponse = createBooking(body);


        createBookingResponse
                .then()
                .log().all()
                .statusCode(200);

        Assertions.assertNotNull(createBookingResponse.jsonPath().getJsonObject("bookingid"));


    }
}

package ApiTests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Booking;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.TestFactory;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class UpdateBookingTests extends BaseTest {
TestFactory fakeParameters = new TestFactory();
    @Test
    public void updateBooking(){
        Booking body = fakeParameters.returnBody();

        Response createBookingRepsonse =  createBooking(body);
        int bookingId = createBookingRepsonse.jsonPath().getInt("bookingid");

        String token = createToken();

        String newName = fakeParameters.getFakeName();
        body.setFirstname(newName);

        Response updateResponse = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie","token="+token)
                .body(body)
                .when()
                .put("booking/"+bookingId);

        updateResponse.prettyPrint();

        Assertions.assertEquals(newName, updateResponse.jsonPath().getJsonObject("firstname"));


    }

}

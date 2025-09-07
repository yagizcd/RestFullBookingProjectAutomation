package ApiTests;

import io.restassured.response.Response;
import models.Booking;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import utils.TestFactory;
import static io.restassured.RestAssured.given;

public class GetBookingTests extends BaseTest {
    TestFactory fakeParameters = new TestFactory();
    Booking body;
    @Test
    public void getAllBokingTest(){
        given(spec)
                .when().get("booking")
                .then()
                .statusCode(200);

    }
    @Test
    public void getBookingByIdTest(){
        this.body = fakeParameters.returnBody();
        int bookingId = createBooking(this.body).jsonPath().getJsonObject("bookingid");

        Response response = given(spec)
                .when().get("booking/{0}",bookingId);

        response
                .then()
                .statusCode(HttpStatus.SC_OK);

        Assertions.assertNotNull(response.jsonPath().getJsonObject("firstname"));

    }
    @Test
    public void getBookingByNameTest(){
        this.body = fakeParameters.returnBody();

        Response createBookingResponse = createBooking(this.body);

        int bookingId = createBookingResponse.jsonPath().getInt("bookingid");
        String requestFirstName = this.body.getFirstname();
        String requestLastName = this.body.getLastname();

        Response getByNameResponse = given(spec)
                .queryParams("firstname",requestFirstName)
                .queryParams("lastname",requestLastName)
                .when()
                .get("booking");

        Assertions.assertEquals(bookingId, getByNameResponse.jsonPath().getInt("[0].bookingid"));
    }
}

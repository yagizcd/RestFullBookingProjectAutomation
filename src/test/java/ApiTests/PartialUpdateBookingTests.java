package ApiTests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Booking;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.TestFactory;

import static io.restassured.RestAssured.given;

public class PartialUpdateBookingTests extends BaseTest {
TestFactory fakeParameters;

    @Test
    public void partiallyUpdateBookingTest() {
        fakeParameters = new TestFactory();
        Booking body = fakeParameters.returnBody();

        String token = createToken();

        Response createBookingResponse = createBooking(body);

        int bookingId = createBookingResponse.jsonPath().getJsonObject("bookingid");

        System.out.println("this a createBookingResponse");

        createBookingResponse.prettyPrint();

        String getRandomNewName = fakeParameters.getFakeName();
        String lastName = fakeParameters.getLastName();

        JSONObject partiallyUpdateBody = new JSONObject();
        partiallyUpdateBody.put("firstname",getRandomNewName);
        partiallyUpdateBody.put("lastname",lastName);



        Response partiallyUpdateReponse = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie","token="+token)
                .body(partiallyUpdateBody.toString())
                .when()
                .patch("/booking/"+bookingId);

        Assertions.assertEquals(getRandomNewName, partiallyUpdateReponse.jsonPath().getJsonObject("firstname"));
        Assertions.assertEquals(lastName, partiallyUpdateReponse.jsonPath().getJsonObject("lastname"));

    }

}

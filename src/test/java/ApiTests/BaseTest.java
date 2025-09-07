package ApiTests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Booking;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import utils.TestFactory;

import java.util.Arrays;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class BaseTest {
    TestFactory factory = new TestFactory();
    protected String BaseUrl = "https://restful-booker.herokuapp.com/";
    RequestSpecification spec;

    @BeforeEach
    public void setup(){
         this.spec = new RequestSpecBuilder()
                .setBaseUri(this.BaseUrl)
                .addFilters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter()))
                .build();
    }


    protected Response createBooking(Booking body){

        Response response = given(spec)
                .when()
                .contentType(ContentType.JSON)
                .body(body)
                .post("booking");

        return response;
    }


    public String createToken(){

        JSONObject body = new JSONObject();
        body.put("username","admin");
        body.put("password","password123");



        Response response = given(spec)
                .contentType(ContentType.JSON)
                .body(body.toString())
                .log().all()
                .when()
                .post("/auth");

        return  response.jsonPath().getString("token");
    }



}

package Review;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Tasks {
    /**
     * Task 1
     * create a get request to zippo api us/90210
     * validate that first place's place name is Beverly Hills
     */
    @BeforeClass
    public void init(){
        RestAssured.baseURI = "http://api.zippopotam.us";
    }


    @Test
    public void task1() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("us/90210")
                .then()
                .log().body()
                .statusCode(200)
                .body("places[0].'place name'", equalTo("Beverly Hills"))
        ;
    }

    @Test
    public void task2(){
        given()
                .pathParam( "country", "us" )
                .pathParam( "zipcode", "90210" )
                .when()
                .get("/{country}/{zipcode}")
                .then()
                .body( "places", not( empty()) )
        ;
    }

        /** Task 2
         * create a get request to zippo api {country}/{zipcode}
         * provide any country and zipcode
         * validate that first places array is not empty
         **/
        @Test(dataProvider = "task3data")
    public  void task3(String placeName, String country, Integer zipCode){

            given()
                    .pathParam( "country", country )
                    .pathParam( "zipcode", zipCode )
                    .when()
                    .get("/{country}/{zipcode}")
                    .then()
                    .body( "places.'place name'", hasItem( placeName ) )
            ;
        }

    @DataProvider
    public Object[][] task3data() {
        return new Object[][] {
                {"Beverly Hills", "us", "90210"},
                {"Fort-de-France", "MQ", "97200"},
                {"Çiçekli Köyü", "TR", "01000"},
        };

        }

        /** Task 3
            * create data provide with 3 fields, place_name, country, zipcode
     * add at least 3 rows of data in your data provider
     * create a get request to zippo api {country}/{zipcode} that uses your data provider
     * validate that places array contains your place_name
     **/
    }

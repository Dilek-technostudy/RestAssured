import Pojo.Location;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Zippo {
    @BeforeClass
    public void init(){
        RestAssured.baseURI = "http://api.zippopotam.us";
    }

    @Test
    public void getZip(){
        Location location = given().
                when().
                get( "us/90210" ).
                then().
                statusCode( 200 ).
                log().body().
                extract().body().as( Location.class );

        System.out.println(location);
        assertThat(location.getPlaces(), not( empty() ));

    }

}

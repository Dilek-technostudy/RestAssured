import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class IntroductionTestsSolution {

    @Test
    public void simpleGetTest() {
        given().
                when().
                get( "http://api.zippopotam.us/us/90210" ).
                then().
                statusCode( 200 );
    }
    @Test
    public void simpleResponseTypeTest() {
        given().
                when().
                get( "http://api.zippopotam.us/us/90210" ).
                then().
                contentType( ContentType.JSON );
    }
}

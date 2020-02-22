
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class GoRestTest {
    //https://gorest.co.in/public-api/users?_format=json&access-token=j6XoJSutZrv-ikB-4X4_Zndi54_iqSZES-Ap
    @Test
    public void queryParamsTest(){
        given().
                log().uri()
                .param( "access-token", "j6XoJSutZrv-ikB-4X4_Zndi54_iqSZES-Ap" ).
                param( "_format", "json" ).
                when().
                get("https://gorest.co.in/public-api/users").
                then().
                log().status().
                log().body().
                body( "_meta.code", equalTo( 200 ) );
    }

    @Test
    public void basicAuthTest(){
        given()
                .auth()         // basic auth
                .preemptive()// basic auth
                .basic("j6XoJSutZrv-ikB-4X4_Zndi54_iqSZES-Ap", "") // basic auth
                .log().headers()
                .when()
                .get("https://gorest.co.in/public-api/users").
                then().
                log().status().
                log().body().
                body( "_meta.code", equalTo( 200 ) );
    }


    @Test
    public void oAuth2Test(){
        given()
                .auth()
                .oauth2("j6XoJSutZrv-ikB-4X4_Zndi54_iqSZES-Ap") // basic OAuth 2
                .log().headers()
                .when()
                .get("https://gorest.co.in/public-api/users").
                then().
                log().status().
                log().body().
                body( "_meta.code", equalTo( 200 ) );
    }

    @Test
    public void noAuthTest(){
        given()
                .when()
                .get("https://gorest.co.in/public-api/users").
                then().
                log().status().
                log().body().
                body( "_meta.code", equalTo( 401 ) );
    }


    }


import Pojo.Task7.Page;
import Pojo.Todo;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TaskSolution {


    /** Task 1
     * create a request to https://httpstat.us/203
     * expect status 203
     * expect content type text
     * **/
    @Test
    public void task1() {
        given().
                when().
                get( "https://httpstat.us/203" ).
                then().
                statusCode( 203 ).
                contentType( ContentType.TEXT ).
                body( equalTo( "203 Non-Authoritative Information" ) );
    }


    /** Task 2
     * create a request to https://httpstat.us/418
     * expect status 418
     * expect content type text
     * expect body to be equal to "418 I'm a teapot"
     * **/

    @Test
    public void task2() {
        given().
                when().
                get( "https://httpstat.us/418" ).
                then().
                statusCode( 418 ).
                contentType( ContentType.TEXT ).
                body( equalTo( "418 I'm a teapot" ) );
    }

    /** Task 3
     * create a request to https://jsonplaceholder.typicode.com/todos/2
     * expect status 200
     * expect content type json
     * expect title in response body to be "quis ut nam facilis et officia qui"
     * **/
    @Test
    public void task3() {
        given().
                when().
                get( "https://jsonplaceholder.typicode.com/todos/2" ).
                then().
                statusCode( 200 ).
                contentType( ContentType.JSON ).
                body( "title", equalTo( "quis ut nam facilis et officia qui" ) );
    }

    @Test
    public void task3alternative() {
        String title = given().
                when().
                get( "https://jsonplaceholder.typicode.com/todos/2" ).
                then().
                statusCode( 200 ).
                contentType( ContentType.JSON ).
                extract().path( "title" );
        Assert.assertEquals( title, "quis ut nam facilis et officia qui" );
    }
    /** Task 4
     * create a request to https://jsonplaceholder.typicode.com/todos/2
     * expect status 200
     * expect content type json
     * expect response completed status to be false
     * **/
    @Test
    public void task4() {
        given().
                when().
                get( "https://jsonplaceholder.typicode.com/todos/2" ).
                then().
                statusCode( 200 ).
                contentType( ContentType.JSON ).
                body( "completed", equalTo( false ) );
    }

    @Test
    public void task4alternative() {
        Boolean status = given().
                when().
                get( "https://jsonplaceholder.typicode.com/todos/2" ).
                then().
                statusCode( 200 ).
                contentType( ContentType.JSON ).
                extract().path( "completed" );
        Assert.assertFalse( status );
    }

    /** Task 5
     * create a request to https://reqres.in/api/users?page=2
     * expect status 200
     * expect content type json
     * expect data to contain first name "George"
     * **/
    @Test
    public void task5() {
        given().
                when().
                get( "https://reqres.in/api/users?page=2" ).
                then().
                statusCode( 200 ).
                contentType( ContentType.JSON ).
                body( "data.first_name", hasItem( "George" ) );
    }
    /** Task 6
     * create a request to https://jsonplaceholder.typicode.com/todos/2
     * expect status 200
     * expect content type json
     * create a pojo and extract it from response body
     * expect completed property of your pojo to be false
     * **/
    @Test
    public void task6() {
        Todo todo = given().
                when().
                get( "https://jsonplaceholder.typicode.com/todos/2" ).
                then().
                statusCode( 200 ).
                contentType( ContentType.JSON ).
                extract().as( Todo.class );
        System.out.println(todo);
        Assert.assertFalse( todo.getCompleted() );
    }

    /** Task 7
     * create a request to https://reqres.in/api/users?page=2
     * expect status 200
     * expect content type json
     * create a pojos to extract response body
     * expect "data" property of your pojo to be not empty
     * **/
    @Test
    public void task7() {
        Page page2 = given().
                when().
                get( "https://reqres.in/api/users?page=2" ).
                then().
                log().body().
                statusCode( 200 ).
//                body( "data", not( empty() ) ).
        contentType( ContentType.JSON ).
                        extract().as( Page.class );

//        Assert.assertNotEquals( page2.getData().size(), 0, "Data should not be empty");

        assertThat( page2.getData(), not( empty() ));
    }

}

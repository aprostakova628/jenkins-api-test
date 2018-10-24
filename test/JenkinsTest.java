
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class JenkinsTest {

    String baseurl = "http://ec2-35-157-74-160.eu-central-1.compute.amazonaws.com:8080";
    String login = "apitestuser";
    String password = "12345aA";
    String wrongpassword = "12345";

    @Test
    public void JenkinsLogInPositive(){
        given().log().all().contentType("application/json").auth().preemptive().
                basic(login, password).
                when().get(baseurl + "/api/json").
                then().statusCode(200);

    }
    @Test
    public void JenkinsLogInWrongPasswordNegative(){
        given().log().all().contentType("application/json").auth().preemptive().
                basic(login, wrongpassword).
                when().get(baseurl + "/api/json").
                then().statusCode(401);

    }
    @Test
    public void JenkinsLogInNoPasswordNegative(){
        given().log().all().contentType("application/json");
                when().get(baseurl + "/api/json").
                then().statusCode(403);
    }
    @Test
    public void JenkinsGetJenkinsClassPositive(){
        given().log().all().contentType("application/json").auth().preemptive().
                basic(login, password).
                when().get(baseurl + "/api/json").
                then().log().all().body("_class", equalTo("hudson.model.Hudson"));

    }
}

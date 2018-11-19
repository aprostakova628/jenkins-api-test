
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class JenkinsTest {

    String baseurl = "http://localhost:8080";
    String login = "admin";
    String password = "Defaultpassw0rd";
    String wrongpassword = "12345";

    @Test
    public void JenkinsLogInPositive(){
        given().log().all().contentType("application/json").auth().preemptive().
                basic(login, password).
                when().get(baseurl + "/api/json").
                then().log().all().statusCode(200);

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
    public void JenkinsGetJenkinsLabelsPositive(){
        given().log().all().contentType("application/json").auth().preemptive().
                basic(login, password).
                when().get(baseurl + "/api/json").
                then().body("assignedLabels.name", hasItems("master"));

    }

    @Test
    public void JenkinsGetJenkinsNodeDescriptionPositive(){
        given().log().all().contentType("application/json").auth().preemptive().
                basic(login, password).
                when().get(baseurl + "/api/json").
                then().body("nodeDescription", equalTo("the master Jenkins node"));

    }
    @Test
    public void JenkinsGetTestJobPositive() {
        given().log().all().contentType("application/json").auth().preemptive().
                basic(login, password).
                when().get(baseurl + "/api/json").
                then().body("jobs.name", hasItems("test"));
    }


}

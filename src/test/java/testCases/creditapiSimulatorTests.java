package testCases;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class creditapiSimulatorTests {

    @Test(priority = 0)
    public void test_getAllRecordedSimulations(){
        Response response = given()
                .when().get("http://localhost:8088/api/v1/simulations")
                .then()
                .statusCode(200).extract().response();

        System.out.println("Status code: "+response.statusLine());
        System.out.println("Response: "+response.asPrettyString());

    }

    @Test(priority = 1)
    public void test_addNewRecord(){
        HashMap data = new HashMap();
        data.put("amount","1700");
        data.put("cpf", "8103000678");
        data.put("email","tooy.highy@gmail.com");
        data.put("installments","4");
        data.put("insurance","true");
        data.put("name", "lanei jovwe");


        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("http://localhost:8088/api/v1/simulations")
                .then()
                .statusCode(201);

    }

    @Test(priority = 2)

            public void test_getSimulation(){
         given()
                .when()
                .get("http://localhost:8088/api/v1/simulations/8103000678")
                .then()
                .statusCode(200);

    }

    @Test(priority = 3)
    public void test_updateSimulation(){
        HashMap data = new HashMap();
        data.put("amount","1500");
        data.put("cpf", "8103000678");
        data.put("email","toy.high@gmail.com");
        data.put("installments","2");
        data.put("insurance","true");
        data.put("name", "Laney Jovwe");

        Response resp = given()
                .contentType("application/json")
                .body(data)
                .when()
                .put("http://localhost:8088/api/v1/simulations/8103000678")
                .then()
                .statusCode(200).extract().response();
        System.out.println("Response body : "+resp.getBody().prettyPrint());

    }

    @Test(priority = 4)
    public void test_deleteSimulation(){
        given().when().delete("http://localhost:8088/api/v1/simulations/8103000678")
                .then()
                .statusCode(204);
    }


}

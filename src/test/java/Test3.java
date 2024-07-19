import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.specification.RequestSpecification;

import org.junit.jupiter.api.Test;
import java.io.File;

import static io.restassured.RestAssured.given;

public class Test3 {
    private RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("https://petstore.swagger.io")
            .setBasePath("/v2")
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    private RequestSpecification requestSpec2 = new RequestSpecBuilder()
            .setBaseUri("https://petstore.swagger.io")
            .setBasePath("/v2")
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.MULTIPART)
            .log(LogDetail.ALL)
            .build();

    private RequestSpecification requestSpec3 = new RequestSpecBuilder()
            .setBaseUri("https://petstore.swagger.io")
            .setBasePath("/v2")
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.URLENC)
            .log(LogDetail.ALL)
            .build();


    @Test
    void findStatus() {

        given()
                .spec(requestSpec)

                .when()
                .get("/pet/findByStatus")

                .then()
                .statusCode(200);
    }
    @Test
    void appNewPet() throws Exception{
        String filePath = "E:/space/IdeaProjects/autotest/src/test/resources/body.json";
        String requestBody = new String(Files.readAllBytes(Paths.get(filePath)));

        given()
                .spec(requestSpec)
                .body(requestBody)

                .when()
                .post("/pet/")

                .then()
                .statusCode(200);

    }

    @Test
    void updatePet() {

        given()
                .spec(requestSpec3)
                .body("name=dog&status=sold")

                .when()
                .post("/pet/102/")

                .then()
                .statusCode(200);

    }

    @Test
    void findPetID() {

        given()
                .spec(requestSpec)

                .when()
                .get("/pet/102")

                .then()
               //.statusCode(200)
                .statusCode(404);

    }

    @Test
    void uploadImage()  {


        given()
                .spec(requestSpec2)
                .multiPart("file", new File("scale_1200.jpg"))

                .when()
                .post("/pet/9222968140497184000/uploadImage/")


                .then()
                .statusCode(200);
    }



    @Test
    void updateNewPet() throws Exception{
        String filePath = "E:/space/IdeaProjects/autotest/src/test/resources/body.json";
        String requestBody = new String(Files.readAllBytes(Paths.get(filePath)));

        given()
                .spec(requestSpec)
                .body(requestBody)

                .when()
                .put("/pet/")

                .then()
                .statusCode(200);

    }

    @Test
    void deletePet(){

        given()
                .spec(requestSpec)

                .when()
                .delete("/pet/102")

                .then()
                .statusCode(200);
    }

    @Test
    void returnPetStatus(){

        given()
                .spec(requestSpec)

                .when()
                .get("/store/inventory")

                .then()
                .statusCode(200);
    }

    @Test
    void findOrderID(){

        given()
                .spec(requestSpec)

                .when()
                .get("/store/order/4")

                .then()
                .statusCode(404);
    }

    @Test
    void orderPet() throws Exception{
        String filePath = "E:/space/IdeaProjects/autotest/src/test/resources/pet.order.json";
        String requestBody = new String(Files.readAllBytes(Paths.get(filePath)));

        given()
                .spec(requestSpec)
                .body(requestBody)

                .when()
                .post("/store/order")

                .then()
                .statusCode(200);

    }
    @Test
    void deleteOrderID(){

        given()
                .spec(requestSpec)

                .when()
                .delete("/store/order/105")

                .then()
                .statusCode(404);
    }

    @Test
    void findUser(){

        given()
                .spec(requestSpec)

                .when()
                .get("/user/user1")

                .then()
                .statusCode(200);
    }

    @Test
    void logsUser(){

        given()
                .spec(requestSpec)

                .when()
                .get("/user/login?username=user1&password=123456789")

                .then()
                .statusCode(200);
    }

    @Test
    void logoutUser(){

        given()
                .spec(requestSpec)

                .when()
                .get("/user/logout")

                .then()
                .statusCode(200);
    }

    @Test
    void listUser() throws Exception {
        String filePath = "E:/space/IdeaProjects/autotest/src/test/resources/list.user.json";
        String requestBody = new String(Files.readAllBytes(Paths.get(filePath)));

        given()
                .spec(requestSpec)
                .body(requestBody)

                .when()
                .post("/user/createWithList")

                .then()
                .statusCode(200);
    }

    @Test
    void createArrayUser() throws Exception {
        String filePath = "E:/space/IdeaProjects/autotest/src/test/resources/list.user.json";
        String requestBody = new String(Files.readAllBytes(Paths.get(filePath)));

        given()
                .spec(requestSpec)
                .body(requestBody)

                .when()
                .post("/user/createWithArray")

                .then()
                .statusCode(200);
    }

    @Test
    void createUser() throws Exception {
        String filePath = "E:/space/IdeaProjects/autotest/src/test/resources/another.user.json";
        String requestBody = new String(Files.readAllBytes(Paths.get(filePath)));

        given()
                .spec(requestSpec)
                .body(requestBody)

                .when()
                .post("/user/")

                .then()
                .statusCode(200);
    }

    @Test
    void updateUser() throws Exception {
        String filePath = "E:/space/IdeaProjects/autotest/src/test/resources/another.user.json";
        String requestBody = new String(Files.readAllBytes(Paths.get(filePath)));

        given()
                .spec(requestSpec)
                .body(requestBody)

                .when()
                .put("/user/string/")

                .then()
                .statusCode(200);
    }

    @Test
    void deleteUser(){

        given()
                .spec(requestSpec)

                .when()
                .delete("/user/user1")

                .then()
                .statusCode(404);
    }
}

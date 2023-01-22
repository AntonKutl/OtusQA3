package services;

import dto.petdto.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class PetAPI {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String BASE_PATH = "/pet";
    private static final String GET_PATH = "/pet/{id}";
    private RequestSpecification spec;
    private ResponseSpecification responseSpecification;

    public PetAPI(){
        spec = given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON);
    }

    public ValidatableResponse createUser(Pet pet){
        return   given(spec)
                .basePath(BASE_PATH)
                .body(pet)
                .log().all()
                .when()
                .post()
                .then()
                .log().all();
    }


    public ValidatableResponse getPet(int id){
        return   given(spec)
                .get(GET_PATH,id)
                .then()
                .log().all();
    }
}

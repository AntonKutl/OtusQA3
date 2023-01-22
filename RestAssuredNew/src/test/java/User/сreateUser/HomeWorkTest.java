package User.сreateUser;

import dto.petdto.Category;
import dto.petdto.Pet;
import dto.petdto.Tag;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import services.PetAPI;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;


public class HomeWorkTest {
    @Test
    /**
     * Добавление питомца и получение статус кода 200
     */
    public void addPet() {
        PetAPI petAPI=new PetAPI();

        Pet pet = Pet.builder()
                .mId(1L)
                .mCategory(Category.builder()
                        .mId(1L)
                        .mName("Bird")
                        .build())
                .mName("Falcon")
                .mPhotoUrls(Arrays.asList(
                        "example1",
                        "example2"
                ))
                .mTags(Arrays.asList(
                        Tag.builder()
                                .mId(1L)
                                .mName("qwer")
                                .build()
                ))
                .mStatus("available")
                .build();

        ValidatableResponse response = petAPI.createUser(pet);
        response.statusCode(200);
    }

    @Test
    /**
     * Добавление питомца и сравнение Json схемы ответа
     */
    public void assertResponsePet() {
        PetAPI petAPI=new PetAPI();

        Pet pet = Pet.builder()
                .mId(1L)
                .mCategory(Category.builder()
                        .mId(1L)
                        .mName("Bird")
                        .build())
                .mName("Falcon")
                .mPhotoUrls(Arrays.asList(
                        "example1",
                        "example2"
                ))
                .mTags(Arrays.asList(
                        Tag.builder()
                                .mId(1L)
                                .mName("qwer")
                                .build()
                ))
                .mStatus("available")
                .build();

        ValidatableResponse response = petAPI.createUser(pet);
        response.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/pet.json"));
    }

    @Test
    /**
     * Получение карточки питомуа и его проверка body
     */
    public void getPet() {
        PetAPI petAPI=new PetAPI();
        ValidatableResponse response = petAPI.getPet(10);

        response
                .header( "content-type","application/json")
                .body("name", equalTo("doggie"))
                .statusCode(200);

    }

    @Test
    /**
     * Получение карточки питомца и проверка статуса
     */
    public void negativeGet() {
        PetAPI petAPI=new PetAPI();
        ValidatableResponse response = petAPI.getPet(-1);
        response
                .statusCode(404);

    }
}

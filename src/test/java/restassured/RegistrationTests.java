package restassured;

import Helpers.Helper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class RegistrationTests implements Helper {
    String endpoint = "/v1/user/registration/usernamepassword";
    @BeforeMethod
    public void precondition(){
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
    }
    @Test
    public void RegistrationPositive(){

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("vister" + i +"@mail.com")
                .password("Rr12345$")
                .build();
        given()
                .body(requestDTO) //тело (body)
                .contentType(ContentType.JSON)// тип JSON
                .when() // когда
                .post(endpoint) // мы направляем запрос на endpoint
                .then() //тогда
                .assertThat().statusCode(200);
    }

    @Test
    public void RegistrationWrongEmailNegative(){

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("vasya" + i +"mail.com")
                .password("Rr12675$")
                .build();
        ErrorDTO errorDTO = given()
                .body(requestDTO)
                .contentType(ContentType.JSON)// тип JSON
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(400)
                .extract()
                .as(ErrorDTO.class);

        System.out.println(errorDTO.getStatus() + " ==== "
                + errorDTO.getMessage() + " ==== " + errorDTO.getError());
    }

    @Test
    public void RegistrationDuplicateNegative(){

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("rita@mail.com")
                .password("Rr12345$")
                .build();
        ErrorDTO errorDTO = given()
                .body(requestDTO)
                .contentType(ContentType.JSON)// тип JSON
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(409)
                .extract()
                .as(ErrorDTO.class);

        System.out.println(errorDTO.getStatus() + " ==== "
                + errorDTO.getMessage() + " ==== " + errorDTO.getError());
    }
}

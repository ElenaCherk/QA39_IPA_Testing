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

public class LoginTests implements Helper {
    String endpoint = "/v1/user/login/usernamepassword";
    @BeforeMethod
    public void precondition(){
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
    }
    @Test
    public void loginPositive(){

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("rita@mail.com")
                .password("Rr12345$")
                .build();

        AuthResponseDTO responseDTO = given() //дано
                .body(requestDTO) //тело (body)
                .contentType(ContentType.JSON)// тип JSON
                .when() // когда
                .post(endpoint) // мы направляем запрос на endpoint
                .then() //тогда
                .assertThat().statusCode(200) // утверждаю что статусКод будет 200
                .extract() // если это так, то вытаскиваю полученный ответ
                .as(AuthResponseDTO.class); //в виде класса AuthResponseDTO
        System.out.println(responseDTO.getToken()); // вытаскиваем токен
   }
    @Test
    public void loginNegativeWrongEmail(){
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("ritamail.com")
                .password("Rr12345$")
                .build();

        ErrorDTO errorDTO = given() //дано
                .body(requestDTO) //тело (body)
                .contentType(ContentType.JSON)// тип JSON
                .when() // когда
                .post(endpoint) // мы направляем запрос на endpoint
                .then() //тогда
                .assertThat().statusCode(401) // утверждаю что статусКод будет 401
                .extract() // если это так, то вытаскиваю полученный ответ
                .as(ErrorDTO.class); //в виде класса ErrorDTO

        System.out.println(errorDTO.getMessage()); // вытаскиваем сообщение
    }
}

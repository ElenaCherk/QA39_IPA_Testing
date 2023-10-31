package restassured;

import Helpers.Helper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import dto.ContactResponseDTO;
import dto.ErrorDTO;
import okhttp3.Request;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class DeleteContactByIDTests implements Helper {
    ContactDTO contactDTO;
    String endpoint = "/v1/contacts";
    String id;

    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";

        ContactDTO contactDTO = ContactDTO.builder()
                .name("QA39_RA")
                .lastName("Automation")
                .email("vasya39" + i + "@mail.com")
                .phone("12364678" + i)
                .address("Rehovot")
                .description("Students")
                .build();

        String message = given()
                .header(authHeader, token)
                .body(contactDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .path("message");
        id = message.substring(message.lastIndexOf(" ") + 1);
    }

    @Test
    public void deleteContactByIDTestsPositive() {

        Request request = new Request.Builder()
                .url(baseURL + endpoint + "/" + id)
                .addHeader("Authorization", token)
                .delete()
                .build();

        ContactResponseDTO contactResponseDTO = given()
                .header(authHeader, token)
                .body(request)
                .contentType(ContentType.JSON)
                .when()
                .delete(endpoint + "/" + id)
                .then()
                .assertThat().statusCode(200)
          //      .assertThat().body("message", containsString("Contact was deleted!"))
                .extract()
                .as(ContactResponseDTO.class);
        System.out.println(contactResponseDTO.getMessage());
    }
    @Test
    public void deleteContactByIDTestsWrongIDNegative() {

        Request request = new Request.Builder()
                .url(baseURL + endpoint + "/" + id)
                .addHeader("Authorization", token)
                .delete()
                .build();

        ErrorDTO errorDTO = given()
                .header(authHeader, token)
                .body(request)
                .contentType(ContentType.JSON)
                .when()
                .delete(endpoint + "/" + id + 1)
                .then()
                .assertThat().statusCode(400)
                //      .assertThat().body("message", containsString("Contact was deleted!"))
                .extract()
                .as(ErrorDTO.class);
        System.out.println(errorDTO.getMessage());
    }
}
package restassured;

import Helpers.Helper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import dto.ContactListDTO;
import okhttp3.Request;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;


public class GetAllContactsTests implements Helper {
    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
    }
    String endpoint = "/v1/contacts";
    @Test
    public void GetAllContactsPositive(){
        Request request = new Request.Builder()
                .url(baseURL+endpoint)
                .addHeader("Authorization", token)
                .build();

        ContactListDTO contacts = given()
                .header(authHeader, token)
                .body(request)
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(ContactListDTO.class);
        for(ContactDTO contactDTO : contacts.getContacts()){
            System.out.println(contactDTO.getId());
            System.out.println(contactDTO.getEmail());
            System.out.println(contactDTO.getName());
            System.out.println(contactDTO.getLastName());
            System.out.println("================================================================");
        }
        }
    }


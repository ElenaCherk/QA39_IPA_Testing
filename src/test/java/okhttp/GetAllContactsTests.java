package okhttp;

import Helpers.Helper;
import dto.ContactDTO;
import dto.ContactListDTO;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetAllContactsTests implements Helper {
    String endpoint = "/v1/contacts";
    @Test
    public void GetAllContactsPositive() throws IOException {
        Request request = new Request.Builder()
                .url(baseURL+endpoint)
                .addHeader("Authorization", token)
                .build();

        Response response = client.newCall(request).execute();
        ContactListDTO contacts = gson.fromJson(response.body().string(), ContactListDTO.class);
        for(ContactDTO contactDTO : contacts.getContacts()){
            System.out.println(contactDTO.getId());
            System.out.println(contactDTO.getEmail());
            System.out.println(contactDTO.getName());
            System.out.println("================================================================");
        }
    }
}

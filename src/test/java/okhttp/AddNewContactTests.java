package okhttp;
import Helpers.Helper;
import dto.ContactDTO;
import dto.ContactResponseDTO;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddNewContactTests implements Helper {
    String endpoint = "/v1/contacts";
    @Test
    public void addNewContactPositive() throws IOException {
        ContactDTO contactDTO = ContactDTO.builder() // создаем контакнт, чтобы поместить его в BODY
                .name("QA39")
                .lastName("Automation")
                .email("qa39"+i+"@mail.com")
                .phone("12345678"+i)
                .address("Rehovot")
                .description("Students")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(contactDTO), JSON);

        Request request = new Request.Builder()
                .url(baseURL + endpoint)
                .addHeader("Authorization", token)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        ContactResponseDTO contactResponseDTO = gson.fromJson(response.body().string(), ContactResponseDTO.class);

        String message = contactResponseDTO.getMessage();
        System.out.println(message);
        String id = message.substring(message.lastIndexOf(" ") + 1); // найдет индекс последнего пробела
        // и относительного него сместится на 1 и метод substring вернет нам оставшуюся строчку до конца
        System.out.println(id);
    }
}

package okhttp;

import Helpers.Helper;
import com.google.gson.Gson;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
public class RegistrationTests implements Helper {
    String endpoint = "/v1/user/registration/usernamepassword";
        @Test
        public void UpdateContactPositive() throws IOException {

            AuthRequestDTO requestDTO = AuthRequestDTO.builder() // формируем запрос Body
                    .username("fortuna" + i + "@mail.com")
                    .password("Rr147655$")
                    .build();

            RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);

            Request request = new Request.Builder()
                    .url(baseURL + endpoint)
                    .post(requestBody)
                    .build();

            Response response = client.newCall(request).execute();

            Assert.assertTrue(response.isSuccessful());
        }
    @Test
    public void RegistrationNegativeWrongEmail() throws IOException {

        AuthRequestDTO requestDTO = AuthRequestDTO.builder() // формируем запрос Body
                .username("fortuna" + i + "mail.com")
                .password("Rr147655$")
                .build();
        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);
        Request request = new Request.Builder()
                .url(baseURL + endpoint)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);
        Assert.assertFalse(response.isSuccessful());
        System.out.println("Response code is ----> " + response.code());
        System.out.println(errorDTO.getStatus() + " ==== "
                        + errorDTO.getMessage() + " ==== " + errorDTO.getError());
    }
}


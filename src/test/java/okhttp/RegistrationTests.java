package okhttp;

import com.google.gson.Gson;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
public class RegistrationTests {
        public static final MediaType JSON = MediaType.get("application/json;charset=utf-8");
        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();
        String baseURL = "https://contactapp-telran-backend.herokuapp.com";
        @Test
        public void RegistrationPositive() throws IOException {
            int i = (int)(System.currentTimeMillis()/1000)%3600;
            AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                    .username("fortuna" + i + "@mail.com")
                    .password("Rr147655$")
                    .build();

            RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);

            Request request = new Request.Builder()
                    .url(baseURL + "/v1/user/registration/usernamepassword")
                    .post(requestBody)
                    .build();

            Response response = client.newCall(request).execute();

            if(response.isSuccessful()){
                String responseJson = response.body().string();
                AuthResponseDTO responseDTO = gson.fromJson(responseJson, AuthResponseDTO.class);
                System.out.println("Response code is ----> " + response.code());
                System.out.println(responseDTO.getToken());
                Assert.assertTrue(response.isSuccessful());
            } else {
                ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);
                System.out.println("Response code is ----> " + response.code());
                System.out.println(errorDTO.getStatus() + " ==== "
                        + errorDTO.getMessage() + " ==== " + errorDTO.getError());
                Assert.assertFalse(response.isSuccessful());
            }
        }
    }


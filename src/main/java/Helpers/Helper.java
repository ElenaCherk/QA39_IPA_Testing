package Helpers;

import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
public interface Helper {
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoicml0YUBtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjk5MjY2ODIwLCJpYXQiOjE2OTg2NjY4MjB9.2NFbYECPjFqAr5u4TxwH6u_WgvupBm9K3PHfQRYxNxo";
    public static final MediaType JSON = MediaType.get("application/json;charset=utf-8");
    int i = (int)(System.currentTimeMillis()/1000)%3600;
    // int i = new Random().nextInt(1000)+1000;
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    String baseURL = "https://contactapp-telran-backend.herokuapp.com";
    String authHeader = "Authorization";
}

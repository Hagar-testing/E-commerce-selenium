package com.ecommerce.api;


import com.ecommerce.objects.User;
import com.ecommerce.utils.UserUtils;
import io.restassured.response.Response;
import static com.ecommerce.constants.EndPointConstant.LOGIN_API;
import static com.ecommerce.utils.ConfigUtils.getBaseUrl;
import static io.restassured.RestAssured.given;

public class LoginAPI {

    private String accessToken;
    private String userId;


    public String getAccessToken() {
        return accessToken;
    }

    public String getUserId() {
        return userId;
    }

    public void login(String email, String password) {

        User user = new User(email,password);

        Response response =
                given()
                        .baseUri(getBaseUrl())
                        .header("Content-Type", "application/json")
                        .body(user)
                        .log().all()
                        .when()
                        .post(LOGIN_API)
                        .then()
                        .log().all()
                        .extract().response();

        if(response.statusCode() != 200){
            throw new RuntimeException("Something went wrong");
        }
        accessToken = response.path("token");
        userId = response.path("userId");


    }
}

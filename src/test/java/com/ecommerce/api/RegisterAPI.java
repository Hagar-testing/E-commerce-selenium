package com.ecommerce.api;


import com.ecommerce.objects.User;
import com.ecommerce.utils.UserUtils;
import io.restassured.response.Response;
import static com.ecommerce.constants.EndPointConstant.REGISTER_API;
import static com.ecommerce.utils.ConfigUtils.getBaseUrl;
import static io.restassured.RestAssured.given;

public class RegisterAPI {


    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void register() {

        User user = UserUtils.generateRandomUser();

        Response response =
                given()
                    .baseUri(getBaseUrl())
                    .header("Content-Type", "application/json")
                    .body(user)
                    .log().all()
                .when()
                    .post(REGISTER_API)
                .then()
                    .log().all()
                    .extract().response();

        if(response.statusCode() != 200){
            throw new RuntimeException("Something went wrong");
        }
        email = user.getUserEmail();
        password = user.getUserPassword();


    }
}

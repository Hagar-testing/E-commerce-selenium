package com.ecommerce.utils;

import com.ecommerce.objects.User;
import com.github.javafaker.Faker;

import java.util.Locale;

public class UserUtils {

    public static User generateRandomUser() {

        Faker faker = new Faker();
        // Generate fake user data
        String confirmPassword = faker.internet().password();
        String firstName = faker.name().firstName();
        String gender = faker.demographic().sex();
        String lastName = faker.name().lastName();
        String occupation = faker.job().title();
        boolean required = faker.random().nextBoolean();
        String userEmail = faker.internet().emailAddress();
        String userMobile = faker.numerify("##########");
        String userPassword = faker.regexify("[A-Z]+[a-z]+[0-9]+[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>/?]+.{4}");
        String userRole = "customer";

        return new User(confirmPassword,
                firstName,
                gender,
                lastName,
                occupation,
                required,
                userEmail,
                userMobile,
                userPassword,
                userRole);
    }
}

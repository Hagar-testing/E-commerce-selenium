package com.ecommerce.objects;

public class User {
    private String confirmPassword;
    private String firstName;
    private String gender;
    private String lastName;
    private String occupation;
    private boolean required;
    private String userEmail;
    private String userMobile;
    private String userPassword;
    private String userRole;


    public User(String confirmPassword,
                String firstName,
                String gender,
                String lastName,
                String occupation,
                boolean required,
                String userEmail,
                String userMobile,
                String userPassword,
                String userRole) {
        this.confirmPassword = confirmPassword;
        this.firstName = firstName;
        this.gender = gender;
        this.lastName = lastName;
        this.occupation = occupation;
        this.required = required;
        this.userEmail = userEmail;
        this.userMobile = userMobile;
        this.userPassword = userPassword;
        this.userRole = userRole;
    }

    public User(String userEmail,
                String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    // Getter and Setter methods for confirmPassword
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    // Getter and Setter methods for firstName
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter and Setter methods for gender
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // Getter and Setter methods for lastName
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Getter and Setter methods for occupation
    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    // Getter and Setter methods for required
    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    // Getter and Setter methods for userEmail
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    // Getter and Setter methods for userMobile
    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    // Getter and Setter methods for userPassword
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    // Getter and Setter methods for userRole
    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}

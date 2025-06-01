package com.example.e_commercewithapi.utils;

import java.util.regex.Pattern;

public class ValidationExtenstions {
    public static boolean isValidEmail(String email) {


        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";


        Pattern p = Pattern.compile(emailRegex);


        return email != null && p.matcher(email).matches();
    }
    public static Boolean isEmailValid(String email) {
        return !email.isEmpty() && isValidEmail(email);
    }

    public static Boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

}
